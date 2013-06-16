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

import game.models.GameModel;
import game.models.Player;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Group;

/**
 * Définit un arme d'un joueur.
 * 
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 * 
 */
public abstract class Weapon extends Item {

   protected Weapon(GameModel game) {
      super(game);
   }

   // Moment du dernier tir
   private float previousShootTime = 0;
   // Temps actuel
   private float currentTime = getCooldown() + 1.0f;

   // Projectile utilisé par l'arme
   protected Projectile bullet;

   /**
    * Lorsque le projectile d'une arme touche une cible
    * 
    * @param target
    *           Joueur touché
    */
   public abstract void onHit(Player target);

   /**
    * Affiche le projectile
    * 
    * @param batch
    *           Permet de dessiner des textures et des sprites à l'écran
    * @param parentAlpha
    *           Composante alpha à utiliser pour l'affichage
    */
   public abstract void drawProjectile(SpriteBatch batch, float parentAlpha);

   /**
    * Lorsqu'un tir est effectué avec l'arme. Les informations nécessaires pour
    * connaître l'origine du tir et sa direction peuvent être récupérées par
    * l'état du propriétaires de l'arme, récupérable avec
    * {@link Weapon#getOwner()}.
    */
   protected abstract void onShoot();

   /**
    * Demande à l'arme de tirer. Le tir s'effectue seulement si le temps de
    * cooldown de l'arme est écoulé.
    * 
    * @param deltaTime
    *           Temps écoulé depuis le dernier update
    */
   public void shoot(float deltaTime) {
      currentTime += deltaTime;
      if (!this.bullet.isActive()) {
         if (currentTime > previousShootTime + getCooldown()) {
            onShoot();
            previousShootTime = currentTime;
         }
      }
   }

   @Override
   public void update(float deltaTime) {
   }

   /**
    * Crée un projectile correspondant à l'arme
    * 
    * @param group
    *           Group d'actors dans lequel ajouter le projectile
    */
   public abstract void createBullet(Group group);

}
