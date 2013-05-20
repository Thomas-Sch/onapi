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

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;

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

   private static final int WIDTH = 100;
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
   Texture texture;

   public Player(Vector2 pos, Vector2 dir, Team team) {
      super();
      setWidth(WIDTH);
      setHeight(HEIGHT);
      moveTo(pos);
      setTeam(team);
      loadResources();
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

      // Affecte le sprite pour l'utilisateur
      TextureRegion region = new TextureRegion(texture, 0, 0, 256, 256);

      int textureWidth = texture.getWidth();
      int textureHeight = texture.getHeight();

      // Dessiner à l'écran le joueur
      batch.begin();
      batch.draw(region, Gdx.graphics.getWidth() / 2 - region.getRegionWidth()
            / 2, Gdx.graphics.getHeight() / 2 - region.getRegionHeight() / 2,
            textureWidth / 2f, textureHeight / 2f, textureWidth, textureHeight,
            1, 1, getRotation(), false);
      batch.end();
   }

   @Override
   public void debugRender(ShapeRenderer renderer) {
      renderer.begin(ShapeType.Rectangle);
      renderer.setColor(Color.RED);
      renderer.rect(getX() - getWidth() / 2f, getY() - getHeight() / 2f,
            getWidth(), getHeight());
      renderer.end();
   }

}
