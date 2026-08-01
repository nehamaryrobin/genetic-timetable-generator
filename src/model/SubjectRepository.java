package model;

import java.util.ArrayList;
import java.util.List;

public class SubjectRepository {

    public static List<Subject> getSubjects() {

        List<Subject> list = new ArrayList<>();

        list.add(new Subject("CS201", "DSA", 3, SubjectType.LECTURE));
        list.add(new Subject("CS202", "DBMS", 3, SubjectType.LECTURE));
        list.add(new Subject("CS203", "Java", 3, SubjectType.LECTURE));
        list.add(new Subject("CS204", "Software Engineering", 2, SubjectType.LECTURE));
        list.add(new Subject("CS205", "Discrete Math", 2, SubjectType.LECTURE));
        list.add(new Subject("CS206", "English", 1, SubjectType.LECTURE));
        list.add(new Subject("CS207L", "Java Lab", 2, SubjectType.LAB));
        list.add(new Subject("CS208L", "DBMS Lab", 2, SubjectType.LAB));

        return list;
    }

}