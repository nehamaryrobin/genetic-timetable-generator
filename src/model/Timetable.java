package model;

public class Timetable {

    // cahnge : session class -> placement class
    public static final int DAYS = 5;
    public static final int PERIODS = 6;

    private final Placement[][] slots;

    public Timetable() {
        slots = new Placement[DAYS][PERIODS];
    }

    public boolean isEmpty(int day, int period) {
        return slots[day][period] == null;
    }

    public Placement get(int day, int period) {
        return slots[day][period];
    }

    public void assign(int day, int period, Session session) {
        Placement placement = new Placement(session, new TimeSlot(Day.values()[day], period)); // new

        slots[day][period] = placement;
    }

    public Placement[][] getSlots() {
        return slots;
    }

}