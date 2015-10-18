<%@include file="Header.jsp"%>
<title>Modify Employee</title>
<%
	String PageNo = (String) request.getAttribute("pageNo");
	String PageName = (String) request.getAttribute("pageName");
	User user = (User) request.getAttribute("loginData");
%>
<center>
	<div style="margin: 80px 0 0 0">
		<form action="EmployeeService" method="post">
			<table cellpadding="10">
				<tr>
					<td>Enter FirtsName</td>
					<td><input class="input" type="text" name="firstName"
						value="<%=user.getArrayList().get(0).getFirstName()%>"></td>
				</tr>
				<tr>
					<td>Enter LastName</td>
					<td><input class="input" type="text" name="LastName"
						value="<%=user.getArrayList().get(0).getLastName()%>"></td>
				</tr>
				<tr>
					<td>Enter ContactNo</td>
					<td><input class="input" type="text" name="contactNO"
						value="<%=user.getArrayList().get(0).getContactNo()%>"></td>
				</tr>
				<tr>
					<td>Enter EmailID</td>
					<td><input class="input" type="text" name="emailID"
						value="<%=user.getArrayList().get(0).getEmailID()%>"></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="hidden" name="password"
						value="<%=user.getArrayList().get(0).getPassword()%>"></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" class="btn" value="Edit"
						name="label" /></td>
				</tr>
			</table>
			<input type="hidden" name="id"
				value="<%=user.getArrayList().get(0).getId()%>"> <input
				type="hidden" value="<%=PageNo%>" name="pageNo" id="pageNo">
			<input type="hidden" value="<%=PageName%>" name="pageName"
				id="pageName">

		</form>
	</div>
</center>
</body>
</html>