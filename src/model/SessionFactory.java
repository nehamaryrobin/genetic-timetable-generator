package model;

import java.util.ArrayList;
import java.util.List;

public class SessionFactory {

    public static List<Session> createSessions(List<Subject> subjects) {

        List<Session> sessions = new ArrayList<>();

        for (Subject subject : subjects) {
            int count = 1;
            String prefix = subject.getName().toUpperCase().replaceAll("\\s+", "");

            // 1. Generate Theory Lectures (1 slot each per theory credit)
            if (subject.hasTheory()) {
                for (int i = 0; i < subject.getTheoryCredits(); i++) {
                    String sessionId = prefix + "-LEC-" + count++;
                    sessions.add(new Session(sessionId, subject, 1, SessionType.LECTURE));
                }
            }

            // 2. Generate Practical Sessions (Tutorial + Lab)
            if (subject.hasPractical()) {
                // Tutorial Hour (1 slot)
                String tutSessionId = prefix + "-TUT-" + count++;
                sessions.add(new Session(tutSessionId, subject, 1, SessionType.TUTORIAL));

                // Lab Session (2 credits -> 3 consecutive slots, 1 credit -> 2 consecutive slots)
                int labDuration = (subject.getPracticalCredits() >= 2) ? 3 : 2;
                String labSessionId = prefix + "-LAB-" + count++;
                sessions.add(new Session(labSessionId, subject, labDuration, SessionType.LAB));
            }
        }

        return sessions;
    }
}
