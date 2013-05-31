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

import java.io.Serializable;
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
      
      // Initialisation des emplacements
      players = new PlayerSlot[nbPlayers];
      for(int i = 0 ; i < players.length ; i++) {
         players[i] = new PlayerSlot(i);
         players[i].addObserver(this);
      }
      
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
         
         if (players[index].setUser(user)) {
            System.out.println("DEBUG - Lobby - add player successfully !");
         }
         else {
            System.out.println("DEBUG - Lobby - error while adding player !");
         }
         
      }
      
      return true;
   }
   
   private int getFirstFreeIndex() {
      int index = -1;
      
      synchronized(players) {
         for (PlayerSlot slot : players) {
            index++;
            if(slot.isFree()) {
               return index;
            }
         }
      }
      
      return index;
   }
   
   private class PlayerSlot extends Observable implements Observer {
      
      private UserInformations user = null;
      
      private PlayerStatus status;
      
      private int slotNumber;
      
      private PlayerSlot(int slotNumber) {
         this.slotNumber = slotNumber;
         status = new PlayerStatus(user.account.getLogin());
         status.addObserver(this);
      }

      @Override
      public void update(Observable arg0, Object arg1) {
         setChanged();
         notifyObservers();
      }
      
      public boolean isFree() {
         return user == null;
      }
      
      public int getSlotNumber() {
         return slotNumber;
      }
      
      public boolean setUser(UserInformations user) {
         boolean success = false;
         
         if(isFree()) {
            this.user = user;
            success = true;
            setChanged();
            notifyObservers();
         }
         
         return success;
      }
      
      public UserInformations removeUser() {
         UserInformations result = user;
         
         if(!isFree()) {
            user = null;
            setChanged();
            notifyObservers();
         }
         
         return result;
      }
      
      public PlayerStatus getStatus() {
         return status;
      }
      
      
   }
   
   public class PlayerStatus extends Observable implements Serializable {
      
      private String name;
      
      private int teamNumber = 0;
      
      private boolean isReady = false;
      
      public PlayerStatus(String name) {
         this.name = name;
      }
      
      public boolean isReady() {
         return isReady;
      }
      
      public String getName() {
         return name;
      }
      
      public int getTeamNumber() {
         return teamNumber;
      }
      
      /**
       * Définit le numéro d'équipe du joueur à cet emplacement.
       * @param number - le numéro d'équipe, un 0 indique qu'il n'en a pas.
       */
      public void setTeamNumber(int number) {
         this.teamNumber = number;
         setChanged();
         notifyObservers();
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
      
      // Ne s'occupe que de la mise à jour d'un slot
      if (o instanceof PlayerSlot) {
         PlayerSlot updtatedSlot = (PlayerSlot)o;
         
         synchronized(players) {
            
            for (PlayerSlot slot : players) {
               
               if (! slot.isFree()) {
                  // TODO
                  
               }
            }
            
            
         }
      }
      
   }

}
