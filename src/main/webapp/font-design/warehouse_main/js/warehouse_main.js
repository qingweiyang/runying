$(document).ready(function(){
	getCurUser();
});

// load main page by mapping index to specific html file
// 0 -- ./warehouse_in/product_in.html
function loadMain(index) {
	$("#main-page").remove();
	switch (index) {
		case 0 :
			$("#main-page").load("./warehouse_in/product_in.html");
			break;
		default :
			$("#main-page").load("./alert_page/not_found.html");
	}
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