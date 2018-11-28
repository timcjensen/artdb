package db;

public class Room {
    private int Room_id;
    private String Room_name;

    public Room(int room_id, String room_name) {
        Room_id = room_id;
        Room_name = room_name;
    }

    public Room() {
    }

    public int getRoom_id() {
        return Room_id;
    }

    public void setRoom_id(int room_id) {
        Room_id = room_id;
    }

    public String getRoom_name() {
        return Room_name;
    }

    public void setRoom_name(String room_name) {
        Room_name = room_name;
    }
}
