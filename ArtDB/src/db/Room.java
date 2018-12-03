package db;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

@Entity
@Indexed
public class Room {
    @Id private int Room_id;
    @Field private String Room_name;

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
