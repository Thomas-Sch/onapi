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

import gui.view.JLog;

import java.io.File;

import log.Log;

/**
 * Contient les différents logs du programme.
 * 
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 *
 */
public class Logs {

   public static Log messages = new Log("messages");
   public static Log errors = new Log("sqlErrors");
   public static Log sqlErrors = new Log("sqlErrors");
   
   /**
    * Initialise les fichiers de sorties
    */
   public static void setupFiles() {
      messages.setOutputFile(new File("Onapi.log"));
      messages.setBufferSize(1);
      
      errors.setOutputFile(new File("Errors.log"));
      errors.setBufferSize(1);
      
      sqlErrors.setOutputFile(new File("SqlErrors.log"));
      sqlErrors.setBufferSize(1);
   }

   /**
    * Ajoute les logs à la fenêtre de logs donnée.
    * 
    * @param frame
    *           - la fenêtre de logs à laquelle ajouter les logs du programme.
    */
   public static void addLogsToFrame(JLog frame) {
      frame.addLogPanel(messages.createLogPanel());
      frame.addLogPanel(errors.createLogPanel());
      frame.addLogPanel(sqlErrors.createLogPanel());
   }

}
