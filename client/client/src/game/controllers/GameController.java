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

   public boolean isCollidingWithWall(float moveSpeed) {

      int n = game.getMap().getGrid().length;
      int playerCaseX = 1 + (int) Math.floor((game.getPlayer().getX() - 0.5f * Tile.WIDTH) / Tile.WIDTH);
      int playerCaseY = 1 + (int) Math.floor((game.getPlayer().getY() - 0.5f * Tile.HEIGHT) / Tile.HEIGHT);
      
      //game.getMap().getGrid()[n - playerCaseY][playerCaseX] = Tile.EXIT;
      
      //
      // int playerPosX = (int) (game.getPlayer().getX() + moveSpeedX);
      // int playerPosY = (int) (game.getPlayer().getY() + moveSpeedY);
      //
      // // test les limites de la map
      // if (playerPosX < 0 || playerPosY < 0
      // || playerPosX > game.getMap().getGrid().length * Tile.WIDTH
      // || playerPosY > game.getMap().getGrid().length * Tile.HEIGHT)
      // return true;

      // test si le joueur se trouve pret d'un mur, autrement la verification
      // n'est pas necessaire
      // if ((playerPosX % Tile.WIDTH) / Tile.WIDTH > moveSpeedX || (playerPosY
      // % Tile.HEIGHT) / Tile.HEIGHT > moveSpeedY)
      // return false;
      //
//      System.out.println("Case : " + playerCaseX + " " + playerCaseY);
//      int k = 0;
////      for (Tile[] blocks : game.getMap().getGrid()) {
////         int l = 0;
////         for (Tile block : blocks) {
////            if (k == playerCaseY && l == playerCaseX)
////               System.out.print("p");
////            else if (block == Tile.WALL)
////               System.out.print("#");
////            else {
////               System.out.print(" ");
////            }
////            l++;
////         }
////         k++;
////         System.out.println();
////      }

      // Test les collisions possible
      // LEFT 

      boolean collision = false;
      Map map = game.getMap();
      Player player = game.getPlayer();
      for (int i = playerCaseX - 1; i <= playerCaseX + 1; ++i) {
         for (int j = playerCaseY - 1; j <= playerCaseY + 1; ++j) {
            if (i != playerCaseX || j != playerCaseY) {
               //map.getGrid()[n-j][i] = Tile.EXIT;
               player.getBounds().x += moveSpeed;
               player.getBounds().y += moveSpeed;
               if (map.getGrid()[n-j][i] == Tile.WALL) {
                  System.out.println("TEST COLLISION");
                  System.out.println(player.getBounds().x + " =?= " + map.getBounds(n-j, i).x);
                  collision = Intersector.overlapRectangles(player.getBounds(),
                              map.getBounds(n-j, i));
               }
               player.getBounds().x -= moveSpeed;
               player.getBounds().y -= moveSpeed;
               if (collision) return false;
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
      float moveSpeed = 3.0f;

      Vector2.tmp.set(0, 0);
      if (getActionState(Action.UP)) {
         Vector2.tmp.y += moveSpeed;
      }
      if (getActionState(Action.DOWN)) {
         Vector2.tmp.y -= moveSpeed;
      }
      if (getActionState(Action.RIGHT)) {
         Vector2.tmp.x += moveSpeed;
      }
      if (getActionState(Action.LEFT)) {
         Vector2.tmp.x -= moveSpeed;
      }
      if ((Vector2.tmp.x != 0 || Vector2.tmp.y != 0) && !isCollidingWithWall(moveSpeed))
         game.getPlayer().move(Vector2.tmp);

      if (getActionState(Action.TORCH)) {
         game.getPlayer().toggleTorch();
         setActionState(GameController.Action.TORCH, false);
      }
      if (getActionState(Action.FIRE)) {
         game.getPlayer().shoot(delta);
      }

      for (Actor e : game.getEntities().getChildren()) {
         ((Entity) e).update(delta);
      }
   }

}
