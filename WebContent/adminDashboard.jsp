<%@include file="Header.jsp"%>
<script type="text/javascript">
	function pageLoad()
	{
		<%!String pageNo = null, pageName = null;%>
		<%pageNo = (String) request.getAttribute("pageNo");
			pageName = (String) request.getAttribute("pageName");

			if (pageName == null && pageNo == null) {%>
				var pageNo = document.getElementById("pageNo").value;
				var pageName = document.getElementById("pageName").value;
				grid(pageName,pageNo);
			<%} else {%>
				var pageName = '<%=pageName%>';
				var pageNo = '<%=pageNo%>
	';
		grid(pageName, pageNo);
<%}%>
	}
	function grid(args1, args2) {
		reqAjax("EmployeeService", "label=Grid" + "&pageName=" + args1
				+ "&pageNo=" + args2, gridCallback);
	}

	function gridCallback(data) {
		var divId = document.getElementById("grid");
		var mycars = new Array();
		mycars = data.split("|");
		var pageNo = document.getElementById("pageNo");
		var pageName = document.getElementById("pageName");
		pageNo.value = mycars[0];
		pageName.value = mycars[1];
		divId.innerHTML = mycars[2];
	}
	function edit(id, label) {
		var Id = document.getElementById("id");
		Id.value = id;
		var Label = document.getElementById("label");
		Label.value = label;
		document.general.action = "EmployeeService";
		document.general.submit();
	}
	function editCallback(data) {
		gridCallback(data);
	}
	function deleteData(id, pageName, pageNo) {
		var strconfirm = confirm("Are you sure you want to delete?", "Yes",
				"No");
		if (strconfirm == true) {
			reqAjax("EmployeeService", "label=Delete" + "&id=" + id
					+ "&pageName=" + pageName + "&pageNo=" + pageNo,
					deleteCallback);
		}
	}
	function deleteCallback(data) {
		var mycars = new Array();
		mycars = data.split("|");
		grid(mycars[0], mycars[1]);
		//pageLoad();
	}
	function activeDeactive(id, pageName, pageNo, flag) {
		if (flag == 1) {
			var strconfirm = confirm("Are you sure you want to Deactive?",
					"Yes", "No");
		} else {
			var strconfirm = confirm("Are you sure you want to Active?", "Yes",
					"No");
		}
		if (strconfirm == true) {
			reqAjax("EmployeeService", "label=activeDeactive" + "&id=" + id
					+ "&pageName=" + pageName + "&pageNo=" + pageNo + "&flag="
					+ flag, activeDeactiveCallback);
		}

	}
	function activeDeactiveCallback(data) {
		var mycars = new Array();
		mycars = data.split("|");
		grid(mycars[0], mycars[1]);
	}

	function CreateNew() {
		document.general.action = "create.jsp";
		document.general.submit();
	}
</script>
<title>Admin</title>

<style>
#grid td {
	width: 25%;
}
</style>
<body onload="pageLoad()">

	<div align="right" style="margin: 0 50px 100px 0">
		<form name="general" method="post">
			<input type="hidden" id="label" name="label"> <input
				type="hidden" id="id" name="id"> <input type="hidden"
				value="1" id="pageNo" name="pageNo"> <input type="hidden"
				value="adminDashboard.jsp" id="pageName" name="pageName">
		</form>
	</div>
	<center>
		
		<div id="grid"></div>
		<br />
		<br />
		<form action="EmployeeService" method="get">
		
		
			<!-- 	<input type="button" value="Export CVS" onclick="ExportCVS()" name="label"/> -->
			<input class="btn" type="submit" value="ExportCVS" name="label" />
			<input class="btn" type="button" value="Add Entry"
			onclick="CreateNew()"><br />
		<br />
		</form>

	</center>
</body>
</html>