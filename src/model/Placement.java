package model;

public class Placement {

    private final Session session;
    private TimeSlot slot; // removed final - mutable
    private Room room;

    public Placement(Session session, TimeSlot slot) {
        this(session, slot, null);
    }

    public Placement(Session session, TimeSlot slot, Room room) {
        this.session = session;
        this.slot = slot; // timeSlot -> slot
    }

    public Placement(Placement other) {
        this.session = other.session;
        this.slot = new TimeSlot(
                other.slot.getDay(),
                other.slot.getPeriod());
        this.room = other.room;
    }

    public Placement copy() {
        return new Placement(this);
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

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void swapSlot(Placement other) {
        TimeSlot temp = this.slot;
        this.slot = other.slot;
        other.slot = temp;
    }

    @Override
    public String toString() {
        return session + " -> " + slot + (room != null ? " [" + room.getName() + "]" : "");
    }
}