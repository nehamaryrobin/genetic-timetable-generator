package model;

public class TimeSlot {

    private final Day day;
    private final int period;

    public TimeSlot(Day day, int period) {

        this.day = day;
        this.period = period;
    }

    public Day getDay() {
        return day;
    }

    public int getPeriod() {
        return period;
    }

    @Override
    public String toString() {
        return day + " P" + period;
    }

}