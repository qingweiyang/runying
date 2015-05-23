 
$(document).ready(function() {
    $("#nav-left").load("../plugins/nav_left.html");
    $("#nav-top").load("../plugins/nav_top.html");
    $("#footer").load("../plugins/footer.html");
    alert("cookie="+getCookie("username"));
});

function loadPdtName() {
	$.post(
		"test.do",
		"name=test",
		function(data) {

		});
}