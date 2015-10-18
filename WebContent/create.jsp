<%@include file="Header.jsp"%>
<title>Create Employee</title>
<center>
	<div style="margin: 80px 0 0 0">
		<form action="EmployeeService" method="post">
			<table cellpadding="10">
				<tr>
					<td>Enter FirtsName</td>
					<td><input class="input" type="text" name="firstName"></td>
				</tr>
				<tr>
					<td>Enter LastName</td>
					<td><input class="input" type="text" name="LastName"></td>
				</tr>
				<tr>
					<td>Enter ContactNo</td>
					<td><input class="input" type="text" name="contactNO"></td>
				</tr>
				<tr>
					<td>Enter EmailID</td>
					<td><input class="input" type="text" name="emailID"></td>
				</tr>
				<tr>
					<td>Enter Password</td>
					<td><input class="input" type="password" name="password"></td>
				</tr>
				<tr>

					<td colspan="2"><input class="btn" type="submit"
						value="Insert" name="label" /></td>
				</tr>
			</table>
			<input type="hidden" value="0" name="pageNo" id="pageNo"> <input
				type="hidden" value="1" name="id"> <input type="hidden"
				value="create.jsp" name="pageNo" id="pageName">
		</form>
	</div>
</center>
</body>
</html>