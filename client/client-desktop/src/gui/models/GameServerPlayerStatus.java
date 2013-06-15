/* ============================================================================
 * Nom du fichier   : GameServerPlayerStatus.java
 * ============================================================================
 * Date de création : 14 juin 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package gui.models;

import javax.swing.DefaultListModel;

import common.components.gameserver.PlayerStatus;

import core.PlayerInfo;

/**
 * 
 * TODO
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 *
 */
public class GameServerPlayerStatus extends DefaultListModel<PlayerInfo> {

   /**
    * ID de sérialisation
    */
   private static final long serialVersionUID = 7918887794494738333L;

   public GameServerPlayerStatus(int slotNumber) {
      for (int i = 0 ; i < slotNumber ; i++) {
         // Création d'une emplacement vide
         PlayerStatus playerStatus = new PlayerStatus(-1, "", i);
         playerStatus.setLeft();
         
         addElement(new PlayerInfo(playerStatus));
      }
   }
   
   
}
