$(document).ready(function(){
    $("#nav-left").load("../plugins/nav_left.html");
    $("#nav-top").load("../plugins/nav_top.html");
    $("#footer").load("../plugins/footer.html");
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