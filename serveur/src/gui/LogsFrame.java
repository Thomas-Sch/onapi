/* ============================================================================
 * Nom du fichier   : LogsFrame.java
 * ============================================================================
 * Date de création : 16 mai 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package gui;

import gui.logs.LogPanel;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

/**
 * TODO
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
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
   }

   public void addLogPanel(LogPanel panel) {
      tabbedPaneLogs.addTab(panel.getName(), panel);
   }
   
   public void removeLogPanel(LogPanel panel) {
      tabbedPaneLogs.remove(panel);
   }
   
   public void setLogPanelTitle(LogPanel panel, String title) {
      
      int index = tabbedPaneLogs.indexOfComponent(panel);
      
      if (index >= 0 && index < tabbedPaneLogs.getTabCount()) {
         tabbedPaneLogs.setTitleAt(index, title);
      }
      
   }
}
