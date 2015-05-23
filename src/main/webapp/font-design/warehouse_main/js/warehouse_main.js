$(document).ready(function(){
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