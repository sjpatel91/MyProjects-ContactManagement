<%@include file="Header.jsp"%>
<title>Change Password</title>
<script type="text/javascript">
	function back() {
		window.location = "employeeDashboard.jsp"
	}
	function changePassword1() {
		var password = document.getElementById("password").value;
		var newPassword = document.getElementById("newPassword").value;
		var confirmPassword = document.getElementById("confirmPassword").value;

		if (password.trim() == "") {
			document.getElementById("error").innerHTML = "Provide Password";
		} else if (newPassword.trim() == "") {
			document.getElementById("error").innerHTML = "Provide New Password";
		} else if (confirmPassword.trim() == "") {
			document.getElementById("error").innerHTML = "Provide Confirm Password";
		} else if (newPassword != confirmPassword) {
			document.getElementById("error").innerHTML = "Provide Correct Confirm Password";
		} else {
			document.changePassword.action = "UserService";
			document.changePassword.submit();
		}
	}
</script>

<%
	String Error = "";
	if (request.getAttribute("error") != null) {
		Error = (String) request.getAttribute("error");
	}
%>
<center>
	<div style="margin: 80px 0 0 0">
		<form name="changePassword" method="Post">
			<table cellpadding="10" id="changePassword">
				<tr>
					<td colspan="2" id="error" class="error"><%=Error%></td>
				</tr>
				<tr>
					<td>Enter Old Password</td>
					<td><input class="input" type="password" name="password" id="password">
					</td>
				</tr>
				<tr>
					<td>Enter New Password</td>
					<td><input class="input" type="password" name="newPassword" id="newPassword">
					</td>
				</tr>
				<tr>
					<td>Enter Confirm Password</td>
					<td><input class="input" type="password" name="confirmPassword"
						id="confirmPassword"></td>
				</tr>
				<tr>
					<td colspan="2"><input type="button" class="btn"
						style="width: 150px" value="ChangePassword"
						onclick="changePassword1()"></td>
				</tr>
			</table>
			<input type="hidden" name="id" value="<%=loggedUser.getId()%>">
			<input type="hidden" name="label" value="ChangePassword">
		</form>
	</div>
</center>
</body>
</html>