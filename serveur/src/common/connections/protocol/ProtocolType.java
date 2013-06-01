/* ============================================================================
 * Nom du fichier   : ProtocolType.java
 * ============================================================================
 * Date de création : 8 mai 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package common.connections.protocol;

/**
 * Type des protocoles.
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 *
 */
public enum ProtocolType {
   ACCEPT, REFUSE,
   ACCOUNT_CREATE,
   JOIN_GAME, LEAVE_GAME,
   LOGIN, LOGOUT,
   TEXT_MESSAGE,
   PING,
   LOBBY_GAME_READY;
}
