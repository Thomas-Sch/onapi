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

   /**
    * Charge les ressources (fichiers images, sons, etc.) nécessaire à l'entité.
    * 
    * Appelé à l'initialisation du jeu
    */
   public void loadResources() {
   }

   /**
    * Initialise l'objet. Appelé au lancement de la fenêtre graphique.
    * 
    * @param initData
    *           Données d'initialisation
    */
   abstract public void init(GameData initData);

   /**
    * Met à jour l'état de l'objet.
    * 
    * @param deltaTime
    *           Temps écoulé depuis le dernier update-
    */
   abstract public void update(float deltaTime);

   /**
    * @return Modèle du jeu
    */
   public GameModel getGame() {
      return game;
   }

}
