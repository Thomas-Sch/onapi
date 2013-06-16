
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
import client.GameData;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

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
   public Exit(GameModel game, int h, int w, float x, float y) {
      super(game);
      setX(x);
      setY(y);
      setHeight(h);
      setWidth(w);
      BodyDef bodyDef = new BodyDef();
      bodyDef.type = BodyType.StaticBody;
      bodyDef.position.set(x, y);
      Body body = game.getWorld().createBody(bodyDef);
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

      new PointLight(game.getRayHandler(), 30, EXIT_LIGHT_COLOR, 150, 0, 0)
            .attachToBody(body, 0, -25);
   }

   public void loadResources() {
      texture = new Texture(Gdx.files.internal("data/exit.png"));
   }

   @Override
   public void init(GameData initData) {
      // TODO Auto-generated method stub
   }

   @Override
   public void update(float deltaTime) {
      // TODO Auto-generated method stub

   }

   public void draw(SpriteBatch batch, float parentAlpha) {
      // Dessine une porte
      batch.draw(texture, getX() - getWidth() / 2, getY() - getHeight() / 2,
            getWidth(), getHeight(), 0, 0, 1, 1);
   }

}