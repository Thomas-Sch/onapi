/* ============================================================================
 * Nom du fichier   : MazeTile.java
 * ============================================================================
 * Date de création : 22 mai 2013
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
public class MazeTile {

   private Tile type;

   public final int x, y;

   public MazeTile(Tile type, int x, int y) {
      setType(type);
      this.x = x;
      this.y = y;
   }

   /**
    * @return the type
    */
   public Tile getType() {
      return type;
   }

   /**
    * @param type
    *           the type to set
    */
   public void setType(Tile type) {
      this.type = type;
   }

}
