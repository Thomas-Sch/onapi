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
 * Représente la porte de sortie du labyrinthe
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
   private final int EXIT_HEIGHT = 127;
   private final int EXIT_WIDTH = 85;
   private Texture texture;

   /**
    * Construit la porte
    * 
    * @param game
    *           Modèle du jeu
    * @param x
    *           Position en x de la porte sur la map
    * @param y
    *           Position en y de la porte sur la map
    */
   public Exit(GameModel game, float x, float y) {
      super(game);
      setX(x);
      setY(y);
      setHeight(EXIT_HEIGHT);
      setWidth(EXIT_WIDTH);

      // Initialise la représentation physique de la porte
      BodyDef bodyDef = new BodyDef();
      bodyDef.type = BodyType.StaticBody;
      bodyDef.position.set(x, y);
      Body body = game.getWorld().createBody(bodyDef);
      PolygonShape shape = new PolygonShape();
      shape.setAsBox(EXIT_WIDTH / 2, EXIT_HEIGHT / 2);
      FixtureDef fix = new FixtureDef();
      fix.shape = shape;
      fix.density = 0.4f;
      fix.friction = 0.5f;
      fix.restitution = 0.8f;
      body.createFixture(fix);
      body.setUserData(this);

      // Lumière émise par la porte
      new PointLight(game.getRayHandler(), 30, EXIT_LIGHT_COLOR, 150, 0, 0)
            .attachToBody(body, 0, -25);
   }

   @Override
   public void loadResources() {
      texture = new Texture(Gdx.files.internal("data/exit.png"));
   }

   @Override
   public void init(GameData initData) {
   }

   @Override
   public void update(float deltaTime) {
   }

   @Override
   public void draw(SpriteBatch batch, float parentAlpha) {
      // Dessine une porte
      batch.draw(texture, getX() - getWidth() / 2, getY() - getHeight() / 2,
            getWidth(), getHeight(), 0, 0, 1, 1);
   }

}