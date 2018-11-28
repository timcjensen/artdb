package db;

public class HibernateGetter extends test {

	static Artist getArtist(int id) {
		return (Artist) session.get(Artist.class, id);
	}
	
	static Art_Object getArt_Object(int id) {
		return (Art_Object) session.get(Art_Object.class, id);
	}

    static Culture_info getCulture_info(int id) {
        return (Culture_info) session.get(Culture_info.class, id);
    }

    static Department getDepartment(int id) {
        return (Department) session.get(Department.class, id);
    }

    static Exhibitions getExhibition(int id) {
        return (Exhibitions) session.get(Exhibitions.class, id);
    }

    static Room getRoom(int id) {
        return (Room) session.get(Room.class, id);
    }

    static Specs getSpec(int id) {
        return (Specs) session.get(Specs.class, id);
    }
}
