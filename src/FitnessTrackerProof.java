import components.queue.Queue;
import components.queue.Queue1L;

/**
 * Proof of concept for a fitness tracker component that tracks the weight
 * progression of weight lifting exercises.
 *
 * @author Jessica Li
 *
 */
public class FitnessTracker {

    private Queue<Integer> rep;

    public FitnessTracker() {
        this.rep = new Queue1L<>();
    }

    public int length() {
        return this.rep.length();
    }

    public void addWeight(int weight) {
        this.rep.enqueue(weight);
    }

    public int removeLast() {
        this.rep.flip();
        int last = this.rep.dequeue();
        this.rep.flip();
        return last;
    }

    public FitnessTracker newInstance() {
        return new FitnessTracker();
    }

    public void transferFrom(FitnessTracker other) {
        this.rep = other.rep;
        other = new FitnessTracker();
    }

    /**
     * Returns the PR maximum weight in {@code this}.
     *
     * @return integer that is the maximum value in the {@code this}
     * @requires
     *
     *           <pre>
     * this is not empty
     *           </pre>
     *
     * @ensures this = #this
     */
    public int findPR() {
        int max = 0;
        FitnessTracker copy = this.newInstance();
        //dequeue from this
        while (this.length() != 0) {
            int temp = this.removeLast();
            if (temp > max) {
                max = temp;
            }
            //enqueue to copy
            copy.addWeight(temp);
        }
        this.transferFrom(copy);
        return max;
    }

    /**
     * Returns the current weight progress in {@code this}.
     *
     * @return integer that is the difference between the last and first
     *         elements in {@code this}
     * @requires
     *
     *           <pre>
     * this is not empty
     *           </pre>
     *
     * @ensures this = #this and the returned value = last element - first
     *          element
     */
    public int getCurrentProgress() {
        int progress = 0;
        FitnessTracker copy = this.newInstance();
        //dequeue from this
        int last = this.removeLast();
        //remove until there's just one entry
        while (this.length() != 1) {
            int temp = this.removeLast();
            //enqueue to copy
            copy.addWeight(temp);
        }
        int first = this.removeLast();
        copy.addWeight(first);
        progress = last - first;
        this.transferFrom(copy);
        return progress;
    }

    /**
     * Returns the first weight recorded in {@code this}.
     *
     * @return integer that is the first value in the {@code this}
     * @requires
     *
     *           <pre>
     * this is not empty
     *           </pre>
     *
     * @ensures this = #this
     */
    public int findFirst() {
        FitnessTracker copy = this.newInstance();
        //remove until there's just one entry
        while (this.length() != 1) {
            int temp = this.removeLast();
            //enqueue to copy
            copy.addWeight(temp);
        }
        int first = this.removeLast();
        copy.addWeight(first);
        this.transferFrom(copy);
        return first;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {

        // create deadlift object
        FitnessTracker deadlift = new FitnessTracker();

        // add weights to the deadlift collection
        deadlift.addWeight(105);
        deadlift.addWeight(115);
        deadlift.addWeight(135);
        deadlift.addWeight(125);
        // find the pr for deadlifts (135)
        int deadliftPR = deadlift.findPR();
        // find the first entry in the deadlift collection (105)
        int deadliftFirst = deadlift.findFirst();
        // find the current progress for deadlifts (125 - 105)
        int deadliftProgress = deadlift.getCurrentProgress();
        System.out.println(deadliftFirst + ", " + deadliftPR);

    }
}
