<%@page import="com.dao.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
if(session.getAttribute("id")==null)
		{
			response.sendRedirect("index.jsp");
		}
	%>
<div>
<table>
<tr>
	<th>Title</th>
	<th>Quantity</th>
	<th>Size</th>
	<th>Image</th>
</tr>
<tr>
	<% 
	Product p = (Product) request.getAttribute("product");
	{%>
	<form action="EditProduct" method="post" enctype="multipart/form-data" >
	<input type="text" value="<%=p.getId() %>" name="id" hidden="true">
	<input type="text" value="<%=p.getTitle() %>"name="title">
	<input type="text" value="<%=p.getQuantity() %>" name="quantity">
	<input type="text" value="<%=p.getSize() %>" name="size">
	<img src="data:image/jpeg;base64,<%=p.getImage() %>">
	<input type="file" name="image">
	<button type="submit">Update</button>
	</form>
	
	<%} %>
</tr>
</table>
</div>

</body>
</html>