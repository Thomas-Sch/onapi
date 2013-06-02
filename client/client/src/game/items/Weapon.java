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

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

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
   
   public abstract void onHit(Player target);

   public abstract void drawProjectile(SpriteBatch batch, float parentAlpha);

   protected abstract void onShoot();

   protected abstract void afterShot();

   public void shoot(float delta) {
      currentTime += delta;
      if (currentTime > previousShootTime + getCooldown()) {
         onShoot();
         previousShootTime = currentTime;
         shot = true;
      }
   }
   
   @Override
   public void update(float deltaTime) {
//      if (shot && currentTime + deltaTime - previousShootTime > AFTER_SHOT_DELAY) {
//         currentTime += deltaTime;
//         afterShot();
//         shot = false;
//      }
   }

}
