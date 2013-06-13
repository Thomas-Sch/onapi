/* ============================================================================
 * Nom du fichier   : Language.java
 * ============================================================================
 * Date de création : 11 mai 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package settings;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import utils.xml.XMLGetters;
import utils.Logs;


/**
 * 
 * TODO
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 *
 */
public class Language {
   
   private static final String xmlRootName = "language";
   
   public enum Text {
      /* Déclarations des textes, ajouter les noms des champs de texte
       * nécessaires.
       */
      APP_TITLE,
      LOBBY_TITLE,
      INVENTORY_TITLE,
      ADMIN_TITLE,
      
      VALIDATE_BUTTON,
      CANCEL_BUTTON,
      PLAY_BUTTON,
      INVENTORY_BUTTON,

      LOGIN_LABEL,
      PASSWORD_LABEL,
      SERVER_ADRESS_LABEL,
      SERVER_PORT_LABEL,
      
      READY_LABEL;
      
      
      
      
      /* Fin des déclarations, ne pas modifier ci-dessous
       */
      
      private String text;
      
      @Override
      public String toString(){
         if (text == null) {
            return "Missing String";
         }
         else {
            return text;
         }
      }
      
      private String getXMLName(){
         return super.toString().toLowerCase();
      }
      
      private void setValue(String text) {
         this.text = text;
      }
      
   }
   
   public static void loadFromFile(File languageFile) {
      
      Logs.messages.push("Language", "Start loading texts from " + languageFile.getAbsolutePath());
      
      if (languageFile.exists()) {  
         Document document = null;
         SAXBuilder saxBuilder = new SAXBuilder();
         
         Element root;
         
         try {
            document = saxBuilder.build(languageFile);
         }
         catch(Exception e) {
            Logs.errors.push("Language", "Unable to load the file \"" + languageFile.getName() + "\".");
         }
         
         // Reconfiguration du type enum en lisant le fichier de langue
         String readValue;
         if (document != null) {
            root = document.getRootElement();
            
            for (Text text : Text.values()) {
               readValue = XMLGetters.getStringChild(root, text.getXMLName());
               
               // Ne modifie que si le texte contient au moins un caractère
               if (!readValue.isEmpty()) {
                  text.setValue(readValue);
               }
               
            }
         }         
      }
      else {
         Logs.errors.push("Language", "File \"" + languageFile.getName() + "\" does not exist.");
      }
      
   }
   
   public static void createUpdatedLanguageFile(File languageFile) {
      createLanguageFile(languageFile, true);
   }
   
   public static void createTemplateLanguageFile(File languageFile) {
      createLanguageFile(languageFile, false);
   }
   
   private static void createLanguageFile(File languageFile, boolean createUpdate) {
      
      try {
         if(!languageFile.getParentFile().exists()) {
            languageFile.getParentFile().mkdirs();
         }
      }
      catch(Exception e) {
         Logs.errors.push("Settings", "Unable to create language folder.");
      }
      
      Element root = new Element(xmlRootName);
      Document document = new Document(root);
      
      Element textElement;
      for (Text text : Text.values()) {
         textElement = new Element(text.getXMLName());
         
         if (createUpdate) {
            textElement.setText(text.toString());
         }
         else {
            textElement.setText("TODO");
         }
         
         root.addContent(textElement);
      }
      
      /* Creation du fichier à l'emplacement voulu
       * ----------------------------------------------------------------------
       */
      FileOutputStream fileOutputStream = null; 
      try {
         fileOutputStream = new FileOutputStream(languageFile);
         
         XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
         
         outputter.output(document, fileOutputStream);
      }
      catch (IOException ex) {
         Logs.errors.push("Language",
                               "Unable to create template file for language.");
      }
      finally {
         if (fileOutputStream != null) {
            try {
               fileOutputStream.close();
            }
            catch (IOException ex) {
               Logs.errors.push("Language",
                                     "Error while closing the output stream.");
            }
         }
      }
      
   }

}
