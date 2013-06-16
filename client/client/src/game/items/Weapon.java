/* ============================================================================
 * Nom du fichier   : Weapon.java
 * ============================================================================
 * Date de création : 1 mai 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package game.items;

import game.models.GameModel;
import game.models.Player;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
public abstract class Weapon extends Item {

   protected Weapon(GameModel game) {
      super(game);
   }

   private float previousShootTime = 0;
   private float currentTime = getCooldown() + 1.0f;

   protected Bullet bullet;

   public abstract void onHit(Player target);

   public abstract void drawProjectile(SpriteBatch batch, float parentAlpha);

   protected abstract void onShoot();

   public void shoot(float delta) {
      currentTime += delta;
      if (!this.bullet.isActive()) {
         if (currentTime > previousShootTime + getCooldown()) {
            onShoot();
            previousShootTime = currentTime;
         }
      }
   }

   @Override
   public void update(float deltaTime) {
   }

   /**
    * @param world
    * @param rayHandler
    */
   public abstract void createBullet(Group group);

}
