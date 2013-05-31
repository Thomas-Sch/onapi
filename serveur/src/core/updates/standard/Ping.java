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
package core.updates.standard;

import core.updates.Update;
import core.updates.StandardUpdateVisitor;


/**
 * TODO
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 *
 */
public class Ping extends Update {
   
   public Ping() {
      super();
   }

   @Override
   public void apply(StandardUpdateVisitor v) {
      v.casePing(this);
   }

}
