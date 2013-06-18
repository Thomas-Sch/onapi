/* ============================================================================
 * Nom du fichier   : LogPanel.java
 * ============================================================================
 * Date de création : 16 mai 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package gui.logs;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Panneau servant à l'affichage d'un log.
 * 
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 * 
 */
public class LogPanel extends JPanel {

   /**
    * ID de sérialisation.
    */
   private static final long serialVersionUID = -5308495458453556350L;

   private JTextArea textArea;

   private JScrollPane scrollPane;

   public LogPanel(String name) {
      setName(name);

      textArea = new JTextArea();
      textArea.setEditable(false);
      textArea.setFont(Font.getFont(Font.SANS_SERIF));
      textArea.setLineWrap(true);

      scrollPane = new JScrollPane(textArea);

      setLayout(new BorderLayout());

      add(scrollPane, BorderLayout.CENTER);
      setVisible(true);

   }

   public void push(LogMessage message) {
      textArea.append(message.getLogShortDate() + "\n");

      // Pour positionner la vue sur la dernière ligne du log dès son écriture.
      textArea.setCaretPosition(textArea.getDocument().getLength());
   }

}
