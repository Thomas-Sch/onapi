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

import java.util.Observable;
import java.util.Observer;

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
   
   private PlayerInfo[] players;
   
   public PlayersInformations(int nbPlayer) {
      players = new PlayerInfo[nbPlayer];
      
      for (int i = 0 ; i < players.length ; i++) {
         players[i] = new PlayerInfo(i);
         players[i].addObserver(this);
      }
   }
   
   public int size() {
      return players.length;
   }
   
   public synchronized PlayerInfo getPlayerAt(int slotNumber) {
      return players[slotNumber];
   }

   @Override
   public void update(Observable arg0, Object arg1) {
      setChanged();
      notifyObservers(arg1);
   }
   
}
