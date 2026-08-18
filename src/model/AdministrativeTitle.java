package model;

public enum AdministrativeTitle {
    DEAN("Dean"),
    HOD("HOD"),
    CHAIRMAN("Chairman"),
    NONE("None");

    private final String displayName;

    AdministrativeTitle(String displayName) {
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
