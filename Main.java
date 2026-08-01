import model.*;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Subject> subjects = SubjectRepository.getSubjects();

        List<Session> sessions = SessionFactory.createSessions(subjects);

        for (Session s : sessions) {

            System.out.println(s.getId() + "(" + s.getDuration() + ")");

        }

    }

}