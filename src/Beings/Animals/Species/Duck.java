package Beings.Animals.Species;

import Beings.Animals.Animal;
import Beings.Animals.Diet;
import Beings.BeingsNames;

public class Duck extends Animal {

    public Duck(int i, int j, boolean isAlive) {
        super(i, j, isAlive);
        this.weight = 1;
        this.maxSpeed = 4;
        this.appetite = 0.15;
        this.diet.put(BeingsNames.Caterpillar, 90);
        this.diet.put(BeingsNames.Plants, 100);
        this.dietType = Diet.Omnivore;
    }
}
