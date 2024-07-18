package Beings.Animals.Species;

import Beings.Animals.Animal;
import Beings.Animals.Diet;
import Beings.BeingsNames;

public class Wolf extends Animal {

    public Wolf(int i, int j, boolean isAlive) {
        super(i, j, isAlive);
        this.weight = 50;
        this.maxSpeed = 3;
        this.appetite = 8;
        this.diet.put(BeingsNames.Boar, 15);
        this.diet.put(BeingsNames.Buffalo, 10);
        this.diet.put(BeingsNames.Deer, 15);
        this.diet.put(BeingsNames.Duck, 40);
        this.diet.put(BeingsNames.Goat, 60);
        this.diet.put(BeingsNames.Horse, 10);
        this.diet.put(BeingsNames.Mouse, 80);
        this.diet.put(BeingsNames.Rabbit, 60);
        this.diet.put(BeingsNames.Sheep, 70);
        this.dietType = Diet.Carnivore;
    }
}
