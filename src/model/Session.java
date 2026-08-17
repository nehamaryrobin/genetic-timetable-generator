package model;

public class Session {

    private final String id;
    private final Subject subject;
    private final int duration; // Number of consecutive slots required
    private final SessionType sessionType;

    public Session(String id, Subject subject, int duration, SessionType sessionType) {

        this.id = id;
        this.subject = subject;
        this.duration = duration;
        this.sessionType = sessionType;
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

    public SessionType getSessionType() {
        return sessionType;
    }

    @Override
    public String toString() {
        return subject.getCode() + "(" + duration + ")";
    }
}