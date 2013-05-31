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
package core.lobby;

import common.connections.protocol.ProtocolType;

import core.Core;
import core.ServerRequestAnswers;
import core.ServerUpdateOrder;
import core.UserInformations;
import core.lobby.protocol.LobbyReceiveProtocol;
import core.lobby.protocol.LobbyUpdateProtocol;

/**
 * TODO
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 *
 */
public class LobbyConnection implements ServerRequestAnswers {
   
   private LobbyReceiveProtocol receiveProtocol;
   private LobbyUpdateProtocol updateProtocol;
   
   private UserInformations user;
   
   public LobbyConnection(Core core, Lobby lobby, UserInformations user) {
      this.user = user;
      receiveProtocol = new LobbyReceiveProtocol(core, lobby, user);
      updateProtocol = new LobbyUpdateProtocol(core, lobby, user);
   }

   @Override
   public void answerRequest(ProtocolType type) {
      
      switch(type) {
         
         case PING :
            receiveProtocol.acceptRequest(ProtocolType.PING);
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
            
         default :
            receiveProtocol.refuseRequest(type);
            user.log.push("Bad request protocol");
      }
      
      
   }
   
   

}
