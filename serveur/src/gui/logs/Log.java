/* ============================================================================
 * Nom du fichier   : Log.java
 * ============================================================================
 * Date de création : 16 mai 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package gui.logs;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.LinkedList;

/**
 * Permet de recevoir des messages de log en préfixant automatiquement par
 * l'heure du message. Les messages peuvent être écrits dans un fichier ou dans
 * un LogPanel.
 * 
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 * 
 */
public class Log {

   private String name;

   private File outputFile = null;
   private OutputStream outputStream = null;
   private OutputStreamWriter outputStreamWriter = null;
   private BufferedWriter bufferedWriter = null;

   private int bufferSize = 1;

   private LinkedList<LogMessage> messages = new LinkedList<>();

   private LogPanel messagesPanel = null;

   /**
    * Crée un log avec le nom donné.
    * 
    * @param name
    *           - le nom du log.
    */
   public Log(String name) {
      this.name = name;
   }

   /**
    * Crée un log avec le nom donné. Les messages seront écrits dans le fichier
    * spécifié dès que le tampon contiendra au moins un certain nombre de
    * messages.
    * 
    * @param name
    *           - le nom du log.
    * @param outputFile
    *           - le fichier dans lequel écrire les messages.
    * @param bufferSize
    *           - le nombre de messages attendus avant de les écrire.
    */
   public Log(String name, File outputFile, int bufferSize) {
      this(name);

      setOutputFile(outputFile);
      setBufferSize(bufferSize);
   }

   /**
    * Définit un fichier de sortie pour le log.
    * 
    * @param outputFile
    *           - le fichier dans lequel écrire.
    */
   public void setOutputFile(File outputFile) {

      // Changement de fichier de sortie => fermeture des flux
      closeStreams();

      this.outputFile = outputFile;

      if (outputFile != null && !outputFile.exists()) {
         try {
            outputFile.createNewFile();
         }
         catch (IOException ex) {
            this.outputFile = null;
            push("Log", ex.getMessage());
         }
      }

      // Fermeture des flux actuels
      closeStreams();

      // Ouverture des nouveaux
      try {
         openStreams();
      }
      catch (IOException ex) {
         push("Log", ex.getMessage());

         // Pour s'assurer que tous les flux soient fermés si l'un d'entre eux
         // a eu un problème.
         closeStreams();
      }

   }

   /**
    * Définit le nombre de messages à attendre avant de les écrire.
    * 
    * @param bufferSize
    *           - le nombre de messages attendus avant de les écrire.
    */
   public void setBufferSize(int bufferSize) {
      this.bufferSize = bufferSize > 1 ? bufferSize : 1;
   }

   /**
    * Crée et retourne une représentation graphique de ce log.
    * 
    * @return Une représentation graphique du log.
    */
   public LogPanel createLogPanel() {
      if (messagesPanel == null) {
         messagesPanel = new LogPanel(name);
      }
      return messagesPanel;
   }

   /**
    * Retourne la représentation graphique de ce log.
    * 
    * @return La représentation graphique, null si elle n'a pas été créée.
    */
   public LogPanel getLogPanel() {
      return messagesPanel;
   }

   /**
    * Envoie un message au log.
    * 
    * @param message
    *           - le message à ajouter en fin de log.
    */
   public void push(String message) {
      pushMessage(new LogMessage(message));
   }

   /**
    * Envoie un message au log en spécifiant sa source. La source sera écrite
    * avant le message si elle existe.
    * 
    * @param source
    *           - le nom affiché indiquant la source.
    * @param message
    *           - le message à ajouter en fin de log.
    */
   public void push(String source, String message) {
      pushMessage(new LogMessage(source, message));
   }

   /**
    * Envoie un message au log.
    * 
    * @param message
    *           - le message à ajouter en fin de log.
    */
   public void pushMessage(LogMessage message) {
      messages.add(message);

      // Envoi du message sur la panel de sortie
      if (messagesPanel != null) {
         messagesPanel.push(message);
      }

      // Ecriture dans le fichier s'il y a un minium de messages
      if (messages.size() >= bufferSize) {
         writeToOutput();
      }

   }

   /**
    * Retourne le résultat du test déterminant si les flux de sorties sont
    * opérationnels.
    * 
    * @return Vrai si les flux sont prêts, Faux le cas échéant.
    */
   private boolean isOutputStreamReady() {
      return outputFile != null && outputStream != null
            && outputStreamWriter != null;
   }

   /**
    * Ouvre les flux de sorties.
    * 
    * @throws FileNotFoundException
    *            si un problème survient à l'ouverture.
    */
   private void openStreams() throws FileNotFoundException {
      if (outputFile != null && !isOutputStreamReady()) {
         outputStream = new FileOutputStream(outputFile);
         outputStreamWriter = new OutputStreamWriter(outputStream);
         bufferedWriter = new BufferedWriter(outputStreamWriter);
      }
   }

   /**
    * Ferme les flux de sortie.
    */
   private void closeStreams() {
      if (bufferedWriter != null) {
         try {
            bufferedWriter.close();
         }
         catch (IOException ex) {
            push("Log", ex.getMessage());
         }
      }

      if (outputStreamWriter != null) {
         try {
            outputStreamWriter.close();
         }
         catch (IOException ex) {
            push("Log", ex.getMessage());
         }
      }

      if (outputStream != null) {
         try {
            outputStream.close();
         }
         catch (IOException ex) {
            push("Log", ex.getMessage());
         }
      }

   }

   /**
    * Écrit les 'tampon' premiers messages.
    */
   private void writeToOutput() {
      if (outputFile != null) {

         try {
            if (!isOutputStreamReady()) {
               openStreams();
            }

            int end = Math.min(bufferSize, messages.size());

            for (int i = 0; i < end; i++) {

               bufferedWriter.write(messages.remove(0).getLogFullDate());
               bufferedWriter.newLine();
            }

            bufferedWriter.flush();

         }
         catch (IOException ex) {
            push("Log", ex.getMessage());
         }
      }
      // Si pas de fichier, suppression de l'élément (afin de ne pas remplir
      // inutilement la mémoire)
      else {
         messages.remove(0);
      }
   }

}
