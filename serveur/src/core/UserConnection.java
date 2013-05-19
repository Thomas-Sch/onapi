/* ============================================================================
 * Nom du fichier   : UserConnection.java
 * ============================================================================
 * Date de création : 6 mai 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package core;

import gui.logs.Log;
import common.components.UserAccount;
import common.connections.Channel;
import common.connections.exceptions.ChannelClosedException;
import common.connections.protocol.ProtocolType;
import core.Core;
import core.protocol.ServerReceiveProtocol;
import java.net.Socket;

/**
 * TODO
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 *
 */
public class UserConnection implements Runnable {
   private Core core;
   
   private UserInformations user;
   
   private ServerReceiveProtocol protocol;
   
   public UserConnection(Core core, Socket socket, int timeout) {
      this.core = core;
      user = new UserInformations();
      user.isConnected = true;
      
      user.channelReceive = new Channel(socket, timeout);
      user.log = new Log("Unknown");
      
      protocol = new ServerReceiveProtocol(core, user);
      
      core.addLogPanel(user.log.createLogPanel());
   }


   @Override
   public void run() {
      
      ProtocolType proType;
      
      while(user.isConnected) {
         
         try {
         
            proType = user.channelReceive.receiveProtocolType();
            
            switch(proType) {
               
               case PING :
                  protocol.ping();
                  break;
                  
               case LOGIN :
                  if (user.account == null) {
                     acceptRequest();
                     protocol.login();
                  }
                  else {
                     refuseRequest();
                  }  
                     
                  break;
                  
               case ACCOUNT_CREATE :
                  if (user.account == null) {
                     acceptRequest();
                     protocol.createAccount();
                  }
                  else {
                     refuseRequest();
                  }
                  
                  break;
                  
               case TEXT_MESSAGE :
                  protocol.textMessage();
                  break;
                  
               default :
                  user.log.push("Not yet implemented protocol");
            }
            
         }
         catch (ChannelClosedException e) {
            user.isConnected = false;
            user.log.push("Client lost");
         }
            
      }
      
      core.removeLogPanel(user.log.getLogPanel());
      
   }
   
   private void acceptRequest() {
      user.channelReceive.sendProtocolType(ProtocolType.ACCEPT);
   }
   
   private void refuseRequest() {
      user.channelReceive.sendProtocolType(ProtocolType.REFUSE);
   }

}
