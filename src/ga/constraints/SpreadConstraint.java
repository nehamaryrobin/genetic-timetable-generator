package ga.constraints;

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

        int violations = 0;
        PlacementIndex index = context.getIndex();

        for (Subject subject : index.getBySubject().keySet()) {

            if (!subject.hasTheory())
                continue;

            List<Placement> placements = index.getBySubject(subject);
            Set<Day> days = new HashSet<>();

            for (Placement placement : placements) {
                if (placement.getSession().getSessionType() == model.SessionType.LECTURE) {
                    days.add(placement.getSlot().getDay());
                }
            }

            int requiredDays = subject.getTheoryCredits();
            int actualDays = days.size();

            if (actualDays < requiredDays) {
                violations += requiredDays - actualDays;
            }
        }
        return violations;
    }
}