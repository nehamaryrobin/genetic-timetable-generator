package ga.constraints;

import config.SchedulingConfig;
import ga.Chromosome;
import ga.EvaluationContext;
import model.Placement;
import model.Subject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MaxDailyLectureConstraint implements Constraint {

    private final int maxDailyLimit;

    // default constructor
    public MaxDailyLectureConstraint() {
        this(2);
    }

    public MaxDailyLectureConstraint(int maxDailyLimit) {
        this.maxDailyLimit = maxDailyLimit;
    }

    @Override
    public int evaluate(EvaluationContext context) {
        int penalty = 0;

        Placement[][] grid = context.getGrid();

        for (int day = 0; day < SchedulingConfig.WORKING_DAYS; day++) {
            Map<Subject, Integer> frequency = new HashMap<>();

            for (int period = 0; period < SchedulingConfig.PERIODS_PER_DAY; period++) {
                Placement placement = grid[day][period];

                if (placement != null) {
                    Subject subject = placement.getSession().getSubject();
                    frequency.put(subject, frequency.getOrDefault(subject, 0) + 1);
                }
            }
            // checks for one working day each loop
            for (Map.Entry<Subject, Integer> entry : frequency.entrySet()) {
                int count = entry.getValue();
                if (count > maxDailyLimit) {
                    penalty += (count - maxDailyLimit) * 10;
                }
            }
        }

        return penalty;
    }
}
