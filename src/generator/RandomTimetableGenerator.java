package generator;

import model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RandomTimetableGenerator {

    private final Random random = new Random();

    public Timetable generate(List<Session> sessions) {
        Timetable timetable = new Timetable();

        List<Session> lectures = new ArrayList<>();
        List<Session> labs = new ArrayList<>();

        separateSessions(sessions, lectures, labs);
        placeLabs(timetable, labs);
        placeLectures(timetable, lectures);

        return timetable;
    }

    private void separateSessions(List<Session> sessions, List<Session> lectures, List<Session> labs) {
        for (Session session : sessions) {
            if (session.getSubject().isLab()) {
                labs.add(session);
            } else {
                lectures.add(session);
            }
        }
    }

    private void placeLabs(Timetable timetable, List<Session> labs) {
        for (Session lab : labs) {
            boolean placed = false;
            while (!placed) {
                int day = random.nextInt(5);
                int period = random.nextInt(6 - lab.getDuration() + 1);

                if (canPlaceLab(timetable, Day.values()[day], period, lab.getDuration())) {
                    Placement placement = new Placement(
                            lab,
                            new TimeSlot(Day.values()[day], period));
                    timetable.addPlacement(placement);
                    placed = true;
                }
            }
        }
    }

    private boolean canPlaceLab(Timetable timetable, Day day, int period, int duration) {
        for (int i = 0; i < duration; i++) {
            if (occupied(timetable, day, period + i)) {
                return false;
            }
        }
        return true;
    }

    private boolean occupied(Timetable timetable, Day day, int period) {
        for (Placement p : timetable.getPlacements()) {
            if (p.getSlot().getDay() == day) {
                int start = p.getSlot().getPeriod();
                int end = start + p.getSession().getDuration() - 1;

                if (period >= start && period <= end) {
                    return true;
                }
            }
        }
        return false;
    }

    private void placeLectures(Timetable timetable, List<Session> lectures) {
        Collections.shuffle(lectures);

        for (Session lecture : lectures) {
            boolean placed = false;
            while (!placed) {
                int day = random.nextInt(5);
                int period = random.nextInt(6);

                if (!occupied(timetable, Day.values()[day], period)) {
                    Placement placement = new Placement(
                            lecture,
                            new TimeSlot(Day.values()[day], period));
                    timetable.addPlacement(placement);
                    placed = true;
                }
            }
        }
    }
}