package utils;
import gui.view.LogsFrame;

import java.io.File;

import log.Log;

/* ============================================================================
 * Nom du fichier   : MidasLog.java
 * ============================================================================
 * Date de création : 17 avr. 2013
 * ============================================================================
 * Auteurs          : Biolzi Sébastien
 *                    Brito Carvalho Bruno
 *                    Decorvet Grégoire
 *                    Schweizer Thomas
 *                    Sinniger Marcel
 * ============================================================================
 */

/**
 * TODO
 * @author Biolzi Sébastien
 * @author Brito Carvalho Bruno
 * @author Decorvet Grégoire
 * @author Schweizer Thomas
 * @author Sinniger Marcel
 *
 */
public class MidasLogs {
   
   public static Log messages = new Log("messages", new File("Midas.log"), 1);
   
   public static Log errors = new Log("errors", new File("Errors.log"), 1);
   
   public static Log sqlErrors = new Log("sqlErrors", new File("sqlErrors.log"), 1);
   
   
   public static void addLogsToFrame(LogsFrame frame) {
      frame.addLogPanel(messages.createLogPanel());
      frame.addLogPanel(errors.createLogPanel());
      frame.addLogPanel(sqlErrors.createLogPanel());
   }

}
