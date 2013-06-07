/* ============================================================================
 * Nom du fichier   : UserAction.java
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
import java.awt.event.ActionListener;

import core.ConnectionsManager;

import client.ClientRequestProtocol;

/**
 * Décrit une action réalisable par l'utilisateur à l'aide de l'interface
 * graphique.
 * 
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas 
 */
abstract public class UserAction implements ActionListener {

   private ConnectionsManager connections;
   
   protected ClientRequestProtocol protocolRequest;
   
   private Object[] dependencies = null;

   public UserAction(ConnectionsManager connections, Object... dependencies) {
      this.dependencies = dependencies;
      this.connections = connections;
      protocolRequest = new ClientRequestProtocol(connections.getChannelRequest());
   }

   /**
    * Réalise l'action lors du déclenchement de celle-ci par l'utilisateur.
    * 
    * @param e
    *           - l'événement survenu.
    */
   public void actionPerformed(ActionEvent e) {
      execute(connections, e, dependencies);
   }

   /**
    * Réalise les opérations à effectuer lors du déclenchement de l'action.
    * 
    * @param connections
    *           - les canaux de communications.
    * @param event
    *           - l'événément survenu.
    * @param dependencies
    *           - le tableau des dépendances éventuelles.
    */
   abstract protected void execute(ConnectionsManager connections, ActionEvent event, Object[] dependencies);

}
