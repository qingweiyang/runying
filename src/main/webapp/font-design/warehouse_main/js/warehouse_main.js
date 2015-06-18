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

//	加载 批量出库
function loadOutBatch() {
	$("#main-page").empty();
	$("#main-page").load("warehouse/outBatch.html");
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