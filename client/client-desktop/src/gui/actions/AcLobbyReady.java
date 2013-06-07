/* ============================================================================
 * Nom du fichier   : AcLobbyReady.java
 * ============================================================================
 * Date de création : 11 mai 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package gui.actions;

import java.awt.event.ActionEvent;

import core.ConnectionsManager;

import client.ClientRequestProtocol;

/**
 * Action indiquant que le joueur est prêt à commencer une partie.
 * 
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 *
 */
public class AcLobbyReady extends UserAction {
   
   ClientRequestProtocol protocol;
   
   public AcLobbyReady(ConnectionsManager connections) {
      super(connections);
      protocol = new ClientRequestProtocol(connections.getChannelRequest());
   }

   @Override
   protected void execute(ConnectionsManager connections, ActionEvent event,
         Object[] dependencies) {
      protocol.lobbySetReady(true);
   }
   
   

}
