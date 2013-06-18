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
 * Exception relative à un temps d'attente dépassé.
 * 
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 * 
 */
public class TimeOutException extends ChannelException {

   /**
    * ID de sérialisation.
    */
   private static final long serialVersionUID = 10003L;

   /**
    * Crée une exception indiquant un temps d'attente dépassé.
    */
   public TimeOutException() {
      super();
   }

   /**
    * Crée une exception indiquant un temps d'attente dépassé.
    * 
    * @param message
    *           - le message à joindre à l'exception.
    */
   public TimeOutException(String message) {
      super(message);
   }

   /**
    * Crée une exception indiquant un temps d'attente dépassé
    * 
    * @param cause
    *           - la cause de l'exception.
    */
   public TimeOutException(Throwable cause) {
      super(cause);
   }

   /**
    * Crée une exception indiquant un temps d'attente dépassé.
    * 
    * @param message
    *           - le message à joindre à l'exception.
    * @param cause
    *           - la cause de l'exception.
    */
   public TimeOutException(String message, Throwable cause) {
      super(message, cause);
   }

}
