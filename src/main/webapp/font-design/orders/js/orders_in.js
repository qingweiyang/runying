
$(document).ready(function() {
	getProducts();
});

function getProducts() {
	
	$.get(
		"getProducts.do",
		function(data) {
			showWarningDialog(data);
		});

}
