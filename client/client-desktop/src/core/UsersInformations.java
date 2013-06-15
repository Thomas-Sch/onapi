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

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

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
   
   public synchronized void addOrUpdate(ConnectedUser user) {
      boolean found = false;
      
      Iterator<UserInfo> it = users.iterator();
      UserInfo current = null;
      
      while (!found && it.hasNext()) {
         current = it.next();
         
         // Mise à jour si correspondance
         if (current.getUserId() == user.getUserId()) {
            current.update(user);
            found = true;
         }
      }
      
      // Cas d'ajout
      if (!found) {
         current = new UserInfo(user);
         users.add(current);
         
      }
      
      setChangedAndNotifyObservers(current);
      
      
   }
   
   public synchronized boolean remove(ConnectedUser user) {
      boolean result = false;
      
      Iterator<UserInfo> it = users.iterator();
      UserInfo current;
      
      while (!result && it.hasNext()) {
         current = it.next();
         
         // Suppression si correspondance
         if (current.getUserId() == user.getUserId()) {
            it.remove();
            result = true;
         }
      }
      
      if (result) {
         setChangedAndNotifyObservers(this);
      }
      
      return result;
   }
   

   @Override
   public void update(Observable arg0, Object arg1) {
      setChangedAndNotifyObservers(arg1);
   }
   
}

