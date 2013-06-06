/* ============================================================================
 * Nom du fichier   : TestCollisions.java
 * ============================================================================
 * Date de création : 6 juin 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

/**
 * TODO
 * 
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 * 
 */
public class TestCollisions {

   Rectangle r1 = new Rectangle(0, 0, 3, 3); // Rectangle de base
   Rectangle r2 = new Rectangle(1, 1, 2, 2); // Intérieur
   Rectangle r3 = new Rectangle(3.0f, 0, 3, 3); // Tangeant
   Rectangle r31 = new Rectangle(2.99f, 0, 3, 3); // Un peu plus que tangeant
   Rectangle r32 = new Rectangle(3.01f, 0, 3, 3); // Un peu moins que tangeant
   Rectangle r4 = new Rectangle(4, 0, 3, 3); // Extérieur (pas de contact)
   Rectangle r5 = new Rectangle(2.99f, 2.99f, 3, 3); // Contact sur 0.01f x
                                                     // 0.01f
   Rectangle r6 = new Rectangle(2, 0, 2, 2); // Contact

   @Test
   public void test1() {
      assertTrue(r1.overlaps(r1));
      assertTrue(Intersector.overlapRectangles(r1, r1));
   }

   @Test
   public void test2() {
      assertTrue(r1.overlaps(r2));
      assertTrue(Intersector.overlapRectangles(r1, r2));
   }

   @Test
   public void test3() {
      assertTrue(r1.overlaps(r3));
      assertFalse(Intersector.overlapRectangles(r1, r3)); // Différence de
                                                          // comportement !!!
   }

   @Test
   public void test31() {
      assertTrue(r1.overlaps(r31));
      assertTrue(Intersector.overlapRectangles(r1, r31));
   }

   @Test
   public void test32() {
      assertFalse(r1.overlaps(r32));
      assertFalse(Intersector.overlapRectangles(r1, r32));
   }

   @Test
   public void test4() {
      assertFalse(r1.overlaps(r4));
      assertFalse(Intersector.overlapRectangles(r1, r4));
   }

   @Test
   public void test5() {
      assertTrue(r1.overlaps(r5));
      assertTrue(Intersector.overlapRectangles(r1, r5));
   }

   @Test
   public void test6() {
      assertTrue(r1.overlaps(r6));
      assertTrue(Intersector.overlapRectangles(r1, r6));
   }

}
