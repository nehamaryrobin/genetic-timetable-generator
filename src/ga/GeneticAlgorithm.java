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
        Chromosome bestSoFar = population.getBest().copy();

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
            Chromosome currentBest = population.getBest();

            if (currentBest.getFitness() < bestSoFar.getFitness()) {
                bestSoFar = currentBest.copy();
            }

            double average = getAverageFitness(population);

            System.out.println(
                    "Generation "
                            + generation
                            + " | Best: "
                            + currentBest.getFitness()
                            + " | Best So Far: "
                            + bestSoFar.getFitness()
                            + " | Average: "
                            + String.format("%.2f", average));
        }

        return bestSoFar;
    }

    private void evaluatePopulation(Population population) {

        for (Chromosome chromosome : population.getChromosomes()) {
            evaluator.evaluate(chromosome);
        }
    }

    private double getAverageFitness(Population population) {

        int total = 0;
        for (Chromosome chromosome : population.getChromosomes()) {
            total += chromosome.getFitness();
        }
        return (double) total / population.size();
    }

}
