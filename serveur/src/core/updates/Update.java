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
 * TODO
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
       * the method for applying visitors to Update
       */
      public abstract void apply(StandardUpdateVisitor v);
      
}
