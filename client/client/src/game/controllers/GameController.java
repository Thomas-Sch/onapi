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

   public boolean isCollidingWithWall(float moveSpeedX, float moveSpeedY) {

      int playerCaseX = (int) Math.floor((game.getPlayer().getX() / Tile.WIDTH));
      int playerCaseY = (int) Math.floor(game.getMap().getGrid().length
            - (int) (game.getPlayer().getY() / Tile.WIDTH));
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
      System.out.println("Case : " + playerCaseX + " " + playerCaseY);
      int k = 0;
      for (Tile[] blocks : game.getMap().getGrid()) {
         int l = 0;
         for (Tile block : blocks) {
            if (k == playerCaseY && l == playerCaseX)
               System.out.print("p");
            else if (block == Tile.WALL)
               System.out.print("#");
            else {
               System.out.print(" ");
            }
            l++;
         }
         k++;
         System.out.println();
      }

      // Test les collisions possible
      // LEFT 

      boolean collision = false;
      Map map = game.getMap();
      Player player = game.getPlayer();
      for (int i = playerCaseY - 1; i < playerCaseY; ++i)
         for (int j = playerCaseX - 1; j < playerCaseX; ++j)
            if (i != playerCaseY && j != playerCaseX) {
               player.getRectangle().x += moveSpeedX;
               player.getRectangle().y += moveSpeedY;
               if (map.getGrid()[i][j] == Tile.WALL
                     && Intersector.overlapRectangles(player.getRectangle(),
                           map.getRectangle(i, j))) collision = true;
               player.getRectangle().x -= moveSpeedX;
               player.getRectangle().y -= moveSpeedY;
               if (collision) return false;
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

      if (getActionState(Action.UP)) {
         if (!isCollidingWithWall(0, +moveSpeed))
            game.getPlayer().move(new Vector2(0, +moveSpeed));
      }
      if (getActionState(Action.DOWN)) {
         if (!isCollidingWithWall(0, -moveSpeed))
            game.getPlayer().move(new Vector2(0, -moveSpeed));
      }
      if (getActionState(Action.RIGHT)) {
         if (!isCollidingWithWall(moveSpeed, 0))
            game.getPlayer().move(new Vector2(+moveSpeed, 0));
      }
      if (getActionState(Action.LEFT)) {
         if (!isCollidingWithWall(-moveSpeed, 0))
            game.getPlayer().move(new Vector2(-moveSpeed, 0));
      }

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
