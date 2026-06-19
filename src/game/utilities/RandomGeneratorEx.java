package game.utilities;

import java.util.Random;

/**
 * The class RandomGeneratorEx serves as an extension to the Random class
 */
public class RandomGeneratorEx extends Random {
    /**
     * Creates a new random number generator. This constructor sets
     * the seed of the random number generator to a value very likely
     * to be distinct from any other invocation of this constructor.
     */
    public RandomGeneratorEx() {
        super();
    }

    /**
     * Creates a new random number generator using a single {@code long} seed.
     * The seed is the initial value of the internal state of the pseudorandom
     * number generator which is maintained by method {@link #next}.
     *
     * @param seed the initial seed
     * @implSpec The invocation {@code new Random(seed)} is equivalent to:
     * <pre>{@code
     * Random rnd = new Random();
     * rnd.setSeed(seed);
     * }</pre>
     * @see #setSeed(long)
     */
    public RandomGeneratorEx(long seed) {
        super(seed);
    }

    /**
     * Used for getting a pseudorandomly chosen {@code int} value between the specified
     * origin (inclusive) and the specified bound (inclusive).
     * @param origin the least value that can be returned
     * @param bound the upper bound (exclusive) for the returned value
     * @return a pseudorandomly chosen {@code int} value between the
     * origin (inclusive) and the bound (exclusive)
     */
    public int nextIntIncl(int origin, int bound) {
        return super.nextInt(origin, bound + 1);
    }
}