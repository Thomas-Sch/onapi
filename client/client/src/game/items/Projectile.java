/* ============================================================================
 * Nom du fichier   : Bullet.java
 * ============================================================================
 * Date de création : 31 mai 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package game.items;

import game.models.Entity;
import game.models.GameModel;
import game.models.Player;
import box2dLight.PointLight;
import client.GameData;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

/**
 * Représente un projectile envoyé par une arme lors d'un tir.
 * 
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 * 
 */
public class Projectile extends Entity {

   private static final float UPDATE_RATE = 1 / 120f;

   protected Body body;
   private Texture texture;
   private float lastUpdate;
   private float mayDistance;
   private boolean active;
   private PointLight pl;
   private Weapon weapon;

   private float speed;
   private float distance;
   private Vector2 increment = new Vector2();

   /**
    * Instancie un projectile.
    * 
    * @param weapon
    *           Arme correspondante
    * @param startX
    *           Position initiale du projectile (x)
    * @param startY
    *           Position initiale du projectile (x)
    * @param maxDistance
    *           Distance maximale à parcourir avant la destruction du projectile
    * @param speed
    *           Vitesse du projectile
    * @param game
    *           Modèle du jeu
    */
   public Projectile(Weapon weapon, float startX, float startY,
         float maxDistance, float speed, Player player, GameModel game) {
      super(game);
      this.mayDistance = maxDistance;
      this.lastUpdate = 0;
      this.weapon = weapon;
      this.active = false;
      this.speed = speed;
      setWidth(30f);
      setHeight(20f);
      setPosition(startX, startY);

      // Définit la consistance physique de la balle
      BodyDef bodyDef = new BodyDef();
      bodyDef.type = BodyType.KinematicBody;
      bodyDef.position.set(getX(), getY());
      body = game.getWorld().createBody(bodyDef);
      PolygonShape shape = new PolygonShape();
      shape.setAsBox(getHeight() / 2, getWidth() / 2);
      FixtureDef fix = new FixtureDef();
      fix.shape = shape;
      fix.density = 0.4f;
      fix.friction = 0.5f;
      fix.restitution = 0.8f;
      body.createFixture(fix);
      this.body.setUserData(this);

      pl = new PointLight(game.getRayHandler(), 50, weapon.getOwner().getTeam()
            .getColor(), 100, getX(), getY());
      pl.attachToBody(body, 0, 0);
      pl.setActive(true);
      setX(startX);
      setY(startY);
   }

   @Override
   public void init(GameData initData) {
      // TODO Auto-generated method stub
   }

   @Override
   public void debugRender(ShapeRenderer renderer) {
      // TODO Auto-generated method stub
      super.debugRender(renderer);
   }

   @Override
   public void loadResources() {
      texture = new Texture(Gdx.files.internal("data/ammo.png"));
   }

   @Override
   public void update(float deltaTime) {
      float currentTime = lastUpdate + deltaTime;
      if (currentTime > lastUpdate + UPDATE_RATE) {
         for (int i = 0; i < Math.floor(currentTime / UPDATE_RATE); ++i) {
            if (active) {
               this.pl.setActive(true);
               if (distance > mayDistance) {
                  deactivate();
               }
               else {
                  setX(getX() + increment.x);
                  setY(getY() + increment.y);
                  body.setTransform(getX(), getY(), getRotation()
                        * ((float) Math.PI) / 180f);
                  distance += increment.len();
               }
            }
         }
         lastUpdate = currentTime;
      }
   }

   @Override
   public void draw(SpriteBatch batch, float parentAlpha) {
      if (isActive()) {
         TextureRegion textureBullet = new TextureRegion(texture, 0, 0, 162,
               256);
         // Dessiner à l'écran la balle
         batch.draw(textureBullet, getX() - getWidth() / 2, getY() - getWidth()
               / 2, 0, 0, texture.getWidth(), texture.getHeight(), getWidth()
               / texture.getWidth(), getHeight() / texture.getHeight(),
               getRotation() - 90, false);

      }
   }

   /**
    * Lorsque le projectile touche un joueur
    * 
    * @param target
    */
   public void onHit(Player target) {
      if (target != weapon.getOwner()) weapon.onHit(target);
   }

   /**
    * @return L'arme correspondante
    */
   public Weapon getWeapon() {
      return this.weapon;
   }

   /**
    * Détruit le projectile (peut être réutilisé ensuite avec
    * {@link Projectile#activate(float, float, float, float)}.
    */
   public void deactivate() {
      setX(0);
      setY(0);
      this.pl.setActive(false);
      this.active = false;
   }

   /**
    * Lance le projectile depuis une position données vers une direction donnée.
    * 
    * @param startX
    *           Point de tir (x)
    * @param startY
    *           Point de tir (y)
    * @param dirX
    *           Point de direction (x)
    * @param dirY
    *           Point de direction (x)
    */
   public void activate(float startX, float startY, float dirX, float dirY) {
      setPosition(startX, startY);
      this.increment.set(dirX, dirY).nor().mul(speed * UPDATE_RATE);
      setRotation(increment.angle() + 90);
      this.distance = 0.0f;
      this.lastUpdate = 0;
      this.active = true;
   }

   /**
    * @return Si le projectile est actif (lancé).
    */
   public boolean isActive() {
      return active;
   }
}
