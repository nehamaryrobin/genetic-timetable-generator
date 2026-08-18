package exception;

import model.Session;

public class UnplaceableSessionException extends RuntimeException {

    private final Session session;
    private final int maxAttempts;

    public UnplaceableSessionException(Session session, int maxAttempts) {
        super(String.format(
            "Failed to place session '%s' (%s, duration: %d slots) after %d random attempts. The timetable grid may be too fragmented or over-constrained.",
            session.getId(),
            session.getSessionType(),
            session.getDuration(),
            maxAttempts
        ));
        this.session = session;
        this.maxAttempts = maxAttempts;
    }

    public Session getSession() {
        return session;
    }

    public int getMaxAttempts() {
        return maxAttempts;
    }
}
