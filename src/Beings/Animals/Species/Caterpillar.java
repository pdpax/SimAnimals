package Beings.Animals.Species;

import Beings.Animals.Animal;
import Beings.Animals.Diet;
import Beings.BeingsNames;

public class Caterpillar extends Animal {

    public Caterpillar(int i, int j, boolean isAlive) {
        super(i, j, isAlive);
        this.weight = 0.01;
        this.maxSpeed = 0;
        this.appetite = 0.001;
        this.diet.put(BeingsNames.Plants, 100);
        this.dietType = Diet.Herbivore;
    }
}
