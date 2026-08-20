package util;

import config.SchedulingConfig;
import model.*;
import ga.Chromosome;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TimetablePrinter {

    @SuppressWarnings("unchecked")
    public static void print(Chromosome chromosome) {
        List<String>[][] grid = new List[SchedulingConfig.WORKING_DAYS][SchedulingConfig.PERIODS_PER_DAY];

        for (int d = 0; d < SchedulingConfig.WORKING_DAYS; d++) {
            for (int p = 0; p < SchedulingConfig.PERIODS_PER_DAY; p++) {
                grid[d][p] = new ArrayList<>();
            }
        }

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
                if (start + i < SchedulingConfig.PERIODS_PER_DAY) {
                    grid[day][start + i].add(label);
                }
            }
        }

        for (int d = 0; d < SchedulingConfig.WORKING_DAYS; d++) {
            System.out.println(Day.values()[d]);

            for (int p = 0; p < SchedulingConfig.PERIODS_PER_DAY; p++) {
                List<String> slotLabels = grid[d][p];
                if (slotLabels.isEmpty()) {
                    System.out.printf("P%d : FREE%n", p + 1);
                } else if (slotLabels.size() == 1) {
                    System.out.printf("P%d : %s%n", p + 1, slotLabels.get(0));
                } else {
                    System.out.printf("P%d : [CONFLICT: %s]%n", p + 1, String.join(" / ", slotLabels));
                }
            }
            System.out.println();
        }

        printFacultyWorkloadSummary(chromosome);
    }

    public static void printFacultyWorkloadSummary(Chromosome chromosome) {
        PlacementIndex index = new PlacementIndex(chromosome);
        Map<Faculty, Integer> workloads = index.getFacultyWeeklyWorkload();

        System.out.println("=== FACULTY WORKLOAD SUMMARY ===");
        System.out.printf("%-25s %-32s %-20s %-10s%n", "Faculty Member", "Rank & Title", "Weekly Load / Limit", "Status");
        System.out.println("----------------------------------------------------------------------------------");

        for (Map.Entry<Faculty, Integer> entry : workloads.entrySet()) {
            Faculty f = entry.getKey();
            int load = entry.getValue();
            int max = f.getMaxWeeklyLoad();
            String rankTitle = f.getRank() + " | " + f.getAdministrativeTitle();
            String status = (load <= max) ? "OK" : "[EXCEEDED]";
            String loadStr = String.format("%d / %d periods", load, max);

            System.out.printf("%-25s %-32s %-20s %-10s%n", f.getName(), rankTitle, loadStr, status);
        }
        System.out.println();
    }
}