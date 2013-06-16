package client;

import game.models.GameModel;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Permet de faire transiter des données de client-desktop à client et
 * vice-versa, pour l'initialisation du jeu lors du lancement de la partie.
 * 
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 * 
 */
public class GameData {
   private GameModel game;
   private Collection<String> messages;

   public GameData() {
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
