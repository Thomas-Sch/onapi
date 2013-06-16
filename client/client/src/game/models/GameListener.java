/* ============================================================================
 * Nom du fichier   : GameListener.java
 * ============================================================================
 * Date de création : 16 juin 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package game.models;

import com.badlogic.gdx.math.Vector2;

/**
 * TODO
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 *
 */
public interface GameListener {

   /**
    * @param gameModel
    * @param player
    */
   void onPlayerMove(GameModel gameModel, Player player);

   /**
    * @param gameModel
    * @param sender
    * @param from
    * @param dir
    */
   void onFire(GameModel gameModel, Player sender, Vector2 from, Vector2 dir);

   /**
    * @param gameModel
    * @param player
    */
   void onPlayerHit(GameModel gameModel, Player player);

   /**
    * @param gameModel
    * @param player
    */
   void onTorch(GameModel gameModel, Player player);

   /**
    * @param gameModel
    * @param player
    */
   void onPlayerOut(GameModel gameModel, Player player);

}
