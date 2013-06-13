/* ============================================================================
 * Nom du fichier   : ServerStandardReceiveProtocol.java
 * ============================================================================
 * Date de création : 8 mai 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package core.protocol;

import gui.LogsFrame;
import common.components.AccountType;
import common.components.UserAccount;
import common.connections.protocol.ProtocolType;
import core.Core;
import core.UserInformations;
import core.updates.components.admin.Kicked;

/**
 * Classe permettant de rassembler les protocoles concernant les requêtes
 * entrantes.
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 *
 */
public class ServerStandardReceiveProtocol {
   
   protected final Core core;
   protected final UserInformations user;
   
   public ServerStandardReceiveProtocol(Core core, UserInformations user) {
      this.core = core;
      this.user = user;
   }
   
   public void ping() {
      // Ne rien faire, le fait d'accepter la requête suffit, car une réponse
      // est déjà donnée.
   }
   
   /**
    * Protocol de test bidon affichant le message reçu.
    */
   @Deprecated
   public void textMessage() {
      String message;
      
      message = user.connectionsToClient.receiveChannel.receiveString();
      
      user.log.push(message);
   }
   
   public void adminRegister() {
      user.connectionsToClient.receiveChannel.sendInt(core.getNumberOfSlots());
      core.adminRegister(user);
   }
   
   public void adminKick() {
      int slot = user.connectionsToClient.receiveChannel.receiveInt();
      String message = user.connectionsToClient.receiveChannel.receiveString();
      
      UserInformations user = core.adminKick(slot);
      
      if (user != null) {
         user.serverUpdate.pushUpdate(new Kicked(message));
      }
      else {
         // Print error
      }
      
   }
   
   public void acceptRequest(ProtocolType type) {
      user.connectionsToClient.receiveChannel.sendProtocolType(type);
   }
   
   public void refuseRequest(ProtocolType type) {
      user.connectionsToClient.receiveChannel.sendProtocolType(ProtocolType.REFUSE);
   }

}
