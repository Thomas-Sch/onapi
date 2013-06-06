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
package game.models.map;

/**
 * TODO
 * 
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 * 
 */
public enum Tile {
   EMPTY, WALL, SPAWNER, EXIT;

   public static final int WIDTH = 100;
   public static final int HEIGHT = WIDTH;
   
   @Override
   public String toString() {
      switch (this) {
         case EMPTY:
            return " ";
         case WALL:
            return "#";
         case SPAWNER:
            return "+";
         case EXIT:
            return "!";
         default:
            return "?";
      }
   }
  
}
