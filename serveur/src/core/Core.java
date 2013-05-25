/* ============================================================================
 * Nom du fichier   : Core.java
 * ============================================================================
 * Date de création : 1 mai 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package core;

import gui.LogsFrame;
import gui.logs.Log;
import gui.logs.LogPanel;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.util.Enumeration;
import java.util.LinkedList;

import common.components.UserAccount;

import core.exceptions.PortException;
import core.lobby.Lobby;
import database.DBController;

/**
 * TODO
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 *
 */
public class Core {
   
   private static final int CLIENT_TIMEOUT = 15000;
   private static final int DEFAULT_PORT = 1234;
   
   private static final String DATABASE_NAME = "onapi.db";
   private static String databasePath;
   
   private LogsFrame logsFrame;
   
   private Log log;
   
   private boolean exit = false;
   
   private InetAddress[] inetAddresses = null;
   
   private Port serverPort;
   
   private DBController dbController;
   
   private LinkedList<UserConnection> connections = new LinkedList<>();
   
   private Lobby lobby;
   
   public Core(boolean enableLogFrame) {
      init(enableLogFrame);
      
      if (!initSuccessful()) {
         throw new RuntimeException("Server not successfully initialized");
      }
   }
   
   public void addLogPanel(LogPanel panel) {
      if (logsFrame != null) {
         synchronized(logsFrame) {
            logsFrame.addLogPanel(panel);
         }
      }
   }
   
   public void removeLogPanel(LogPanel panel) {
      if (logsFrame != null) {
         synchronized(logsFrame) {
            logsFrame.removeLogPanel(panel);
         }
      }
   }
   
   public void setLogPanelTitle(LogPanel panel, String title) {
      if (logsFrame != null) {
         synchronized(logsFrame) {
            logsFrame.setLogPanelTitle(panel, title);
         }
      }
   }
   
   public void start() {
      
      if(logsFrame != null) {
         logsFrame.setVisible(true);
         logsFrame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
      }
      
      log.push("Onapi server started.");
      
      for (int i = 0 ; i < inetAddresses.length ; i++) {
         log.push("Network interface " + i +
               (i == 0 ? " (suggested)" : "") + "\n" +
             " - name    : " + inetAddresses[i].getHostName() + "\n" +
             " - address : " + inetAddresses[i].getHostAddress() + "\n" +
             " - port    : " + getPortNumber());
      }
      
      while(!exit) {
         
         try {
            Socket socket = serverPort.accept();
            
            // Démarre le processus de gestion d'un nouveau client
            UserConnection userConnection =
                  new UserConnection(this, socket, CLIENT_TIMEOUT);
            
            Thread thread = new Thread(userConnection);
            thread.start();
            
            // Enregistre le nouveau client
            synchronized(connections) {
               connections.add(userConnection);
            }
            
            
         }
         catch (PortException e) {
            log.push("Unable to accept new connection : " + e.getMessage());
         }
         
         
      }
      
   }
   
   public void removeConnection(UserConnection userConnection) {
      synchronized(connections) {
         connections.remove(userConnection);
      }
   }
   
   public InetAddress getInetAdress() {
      return inetAddresses[0];
   }
   
   public int getPortNumber() {
      return serverPort.getPortNumber();
   }
   
   public UserAccount checkAuthentification(String login, String password) {
      UserAccount user;
      
      dbController.openConnection();
      user = dbController.checkUserConnection(login, password);
      dbController.closeConnection();
      
      return user;
   }
   
   public UserAccount createAccount(String login, String password) {
      UserAccount user;
      
      dbController.openConnection();
      user = dbController.createUser(login, password, "user");
      dbController.closeConnection();
      
      return user;
   }
   
   public Lobby getFreeLoby() {
      if (lobby.getFreeSlots() > 0) {
         return lobby;
      }
      else {
         return null;
      }
   }
   
   private InetAddress[] getIps() {
      LinkedList<InetAddress> ipAddresses = new LinkedList<InetAddress>();

      try {
         Enumeration<NetworkInterface> interfaces = 
               NetworkInterface.getNetworkInterfaces();

         while (interfaces.hasMoreElements()) {
            NetworkInterface networkInterface = interfaces.nextElement();

            if (!networkInterface.isVirtual() && networkInterface.isUp()) {

               Enumeration<InetAddress> ipEnum =
                     networkInterface.getInetAddresses();
               InetAddress ia;
               String address;

               while (ipEnum.hasMoreElements()) {
                  ia = ipEnum.nextElement();
                  address = ia.getHostAddress().toString();

                  // Ne prendre que les IPv4
                  if (address.length() < 16) {
                     // Ne pas prendre l'adresse IP Local ou une IPv6 courte
                     if (!address.startsWith("127")
                         && address.indexOf(":") < 0) {

                        ipAddresses.add(ia);
                     }
                  }

               }
            }

         }
      }
      catch (IOException e) {
         System.out.println("No network card !");
      }

      InetAddress[] adresses = new InetAddress[ipAddresses.size()];
      
      return ipAddresses.toArray(adresses);
      
   }
   
   private void init(boolean enableLogFrame) {
      serverPort = new Port(DEFAULT_PORT);
      serverPort.activateFreePort();
      
      try {
         inetAddresses = getIps();
      }
      catch (Exception e) {
         System.err.println("Unable to obtain the IP address of the server");
      }
      
      log = new Log("main");
      if (enableLogFrame) {
         logsFrame = new LogsFrame("Onapi - Server", 15, 15, 500, 400);
         logsFrame.addLogPanel(log.createLogPanel());
      }
      
      // Base de données
      File file = new File(".");
      
      databasePath = file.getAbsoluteFile().getParent() + File.separator + "database"
            + File.separator + DATABASE_NAME;
      
      dbController = new DBController(databasePath);
      
      lobby = new Lobby(10, DEFAULT_PORT + 1, CLIENT_TIMEOUT);
      
   }
   
   private boolean initSuccessful(){
      return inetAddresses != null && inetAddresses.length > 0;
   }

}
