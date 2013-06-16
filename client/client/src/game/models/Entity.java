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

import client.GameData;

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

   private final GameModel game;

   protected Entity(GameModel game) {
      this.game = game;
   }
   
   @Override
   public void debugRender(ShapeRenderer renderer) {
   }

   public void loadResources() {
   }
   
   abstract public void init(GameData initData);
   
   abstract public void update(float deltaTime);

   /**
    * @return the game
    */
   public GameModel getGame() {
      return game;
   }

}
