package model;

import java.util.ArrayList;
import java.util.List;

public class SessionFactory {

    public static List<Session> createSessions(List<Subject> subjects) {
        List<StudentGroup> groups = StudentGroupRepository.getStudentGroups();
        StudentGroup defaultGroup = groups.isEmpty() ? null : groups.get(0);
        return createSessions(subjects, defaultGroup);
    }

    public static List<Session> createSessions(List<Subject> subjects, StudentGroup studentGroup) {

        List<Session> sessions = new ArrayList<>();

        for (Subject subject : subjects) {
            int count = 1;
            String prefix = subject.getName().toUpperCase().replaceAll("\\s+", "");

            // 1. Generate Theory Lectures (1 slot each per theory credit)
            if (subject.hasTheory()) {
                for (int i = 0; i < subject.getTheoryCredits(); i++) {
                    String sessionId = prefix + "-LEC-" + count++;
                    sessions.add(new Session(sessionId, subject, 1, SessionType.LECTURE, studentGroup));
                }
            }

            // 2. Generate Practical Sessions (Tutorial + Lab)
            if (subject.hasPractical()) {
                // Tutorial Hour (1 slot)
                String tutSessionId = prefix + "-TUT-" + count++;
                sessions.add(new Session(tutSessionId, subject, 1, SessionType.TUTORIAL, studentGroup));

                // Lab Session (n practical credits -> n + 1 consecutive lab slots)
                int labDuration = subject.getPracticalCredits() + 1;
                String labSessionId = prefix + "-LAB-" + count++;
                sessions.add(new Session(labSessionId, subject, labDuration, SessionType.LAB, studentGroup));
            }
        }

        return sessions;
    }
}
