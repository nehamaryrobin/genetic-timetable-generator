package model;

public class Placement {

    private final Session session;
    private TimeSlot slot; // removed final - mutable

    public Placement(Session session, TimeSlot slot) {
        this.session = session;
        this.slot = slot; // timeSlot -> slot
    }

    public Session getSession() {
        return session;
    }

    public TimeSlot getSlot() {
        return slot;
    }

    public void setSlot(TimeSlot slot) {
        this.slot = slot;
    }

    @Override
    public String toString() {
        return session + " -> " + slot;
    }
}