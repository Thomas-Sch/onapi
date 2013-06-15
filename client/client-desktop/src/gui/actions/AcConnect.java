/* ============================================================================
 * Nom du fichier   : AcConnect.java
 * ============================================================================
 * Date de création : 11 mai 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package gui.actions;

import gui.controller.AdminFrame;
import gui.controller.MainFrame;
import gui.utils.LoginInfo;
import gui.view.JLogin;

import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionEvent;

import utils.Logs;
import client.ClientRequestProtocol;
import client.ClientRequestProtocol.ConnectionChannels;

import common.components.AccountType;
import common.components.UserAccount;
import common.connections.exceptions.ChannelException;

import core.ConnectionsManager;

/**
 * Contrôleur et action de connection.
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 *
 */
public class AcConnect extends UserAction {
   
   private ConnectionChannels connectionsChannels;
   
   public AcConnect(ConnectionsManager connections, Object ... dependencies) {
      super(connections, dependencies);
   }

   @Override
   protected void execute(ConnectionsManager connections, ActionEvent event, Object[] dependencies) {
      JLogin view = (JLogin)dependencies[0];
      LoginInfo infos = view.getLoginInfo();
      
      // Affichage des informations saisies par l'utilisateur.
      Logs.messages.push("Requête de connexion au serveur.");
      Logs.messages.push("Login: " + infos.getLogin());
      Logs.messages.push("Mdp: " + infos.getPassword());
      Logs.messages.push("Ip serv: " + infos.getServerAdress());
      Logs.messages.push("Port: " + infos.getServerPort());
      
      if(infos.isValid()) {
         try {
            protocolRequest = new ClientRequestProtocol();
            connectionsChannels = protocolRequest.connectToServer(
                  infos.getServerAdress(), Integer.valueOf(infos.getServerPort()),
                  15000);
            
            connections.setup(connectionsChannels);
            
            view.setMessage("Connection established", Color.GREEN);
            
            UserAccount account = protocolRequest.login(infos.getLogin(), infos.getPassword());
            
            if(account != null) {
               Logs.messages.push("Connecting to user account: " + account);
               view.setMessage("Successfull identification", Color.GREEN);
               view.dispose();
               MainFrame mainFrame = new MainFrame(connections, account, infos.getServerAdress(),  infos.getServerPort());
               
               // Ouverture de la console d'administration
               if(account.getType() == AccountType.ADMINISTRATOR) {
                  // S'enregistrer comme admin et initialisation de la liste de joueurs.
                  int nbSlots = protocolRequest.adminSelfRegister();
                  connections.setupPlayers(nbSlots);
                  
                  new AdminFrame(connections, (Frame)mainFrame.getGraphicalComponent());
               }
            } else {
               view.setMessage("Invalid Identifiers", Color.RED);
            }
         } catch (ChannelException e) {
            view.setMessage(e.getMessage(), Color.RED);
         }
      } else {
         view.setMessage("Wrong login informations", Color.RED);
      }
      
      

   }
}
