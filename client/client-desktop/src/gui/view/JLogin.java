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
import gui.utils.LoginInfo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
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
   private JLabel lblMessage;
   
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
      lblMessage = new JLabel("");
      lblMessage.setForeground(Color.RED);
      
      ltpLogin.setText("GregoireDec");
      lppPassword.setText("1234");
      ltpServerAdress.setText("127.0.0.1");
      ltpServerPort.setText("1234");
   }
   
   public JPanel buildContent() {
      JPanel pnlMargin = new JPanel();
      JPanel pnlContent = new JPanel();
      JPanel pnlLogin = new JPanel();
      JPanel pnlFooter = new JPanel();
      
      pnlMargin.setLayout(new FlowLayout(FlowLayout.CENTER, 10,10));
      pnlContent.setLayout(new BorderLayout(5,5));
      pnlLogin.setLayout(new BoxLayout(pnlLogin, BoxLayout.PAGE_AXIS));
      pnlFooter.setLayout(new GridLayout(2, 1));
      
      pnlMargin.add(pnlContent,BorderLayout.CENTER);
      pnlContent.add(pnlLogin,BorderLayout.CENTER);
      pnlContent.add(pnlFooter, BorderLayout.SOUTH);
      
      pnlLogin.add(ltpLogin);
      pnlLogin.add(lppPassword);
      pnlLogin.add(ltpServerAdress);
      pnlLogin.add(ltpServerPort);
      
      pnlFooter.add(lblMessage);
      pnlFooter.add(vclActions);
      
      return pnlMargin;
   }
   
   public void addValidateListener(ActionListener listener) {
      vclActions.addValidateListener(listener);
   }
   
   public void addCancelListener(ActionListener listener) {
      vclActions.addCancelListener(listener);
   }
   
   private String getLogin() {
      return ltpLogin.getText();
   }
   
   private String getPassword() {
      return lppPassword.getText();
   }
   
   private String getServerAdress() {
      return ltpServerAdress.getText();
   }
   
   private String getServerPort() {
      return ltpServerPort.getText();
   }
   
   public void setMessage(String message) {
      lblMessage.setText(message);
   }
   
   public void setMessage(String message, Color color) {
      lblMessage.setForeground(color);
      lblMessage.setText(message);
   }
   
   public LoginInfo getLoginInfo() {
      return new LoginInfo(getServerAdress(), getServerPort(), getLogin(), getPassword());
   }
}
