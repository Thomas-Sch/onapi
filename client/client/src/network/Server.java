/* ============================================================================
 * Nom du fichier   : Server.java
 * ============================================================================
 * Date de création : 1 mai 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package network;

/**
 * Classe singleton pour la communication avec le serveur de jeu.
 * 
 * Singleton Pattern :
 * http://fr.wikipedia.org/wiki/Singleton_(patron_de_conception)
 * 
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 * 
 */
public final class Server {

   private static volatile Server instance = null;

   private Server() {
      super();
   }

   /**
    * Retourne l'unique instance du serveur, et la crée si elle n'a 
    * pas encore déjà été instanciée.
    * 
    * @return L'instance unique de l'objet serveur.
    */
   public final static Server getInstance() {
      if (instance == null) {
         synchronized (Server.class) {
            if (instance == null) {
               instance = new Server();
            }
         }
      }
      return instance;
   }
   
   public void connect() {
      // TODO Implémenter la connexion du serveur
   }
   
   public void disconnect() {
      // TODO Implémenter la déconnexion du serveur
   }


}
