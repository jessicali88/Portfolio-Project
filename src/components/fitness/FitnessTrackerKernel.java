package components.fitness;

import components.standard.Standard;

/**
 * First-in-first-out (FIFO) fitness tracker kernel component with primary
 * methods. (Note: by package-wide convention, all references are non-null.)
 *
 * @mathmodel type FitnessTracker is modeled by string of Integers
 * @initially {@code
 * ():
 *  ensures
 *   this = <>
 * }
 */
public interface FitnessTrackerKernel extends Standard<FitnessTracker> {
    /**
     * Adds weight {@code x} to the end of {@code this}.
     *
     * @param x
     *            the weight entry to be added
     * @aliases reference {@code x}
     * @updates this
     * @ensures {@code this = <x> * #this}
     */
    void addWeight(int x);

    /**
     * Removes and returns first weight {@code x} from the front of
     * {@code this}.
     *
     * @return the entry removed
     * @updates this
     * @requires {@code this /= <>}
     * @ensures {@code #this = <removeFirst> * this}
     */
    int removeFirst();

    /**
     * Reports length of {@code this}.
     *
     * @return the length of {@code this}
     * @ensures length = |this|
     */
    int length();
}
