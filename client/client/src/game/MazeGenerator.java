/* ============================================================================
 * Nom du fichier   : MazeGenerator.java
 * ============================================================================
 * Date de création : 16 mai 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package game;

import game.models.Map.Tile;

import java.util.Random;

/**
 * TODO
 * 
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 * 
 */
public abstract class MazeGenerator {

   public static Tile[][] create(int size) {
      Tile[][] grid = new Tile[size][size];
      Random r = new Random();
      Tile[] tileTypes = Tile.values();

      for (int i = 0; i < grid.length; i++) {
         for (int j = 0; j < grid[i].length; j++) {
            grid[i][j] = tileTypes[r.nextInt(2)];
         }
      }

      return grid;
   }

}
