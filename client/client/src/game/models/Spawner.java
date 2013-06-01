/* ============================================================================
 * Nom du fichier   : Spawner.java
 * ============================================================================
 * Date de création : 31 mai 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package game.models;

import com.badlogic.gdx.math.Vector2;

import game.models.map.Map;
import game.models.map.Tile;

/**
 * TODO
 * 
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 * 
 */
public class Spawner {

   private Team team;

   /**
    * Coordonnées du spawner dans la grille
    */
   private int x, y;

   public Spawner(int x, int y, Team team) {
      this.x = x;
      this.y = y;
      this.team = team;
      team.getSpawners().add(this);
   }

   /**
    * @return L'équipe associée au joueur
    */
   public Team getTeam() {
      return team;
   }

   public int getX() {
      return x;
   }

   public int getY() {
      return y;
   }
   
   public void spawn(Player p) {
      Vector2.tmp.set(Map.getRealPos(x, y));
      Vector2.tmp.sub(Tile.WIDTH / 2, Tile.HEIGHT / 2);
      p.moveTo(Vector2.tmp);
   }
}
