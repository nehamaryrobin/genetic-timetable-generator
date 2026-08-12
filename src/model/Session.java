package model;

public class Session {

    private final String id;
    private final Subject subject;
    private final int duration; // Number of consecutive slots required

    public Session(String id, Subject subject, int duration) {

        this.id = id;
        this.subject = subject;
        this.duration = duration;
    }

    public String getId() {
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