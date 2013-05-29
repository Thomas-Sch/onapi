/* ============================================================================
 * Nom du fichier   : AccountConnection.java
 * ============================================================================
 * Date de création : 19 mai 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package core.accountManagement;

import common.connections.protocol.ProtocolType;

import core.Core;
import core.ServerRequestAnswers;
import core.UserInformations;
import core.accountManagement.protocol.AccountReceiveProtocol;

/**
 * TODO
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 *
 */
public class AccountConnection implements ServerRequestAnswers {
   
   private AccountReceiveProtocol receiveProtocol;
   
   private UserInformations user;
   
   public AccountConnection(Core core, UserInformations user) {
      this.user = user;
      receiveProtocol = new AccountReceiveProtocol(core, user);
   }

   @Override
   public void answerRequest(ProtocolType type) {
      
      switch(type) {
         
         case PING :
            receiveProtocol.acceptRequest(ProtocolType.PING);
            receiveProtocol.ping();
            break;
            
         case LOGIN :
            if (user.account == null) {
               receiveProtocol.acceptRequest(type);
               receiveProtocol.login();
            }
            else {
               receiveProtocol.refuseRequest(type);
            }  
               
            break;
            
         case LOGOUT :
            if (user.account != null) {
               receiveProtocol.acceptRequest(type);
               receiveProtocol.logout();
            }
            else {
               receiveProtocol.refuseRequest(type);
            }
            
            break;
            
         case ACCOUNT_CREATE :
            if (user.account == null) {
               receiveProtocol.acceptRequest(type);
               receiveProtocol.createAccount();
            }
            else {
               receiveProtocol.refuseRequest(type);
            }
            
            break;
            
         case JOIN_GAME :
            if (user.account != null && user.lobby == null) {
               receiveProtocol.acceptRequest(type);
               receiveProtocol.joinGame();
            }
            else {
               receiveProtocol.refuseRequest(type);
            }
            
            break;
            
         case TEXT_MESSAGE :
            receiveProtocol.acceptRequest(type);
            receiveProtocol.textMessage();
            break;
            
         default :
            receiveProtocol.refuseRequest(type);
            user.log.push("Bad request protocol");
      }
      
      
   }

}
