package ga.constraints;

import ga.Chromosome;
import ga.EvaluationContext;
import model.Day;
import model.Placement;
import model.Subject;
import util.PlacementIndex;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SpreadConstraint implements Constraint {

    @Override
    public int evaluate(EvaluationContext context) {

        int penalty = 0;
        PlacementIndex index = context.getIndex();

        for (Subject subject : index.getBySubject().keySet()) {

            if (subject.isLab())
                continue;

            List<Placement> placements = index.getBySubject(subject);
            Set<Day> days = new HashSet<>();

            for (Placement placement : placements) {
                days.add(placement.getSlot().getDay());

            }
            int requiredDays = subject.getCredits();
            int actualDays = days.size();

            if (actualDays < requiredDays) {
                penalty += (requiredDays - actualDays) * 10;
            }
        }

        return penalty;
    }
}