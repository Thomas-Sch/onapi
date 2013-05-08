/* ============================================================================
 * Nom du fichier   : Port.java
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

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import core.exceptions.PortException;

/**
 * TODO
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 *
 */
public class Port {
   
   private final static int PORT_NUMBER_MIN = 2000;
   private final static int PORT_NUMBER_MAX = 10000;
   
   private int portNumber;
   
   private ServerSocket socket;
   
   public Port(int portNumber) {
      this.portNumber = portNumber;
   }
   
   public InetAddress getInetAddress() {
      return socket.getInetAddress();
   }
   
   public int getPortNumber() {
      return portNumber;
   }
   
   public int activateFreePort() {
      
      boolean done = false;
      
      // Tente de réserver le port voulu
      try {
         reserve();
         done = true;
      }
      catch (PortException e) {
         portNumber = PORT_NUMBER_MIN;
      }
      
      // Cherche un port libre si le port voulu n'a pas pu être réservé.
      while(!done && portNumber < PORT_NUMBER_MAX) {
         try {
            socket = new ServerSocket(portNumber);
         }
         catch (IOException e) {
            portNumber++;
         }
      }
      
      return portNumber;
   }
   
   public void reserve() throws PortException {
      try {
         socket = new ServerSocket(portNumber);
      }
      catch (IOException e) {
         throw new PortException("Unable to use port number " + portNumber, e);
      }
      
   }
   
   public Socket accept() throws PortException {
      Socket resultSocket;
      
      try {
         resultSocket = socket.accept();
      }
      catch (IOException e) {
         throw new PortException("Error while trying to accept new connection",
               e);
      }
      
      return resultSocket;
   }
   
   public void release() throws PortException {
      try {
         socket.close();
      }
      catch(IOException e) {
         throw new PortException("Unable to close port number " + portNumber, e);
      }
   }

}
