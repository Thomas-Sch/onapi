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

import game.models.Player;

import box2dLight.RayHandler;

import com.badlogic.gdx.graphics.Color;
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
public abstract class Weapon extends Item {

   private static final float AFTER_SHOT_DELAY = 0.2f;
   
   private float previousShootTime = 0;
   private float currentTime = getCooldown() + 1.0f;
   
   private boolean shot = false;
   
   protected Bullet bullet;
   
   public abstract void onHit(Player target);

   public abstract void drawProjectile(SpriteBatch batch, float parentAlpha);

   protected abstract void onShoot();

   protected abstract void afterShot();

   public void shoot(float delta) {
      currentTime += delta;
      if(!this.bullet.isActive())
         if (currentTime > previousShootTime + getCooldown()) {
            onShoot();
            previousShootTime = currentTime;
            shot = true;
         }
   }
   
   @Override
   public void update(float deltaTime) {
   }

   /**
    * @param world
    * @param rayHandler
    */
   public abstract void createBullet(World world, Group group,RayHandler rayHandler);

}
