import model.*;
import util.TimetablePrinter;
import util.TimetableCapacityValidator;
import exception.InsufficientCapacityException;
import exception.UnplaceableSessionException;
import ga.*;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        System.out.println("==================================================");
        System.out.println("      GENETIC TIMETABLE GENERATOR ENGINE          ");
        System.out.println("==================================================");

        try {
            // 1. Load Data
            List<Faculty> facultyList = FacultyRepository.getFaculty();
            List<Subject> subjects = SubjectRepository.getSubjects();
            List<Session> sessions = SessionFactory.createSessions(subjects);

            int requiredSlots = TimetableCapacityValidator.calculateTotalRequiredSlots(sessions);
            System.out.println("\nLoaded " + facultyList.size() + " faculty members & " + subjects.size() + " subjects -> " + sessions.size() + " scheduling sessions (" + requiredSlots + " period slots required).");

            System.out.println("\n--- SUBJECT TO FACULTY ASSIGNMENTS ---");
            for (Subject subject : subjects) {
                Faculty faculty = subject.getAssignedFaculty();
                String facultyInfo = (faculty != null) ? faculty.getName() + " [" + faculty.getRank() + " | " + faculty.getAdministrativeTitle() + "]" : "Unassigned";
                System.out.printf("  • %-7s : %-30s -> %s%n", subject.getCode(), subject.getName(), facultyInfo);
            }
            TimetableCapacityValidator.validate(sessions);

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

        } catch (InsufficientCapacityException e) {
            System.err.println("\n[VALIDATION ERROR] " + e.getMessage());
            System.err.println("Please reduce subject credits or increase WORKING_DAYS / PERIODS_PER_DAY in SchedulingConfig.");
        } catch (UnplaceableSessionException e) {
            System.err.println("\n[PLACEMENT ERROR] " + e.getMessage());
        }
    }
}