/* ============================================================================
 * Nom du fichier   : Launcher.java
 * ============================================================================
 * Date de création : 1 mai 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */

package client;

import game.models.GameModel;
import gui.controller.Login;
import gui.view.JLog;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import settings.Settings;
import utils.Logs;
import core.ConnectionsManager;

/**
 * Classe d'amorce pour lancer le programme.
 * 
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 * 
 */
public class Launcher {
   
   private final static boolean LOGS_ON = false;
   private final static boolean LOGS_FRAME_ON = false;
   private final static boolean DEVELOPPEMENT_MODE_ON = false;

   private static Settings sets;

   /**
    * @param args
    */
   @SuppressWarnings("unused") // enlève les warning pour code mort des logs
   public static void main(String[] args) {
      GameData initData = new GameData();
      GameModel game = new GameModel(initData);
      ConnectionsManager connections = new ConnectionsManager(game);

      // Lance le jeu directement si mode debug
      if (args.length > 0 && args[0].equals("--debug")) {
         new GameLauncher(true).run(initData);
      }
      else {

         // Définition du look and feel.
         try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
         }
         catch (ClassNotFoundException ex) {
            Logs.errors
                  .push("Launcher", "Choosen LookAndFeel does not exists.");
         }
         catch (InstantiationException ex) {
            Logs.errors.push("Launcher",
                  "Unable to create the choosen LookAndFeel.");
         }
         catch (IllegalAccessException ex) {
            Logs.errors.push("Launcher",
                  "LookAndFeel class or initializer is not accessible.");
         }
         catch (UnsupportedLookAndFeelException ex) {
            Logs.errors.push("Launcher",
                  "Choosen LookAndFeel is not supported.");
         }
         catch (ClassCastException ex) {
            Logs.errors.push("Launcher",
                  "Choosen LookAndFeel is not a real LookAndFeel.");
         }

         sets = new Settings();
         sets.loadSettings();

         new Login(connections, new GameLauncher(false));
         
         // Création des logs
         if (LOGS_ON) {
            Logs.setupFiles();
         }
         if (LOGS_ON && LOGS_FRAME_ON) {
            JLog logsFrame = new JLog("Onapi - Logs", 0, 0, 500, 400);
            Logs.addLogsToFrame(logsFrame);
         }
         
         // Auto création du template de langue et du fichier update
         if (DEVELOPPEMENT_MODE_ON) {
            Settings.createTemplateForLanguage("fr");
            Settings.createUpdateForLanguage("fr");
         }
         
      }
   }

}
