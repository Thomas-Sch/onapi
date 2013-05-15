/* ============================================================================
 * Nom du fichier   : JLogPanel.java
 * ============================================================================
 * Date de création : 15 avr. 2013
 * ============================================================================
 * Auteurs          : Biolzi Sébastien
 *                    Brito Carvalho Bruno
 *                    Decorvet Grégoire
 *                    Schweizer Thomas
 *                    Sinniger Marcel
 * ============================================================================
 */
package gui.component;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import log.LogMessage;

/**
 * TODO
 * @author Biolzi Sébastien
 * @author Brito Carvalho Bruno
 * @author Decorvet Grégoire
 * @author Schweizer Thomas
 * @author Sinniger Marcel
 *
 */
@SuppressWarnings("serial")
public class JLogPanel extends JPanel {
   
   private JTextArea textArea;
   
   private JScrollPane scrollPane;
   
   public JLogPanel(String name) {
      setName(name);
      
      textArea = new JTextArea();
      textArea.setEditable(false);
      
      textArea.setFont(Font.getFont(Font.SANS_SERIF));
      
      scrollPane = new JScrollPane(textArea);
      
      setLayout(new BorderLayout());
      
      add(scrollPane, BorderLayout.CENTER);
      setVisible(true);
      
   }
   
   public void push(LogMessage message) {
      textArea.append(message.getLogShortDate() + "\n");
   }

}
