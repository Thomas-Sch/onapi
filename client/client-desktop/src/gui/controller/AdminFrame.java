/* ============================================================================
 * Nom du fichier   : AdminFrame.java
 * ============================================================================
 * Date de création : 10 juin 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package gui.controller;

import gui.actions.admin.AcKick;
import gui.utils.SelectedPlayer;
import gui.view.JAdminFrame;

import java.awt.Component;
import java.awt.Frame;

import javax.swing.JFrame;

import settings.Language.Text;
import core.ConnectionsManager;
import core.PlayersInformations;
import core.UsersInformations;

/**
 * Contrôleur de la fenêtre des outils à disposition d'un administrateur.
 * 
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 * 
 */
public class AdminFrame extends Controller {

   private JAdminFrame view;
   private UsersInformations modelAllUsers;

   private PlayersInformations modelServer;

   private SelectedPlayer selectedPlayer;

   public AdminFrame(ConnectionsManager connections, Frame frame) {
      super(connections, frame);
   }

   @Override
   protected void initComponents(Object... objects) {
      modelAllUsers = connections.getAllUsers();
      modelServer = connections.getPlayers();

      view = new JAdminFrame(modelAllUsers, modelServer, (Frame) objects[0]);
      view.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
      view.setTitle(Text.APP_TITLE.toString() + " - "
            + Text.ADMIN_TITLE.toString());

      // Ajout de l'observateur
      modelAllUsers.addObserver(view);
      modelServer.addObserver(view);

      view.setVisible(true);
   }

   @Override
   protected void initListeners() {
      selectedPlayer = view.getSelectedPlayer();
      view.addAction(new AcKick(connections, selectedPlayer), "Ejecter");
   }

   @Override
   public Component getGraphicalComponent() {
      return view;
   }
}
