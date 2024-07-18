package Beings.Animals.Species;

import Beings.Animals.Animal;
import Beings.Animals.Diet;
import Beings.BeingsNames;

public class Eagle extends Animal {

    public Eagle(int i, int j, boolean isAlive) {
        super(i, j, isAlive);
        this.weight = 6;
        this.maxSpeed = 3;
        this.appetite = 1;
        this.diet.put(BeingsNames.Duck, 80);
        this.diet.put(BeingsNames.Fox, 10);
        this.diet.put(BeingsNames.Mouse, 90);
        this.diet.put(BeingsNames.Rabbit, 90);
        this.dietType = Diet.Carnivore;
    }
}
