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