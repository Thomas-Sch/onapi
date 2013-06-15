/* ============================================================================
 * Nom du fichier   : Exit.java
 * ============================================================================
 * Date de création : 14 juin 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package game.models;

import game.models.map.Tile;
import box2dLight.PointLight;
import box2dLight.RayHandler;

import client.GameInitData;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * TODO
 * 
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 * 
 */
public class Exit extends Entity {

   private static final Color EXIT_LIGHT_COLOR = new Color(90 / 255f,
         237 / 255f, 178 / 255f, 0.8f);
   private Texture texture;

   /**
    * 
    */
   public Exit(World world, RayHandler handler, int h, int w, float x, float y) {
      // TODO Auto-generated constructor stub
      setX(x);
      setY(y);
      setHeight(h);
      setWidth(w);
      BodyDef bodyDef = new BodyDef();
      bodyDef.type = BodyType.StaticBody;
      bodyDef.position.set(x, y);
      Body body = world.createBody(bodyDef);
      body.setUserData(Tile.EXIT);
      PolygonShape shape = new PolygonShape();
      shape.setAsBox(w / 2, h / 2);
      FixtureDef fix = new FixtureDef();
      fix.shape = shape;
      fix.density = 0.4f;
      fix.friction = 0.5f;
      fix.restitution = 0.8f;
      body.createFixture(fix);
      body.setUserData(Tile.EXIT);

      new PointLight(handler, 30, EXIT_LIGHT_COLOR, 150, 0, 0).attachToBody(
            body, 0, -25);
   }

   public void loadResources() {
      texture = new Texture(Gdx.files.internal("data/exit.png"));
   }

   @Override
   public void init(GameInitData initData) {
      // TODO Auto-generated method stub
   }

   @Override
   public void update(float deltaTime) {
      // TODO Auto-generated method stub

   }

   public void draw(SpriteBatch batch, float parentAlpha) {
      // Dessine une porte
      /*
       * TextureRegion region = new TextureRegion(texture, 0, 0, 284, 426);
       * 
       * // Dessiner à l'écran le joueur batch.draw(region, getX() -
       * texture.getWidth() / 2, getY() - texture.getHeight() / 2,
       * texture.getWidth() / 2f, texture.getHeight() / 2f, texture.getWidth(),
       * texture.getHeight(), getWidth() / texture.getWidth(), getHeight() /
       * texture.getHeight(), getRotation(), false);
       */
      batch.draw(texture, getX() - getWidth() / 2, getY() - getHeight() / 2,
            getWidth(), getHeight(), 0, 0, 1, 1);

      // Dessiner à l'écran le joueur
      // batch.draw(texture, getX(), getY(), getWidth(), getHeight(),
      // getWidth(), getHeight(), 1f, 1f);
   }

}
