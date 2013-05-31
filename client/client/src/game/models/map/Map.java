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
import com.badlogic.gdx.physics.box2d.World;

/**
 * La map est une grille dans laquelle on place les murs et autres éléments de
 * la carte (spawners, sortie...).
 * 
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 * 
 */
public class Map extends Entity {

   private Tile[][] grid;
   private Texture textureMur;
   private Texture textureSol;

   public void loadResources() {
      textureSol = new Texture(Gdx.files.internal("data/sol.png"));
      textureMur = new Texture(Gdx.files.internal("data/mur.jpg"));
   }

   public Map(int size, World world, int nbTeams) {
      setGrid(new MazeGenerator().generateMaze(8, nbTeams));

      // Définit la consistance physique des murs
      for (int i = 0; i < grid.length; i++) {
         for (int j = 0; j < grid[i].length; j++) {
            Tile t = grid[i][j];
            if (t == Tile.WALL) {
               Rectangle bounds = new Rectangle(i * Tile.WIDTH,
                     j * Tile.HEIGHT, Tile.WIDTH, Tile.HEIGHT);
               BodyDef bodyDef = new BodyDef();
               bodyDef.type = BodyType.StaticBody;
               bodyDef.position.set(bounds.x, bounds.y);
               Body body = world.createBody(bodyDef);
               PolygonShape shape = new PolygonShape();
               shape.setAsBox(bounds.height / 2, bounds.width / 2);
               FixtureDef fix = new FixtureDef();
               fix.shape = shape;
               fix.density = 0.4f;
               fix.friction = 0.5f;
               fix.restitution = 0.8f;
               body.createFixture(fix);
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

   /**
    * Retourne les coordonnées du centre de la case voulue sur la map
    * 
    * @param i
    * @param j
    * @return
    */
   public Vector2 getRealPos(int i, int j) {
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

      // repete la texture
      textureSol.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);
      textureMur.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);

      for (int i = 0; i < grid.length; i++) {
         for (int j = 0; j < grid[i].length; j++) {
            if (grid[i][j] == Tile.WALL) {
               // Dessiner un mur
               batch.draw(textureMur, (i - 0.5f) * Tile.WIDTH, (j - 0.5f)
                     * Tile.HEIGHT, (float) Tile.WIDTH, (float) Tile.HEIGHT,
                     0f, 0f, 1f, 1f);
            }
            else {
               // dessine un sol
               batch.draw(textureSol, (i - 0.5f) * Tile.WIDTH, (j - 0.5f)
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
      // TODO Auto-generated method stub

   }

}
