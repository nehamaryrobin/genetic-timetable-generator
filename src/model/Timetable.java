package model;

public class Timetable {

    public static final int DAYS = 5;
    public static final int PERIODS = 6;

    private final Subject[][] slots;

    public Timetable() {

        slots = new Subject[DAYS][PERIODS];
    }

    public void assign(int day, int period, Subject subject) {

        slots[day][period] = subject;
    }

    public Subject get(int day, int period) {

        return slots[day][period];
    }

    public Subject[][] getSlots() {
        return slots;
    }

}