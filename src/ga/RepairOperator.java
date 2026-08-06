package ga;

import config.SchedulingConfig;
import model.Day;
import model.Placement;
import model.TimeSlot;
import util.TimetableGridBuilder;

/* HIGH LEVEL ALGO : 
Build grid
For every Placement
    Check if placement is valid
    If not
        Find first available slot
        Move placement
Rebuild grid

Return */

public class RepairOperator {

    public void repair(Chromosome chromosome) {

        Placement[][] grid = TimetableGridBuilder.build(chromosome);

        for (Placement placement : chromosome.getPlacements()) {

            if (isValidPlacement(grid, placement))
                continue;

            relocatePlacement(chromosome, grid, placement);

            // FLAW : O(N)^2 , later refactor to SlotAvailabilityTracker
            grid = TimetableGridBuilder.build(chromosome);

        }

    }

    private boolean isValidPlacement(Placement[][] grid, Placement placement) {

        int day = placement.getSlot().getDay().ordinal();
        int start = placement.getSlot().getPeriod();
        int duration = placement.getSession().getDuration();

        for (int i = 0; i < duration; i++) {

            Placement current = grid[day][start + i];
            if (current != placement)
                return false;
        }

        return true;

    }

    private void relocatePlacement(Chromosome chromosome, Placement[][] grid, Placement placement) {

        int duration = placement.getSession().getDuration();

        for (Day day : Day.values()) {

            for (int period = 0; period <= SchedulingConfig.PERIODS_PER_DAY - duration; period++) {

                if (canPlace(grid, day.ordinal(), period, duration)) {

                    placement.setSlot(new TimeSlot(day, period));
                    return;

                }

            }
        }
    }

    private boolean canPlace(Placement[][] grid, int day, int period, int duration) {

        for (int i = 0; i < duration; i++) {
            if (grid[day][period + i] != null)
                return false;
        }
        return true;
    }

}