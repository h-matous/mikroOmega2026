package game.data;

/**
 * The <strong>CollectableSpawningData</strong> is responsible for holding the information for spawning collectables
 * <br><br>
 * <strong>minSpawnUpdateDelay</strong> the minimum amount updates that must pass before spawning a new collectable
 * <br>
 * <strong>baseSpawnUpdateDelay</strong> the starting number of updates between spawns, before any Score scaling is applied
 * <br>
 * <strong>collectableSpawnCoefficient</strong> represents the steepness of the logarithmic curve to the base of e, higher value represents smaller delay (higher difficulty) in between spawns over time
 */
public class CollectableSpawningData {
    private final int minSpawnUpdateDelay;
    private final int baseSpawnUpdateDelay;

    private final double collectableSpawnCoefficitent;

    /**
     * Default constructor sets default values
     */
    public CollectableSpawningData() {
        this.minSpawnUpdateDelay = 20; //1/5 of a second
        this.baseSpawnUpdateDelay = 200; //2 seconds

        this.collectableSpawnCoefficitent = 25.0;
    }

    /**
     * Used for getting the minimum amount of updates that must pass before spawning a new collectable
     * @return int representing the number of minimum updates
     */
    public int getMinSpawnUpdateDelay() {
        return minSpawnUpdateDelay;
    }

    /**
     * Used for getting the starting amount of updates between spawns
     * @return int representing the number of base updates
     */
    public int getBaseSpawnUpdateDelay() {
        return baseSpawnUpdateDelay;
    }


    /**
     * Used for getting the collectable spawning coefficient that changes the steepness of a Score dependent curve
     * @return double representing the coefficient
     */
    public double getCollectableSpawnCoefficient() {
        return collectableSpawnCoefficitent;
    }
}