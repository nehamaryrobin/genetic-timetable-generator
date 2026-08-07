package ga;

import model.Session;

import java.util.List;

public class GeneticAlgorithm {

    private final PopulationGenerator populationGenerator = new PopulationGenerator();
    private final FitnessEvaluator evaluator = new FitnessEvaluator();
    private final TournamentSelection selection = new TournamentSelection(3);
    private final OnePointCrossover crossover = new OnePointCrossover();
    private final SwapMutation mutation = new SwapMutation(0.05);
    private final RepairOperator repair = new RepairOperator();

    public Chromosome run(int populationSize, int generations, List<Session> sessions) {

        Population population = populationGenerator.generate(populationSize, sessions);

        evaluatePopulation(population);

        for (int generation = 0; generation < generations; generation++) {

            Population nextGeneration = new Population();

            while (nextGeneration.size() < populationSize) {

                Chromosome parent1 = selection.select(population);
                Chromosome parent2 = selection.select(population);
                Chromosome child = crossover.crossover(parent1, parent2);

                repair.repair(child);
                mutation.mutate(child);
                repair.repair(child);
                evaluator.evaluate(child);
                nextGeneration.addChromosome(child);

            }
            population = nextGeneration;
        }
        return getBest(population);
    }

    private void evaluatePopulation(Population population) {

        for (Chromosome chromosome : population.getChromosomes()) {
            evaluator.evaluate(chromosome);
        }
    }

    private Chromosome getBest(Population population) {

        Chromosome best = null;

        for (Chromosome chromosome : population.getChromosomes()) {

            if (best == null || chromosome.getFitness() < best.getFitness()) {
                best = chromosome;
            }
        }
        return best;
    }
}
