
$(document).ready(function() {

});

function login() {
	var param = {};
	param.username = $("#username").val();
	param.password = $("#password").val();
	
	$.post(
		"login.do",
		param,
		function(data) {
			if(data.status == 1) {
				location.href = "../warehouse_main/warehouse_main.html";
			}
		});

}
