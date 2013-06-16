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
 * Définit les types de tiles (cases) possibles pour la grille représentant la
 * map
 * 
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 * 
 */
public enum Tile {
   /**
    * Sol sans rien de plus
    */
   EMPTY,
   
   /**
    * Mur
    */
   WALL, 
   
   /**
    * Sol sur lequel des joueurs peuvent apparaître
    */
   SPAWNER, 
   
   /**
    * Emplacement de la porte de sortie
    */
   EXIT;

   /**
    * Largeur d'une tile à l'affichage
    */
   public static final int WIDTH = 550;
   
   /**
    * Hauteur d'une tile à l'affichage
    */
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
