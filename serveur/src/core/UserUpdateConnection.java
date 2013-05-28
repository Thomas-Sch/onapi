/* ============================================================================
 * Nom du fichier   : UserUpdateConnection.java
 * ============================================================================
 * Date de création : 20 mai 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package core;

import gui.logs.Log;

import java.net.Socket;

import common.connections.Channel;
import common.connections.exceptions.ChannelClosedException;
import common.connections.exceptions.TimeOutException;
import common.connections.protocol.ProtocolType;

import core.accountManagement.AccountConnection;

/**
 * TODO
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 *
 */
public class UserUpdateConnection implements Runnable {
   
   private UserInformations user;
   
   public UserUpdateConnection(UserInformations user) {
      this.user = user;
   }


   @Override
   public void run() {
      
      while(user.isConnected) {
         
         try {
         
//            user.update.updateInformations();
            
            try {
               Thread.sleep(500);
            }
            catch (InterruptedException e) {}
            
            
         }
         catch (TimeOutException e) {
            user.isConnected = false;
            user.log.push("Timeout with client");
         }
         catch (ChannelClosedException e) {
            user.isConnected = false;
            user.log.push("Client lost");
         }
      
      }
      
      
   }
   
   

}
