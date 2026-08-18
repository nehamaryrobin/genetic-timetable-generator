package model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Faculty {

    private final String id;
    private final String name;
    private final AcademicRank rank;
    private final AdministrativeTitle administrativeTitle;
    private final int maxWeeklyLoad;
    private final int maxDailyLoad;
    private final Set<TimeSlot> blackoutSlots;
    private final Set<String> qualifiedCourseCodes;

    public Faculty(String id, String name, AcademicRank rank, AdministrativeTitle administrativeTitle,
                   int maxWeeklyLoad, int maxDailyLoad) {
        this.id = id;
        this.name = name;
        this.rank = rank;
        this.administrativeTitle = administrativeTitle;
        this.maxWeeklyLoad = maxWeeklyLoad;
        this.maxDailyLoad = maxDailyLoad;
        this.blackoutSlots = new HashSet<>();
        this.qualifiedCourseCodes = new HashSet<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public AcademicRank getRank() {
        return rank;
    }

    public AdministrativeTitle getAdministrativeTitle() {
        return administrativeTitle;
    }

    public int getMaxWeeklyLoad() {
        return maxWeeklyLoad;
    }

    public int getMaxDailyLoad() {
        return maxDailyLoad;
    }

    public Set<TimeSlot> getBlackoutSlots() {
        return blackoutSlots;
    }

    public void addBlackoutSlot(TimeSlot slot) {
        blackoutSlots.add(slot);
    }

    public boolean isBlackout(TimeSlot slot) {
        return blackoutSlots.contains(slot);
    }

    public Set<String> getQualifiedCourseCodes() {
        return qualifiedCourseCodes;
    }

    public void addQualifiedCourseCode(String courseCode) {
        qualifiedCourseCodes.add(courseCode);
    }

    public boolean canTeach(String courseCode) {
        return qualifiedCourseCodes.contains(courseCode);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (other == null || getClass() != other.getClass())
            return false;
        Faculty faculty = (Faculty) other;
        return Objects.equals(id, faculty.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("%s (%s, %s)", name, rank, administrativeTitle);
    }
}
