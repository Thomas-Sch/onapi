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
import game.models.map.Tile;
import box2dLight.PointLight;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

/**
 * Extension de la classe player pour les spécificités du joueur principal.
 * 
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 * 
 */
public class MainPlayer extends Player {

   // Paramètres du halo de lumière autour du joueur
   public final static Color HALO_COLOR = new Color(1, 1, 1, 0.15f);
   public final static float HALO_DISTANCE = Tile.WIDTH * 0.66f;
   
   public MainPlayer(Vector2 pos, Vector2 dir, Team team, Weapon weapon,
         Skill skill, Bonus bonus, GameModel game) {
      super(pos, dir, team, weapon, skill, bonus, game);

      // Halo de lumière diffusé autour du joueur
      (new PointLight(game.getRayHandler(), 100, HALO_COLOR, HALO_DISTANCE,
            getX(), getY())).attachToBody(getBody(), 0, 0);
   }

   public MainPlayer(Player p) {
      super(p.getPos(), p.getDir(), p.getTeam(), p.getWeapon(), p.getSkill(), p
            .getBonus(), p.getGame());
   }

   @Override
   public void shoot(float delta) {
      super.shoot(delta);
      getGame().updates
            .notifyShoot(this, this.getPos().cpy(), getDir().angle());
   }
}
