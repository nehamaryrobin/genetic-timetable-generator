package model;

import java.util.ArrayList;
import java.util.List;

public class SessionFactory {
    public static List<Session> createSessions(List<Subject> subjects) {

        List<Session> sessions = new ArrayList<>();
        int sessionId = 1;

        for (Subject subject : subjects) {

            if (subject.isLab()) {
                sessions.add(new Session(sessionId++, subject, subject.getCredits()));

            } else {

                for (int i = 0; i < subject.getCredits(); i++) {
                    sessions.add(new Session(sessionId++, subject, 1));
                }
            }
        }
        return sessions;
    }
}