import java.io.IOException;

import core.Core;

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

/**
 * Classe d'amorce pour le serveur.
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 *
 */
public class Server {
   
   
   public static void main(String[] args) throws IOException {
      
      Core core = new Core(true);
      
      core.start();
      
      
   }

}
