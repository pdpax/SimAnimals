package Beings.Animals.Species;

import Beings.Animals.Animal;
import Beings.Animals.Diet;
import Beings.BeingsNames;

public class Sheep extends Animal {

    public Sheep(int i, int j, boolean isAlive) {
        super(i, j, isAlive);
        this.weight = 70;
        this.maxSpeed = 3;
        this.appetite = 15;
        this.diet.put(BeingsNames.Plants, 100);
        this.dietType = Diet.Herbivore;
    }
}
