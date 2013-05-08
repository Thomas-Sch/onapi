/* ============================================================================
 * Nom du fichier   : XMLSetters.java
 * ============================================================================
 * Date de création : 18 avr. 2013
 * ============================================================================
 * Auteurs          : Biolzi Sébastien
 *                    Brito Carvalho Bruno
 *                    Decorvet Grégoire
 *                    Schweizer Thomas
 *                    Sinniger Marcel
 * ============================================================================
 */
package utils.xml;

import org.jdom2.Attribute;
import org.jdom2.Element;

import utils.MidasLogs;

/**
 * Classe utilitaire regroupant des fonctions générales pour modifier des
 * informations dans une arborescence xml.
 * @author Biolzi Sébastien
 * @author Brito Carvalho Bruno
 * @author Decorvet Grégoire
 * @author Schweizer Thomas
 * @author Sinniger Marcel
 *
 */
public class XMLModifiers {
   
   /**
    * Ajoute un enfant direct au noeud parent donné, et affecte la valeur
    * entière spécifiée à l'enfant.
    * @param parent - le parent auquel accrocher l'enfant.
    * @param childName - l'enfant à accrocher au noeud parent.
    * @param childValue - la valeur de l'enfant.
    */
   public static void addChild(Element parent, String childName, int childValue) {
      addChild(parent, childName, Integer.toString(childValue));
   }
   
   /**
    * Ajoute un enfant direct au noeud parent donné, et affecte la valeur
    * spécifiée à l'enfant.
    * @param parent - le parent auquel accrocher l'enfant.
    * @param childName - l'enfant à accrocher au noeud parent.
    * @param childValue - la valeur de l'enfant.
    */
   public static void addChild(Element parent, String childName, String childValue) {
      if (parent == null) {
         MidasLogs.errors.push("XmlModifiers", "Parent is missing.");
      }
      else if (childName == null || childName.isEmpty()) {
         MidasLogs.errors.push("XmlModifiers", "Child name is missing.");
      }
      else if (childValue == null) {
         MidasLogs.errors.push("XmlModifiers", "Child value is missing.");
      }
      else {
         Element newChild = new Element(childName);
         newChild.setText(childValue);
         parent.addContent(newChild);
      }
   }
   
   /**
    * Ajoute un attribut au noeud donné, et affecte la valeur entière spécifiée
    * à l'attribut.
    * @param parent - le parent auquel ajouter l'attribut.
    * @param attributeName - l'attribut à ajouter au noeud.
    * @param attributeValue - la valeur de l'attribut.
    */
   public static void addAttribute(Element node, String attributeName, int attributeValue) {
      addAttribute(node, attributeName, Integer.toString(attributeValue));
   }
   
   /**
    * Ajoute un attribut au noeud donné, et affecte la valeur spécifiée à
    * l'attribut.
    * @param parent - le parent auquel ajouter l'attribut.
    * @param attributeName - l'attribut à ajouter au noeud.
    * @param attributeValue - la valeur de l'attribut.
    */
   public static void addAttribute(Element node, String attributeName, String attributeValue) {
      if (node == null) {
         MidasLogs.errors.push("XmlModifiers", "Node is missing.");
      }
      else if (attributeName == null || attributeName.isEmpty()) {
         MidasLogs.errors.push("XmlModifiers", "Attribute name is missing.");
      }
      else if (attributeValue == null) {
         MidasLogs.errors.push("XmlModifiers", "Attribute value is missing.");
      }
      else {
         node.setAttribute(new Attribute(attributeName, attributeValue));
      }
   }

}
