/* ============================================================================
 * Nom du fichier   : UpdateVisitor.java
 * ============================================================================
 * Date de création : 31 mai 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package core.updates;

import core.updates.components.LobbyGameReady;
import core.updates.components.LobbyUpdateSlot;
import core.updates.components.StandardPing;
import core.updates.components.admin.Kicked;
import core.updates.components.admin.UpdatedServerUser;

/**
 * Interface du visiteur pour les mises à jour.
 * 
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 * 
 */
public interface UpdateVisitor {

   // Général
   void casePing(StandardPing update);

   // Salon d'attente
   void caseLobbyGameReady(LobbyGameReady update);

   void caseLobbyUpdateSlot(LobbyUpdateSlot update);

   // Administration
   void caseUpdateServerUser(UpdatedServerUser udpate);

   void caseKicked(Kicked update);

}
