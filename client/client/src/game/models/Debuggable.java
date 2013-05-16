/* ============================================================================
 * Nom du fichier   : Debuggable.java
 * ============================================================================
 * Date de création : 15 mai 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package game.models;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Définit un objet pouvant être affiché en mode debug
 * 
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 * 
 */
public interface Debuggable {

   /**
    * Définit ce qu'il faut afficher lorsqu'on souhaite afficher des
    * informations de debug pour l'objet.
    * 
    * @param renderer
    *           Fournit les méthodes nécessaires à l'affichage
    */
   void debugRender(ShapeRenderer renderer);

}
