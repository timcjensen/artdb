<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ArtDB Search</title>
</head>
<body BGCOLOR="#FDF5E6">
	<%@ page import="java.io.*"%>
	<%@ page import="db.*"%>

	<h1 align="center">Search</h1>

	<form action="index.jsp" method="get">
		Search object by Object ID: <label>
		<input type="text" name="object">
	</label>Input<br>
		 <input type="Submit" value="Submit Order">
	</form>

	<%	HibernateGetter.init();
		String object_id = request.getParameter("object");
        Art_Object artObject;
		if(object_id != null && !object_id.trim().equals("")){
		    System.out.println(object_id);
		    artObject = HibernateGetter.getArt_Object(Integer.parseInt(object_id));
		    
		    searchResult search = new searchResult();
		    search.doGet(request, response);
        }
    %>
</body>
</html>