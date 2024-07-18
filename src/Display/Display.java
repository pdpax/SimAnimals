package Display;

import Beings.BeingsNames;

import static WorldInit.Setup.map;

public class Display implements Runnable {

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            System.out.println("\n.................................... New Report ........................................");

            System.out.print("Turns ");
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                    System.out.print(".... Cell " + j + "" + i + ": " + map[i][j].turn);
                }
            }


            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                    System.out.print("\nCell (" + j + "," + i + "):" + map[i][j].terrain + " ---- "); //Cell (X,Y)
                    int m = 0;
                    for (BeingsNames being : map[i][j].count.keySet()) {
                        if (m == 0) {
                            System.out.print(" " + being.name() + ": " + map[i][j].count.get(being));
                        } else {
                            System.out.print(", " + being.name() + ": " + map[i][j].count.get(being));
                        }
                        m++;
                    }
                }
            }

            try {
                Thread.sleep(10000);
            } catch (
                    InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
