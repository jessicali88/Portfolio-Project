import components.fitness.FitnessTracker;
import components.fitness.FitnessTracker1;

public class LegDayDemo {
    public static void main(String[] args) {
        FitnessTracker squats = new FitnessTracker1();
        squats.addWeight(115);
        squats.addWeight(125);
        squats.addWeight(135);

        FitnessTracker rdls = new FitnessTracker1();
        rdls.addWeight(45);
        rdls.addWeight(55);
        rdls.removeFirst();

        int numberSquats = squats.length();
        double squatSD = squats.getConsistencyScore();

        System.out.println("Number of Squat Entries: " + numberSquats);
        System.out.println("Squat Consistency Score: " + squatSD);
        System.out.println("Squat Entries: " + squats.toString());
        System.out.println("RDL Entries: " + rdls.toString());

    }

}
