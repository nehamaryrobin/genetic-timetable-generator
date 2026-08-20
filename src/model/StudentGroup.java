package model;

import java.util.Objects;

public class StudentGroup {

    private final String id;
    private final String name;
    private final int studentCount;
    private final Room homeClassroom;

    public StudentGroup(String id, String name, int studentCount, Room homeClassroom) {
        this.id = id;
        this.name = name;
        this.studentCount = studentCount;
        this.homeClassroom = homeClassroom;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getStudentCount() {
        return studentCount;
    }

    public Room getHomeClassroom() {
        return homeClassroom;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (other == null || getClass() != other.getClass())
            return false;
        StudentGroup group = (StudentGroup) other;
        return Objects.equals(id, group.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("%s (Students: %d, Home: %s)", name, studentCount, homeClassroom != null ? homeClassroom.getName() : "None");
    }
}
