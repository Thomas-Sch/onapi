/* ============================================================================
 * Nom du fichier   : Controller.java
 * ============================================================================
 * Date de création : 11 mai 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package gui.controller;

import java.awt.Component;


/**
 * 
 * TODO
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 *
 */
public abstract class Controller {
   
   /**
    * Crée un contrôleur.
    * @param objects - Paramètres à initialiser en premier par un enfant.
    */
   protected Controller(Object ... objects) {      
      initComponents(objects);
      initListeners();
   }
   
   protected Controller() {      
      this((Object[])null);
   }
   
   /**
    * Initialise les composants internes du contrôleurs. Cette fonction ne doit
    * pas être appelée, elle est gérée automatiquement par le constructeur.
    */
   protected abstract void initComponents(Object ... objects);
   
   /**
    * Initialise les listeners du contrôleur. Cette fonction ne doit
    * pas être appelée, elle est gérée automatiquement par le constructeur.
    */
   protected abstract void initListeners();
   
   /**
    * Retourne le composant graphique associé au contrôleur.
    * @return le composant graphique, null s'il n'y en a pas.
    */
   public abstract Component getGraphicalComponent();
}
