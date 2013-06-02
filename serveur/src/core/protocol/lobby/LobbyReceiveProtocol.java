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
package core.protocol.lobby;

import common.components.lobby.PlayerStatus;
import core.Core;
import core.UserInformations;
import core.accountManagement.AccountConnection;
import core.lobby.Lobby;
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
   
   private PlayerStatus status;
   
   public LobbyReceiveProtocol(Core core, Lobby lobby, UserInformations user,
                               PlayerStatus status) {
      super(core, user);
      this.lobby = lobby;
      this.status = status;
   }
   
   public void leave() {
      if (lobby.removePlayer(user)) {
         user.lobby = null;
            
         // On retourne au protocol de connexion.
         user.serverReceive = new AccountConnection(core, user);
      }
   }
   
   public void setReady() {
      boolean ready = user.connectionsToClient.receiveChannel.receiveBoolean();
      status.setName("Toto " + status.getTeamNumber());
      status.setTeamNumber(status.getTeamNumber()+1); // TODO a supprimer
      status.setReady(ready);
   }

}
