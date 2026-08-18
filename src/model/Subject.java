package model;

import java.util.Objects;

public class Subject {

    private final String code;
    private final String name;
    private final int theoryCredits;
    private final int practicalCredits;
    private final CourseType courseType;
    private Faculty assignedFaculty;

    public Subject(String code, String name, int theoryCredits, int practicalCredits, CourseType courseType) {
        this(code, name, theoryCredits, practicalCredits, courseType, null);
    }

    public Subject(String code, String name, int theoryCredits, int practicalCredits, CourseType courseType, Faculty assignedFaculty) {
        this.code = code;
        this.name = name;
        this.theoryCredits = theoryCredits;
        this.practicalCredits = practicalCredits;
        this.courseType = courseType;
        this.assignedFaculty = assignedFaculty;
    }

    public Faculty getAssignedFaculty() {
        return assignedFaculty;
    }

    public void setAssignedFaculty(Faculty assignedFaculty) {
        this.assignedFaculty = assignedFaculty;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getTheoryCredits() {
        return theoryCredits;
    }

    public int getPracticalCredits() {
        return practicalCredits;
    }

    public int getTotalCredits() {
        return theoryCredits + practicalCredits;
    }

    public CourseType getCourseType() {
        return courseType;
    }

    public boolean hasTheory() {
        return theoryCredits > 0;
    }

    public boolean hasPractical() {
        return practicalCredits > 0;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (other == null || getClass() != other.getClass())
            return false;

        Subject subject = (Subject) other;
        return Objects.equals(code, subject.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    @Override
    public String toString() {
        return code;
    }
}
