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
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;


/**
 * TODO
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 *
 */
public class Player extends Actor {

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

   public void moveTo(Vector2 newPos) {
      this.pos = newPos;
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

   public Team getTeam() {
      return team;
   }

   public void setTeam(Team team) {
      this.team = team;
   }
   
   
   
}
