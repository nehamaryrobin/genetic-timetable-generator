import model.*;
import ga.*;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Subject> subjects = SubjectRepository.getSubjects();
        List<Session> sessions = SessionFactory.createSessions(subjects);

        PopulationGenerator generator = new PopulationGenerator();
        Population population = generator.generate(10, sessions);

        // Evaluate fitness for every chromosome in the population
        FitnessEvaluator evaluator = new FitnessEvaluator();
        for (Chromosome c : population.getChromosomes()) {
            evaluator.evaluate(c);
        }

        System.out.println("Population Size : " + population.size());
        System.out.println();

        System.out.println("--- Chromosome Fitness Scores ---");
        int index = 1;
        for (Chromosome c : population.getChromosomes()) {
            System.out.printf("Chromosome %2d Fitness : %d%n", index++, c.getFitness());
        }
        System.out.println();

        TournamentSelection selection = new TournamentSelection(3);
        Chromosome parent = selection.select(population);

        System.out.println("Selected Fitness   : " + parent.getFitness());

    }

}