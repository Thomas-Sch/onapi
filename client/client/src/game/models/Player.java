/* ============================================================================
 * Nom du fichier   : Player.java
 * ============================================================================
 * Date de création : 1 mai 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package game.models;

import com.badlogic.gdx.math.Vector2;

/**
 * TODO
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 *
 */
public class Player {

   private Vector2 pos;
   private Vector2 dir;
   private Team team;


   public Player(Vector2 pos, Vector2 dir, Team team) {
      super();
      this.pos = pos;
      this.dir = dir;
      this.setTeam(team);
   }

   
   public Vector2 getPos() {
      return pos;
   }


   public void setPos(Vector2 pos) {
      this.pos = pos;
   }

   public void moveTo(Vector2 newPos) {
      setPos(newPos);
   }
   
   public void move(Vector2 dir) {
      this.pos.add(dir);
   }

   public Vector2 getDir() {
      return dir;
   }


   public void setDir(Vector2 dir) {
      this.dir = dir;
   }


   /**
    * @return the team
    */
   public Team getTeam() {
      return team;
   }


   /**
    * @param team the team to set
    */
   public void setTeam(Team team) {
      this.team = team;
   }
   
   
   
}
