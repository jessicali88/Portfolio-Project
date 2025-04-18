package components.fitness;

/**
 * Customized JUnit test fixture for {@code Set3a}.
 */
public class FitnessTracker1Test extends FitnessTrackerTest {
    @Override
    protected final FitnessTracker constructorTest() {
        return new FitnessTracker1();
    }
}
