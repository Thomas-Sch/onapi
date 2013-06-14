/* ============================================================================
 * Nom du fichier   : UsersInformations.java
 * ============================================================================
 * Date de création : 7 juin 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package core;

import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

/**
 * 
 * TODO
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 *
 */
public class UsersInformations extends CoreComponent implements Observer {
   
   private LinkedList<UserInfo> users;
   
   public UsersInformations() {
      users = new LinkedList<UserInfo>();
   }
   
   public int size() {
      return users.size();
   }
   
   public synchronized UserInfo getPlayerAt(int slotNumber) {
      return users.get(slotNumber);
   }

   @Override
   public void update(Observable arg0, Object arg1) {
      setChangedAndNotifyObservers(arg1);
   }
   
}

