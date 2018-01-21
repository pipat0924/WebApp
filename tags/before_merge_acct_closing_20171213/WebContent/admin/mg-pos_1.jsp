<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>EPIS Back Office : Pos Management UI.</title>
<link href="../pages/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="../pages/resources/bootstrap-table/src/bootstrap-table.css" rel="stylesheet" type="text/css" />
<link href="../pages/resources/custom.css" rel="stylesheet" type="text/css" />
<script src="../pages/resources/jquery.min.js" type="text/javascript"></script>
<script src="../pages/resources/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="../pages/resources/bootstrap-table/src/bootstrap-table.js"></script>
<script src="../pages/resources/custom.js" type="text/javascript"></script>
<script>
	function runningFormatter(val, row, ind) { return ind + 1; }
	function runningMsgHide() { $('#alertSuccessAreaMsg').addClass('hide');$('#alertDuplicateMsg').addClass('hide');$('#alertDangerAreaMsg').addClass('hide'); }
	function isPositiveFormatter(val) {var target; if("0" == val) {target = "ระงับใช้งาน";} else if("1" == val) { target = "เปิดใช้งาน";} return target; }
	function shopFormatter(val) { return val.name; }
	function rowsFormatter(id) {return '<a id="posEdit" style="cursor:pointer" onClick="findPosDetailById(\''+ id +'\')" class="btn-sm btn-info"><i class="glyphicon glyphicon-edit"></i> แก้ไข</a>'; }
	function findPosDetailById(id) {
		$.get("../findPosDetailById.json", { "id": id }, function(res) {
			$('#searchPosPanel').addClass('hide'); $('#displayPosPanel').addClass('hide');  $('#createPosPanel').removeClass('hide');  $('#resetCreate').addClass('disabled');
			$("#id").val(res.id); $("#no").val(res.no); $("#name").val(res.name); $("#mac").val(res.mac); $("#isPositive").val(res.isPositive); $("#shop").val(res.shop.id); $("#description").val(res.description); 
		});
	}	
