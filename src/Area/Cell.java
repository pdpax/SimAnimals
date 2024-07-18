package Area;


import App.Main;
import Beings.Animals.Animal;
import Beings.BeingsNames;
import Beings.Plants.Plants;

import java.util.*;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CyclicBarrier;

import static java.util.Map.entry;


public class Cell implements Runnable {
    public int turn = 0;
    public int iY, jX;
    public Terrain terrain;
    private CyclicBarrier barrier;

    public Cell(int iY, int jX) {
        this.iY = iY;
        this.jX = jX;
    }

    public void setBarrier(CyclicBarrier barrier) {
        this.barrier = barrier;
    }

    public final Map<BeingsNames, Integer> count = Collections.synchronizedMap(new HashMap<>(Map.ofEntries(
            entry(BeingsNames.Plants, 0),
            entry(BeingsNames.Bear, 0),
            entry(BeingsNames.Boa, 0),
            entry(BeingsNames.Boar, 0),
            entry(BeingsNames.Buffalo, 0),
            entry(BeingsNames.Caterpillar, 0),
            entry(BeingsNames.Deer, 0),
            entry(BeingsNames.Duck, 0),
            entry(BeingsNames.Eagle, 0),
            entry(BeingsNames.Fox, 0),
            entry(BeingsNames.Goat, 0),
            entry(BeingsNames.Horse, 0),
            entry(BeingsNames.Mouse, 0),
            entry(BeingsNames.Rabbit, 0),
            entry(BeingsNames.Sheep, 0),
            entry(BeingsNames.Wolf, 0),
            entry(BeingsNames.Vulture, 0)
    )
    ));

    public final List<Animal> animalList = new CopyOnWriteArrayList<>();
    public final List<Plants> plantsList = new CopyOnWriteArrayList<>();

    public final Map<Animal, Cell> transportMap = Collections.synchronizedMap(new HashMap<>());

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            turn++;
            Plants.pollinate(this);

            for (Animal animal : animalList) {
                boolean removable = animal.decompose();
                if (removable) {
                    removeAnimal(animal);
                    continue;
                }
                if (!animal.isAlive) continue;
                animal.increaseAge();
                animal.checkLifeExpectancy();
                if (!animal.isAlive) continue;

                animal.starve();
                if (!animal.isAlive) continue;
                animal.eat();
                animal.breed();
                //animal.advanceBreedability();

                boolean hasMoved = animal.move();
                if (hasMoved) {
                    removeAnimal(animal);
                }


            }

            if (Main.landExists) {
                barrierWait();
            }
            sleep(4000);

        }
    }

    public void removeAnimal(Animal animal) {
        animalList.remove(animal);
    }

    private void barrierWait() {
        try {
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
    }

    private void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}