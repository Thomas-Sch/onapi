import java.util.Scanner;

import javax.swing.JFrame;

import utils.connections.Channel;

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


/**
 * TODO
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 *
 */
public class ClientTest {
   
   public static void main(String[] args) {
      boolean exit = false;
      String adresse;
      int port;
      
      Channel channel;
      
      Scanner sc = new Scanner(System.in);
      
      System.out.print("Entrez l'addresse ip > ");
      adresse = sc.nextLine().trim();
      
      System.out.print("Entrez le numero de port > ");
      
      port = sc.nextInt();
      
      System.out.println("Connexion en cours...");
      
      channel = new Channel(adresse, port, 5000);
      
      System.out.println("Connexion etablie !");
      
      JFrame frame = (JFrame)channel.receiveObject();
      frame.setVisible(true);
      
      String message;
      while(!exit) {
         
         System.out.print("Envoie du texte > ");
         
         message = sc.nextLine();
         
         channel.sendString(message);
         
         System.out.println("Recu du serveur : " + channel.receiveString());
         
      }
      
      
   }

}
