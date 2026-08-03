package ga;

import config.SchedulingConfig;
import model.Placement;
import util.TimetableGridBuilder;

public class FitnessEvaluator {

    public int evaluate(Chromosome chromosome) {

        int penalty = 0;
        penalty += consecutiveLecturePenalty(chromosome);
        chromosome.setFitness(penalty);
        return penalty;
    }

    // PENALTY 1
    private int consecutiveLecturePenalty(Chromosome chromosome) {

        int penalty = 0;
        Placement[][] grid = TimetableGridBuilder.build(chromosome);

        for (int day = 0; day < SchedulingConfig.WORKING_DAYS; day++) {
            for (int period = 0; period < SchedulingConfig.PERIODS_PER_DAY - 1; period++) {
                if (grid[day][period] == null || grid[day][period + 1] == null)
                    continue;
                if (grid[day][period].equals(grid[day][period + 1])) {
                    penalty += 10;
                }
            }
        }
        return penalty;
    }

}