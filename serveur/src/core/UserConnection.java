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
   
   
   public UserConnection(Socket socket, int timeout) {
      channel = new Channel(socket, timeout);
   }


   @Override
   public void run() {
      int counter = 0;
      String msg = "";
      
      JFrame frame = new JFrame("Popup !!");
      frame.setBounds(800, 50, 300, 100);
      
      channel.sendObject(frame);
      
      while(active) {
         counter++;
         
         msg = channel.receiveString();
         
         System.out.println("Server - " + counter + ". " + msg);
         
         msg = "";
         
         channel.sendString("orly ?");
      }
      
   }

}
