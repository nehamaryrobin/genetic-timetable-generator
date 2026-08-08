package ga;

import ga.constraints.Constraint;
import ga.constraints.ConsecutiveLectureConstraint;

import java.util.ArrayList;
import java.util.List;

public class FitnessEvaluator {

    private final List<Constraint> constraints = new ArrayList<>();

    public FitnessEvaluator() {
        constraints.add(new ConsecutiveLectureConstraint());
    }

    public int evaluate(Chromosome chromosome) {
        int penalty = 0;
        for (Constraint constraint : constraints) {
            penalty += constraint.evaluate(chromosome);
        }

        chromosome.setFitness(penalty);
        return penalty;
    }
}