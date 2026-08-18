package exception;

public class InsufficientCapacityException extends RuntimeException {

    private final int requiredSlots;
    private final int totalCapacity;

    public InsufficientCapacityException(int requiredSlots, int totalCapacity) {
        super(String.format(
            "Schedule capacity exceeded! Total required session slots (%d) exceed available timetable capacity (%d slots).",
            requiredSlots, totalCapacity
        ));
        this.requiredSlots = requiredSlots;
        this.totalCapacity = totalCapacity;
    }

    public int getRequiredSlots() {
        return requiredSlots;
    }

    public int getTotalCapacity() {
        return totalCapacity;
    }
}
