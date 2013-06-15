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
import game.models.map.Map;
import game.models.map.Tile;

import java.util.HashMap;

import com.badlogic.gdx.math.Rectangle;
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

   public boolean isCollidingWithWall(Action direction, float moveSpeedX,
         float moveSpeedY) {
      Map map = game.getMap();
      Tile[][] grid = map.getGrid();
      int n = grid.length;
      moveSpeedX = moveSpeedX * 3 + 3;
      moveSpeedY = moveSpeedY * 3 + 3;

      // Position du joueur dans la grille
      int pX = (int) Math.floor(game.getPlayer().getX() / Tile.WIDTH);
      int pY = n - 1 - (int) Math.floor(game.getPlayer().getY() / Tile.HEIGHT);

      Rectangle nextRectPlayer = new Rectangle(game.getPlayer().getX()
            + moveSpeedX - (game.getPlayer().getWidth() + 10) / 2, game
            .getPlayer().getY()
            + moveSpeedY
            - (game.getPlayer().getWidth() + 10) / 2, 50, 50);

      for (int i = pY - 1; i <= pY + 1; i++) {
         for (int j = pX - 1; j <= pX + 1; j++) {
            if (i > 0 && i < game.getMap().getGrid().length && j > 0
                  && j < game.getMap().getGrid().length
                  && !(i == pY && pX == j)) {
               if (map.getGrid()[i][j] == Tile.WALL) {
                  // Teste les collisions entre le joueur et les murs à tester
                  if (nextRectPlayer.x == map.getWallBounds(i, j).x
                        || nextRectPlayer.y == map.getWallBounds(i, j).y
                        || nextRectPlayer.overlaps(map.getWallBounds(i, j))) {
                     return true;
                  }
               }
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

      if (getActionState(Action.UP)) {
         if (!isCollidingWithWall(Action.UP, 0, +moveSpeed))
            Vector2.tmp.y += moveSpeed;
      }
      if (getActionState(Action.DOWN)) {
         if (!isCollidingWithWall(Action.DOWN, 0, -moveSpeed))
            Vector2.tmp.y -= moveSpeed;
      }
      if (getActionState(Action.RIGHT)) {
         if (!isCollidingWithWall(Action.RIGHT, +moveSpeed, 0))
            Vector2.tmp.x += moveSpeed;
      }
      if (getActionState(Action.LEFT)) {
         if (!isCollidingWithWall(Action.LEFT, -moveSpeed, 0))
            Vector2.tmp.x -= moveSpeed;
      }

      if (Vector2.tmp.x != 0 || Vector2.tmp.y != 0) {
         synchronized (game.getPlayer()) {
            game.getPlayer().move(Vector2.tmp);
         }
      }

      // {
      // int x = 1 + (int) Math
      // .floor((game.getPlayer().getX() - 0.5f * Tile.WIDTH) / Tile.WIDTH);
      // int y = (int) Math
      // .floor((game.getPlayer().getY() - 0.5f * Tile.HEIGHT) / Tile.HEIGHT);
      // if(game.getMap().getGrid()[y][x] == Tile.EXIT){
      // System.out.println("Le player vient de sortir !!");
      // }
      //
      // }
      if (getActionState(Action.TORCH)) {
         game.getPlayer().toggleTorch();
         setActionState(Action.TORCH, false);
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
