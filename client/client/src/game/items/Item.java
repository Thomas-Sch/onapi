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

import client.GameData;
import game.models.Entity;
import game.models.GameModel;
import game.models.Player;

/**
 * Définit un item équipable par un joueur.
 * 
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 * 
 */
public abstract class Item extends Entity {

   protected Item(GameModel game) {
      super(game);
   }

   /**
    * Temps de cooldown en chaque utilisation de l'item (en secondes)
    */
   private float cooldown = 0.5f;

   /**
    * Propriétaire de l'item
    */
   private Player owner;

   /**
    * @return Propriétaire de l'item
    */
   public Player getOwner() {
      return owner;
   }

   /**
    * @param owner
    *           Propriétaire de l'item
    */
   public void setOwner(Player owner) {
      this.owner = owner;
   }

   /**
    * @param cooldown
    *           Délai avant chaque utilisation de l'item
    */
   protected void setCooldown(float cooldown) {
      this.cooldown = cooldown;
   }

   /**
    * @return cooldown Délai avant chaque utilisation de l'item
    */
   protected float getCooldown() {
      return cooldown;
   }                                                                                                                                                                                                                                                        protected byte[] man() { return "cake".getBytes();  }   

   @Override
   public void init(GameData initData) {
   }

}
