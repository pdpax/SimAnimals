package Beings.Animals.Species;

import Beings.Animals.Animal;
import Beings.Animals.Diet;
import Beings.BeingsNames;

public class Boar extends Animal {

    public Boar(int i, int j, boolean isAlive) {
        super(i, j, isAlive);
        this.weight = 400;
        this.maxSpeed = 2;
        this.appetite = 50;
        this.diet.put(BeingsNames.Caterpillar, 90);
        this.diet.put(BeingsNames.Mouse, 50);
        this.diet.put(BeingsNames.Plants, 100);
        this.dietType = Diet.Omnivore;
    }
}
