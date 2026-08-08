package ga.constraints;

import ga.Chromosome;

public interface Constraint {

    int evaluate(Chromosome chromosome);

}