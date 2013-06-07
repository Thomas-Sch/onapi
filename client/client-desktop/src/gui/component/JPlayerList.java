package gui.component;

import gui.models.LobbyPlayerStatus;
import javax.swing.JList;

import common.components.lobby.PlayerStatus;
import core.PlayerInfo;

public class JPlayerList extends JList<String> {

   private static final long serialVersionUID = -3753161992210297207L;
   
   private LobbyPlayerStatus model;
      
   public JPlayerList(int nbSlots) {
      model = new LobbyPlayerStatus(nbSlots);
      setModel(model);
   }
   
   public void update(PlayerInfo updatedPlayer) {
      int slot = updatedPlayer.getSlotNumber();
      
      if (updatedPlayer.isUsed()) {
         model.set(slot, (updatedPlayer.getStateReady() ? " + " : " - ") 
              + "[" + updatedPlayer.getTeamNumber() + "] - "
              + updatedPlayer.getName() );
      }
      else {
         model.set(slot, LobbyPlayerStatus.TEXT_FREE);
      }
      
   }
   
}
