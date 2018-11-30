package db;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class HibernateGetter {

    private static Session session;
    static Transaction tx = null;

    public static Session init(){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        return session = sessionFactory.openSession();
    }
    
	public static Artist getArtist(int id) {
		return (Artist) session.get(Artist.class, id);

	}
	
	public static Art_Object getArt_Object(int id) {
		return (Art_Object) session.get(Art_Object.class, id);
	}

    public static Culture_info getCulture_info(int id) {
        return (Culture_info) session.get(Culture_info.class, id);
    }

    public static Department getDepartment(int id) {
        return (Department) session.get(Department.class, id);
    }

    public static Exhibitions getExhibition(int id) {
        return (Exhibitions) session.get(Exhibitions.class, id);
    }

    public static Room getRoom(int id) {
        return (Room) session.get(Room.class, id);
    }

    public static Specs getSpec(int id) {
        return (Specs) session.get(Specs.class, id);
    }
}
