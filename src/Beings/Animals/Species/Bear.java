package Beings.Animals.Species;

import Beings.Animals.Animal;
import Beings.Animals.Diet;
import Beings.BeingsNames;

public class Bear extends Animal {

    public Bear(int i, int j, boolean isAlive) {
        super(i, j, isAlive);
        this.weight = 500;
        this.maxSpeed = 2;
        this.appetite = 80;
        this.diet.put(BeingsNames.Boa, 80);
        this.diet.put(BeingsNames.Boar, 50);
        this.diet.put(BeingsNames.Buffalo, 20);
        this.diet.put(BeingsNames.Deer, 80);
        this.diet.put(BeingsNames.Duck, 10);
        this.diet.put(BeingsNames.Goat, 70);
        this.diet.put(BeingsNames.Horse, 40);
        this.diet.put(BeingsNames.Mouse, 90);
        this.diet.put(BeingsNames.Rabbit, 80);
        this.diet.put(BeingsNames.Sheep, 70);
        this.dietType = Diet.Carnivore;
    }
}
