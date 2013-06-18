/* ============================================================================
 * Nom du fichier   : Lobby.java
 * ============================================================================
 * Date de création : 3 juin 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package gui.controller;

import gui.actions.AcLobbyNotReady;
import gui.actions.AcLobbyReady;
import gui.utils.WindowCloseListener;
import gui.view.JLobby;

import java.awt.Component;
import java.awt.event.WindowEvent;
import java.awt.Frame;

import javax.swing.JFrame;

import core.ConnectionsManager;
import core.PlayersInformations;

import settings.Language.Text;

/**
 * Contrôleur permettant de démarrer la fenêtre du salon d'attente.
 * 
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 * 
 */
public class Lobby extends Controller {

   private JLobby view;
   private PlayersInformations model;

   public Lobby(ConnectionsManager connections, Frame parent) {
      super(connections, parent);
   }

   @Override
   protected void initComponents(Object... objects) {
      model = connections.getPlayers();
      view = new JLobby(model, (Frame) objects[0]);
      view.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
      view.addWindowListener(new WindowCloseListener() {
         @Override
         public void windowClosing(WindowEvent arg0) {
            protocolRequest.leaveGame();
            view.dispose();
         }
      });
      view.setTitle(Text.APP_TITLE.toString() + " - "
            + Text.LOBBY_TITLE.toString());

      // Ajout de l'observateur
      model.addObserver(view);

      view.setVisible(true);
   }

   @Override
   protected void initListeners() {
      view.setActionIsReady(new AcLobbyReady(connections));
      view.setActionIsNotReady(new AcLobbyNotReady(connections));
   }

   @Override
   public Component getGraphicalComponent() {
      return view;
   }

}
