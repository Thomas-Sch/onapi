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
   
   private int serverSlot;
   
   private ActivityType activity;
   
   private String accountName;
   
   /**
    * Crée le conteneur des informations de base d'un utilisateur.
    * @param serverSlot - son emplacement sur le serveur.
    * @param activity - son activité au sein du serveur.
    * @param accountName - son nom de compte.
    */
   public ConnectedUser(int serverSlot, ActivityType activity, String accountName) {
      this.serverSlot = serverSlot;
      this.activity = activity;
      this.accountName = accountName;
   }
   
   public int getServerSlot() {
      return serverSlot;
   }
   
   public void setServerSlot(int serverSlot) {
      if (serverSlot >= 0 && this.serverSlot != serverSlot) {
         this.serverSlot = serverSlot;
         setChangedAndNotifyObservers();
      }
   }
   
   public ActivityType getActivity() {
      return activity;
   }
   
   public void setServerActivity(ActivityType activity) {
      if (this.activity != activity) {
         this.activity = activity;
         setChangedAndNotifyObservers();
      }
   }
   
   public String getAccountName() {
      return accountName;
   }
   
   public void setAccountName(String accountName) {
      if (this.accountName.equals(accountName)) {
         this.accountName = accountName;
         setChangedAndNotifyObservers();
      }
   }
   
   @Override
   public String toString() {
      return accountName + " [" + activity + "]";
   }

}
