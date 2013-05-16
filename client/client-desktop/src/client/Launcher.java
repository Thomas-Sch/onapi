package client;
import gui.controller.Login;
import gui.view.LogsFrame;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import settings.Settings;
import utils.Logs;

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

/**
 * Classe d'amorce pour lancer le programme.
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 *
 */
public class Launcher {
   
   private static Settings sets;

   /**
    * @param args
    */
   public static void main(String[] args) {      
      // D�finition du look and feel.
      try {
         UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
      }
      catch (ClassNotFoundException ex) {
         Logs.errors.push("Launcher", "Choosen LookAndFeel does not exists.");
      }
      catch (InstantiationException ex) {
         Logs.errors.push("Launcher", "Unable to create the choosen LookAndFeel.");
      }
      catch (IllegalAccessException ex) {
         Logs.errors.push("Launcher", "LookAndFeel class or initializer is not accessible.");
      }
      catch (UnsupportedLookAndFeelException ex) {
         Logs.errors.push("Launcher", "Choosen LookAndFeel is not supported.");
      }
      catch (ClassCastException ex) {
         Logs.errors.push("Launcher", "Choosen LookAndFeel is not a real LookAndFeel."); 
      }
      
      sets = new Settings();
      sets.loadSettings();
      
      
      new Login();
      
      // Temporaire en attendant de merge.
      (new Logs()).addLogsToFrame(new LogsFrame("Logs", 0, 0, 500, 400));
      
      Settings.createTemplateForLanguage("fr");
      Settings.createUpdateForLanguage("fr");
   }

}
