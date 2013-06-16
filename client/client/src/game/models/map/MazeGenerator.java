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
 * Crée labyrinthe aléatoirement dans une grille 2D, dans laquelle on définit
 * des sommets, qui correspondent à des cases reliées entre elles par une case
 * intermédiaire. La taille du labyrinthe est donnée en nombre de sommets de
 * côté.
 * 
 * Par exemple, pour un labyrinthe de taille 3, la grille initiale sera la
 * suivante :
 * 
 * <pre>
 * #######
 * # # # #  [#] = Mur
 * #######  [ ] = Sommet du labyrinthe (Sol dans la grille)
 * # # # #
 * #######
 * # # # #
 * #######
 * </pre>
 * 
 * Le générateur relie ensuite les sommets pour former un labyrinthe. Il ajoute
 * ensuite les spawners et la sortie aléatoirement sur les sommets. Voici un
 * exemple de labyrinthe final de taille 6 :
 * 
 * <pre>
 * #############
 * #+#+   + +# #
 * # ### ### # #
 * #+#  +#+#  +#  [#] = Mur
 * # # ### ### #  [ ] = Sommet du labyrinthe (Sol dans la grille)
 * #  +#!   +#+#  [+] = Spawner
 * ####### ### #  [!] = Sortie
 * #+ +  #+#+  #
 * ### # # # ###
 * #+  #+ +# #+#
 * # ####### # #
 * #  + +   + +#
 * #############
 * </pre>
 * 
 * @author Crescenzio Fabio
 * @author Decorvet Grégoire
 * @author Jaquier Kevin
 * @author Schweizer Thomas
 * 
 */
public class MazeGenerator {

   private static final int[] NEIGHBOURS_COORDS = new int[] { -1, +1 };

   /**
    * Taille (en nombre de sommets de côté)
    */
   private int mazeSize;

   /**
    * Taille de la grille (en nombre de cases de côté)
    */
   private int gridSize;

   /**
    * Grille à remplir
    */
   private Tile[][] grid;

   /**
    * Grille des sommets
    */
   private MazeTile[][] maze;

   /**
    * Générateur aléatoire
    */
   private Random rand;

   /**
    * Matrice des sommets : visited[i][j] = true si maze[i][j] est visité, sinon
    * false
    */
   private boolean[][] visited;

   /**
    * Pile représentant le chemin parcouru dans le labyrinthe (voir algorithme
    * de génération)
    */
   private LinkedList<MazeTile> pathStack;

   /**
    * Initialise un générateur sans seed donc dont le résultat n'est pas
    * reproductible
    */
   public MazeGenerator() {
      rand = new Random();
   }

   /**
    * Initialise un générateur avec un seed donné
    * 
    * @param seed
    *           Seed pour la génération aléatoire
    */
   public MazeGenerator(long seed) {
      rand = new Random(seed);
   }

   /**
    * Initialise le générateur pour une taille de labyrinthe donnéee. Crée la
    * grille de façon à avoir que des murs sauf aux sommets où il y a des sols.
    * 
    * Exemple pour une grille de taille 3 :
    * 
    * <pre>
    * #######
    * # # # #  [#] = Mur
    * #######  [ ] = Sommet du labyrinthe (Sol dans la grille)
    * # # # #
    * #######
    * # # # #
    * #######
    * </pre>
    * 
    * @param size
    *           Taille en nombre de sommets de côté
    */
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

   /**
    * Converti un coordonnée de sommet en coordonnée de grille
    * 
    * @param mazeCoord
    *           Coordonnée x ou y d'un sommet
    * @return Coordonnée correspondante dans la grille
    */
   public static int gridCoord(int mazeCoord) {
      return 2 * mazeCoord + 1;
   }

