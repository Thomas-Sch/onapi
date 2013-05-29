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
import common.components.UserAccount;
import common.connections.Channel;
import core.lobby.Lobby;

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
   
   public UserState state;
   
   public ServerRequestAnswers serverReceive;
   public ServerUpdateOrder serverUpdate;
   
   public UserUpdateConnection updateActivity;
   
   public Lobby lobby;
   
   public UserInformations() {
      
   }
   
   public enum UserState {
      AUTHENTIFICATION, LOBBY, GAME;
   }

}
