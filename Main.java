import model.*;
import util.TimetablePrinter;
import ga.*;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        System.out.println("==================================================");
        System.out.println("      GENETIC TIMETABLE GENERATOR ENGINE          ");
        System.out.println("==================================================");

        // 1. Load Data
        List<Subject> subjects = SubjectRepository.getSubjects();
        List<Session> sessions = SessionFactory.createSessions(subjects);

        System.out.println("\nLoaded " + subjects.size() + " subjects -> " + sessions.size() + " scheduling sessions.");

        // 2. Configure Genetic Algorithm Engine (Dependency Injection)
        GeneticAlgorithm ga = new GeneticAlgorithm(
                new PopulationGenerator(),
                new FitnessEvaluator(),
                new TournamentSelection(3),
                new OnePointCrossover(),
                new SwapMutation(0.05),
                new RepairOperator(),
                new Elitism(2)
        );

        // 3. Execute Evolutionary Optimization
        System.out.println("\n=== EXECUTING GENETIC ALGORITHM (Population: 100, Generations: 100) ===");
        Chromosome best = ga.run(100, 100, sessions);

        // 4. Output Final Best Timetable
        System.out.println("\n=== FINAL BEST TIMETABLE ===");
        System.out.println("Fitness: " + best.getFitness());
        System.out.println();
        TimetablePrinter.print(best);
    }
}