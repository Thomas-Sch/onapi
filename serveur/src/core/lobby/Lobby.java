/* ============================================================================
 * Nom du fichier   : Lobby.java
 * ============================================================================
 * Date de création : 19 mai 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package core.lobby;

import java.net.Socket;
import java.util.LinkedList;
import java.util.Random;

import common.connections.Channel;

import core.Port;
import core.UserInformations;
import core.UserUpdateConnection;
import core.exceptions.PortException;
import core.lobby.exceptions.LobbyException;

/**
 * TODO
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 *
 */
public class Lobby {
   
   private boolean exit = false;
   
   private Thread activity;
   
   private LinkedList<UserInformations> players = new LinkedList<>();
   
   private int numberMaxOfPlayers;
   
   private int updateTimeout;
   
   private LinkedList<ExpectedPlayer> expectedPlayers = new LinkedList<>();
   
   private boolean waiting = false;
   
   public Lobby(int nbPlayers) {
      init(nbPlayers);
   }
   
   public int addPlayer(UserInformations user) {
      int code;
      
      synchronized(players) {
         if (players.size() < numberMaxOfPlayers && !isUserAlreadyIn(user)) {
            players.add(user);
            
            code = generateConnectionCode();
            
            System.out.println("Generated code : " + code);
            
            synchronized (expectedPlayers) {
               expectedPlayers.add(new ExpectedPlayer(user, code));
            }
            
         }
         else {
            throw new LobbyException("This lobby is already full.");
         }
         
         
      }
      
      return code;
   }
   
   
   public void removePlayer(UserInformations user) {
      
      synchronized (players) {
         if (players.remove(user)) {
            user.log.push("Removed from the lobby list.");
         }
         else {
            user.log.push("Tryed to remove from a wrong lobby.");
            throw new LobbyException("This player was not in that lobby.");
         }
      }
      
   }
   
   public int getNumberOfPlayers() {
      int result;
      synchronized (players) {
         result = players.size();
      }
      return result;
   }
   
   public int getMaxNumberOfPlayers() {
      return numberMaxOfPlayers;
   }
   
   public int getFreeSlots() {
      return getMaxNumberOfPlayers() - getNumberOfPlayers();
   }
   
   
   private void init(int nbPlayers) {
      numberMaxOfPlayers = nbPlayers;
   }
   
   private int generateConnectionCode() {
      int code;
      
      do {
         code = (int)(Math.random() * Integer.MAX_VALUE);
      } while (!isCodeFree(code));
      
      return code;
   }
   
   private boolean isCodeFree(int code) {
      
      synchronized (expectedPlayers) {
         
         for(ExpectedPlayer player : expectedPlayers) {
            if (player.code == code) {
               return false;
            }
         }
         
         return true;
      }
   }
   
   private boolean isUserAlreadyIn(UserInformations user) {
      
      synchronized(players) {
         for (UserInformations player : players) {
            if (player.account.getId() == user.account.getId()) {
               return true;
            }
         }
      }
      
      synchronized (expectedPlayers) {
         for (ExpectedPlayer player : expectedPlayers) {
            if (player.user.account.getId() == user.account.getId()) {
               return true;
            }
         }
      }
      
      return false;
      
   }

}
