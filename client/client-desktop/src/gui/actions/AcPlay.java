/* ============================================================================
 * Nom du fichier   : AcPlay.java
 * ============================================================================
 * Date de cr�ation : 11 mai 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Gr�goire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package gui.actions;

import java.awt.event.ActionEvent;

import utils.Logs;

import gui.UserAction;

/**
 * TODO
 * @author Crescenzio Fabio
 * @author Decorvet Gr�goire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 *
 */
public class AcPlay extends UserAction {

   @Override
   protected void execute(ActionEvent event, Object[] dependencies) {
      Logs.messages.push("Recherche d'une partie initi�e !");
   }

}
