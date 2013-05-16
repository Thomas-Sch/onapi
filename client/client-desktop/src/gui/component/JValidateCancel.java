/* ============================================================================
 * Nom du fichier   : JValidateCancel.java
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

import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import settings.Language.Text;

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
