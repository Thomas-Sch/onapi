/* ============================================================================
 * Nom du fichier   : ConnectionsManager.java
 * ============================================================================
 * Date de création : 5 juin 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package core;

import gui.component.JPlayerList;
import common.connections.Channel;
import common.connections.exceptions.ChannelClosedException;
import common.connections.exceptions.ChannelException;
import common.connections.exceptions.TimeOutException;
import common.connections.protocol.ProtocolType;

import client.ClientReceiveProtocol;
import client.ClientRequestProtocol;
import client.ClientRequestProtocol.ConnectionChannels;

/**
 * 
 * TODO
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 *
 */
public class ConnectionsManager {
   
   private ConnectionChannels connections;
   
   private Updater updateActivity;
   private KeepAlive keepAliveActivity;
   
   private PlayersInformations players;
   
   public ConnectionsManager () {
      
   }
   
   public void setup(ConnectionChannels connections) {
      this.connections = connections;
      
      updateActivity = new Updater(connections.updateChannel);
      updateActivity.start();
      
      keepAliveActivity = new KeepAlive(connections.requestChannel);
      keepAliveActivity.start();
   }
   
   public void setupPlayers(int number) {
      players = new PlayersInformations(number);
   }
   
   public Channel getChannelUpdate() {
      if(connections != null) {
         return connections.updateChannel;
      }
      else {
         return null;
      }
   }
   
   public Channel getChannelRequest() {
      if(connections != null) {
         return connections.requestChannel;
      }
      else {
         return null;
      }
   }
   
   public PlayersInformations getPlayers() {
      return players;
   }
   
   public class KeepAlive extends Thread {
      private Channel requestChannel;
      private ClientRequestProtocol protocol;
      
      private boolean exit = false;
      
      public KeepAlive(Channel requestChannel) {
         this.requestChannel = requestChannel;
         protocol = new ClientRequestProtocol(requestChannel);
      }
      
      @Override
      public void run() {
         while(!exit) {
            protocol.keepAlive();
            
            try {
               Thread.sleep(2500);
            }
            catch (Exception e) { }
         }
      }
   }
   
   public class Updater extends Thread {
      
      private Channel updateChannel;
      private ClientReceiveProtocol protocol;
      
      private boolean exit = false;
      
      public Updater(Channel updateChannel) {
         this.updateChannel = updateChannel;
         protocol = new ClientReceiveProtocol(updateChannel);
      }

      @Override
      public void run() {

         ProtocolType proType;

         while (!exit) {

            try {
               proType = updateChannel.receiveProtocolType();

               switch (proType) {
                  case PING:
                     protocol.ping();
                     break;

                  case TEXT_MESSAGE:
                     System.out.println(protocol.updateMessage());
                     break;
                     
                  case LOBBY_UPDATED_SLOT_STATUS:
                     protocol.lobbyUpdateSlotStatus(players);
                     break;
                     
                  case LOBBY_GAME_READY :
                     protocol.lobbyUpdateGameReady(42);
                     System.out.println("DEBUG - game ready !");
                     break;

                  default:
                     System.out.println("Bad request protocol");
               }

            }
            catch (TimeOutException e) {
               System.out.println("Timeout with server");
            }
            catch (ChannelClosedException e) {
               System.out.println("Server lost");
            }
            catch (ChannelException e) {
               System.out.println("Server lost");
            }
         }

      }
      
      
   }

}
