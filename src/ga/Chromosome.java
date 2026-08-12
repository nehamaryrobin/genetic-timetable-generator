package ga;

import model.Placement;

import java.util.ArrayList;
import java.util.List;

//chromosome and timetable same 
public class Chromosome {

    // private final List<Placement> genes; old
    private final List<Placement> placements; // new
    private int fitness;

    public Chromosome() {
        placements = new ArrayList<>();
        fitness = Integer.MAX_VALUE;
    }

    // COPY CONSTRUCTOR
    public Chromosome(Chromosome other) {
        this.placements = new ArrayList<>();
        for (Placement placement : other.placements) {
            this.placements.add(new Placement(placement));
        }
        this.fitness = other.fitness;
    }

    public Chromosome copy() {
        return new Chromosome(this);
    }

    public void addPlacements(Placement placement) {
        placements.add(placement);
    }

    public List<Placement> getPlacements() {
        return placements;
    }

    public int getFitness() {
        return fitness;

    }

    public void setFitness(int fitness) {
        this.fitness = fitness;

    }

}