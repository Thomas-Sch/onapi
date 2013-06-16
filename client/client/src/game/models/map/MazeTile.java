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
 * Définit un sommet du labyrinthe, comprenant le type de tile et ses
 * coordonnées dans la grille.
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

   /**
    * @param type Type de tile du sommet
    * @param x Coordonnée x dans la grille
    * @param y Coordonnée y dans la grille
    */
   public MazeTile(Tile type, int x, int y) {
      setType(type);
      this.x = x;
      this.y = y;
   }

   /**
    * @return Type de tile du sommet
    */
   public Tile getType() {
      return type;
   }

   /**
    * @param type
    *          Type de tile du sommet
    */
   public void setType(Tile type) {
      this.type = type;
   }

}
