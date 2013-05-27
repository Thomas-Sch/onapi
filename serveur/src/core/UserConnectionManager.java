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
import core.protocol.ServerStandardReceiveProtocol;
import java.net.Socket;

/**
 * TODO
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 *
 */
public class UserConnectionManager implements Runnable {
   private Core core;
   
   private UserInformations user;
   
   public UserConnectionManager(Core core, Socket socket, int timeout) {
      init(core, socket, timeout);
   }
   
   private void init(Core core, Socket socket, int timeout) {
      this.core = core;
      
      // Création d'une entité propre au client
      user = new UserInformations();
      
      // Création du log
      user.log = new Log("Unknown");
      core.addLogPanel(user.log.createLogPanel());
      
      // Création du canal de mises à jour du client
      user.log.push("Create ConnectionsToClient...");
      user.connectionsToClient = new ConnectionsToClient(core, this, socket, timeout);
      user.log.push("done.");
      
      
      user.isConnected = true;
      
      
      user.server = new AccountConnection(core, user);
      
      
      
   }


   @Override
   public void run() {
      
      ProtocolType proType;
      
      while(user.isConnected) {
         
         try {
         
            proType = user.connectionsToClient.receiveChannel.receiveProtocolType();
            
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
