/* ============================================================================
 * Nom du fichier   : JLogPanel.java
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

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import log.LogMessage;

/**
 * 
 * TODO
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
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
