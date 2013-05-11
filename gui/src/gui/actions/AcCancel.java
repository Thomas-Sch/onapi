/* ============================================================================
 * Nom du fichier   : AcCancel.java
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

import javax.swing.JFrame;

import gui.UserAction;

/**
 * TODO
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 *
 */
public class AcCancel extends UserAction {
   
   public AcCancel(Object ... dependencies) {
      super(dependencies);
   }

   @Override
   protected void execute(ActionEvent event, Object[] dependencies) {
      JFrame view = (JFrame)dependencies[0];
      view.dispose();
   }

}
