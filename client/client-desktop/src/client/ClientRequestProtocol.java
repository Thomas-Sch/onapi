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

   private boolean initDone = false;
   
   private Channel requestChannel;

   public ClientRequestProtocol() {

   }
   
   /**
    * <p>
    * <b><u>ATTENTION :</b></u> le canal doit déjà être connecté au serveur.
    * @param channel
    */
   @Deprecated
   public ClientRequestProtocol(Channel channel) {
      requestChannel = channel;
      initDone = true;
   }

   /**
    * Établit une connexion avec le serveur dont les coordonnées sont passées en
    * paramètres. Retourne alors les deux canaux de communication avec le
    * serveur, à savoir celui servant pour envoyer les requêtes du client, et
    * celui servant à écouter les informations transmises par le serveur.
    * <p>
    * Méthode à appeler <b><u>avant toute autre</b></u>.
    * 
    * @param address
    *           - l'adresse ip du serveur.
    * @param portNumber
    *           - le numéro de port du serveur.
    * @param timeout
    *           - le temps de réponse maximal avant de fermer la connexion.
    * @return Le canal à utiliser pour les requêtes client, ainsi que le canal
    *         recevant les informations du serveur.
    */
   public ConnectionChannels connectToServer(String address, int portNumber,
         int timeout) {
      // Initialisation du canal pour les requêtes du client
      requestChannel = new Channel(address, portNumber, timeout);

      return new ConnectionChannels(requestChannel, connectToServer());
   }

   /**
    * Établit et retourne le canal des mises à jours du serveur.
    * 
    * @return Le canal d'informations en provenance du serveur.
    */
   private Channel connectToServer() {
      Channel channel;

      synchronized (requestChannel) {
         int port = requestChannel.receiveInt();
         int code = requestChannel.receiveInt();

         channel = new Channel(requestChannel.getAddress(), port,
               requestChannel.getTimeout());

         channel.sendInt(code);

         int codeConfirm = channel.receiveInt();

         if (codeConfirm != code) {
            throw new ProtocolException("Error while connecting to update port");
         }

      }

      initDone = true;

      return channel;
   }

   /**
    * Envoi un ping au serveur pour déterminer le temps de latence.
    * 
    * @return la durée en millisecondes nécessaire pour faire l'aller-retour.
    */
   public long ping() {

      if (!initDone) {
         throw new ProtocolException(
               "Error, connection to server has not been initialized");
      }

      synchronized (requestChannel) {

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
    * 
    * @return vrai si la connexion en encore active, faux sinon.
    */
   public boolean keepAlive() {

      if (!initDone) {
         throw new ProtocolException(
               "Error, connection to server has not been initialized");
      }

      boolean stillAlive = false;

      try {
         ping();

         stillAlive = true;
      }
      catch (TimeOutException e) {
      }

      return stillAlive;
   }

   /**
    * Se connecte au compte client correspondant aux paramètres donnés.
    * 
    * @param login
    *           - le nom du compte.
    * @param password
    *           - le mot de passe associé au login.
    * @return le compte client si les informations données sont exactes, null le
    *         cas échéant.
    */
   public UserAccount login(String login, String password) {

      if (!initDone) {
         throw new ProtocolException(
               "Error, connection to server has not been initialized");
      }

      UserAccount account = null;
      boolean loginAccepted = false;

      synchronized (requestChannel) {
         requestChannel.sendProtocolType(ProtocolType.LOGIN);

         // Ne poursuit que si la requête est acceptée par le serveur.
         if (isRequestAccepted(ProtocolType.LOGIN)) {
            requestChannel.sendString(login);
            requestChannel.sendString(password);

            loginAccepted = requestChannel.receiveBoolean();

            if (loginAccepted) {
               account = (UserAccount) requestChannel.receiveObject();
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

      if (!initDone) {
         throw new ProtocolException(
               "Error, connection to server has not been initialized");
      }

      boolean success = false;
      synchronized (requestChannel) {
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
    * 
    * @param login
    *           - le nom du nouveau compte.
    * @param password
    *           - le mot de passe associé au compte créé.
    * @return le compte client si le compte a pu être créé, null le cas échéant.
    */
   public UserAccount createAccount(String login, String password) {

      if (!initDone) {
         throw new ProtocolException(
               "Error, connection to server has not been initialized");
      }

      UserAccount account = null;
      boolean accountCreated = false;

      synchronized (requestChannel) {
         requestChannel.sendProtocolType(ProtocolType.ACCOUNT_CREATE);

         // Ne poursuit que si le serveur a accepté la requête
         if (isRequestAccepted(ProtocolType.ACCOUNT_CREATE)) {
            requestChannel.sendString(login);
            requestChannel.sendString(password);

            accountCreated = requestChannel.receiveBoolean();

            if (accountCreated) {
               account = (UserAccount) requestChannel.receiveObject();
            }
         }
      }

      return account;
   }

   /**
    * Demande à rejoindre une partie. Un nouveau canal de communication est
    * alors créé, servant de canal d'écoute pour recevoir les mises à jour du
    * serveur.
    * 
    * @return Le résultat de la tentative de.
    */
   public boolean joinLobby() {

      if (!initDone) {
         throw new ProtocolException(
               "Error, connection to server has not been initialized");
      }

      boolean success = false;

      synchronized (requestChannel) {

         requestChannel.sendProtocolType(ProtocolType.JOIN_GAME);

         if (isRequestAccepted(ProtocolType.JOIN_GAME)) {

            boolean isLobbyFree = requestChannel.receiveBoolean();

            if (isLobbyFree) {
               // Réception de la confirmation
               success = requestChannel.receiveBoolean();
            }
            else {
               System.out.println("No free lobbby for the moment.");
            }

         }
         else {
            System.out.println("Join game request refused by server.");
         }

      }

      return success;
   }

   /**
    * Protocole bidon pour test.
    * 
    * @param message
    */
   @Deprecated
   public void sendMessage(String message) {

      if (!initDone) {
         throw new ProtocolException(
               "Error, connection to server has not been initialized");
      }

      synchronized (requestChannel) {
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
         throw new ProtocolException("Was wainting for " + typeWanted + " or "
               + ProtocolType.REFUSE + " but received " + type);
      }

   }
   
   /**
    * Conteneur comprenant une référence sur le canal à utiliser pour envoyer
    * une requête au serveur, et une seconde référence sur le canal utilisé par
    * le serveur pour envoyer diverses informations quand il le souhaite.
    * 
    * @author Crescenzio Fabio
    * @author Decorvet Grégoire
    * @author Jaquier Kevin
    * @author Schweizer Thomas
    * 
    */
   public class ConnectionChannels {

      /**
       * Canal à utiliser pour envoyer des requêtes au serveur.
       */
      public Channel requestChannel;

      /**
       * Canal utilisé par le serveur pour transmettre des informations au
       * client.
       */
      public Channel updateChannel;

      /**
       * Constructeur privé, pour simplifier la création de la pair de canaux.
       * 
       * @param request
       *           - le canal pour les requêtes du client.
       * @param update
       *           - le canal recevant les informations du serveur.
       */
      private ConnectionChannels(Channel request, Channel update) {
         requestChannel = request;
         updateChannel = update;
      }

   }

}
