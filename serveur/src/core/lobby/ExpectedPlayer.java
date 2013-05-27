/* ============================================================================
 * Nom du fichier   : ExpectedPlayer.java
 * ============================================================================
 * Date de création : 22 mai 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package core.lobby;

import core.UserInformations;

/**
 * TODO
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 *
 */
class ExpectedPlayer {
   
   UserInformations user;
   
   int code;
   
   ExpectedPlayer(UserInformations user, int code) {
      this.user = user;
      this.code = code;
   }

}
