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
 * Représentation graphique d'un log.
 * 
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 * 
 */
public class LogPanel extends JPanel {

   private static final long serialVersionUID = 2581654682724148340L;

   private JTextArea textArea;

   private JScrollPane scrollPane;

   /**
    * Crée la représentation graphique d'un log, avec un nom indiqué.
    * 
    * @param name
    *           - le nom du log.
    */
   public LogPanel(String name) {
      setName(name);

      textArea = new JTextArea();
      textArea.setEditable(false);
      textArea.setFont(Font.getFont(Font.SANS_SERIF));
      textArea.setLineWrap(true);

      textArea.setFont(Font.getFont(Font.SANS_SERIF));

      scrollPane = new JScrollPane(textArea);

      setLayout(new BorderLayout());

      add(scrollPane, BorderLayout.CENTER);
      setVisible(true);

   }

   /**
    * Ajoute à la fin du log graphique le message donné.
    * 
    * @param message
    *           - le message à ajouter.
    */
   public void push(LogMessage message) {
      textArea.append(message.getLogShortDate() + "\n");

      // Pour positionner la vue sur la dernière ligne du log dès son écriture.
      textArea.setCaretPosition(textArea.getDocument().getLength());
   }

}
