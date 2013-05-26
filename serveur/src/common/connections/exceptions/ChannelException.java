/* ============================================================================
 * Nom du fichier   : ConnectionException.java
 * ============================================================================
 * Date de création : 8 mai 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package common.connections.exceptions;

/**
 * Exception relatives à un canal de communication.
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 *
 */
public class ChannelException extends RuntimeException {
   
   public ChannelException() {
      super();
   }
   
   public ChannelException(String message) {
      super(message);
   }
   
   public ChannelException(Throwable cause) {
      super(cause);
   }
   
   public ChannelException(String message, Throwable cause) {
      super(message,cause);
   }  

}
