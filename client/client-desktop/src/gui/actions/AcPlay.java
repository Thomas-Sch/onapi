/* ============================================================================
 * Nom du fichier   : AcPlay.java
 * ============================================================================
 * Date de cr�ation : 11 mai 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package gui.actions;

import java.awt.event.ActionEvent;

import client.Onapi;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import utils.Logs;

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
      Logs.messages.push("Recherche d'une partie initiée !");
      LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
      cfg.title = "ONAPI";
      cfg.useGL20 = true;
      cfg.width = 480;
      cfg.height = 320;

      new LwjglApplication(new Onapi(), cfg);
   }

}
