package components.fitness;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * JUnit test fixture for {@code FitnessTracker}'s constructor, standard,
 * kernel, common, and enhanced methods.
 *
 * @author Jessica Li
 *
 */

public abstract class FitnessTrackerTest {
    /**
     * Invokes the appropriate {@code FitnessTracker} constructor for the
     * implementation under test and returns the result.
     *
     * @return the new FitnessTracker
     * @ensures constructorTest = {}
     */
    protected abstract FitnessTracker constructorTest();

    /**
     * Creates and returns a {@code FitnessTracker} of the implementation under
     * test type with the given entries.
     *
     * @param args
     *            the entries for the FitnessTracker
     * @return the constructed FitnessTracker
     * @requires [every entry is a positive integer]
     * @ensures createFromArgsTest = [entries in args]
     */
    private FitnessTracker createFromArgsTest(int... args) {
        FitnessTracker exercise = this.constructorTest();
        for (int weights : args) {
            exercise.addWeight(weights);
        }
        return exercise;
    }

    /**
     * Test no argument constructor.
     */
    @Test
    public final void testConstructor() {
        FitnessTracker m = this.constructorTest();
        FitnessTracker mExpected = new FitnessTracker1();
        assertEquals(mExpected, m);
    }

    /* Standard Tests */
    /**
     * Test newInstance.
     */
    @Test
    public final void testNewInstance() {
        FitnessTracker mExpected = this.constructorTest();
        FitnessTracker m = mExpected.newInstance();
        assertEquals(mExpected, m);
    }

    /**
     * Test clear on an empty object.
     */
    @Test
    public final void testClearEmpty() {
        FitnessTracker m = this.constructorTest();
        m.clear();
        FitnessTracker mExpected = m.newInstance();
        assertEquals(mExpected, m);
    }

    /**
     * Test clear on a non empty object.
     */
    @Test
    public final void testClearNonEmpty() {
        FitnessTracker m = this.createFromArgsTest(1, 2, 3, 4);
        m.clear();
        FitnessTracker mExpected = m.newInstance();
        assertEquals(mExpected, m);
    }

    /**
     * Test clear on a nonempty object again.
     */
    @Test
    public final void testClearNonEmpty2() {
        FitnessTracker m = this.createFromArgsTest(10, 4, 5);
        m.clear();
        FitnessTracker mExpected = m.newInstance();
        assertEquals(mExpected, m);
    }

    /**
     * Test transferFrom on nonempty.
     */
    @Test
    public final void testTransferFrom() {
        FitnessTracker m = this.createFromArgsTest(1, 2, 3, 4);
        FitnessTracker nToTransfer = this.createFromArgsTest(2);
        FitnessTracker mExpected = this.createFromArgsTest(2);
        FitnessTracker nExpected = m.newInstance();
        m.transferFrom(nToTransfer);
        assertEquals(mExpected, m);
        assertEquals(nExpected, nToTransfer);
    }

    /**
     * Test transferFrom on empty.
     */
    @Test
    public final void testTransferFromEmpty() {
        FitnessTracker m = this.createFromArgsTest();
        FitnessTracker mExpected = this.createFromArgsTest();
        m.transferFrom(mExpected);
        assertEquals(mExpected, m);
    }

    /**
     * Test transferFrom on nonempty to empty.
     */
    @Test
    public final void testTransferFromToEmpty() {
        FitnessTracker m = this.createFromArgsTest(1, 3, 4);
        FitnessTracker nToTransfer = this.createFromArgsTest();
        FitnessTracker mExpected = this.createFromArgsTest();
        FitnessTracker nExpected = m.newInstance();
        m.transferFrom(mExpected);
        assertEquals(mExpected, m);
        assertEquals(nExpected, nToTransfer);
    }

    /* Kernel Tests */
    /**
     * Test addWeight on empty.
     */
    @Test
    public final void testAddWeightOnEmpty() {
        FitnessTracker m = this.constructorTest();
        FitnessTracker mExpected = this.createFromArgsTest(1);
        m.addWeight(1);
        assertEquals(mExpected, m);
    }

    /**
     * Test addWeight on nonempty.
     */
    @Test
    public final void testAddWeightNonEmpty() {
        FitnessTracker m = this.createFromArgsTest(1, 2, 3);
        FitnessTracker mExpected = this.createFromArgsTest(1, 2, 3, 4);
        m.addWeight(4);
        assertEquals(mExpected, m);
    }

    /**
     * Test addWeight adding two times.
     */
    @Test
    public final void testAddWeightNonEmpty2() {
        FitnessTracker m = this.createFromArgsTest(1, 2, 3);
        FitnessTracker mExpected = this.createFromArgsTest(1, 2, 3, 4, 2);
        m.addWeight(4);
        m.addWeight(2);
        assertEquals(mExpected, m);
    }

    /**
     * Test removeFirst on nonempty.
     */
    @Test
    public final void testRemoveFirstNonEmpty() {
        FitnessTracker m = this.createFromArgsTest(1, 2, 3, 4);
        FitnessTracker mExpected = this.createFromArgsTest(2, 3, 4);
        int removedEntry = m.removeFirst();
        assertEquals(1, removedEntry);
        assertEquals(mExpected, m);
    }

    /**
     * Test removeFirst to become empty.
     */
    @Test
    public final void testRemoveFirstToEmpty() {
        FitnessTracker m = this.createFromArgsTest(2);
        FitnessTracker mExpected = this.createFromArgsTest();
        int removedEntry = m.removeFirst();
        assertEquals(2, removedEntry);
        assertEquals(mExpected, m);
    }

