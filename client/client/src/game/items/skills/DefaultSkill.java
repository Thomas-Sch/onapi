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

import com.badlogic.gdx.graphics.Color;

import game.items.Skill;
import game.models.Player;

/**
 * TODO
 * 
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 * 
 */
public class DefaultSkill extends Skill {

   private float lastUpdate = 0f;
   private int distanceMax = 600;
   private float distance = 0f;

   public DefaultSkill() {
      setActive(false);
   }

   @Override
   public void update(float deltaTime) {
      if (isActive()) {
         float current = lastUpdate + deltaTime;
         if (lastUpdate == 0f || current > 0.5) {
            for (int i = 0; i < Math.floor(current / 0.5);) {
               if (distance >= distanceMax) {
                  deactivate();
                  break;
               }
               else {
                  distance += 0.1;
                  getOwner().getPointLight().setDistance(distance);
               }
            }
         }
         lastUpdate = current;
      }
   }

   public void deactivate() {
      distance = 0;
      lastUpdate = 0;
      getOwner().getPointLight().setDistance(Player.PL_DISTANCE_DEFAULT);
      getOwner().getPointLight().setColor(Player.HALO_COLOR);
      setActive(false);
   }

   public void activate() {
      if (!isActive()) {
         setActive(true);
         getOwner().getPointLight().setColor(getOwner().getTeam().getColor());
      }
   }

}
