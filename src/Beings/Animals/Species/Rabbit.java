package Beings.Animals.Species;

import Beings.Animals.Animal;
import Beings.Animals.Diet;
import Beings.BeingsNames;

public class Rabbit extends Animal {

    public Rabbit(int i, int j, boolean isAlive) {
        super(i, j, isAlive);
        this.weight = 2;
        this.maxSpeed = 2;
        this.appetite = 0.45;
        this.diet.put(BeingsNames.Plants, 100);
        this.dietType = Diet.Herbivore;
    }
}
