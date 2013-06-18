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
package common.connections.exceptions;

/**
 * Indique que le canal utilisé pour communiquer a été fermé.
 * 
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 * 
 */
public class ChannelClosedException extends RuntimeException {

   /**
    * ID de sérialisation.
    */
   private static final long serialVersionUID = 10000L;

   /**
    * Crée une exception indiquant que le canal a été fermé.
    */
   public ChannelClosedException() {
      super();
   }

   /**
    * Crée une exception indiquant que le canal a été fermé.
    * 
    * @param message
    *           - le message à joindre à l'exception.
    */
   public ChannelClosedException(String message) {
      super(message);
   }

   /**
    * Crée une exception indiquant que le canal a été fermé.
    * 
    * @param cause
    *           - la cause de l'exception.
    */
   public ChannelClosedException(Throwable cause) {
      super(cause);
   }

   /**
    * Crée une exception indiquant que le canal a été fermé.
    * 
    * @param message
    *           - le message à joindre à l'exception.
    * @param cause
    *           - la cause de l'exception.
    */
   public ChannelClosedException(String message, Throwable cause) {
      super(message, cause);
   }

}
