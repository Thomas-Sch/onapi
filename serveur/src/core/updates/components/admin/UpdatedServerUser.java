/* ============================================================================
 * Nom du fichier   : UpdatedServerUser.java
 * ============================================================================
 * Date de création : 14 juin 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package core.updates.components.admin;

import common.components.ConnectedUser;

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
public class UpdatedServerUser extends Update {
   
   public ConnectedUser user;
   
   public UpdatedServerUser(ConnectedUser user) {
      super();
      this.user = user;
   }

   @Override
   public void apply(UpdateVisitor v) {
      v.caseUpdateServerUser(this);
   }

}
