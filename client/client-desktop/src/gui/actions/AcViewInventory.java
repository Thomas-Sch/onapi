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

import gui.utils.Positions;
import gui.utils.Positions.ScreenPosition;
import gui.view.JInventory;

import java.awt.event.ActionEvent;

import settings.Language.Text;
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
      JInventory view = new JInventory();
      view.setTitle(Text.APP_TITLE.toString() + " - " + Text.INVENTORY_TITLE.toString());
      Positions.setPositionOnScreen(view, ScreenPosition.CENTER);
      view.setVisible(true);
   }

}
