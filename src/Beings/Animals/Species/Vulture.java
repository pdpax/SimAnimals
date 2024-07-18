package Beings.Animals.Species;

import Beings.Animals.Animal;
import Beings.Animals.Diet;
import Beings.BeingsNames;

public class Vulture extends Animal {

    public Vulture(int i, int j, boolean isAlive) {
        super(i, j, isAlive);
        this.weight = 50;
        this.maxSpeed = 3;
        this.appetite = 3;
        this.diet.put(BeingsNames.Bear, 100);
        this.diet.put(BeingsNames.Boa, 100);
        this.diet.put(BeingsNames.Boar, 100);
        this.diet.put(BeingsNames.Buffalo, 100);
        this.diet.put(BeingsNames.Deer, 100);
        this.diet.put(BeingsNames.Duck, 100);
        this.diet.put(BeingsNames.Eagle, 100);
        this.diet.put(BeingsNames.Fox, 100);
        this.diet.put(BeingsNames.Goat, 100);
        this.diet.put(BeingsNames.Horse, 100);
        this.diet.put(BeingsNames.Mouse, 100);
        this.diet.put(BeingsNames.Rabbit, 100);
        this.diet.put(BeingsNames.Sheep, 100);
        this.diet.put(BeingsNames.Wolf, 100);
        this.diet.put(BeingsNames.Vulture, 100);
        this.dietType = Diet.Carnivore;
        //No Caterpillar or Plant
    }
}
