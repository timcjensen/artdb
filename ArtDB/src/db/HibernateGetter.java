package db;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;

import java.util.List;
import java.util.ListIterator;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class HibernateGetter {

    private static Session session;
    static Transaction tx = null;
    
    public static ResultObject[] searchResult(String object_title) {
		System.out.println("Initializing search:");
		Session session = HibernateGetter.init();	
		System.out.println("Session init");
		
		FullTextSession fullTextSession = Search.getFullTextSession(session);
		
		try {
			fullTextSession.createIndexer().startAndWait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		QueryBuilder builder = fullTextSession.getSearchFactory()
				.buildQueryBuilder()
				.forEntity(Art_Object.class)
				.get();
		
		org.apache.lucene.search.Query query =
				builder.keyword().onField("Title").matching(object_title).createQuery();
		
		org.hibernate.search.FullTextQuery fullTextQuery = fullTextSession.createFullTextQuery(query, Art_Object.class);
		List<Art_Object> result = fullTextQuery.list();
		int resultCount = result.size();
		ResultObject[] resultList = new ResultObject[resultCount];
		
		System.out.println(resultCount);
		
		ListIterator<Art_Object> itr = result.listIterator();
		
		for(int i = 0 ; i < resultCount; i++) {
			resultList[i] = new ResultObject(itr.next().getObject_id());
		}
		
		session.close();
		return resultList;
    }

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
