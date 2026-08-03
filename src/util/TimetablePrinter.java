package util;

import config.SchedulingConfig;
import model.*;

public class TimetablePrinter {

    public static void print(
            Timetable timetable) {

        String[][] grid = new String[SchedulingConfig.WORKING_DAYS][SchedulingConfig.PERIODS_PER_DAY];

        for (Placement p : timetable.getPlacements()) {

            int day = p.getSlot()
                    .getDay()
                    .ordinal();

            int start = p.getSlot()
                    .getPeriod();

            int duration = p.getSession()
                    .getDuration();

            for (int i = 0; i < duration; i++) {

                grid[day][start + i] = p.getSession()
                        .getSubject()
                        .getCode();
            }
        }

        for (int d = 0; d < SchedulingConfig.WORKING_DAYS; d++) {

            System.out.println(
                    Day.values()[d]);

            for (int p = 0; p < SchedulingConfig.PERIODS_PER_DAY; p++) {

                if (grid[d][p] == null)
                    System.out.printf(
                            "P%d : FREE%n",
                            p + 1);
                else
                    System.out.printf(
                            "P%d : %s%n",
                            p + 1,
                            grid[d][p]);
            }

            System.out.println();

        }

    }

}