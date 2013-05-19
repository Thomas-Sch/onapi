/* ============================================================================
 * Nom du fichier   : Client.java
 * ============================================================================
 * Date de création : 19 mai 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package core;

import gui.logs.Log;
import common.components.UserAccount;
import common.connections.Channel;

/**
 * Classe regroupant les variables liées à un utilisateur.
 * 
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 *
 */
public class UserInformations {
   
   public Channel channelReceive;
   public Channel channelUpdate;
   
   public Log log;
   
   public boolean isConnected;
   
   public UserAccount account;
   
   public UserInformations() {
      
   }
   

}
