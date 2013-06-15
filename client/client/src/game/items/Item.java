/* ============================================================================
 * Nom du fichier   : Item.java
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

import client.GameInitData;
import game.models.Entity;
import game.models.Player;

/**
 * TODO
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 *
 */
public abstract class Item extends Entity {
   
   /**
    * Temps de cooldown en chaque utilisation de l'item (en secondes)
    */
   private float cooldown = 0.5f;
   
   /**
    * Propriétaire de l'arme
    */
   private Player owner;

   /**
    * @return Propriétaire de l'arme
    */
   public Player getOwner() {
      return owner;
   }

   /**
    * @param owner Propriétaire de l'arme
    */
   public void setOwner(Player owner) {
      this.owner = owner;
   }
   
   protected void setCooldown(float cooldown) {
      this.cooldown = cooldown;
   }
   
   protected float getCooldown() {
      return cooldown;
   }
   
   @Override
   public void init(GameInitData initData) { }
   
}
