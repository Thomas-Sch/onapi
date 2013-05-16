/* ============================================================================
 * Nom du fichier   : JLabelInfo.java
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

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Classe représentant une paire de deux label adjacent horizontalement.
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
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
