package db;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import org.hibernate.query.Query;

import javax.servlet.*;
import java.io.*;
import java.util.*;

@WebServlet("/SearchResults")

public class ObjectSearchResult extends HttpServlet{
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
        	
        	out.append("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " + "Transitional//EN\">\n" + "<HTML>\n"
                    + "<HEAD>\n" + "<TITLE>Search Results</TITLE>\n");
        	
        	out.append("<style>" +
        	"table {" +
        	    "font-family: arial, sans-serif;" +
        	    "border-collapse: collapse;" +
        	    "width: 100%;}" +
        	"td, th {" +
        	    "border: 1px solid #dddddd;" +
        	    "text-align: left;" +
        	    "padding: 8px;}" +
        	"th {" +
        	    "cursor: pointer;}" +
        	"tr:nth-child(even) {" +
        	    "background-color: #dddddd;}" +
        	"</style>");
        	
        	List<?> resultList = HibernateGetter.searchResult(table, searchString, searchColumn);
        	
        	ListIterator<?> itr = resultList.listIterator();
        	
        	query = objectMerge(table);
        	
        	out.append("<table id=\"result\">\n");
        	out.append("<thread>\n");
        	out.append("<tr>\n");
        	out.append("<th>More info</th>\n");
        	out.append("<th onClick=\"sortTable(0)\">Title</th>\n");
        	out.append("<th onClick=\"sortTable(1)\">Description</th>\n");
        	out.append("<th onClick=\"sortTable(2)\">Artist</th>\n");
        	out.append("<th onClick=\"sortTable(3)\">Country</th>\n");
        	out.append("<th onClick=\"sortTable(4)\">Department</th>\n");
        	out.append("<th onClick=\"sortTable(5)\">Exhibition</th>\n");
        	out.append("<th onClick=\"sortTable(6)\">Room</th>\n");
        	out.append("<th onClick=\"sortTable(7)\">Dimensions</th>\n");
        	out.append("<th>Image</th\n>");
        	out.append("</tr>\n");
        	out.append("</thread>\n");
        	out.append("<tbody>\n");
        	while(itr.hasNext()) {
        		String id = generateID(table, itr);
        		query.setParameter("id", id);
        		List<?> list = query.list();
        		ListIterator<?> objectItr = list.listIterator();
                while(objectItr.hasNext()) {
                	out.append("<tr>\n");
                	ResultObject result = new ResultObject(((Art_Object) objectItr.next()).getObject_id());
                	out.append(result.toString());
                	out.append("</tr>\n");
                }
        	}
        	out.append("</tbody>\n");
        	out.append("</table>\n");
        	out.append("<script src=\"object.js\"></script>");
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
