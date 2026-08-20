package ga.constraints;

import config.SchedulingConfig;
import ga.EvaluationContext;
import model.Faculty;
import model.Placement;
import util.PlacementIndex;

import java.util.List;
import java.util.Map;

public class FacultyOverlapConstraint implements Constraint {

    @Override
    public int evaluate(EvaluationContext context) {
        int violations = 0;
        PlacementIndex index = context.getIndex();
        Map<Faculty, List<Placement>> byFaculty = index.getByFaculty();

        for (Map.Entry<Faculty, List<Placement>> entry : byFaculty.entrySet()) {
            List<Placement> placements = entry.getValue();
            int[][] facultyOccupancy = new int[SchedulingConfig.WORKING_DAYS][SchedulingConfig.PERIODS_PER_DAY];

            for (Placement placement : placements) {
                int day = placement.getSlot().getDay().ordinal();
                int start = placement.getSlot().getPeriod();
                int duration = placement.getSession().getDuration();

                for (int i = 0; i < duration; i++) {
                    if (start + i < SchedulingConfig.PERIODS_PER_DAY) {
                        facultyOccupancy[day][start + i]++;
                    }
                }
            }

            for (int d = 0; d < SchedulingConfig.WORKING_DAYS; d++) {
                for (int p = 0; p < SchedulingConfig.PERIODS_PER_DAY; p++) {
                    if (facultyOccupancy[d][p] > 1) {
                        violations += (facultyOccupancy[d][p] - 1);
                    }
                }
            }
        }

        return violations;
    }
}
