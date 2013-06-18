/* ============================================================================
 * Nom du fichier   : PlayersInformations.java
 * ============================================================================
 * Date de création : 7 juin 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package core;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

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
public class PlayersInformations extends CoreComponent implements Observer {
   
   private LinkedList<PlayerInfo> players;
   
   private int slotNumber;
   
   public PlayersInformations(int slotNumber) {
      players = new LinkedList<PlayerInfo>();
      
      this.slotNumber = slotNumber;
   }
   
   public int size() {
      return slotNumber;
   }
   
   public synchronized PlayerInfo getPlayerAt(int slotNumber) {
      return players.get(slotNumber);
   }
   
   public synchronized void addOrUpdate(PlayerStatus playerStatus) {
      boolean found = false;
      
      Iterator<PlayerInfo> it = players.iterator();
      PlayerInfo current = null;
      
      while (!found && it.hasNext()) {
         current = it.next();
         
         // Mise à jour si correspondance
         if (current.getPlayerId() == playerStatus.getPlayerId()) {
            current.update(playerStatus);
            found = true;
            
         }
      }
      
      // Cas d'ajout
      if (!found) {
         current = new PlayerInfo(playerStatus); 
         
         players.add(current);
      }
      
      setChangedAndNotifyObservers(current);
      
   }
   
   public synchronized boolean remove(PlayerStatus playerStatus) {
      boolean result = false;
      
      Iterator<PlayerInfo> it = players.iterator();
      PlayerInfo current;
      
      while (!result && it.hasNext()) {
         current = it.next();
         
         // Suppression si correspondance
         if (current.getPlayerId() == playerStatus.getPlayerId()) {
            current.update(playerStatus);
            it.remove();
            result = true;
            setChangedAndNotifyObservers(current);
         }
      }
      
      return result;
   }
   

   @Override
   public void update(Observable arg0, Object arg1) {
      setChangedAndNotifyObservers(arg1);
   }
   
}

