/* ============================================================================
 * Nom du fichier   : Login.java
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

import gui.actions.AcCancel;
import gui.actions.AcConnect;
import gui.utils.Positions;
import gui.view.JLogin;

import java.awt.Component;

import javax.swing.JFrame;

import core.ConnectionsManager;


import settings.Settings;
import settings.Language.Text;

/**
 * Contrôleur de la fenêtre de login.
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 */
public class Login extends Controller {
   
   JLogin view;
   
   public Login(ConnectionsManager connections) {
      super(connections);
   }

   @Override
   protected void initComponents(Object ... object) {
      view = new JLogin(Text.APP_TITLE.toString());
      Positions.setPositionOnScreen(view,  Settings.mainFrame.anchor);
      view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      view.setVisible(true);
   }

   @Override
   protected void initListeners() {
      view.addValidateListener(new AcConnect(connections, view));
      view.addCancelListener(new AcCancel(connections, view));
   }

   @Override
   public Component getGraphicalComponent() {
      return view;
   }

}
