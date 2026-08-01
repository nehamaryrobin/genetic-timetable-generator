package model;

public class Session {

    private final Subject subject;
    private final int duration; // Number of consecutive slots required

    public Session(Subject subject, int duration) {

        this.subject = subject;
        this.duration = duration;
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