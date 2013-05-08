import utils.MidasLogs;
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
      new JLogin();
      
      // Temporaire en attendant de merge.
      (new MidasLogs()).addLogsToFrame(new LogsFrame("Logs", 0, 0, 500, 400));
      
      MidasLogs.messages.push("Salut");
   }

}
