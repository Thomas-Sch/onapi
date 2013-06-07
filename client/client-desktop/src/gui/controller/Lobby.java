package gui.controller;

import gui.actions.AcCancel;
import gui.actions.AcConnect;
import gui.actions.AcLobbyNotReady;
import gui.actions.AcLobbyReady;
import gui.component.JPlayerList;
import gui.utils.Positions;
import gui.utils.Positions.ScreenPosition;
import gui.view.JLobby;
import gui.view.JLogin;

import java.awt.Component;
import java.awt.Dialog.ModalityType;
import java.awt.Frame;

import javax.swing.JFrame;

import core.ConnectionsManager;
import core.PlayersInformations;

import settings.Settings;
import settings.Language.Text;
import utils.Logs;

import client.ClientRequestProtocol;
import client.GameLauncher;

public class Lobby extends Controller {
   
   JLobby view;
   PlayersInformations model;
   
   /*
    * protocol = (ClientRequestProtocol) dependencies[0];
      
      boolean success = protocol.joinLobby();
      
      Logs.messages.push("Recherche d'une partie initiée !");
      JLobby view = new JLobby();
      view.setTitle(Text.APP_TITLE.toString() + " - " + Text.LOBBY_TITLE.toString());
      Positions.setPositionOnScreen(view, ScreenPosition.CENTER);
      view.setVisible(true);
      
      new GameLauncher(connections, true); // TODO true = temporaire
    * 
    * 
    */

   public Lobby(ConnectionsManager connections, Frame parent) {
      super(connections, parent);
   }

   @Override
   protected void initComponents(Object... objects) {
      model = connections.getPlayers();
      view = new JLobby(model, (Frame)objects[0]);
      view.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
      view.setTitle(Text.APP_TITLE.toString() + " - " + Text.LOBBY_TITLE.toString());
      
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
