/* ============================================================================
 * Nom du fichier   : UserInfo.java
 * ============================================================================
 * Date de création : 14 juin 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package core;

import java.util.Observable;
import java.util.Observer;

import common.components.ActivityType;
import common.components.ConnectedUser;
import common.components.gameserver.PlayerStatus;

/**
 * 
 * TODO
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 *
 */
public class PlayerInfo extends CoreComponent implements Observer {
   
   private PlayerStatus player;
   
   public PlayerInfo(PlayerStatus player) {
      this.player = player;
      this.player.addObserver(this);
   }
   
   public void update(PlayerStatus status) {
      
      // Libération de l'emplacement
      if (status.isFree()) {
         player.setLeft();
      }
      // Mise à jour
      else {
         player.startMultipleChanges();
         
         player.setPlayerId(status.getPlayerId());
         player.setName(status.getName());
         player.setTeamNumber(status.getTeamNumber());
         player.setReady(status.isReady());
         
         player.endMultipleChanges();
      }
      
      
   }
   
   public void update(PlayerInfo playerInfo) {
      
      // Libération de l'emplacement
      if (playerInfo.isFree()) {
         player.setLeft();
      }
      // Mise à jour
      else {
         player.startMultipleChanges();
         
         player.setPlayerId(playerInfo.getPlayerId());
         player.setName(playerInfo.getName());
         player.setTeamNumber(playerInfo.getTeamNumber());
         player.setReady(playerInfo.isReady());
         
         player.endMultipleChanges();
      }
   }
   
   public int getPlayerId() {
      return player.getPlayerId();
   }
   
   public int getSlotNumber() {
      return player.getSlotNumber();
   }
   
   public boolean isReady() {
      return player.isReady();
   }
   
   public String getName() {
      return player.getName();
   }
   
   public int getTeamNumber() {
      return player.getTeamNumber();
   }
   
   public boolean isFree() {
      return player.isFree();
   }
   
   @Override
   public String toString() {
      return player.toString();
   }

   @Override
   public void update(Observable arg0, Object arg1) {
      setChangedAndNotifyObservers(arg1);
   }
   
}