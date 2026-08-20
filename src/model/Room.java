package model;

import java.util.Objects;

public class Room {

    private final String id;
    private final String name;
    private final RoomType roomType;
    private final int capacity;

    public Room(String id, String name, RoomType roomType, int capacity) {
        this.id = id;
        this.name = name;
        this.roomType = roomType;
        this.capacity = capacity;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (other == null || getClass() != other.getClass())
            return false;
        Room room = (Room) other;
        return Objects.equals(id, room.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("%s [%s, Cap: %d]", name, roomType, capacity);
    }
}
