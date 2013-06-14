///* ============================================================================
// * Nom du fichier   : ClientRequestProtocolTest.java
// * ============================================================================
// * Date de création : 21 mai 2013
// * ============================================================================
// * Auteurs          : Crescenzio Fabio
// *                    Decorvet Grégoire
// *                    Jaquier Kevin
// *                    Schweizer Thomas
// * ============================================================================
// */
//package client;
//
//import static org.junit.Assert.*;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//import client.ClientRequestProtocol.ConnectionChannels;
//import common.components.UserAccount;
//import common.connections.Channel;
//
///**
// * TODO
// * @author Crescenzio Fabio
// * @author Decorvet Grégoire
// * @author Jaquier Kevin
// * @author Schweizer Thomas
// *
// */
//public class ClientRequestProtocolTest {
//   private static final String ADDRESS = "127.0.0.1";
//   private static final int TIMEOUT = 10000;
//   private static final int NUMBER_OF_CLIENTS = 100;
//   
//   /*
//    * ATTENTION : le test de création ne peut réussir qu'une fois. Après les
//    * comptes existent déjà et ne pourront plus être créés
//    */
//   private static final boolean createAccountsTest = true;
//   
//   private static int testNumber = 0;
//   private static Server server;
//   private static int portNumber;
//   
//   private ConnectionChannels[] connections;
//   private ClientRequestProtocol[] protocol;
//   
//   
//   class Server extends Thread {
//      
//      Core core;
//      
//      Server() {
//         core = new Core(false);
//      }
//
//      @Override
//      public void run() {
//         core.start();
//      }
//   }
//
//   /**
//    * @throws java.lang.Exception
//    */
//   @Before
//   public void setUp() throws Exception {
//      if (server == null) {
//         System.out.println("Creation du serveur.");
//         
//         server = new Server();
//         
//         portNumber = server.core.getPortNumber();
//         
//         server.start();
//      }
//      
//      testNumber++;
//      System.out.println("+" + String.format("%78s", "").replace(' ', '-') + "+");
//      System.out.println("| Initialisation du test " + testNumber);
//      System.out.println("+" + String.format("%78s", "").replace(' ', '-') + "+");
//      System.out.print("Connexion(s) au serveur...");
//      
//      connections = new ConnectionChannels[NUMBER_OF_CLIENTS];
//      protocol = new ClientRequestProtocol[NUMBER_OF_CLIENTS];
//      
//      for (int i = 0 ; i < NUMBER_OF_CLIENTS ; i++) {
//         protocol[i] = new ClientRequestProtocol();
//         connections[i] = protocol[i].connectToServer(ADDRESS, portNumber, TIMEOUT);
//      }      
//      
//      System.out.println("Etablie(s).");
//      System.out.println("... Debut du test ...");
//   }
//
//   /**
//    * @throws java.lang.Exception
//    */
//   @After
//   public void tearDown() throws Exception {
//      
//      for (int i = 0 ; i < NUMBER_OF_CLIENTS ; i++) {
//         connections[i].requestChannel.close();
//         connections[i].updateChannel.close();
//      }
//      
//      System.out.println("Fin du test " + testNumber + ".");
//      System.out.println("+" + String.format("%78s", "").replace(' ', '-') + "+");
//      System.out.println("\n");
//   }
//
//   /**
//    * Test method for {@link client.ClientRequestProtocol#ClientRequestProtocol(common.connections.Channel)}.
//    */
//   @Test
//   public void testClientRequestProtocol() {
//      
//      for (int i = 0 ; i < NUMBER_OF_CLIENTS ; i++) {
//         assertNotNull(protocol[i]);
//      }
//      
//   }
//
//   /**
//    * Test method for {@link client.ClientRequestProtocol#ping()}.
//    */
//   @Test
//   public void testPing() {
//      long value;
//      for (int i = 0 ; i < NUMBER_OF_CLIENTS ; i++) {
//         value = protocol[i].ping();
//         assertTrue(value >= 0 && value < TIMEOUT);
//      }
//   }
//
//   /**
//    * Test method for {@link client.ClientRequestProtocol#keepAlive()}.
//    */
//   @Test
//   public void testKeepAlive() {
//      boolean stillAlive;
//      for (int i = 0 ; i < NUMBER_OF_CLIENTS ; i++) {
//         stillAlive = protocol[i].keepAlive();
//         assertTrue(stillAlive);
//      }
//   }
//
//   /**
//    * Test method for {@link client.ClientRequestProtocol#login(java.lang.String, java.lang.String)}.
//    */
//   @Test
//   public void testLogin() {
//      
//      for (int i = 0 ; i < NUMBER_OF_CLIENTS ; i++) {
//         // Test d'un utilisateur inexistant
//         String login = "";
//         String password = "pwd";
//         UserAccount account = protocol[i].login(login, password);
//         assertNull(account);
//         
//         // Test d'un utilisateur existant
//         login = "GregoireDec";
//         password = "1234";
//         account = protocol[i].login(login, password);
//         assertEquals(account.getLogin(), login);
//         
//         // Test d'une connection alors que déjà connecté (le compte retour doit
//         // valoir "null", le serveur refusant de répondre
//         login = "ThomasSch";
//         password = "1234";
//         account = protocol[i].login(login, password);
//         assertNull(account);
//      }
//      
//   }
//
//   /**
//    * Test method for {@link client.ClientRequestProtocol#logout()}.
//    */
//   @Test
//   public void testLogout() {
//      for (int i = 0 ; i < NUMBER_OF_CLIENTS ; i++) {
//         
//         // Test du logout sans se connecter
//         assertFalse(protocol[i].logout());
//         
//         // Se connecte puis logout
//         UserAccount account = protocol[i].login("GregoireDec", "1234");
//         assertTrue(protocol[i].logout());
//         
//         // Test d'un second logout après le premier
//         assertFalse(protocol[i].logout());
//         
//      }
//   }
//
//   /**
//    * Test method for {@link client.ClientRequestProtocol#createAccount(java.lang.String, java.lang.String)}.
//    */
//   @Test
//   public void testCreateAccount() {
//      
//      for (int i = 0 ; i < NUMBER_OF_CLIENTS ; i++) {
//         
//         UserAccount account;
//         String login = "client_" + i;
//         String password = "pwd_" + i;
//         
//         // Doit pouvoir créer un compte
//         account = protocol[i].createAccount(login, password);
//         
//         // Dans le cas des comptes déjà créé, account doit être null
//         // Afin de terminer dans le même état que pour l'autre test : login
//         if (!createAccountsTest) {
//            assertNull(account);
//            account = protocol[i].login(login, password);
//            assertNotNull(account);
//         }
//         
//         assertEquals(account.getLogin(), login);
//         assertEquals(account.getPassword(), password);
//         
//         // Connexion impossible (la création entraîne la connexion d'office)
//         assertNull(protocol[i].login(login, password));
//         
//         // Dès lors la déconnexion doit être possible
//         assertTrue(protocol[i].logout());
//         
//         // Et la re-connexion au compte créé également
//         assertNotNull(protocol[i].login(login, password));
//         
//      }
//      
//      
//   }
//
//   /**
//    * Test method for {@link client.ClientRequestProtocol#joinGame()}.
//    */
//   @Test
//   public void testJoinGame() {
//      boolean hasFreeSlots;
//      boolean lobbyJoined;
//      for (int i = 0 ; i < NUMBER_OF_CLIENTS ; i++) {
//         
//         
//         
//         // Rejoindre une partie sans être connecté ne doit pas être possible
//         lobbyJoined = protocol[i].joinLobby();
//         assertFalse(lobbyJoined);
//         
//         // Se connecter puis rejoindre
//         hasFreeSlots = server.core.getFreeLoby() != null;
//         
//         protocol[i].login("GregoireDec", "1234");
//         protocol[i].joinLobby();
//         // Attention, si trop de client pour le test, le surplus de client se
//         // verra refuser l'accès au lobby
//         if (hasFreeSlots) {
//            assertNotNull(channel);
//            
//            // rejoindre à nouveau (ne doit pas être possible et retourner null)
//            channel = protocol[i].joinGame();
//            assertNull(channel);
//            
//            // ne doit pas pouvoir se déconnecter (à ce stade il doit d'abord
//            // quitter la partie)
//            assertFalse(protocol[i].logout());
//
//            // du coup, rejoindre à nouveau est toujours impossible
//            channel = protocol[i].joinGame();
//            assertNull(channel);
//         }
//         
//         // Plus de place dans le lobby, aucun test supplémentaire
//         else {
//            assertNull(channel);
//         }
//         
//         
//         
//      }
//      
//   }
//
//   /**
//    * Test method for {@link client.ClientRequestProtocol#sendMessage(java.lang.String)}.
//    */
//   @Test
//   public void testSendMessage() {
//      
//      for (int i = 0 ; i < NUMBER_OF_CLIENTS ; i++) {
//         protocol[i].sendMessage("Message quelconque");
//      }
//      
//      // Si aucune exception, arrive ici
//      assertTrue(true);
//   }
//
//}