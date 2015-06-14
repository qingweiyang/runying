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

function watchWarehouse() {
	$(".content").remove();
}

function loadList() {
	alert("nice");
	$(".content").empty();
	$(".content").load("../admConfig/list.html");
}

function loadWarehouseCurrent() {
	$(".content").empty();
	$(".content").load("../warehouse_check/warehouse_current.html");
}

function loadOrdersIn() {
	$(".content").empty();
	$(".content").load("../orders/orders_in.html");
}

function loadAllOrders() {
	$(".content").empty();
	$(".content").load("../orders/orders_all.html");
}

function loadProcessIn() {
	$(".content").empty();
	$(".content").load("../process/process_in.html");
}

function loadProcessCheck() {
	$(".content").empty();
	$(".content").load("../process/process_check.html");
}

function loadWarehouseInOut() {
	location.href = "../warehouse/main.html";
}