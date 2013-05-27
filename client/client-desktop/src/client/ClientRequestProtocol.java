/* ============================================================================
 * Nom du fichier   : Protocol.java
 * ============================================================================
 * Date de création : 8 mai 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package client;

import common.components.UserAccount;
import common.connections.Channel;
import common.connections.exceptions.ProtocolException;
import common.connections.exceptions.TimeOutException;
import common.connections.protocol.ProtocolType;


/**
 * Classe permettant de rassembler les protocoles utilisés par le client pour
 * communiquer ses requêtes au serveur.
 * 
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 *
 */
public class ClientRequestProtocol {
   
   private Channel channel;
   
   public ClientRequestProtocol(Channel channel) {
      this.channel = channel;
   }
   
   /**
    * Envoi un ping au serveur pour déterminer le temps de latence.
    * @return la durée en millisecondes nécessaire pour faire l'aller-retour.
    */
   public long ping() {
      
      synchronized(channel) {
      
         long time = System.currentTimeMillis();
         
         channel.sendProtocolType(ProtocolType.PING);
         
         if (isRequestAccepted(ProtocolType.PING)) {
            return System.currentTimeMillis() - time;
         }
         else {
            throw new ProtocolException("Wrong protocol for PING");
         }
         
      }
      
      
   }
   
   /**
    * Envoie un message bidon au serveur pour conserver la connexion.
    * @return vrai si la connexion en encore active, faux sinon.
    */
   public boolean keepAlive() {
      boolean stillAlive = false;
      
      try {
         ping();
         
         stillAlive = true;
      }
      catch (TimeOutException e) { }
      
      return stillAlive;
   }
   
   /**
    * Se connecte au compte client correspondant aux paramètres donnés.
    * @param login - le nom du compte.
    * @param password - le mot de passe associé au login.
    * @return le compte client si les informations données sont exactes, null
    * le cas échéant.
    */
   public UserAccount login(String login, String password) {
      UserAccount account = null;
      boolean loginAccepted = false;
      
      synchronized(channel) {
         channel.sendProtocolType(ProtocolType.LOGIN);
         
         // Ne poursuit que si la requête est acceptée par le serveur.
         if (isRequestAccepted(ProtocolType.LOGIN)) {
            channel.sendString(login);
            channel.sendString(password);
            
            loginAccepted = channel.receiveBoolean();
            
            if (loginAccepted) {
               account = (UserAccount)channel.receiveObject();
            }
         }
         else {
            System.out.println("Login request refuse by server.");
         }
      }
      
      return account;
   }
   
   /**
    * Se déconnecte du serveur.
    */
   public boolean logout() {
      boolean success = false;
      synchronized(channel) {
         channel.sendProtocolType(ProtocolType.LOGOUT);
         
         if (isRequestAccepted(ProtocolType.LOGOUT)) {
            success = true;
            System.out.println("Logout done.");
         }
         else {
            System.out.println("Logout refused.");
         }
      }
      return success;
   }
   
   /**
    * Demande au serveur de créer un compte correspondant au nom de compte
    * (login) et de lui associer le mot de passe donné. Si le compte est créé,
    * le client sera automatiquement considéré comme authentifié auprès de ce
    * compte. Un login n'est plus nécessaire pour cette session.
    * @param login - le nom du nouveau compte.
    * @param password - le mot de passe associé au compte créé.
    * @return le compte client si le compte a pu être créé, null le cas
    * échéant.
    */
   public UserAccount createAccount(String login, String password) {
      UserAccount account = null;
      boolean accountCreated = false;
      
      synchronized(channel) {
         channel.sendProtocolType(ProtocolType.ACCOUNT_CREATE);
         
         // Ne poursuit que si le serveur a accepté la requête
         if(isRequestAccepted(ProtocolType.ACCOUNT_CREATE)) {
            channel.sendString(login);
            channel.sendString(password);
            
            accountCreated = channel.receiveBoolean();
            
            if (accountCreated) {
               account = (UserAccount)channel.receiveObject();
            }
         }
      }
      
      return account;
   }
   
   /**
    * Demande à rejoindre une partie. Un nouveau canal de communication est
    * alors créé, servant de canal d'écoute pour recevoir les mises à jour du
    * serveur.
    * @return le canal de réception en provenance du serveur.
    */
   public Channel joinGame() {
      
      Channel updateChannel = null;
      
      synchronized(channel) {
         
         channel.sendProtocolType(ProtocolType.JOIN_GAME);
         
         if (isRequestAccepted(ProtocolType.JOIN_GAME)) {
            
            boolean isLobbyFree = channel.receiveBoolean();
            
            if (isLobbyFree) {
               // Réception du nouveau numéro de port pour les updates.
               int portNumber = channel.receiveInt();
               
               updateChannel = new Channel(channel.getAddress(), portNumber,
                                           channel.getTimeout());
            }
            else {
               System.out.println("No free lobbby for the moment.");
            }
            
         }
         else {
            System.out.println("Join game request refused by server.");
         }
         
      }
      
      return updateChannel;
   }
   
   
   
   /**
    * Protocole bidon pour test.
    * @param message
    */
   @Deprecated
   public void sendMessage(String message) {
      synchronized(channel) {
         channel.sendProtocolType(ProtocolType.TEXT_MESSAGE);
         
         if (isRequestAccepted(ProtocolType.TEXT_MESSAGE)) {
            channel.sendString(message);
         }
         else {
            System.out.println("Server refuse to receive text message.");
         }
         
      }
   }
   
   private boolean isRequestAccepted(ProtocolType typeWanted) {
      ProtocolType type = channel.receiveProtocolType();
      
      if (type == typeWanted) {
         return true;
      }
      else if (type == ProtocolType.REFUSE) {
         return false;
      }
      else {
         throw new ProtocolException("Was wainting for " + typeWanted
               + " or " + ProtocolType.REFUSE + " but received " + type);
      }
      
   }

}
