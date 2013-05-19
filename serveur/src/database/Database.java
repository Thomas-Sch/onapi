package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Cette classe fait l'interface avec la base de données.
 * 
 * @author Leucistic source :
 *         http://www.siteduzero.com/forum/sujet/sqlite-et-java-10814
 * 
 */
public class Database

{
   private String dbName;
   public Connection connection;
   private Statement requete;

   /**
    * Constructeur de la classe Database
    * 
    * @param dbName
    *           Le nom de la base de données
    */
   public Database(String dbName) {
      // Charge le driver sqlite JDBC en utilisant le class loader actuel
      try {
         Class.forName("org.sqlite.JDBC");
      }
      catch (ClassNotFoundException e1) {
         System.err.println(e1.getMessage());
      }

      this.dbName = dbName;
      this.connection = null;
   }

   /**
    * Ouvre la base de données spécifiée
    * 
    * @return True si la connection a été réussie. False sinon.
    */
   public boolean connect() {
      try {
         // Etabli la connection
         connection = DriverManager.getConnection("jdbc:sqlite:" + this.dbName);
         // Déclare l'objet qui permet de faire les requêtes
         requete = connection.createStatement();

         // Le PRAGMA synchronous de SQLite permet de vérifier chaque écriture
         // avant d'en faire une nouvelle.
         // Le PRAGMA count_changes de SQLite permet de compter le nombre de
         // changements fait sur la base
         // Résultats de mes tests :
         // synchronous OFF, une insertion est 20 fois plus rapide.
         // La différences avec le count_changes est de l'ordre de la �s.
         // Les autres PRAGMA : http://www.sqlite.org/pragma.html

         requete.executeUpdate("PRAGMA synchronous = OFF;");
         requete.setQueryTimeout(30);

         return true;
      }
      catch (SQLException e) {
         // message = "out of memory" souvent le resultat de la BDD pas trouvée
         e.printStackTrace();
         return false;
      }
   }

   /**
    * Ferme la connection à la base de données
    * 
    * @return True si la connection a bien été fermée. False sinon.
    */
   public boolean disconnect() {
      try {
         if (connection != null) connection.close();

         return true;
      }
      catch (SQLException e) {
         e.printStackTrace();
         return false;
      }
   }

   /**
    * Permet de faire une requête SQL
    * 
    * @param requete
    *           La requête SQL (avec un ";" à la fin)
    * @return Un ResultSet contenant le résultat de la requête
    */
   public ResultSet getResultOf(String requete) {
      try {
         return this.requete.executeQuery(requete);
      }
      catch (SQLException e) {
         e.printStackTrace();
      }

      return null;
   }

   /**
    * Permet d'executer une requete SQL sans se soucier du résultat.
    * 
    * @param requete
    *           La requete SQL de modification
    */
   public void execute(String requete) {
      try {
         this.requete.execute(requete);
      }
      catch (SQLException e) {
         e.printStackTrace();
      }
   }

   /**
    * 
    * Permet de modifier une entrée de la base de données..
    * 
    * @param requete
    *           La requete SQL de modification
    */
   public void updateValue(String requete) {
      try {
         this.requete.executeUpdate(requete);
      }
      catch (SQLException e) {
         e.printStackTrace();
      }
   }
}