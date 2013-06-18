/* ============================================================================
 * Nom du fichier   : ServerRequestAnswers.java
 * ============================================================================
 * Date de création : 19 mai 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package core;

import common.connections.protocol.ProtocolType;

/**
 * Interface définissant un gestionnaire de connexion pour les requêtes d'un
 * client.
 * 
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 * 
 */
public interface ServerRequestAnswers {

   public void answerRequest(ProtocolType type);

}
