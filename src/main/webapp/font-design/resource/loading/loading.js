function loading() {
	//$('#myModal').modal('show');
	if($("body").find("#loadingModal").size() < 1) {
		$("body").append('<div class="modal fade" id="loadingModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">'+
				'<div class="spinner">'+
				'<div class="bounce1"></div><div class="bounce2"></div><div class="bounce3"></div>'+
				'</div>');
	}
	
	$('#loadingModal').modal({backdrop: 'static', keyboard: false});
}

function loadingEnd() {
	$('#loadingModal').modal('hide');
	$("body").removeAttr("style");
}