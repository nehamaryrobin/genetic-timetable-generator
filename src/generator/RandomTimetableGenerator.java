package generator;

import model.*;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;

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

    private boolean canPlaceLab(Timetable timetable, int day, int period, int duration) {

        for (int i = 0; i < duration; i++) {
            if (!timetable.isEmpty(day, period + i)) {
                return false;
            }
        }
        return true;
    }

    private void placeLabs(Timetable timetable, List<Session> labs) {

        for (Session lab : labs) {
            boolean placed = false;
            while (!placed) {
                int day = random.nextInt(Timetable.DAYS);
                int period = random.nextInt(Timetable.PERIODS - lab.getDuration() + 1);

                if (canPlaceLab(timetable, day, period, lab.getDuration())) {
                    for (int i = 0; i < lab.getDuration(); i++) {

                        timetable.assign(day, period + i, lab);
                    }
                    placed = true;
                }
            }
        }
    }

    private void placeLectures(Timetable timetable, List<Session> lectures) {

        Collections.shuffle(lectures);

        for (Session lecture : lectures) {
            boolean placed = false;

            while (!placed) {

                int day = random.nextInt(Timetable.DAYS);
                int period = random.nextInt(Timetable.PERIODS);

                if (timetable.isEmpty(day, period)) {
                    timetable.assign(day, period, lecture);
                    placed = true;

                }
            }
        }
    }
}
