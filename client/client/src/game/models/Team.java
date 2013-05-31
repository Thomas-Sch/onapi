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

}
