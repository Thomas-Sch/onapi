package gui.component;

import gui.testdata.PlayerData;

import javax.swing.JList;

public class JPlayerList extends JList<String> {

   /**
    * 
    */
   private static final long serialVersionUID = -3753161992210297207L;
   
   public JPlayerList() {
      setModel(new PlayerData());
   }
}
