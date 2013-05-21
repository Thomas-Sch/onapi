/* ============================================================================
 * Nom du fichier   : AccountReceiveProtocol.java
 * ============================================================================
 * Date de création : 19 mai 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package core.accountManagement.protocol;

import common.components.UserAccount;
import core.Core;
import core.UserInformations;
import core.lobby.Lobby;
import core.lobby.LobbyConnection;
import core.lobby.exceptions.LobbyException;
import core.lobby.protocol.LobbyReceiveProtocol;
import core.protocol.ServerStandardProtocol;
import database.components.AccountType;

/**
 * TODO
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 *
 */
public class AccountReceiveProtocol extends ServerStandardProtocol {
   
   public AccountReceiveProtocol(Core core, UserInformations user) {
      super(core, user);
   }
   
   public void login() {
      boolean isValidAccount = false;
      
      String login = user.channelReceive.receiveString();
      String password = user.channelReceive.receiveString();
      
      user.log.push("Try login account : " + login);
      
      isValidAccount = core.checkAuthentification(login, password);
      
      user.channelReceive.sendBoolean(isValidAccount);
      
      if (isValidAccount) {
         user.log.push("Now connected as " + login);
         
         user.account = new UserAccount(AccountType.USER, login, password, "Unknown", "User");
         
         user.channelReceive.sendObject(user.account);
         
         updateLogName();
      }
      else {
         user.log.push("Bad account informations");
      }
   }
   
   public void logout() {
      // Rien à envoyer au client, seulement à mettre à jour côté serveur.
      user.account = null;
      
      updateLogName();
   }
   
   public void createAccount() {
      boolean accountCreated = false;
      
      String login = user.channelReceive.receiveString();
      String password = user.channelReceive.receiveString();
      
      user.log.push("Try creating account : " + login);
      
      
      accountCreated = core.createAccount(login, password);
      
      user.channelReceive.sendBoolean(accountCreated);
      
      if (accountCreated) {
         
         user.log.push("Account created with success");
         
         user.account = new UserAccount(AccountType.USER, login, password, "Unknown", "User");
         
         user.channelReceive.sendObject(user.account);
         
         updateLogName();
      }
      else {
         user.log.push("Fail to create the account");
      }
      
   }
   
   public void joinGame() {
      
      Lobby freeLobby = core.getFreeLoby();
      
      boolean isFreeLobby = freeLobby != null;
      
      user.channelReceive.sendBoolean(isFreeLobby);
      
      if (isFreeLobby) {
         user.channelReceive.sendInt(freeLobby.getUpdatePortNumber());
         
         try {
            freeLobby.addPlayer(user);
            
            user.lobby = freeLobby;
            user.server = new LobbyConnection(core, freeLobby, user);
            
            user.log.push("Lobby joined with success");
         }
         catch (LobbyException e) {
            user.log.push("Unable to join the lobby");
         }
      }
      
      
      
      
   }
   
   
   private void updateLogName() {
      if (user.account != null) {
         core.setLogPanelTitle(user.log.getLogPanel(),
                               user.account.getLogin());
      }
      else {
         core.setLogPanelTitle(user.log.getLogPanel(),
                              "Unknown");
      }
   }
}
