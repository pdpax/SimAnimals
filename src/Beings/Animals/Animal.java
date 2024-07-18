package Beings.Animals;

import Area.Cell;
import Area.Grid;
import Area.Terrain;
import Beings.AllBeings;
import Beings.BeingsNames;
import Beings.Plants.Plants;
import WorldInit.Setup;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

import static java.util.Map.entry;

public class Animal extends AllBeings {
    public static final Map<BeingsNames, Integer> lifeExpectancies = Map.ofEntries( //if they don't get eaten, they die after a set number of turns
            entry(BeingsNames.Bear, 300),
            entry(BeingsNames.Boa, 300),
            entry(BeingsNames.Boar, 200),
            entry(BeingsNames.Buffalo, 200),
            entry(BeingsNames.Caterpillar, 5),
            entry(BeingsNames.Deer, 200),
            entry(BeingsNames.Duck, 100),
            entry(BeingsNames.Eagle, 300),
            entry(BeingsNames.Fox, 140),
            entry(BeingsNames.Goat, 150),
            entry(BeingsNames.Horse, 300),
            entry(BeingsNames.Mouse, 20),
            entry(BeingsNames.Rabbit, 100),
            entry(BeingsNames.Sheep, 120),
            entry(BeingsNames.Wolf, 160),
            entry(BeingsNames.Vulture, 300)
    );

    public boolean isAlive = true;
    public int decomposing;    //max 5 days (turns)
    public Sex sex;
    public int age;    //number of turns taken
    public double hunger;
    public double appetite;   //kg of food needed to be full
    public int maxSpeed; //number of locations per turn
    public int waitToBreed;   //a female becomes unavailable for breeding for x turns every time it breeds
    public static final int WAIT_TO_BREED_TIME = 10;
    public int waitToBreedTime;
    public Map<BeingsNames, Integer> diet = new TreeMap<>(Comparator.reverseOrder());
    public BeingsNames favoriteFood;
    public Diet dietType;

    public Animal(int iY, int jX, boolean isAlive) {
        super(iY, jX);
        this.isAlive = isAlive;
        this.hunger = 0;
        this.decomposing = 0;
        this.waitToBreed = 0;
        this.sex = new Random().nextBoolean() ? Sex.male : Sex.female;
        this.age = 0;
    }

    public void increaseAge() {
        if (this.isAlive) {
            this.age++;
        }
    }

    public void checkLifeExpectancy() {
        if (this.isAlive && lifeExpectancies.get(this.beingName).equals(this.age)) {
            this.death();
        }
    }

    public boolean decompose() {
        if (this.isAlive) return false;
        this.decomposing++;
        return this.decomposing == 6 || this.weight <= 0; //animals won't eat it after it reaches 5
    }

    /*  death:
     *   !:  death only sets isAlive to false and decrements the counter.
     *       It does not remove the animal from the animalList.
     *       Only being completely eaten or decomposed will remove an animal from the list.
     *       And so does their movement to another cell.
     * */

    public void death() {
        if (this.isAlive) {
            this.isAlive = false;
            Setup.map[this.iY][this.jX].count.put(this.beingName, Setup.map[this.iY][this.jX].count.get(this.beingName) - 1);
        }
    }

    /*  starve:
     *   Only proceed if the animal is alive.
     *   Add 10% of appetite to hunger.
     *   Animal loses 10% of its current weight if its hunger is more than its appetite.
     *   Animal dies if its weight reduces to 20% of its original weight.
     * */

    public void starve() {
        if (this.isAlive) {
            this.hunger += this.appetite * 0.1;
            if (this.hunger >= this.appetite) {
                this.weight -= this.weight * 0.1;
            }
            double maxWeight = Grid.weights.get(this.beingName);
            if (this.weight <= maxWeight * 0.2) {
                this.death();
            }
        }
    }

    /*  eat:
     *   Proceed only if the animal is hungry.
     *   Seek Food.
     *   Return if no food was found.
     *   Eat the food.
     *   Repeat until no longer hungry or no more food to eat.
     * */

    public void eat() {

        /*
         * Number of kilograms of food required to fully feed the animal is its bite size.
         * If there is less food than bite size, all of it is consumed by the animal and reduces hunger accordingly
         * */

        while (this.hunger > 0) {

            AllBeings food = seekFood();

            if (food == null) {
                return;
            }

            //we have to cast, because getEaten methods are different for plants and animals
            if (food.beingName == BeingsNames.Plants) {
                ((Plants) food).getEaten(this);
            } else {
                ((Animal) food).getEaten(this);
            }
        }
    }


