/* ============================================================================
 * Nom du fichier   : ServerProtocol.java
 * ============================================================================
 * Date de création : 8 mai 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package utils.connections.protocol;

import java.io.PrintStream;

import utils.connections.Channel;

/**
 * TODO
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 *
 */
public class ServerProtocol {
   
   private Channel channel;
   
   public ServerProtocol(Channel channel) {
      this.channel = channel;
   }
   
   public void ping() {
      channel.sendProtocolType(ProtocolType.PING);
   }
   
   public void textMessage(PrintStream stream) {
      String message;
      
      message = channel.receiveString();
      
      stream.println(message);
   }
   
   public void authentifaction() {
      String login = channel.receiveString();
      String password = channel.receiveString();
      
      
   }

}
