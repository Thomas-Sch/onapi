/* ============================================================================
 * Nom du fichier   : Bonus.java
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

/**
 * Définit un bonus. Un bonus est un item ajoutant certains effets à l'arme du
 * joueur pour toute la durée de la partie.
 * 
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 * 
 */
public abstract class Bonus extends Item {

   protected Bonus(GameModel game) {
      super(game);
   }

}
