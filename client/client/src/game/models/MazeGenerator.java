/* ============================================================================
 * Nom du fichier   : MazeGenerator.java
 * ============================================================================
 * Date de création : 16 mai 2013
 * ============================================================================
 * Auteurs          : Crescenzio Fabio
 *                    Decorvet Grégoire
 *                    Jaquier Kevin
 *                    Schweizer Thomas
 * ============================================================================
 */
package game.models;

import game.models.Map.Tile;

import java.util.LinkedList;
import java.util.Random;

/**
 * TODO
 * 
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 * 
 */
public class MazeGenerator {

   private static final int MAZE_SIZE = 5;
   private static final int GRID_SIZE = (MAZE_SIZE - 1) / 2;
   
   private MazeTile[][] maze;
   private Random rand;
   private Tile start;
   private boolean[][] visited;
   private LinkedList<Tile> pathStack;
   
   public MazeGenerator() {
      rand = new Random();
   }
   
   public MazeGenerator(long seed) {
      rand = new Random(seed);
   }
   
   private void init(Tile[][] grid) {
      grid = new Tile[GRID_SIZE][GRID_SIZE];
      maze = new MazeTile[MAZE_SIZE][MAZE_SIZE];
      visited = new boolean[MAZE_SIZE][MAZE_SIZE];
      pathStack = new LinkedList<Tile>();
      for (int i = 0; i < MAZE_SIZE; i++) {
         for (int j = 0; j < MAZE_SIZE; j++) {
            // Rempli tout sauf les cases correspondant à un sommet du graphe
            // (sommets de coordonnées impaires)
            if ((i % 2) == 1 && (j % 2) == 1)
               maze[i][j].fill();
            else
               maze[i][j].empty();
         }
      }
   }
   
   private LinkedList<Tile> nextVertices(Tile vertex) {
      LinkedList<Tile> next = new LinkedList<Tile>();
//      for (Tile adj : maze.getAdjacents(vertex)) {
//         // On prend le sommet si pas encore visité
//         if (!visited.get(adj).booleanValue()) {
//            next.add(adj);
//         }
//      }
      return next;
   }
   
   
   /**
    * Génère un labyrinthe sur le graphe donné selon
    * l'algorithme de de l'exploration exhaustive.
    */
   public Tile[][] generateMaze(Tile[][] grid) {
      init(grid);
//      LinkedList<Tile> nextSteps;
//      Tile nextVertice;
//      Tile current = start;
//      do {
//         // Ajoute le sommet à la pile et le marque comme visité
//         pathStack.push(current);
//         visited.put(current, true);
//         
//         // Passe à un sommet suivant aléatoire ou remonte s'il n'y
//         // en a pas
//         nextSteps = nextVertices(current);
//         if (nextSteps.size() > 0) {
//            pathStack.pop();
//            if (pathStack.size() > 0) {
//               current = pathStack.element();
//            } else {
//               break;
//            }
//         } else {
//            nextVertice = nextSteps.get(rand.nextInt(nextSteps.size()));
//            //maze.addEdge(current, nextVertice);
//            current = nextVertice;
//         }
//         
//      } while (pathStack.size() > 0);
      
      return grid;
   }
   
   public static Tile[][] createTest(int size) {
      Tile[][] grid = new Tile[size][size];
      Random r = new Random();
      Tile[] tileTypes = Tile.values();

      for (int i = 0; i < grid.length; i++) {
         for (int j = 0; j < grid[i].length; j++) {
            grid[i][j] = tileTypes[r.nextInt(2)];
         }
      }

      return grid;
   }

}
