/* ============================================================================
 * Nom du fichier   : PlayersStatus.java
 * ============================================================================
 * Date de création : 14 juin 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package gui.utils;

/**
 * 
 * TODO
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 *
 */
public class SelectedPlayer {
   
   int playerServerId;
   
   public SelectedPlayer() {
      
   }
   
   public int getPlayerServerId() {
      return playerServerId;
   }
   
   public void setPlayerServerId(int playerServerId) {
      this.playerServerId = playerServerId;
      
      System.out.println("DEBUG - selected id : " + playerServerId);
   }

}
