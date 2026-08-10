package ga;

import ga.constraints.*;
import java.util.ArrayList;
import java.util.List;

// FitnessEvaluator doesn't care how a constraint works.

public class FitnessEvaluator {

    private final List<Constraint> constraints = new ArrayList<>();

    public FitnessEvaluator() {
        constraints.add(new ConsecutiveLectureConstraint());
        constraints.add(new MaxDailyLectureConstraint());
        constraints.add(new SpreadConstraint());
    }

    public int evaluate(Chromosome chromosome) {

        EvaluationContext context = new EvaluationContext(chromosome);
        int penalty = 0;

        for (Constraint constraint : constraints) {
            penalty += constraint.evaluate(context);
        }
        chromosome.setFitness(penalty);
        return penalty;
    }
}