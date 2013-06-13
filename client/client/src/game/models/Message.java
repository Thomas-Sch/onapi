/* ============================================================================
 * Nom du fichier   : Messages.java
 * ============================================================================
 * Date de création : 13 juin 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package game.models;

import java.util.Date;

/**
 * TODO
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 *
 */
public class Message {

   private String message;
   /**
    * 
    */
   public Message(String message) {
       this.message = message;
   }
   
   public String getMessageText(){
      return message;
   }
   

}
