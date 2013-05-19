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

import common.connections.Channel;

/**
 * Classe permettant de rassembler les protocoles utilisés par le client pour
 * réagir aux mises à jours en provenance du serveur.
 * <p>Utilisé lors d'une partie pour la réception des informations de ladite
 * partie.
 * 
 * <p><b>TODO</b>
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
   
}
