/* ============================================================================
 * Nom du fichier   : AccountConnection.java
 * ============================================================================
 * Date de création : 19 mai 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package core.accountManagement;

import common.components.AccountType;
import common.connections.protocol.ProtocolType;

import core.Core;
import core.ServerRequestAnswers;
import core.UserInformations;
import core.protocol.account.AccountReceiveProtocol;

/**
 * Gère une connexion utilisateur au niveau de la racine du serveur,
 * c'est-à-dire les actions de bases que peut effectuer un client juste après
 * s'être connecté.
 * 
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 * 
 */
public class AccountConnection implements ServerRequestAnswers {

   private AccountReceiveProtocol receiveProtocol;

   private UserInformations user;

   /**
    * Crée le gestionnaire de connexion.
    * 
    * @param core
    *           - le coeur logique du serveur.
    * @param user
    *           - l'utilisateur à gérer.
    */
   public AccountConnection(Core core, UserInformations user) {
      this.user = user;
      receiveProtocol = new AccountReceiveProtocol(core, user);
   }

   @Override
   public void answerRequest(ProtocolType type) {

      switch (type) {

         case PING:
            receiveProtocol.acceptRequest(ProtocolType.PING);
            receiveProtocol.ping();
            break;

         case LOGIN:
            if (user.account == null) {
               receiveProtocol.acceptRequest(type);
               receiveProtocol.login();
            }
            else {
               receiveProtocol.refuseRequest(type);
            }
            break;

         case LOGOUT:
            if (user.account != null) {
               receiveProtocol.acceptRequest(type);
               receiveProtocol.logout();
            }
            else {
               receiveProtocol.refuseRequest(type);
            }
            break;

         case ACCOUNT_CREATE:
            if (user.account == null) {
               receiveProtocol.acceptRequest(type);
               receiveProtocol.createAccount();
            }
            else {
               receiveProtocol.refuseRequest(type);
            }
            break;

         case JOIN_GAME:
            if (user.account != null && user.gameServer == null) {
               receiveProtocol.acceptRequest(type);
               receiveProtocol.joinLobby();
            }
            else {
               receiveProtocol.refuseRequest(type);
            }
            break;

         case ADMIN_REGISTER:
            if (user.account.getType() == AccountType.ADMINISTRATOR) {
               receiveProtocol.acceptRequest(type);
               receiveProtocol.adminRegister();
            }
            else {
               receiveProtocol.refuseRequest(type);
            }
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
