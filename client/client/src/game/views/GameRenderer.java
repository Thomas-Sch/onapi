/* ============================================================================
 * Nom du fichier   : GameRenderer.java
 * ============================================================================
 * Date de création : 1 mai 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package game.views;

import game.models.GameModel;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

/**
 * Gère l'affichage du jeu à l'écran.
 * 
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 * 
 */
public class GameRenderer {

   private static final float W_COEFF = 1 / 100f;
   private static final float H_COEFF = W_COEFF;

   /**
    * Modèle du jeu à afficher
    */
   private GameModel game;

   /**
    * Indique si on est en mode debug
    */
   private boolean debug;

   /**
    * Hauteur de la fenêtre graphique
    */
   private int width;

   /**
    * Largeur de la fenêtre graphique
    */
   private int height;
   
   /**
    * Camera de la vue
    */
   private OrthographicCamera cam;

   private Rectangle viewport;

   /**
    * Scène des contrôles d'interface graphique
    */
   private Stage ui;

   // Objets pour le rendu en mode debug
   ShapeRenderer debugRenderer = new ShapeRenderer();

   // Contrôles de l'interface
   private Label lblOut;

   public GameRenderer(GameModel game, boolean debug) {
      this.game = game;
      this.debug = debug;
      
      // Active la synchronisation verticale
      Gdx.graphics.setVSync(true);
      
      // Initialise la caméra
      this.cam = new OrthographicCamera(width, height);
      viewport = new Rectangle(0, 0, 1280, 720);

      debugRenderer.setProjectionMatrix(cam.combined);

      // Initialise la scène de l'interface utilisateur
      ui = new Stage(width, height, true);
      Gdx.input.setInputProcessor(ui);
      initUI();
   }

   /**
    * Initialise l'interface graphique superposée
    */
   private void initUI() {
      // Charge les définitions d'apparence des contrôles
      final Skin skin = new Skin(Gdx.files.internal("data/uiskin.json"));

      Table table = new Table(skin);
      table.setFillParent(true);
      ui.addActor(table);

      lblOut = new Label("...", skin);
      table.add(lblOut);

      table.pack();
   }

   // temp
   public void update(float delta) {
      lblOut.setText("Pos : "
            + String.format("(%+3.2f, %+3.2f)", game.getPlayer().getPos().x,
                  game.getPlayer().getPos().y));
   }

   /**
    * Méthode appelée à chaque rafraîchissement de l'écran
    */
   public void render() {
      GL20 gl = Gdx.graphics.getGL20();

      // Mise à jour de la logique
      update(Gdx.graphics.getDeltaTime());

      // Mise à jour de la caméra
      gl.glViewport((int) viewport.x, (int) viewport.y, (int) viewport.width,
            (int) viewport.height);
      Vector2.tmp.set(game.getPlayer().getPos().x - cam.position.x, game
            .getPlayer().getPos().y - cam.position.y);
      cam.translate(Vector2.tmp);
      cam.update();

      if (debug) debugRender();

      game.getPlayer().draw(ui.getSpriteBatch(), 1.0f);
      game.getMap().draw(ui.getSpriteBatch(), 1.0f);

      ui.act(Gdx.graphics.getDeltaTime());
      ui.draw();

   }

   /**
    * Affiche des données de debug à l'écran
    */
   public void debugRender() {
      debugRenderer.setProjectionMatrix(cam.combined);
      game.getMap().debugRender(debugRenderer);
      game.getPlayer().debugRender(debugRenderer);
   }

   /**
    * (Re)définit la hauteur et la largeur de l'écran graphique
    * 
    * @param width
    *           Nouvelle largeur de l'écran graphique
    * @param height
    *           Nouvelle hauteur de l'écran graphique
    */
   public void setSize(int width, int height) {
      this.width = width;
      this.height = height;
      cam = new OrthographicCamera(width, height);
      viewport.setHeight(height);
      viewport.setWidth(width);
      ui.setViewport(width, height, true);
   }

}
