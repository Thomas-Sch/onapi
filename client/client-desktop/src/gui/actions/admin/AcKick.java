package gui.actions.admin;

import java.awt.event.ActionEvent;

import core.ConnectionsManager;
import gui.actions.UserAction;

public class AcKick extends UserAction {
   
   public AcKick(ConnectionsManager connections, Integer selectedPlayer) {
      super(connections, selectedPlayer);
   }

   @Override
   protected void execute(ConnectionsManager connections, ActionEvent event,
         Object[] dependencies) {
      protocolRequest.adminKickPlayer((Integer)dependencies[0], "Pas de chance, vous avez été éjecté !");
   }

}
