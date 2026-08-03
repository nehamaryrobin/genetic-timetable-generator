package util;

import model.*;

public class TimetablePrinter {

    public static void print(Timetable timetable) {

        System.out.println();

        for (int day = 0; day < Timetable.DAYS; day++) {

            System.out.println(Day.values()[day]);

            for (int period = 0; period < Timetable.PERIODS; period++) {

                Placement placement = timetable.get(day, period);

                if (placement == null) {

                    System.out.printf("P%d : FREE%n", period + 1);

                } else {

                    System.out.printf("P%d : %s%n", period + 1, placement.getSession().getSubject().getCode());
                }

            }
            System.out.println();
        }
    }
}