
$(document).ready(function() {

});

function login() {
	var param = {};
	param.username = $("#username").val();
	param.password = $("#password").val();
	
	loading();
	
	$.post(
		"login.do",
		param,
		function(data) {
			loadingEnd();
			if(data.status == 1) {
				location.href = "../warehouse_main/warehouse_main.html";
			} else {
				$("#username-suf-des").text("输入错误");
				$("#password-suf-des").text("输入错误");
			}
		});

}
