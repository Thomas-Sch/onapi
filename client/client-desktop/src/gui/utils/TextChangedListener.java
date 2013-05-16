/* ============================================================================
 * Nom du fichier   : TextChangedListener.java
 * ============================================================================
 * Date de création : 11 mai 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package gui.utils;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;

/**
 * 
 * TODO
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
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
