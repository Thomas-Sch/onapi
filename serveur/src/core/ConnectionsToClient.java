/* ============================================================================
 * Nom du fichier   : ConnectionsToClient.java
 * ============================================================================
 * Date de création : 27 mai 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package core;

import java.net.Socket;

import utils.RandomGenerator;

import common.connections.Channel;
import common.connections.exceptions.ChannelException;

/**
 * TODO
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 *
 */
public class ConnectionsToClient {
   private Core core;
   public Channel receiveChannel;
   public Channel updateChannel;
   
   public ConnectionsToClient(Core core, UserConnectionManager connection,
                              Socket socket, int timeout) {
      init(core, connection, socket, timeout);
   }
   
   private void init(Core core, UserConnectionManager connection,
                     Socket socket, int timeout) {
      this.core = core;
      
      System.out.print("DEBUG - creating receiveChannel...");
      receiveChannel = new Channel(socket, timeout);
      System.out.println("done.");
      
      initUpdateChannel(connection);
   }
   
   private void initUpdateChannel(UserConnectionManager connection) {
      System.out.print("DEBUG - create random code...");
      int code = RandomGenerator.generateConnectionCode();
      System.out.println("random code done.");
      
      System.out.println("DEBUG - add new client for update with code : " + code);
      
      core.askForUpdateChannel(connection, code);
      
      receiveChannel.sendInt(core.getUpdatePortNumber());
      receiveChannel.sendInt(code);
      
      System.out.println("DEBUG - wait for channel update to client");
      
      updateChannel = core.waitForUpdateChannel(code);
      
      System.out.println("DEBUG - channel update done to client");
      
      updateChannel.sendInt(code);
   }
   

}
