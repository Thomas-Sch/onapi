/* ============================================================================
 * Nom du fichier   : DBController.java
 * ============================================================================
 * Date de création : 15 mai 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package database;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * TODO
 * 
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 * 
 */
public class DBController {

   private Database database;

   public DBController(String dbName) {
      database = new Database(dbName);
   }

   /**
    * Ouvre la connexion � la base de donn�es.
    */
   public void openConnection() {
      database.connect();
   }

   /**
    * Crée un utilisateur dans la base de données.
    * 
    * @param login
    * @param password
    * @return
    */
   public boolean createUser(String login, String password) {
      ResultSet result = database.getResultOf("SELECT 1 from User where name='"
            + login + "'");
      try {
         if (result.next())
            return false;
         else
            database.execute("INSERT INTO User(name, password) VALUES('"
                  + login + "', '" + password + "')");
         return true;
      }
      catch (SQLException e) {
         // TODO Auto-generated catch block
         //e.printStackTrace();
         return false;
      }
   }

   /**
    * Test la connection avec le login password de l'utilisateur.
    * 
    * @param login
    * @param password
    * @return correct ou non
    */
   public boolean checkUserConnection(String login, String password) {
      ResultSet result = database.getResultOf("SELECT 1 from User where name='"
            + login + "' and password='" + password + "'");
      try {
         if (result.next()) return true;
      }
      catch (SQLException e) {
         // TODO Auto-generated catch block
         // e.printStackTrace();
         return false;
      }
      return false;
   }

   /**
    * Termine la connexion avec la base de donn�es
    * 
    * @return
    */
   public boolean closeConnection() {
      return database.disconnect();
   }

}
