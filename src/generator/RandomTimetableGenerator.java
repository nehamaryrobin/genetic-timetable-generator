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

        separateSessions(sessions, lectures, labs); // to do

        placeLabs(timetable, labs); // to do

        placeLectures(timetable, lectures); // to do

        return timetable;

    }

}