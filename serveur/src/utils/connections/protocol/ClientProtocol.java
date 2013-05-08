/* ============================================================================
 * Nom du fichier   : Protocol.java
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

import utils.connections.Channel;
import utils.connections.exceptions.ProtocolException;
import utils.connections.exceptions.TimeOutException;

/**
 * TODO
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 *
 */
public class ClientProtocol {
   
   private Channel channel;
   
   public ClientProtocol(Channel channel) {
      this.channel = channel;
   }
   
   public long ping() {
      ProtocolType proType;
      
      long time = System.currentTimeMillis();
      
      channel.sendProtocolType(ProtocolType.PING);
      
      proType = channel.receiveProtocolType();
      
      if (proType == ProtocolType.PING) {
         return System.currentTimeMillis() - time;
      }
      else {
         throw new ProtocolException("Wrong protocol for PING");
      }
   }
   
   public boolean keepAlive() {
      boolean stillAlive = false;
      
      try {
         ping();
         
         stillAlive = true;
      }
      catch (TimeOutException e) {
         
      }
      
      return stillAlive;
   }
   
   public void sendMessage(String message) {
      
      channel.sendProtocolType(ProtocolType.TEXT_MESSAGE);
      channel.sendString(message);
      
   }

}
