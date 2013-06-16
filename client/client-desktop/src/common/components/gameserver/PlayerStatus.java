/* ============================================================================
 * Nom du fichier   : PlayersStatus.java
 * ============================================================================
 * Date de création : 1 juin 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package common.components.gameserver;

import java.io.Serializable;

import settings.Language.Text;

import common.components.ObservableComponent;

/**
 * TODO
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 *
 */
public class PlayerStatus extends ObservableComponent implements Serializable {

   /**
    * ID de sérialisation
    */
   private static final long serialVersionUID = -9212231828720224663L;
   
   private int playerId;

   private int slotNumber;
   
   private String name;
   
   private int teamNumber = 0;
   
   private boolean isReady = false;
   
   public PlayerStatus(int playerId, String name, int slotNumber) {
      this.playerId = playerId;
      this.name = name;
      this.slotNumber = slotNumber;
   }
   
   public int getPlayerId() {
      return playerId;
   }
   
   public void setPlayerId(int playerId) {
      if (this.playerId != playerId) {
         this.playerId = playerId;
         setChangedAndNotifyObservers(this);
      }
   }
   
   public int getSlotNumber() {
      return slotNumber;
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
   
   public void setName(String name) {
      this.name = name;
      setChangedAndNotifyObservers();
   }
   
   /**
    * Définit le numéro d'équipe du joueur à cet emplacement.
    * @param number - le numéro d'équipe, un 0 indique qu'il n'en a pas.
    */
   public void setTeamNumber(int number) {
      if (teamNumber != number && number >= 0) {
         teamNumber = number;
         setChangedAndNotifyObservers(this);
      }
   }
   
   /**
    * Définit le status d'emplacement libre (le joueur a quitté).
    */
   public void setLeft() {
      if (!isFree()) {
         playerId = -1;
         name = "";
         setChangedAndNotifyObservers(this);
      }
   }
   
   public boolean isFree() {
      return name == null || name.isEmpty();
   }
   
   public void setReady(boolean ready) {
      if (isReady != ready) {
         isReady = ready;
         setChangedAndNotifyObservers(this);
      }
   }
   
   public void updateFrom(PlayerStatus status) {
      this.name = status.name;
      this.teamNumber = status.teamNumber;
      this.isReady = status.isReady;
      setChangedAndNotifyObservers(this);
   }
   
   @Override
   public String toString() {
      if (isFree()) {
         return Text.SLOT_FREE.toString();
      }
      else {
         return (isReady ? " + " : " - ") + "[" + teamNumber + "] - " + name;
      }
   }
   
}
