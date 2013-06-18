/* ============================================================================
 * Nom du fichier   : GameServerConnection.java
 * ============================================================================
 * Date de création : 19 mai 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package core.gameserver;

import common.components.AccountType;
import common.components.gameserver.PlayerStatus;
import common.connections.protocol.ProtocolType;

import core.Core;
import core.ServerRequestAnswers;
import core.UserInformations;
import core.protocol.gameserver.GameServerReceiveProtocol;

/**
 * Gère une connexion utilisateur au niveau du serveur de jeu, c'est-à-dire les
 * actions que peut effectuer un client après avoir rejoint un serveur de jeu.
 * 
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 * 
 */
public class GameServerConnection implements ServerRequestAnswers {

   private GameServerReceiveProtocol receiveProtocol;

   private UserInformations user;

   /**
    * Crée le gestionnaire de connexion.
    * 
    * @param core - le coeur logique du serveur.
    * @param gameServer - le serveur de jeu.
    * @param user - l'utilisateur à gérer.
    * @param status - le status du joueur sur le serveur.
    */
   public GameServerConnection(Core core, GameServer gameServer,
         UserInformations user, PlayerStatus status) {
      this.user = user;
      receiveProtocol = new GameServerReceiveProtocol(core, gameServer, user,
            status);
   }

   @Override
   public void answerRequest(ProtocolType type) {

      switch (type) {

         case PING:
            receiveProtocol.acceptRequest(type);
            receiveProtocol.ping();
            break;

         case LEAVE_GAME:
            receiveProtocol.acceptRequest(type);
            receiveProtocol.leave();
            break;

         case LOBBY_SET_READY:
            receiveProtocol.acceptRequest(type);
            receiveProtocol.lobbySetReady();
            break;

         case ADMIN_KICK:
            if (user.account.getType() == AccountType.ADMINISTRATOR) {
               receiveProtocol.acceptRequest(type);
               receiveProtocol.adminKick();
            }
            else {
               receiveProtocol.refuseRequest(type);
            }
            break;

         default:
            receiveProtocol.refuseRequest(type);
            user.log.push("Bad request protocol");
      }

   }

}
