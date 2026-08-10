package util;

import ga.Chromosome;
import model.Day;
import model.Placement;
import model.Subject;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//PlacementIndex doesn't care what the constraint is.

public class PlacementIndex {

    private final Map<Subject, List<Placement>> bySubject;
    private final Map<Day, List<Placement>> byDay;

    public PlacementIndex(Chromosome chromosome) {

        bySubject = new HashMap<>();
        byDay = new EnumMap<>(Day.class); // new EnumMap
        buildIndex(chromosome);
    }

    private void buildIndex(Chromosome chromosome) {

        for (Placement placement : chromosome.getPlacements()) {

            Subject subject = placement.getSession().getSubject();

            Day day = placement.getSlot().getDay();

            bySubject.computeIfAbsent(subject, s -> new ArrayList<>()).add(placement);

            byDay.computeIfAbsent(day, d -> new ArrayList<>()).add(placement);
        }
    }

    public List<Placement> getBySubject(Subject subject) {

        return bySubject.getOrDefault(subject, new ArrayList<>());
    }

    public List<Placement> getByDay(Day day) {

        return byDay.getOrDefault(day, new ArrayList<>());
    }

    public Map<Subject, List<Placement>> getBySubject() {
        return bySubject;
    }

    public Map<Day, List<Placement>> getByDay() {
        return byDay;
    }
}