/* ============================================================================
 * Nom du fichier   : Channel.java
 * ============================================================================
 * Date de création : 8 mai 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package utils.connections;

import utils.connections.exceptions.ChannelException;
import utils.connections.exceptions.TimeOutException;
import utils.connections.protocol.ProtocolType;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 * Canal utilisé pour se connecter à une machine distante.
 * 
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 * 
 */
public class Channel {

   private static final int DEFAULT_TIMEOUT = 5000;

   private int timeout;

   private ObjectInputStream inputStream;
   private ObjectOutputStream outputStream;

   private Socket socket;

   /**
    * Etablit un canal de communication à partir d'un socket déjà existant.
    * <p>
    * <u><b>Principalement</b></u> utilisé par le serveur pour établir le
    * contact avec un nouveau client.
    * 
    * @param socket
    *           - le socket à utiliser.
    * @param timeout
    *           - le temps en millisecondes avant la fermeture du canal.
    */
   public Channel(Socket socket, int timeout) {
      initConnection(socket, timeout);
   }

   /**
    * Etablit un canal de communication sur le port donné de la machine distante
    * correspondant à l'adresse spécifiée.
    * <p>
    * <u><b>Principalement</b></u> utilisé par le client pour établir une
    * connection avec le serveur donné
    * 
    * @param address
    *           - l'adresse de la machine distante.
    * @param portNumber
    *           - le port de la machine distante.
    * @param timeout
    *           - le temps en millisecondes avant la fermeture du canal.
    */
   public Channel(String address, int portNumber, int timeout) {
      initConnection(address, portNumber, timeout);
   }

   /**
    * Envoie la chaîne de caractères donnée sur le canal.
    * 
    * @param message
    *           - la chaîne à transmettre.
    */
   public void sendString(String message) {
      try {
         outputStream.writeUTF(message);
         outputStream.flush();
      }
      catch (IOException e) {
         throw new ChannelException("Unable to send the String");
      }
   }

   /**
    * Retourne la chaîne de caractères reçue par le canal.
    * 
    * @return la chaîne de caractères reçue.
    */
   public String receiveString() {
      String result = null;

      try {
         result = inputStream.readUTF();
      }
      catch (SocketTimeoutException e) {
         throw new TimeOutException("Timeout while waiting for a String");
      }
      catch (IOException e) {
         throw new ChannelException("Read operation for String aborted");
      }

      return result;
   }

   /**
    * Envoie un objet donné sur le canal.
    * 
    * @param object
    *           - l'objet à envoyer.
    */
   public void sendObject(Object object) {
      try {
         outputStream.writeObject(object);
         outputStream.flush();
      }
      catch (IOException e) {
         throw new ChannelException("Unable to send the Object");
      }
   }

   /**
    * Retourne l'objet reçu par le canal.
    * 
    * @return l'objet reçu.
    */
   public Object receiveObject() {
      Object result = null;

      try {
         result = inputStream.readObject();
      }
      catch (SocketTimeoutException e) {
         throw new TimeOutException("Timeout while waiting for an Object");
      }
      catch (IOException e) {
         throw new ChannelException("Read operation for Object aborted");
      }
      catch (ClassNotFoundException e) {
         throw new ChannelException("Class not found for the received Object");
      }

      return result;
   }
   
   /**
    * Envoie un type de protocole donné sur le canal. Sert à informer la machine
    * distante des instructions à venir.
    * 
    * @param type
    *           - le type de protocole à envoyer.
    */
   public void sendProtocolType(ProtocolType type) {
      try {
         outputStream.writeObject(type);
         outputStream.flush();
      }
      catch (IOException e) {
         throw new ChannelException("Unable to send the ProtocolType");
      }
   }
   
   /**
    * Retourne le type de protocole reçu par le canal.
    * 
    * @return le type de protocole reçu.
    */
   public ProtocolType receiveProtocolType() {
      ProtocolType result = null;

      try {
         result = (ProtocolType)inputStream.readObject();
      }
      catch (SocketTimeoutException e) {
         throw new TimeOutException("Timeout while waiting for a ProtocolType");
      }
      catch (IOException e) {
         throw new ChannelException("Read operation for ProtocolType aborted");
      }
      catch (ClassNotFoundException e) {
         throw new ChannelException("Class not found for the received ProtocolType");
      }

      return result;
   }

   /*
    * -------------------------------------------------------------------------
    * Fonctions internes
    * -------------------------------------------------------------------------
    */
   private void initConnection(Socket socket, int timeout) {
      initTimeout(timeout);
      this.socket = socket;
      setupSocket();
      initStreams();
   }

   private void initConnection(String address, int portNumber, int timeout) {
      initTimeout(timeout);
      initSocket(address, portNumber);
      setupSocket();
      initStreams();
   }

   private void initTimeout(int timeout) {
      if (timeout >= 0) {
         this.timeout = timeout;
      }
      else {
         this.timeout = DEFAULT_TIMEOUT;
      }
   }

   private void initSocket(String address, int portNumber) {
      try {
         socket = new Socket(address, portNumber);
      }
      catch (IOException e) {
         throw new ChannelException("Unable to connect to " + address + " / "
               + portNumber);
      }
   }

   private void setupSocket() {
      try {
         socket.setSoTimeout(timeout);
      }
      catch (IOException e) {
         throw new ChannelException("Unable to setup timeout for socket");
      }
   }

   private void initStreams() {
      try {
         outputStream = new ObjectOutputStream(socket.getOutputStream());
         // s'assure que le header ne bloque pas la lecture (c.f. API)
         outputStream.flush();

         inputStream = new ObjectInputStream(socket.getInputStream());

      }
      catch (IOException e) {
         throw new ChannelException("Unable to init streams");
      }
   }

}
