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

import gui.controller.MainFrame;
import gui.view.JLogin;

import java.awt.Color;
import java.awt.event.ActionEvent;

import utils.Logs;
import client.ClientRequestProtocol;

import common.components.UserAccount;
import common.connections.Channel;
import common.connections.exceptions.ChannelException;

/**
 * Contrôleur et action de connection.
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 *
 */
public class AcConnect extends UserAction {
   
   private static Channel channelRequest;
   private ClientRequestProtocol protocol;
   
   public AcConnect(Object ... dependencies) {
      super(dependencies);
   }

   @Override
   protected void execute(ActionEvent event, Object[] dependencies) {
      JLogin view = (JLogin)dependencies[0];
      Logs.messages.push("Requête de connexion au serveur.");
      Logs.messages.push("Login: " + view.getLogin());
      Logs.messages.push("Mdp: " + view.getPassword());
      Logs.messages.push("Ip serv: " + view.getServerAdress());
      Logs.messages.push("Port: " + view.getServerPort());
      
      try {
         channelRequest = new Channel(view.getServerAdress(), Integer.valueOf(view.getServerPort()), 1000);
         
         view.setMessage("Connection au serveur effectuée.", Color.GREEN);
         protocol = new ClientRequestProtocol(channelRequest);
         UserAccount account = protocol.login(view.getLogin(), view.getPassword());
         
         if(account != null) {
            System.out.println("Connexion au compte client : " + account);
            view.setMessage("Identification réussie.");
            view.dispose();
            new MainFrame(account, view.getServerAdress(),  view.getServerPort());
         } else {
            view.setMessage("Identifiant/Mot de passe inconnus", Color.RED);
         }
      } catch (ChannelException e) {
         view.setMessage(e.getMessage(), Color.RED);
      }
   }

}
