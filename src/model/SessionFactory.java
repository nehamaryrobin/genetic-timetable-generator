package model;

import java.util.ArrayList;
import java.util.List;

public class SessionFactory {

    public static List<Session> createSessions(List<Subject> subjects) {

        List<Session> sessions = new ArrayList<>();

        for (Subject subject : subjects) {
            int count = 1;

            // "Software Engineering" -> "SOFTWAREENGINEERING" or "JAVA")
            String prefix = subject.getName().toUpperCase().replaceAll("\\s+", "");

            if (subject.isLab()) {
                String sessionId = prefix + "-" + count++;
                sessions.add(new Session(sessionId, subject, subject.getCredits()));

            } else {
                for (int i = 0; i < subject.getCredits(); i++) {
                    String sessionId = prefix + "-" + count++;
                    sessions.add(new Session(sessionId, subject, 1));
                }
            }
        }

        return sessions;
    }
}
