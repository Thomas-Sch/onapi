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

/**
 * Façade permettant de communiquer avec le serveur de jeu.
 * 
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 * 
 */
public class Server {

   private InetAddress address;

   public Server(InetAddress address) {
      this.address = address;
   }

   public void connect() {
      // TODO implémenter connect
   }

   public void disconnect() {
      // TODO implémenter disconnect
   }

   public InetAddress getAddress() {
      return address;
   }

   public void setAddress(InetAddress address) {
      this.address = address;
   }

}
