package gui.component;

import gui.testdata.UpgradeData;

import javax.swing.JComboBox;

public class JUpgradeComboBox extends JComboBox<String> {

   /**
    * ID de sérialisation.
    */
   private static final long serialVersionUID = 6351993650478544656L;
   
   public JUpgradeComboBox() {
      UpgradeData data = new UpgradeData();
      
      for(Object s : data.toArray()) {
         addItem((String) s);
      }
   }
}