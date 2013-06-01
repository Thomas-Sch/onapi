/* ============================================================================
 * Nom du fichier   : ClientTest.java
 * ============================================================================
 * Date de création : 8 mai 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
import java.util.Scanner;

import common.components.UserAccount;
import common.connections.Channel;
import common.connections.protocol.ProtocolType;
import client.ClientReceiveProtocol;
import client.ClientRequestProtocol;



/**
 * TODO ATTENTION : code ignoble de test
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 *
 */
public class ClientTest {
   
   private static Scanner scanner = new Scanner(System.in);
   
   private static Channel channelRequest;
   
   private static Channel channelUpdate;
   
   public static class ClientUpdates implements Runnable {
      
      private ClientReceiveProtocol protocol;
      
      private Channel channel;
      
      public ClientUpdates(Channel channel) {
         this.channel = channel;
         protocol = new ClientReceiveProtocol(channel);
      }

      @Override
      public void run() {
         ProtocolType type;
         
         while(true) {
            
            type = channel.receiveProtocolType();
            
            switch (type) {
               
               case PING :
                  protocol.ping();
                  break;
                  
               case TEXT_MESSAGE :
                  System.out.println("Test update : " + channel.receiveString());
                  break;
               
               default :
                  System.out.println("Oups, bad protocol");
            }
            
         }
         
      }
      
   }
   
   public static class ClientConnection implements Runnable {
      
      private ClientRequestProtocol protocol;
      
      public ClientConnection(String address, int port, int timeout) {
         protocol = new ClientRequestProtocol();
         System.out.println("DEBUG - call to connectServer()");
         channelUpdate = protocol.connectToServer(address, port, timeout).updateChannel;
         System.out.println("DEBUG - update channel created.");
      }
      
      public void executeCommand(String command) {
         
         if(command.equalsIgnoreCase("-ping")) {
            long ping;
            
            synchronized (protocol) {
               ping = protocol.ping();
            }
            
            System.out.println("Ping : " + ping + " ms");
         }
         else if (command.equalsIgnoreCase("-create")) {
            String login;
            String password;
            
            System.out.println("Création d'un nouveau compte :");
            System.out.print("Login > ");
            login = scanner.nextLine();
            
            System.out.print("Password > ");
            password = scanner.nextLine();
            
            UserAccount account = protocol.createAccount(login, password);
            
            if(account == null) {
               System.out.println("Echec création");
            }
            else {
               System.out.println("Compte créé");
               
               account.toString();
            }
            
         }
         else if (command.equalsIgnoreCase("-login")) {
            String login;
            String password;
            
            System.out.println("Connexion au compte client");
            System.out.print("Login > ");
            login = scanner.nextLine();
            
            System.out.print("Password > ");
            password = scanner.nextLine();
            
            UserAccount account = protocol.login(login, password);
            
            if(account == null) {
               System.out.println("Echec connexion");
            }
            else {
               System.out.println("Connexion au compte client : " + account);
            }
         }
         else if (command.equalsIgnoreCase("-logout")) {
            protocol.logout();
         }
         else if (command.equalsIgnoreCase("-join")) {
            protocol.joinLobby();
         }
         // Autrement envoie le texte tel quel
         else {
            synchronized (protocol) {
               protocol.sendMessage(command);
            }
         }
         
         
         
      }

      @Override
      public void run() {
         while(true) {
            
            protocol.keepAlive();
            
            try {
               Thread.sleep(2500);
            }
            catch (Exception e) {}
            
         }
         
      }
      
   }
   
   public static void main(String[] args) {
      boolean exit = false;
      String adresse;
      int port;
      
      ClientConnection connection;
      ClientUpdates updates;
      Thread threadConnection;
      Thread threadUpdates;
      
      
      System.out.print("Entrez l'addresse ip > ");
      adresse = scanner.nextLine().trim();
      
      System.out.print("Entrez le numero de port > ");
      
      port = Integer.parseInt(scanner.nextLine());
      
      System.out.println("Connexion en cours...");
      
      System.out.println("Connexion etablie !");
      
      // Creation du thread de traitement
      connection = new ClientConnection(adresse, port, 10000);
      updates = new ClientUpdates(channelUpdate);

      threadConnection = new Thread(connection);
      threadConnection.start();
      
      threadUpdates = new Thread(updates);
      threadUpdates.start();
      
      String message;
      while(!exit) {
         
         System.out.print("Saisie > ");
         
         message = scanner.nextLine();
         
         connection.executeCommand(message);
         
      }
      
      
   }

}
