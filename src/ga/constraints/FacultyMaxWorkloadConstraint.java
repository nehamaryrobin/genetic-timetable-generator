package ga.constraints;

import ga.EvaluationContext;
import model.Day;
import model.Faculty;
import util.PlacementIndex;

import java.util.Map;

public class FacultyMaxWorkloadConstraint implements Constraint {

    @Override
    public int evaluate(EvaluationContext context) {
        int violations = 0;
        PlacementIndex index = context.getIndex();
        Map<Faculty, Integer> weeklyWorkloads = index.getFacultyWeeklyWorkload();

        for (Map.Entry<Faculty, Integer> entry : weeklyWorkloads.entrySet()) {
            Faculty faculty = entry.getKey();
            int weeklyLoad = entry.getValue();

            // 1. Weekly Workload Limit Violation
            if (weeklyLoad > faculty.getMaxWeeklyLoad()) {
                violations += (weeklyLoad - faculty.getMaxWeeklyLoad());
            }

            // 2. Daily Workload Limit Violation
            for (Day day : Day.values()) {
                int dailyLoad = index.getFacultyDailyWorkload(faculty, day);
                if (dailyLoad > faculty.getMaxDailyLoad()) {
                    violations += (dailyLoad - faculty.getMaxDailyLoad());
                }
            }
        }

        return violations;
    }
}
