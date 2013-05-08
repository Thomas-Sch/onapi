/* ============================================================================
 * Nom du fichier   : Connection.java
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

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;
import java.rmi.ConnectException;

import utils.connections.exceptions.ChannelException;
import utils.connections.exceptions.TimeOutException;

/**
 * TODO
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
   
   public Channel(Socket socket, int timeout) {
      initConnection(socket, timeout);
   }
   
   public Channel(String address, int portNumber, int timeout) {
      initConnection(address, portNumber, timeout);
   }
   
   public void sendString(String message) {
      try {
         outputStream.writeUTF(message);
         outputStream.flush();
      }
      catch (IOException e) {
         throw new ChannelException("Unable to send the String", e);
      }
   }
   
   public String receiveString() {
      String result = null;
      
      try {
         result = inputStream.readUTF();
      }
      catch (SocketTimeoutException e) {
         throw new TimeOutException("Timeout while waiting for a String", e);
      }
      catch (IOException e) {
         throw new ChannelException("Read operation for String aborted", e);
      }
      
      return result;
   }
   
   public void sendObject(Object o) {
      try {
         outputStream.writeObject(o);
         outputStream.flush();
      }
      catch (IOException e) {
         throw new ChannelException("Unable to send the Object", e);
      }
   }
   
   public Object receiveObject() {
      Object result = null;
      
      try {
         result = inputStream.readObject();
      }
      catch (SocketTimeoutException e) {
         throw new TimeOutException("Timeout while waiting for an Object", e);
      }
      catch (IOException e) {
         throw new ChannelException("Read operation for Object aborted", e);
      }
      catch (ClassNotFoundException e) {
         throw new ChannelException("Class not fount for the received Object", e);
      }
      
      return result;
   }
   
   
   /* -------------------------------------------------------------------------
    *   Fonctions internes
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
         throw new ChannelException("Unable to connect to " + address + " / " + portNumber, e);
      }
   }
   
   private void setupSocket() {
      try {
         socket.setSoTimeout(timeout);
      }
      catch (IOException e) {
         throw new ChannelException("Unable to setup timeout for socket", e);
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
         throw new ChannelException("Unable to init streams", e);
      }
   }

}
