<%@page import="java.util.List"%>
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
<div>
	<%
			if(session.getAttribute("id")==null)
			{
				response.sendRedirect("index.jsp");
			}
		%>
	<div>
	<h2>Product Management Tool</h2>
	</div>
	<div>
	<a href="Logout">Logout</a>
	<% if (session.getAttribute("error")!=null){%>
		<h2><%=session.getAttribute("error")%></h2>
		<%} 
		session.removeAttribute("error");
		%>
	<h4> Please enter product details to add to stock</h4>
	<form action="AddProduct" method="post" enctype="multipart/form-data">
		<div>
		<label for="Title">Title</label>
		<input name="Title" id="Title" type="text">
		</div>
		<div>
		<label for="Quantity">Quantity</label>
		<input name="Quantity" id="Quantity" type="text">
		</div>
		<div>
		<label for="Size">Size</label>
		<input name="Size" id="Size" type="text">
		</div>
		<div>
		<label for="Image">Image</label>
		<input name="Image" id="Image" type="file">
		</div>
		<button type="submit">Add</button>
	</form>
	</div>
	<div>
	<table>
	<tr>
		<th>S.No</th>
		<th>Title</th>
		<th>Quantity</th>
		<th>Size</th>
		<th>Image</th>
		<th>Action</th>
	</tr>
	
	<% List<Product> products = (List<Product>)request.getAttribute("products");
		for(Product p:products) {
	%>
	<tr>
		<td><%=p.getId() %></td>
		<td><%=p.getTitle() %></td>
		<td><%=p.getQuantity() %></td>
		<td><%=p.getSize() %></td>
		<td><img src="data:image/jpeg;base64,<%=p.getImage() %>"></td>
		<td><a href="EditProduct?product_id=<%=p.getId() %>">Edit</a>
			<a href="RemoveProduct?product_id=<%=p.getId() %>">Delete</a>
		</td>
		
	</tr>
	<%} %>
	
	</table>
	</div>
</div>
</body>
</html>