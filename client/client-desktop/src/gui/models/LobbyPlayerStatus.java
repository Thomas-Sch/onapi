package gui.models;

import javax.swing.DefaultListModel;

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
