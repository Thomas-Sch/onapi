/* ============================================================================
 * Nom du fichier   : DefaultBonus.java
 * ============================================================================
 * Date de création : 2 juin 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package game.items.bonus;

import game.items.Bonus;
import game.models.GameModel;

/**
 * Bonus par défaut. Ne fait rien.
 * 
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 * 
 */
public class DefaultBonus extends Bonus {

   public DefaultBonus(GameModel game) {
      super(game);
   }

   @Override
   public void update(float deltaTime) {
   }

}
