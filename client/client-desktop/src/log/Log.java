/* ============================================================================
 * Nom du fichier   : Log.java
 * ============================================================================
 * Date de création : 7 avr. 2013
 * ============================================================================
 * Auteurs          : Biolzi Sébastien
 *                    Brito Carvalho Bruno
 *                    Decorvet Grégoire
 *                    Schweizer Thomas
 *                    Sinniger Marcel
 * ============================================================================
 */
package log;

import gui.component.JLogPanel;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.LinkedList;

/**
 * TODO
 * @author Biolzi Sébastien
 * @author Brito Carvalho Bruno
 * @author Decorvet Grégoire
 * @author Schweizer Thomas
 * @author Sinniger Marcel
 *
 */
public class Log {
   
   private String name;
   
   private File outputFile = null;
   private OutputStream outputStream = null;
   private OutputStreamWriter outputStreamWriter = null;
   private BufferedWriter bufferedWriter = null;
   
   private int bufferSize = 1;
   
   private LinkedList<LogMessage> messages = new LinkedList<LogMessage>();
   
   private JLogPanel messagesPanel = null;
   
   public Log(String name) {
      this.name = name;
   }
   
   public Log(String name, File outputFile, int bufferSize) {
      this(name);
      
      setOutputFile(outputFile);
      setBufferSize(bufferSize);
   }
   
   public void setOutputFile(File outputFile) {
      
      // Changement de ficier de sortie => fermeture des flux
      closeStreams();
      
      this.outputFile = outputFile;
      
      if (outputFile != null && !outputFile.exists()) {
         try {
            //outputFile.getParentFile().mkdirs();
            outputFile.createNewFile();
         }
         catch(IOException ex) {
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
   
   public void setBufferSize(int bufferSize) {
      this.bufferSize = bufferSize > 1 ? bufferSize : 1;
   }
   
   public JLogPanel createLogPanel() {
      if(messagesPanel == null) {
         messagesPanel = new JLogPanel(name);
      }
      return messagesPanel;
   }
   
   public JLogPanel getLogPanel() {
      return messagesPanel;
   }
   
   public void push(String message) {
      pushMessage(new LogMessage(message));
   }
   
   public void push(String source, String message) {
      pushMessage(new LogMessage(source, message));
   }
   
   public void pushMessage(LogMessage message) {
      messages.add(message);
      
      // Envoi du message sur la panel de sortie
      if (messagesPanel != null) {
         messagesPanel.push(message);
      }
      
      // Ecriture dans le fichier s'il y a un minium de messages
      if(messages.size() >=  bufferSize) {
         writeToOutput();
      }
      
   }
   
   private boolean isOutputStreamReady() {
      
      return outputFile != null
             && outputStream != null
             && outputStreamWriter != null;
      
   }
   
   private void openStreams() throws FileNotFoundException {
      
      if (outputFile != null && !isOutputStreamReady()) {
         outputStream = new FileOutputStream(outputFile);
         outputStreamWriter = new OutputStreamWriter(outputStream);
         bufferedWriter = new BufferedWriter(outputStreamWriter);
      }
      
   }
   
   private void closeStreams() {
      if(bufferedWriter != null) {
         try {
            bufferedWriter.close();
            
            //bufferedWriter = null;
         }
         catch (IOException ex) {
            push("Log", ex.getMessage());
         }
      }
      
      if(outputStreamWriter != null) {
         try {
            outputStreamWriter.close();
            
            //bufferedWriter = null;
         }
         catch (IOException ex) {
            push("Log", ex.getMessage());
         }
      }
      
      if(outputStream != null) {
         try {
            outputStream.close();
            
            //bufferedWriter = null;
         }
         catch (IOException ex) {
            push("Log", ex.getMessage());
         }
      }
      
   }
   
   private void writeToOutput() {
      if (outputFile != null) {
         
         try {
         
            if (!isOutputStreamReady()) {
               openStreams();
            }
            
            int end = Math.min(bufferSize, messages.size());
            
            for(int i = 0; i < end ; i++) {
               
               bufferedWriter.write(messages.remove(0).getLogFullDate());
               bufferedWriter.newLine();
            }
            
            bufferedWriter.flush();
            
         }
         catch (IOException ex) {
            push("Log", ex.getMessage());
         }
         
         
      }      
   }

}
