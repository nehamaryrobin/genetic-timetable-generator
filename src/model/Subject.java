package model;

public class Subject {

    private final String code;
    private final String name;
    private final int credits;
    private final SubjectType type;

    public Subject(String code,
            String name,
            int credits,
            SubjectType type) {

        this.code = code;
        this.name = name;
        this.credits = credits;
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getCredits() {
        return credits;
    }

    public SubjectType getType() {
        return type;
    }

    public boolean isLab() {
        return type == SubjectType.LAB;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (other == null || getClass() != other.getClass())
            return false;
        Subject subject = (Subject) other;
        return java.util.Objects.equals(code, subject.code);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(code);
    }

    @Override
    public String toString() {
        return code;
    }
}