/* ============================================================================
 * Nom du fichier   : ServerProtocol.java
 * ============================================================================
 * Date de création : 8 mai 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package core.protocol;

import common.components.UserAccount;
import common.connections.protocol.ProtocolType;
import core.Core;
import core.UserInformations;
import database.components.AccountType;

/**
 * Classe permettant de rassembler les protocoles concernant les requêtes
 * entrantes.
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 *
 */
public class ServerReceiveProtocol {
   
   private Core core;
   private UserInformations user;
   
   public ServerReceiveProtocol(Core core, UserInformations user) {
      this.core = core;
      this.user = user;
   }
   
   public void ping() {
      user.channelReceive.sendProtocolType(ProtocolType.PING);
   }
   
   /**
    * Protocol de test bidon affichant le message reçu.
    */
   @Deprecated
   public void textMessage() {
      String message;
      
      message = user.channelReceive.receiveString();
      
      user.log.push(message);
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
      user.channelReceive.sendProtocolType(ProtocolType.LOGOUT);
      
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
