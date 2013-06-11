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

import box2dLight.PointLight;
import box2dLight.RayHandler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;
import game.models.Entity;
import game.models.Player;

/**
 * TODO
 * 
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 * 
 */
public class Bullet extends Entity {

   private float posX;
   private float posY;
   private float startX;
   private float startY;
   private float speed;
   protected Body body;
   private int pos;
   private Texture texture;
   private float lastUpdate;
   private float length;
   private boolean active;
   private PointLight pl;
   private Vector2 dir;
   private Weapon weapon;

   public Vector2 getPos() {
      return dir;
   }

   public Bullet(World world, Weapon weapon, float startX, float startY, float x, float y,
         float lenght, float speed, RayHandler handler, Player player) {
      this.dir = new Vector2(posX / speed, posY / speed);
      this.posX = 0;
      this.posY = 0;
      this.length = lenght;
      this.speed = speed;
      this.pos = 0;
      this.startX = startX;
      this.startY = startY;
      this.lastUpdate = 0;
      this.weapon = weapon;
      this.active = false;
      setWidth(30f);
      setHeight(20f);

      // Définit la consistance physique de la balle
      BodyDef bodyDef = new BodyDef();
      bodyDef.type = BodyType.DynamicBody;
      bodyDef.position.set(getPos());

      body = world.createBody(bodyDef);
      body.setActive(true);
      PolygonShape shape = new PolygonShape();
      shape.setAsBox(getHeight(), getWidth());
      FixtureDef fix = new FixtureDef();
      fix.shape = shape;
      fix.density = 0.4f;
      fix.friction = 0.5f;
      fix.restitution = 0.8f;
      body.createFixture(fix);
      this.body.setActive(false);
      body.setUserData(this);

      pl = new PointLight(handler, 50, new Color(1, 1 ,1, 1), 40, getX(), getY());
      pl.attachToBody(body, 0, 0);
      pl.setActive(true);
      setX(startX);
      setY(startY);
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
      lastUpdate += 1;
      if (active) {
         if (Math.sqrt(posX * posX + posY * posY) > length) deactivate();
         if (lastUpdate > 2) {
            ++pos;
            body.setTransform(getX(), getY(), getRotation() * ((float) Math.PI) / 180f);
            posX += speed / 10 * pos * dir.x;
            posY += speed / 10 * pos * dir.y;
            setX(startX + posX);
            setY(startY + posY);
            lastUpdate = 0;
         }
      }
   }

   @Override
   public void draw(SpriteBatch batch, float parentAlpha) {
      super.draw(batch, parentAlpha);
      if (isActive()) {
         TextureRegion textureBullet = new TextureRegion(texture, 0, 0, 162,
               256);
         // Dessiner à l'écran la balle
         batch.draw(textureBullet, getX() + getWidth()/2, getY()+ getWidth()/2, 0, 0, texture.getWidth(),
               texture.getHeight(), getWidth() / texture.getWidth(),
               getHeight() / texture.getHeight(), getRotation()-90, false);

      }
   }

   public void onHit(Player target) {
      weapon.onHit(target);
   }

   public void deactivate() {
      this.active = false;
      this.body.setActive(false);
      this.pl.setActive(false);
   }

   public void activate(float startX, float startY, float posX, float posY) {
      this.active = true;
      this.body.setActive(true);
      this.pl.setActive(true);
      this.posX = 0;
      this.posY = 0;
      this.dir.x = posX / speed;
      this.dir.y = posY / speed;
      setRotation(dir.angle()+90);
      this.startX = startX;
      this.startY = startY;
      setX(startX);
      setY(startY);
      this.pos = 0;
      this.lastUpdate = 0;
   }

   /**
    * @return
    */
   public boolean isActive() {
      // TODO Auto-generated method stub
      return active;
   }
}
