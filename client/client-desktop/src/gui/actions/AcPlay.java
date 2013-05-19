/* ============================================================================
 * Nom du fichier   : AcPlay.java
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

import gui.view.JLobby;

import java.awt.event.ActionEvent;

import settings.Language.Text;
import utils.Logs;
import client.GameLauncher;

/**
 * Contrôleur et action du jeu.
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 *
 */
public class AcPlay extends UserAction {

   @Override
   protected void execute(ActionEvent event, Object[] dependencies) {
      Logs.messages.push("Recherche d'une partie initiée !");
      JLobby lobby = new JLobby();
      lobby.setVisible(true);
      lobby.setTitle(Text.APP_TITLE.toString() + " - " + Text.LOBBY_TITLE.toString());
      new GameLauncher(true); // TODO true = temporaire
   }

}
