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
      
      public static final int WIDTH = 1;
      public static final int HEIGHT = WIDTH;
   }

   private Tile[][] grid;

   public Map(int size) {
      setGrid(MazeGenerator.create(size));
   }

   public Tile[][] getGrid() {
      return grid;
   }

   public void setGrid(Tile[][] grid) {
      this.grid = grid;
   }
   
   @Override
   public void debugRender(ShapeRenderer renderer) {
      renderer.begin(ShapeType.FilledRectangle);
      renderer.setColor(Color.GRAY);
      for (int i = 0; i < grid.length; i++) {
         for (int j = 0; j < grid[i].length; j++) {
            if (grid[i][j] == Tile.WALL) {
               renderer.filledRect(i, j, Tile.WIDTH, Tile.HEIGHT);
            }
         }
      }
      renderer.end();
   }

}