    /**
     * Test removeFirst twice.
     */
    @Test
    public final void testRemoveFirstTwice() {
        FitnessTracker m = this.createFromArgsTest(1, 2, 5, 6);
        FitnessTracker mExpected = this.createFromArgsTest(5, 6);
        int removedEntry = m.removeFirst();
        int removedEntry2 = m.removeFirst();
        assertEquals(1, removedEntry);
        assertEquals(2, removedEntry2);
        assertEquals(mExpected, m);
    }

    /**
     * Test length on empty.
     */
    @Test
    public final void testLengthEmpty() {
        FitnessTracker m = this.createFromArgsTest();
        int length = m.length();
        assertEquals(0, length);
    }

    /**
     * Test length on nonempty.
     */
    @Test
    public final void testLengthNonEmpty() {
        FitnessTracker m = this.createFromArgsTest(1, 2, 3);
        int length = m.length();
        assertEquals(3, length);
    }

    /**
     * Test length on longer nonempty.
     */
    @Test
    public final void testLengthNonEmpty2() {
        FitnessTracker m = this.createFromArgsTest(3, 2, 4, 6, 3, 8);
        int length = m.length();
        assertEquals(6, length);
    }

    /* Secondary Tests */
    /**
     * Test findPR when PR is at the end.
     */
    @Test
    public final void testFindPR() {
        FitnessTracker m = this.createFromArgsTest(2, 3, 6, 7);
        int PR = m.findPR();
        assertEquals(7, PR);
    }

    /**
     * Test findPR when PR is at the front.
     */
    @Test
    public final void testFindPR2() {
        FitnessTracker m = this.createFromArgsTest(10, 4, 3, 5);
        int PR = m.findPR();
        assertEquals(10, PR);
    }

    /**
     * Test findPR when there are multiple.
     */
    @Test
    public final void testFindPR3() {
        FitnessTracker m = this.createFromArgsTest(10, 13, 13, 5);
        int PR = m.findPR();
        assertEquals(13, PR);
    }

    /**
     * Test getCurrentProgress to return negative value.
     */
    @Test
    public final void testGetCurrentProgressNegative() {
        FitnessTracker m = this.createFromArgsTest(10, 4, 3, 5);
        int progress = m.getCurrentProgress();
        assertEquals(-5, progress);
    }

    /**
     * Test getCurrentProgress to return positive value.
     */
    @Test
    public final void testGetCurrentProgressPositive() {
        FitnessTracker m = this.createFromArgsTest(1, 5);
        int progress = m.getCurrentProgress();
        assertEquals(4, progress);
    }

    /**
     * Test getCurrentProgress to return 0.
     */
    @Test
    public final void testGetCurrentProgressZero() {
        FitnessTracker m = this.createFromArgsTest(2, 4, 3, 2);
        int progress = m.getCurrentProgress();
        assertEquals(0, progress);
    }

    /**
     * Test getConsistencyScore to return a value with delta.
     */
    @Test
    public final void testGetConsistencyScore() {
        FitnessTracker m = this.createFromArgsTest(2, 4, 3, 100);
        double sd = m.getConsistencyScore();
        assertEquals(42, sd, 0.1);
    }

    /**
     * Test getConsistencyScore when all values are the same.
     */
    @Test
    public final void testGetConsistencyScoreEqual() {
        FitnessTracker m = this.createFromArgsTest(2, 2, 2, 2);
        double sd = m.getConsistencyScore();
        assertEquals(0, sd, 0);
    }

    /**
     * Test getConsistencyScore for 1 element.
     */
    @Test
    public final void testGetConsistencyScoreOne() {
        FitnessTracker m = this.createFromArgsTest(1);
        double sd = m.getConsistencyScore();
        assertEquals(0, sd, 0);
    }

    /* Common Methods Tests */
    /**
     * Test toString.
     */
    @Test
    public final void testToString() {
        FitnessTracker m = this.createFromArgsTest(1, 2, 3, 4);
        String mExpected = "1, 2, 3, 4";
        assertEquals(mExpected, m.toString());
    }

    /**
     * Test toString for 1 element.
     */
    @Test
    public final void testToStringOne() {
        FitnessTracker m = this.createFromArgsTest(1);
        String mExpected = "1";
        assertEquals(mExpected, m.toString());
    }

    /**
     * Test toString on empty.
     */
    @Test
    public final void testToStringEmpty() {
        FitnessTracker m = this.createFromArgsTest();
        String mExpected = "";
        assertEquals(mExpected, m.toString());
    }

    /**
     * Test equals when true.
     */
    @Test
    public final void testEqualsTrue() {
        FitnessTracker m = this.createFromArgsTest(1, 2, 3, 4);
        FitnessTracker mSame = this.createFromArgsTest(1, 2, 3, 4);
        boolean isEqual = m.equals(mSame);
        assertEquals(true, isEqual);
    }

    /**
     * Test equals when false.
     */
    @Test
    public final void testEqualsFalse() {
        FitnessTracker m = this.createFromArgsTest(1, 2, 3, 5);
        FitnessTracker mSame = this.createFromArgsTest(1, 2, 3, 4);
        boolean isEqual = m.equals(mSame);
        assertEquals(false, isEqual);
    }

    /**
     * Test equals when false due to different lengths.
     */
    @Test
    public final void testEqualsFalse2() {
        FitnessTracker m = this.createFromArgsTest(1, 2, 3, 4, 5);
        FitnessTracker mSame = this.createFromArgsTest(1, 2, 3, 4);
        boolean isEqual = m.equals(mSame);
        assertEquals(false, isEqual);
    }
}
