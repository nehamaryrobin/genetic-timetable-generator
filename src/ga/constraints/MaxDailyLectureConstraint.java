package ga.constraints;

import ga.EvaluationContext;
import model.Day;
import model.Placement;
import model.Subject;
import util.PlacementIndex;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MaxDailyLectureConstraint implements Constraint {

    private final int maxDailyLimit;

    public MaxDailyLectureConstraint() {
        this(2);
    }

    public MaxDailyLectureConstraint(int maxDailyLimit) {
        this.maxDailyLimit = maxDailyLimit;
    }

    @Override
    public int evaluate(EvaluationContext context) {
        int penalty = 0;

        PlacementIndex index = context.getIndex();
        for (Day day : Day.values()) {

            List<Placement> placements = index.getByDay(day);
            Map<Subject, Integer> frequency = new HashMap<>();

            for (Placement placement : placements) {
                Subject subject = placement.getSession().getSubject();

                if (subject.isLab())
                    continue;
                frequency.put(subject, frequency.getOrDefault(subject, 0) + 1);
            }

            for (int count : frequency.values()) {
                if (count > maxDailyLimit) {
                    penalty += (count - maxDailyLimit) * 10;
                }
            }
        }
        return penalty;
    }
}
