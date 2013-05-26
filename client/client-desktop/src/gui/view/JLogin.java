/* ============================================================================
 * Nom du fichier   : JLogin.java
 * ============================================================================
 * Date de création : 1 mai 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package gui.view;

import gui.component.JLabelPasswordPanel;
import gui.component.JLabelTextPanel;
import gui.component.JValidateCancel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import settings.Language.Text;

/**
 * TODO
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 *
 */
public class JLogin extends JFrame{

   /**
    * ID de s�rialisation du composant graphique.
    */
   private static final long serialVersionUID = -9093556072363479532L;
   
   private JValidateCancel vclActions;
   private JLabelTextPanel ltpLogin;
   private JLabelTextPanel ltpServerAdress;
   private JLabelTextPanel ltpServerPort;
   private JLabelPasswordPanel lppPassword;
   
   public JLogin (String title) {
      super(title);
      
      initContent();
      
      setContentPane(buildContent());
      pack();
   }
   
   public void initContent() {
      vclActions = new JValidateCancel();
      ltpLogin = new JLabelTextPanel(Text.LOGIN_LABEL.toString());
      lppPassword = new JLabelPasswordPanel(Text.PASSWORD_LABEL.toString());
      ltpServerAdress = new JLabelTextPanel(Text.SERVER_ADRESS_LABEL.toString());
      ltpServerPort = new JLabelTextPanel(Text.SERVER_PORT_LABEL.toString());
   }
   
   public JPanel buildContent() {
      JPanel pnlContent = new JPanel();
      
      pnlContent.setLayout(new GridLayout(5, 0, 10, 10));
      
      pnlContent.add(ltpLogin);
      pnlContent.add(lppPassword);
      pnlContent.add(ltpServerAdress);
      pnlContent.add(ltpServerPort);
      pnlContent.add(vclActions);
      
      return pnlContent;
   }
   
   public void addValidateListener(ActionListener listener) {
      vclActions.addValidateListener(listener);
   }
   
   public void addCancelListener(ActionListener listener) {
      vclActions.addCancelListener(listener);
   }
   
   public String getLogin() {
      return ltpLogin.getText();
   }
   
   public String getPassword() {
      return lppPassword.getText();
   }
   
   public String getServerAdress() {
      return ltpServerAdress.getText();
   }
   
   public String getServerPort() {
      return ltpServerPort.getText();
   }
}
