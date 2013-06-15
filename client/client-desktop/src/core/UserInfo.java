/* ============================================================================
 * Nom du fichier   : UserInfo.java
 * ============================================================================
 * Date de création : 14 juin 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package core;

import java.util.Observable;
import java.util.Observer;

import common.components.ActivityType;
import common.components.ConnectedUser;

/**
 * 
 * TODO
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 *
 */
public class UserInfo extends CoreComponent implements Observer {
   
   private ConnectedUser user;
   
   public UserInfo(ConnectedUser user) {
      this.user = user;
      this.user.addObserver(this);
   }
   
   public void update(ConnectedUser user) {
      this.user.startMultipleChanges();
      this.user.setConnected(user.isConnected());
      this.user.setUserId(user.getUserId());
      this.user.setAccountName(user.getAccountName());
      this.user.setServerActivity(user.getActivity());
      this.user.endMultipleChanges();
   }
   
   public void update(UserInfo userInfo) {
      user.startMultipleChanges();
      user.setConnected(userInfo.isConnected());
      user.setUserId(userInfo.getUserId());
      user.setAccountName(userInfo.getAccountName());
      user.setServerActivity(userInfo.getActivity());
      user.endMultipleChanges();
   }
   
   public boolean isConnected() {
      return user.isConnected();
   }
   
   public int getUserId() {
      return user.getUserId();
   }
   
   public ActivityType getActivity() {
      return user.getActivity();
   }
   
   public String getAccountName() {
      return user.getAccountName();
   }
   
   @Override
   public String toString() {
      return user.toString();
   }

   @Override
   public void update(Observable arg0, Object arg1) {
      setChangedAndNotifyObservers(arg1);
   }
   
}