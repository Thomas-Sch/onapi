/* ============================================================================
 * Nom du fichier   : JMainFrame.java
 * ============================================================================
 * Date de création : 1 mai 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package gui.view;

import gui.component.JLabelInfo;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import settings.Language.Text;

/**
 * Fenêtre principale de l'application.
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 * 
 */
public class JMainFrame extends JFrame {

   /**
    * ID de s�rialisation du composant graphique.
    */
   private static final long serialVersionUID = -8770775554830532082L;
   
   private JLabelInfo lbiUserGreeting;
   private JButton btnPlay;
   private JButton btnInventory;
   
   public JMainFrame(String title) {
      super(title);
      
      initContent();
      setContentPane(buildContent());
      
      pack();
   }
   
   public void initContent() {
      lbiUserGreeting = new JLabelInfo("Bienvenue !");
      btnPlay = new JButton(Text.PLAY_BUTTON.toString());
      btnInventory = new JButton(Text.INVENTORY_BUTTON.toString());
   }
   
   public JPanel buildContent() {
      JPanel pnlContent = new JPanel();
      
      pnlContent.setLayout(new GridBagLayout());
      
      GridBagConstraints constraints = new GridBagConstraints();
      constraints.fill = GridBagConstraints.HORIZONTAL;
      constraints.insets = new Insets(5, 5, 5, 5);
      constraints.weightx = 0.5;
      constraints.weighty = 0.1;
      
      constraints.gridx = 0;
      constraints.gridy = 0;
      constraints.gridwidth = 2;
      pnlContent.add(lbiUserGreeting, constraints);
      
      constraints.gridy = 1;
      pnlContent.add(new JSeparator(), constraints);
      
      constraints.gridx = 0;
      constraints.gridy = 2;
      constraints.gridwidth = 1;
      constraints.fill = GridBagConstraints.BOTH;
      constraints.insets = new Insets(100, 100, 100, 100);
      constraints.weighty = 0.8;
      pnlContent.add(btnPlay, constraints);
      
      constraints.gridx = 1;
      pnlContent.add(btnInventory, constraints);
      
      return pnlContent; 
   }
   
   public void addPlayListener(ActionListener listener) {
      btnPlay.addActionListener(listener);
   }

   public void addInventoryListener(ActionListener listener) {
      btnInventory.addActionListener(listener);
   }
}
