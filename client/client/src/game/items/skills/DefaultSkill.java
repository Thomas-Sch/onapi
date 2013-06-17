/* ============================================================================
 * Nom du fichier   : DefaultSkill.java
 * ============================================================================
 * Date de création : 2 juin 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package game.items.skills;

import game.items.Skill;
import game.models.GameModel;
import box2dLight.PointLight;

import com.badlogic.gdx.graphics.Color;

/**
 * Compétence par défaut. Fait apparaître un puissant halo éblouissant autour du
 * joueur.
 * 
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 * 
 */
public class DefaultSkill extends Skill {

   private static final Color EFFECT_COLOR = new Color(4242f, 4242f, 4242f, 1);
   private static final float UPDATE_RATE = 1f / 60f;
   private static final int DISTANCE_MAX = 1500;
   private static final float EFFECT_DURATION = 5.0f;
   private static final float INCREMENT = DISTANCE_MAX
         / (EFFECT_DURATION / UPDATE_RATE);
   private static final int USE_LIMIT = 2;

   private float lastUpdate = 0f;
   private float currentDist = 0f;
   private PointLight pl;
   private int used = 0;

   public DefaultSkill(GameModel game) {
      super(game);
      setCooldown(10f);
      setActive(false);
   }

   @Override
   public void update(float deltaTime) {
      if (isActive()) {
         if (!getOwner().isInGame()) {
            deactivate();
         }

         float currentTime = lastUpdate + deltaTime;
         if (currentTime > lastUpdate + UPDATE_RATE) {
            currentDist += INCREMENT;
            pl.setDistance(currentDist);
            if (currentDist >= DISTANCE_MAX) {
               deactivate();
            }
         }
         lastUpdate = currentTime;
      }
   }

   /**
    * Désactive la compétence
    */
   public void deactivate() {
      currentDist = 0;
      lastUpdate = 0;
      pl.setActive(false);
      setActive(false);
   }

   /**
    * Active la compétence
    */
   public void activate() {
      if (!isActive()) {
         if (used < USE_LIMIT) {
            used++;
            setActive(true);
            pl = new PointLight(getGame().getRayHandler(), 50, EFFECT_COLOR, 0,
                  0, 0);
            pl.attachToBody(getOwner().getBody(), 0, 0);
            pl.setActive(true);
         }
      }
   }

}
