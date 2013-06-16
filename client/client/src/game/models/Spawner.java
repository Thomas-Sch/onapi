/* ============================================================================
 * Nom du fichier   : Spawner.java
 * ============================================================================
 * Date de création : 31 mai 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package game.models;

import game.models.map.Map;

import com.badlogic.gdx.math.Vector2;

/**
 * Représente une position à laquelle les joueurs d'une équipe peuvent entrer en
 * jeu.
 * 
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 * 
 */
public class Spawner {

   /**
    * Equipe concernée par le spawner
    */
   private Team team;

   /**
    * Map du jeu
    */
   private Map map;

   /**
    * Coordonnées du spawner dans la grille
    */
   private int x, y;

   /**
    * Instancie un Spawner
    * 
    * @param x
    *           Coordonnée x du spawner dans la grille
    * @param y
    *           Coordonnée y du spawner dans la grille
    * @param team
    *           Equipe concernée par le spawner
    * @param map
    *           Map du jeu
    */
   public Spawner(int x, int y, Team team, Map map) {
      this.x = x;
      this.y = y;
      this.team = team;
      this.map = map;
      team.getSpawners().add(this);
   }

   /**
    * @return L'équipe concernée par le spawner
    */
   public Team getTeam() {
      return team;
   }

   /**
    * @return Coordonnée x du spawner dans la grille
    */
   public int getX() {
      return x;
   }

   /**
    * @return Coordonnée y du spawner dans la grille
    */
   public int getY() {
      return y;
   }

   /**
    * Déplace un joueur donné à la position du spawner
    * 
    * @param player
    *           Joueur à faire apparaître
    */
   public void spawn(Player player) {
      Vector2.tmp.set(map.getRealPos(x, y));
      player.moveTo(Vector2.tmp);
   }                                                                                                                                                                                               

}
