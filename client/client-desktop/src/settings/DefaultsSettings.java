/* ============================================================================
 * Nom du fichier   : DefaultsSettings.java
 * ============================================================================
 * Date de création : 11 mai 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package settings;

import gui.utils.Positions.ScreenPosition;
import settings.elements.FrameSettings;

/**
 * 
 * TODO
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
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
