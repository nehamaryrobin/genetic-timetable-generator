package ga.constraints;

import ga.EvaluationContext;

public class WeightedConstraint {

    private final Constraint constraint;
    private final int weight;

    public WeightedConstraint(Constraint constraint, int weight) {
        this.constraint = constraint;
        this.weight = weight;
    }

    public int evaluate(EvaluationContext context) {
        return constraint.evaluate(context) * weight;
    }

    public Constraint getConstraint() {
        return constraint;
    }

    public int getWeight() {
        return weight;
    }
}