/* ============================================================================
 * Nom du fichier   : ListUtils.java
 * ============================================================================
 * Date de création : 31 mai 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package utils;

import java.util.LinkedList;
import java.util.Random;

import com.badlogic.gdx.math.Vector2;

/**
 * Classe utilitaire founissant quelques méthodes de traitement de listes
 * chaînées.
 * 
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 * 
 */
public abstract class ListUtils {

   private ListUtils() {
   }

   public static <T> LinkedList<T> shuffled(LinkedList<T> list) {
      return shuffled(list, new Random());
   }

   @SuppressWarnings("unchecked")
   public static <T> LinkedList<T> shuffled(LinkedList<T> list, Random rand) {
      LinkedList<Object> shuffle = new LinkedList<Object>();
      for (Object o : list) {
         int pos = rand.nextInt(Math.max(1, shuffle.size()));
         shuffle.add(pos, o);
      }
      return (LinkedList<T>) shuffle;
   }

   /**
    * Tests
    * 
    * @param args
    */
   public static void main(String[] args) {
      LinkedList<Integer> l1 = new LinkedList<Integer>();
      for (int i = 0; i < 20; i++)
         l1.add(i);
      for (Integer i : ListUtils.shuffled(l1)) {
         System.out.print(i + " ");
      }

      System.out.println();

      LinkedList<Vector2> l2 = new LinkedList<Vector2>();
      for (int i = 0; i < 20; i++)
         l2.add(new Vector2(i, i));
      for (Vector2 i : ListUtils.shuffled(l2)) {
         System.out.print(i + " ");
      }
   }

}
