package Beings.Animals.Species;

import Beings.Animals.Animal;
import Beings.Animals.Diet;
import Beings.BeingsNames;

public class Deer extends Animal {

    public Deer(int i, int j, boolean isAlive) {
        super(i, j, isAlive);
        this.weight = 100;
        this.maxSpeed = 4;
        this.appetite = 50;
        this.diet.put(BeingsNames.Plants, 100);
        this.dietType = Diet.Herbivore;
    }
}
