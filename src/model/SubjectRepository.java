package model;

import java.util.ArrayList;
import java.util.List;

public class SubjectRepository {

    public static List<Subject> getSubjects() {

        List<Subject> list = new ArrayList<>();

        // Pure Theory Courses
        list.add(new Subject("CS201", "DSA", 3, 0, CourseType.THEORY));
        list.add(new Subject("CS204", "Software Engineering", 2, 0, CourseType.THEORY));
        list.add(new Subject("CS205", "Discrete Math", 2, 0, CourseType.THEORY));
        list.add(new Subject("CS206", "English", 1, 0, CourseType.THEORY));

        // Combined Theory & Practical Courses (4 Total Credits)
        list.add(new Subject("CS202", "DBMS", 3, 1, CourseType.THEORY_PRACTICAL)); // 3 cred theory & 1 cred practical
        list.add(new Subject("CS203", "Java", 2, 2, CourseType.THEORY_PRACTICAL)); // 2 cred theory & 2 cred practical

        // Pure Practical Courses
        list.add(new Subject("CS207L", "Java", 0, 2, CourseType.PRACTICAL)); // 2 practical credits (3 labs + 1 //
                                                                             // tutorial)
        list.add(new Subject("CS208L", "DBMS", 0, 1, CourseType.PRACTICAL)); // 1 practical credit (2 labs + 1 //
                                                                             // tutorial)

        return list;
    }

}