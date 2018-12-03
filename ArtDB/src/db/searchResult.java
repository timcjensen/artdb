package db;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import org.hibernate.query.Query;

import javax.servlet.*;
import java.io.*;
import java.util.*;

@WebServlet("/SearchResults")

public class searchResult extends HttpServlet{
	private Query<?> query;
    private static final long serialVersionUID = 1L;
    Class<?> table = null;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        Enumeration<String> paramNames = request.getParameterNames();
        String searchString = request.getParameter("searchString");
        System.out.println("Search string: " + searchString);
        try {
			table = Class.forName("db." + request.getParameter("searchTable"));
		} catch (ClassNotFoundException e) {
			System.out.println("!!! CLASS NOT FOUND !!!");
			e.printStackTrace();
		}
        System.out.println("Search table: " + table.getName());
        String searchColumn = request.getParameter("searchColumn");
        System.out.println("Search string: " + searchColumn);
        
        if(paramNames.hasMoreElements()){
        	
        	out.print("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " + "Transitional//EN\">\n" + "<HTML>\n"
                    + "<HEAD>\n" + "<TITLE>Search Results</TITLE>\n");
        	
        	out.print("<style>" +
        	"table {" +
        	    "font-family: arial, sans-serif;" +
        	    "border-collapse: collapse;" +
        	    "width: 100%;}" +
        	"td, th {" +
        	    "border: 1px solid #dddddd;" +
        	    "text-align: left;" +
        	    "padding: 8px;}" +
        	"tr:nth-child(even) {" +
        	    "background-color: #dddddd;}" +
        	"</style>");
        	
        	List<?> resultList = HibernateGetter.searchResult(table, searchString, searchColumn);
        	
        	ListIterator<?> itr = resultList.listIterator();
        	
        	query = objectMerge(table);
        	
        	out.print("<table id=\"result\">");
        	out.print("<thread>");
        	out.print("<tr>");
        	out.print("<th onClick=\"sortTable(0)\">Title</th>");
        	out.print("<th onClick=\"sortTable(1)\">Description</th>");
        	out.print("<th onClick=\"sortTable(2)\">Artist</th>");
        	out.print("<th onClick=\"sortTable(3)\">Department</th>");
        	out.print("<th onClick=\"sortTable(4)\">Exhibition</th>");
        	out.print("<th>Image</th>");
        	out.print("</tr>");
        	out.print("</thread>");
        	out.print("<tbody>");
        	while(itr.hasNext()) {
        		String id = generateID(table, itr);
        		query.setParameter("id", id);
        		List<?> list = query.list();
        		ListIterator<?> objectItr = list.listIterator();
                while(objectItr.hasNext()) {
                	out.print("<tr>");
                	ResultObject result = new ResultObject(((Art_Object) objectItr.next()).getObject_id());
                	out.print(result.toString());
                	out.print("</tr>");
                }
        	}
        	out.print("</tbody>");
        	out.print("</table>");
        	out.print("<script src=\"script.js\"></script>");
        }
    }
    
    private Query<?> objectMerge(Class<?> type){
    	String id;
    	if(type.equals(Art_Object.class)) {
    		id = "Object_id";
    	}
    	else if (type.equals(Artist.class)) {
    		id = "Artist_id";
    	}
    	else if (type.equals(Culture_info.class)) {
    		id = "Culture_id";
    	}
    	else if (type.equals(Department.class)) {
    		id = "Department_id";
    	}
    	else if(type.equals(Exhibitions.class)){
    		id = "Exhibition_id";
    	}
    	else if(type.equals(Room.class)) {
    		id = "Room_id";
    	}
    	else if(type.equals(Specs.class)) {
    		id = "Spec_id";
    	}
    	else {
    		id = "error";
    		System.out.println("ERROR: NULL objectMerge: " + type.getName());
    	}
    	Query<?> query = HibernateGetter.getSession().createQuery("from Art_Object where " + id + " = :id");
    	
    	return query;
    }
    
    private String generateID(Class<?> type, ListIterator<?> itr) {
    	String id;
    	if(type.equals(Art_Object.class)) {
    		id = Integer.toString(((Art_Object) itr.next()).getObject_id());
    	}
    	else if (type.equals(Artist.class)) {
    		id = Integer.toString(((Artist) itr.next()).getArtist_id());
    	}
    	else if (type.equals(Culture_info.class)) {
    		id = Integer.toString(((Culture_info) itr.next()).getCulture_id());
    	}
    	else if (type.equals(Department.class)) {
    		id = Integer.toString(((Department) itr.next()).getDepartment_id());
    	}
    	else if(type.equals(Exhibitions.class)){
    		id = Integer.toString(((Exhibitions) itr.next()).getExhibition_id());
    	}
    	else if(type.equals(Room.class)) {
    		id = Integer.toString(((Room) itr.next()).getRoom_id());
    	}
    	else if(type.equals(Specs.class)) {
    		id = Integer.toString(((Specs) itr.next()).getSpec_id());
    	}
    	else {
    		id = "error";
    		System.out.println("ERROR: NULL ID: " + type.getName());
    	}
    	return id;
    }
   
}
