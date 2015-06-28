function loading() {
	//$('#myModal').modal('show');
	$("body").append('<div class="modal fade" id="loadingModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"><div class="loadbg col-md-offset-5"><div class="circle1 circle"></div><div class="circle2 circle"></div><div class="circle3 circle"></div></div>');

	$('#loadingModal').modal({backdrop: 'static', keyboard: false});
}

function loadingEnd() {
	$('#loadingModal').modal('hide');
}