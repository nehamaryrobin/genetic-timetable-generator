package model;

public class Placement {

    private final Session session;
    private final int day;
    private final int period;

    public Placement(Session session, int day, int period) {

        this.session = session;
        this.day = day;
        this.period = period;
    }

    public Session getSession() {
        return session;
    }

    public int getDay() {
        return day;
    }

    public int getPeriod() {
        return period;
    }
}