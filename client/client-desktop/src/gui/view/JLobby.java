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
import java.util.Observable;
import java.util.Observer;

import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JPanel;

import settings.Language.Text;
import core.PlayerInfo;
import core.PlayersInformations;

/**
 * 
 * Fenêtre de salle d'attente d'une partie pour les joueurs.
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 *
 */
public class JLobby extends JDialog implements Observer {

   /**
    * ID de sérialisation.
    */
   private static final long serialVersionUID = -6320584669316790021L;
   
   private PlayersInformations model;
   
   private JPlayerList pltPlayers;
   
   private UserActions userActions;
   
   public JLobby(PlayersInformations model, Frame parent) {
      super(parent);
      this.model = model;
      initContent();
      setContentPane(buildContent());
      pack();
   }
   
   public void initContent() {
      pltPlayers = new JPlayerList(model.size());
      pltPlayers.setPreferredSize(new Dimension(200, 200));
      
      userActions = new UserActions();
   }
   
   public JPanel buildContent() {
      JPanel pnlContent = new JPanel();
      pnlContent.setLayout(new BorderLayout(5,5));
      
      pnlContent.add(pltPlayers, BorderLayout.WEST);
      pnlContent.add(userActions, BorderLayout.CENTER);
      
      return pnlContent;
   }
   
   private class UserActions extends JPanel {
      private static final long serialVersionUID = -6307605020243876769L;
      
      //private JPanel pnlContent;
      private JCheckBox checkReady;
      
      private UserAction actionReady;
      private UserAction actionNotReady;
      
      public UserActions() {
         
         //pnlContent = new JPanel();
         
         setLayout(new BorderLayout(5,5));
         
         // Composants dans le panel top qui contient les préférences du joueur
         // pour son équipement.
         JPanel pnlTop = new JPanel();
         pnlTop.setLayout(new FlowLayout(FlowLayout.CENTER));
         pnlTop.add(new JWeaponComboBox());
         pnlTop.add(new JUpgradeComboBox());
         pnlTop.add(new JAbilityComboBox());
         
         add(pnlTop, BorderLayout.CENTER);
         
         checkReady = new JCheckBox(Text.READY_LABEL.toString());
         checkReady.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               if (checkReady.isSelected() && actionReady != null) {
                  actionReady.actionPerformed(e);
               }
               else if (actionNotReady != null) {
                  actionNotReady.actionPerformed(e);
               }
            }
         });
         
         add(checkReady, BorderLayout.SOUTH);
      }
      
      public void setActionReady(UserAction action) {
         actionReady = action;
      }
      
      public void setActionNotReady(UserAction action) {
         actionNotReady = action;
      }
      
      
   }
   
   public void setActionIsReady(UserAction action) {
      userActions.setActionReady(action);
   }
   
   public void setActionIsNotReady(UserAction action) {
      userActions.setActionNotReady(action);
   }

   @Override
   public void update(Observable o, Object arg) {
      if (arg != null) {
         PlayerInfo updatedPlayer = (PlayerInfo)arg;
         
         pltPlayers.update(updatedPlayer);
      }
   }

}
