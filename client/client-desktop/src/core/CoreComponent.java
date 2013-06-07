/* ============================================================================
 * Nom du fichier   : CoreComponent.java
 * ============================================================================
 * Date de création : 7 juin 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package core;

import java.util.Observable;

/**
 * 
 * TODO
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 *
 */
public class CoreComponent extends Observable {
   
   private boolean multiChangeEnable = false;
   
   public CoreComponent() {
      
   }
   
   /**
    * A appeler avant d'effectuer plusieurs changements rapides de l'objet.
    */
   public void startMultipleChanges() {
      multiChangeEnable = true;
   }
   
   /**
    * A appeler après avoir effectué plusieurs changements rapides de l'objet
    * pour notifier alors les observateurs.
    */
   public void endMultipleChanges() {
      multiChangeEnable = false;
      notifyObservers(this);
   }
   
   @Override
   public void notifyObservers() {
      if (!multiChangeEnable) {
         super.notifyObservers();
      }
   }
   
   @Override
   public void notifyObservers(Object arg) {
      if (!multiChangeEnable) {
         super.notifyObservers(arg);
      }
   }
   

}
