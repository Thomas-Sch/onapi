/* ============================================================================
 * Nom du fichier   : Protocol.java
 * ============================================================================
 * Date de création : 8 mai 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package utils.connections.protocol;

import utils.connections.Channel;
import utils.connections.exceptions.ProtocolException;
import utils.connections.exceptions.TimeOutException;

/**
 * Classe permettant de rassembler les protocoles utilisés par le client pour
 * communiquer avec le serveur.
 * 
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 *
 */
public class ClientProtocol {
   
   private Channel channel;
   
   public ClientProtocol(Channel channel) {
      this.channel = channel;
   }
   
   /**
    * Envoi un ping au serveur pour déterminer le temps de latence.
    * @return la durée en millisecondes nécessaire pour faire l'aller-retour.
    */
   public long ping() {
      ProtocolType proType;
      
      long time = System.currentTimeMillis();
      
      channel.sendProtocolType(ProtocolType.PING);
      
      proType = channel.receiveProtocolType();
      
      if (proType == ProtocolType.PING) {
         return System.currentTimeMillis() - time;
      }
      else {
         throw new ProtocolException("Wrong protocol for PING");
      }
   }
   
   /**
    * Envoie un message bidon au serveur pour conserver la connexion.
    * @return vrai si la connexion en encore active, faux sinon.
    */
   public boolean keepAlive() {
      boolean stillAlive = false;
      
      try {
         ping();
         
         stillAlive = true;
      }
      catch (TimeOutException e) {
         
      }
      
      return stillAlive;
   }
   
   public void sendMessage(String message) {
      
      channel.sendProtocolType(ProtocolType.TEXT_MESSAGE);
      channel.sendString(message);
      
   }

}
