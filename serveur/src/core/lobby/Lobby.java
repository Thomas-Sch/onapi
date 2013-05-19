/* ============================================================================
 * Nom du fichier   : Lobby.java
 * ============================================================================
 * Date de création : 19 mai 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package core.lobby;

import core.UserInformations;

/**
 * TODO
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 *
 */
public class Lobby {
   
   private UserInformations[] players;
   
   public Lobby(int nbPlayers) {
      players = new UserInformations[nbPlayers];
      
      for (UserInformations info : players) {
         info = null;
      }
   }

}
