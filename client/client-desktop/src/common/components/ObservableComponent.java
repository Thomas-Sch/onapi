/* ============================================================================
 * Nom du fichier   : ObservableComponent.java
 * ============================================================================
 * Date de création : 13 juin 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package common.components;

import java.util.Observable;

/**
 * Représente un objet observable en fournissant quelques outils supplémentaires
 * par rapport à la classe Java de base.
 * 
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 * 
 */
public class ObservableComponent extends Observable {

   private boolean multiChangeEnable = false;

   /**
    * Crée un objet observable.
    */
   public ObservableComponent() {
      super();
   }

   /**
    * Raccourci d'écriture pour indiquer que des changements ont eu lieu et en
    * informer les observateurs.
    */
   protected void setChangedAndNotifyObservers() {
      setChanged();
      notifyObservers();
   }

   /**
    * Raccourci d'écriture pour indiquer que des changements ont eu lieu et en
    * informer les observateurs.
    */
   protected void setChangedAndNotifyObservers(Object arg) {
      setChanged();
      notifyObservers(arg);
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
