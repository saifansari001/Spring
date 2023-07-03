<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Ecommerce Product Utitily </title>
</head>
<body>
<h2>Product Management Dashboard Login</h2>
	<div>
		<% if (session.getAttribute("error")!=null){%>
		<h2><%=session.getAttribute("error")%></h2>
		<%} 
		session.removeAttribute("error");
		%>
		<%
			if(session.getAttribute("id")!=null)
			{
				response.sendRedirect("View");
			}
		%>
		
		<form action="Login" method="POST">
			<input type="text" name="username" placeholder="username">
			<input type="password" name="password" placeholder="password" >
			<button type="submit">Login</button>
			<a href="ForgotPassword.html">Forget your password?</a>
		</form>
	</div>
</body>
</html>