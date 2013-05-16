/* ============================================================================
 * Nom du fichier   : JLabelInfo.java
 * ============================================================================
 * Date de création : 8 mai 2013
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

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Classe représentant une pair de deux label adjacent horizontalement.
 * @author Biolzi Sébastien
 * @author Brito Carvalho Bruno
 * @author Decorvet Grégoire
 * @author Schweizer Thomas
 * @author Sinniger Marcel
 *
 */
public class JLabelInfo extends JPanel {

   /**
    * ID de sérialisation.
    */
   private static final long serialVersionUID = -4049279064044227122L;
   
   private JLabel lblMetaInfo;
   private JLabel lblData;
   
   public JLabelInfo (String metaInfo, String data) {
      setLayout(new BorderLayout(5,5));
      
      lblMetaInfo = new JLabel(metaInfo);
      lblData = new JLabel(data);
      
      add(lblMetaInfo, BorderLayout.WEST);
      add(lblData, BorderLayout.EAST);
   }
   
   /**
    * @param string
    */
   public JLabelInfo(String metaInfo) {
      this(metaInfo, "");
   }

   protected void setDataAlignement(int alignement) {
      lblData.setHorizontalAlignment(alignement);
   }

}
