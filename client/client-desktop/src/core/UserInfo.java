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
public class UserInfo extends CoreComponent {
   
   private ConnectedUser user;
   
   public UserInfo(ConnectedUser user) {
      this.user = user;
   }
   
   public int getServerSlotNumber() {
      return user.getServerSlot();
   }
   
   public ActivityType getActivity() {
      return user.getActivity();
   }
   
}