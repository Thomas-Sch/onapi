/* ============================================================================
 * Nom du fichier   : JUserList.java
 * ============================================================================
 * Date de création : 14 juin 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package gui.component;

import java.util.Enumeration;

import gui.models.AllUsersStatus;
import javax.swing.JList;
import core.UserInfo;

public class JUserList extends JList<UserInfo> {

   private static final long serialVersionUID = -3753161992210297207L;
   
   private AllUsersStatus model;
      
   public JUserList() {
      model = new AllUsersStatus();
      setModel(model);
   }
   
   public void update(UserInfo updatedUser) {
      
      boolean found = false;
      int index = -1;
      Enumeration<UserInfo> enumeration = model.elements();
      UserInfo userInfo;
      
      while (!found && enumeration.hasMoreElements()) {
         index++;
         userInfo = enumeration.nextElement();
         
         if (userInfo.getUserId() == updatedUser.getUserId()) {
            found = true;
         }
      }
      
      // Cas de mise à jour
      if (found) {
         if (updatedUser.isConnected()) {
            model.set(index, updatedUser);
         }
         else {
            model.remove(index);
         }
      }
      // Cas d'ajout
      else {
         model.addElement(updatedUser);
      }
      
   }
   
}
