/* ============================================================================
 * Nom du fichier   : ServerStandardUpdateProtocol.java
 * ============================================================================
 * Date de création : 8 mai 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package core.protocol.updates;

import common.components.lobby.PlayerStatus;
import common.connections.exceptions.ProtocolException;
import common.connections.protocol.ProtocolType;
import core.Core;
import core.UserInformations;

/**
 * Classe permettant de rassembler les protocoles concernant les mises à jour.
 * 
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 *
 */
public class ServerUpdateProtocol {
   
   protected final Core core;
   protected final UserInformations user;
   
   public ServerUpdateProtocol(Core core, UserInformations user) {
      this.core = core;
      this.user = user;
   }
   
   /**
    * Test la latence entre le serveur et le client.
    * @return La latence en millisecondes.
    */
   public long ping() {
      long time = System.currentTimeMillis();
      
      synchronized(user.connectionsToClient.updateChannel) {
         user.connectionsToClient.updateChannel.sendProtocolType(ProtocolType.PING);
      
         if (user.connectionsToClient.updateChannel.receiveProtocolType() == ProtocolType.PING) {
            return System.currentTimeMillis() - time;
         }
         else {
            throw new ProtocolException("Wrong protocol for PING");
         }
      }
      
   }
   
   /**
    * Protocol de test bidon affichant le message reçu.
    */
   @Deprecated
   public void textMessage(String message) {
      synchronized(user.connectionsToClient.updateChannel) {
         user.connectionsToClient.updateChannel.sendProtocolType(ProtocolType.TEXT_MESSAGE);
         user.connectionsToClient.updateChannel.sendString(message);
      }
   }
   
   public void lobbyUpdateSlot(int slotNumber, PlayerStatus status) {
      // Partie debug TODO
      
      user.log.push("DEBUG - update slot status : name :" + status.getName() +
            ", team : " + status.getTeamNumber() + ", ready ? " + status.isReady());
      
      synchronized(user.connectionsToClient.updateChannel) {
         user.connectionsToClient.updateChannel.sendProtocolType(ProtocolType.LOBBY_UPDATED_SLOT_STATUS);
         user.connectionsToClient.updateChannel.sendObject(status);
      }
   }
   
   public void lobbyGameIsReady() {
      synchronized(user.connectionsToClient.updateChannel) {
         user.connectionsToClient.updateChannel.sendProtocolType(ProtocolType.LOBBY_GAME_READY);
      }
      // TODO more ?
   }

}