</script>	
</head>
<body>
	<header class="header_page"></header>
	<section class="container-fluid menu">
	    <div class="row">
	        <div class="col-md-12 tab-modefile">
	        	<ol class="breadcrumb">
                    <li><i>การจัดการทั่วไป</i></li>
                    <li class="active"><i>ข้อมูลเครื่องที่รับชําระ</i></li>
                </ol>
	        </div>
	    </div>
		<div class="row">
			<div class="col-md-12 tab-modefile">
				<div id="alertDuplicateMsg" class="hide alert alert-danger"><span class="glyphicon glyphicon-ok-sign"></span> รหัสเครื่องซ้ำ</div>
				<div id="alertSuccessAreaMsg" class="hide alert alert-success"><span class="glyphicon glyphicon-ok-sign"></span> บันทึกเรียบร้อย</div>
				<div id="alertDangerAreaMsg" class="hide alert alert-danger"><span class="glyphicon glyphicon-remove-sign"></span> เกิดข้อผิดพลาด</div>
				<!-- Search -->
				<div id="searchPosPanel">
				<ul class="nav nav-tabs" role="tablist">
					<li role="presentation" class="active"><a href="#"><span class="glyphicon glyphicon-filter"></span> ค้นหาเครื่องที่รับชําระ</a></li>
				</ul>
				<div class="panel panel-default panal-radius">
					<div class="panel-body">
						<div class="tab-content">
							<div role="tabpanel" class="tab-pane active" id="tab_1">
								<div class="form-horizontal">
									<div class="form-group">
										<label class="control-label col-sm-2">รหัสเครื่องรับชำระเงิน :</label>
										<div class="col-sm-2"><input class="form-control" id="noForSh" name="noForSh"></div>
										<label class="control-label col-sm-2">ชื่อเครื่องรับชำระเงิน :</label>
										<div class="col-sm-2"><input class="form-control" id="nameForSh" name="nameForSh"></div>
										<div class="col-sm-3"></div>
									</div>
									<div class="form-group">
										<label class="control-label col-sm-2">ชื่อหน่วยงานรับชําระ :</label>
										<div class="col-sm-6">
											<select class="form-control" id="shopForSh" name="shopForSh"></select>
										</div>
										<div class="col-sm-4">
										<a id="searchPos" class="btn btn-primary"><span class="glyphicon glyphicon-search"></span> ค้นหาข้อมูล</a>&nbsp;
										<a id="resetSearch" class="btn btn-danger"><span class="glyphicon glyphicon-minus-sign"></span> ยกเลิก</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<a id="createPos" class="btn btn-success"><span class="glyphicon glyphicon-file"></span> เพิ่มรายการ</a></div>
									</div>
								</div>
	                   		</div>
						</div> 
					</div>	
				</div>
				</div>
				<!-- Display -->
				<div id="displayPosPanel" class="hide tab-content">
					<div role="tabpanel" class="tab-pane active" id="tab-2-1">
					<table id="posTable" data-toggle="table" data-toolbar="#PosToolbar" data-search="true" data-show-pagination-switch="true" data-show-refresh="true" data-show-toggle="true" data-show-columns="true" data-show-export="true" data-pagination="true" data-height="400" data-cache="false">
                       <thead>
                           <tr>
								<th data-field="shop" data-formatter="shopFormatter" data-align="left">ชื่อหน่วยงานรับชําระ</th>
								<th data-field="no" data-align="center">รหัสเครื่องรับชำระเงิน</th>
								<th data-field="name" data-align="left">ชื่อเครื่องรับชำระเงิน</th>
								<th data-field="mac" data-align="center">Mac Address</th>
								<th data-field="isPositive" data-formatter="isPositiveFormatter" data-align="left">สถานะ</th>
								<th data-field="id" data-formatter="rowsFormatter" data-align="center"></th>
							</tr>
                       </thead>
                   </table>
                   </div>
                </div>
				<!-- Create -->
				<div id="createPosPanel" class="hide">
				<ul class="nav nav-tabs" role="tablist">
					<li role="presentation" class="active"><a href="#"><span class="glyphicon glyphicon-list"></span> เพิ่มเครื่องที่รับชําระ</a></li>
				</ul>
				<div class="panel panel-default panal-radius">
					<form id="createPosFm" method="post" class="form-horizontal" role="form">
					<div class="panel-body">
						<div class="tab-content">
							<div role="tabpanel" class="tab-pane active" id="tab_1">
								<div class="form-horizontal">
									<div class="form-group">
										<label class="control-label col-sm-2">รหัสเครื่องรับชำระเงิน :</label>
										<div class="col-sm-2"><input class="form-control" id="no" name="no"></div>
										<label class="control-label col-sm-2">Mac Address :</label>
										<div class="col-sm-2"><input class="form-control" id="mac" name="mac"></div>
										<div class="col-sm-3"></div>
									</div>
									<div class="form-group">
										<label class="control-label col-sm-2">ชื่อเครื่องรับชำระเงิน :</label>
										<div class="col-sm-2"><input class="form-control" id="name" name="name"></div>
										<label class="control-label col-sm-2">สถานะ :</label>
										<div class="col-sm-2">
											<select class="form-control" id="isPositive" name="isPositive">
                        						<option value="">- กรุณาเลือก -</option>
                        						<option value="1">เปิดใช้งาน</option>
                        						<option value="0">ระงับใช้งาน</option>
                        					</select>	
										</div>
										<div class="col-sm-3"></div>
									</div>
									<div class="form-group">
										<label class="control-label col-sm-2">ชื่อหน่วยงานรับชําระ :</label>
										<div class="col-sm-6">
											<select class="form-control" id="shop" name="shop"></select>
										</div>
										<div class="col-sm-4"></div>
									</div>
									<div class="form-group">
										<label class="control-label col-sm-2">อธิบายเพิ่มเติม :</label>
										<div class="col-sm-6">
											<input class="form-control" id="description" name="description">
										</div>
										<div class="col-sm-4">
											<a id="savePos" class="btn btn-primary"><span class="glyphicon glyphicon-plus-sign"></span> บันทึกข้อมูล</a>&nbsp;
											<a id="resetCreate" class="btn btn-danger"><span class="glyphicon glyphicon-minus-sign"></span> ยกเลิก</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<a id="backToSearch" class="btn btn-success"><span class="glyphicon glyphicon-search"></span> ค้นหาข้อมูล</a>
										</div>
										<input type="hidden" id="id" name="id" value="0"/>
									</div>
								</div>
	                   		</div>
						</div> 
					</div>	
					</form>
				</div>
				</div>							
			</div>
	</section>
