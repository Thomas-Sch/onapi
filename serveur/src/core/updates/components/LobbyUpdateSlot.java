/* ============================================================================
 * Nom du fichier   : LobbyUpdateSlot.java
 * ============================================================================
 * Date de création : 1 juin 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package core.updates.components;

import common.components.lobby.PlayerStatus;

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
public class LobbyUpdateSlot extends Update {
   
   public int slotNumber;
   public PlayerStatus status;
   
   public LobbyUpdateSlot(int slotNumber, PlayerStatus status) {
      super();
      this.slotNumber = slotNumber;
      this.status = status;
   }

   @Override
   public void apply(UpdateVisitor v) {
      v.caseLobbyUpdateSlot(this);
   }

}
