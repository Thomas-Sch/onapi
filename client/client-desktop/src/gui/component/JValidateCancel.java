/* ============================================================================
 * Nom du fichier   : JValidateCancel.java
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

import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import settings.Language.Text;

/**
 * 
 * TODO
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 *
 */
public class JValidateCancel extends JPanel {

   /**
    * ID de sérialisation.
    */
   private static final long serialVersionUID = -2927910491428579256L;
   
   private JButton btnValidate = new JButton(Text.VALIDATE_BUTTON.toString());
   private JButton btnCancel = new JButton(Text.CANCEL_BUTTON.toString());
   
   private FlowLayout fltLayout = new FlowLayout();
   
   public JValidateCancel() {
      setLayout(fltLayout);
      add(btnValidate);
      add(btnCancel);
   }
   
   public void addValidateListener(ActionListener listener) {
      btnValidate.addActionListener(listener);
   }
   
   public void addCancelListener(ActionListener listener) {
      btnCancel.addActionListener(listener);
   }
}
