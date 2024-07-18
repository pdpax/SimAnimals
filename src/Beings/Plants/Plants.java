package Beings.Plants;

import Area.Cell;
import Area.Grid;
import Beings.AllBeings;
import Beings.Animals.Animal;
import Beings.BeingsNames;
import WorldInit.Setup;

public class Plants extends AllBeings {

    public Plants(int iY, int jX) {
        super(iY, jX);
        weight = 1;
    }

    public static void createPlant(Cell cell) {
        Plants plant = new Plants(cell.iY, cell.jX);
        cell.count.put(BeingsNames.Plants, cell.count.get(BeingsNames.Plants) + 1);
        cell.plantsList.add(plant);
    }

    public static void pollinate(Cell cell) {

        int plantsInCell = cell.count.get(BeingsNames.Plants);
        int maxPlantsInCell = Grid.soilFertility.get(cell.terrain);
        while (plantsInCell < maxPlantsInCell) {
            createPlant(cell);
            plantsInCell++;
        }

    }

    /* getEaten:
    *   Same as Animal's getEaten
    * */

    public synchronized void getEaten(Animal hunter) {
        if (this.weight > hunter.appetite) {
            this.weight -= hunter.appetite;
            hunter.hunger = 0;
        } else if (this.weight == hunter.appetite) {
            this.weight = 0;
            hunter.hunger = 0;
            this.death();
        } else {
            hunter.hunger -= this.weight;
            this.death();
        }
    }

    private void death() {   //Plants death is both death and removal
        Setup.map[this.iY][this.jX].count.put(BeingsNames.Plants,
                Setup.map[this.iY][this.jX].count.get(BeingsNames.Plants) - 1);
        Setup.map[this.iY][this.jX].plantsList.remove(this);
    }
}
