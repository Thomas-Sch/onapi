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

import settings.Settings;
import sun.util.logging.resources.logging;
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
      int id = user.connectionsToClient.receiveChannel.receiveInt();
      String message = user.connectionsToClient.receiveChannel.receiveString();
      
      if (Settings.DEBUG_MODE_ON) {
         user.log.push("Trying to kick playser with " + id + ".");
      }
      
      UserInformations kickedUser = core.adminKick(id);
      
      if (kickedUser != null) {
         kickedUser.serverUpdate.pushUpdate(new Kicked(message));
         
         if (Settings.DEBUG_MODE_ON) {
            user.log.push("Matching player kicked.");
         }
      }
      else {
         if (Settings.DEBUG_MODE_ON) {
            user.log.push("Kick request fail, no matching player.");
         }
      }
      
   }
   
   public void acceptRequest(ProtocolType type) {
      user.connectionsToClient.receiveChannel.sendProtocolType(type);
      
      if (Settings.DEBUG_MODE_ON) {
         user.log.push("Accept protocol type : " + type);
      }
   }
   
   public void refuseRequest(ProtocolType type) {
      user.connectionsToClient.receiveChannel.sendProtocolType(ProtocolType.REFUSE);
      
      if (Settings.DEBUG_MODE_ON) {
         user.log.push("Refuse protocol type : " + type);
      }
   }

}