</body>
<script>
var view = (function($){
	function DropDown(el, url) { var obj = this, data = [{ key: "", value: "", text: "Please Select" }]; obj.el = el; obj.elem = $(el);
	obj.data = function(array) { if ($.type(array) == "array") { setup(data = array); return this } return data; };
	obj.init = function(url) { if (url) $.get(url, function(res) { setup(data = res.data) }); else setup(data); return this };
	obj.disable = function(flag) { obj.elem.attr("disabled", (flag == null ? true : flag)); return this }
	obj.enable = function() { obj.disable(false); return this }
	obj.index = function() { if (arguments.length == 1) { var maxLen = data.length, index = arguments[0]; obj.elem.find("option").each(function(i, opt){ opt.removeAttribute("selected") }).eq(index < 0 ? maxLen + index : index).attr("selected", true); return this } return $.inArray("selected", obj.elem.find("option").map(function(i, opt){ return opt.selected ? "selected" : "no-selected" })) }
	obj.selected = function(){ return data[obj.index()]; }; 
	obj.val = function() { return obj.elem.val(); }; 
	obj.key = function(){ if (arguments.length == 0) { var key = obj.elem.find("option:selected").data("key"); if (typeof key === "string" && key === "undefined") { var item = data[obj.index()]; key = item && item.key ? item.key : item.id } return key } var key = arguments[0]; for (var i = 0, m = data.length; i < m; i++) { var item = data[i]; if (item.key && item.key == key) { obj.index(i); break; } if (item.id && item.id == key) { obj.index(i); break; } } return this }
	function setup(array) { obj.elem.find("*").remove(); 
		obj.elem.append('<option data-index="" data-key="" value="">- กรุณาเลือก -</option>');
		$.each(array,function(i,o){ 
			if("#shopForSh" == el) {
				obj.elem.append('<option data-index="'+ i +'" data-key="'+ o.no +'" value="'+ o.no +'">'+ o.name +'</option>')
			} else if("#shop" == el) {
				obj.elem.append('<option data-index="'+ i +'" data-key="'+ o.id +'" value="'+ o.id +'">'+ o.name +'</option>')
			}
		}); 
	}
	data = obj.elem.change(window[el.substring(1) +"ChangeEvent"]).find("option").map(function(i, opt){ return { key: opt.getAttribute("data-key"), value: opt.value, text: opt.textContent } });
	}
	(function(){ $(window["setup"]) })();
		new DropDown("#shopForSh").init("../findShopList.json");
		new DropDown("#shop").init("../findShopList.json");
	return this;
})(jQuery);

