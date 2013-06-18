/* ============================================================================
 * Nom du fichier   : Ping.java
 * ============================================================================
 * Date de création : 31 mai 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package core.updates.components;

import core.updates.Update;
import core.updates.UpdateVisitor;

/**
 * Mise à jour servant de ping.
 * 
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 * 
 */
public class StandardPing extends Update {

   public StandardPing() {
      super();
   }

   @Override
   public void apply(UpdateVisitor v) {
      v.casePing(this);
   }

}
