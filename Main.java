import generator.RandomTimetableGenerator;
import model.*;
import util.TimetablePrinter;

import java.util.List;

import ga.Population;
import ga.PopulationGenerator;

public class Main {

    public static void main(String[] args) {

        List<Subject> subjects = SubjectRepository.getSubjects();

        List<Session> sessions = SessionFactory.createSessions(subjects);

        PopulationGenerator generator = new PopulationGenerator();

        Population population = generator.generate(10, sessions);

        System.out.println(
                "Population Size : "
                        + population.size());

    }

}