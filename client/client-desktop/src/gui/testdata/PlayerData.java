package gui.testdata;

import javax.swing.DefaultListModel;

public class PlayerData extends DefaultListModel<String> {
   /**
    * ID de sérialisation
    */
   private static final long serialVersionUID = -4549721227683209539L;

   public PlayerData()
   {
      addElement("Joueur 1");
      addElement("QSIIX");
   }
}