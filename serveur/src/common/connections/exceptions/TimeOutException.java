/* ============================================================================
 * Nom du fichier   : TimeOutException.java
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
 * TODO
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 *
 */
public class TimeOutException extends ChannelException {
   
   public TimeOutException() {
      super();
   }
   
   public TimeOutException(String message) {
      super(message);
   }
   
   public TimeOutException(Throwable cause) {
      super(cause);
   }
   
   public TimeOutException(String message, Throwable cause) {
      super(message,cause);
   }

}
