package ga.constraints;

import ga.EvaluationContext;

// Constraint doesn't care how the index was built.

public interface Constraint {
    int evaluate(EvaluationContext context);
}