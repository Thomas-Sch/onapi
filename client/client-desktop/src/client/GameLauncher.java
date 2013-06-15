package client;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import core.ConnectionsManager;

public class GameLauncher {
   GameInitData initData;
   private boolean debug;
   
   private ConnectionsManager connections;
   
   public GameLauncher(ConnectionsManager connections, boolean debug) {
      this.connections = connections;
      this.debug = debug;
      this.initData = new GameInitData();
   }
   
   public void run() {
      LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
      cfg.title = "ONAPI";
      cfg.useGL20 = true;
      cfg.width = 1280;
      cfg.height = 720;

      new LwjglApplication(new Onapi(this.debug, initData), cfg);
   }
   
}
