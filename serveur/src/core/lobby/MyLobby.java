/* ============================================================================
 * Nom du fichier   : MyLobby.java
 * ============================================================================
 * Date de création : 29 mai 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package core.lobby;

import java.util.Observable;
import java.util.Observer;

import core.UserInformations;
import core.lobby.exceptions.LobbyException;

/**
 * TODO
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 *
 */
public class MyLobby implements Observer {
   
   private PlayerSlot[] players;
   
   public MyLobby(int nbPlayers) {
      init(nbPlayers);
   }
   
   /**
    * Initialise le salon d'attente.
    * @param nbPlayers - le nombre de joueurs que pourra accueillir le salon.
    * @throws LobbyException si le nombre de places est négatif ou nul.
    */
   private void init(int nbPlayers) {
      
      if (nbPlayers <= 0) {
         throw new LobbyException("Number of players has to be greater than 0.");
      }
      
      players = new PlayerSlot[nbPlayers];
      
   }
   
   public int getMaxNumberOfPlayers() {
      return players.length;
   }
   
   public int getNumberOfFreeSlots() {
      int count = 0;
      
      synchronized (players) {
         for(PlayerSlot player : players) {
            if (player.user == null) {
               count++;
            }
         }
      }
      
      return count;
   }
   
   public int getNumberOfPlayers() {
      return getMaxNumberOfPlayers() - getNumberOfFreeSlots();
   }
   
   public boolean hasFreeSlot() {
      return getNumberOfFreeSlots() > 0;
   }
   
   public boolean addPlayer(UserInformations user) {
      synchronized (players) {
         int index = getFirstFreeIndex();
         
         // Indice négatif => aucun emplacement de libre
         if (index < 0) {
            return false;
         }
         
         players[index].user = user;
         players[index].status.setReady(false);
         
      }
      
      return true;
   }
   
   private int getFirstFreeIndex() {
      int index = -1;
      
      synchronized(players) {
         for (PlayerSlot slot : players) {
            index++;
            if(slot.user == null) {
               return index;
            }
         }
      }
      
      return index;
   }
   
   private class PlayerSlot extends Observable implements Observer {
      
      private UserInformations user = null;
      
      private PlayerStatus status;
      
      private PlayerSlot() {
         status = new PlayerStatus();
         status.addObserver(this);
      }

      @Override
      public void update(Observable arg0, Object arg1) {
         setChanged();
         notifyObservers();
      }
      
      
   }
   
   public class PlayerStatus extends Observable {
      
      private boolean isReady = false;
      
      public PlayerStatus() {
         
      }
      
      public boolean isReady() {
         return isReady;
      }
      
      public void setReady(boolean ready) {
         if (isReady != ready) {
            isReady = ready;
            setChanged();
            notifyObservers();
         }
      }
   }

   @Override
   public void update(Observable o, Object arg) {
      
      synchronized(players) {
         
         for (PlayerSlot slot : players) {
            
            if (slot.user != null) {
               // TODO
               
               
               
            }
         }
         
         
      }
      
   }

}
