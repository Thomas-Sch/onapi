/* ============================================================================
 * Nom du fichier   : InputController.java
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

import game.models.Entity;
import game.models.GameModel;
import game.models.Player;
import game.models.map.Map;
import game.models.map.Tile;

import java.util.HashMap;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

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
public class InputController {

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
      TORCH,

      /**
       * Commande spéciale pour le debug
       */
      DEV_CHEAT;
   }

   /**
    * Table donnant l'état de chaque action (toutes à false au début)
    */
   private java.util.Map<Action, Boolean> keys = new HashMap<Action, Boolean>();
   {
      for (Action act : Action.values()) {
         keys.put(act, false);
      }
   };

   /**
    * @param game
    *           Modèle du jeu en lui-même
    */
   public InputController(GameModel game) {
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
   
   @SuppressWarnings("unused")
   public boolean isCollidingWithWall(float moveSpeedX, float moveSpeedY) {
      int n = game.getMap().getGrid().length;
      int playerCaseX = 1 + (int) Math
            .floor((game.getPlayer().getX() - 0.5f * Tile.WIDTH) / Tile.WIDTH);
      int playerCaseY = 1 + (int) Math
            .floor((game.getPlayer().getY() - 0.5f * Tile.HEIGHT) / Tile.HEIGHT);

      System.out.println("CASE : " + playerCaseY + " " + playerCaseX);
      for (int i = playerCaseY - 1; i <= playerCaseY + 1; i++) {
         for (int j = playerCaseX - 1; j <= playerCaseX + 1; j++) {
            System.out.println(i + " " + j);
            if (i > 0 && i < game.getMap().getGrid().length && j > 0
                  && j < game.getMap().getGrid().length
                  && !(i == playerCaseY && playerCaseX == j)) {
               game.getPlayer().getRectangle().x += moveSpeedX;
               game.getPlayer().getRectangle().x += moveSpeedY;
               System.out.println("Test du rectangle "
                     + game.getPlayer().getRectangle().getX() + " "
                     + game.getPlayer().getRectangle().getY() + " avec "
                     + game.getMap().getRectangle(i, j).getX() + " "
                     + game.getMap().getRectangle(i, j).getY());
               System.out.println("Fin rectangle:"
                     + game.getMap().getRectangle(i, j).width);
               if (Intersector.overlapRectangles(game.getMap().getRectangle(i, j), game.getPlayer()
                     .getRectangle())) {
                  return true;
               }
               game.getPlayer().getRectangle().x -= moveSpeedX;
               game.getPlayer().getRectangle().x -= moveSpeedY;
            }
         }
      }
      return false;
   }

   /**
    * Méthode de mise à jour de la logique de jeu
    * 
    * @param delta
    *           Différence de temps (en secondes) depuis le dernier update
    */
   public void update(float delta) {
      float moveSpeed = 10.0f;

      Vector2.tmp.set(0, 0);
      
      synchronized (game.getPlayer()) {
         
      if (getActionState(Action.UP)) {
         if (!isCollidingWithWall(0, +moveSpeed)) Vector2.tmp.y += moveSpeed;
      }
      if (getActionState(Action.DOWN)) {
         if (!isCollidingWithWall(0, -moveSpeed)) Vector2.tmp.y -= moveSpeed;
      }
      if (getActionState(Action.RIGHT)) {
         if (!isCollidingWithWall(+moveSpeed, 0)) Vector2.tmp.x += moveSpeed;
      }
      if (getActionState(Action.LEFT)) {
         if (!isCollidingWithWall(-moveSpeed, 0)) Vector2.tmp.x -= moveSpeed;
      }

      if (Vector2.tmp.x != 0 || Vector2.tmp.y != 0)
         game.getPlayer().move(Vector2.tmp);
      }

      if (getActionState(Action.TORCH)) {
         game.getPlayer().toggleTorch();
         setActionState(InputController.Action.TORCH, false);
      }
      if (getActionState(Action.FIRE)) {
         game.getPlayer().shoot(delta);
      }
      
      if (getActionState(Action.SKILL)) {
         game.getPlayer().getSkill().activate();
      }

      for (Actor e : game.getEntities().getChildren()) {
         ((Entity) e).update(delta);
      }
   }

}
