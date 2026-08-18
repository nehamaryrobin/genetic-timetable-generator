package model;

import java.util.ArrayList;
import java.util.List;

public class SubjectRepository {

    public static List<Subject> getSubjects() {

        List<Subject> list = new ArrayList<>();

        // Pure Theory Courses
        list.add(createSubject("CS201", "Aritifical Intelligence", 3, 0, CourseType.THEORY));
        list.add(createSubject("CS204", "Distributed Computing", 2, 0, CourseType.THEORY));
        list.add(createSubject("CS205", "Math", 3, 0, CourseType.THEORY));
        list.add(createSubject("CS206", "Introduction to Cyber Security", 3, 0, CourseType.THEORY));

        // Combined Theory & Practical Courses
        list.add(createSubject("CS202", "Software in IoT", 3, 1, CourseType.THEORY_PRACTICAL));

        // Pure Practical Courses
        list.add(createSubject("CS207L", "Web Technology Lab", 0, 2, CourseType.PRACTICAL));
        list.add(createSubject("CS208L", "AI Lab", 0, 2, CourseType.PRACTICAL));

        return list;
    }

    private static Subject createSubject(String code, String name, int theory, int practical, CourseType type) {
        Faculty faculty = FacultyRepository.findByCourseCode(code);
        return new Subject(code, name, theory, practical, type, faculty);
    }
}