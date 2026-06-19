package game.data;

public class BananaParticleData {
    //Minimum and maximum count of BananaParticles to spawn (both values are inclusive)
    private int minCount;
    private int maxCount;

    //Minimum and maximum initial upward speed of each BananaParticle before they spawn (both values are inclusive)
    private int initMinUpSpeed;
    private int initMaxUpSpeed;

    //Minimum and maximum initial horizontal speed of each BananaParticle before they spawn (both values are inclusive)
    private int initMinHorizontalSpeed;
    private int initMaxHorizontalSpeed;


    private int downAcceleration;
    private int maxFallingSpeed;

    //By how much should the original calculated render scale be divided (can be just 1 if the desired scale is the same as collectables)
    private float scaleDivisor;


    /**
     * Default constructor sets default values
     */
    public BananaParticleData() {
        this.minCount = 3;
        this.maxCount = 5;

        this.initMinUpSpeed = 12;
        this.initMaxUpSpeed = 16;

        this.initMinHorizontalSpeed = -5;
        this.initMaxHorizontalSpeed = 5;

        this.downAcceleration = 1;
        this.maxFallingSpeed = 5;

        this.scaleDivisor = 2.0f;
    }

    /**
     * Fully parametric constructor
     * @param minCount minimum count of BananaParticles to spawn (inclusive)
     * @param maxCount maximum count of BananaParticles to spawn (inclusive)
     * @param initMinUpSpeed minimum initial upward speed of each BananaParticle before they spawn (inclusive)
     * @param initMaxUpSpeed maximum initial upward speed of each BananaParticle before they spawn (inclusive)
     * @param initMinHorizontalSpeed minimum initial horizontal speed of each BananaParticle before they spawn (inclusive)
     * @param initMaxHorizontalSpeed maximum initial horizontal speed of each BananaParticle before they spawn (inclusive)
     * @param downAcceleration down acceleration of the BananaParticle
     * @param maxFallingSpeed maximum falling speed of the BananaParticle (terminal velocity)
     * @param scaleDivisor render scale divisor as a float
     */
    public BananaParticleData(int minCount, int maxCount, int initMinUpSpeed, int initMaxUpSpeed, int initMinHorizontalSpeed, int initMaxHorizontalSpeed, int downAcceleration, int maxFallingSpeed, float scaleDivisor) {
        this.minCount = minCount;
        this.maxCount = maxCount;
        this.initMinUpSpeed = initMinUpSpeed;
        this.initMaxUpSpeed = initMaxUpSpeed;
        this.initMinHorizontalSpeed = initMinHorizontalSpeed;
        this.initMaxHorizontalSpeed = initMaxHorizontalSpeed;
        this.downAcceleration = downAcceleration;
        this.maxFallingSpeed = maxFallingSpeed;
        this.scaleDivisor = scaleDivisor;
    }

    /**
     * Used for getting the minimum count of BananaParticles
     * @return inclusive int
     */
    public int getMinCount() {
        return minCount;
    }

    /**
     * Used for getting the maximum count of BananaParticles
     * @return inclusive int
     */
    public int getMaxCount() {
        return maxCount;
    }

    /**
     * Used for getting the minimum upward speed of each BananaParticle
     * @return inclusive int
     */
    public int getInitMinUpSpeed() {
        return initMinUpSpeed;
    }

    /**
     * Used for getting the maximum upward speed of each BananaParticle
     * @return inclusive int
     */
    public int getInitMaxUpSpeed() {
        return initMaxUpSpeed;
    }

    /**
     * Used for getting the minimum initial horizontal speed of each BananaParticle
     * @return inclusive int
     */
    public int getInitMinHorizontalSpeed() {
        return initMinHorizontalSpeed;
    }

    /**
     * Used for getting the maximum initial horizontal speed of each BananaParticle
     * @return inclusive int
     */
    public int getInitMaxHorizontalSpeed() {
        return initMaxHorizontalSpeed;
    }

    /**
     * Used for getting the down acceleration
     * @return returns the down acceleration
     */
    public int getDownAcceleration() {
        return downAcceleration;
    }

    /**
     * Used for getting the maximum falling speed (terminal velocity)
     * @return returns the maximum falling speed
     */
    public int getMaxFallingSpeed() {
        return maxFallingSpeed;
    }

    /**
     * Used for getting the amount by how much should the original calculated render scale should be divided with
     * @return returns the divisor as a float
     */
    public float getScaleDivisor() {
        return scaleDivisor;
    }
}
