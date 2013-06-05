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
import game.models.Spawner;
import game.models.Team;

import java.util.LinkedList;

import org.omg.CORBA.Bounds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

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
public class Map extends Entity implements ContactListener{

   private Tile[][] grid;
   private Texture textureMur;
   private Texture textureSol;
   private LinkedList<Spawner> spawners = new LinkedList<Spawner>();
   private Body[][] wallBodies;
   private Rectangle[][] bounds;

   public void loadResources() {
      textureSol = new Texture(Gdx.files.internal("data/sol.png"));
      textureMur = new Texture(Gdx.files.internal("data/mur.jpg"));
   }

   public Map(World world, Team[] teams) {
      super();

      // Crée la map et ajoute les spawners
      MazeGenerator generator = new MazeGenerator();
      setGrid(generator.generateMaze(teams.length * 3));
      spawners = generator.generateSpawners(teams);
      // Définit la consistance physique des murs
      wallBodies = new Body[grid.length][grid.length];
      bounds = new Rectangle[grid.length][grid.length];
            
      for (int i = 0; i < grid.length; i++) {
         for (int j = 0; j < grid[i].length; j++) {
            Tile t = grid[i][j];
            if (t == Tile.WALL) {
               bounds[i][j] = new Rectangle(i * Tile.WIDTH - Tile.WIDTH/2,
                     j * Tile.HEIGHT - Tile.HEIGHT/2, Tile.WIDTH, Tile.HEIGHT);
               BodyDef bodyDef = new BodyDef();
               bodyDef.type = BodyType.StaticBody;
               bodyDef.position.set(bounds[i][j].x, bounds[i][j].y);
               Body body = world.createBody(bodyDef);
               PolygonShape shape = new PolygonShape();
               shape.setAsBox(bounds[i][j].height, bounds[i][j].width);
               FixtureDef fix = new FixtureDef();
               fix.shape = shape;
               fix.density = 0.4f;
               fix.friction = 0.5f;
               fix.restitution = 0.8f;
               body.createFixture(fix); 
               wallBodies[i][j] = body;
            }
         }
      }

      loadResources();
      System.out.println("Generated map :\n" + this);
   }

   public Tile[][] getGrid() {
      return grid;
   }

   public void setGrid(Tile[][] grid) {
      this.grid = grid;
   }

   public LinkedList<Spawner> getSpawners() {
      return spawners;
   }
   
   public Rectangle getBounds(int i, int j){
      return bounds[i][j];
   }

   /**
    * Retourne les coordonnées du centre de la case voulue sur la map
    * 
    * @param i
    * @param j
    * @return
    */
   public static Vector2 getRealPos(int i, int j) {
      return new Vector2((0.5f + i) * Tile.WIDTH, (0.5f + j) * Tile.HEIGHT);
   }
   
   @Override
   public void debugRender(ShapeRenderer renderer) {
      /*
       * renderer.begin(ShapeType.FilledRectangle);
       * renderer.setColor(Color.GRAY); for (int i = 0; i < grid.length; i++) {
       * for (int j = 0; j < grid[i].length; j++) { if (grid[i][j] == Tile.WALL)
       * { renderer.filledRect(i * Tile.WIDTH, j * Tile.HEIGHT, Tile.WIDTH,
       * Tile.HEIGHT); } } } renderer.end();
       */
   }

   @Override
   public void draw(SpriteBatch batch, float parentAlpha) {
      super.draw(batch, parentAlpha);

      // Répète la texture
      textureSol.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);
      textureMur.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);

      for (int i = 0; i < grid.length; i++) {
         for (int j = 0; j < grid[i].length; j++) {
            int x = j;//grid.length - i;
            int y = grid.length-i;//grid[i].length - i;
            if (grid[i][j] == Tile.WALL) {
               // Dessine un mur
               batch.draw(textureMur, (x - 0.5f) * Tile.WIDTH, (y - 0.5f)
                     * Tile.HEIGHT, (float) Tile.WIDTH, (float) Tile.HEIGHT,
                     0f, 0f, 1f, 1f);
            }
            else if (grid[i][j] == Tile.EXIT) {
               // Dessine un sol coloré
               Color previousTint = batch.getColor();
               batch.setColor(Color.GREEN);
               batch.draw(textureSol, (x - 0.5f) * Tile.WIDTH, (y - 0.5f)
                     * Tile.HEIGHT, (float) Tile.WIDTH, (float) Tile.HEIGHT,
                     0f, 0f, 8f, 8f);
               batch.setColor(previousTint);
            }
            else {
               // Dessine un sol
               batch.draw(textureSol, (x - 0.5f) * Tile.WIDTH, (y - 0.5f)
                     * Tile.HEIGHT, (float) Tile.WIDTH, (float) Tile.HEIGHT,
                     0f, 0f, 8f, 8f);

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
      for (int i = 0; i < wallBodies.length; i++) {
         for (int j = 0; j < wallBodies.length; j++) {
            if (wallBodies[i][j] != null) {
               // wallBodies[i][j].setTransform(grid[i][j]., y, angle)
            }
         }
      }
   }

   @Override
   public void beginContact(Contact contact) {
      // TODO Auto-generated method stub
      System.out.println("HERE 2");
   }

   @Override
   public void endContact(Contact contact) {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void preSolve(Contact contact, Manifold oldManifold) {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void postSolve(Contact contact, ContactImpulse impulse) {
      // TODO Auto-generated method stub
      
   }

}
