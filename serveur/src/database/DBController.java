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
import java.util.LinkedList;

import common.components.AccountType;
import common.components.UserAccount;

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
    * Ouvre la connexion à la base de données.
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
   public UserAccount createUser(String login, String password, String role) {
      ResultSet result = database.getResultOf("SELECT 1 from User where name='"
            + login + "'");
      try {
         if (result.next())
            return null;
         else{
            database.execute("INSERT INTO User(name, password, role) VALUES('"
                  + login + "', '" + password + "')");
         
            //recupère l'id inseré
            result = database.getResultOf("SELECT id, role from User where name='"
               + login + "' order by id desc limit 0,1");
            return new UserAccount(result.getInt(result.findColumn("id")), 
               (result.getString(result.findColumn("role")).equals("admin")?
                     AccountType.ADMINISTRATOR:AccountType.USER),
               login,
               password, 
               "", 
               "");
            }
         }
      catch (SQLException e) {
         // TODO Auto-generated catch block
         //e.printStackTrace();
         return null;
      }
   }
   public LinkedList<Item> getSkills(int player_id){
      ResultSet result = database.getResultOf("SELECT id, name from Item INNER JOIN Skill ON Skill.id = Item.id where user_id='"
            + player_id + "'");
      LinkedList<Item> skills = new LinkedList<Item>();
      
      try {
         while(result.next()){
            skills.add(new Item(result.getInt(result.findColumn("id")), result.getString(result.findColumn("name"))));
         }
         return null;
      }
      catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
         return null;
      }
   }

   public LinkedList<Item> getWeapons(int player_id){
      ResultSet result = database.getResultOf("SELECT id, name from Item INNER JOIN Weapon ON Skill.id = Item.id where user_id='"
            + player_id + "'");
      LinkedList<Item> weapons = new LinkedList<Item>();
      
      try {
         while(result.next()){
            weapons.add(new Item(result.getInt(result.findColumn("id")), result.getString(result.findColumn("name"))));
         }
         return null;
      }
      catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
         return null;
      }
   }
   
   public LinkedList<Item> getUpgrades(int player_id){
      ResultSet result = database.getResultOf("SELECT id, name from Item INNER JOIN Upgrade ON Skill.id = Item.id where user_id='"
            + player_id + "'");
      LinkedList<Item> upgrades = new LinkedList<Item>();
      
      try {
         while(result.next()){
            upgrades.add(new Item(result.getInt(result.findColumn("id")), result.getString(result.findColumn("name"))));
         }
         return null;
      }
      catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
         return null;
      }
    }

   /**
    * Test la connection avec le login password de l'utilisateur.
    * 
    * @param login
    * @param password
    * @return correct ou non
    */
   public UserAccount checkUserConnection(String login, String password) {
      ResultSet result = database.getResultOf("SELECT id, role from User where name='"
            + login + "' and password='" + password + "'");
      try {
         if (result.next()) 
            return new UserAccount(result.getInt(result.findColumn("id")), 
                                  (result.getString(result.findColumn("role")).equals("admin")?
                                        AccountType.ADMINISTRATOR:AccountType.USER),
                                  login,
                                  password, 
                                  "", 
                                  "");
      }
      catch (SQLException e) {
         // TODO Auto-generated catch block
         // e.printStackTrace();
         return null;
      }
      return null;
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
