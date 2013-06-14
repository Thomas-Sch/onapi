/* ============================================================================
 * Nom du fichier   : AcKick.java
 * ============================================================================
 * Date de création : 14 juin 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package gui.actions.admin;

import java.awt.event.ActionEvent;

import core.ConnectionsManager;
import gui.actions.UserAction;

/**
 * 
 * TODO
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 *
 */
public class AcKick extends UserAction {
   
   public AcKick(ConnectionsManager connections, Integer selectedPlayer) {
      super(connections, selectedPlayer);
   }

   @Override
   protected void execute(ConnectionsManager connections, ActionEvent event,
         Object[] dependencies) {
      protocolRequest.adminKickPlayer((Integer)dependencies[0], "Pas de chance, vous avez été éjecté !");
   }

}
