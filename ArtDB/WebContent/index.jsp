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
		Search string: <label> <input type="text" name="searchString"></label>
		Table: <label><input type="text" name="searchTable"></label>
		Column: <label><input type="text" name="searchColumn"></label>
		<br> <input type="Submit" value="Submit Search">
	</form>

	<%	HibernateGetter.init();
		String search = request.getParameter("searchString");
		if(search != null && !search.trim().equals("")){
		    System.out.println(search);
		    
		    searchResult result = new searchResult();
		    result.doGet(request, response);
        }
		HibernateGetter.closeSession();
    %>
</body>
</html>