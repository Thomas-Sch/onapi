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

import utils.connections.Channel;
import utils.connections.protocol.ClientProtocol;

/**
 * TODO ATTENTION : code ignoble de test
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 *
 */
public class ClientTest {
   
   public static class ClientConnection implements Runnable {
      
      private ClientProtocol protocol;
      
      public ClientConnection(Channel channel) {
         protocol = new ClientProtocol(channel);
      }
      
      public void executeCommand(String command) {
         
         if(command.equalsIgnoreCase("-ping")) {
            long ping;
            
            synchronized (protocol) {
               ping = protocol.ping();
            }
            
            System.out.println("Ping : " + ping + " ms");
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
      
      Scanner sc = new Scanner(System.in);
      
      System.out.print("Entrez l'addresse ip > ");
      adresse = sc.nextLine().trim();
      
      System.out.print("Entrez le numero de port > ");
      
      port = sc.nextInt();
      
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
         
         message = sc.nextLine();
         
         connection.executeCommand(message);
         
      }
      
      
   }

}
