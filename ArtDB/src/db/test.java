package db;

import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.hibernate.Session;

import java.util.List;
import java.util.ListIterator;

public class test {
	public static void main(String[] args) {
		String queryString = "The White Bridge";
		System.out.println("Initializing session:");
		Session session = HibernateGetter.init();	
		System.out.println("Session init");
		
		FullTextSession fullTextSession = Search.getFullTextSession(session);
		System.out.println("FullTextSession fullTextSession = Search.getFullTextSession(session)");
		
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
				builder.keyword().onField("Title").matching(queryString).createQuery();
		
		org.hibernate.search.FullTextQuery fullTextQuery = fullTextSession.createFullTextQuery(query, Art_Object.class);
		List<Art_Object> result = fullTextQuery.list();
		System.out.println("List<Art_Object> result = fullTextQuery.list();");
		
		System.out.println(result.size());
		
		ListIterator<Art_Object> itr = result.listIterator();
		while(itr.hasNext()) {
			System.out.println(itr.next().getTitle());
		}
		session.close();
	}
}
