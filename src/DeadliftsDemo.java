import components.fitness.FitnessTracker;
import components.fitness.FitnessTracker1;

public class DeadliftsDemo {
    public static void main(String[] args) {
        FitnessTracker deadlifts = new FitnessTracker1();
        deadlifts.addWeight(100);
        deadlifts.addWeight(125);
        deadlifts.addWeight(145);
        deadlifts.addWeight(150);
        int deadliftPR = deadlifts.findPR();
        // find the current progress for deadlifts (125 - 105)
        int deadliftProgress = deadlifts.getCurrentProgress();
        System.out.println("Deadlift PR: " + deadliftPR + " lbs");
        System.out.println("Deadlift progress: " + deadliftProgress + " lbs");

    }
}
