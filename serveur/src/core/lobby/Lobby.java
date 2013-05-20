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
   
   private UserInformations[] players;
   
   private int numberOfPlayers = 0;
   
   private int updateTimeout;
   
   private Port updatePort;
   
   
   
   public Lobby(int nbPlayers, int updatePortWanted, int updateTimeout) {
      init(nbPlayers, updatePortWanted, updateTimeout);
      
   }
   
   public synchronized void addPlayer(UserInformations user) throws LobbyException {
      
      if (numberOfPlayers < players.length) {
         players[numberOfPlayers] = user;
         numberOfPlayers++;
         
         // Attendra un temps maximum fixé, au-delà : échec de connexion
         updatePort.setTimeout(3000);
         
         try {
            user.update = new UserUpdateConnection(user, updatePort.accept(),
                                                   updateTimeout);
            
            Thread thread = new Thread(user.update);
            thread.start();
         }
         catch (PortException e) {
            numberOfPlayers--;
            players[numberOfPlayers] = null;
         }
         
         updatePort.setTimeout(0);
      }
      else {
         throw new LobbyException("This lobby is already full.");
      }
      
   }
   
   public int getNumberOfPlayers() {
      return numberOfPlayers;
   }
   
   public int getMaxNumberOfPlayers() {
      return players.length;
   }
   
   public int getFreeSlots() {
      return getMaxNumberOfPlayers() - getNumberOfPlayers();
   }
   
   public int getUpdatePortNumber() {
      return updatePort.getPortNumber();
   }
   
   
   private void init(int nbPlayers, int updatePortNumber, int updateTimeout) {
      players = new UserInformations[nbPlayers];
      
      this.updateTimeout = updateTimeout;
      
      updatePort = new Port(updatePortNumber);
      updatePort.activateFreePort();
      
   }

}
