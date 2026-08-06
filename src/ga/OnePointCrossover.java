package ga;

import model.Placement;

import java.util.List;
import java.util.Random;

//FLAW : can make invalid timetable
//eg : two subjects , same slot

public class OnePointCrossover {

    private final Random random = new Random();

    public Chromosome crossover(Chromosome parent1, Chromosome parent2) {

        Chromosome child = new Chromosome();

        List<Placement> p1 = parent1.getPlacements();
        List<Placement> p2 = parent2.getPlacements();

        int size = p1.size();

        int crossoverPoint = random.nextInt(size);

        for (int i = 0; i < crossoverPoint; i++) {

            child.addPlacements(new Placement(p1.get(i)));

        }

        for (int i = crossoverPoint; i < size; i++) {

            child.addPlacements(new Placement(p2.get(i)));

        }

        child.setFitness(Integer.MAX_VALUE);
        return child;
    }

}