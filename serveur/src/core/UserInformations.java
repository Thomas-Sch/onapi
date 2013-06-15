/* ============================================================================
 * Nom du fichier   : Client.java
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

import gui.logs.Log;
import common.components.ActivityType;
import common.components.ConnectedUser;
import common.components.UserAccount;
import common.connections.Channel;
import core.gameserver.GameServer;
import core.updates.ServerUpdateOrder;

/**
 * Classe regroupant les variables liées à un utilisateur.
 * 
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 *
 */
public class UserInformations {
   
   public ConnectionsToClient connectionsToClient;
   
   public Log log;
   
   public boolean isConnected;
   
   public UserAccount account;
   
   public ActivityType activity;
   
   public ServerRequestAnswers serverReceive;
   public ServerUpdateOrder serverUpdate;
   
   public UserUpdateConnection updateActivity;
   
   public GameServer gameServer;
   
   public UserInformations() {
      
   }
   
   public ConnectedUser getConnectedUser() {
      ConnectedUser connectedUser = new ConnectedUser(account.getId(),
            activity, account.getLogin());
      
      if (! isConnected) {
         connectedUser.setConnected(false);
      }
      
      return connectedUser;
   }

}
