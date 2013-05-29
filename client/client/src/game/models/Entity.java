/* ============================================================================
 * Nom du fichier   : Entity.java
 * ============================================================================
 * Date de création : 15 mai 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package game.models;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Représente une entité du graphe de scène. Une telle entité est capable de
 * s'afficher à l'écran.
 * 
 * 
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 * 
 */
public abstract class Entity extends Actor implements Debuggable {

   @Override
   public void debugRender(ShapeRenderer renderer) {
   }

   public void loadResources() {
   }
   
   abstract public void update(float deltaTime);

}
