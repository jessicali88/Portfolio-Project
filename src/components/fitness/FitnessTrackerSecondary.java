package components.fitness;

/**
 * Layered implementations of secondary methods for {@code FitnessTracker}.
 */
public abstract class FitnessTrackerSecondary implements FitnessTracker {
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
    @Override
    public int findPR() {
        assert this.length() != 0 : "Violation of: this is not empty";
        int max = 0;
        FitnessTracker copy = this.newInstance();
        while (this.length() != 0) {
            int tempEntry = this.removeFirst();
            if (tempEntry > max) {
                max = tempEntry;
            }
            copy.addWeight(tempEntry);
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
     * |this| >= 2
     *           </pre>
     *
     * @ensures this = #this and the returned value = last element - first
     *          element
     */
    @Override
    public int getCurrentProgress() {
        assert this.length() >= 2 : "Violation of: this is not empty";

        int progress = 0;
        FitnessTracker copy = this.newInstance();
        //get first entry
        int first = this.removeFirst();
        copy.addWeight(first);
        //remove until there's just one entry (the last one)
        while (this.length() != 1) {
            int temp = this.removeFirst();
            //enqueue to copy
            copy.addWeight(temp);
        }
        int last = this.removeFirst();
        progress = last - first;
        this.transferFrom(copy);
        return progress;
    }

    /**
     * Returns the standard deviation in weight recorded in {@code this}.
     *
     * @return standard deviation of the weights in {@code this}
     * @requires
     *
     *           <pre>
     * this is not empty
     *           </pre>
     *
     * @ensures this = #this
     */
    @Override
    public double getConsistencyScore() {
        assert this.length() != 0 : "Violation of: this is not empty";
        FitnessTracker copy = this.newInstance();
        int sum = 0;
        int sumSq = 0;
        int n = this.length();
        //find avg
        while (this.length() != 0) {
            int removedWeight = this.removeFirst();
            sum += removedWeight;
            copy.addWeight(removedWeight);
        }
        int avg = sum / n;
        //find sum of squares
        while (copy.length() != 0) {
            int copyRemovedWeight = copy.removeFirst();
            sumSq += Math.pow(copyRemovedWeight - avg, 2);
            this.addWeight(copyRemovedWeight);
        }
        return Math.round(Math.sqrt(sumSq / n));
    }

    /**
     * Returns the string representation of {@code this}.
     *
     * @return String that is a representation of {@code this}
     * @requires
     *
     *           <pre>
     * this is not null
     *           </pre>
     *
     * @ensures this = toString(#this)
     */
    @Override
    public String toString() {
        assert this != null : "Violation of: this is not null";
        String weightString = new String();
        if (this.length() > 0) {
            FitnessTracker copy = this.newInstance();
            while (this.length() != 0) {
                int removedWeight = this.removeFirst();
                weightString = weightString.concat(removedWeight + ", ");
                copy.addWeight(removedWeight);
            }
            this.transferFrom(copy);
            //don't include extra comma at end
            weightString = weightString.substring(0, weightString.length() - 2);
        }

        return weightString;
    }

    /**
     * Returns whether some FitnessTracker object is equal to {@code this}.
     *
     * @return true if the object is equal to {@code this}, false otherwise
     * @param obj
     *            FitnessTracker object with which to compare
     * @requires
     *
     *           <pre>
     * this is not null
     *           </pre>
     *
     * @ensures obj is equal to this
     */
    @Override
    public boolean equals(Object obj) {
        assert this != null : "Violation of: this is not null";
        FitnessTracker x = (FitnessTracker) obj;
        FitnessTracker thisCopy = this.newInstance();
        FitnessTracker xCopy = this.newInstance();
        boolean isEqual = true;
        while (this.length() == x.length() && this.length() != 0) {
            int thisRemoved = this.removeFirst();
            int xRemoved = x.removeFirst();
            if (thisRemoved != xRemoved) {
                isEqual = false;
            }
            thisCopy.addWeight(thisRemoved);
            xCopy.addWeight(xRemoved);
        }
        if (this.length() != x.length()) {
            isEqual = false;
        }
        this.transferFrom(thisCopy);
        x.transferFrom(xCopy);
        return isEqual;
    }
}
