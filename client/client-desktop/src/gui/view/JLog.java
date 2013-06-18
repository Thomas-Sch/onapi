/* ============================================================================
 * Nom du fichier   : JLog.java
 * ============================================================================
 * Date de création : 15 avr. 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package gui.view;

import gui.component.JLogPanel;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

/**
 * Fenêtre contenant plusieurs logs graphiques sous forme d'un log par onglet.
 * 
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 *
 */
public class JLog extends JFrame {

   private static final long serialVersionUID = -3342054795452954852L;

   private JTabbedPane tabbedPaneLogs;

   /**
    * Crée la fenêtre de logs.
    * 
    * @param title
    *           - le titre de la fenêtre.
    * @param posX
    *           - la position en X du coin supérieur gauche de la fenêtre.
    * @param posY
    *           - la position en Y du coin supérieur gauche de la fenêtre.
    * @param width
    *           - la largeur de la fenêtre.
    * @param height
    *           - la hauteur de la fenêtre.
    */
   public JLog(String title, int posX, int posY, int width, int height) {
      super(title);

      setBounds(posX, posY, width, height);

      tabbedPaneLogs = new JTabbedPane(JTabbedPane.LEFT);
      getContentPane().add(tabbedPaneLogs);

      setVisible(true);
   }

   /**
    * Ajoute un log graphique à la fenêtre en créant un nouvel onglet.
    * 
    * @param panel
    *           - le log graphique à ajouter.
    */
   public void addLogPanel(JLogPanel panel) {
      tabbedPaneLogs.addTab(panel.getName(), panel);
   }

}
