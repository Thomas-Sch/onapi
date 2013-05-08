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
 * TODO
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 *
 */
public class PortException extends IOException {
   
   public PortException() {
      super();
   }
   
   public PortException(String message) {
      super(message);
   }
   
   public PortException(Throwable cause) {
      super(cause);
   }
   
   public PortException(String message, Throwable cause) {
      super(message, cause);
   }

}
