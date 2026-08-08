package ga.constraints;

import ga.Chromosome;
import model.Placement;
import util.TimetableGridBuilder;

public class ConsecutiveLectureConstraint implements Constraint {

    @Override
    public int evaluate(Chromosome chromosome) {

        int penalty = 0;

        Placement[][] grid = TimetableGridBuilder.build(chromosome);

        for (int day = 0; day < 5; day++) {
            for (int period = 0; period < 5; period++) {

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