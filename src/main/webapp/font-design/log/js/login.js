
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
			if(data.status == 1) {
				setCookie("id", data.conts.id, 1);
				setCookie("username", data.conts.username, 1);
				location.href = "../warehouse_main/warehouse_main.html";
			}
		});

}
