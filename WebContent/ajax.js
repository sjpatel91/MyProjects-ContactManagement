function GetXmlHttpObject(handler) {
	var objXmlHttp = null;
	if (navigator.userAgent.indexOf("Opera") >= 0) {
		alert("This example doesn't work in Opera");
		return;
	}
	if (navigator.userAgent.indexOf("MSIE") >= 0) {
		var strName = "Msxml2.XMLHTTP";
		if (navigator.appVersion.indexOf("MSIE 5.5") >= 0) {
			strName = "Microsoft.XMLHTTP";
		}
		try {
			objXmlHttp = new ActiveXObject(strName);
			objXmlHttp.onreadystatechange = handler;
			return objXmlHttp;
		}
		catch (e) {
			alert("Error. Scripting for ActiveX might be disabled");
			return;
		}
	}
	if (navigator.userAgent.indexOf("Mozilla") >= 0) {
		objXmlHttp = new XMLHttpRequest();
		objXmlHttp.onload = handler;
		objXmlHttp.onerror = handler;
		return objXmlHttp;
	}
}
var canICallreqAjax = true;
var ajaxCallBackTMP;
function reqAjax(url, queryString, ajaxCallBack) {
	if (canICallreqAjax) {
		ajaxCallBackTMP = ajaxCallBack;
		xmlHttp = GetXmlHttpObject(tempHandlerForDoNothing);
	//alert(url);
		xmlHttp.open("POST", url, true);
		xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		xmlHttp.send(queryString);
		canICallreqAjax = false;
		return true;
	} else {
		return false;
	}
}
function tempHandlerForDoNothing() {
	if (xmlHttp.readyState == 4) {
		if (xmlHttp.status == 200) {
			canICallreqAjax = true;
			ajaxCallBackTMP(xmlHttp.responseText);
		}
	}
}
NameValueCollection = function () {
	this.name = [];
	this.value = [];
	this.add = function (name, value) {
		this.name[this.name.length] = name;
		this.value[this.value.length] = value;
	};
	this.getQueryString = function () {
		var queryString = "";
		if (this.name.length == this.value.length) {
			for (var n = 0; n < this.name.length; n++) {
				if (n > 0) {
					queryString = queryString + "&";
				}
				if (this.name[n] == "action") {
					queryString = queryString + this.name[n] + "=" + (this.value[n]);
				} else {
					queryString = queryString + this.name[n] + "=" + encodeURIComponent(this.value[n]);
				}
			}
		}
		return queryString;
	};
};