/* ============================================================================
 * Nom du fichier   : Team.java
 * ============================================================================
 * Date de création : 8 mai 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package game.models;

import java.util.List;

import com.badlogic.gdx.graphics.Color;

/**
 * TODO
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 *
 */
public class Team {

   public Team(Color color, List<Player> members) {
      super();
      this.color = color;
      this.members = members;
   }


   private Color color;
   private List<Player> members;
   
   public Color getColor() {
      return color;
   }


   public void setColor(Color color) {
      this.color = color;
   }


   public List<Player> getMembers() {
      return members;
   }


   public void setMembers(List<Player> members) {
      this.members = members;
   }
   
}
