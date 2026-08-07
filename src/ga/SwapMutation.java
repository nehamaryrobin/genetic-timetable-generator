package ga;

import model.Placement;
import model.TimeSlot;

import java.util.List;
import java.util.Random;

import config.SchedulingConfig;

public class SwapMutation {

    private static final int MAX_ATTEMPTS = 50;

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

        int attempts = 0;

        while (attempts < MAX_ATTEMPTS) {

            int first = random.nextInt(placements.size());
            int second = random.nextInt(placements.size());

            if (first == second) {
                attempts++;
                continue;
            }

            Placement p1 = placements.get(first);
            Placement p2 = placements.get(second);

            if (isLegalSwap(p1, p2)) {

                p1.swapSlot(p2);
                return;

            }
            attempts++;
        }
    }

    private boolean isLegalSwap(Placement first, Placement second) {

        return isLegalStart(first, second.getSlot()) && isLegalStart(second, first.getSlot());

    }

    private boolean isLegalStart(Placement placement, TimeSlot slot) {

        int duration = placement.getSession().getDuration();
        int lastPeriod = slot.getPeriod() + duration - 1;

        return lastPeriod < SchedulingConfig.PERIODS_PER_DAY;
    }

}