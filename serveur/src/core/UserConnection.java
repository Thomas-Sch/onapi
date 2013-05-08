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

import java.net.Socket;

import javax.swing.JFrame;

import utils.connections.Channel;
import utils.connections.protocol.ClientProtocol;
import utils.connections.protocol.ProtocolType;
import utils.connections.protocol.ServerProtocol;

/**
 * TODO
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 *
 */
public class UserConnection implements Runnable {
   
   private Channel channel;
   
   private boolean active = true;
   
   private ServerProtocol protocol;
   
   public UserConnection(Socket socket, int timeout) {
      channel = new Channel(socket, timeout);
      protocol = new ServerProtocol(channel);
   }


   @Override
   public void run() {
      
      ProtocolType proType;
      
      while(active) {
         
         proType = channel.receiveProtocolType();
         
         System.out.println("Requete client : " + proType);
         
         switch(proType) {
            
            case PING :
               protocol.ping();
               break;
               
            case AUTHENTIFICATION :
               protocol.authentifaction();
               break;
               
            case TEXT_MESSAGE :
               protocol.textMessage(System.out);
               break;
               
            default :
               System.out.println("Not yet implemented protocol");
         }
         
      }
      
   }

}
