package gui.view;

import gui.TESTConnectionController;
import gui.component.JAbilityComboBox;
import gui.component.JPlayerList;
import gui.component.JUpgradeComboBox;
import gui.component.JWeaponComboBox;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JPanel;

import client.ClientRequestProtocol;

import settings.Language.Text;

public class JLobby extends JDialog {

   /**
    * ID de sérialisation.
    */
   private static final long serialVersionUID = -6320584669316790021L;
   
   private JPlayerList pltPlayers;
   
   public JLobby () {
      initContent();
      setContentPane(buildContent());
      pack();   
   }
   
   public void initContent() {
      pltPlayers = new JPlayerList();
      pltPlayers.setPreferredSize(new Dimension(200, 200));
   }
   
   public JPanel buildContent() {
      JPanel pnlContent = new JPanel();
      pnlContent.setLayout(new BorderLayout(5,5));
      
      pnlContent.add(pltPlayers, BorderLayout.WEST);
      pnlContent.add(userActions(), BorderLayout.CENTER);
      
      
      
      return pnlContent;
   }
   
   public JPanel userActions() {
      JPanel pnlContent = new JPanel();
      pnlContent.setLayout(new BorderLayout(5,5));
      
      // Composants dans le panel top qui contient les préférences du joueur
      // pour son équipement.
      JPanel pnlTop = new JPanel();
      pnlTop.setLayout(new FlowLayout(FlowLayout.CENTER));
      pnlTop.add(new JWeaponComboBox());
      pnlTop.add(new JUpgradeComboBox());
      pnlTop.add(new JAbilityComboBox());
      
      pnlContent.add(pnlTop, BorderLayout.CENTER);
      
      // TODO Attention, modifications très moches effectuées ci-dessous pour test
      JCheckBox checkReady = new JCheckBox(Text.READY_LABEL.toString());
      checkReady.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent arg0) {
            ClientRequestProtocol protocol = new ClientRequestProtocol(
                  TESTConnectionController.connections.requestChannel);
            protocol.lobbySetReady(((JCheckBox)arg0.getSource()).isSelected());
         }
      });
      
      pnlContent.add(checkReady, BorderLayout.SOUTH);
      
      return pnlContent;
   }

}
