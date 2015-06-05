$(document).ready(function(){
	getCurUser();
	loadWarehouse();
	
	//监听 缴纳保证金modal， modal出现前判断是否用户已申请过缴纳，且申请未通过
	$("#in-warehouse-modal").on("show.bs.modal", function (e) {
	  
	});
	
});

/*入库*/
function inWarehouseSubmit() {
	var param = {};
  	//获取输入值
  	var pay = $("#pdt-number").val();
  	if(isNaN(pay) || pay < 0) {
    	//输入不合法，要求为大于0的整数
    	alert("输入不合法，要求为大于0的整数");
    	return;
  	}
  	var product = {};
  	product.id = $("#pdt-id").text();
  	param.product = product;
  	param.number = pay;

  	$.ajax({
      type : "POST",
      contentType : 'application/json', 
      url : "inWarehouse.do",
      data : JSON.stringify(param), 
      dataType: "json",
      success : function(data) {
          if(data.status == 1) {
        	$("#in-warehouse-modal").modal("hide");
        	//刷新
        	loadWarehouse();
          } else {
            alert(data.description);
          }
      },
      error : function(){
          alert("error");
      }
    });
}

/*出库*/
function outWarehouseSubmit() {
	var param = {};
  	//获取输入值
  	var pay = $("#out-pdt-number").val();
  	if(isNaN(pay) || pay < 0) {
    	//输入不合法，要求为大于0的整数
    	alert("输入不合法，要求为大于0的整数");
    	return;
  	}
  	var product = {};
  	product.id = $("#out-pdt-id").text();
  	param.product = product;
  	param.number = pay;

  	$.ajax({
      type : "POST",
      contentType : 'application/json', 
      url : "outWarehouse.do",
      data : JSON.stringify(param), 
      dataType: "json",
      success : function(data) {
          if(data.status == 1) {
        	$("#out-warehouse-modal").modal("hide");
        	//刷新
        	loadWarehouse();
          } else {
            alert(data.description);
          }
      },
      error : function(){
          alert("error");
      }
    });
}

function inWarehouseShow(item) {
	$("#pdt-name").val(item.product.materialName);
	$("#pdt-left").text(item.number); 
	$("#pdt-id").text(item.id); 
	$("#in-warehouse-modal").modal("show");
}

function outWarehouseShow(item) {
	$("#out-pdt-name").val(item.product.materialName);
	$("#out-pdt-left").text(item.number); 
	$("#out-pdt-id").text(item.id); 
	$("#out-warehouse-modal").modal("show");
}

// 加载显示仓库的商品信息
function loadWarehouse() {
	$.get(
		"getWarehouses.do",
		function(data) {
			var text = "";
       		 $.each(data, function(i, item) {
       			text += "<li><figure>" +
       						"<div><img src='../resource/img/"+item.product.img+"' alt='img01'></div>"+
       						"<figcaption><div class='col-md-12 column'>" +
       							"<dl>" +
	       							"<dt>产品名称</dt><dd>"+item.product.materialName+"</dd>"+
	       							"<dt>size1</dt><dd>"+item.product.size1+"</dd>"+
	       							"<dt>size2</dt><dd>"+item.product.size2+"</dd>"+
	       							"<dt>库存量</dt><dd>"+item.number+"</dd>"+
       							"</dl>" +
       							"<button type='button' class='btn btn-primary btn-default' onclick='inWarehouseShow("+JSON.stringify(item)+");'>入库</button>" +
       							"<button type='button' class='btn btn-primary btn-default col-md-offset-2' onclick='outWarehouseShow("+JSON.stringify(item)+");'>出库</button>" +
       						"</div></figcaption>" +
       					"</figure></li>";
        	});
       		//$("#warehouse-main").clear();
        	$("#warehouse-main").html(text);
		}); 
}

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