/* ============================================================================
 * Nom du fichier   : MainMenuScreen.java
 * ============================================================================
 * Date de création : 4 mai 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;

/**
 * TODO
 * 
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 * 
 */
public class MainMenuScreen extends ScreenAdapter {

   @Override
   public void show() {
      System.out.println("MainMenuScreen : showed");
   }

   @Override
   public void render(float delta) {
      Gdx.gl.glClearColor(1f, 0.1f, 0.1f, 1);
      Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
   }

   @Override
   public void hide() {
      System.out.println("MainMenuScreen : hidden");
   }

   @Override
   public void pause() {
      System.out.println("MainMenuScreen : paused");
   }

   @Override
   public void resume() {
      System.out.println("MainMenuScreen : resumed");
   }

}
