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
 * TODO
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 *
 */
public abstract  class Skill extends Item {
   
   protected Skill(GameModel game) {
      super(game);
   }
   
   private boolean active;
   
   abstract public void deactivate();

   abstract public void activate();

   public void setActive(boolean active){
      this.active = active;
   }
   /**
    * @return
    */
   public boolean isActive() {
      return active;
   }

}
