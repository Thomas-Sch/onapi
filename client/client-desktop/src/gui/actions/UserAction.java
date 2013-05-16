/* ============================================================================
 * Nom du fichier   : UserAction.java
 * ============================================================================
 * Date de création : 11 mai 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package gui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Decrit une action réalisable par l'utilisateur à l'aide de l'interface
 * graphique.
 * 
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas 
 */
abstract public class UserAction implements ActionListener {

   private Object[] dependencies = null;

   public UserAction(Object... dependencies) {
      this.dependencies = dependencies;
   }

   /**
    * Réalise l'action lors du déclenchement de celle-ci par l'utilisateur.
    * 
    * @param e
    *           - l'événement survenu.
    */
   public void actionPerformed(ActionEvent e) {
      execute(e, dependencies);
   }

   /**
    * Réalise les opérations à effectuer lors du déclenchement de l'action.
    * 
    * @param core
    *           - le coeur à disposition de l'action.
    * @param event
    *           - l'événément survenu.
    * @param dependencies
    *           - le tableau des dépendances éventuelles.
    */
   abstract protected void execute(ActionEvent event, Object[] dependencies);

}
