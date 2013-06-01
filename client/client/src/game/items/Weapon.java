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

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * TODO
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 *
 */
public abstract class Weapon extends Item {

   public abstract void onHit(Player target);

   public abstract void drawProjectile(SpriteBatch batch, float parentAlpha);
   
   
}