$(document).on("click", "a#searchPos", function() {
	runningMsgHide(); 
	if($('#noForSh').val() == '' && $('#nameForSh').val() == '' && $('#shopForSh').val() == '') {
		if($('#noForSh').val() == '') { $('#noForSh').attr("style", "color:#a94442"); $("#noForSh").attr("style", "background-color:#ffffaa;border:solid 1px #a94442;padding:0 10px"); }
		if($('#nameForSh').val() == '') { $('#nameForSh').attr("style", "color:#a94442"); $("#nameForSh").attr("style", "background-color:#ffffaa;border:solid 1px #a94442;padding:0 10px"); }
		if($('#shopForSh').val() == '') { $('#shopForSh').attr("style", "color:#a94442"); $("#shopForSh").attr("style", "background-color:#ffffaa;border:solid 1px #a94442;padding:0 10px"); }
			return false;
	} else {
		$('#displayPosPanel').removeClass('hide');  $('#createPosPanel').addClass('hide');
		$('#noForSh').removeAttr("style"); $('#nameForSh').removeAttr("style"); $('#shopForSh').removeAttr("style");
		$("#posTable").bootstrapTable("refresh", { url: "../findPosByBranchAttribute.json?no="+ $("#noForSh").val() +"&name="+ $("#nameForSh").val() +"&shop="+ $("#shopForSh").val() });
		setTimeout(function () { $("#posTable").bootstrapTable("resetView"); }, 200);
	}
}).on("click", "a#createPos", function() {
	runningMsgHide(); 
	$('#searchPosPanel').addClass('hide'); $('#displayPosPanel').addClass('hide');  $('#createPosPanel').removeClass('hide');
	$('#noForSh').removeAttr("style"); $('#nameForSh').removeAttr("style"); $('#shopForSh').removeAttr("style");
	$("#id").val(''); $("#no").val(''); $("#name").val(''); $("#mac").val(''); $("#isPositive").val(''); $("#shop").val(''); $("#description").val('');
}).on("click", "a#backToSearch", function() {
	runningMsgHide(); 
	$('#searchPosPanel').removeClass('hide'); $('#displayPosPanel').addClass('hide');  $('#createPosPanel').addClass('hide');		
	$('#noForSh').removeAttr("style"); $('#nameForSh').removeAttr("style"); $('#shopForSh').removeAttr("style"); 
}).on("click", "a#resetSearch", function() {
	runningMsgHide(); 
	$('#noForSh').removeAttr("style"); $('#nameForSh').removeAttr("style"); $('#shopForSh').removeAttr("style");
	$('#noForSh').val(""); $('#nameForSh').val(""); $('#shopForSh').val("");
}).on("click", "a#resetCreate", function() {
	runningMsgHide(); 
	$('#no').removeAttr("style"); $('#name').removeAttr("style"); $('#mac').removeAttr("style"); $('#shop').removeAttr("style");
	$('#createPosFm')[0].reset();
}).on("click", "a#savePos", function() {
	runningMsgHide(); 
	if($('#no').val() == '' && $('#name').val() == '' && $('#mac').val() == '' && $('#shop').val() == null) {
		if($('#no').val() == '') { $('#no').attr("style", "color:#a94442"); $("#no").attr("style", "background-color:#ffffaa;border:solid 1px #a94442;padding:0 10px"); }
		if($('#name').val() == '') { $('#name').attr("style", "color:#a94442"); $("#name").attr("style", "background-color:#ffffaa;border:solid 1px #a94442;padding:0 10px"); }
		if($('#mac').val() == '') { $('#mac').attr("style", "color:#a94442"); $("#mac").attr("style", "background-color:#ffffaa;border:solid 1px #a94442;padding:0 10px"); }
		if($('#shop').val() == null) { $('#shop').attr("style", "color:#a94442"); $("#shop").attr("style", "background-color:#ffffaa;border:solid 1px #a94442;padding:0 10px"); }
			return false;
	} else { $('#no').removeAttr("style"); $('#name').removeAttr("style"); $('#mac').removeAttr("style"); $('#shop').removeAttr("style");
		var dataShop = {id: $("#shop").val(), name : ""};
		var dataSend={
			id:$("#id").val(),	
			no:$("#no").val(),
			name:$("#name").val(),
			mac:$("#mac").val(),
			shop:dataShop,
			isPositive:$("#isPositive").val(),
			description:$("#description").val()
 		};
		$.ajax({
			type: "POST",
			url: "../savePosByBranchAttribute.json",
			data: JSON.stringify(dataSend),
			dataType: "json",
			contentType: "application/json; charset=utf-8",
				success:function(data){
				if(data != 0 ){
					$('#createPosFm')[0].reset();
					$('#alertSuccessAreaMsg').removeClass('hide');
					$('#alertDangerAreaMsg').addClass('hide');
					$('#alertDuplicateMsg').addClass('hide');
					
					}else{
                		$('#alertDuplicateMsg').removeClass('hide');
                	}
			},
	        	error: function(jqxhr) {
	        		$('#alertDangerAreaMsg').removeClass('hide');
	        		$('#alertSuccessAreaMsg').addClass('hide');
	        }
		});
	}
});
</script>
</html>
