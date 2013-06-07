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
package common.components.lobby;

import java.io.Serializable;
import java.util.Observable;

/**
 * TODO
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 *
 */
public class PlayerStatus extends Observable implements Serializable {
   
   private static final long serialVersionUID = 4610616944538954831L;

   private int slotNumber;
   
   private String name;
   
   private int teamNumber = 0;
   
   private boolean isReady = false;
   
   public PlayerStatus(String name, int slotNumber) {
      this.name = name;
      this.slotNumber = slotNumber;
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
      setChanged();
      notifyObservers();
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
   
   public void updateFrom(PlayerStatus status) {
      this.name = status.name;
      this.teamNumber = status.teamNumber;
      this.isReady = status.isReady;
      setChanged();
      notifyObservers();
   }
   
   @Override
   public String toString() {
      return (isReady ? " + " : " - ") + "[" + teamNumber + "] - " + name;
   }
   
}