    /*  seekFood
     *   Give herbivores the first plant on the plantsList. Return null if no plants.
     *   Give omnivores who favor plants the first plant on the plantsList.
     *   Give vultures the first dead animal on the animalList. Return null if no dead animals.
     *   Look for the favorite food and return it if it exists.
     *   If favorite food isn't available, return the next highest dietary preferences on the animalList.
     *   Return null if no food.
     * */

    public AllBeings seekFood() {

        if (this.dietType == Diet.Herbivore) {
            if (Setup.map[this.iY][this.jX].plantsList.size() > 0) {
                return Setup.map[this.iY][this.jX].plantsList.get(0);
            } else {
                return null;
            }
        }

        if (this.dietType == Diet.Omnivore
                && this.favoriteFood == BeingsNames.Plants
                && Setup.map[this.iY][this.jX].plantsList.size() > 0) {

            return Setup.map[this.iY][this.jX].plantsList.get(0);

        }

        if (this.beingName == BeingsNames.Vulture) {
            for (Animal animal : Setup.map[this.iY][this.jX].animalList) {
                if (!animal.isAlive) {
                    return animal;
                }
            }

            return null;
        }

//else if carnivore and not vulture
// or an omnivore whose favorite food is not plants
// or an omnivore whose favorite food is plants but there are no plants
// AND if the favorite food is available:

        if (Setup.map[this.iY][this.jX].count.get(this.favoriteFood) > 0) {
            for (Animal animal : Setup.map[this.iY][this.jX].animalList) {
                if (animal.beingName == this.favoriteFood) {
                    return animal;
                }
            }
        }

//if favorite food is not available

        BeingsNames foodType = findAvailableFoodType(); //not doing this right away just to save time
        for (Animal animal : Setup.map[this.iY][this.jX].animalList) {
            if (animal.beingName == foodType) {
                return animal;
            }
        }
        return null;
    }

    public BeingsNames findAvailableFoodType() {
        int highlightedFoodInterestValue = 0;
        BeingsNames food = null;
        for (BeingsNames foodCandidate : this.diet.keySet()) {
            if (this.diet.get(foodCandidate) > highlightedFoodInterestValue
                    && Setup.map[this.iY][this.jX].count.get(foodCandidate) > 0) {
                highlightedFoodInterestValue = this.diet.get(foodCandidate);
                food = foodCandidate;
            }
        }
        return food;
    }


    public BeingsNames findFavoriteFood() {
        int max = 0;
        BeingsNames favoriteFood = null;
        for (BeingsNames beingName : this.diet.keySet()) {
            if (this.diet.get(beingName) > max) {
                max = this.diet.get(beingName);
                favoriteFood = beingName;
            }
        }
        return favoriteFood;
    }


    /*  getEaten:
     *   Living preys are killed.
     *   The prey's weight and the hunter's appetite are adjusted according to one another.
     *   If the prey's weight becomes 0, the prey is removed from the animalList.
     * */

    public void getEaten(Animal hunter) {
        if (this.isAlive) {
            this.death();
        }
        if (this.weight > hunter.appetite) {
            this.weight -= hunter.appetite;
            hunter.hunger = 0;
        } else if (this.weight == hunter.appetite) {
            this.weight = 0;
            hunter.hunger = 0;
            Setup.map[this.iY][this.jX].removeAnimal(this);
        } else {
            hunter.hunger -= this.weight;
            Setup.map[this.iY][this.jX].removeAnimal(this);
        }
    }

    /*  breed:
     *  Proceed only if the animal is male, there is more than one of that animal in the cell,
     *  and the current count of that animal in the cell doesn't exceed the permitted maximum limit.
     *  Seek mate.
     *  Return if no mate.
     *  Create an offspring of the same type.
     *  If the offspring is alive, find its favorite food and increment the counter.
     *  Add the offspring to the animalList.
     * */

