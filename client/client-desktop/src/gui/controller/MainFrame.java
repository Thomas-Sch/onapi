/* ============================================================================
 * Nom du fichier   : Launcher.java
 * ============================================================================
 * Date de cr�ation : 8 mai 2013
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

import common.components.UserAccount;

import settings.Language.Text;
import settings.Settings;

/**
 * Contr�leur de la fen�tre principale.
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 *
 */
public class MainFrame extends Controller {
   
   private JMainFrame view;
   private String serverAdress;
   private String serverPort;
   private UserAccount user;
   

   /**
    * @param login Identifiant de l'utilisateur.
    * @param password Mot de passe de l'utilisateur.
    */
   public MainFrame(UserAccount user, String serverAdress, String serverPort) {
      super(user, serverAdress, serverPort);
   }

   @Override
   protected void initComponents(Object ... objects) {
      
      // Pas besoin de tests on sait exactement ce que l'on met dedans.
      user = (UserAccount) objects[0];
      serverAdress = (String) objects[1];
      serverPort = (String) objects[2];
      
      
      view = new JMainFrame(Text.APP_TITLE.toString() + " - " + serverAdress + "/" + serverPort, this);
      
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
   
   /**
    * Renvoie le login de l'utilisateur.
    * @return le login de l'utilisateur.
    */
   public String getUserName() {
      return user.getLogin();
   }
}
