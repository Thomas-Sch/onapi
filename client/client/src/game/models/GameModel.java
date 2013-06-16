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

import game.items.Projectile;
import game.items.bonus.DefaultBonus;
import game.items.skills.DefaultSkill;
import game.items.weapons.DefaultWeapon;
import game.models.map.Map;
import game.models.map.Tile;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

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

   private static final Vector2 GRAVITY = new Vector2(0, 0);

   /**
    * Observable pour les événements qui méritent d'être synchronisés
    */
   public class Updater {
      List<GameListener> listeners = new LinkedList<GameListener>();

      /**
       * Abonne un observeur aux événements
       * 
       * @param listener
       *           Observeur à abonner
       */
      public void addListener(GameListener listener) {
         listeners.add(listener);
      }

      /**
       * Lorsqu'un joueur change de position ou de direction.
       * 
       * @param player
       *           Joueur modifié
       */
      protected void notifyPlayerMove(Player player) {
         for (GameListener gl : listeners) {
            gl.onPlayerMove(GameModel.this, player);
         }
      }

      /**
       * Lorsqu'un joueur reçoit des dommages
       * 
       * @param player
       *           Joueur concerné
       */
      protected void notifyPlayerHit(Player player) {
         for (GameListener gl : listeners) {
            gl.onPlayerHit(GameModel.this, player);
         }
      }

      /**
       * Lorsqu'un joueur tire
       * 
       * @param sender
       *           Joueur ayant tiré
       * @param from
       *           Origine du tir
       * @param dir
       *           Direction du tir
       */
      protected void notifyShoot(Player sender, Vector2 from, float angle) {
         for (GameListener gl : listeners) {
            gl.onFire(GameModel.this, sender, from, angle);
         }
      }

      /**
       * Lorsqu'un joueuer éteint ou allume sa lampe torche
       * 
       * @param player
       *           Joueur concerné
       */
      protected void notifyTorch(Player player) {
         for (GameListener gl : listeners) {
            gl.onTorch(GameModel.this, player);
         }
      }

      /**
       * Lorsqu'un joueur sort du labyrinthe
       * 
       * @param player
       *           Joueur concerné
       */
      protected void notifyPlayerOut(Player player) {
         for (GameListener gl : listeners) {
            gl.onPlayerOut(GameModel.this, player);
         }
      }

   }

   public final Updater updates = new Updater();

   /**
    * Modèle gérant les interactions physiques entre les entités ayant une
    * représentation physique (body). Utilisé par le moteur physique (box2d).
    */
   private World world;

   /**
    * Données d'initialisation du jeu
    */
   private GameData initData;

   /**
    * Stocke les entités de la scène. Attention, l'ordre d'insertion correspond
    * à l'ordre d'affichage : le premier élément inséré sera en arrière-plan, le
    * dernier en avant-plan.
    */
   private Group entities = new Group();

   /**
    * Représentation de la carte sous forme de grille de "tiles" (cases) de
    * types différents (mur, sol, etc.)
    */
   private Map map;

   /**
    * Personnage contrôlé par l'utilisateur
    */
   private MainPlayer player;

   /**
    * Equipes en jeu
    */
   private Team[] teams;

   /**
    * Modèle gérant l'éclairage. Se repose sur la représentation physique
    * (bodies) des entités pour les ombres. Utilisé par le moteur d'éclairage
    * (box2dlights).
    */
   private RayHandler rayHandler;

   /**
    * Permet d'activer ou non l'éclairage
    */
   private boolean isLightingActive = true;
   private boolean debug;

   /**
    * Instancie le modèle de jeu
    * 
    * @param initData
    *           Données d'initialiser à transmettre
    */
   public GameModel(GameData initData) {
      this.initData = initData;
      initData.setGame(this);
   }

   /**
    * Initialise le jeu
    * 
    * @param debug
    *           Activer le mode debug.
    */
   public void init(boolean debug) {
      this.debug = debug;
      world = new World(GRAVITY, false);
      rayHandler = new RayHandler(world);

      teams = new Team[] { new Team(Color.BLUE), new Team(Color.RED) };
      map = new Map(this, teams);

      entities.addActor(map);
      entities.addActor(new Exit(this, map.getExitPos().x, map.getExitPos().y));

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

      // Initialise toutes les entités
      for (Actor e : entities.getChildren()) {
         ((Entity) e).init(initData);
      }
   }

   /**
    * Crée un observateur sur les collisions entre les bodies et définit le
    * comportement selon les cas.
    */
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
                        && userdataA instanceof Projectile
                        && userdataB instanceof Player)
                     ((Projectile) userdataA).onHit((Player) userdataB);

                  else if (a.getBody().getUserData() != null
                        && b.getBody().getUserData() != null
                        && userdataB instanceof Projectile
                        && userdataA instanceof Player)
                     ((Projectile) userdataB).onHit((Player) userdataA);

                  else if (a.getBody().getUserData() != null
                        && b.getBody().getUserData() != null
                        && a.getBody().getUserData() instanceof Projectile
                        && b.getBody().getUserData() instanceof Tile)
                     ((Projectile) a.getBody().getUserData()).deactivate();
                  else if (a.getBody().getUserData() != null
                        && b.getBody().getUserData() != null
                        && b.getBody().getUserData() instanceof Projectile
                        && a.getBody().getUserData() instanceof Tile)
                     ((Projectile) b.getBody().getUserData()).deactivate();
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

   /**
    * Récupère un joueur selon son identifiant unique
    * 
    * @param playerId
    *           Identifiant du joueur
    * @return Le joueur s'il existe
    */
   public Player getPlayerById(int playerId) {
      for (Team team : teams) {
         for (Player player : team.getMembers()) {
            if (player.getId() == playerId) {
               return player;
            }
         }
      }
      throw new NoSuchElementException();
   };

   /**
    * @param player
    *           Personnage du joueur principal
    */
   public void setPlayer(Player player) {
      this.player = new MainPlayer(player);
   }

   /**
    * @return Map de la partie
    */
   public Map getMap() {
      return map;
   }

   /**
    * @param map
    *           Map de la partie
    */
   public void setMap(Map map) {
      this.map = map;
   }

   /**
    * Charge les ressources du jeu (fichiers images, sons...)
    */
   public void loadResources() {
      for (Actor e : entities.getChildren()) {
         ((Entity) e).loadResources();
      }
   }

   /**
    * @return Modèle gérant les interactions physiques entre les entités ayant
    *         une représentation physique (body). Utilisé par le moteur physique
    *         box2d.
    */
   public World getWorld() {
      return world;
   }

   /**
    * @return Modèle gérant l'éclairage. Se repose sur la représentation
    *         physique (bodies) des entités pour les ombres. Utilisé par le
    *         moteur d'éclairage (box2dlights).
    */
   public RayHandler getRayHandler() {
      return rayHandler;
   }

   /**
    * @return Les entités du jeu.
    */
   public Group getEntities() {
      return entities;
   }

   /**
    * Commande spéciale pour le debug
    */
   public void debugMe() {
      System.out.println("CHEAT");
      if (debug) {
         Vector2.tmp3.set(map.getExitPos());
         if (player.isInGame()) {
            Vector2.tmp3.add(200, -200);
            player.damage(25);
         }
         else {
            player.setHP(100);
            player.setOut(false);
            Vector2.tmp3.add(100, -200);
         }
         player.moveTo(Vector2.tmp3);
      }
   }


   /**
    * @return true si l'éclairage est actif
    */
   public boolean isLightingActive() {
      return isLightingActive;
   }

   /**
    * @param isLightingActive
    *           Activer l'éclairage.
    */
   public void setLightingActive(boolean isLightingActive) {
      this.isLightingActive = isLightingActive;
   }

}
