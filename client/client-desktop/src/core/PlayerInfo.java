/* ============================================================================
 * Nom du fichier   : PlayerInfo.java
 * ============================================================================
 * Date de création : 7 juin 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package core;

/**
 * 
 * TODO
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 *
 */
public class PlayerInfo extends CoreComponent {
   
   private int slotNumber;
   private boolean isUsed = false;
   
   private String name;
   
   private int teamNumber;
   private boolean isReady;
   
   public PlayerInfo(int slotNumber) {
      this.slotNumber = slotNumber;
   }
   
   public boolean isUsed() {
      return isUsed;
   }
   
   public void setIsUsed(boolean isUsed) {
      if (this.isUsed != isUsed) {
         this.isUsed = isUsed;
         setChanged();
         notifyObservers(this);
      }
   }
   
   public int getSlotNumber() {
      return slotNumber;
   }
   
   public String getName() {
      return name;
   }
   
   public void setName(String name) {
      this.name = name;
      setChanged();
      notifyObservers(this);
   }
   
   public int getTeamNumber() {
      return teamNumber;
   }
   
   public void setTeamNumber(int teamNumber) {
      this.teamNumber = teamNumber;
      setChanged();
      notifyObservers(this);
   }
   
   public boolean getStateReady() {
      return isReady;
   }
   
   public void setStateReady(boolean isReady) {
      this.isReady = isReady;
      setChanged();
      notifyObservers(this);
   }
   
}