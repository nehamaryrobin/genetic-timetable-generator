package ga;

import java.util.List;
import java.util.Random;

public class TournamentSelection {

    private final Random random = new Random();
    private final int tournamentSize;

    public TournamentSelection(int tournamentSize) {
        this.tournamentSize = tournamentSize;
    }

    public Chromosome select(Population population) {

        List<Chromosome> chromosomes = population.getChromosomes();

        Chromosome best = null;

        for (int i = 0; i < tournamentSize; i++) {

            Chromosome candidate = chromosomes.get(random.nextInt(chromosomes.size()));

            if (best == null || candidate.getFitness() < best.getFitness()) { // minimization !!
                best = candidate;

            }

        }

        return best;

    }

}