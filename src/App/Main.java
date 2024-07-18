package App;

import Area.Cell;
import Area.Terrain;
import Beings.Transporter;
import WorldInit.Setup;

import java.util.concurrent.CyclicBarrier;

public class Main {
    public static boolean landExists = false;

    /*  main:
     *  Run the setup.
     *  Check to see if the entire universe is water.
     *  If land exists, proceed to implement the CyclicBarrier task.
     *  Assign the barrier to each cell and run the thread cells
     * */

    public static void main(String[] args) {

        Setup.setup();

        for (int i = 0; i < Setup.map.length; i++) {
            for (int j = 0; j < Setup.map[i].length; j++) {
                if (Setup.map[i][j].terrain != Terrain.Water) {
                    landExists = true;
                    break;
                }
            }
            if (landExists) {
                break;
            }
        }

        if (!landExists) {
            System.out.println("Your world only consists of water. You might want to restart the simulation. :)");
        } else {
            Setup.barrier = new CyclicBarrier(Setup.cellThreads.size(), () -> {

                for (int n = 0; n < 2; n++) {
                    //running transport twice to make sure we transfer as many animals as reasonably possible
                    for (int i = 0; i < Setup.cellThreads.size(); i++) {
                        int iY = Integer.parseInt(Setup.cellThreads.get(i).getName().charAt(5) + "");
                        int jX = Integer.parseInt(Setup.cellThreads.get(i).getName().charAt(7) + "");
                        Transporter.transport(Setup.map[iY][jX].transportMap);
                    }
                }
            });
        }

        for (Cell[] row : Setup.map) {
            for (Cell cell : row) {
                cell.setBarrier(Setup.barrier);
            }
        }

        for (Thread thread : Setup.cellThreads) {
            thread.start();
        }

    }
}