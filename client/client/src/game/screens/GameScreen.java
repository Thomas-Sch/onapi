/* ============================================================================
 * Nom du fichier   : GameScreen.java
 * ============================================================================
 * Date de création : 1 mai 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package game.screens;

import game.controllers.GameController;
import game.models.GameModel;
import game.views.GameRenderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;

/**
 * Un des différents écrans (screens) de l'application.
 * 
 * Celui-ci sert de point d'entrée au modèle, à la vue et au(x) contrôleur(s).
 * Utilisé par le framework comme gestionnnaire des événements d'entrée
 * (clavier, souris, changement d'état de la fenêtre principale...).
 * 
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 * 
 */
public class GameScreen extends ScreenAdapter {

   private GameController controller;
   private GameModel game;
   private GameRenderer renderer;

   @Override
   public void show() {
      System.out.println("GameScreen : showed");
      game = new GameModel();
      renderer = new GameRenderer(game, true);
      controller = new GameController(game);
      Gdx.input.setInputProcessor(this);
   }

   @Override
   public void render(float delta) {
      Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
      Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

      // Il faudrait que les deux appels suivants se fassent en parallèle.
      controller.update(delta);
      renderer.render();
   }

   @Override
   public void resize(int width, int height) {
      renderer.setSize(width, height);
   }

   @Override
   public void dispose() {
      Gdx.input.setInputProcessor(null);
   }

   @Override
   public boolean keyDown(int keycode) {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public boolean keyUp(int keycode) {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public boolean keyTyped(char character) {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public boolean touchDown(int screenX, int screenY, int pointer, int button) {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public boolean touchUp(int screenX, int screenY, int pointer, int button) {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public boolean mouseMoved(int screenX, int screenY) {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public void hide() {
      System.out.println("GameScreen : hidden");
      // TODO Auto-generated method stub
   }

   @Override
   public void pause() {
      System.out.println("GameScreen : paused");
      // TODO Auto-generated method stub
   }

   @Override
   public void resume() {
      System.out.println("GameScreen : resumed");
      // TODO Auto-generated method stub
   }

}
