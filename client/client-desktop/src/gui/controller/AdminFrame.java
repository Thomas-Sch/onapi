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
import gui.view.JAdminFrame;

import java.awt.Component;
import java.awt.Frame;

import javax.swing.JFrame;

import settings.Language.Text;
import core.ConnectionsManager;
import core.PlayersInformations;

public class AdminFrame extends Controller {
   
   private JAdminFrame view;
   private PlayersInformations model;
   
   private Integer selectedPlayer;
   
   public AdminFrame(ConnectionsManager connections, Frame frame) {
      super(connections, frame);
   }

   @Override
   protected void initComponents(Object... objects) {
      model = connections.getPlayers();
      view = new JAdminFrame(model, (Frame)objects[0]);
      view.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
      view.setTitle(Text.APP_TITLE.toString() + " - " + Text.ADMIN_TITLE.toString());
      
      // Ajout de l'observateur
      model.addObserver(view);
      
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
