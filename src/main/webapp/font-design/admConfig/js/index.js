$(document).ready(function(){
	//getCurUser();
});

function logout() {
	$.get(
		"logout.do",
		function(data) {
			if(data.status == 1)
				location.href = "../log/login.html";
		}); 
}

function getCurUser() {
	$.get(
		"getCurUser.do",
		function(data) {
			$("#username").text(data.username);
		});
}

function loadList() {
	$(".content").empty();
	$(".content").load("../admConfig/list.html");
}

function loadAdd() {
	$(".content").empty();
	$(".content").load("../admConfig/add.html");
}
