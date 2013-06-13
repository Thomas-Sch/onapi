/* ============================================================================
 * Nom du fichier   : ServerUpdateOrder.java
 * ============================================================================
 * Date de création : 23 mai 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package core.updates;

import java.util.LinkedList;

import core.Core;
import core.UserInformations;
import core.protocol.updates.ServerUpdateProtocol;
import core.updates.components.LobbyGameReady;
import core.updates.components.LobbyUpdateSlot;
import core.updates.components.StandardPing;
import core.updates.components.admin.Kicked;

/**
 * TODO
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 *
 */
public class ServerUpdateOrder implements UpdateVisitor {
   
   private UserInformations user;
   
   private ServerUpdateProtocol protocol;
   
   private LinkedList<Update> waitingUpdates = new LinkedList<>();
   
   public ServerUpdateOrder(Core core, UserInformations user) {
      this.user = user;
      protocol = new ServerUpdateProtocol(core, user);
   }
   
   public void pushUpdate(Update update) {
      synchronized (waitingUpdates) {
         waitingUpdates.add(update);
      }
   }
   
   
   public boolean sendUpdate() {
      boolean updateSent = false;
      synchronized(waitingUpdates) {
         if(!waitingUpdates.isEmpty()) {
            Update update = waitingUpdates.remove();
            update.apply(this);
            updateSent = true;
         }
      }
      return updateSent;
   }


   @Override
   public void casePing(StandardPing update) {
      long ping = protocol.ping();
      user.log.push("Ping : " + ping + " ms");
   }

   @Override
   public void caseLobbyGameReady(LobbyGameReady update) {
      protocol.lobbyGameIsReady();
      user.log.push("Update lobby : signal game is ready to start");
   }

   @Override
   public void caseLobbyUpdateSlot(LobbyUpdateSlot update) {
      protocol.lobbyUpdateSlot(update.slotNumber, update.status);
      user.log.push("Update lobby : a player slot has changed");
   }

   @Override
   public void caseKicked(Kicked update) {
      protocol.adminKick(update.message);
      user.log.push("Kicked by an admin.");
   }
   
}
