/* ============================================================================
 * Nom du fichier   : Game.java
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

import java.util.List;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;


/**
 * TODO
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 *
 */
public class GameModel {

   private Map map;
   private Player player;
   private List<Team> teams;

   public GameModel() {
      Team t1 = new Team(Color.RED);
      player = new Player(new Vector2(0f, 0f), new Vector2(0f, 1f), t1);
   }
   
   public List<Team> getTeams() {
      return teams;
   }

   public void setTeams(List<Team> teams) {
      this.teams = teams;
   }

   public Player getPlayer() {
      return player;
   }

   public void setPlayer(Player player) {
      this.player = player;
   }


   public Map getMap() {
      return map;
   }


   public void setMap(Map map) {
      this.map = map;
   }
   
   
}
