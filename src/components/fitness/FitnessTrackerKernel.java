// package components.FitnessTracker;

import components.standard.Standard;

/**
 * Last-in-first-out (LIFO) fitness tracker kernel component with primary
 * methods. (Note: by package-wide convention, all references are non-null.)
 *
 * @mathmodel type StackKernel is modeled by string of Integers
 * @initially {@code
 * ():
 *  ensures
 *   this = <>
 * }
 */
public interface FitnessTrackerKernel extends Standard<FitnessTrackerProof> {
    /**
     * Adds weight {@code x} to the end of {@code this}.
     *
     * @param x
     *            the weight entry to be added
     * @aliases reference {@code x}
     * @updates this
     * @ensures {@code this = <x> * #this}
     */
    void addWeight(Integer x);

    /**
     * Removes last weight {@code x} from the end of {@code this}.
     *
     * @return the entry removed
     * @updates this
     * @requires {@code this /= <>}
     * @ensures {@code #this = <removeLast> * this}
     */
    Integer removeLast();

    /**
     * Reports length of {@code this}.
     *
     * @return the length of {@code this}
     * @ensures length = |this|
     */
    int length();
}
