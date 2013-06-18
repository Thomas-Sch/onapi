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
 * Exception relative à une erreur de protocole.
 * 
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 * 
 */
public class ProtocolException extends RuntimeException {

   /**
    * ID de sérialisation.
    */
   private static final long serialVersionUID = 10002L;

   /**
    * Crée une exception indiquant une erreur de protocole.
    */
   public ProtocolException() {
      super();
   }

   /**
    * Crée une exception indiquant une erreur de protocole.
    * 
    * @param message
    *           - le message à joindre à l'exception.
    */
   public ProtocolException(String message) {
      super(message);
   }

   /**
    * Crée une exception indiquant une erreur de protocole.
    * 
    * @param cause
    *           - la cause de l'exception.
    */
   public ProtocolException(Throwable cause) {
      super(cause);
   }

   /**
    * Crée une exception indiquant une erreur de protocole.
    * 
    * @param message
    *           - le message à joindre à l'exception.
    * @param cause
    *           - la cause de l'exception.
    */
   public ProtocolException(String message, Throwable cause) {
      super(message, cause);
   }

}
