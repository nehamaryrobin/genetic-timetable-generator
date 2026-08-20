package util;

import ga.Chromosome;
import model.Day;
import model.Faculty;
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
    private final Map<Faculty, List<Placement>> byFaculty;
    private final Map<Faculty, Integer> facultyWeeklyWorkload;
    private final Map<Faculty, Map<Day, Integer>> facultyDailyWorkload;

    public PlacementIndex(Chromosome chromosome) {
        bySubject = new HashMap<>();
        byDay = new EnumMap<>(Day.class);
        byFaculty = new HashMap<>();
        facultyWeeklyWorkload = new HashMap<>();
        facultyDailyWorkload = new HashMap<>();
        buildIndex(chromosome);
    }

    private void buildIndex(Chromosome chromosome) {
        for (Placement placement : chromosome.getPlacements()) {
            Subject subject = placement.getSession().getSubject();
            Day day = placement.getSlot().getDay();

            bySubject.computeIfAbsent(subject, s -> new ArrayList<>()).add(placement);
            byDay.computeIfAbsent(day, d -> new ArrayList<>()).add(placement);

            Faculty faculty = subject.getAssignedFaculty();
            if (faculty != null) {
                byFaculty.computeIfAbsent(faculty, f -> new ArrayList<>()).add(placement);

                int duration = placement.getSession().getDuration();

                facultyWeeklyWorkload.put(faculty, facultyWeeklyWorkload.getOrDefault(faculty, 0) + duration);

                facultyDailyWorkload.computeIfAbsent(faculty, f -> new EnumMap<>(Day.class));
                Map<Day, Integer> dailyMap = facultyDailyWorkload.get(faculty);
                dailyMap.put(day, dailyMap.getOrDefault(day, 0) + duration);
            }
        }
    }

    public List<Placement> getBySubject(Subject subject) {
        return bySubject.getOrDefault(subject, new ArrayList<>());
    }

    public List<Placement> getByDay(Day day) {
        return byDay.getOrDefault(day, new ArrayList<>());
    }

    public List<Placement> getByFaculty(Faculty faculty) {
        return byFaculty.getOrDefault(faculty, new ArrayList<>());
    }

    public Map<Subject, List<Placement>> getBySubject() {
        return bySubject;
    }

    public Map<Day, List<Placement>> getByDay() {
        return byDay;
    }

    public Map<Faculty, List<Placement>> getByFaculty() {
        return byFaculty;
    }

    public Map<Faculty, Integer> getFacultyWeeklyWorkload() {
        return facultyWeeklyWorkload;
    }

    public int getFacultyWeeklyWorkload(Faculty faculty) {
        return facultyWeeklyWorkload.getOrDefault(faculty, 0);
    }

    public int getFacultyDailyWorkload(Faculty faculty, Day day) {
        Map<Day, Integer> dailyMap = facultyDailyWorkload.get(faculty);
        return (dailyMap != null) ? dailyMap.getOrDefault(day, 0) : 0;
    }

    public Map<Faculty, Map<Day, Integer>> getFacultyDailyWorkload() {
        return facultyDailyWorkload;
    }
}