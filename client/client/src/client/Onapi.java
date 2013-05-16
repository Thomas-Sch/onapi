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

   private GameScreen gameScreen;

   public Onapi(boolean debug) {
      this.gameScreen = new GameScreen(debug);
   }
   
   @Override
   public void create() {
      setScreen(gameScreen);
   }

}
