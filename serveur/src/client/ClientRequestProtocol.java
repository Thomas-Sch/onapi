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
   
   private Channel requestChannel;
   
   public ClientRequestProtocol(Channel channel) {
      this.requestChannel = channel;
   }
   
   /**
    * TODO Doit être appelé avant toute autre action !
    * @return
    */
   public Channel connectToServer() {
      System.out.println("DEBUG - want to connect update port.");
      Channel channel;
      
      int port = requestChannel.receiveInt();
      System.out.println("DEBUG - receive " + port + " as port number.");
      
      int code = requestChannel.receiveInt();
      
      System.out.println("DEBUG - receive " + code + " as client code.");
      
      System.out.print("DEBUG - create update channel...");
      channel = new Channel(requestChannel.getAddress(), port, requestChannel.getTimeout());
      System.out.println("done.");
      
      channel.sendInt(code);
      
      System.out.println("DEBUG - wainting for code confirmation...");
      int codeConfirm = channel.receiveInt();
      System.out.println("DEBUG - code confirmation received : " + codeConfirm);
      
      
      if (codeConfirm != code) {
         throw new ProtocolException("Error while connecting to update port");
      }
      
      System.out.println("Connexion du canal update etablie.");
      
      return channel;
   }
   
   /**
    * Envoi un ping au serveur pour déterminer le temps de latence.
    * @return la durée en millisecondes nécessaire pour faire l'aller-retour.
    */
   public long ping() {
      
      synchronized(requestChannel) {
      
         long time = System.currentTimeMillis();
         
         requestChannel.sendProtocolType(ProtocolType.PING);
         
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
      
      synchronized(requestChannel) {
         requestChannel.sendProtocolType(ProtocolType.LOGIN);
         
         // Ne poursuit que si la requête est acceptée par le serveur.
         if (isRequestAccepted(ProtocolType.LOGIN)) {
            requestChannel.sendString(login);
            requestChannel.sendString(password);
            
            loginAccepted = requestChannel.receiveBoolean();
            
            if (loginAccepted) {
               account = (UserAccount)requestChannel.receiveObject();
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
      synchronized(requestChannel) {
         requestChannel.sendProtocolType(ProtocolType.LOGOUT);
         
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
      
      synchronized(requestChannel) {
         requestChannel.sendProtocolType(ProtocolType.ACCOUNT_CREATE);
         
         // Ne poursuit que si le serveur a accepté la requête
         if(isRequestAccepted(ProtocolType.ACCOUNT_CREATE)) {
            requestChannel.sendString(login);
            requestChannel.sendString(password);
            
            accountCreated = requestChannel.receiveBoolean();
            
            if (accountCreated) {
               account = (UserAccount)requestChannel.receiveObject();
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
      
      synchronized(requestChannel) {
         
         requestChannel.sendProtocolType(ProtocolType.JOIN_GAME);
         
         if (isRequestAccepted(ProtocolType.JOIN_GAME)) {
            
            boolean isLobbyFree = requestChannel.receiveBoolean();
            
            if (isLobbyFree) {
               // Réception du nouveau numéro de port pour les updates.
               int portNumber = requestChannel.receiveInt();
               
               updateChannel = new Channel(requestChannel.getAddress(), portNumber,
                                           requestChannel.getTimeout());
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
      synchronized(requestChannel) {
         requestChannel.sendProtocolType(ProtocolType.TEXT_MESSAGE);
         
         if (isRequestAccepted(ProtocolType.TEXT_MESSAGE)) {
            requestChannel.sendString(message);
         }
         else {
            System.out.println("Server refuse to receive text message.");
         }
         
      }
   }
   
   private boolean isRequestAccepted(ProtocolType typeWanted) {
      ProtocolType type = requestChannel.receiveProtocolType();
      
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
