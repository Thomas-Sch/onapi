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

import game.controllers.InputController;
import game.models.GameModel;
import game.views.GameRenderer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.math.Vector2;

import client.GameData;

/**
 * Ecran principal du jeu, correspondant à une partie en cours.
 * 
 * L'écran sert de point d'entrée au modèle, à la vue et au(x) contrôleur(s).
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

   private static final int MOUSE_LEFT_BUTTON = 0;
   private static final int MOUSE_RIGHT_BUTTON = 1;

   private boolean debug;

   /**
    * Contrôleur du jeu
    */
   private InputController controller;

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
    *           Activer le mode debug.
    */
   public GameScreen(boolean debug, GameData initData) {
      this.debug = debug;
      game = initData.getGame();
   }

   @Override
   public void show() {
      System.out.println("Initialization...");
      game.init(debug);
      System.out.println("Resources loading...");
      game.loadResources();
      System.out.println("Resources loaded.");
      renderer = new GameRenderer(game, debug);
      controller = new InputController(game);
      Gdx.input.setInputProcessor(this);
      System.out.println("Game ready.");
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

   /**
    * Met à jour la logique de jeu
    * 
    * @param deltaTime
    *           Temps écoulé depuis le dernier update
    */
   private void update(float deltaTime) {
      controller.update(deltaTime);
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
         controller.setActionState(InputController.Action.UP, true);
      if (keycode == Keys.S)
         controller.setActionState(InputController.Action.DOWN, true);
      if (keycode == Keys.A)
         controller.setActionState(InputController.Action.LEFT, true);
      if (keycode == Keys.D)
         controller.setActionState(InputController.Action.RIGHT, true);
      if (keycode == Keys.SPACE)
         controller.setActionState(InputController.Action.SKILL, true);
      return true;
   }

   @Override
   public boolean keyUp(int keycode) {
      if (keycode == Keys.W)
         controller.setActionState(InputController.Action.UP, false);
      if (keycode == Keys.S)
         controller.setActionState(InputController.Action.DOWN, false);
      if (keycode == Keys.A)
         controller.setActionState(InputController.Action.LEFT, false);
      if (keycode == Keys.D)
         controller.setActionState(InputController.Action.RIGHT, false);
      if (keycode == Keys.SPACE)
         controller.setActionState(InputController.Action.SKILL, false);

      return true;
   }

   @Override
   public boolean keyTyped(char character) {
      if ((character == 'X' || character == 'x')) game.debugMe();
      return true;
   }

   @Override
   public boolean touchDown(int screenX, int screenY, int pointer, int button) {
      if (button == MOUSE_RIGHT_BUTTON) {
         controller.setActionState(InputController.Action.TORCH, true);
      }
      if (button == MOUSE_LEFT_BUTTON) {
         controller.setActionState(InputController.Action.FIRE, true);
      }
      return true;
   }

   @Override
   public boolean touchUp(int screenX, int screenY, int pointer, int button) {
      if (button == MOUSE_RIGHT_BUTTON) {
         controller.setActionState(InputController.Action.TORCH, false);
      }
      if (button == MOUSE_LEFT_BUTTON) {
         controller.setActionState(InputController.Action.FIRE, false);
      }
      return true;
   }

   @Override
   public boolean touchDragged(int screenX, int screenY, int pointer) {
      if (Gdx.input.isButtonPressed(MOUSE_LEFT_BUTTON)) {
         controller.setActionState(InputController.Action.FIRE, true);
      }
      mouseMoved(screenX, screenY);
      return true;
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

      // Affecte le nouvel angle NOTE : ne pas utiliser vector2.tmp, autrement
      // modifier dans autre processus
      Vector2.tmp2.x = mouseX;
      Vector2.tmp2.y = mouseY;
      synchronized (game.getPlayer()) {
         game.getPlayer().setDir(Vector2.tmp2);
      }
      return true;
   }

}
