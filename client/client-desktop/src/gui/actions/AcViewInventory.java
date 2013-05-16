/* ============================================================================
 * Nom du fichier   : AcViewInventory.java
 * ============================================================================
 * Date de création : 11 mai 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package gui.actions;

import java.awt.event.ActionEvent;

import utils.Logs;

/**
 * TODO
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 *
 */
public class AcViewInventory extends UserAction {

   @Override
   protected void execute(ActionEvent event, Object[] dependencies) {
      Logs.messages.push("Ouverture de la fenêtre pour voir son inventaire");
   }

}
