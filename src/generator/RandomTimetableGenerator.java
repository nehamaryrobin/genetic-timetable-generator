package generator;

import config.SchedulingConfig;
import model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import ga.Chromosome;

public class RandomTimetableGenerator {

    private final Random random = new Random();

    public Chromosome generate(List<Session> sessions) {
        Chromosome chromosome = new Chromosome();

        List<Session> lectures = new ArrayList<>();
        List<Session> labs = new ArrayList<>();

        separateSessions(sessions, lectures, labs);
        placeLabs(chromosome, labs);
        placeLectures(chromosome, lectures);

        return chromosome;
    }

    private void separateSessions(List<Session> sessions, List<Session> lectures, List<Session> labs) {
        for (Session session : sessions) {
            if (session.getSessionType() == SessionType.LAB) {
                labs.add(session);
            } else {
                lectures.add(session);
            }
        }
    }

    private void placeLabs(Chromosome chromosome, List<Session> labs) {
        for (Session lab : labs) {
            boolean placed = false;
            while (!placed) {
                int day = random.nextInt(SchedulingConfig.WORKING_DAYS);
                int period = random.nextInt(SchedulingConfig.PERIODS_PER_DAY - lab.getDuration() + 1);

                if (canPlaceLab(chromosome, Day.values()[day], period, lab.getDuration())) {
                    Placement placement = new Placement(
                            lab,
                            new TimeSlot(Day.values()[day], period));
                    chromosome.addPlacements(placement);
                    placed = true;
                }
            }
        }
    }

    private boolean canPlaceLab(Chromosome chromosome, Day day, int period, int duration) {
        for (int i = 0; i < duration; i++) {
            if (occupied(chromosome, day, period + i)) {
                return false;
            }
        }
        return true;
    }

    private boolean occupied(Chromosome chromosome, Day day, int period) {
        for (Placement p : chromosome.getPlacements()) {
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

    private void placeLectures(Chromosome chromosome, List<Session> lectures) {
        Collections.shuffle(lectures);

        for (Session lecture : lectures) {
            boolean placed = false;
            while (!placed) {
                int day = random.nextInt(SchedulingConfig.WORKING_DAYS);
                int period = random.nextInt(SchedulingConfig.PERIODS_PER_DAY);

                if (!occupied(chromosome, Day.values()[day], period)) {
                    Placement placement = new Placement(
                            lecture,
                            new TimeSlot(Day.values()[day], period));
                    chromosome.addPlacements(placement);
                    placed = true;
                }
            }
        }
    }
}