/* ============================================================================
 * Nom du fichier   : Update.java
 * ============================================================================
 * Date de création : 31 mai 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package core.updates;

/**
 * Représente une mise à jour.
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 *
 */
public abstract class Update {

      public Update() {
         
      }

      /**
       * Effectue l'action adéquate pour la mise à jour.
       */
      public abstract void apply(UpdateVisitor v);
      
}
