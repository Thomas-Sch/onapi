/* ============================================================================
 * Nom du fichier   : Positions.java
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
package gui.utils;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JFrame;

/**
 * TODO
 * @author Biolzi Sébastien
 * @author Brito Carvalho Bruno
 * @author Decorvet Grégoire
 * @author Schweizer Thomas
 * @author Sinniger Marcel
 *
 */
public class Positions {
   
   public enum ScreenPosition {CENTER, TOP_LEFT, TOP_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT};
   
   public static void setPositionOnScreen(JFrame frame, ScreenPosition position) {
      Dimension screenDimensions = Toolkit.getDefaultToolkit().getScreenSize();
      
      Point location = computeRelativePosition(screenDimensions.width,
            screenDimensions.height, frame.getWidth(), frame.getHeight(), position);
      
      frame.setLocation(location.x, location.y);
      
   }
   
   public static Point computeRelativePosition(
         int containerWidth, int containerHeight,
         int frameWidth, int frameHeight, ScreenPosition position) {
      
      Point result = new Point(0, 0);
      
      switch (position) {
         case CENTER :
            result.x = (containerWidth - frameWidth) / 2;
            result.y = (containerHeight - frameHeight) / 2;
            break;
            
         case BOTTOM_LEFT :
            result.x = 0;
            result.y = containerHeight - frameHeight;
            break;
            
         case BOTTOM_RIGHT :
            result.x = containerWidth - frameWidth;
            result.y = containerHeight - frameHeight;
            break;
         
         case TOP_RIGHT :
            result.x = containerWidth - frameWidth;
            result.y = 0;
            break;
            
         case TOP_LEFT :
         default :
            result.x = 0;
            result.y = 0;
      }
      
      return result;
      
   }
   
   

}
