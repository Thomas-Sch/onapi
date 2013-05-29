/* ============================================================================
 * Nom du fichier   : RandomGenerator.java
 * ============================================================================
 * Date de création : 27 mai 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package utils;

import java.util.LinkedList;

/**
 * Classe permettant de générer des valeurs aléatoires.
 * 
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 * 
 */
public class RandomGenerator {

   private static LinkedList<Integer> alreadyUsedCodes = new LinkedList<>();

   /**
    * Constructeur privé pour empêcher l'instanciation de la classe.
    */
   private RandomGenerator() {
   }

   /**
    * Retourne un code entier aléatoire qui n'est pas actuellement utilisé.
    * 
    * @return Un code alétaoire non utilisé.
    */
   public static int generateConnectionCode() {
      int code;

      do {
         code = (int) (Math.random() * Integer.MAX_VALUE);
      } while (!isCodeFree(code));
      
      synchronized (alreadyUsedCodes) {
         alreadyUsedCodes.add(code);
      }

      return code;
   }

   /**
    * Indique que le code donné n'est plus utilisé.
    * 
    * @param code
    *           - le code à libérer.
    */
   public static void freeConnectionCode(int code) {
      synchronized (alreadyUsedCodes) {
         alreadyUsedCodes.remove(alreadyUsedCodes.indexOf(code));
      }
   }

   /**
    * Vérifie que le code donné ne soit pas utilisé.
    * 
    * @param code
    *           - le code à tester.
    * @return Vrai si le code n'est pas utilisé, faux le cas échéant.
    */
   private static boolean isCodeFree(int code) {
      boolean free = true;
      synchronized (alreadyUsedCodes) {
         free = !alreadyUsedCodes.contains(code);
      }
      return free;
   }

}
