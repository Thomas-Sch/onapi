package gui.testdata;

import javax.swing.DefaultListModel;

public class WeaponData extends DefaultListModel<String> {
   /**
    * ID de sérialisation.
    */
   private static final long serialVersionUID = 8713112657651127669L;

   public WeaponData()
   {
      addElement("Paquerette");
      addElement("Vieux jambon");
   }
}