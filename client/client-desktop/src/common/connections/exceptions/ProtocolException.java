/* ============================================================================
 * Nom du fichier   : ProtocolException.java
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
public class ProtocolException extends RuntimeException {
   
   public ProtocolException() {
      super();
   }
   
   public ProtocolException(String message) {
      super(message);
   }
   
   public ProtocolException(Throwable cause) {
      super(cause);
   }
   
   public ProtocolException(String message, Throwable cause) {
      super(message,cause);
   }  

}
