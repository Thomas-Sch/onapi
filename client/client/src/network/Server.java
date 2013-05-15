/* ============================================================================
 * Nom du fichier   : Server.java
 * ============================================================================
 * Date de création : 1 mai 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package network;

import java.net.InetAddress;
import java.net.Socket;

/**
 * Façade permettant d'initialiser la connexion avec le serveur de jeu.
 * 
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 * 
 */
public class Server {

   private InetAddress address;
   private int port;

   public Server(InetAddress address, int port) {
      this.address = address;
   }

   public void connect() {
      // TODO connexion...
   }

   public void disconnect() {
   // TODO déconnexion...
   }

   public InetAddress getAddress() {
      return address;
   }

   public void setAddress(InetAddress address) {
      this.address = address;
   }

   public void setPort(int port) {
      this.port = port;
   }
   
   public int getPort() {
      return this.port;
   }
   
}
