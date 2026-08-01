package model;

public class Session {

    private final int id;
    private final Subject subject;
    private final int duration; // Number of consecutive slots required

    public Session(int id, Subject subject, int duration) {

        this.id = id;
        this.subject = subject;
        this.duration = duration;
    }

    public int getId() {
        return id;
    }

    public Subject getSubject() {
        return subject;
    }

    public int getDuration() {
        return duration;
    }

    @Override
    public String toString() {

        return subject.getCode() + "(" + duration + ")";
    }
}