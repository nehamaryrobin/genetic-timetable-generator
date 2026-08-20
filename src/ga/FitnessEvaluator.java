package ga;

import ga.constraints.ConsecutiveLectureConstraint;
import ga.constraints.FacultyMaxWorkloadConstraint;
import ga.constraints.GapConstraint;
import ga.constraints.MaxDailyLectureConstraint;
import ga.constraints.OverlapConstraint;
import ga.constraints.SpreadConstraint;
import ga.constraints.TutorialBeforeLabConstraint;
import ga.constraints.WeightedConstraint;

import java.util.ArrayList;
import java.util.List;

public class FitnessEvaluator {

    private final List<WeightedConstraint> constraints = new ArrayList<>();

    public FitnessEvaluator() {

        constraints.add(new WeightedConstraint(new OverlapConstraint(), 100));
        constraints.add(new WeightedConstraint(new ConsecutiveLectureConstraint(), 10));
        constraints.add(new WeightedConstraint(new SpreadConstraint(), 10));
        constraints.add(new WeightedConstraint(new MaxDailyLectureConstraint(), 10));
        constraints.add(new WeightedConstraint(new GapConstraint(), 5));
        constraints.add(new WeightedConstraint(new TutorialBeforeLabConstraint(), 10));
        constraints.add(new WeightedConstraint(new FacultyMaxWorkloadConstraint(), 10));
    }

    public int evaluate(Chromosome chromosome) {
        EvaluationContext context = new EvaluationContext(chromosome);
        int fitness = 0;
        for (WeightedConstraint constraint : constraints) {
            fitness += constraint.evaluate(context);
        }

        chromosome.setFitness(fitness);
        return fitness;
    }
}