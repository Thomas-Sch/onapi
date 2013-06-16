/* ============================================================================
 * Nom du fichier   : DefaultWeapon.java
 * ============================================================================
 * Date de création : 1 mai 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package game.items.weapons;

import game.items.Projectile;
import game.items.Weapon;
import game.models.GameModel;
import game.models.Player;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Group;

/**
 * Arme par défaut du joueur. Ne peut tirer qu'une balle à la fois.
 * 
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 * 
 */
public class DefaultWeapon extends Weapon {

   public DefaultWeapon(GameModel game) {
      super(game);
      setCooldown(0.02f);
   }

   public void createBullet(Group group) {
      this.bullet = new Projectile(this, 0, 0, 750f, 200f, getOwner(), getGame());
      group.addActor(bullet);
   }

   @Override
   public void onHit(Player target) {
      if (bullet.isActive()) {
         target.damage(20);
         System.out.println("target hit");
         bullet.deactivate();
      }
   }

   @Override
   public void drawProjectile(SpriteBatch batch, float parentAlpha) {
      if (bullet.isActive()) bullet.draw(batch, parentAlpha);
   }

   @Override
   protected void onShoot() {
      System.out.printf("%3s shot from %10s to %10s\n", getOwner().getId(),
            getOwner().getPos(), getOwner().getDir());
      bullet.activate(getOwner().getX(), getOwner().getY(),
            getOwner().getDir().x, getOwner().getDir().y);
   }

}
