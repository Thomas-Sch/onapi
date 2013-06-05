/* ============================================================================
 * Nom du fichier   : ClientReceiveProtocol.java
 * ============================================================================
 * Date de création : 19 mai 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package client;

import common.components.lobby.PlayerStatus;
import common.connections.Channel;
import common.connections.exceptions.ProtocolException;
import common.connections.protocol.ProtocolType;

/**
 * Classe permettant de rassembler les protocoles utilisés par le client pour
 * réagir aux mises à jours en provenance du serveur.
 * <p>Utilisé lors d'une partie pour la réception des informations de ladite
 * partie.
 * 
 * <p><b>TODO</b> : définir les objets à modifier. Définir s'ils sont modifiés
 * ici ou donnés en valeur de retour.
 * 
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 *
 */
public class ClientReceiveProtocol {

   private Channel channel;
   
   public ClientReceiveProtocol(Channel channel) {
      this.channel = channel;
   }
   
   /**
    * Protocol à utiliser en réponse à un ping du serveur.
    */
   public void ping() {
      synchronized (channel) {
         channel.sendProtocolType(ProtocolType.PING);
      }
   }
   
   /**
    * Protocole bidon pour test.
    */
   @Deprecated
   public String updateMessage() {
      String message;
      
      synchronized (channel) {
         message = channel.receiveString();
      }
      
      return message;
   }
   
   /**
    * TODO
    * @param toDefine - TODO
    */
   public void lobbyUpdateSlotStatus(int toDefine) {
      PlayerStatus status;
      
      synchronized (channel) {
         status = (PlayerStatus)channel.receiveObject();
      }
   }
   
   /**
    * TODO
    * @param toDefine - TODO
    */
   public void lobbyUpdateGameReady(int toDefine) {
      // TODO something ?
   }
   
}
