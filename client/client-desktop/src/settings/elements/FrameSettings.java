/* ============================================================================
 * Nom du fichier   : FrameSettings.java
 * ============================================================================
 * Date de création : 17 avr. 2013
 * ============================================================================
 * Auteurs          : Biolzi Sébastien
 *                    Brito Carvalho Bruno
 *                    Decorvet Grégoire
 *                    Schweizer Thomas
 *                    Sinniger Marcel
 * ============================================================================
 */
package settings.elements;

import gui.utils.Positions.ScreenPosition;

/**
 * TODO
 * @author Biolzi Sébastien
 * @author Brito Carvalho Bruno
 * @author Decorvet Grégoire
 * @author Schweizer Thomas
 * @author Sinniger Marcel
 *
 */
public class FrameSettings {
   
   private String name;
   
   public int width, height;
   
   public int positionX, positionY;
   
   public ScreenPosition anchor;
   
   public FrameSettings(String name) {
      if (name == null || name.isEmpty()) {
        this.name = "unknown";
      }
      else {
         this.name = name;
      }
   }
   
   public String getName() {
      return name;
   }
   
}
