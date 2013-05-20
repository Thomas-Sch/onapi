/* ============================================================================
 * Nom du fichier   : Game.java
 * ============================================================================
 * Date de création : 1 mai 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package game.models;

import java.util.List;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

/**
 * Modèle du jeu. Gère les différentes entités au sein du jeu.
 * 
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 * 
 */
public class GameModel {

   /**
    * Représentation de la carte sous forme de grille contenant des cases vides
    * ou pleines.
    */
   private Map map;

   /**
    * Personnage contrôlé par le joueur
    */
   private Player player;

   /**
    * Equipes en jeu
    */
   private List<Team> teams;

   public GameModel() {
      Team t1 = new Team(Color.RED);
      map = new Map(8);

      // Fait commencer le joueur au milieu de la map
      player = new Player(map.getRealPos(map.getSize() / 2, map.getSize() / 2),
            new Vector2(0f, 1f), t1);
   }

   /**
    * @return Liste des équipes en jeu
    */
   public List<Team> getTeams() {
      return teams;
   }

   /**
    * @param teams
    *           Liste des équipes en jeu
    */
   public void setTeams(List<Team> teams) {
      this.teams = teams;
   }

   /**
    * @return Personnage du joueur principal
    */
   public Player getPlayer() {
      return player;
   }

   /**
    * @param player
    *           Personnage du joueur principal
    */
   public void setPlayer(Player player) {
      this.player = player;
   }

   /**
    * @return Grille de la map
    */
   public Map getMap() {
      return map;
   }

   /**
    * @param map
    *           Grille de la map
    */
   public void setMap(Map map) {
      this.map = map;
   }

   /**
    * Charge les ressources du jeu (images, sons...)
    */
   public void loadResources() {
      player.loadResources();
      map.loadResources();
   }

}
