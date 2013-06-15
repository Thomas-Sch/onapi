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

import game.items.Bullet;
import game.items.Weapon;
import game.models.Player;
import box2dLight.RayHandler;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Group;

/**
 * TODO
 * 
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 * 
 */
public class DefaultWeapon extends Weapon {

   public DefaultWeapon() {
      setCooldown(0.2f);
   }

   public void createBullet(World world, Group group, RayHandler handler) {
      this.bullet = new Bullet(world, this, 0f, 0f, 0f, 0f, 750f, 200f,
            handler, getOwner());
      group.addActor(bullet);
   }

   @Override
   public void onHit(Player target) {
      if (bullet.isActive()) {
         target.damage(25);
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
