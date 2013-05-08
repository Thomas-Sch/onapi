/* ============================================================================
 * Nom du fichier   : Onapi.java
 * ============================================================================
 * Date de création : 1 mai 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package client;

import game.screens.GameScreen;
import game.screens.MainMenuScreen;

import com.badlogic.gdx.Game;

/**
 * Classe d'initialisation du jeu. Gère la transition entre les différents
 * screens.
 * 
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 * 
 */
public class Onapi extends Game {

   private GameScreen gameScreen = new GameScreen();
   private MainMenuScreen mainMenu = new MainMenuScreen();

   @Override
   public void create() {

      //
      // Code temporaire - pour voir comment sont gérés les screens
      //
      
      setScreen(gameScreen); 
//      try { Thread.sleep(3000); } catch (InterruptedException e) {}
//      setScreen(mainMenu);
//      try { Thread.sleep(3000); } catch (InterruptedException e) {}
//      setScreen(gameScreen);
//      try { Thread.sleep(3000); } catch (InterruptedException e) {}
//      setScreen(mainMenu);
//      try { Thread.sleep(3000); } catch (InterruptedException e) {}
//      setScreen(gameScreen);
   }
   
   
}
