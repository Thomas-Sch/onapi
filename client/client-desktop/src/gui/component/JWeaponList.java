package gui.component;

import gui.testdata.WeaponData;

import javax.swing.JList;

public class JWeaponList extends JList<String> {

   /**
    * ID de sérialisation.
    */
   private static final long serialVersionUID = -5146155754042584078L;
   
   public JWeaponList() {
      setModel(new WeaponData());
   }
}
