/* ============================================================================
 * Nom du fichier   : Settings.java
 * ============================================================================
 * Date de création : 13 juin 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package settings;

/**
 * Contient les différentes options globales du serveur.
 * 
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 * 
 */
public class Settings {

   // Options de développement
   public static final boolean DEBUG_MODE_ON = true;
   
   // Options du serveur
   public static final String GAMESERVER_NAME = "GameServer";
   public static final int GAMESERVER_PLAYER_NUMBER = 4;
   public static final int GAMESERVER_TEAM_NUMBER = 2;
   
   // Options concernant le réseau
   public static final int PORT_NUMBER = 1234;
   public static final int TIMEOUT_CLIENT = 15000;
   
   // Options de gestion interne
   public static final String DATABASE_DIRECTORY = "database";
   public static final String DATABASE_NAME = "onapi.db";

   /**
    * Constructeur privé pour interdire l'instanciation de la classe.
    */
   private Settings() { }

}
