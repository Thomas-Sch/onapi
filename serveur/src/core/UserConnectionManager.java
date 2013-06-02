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
import core.protocol.updates.ServerUpdateProtocol;
import core.updates.ServerUpdateOrder;
import core.updates.components.StandardPing;

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
   private static final int DELAY_BETWEEN_UPDATES = 100;
   
   private final int TIME_ALAPSED_FOR_CHECK;
   
   private Core core;
   
   private UserInformations user;
   
   private Thread threadReceive;
   private Thread threadUpdate;
   
   public UserConnectionManager(Core core, Socket socket, int timeout) {
      this.TIME_ALAPSED_FOR_CHECK = timeout / 2;
      this.core = core;
      init(socket, timeout);
   }
   
   private void init(Socket socket, int timeout) {
      
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
      user.serverUpdate = new ServerUpdateOrder(core, user);
      
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
         
         int timeSinceLastUpdate = 0;
         
         while (user.isConnected) {
            
            try {
               
               if (user.serverUpdate.sendUpdate()) {
                  timeSinceLastUpdate = 0;
               }
               else {
                  timeSinceLastUpdate += DELAY_BETWEEN_UPDATES;
                  
                  if (TIME_ALAPSED_FOR_CHECK < timeSinceLastUpdate) {
                     user.serverUpdate.pushUpdate(new StandardPing());
                  }
               }
               
               try {
                  Thread.sleep(DELAY_BETWEEN_UPDATES);
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
         
         // Utilisateur perdu
         if (user.lobby != null) {
            user.lobby.removePlayer(user);
         }
         
      }
      
      
      
      
   }
   
   

}
