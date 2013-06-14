/* ============================================================================
 * Nom du fichier   : LobbyPlayerStatus.java
 * ============================================================================
 * Date de création : 1 juin 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package gui.models;

import javax.swing.DefaultListModel;

/**
 * 
 * TODO
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 *
 */
public class LobbyPlayerStatus extends DefaultListModel<String> {
   
   public static final String TEXT_FREE = " > libre < ";
   
   private static final long serialVersionUID = 1169360848263712382L;

   public LobbyPlayerStatus(int nbSlots)
   {
      for (int i = 0 ; i < nbSlots ; i++) {
         addElement(TEXT_FREE);
      }
   }
}
