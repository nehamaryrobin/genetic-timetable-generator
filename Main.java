import generator.RandomTimetableGenerator;
import model.*;
import util.TimetablePrinter;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Subject> subjects = SubjectRepository.getSubjects();

        List<Session> sessions = SessionFactory.createSessions(subjects);

        RandomTimetableGenerator generator = new RandomTimetableGenerator();

        Timetable timetable = generator.generate(sessions);

        TimetablePrinter.print(timetable);

    }

}