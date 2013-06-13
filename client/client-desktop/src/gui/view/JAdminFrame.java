package gui.view;

import gui.actions.UserAction;
import gui.component.JAbilityComboBox;
import gui.component.JPlayerList;
import gui.component.JUpgradeComboBox;
import gui.component.JWeaponComboBox;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import core.PlayerInfo;
import core.PlayersInformations;

import settings.Language.Text;

/**
 * 
 * TODO
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 *
 */
public class JAdminFrame extends JDialog implements Observer {

   /**
    * ID de sérialisation.
    */
   private static final long serialVersionUID = -1202660840303381066L;   
   
   private PlayersInformations model;
   
   private JPlayerList pltPlayers;
   private Integer selectedPlayer;
   
   private AdminActions adminActions;
   
   public JAdminFrame(PlayersInformations model, Frame parent) {
      super(parent);
      this.model = model;
      initContent();
      setContentPane(buildContent());
      pack();
   }
   
   public void initContent() {
      pltPlayers = new JPlayerList(model.size());
      pltPlayers.setPreferredSize(new Dimension(200, 200));
      pltPlayers.addListSelectionListener(new ListSelectionListener() {
         @Override
         public void valueChanged(ListSelectionEvent e) {
            selectedPlayer = pltPlayers.getSelectedIndex();
            System.out.println("DEBUG - new selected slot : " + selectedPlayer);
         }
      });
      // Initialiser la valeur actuelle
      selectedPlayer = pltPlayers.getSelectedIndex();
      
      adminActions = new AdminActions();
   }
   
   public JPanel buildContent() {
      JPanel pnlContent = new JPanel();
      pnlContent.setLayout(new BorderLayout(5,5));
      
      pnlContent.add(pltPlayers, BorderLayout.WEST);
      pnlContent.add(adminActions, BorderLayout.CENTER);
      
      return pnlContent;
   }
   
   private class AdminActions extends JPanel {
      private static final long serialVersionUID = -6307605020243876770L;
      
      private JPanel pnlTop = new JPanel();
      
      public AdminActions() {
         
         //pnlContent = new JPanel();
         
         setLayout(new BorderLayout(5,5));
         
         // Composants dans le panel top qui contient les préférences du joueur
         // pour son équipement.
         
         pnlTop.setLayout(new FlowLayout(FlowLayout.CENTER));
         add(pnlTop, BorderLayout.CENTER);
      }
      
      public void addAction(UserAction action, String name) {
         JButton button = new JButton(name);
         
         button.addActionListener(action);
         
         pnlTop.add(button);
         
         // pnlTop.repaint();
      }
      
   }
   
   public Integer getSelectedPlayer() {
      return selectedPlayer;
   }
   
   public void addAction(UserAction action, String name) {
      adminActions.addAction(action, name);
   }

   @Override
   public void update(Observable o, Object arg) {
      if (arg != null) {
         PlayerInfo updatedPlayer = (PlayerInfo)arg;
         
         pltPlayers.update(updatedPlayer);
      }
   }

}
