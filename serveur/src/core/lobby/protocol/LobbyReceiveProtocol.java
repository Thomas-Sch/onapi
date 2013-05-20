/* ============================================================================
 * Nom du fichier   : LobbyReceiveProtocol.java
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

import common.connections.protocol.ProtocolType;
import core.Core;
import core.UserInformations;
import core.lobby.Lobby;
import core.protocol.ServerStandardProtocol;

/**
 * TODO
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 *
 */
public class LobbyReceiveProtocol extends ServerStandardProtocol {
   
   private Lobby lobby;
   
   public LobbyReceiveProtocol(Core core, Lobby lobby, UserInformations user) {
      super(core, user);
      this.lobby = lobby;
   }
   
   public void leave() {
      
      
      
   }
   
   public void setReady() {
      
      
      
   }

}
