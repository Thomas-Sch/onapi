/* ============================================================================
 * Nom du fichier   : CoreException.java
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
 * Exception générale liée au coeur du serveur.
 * 
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 * 
 */
public class CoreException extends Exception {
   
   /**
    * ID de sérialisation.
    */
   private static final long serialVersionUID = 356320132101265386L;

   /**
    * Crée une exception indiquant une erreur quelconque du coeur du serveur.
    */
   public CoreException() {
      super();
   }

   /**
    * Crée une exception indiquant une erreur quelconque du coeur du serveur.
    * 
    * @param message
    *           - le message à joindre à l'exception.
    */
   public CoreException(String message) {
      super(message);
   }

   /**
    * Crée une exception indiquant une erreur quelconque du coeur du serveur.
    * 
    * @param cause
    *           - la cause de l'exception.
    */
   public CoreException(Throwable cause) {
      super(cause);
   }

   /**
    * Crée une exception indiquant une erreur quelconque du coeur du serveur.
    * 
    * @param message
    *           - le message à joindre à l'exception.
    * @param cause
    *           - la cause de l'exception.
    */
   public CoreException(String message, Throwable cause) {
      super(message, cause);
   }

}
