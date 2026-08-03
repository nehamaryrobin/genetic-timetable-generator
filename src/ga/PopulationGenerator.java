package ga;

import generator.RandomTimetableGenerator;
import model.Session;

import java.util.List;

public class PopulationGenerator {

    private final RandomTimetableGenerator generator = new RandomTimetableGenerator();

    public Population generate(int populationSize,
            List<Session> sessions) {

        Population population = new Population();

        for (int i = 0; i < populationSize; i++) {

            Chromosome chromosome = generator.generate(sessions);
            population.addChromosome(chromosome);

        }

        return population;
    }

}