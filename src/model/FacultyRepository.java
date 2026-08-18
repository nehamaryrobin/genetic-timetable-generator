package model;

import java.util.ArrayList;
import java.util.List;

public class FacultyRepository {

    public static List<Faculty> getFaculty() {
        List<Faculty> list = new ArrayList<>();

        Faculty f1 = new Faculty("FAC101", "Dr. Sarah Connor", AcademicRank.PROFESSOR, AdministrativeTitle.HOD, 10, 3);
        f1.addQualifiedCourseCode("CS201");
        f1.addQualifiedCourseCode("CS208L");
        list.add(f1);

        Faculty f2 = new Faculty("FAC102", "Dr. Alan Turing", AcademicRank.ASSOCIATE_PROFESSOR, AdministrativeTitle.CHAIRMAN, 12, 3);
        f2.addQualifiedCourseCode("CS204");
        f2.addQualifiedCourseCode("CS206");
        list.add(f2);

        Faculty f3 = new Faculty("FAC103", "Prof. Grace Hopper", AcademicRank.PROFESSOR, AdministrativeTitle.DEAN, 8, 2);
        f3.addQualifiedCourseCode("CS205");
        list.add(f3);

        Faculty f4 = new Faculty("FAC104", "Dr. Linus Torvalds", AcademicRank.ASSISTANT_PROFESSOR, AdministrativeTitle.NONE, 16, 4);
        f4.addQualifiedCourseCode("CS202");
        f4.addQualifiedCourseCode("CS207L");
        list.add(f4);

        return list;
    }

    public static Faculty findByCourseCode(String courseCode) {
        for (Faculty faculty : getFaculty()) {
            if (faculty.canTeach(courseCode)) {
                return faculty;
            }
        }
        return null;
    }
}
