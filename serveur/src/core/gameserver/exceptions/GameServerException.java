/* ============================================================================
 * Nom du fichier   : GameServerException.java
 * ============================================================================
 * Date de création : 19 mai 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package core.gameserver.exceptions;

/**
 * Exception générale liée au serveur de jeu.
 * 
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 * 
 */
public class GameServerException extends RuntimeException {

   /**
    * ID de sérialisation.
    */
   private static final long serialVersionUID = 6130283653529717488L;

   /**
    * Crée une exception indiquant une erreur quelconque du serveur de jeu.
    */
   public GameServerException() {
      super();
   }

   /**
    * Crée une exception indiquant une erreur quelconque du serveur de jeu.
    * 
    * @param message
    *           - le message à joindre à l'exception.
    */
   public GameServerException(String message) {
      super(message);
   }

   /**
    * Crée une exception indiquant une erreur quelconque du serveur de jeu.
    * 
    * @param cause
    *           - la cause de l'exception.
    */
   public GameServerException(Throwable cause) {
      super(cause);
   }

   /**
    * Crée une exception indiquant une erreur quelconque du serveur de jeu.
    * 
    * @param message
    *           - le message à joindre à l'exception.
    * @param cause
    *           - la cause de l'exception.
    */
   public GameServerException(String message, Throwable cause) {
      super(message, cause);
   }

}
