package db;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import javax.servlet.*;
import java.io.*;
import java.util.*;

@WebServlet("/DetailedObject")

public class DetailedObject extends HttpServlet{
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        Enumeration<String> paramNames = request.getParameterNames();
        String object_id = request.getParameter("object_id");
        
        if(paramNames.hasMoreElements()){
        	out.append("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " + "Transitional//EN\">\n" + "<HTML>\n"
                    + "<HEAD>\n" + "<TITLE>Search Results</TITLE>\n");
        	out.append("<button onclick=\"goBack()\">Go Back</button>\r\n" + 
        			"\r\n" + 
        			"<script>\r\n" + 
        			"function goBack() {\r\n" + 
        			"    window.history.back();\r\n" + 
        			"}\r\n" + 
        			"</script>\r\n");
        	ResultObject result = new ResultObject(Integer.parseInt(object_id));
        	out.append(result.fullInfo());
        }
    }   
}
