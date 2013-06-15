/* ============================================================================
 * Nom du fichier   : ConnectedUser.java
 * ============================================================================
 * Date de création : 14 juin 2013
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
 * 
 * TODO
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 *
 */
public class ConnectedUser extends ObservableComponent implements Serializable {
   
   /**
    * ID de sérialisation.
    */
   private static final long serialVersionUID = -2074702964251368698L;
   
   private boolean isConnected;
   
   private int userId;
   
   private ActivityType activity;
   
   private String accountName;
   
   /**
    * Crée le conteneur des informations de base d'un utilisateur.
    * @param userId - son identifiant pour le serveur.
    * @param activity - son activité au sein du serveur.
    * @param accountName - son nom de compte.
    */
   public ConnectedUser(int userId, ActivityType activity, String accountName) {
      this.userId = userId;
      this.activity = activity;
      this.accountName = accountName;
      
      isConnected = true;
   }
   
   public boolean isConnected() {
      return isConnected;
   }
   
   public void setConnected(boolean isConnected) {
      if (this.isConnected != isConnected) {
         this.isConnected = isConnected;
         setChangedAndNotifyObservers(this);
      }
   }
   
   public int getUserId() {
      return userId;
   }
   
   public void setUserId(int userId) {
      if (userId >= 0 && this.userId != userId) {
         this.userId = userId;
         setChangedAndNotifyObservers(this);
      }
   }
   
   public ActivityType getActivity() {
      return activity;
   }
   
   public void setServerActivity(ActivityType activity) {
      if (this.activity != activity) {
         this.activity = activity;
         setChangedAndNotifyObservers(this);
      }
   }
   
   public String getAccountName() {
      return accountName;
   }
   
   public void setAccountName(String accountName) {
      if (this.accountName.equals(accountName)) {
         this.accountName = accountName;
         setChangedAndNotifyObservers(this);
      }
   }
   
   @Override
   public boolean equals(Object o) {
      if (o instanceof ConnectedUser) {
         return userId == ((ConnectedUser)o).userId;
      }
      else {
         return false;
      }
   }
   
   @Override
   public String toString() {
      return accountName + " [" + activity + "]";
   }

}
