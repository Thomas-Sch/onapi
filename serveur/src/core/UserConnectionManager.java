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
import core.protocol.ServerStandardUpdateProtocol;

import java.net.Socket;

import sun.util.logging.resources.logging;

/**
 * TODO
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 *
 */
public class UserConnectionManager {
   private Core core;
   
   private UserInformations user;
   
   private Thread threadReceive;
   private Thread threadUpdate;
   
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
      
      user.serverReceive = new AccountConnection(core, user);
      
      // Création des threads de communications
      threadReceive = new Thread(new ClientRequests());
      threadUpdate = new Thread(new ClientUpdate());
      
      threadReceive.start();
      threadUpdate.start();
      
      
   }
   
   private class ClientRequests implements Runnable {
      
      private ClientRequests() {
         
      }

      @Override
      public void run() {
         ProtocolType proType;
         
         while(user.isConnected) {
            
            try {
            
               proType = user.connectionsToClient.receiveChannel.receiveProtocolType();
               
               user.serverReceive.answerRequest(proType);
               
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
         core.removeConnection(UserConnectionManager.this);
         
      }
      
   }

   private class ClientUpdate implements Runnable {
      
      private ClientUpdate() {
         
      }

      @Override
      public void run() {
         
         // TODO to change !
         ServerStandardUpdateProtocol protocol = new ServerStandardUpdateProtocol(core, user);
         
         while (user.isConnected) {
            
            try {
               
               user.log.push("Ping to client : " + protocol.ping() + " ms.");
               
               protocol.textMessage("still here ?");
               
               try {
                  Thread.sleep(5000);
               }
               catch (InterruptedException e) { }
               
               
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
         
      }
      
      
      
      
   }
   
   

}
