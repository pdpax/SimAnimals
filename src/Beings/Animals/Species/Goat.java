package Beings.Animals.Species;

import Beings.Animals.Animal;
import Beings.Animals.Diet;
import Beings.BeingsNames;

public class Goat extends Animal {

    public Goat(int i, int j, boolean isAlive) {
        super(i, j, isAlive);
        this.weight = 60;
        this.maxSpeed = 3;
        this.appetite = 10;
        this.diet.put(BeingsNames.Plants, 100);
        this.dietType = Diet.Herbivore;
    }
}
