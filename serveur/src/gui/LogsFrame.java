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
 * Fenêtre gérant l'affichage de plusieurs panneaux de log.
 * 
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 * 
 */
public class LogsFrame extends JFrame {

   /**
    * ID de sérialisation.
    */
   private static final long serialVersionUID = 3686426401919375295L;
   
   private JTabbedPane tabbedPaneLogs;

   public LogsFrame(String title, int posX, int posY, int width, int height) {
      super(title);

      setBounds(posX, posY, width, height);

      tabbedPaneLogs = new JTabbedPane(JTabbedPane.LEFT);
      getContentPane().add(tabbedPaneLogs);
   }

   public void addLogPanel(LogPanel panel) {
      try {
         tabbedPaneLogs.addTab(panel.getName(), panel);
      }
      catch (IndexOutOfBoundsException e) {
         System.out.println("DEBUG - ou la");
      }
   }

   public void removeLogPanel(LogPanel panel) {
      try {
         tabbedPaneLogs.remove(panel);
      }
      catch (IndexOutOfBoundsException e) {
         System.out.println("DEBUG - ici");
      }
   }

   public void setLogPanelTitle(LogPanel panel, String title) {

      int index = tabbedPaneLogs.indexOfComponent(panel);

      if (index >= 0 && index < tabbedPaneLogs.getTabCount()) {
         try {
            tabbedPaneLogs.setTitleAt(index, title);
         }
         catch (IndexOutOfBoundsException e) {
            System.out.println("DEBUG - la");
         }
      }

   }
}
