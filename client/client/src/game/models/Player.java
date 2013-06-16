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

import game.items.Bonus;
import game.items.Skill;
import game.items.Weapon;
import game.models.map.Tile;
import box2dLight.ConeLight;
import box2dLight.PointLight;
import client.GameData;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

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

   private static final float RUNNING_FRAME_DURATION = 1.5f;

   private static int lastId = 1;
   private final int id = lastId++;

   private static final int WIDTH = 100;
   private static final int HEIGHT = WIDTH;

   public final Color TORCH_COLOR = new Color(237f / 255f, 240f / 255f,
         168f / 255f, 0.6f);
   public final static Color HALO_COLOR = new Color(1, 1, 1, 0.3f);
   private static int HP_START = 100;

   private static final Vector2 GRAVEYARD_POS = new Vector2(-500, -500);
   private final Vector2 deadPos;

   private TextureRegion playerFrame;

   /** Animations **/
   private Animation walkAnimation;
   private boolean isMoving = false;
   private float lastFrameUpdate = 0.0f;
   private float currentTime = 0.0f;
   private static final int NB_FRAMES_ANIMATION = 6;
   private static final float ANIMATION_DURATION = 0.3f; // en [s]

   /**
    * Référence de l'équipe à laquelle appartient le personnage
    */
   private Team team;

   /**
    * Nombre de points de santé (health points) du joueur
    */
   private int hp = HP_START;

   private boolean out = false;

   /**
    * Arme équipée par le joueur
    */
   private Weapon weapon;

   /**
    * Compétence spéciale du joueur
    */
   private Skill skill;

   /**
    * Amélioration d'arme équipée par le joueur
    */
   private Bonus bonus;

   /**
    * @return Arme équipée par le joueur
    */
   public Weapon getWeapon() {
      return weapon;
   }

   /**
    * @return Compétence spéciale du joueur
    */
   public Skill getSkill() {
      return skill;
   }

   /**
    * @return Amélioration d'arme équipée par le joueur
    */
   public Bonus getBonus() {
      return bonus;
   }

   /**
    * Texture du joueur à son affichage
    */
   private Texture texture;

   private ConeLight torchLight;

   private Body body;

   private Rectangle bounds;
   private PointLight pl;

   public final static int HALO_DISTANCE = Tile.WIDTH - 50;

   public Player(Vector2 pos, Vector2 dir, Team team, Weapon weapon,
         Skill skill, Bonus bonus, GameModel game) {
      super(game);

      setWidth(WIDTH);
      setHeight(HEIGHT);
      bounds = new Rectangle(pos.x, pos.y, getWidth() / 2, getHeight() / 2);

      setTeam(team);
      team.getMembers().add(this);
      this.weapon = weapon;
      this.weapon.setOwner(this);
      this.skill = skill;
      this.skill.setOwner(this);
      this.bonus = bonus;
      this.bonus.setOwner(this);

      loadResources();

      // Définit la consistance physique du joueur
      BodyDef bodyDef = new BodyDef();
      bodyDef.type = BodyType.DynamicBody;
      bodyDef.position.set(getPos());
      body = game.getWorld().createBody(bodyDef);
      PolygonShape shape = new PolygonShape();
      shape.setAsBox(bounds.height, bounds.width);
      FixtureDef fix = new FixtureDef();
      fix.shape = shape;
      fix.density = 0.01f;
      fix.friction = 0.01f;
      fix.restitution = 0.01f;
      body.createFixture(fix);
      body.setUserData(this);

      // Lampe torche
      torchLight = new ConeLight(game.getRayHandler(), 50, TORCH_COLOR, 750, 1,
            1, 270, 30);
      torchLight.attachToBody(body, 1, 1);
      this.torchLight.setActive(true);

      setDir(dir);
      moveTo(pos);
      deadPos = new Vector2(GRAVEYARD_POS.x + pos.x, GRAVEYARD_POS.y + pos.y);

   }

   @Override
   public void init(GameData initData) {
      // TODO Auto-generated method stub
   }

   public void loadResources() {
      texture = new Texture(Gdx.files.internal("data/sprite1_perso.png"));
      TextureAtlas atlas = new TextureAtlas(
            Gdx.files.internal("data/player.atlas"));

      TextureRegion[] playerWalk = new TextureRegion[5];
      for (int i = 0; i < 5; i++) {
         playerWalk[i] = atlas.findRegion("player" + (i + 1));
      }
      walkAnimation = new Animation(RUNNING_FRAME_DURATION, playerWalk);

   }

   public Rectangle getBounds() {
      return bounds;
   }

   /**
    * @return Position du joueur sur la map
    */
   public Vector2 getPos() {
      return new Vector2(getX(), getY());
   }

   /**
    * Place le joueur à une nouvelle position sur la map
    * 
    * @param newPos
    *           Nouvelle position
    */
   public void moveTo(Vector2 newPos) {
      setPosition(newPos.x - bounds.width / 2f, newPos.y - bounds.height
            / 2f);
      bounds.x = newPos.x;
      bounds.y = newPos.y;
      isMoving = true;
   }

   /**
    * Déplace le joueur depuis sa position actuelle
    * 
    * @param dir
    *           Vecteur de déplacement (additionné à sa position actuelle)
    */
   public void move(Vector2 dir) {
      if (isInGame()) {
         setPosition(getX() + dir.x, getY() + dir.y);
         bounds.x += dir.x;
         bounds.y += dir.y;
         isMoving = true;
      }
   }

   /**
    * @param dir
    *           Orientation du joueur
    */
   public void setDir(Vector2 dir) {
      if (isInGame()) {
         setRotation(dir.angle());
         this.dir = dir;
      }
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
   public void update(float deltaTime) {
      updateAnimation(deltaTime);
      body.setTransform(getPos(), getRotation() * ((float) Math.PI) / 180f);
      getSkill().update(deltaTime);
   }

   private void updateAnimation(float deltaTime) {
      currentTime += deltaTime;
      if (currentTime - lastFrameUpdate > ANIMATION_DURATION
            / NB_FRAMES_ANIMATION) {
         if (isMoving) {
            animationFrameIndex = (animationFrameIndex+1)
                  % (NB_FRAMES_ANIMATION + 1);
            isMoving = false;
         }
         else {
            animationFrameIndex = 0;
         }
         lastFrameUpdate = currentTime;
      }
   }

   public void shoot(float delta) {
      if (isInGame()) {
         weapon.shoot(delta);
      }
   }

   private int animationFrameIndex = 0;

   @Override
   public void draw(SpriteBatch batch, float parentAlpha) {
      super.draw(batch, parentAlpha);

      if (isInGame()) {

         // Affecte le sprite pour l'utilisateur
         // TextureRegion region = new TextureRegion(texture, 0, 0, 256, 256);

         int textureWidth = texture.getWidth();
         int textureHeight = texture.getHeight();

         weapon.draw(batch, parentAlpha);
         skill.draw(batch, parentAlpha);
         bonus.draw(batch, parentAlpha);

         Color previousTint = batch.getColor();
         batch.setColor(team.getColor());

         playerFrame = walkAnimation.getKeyFrame(animationFrameIndex, true);

         // Dessiner à l'écran le joueur
         batch.draw(playerFrame, getPos().x - texture.getWidth() / 2,
               getPos().y - texture.getHeight() / 2, textureWidth / 2f,
               textureHeight / 2f, textureWidth, textureHeight, getWidth()
                     / textureWidth, getHeight() / textureHeight,
               getRotation() + 180, false);

         batch.setColor(previousTint);
      }
   }

   @Override
   public void debugRender(ShapeRenderer renderer) {

   }

   /**
    * Orientation du personnage
    */
   private Vector2 dir;

   /**
    * @return Orientation du joueur
    */
   public Vector2 getDir() {
      return dir;
   }

   public Body getBody() {
      return body;
   }

   @Override
   public String toString() {
      return String.format("Pos=%s", getPos());
   }

   /**
    * @return Nombre de points de santé (health points) du joueur
    */
   public int getHP() {
      return hp;
   }

   /**
    * @param hp
    *           Nombre de points de santé (health points) du joueur
    */
   public void setHP(int hp) {
      this.hp = hp;
   }

   /**
    * Inflige un certain nombre de points de dégats au joueur.
    * 
    * @param damagePoints
    *           Points de dégats à infliger
    */
   public void damage(int damagePoints) {
      if (isInGame()) {
         hp -= damagePoints;
         System.out.println(this + "\t : HP = " + hp);
         if (hp <= 0) die();
      }
   }

   public void die() {
      this.hp = 0;
      moveTo(deadPos);
      System.out.println(this + "\t DEAD");
      torchLight.setActive(false);
   }

   /**
    * 
    */
   public void toggleTorch() {
      if (isInGame()) {
         this.torchLight.setActive(!torchLight.isActive());
      }
   }

   public void setTorchColor(Color color) {
      torchLight.setColor(color);
   }

   public Color getTorchColor() {
      return torchLight.getColor();
   }

   public int getId() {
      return id;
   }

   /**
    * @param defaultWeapon
    */
   public void setWeapon(Weapon weapon) {
      this.weapon = weapon;
   }

   @Override
   public boolean equals(Object obj) {
      return obj instanceof Player && ((Player) obj).id == id;
   }

   /**
    * @return the out
    */
   public boolean isOut() {
      return out;
   }

   /**
    * @param out
    *           the out to set
    */
   public void setOut(boolean out) {
      this.out = out;
      torchLight.setActive(isInGame());
   }

   public boolean isDead() {
      return hp <= 0;
   }

   public boolean isInGame() {
      return !isOut() && !isDead();
   }

   public void toggleSkill() {
      if (isInGame()) {
         getSkill().activate();
      }
   }

}