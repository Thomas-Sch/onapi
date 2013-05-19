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
   
   public static class ClientConnection implements Runnable {
      
      private ClientRequestProtocol protocol;
      
      public ClientConnection(Channel channel) {
         protocol = new ClientRequestProtocol(channel);
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
         else if (command.equalsIgnoreCase("-connect")) {
            String login;
            String password;
            
            System.out.println("Connexion au compte client");
            System.out.print("Login > ");
            login = scanner.nextLine();
            
            System.out.print("Password > ");
            password = scanner.nextLine();
            
            UserAccount account = protocol.authentification(login, password);
            
            if(account == null) {
               System.out.println("Echec connexion");
            }
            else {
               System.out.println("Connexion au compte client :");
               
               account.toString();
            }
            
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
            
            synchronized(protocol) {
               protocol.keepAlive();
            }
            
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
      Thread threadConnection;
      
      
      Channel channel;
      
      
      
      System.out.print("Entrez l'addresse ip > ");
      adresse = scanner.nextLine().trim();
      
      System.out.print("Entrez le numero de port > ");
      
      port = scanner.nextInt();
      
      System.out.println("Connexion en cours...");
      
      channel = new Channel(adresse, port, 5000);
      
      System.out.println("Connexion etablie !");
      
      // Creation du thread de traitement
      connection = new ClientConnection(channel);
      threadConnection = new Thread(connection);
      threadConnection.start();
      
      String message;
      while(!exit) {
         
         System.out.print("Saisie > ");
         
         message = scanner.nextLine();
         
         connection.executeCommand(message);
         
      }
      
      
   }

}
