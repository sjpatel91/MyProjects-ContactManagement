<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="style.css" rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>
	<center>
		<%!String error = "";%>
		<%
			if (request.getAttribute("error") != null) {
				error = (String) request.getAttribute("error");
			}
		%>
		<div class="login">
			<form action="LoginService" method="post">
				<table cellpadding="10">
					<tr>
						<td colspan="2" class="error"><%=error%></td>
					</tr>
					<tr>
						<td>Enter UserName</td>
						<td><input class="input" type="text" name="uname"></td>
					</tr>
					<tr>
						<td>Enter Password</td>
						<td><input class="input" type="password" name="password"></td>
					</tr>
					<tr>
						<td></td>
						<td><input class="button" type="submit" value="Login"></td>
					</tr>
				</table>
			</form>
		</div>
	</center>
</body>
</html>