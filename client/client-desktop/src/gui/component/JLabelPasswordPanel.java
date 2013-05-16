/* ============================================================================
 * Nom du fichier   : JLabelPasswordPanel.java
 * ============================================================================
 * Date de création : 11 mai 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package gui.component;

import gui.utils.TextChangedListener;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * 
 * TODO
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 *
 */
public class JLabelPasswordPanel extends JPanel {
   
   /**
    * ID de sérialisation.
    */
   private static final long serialVersionUID = -1297406355333906465L;
   
   private static final int defaultColumnsSize = 30;
   
   private BorderLayout layout;
   protected JLabel label;
   protected JTextField textField;
   
   public JLabelPasswordPanel(String textForLabel, int textFieldColumns) {
      
      layout = new BorderLayout(5,5);
      label = new JLabel(textForLabel);
      
      textField = new JPasswordField();
      textField.setColumns(textFieldColumns);
      
      setLayout(layout);
      
      add(label, BorderLayout.WEST);
      add(textField, BorderLayout.EAST);      
   }
   
   public JLabelPasswordPanel(String textForLabel) {
      this(textForLabel, defaultColumnsSize);
   }
   
   public void setText(String text) {
      textField.setText(text);
   }
   
   public String getText() {
      return textField.getText();
   }
   
   public void addTextChangedListener(TextChangedListener listener) {
      TextChangedListener.addListener(textField, listener);
   }
   
}