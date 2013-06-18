/* ============================================================================
 * Nom du fichier   : GameServer.java
 * ============================================================================
 * Date de création : 29 mai 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package core.gameserver;

import gui.LogsFrame;
import gui.logs.Log;

import java.util.Observable;
import java.util.Observer;

import common.components.AccountType;
import common.components.gameserver.PlayerStatus;

import settings.Settings;

import core.Core;
import core.UserInformations;
import core.gameserver.exceptions.GameServerException;
import core.updates.components.LobbyUpdateSlot;

/**
 * Représente un serveur de jeu. Gère le salon d'attente avant de lancer la
 * partie.
 * 
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 * 
 */
public class GameServer implements Observer {

   private Core core;

   private PlayerSlot[] players;

   // Partie jeu
//   Non utilisé pour l'instant
//   private boolean gameIsRunning = false;

   private Log log;

   /**
    * Crée un serveur de jeu.
    * 
    * @param nbPlayers
    *           - le nombre de joueurs accepté par le serveur.
    * @param name
    *           - le nom du serveur.
    * @param logsFrame
    *           - la fenêtre de logs dans laquelle afficher le log du serveur.
    */
   public GameServer(Core core, int nbPlayers, String name, LogsFrame logsFrame) {
      init(core, nbPlayers, name, logsFrame);
   }

   /**
    * Initialise le serveur de jeu.
    * 
    * @param core
    *           - le coeur logique du serveur.
    * @param nbPlayers
    *           - le nombre de joueurs que pourra accueillir le salon.
    * @param name
    *           - le nom du serveur de jeu.
    * @param logsFrame
    *           - la fenêtre de logs pour y ajouter celui du lobby.
    * @throws LobbyException
    *            si le nombre de places est négatif ou nul.
    */
   private void init(Core core, int nbPlayers, String name, LogsFrame logsFrame) {
      this.core = core;

      log = new Log(name);
      logsFrame.addLogPanel(log.createLogPanel());

      log.push("Starting the server " + name + " ...");

      if (nbPlayers <= 0) {
         throw new GameServerException(
               "Number of players has to be greater than 0.");
      }

      // Initialisation des emplacements
      players = new PlayerSlot[nbPlayers];
      for (int i = 0; i < players.length; i++) {
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
         for (PlayerSlot player : players) {
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

            printStatus();

            // Transmet alors les données actuelles au nouveau venu
            for (PlayerSlot slot : players) {
               if (!slot.isFree()) {
                  user.serverUpdate.pushUpdate(new LobbyUpdateSlot(
                        slot.slotNumber, slot.getStatus()));
               }
            }

         }
         else {
            if (Settings.DEBUG_MODE_ON) {
               log.push("Error while adding a player");
            }
         }

         return players[index].status;
      }
   }

   public boolean removePlayer(UserInformations user) {
      synchronized (players) {
         // Recherche du bon joueur pour suppression
         for (PlayerSlot slot : players) {

            if (slot.user == user) {
               slot.removeUser();

               if (Settings.DEBUG_MODE_ON) {
                  log.push("Player " + user.account.getLogin() + " removed");
               }

               printStatus();
               return true;
            }
         }
      }
      return false;
   }

   public UserInformations adminKick(int id) {
      UserInformations user = null;
      int i = 0;

      synchronized (players) {
         while (i < players.length) {
            if (!players[i].isFree() && players[i].status.getPlayerId() == id) {

               user = players[i].removeUser();
               printStatus();
            }
            i++;
         }
      }

      return user;
   }

   private int getFirstFreeIndex() {
      int index = -1;

      synchronized (players) {
         for (PlayerSlot slot : players) {
            index++;
            if (slot.isFree()) {
               return index;
            }
         }
      }

      return index;
   }

   private void printStatus() {
      int slotRemainings = getNumberOfFreeSlots();
      log.push(" Current lobby status : " + getNumberOfPlayers() + " / "
            + getMaxNumberOfPlayers() + " (" + slotRemainings + " free slot"
            + (slotRemainings > 1 ? "s" : "") + ")");
   }

   private class PlayerSlot extends Observable implements Observer {

      private UserInformations user = null;

      private PlayerStatus status;

      private int slotNumber;

      private PlayerSlot(int slotNumber) {
         this.slotNumber = slotNumber;
         status = new PlayerStatus(-1, "No player", slotNumber);
         status.addObserver(this);
      }

      @Override
      public void update(Observable arg0, Object arg1) {
         setChanged();
         notifyObservers(this);
      }

      public boolean isFree() {
         return user == null;
      }

      public int getSlotNumber() {
         return slotNumber;
      }

      public boolean setUser(UserInformations user) {
         boolean success = false;

         if (isFree()) {
            this.user = user;
            status.setPlayerId(user.account.getId());
            status.setName(user.account.getLogin());
            status.setReady(false);
            success = true;
            setChanged();
            notifyObservers(this);
         }

         return success;
      }

      public UserInformations removeUser() {
         UserInformations result = user;

         if (!isFree()) {
            user = null;
            status.setLeft();
            setChanged();
            notifyObservers(this);
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
         PlayerSlot updatedSlot = (PlayerSlot) o;
         PlayerStatus status = updatedSlot.getStatus();
         int slotNumber = updatedSlot.getSlotNumber();

         // Envoi de l'information mise à jour aux joueurs présents qui ne sont
         // pas administrateurs
         synchronized (players) {
            for (PlayerSlot slot : players) {
               if (!slot.isFree()
                     && slot.user.account.getType() != AccountType.ADMINISTRATOR) {
                  slot.user.serverUpdate.pushUpdate(new LobbyUpdateSlot(
                        slotNumber, status));
               }
            }
         }

         // Envoi de l'information aux administrateurs
         core.adminUpdate(new LobbyUpdateSlot(slotNumber, status));
      }

   }

}
