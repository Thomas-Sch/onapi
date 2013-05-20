/* ============================================================================
 * Nom du fichier   : LobbyException.java
 * ============================================================================
 * Date de création : 19 mai 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package core.lobby.exceptions;

/**
 * Exception générique liée au salon d'attente.
 * 
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 *
 */
public class LobbyException extends Exception {
   
   public LobbyException() {
      super();
   }
   
   public LobbyException(String message) {
      super(message);
   }
   
   public LobbyException(Throwable cause) {
      super(cause);
   }
   
   public LobbyException(String message, Throwable cause) {
      super(message, cause);
   }

}
