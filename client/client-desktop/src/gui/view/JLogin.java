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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import settings.Language.Text;

/**
 * Fenêtre de login.
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 *
 */
public class JLogin extends JFrame{

   /**
    * ID sérialisation du composant graphique.
    */
   private static final long serialVersionUID = -9093556072363479532L;
   
   private JValidateCancel vclActions;
   private JLabelTextPanel ltpLogin;
   private JLabelTextPanel ltpServerAdress;
   private JLabelTextPanel ltpServerPort;
   private JLabelPasswordPanel lppPassword;
   private JLabel lblConnectionInfo;
   
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
      lblConnectionInfo = new JLabel("");
      lblConnectionInfo.setForeground(Color.RED);
      
      // TODO pour tests :
      ltpLogin.setText("GregoireDec");
      lppPassword.setText("1234");
      ltpServerAdress.setText("127.0.0.1");
      ltpServerPort.setText("1234");
   }
   
   public JPanel buildContent() {
      JPanel pnlMargin = new JPanel();
      JPanel pnlContent = new JPanel();
      pnlMargin.setLayout(new FlowLayout(FlowLayout.CENTER, 10,10));
      pnlContent.setLayout(new BoxLayout(pnlContent, BoxLayout.PAGE_AXIS));
      
      pnlContent.add(ltpLogin);
      pnlContent.add(lppPassword);
      pnlContent.add(ltpServerAdress);
      pnlContent.add(ltpServerPort);
      pnlContent.add(lblConnectionInfo);
      pnlContent.add(vclActions);
      
      pnlMargin.add(pnlContent,BorderLayout.CENTER);
      return pnlMargin;
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
   
   public void setMessage(String message) {
      lblConnectionInfo.setText(message);
   }
   
   public void setMessage(String message, Color color) {
      lblConnectionInfo.setForeground(color);
      lblConnectionInfo.setText(message);
   }
}
