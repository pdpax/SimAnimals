package Beings.Animals.Species;

import Beings.Animals.Animal;
import Beings.Animals.Diet;
import Beings.BeingsNames;

public class Mouse extends Animal {

    public Mouse(int i, int j, boolean isAlive) {
        super(i, j, isAlive);
        this.weight = 0.05;
        this.maxSpeed = 1;
        this.appetite = 0.01;
        this.diet.put(BeingsNames.Caterpillar, 90);
        this.diet.put(BeingsNames.Plants, 100);
        this.dietType = Diet.Omnivore;
    }
}
