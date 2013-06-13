/* ============================================================================
 * Nom du fichier   : Kicked.java
 * ============================================================================
 * Date de création : 11 juin 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package core.updates.components.admin;

import core.updates.Update;
import core.updates.UpdateVisitor;

/**
 * TODO
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 *
 */
public class Kicked extends Update {
   
   public String message;
   
   public Kicked(String kickMessage) {
      super();
      message = kickMessage;
   }
   
   @Override
   public void apply(UpdateVisitor v) {
      v.caseKicked(this);
   }

}
