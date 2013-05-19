/* ============================================================================
 * Nom du fichier   : Log.java
 * ============================================================================
 * Date de création : 11 mai 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */

package utils;
import gui.view.LogsFrame;

import java.io.File;

import log.Log;


/**
 * Contrôleur de la fenêtre de login.
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 *
 */
public class Logs {
   
   public static Log messages = new Log("messages", new File("Onapi.log"), 1);
   
   public static Log errors = new Log("errors", new File("Errors.log"), 1);
   
   public static Log sqlErrors = new Log("sqlErrors", new File("sqlErrors.log"), 1);
   
   
   public static void addLogsToFrame(LogsFrame frame) {
      frame.addLogPanel(messages.createLogPanel());
      frame.addLogPanel(errors.createLogPanel());
      frame.addLogPanel(sqlErrors.createLogPanel());
   }

}
