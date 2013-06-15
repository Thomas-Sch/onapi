/* ============================================================================
 * Nom du fichier   : CoreRuntimeException.java
 * ============================================================================
 * Date de création : 19 mai 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package core.exceptions;

/**
 * Exception générale liée au coeur du client.
 * 
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 * 
 */
public class CoreRuntimeException extends RuntimeException {

   /**
    * ID de sérialisation.
    */
   private static final long serialVersionUID = 4410946800873681422L;

   /**
    * Crée une exception indiquant une erreur quelconque du coeur du client.
    */
   public CoreRuntimeException() {
      super();
   }

   /**
    * Crée une exception indiquant une erreur quelconque du coeur du client.
    * 
    * @param message
    *           - le message à joindre à l'exception.
    */
   public CoreRuntimeException(String message) {
      super(message);
   }

   /**
    * Crée une exception indiquant une erreur quelconque du coeur du client.
    * 
    * @param cause
    *           - la cause de l'exception.
    */
   public CoreRuntimeException(Throwable cause) {
      super(cause);
   }

   /**
    * Crée une exception indiquant une erreur quelconque du coeur du client.
    * 
    * @param message
    *           - le message à joindre à l'exception.
    * @param cause
    *           - la cause de l'exception.
    */
   public CoreRuntimeException(String message, Throwable cause) {
      super(message, cause);
   }

}
