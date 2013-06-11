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

import java.util.LinkedList;
import java.util.Random;

import game.items.Bullet;
import game.items.bonus.DefaultBonus;
import game.items.skills.DefaultSkill;
import game.items.weapons.DefaultWeapon;
import game.models.map.Map;
import game.models.map.Tile;
import box2dLight.RayHandler;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;

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

   public static final float WORLD_TO_SCREEN = 100f;
   public static final float SCREEN_TO_WORLD = 1f / WORLD_TO_SCREEN;

   private static final Vector2 GRAVITY = new Vector2(0, 0);

   private World world;

   /**
    * Stocke les entités de la scène. Attention, l'ordre d'insertion correspond
    * à l'ordre d'affichage : le premier élément inséré sera en arrière-plan, le
    * dernier en avant-plan.
    * 
    */
   private Group entities = new Group();

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
   private Team[] teams;

   private RayHandler rayHandler;

   public GameModel() {
      world = new World(GRAVITY, false);
      rayHandler = new RayHandler(world);

      teams = new Team[] { new Team(Color.BLUE), new Team(Color.RED) };
      map = new Map(world, teams);

      entities.addActor(map);

      // Fait commencer le joueur au milieu de la map
      player = new Player(Map.getRealPos(0, 0), new Vector2(0f, 1f), teams[0],
            new DefaultWeapon(), new DefaultSkill(), new DefaultBonus(), world,
            rayHandler);
      player.getWeapon().createBullet(world, entities, rayHandler);
      entities.addActor(player);

      // Ajoute d'autres joueurs
      for (int i = 0; i < 14; i++) {
         Player other = new Player(Map.getRealPos(0, 0), new Vector2(-1f, -1f),
               teams[0], new DefaultWeapon(), new DefaultSkill(),
               new DefaultBonus(), world, rayHandler);
         other.getWeapon().createBullet(world, entities, rayHandler);

         entities.addActor(other);
      }
      for (int i = 0; i < 15; i++) {
         Player other = new Player(Map.getRealPos(0, 0), new Vector2(-1f, -1f),
               teams[1], new DefaultWeapon(), new DefaultSkill(),
               new DefaultBonus(), world, rayHandler);
         entities.addActor(other);
         other.getWeapon().createBullet(world, entities, rayHandler);
      }

      // Fait "spawner" (apparaitre) les joueurs sur la carte, autrement dit,
      // leur donne à chaque une position initiale
      for (Team t : teams) {
         t.spawnPlayers();
      }

      // Créer les contact listener
      createCollisionListener();

   }

   private void createCollisionListener() {
      world.setContactListener(new ContactListener() {

         @Override
         public void preSolve(Contact contact, Manifold oldManifold) {
            // TODO Auto-generated method stub
            Fixture a = contact.getFixtureA();
            Fixture b = contact.getFixtureB();
            if(a.getUserData()!=null && b.getUserData()!= null){
               System.out.println("Objet de type " + a.getUserData() + " avec " + b.getUserData());
               if (a.getUserData() != null && b.getUserData() != null
                     && a.getUserData() instanceof Bullet
                     && b.getUserData() instanceof Player)
                  ((Bullet) a.getUserData()).onHit((Player) b.getUserData());
   
               else if (a.getUserData() != null && b.getUserData() != null
                     && b.getUserData() instanceof Bullet
                     && a.getUserData() instanceof Player)
                  ((Bullet) b.getUserData()).onHit((Player) a.getUserData());
               else if (a.getUserData() != null && b.getUserData() != null
                     && a.getUserData() instanceof Bullet
                     && b.getUserData() instanceof Tile)
                  ((Bullet) a.getUserData()).deactivate();
               else if (a.getUserData() != null && b.getUserData() != null
                     && b.getUserData() instanceof Bullet
                     && a.getUserData() instanceof Tile)
                  ((Bullet) b.getUserData()).deactivate();
            }
         }
         @Override
         public void postSolve(Contact contact, ContactImpulse impulse) {
            // TODO Auto-generated method stub

         }

         @Override
         public void endContact(Contact contact) {
            // TODO Auto-generated method stub

         }

         @Override
         public void beginContact(Contact contact) {

         }
      });
   }

   /**
    * @return Liste des équipes en jeu
    */
   public Team[] getTeams() {
      return teams;
   }

   /**
    * @param teams
    *           Liste des équipes en jeu
    */
   public void setTeams(Team[] teams) {
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
      for (Actor e : entities.getChildren()) {
         ((Entity) e).loadResources();
      }
   }

   /**
    * @return Le "world" du moteur physique
    */
   public World getWorld() {
      return world;
   }

   /**
    * @return Gestionnaire de lancers de rayons
    */
   public RayHandler getRayHandler() {
      return rayHandler;
   }

   public Group getEntities() {
      return entities;
   }

   public void executeDevCheat() {
      System.out.println("CHEAT");
      LinkedList<Player> ennemies = teams[1].getMembers();
      Player target = ennemies.get(new Random().nextInt(ennemies.size()));
      player.getWeapon().onHit(target);
   }

}
