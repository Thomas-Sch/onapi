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
 * Type des protocoles à utiliser pour spécifier le type de transmission à
 * effectuer.
 * 
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 * 
 */
public enum ProtocolType {
   REFUSE,

   PING, ACCOUNT_CREATE, LOGIN, LOGOUT,

   TEXT_MESSAGE,

   JOIN_GAME, LEAVE_GAME,

   // Game

   // Lobby
   LOBBY_SET_READY, LOBBY_UPDATED_SLOT_STATUS, LOBBY_GAME_READY,

   // Admin
   ADMIN_REGISTER, ADMIN_UPDATED_SLOT, ADMIN_KICK;

}
