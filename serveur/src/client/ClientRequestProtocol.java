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
      ProtocolType proType;
      
      synchronized(channel) {
      
         long time = System.currentTimeMillis();
         
         channel.sendProtocolType(ProtocolType.PING);
         
         proType = channel.receiveProtocolType();
         
         if (proType == ProtocolType.PING) {
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
   public UserAccount authentification(String login, String password) {
      UserAccount account = null;
      boolean loginAccepted = false;
      
      synchronized(channel) {
         channel.sendProtocolType(ProtocolType.LOGIN);
         
         // Ne poursuit que si la requête est acceptée par le serveur.
         if (isRequestAccepted()) {
            channel.sendString(login);
            channel.sendString(password);
            
            loginAccepted = channel.receiveBoolean();
            
            if (loginAccepted) {
               account = (UserAccount)channel.receiveObject();
            }
         }
      }
      
      return account;
   }
   
   /**
    * Demande au serveur de créer un compte correspondant au nom de compte
    * (login) et de lui associer le mot de passe donné.
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
         if(isRequestAccepted()) {
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
      
      synchronized(channel) {
         // TODO
      }
      
      return null;
   }
   
   /**
    * Se déconnecte du serveur.
    */
   public void disconnect() {
      synchronized(channel) {
         channel.sendProtocolType(ProtocolType.LOGOUT);
         channel.receiveProtocolType();
      }
   }
   
   /**
    * Protocole bidon pour test.
    * @param message
    */
   @Deprecated
   public void sendMessage(String message) {
      synchronized(channel) {
         channel.sendProtocolType(ProtocolType.TEXT_MESSAGE);
         channel.sendString(message);
      }
   }
   
   private boolean isRequestAccepted() {
      ProtocolType type = channel.receiveProtocolType();
      
      if (type == ProtocolType.ACCEPT) {
         return true;
      }
      else if (type == ProtocolType.REFUSE) {
         return false;
      }
      else {
         throw new ProtocolException("Was wainting for " + ProtocolType.ACCEPT
               + " or " + ProtocolType.REFUSE + " but received " + type);
      }
      
   }

}
