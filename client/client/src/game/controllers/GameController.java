/* ============================================================================
 * Nom du fichier   : GameController.java
 * ============================================================================
 * Date de création : 1 mai 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package game.controllers;

import game.models.GameModel;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.math.Vector2;

/**
 * Traite les entrées de l'utilisateur, les fait valider auprès du serveur puis
 * les envoie au modèle pour que celui-ci les applique dans le jeu.
 * 
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 * 
 */
public class GameController {

   /**
    * Modèle du jeu à contrôler
    */
   private GameModel game;

   /**
    * Représente les diverses actions demandées par l'utilisateur
    * 
    * @author Crescenzio Fabio
    * @author Decorvet Grégoire
    * @author Jaquier Kevin
    * @author Schweizer Thomas
    * 
    */
   public static enum Action {
      /**
       * Déplacement vers le haut
       */
      UP,

      /**
       * Déplacement vers le bas
       */
      DOWN,

      /**
       * Déplacement vers la gauche
       */
      LEFT,

      /**
       * Déplacement vers la droite
       */
      RIGHT,

      /**
       * Rotation gauche
       */
      TURN_LEFT,

      /**
       * Rotation droite
       */
      TURN_RIGHT,

      /**
       * Tir avec l'arme
       */
      FIRE,

      /**
       * Activation/Désactivation d'une compétence
       */
      SKILL,

      /**
       * Activation/Désactivation de la lampe torche
       */
      LAMP;
   }

   /**
    * Table donnant l'état de chaque action (toutes à false au début)
    */
   private Map<Action, Boolean> keys = new HashMap<Action, Boolean>();
   {
      for (Action act : Action.values()) {
         keys.put(act, false);
      }
   };

   /**
    * @param game
    *           Modèle du jeu en lui-même
    */
   public GameController(GameModel game) {
      this.game = game;
   }

   /**
    * Change l'état (activé => true, désactivé => false) d'une action
    * 
    * @param action
    *           Action concernée
    * @param state
    *           Nouvel état de l'action
    */
   public void setActionState(Action action, Boolean state) {
      keys.get(keys.put(action, state));
   }

   /**
    * Obtient l'état actuel d'une action
    * 
    * @param action
    *           Action concernée
    * @return Etat actuel de l'action
    */
   public boolean getActionState(Action action) {
      return keys.get(action).booleanValue();
   }

   /**
    * Méthode de mise à jour de la logique de jeu
    * 
    * @param delta
    *           Différence de temps depuis le dernier update
    */
   public void update(float delta) {
      if (getActionState(Action.UP)) {
         game.getPlayer().move(new Vector2(0, +0.1f));
      }
      if (getActionState(Action.DOWN)) {
         game.getPlayer().move(new Vector2(0, -0.1f));
      }
      if (getActionState(Action.RIGHT)) {
         game.getPlayer().move(new Vector2(+0.1f, 0));
      }
      if (getActionState(Action.LEFT)) {
         game.getPlayer().move(new Vector2(-0.1f, 0));
      }

   }

}
