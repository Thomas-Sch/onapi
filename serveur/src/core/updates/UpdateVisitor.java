/* ============================================================================
 * Nom du fichier   : Visitor.java
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


/**
 * TODO
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
   void caseKicked(Kicked update);

}
