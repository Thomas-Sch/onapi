/* ============================================================================
 * Nom du fichier   : LobbyUpdateProtocol.java
 * ============================================================================
 * Date de création : 19 mai 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package core.lobby.protocol;

import core.Core;
import core.UserInformations;
import core.lobby.Lobby;

/**
 * TODO
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 *
 */
public class LobbyUpdateProtocol {
   
   private Core core;
   private Lobby lobby;
   private UserInformations user;
   
   public LobbyUpdateProtocol(Core core, Lobby lobby, UserInformations user) {
      this.core = core;
      this.lobby = lobby;
      this.user = user;
   }
   
   

}
