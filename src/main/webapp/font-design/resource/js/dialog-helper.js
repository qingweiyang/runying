function showWarningDialog(mes) {
	var modal = '<div class="modal" id="warningModal" tabindex="-1" role="dialog" aria-labelledby="warningModalLabel">'+
			      '<div class="modal-dialog modal-sm" role="document">'+
			        '<div class="modal-content">'+
			          '<div class="modal-header">'+
			            '<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>'+
			            '<h4 class="modal-title text-danger" id="warningModalLabel">'+
			              '<i class="fa fa-warning"></i> 警告'+
			            '</h4>'+
			          '</div>'+
			          '<div id="warningModalContent" class="modal-body text-warning" style="padding-left: 15%;">'+
			          '</div>'+
			          '<div class="modal-footer">'+
			            '<button type="button" class="btn btn-default btn-danger" data-dismiss="modal">确认</button>'+
			          '</div>'+
			        '</div>'+
			      '</div>'+
			    '</div>';

	if($("body").find("#warningModal").size() < 1) {
		$("body").append(modal);
	}
    $("#warningModalContent").html("<strong>"+mes+"</strong>");
    
    $("#warningModal").modal("show");
}
