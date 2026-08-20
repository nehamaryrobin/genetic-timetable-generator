package model;

public enum RoomType {
    HOME_CLASSROOM("Home Classroom"),
    LECTURE_HALL("Lecture Hall"),
    LAB_ROOM("Lab Room");

    private final String displayName;

    RoomType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
