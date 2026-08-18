package util;

import config.SchedulingConfig;
import exception.InsufficientCapacityException;
import model.Session;

import java.util.List;

public class TimetableCapacityValidator {

    public static void validate(List<Session> sessions) {
        int totalRequiredSlots = calculateTotalRequiredSlots(sessions);
        int totalCapacity = SchedulingConfig.WORKING_DAYS * SchedulingConfig.PERIODS_PER_DAY;

        if (totalRequiredSlots > totalCapacity) {
            throw new InsufficientCapacityException(totalRequiredSlots, totalCapacity);
        }
    }

    public static int calculateTotalRequiredSlots(List<Session> sessions) {
        int slots = 0;
        for (Session session : sessions) {
            slots += session.getDuration();
        }
        return slots;
    }
}
