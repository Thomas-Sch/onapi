import utils.Logs;
import gui.controller.Login;
import gui.view.JLogin;
import gui.view.LogsFrame;

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
 * TODO
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 *
 */
public class Launcher {

   /**
    * @param args
    */
   public static void main(String[] args) {
      System.out.println("Je suis l'interface !");
      new Login();
      
      // Temporaire en attendant de merge.
      (new Logs()).addLogsToFrame(new LogsFrame("Logs", 0, 0, 500, 400));
      
      Logs.messages.push("Salut");
   }

}
