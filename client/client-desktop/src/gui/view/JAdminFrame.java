/* ============================================================================
 * Nom du fichier   : JAdminFrame.java
 * ============================================================================
 * Date de création : 14 juin 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package gui.view;

import gui.actions.UserAction;
import gui.component.JPlayerList;
import gui.component.JUserList;
import gui.utils.SelectedPlayer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import core.PlayerInfo;
import core.PlayersInformations;
import core.UserInfo;
import core.UsersInformations;

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
    * ID de sérialisation.a
    */
   private static final long serialVersionUID = -1202660840303381066L;   
   
   @SuppressWarnings("unused") // Variable en prévision de la suite
   private UsersInformations modelAllUsers;
   private PlayersInformations modelServer;
   
   private JUserList pltUsersAll;
   private JPlayerList pltPlayersServer;
   
   private SelectedPlayer selectedPlayer;
   
   private JTabbedPane tpePlayerLists;
   
   private AdminActions adminActions;
   
   public JAdminFrame(UsersInformations modelAllUsers, PlayersInformations modelServer, Frame parent) {
      super(parent);
      this.modelAllUsers = modelAllUsers;
      this.modelServer = modelServer;
      initContent();
      initListeners();
      setContentPane(buildContent());
      pack();
   }
   
   private void initListeners() {
      pltUsersAll.addListSelectionListener(new ListSelectionListener() {
         @Override
         public void valueChanged(ListSelectionEvent e) {
            selectedPlayer.setPlayerServerId(pltUsersAll.getSelectedValue().getUserId());
         }
      });
      
      pltPlayersServer.addListSelectionListener(new ListSelectionListener() {
         @Override
         public void valueChanged(ListSelectionEvent e) {
            selectedPlayer.setPlayerServerId(pltPlayersServer.getSelectedValue().getPlayerId());
         }
      });
      
      tpePlayerLists.addChangeListener(new ChangeListener() {
         @Override
         public void stateChanged(ChangeEvent arg0) {
            switch( tpePlayerLists.getSelectedIndex()) {
               case 0 :
                  UserInfo userInfo = pltUsersAll.getSelectedValue();
                  
                  if (userInfo == null) {
                     selectedPlayer.setPlayerServerId(-1);
                  }
                  else {
                     selectedPlayer.setPlayerServerId(userInfo.getUserId());
                  }
                  break;
                  
               case 1 :
                  PlayerInfo playerInfo = pltPlayersServer.getSelectedValue();
                  
                  if (playerInfo == null) {
                     selectedPlayer.setPlayerServerId(-1);
                  }
                  else {
                     selectedPlayer.setPlayerServerId(playerInfo.getPlayerId());
                  }
                  break;
            }
         }
      });
   }
   
   private void initContent() {
      pltUsersAll = new JUserList();
      pltUsersAll.setPreferredSize(new Dimension(200, 200));
      
      pltPlayersServer = new JPlayerList(modelServer.size());
      pltPlayersServer.setPreferredSize(new Dimension(200, 200));

      // Initialiser la valeur actuelle
      selectedPlayer = new SelectedPlayer();
      selectedPlayer.setPlayerServerId(-1);
      
      adminActions = new AdminActions();
      tpePlayerLists = new JTabbedPane();
      tpePlayerLists.insertTab("All", null, new JScrollPane(pltUsersAll), null, tpePlayerLists.getTabCount());
      tpePlayerLists.insertTab("Server", null, new JScrollPane(pltPlayersServer), null, tpePlayerLists.getTabCount());
   }
   
   private JPanel buildContent() {
      JPanel pnlContent = new JPanel();
      pnlContent.setLayout(new BorderLayout(5,5));
      
      pnlContent.add(tpePlayerLists, BorderLayout.WEST);
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
   
   public SelectedPlayer getSelectedPlayer() {
      return selectedPlayer;
   }
   
   public void addAction(UserAction action, String name) {
      adminActions.addAction(action, name);
   }

   @Override
   public void update(Observable o, Object arg) {
      
      if (o instanceof PlayersInformations) {
         System.out.println("DEBUG - update player list");
         
         if (arg != null) {
            PlayerInfo updatedPlayer = (PlayerInfo)arg;
            
            pltPlayersServer.update(updatedPlayer);
         }
      }
      else if (o instanceof UsersInformations) {
         System.out.println("DEBUG - update user list");
         
         if (arg != null) {
            UserInfo updatedUser = (UserInfo)arg;
            
            pltUsersAll.update(updatedUser);
         }
         else {
            System.out.println("DEBUG - oups arg null");
         }
      }
      
      
   }

}
