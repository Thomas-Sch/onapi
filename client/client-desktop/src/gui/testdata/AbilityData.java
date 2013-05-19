package gui.testdata;

import javax.swing.DefaultListModel;

public class AbilityData extends DefaultListModel<String> {
   /**
    * ID de sérialisation.
    */
   private static final long serialVersionUID = 8240919794293667965L;

   public AbilityData()
   {
      addElement("Acid trap");
      addElement("Disco ball");
   }
}