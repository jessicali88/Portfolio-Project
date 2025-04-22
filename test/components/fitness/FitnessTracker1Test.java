package components.fitness;

/**
 * Customized JUnit test fixture for {@code FitnessTracker1}.
 */
public class FitnessTracker1Test extends FitnessTrackerTest {
    @Override
    protected final FitnessTracker constructorTest() {
        return new FitnessTracker1();
    }
}
