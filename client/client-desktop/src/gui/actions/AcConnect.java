/* ============================================================================
 * Nom du fichier   : AcConnect.java
 * ============================================================================
 * Date de création : 11 mai 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Gr�goire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package gui.actions;

import gui.controller.MainFrame;
import gui.view.JLogin;

import java.awt.event.ActionEvent;

import utils.Logs;

/**
 * TODO
 * @author Crescenzio Fabio
 * @author Decorvet Gr�goire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 *
 */
public class AcConnect extends UserAction {
   
   public AcConnect(Object ... dependencies) {
      super(dependencies);
   }

   @Override
   protected void execute(ActionEvent event, Object[] dependencies) {
      JLogin view = (JLogin)dependencies[0];
      Logs.messages.push("Requ�te de connexion au serveur.");
      Logs.messages.push("Login: " + view.getLogin());
      Logs.messages.push("Mdp: " + view.getPassword());
      
      //La connection est ok par défaut. Dans cette version.
      new MainFrame(view.getLogin(), view.getPassword());
   }

}
