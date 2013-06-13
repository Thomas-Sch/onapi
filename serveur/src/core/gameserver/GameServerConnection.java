/* ============================================================================
 * Nom du fichier   : LobbyConnection.java
 * ============================================================================
 * Date de création : 19 mai 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package core.gameserver;

import common.components.gameserver.PlayerStatus;
import common.connections.protocol.ProtocolType;

import core.Core;
import core.ServerRequestAnswers;
import core.UserInformations;
import core.protocol.gameserver.GameServerReceiveProtocol;

/**
 * TODO
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 *
 */
public class GameServerConnection implements ServerRequestAnswers {
   
   private GameServerReceiveProtocol receiveProtocol;
   
   private UserInformations user;
   
   public GameServerConnection(Core core, GameServer gameServer, UserInformations user,
                          PlayerStatus status) {
      this.user = user;
      receiveProtocol = new GameServerReceiveProtocol(core, gameServer, user, status);
   }

   @Override
   public void answerRequest(ProtocolType type) {
      
      switch(type) {
         
         case PING :
            receiveProtocol.acceptRequest(type);
            receiveProtocol.ping();
            break;
            
         case TEXT_MESSAGE :
            receiveProtocol.acceptRequest(type);
            receiveProtocol.textMessage();
            break;
            
         case LEAVE_GAME :
            receiveProtocol.acceptRequest(type);
            receiveProtocol.leave();
            break;
            
         case LOBBY_SET_READY :
            receiveProtocol.acceptRequest(type);
            receiveProtocol.lobbySetReady();
            break;
            
         default :
            receiveProtocol.refuseRequest(type);
            user.log.push("Bad request protocol");
      }
      
      
   }
   
   

}
