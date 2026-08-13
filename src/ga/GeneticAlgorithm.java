package ga;

import model.Session;

import java.util.List;

public class GeneticAlgorithm {

    private final PopulationGenerator populationGenerator;
    private final FitnessEvaluator evaluator;
    private final TournamentSelection selection;
    private final OnePointCrossover crossover;
    private final SwapMutation mutation;
    private final RepairOperator repair;
    private final Elitism elitism;

    public GeneticAlgorithm(
            PopulationGenerator populationGenerator,
            FitnessEvaluator evaluator,
            TournamentSelection selection,
            OnePointCrossover crossover,
            SwapMutation mutation,
            RepairOperator repair,
            Elitism elitism) {
        this.populationGenerator = populationGenerator;
        this.evaluator = evaluator;
        this.selection = selection;
        this.crossover = crossover;
        this.mutation = mutation;
        this.repair = repair;
        this.elitism = elitism;
    }

    public Chromosome run(int populationSize, int generations, List<Session> sessions) {

        Population population = populationGenerator.generate(populationSize, sessions);

        evaluatePopulation(population);

        for (int generation = 0; generation < generations; generation++) {

            Population nextGeneration = new Population();

            elitism.preserve(population, nextGeneration);

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

            Chromosome best = getBest(population);
            double average = getAverageFitness(population);

            System.out.println("Generation " + generation + " | Best: " + best.getFitness() + " | Average: "
                    + String.format("%.2f", average) + " | Elitism: " + elitism.getEliteCount());
        }
        return getBest(population);
    }

    private void evaluatePopulation(Population population) {

        for (Chromosome chromosome : population.getChromosomes()) {
            evaluator.evaluate(chromosome);
        }
    }

    @Deprecated
    private Chromosome getBest(Population population) {
        Chromosome best = null;
        for (Chromosome chromosome : population.getChromosomes()) {
            if (best == null || chromosome.getFitness() < best.getFitness()) {
                best = chromosome;
            }
        }
        return best;
    }

    private double getAverageFitness(Population population) {
        long sum = 0;
        for (Chromosome c : population.getChromosomes()) {
            sum += c.getFitness();
        }
        return (double) sum / population.size();
    }

}
