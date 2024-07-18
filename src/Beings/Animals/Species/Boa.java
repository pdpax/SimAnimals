package Beings.Animals.Species;

import Beings.Animals.Animal;
import Beings.Animals.Diet;
import Beings.BeingsNames;

public class Boa extends Animal {

    public Boa(int i, int j, boolean isAlive) {
        super(i, j, isAlive);
        this.weight = 15;
        this.maxSpeed = 1;
        this.appetite = 3;
        this.diet.put(BeingsNames.Boar, 5);
        this.diet.put(BeingsNames.Duck, 10);
        this.diet.put(BeingsNames.Fox, 15);
        this.diet.put(BeingsNames.Horse, 10);
        this.diet.put(BeingsNames.Mouse, 40);
        this.diet.put(BeingsNames.Rabbit, 20);
        this.dietType = Diet.Carnivore;
    }
}
