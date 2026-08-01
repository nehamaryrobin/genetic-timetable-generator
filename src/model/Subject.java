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
    public String toString() {
        return code;
    }
}