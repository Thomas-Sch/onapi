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

import common.connections.exceptions.ChannelException;
import common.connections.protocol.ProtocolType;
import core.Core;
import core.UserInformations;
import core.accountManagement.AccountConnection;
import core.lobby.Lobby;
import core.lobby.exceptions.LobbyException;
import core.protocol.ServerStandardReceiveProtocol;

/**
 * TODO
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 *
 */
public class LobbyReceiveProtocol extends ServerStandardReceiveProtocol {
   
   private Lobby lobby;
   
   public LobbyReceiveProtocol(Core core, Lobby lobby, UserInformations user) {
      super(core, user);
      this.lobby = lobby;
   }
   
   public void leave() {
      
      try {
         user.lobby.removePlayer(user);
         
         user.lobby = null;
         user.update = null;
         
         try {
            user.connectionsToClient.updateChannel.close();
         }
         catch (ChannelException e) {
            user.log.push("Error while closing the update channel.");
         }
         user.connectionsToClient.updateChannel = null;
         
         user.server = new AccountConnection(core, user);
      }
      catch (LobbyException e) {
         
      }
      
      
      
   }
   
   public void setReady() {
      
      
      
   }

}
