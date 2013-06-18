/* ============================================================================
 * Nom du fichier   : LogMessage.java
 * ============================================================================
 * Date de création : 11 mai 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package log;

import utils.Formatters;

/**
 * Message de log, comprenant sa date de création et éventuellement le nom de sa
 * source
 * 
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 * 
 */
public class LogMessage {

   private long time;
   private String source;
   private String message;

   /**
    * Crée un message de log, sans source. La date est automatiquement établie.
    * 
    * @param message
    *           - le contenu du message de log.
    */
   public LogMessage(String message) {
      source = null;
      this.message = message;

      time = System.currentTimeMillis();
   }

   /**
    * Crée un message de log avec un nom de source donné. La date est
    * automatiquement établie.
    * 
    * @param source
    *           - le nom de la source.
    * @param message
    *           - le contenu du message de log.
    */
   public LogMessage(String source, String message) {
      this(message);

      this.source = source;
   }

   /**
    * Retourne la date de création du message.
    * 
    * @return La date du message.
    */
   public String getDate() {
      return Formatters.date(time, Formatters.DateFormat.YYYY_MM_DD);
   }

   /**
    * Retourne l'heure de création du message.
    * 
    * @return L'heure de création du message.
    */
   public String getDayTime() {
      return Formatters.time(time);
   }

   /**
    * Retourne le nom de la source du message.
    * 
    * @return Le nom de la source, null s'il n'y en n'a pas.
    */
   public String getSource() {
      return source;
   }

   /**
    * Retourne le contenu du message.
    * 
    * @return Le contenu du message.
    */
   public String getMessage() {
      return message;
   }

   /**
    * Retourne le message sous une forme restreinte. Indique uniquement l'heure
    * du message, le nom de la source ainsi que le contenu du message.
    * 
    * @return Le message sans la date.
    */
   public String getLogShortDate() {
      return getDayTime() + (source != null ? " - " + source : "") + " : "
            + message;
   }

   /**
    * Retourne le message sous sa forme complète. Indique la date et l'heure du
    * message, le nom de la source ainsi que le contenu du message.
    * 
    * @return Le message complet.
    */
   public String getLogFullDate() {
      return toString();
   }

   @Override
   public String toString() {
      return getFullDate() + (source != null ? " - " + source : "") + " : "
            + message;
   }

   /**
    * Retourne la date complète du message, à savoir la date et l'heure.
    * 
    * @return La date complète.
    */
   private String getFullDate() {
      return Formatters.date(time, Formatters.DateFormat.YYYY_MM_DD) + " "
            + Formatters.time(time);
   }

}
