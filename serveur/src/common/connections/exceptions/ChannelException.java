/* ============================================================================
 * Nom du fichier   : ChannelException.java
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
 * Exception relative à un canal de communication.
 * 
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 * 
 */
public class ChannelException extends RuntimeException {

   /**
    * ID de sérialisation.
    */
   private static final long serialVersionUID = 10001L;

   /**
    * Crée une exception indiquant une erreur quelconque du canal.
    */
   public ChannelException() {
      super();
   }

   /**
    * Crée une exception indiquant une erreur quelconque du canal.
    * 
    * @param message
    *           - le message à joindre à l'exception.
    */
   public ChannelException(String message) {
      super(message);
   }

   /**
    * Crée une exception indiquant une erreur quelconque du canal.
    * 
    * @param cause
    *           - la cause de l'exception.
    */
   public ChannelException(Throwable cause) {
      super(cause);
   }

   /**
    * Crée une exception indiquant une erreur quelconque du canal.
    * 
    * @param message
    *           - le message à joindre à l'exception.
    * @param cause
    *           - la cause de l'exception.
    */
   public ChannelException(String message, Throwable cause) {
      super(message, cause);
   }

}