   /**
    * Obtient les sommets accessibles depuis un sommet donné.
    * 
    * @param current
    *           Sommet
    * @return
    */
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
    * Génère un labyrinthe de taille donnés selon l'algorithme de de
    * l'exploration exhaustive.
    * 
    * Renvoie la grille générée. Voir documentation de {@link MazeGenerator}
    * pour plus de détails.
    * 
    * @param size
    *           Taille du labyrinthe à générer, en nombre de sommets de côté
    * 
    */
   public Tile[][] generateMaze(int size) {
      // Le principe de l'algorithme est de parcourir le graphe des sommets du
      // labyrinthe, en passant d'un sommet à un de ses sommets adjacents choisi
      // aléatoirement et pas déjà visité. Lorsqu'on arrive à un cul-de-sac,
      // donc à un sommet qui n'a comme sommet adjacent que celui par lequel on
      // est venu, on revient au sommet précédent et on continue par un autre
      // sommet adjacent.
      // On s'arrête lorsqu'on est remonté jusqu'au sommet de départ.
      //
      // Pour voir la grille comme un graphe, on considère que les sommets sont
      // les
      // cases de coordonnées impaires (voir documentation de MazeGenerator), et
      // que
      // les arètes sont les cases situées entre deux sommets. Deux sommets sont
      // reliés
      // par une arète si la case qui les sépare n'est pas un mur.
      // On modélise le chemin effectué par la pile des sommets nous séparant du
      // sommet
      // de départ (pathStack).

      init(size);
      LinkedList<MazeTile> nextSteps; // sommets adjacents au sommet courant
      MazeTile nextVertice; // sommet adjacent choisi
      MazeTile current = maze[0][0]; // sommet courant (sommet de départ en
                                     // (0,0))

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
      } while (pathStack.size() > 0); // lorsque la pile est vide, on est revenu
                                      // au sommet initial

      // Ajoute la porte de sortie aléatoirement dans la grille
      addExit();

      return grid;
   }

   /**
    * Ajoute des spawners aléatoirement sur une map déjà générée, pour les
    * équipes données.
    * 
    * @param teams
    *           Equipes pour lesquels on génère les spawners
    * @param map
    *           Map à utiliser pour ajouter les spawners. La grille de la map
    *           doit être celle ayant été générée au dernier appel de
    *           {@link MazeGenerator#generateMaze(int)}.
    * @return Liste des spawners créés
    */
   public LinkedList<Spawner> generateSpawners(Team[] teams, Map map) {
      LinkedList<Spawner> spawners = new LinkedList<Spawner>();
      int x, y, idTeam;
      for (int i = 0; i < mazeSize; i++) {
         for (int j = 0; j < mazeSize; j++) {
            x = gridCoord(i);
            y = gridCoord(j);
            if (grid[x][y] != Tile.EXIT) {
               idTeam = (i + j) % (teams.length + 1);
               if (idTeam < teams.length) {
                  grid[x][y] = Tile.SPAWNER;
                  spawners.add(new Spawner(x, y, teams[idTeam], map));
               }
            }
         }
      }
      return spawners;
   }

   /**
    * Ajoute la porte de sortie à une position aléatoire dans la grille
    * (toujours sur un sommet).
    */
   private void addExit() {
      int x = gridCoord(rand.nextInt(mazeSize));
      int y = gridCoord(rand.nextInt(mazeSize));
      grid[x][y] = Tile.EXIT;
   }

   /**
    * Crée le chemin entre les deux sommets (doivent être à une case l'un de
    * l'autre)
    */
   private void addEdge(MazeTile vertice1, MazeTile vertice2) {
      int edgeX = (gridCoord(vertice1.x) + gridCoord(vertice2.x)) / 2;
      int edgeY = (gridCoord(vertice1.y) + gridCoord(vertice2.y)) / 2;
      grid[edgeX][edgeY] = Tile.EMPTY;
   }

   /**
    * @param size Taille de la grille en nombre de cases de côté
    * @return Grille de test, comprenant des murs et des sols mélangés
    * aléatoirement.
    */
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
