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
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.math.Vector2;

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

   private boolean debug;

   /**
    * Contrôleur du jeu
    */
   private GameController controller;

   /**
    * Modèle du jeu
    */
   private GameModel game;

   /**
    * Gestionnaire d'affichage du jeu
    */
   private GameRenderer renderer;

   /**
    * @param debug
    */
   public GameScreen(boolean debug) {
      this.debug = debug;
   }

   @Override
   public void show() {
      System.out.println("Resources loading...");
      game = new GameModel();
      renderer = new GameRenderer(game, true);
      controller = new GameController(game);
      Gdx.input.setInputProcessor(this);
      game.loadResources();
      System.out.println("Resources loaded.");
   }

   @Override
   public void render(float delta) {
      // Mise à jour de la logique du jeu
      update(delta);

      // Mise à jour de l'affichage
      Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
      Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
      renderer.render();
   }

   private void update(float delta) {
      controller.update(delta);
      game.getMap().update(delta);
      game.getPlayer().update(delta);
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
      if (keycode == Keys.W)
         controller.setActionState(GameController.Action.UP, true);
      if (keycode == Keys.S)
         controller.setActionState(GameController.Action.DOWN, true);
      if (keycode == Keys.A)
         controller.setActionState(GameController.Action.LEFT, true);
      if (keycode == Keys.D)
         controller.setActionState(GameController.Action.RIGHT, true);
      return true;
   }

   @Override
   public boolean keyUp(int keycode) {
      if (keycode == Keys.W)
         controller.setActionState(GameController.Action.UP, false);
      if (keycode == Keys.S)
         controller.setActionState(GameController.Action.DOWN, false);
      if (keycode == Keys.A)
         controller.setActionState(GameController.Action.LEFT, false);
      if (keycode == Keys.D)
         controller.setActionState(GameController.Action.RIGHT, false);
      return true;
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
      float playerX = Gdx.graphics.getWidth() / 2;
      float playerY = Gdx.graphics.getHeight() / 2;

      float mouseX;
      float mouseY;

      if (screenX > playerX && screenY <= playerY) {// Q1
         mouseX = screenX - playerX;
         mouseY = playerY - screenY;
      }
      else if (screenX <= playerX && screenY <= playerY) {// Q2
         mouseX = screenX - playerX;
         mouseY = playerY - screenY;
      }
      else if (screenX <= playerX && screenY >= playerY) {// Q3
         mouseX = screenX - playerX;
         mouseY = playerY - screenY;
      }
      else {// Q4
         mouseX = screenX - playerX;
         mouseY = playerY - screenY;
      }

      // Affecte le nouvel angle
      Vector2.tmp.set(mouseX, mouseY);
      game.getPlayer().setDir(Vector2.tmp);

      return true;
   }

}
