import model.*;
import util.TimetablePrinter;
import ga.*;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        // Data Preparation
        List<Subject> subjects = SubjectRepository.getSubjects();
        List<Session> sessions = SessionFactory.createSessions(subjects);

        // Initial Population Geneation
        PopulationGenerator generator = new PopulationGenerator();
        Population population = generator.generate(10, sessions);

        // Evaluate fitness for every chromosome in the population
        FitnessEvaluator evaluator = new FitnessEvaluator();
        for (Chromosome c : population.getChromosomes()) {
            evaluator.evaluate(c);
        }

        // Displaying Results
        System.out.println("=== INITIAL POPULATION & FITNESS ===");
        System.out.println("Population Size : " + population.size());
        System.out.println();

        System.out.println("--- Chromosome Fitness Scores ---");
        int index = 1;
        for (Chromosome c : population.getChromosomes()) {
            System.out.printf("Chromosome %2d Fitness : %d%n", index++, c.getFitness());
        }

        // Tournament Selection
        System.out.println("\n=== TOURNAMENT SELECTION ===");
        TournamentSelection selection = new TournamentSelection(3);
        Chromosome parent = selection.select(population);
        System.out.println("Selected Best Parent Fitness : " + parent.getFitness());

        // Deep Copy verification
        System.out.println("\n=== DEEP COPY VERIFICATION ===");
        Chromosome original = population.getChromosomes().get(0);
        Chromosome copy = original.copy();

        System.out.println("Original before mutation : " + original.getPlacements().get(0).getSlot());

        // Mutate copy
        copy.getPlacements().get(0).setSlot(new TimeSlot(Day.FRIDAY, 5));

        System.out.println("Original after mutation  : " + original.getPlacements().get(0).getSlot());
        System.out.println("Copy after mutation      : " + copy.getPlacements().get(0).getSlot());

        // Swap Mutation Test
        System.out.println("\n=== SWAP MUTATION TEST ===");
        SwapMutation mutation = new SwapMutation(1.0); // 1.0 only for testing, usually < 0.1
        Chromosome chromosome = population.getChromosomes().get(0);

        System.out.println("--- Timetable BEFORE Swap Mutation ---");
        TimetablePrinter.print(chromosome);

        mutation.mutate(chromosome);

        System.out.println("--- Timetable AFTER Swap Mutation ---");
        TimetablePrinter.print(chromosome);

        // Genetic Algorithm Execution
        System.out.println("\n=== RUNNING GENETIC ALGORITHM (100 Chromosomes, 200 Generations) ===");
        GeneticAlgorithm ga = new GeneticAlgorithm();
        Chromosome best = ga.run(100, 200, sessions);

        System.out.println("\n=== FINAL OPTIMIZED TIMETABLE ===");
        System.out.println("Best Solution Fitness Score (Penalties) : " + best.getFitness());
        TimetablePrinter.print(best);
    }

}