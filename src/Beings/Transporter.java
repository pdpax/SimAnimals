package Beings;

import Area.Cell;
import Area.Grid;
import Beings.Animals.Animal;
import WorldInit.Setup;

import java.util.*;

public class Transporter {

    /*  transport:
    *   Skip if map is empty.
    *   If the destination's maxPerCell allows, add the animal to the destination cell's animalList.
    *   Adjust the counters.
    *   !:  The animal was already removed from the source's animalList in the cell's run method.
    *   Change the coordinates of the animal to match the destination.
    *   Add animal to the toRemove list.
    *   After going through the whole transportMap, remove the transported animals from the transportMap.
    *   !:  Those who could not move due to maxPerCell, will be moved the next time.
    *   !:  Bad Logic: there should be a limit to how many animals can be in this limbo.
    *       In fact, there shouldn't even be a limbo.
    * */

    public static void transport(Map<Animal, Cell> transportMap) {

        if (transportMap.isEmpty()) {
            return;
        }

        List<Animal> toRemove = new ArrayList<>();

        Iterator<Animal> keyIterator = transportMap.keySet().iterator();
        while (keyIterator.hasNext()) {
            Animal key = keyIterator.next();
            Cell destination = transportMap.get(key);
            if (destination.count.get(key.beingName) < Grid.maxPerCell.get(key.beingName)) {
                destination.animalList.add(key);
                destination.count.put(key.beingName, destination.count.get(key.beingName) + 1);
                Setup.map[key.iY][key.jX].count.put(key.beingName,Setup.map[key.iY][key.jX].count.get(key.beingName)-1);
                key.iY = destination.iY;
                key.jX = destination.jX;
                toRemove.add(key);
            }
        }

        for (Animal key : toRemove) {
            transportMap.remove(key);
        }
    }
}
