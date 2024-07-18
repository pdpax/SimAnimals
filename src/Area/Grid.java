package Area;

import Beings.BeingsNames;

import java.util.Map;

import static java.util.Map.entry;

public class Grid {
    public static Map<BeingsNames, Integer> maxPerCell = getMap(MaxPerCellType.Standard);

    private static Map<BeingsNames, Integer> getMap(MaxPerCellType type) {

        return switch (type) {
            case Standard -> Map.ofEntries(
                    entry(BeingsNames.Bear, 5),
                    entry(BeingsNames.Boa, 30),
                    entry(BeingsNames.Boar, 50),
                    entry(BeingsNames.Buffalo, 10),
                    entry(BeingsNames.Caterpillar, 1000),
                    entry(BeingsNames.Deer, 20),
                    entry(BeingsNames.Duck, 200),
                    entry(BeingsNames.Eagle, 20),
                    entry(BeingsNames.Fox, 30),
                    entry(BeingsNames.Goat, 140),
                    entry(BeingsNames.Horse, 20),
                    entry(BeingsNames.Mouse, 500),
                    entry(BeingsNames.Plants, 10000),
                    entry(BeingsNames.Rabbit, 150),
                    entry(BeingsNames.Sheep, 140),
                    entry(BeingsNames.Wolf, 30),
                    entry(BeingsNames.Vulture, 20)
            );
            case Customized -> Map.ofEntries(
                    entry(BeingsNames.Bear, 20),
                    entry(BeingsNames.Boa, 30),
                    entry(BeingsNames.Boar, 80),
                    entry(BeingsNames.Buffalo, 50),
                    entry(BeingsNames.Caterpillar, 1000),
                    entry(BeingsNames.Deer, 50),
                    entry(BeingsNames.Duck, 200),
                    entry(BeingsNames.Eagle, 20),
                    entry(BeingsNames.Fox, 60),
                    entry(BeingsNames.Goat, 140),
                    entry(BeingsNames.Horse, 20),
                    entry(BeingsNames.Mouse, 5000),
                    entry(BeingsNames.Plants, 10000),
                    entry(BeingsNames.Rabbit, 400),
                    entry(BeingsNames.Sheep, 140),
                    entry(BeingsNames.Wolf, 30),
                    entry(BeingsNames.Vulture, 30)
            );
            case AllOne -> Map.ofEntries(
                    entry(BeingsNames.Bear, 1),
                    entry(BeingsNames.Boa, 1),
                    entry(BeingsNames.Boar, 1),
                    entry(BeingsNames.Buffalo, 1),
                    entry(BeingsNames.Caterpillar, 1),
                    entry(BeingsNames.Deer, 1),
                    entry(BeingsNames.Duck, 1),
                    entry(BeingsNames.Eagle, 1),
                    entry(BeingsNames.Fox, 1),
                    entry(BeingsNames.Goat, 1),
                    entry(BeingsNames.Horse, 1),
                    entry(BeingsNames.Mouse, 1),
                    entry(BeingsNames.Plants, 1),
                    entry(BeingsNames.Rabbit, 1),
                    entry(BeingsNames.Sheep, 1),
                    entry(BeingsNames.Wolf, 1),
                    entry(BeingsNames.Vulture, 1)
            );
            case AllTwo -> Map.ofEntries(
                    entry(BeingsNames.Bear, 2),
                    entry(BeingsNames.Boa, 2),
                    entry(BeingsNames.Boar, 2),
                    entry(BeingsNames.Buffalo, 2),
                    entry(BeingsNames.Caterpillar, 2),
                    entry(BeingsNames.Deer, 2),
                    entry(BeingsNames.Duck, 2),
                    entry(BeingsNames.Eagle, 2),
                    entry(BeingsNames.Fox, 2),
                    entry(BeingsNames.Goat, 2),
                    entry(BeingsNames.Horse, 2),
                    entry(BeingsNames.Mouse, 2),
                    entry(BeingsNames.Plants, 2),
                    entry(BeingsNames.Rabbit, 2),
                    entry(BeingsNames.Sheep, 2),
                    entry(BeingsNames.Wolf, 2),
                    entry(BeingsNames.Vulture, 2)
            );
            case OnlyFew -> Map.ofEntries(
                    entry(BeingsNames.Bear, 10),
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
                    entry(BeingsNames.Mouse, 100),
                    entry(BeingsNames.Plants, 1000),
                    entry(BeingsNames.Rabbit, 0),
                    entry(BeingsNames.Sheep, 0),
                    entry(BeingsNames.Wolf, 0),
                    entry(BeingsNames.Vulture, 0)
            );
            default -> throw new IllegalArgumentException("Unexpected Input");
        };

    }

    public static Map<BeingsNames, Double> weights = Map.ofEntries(
            entry(BeingsNames.Bear, 500.0),
            entry(BeingsNames.Boa, 15.0),
            entry(BeingsNames.Boar, 400.0),
            entry(BeingsNames.Buffalo, 700.0),
            entry(BeingsNames.Caterpillar, 0.01),
            entry(BeingsNames.Deer, 100.0),
            entry(BeingsNames.Duck, 1.0),
            entry(BeingsNames.Eagle, 6.0),
            entry(BeingsNames.Fox, 8.0),
            entry(BeingsNames.Goat, 60.0),
            entry(BeingsNames.Horse, 400.0),
            entry(BeingsNames.Mouse, 0.05),
            entry(BeingsNames.Plants, 1.0),
            entry(BeingsNames.Rabbit, 2.0),
            entry(BeingsNames.Sheep, 70.0),
            entry(BeingsNames.Wolf, 50.0),
            entry(BeingsNames.Vulture, 6.0)
    );


    public static Map<Terrain, Integer> soilFertility = Map.ofEntries(
            entry(Terrain.Fertile, 10000),
            entry(Terrain.Dry, 5000),
            entry(Terrain.Desert, 1000),
            entry(Terrain.Water, 0)
    );
}
