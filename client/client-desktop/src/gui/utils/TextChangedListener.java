/* ============================================================================
 * Nom du fichier   : TextChangedListener.java
 * ============================================================================
 * Date de création : 1 mai 2013
 * ============================================================================
 * Auteurs          : Biolzi Sébastien
 *                    Brito Carvalho Bruno
 *                    Decorvet Grégoire
 *                    Schweizer Thomas
 *                    Sinniger Marcel
 * ============================================================================
 */
package gui.utils;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;

/**
 * Listener simplifié réagissant au changement d'un texte.
 * @author Biolzi Sébastien
 * @author Brito Carvalho Bruno
 * @author Decorvet Grégoire
 * @author Schweizer Thomas
 * @author Sinniger Marcel
 *
 */
abstract public class TextChangedListener implements DocumentListener {

   @Override
   public void changedUpdate(DocumentEvent arg0) {
      textChanged(arg0);
   }

   @Override
   public void insertUpdate(DocumentEvent arg0) {
      textChanged(arg0);
   }

   @Override
   public void removeUpdate(DocumentEvent arg0) {
      textChanged(arg0);
   }
   
   /**
    * Fonction appelée lors d'un changement de contenu du texte.
    * @param event - l'évenement survenu.
    */
   abstract public void textChanged(DocumentEvent event);
   
   /**
    * Ajoute le listener donné au composant spécifié.
    * @param textComponent - le champ texte étant la source de l'événement.
    * @param listener - le listener gérant l'événement.
    */
   public static void addListener(JTextComponent textComponent,
                                  TextChangedListener listener) {
      textComponent.getDocument().addDocumentListener(listener);
   }

}
