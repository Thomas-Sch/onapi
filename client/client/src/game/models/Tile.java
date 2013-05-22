/* ============================================================================
 * Nom du fichier   : MazeTile.java
 * ============================================================================
 * Date de création : 21 mai 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package game.models;

/**
 * TODO
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 *
 */
public enum Tile {
   EMPTY, WALL;

   public static final int WIDTH = 350;
   public static final int HEIGHT = WIDTH;

   @Override
   public String toString() {
      switch (this) {
         case EMPTY:
            return " ";
         case WALL:
            return "#";
         default:
            return "?";
      }
   }
}
