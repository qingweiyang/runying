$(document).ready(function() {

});

function login() {
	var param = {};
	param.username = $("#username").val();
	param.password = $("#password").val();
	setCookie("username", username, 1);
	
	$.post(
		"login.do",
		param,
		function(data) {
			location.href = "../warehouse_main/warehouse_main.html";
		});

}
