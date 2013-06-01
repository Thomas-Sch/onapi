/* ============================================================================
 * Nom du fichier   : Team.java
 * ============================================================================
 * Date de création : 8 mai 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package game.models;

import java.util.LinkedList;
import java.util.ListIterator;

import utils.ListUtils;

import com.badlogic.gdx.graphics.Color;

/**
 * TODO
 * 
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 * 
 */
public class Team {

   private Color color;
   private LinkedList<Player> members;
   private LinkedList<Spawner> spawners;

   public Team(Color color) {
      this(color, new LinkedList<Player>());
   }

   public Team(Color color, LinkedList<Player> members) {
      super();
      this.color = color;
      this.members = members;
      this.spawners = new LinkedList<Spawner>();
   }

   public Color getColor() {
      return color;
   }

   public void setColor(Color color) {
      this.color = color;
   }

   public LinkedList<Player> getMembers() {
      return members;
   }

   public void setMembers(LinkedList<Player> members) {
      this.members = members;
   }

   /**
    * @return La liste des spawners actuellement utilisés par l'équipe
    */
   public LinkedList<Spawner> getSpawners() {
      return spawners;
   }

   /**
    * Déplace les membres de l'équipe en les plaçant aléatoirement sur les
    * spawners à disposition de l'équipe. Il peut ne pas y avoir de joueur à la
    * fin sur un spawner, comme il peut y en avoir plusieurs.
    */
   public void spawnPlayers() {
      LinkedList<Spawner> spawns = ListUtils.shuffled(spawners);
      ListIterator<Spawner> spawnIt = spawns.listIterator();
      Spawner currentSpawner;
      for (Player p : members) {
         if (!spawnIt.hasNext()) {
            spawnIt = spawns.listIterator();
            currentSpawner = spawns.get(0);
         }
         else {
            currentSpawner = spawnIt.next();
         }
         currentSpawner.spawn(p);
      }
   }

}
