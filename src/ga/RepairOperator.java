package ga;

import config.SchedulingConfig;
import model.Day;
import model.Placement;
import model.TimeSlot;

import java.util.ArrayList;
import java.util.List;

public class RepairOperator {

    public void repair(Chromosome chromosome) {
        List<Placement>[][] multiGrid = buildMultiGrid(chromosome);

        for (Placement placement : chromosome.getPlacements()) {
            if (isValidPlacement(multiGrid, placement)) {
                continue;
            }

            relocatePlacement(multiGrid, placement);
        }
    }

    @SuppressWarnings("unchecked")
    private List<Placement>[][] buildMultiGrid(Chromosome chromosome) {
        List<Placement>[][] grid = new List[SchedulingConfig.WORKING_DAYS][SchedulingConfig.PERIODS_PER_DAY];
        for (int d = 0; d < SchedulingConfig.WORKING_DAYS; d++) {
            for (int p = 0; p < SchedulingConfig.PERIODS_PER_DAY; p++) {
                grid[d][p] = new ArrayList<>();
            }
        }

        for (Placement placement : chromosome.getPlacements()) {
            int day = placement.getSlot().getDay().ordinal();
            int start = placement.getSlot().getPeriod();
            int duration = placement.getSession().getDuration();

            for (int i = 0; i < duration; i++) {
                if (start + i < SchedulingConfig.PERIODS_PER_DAY) {
                    grid[day][start + i].add(placement);
                }
            }
        }

        return grid;
    }

    private boolean isValidPlacement(List<Placement>[][] grid, Placement placement) {
        int day = placement.getSlot().getDay().ordinal();
        int start = placement.getSlot().getPeriod();
        int duration = placement.getSession().getDuration();

        for (int i = 0; i < duration; i++) {
            if (start + i >= SchedulingConfig.PERIODS_PER_DAY || grid[day][start + i].size() > 1) {
                return false;
            }
        }

        return true;
    }

    private void relocatePlacement(List<Placement>[][] grid, Placement placement) {
        int oldDay = placement.getSlot().getDay().ordinal();
        int oldStart = placement.getSlot().getPeriod();
        int duration = placement.getSession().getDuration();

        for (Day day : Day.values()) {
            int d = day.ordinal();
            for (int period = 0; period <= SchedulingConfig.PERIODS_PER_DAY - duration; period++) {
                if (canPlace(grid, d, period, duration, placement)) {
                    // Remove placement from old slots
                    for (int i = 0; i < duration; i++) {
                        if (oldStart + i < SchedulingConfig.PERIODS_PER_DAY) {
                            grid[oldDay][oldStart + i].remove(placement);
                        }
                    }

                    // Update slot
                    placement.setSlot(new TimeSlot(day, period));

                    // Add placement to new slots
                    for (int i = 0; i < duration; i++) {
                        grid[d][period + i].add(placement);
                    }
                    return;
                }
            }
        }
    }

    private boolean canPlace(List<Placement>[][] grid, int day, int period, int duration, Placement currentPlacement) {
        for (int i = 0; i < duration; i++) {
            List<Placement> cell = grid[day][period + i];
            // Cell is valid if empty or contains only currentPlacement
            if (!cell.isEmpty() && !(cell.size() == 1 && cell.contains(currentPlacement))) {
                return false;
            }
        }
        return true;
    }
}