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
package game.models.map;

import game.models.Spawner;
import game.models.Team;

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

   private static final int[] NEIGHBOURS_COORDS = new int[] { -1, +1 };

   private int mazeSize;
   private int gridSize;
   private Tile[][] grid;
   private MazeTile[][] maze;
   private Random rand;
   private boolean[][] visited;
   private LinkedList<MazeTile> pathStack;

   public MazeGenerator() {
      rand = new Random();
   }

   public MazeGenerator(long seed) {
      rand = new Random(seed);
   }

   private void init(int size) {
      mazeSize = size;
      gridSize = 2 * mazeSize + 1;
      grid = new Tile[gridSize][gridSize];
      maze = new MazeTile[mazeSize][mazeSize];
      visited = new boolean[mazeSize][mazeSize];
      pathStack = new LinkedList<MazeTile>();

      // Rempli la map de murs
      for (int i = 0; i < gridSize; i++)
         for (int j = 0; j < gridSize; j++)
            grid[i][j] = Tile.WALL;

      // Met des cases vides aux sommets du labyrinthe
      for (int i = 0; i < mazeSize; i++) {
         for (int j = 0; j < mazeSize; j++) {
            int x = gridCoord(i);
            int y = gridCoord(j);
            grid[x][y] = Tile.EMPTY;
            maze[i][j] = new MazeTile(grid[x][y], i, j);
         }
      }
   }

   public static int gridCoord(int mazeCoord) {
      return 2 * mazeCoord + 1;
   }

   private LinkedList<MazeTile> nextVertices(MazeTile current) {
      LinkedList<MazeTile> next = new LinkedList<MazeTile>();
      for (int i : NEIGHBOURS_COORDS) {
         int nextX = current.x + i;
         int nextY = current.y + i;
         if (nextX >= 0 && nextX < mazeSize && !visited[nextX][current.y])
            next.add(maze[nextX][current.y]);
         if (nextY >= 0 && nextY < mazeSize && !visited[current.x][nextY])
            next.add(maze[current.x][nextY]);
      }
      return next;
   }

   /**
    * Génère un labyrinthe sur le graphe donné selon l'algorithme de de
    * l'exploration exhaustive.
    */
   public Tile[][] generateMaze(int size) {
      init(size);
      LinkedList<MazeTile> nextSteps;
      MazeTile nextVertice;
      MazeTile current = maze[0][0];

      do {
         // Ajoute le sommet à la pile et le marque comme visité
         pathStack.push(current);
         visited[current.x][current.y] = true;

         // Passe à un sommet suivant aléatoire ou remonte s'il n'y
         // en a pas
         nextSteps = nextVertices(current);
         if (nextSteps.size() > 0) {
            nextVertice = nextSteps.get(rand.nextInt(nextSteps.size()));
            addEdge(current, nextVertice);
            current = nextVertice;
         }
         else {
            pathStack.pop();
            if (pathStack.size() > 0) {
               // Revient à la tête (se remet dans la pile à la prochaine
               // itération)
               current = pathStack.pop();
            }
            else {
               break;
            }
         }
      } while (pathStack.size() > 0);

      addExit();
      
      return grid;
   }

   /**
    * 
    */
   public LinkedList<Spawner> generateSpawners(Team[] teams) {
      LinkedList<Spawner> spawners = new LinkedList<Spawner>(); 
      int x, y;
      for (int i = 0; i < mazeSize; i++) {
         for (int j = 0; j < mazeSize; j++) {
            x = gridCoord(i);
            y = gridCoord(j);
            if (grid[x][y] != Tile.EXIT) {
               grid[x][y] = Tile.SPAWNER;//.spawner((x + y) % (nbTeams + 1));
               spawners.add(new Spawner(x, y, teams[(x + y) % teams.length]));
            }
         }
      }
      return spawners;
   }

   /**
    * 
    */
   private void addExit() {
      int x = gridCoord(rand.nextInt(mazeSize));
      int y = gridCoord(rand.nextInt(mazeSize));
      grid[x][y] = Tile.EXIT;
   }
   
   /**
    * Crée le chemin entre les deux sommets (doivent être à une case l'un de
    * l'autre)
    * 
    * @param current
    * @param nextVertice
    */
   private void addEdge(MazeTile current, MazeTile nextVertice) {
      int edgeX = (gridCoord(current.x) + gridCoord(nextVertice.x)) / 2;
      int edgeY = (gridCoord(current.y) + gridCoord(nextVertice.y)) / 2;
      grid[edgeX][edgeY] = Tile.EMPTY;
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
