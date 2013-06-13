package core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.SynchronousQueue;


import game.models.GameModel;
import game.models.Message;

public class GameInitData {
   private GameModel game;
   private PlayersInformations players;
   private Collection<String> messages;
   
   public GameInitData(GameModel game, PlayersInformations players){
      messages = new ArrayList<String>();
      this.players = players;
      this.game = game;
   }
}
