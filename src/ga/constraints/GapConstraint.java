package ga.constraints;

import ga.EvaluationContext;
import model.Day;
import model.Placement;
import util.PlacementIndex;

import java.util.List;

public class GapConstraint
        implements Constraint {

    @Override
    public int evaluate(
            EvaluationContext context) {

        int violations = 0;

        PlacementIndex index = context.getIndex();

        for (Day day : Day.values()) {

            List<Placement> placements = index.getByDay(day);

            if (placements.isEmpty())
                continue;

            int firstPeriod = getFirstPeriod(placements);

            int lastPeriod = getLastPeriod(placements);

            int occupiedPeriods = 0;

            for (Placement placement : placements) {

                occupiedPeriods += placement.getSession()
                        .getDuration();
            }

            int span = lastPeriod - firstPeriod + 1;

            int gaps = span - occupiedPeriods;

            if (gaps > 0) {

                violations += gaps;
            }
        }

        return violations;
    }

    private int getFirstPeriod(
            List<Placement> placements) {

        int first = Integer.MAX_VALUE;

        for (Placement placement : placements) {

            int period = placement.getSlot()
                    .getPeriod();

            first = Math.min(first, period);
        }

        return first;
    }

    private int getLastPeriod(
            List<Placement> placements) {

        int last = Integer.MIN_VALUE;

        for (Placement placement : placements) {

            int period = placement.getSlot()
                    .getPeriod();

            int endPeriod = period
                    + placement.getSession()
                            .getDuration()
                    - 1;

            last = Math.max(last, endPeriod);
        }

        return last;
    }
}