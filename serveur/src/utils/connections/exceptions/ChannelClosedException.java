/* ============================================================================
 * Nom du fichier   : ChannelClosedException.java
 * ============================================================================
 * Date de création : 15 mai 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package utils.connections.exceptions;

/**
 * TODO
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 *
 */
public class ChannelClosedException extends RuntimeException {
   
   public ChannelClosedException() {
      super();
   }
   
   public ChannelClosedException(String message) {
      super(message);
   }
   
   public ChannelClosedException(Throwable cause) {
      super(cause);
   }
   
   public ChannelClosedException(String message, Throwable cause) {
      super(message,cause);
   }  

}

