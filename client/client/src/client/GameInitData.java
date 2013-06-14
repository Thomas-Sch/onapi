package client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.SynchronousQueue;

//import core.PlayersInformations;


import game.models.GameModel;
import game.models.Message;

public class GameInitData {
   private GameModel game;
//   private PlayersInformations players;
   private Collection<String> messages;
   
   public GameInitData(){//, PlayersInformations players){
      messages = new ArrayList<String>();
//      this.players = players;
      
      this.game = new GameModel(this);
      
   }

   public GameModel getGame() {
      return game;
   }

//   public PlayersInformations getPlayers() {
//      return players;
//   }

   public Collection<String> getMessages() {
      return messages;
   }

   public void setGame(GameModel game) {
      this.game = game;
   }

//   public void setPlayers(PlayersInformations players) {
//      this.players = players;
//   }

   public void setMessages(Collection<String> messages) {
      this.messages = messages;
   }
   
}
