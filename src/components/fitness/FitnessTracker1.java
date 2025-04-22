package components.fitness;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * {@code FitnessTracker} represented as a {@code Queue} of elements with
 * implementations of primary methods.
 *
 * @convention <pre>
 * [$this.rep is a valid representation of FitnessTracker
 * and entires in $this.rep are positive integers]
 * </pre>
 * @correspondence <pre>
 * this = [$this.rep]
 * </pre>
 *
 * @author Jessica Li
 *
 */
public class FitnessTracker1 extends FitnessTrackerSecondary {
    /*
     * Private members --------------------------------------------------------
     */

    /**
     * Elements included in {@code this}.
     */
    private Queue<Integer> rep;

    /**
     * Creator of initial representation.
     */
    private void createNewRep() {

        this.rep = new ArrayDeque<>();
    }

    /**
     * No-argument constructor.
     */
    public FitnessTracker1() {

        this.createNewRep();
    }

    /*
     * Standard methods
     *
     */
    @Override
    public final FitnessTracker newInstance() {
        try {
            return this.getClass().getConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            throw new AssertionError(
                    "Cannot construct object of type " + this.getClass());
        }
    }

    @Override
    public final void clear() {
        this.createNewRep();
    }

    @Override
    public final void transferFrom(FitnessTracker source) {
        assert source != null : "Violation of: source is not null";
        assert source != this : "Violation of: source is not this";
        assert source instanceof FitnessTracker1 : ""
                + "Violation of: source is of dynamic type FitnessTracker1";
        /*
         * This cast cannot fail since the assert above would have stopped
         * execution in that case.
         */
        FitnessTracker1 localSource = (FitnessTracker1) source;
        this.rep = localSource.rep;
        localSource.createNewRep();
    }

    /*
     * Kernel methods
     *
     */
    @Override
    public final void addWeight(int x) {
        this.rep.add(x);
    }

    @Override
    public final int removeFirst() {
        return this.rep.remove();
    }

    @Override
    public final int length() {
        return this.rep.size();
    }
}
