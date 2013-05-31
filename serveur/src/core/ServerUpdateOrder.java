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
package core;

import java.util.LinkedList;

import core.protocol.ServerStandardUpdateProtocol;
import core.updates.Update;
import core.updates.StandardUpdateVisitor;

/**
 * TODO
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 *
 */
public class ServerUpdateOrder implements StandardUpdateVisitor {
   
   private UserInformations user;
   
   private ServerStandardUpdateProtocol protocol;
   
   private LinkedList<Update> waitingUpdates = new LinkedList<>();
   
   public ServerUpdateOrder(Core core, UserInformations user) {
      this.user = user;
      protocol = new ServerStandardUpdateProtocol(core, user);
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
   public void casePing(Update update) {
      long ping = protocol.ping();
      user.log.push("Ping : " + ping + " ms");
   }

}
