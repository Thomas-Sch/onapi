/* ============================================================================
 * Nom du fichier   : Player.java
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

import box2dLight.ConeLight;
import box2dLight.PointLight;
import box2dLight.RayHandler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

/**
 * Représente le personnage principal du jeu.
 * 
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 * 
 */
public class Player extends Entity {

   private static final int WIDTH = 50;
   private static final int HEIGHT = WIDTH;

   /**
    * Position actuelle du personnage sur la map
    */
   private Vector2 pos;

   /**
    * Orientation du personnage
    */
   private Vector2 dir;

   /**
    * Référence de l'équipe à laquelle appartient le personnage
    */
   private Team team;

   /**
    * Texture du joueur à son affichage
    */
   private Texture texture;

   private ConeLight torchLight;

   private Body body;

   public Player(Vector2 pos, Vector2 dir, Team team, World world,
         RayHandler handler) {
      super();
      setWidth(WIDTH);
      setHeight(HEIGHT);
      moveTo(pos);
      setTeam(team);
      loadResources();

      // Définit la consistance physique du joueur
      Rectangle bounds = new Rectangle(getX(), getY(), getWidth(), getHeight());
      BodyDef bodyDef = new BodyDef();
      bodyDef.type = BodyType.DynamicBody;
      bodyDef.position.set(getPos());
      body = world.createBody(bodyDef);
      PolygonShape shape = new PolygonShape();
      shape.setAsBox(bounds.height / 2, bounds.width / 2);
      FixtureDef fix = new FixtureDef();
      fix.shape = shape;
      fix.density = 0.4f;
      fix.friction = 0.5f;
      fix.restitution = 0.8f;
      body.createFixture(fix);

      // Initialise les lumières diffusées par le joueur
      torchLight = new ConeLight(handler, 5000, new Color(237f / 255f,
            240f / 255f, 168f / 255f, 0.9f), 15, 1, 1, 270, 20);
      torchLight.attachToBody(body, 0, 0);
      PointLight pl = new PointLight(handler, 5000, new Color(1, 1, 1, 0.5f),
            4, getPos().x, getPos().y);
      pl.attachToBody(body, 0, 0);
   }

   public void loadResources() {
      texture = new Texture(Gdx.files.internal("data/sprite1_perso.png"));
   }

   /**
    * @return Position du joueur sur la map
    */
   public Vector2 getPos() {
      return pos;
   }

   /**
    * Place le joueur à une nouvelle position sur la map
    * 
    * @param newPos
    *           Nouvelle position
    */
   public void moveTo(Vector2 newPos) {
      this.pos = newPos;
      setPosition(pos.x, pos.y);
   }

   /**
    * Déplace le joueur depuis sa position actuelle
    * 
    * @param dir
    *           Vecteur de déplacement (additionné à sa position actuelle)
    */
   public void move(Vector2 dir) {
      this.pos.add(dir);
      setPosition(pos.x, pos.y);
   }

   /**
    * @return Orientation du joueur
    */
   public Vector2 getDir() {
      return dir;
   }

   /**
    * @param dir
    *           Orientation du joueur
    */
   public void setDir(Vector2 dir) {
      this.dir = dir;
   }

   /**
    * @return Equipe du joueur
    */
   public Team getTeam() {
      return team;
   }

   /**
    * @param team
    *           Equipe du joueur
    */
   public void setTeam(Team team) {
      this.team = team;
   }

   @Override
   public void draw(SpriteBatch batch, float parentAlpha) {
      super.draw(batch, parentAlpha);

      body.setTransform(body.getPosition(), dir.angle());
      
      // Affecte le sprite pour l'utilisateur
      TextureRegion region = new TextureRegion(texture, 0, 0, 256, 256);

      int textureWidth = texture.getWidth();
      int textureHeight = texture.getHeight();

      // Dessiner à l'écran le joueur
      batch.begin();
      batch.draw(region, Gdx.graphics.getWidth() / 2 - region.getRegionWidth()
            / 2, Gdx.graphics.getHeight() / 2 - region.getRegionHeight() / 2,
            textureWidth / 2f, textureHeight / 2f, textureWidth, textureHeight,
            getWidth() / textureWidth, getHeight() / textureHeight,
            getRotation(), false);
      batch.end();
   }

   @Override
   public void debugRender(ShapeRenderer renderer) {
      // renderer.begin(ShapeType.Rectangle);
      // renderer.setColor(Color.RED);
      // renderer.rect(getX() - getWidth() / 2f, getY() - getHeight() / 2f,
      // getWidth(), getHeight());
      // renderer.end();
   }

   /**
    * @param body
    *           the body to set
    */
   public void setBody(Body body) {
      this.body = body;
   }

}
