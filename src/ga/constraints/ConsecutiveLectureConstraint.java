package ga.constraints;

import config.SchedulingConfig;

import ga.EvaluationContext;
import model.Placement;

public class ConsecutiveLectureConstraint implements Constraint {

    @Override
    public int evaluate(EvaluationContext context) {

        int penalty = 0;
        Placement[][] grid = context.getGrid();

        for (int day = 0; day < SchedulingConfig.WORKING_DAYS; day++) {
            for (int period = 0; period < SchedulingConfig.PERIODS_PER_DAY - 1; period++) {

                Placement current = grid[day][period];
                Placement next = grid[day][period + 1];

                if (current == null || next == null)
                    continue;

                if (current.getSession().getSubject().equals(next.getSession().getSubject())) {
                    penalty += 10;
                }
            }
        }
        return penalty;
    }
}