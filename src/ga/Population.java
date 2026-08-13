package ga;

import java.util.ArrayList;
import java.util.List;

public class Population {

    private List<Chromosome> chromosomes;

    public Population() {
        chromosomes = new ArrayList<>();
    }

    public void addChromosome(Chromosome chromosome) {
        chromosomes.add(chromosome);
    }

    public List<Chromosome> getChromosomes() {
        return chromosomes;
    }

    public Chromosome getBest() {
        Chromosome best = null;
        for (Chromosome chromosome : chromosomes) {
            if (best == null || chromosome.getFitness() < best.getFitness()) {
                best = chromosome;
            }
        }
        return best;
    }

    public int size() {
        return chromosomes.size();
    }

}