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
package game.models;

import game.MazeGenerator;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;

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

   public enum Tile {
      EMPTY, WALL;

      public static final int WIDTH = 350;
      public static final int HEIGHT = WIDTH;

      @Override
      public String toString() {
         switch (this) {
            case EMPTY:
               return "[ ]";
            case WALL:
               return "[#]";
            default:
               return "?";
         }
      }
   }

   private Tile[][] grid;

   public Map(int size) {
      setGrid(MazeGenerator.create(size));
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
      renderer.begin(ShapeType.FilledRectangle);
      renderer.setColor(Color.GRAY);
      for (int i = 0; i < grid.length; i++) {
         for (int j = 0; j < grid[i].length; j++) {
            if (grid[i][j] == Tile.EMPTY) {
               renderer.filledRect(i * Tile.WIDTH, j * Tile.HEIGHT, Tile.WIDTH,
                     Tile.HEIGHT);
            }
         }
      }
      renderer.end();
   }

   @Override
   public String toString() {
      StringBuffer sb = new StringBuffer();

      for (int i = 0; i < grid.length; i++) {
         for (int j = 0; j < grid[i].length; j++) {
            sb.append(grid[i][j]);
         }
         sb.append("\n");
      }
      return sb.toString();
   }

   /**
    * @return Le nombre de cases en largeur et hauteur de la map
    */
   public int getSize() {
      return grid.length;
   }

}
