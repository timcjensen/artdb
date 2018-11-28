package db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class test {
	
	static Session session;
	public static void main(String[] args) {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		session = sessionFactory.getCurrentSession();
		
		Transaction tx = session.beginTransaction();

		Artist artist = HibernateGetter.getArtist(1);
		Art_Object art = HibernateGetter.getArt_Object(44);
		System.out.println(artist.getArtist_name());
		System.out.println(art.getTitle());
		
		tx.commit();
		sessionFactory.close();
	}
}
