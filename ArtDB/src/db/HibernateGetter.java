package db;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class HibernateGetter {

    private static Session session;
    static Transaction tx = null;
    
    public static List<?> searchResult(Class<?> type, String matching, String field) {
		System.out.println("Initializing search:");
		
		FullTextSession fullTextSession = Search.getFullTextSession(session);
		
		try {
			fullTextSession.createIndexer().startAndWait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		QueryBuilder builder = fullTextSession.getSearchFactory()
				.buildQueryBuilder()
				.forEntity(type)
				.get();
		
		org.apache.lucene.search.Query query =
				builder.keyword().onField(field).matching(matching).createQuery();
		
		org.hibernate.search.FullTextQuery fullTextQuery = fullTextSession.createFullTextQuery(query, type);
		
		List<?> result = fullTextQuery.list();
		int resultCount = result.size();
		
		System.out.println(resultCount);
				
		return result;
    }

    public static Session init(){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		System.out.println("Session init");
        return session = sessionFactory.openSession();
    }
    
    public static Session getSession() {
    	return session;
    }
    
    public static boolean closeSession() {
    	if(session.isOpen()) {
    		session.close();
    		return true;
    	}    	
    	else
    		return false;
    }
    
	public static Artist getArtist(int id) {
		return session.get(Artist.class, id);

	}
	
	public static Art_Object getArt_Object(int id) {
		return session.get(Art_Object.class, id);
	}

    public static Culture_info getCulture_info(int id) {
        return session.get(Culture_info.class, id);
    }

    public static Department getDepartment(int id) {
        return session.get(Department.class, id);
    }

    public static Exhibitions getExhibition(int id) {
        return session.get(Exhibitions.class, id);
    }

    public static Room getRoom(int id) {
        return session.get(Room.class, id);
    }

    public static Specs getSpec(int id) {
        return session.get(Specs.class, id);
    }
}
