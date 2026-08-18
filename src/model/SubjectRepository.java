package model;

import java.util.ArrayList;
import java.util.List;

public class SubjectRepository {

    public static List<Subject> getSubjects() {

        List<Subject> list = new ArrayList<>();

        // Pure Theory Courses
        list.add(new Subject("CS201", "Aritifical Intelligence", 3, 0, CourseType.THEORY));
        list.add(new Subject("CS204", "Distributed Computing", 2, 0, CourseType.THEORY));
        list.add(new Subject("CS205", "Math", 3, 0, CourseType.THEORY));
        list.add(new Subject("CS206", "Introduction to Cyber Security", 3, 0, CourseType.THEORY));

        // Combined Theory & Practical Courses (4 Total Credits)
        list.add(new Subject("CS202", "Software in IoT", 3, 1, CourseType.THEORY_PRACTICAL)); // 3 cred theory & 1 cred

        // Pure Practical Courses
        list.add(new Subject("CS207L", "Web Technology Lab", 0, 2, CourseType.PRACTICAL)); // 2 practical credits (3
                                                                                           // labs + 1
                                                                                           // tutorial)
        list.add(new Subject("CS208L", "AI Lab", 0, 1, CourseType.PRACTICAL)); // 1 practical credit (2 labs + 1 //
                                                                               // tutorial)

        return list;
    }

}