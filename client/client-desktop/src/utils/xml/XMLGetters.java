/* ============================================================================
 * Nom du fichier   : XMLGetters.java
 * ============================================================================
 * Date de création : 17 avr. 2013
 * ============================================================================
 * Auteurs          : Biolzi Sébastien
 *                    Brito Carvalho Bruno
 *                    Decorvet Grégoire
 *                    Schweizer Thomas
 *                    Sinniger Marcel
 * ============================================================================
 */
package utils.xml;

import org.jdom2.Element;

import utils.Logs;

/**
 * Classe utilitaire regroupant des fonctions générales pour récupérer des
 * informations dans une arborescence xml.
 * @author Biolzi Sébastien
 * @author Brito Carvalho Bruno
 * @author Decorvet Grégoire
 * @author Schweizer Thomas
 * @author Sinniger Marcel
 *
 */
public class XMLGetters {
   
   /**
    * Retourne la valeur entière d'un enfant direct pour le noeud donné en
    * paramètre.
    * @param element - le noeud auquel l'enfant est directement rataché.
    * @param childName - le nom de l'enfant .
    * @return la valeur entière associée, ou 0 en cas d'échec.
    */
   public static int getIntegerChild(Element element, String childName) {
      return getIntegerChild(element, childName, 0);
   }
   
   /**
    * Retourne la valeur entière d'un enfant direct pour le noeud donné en
    * paramètre. Permet de spécifier la valeur à retourner en cas d'échec.
    * @param element - le noeud auquel l'enfant est directement rataché.
    * @param childName - le nom de l'enfant.
    * @param defaultValue - la valeur à retourner en cas d'échec.
    * @return la valeur entière associée, ou la valeur donnée en cas d'échec.
    */
   public static int getIntegerChild(Element element, String childName,
                                     int defaultValue) {
      int result = defaultValue;
      
      try {
         String value = getStringChild(element, childName);
         
         if (!value.isEmpty()) {
            result = Integer.parseInt(value);
         }
      }
      catch (NumberFormatException ex) {
         Logs.errors.push("XMLGetters", "Integer parse error for child \""
                               + element.getName() + "." + childName + "\"");
      }
      
      return result;
   }
   
   /**
    * Retourne la valeur entière d'un attribut du noeud donné en paramètre.
    * @param element - le noeud auquel appartient l'attribut.
    * @param name - le nom de l'attribut à récupérer.
    * @return la valeur de l'attribut, ou 0 en cas d'échec
    */
   public static int getIntegerAttribute(Element element, String name) {
      return getIntegerAttribute(element, name, 0);
   }
   
   /**
    * Retourne la valeur entière d'un attribut du noeud donné en paramètre.
    * Permet de spécifier la valeur à retourner en cas d'échec.
    * @param element - le noeud auquel appartient l'attribut.
    * @param name - le nom de l'attribut à récupérer.
    * @param defaultValue - la valeur à retourner en cas d'échec.
    * @return la valeur de l'attribut, ou la valeur donnée en cas d'échec.
    */
   public static int getIntegerAttribute(Element element, String attributeName,
                                         int defaultValue) {
      int result = defaultValue;
      
      try {
         result = Integer.parseInt(getStringAttribute(element, attributeName));
      }
      catch (NumberFormatException ex) {
         Logs.errors.push("XMLGetters",
                               "Integer parse error for attribute \""
                               + element.getName() + "." + attributeName
                               + "\".");
      }
      
      return result;
   }
   
   /**
    * Retourne la chaîne de caractères contenue par l'enfant direct du noeud
    * donné.
    * @param element - le noeud auquel l'enfant est directement rattaché.
    * @param childName - le nom de l'enfant direct.
    * @return la chaîne de caractère, ou une chaîne vide si l'enfant n'existe
    * pas pour le noeud donné.
    */
   public static String getStringChild(Element element, String childName) {
      return getStringChild(element, childName, "");
   }
   
   /**
    * Retourne la chaîne de caractères contenue par l'enfant direct du noeud
    * donné. Permet de spécifier la valeur à retourner si l'enfant n'existe
    * pas.
    * @param element - le noeud auquel l'enfant est directement rattaché.
    * @param childName - le nom de l'enfant direct.
    * @param defaultValue - la valeur à retourner si l'enfant n'existe pas.
    * @return la chaîne de caractère, ou la valeur par défaut spécifiée si
    * l'enfant n'existe pas pour le noeud donné.
    */
   public static String getStringChild(Element element, String childName, String defaultValue) {
      String result;
      
      result = element.getChildTextTrim(childName);
      
      if (result == null) {
         Logs.errors.push("XMLGetters",
                               "Missing XML child : \"" + childName + "\".");
         result = defaultValue;
      }
      
      return result;
   }
   
   /**
    * Retourne la chaîne de caractères d'un attribut du noeud donné en paramètre.
    * @param element - le noeud auquel appartient l'attribut.
    * @param name - le nom de l'attribut à récupérer.
    * @return la valeur de l'attribut, ou une chaîne vide si l'attribut
    * n'existe pour le noeud donné.
    */
   public static String getStringAttribute(Element element, String attributeName) {
      return getStringAttribute(element, attributeName, "");
   }
   
   /**
    * Retourne la chaîne de caractères d'un attribut du noeud donné en paramètre.
    * Permet de spécifier la valeur à retourner en cas d'échec.
    * @param element - le noeud auquel appartient l'attribut.
    * @param name - le nom de l'attribut à récupérer.
    * @param defaultValue - la valeur à retourner en cas d'échec.
    * @return la valeur de l'attribut, ou la valeur donnée si l'attribut
    * n'existe pas pour le noeud donné.
    */
   public static String getStringAttribute(Element element, String attributeName, String defaultValue) {
      String result = defaultValue;
      
      try {
         result = element.getAttributeValue(attributeName).trim();
      }
      catch (NullPointerException ex) {
         Logs.errors.push("XMLGetters", "Missing XML attribute : \""
                               + attributeName + "\".");
      }
      
      return result;
   }

}
