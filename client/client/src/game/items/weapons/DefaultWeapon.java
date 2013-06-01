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

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import game.items.Weapon;
import game.models.Player;

/**
 * TODO
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 *
 */
public class DefaultWeapon extends Weapon {

   @Override
   public void onHit(Player target) {
      target.damage(10);
   }

   @Override
   public void drawProjectile(SpriteBatch batch, float parentAlpha) {
      // TODO Auto-generated method stub
   }

   @Override
   public void update(float deltaTime) {
      // TODO Auto-generated method stub
      
   }

}
