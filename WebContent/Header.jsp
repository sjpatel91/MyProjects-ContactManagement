<%@page import="pojo.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<script type="text/javascript" src="ajax.js"></script>
<link href="style.css" rel="stylesheet" type="text/css" />
<body>
	<%
		session = request.getSession();
		User loggedUser = null;

		if (session.getAttribute("LoggedUser") != null) {
			loggedUser = (User) session.getAttribute("LoggedUser");
	%>

	<table align="right">
		<tr align="right">
			<td>Wel-Come:</td>
			<td><%=loggedUser.getFirstName()%></td>
			<form action="LoginService">
				<td><input
					style="width: 75px; height: 25px; border-radius: 10px; font-size: 13px;"
					type="submit" value="Log Out" /></td>
			</form>
		</tr>
	</table>
	<%
		} else {
			response.sendRedirect("index.jsp");
		}
	%>


	<br />
	<br />