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

import com.badlogic.gdx.math.Vector2;

import game.models.GameListener;
import game.models.GameModel;
import game.models.Player;
import client.ClientReceiveProtocol;
import client.ClientRequestProtocol;
import client.ClientRequestProtocol.ConnectionChannels;
import client.GameData;
import client.GameLauncher;

import common.connections.Channel;
import common.connections.exceptions.ChannelClosedException;
import common.connections.exceptions.ChannelException;
import common.connections.exceptions.TimeOutException;
import common.connections.protocol.ProtocolType;

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
   
   private UsersInformations users = new UsersInformations(); // Pour les administrateurs
   private PlayersInformations players;
   
   private GameData gameData;

   private GameLauncher launcher;
   
   public ConnectionsManager (GameModel gameModel) {
      gameData = new GameData();
      gameData.setGame(gameModel);
   }
   
   public void setup(ConnectionChannels connections) {
      this.connections = connections;
      
      updateActivity = new Updater(connections.updateChannel);
      updateActivity.start();
      
      keepAliveActivity = new KeepAlive(connections.requestChannel);
      keepAliveActivity.start();
   }
   
   public void setupPlayers(int number) {
      if (players == null) {
         players = new PlayersInformations(number);
      }
   }
   
   public void setupAdminUserList() {
      if (users == null) {
         users = new UsersInformations();
      }
   }
   
   public void setupGameModel(GameModel gameModel) {
      gameData = new GameData();
      gameData.setGame(gameModel);
   }
   
   public void setupGameLauncher(GameLauncher launcher) {
      this.launcher = launcher;
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
   
   public UsersInformations getAllUsers() {
      return users;
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
   
   public class Notifier implements GameListener {
      private ClientRequestProtocol protocol;

      public Notifier(GameData data, ClientRequestProtocol protocol) {
         data.getGame().updates.addListener(this);
         this.protocol = protocol;
      }

      @Override
      public void onPlayerMove(GameModel gameModel, Player player) {
      // TODO Auto-generated method stub
         //protocol.sendMessage(player.getId() + " moved.");
         System.out.println("MOVE #" + player.getId());
      }

      @Override
      public void onFire(GameModel gameModel, Player sender, Vector2 from,
            float angle) {
         // TODO Auto-generated method stub
         System.out.println("FIRE #" + sender.getId());
      }

      @Override
      public void onPlayerHit(GameModel gameModel, Player player) {
         // TODO Auto-generated method stub
         System.out.println("HIT #" + player.getId());
      }

      @Override
      public void onTorch(GameModel gameModel, Player player) {
         // TODO Auto-generated method stub
         System.out.println("TORCH #" + player.getId());
      }

      @Override
      public void onPlayerOut(GameModel gameModel, Player player) {
         // TODO Auto-generated method stub
         System.out.println("OUT #" + player.getId());
      }
   }
   
   public class Updater extends Thread {
      
      private Channel updateChannel;
      private ClientReceiveProtocol protocol;
      
      private boolean exit = false;
      
      public Updater(Channel updateChannel) {
         this.updateChannel = updateChannel;
         protocol = new ClientReceiveProtocol(updateChannel, launcher);
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
                     protocol.lobbyUpdateGameReady(gameData);
                     System.out.println("DEBUG - game ready !");
                     break;
                     
                  case ADMIN_UPDATED_SLOT :
                     protocol.adminUpdateServerSlotStatus(users);
                     break;
                     
                  case ADMIN_KICK :
                     protocol.adminKicked(gameData);
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
