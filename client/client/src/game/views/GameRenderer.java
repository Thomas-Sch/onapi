/* ============================================================================
 * Nom du fichier   : GameRenderer.java
 * ============================================================================
 * Date de création : 1 mai 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package game.views;

import game.models.GameModel;

/**
 * Gère l'affichage du jeu à l'écran.
 * 
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 * 
 */
public class GameRenderer {

   private GameModel game;
   private boolean debug;
   private int width;
   private int height;

   public GameRenderer(GameModel game, boolean debug) {
      this.game = game;
      this.debug = debug;
   }

   /**
    * Méthode appelée à chaque rafraîchissement de l'écran
    */
   public void render() {
      // TODO Auto-generated method stub

   }

   /**
    * (Re)définit la hauteur et la largeur de l'écran graphique
    * 
    * @param width
    * @param height
    */
   public void setSize(int width, int height) {
      this.width = width;
      this.height = height;
   }

}
