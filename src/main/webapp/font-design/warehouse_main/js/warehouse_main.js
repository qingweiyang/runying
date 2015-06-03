$(document).ready(function(){
	getCurUser();
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
	$("#main-page").remove();
}

function loadProductIn() {
	$("#main-page").empty();
	$("#main-page").load("../warehouse_in/product_in.html");
}

function loadWarehouseCurrent() {
	$("#main-page").empty();
	$("#main-page").load("../warehouse_check/warehouse_current.html");
}

function loadOrdersIn() {
	$("#main-page").empty();
	$("#main-page").load("../orders/orders_in.html");
}

function loadAllOrders() {
	$("#main-page").empty();
	$("#main-page").load("../orders/orders_all.html");
}

function loadProcessIn() {
	$("#main-page").empty();
	$("#main-page").load("../process/process_in.html");
}

function loadProcessCheck() {
	$("#main-page").empty();
	$("#main-page").load("../process/process_check.html");
}