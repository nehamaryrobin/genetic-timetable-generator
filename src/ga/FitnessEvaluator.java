package ga;

import ga.constraints.ConsecutiveLectureConstraint;
import ga.constraints.GapConstraint;
import ga.constraints.MaxDailyLectureConstraint;
import ga.constraints.SpreadConstraint;
import ga.constraints.WeightedConstraint;

import java.util.ArrayList;
import java.util.List;

public class FitnessEvaluator {

    private final List<WeightedConstraint> constraints = new ArrayList<>();

    public FitnessEvaluator() {

        constraints.add(new WeightedConstraint(new ConsecutiveLectureConstraint(), 10));
        constraints.add(new WeightedConstraint(new SpreadConstraint(), 10));
        constraints.add(new WeightedConstraint(new MaxDailyLectureConstraint(), 10));
        constraints.add(new WeightedConstraint(new GapConstraint(), 5));
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