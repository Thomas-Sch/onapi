/* ============================================================================
 * Nom du fichier   : MainPlayer.java
 * ============================================================================
 * Date de création : 15 juin 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package game.models;

import game.items.Bonus;
import game.items.Skill;
import game.items.Weapon;
import box2dLight.PointLight;

import com.badlogic.gdx.math.Vector2;

/**
 * TODO
 * 
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 * 
 */
public class MainPlayer extends Player {

   public MainPlayer(Vector2 pos, Vector2 dir, Team team, Weapon weapon,
         Skill skill, Bonus bonus, GameModel game) {
      super(pos, dir, team, weapon, skill, bonus, game);

      // Halo de lumière diffusé autour du joueur
      (new PointLight(game.getRayHandler(), 100, HALO_COLOR,
            PL_DISTANCE_DEFAULT, getX(), getY())).attachToBody(getBody(), 0, 0);
   }

   public MainPlayer(Player p) {
      super(p.getPos(), p.getDir(), p.getTeam(), p.getWeapon(), p.getSkill(), p
            .getBonus(), p.getGame());
   }
}
