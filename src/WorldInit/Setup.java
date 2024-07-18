package WorldInit;

import Area.Cell;
import Area.Grid;
import Area.Terrain;
import Beings.Animals.Animal;
import Beings.BeingsNames;
import Beings.Plants.Plants;
import Display.Display;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CyclicBarrier;

import static Util.Input.getInt;

public class Setup {
    public static int width;
    public static int height;
    public static Cell[][] map;
    public static List<Thread> cellThreads = new ArrayList<>();
    private static final Random random = new Random();
    public static CyclicBarrier barrier;

    public static void setup() {
        getGridSize();
        mapSetup();
        Thread tDisplay = new Thread(new Display());
        tDisplay.setPriority(Thread.MAX_PRIORITY);
        tDisplay.setName("Display");
        tDisplay.start();
    }

    private static void getGridSize() {
      /*
        System.out.print("Enter Grid width (1-5): ");
        width = getInt(1, 5);
        System.out.print("Enter Grid height (1-5): ");
        height = getInt(1, 5);
    */
        System.out.print("Enter Grid Size (1-5): ");
        width = getInt(1, 5);
        height = width;
    }

    /*  mapSetup:
    *   Create the map and construct its cells
    *   Assign a terrain to each cell
    *   Spawn animals for the first time
    *   Create a thread for each cell
    *   Add each thread to cellThreads -- to start them from App.Main.main
    * */

    private static void mapSetup() {
        map = new Cell[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                map[i][j] = new Cell(i, j);

                map[i][j].terrain = switch (random.nextInt(4) + 1) {
                    case 1 -> Terrain.Water;
                    case 2 -> Terrain.Fertile;
                    case 3 -> Terrain.Dry;
                    case 4 -> Terrain.Desert;
                    default -> throw new IllegalStateException("Unexpected value");
                };


                spawnInit(map[i][j]);
                if (map[i][j].terrain != Terrain.Water) {
                    Thread tCell = new Thread(map[i][j]);
                    tCell.setName("Cell " + i + "," + j);  //Cell y,x not x,y as in display
                    tCell.setPriority(Thread.MAX_PRIORITY);
                    cellThreads.add(tCell);
                }
            }
        }
    }


    /*  spawnInit:
    *   Skip if terrain is water. There are no fish in this program.
    *   Randomize the spawn number for each being
    *   Create beings
    *   For animals, find and assign favorite food to speed up the search for food
    *   !:  Dead animals can be spawned to sustain vultures. In earlier editions,
    *       vultures were created to take care of the dead animals, but they became unnecessary in the later editions.
    *       They are kept in the program regardless.
    *   Increment the cell's counter of the created animal if the being is alive.
    *   !:  Plants' counters are changed in Plants.
    *   Add the animal to the cell's animalList
    * */

    private static void spawnInit(Cell cell) {
        if (cell.terrain == Terrain.Water) {
            return;
        }
        for (BeingsNames being : BeingsNames.values()) {
            int spawnNumber;
            if (being.equals(BeingsNames.Plants)) {
                spawnNumber = random.nextInt(Grid.soilFertility.get(cell.terrain)) + 1;
            } else {
                if (Grid.maxPerCell.get(being) > 0) {   //in case we're testing using MaxPerCellType and some are set to 0
                    spawnNumber = random.nextInt(Grid.maxPerCell.get(being)) + 1;
                } else {
                    spawnNumber = 0;
                }
            }

            for (int i = 0; i < spawnNumber; i++) {
                if (being.equals(BeingsNames.Plants)) {
                    Plants.createPlant(cell);
                } else {
                    try {
                        boolean isAlive = random.nextInt(10) > 4; // 50% chance of spawning dead animals

                        // Creating the animal
                        String className = "Beings.Animals.Species." + being.name();

                        Class<?> animalClass = Class.forName(className);
                        Constructor<?> constructor = animalClass.getDeclaredConstructor(int.class, int.class, boolean.class);
                        Object instance = constructor.newInstance(cell.iY, cell.jX, isAlive);

                        // Adding the animal to the cell's counter and list
                        Animal animal = (Animal) instance;
                        animal.favoriteFood = animal.findFavoriteFood();
                        BeingsNames theBeing = BeingsNames.valueOf(animal.getClass().getSimpleName());
                        if (animal.isAlive) {
                            cell.count.put(theBeing, cell.count.getOrDefault(theBeing, 0) + 1);
                        }
                        cell.animalList.add(animal);

                    } catch (ClassNotFoundException | IllegalAccessException | InstantiationException |
                             NoSuchMethodException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}