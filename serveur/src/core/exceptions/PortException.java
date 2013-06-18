/* ============================================================================
 * Nom du fichier   : PortException.java
 * ============================================================================
 * Date de création : 6 mai 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package core.exceptions;

import java.io.IOException;

/**
 * Exception indiquant une erreur liée au port d'écoute.
 * 
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 * 
 */
public class PortException extends IOException {

   /**
    * ID de sérialisation.
    */
   private static final long serialVersionUID = 20002L;

   /**
    * Crée une exception indiquant une erreur quelconque du port d'écoute.
    */
   public PortException() {
      super();
   }

   /**
    * Crée une exception indiquant une erreur quelconque du port d'écoute.
    * 
    * @param message
    *           - le message à joindre à l'exception.
    */
   public PortException(String message) {
      super(message);
   }

   /**
    * Crée une exception indiquant une erreur quelconque du port d'écoute.
    * 
    * @param cause
    *           - la cause de l'exception.
    */
   public PortException(Throwable cause) {
      super(cause);
   }

   /**
    * Crée une exception indiquant une erreur quelconque du port d'écoute.
    * 
    * @param message
    *           - le message à joindre à l'exception.
    * @param cause
    *           - la cause de l'exception.
    */
   public PortException(String message, Throwable cause) {
      super(message, cause);
   }

}
