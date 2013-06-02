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

import gui.LogsFrame;
import gui.logs.Log;

import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

import common.components.lobby.PlayerStatus;

import core.UserInformations;
import core.lobby.exceptions.LobbyException;
import core.updates.components.LobbyUpdateSlot;

/**
 * TODO
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 *
 */
public class Lobby implements Observer {
   
   private PlayerSlot[] players;
   
   private Log log;
   
   public Lobby(int nbPlayers, LogsFrame logsFrame) {
      init(nbPlayers, logsFrame);
   }
   
   /**
    * Initialise le salon d'attente.
    * @param nbPlayers - le nombre de joueurs que pourra accueillir le salon.
    * @param logsFrame - la fenêtre de logs pour y ajouter celui du lobby.
    * @throws LobbyException si le nombre de places est négatif ou nul.
    */
   private void init(int nbPlayers, LogsFrame logsFrame) {
      log = new Log("Lobby");
      logsFrame.addLogPanel(log.createLogPanel());
      
      log.push("Starting the lobby...");
      
      if (nbPlayers <= 0) {
         throw new LobbyException("Number of players has to be greater than 0.");
      }
      
      // Initialisation des emplacements
      players = new PlayerSlot[nbPlayers];
      for(int i = 0 ; i < players.length ; i++) {
         players[i] = new PlayerSlot(i);
         players[i].addObserver(this);
      }
      
      log.push("Lobby is ready for " + nbPlayers + " players.");
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
   
   public PlayerStatus addPlayer(UserInformations user) {
      synchronized (players) {
         int index = getFirstFreeIndex();
         
         // Indice négatif => aucun emplacement de libre
         if (index < 0) {
            return null;
         }
         
         if (players[index].setUser(user)) {
            log.push("DEBUG - Added a new player successfully !");
            
            printStatus();
            
            // Transmet alors les données actuelles
            for(PlayerSlot slot : players) {
               if(!slot.isFree()) {
                  user.serverUpdate.pushUpdate(new LobbyUpdateSlot(slot.slotNumber, slot.getStatus()));
               }
            }
            
         }
         else {
            log.push("DEBUG - Error while adding player !");
         }
         
         return players[index].status;
      }
   }
   
   public boolean removePlayer(UserInformations user) {
      synchronized (players) {
         // Recherche du bon joueur
         for (PlayerSlot slot : players) {
            if (slot.user == user) {
               slot.removeUser();
               log.push("DEBUG - Player " + user.account.getLogin() + " removed");
               user.serverUpdate.pushUpdate(new LobbyUpdateSlot(slot.slotNumber, slot.getStatus()));
               
               printStatus();
               return true;
            }
         }
      }
      return false;
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
   
   private void printStatus() {
      int slotRemainings = getNumberOfFreeSlots();
      log.push(" Current lobby status : " + getNumberOfPlayers() +
            " / " + getMaxNumberOfPlayers() + " (" + slotRemainings +
            " free slot" + (slotRemainings > 1 ? "s" : "") + ")");
   }
   
   private class PlayerSlot extends Observable implements Observer {
      
      private UserInformations user = null;
      
      private PlayerStatus status;
      
      private int slotNumber;
      
      private PlayerSlot(int slotNumber) {
         this.slotNumber = slotNumber;
         status = new PlayerStatus("No player");
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
            status.setName(user.account.getLogin());
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
   
   @Override
   public void update(Observable o, Object arg) {
      
      // Ne s'occupe que de la mise à jour d'un slot
      if (o instanceof PlayerSlot) {
         PlayerSlot updtatedSlot = (PlayerSlot)o;
         
         synchronized(players) {
            
            for (PlayerSlot slot : players) {
               
               if (! slot.isFree()) {
                  slot.user.serverUpdate.pushUpdate(
                        new LobbyUpdateSlot(updtatedSlot.getSlotNumber(),
                                            updtatedSlot.getStatus()));
                  
               }
            }
            
            
         }
      }
      
   }

}
