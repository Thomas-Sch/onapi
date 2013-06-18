/* ============================================================================
 * Nom du fichier   : Map.java
 * ============================================================================
 * Date de création : 1 mai 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package game.models.map;

import game.models.Entity;
import game.models.GameModel;
import game.models.Spawner;
import game.models.Team;

import java.util.LinkedList;

import client.GameData;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

/**
 * La map est une grille dans laquelle on place les murs et autres éléments de
 * la carte (spawners, sortie...).
 * 
 * La map générée est (3 * nb de teams) x (3 * nb de teams) sommets de
 * labyrinthe, soit une grille carrée de 6 * nb de teams + 1 cellules de côté.
 * 
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 * 
 */
public class Map extends Entity {

   /**
    * Grille de la map
    */
   private Tile[][] grid;

   /**
    * Liste des spawners pour faire apparaître les joueurs
    */
   private LinkedList<Spawner> spawners = new LinkedList<Spawner>();

   /**
    * Position réelle de la porte de sortie
    */
   private Vector2 exitPos = new Vector2();

   // Représentations physiques des cases de la grille
   private Body[][] wallBodies;
   private Rectangle[][] bounds;

   // Textures pour l'affichage
   private Texture textureMur;
   private Texture textureSol;

   @Override
   public void loadResources() {
      textureSol = new Texture(Gdx.files.internal("data/sol.png"));
      textureMur = new Texture(Gdx.files.internal("data/mur.jpg"));
   }

   public Map(GameModel game, Team[] teams) {
      super(game);

      // Crée la map et ajoute les spawners
      MazeGenerator generator = new MazeGenerator();
      setGrid(generator.generateMaze(teams.length * 6));
      spawners = generator.generateSpawners(teams, this);

      // Définit la consistance physique des murs
      wallBodies = new Body[grid.length][grid.length];
      bounds = new Rectangle[grid.length][grid.length];

      // Initialise les murs et la sortie
      for (int i = 0; i < grid.length; i++) {
         for (int j = 0; j < grid[i].length; j++) {
            Tile t = grid[i][j];
            bounds[i][j] = new Rectangle(j * Tile.WIDTH, (grid.length - i - 1)
                  * Tile.HEIGHT, Tile.WIDTH, Tile.HEIGHT);
            switch (t) {
               case WALL:
                  BodyDef bodyDef = new BodyDef();
                  bodyDef.type = BodyType.StaticBody;
                  bodyDef.position.set(bounds[i][j].x + bounds[i][j].width / 2,
                        bounds[i][j].y + bounds[i][j].height / 2);
                  Body body = game.getWorld().createBody(bodyDef);
                  PolygonShape shape = new PolygonShape();
                  shape.setAsBox(bounds[i][j].width / 2,
                        bounds[i][j].height / 2);
                  FixtureDef fix = new FixtureDef();
                  fix.shape = shape;
                  fix.density = 0.4f;
                  fix.friction = 0.5f;
                  fix.restitution = 0.8f;
                  body.createFixture(fix);
                  wallBodies[i][j] = body;
                  // body.setTransform(0, bounds[i][j].y,0);
                  body.setUserData(Tile.WALL);
                  break;
               case EXIT:
                  Vector2.tmp2.set(getRealPos(i, j));
                  Vector2.tmp2.add(-Tile.WIDTH / 3, Tile.HEIGHT / 3);
                  exitPos.set(Vector2.tmp2);
                  break;
               default:
                  break;
            }

         }
      }

      System.out.println("Generated map :\n" + this);
   }

   @Override
   public void init(GameData initData) {
      // TODO Auto-generated method stub
   }

   /**
    * @return Grille représentant la map
    */
   public Tile[][] getGrid() {
      return grid;
   }

   /**
    * @param grid
    *           Grille représentant la map
    */
   public void setGrid(Tile[][] grid) {
      this.grid = grid;
   }

   /**
    * @return Liste des spawners pour faire apparaître les joueurs
    */
   public LinkedList<Spawner> getSpawners() {
      return spawners;
   }

   /**
    * @param x
    *           Position x dans la grille de la case voulue
    * @param y
    *           Position y dans la grille de la case voulue
    * @return Rectangle représentant la position et la taille d'une case (tile)
    *         de la grille.
    */
   public Rectangle getWallBounds(int x, int y) {
      return bounds[x][y];
   }

   /**
    * @return Position réelle de la porte de sortie
    */
   public Vector2 getExitPos() {
      return exitPos;
   }

   /**
    * @param x
    *           Position x dans la grille de la case voulue
    * @param y
    *           Position y dans la grille de la case voulue
    * @return Coordonnées réelles du centre de la case voulue sur la map
    */
   public Vector2 getRealPos(int x, int y) {
      return new Vector2((0.5f + y) * Tile.WIDTH, (grid.length - 0.5f - x)
            * Tile.HEIGHT);
   }

   /**
    * @param x
    *           Position réelle x sur la map
    * @param y
    *           Position réelle y sur la map
    * @return Coordonnées de la case correspondante à la position données
    */
   public Vector2 getGridCoord(float x, float y) {
      return new Vector2((float) Math.floor(y / Tile.WIDTH - 0.f),
            (int) Math.floor(grid.length - 0.5f - (x / Tile.HEIGHT)));
   }

   @Override
   public void debugRender(ShapeRenderer renderer) {
   }

   @Override
   public void draw(SpriteBatch batch, float parentAlpha) {
      super.draw(batch, parentAlpha);

      // Répète la texture
      textureSol.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);
      textureMur.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);
      for (int i = 0; i < grid.length; i++) {
         for (int j = 0; j < grid[i].length; j++) {
            if (grid[i][j] == Tile.WALL) {
               // Dessine un mur
               batch.draw(textureMur, bounds[i][j].x, bounds[i][j].y,
                     bounds[i][j].width, bounds[i][j].height, 0f, 0f, 1f, 1f);
            }
            else {
               // Dessine un sol
               batch.draw(textureSol, bounds[i][j].x, bounds[i][j].y,
                     bounds[i][j].width, bounds[i][j].height, 0f, 0f, 8f, 8f);

            }
         }
      }

   }

   /**
    * Crée une séparation à afficher dans la console
    * 
    * @param length
    *           Longueur de la séparation
    * @return La chaîne à afficher
    */
   private String separation(int length) {
      StringBuffer sb = new StringBuffer();
      sb.append("+");
      for (int j = 0; j < length; j++) {
         sb.append("-");
      }
      sb.append("+\n");
      return sb.toString();
   }

   /**
    * Utilisé pour le debug
    */
   @Override
   public String toString() {

      StringBuffer sb = new StringBuffer();

      sb.append(separation(grid.length));
      for (int i = 0; i < grid.length; i++) {
         sb.append("|");
         for (int j = 0; j < grid[i].length; j++) {
            sb.append(grid[i][j]);
         }
         sb.append("|\n");
      }
      sb.append(separation(grid.length));
      return sb.toString();
   }

   /**
    * @return Le nombre de cases en largeur et hauteur de la map
    */
   public int getSize() {
      return grid.length;
   }

   @Override
   public void update(float deltaTime) {
   }

}
