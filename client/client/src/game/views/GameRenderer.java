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

   private GameModel game;
   private boolean debug;
   private int width;
   private int height;

   private Stage ui;
   private Label lblOut;

   public GameRenderer(GameModel game, boolean debug) {
      this.game = game;
      this.debug = debug;
      ui = new Stage(width, height, true);
      Gdx.input.setInputProcessor(ui);
      create();
   }

   private void create() {
      final Skin skin = new Skin(Gdx.files.internal("data/uiskin.json"));

      Table table = new Table(skin);
      table.setFillParent(true);
      ui.addActor(table);

      lblOut = new Label("...", skin);
      table.add(lblOut);

      table.pack();
   }

   public void update() {
      lblOut.setText("Pos : "
            + String.format("(%+3.2f, %+3.2f)", game.getPlayer().getPos().x, game
                  .getPlayer().getPos().y));
   }

   /**
    * Méthode appelée à chaque rafraîchissement de l'écran
    */
   public void render() {
      update();

      ui.act(Gdx.graphics.getDeltaTime());
      ui.draw();
   }

   /**
    * (Re)définit la hauteur et la largeur de l'écran graphique
    * 
    * @param width
    * @param height
    */
   public void setSize(int width, int height) {
      this.width = width;
      this.height = height;
      ui.setViewport(width, height, true);
   }

}
