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
import box2dLight.ConeLight;
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
 * Représente un personnage du jeu.
 * 
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 * 
 */
public class Player extends Entity {

   // Durée de l'animation
   private static final float RUNNING_FRAME_DURATION = 1.5f;

   // Identifiant unique du joueur
   private static int lastId = 1;
   private final int id = lastId++;

   // Dimensions du joueur
   private static final int WIDTH = 100;
   private static final int HEIGHT = WIDTH;

   // Couleur de la lampe torche
   public final Color TORCH_COLOR = new Color(237f / 255f, 240f / 255f,
         168f / 255f, 0.6f);

   // Total de points de vie d'un joueur au début de la partie
   private static int HP_START = 100;

   // Position du joueur lorsqu'il meurt (transporté en dehors du labyrinthe)
   private static final Vector2 GRAVEYARD_POS = new Vector2(-500, -500);
   private final Vector2 deadPos;

   // Données de l'animation
   private TextureRegion playerFrame; // Region du sprite utilisée
   private Animation walkAnimation; // Animation d'un joueur en déplacement
   private boolean isMoving = false; // S'il faut lancer l'animation
   private float lastFrameUpdate = 0.0f; // Moment du dernier update de
                                         // l'animation
   private float currentTime = 0.0f; // Temps actuel du jeu
   private static final int NB_FRAMES_ANIMATION = 6; // Nombre de frames du
                                                     // sprite
   private static final float ANIMATION_DURATION = 0.3f; // Durée de l'animation
                                                         // (en [s])
   private int animationFrameIndex = 0; // Indice du numéro de frame courant

   /**
    * Référence de l'équipe à laquelle appartient le personnage
    */
   private Team team;

   /**
    * Nombre de points de santé (health points) du joueur
    */
   private int hp = HP_START;

   /**
    * Indique si le joueur est sorti du labyrinthe
    */
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
    * Orientation du personnage
    */
   private Vector2 dir;

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

   /**
    * Lumière représentant la lampe torche du joueur
    */
   private ConeLight torchLight;

   /**
    * Représente l'entité physique du joueur
    */
   private Body body;

   /**
    * Le rectangle qui représente la hitbox du joueur
    */
   private Rectangle bounds;

   /**
    * Instancie un joueur
    * 
    * @param pos
    *           Position initiale du joueur
    * @param dir
    *           Orientation initiale du joueur
    * @param team
    *           Equipe à laquelle il appartient
    * @param weapon
    *           Arme équipée
    * @param skill
    *           Compétence choisie
    * @param bonus
    *           Bonus choisi
    * @param game
    *           Modèle du jeu
    */
   public Player(Vector2 pos, Vector2 dir, Team team, Weapon weapon,
         Skill skill, Bonus bonus, GameModel game) {
      super(game);

      // Définit la hauteur et la largeur d'un joueur
      setWidth(WIDTH);
      setHeight(HEIGHT);

      // Définit les bornes de la hitbox
      bounds = new Rectangle(pos.x, pos.y, getWidth() / 2, getHeight() / 2);

      // Ajoute le joueur dans l'équipe
      setTeam(team);
      team.getMembers().add(this);

      // Set les informations relatives au joueurs : Arme skill et bonus
      this.weapon = weapon;
      this.weapon.setOwner(this);
      this.skill = skill;
      this.skill.setOwner(this);
      this.bonus = bonus;
      this.bonus.setOwner(this);

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

      // Crée la lampe torche
      torchLight = new ConeLight(game.getRayHandler(), 50, TORCH_COLOR, 750, 1,
            1, 270, 30);
      torchLight.attachToBody(body, 1, 1);
      this.torchLight.setActive(true);

      // Définit l'orientation du joueur et le place sur la map
      setDir(dir);
      moveTo(pos);

      // Définit la position lorsqu'un joueur meurt
      deadPos = new Vector2(GRAVEYARD_POS.x + pos.x, GRAVEYARD_POS.y + pos.y);

   }

   @Override
   public void init(GameData initData) {
   }

   @Override
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

   /**
    * @return Hitbox du joueur
    */
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
      setPosition(newPos.x - bounds.width / 2f, newPos.y - bounds.height / 2f);
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

