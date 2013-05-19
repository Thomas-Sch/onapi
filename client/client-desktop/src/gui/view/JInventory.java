package gui.view;

import gui.component.JAbilityList;
import gui.component.JUpgradeList;
import gui.component.JWeaponList;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class JInventory extends JFrame {

   /**
    * ID de sérialisation.
    */
   private static final long serialVersionUID = -5440589622821889297L;
   
   public JInventory() {
      initContent();
      setContentPane(buildContent());
      pack();
   }
   
   public void initContent() {
      
   }
   
   public JPanel buildContent() {
      JPanel pnlContent = new JPanel();
      pnlContent.setLayout(new GridLayout(0,3,10,10));
      
      pnlContent.add(new JWeaponList());
      pnlContent.add(new JUpgradeList());
      pnlContent.add(new JAbilityList());
      
      return pnlContent;
   }
   
   public Dimension getPreferredSize() {
      return new Dimension(300, 100);
   }
   

}
