/* ============================================================================
 * Nom du fichier   : Team.java
 * ============================================================================
 * Date de création : 8 mai 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package game.models;

import java.util.LinkedList;
import java.util.ListIterator;

import utils.ListUtils;

import com.badlogic.gdx.graphics.Color;

/**
 * Représente une équipe de joueurs
 * 
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 * 
 */
public class Team {

   /**
    * Couleur de l'équipe
    */
   private Color color;

   /**
    * Joueurs membres de l'équipe
    */
   private LinkedList<Player> members;

   /**
    * Points d'apparition des joueurs
    */
   private LinkedList<Spawner> spawners;

   /**
    * Crée une équipe d'une couleur donnée sans joueurs
    * 
    * @param color
    *           Couleur de l'équipe
    */
   public Team(Color color) {
      this(color, new LinkedList<Player>());
   }

   /**
    * Crée une équipe d'une couleur donnée sans joueurs
    * 
    * @param color
    *           Couleur de l'équipe
    * @param members
    *           Joueurs membres de l'équipe
    */
   public Team(Color color, LinkedList<Player> members) {
      super();
      this.color = color;
      this.members = members;
      this.spawners = new LinkedList<Spawner>();
   }

   /**
    * @return Couleur de l'équipe
    */
   public Color getColor() {
      return color;
   }

   /**
    * @return Couleur de l'équipe
    */
   public void setColor(Color color) {
      this.color = color;
   }

   /**
    * @return Liste des joueurs membres de l'équipe
    */
   public LinkedList<Player> getMembers() {
      return members;
   }

   /**
    * @param members
    *           Liste des joueurs membres de l'équipe
    */
   public void setMembers(LinkedList<Player> members) {
      this.members = members;
   }

   /**
    * @return La liste des points d'apparition actuellement utilisables par
    *         l'équipe
    */
   public LinkedList<Spawner> getSpawners() {
      return spawners;
   }

   /**
    * Déplace les membres de l'équipe en les plaçant aléatoirement sur les
    * points d'apparition à disposition de l'équipe. Il peut ne pas y avoir de
    * joueur à la fin sur une des positions, comme il peut y en avoir plusieurs.
    */
   public void spawnPlayers() {
      LinkedList<Spawner> spawns = ListUtils.shuffled(spawners);
      ListIterator<Spawner> spawnIt = spawns.listIterator();
      Spawner currentSpawner;
      for (Player p : members) {
         if (!spawnIt.hasNext()) {
            spawnIt = spawns.listIterator();
            currentSpawner = spawns.get(0);
         }
         else {
            currentSpawner = spawnIt.next();
         }
         currentSpawner.spawn(p);
      }
   }

}
