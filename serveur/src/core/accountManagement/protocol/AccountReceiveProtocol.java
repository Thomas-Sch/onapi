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

import common.components.AccountType;
import common.components.UserAccount;
import core.Core;
import core.UserInformations;
import core.lobby.Lobby;
import core.lobby.LobbyConnection;
import core.lobby.exceptions.LobbyException;
import core.lobby.protocol.LobbyReceiveProtocol;
import core.protocol.ServerStandardReceiveProtocol;

/**
 * TODO
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 *
 */
public class AccountReceiveProtocol extends ServerStandardReceiveProtocol {
   
   public AccountReceiveProtocol(Core core, UserInformations user) {
      super(core, user);
   }
   
   public void login() {
      UserAccount account = null;
      boolean isValidAccount = false;
      
      String login = user.connectionsToClient.receiveChannel.receiveString();
      String password = user.connectionsToClient.receiveChannel.receiveString();
      
      user.log.push("Try login account : " + login);
      
      account = core.checkAuthentification(login, password);
      
      isValidAccount = account != null;
      
      user.connectionsToClient.receiveChannel.sendBoolean(isValidAccount);
      
      if (isValidAccount) {
         user.log.push("Now connected as " + login);
         user.account = account;
         user.connectionsToClient.receiveChannel.sendObject(user.account);
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
      UserAccount account = null;
      boolean accountCreated = false;
      
      String login = user.connectionsToClient.receiveChannel.receiveString();
      String password = user.connectionsToClient.receiveChannel.receiveString();
      
      user.log.push("Try creating account : " + login);
      
      account = core.createAccount(login, password, AccountType.USER);
      accountCreated = account != null;
      
      user.connectionsToClient.receiveChannel.sendBoolean(accountCreated);
      
      if (accountCreated) {
         user.log.push("Account created with success");
         user.account = account;
         user.connectionsToClient.receiveChannel.sendObject(user.account);
         updateLogName();
      }
      else {
         user.log.push("Fail to create the account");
      }
      
   }
   
   public void joinGame() {
      
      Lobby freeLobby = core.getFreeLoby();
      
      boolean isFreeLobby = freeLobby != null;
      
      user.connectionsToClient.receiveChannel.sendBoolean(isFreeLobby);
      
      if (isFreeLobby) {
//         user.channelReceive.sendInt(freeLobby.getUpdatePortNumber());
         
         try {
            user.log.push("Try to connect to Lobby...");
            user.lobby = freeLobby;
            LobbyConnection connection = new LobbyConnection(core, freeLobby, user);
            user.serverReceive = connection;
            
            int code = freeLobby.addPlayer(user);
            
            // Envoi d'un numéro de code
            user.connectionsToClient.receiveChannel.sendInt(code);
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
