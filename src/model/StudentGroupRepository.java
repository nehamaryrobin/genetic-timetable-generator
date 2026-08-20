package model;

import java.util.ArrayList;
import java.util.List;

public class StudentGroupRepository {

    public static List<StudentGroup> getStudentGroups() {
        List<StudentGroup> list = new ArrayList<>();

        Room homeA = RoomRepository.findById("R301");
        Room homeB = RoomRepository.findById("R302");

        list.add(new StudentGroup("SG-CSE-A", "CSE Section A", 55, homeA));
        list.add(new StudentGroup("SG-CSE-B", "CSE Section B", 52, homeB));

        return list;
    }

    public static StudentGroup findById(String id) {
        for (StudentGroup group : getStudentGroups()) {
            if (group.getId().equalsIgnoreCase(id)) {
                return group;
            }
        }
        return null;
    }
}
