/* ============================================================================
 * Nom du fichier   : FrameSettings.java
 * ============================================================================
 * Date de création : 11 mai 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package settings.elements;

import gui.utils.Positions.ScreenPosition;

/**
 * 
 * TODO
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
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
