/* ============================================================================
 * Nom du fichier   : Launcher.java
 * ============================================================================
 * Date de création : 8 mai 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package gui.controller;

import gui.actions.AcPlay;
import gui.actions.AcViewInventory;
import gui.utils.Positions;
import gui.view.JMainFrame;

import java.awt.Component;

import javax.swing.JFrame;

import settings.Language.Text;
import settings.Settings;

/**
 * Contrôleur de la fenêtre principale.
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 *
 */
public class MainFrame extends Controller {
   
   private JMainFrame view;
   

   /**
    * @param login Identifiant de l'utilisateur.
    * @param password Mot de passe de l'utilisateur.
    */
   public MainFrame(String login, String password) {
   }

   @Override
   protected void initComponents() {
      view = new JMainFrame(Text.APP_TITLE.toString());
      
      view.setSize(Settings.mainFrame.width, Settings.mainFrame.height);
      Positions.setPositionOnScreen(view,  Settings.mainFrame.anchor);
      view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      view.setVisible(true);
   }

   @Override
   protected void initListeners() {
      view.addPlayListener(new AcPlay());
      view.addInventoryListener(new AcViewInventory());
   }

   @Override
   public Component getGraphicalComponent() {
      return view;
   }
}
