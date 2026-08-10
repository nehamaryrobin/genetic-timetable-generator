package ga;

import model.Placement;
import util.PlacementIndex;
import util.TimetableGridBuilder;

public class EvaluationContext {

    private final Chromosome chromosome;
    private final Placement[][] grid;
    private final PlacementIndex index;

    public EvaluationContext(Chromosome chromosome) {

        this.chromosome = chromosome;

        this.grid = TimetableGridBuilder.build(chromosome);

        this.index = new PlacementIndex(chromosome);
    }

    public Chromosome getChromosome() {
        return chromosome;
    }

    public Placement[][] getGrid() {
        return grid;
    }

    public PlacementIndex getIndex() {
        return index;
    }
}