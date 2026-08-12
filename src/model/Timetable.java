package model;

import java.util.ArrayList;
import java.util.List;

@Deprecated
public class Timetable {

    private final List<Placement> placements;

    @Deprecated
    public Timetable() {
        placements = new ArrayList<>();
    }

    @Deprecated
    public void addPlacement(Placement placement) {
        placements.add(placement);
    }

    @Deprecated
    public List<Placement> getPlacements() {
        return placements;
    }

}