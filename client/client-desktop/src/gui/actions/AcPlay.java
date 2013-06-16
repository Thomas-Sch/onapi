/* ============================================================================
 * Nom du fichier   : AcPlay.java
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

import gui.controller.Lobby;

import java.awt.Frame;
import java.awt.event.ActionEvent;

import utils.Logs;
import client.GameLauncher;
import core.ConnectionsManager;

/**
 * Contrôleur et action du jeu.
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 *
 */
public class AcPlay extends UserAction {
   
   public AcPlay(ConnectionsManager connections, Frame parent) {
      super(connections, parent);
   }

   @Override
   protected void execute(ConnectionsManager connections, ActionEvent event, Object[] dependencies) {
      Logs.messages.push("Recherche d'une partie initiée !");
      
      int lobbySize = protocolRequest.joinLobby();
      
      if (lobbySize < 0) {
         Logs.messages.push("Aucun lobby de disponible");
      }
      else {
         // Mise en place des infos de joueurs
         connections.setupPlayers(lobbySize);
         
         new GameLauncher(true); // TODO true = temporaire
         Lobby lobby = new Lobby(connections, (Frame)dependencies[0]);
         
//       JLobby view = new JLobby((Frame)dependencies[0]);
//       view.setTitle(Text.APP_TITLE.toString() + " - " + Text.LOBBY_TITLE.toString());
//       Positions.setPositionOnScreen(view, ScreenPosition.CENTER);
//       view.setVisible(true);
       
      }
      
      
   }

}
