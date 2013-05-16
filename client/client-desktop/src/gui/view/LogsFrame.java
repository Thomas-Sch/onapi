/* ============================================================================
 * Nom du fichier   : LogsFrame.java
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
package gui.view;

import gui.component.JLogPanel;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

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
public class LogsFrame extends JFrame {
   
   private JTabbedPane tabbedPaneLogs;
   
   public LogsFrame(String title, int posX, int posY, int width, int height) {
      super(title);
      
      setBounds(posX, posY, width, height);
      
      tabbedPaneLogs = new JTabbedPane(JTabbedPane.LEFT);
      getContentPane().add(tabbedPaneLogs);
      
      setVisible(true);
   }
   
   public void addLogPanel(JLogPanel panel) {
      tabbedPaneLogs.addTab(panel.getName(), panel);
   }
   

}
