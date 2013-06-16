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
 * Spécifie les méthodes à implémenter pour pouvoir s'abonner aux changements du
 * modèle de jeu.
 * 
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 * 
 */
public interface GameListener {

   /**
    * Lorsqu'un joueur change de position ou de direction.
    * 
    * @param gameModel
    *           Modèle de jeu
    * @param player
    *           Joueur modifié
    */
   void onPlayerMove(GameModel gameModel, Player player);

   /**
    * Lorsqu'un joueur tire
    * 
    * @param gameModel
    *           Modèle de jeu
    * @param sender
    *           Joueur ayant tiré
    * @param from
    *           Origine du tir
    * @param dir
    *           Direction du tir
    */
   void onFire(GameModel gameModel, Player sender, Vector2 from, float angle);

   /**
    * Lorsqu'un joueur reçoit des dommages
    * 
    * @param gameModel
    *           Modèle de jeu
    * @param player
    *           Joueur concerné
    */
   void onPlayerHit(GameModel gameModel, Player player);

   /**
    * Lorsqu'un joueuer éteint ou allume sa lampe torche
    * 
    * @param gameModel
    *           Modèle de jeu
    * @param player
    *           Joueur concerné
    */
   void onTorch(GameModel gameModel, Player player);

   /**
    * Lorsqu'un joueur sort du labyrinthe
    * 
    * @param gameModel
    *           Modèle de jeu
    * @param player
    *           Joueur concerné
    */
   void onPlayerOut(GameModel gameModel, Player player);

}
