/* ============================================================================
 * Nom du fichier   : LogMessage.java
 * ============================================================================
 * Date de création : 7 avr. 2013
 * ============================================================================
 * Auteurs          : Biolzi Sébastien
 *                    Brito Carvalho Bruno
 *                    Decorvet Grégoire
 *                    Schweizer Thomas
 *                    Sinniger Marcel
 * ============================================================================
 */
package log;

import utils.Formatters;

/**
 * TODO
 * @author Biolzi Sébastien
 * @author Brito Carvalho Bruno
 * @author Decorvet Grégoire
 * @author Schweizer Thomas
 * @author Sinniger Marcel
 *
 */
public class LogMessage {
   
   private long time;
   private String source;
   private String message;
   
   public LogMessage(String message) {
      source = null;
      this.message = message;
      
      time = System.currentTimeMillis();
   }
   
   public LogMessage(String source, String message) {
      this(message);
      
      this.source = source;
   }
   
   public String getDate() {
      return Formatters.date(time, Formatters.DateFormat.YYYY_MM_DD);
   }
   
   public String getDayTime() {
      return Formatters.time(time);
   }
   
   public String getSource() {
      return source;
   }
   
   public String getMessage() {
      return message;
   }
   
   public String getLogShortDate() {
      return getDayTime() + (source != null ? " - " + source : "")
            + " : " + message;
   }
   
   public String getLogFullDate() {
      return toString();
   }
   
   @Override
   public String toString() {
      return getFullDate() + (source != null ? " - " + source : "")
             + " : " + message; 
   }
   
   private String getFullDate() {
      return Formatters.date(time,
                             Formatters.DateFormat.YYYY_MM_DD) + " "
             + Formatters.time(time);
   }

}
