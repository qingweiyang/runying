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

function loadList() {
	$(".content").empty();
	$(".content").load("../admConfig/list.html");
}

function loadAdd() {
	$(".content").empty();
	$(".content").load("../admConfig/add.html");
}

function loadProductList() {
	$(".content").empty();
	$(".content").load("../admConfig/productList.html");
}

function loadProductAdd() {
	$(".content").empty();
	$(".content").load("../admConfig/productAdd.html");
}

function loadUserInfo() {
	$(".content").empty();
	$(".content").load("../warehouse_main/userInfo.html", function() {
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
	$(".content").empty();
	$(".content").load("../warehouse_main/userEdit.html", function() {
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

function searchProduct() {
	show(1, countPerPage);
}