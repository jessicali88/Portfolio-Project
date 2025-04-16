// package components.FitnessTracker;

/**
 * {@code FitnessTrackerKernel} enhanced with secondary methods.
 *
 */
public interface FitnessTracker extends FitnessTrackerKernel {

    /**
     * Returns the PR maximum weight in {@code this}.
     *
     * @return integer that is the maximum value in the {@code this}
     * @requires <pre>
     * this is not empty
     *</pre>
     *
     * @ensures this = #this and @code <findPR> is the max of this
     */
    int findPR();

    /**
     * Returns the current weight progress in {@code this}.
     *
     * @return integer that is the difference between the last and first
     *         elements in {@code this}
     * @requires <pre>
     * this is not empty
     *</pre>
     *
     * @ensures this = #this and the returned value = last element - first
     *          element
     */
    int getCurrentProgress();

    /**
     * Returns the first weight recorded in {@code this}.
     *
     * @return integer that is the first value in the {@code this}
     * @requires <pre>
     * this is not empty
     * </pre>
     *
     * @ensures this = #this and @code findFirst is a prefix of this
     */
    int findFirst();
}
