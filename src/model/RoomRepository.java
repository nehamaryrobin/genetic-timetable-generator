package model;

import java.util.ArrayList;
import java.util.List;

public class RoomRepository {

    public static List<Room> getRooms() {
        List<Room> list = new ArrayList<>();

        // Home Classrooms
        list.add(new Room("R301", "Room 301", RoomType.HOME_CLASSROOM, 60));
        list.add(new Room("R302", "Room 302", RoomType.HOME_CLASSROOM, 60));

        // Lecture Halls
        list.add(new Room("LH101", "Main Auditorium", RoomType.LECTURE_HALL, 180));

        // Lab Rooms
        list.add(new Room("LAB1", "Web Technology Lab", RoomType.LAB_ROOM, 40));
        list.add(new Room("LAB2", "AI & Data Science Lab", RoomType.LAB_ROOM, 40));

        return list;
    }

    public static Room findById(String id) {
        for (Room room : getRooms()) {
            if (room.getId().equalsIgnoreCase(id)) {
                return room;
            }
        }
        return null;
    }

    public static List<Room> findByType(RoomType type) {
        List<Room> result = new ArrayList<>();
        for (Room room : getRooms()) {
            if (room.getRoomType() == type) {
                result.add(room);
            }
        }
        return result;
    }
}
