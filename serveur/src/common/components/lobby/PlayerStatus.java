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
}
