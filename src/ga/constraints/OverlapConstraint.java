package ga.constraints;

import config.SchedulingConfig;
import ga.EvaluationContext;
import model.Placement;

public class OverlapConstraint implements Constraint {

    @Override
    public int evaluate(EvaluationContext context) {
        int[][] occupancy = new int[SchedulingConfig.WORKING_DAYS][SchedulingConfig.PERIODS_PER_DAY];

        for (Placement placement : context.getChromosome().getPlacements()) {
            int day = placement.getSlot().getDay().ordinal();
            int start = placement.getSlot().getPeriod();
            int duration = placement.getSession().getDuration();

            for (int i = 0; i < duration; i++) {
                if (start + i < SchedulingConfig.PERIODS_PER_DAY) {
                    occupancy[day][start + i]++;
                }
            }
        }

        int violations = 0;
        for (int d = 0; d < SchedulingConfig.WORKING_DAYS; d++) {
            for (int p = 0; p < SchedulingConfig.PERIODS_PER_DAY; p++) {
                if (occupancy[d][p] > 1) {
                    violations += (occupancy[d][p] - 1);
                }
            }
        }

        return violations;
    }
}
