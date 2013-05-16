/* ============================================================================
 * Nom du fichier   : JLabelTextPanel.java
 * ============================================================================
 * Date de création : 7 mai 2013
 * ============================================================================
 * Auteurs          : Biolzi Sébastien
 *                    Brito Carvalho Bruno
 *                    Decorvet Grégoire
 *                    Schweizer Thomas
 *                    Sinniger Marcel
 * ============================================================================
 */
package gui.component;

import gui.utils.TextChangedListener;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class JLabelTextPanel extends JPanel {
   
   /**
    * ID de sérialisation.
    */
   private static final long serialVersionUID = -1297406355333906465L;
   
   private static final int defaultColumnsSize = 30;
   
   private BorderLayout layout;
   protected JLabel label;
   protected JTextField textField;
   
   public JLabelTextPanel(String textForLabel, int textFieldColumns) {
      
      layout = new BorderLayout(5,5);
      label = new JLabel(textForLabel);
      
      textField = new JTextField();
      textField.setColumns(textFieldColumns);
      
      setLayout(layout);
      
      add(label, BorderLayout.WEST);
      add(textField, BorderLayout.EAST);      
   }
   
   public JLabelTextPanel(String textForLabel) {
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