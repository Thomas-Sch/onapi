package gui.component;

import gui.testdata.AbilityData;

import javax.swing.JList;

public class JAbilityList extends JList<String> {

   /**
    * ID de sérialisation.
    */
   private static final long serialVersionUID = -7716210980341006753L;
   
   public JAbilityList() {
      setModel(new AbilityData());
   }
}
