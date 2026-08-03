package util;

import config.SchedulingConfig;
import ga.Chromosome;
import model.Placement;

public class TimetableGridBuilder {

    public static Placement[][] build(Chromosome chromosome) {

        Placement[][] grid = new Placement[SchedulingConfig.WORKING_DAYS][SchedulingConfig.PERIODS_PER_DAY];

        for (Placement placement : chromosome.getPlacements()) {

            int day = placement.getSlot()
                    .getDay()
                    .ordinal();

            int period = placement.getSlot()
                    .getPeriod();

            int duration = placement.getSession()
                    .getDuration();

            for (int i = 0; i < duration; i++) {
                grid[day][period + i] = placement;
            }

        }

        return grid;

    }

}
