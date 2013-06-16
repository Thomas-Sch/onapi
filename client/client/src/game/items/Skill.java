/* ============================================================================
 * Nom du fichier   : Skill.java
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
 * Définit une compétence du joueur. Un compétence est un effet activable en
 * cours de partie par le joueur. Son nombre d'utilisations peut ou non être
 * limité, de même que la durée de son effet.
 * 
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 * 
 */
public abstract class Skill extends Item {

   protected Skill(GameModel game) {
      super(game);
   }

   /**
    * Indique si l'effet est actif
    */
   private boolean active;

   /**
    * Active l'effet de la compétence
    */
   abstract public void deactivate();

   /**
    * Désactive l'effet de la compétence
    */
   abstract public void activate();

   public void setActive(boolean active) {
      this.active = active;
   }

   /**
    * @return true si l'effet est actif
    */
   public boolean isActive() {
      return active;
   }

}
