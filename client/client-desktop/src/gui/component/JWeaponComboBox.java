package gui.component;

import gui.testdata.WeaponData;

import javax.swing.JComboBox;

public class JWeaponComboBox extends JComboBox<String> {

   /**
    * ID de sérialisation.
    */
   private static final long serialVersionUID = -5358081635200713124L;
   
   public JWeaponComboBox() {
      WeaponData data = new WeaponData();
      
      for(Object s : data.toArray()) {
         addItem((String) s);
      }
   }

}
