package ga;

import model.Placement;
import model.TimeSlot;

import java.util.List;
import java.util.Random;

public class SwapMutation {

    private final Random random = new Random();
    private final double mutationRate;

    public SwapMutation(double mutationRate) {
        this.mutationRate = mutationRate;
    }

    public void mutate(Chromosome chromosome) {

        if (random.nextDouble() > mutationRate)
            return;

        List<Placement> placements = chromosome.getPlacements();

        if (placements.size() < 2)
            return;

        int first = random.nextInt(placements.size());
        int second = random.nextInt(placements.size());

        while (first == second) {
            second = random.nextInt(placements.size());
        }

        // SYNTAX : this.swapSlot(other)
        placements.get(first).swapSlot(placements.get(second));

    }

}