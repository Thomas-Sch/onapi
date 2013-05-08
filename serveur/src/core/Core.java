/* ============================================================================
 * Nom du fichier   : Core.java
 * ============================================================================
 * Date de création : 1 mai 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package core;

import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.LinkedList;

import core.exceptions.PortException;

/**
 * TODO
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 *
 */
public class Core {
   
   private boolean exit = false;
   
   private InetAddress inetAddress = null;
   
   private Port serverPort;
   
   private LinkedList<UserConnection> connections = new LinkedList<>();
   
   
   public Core() {
      init();
      
      if (!initSuccessful()) {
         throw new RuntimeException("Server not successfully initialized");
      }
   }
   
   private void init() {
      serverPort = new Port(1234);
      serverPort.activateFreePort();
      
      try {
         inetAddress = InetAddress.getLocalHost();
      }
      catch (Exception e) {
         System.err.println("Unable to obtain the IP address of the server");
      }
      
   }
   
   private boolean initSuccessful(){
      return inetAddress != null;
   }
   
   
   
   public void start() {
      
      System.out.println("Onapi server started.\n" +
                         " - name    : " + inetAddress.getHostName() + "\n" +
                         " - address : " + inetAddress.getHostAddress() + "\n" +
                         " - port    : " + getPortNumber());
      
      
      while(!exit) {
         
         try {
            Socket socket = serverPort.accept();
            
            // Démarre le processus de gestion d'un nouveau client
            UserConnection userConnection = new UserConnection(socket, 0);
            
            Thread thread = new Thread(userConnection);
            thread.start();
            
            // Enregistre le nouveau client
            synchronized(connections) {
               connections.add(userConnection);
            }
            
            
         }
         catch (PortException e) {
            System.err.println("Unable to accept new connection : " + e.getMessage());
         }
         
         
      }
      
   }
   
   public InetAddress getInetAdress() {
      return inetAddress;
   }
   
   public int getPortNumber() {
      return serverPort.getPortNumber();
   }

}
