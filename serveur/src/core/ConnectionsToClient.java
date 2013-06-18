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

/**
 * Représente une connexion avec un client, en comprenant les deux canaux de
 * communication, à savoir celui des requêtes et des mises à jour.
 * 
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
      receiveChannel = new Channel(socket, timeout);

      initUpdateChannel(connection);
   }

   private void initUpdateChannel(UserConnectionManager connection) {
      int code = RandomGenerator.generateConnectionCode();

      core.askForUpdateChannel(connection, code);

      receiveChannel.sendInt(core.getUpdatePortNumber());
      receiveChannel.sendInt(code);

      updateChannel = core.waitForUpdateChannel(code);

      updateChannel.sendInt(code);
   }

}
