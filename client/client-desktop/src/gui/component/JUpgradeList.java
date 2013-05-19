package gui.component;

import gui.testdata.UpgradeData;

import javax.swing.JList;

public class JUpgradeList extends JList<String> {
   
   /**
    * ID de sérialisation.
    */
   private static final long serialVersionUID = -5146153754042584078L;
   
   public JUpgradeList() {
      setModel(new UpgradeData());
   }
}
