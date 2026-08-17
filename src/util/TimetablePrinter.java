package util;

import config.SchedulingConfig;
import model.*;
import ga.Chromosome;

public class TimetablePrinter {

    /* Changed parameter type from Timetable to Chromosome */
    public static void print(Chromosome chromosome) {

        String[][] grid = new String[SchedulingConfig.WORKING_DAYS][SchedulingConfig.PERIODS_PER_DAY];

        for (Placement p : chromosome.getPlacements()) {

            int day = p.getSlot().getDay().ordinal();
            int start = p.getSlot().getPeriod();
            int duration = p.getSession().getDuration();

            String name = p.getSession().getSubject().getName();
            SessionType type = p.getSession().getSessionType();
            String label;

            if (type == SessionType.LAB) {
                label = name + " (LAB)";
            } else if (type == SessionType.TUTORIAL) {
                label = name + " (TUT)";
            } else {
                label = name;
            }

            for (int i = 0; i < duration; i++) {
                grid[day][start + i] = label;
            }
        }

        for (int d = 0; d < SchedulingConfig.WORKING_DAYS; d++) {

            System.out.println(Day.values()[d]);

            for (int p = 0; p < SchedulingConfig.PERIODS_PER_DAY; p++) {

                if (grid[d][p] == null)
                    System.out.printf("P%d : FREE%n", p + 1);
                else
                    System.out.printf("P%d : %s%n", p + 1, grid[d][p]);
            }
            System.out.println();

        }
    }
}