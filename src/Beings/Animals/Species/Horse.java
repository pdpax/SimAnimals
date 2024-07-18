package Beings.Animals.Species;

import Beings.Animals.Animal;
import Beings.Animals.Diet;
import Beings.BeingsNames;

public class Horse extends Animal {

    public Horse(int i, int j, boolean isAlive) {
        super(i, j, isAlive);
        this.weight = 400;
        this.maxSpeed = 4;
        this.appetite = 60;
        this.diet.put(BeingsNames.Plants, 100);
        this.dietType = Diet.Herbivore;
    }
}
