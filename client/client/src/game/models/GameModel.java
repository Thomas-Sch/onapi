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

import game.items.Bullet;
import game.items.bonus.DefaultBonus;
import game.items.skills.DefaultSkill;
import game.items.weapons.DefaultWeapon;
import game.models.map.Map;
import game.models.map.Tile;

import java.util.LinkedList;
import java.util.List;

import box2dLight.RayHandler;
import client.GameData;

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

   private final int EXIT_HEIGHT = 127;
   private final int EXIT_WIDTH = 85;

   private static final Vector2 GRAVITY = new Vector2(0, 0);

   /**
    * Observable pour les événements qui méritent d'être synchronisés
    */
   public class Updater {
      List<GameListener> listeners = new LinkedList<GameListener>();

      public void addListener(GameListener listener) {
         listeners.add(listener);
      }

      protected void notifyPlayerMove(Player player) {
         for (GameListener gl : listeners) {
            gl.onPlayerMove(GameModel.this, player);
         }
      }

      protected void notifyPlayerHit(Player player) {
         for (GameListener gl : listeners) {
            gl.onPlayerHit(GameModel.this, player);
         }
      }

      protected void notifyShoot(Player sender, Vector2 from, Vector2 dir) {
         for (GameListener gl : listeners) {
            gl.onFire(GameModel.this, sender, from, dir);
         }
      }

      protected void notifyTorch(Player player) {
         for (GameListener gl : listeners) {
            gl.onTorch(GameModel.this, player);
         }
      }

      protected void notifyPlayerOut(Player player) {
         for (GameListener gl : listeners) {
            gl.onPlayerOut(GameModel.this, player);
         }
      }

   }

   public final Updater updates = new Updater();

   private World world;

   private GameData initData;

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
   private MainPlayer player;

   /**
    * Equipes en jeu
    */
   private Team[] teams;

   private RayHandler rayHandler;

   private boolean isLightingActive = true;

   public GameModel(GameData initData) {
      this.initData = initData;
      initData.setGame(this);
   }

   public void init() {
      world = new World(GRAVITY, false);
      rayHandler = new RayHandler(world);

      teams = new Team[] { new Team(Color.BLUE), new Team(Color.RED) };
      map = new Map(this, teams);

      entities.addActor(map);
      entities.addActor(new Exit(this, EXIT_HEIGHT, EXIT_WIDTH,
            map.getExitPos().x, map.getExitPos().y));

      // Fait commencer le joueur au milieu de la map
      player = new MainPlayer(map.getRealPos(0, 0), new Vector2(0f, 1f),
            teams[0], new DefaultWeapon(this), new DefaultSkill(this),
            new DefaultBonus(this), this);
      player.getWeapon().createBullet(entities);
      entities.addActor(player);

      // Ajoute d'autres joueurs
      for (int i = 0; i < 14; i++) {
         Player other = new Player(map.getRealPos(0, 0), new Vector2(-1f, -1f),
               teams[0], new DefaultWeapon(this), new DefaultSkill(this),
               new DefaultBonus(this), this);
         other.getWeapon().createBullet(entities);

         entities.addActor(other);
      }
      for (int i = 0; i < 15; i++) {
         Player other = new Player(map.getRealPos(0, 0), new Vector2(-1f, -1f),
               teams[1], new DefaultWeapon(this), new DefaultSkill(this),
               new DefaultBonus(this), this);
         entities.addActor(other);
         other.getWeapon().createBullet(entities);
      }

      // Fait "spawner" (apparaitre) les joueurs sur la carte, autrement dit,
      // leur donne à chaque une position initiale
      for (Team t : teams) {
         t.spawnPlayers();
      }

      // Créer les contact listener
      createCollisionListener();

      for (Actor e : entities.getChildren()) {
         ((Entity) e).init(initData);
      }
   }

   private void createCollisionListener() {
      world.setContactListener(new ContactListener() {

         @Override
         public void preSolve(Contact contact, Manifold oldManifold) {
         }

         @Override
         public void postSolve(Contact contact, ContactImpulse impulse) {
         }

         @Override
         public void endContact(Contact contact) {
         }

         @Override
         public void beginContact(Contact contact) {
            Fixture a = contact.getFixtureA();
            Fixture b = contact.getFixtureB();

            if (a.getBody() != null && b.getBody() != null) {

               if (a.getBody().getUserData() != null
                     && b.getBody().getUserData() != null) {
                  Object userdataA = a.getBody().getUserData();
                  Object userdataB = b.getBody().getUserData();
                  if (userdataA != null && userdataB != null
                        && userdataA instanceof Bullet
                        && userdataB instanceof Player)
                     ((Bullet) userdataA).onHit((Player) userdataB);

                  else if (a.getBody().getUserData() != null
                        && b.getBody().getUserData() != null
                        && userdataB instanceof Bullet
                        && userdataA instanceof Player)
                     ((Bullet) userdataB).onHit((Player) userdataA);

                  else if (a.getBody().getUserData() != null
                        && b.getBody().getUserData() != null
                        && a.getBody().getUserData() instanceof Bullet
                        && b.getBody().getUserData() instanceof Tile)
                     ((Bullet) a.getBody().getUserData()).deactivate();
                  else if (a.getBody().getUserData() != null
                        && b.getBody().getUserData() != null
                        && b.getBody().getUserData() instanceof Bullet
                        && a.getBody().getUserData() instanceof Tile)
                     ((Bullet) b.getBody().getUserData()).deactivate();
                  else if (a.getBody().getUserData() != null
                        && b.getBody().getUserData() != null
                        && b.getBody().getUserData() instanceof Player
                        && a.getBody().getUserData() instanceof Exit)
                     player.setOut(true);
                  else if (a.getBody().getUserData() != null
                        && b.getBody().getUserData() != null
                        && b.getBody().getUserData() instanceof Exit
                        && a.getBody().getUserData() instanceof Player)
                     player.setOut(true);
               }
            }
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

   public void getPlayerById(int playerId) {
   };

   public void firePlayer(int player_id, float posXShoot, float posYShoot,
         Vector2 dir) {
   };

   public void AddKillMessage(String message) {
   }

   /**
    * @param player
    *           Personnage du joueur principal
    */
   public void setPlayer(Player player) {
      this.player = new MainPlayer(player);
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
      player.moveTo(map.getExitPos());
   }

   /**
    * @return the isLightingActive
    */
   public boolean isLightingActive() {
      return isLightingActive;
   }

   /**
    * @param isLightingActive
    *           the isLightingActive to set
    */
   public void setLightingActive(boolean isLightingActive) {
      this.isLightingActive = isLightingActive;
   }

}
