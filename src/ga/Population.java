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

    public int size() {
        return chromosomes.size();
    }

}