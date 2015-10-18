<%@include file="Header.jsp"%>
<title>Employee</title>
<script type="text/javascript">
	function displayHideShow() {
		window.location = "changePassword.jsp"
	}
</script>
</head>

<%
	session = request.getSession();
	User user = (User) session.getAttribute("LoggedUser");
	User user1 = (User) request.getAttribute("loginData");
%>
<center>
	<div style="margin: 80px 0 0 0">
		<form action="UserService" method="post">
			<table id="myProfile" cellpadding="10">
				<tr>
					<td>Enter FirtsName</td>
					<td><input class="input" type="text" name="firstName"
						value="<%=user1.getArrayList().get(0).getFirstName()%>"></td>
				</tr>
				<tr>
					<td>Enter LastName</td>
					<td><input class="input" type="text" name="LastName"
						value="<%=user1.getArrayList().get(0).getLastName()%>"></td>
				</tr>
				<tr>
					<td>Enter ContactNo</td>
					<td><input class="input" type="text" name="contactNO"
						value="<%=user1.getArrayList().get(0).getContactNo()%>"></td>
				</tr>
				<tr>
					<td>Enter EmailID</td>
					<td><input class="input" type="text" name="emailID"
						value="<%=user1.getArrayList().get(0).getEmailID()%>"></td>
				</tr>
				<tr>
					<td></td>
					<td><input class="input" type="hidden" name="password"
						value="<%=user1.getArrayList().get(0).getPassword()%>"></td>
				</tr>
				<tr>

					<td><input class="btn" type="submit" value="Profile"
						name="label" /></td>
					<td><input class="btn" type="button" style="width: 150px"
						value="Change Password" onclick="displayHideShow()" /></td>
				</tr>
			</table>
			<input type="hidden" name="id" value="<%=user.getId()%>">
		</form>
	</div>
</center>
</body>
</html>