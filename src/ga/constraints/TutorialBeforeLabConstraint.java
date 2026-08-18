package ga.constraints;

import ga.EvaluationContext;
import model.Placement;
import model.SessionType;
import model.Subject;
import util.PlacementIndex;

import java.util.List;

public class TutorialBeforeLabConstraint implements Constraint {

    @Override
    public int evaluate(EvaluationContext context) {
        int violations = 0;
        PlacementIndex index = context.getIndex();

        for (Subject subject : index.getBySubject().keySet()) {
            if (!subject.hasPractical()) {
                continue;
            }

            List<Placement> placements = index.getBySubject(subject);
            Placement tutPlacement = null;
            Placement labPlacement = null;

            for (Placement p : placements) {
                if (p.getSession().getSessionType() == SessionType.TUTORIAL) {
                    tutPlacement = p;
                } else if (p.getSession().getSessionType() == SessionType.LAB) {
                    labPlacement = p;
                }
            }

            if (tutPlacement != null && labPlacement != null) {
                int tutDay = tutPlacement.getSlot().getDay().ordinal();
                int labDay = labPlacement.getSlot().getDay().ordinal();

                int expectedTutDay = (labDay + 4) % 5;

                if (tutDay != expectedTutDay) {
                    violations++;
                }
            }
        }

        return violations;
    }
}
