package model;

public class Placement {

    private final Session session;
    private final TimeSlot timeSlot;

    public Placement(Session session, TimeSlot timeSlot) {

        this.session = session;
        this.timeSlot = timeSlot;
    }

    public Session getSession() {
        return session;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    @Override
    public String toString() {
        return session + " @ " + timeSlot;
    }
}