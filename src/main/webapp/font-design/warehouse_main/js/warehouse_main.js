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
			$("#privilege").text(data.privilege);
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

function loadWarehouseInOut() {
	location.href = "../warehouse/main.html";
}

function loadUserInfo() {
	$("#main-page").empty();
	$("#main-page").load("userInfo.html", function() {
        $("#name").text($("#username").text());
        var pri = '';
        var privilege = Number($("#privilege").text());
        if((privilege & 1) == 1) {
          pri += '<span class="label label-primary">录入订单</span> ';
        } 
        if(((privilege >> 1) & 1) == 1) {
          pri += '<span class="label label-primary">增加工序</span> ';
        } 
        if(((privilege >> 2) & 1) == 1) {
          pri += '<span class="label label-primary">入库出库</span> ';
        }
        if(((privilege >> 3) & 1) == 1) {
          pri += '<span class="label label-success">管理员</span> ';
        }
        $("#pri-suf-des").append(pri);
    }); 
}

function loadUserEdit() {
	$("#main-page").empty();
	$("#main-page").load("userEdit.html", function() {
        $("#name").text($("#username").text());
        var pri = '';
        var privilege = Number($("#privilege").text());
        if((privilege & 1) == 1) {
          pri += '<span class="label label-primary">录入订单</span> ';
        } 
        if(((privilege >> 1) & 1) == 1) {
          pri += '<span class="label label-primary">增加工序</span> ';
        } 
        if(((privilege >> 2) & 1) == 1) {
          pri += '<span class="label label-primary">入库出库</span> ';
        }
        if(((privilege >> 3) & 1) == 1) {
          pri += '<span class="label label-success">管理员</span> ';
        }
        $("#pri-suf-des").append(pri);
    }); 
}

//加载 仓库列表
function loadList() {
	$("#main-page").children().remove();
	$("#main-page").load("warehouse/list.html");
}

//加载 批量出库
function loadOutBatch() {
	$("#main-page").empty();
	$("#main-page").load("warehouse/outBatch.html");
}

//加载 批量入库
function loadInBatch() {
	$("#main-page").empty();
	$("#main-page").load("warehouse/inBatch.html");
}

function loadOutBatchView() {
	if(orders.length == 0) {
		alert("至少选个啊");
		return ;
	}
	$("#main-page").empty();
	$("#main-page").load("warehouse/outBatchView.html", function() {
		var text = "";
		for(var i = 0 ; i < orders.length ; i++) {
			var item = orders[i];
        	text += "<tr><td>"+(i+1)+"</td>"+
                      "<td>"+item.materialName+"</td>"+
                      "<td>"+item.size1+"</td>"+
                      "<td>"+item.processes.length+"</td>"+
                      "<td><input></td>"+
                      "<td> NULL </td>"+
                      "</tr>";
        };
		$("#current-table tbody").html(text);
    }); 
}

function loadInBatchView() {
	if(orders.length == 0) {
		alert("至少选个啊");
		return ;
	}
	$("#main-page").empty();
	$("#main-page").load("warehouse/inBatchView.html", function() {
		var text = "";
		for(var i = 0 ; i < orders.length ; i++) {
			var item = orders[i];
        	text += "<tr><td>"+(i+1)+"</td>"+
                      "<td>"+item.materialName+"</td>"+
                      "<td>"+item.size1+"</td>"+
                      "<td>"+item.processes.length+"</td>"+
                      "<td>"+item.count+"</td>"+
                      "<td>null</td>"+
                      "</tr>";
        };
		$("#current-table tbody").html(text);
    }); 
}

function outWarehouseBatch() {
	  tableVO.rows = orders;
	  var error = false;
	  $("#current-table tbody tr").each(function(i, item) {
		  var ct = $($(item).find("input:eq(0)")).val();
		  
		  //测试是否为正整数
		  var type = "^[0-9]*[1-9][0-9]*$"; 
		  var re = new RegExp(type);
		  if("" == ct || ct.match(re) == null) {
			  alert("数量得为正数");
			  error = true;
			  return false ;
		  }
		  //测试数量是否小于库存数
		  if(tableVO.rows[i].warehouseCount < ct) {
			  alert("出货数量必须小于库存量");
			  error = true;
			  return false ;
		  }
		  tableVO.rows[i].count = ct;
		  
	   });
	  if(error) {
		  return ;
	  }
	  
	  $.ajax({
	      type : "POST",
	      contentType : 'application/json', 
	      url : "outWarehouseBatch.do",
	      data : JSON.stringify(tableVO), 
	      dataType: "json",
	      success : function(data) {
	    	  if(data.status == 1) {
	    		  $("#main-page").empty();
	    		  $("#main-page").load("warehouse/outBatchSuccess.html");
	    	  } else {
	    		  alert(data.description);
	    	  }
	      },
	      error : function(){
	          alert("error");
	      }
	   });
}

function inWarehouseBatch() {
	  tableVO.rows = orders;
	  $.ajax({
	      type : "POST",
	      contentType : 'application/json', 
	      url : "inWarehouseBatch.do",
	      data : JSON.stringify(tableVO), 
	      dataType: "json",
	      success : function(data) {
	    	  if(data.status == 1) {
	    		  $("#main-page").empty();
	    		  $("#main-page").load("warehouse/inBatchSuccess.html");
	    	  } else {
	    		  alert(data.description);
	    	  }
	      },
	      error : function(){
	          alert("error");
	      }
	   });
}

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
  	
  	$("#in-warehouse-modal").modal("hide");
  	
  	$.ajax({
      type : "POST",
      contentType : 'application/json', 
      url : "inWarehouse.do",
      data : JSON.stringify(param), 
      dataType: "json",
      success : function(data) {
          if(data.status == 1) {
        	//刷新
        	loadList();
        	//bug ...
        	$("div").removeClass("modal-backdrop fade in");
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

function print() {
	$("#print_area").show();
	$("#print_area").printArea();
	$("#print_area").hide();
}