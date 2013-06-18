/* ============================================================================
 * Nom du fichier   : GameReady.java
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
 * Mise à jour indiquant que la partie peut commencer.
 * 
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 * 
 */
public class LobbyGameReady extends Update {

   public LobbyGameReady() {
      super();
   }

   @Override
   public void apply(UpdateVisitor v) {
      v.caseLobbyGameReady(this);
   }

}