   /**
    * Met à jour le sprite de l'animation du joueur
    * 
    * @param deltaTime
    *           Temps écoulé depuis la dernière mise à jour du sprite
    */
   private void updateAnimation(float deltaTime) {
      currentTime += deltaTime;
      if (currentTime - lastFrameUpdate > ANIMATION_DURATION
            / NB_FRAMES_ANIMATION) {
         if (isMoving) {
            animationFrameIndex = (animationFrameIndex + 1)
                  % (NB_FRAMES_ANIMATION + 2);
            isMoving = false;
         }
         else {
            animationFrameIndex = 4;
         }
         lastFrameUpdate = currentTime;
      }
   }

   /**
    * Fait tirer un joueur
    * 
    * @param deltaTime
    *           Temps écoulé depuis le dernier update
    */
   public void shoot(float deltaTime) {
      if (isInGame()) {
         weapon.shoot(deltaTime);
      }
   }

   @Override
   public void draw(SpriteBatch batch, float parentAlpha) {
      super.draw(batch, parentAlpha);

      if (isInGame()) {

         // Affiche les items et leurs effects
         weapon.draw(batch, parentAlpha);
         skill.draw(batch, parentAlpha);
         bonus.draw(batch, parentAlpha);

         // Affiche l'image courante du sprite animé du joueur dans la
         // couleur de son équipe
         Color previousTint = batch.getColor();
         batch.setColor(team.getColor());
         playerFrame = walkAnimation.getKeyFrame(animationFrameIndex, true);
         batch.draw(playerFrame, getPos().x - texture.getWidth() / 2,
               getPos().y - texture.getHeight() / 2, texture.getWidth() / 2f,
               texture.getHeight() / 2f, texture.getWidth(),
               texture.getHeight(), getWidth() / texture.getWidth(),
               getHeight() / texture.getHeight(), getRotation() + 180, false);
         batch.setColor(previousTint);
      }
   }

   @Override
   public void debugRender(ShapeRenderer renderer) {
   }

   /**
    * @return Orientation du joueur
    */
   public Vector2 getDir() {
      return dir;
   }

   /**
    * @return Représentation physique du joueur
    */
   public Body getBody() {
      return body;
   }

   /**
    * Utilisé pour le debug
    */
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

   /**
    * Fait mourir le joueur et le téléporte au cimetière (position des joueurs
    * morts)
    */
   public void die() {
      this.hp = 0;
      moveTo(deadPos);
      System.out.println(this + "\t DEAD");
      torchLight.setActive(false);
   }

   /**
    * Allume/éteint la lampe torche
    */
   public void toggleTorch() {
      if (isInGame()) {
         this.torchLight.setActive(!torchLight.isActive());
      }
   }

   /**
    * @param color
    *           Couleur de la lampe torche du joueur
    */
   public void setTorchColor(Color color) {
      torchLight.setColor(color);
   }

   /**
    * @return Couleur de la lampe torche du joueur
    */
   public Color getTorchColor() {
      return torchLight.getColor();
   }

   /**
    * @return Identifiant unique du joueur
    */
   public int getId() {
      return id;
   }

   /**
    * @param weapon
    *           Arme à équiper
    */
   public void setWeapon(Weapon weapon) {
      this.weapon = weapon;
   }

   @Override
   public boolean equals(Object obj) {
      return obj instanceof Player && ((Player) obj).id == id;
   }

   /**
    * @return true si le joueur est sorti du labyinthe
    */
   public boolean isOut() {
      return out;
   }

   /**
    * @param out
    *           true si le joueur est sorti du labyinthe
    */
   public void setOut(boolean out) {
      this.out = out;
      torchLight.setActive(isInGame());
   }

   /**
    * @return true si un joueur est mort
    */
   public boolean isDead() {
      return hp <= 0;
   }

   /**
    * @return true si un joueur est encore dans la partie
    */
   public boolean isInGame() {
      return !isOut() && !isDead();
   }

   /**
    * Active la compétence du joueur
    */
   public void activateSkill() {
      if (isInGame()) {
         getSkill().activate();
      }
   }

}