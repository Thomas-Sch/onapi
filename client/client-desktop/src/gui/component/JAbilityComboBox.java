package gui.component;

import gui.testdata.AbilityData;

import javax.swing.JComboBox;

public class JAbilityComboBox extends JComboBox<String> {

   /**
    * ID de sérialisation.
    */
   private static final long serialVersionUID = 1938571014340492847L;
   
   public JAbilityComboBox() {
      AbilityData data = new AbilityData();
      
      for(Object s : data.toArray()) {
         addItem((String) s);
      }
   }

}
