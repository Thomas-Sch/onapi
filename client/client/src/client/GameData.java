package client;

import game.models.GameModel;

import java.util.ArrayList;
import java.util.Collection;

public class GameData {
   private GameModel game;
   private Collection<String> messages;

   // TODO Ajouter un ConnectionsManager, nécessite de faire le lien avec client-desktop
   // (Attention, impossible d'inclure client-desktop sinon cyclicité de build !)
   
   public GameData(){
      messages = new ArrayList<String>();
      this.game = new GameModel(this);
      
   }

   public GameModel getGame() {
      return game;
   }

   public Collection<String> getMessages() {
      return messages;
   }

   public void setGame(GameModel game) {
      this.game = game;
   }

   public void setMessages(Collection<String> messages) {
      this.messages = messages;
   }
   
}
