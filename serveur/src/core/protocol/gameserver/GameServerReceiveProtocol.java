/* ============================================================================
 * Nom du fichier   : GameServerReceiveProtocol.java
 * ============================================================================
 * Date de création : 19 mai 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package core.protocol.gameserver;

import common.components.gameserver.PlayerStatus;

import core.Core;
import core.UserInformations;
import core.accountManagement.AccountConnection;
import core.gameserver.GameServer;
import core.protocol.ServerStandardReceiveProtocol;

/**
 * TODO
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 *
 */
public class GameServerReceiveProtocol extends ServerStandardReceiveProtocol {
   
   private GameServer gameServer;
   
   private PlayerStatus status;
   
   public GameServerReceiveProtocol(Core core, GameServer gameServer, UserInformations user,
                               PlayerStatus status) {
      super(core, user);
      this.gameServer = gameServer;
      this.status = status;
   }
   
   public void leave() {
      if (gameServer.removePlayer(user)) {
         user.gameServer = null;
            
         // On retourne au protocol de connexion.
         user.serverReceive = new AccountConnection(core, user);
      }
   }
   
   public void lobbySetReady() {
      boolean ready = user.connectionsToClient.receiveChannel.receiveBoolean();
      status.setReady(ready);
   }

}
