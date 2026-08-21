package ga;

import java.util.Comparator;

public class Elitism {

    private final int eliteCount;

    public Elitism(int eliteCount) {
        this.eliteCount = eliteCount;
    }

    public int getEliteCount() {
        return eliteCount;
    }

    // O(N log N)
    public void preserve(Population current, Population next) {
        current.getChromosomes()
                .stream()
                .sorted(Comparator.comparingInt(Chromosome::getFitness))
                .limit(eliteCount)
                .forEach(chromosome -> next.addChromosome(chromosome.copy()));
    }
}