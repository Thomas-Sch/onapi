/* ============================================================================
 * Nom du fichier   : Positions.java
 * ============================================================================
 * Date de création : 11 mai 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package gui.utils;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 * 
 * TODO
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
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
   
   public static void setPositionOnScreen(JDialog frame, ScreenPosition position) {
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
