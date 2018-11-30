package db;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import java.util.*;

@WebServlet("/SearchResults")

public class searchResult extends HttpServlet{
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        Enumeration<String> paramNames = request.getParameterNames();
        String[] params = request.getParameterValues("object");
        String title = params[0];

        if(paramNames.hasMoreElements()){
        	out.print("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " + "Transitional//EN\">\n" + "<HTML>\n"
                    + "<HEAD>\n" + "<TITLE>Search Results</TITLE>\n");
        	ResultObject[] results = HibernateGetter.searchResult(title);
        	
        	for(int i = 0 ; i < results.length ; i++) {
                out.println(results[i].getTitle() + "<br>");
        	}
            
        }
    }
}
