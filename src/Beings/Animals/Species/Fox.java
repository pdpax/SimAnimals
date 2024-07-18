package Beings.Animals.Species;

import Beings.Animals.Animal;
import Beings.Animals.Diet;
import Beings.BeingsNames;

public class Fox extends Animal {

    public Fox(int i, int j, boolean isAlive) {
        super(i, j, isAlive);
        this.weight = 8;
        this.maxSpeed = 2;
        this.appetite = 2;
        this.diet.put(BeingsNames.Boar, 5);
        this.diet.put(BeingsNames.Caterpillar, 40);
        this.diet.put(BeingsNames.Deer, 5);
        this.diet.put(BeingsNames.Duck, 60);
        this.diet.put(BeingsNames.Mouse, 90);
        this.diet.put(BeingsNames.Rabbit, 70);
        this.dietType = Diet.Carnivore;
    }
}
