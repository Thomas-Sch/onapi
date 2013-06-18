/* ============================================================================
 * Nom du fichier   : Server.java
 * ============================================================================
 * Date de création : 1 mai 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */

import settings.Settings;
import core.Core;

/**
 * Classe d'amorce pour le serveur.
 * 
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 * 
 */
public class Server {
   
   private static boolean logs = false;

   /**
    * Méthode principale à appeler pour démarrer le serveur.
    * 
    * @param args
    *           - les arguments de l'appel.
    * @throws IOException
    */
   public static void main(String[] args) {
      
      if (args.length > 0) {
         Settings.GAMESERVER_PLAYER_NUMBER = Integer.parseInt(args[0]);
      }
      
      if (args.length == 2) {
         if (args[1].equalsIgnoreCase("-logs")) {
            logs = true;
         }
      }

      // Création du coeur du serveur
      Core core = new Core(logs);

      // Démarrage du serveur
      core.start();

   }

}
