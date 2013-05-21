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
import common.connections.exceptions.ChannelException;
import common.connections.exceptions.TimeOutException;
import common.connections.protocol.ProtocolType;
import core.Core;
import core.accountManagement.AccountConnection;
import core.protocol.ServerStandardProtocol;
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
   
   public UserConnection(Core core, Socket socket, int timeout) {
      this.core = core;
      user = new UserInformations();
      user.isConnected = true;
      
      user.channelReceive = new Channel(socket, timeout);
      user.log = new Log("Unknown");
      
      user.server = new AccountConnection(core, user);
      
      core.addLogPanel(user.log.createLogPanel());
   }


   @Override
   public void run() {
      
      ProtocolType proType;
      
      while(user.isConnected) {
         
         try {
         
            proType = user.channelReceive.receiveProtocolType();
            
            user.server.answerRequest(proType);
            
            
         }
         catch (TimeOutException e) {
            user.isConnected = false;
            user.log.push("Timeout with client");
         }
         catch (ChannelClosedException e) {
            user.isConnected = false;
            user.log.push("Client lost");
         }
         catch (ChannelException e) {
            user.isConnected = false;
            user.log.push("Client lost");
         }
            
      }
      
      core.removeLogPanel(user.log.getLogPanel());
      core.removeConnection(this);
      
   }
   
   

}
