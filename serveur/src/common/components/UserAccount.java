/* ============================================================================
 * Nom du fichier   : UserAccount.java
 * ============================================================================
 * Date de création : 16 mai 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package common.components;

import java.io.Serializable;


/**
 * Représente un compte d'utilisateur de la base de donnée.
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 *
 */
public class UserAccount implements Serializable {
   
   /**
    * ID de sérialisation.
    */
   private static final long serialVersionUID = 3154100529354245563L;

   private AccountType type;
   
   private int id;
   
   private String login;
   
   private String password;
   
   private String firstName;
   
   private String lastName;
   
   public UserAccount(int id, AccountType type, String login, String password,
         String firstName, String lastName) {
      this.id = id;
      this.type = type;
      this.login = login;
      this.password = password;
      this.firstName = firstName;
      this.lastName = lastName;
   }
   
   public int getId() {
      return id;
   }
   
   public void setType(AccountType type) {
      this.type = type;
   }
   
   public AccountType getType(){
      return type;
   }
   
   public void setLogin(String login) {
      this.login = login;
   }
   
   public String getLogin() {
      return login;
   }
   
   public void setPassword(String password) {
      this.password = password;
   }
   
   public String getPassword() {
      return password;
   }
   
   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }
   
   public String getFirstName() {
      return firstName;
   }
   
   public void setLastName(String lastName) {
      this.lastName = lastName;
   }
   
   public String getLastName() {
      return lastName;
   }
   
   @Override
   public String toString() {
      return type + " - " + login + " : " + firstName + " " + lastName;
   }

}
