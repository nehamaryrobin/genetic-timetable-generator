package model;

import java.util.ArrayList;
import java.util.List;

//new : Placement[][] -> List<Placement>
public class Timetable {

    private final List<Placement> placements;

    public Timetable() {
        placements = new ArrayList<>();
    }

    public void addPlacement(Placement placement) {
        placements.add(placement);
    }

    public List<Placement> getPlacements() {
        return placements;
    }

}