    public void breed() {
        Cell cell = Setup.map[this.iY][this.jX];
        int currentCount = cell.count.get(this.beingName);
        int maxPerCellAllowed = Grid.maxPerCell.get(this.beingName);
        if (this.sex == Sex.female
                || currentCount < 2
                || currentCount == maxPerCellAllowed) {
            return;
        }

        Animal mate = this.seekMate();
        if (mate == null) return;

        try {
            // Creating the animal
            String className = "Beings.Animals.Species." + this.beingName.name();
            Class<?> animalClass = Class.forName(className);
            Constructor<?> constructor = animalClass.getDeclaredConstructor(int.class, int.class, boolean.class);
            Object instance = constructor.newInstance(this.iY, this.jX, isAlive);

            // Adding the animal to the cell's counter and list
            Animal offspring = (Animal) instance;
            BeingsNames theBeing = BeingsNames.valueOf(offspring.getClass().getSimpleName());

            if (offspring.isAlive) {
                offspring.favoriteFood = offspring.findFavoriteFood();
                cell.count.put(theBeing,
                        cell.count.get(theBeing) + 1);
            }
            cell.animalList.add(offspring);


            //mate.waitToBreed = WAIT_TO_BREED_TIME;

        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException |
                 NoSuchMethodException |
                 InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    /*  seekMate:
    *   Just find a woman that breathes...
    *   Return null if none are found... *sigh*
    * */
    public Animal seekMate() {
        for (Animal mate : Setup.map[this.iY][this.jX].animalList) {
            if (mate.beingName != this.beingName
                    || !mate.isAlive
                    || mate.sex != Sex.female
                //|| mate.waitToBreed > 0
            ) {
                continue;
            }
            return mate;
        }
        return null;
    }

    public void advanceBreedability() {
        if (this.waitToBreed > 0) {
            this.waitToBreed--;
        }
    }

    /*  move:
    *   Proceed only if decideToMove is true.
    *   Randomize trip count based on maxSpeed.
    *   Find the destination.
    *   Return false if all neighboring cells are water and no movement is allowed.
    *   Add animal to transportMap <Animal, Destination Cell> and return true.
    * */

    public boolean move() {
        if (this.decideToMove()) {
            while (true) {
                int tripCount = new Random().nextInt(this.maxSpeed) + 1;
                int[] destination = {this.iY, this.jX};
                for (int k = 0; k < tripCount; k++) {
                    destination = chooseDirection(destination);
                    if (destination == null) {  //all immediate neighbors are water
                        return false;
                    }
                }

                Setup.map[this.iY][this.jX].transportMap.put(this, Setup.map[destination[0]][destination[1]]);
                return true;

            }
        }
        return false;
    }

    public boolean decideToMove() {
        if (this.maxSpeed == 0) {   //stationary, e.g. caterpillar
            return false;
        }
        if (!this.isAlive) {  //dead animals don't move
            return false;
        }
        AllBeings food = seekFood();
        if (food == null) { //no food
            return true;
        }
        if (Setup.map[this.iY][this.jX].count.get(this.beingName).equals(1)) { //no mate
            return true;
        }

        return (new Random()).nextBoolean();
    }

    public int[] chooseDirection(int[] destination) {

        int newI = destination[0];
        int newJ = destination[1];
        List<Integer> neighbors;
        neighbors = findAvailableNeighbors(newI, newJ);
        if (neighbors.size() == 0) {    //all neighbors are water
            return null;
        }
        int randomDirectionIndex = (new Random()).nextInt(neighbors.size());
        int direction = neighbors.get(randomDirectionIndex);

        switch (direction) {
            case 0 -> newI--;
            case 1 -> {
                newI--;
                newJ++;
            }
            case 2 -> newJ++;
            case 3 -> {
                newI++;
                newJ++;
            }
            case 4 -> newI++;
            case 5 -> {
                newI++;
                newJ--;
            }
            case 6 -> newJ--;
            case 7 -> {
                newI--;
                newJ--;
            }
        }
        return new int[]{newI, newJ};
    }

    private List<Integer> findAvailableNeighbors(int newI, int newJ) {
        //  7   0   1
        //  6   ?   2
        //  5   4   3
        //i-1 go up     i+1 go down     i => y
        //j-1 go left   j+1 go right    j => x
        List<Integer> neighbors = new ArrayList<>();

        int up = newI - 1;
        int down = newI + 1;
        int left = newJ - 1;
        int right = newJ + 1;
        boolean canGoUp = up >= 0; //why did I include 0? up is already the cell above (newI - 1), so the cell above can be 0.
        boolean canGoDown = down < Setup.height;
        boolean canGoLeft = left >= 0;
        boolean canGoRight = right < Setup.width;

        if (canGoUp && !Setup.map[up][newJ].terrain.equals(Terrain.Water)) {
            neighbors.add(0);
        }
        if (canGoUp && canGoRight && !Setup.map[up][right].terrain.equals(Terrain.Water)) {
            neighbors.add(1);
        }
        if (canGoRight && !Setup.map[newI][right].terrain.equals(Terrain.Water)) {
            neighbors.add(2);
        }
        if (canGoDown && canGoRight && !Setup.map[down][right].terrain.equals(Terrain.Water)) {
            neighbors.add(3);
        }
        if (canGoDown && !Setup.map[down][newJ].terrain.equals(Terrain.Water)) {
            neighbors.add(4);
        }
        if (canGoDown && canGoLeft && !Setup.map[down][left].terrain.equals(Terrain.Water)) {
            neighbors.add(5);
        }
        if (canGoLeft && !Setup.map[newI][left].terrain.equals(Terrain.Water)) {
            neighbors.add(6);
        }
        if (canGoLeft && canGoUp && !Setup.map[up][left].terrain.equals(Terrain.Water)) {
            neighbors.add(7);
        }
        return neighbors;
    }


}