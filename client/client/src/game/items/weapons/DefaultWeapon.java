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

import game.items.Weapon;
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
public class DefaultWeapon extends Weapon {

   private Color originalTorchColor;
   private boolean gotColor = false;

   public DefaultWeapon() {
      setCooldown(0.2f);
   }

   @Override
   public void onHit(Player target) {
      target.damage(10);
   }

   @Override
   public void drawProjectile(SpriteBatch batch, float parentAlpha) {
      // TODO Auto-generated method stub
   }

   @Override
   protected void onShoot() {
      System.out.printf("%3s shot from %10s to %10s\n", getOwner().getId(),
            getOwner().getPos(), getOwner().getDir());
//      if (!gotColor) {
//         originalTorchColor = getOwner().getTorchColor().cpy();
//         System.out.println(originalTorchColor);
//         gotColor = true;
//      }
//      getOwner().setTorchColor(Color.YELLOW);
   }

   @Override
   protected void afterShot() {
//      System.out.println("After shot");
//      getOwner().setTorchColor(originalTorchColor);
   }

}
