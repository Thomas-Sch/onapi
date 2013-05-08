/* ============================================================================
 * Nom du fichier   : DefaultsSettings.java
 * ============================================================================
 * Date de création : 19 avr. 2013
 * ============================================================================
 * Auteurs          : Biolzi Sébastien
 *                    Brito Carvalho Bruno
 *                    Decorvet Grégoire
 *                    Schweizer Thomas
 *                    Sinniger Marcel
 * ============================================================================
 */
package settings;

import gui.utils.Positions.ScreenPosition;
import settings.elements.FrameSettings;

/**
 * TODO
 * @author Biolzi Sébastien
 * @author Brito Carvalho Bruno
 * @author Decorvet Grégoire
 * @author Schweizer Thomas
 * @author Sinniger Marcel
 *
 */
class DefaultsSettings {
   
public static String languageCode;
   
   public static FrameSettings mainFrame = new FrameSettings("mainFrame");
   
   
   { // Initialisation par défaut des composants
      setDefaults();
   }
   
   static void setDefaults() {
      
      // Default for languageCode
      languageCode = "fr";
      
      // Defaults for mainFrame
      mainFrame.anchor = ScreenPosition.CENTER;
      mainFrame.positionX = 0;
      mainFrame.positionY = 0;
      mainFrame.width = 200;
      mainFrame.height = 400;
      
   }

}
