<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>EPIS Back Office : Shop Management UI.</title>
<link href="../pages/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="../pages/resources/bootstrap-table/src/bootstrap-table.css" rel="stylesheet" type="text/css" />
<link href="../pages/resources/custom.css" rel="stylesheet" type="text/css" />
<script src="../pages/resources/jquery.min.js" type="text/javascript"></script>
<script src="../pages/resources/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="../pages/resources/bootstrap-table/src/bootstrap-table.js"></script>
<script src="../pages/resources/custom.js" type="text/javascript"></script>
<script>
	function runningFormatter(val, row, ind) { return ind + 1; }
	function runningMsgHide() { $('#alertSuccessAreaMsg').addClass('hide'); $('#alertDangerAreaMsg').addClass('hide'); }
	function isPositiveFormatter(val) {var target; if("0" == val) {target = "ระงับใช้งาน";} else if("1" == val) { target = "เปิดใช้งาน";} return target; }
	function rowsFormatter(id) {return '<a id="shopEdit" style="cursor:pointer" onClick="findShopDetailById(\''+ id +'\')" class="btn-sm btn-info"><i class="glyphicon glyphicon-edit"></i> แก้ไข</a>'; }
	function findShopDetailById(id) {
		$.get("../findShopDetailById.json", { "id": id }, function(res) {
			$('#searchShopPanel').addClass('hide'); $('#displayShopPanel').addClass('hide');  $('#createShopPanel').removeClass('hide');  $('#resetCreate').addClass('disabled');
			$("#id").val(res.id); $("#businessPlace").val(res.businessPlace); $("#businessArea").val(res.businessArea); $("#name").val(res.name); $("#costCenter").val(res.costCenter); $("#isPositive").val(res.isPositive); $("#descAbvrTh").val(res.descAbvrTh); $("#description").val(res.description); 
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
                    <li class="active"><i>ข้อมูลหน่วยงานรับชําระ</i></li>
                </ol>
	        </div>
	    </div>
		<div class="row">
			
			<div class="col-md-12 tab-modefile">
				<div id="alertSuccessAreaMsg" class="hide alert alert-success"><span class="glyphicon glyphicon-ok-sign"></span> บันทึกเรียบร้อย</div>
				<div id="alertDangerAreaMsg" class="hide alert alert-danger"><span class="glyphicon glyphicon-remove-sign"></span> เกิดข้อผิดพลาด</div>
				<!-- Search -->
				<div id="searchShopPanel">
				<ul class="nav nav-tabs" role="tablist">
					<li role="presentation" class="active"><a href="#"><span class="glyphicon glyphicon-filter"></span> ค้นหาหน่วยงานรับชําระ</a></li>
				</ul>
				<div class="panel panel-default panal-radius">
					<div class="panel-body">
						<div class="tab-content">
							<div role="tabpanel" class="tab-pane active" id="tab_1">
								<div class="form-horizontal">
									<div class="form-group">
										<label class="control-label col-sm-2">รหัสสาขาหน่วยงาน (BP) :</label>
										<div class="col-sm-2"><input class="form-control" id="businessPlaceForSh" name="businessPlaceForSh"></div>
										<label class="control-label col-sm-2">รหัสพื้นที่ธุระกิจ (BA) :</label>
										<div class="col-sm-2"><input class="form-control" id="businessAreaForSh" name="businessAreaForSh"></div>
										<div class="col-sm-3"></div>
									</div>
									<div class="form-group">
										<label class="control-label col-sm-2">ชื่อหน่วยงานสาขา :</label>
										<div class="col-sm-6">
											<input class="form-control" id="nameForSh" name="nameForSh">
										</div>
										<div class="col-sm-4">
										<a id="searchShop" class="btn btn-primary"><span class="glyphicon glyphicon-search"></span> ค้นหาข้อมูล</a>&nbsp;
										<a id="resetSearch" class="btn btn-danger"><span class="glyphicon glyphicon-minus-sign"></span> ยกเลิก</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<a id="createShop" class="btn btn-success"><span class="glyphicon glyphicon-file"></span> เพิ่มรายการ</a></div>
									</div>
								</div>
	                   		</div>
						</div> 
					</div>	
				</div>
				</div>
				<!-- Display -->
				<div id="displayShopPanel" class="hide tab-content">
					<div role="tabpanel" class="tab-pane active" id="tab-2-1">
					<table id="shopTable" data-toggle="table" data-toolbar="#shopToolbar" data-search="true" data-show-pagination-switch="true" data-show-refresh="true" data-show-toggle="true" data-show-columns="true" data-show-export="true" data-pagination="true" data-height="400" data-cache="false">
                       <thead>
                           <tr>
								<th data-field="businessPlace" data-align="center">รหัสสาขาหน่วยงาน (BP)</th>
								<th data-field="businessArea" data-align="center">รหัสพื้นที่ธุระกิจ (BA)</th>
								<th data-field="costCenter" data-align="center">ศูนย์ต้นทุน</th>
								<th data-field="name" data-align="left">ชื่อหน่วยงานสาขา</th>
								<th data-field="descAbvrTh" data-align="left">ชื่อย่อ</th>
								<th data-field="isPositive" data-formatter="isPositiveFormatter" data-align="left">สถานะ</th>
								<th data-field="id" data-formatter="rowsFormatter" data-align="center"></th>
							</tr>
                       </thead>
                   </table>
                   </div>
                </div> 
				<!-- Create -->
				<div id="createShopPanel" class="hide">
				<ul class="nav nav-tabs" role="tablist">
					<li role="presentation" class="active"><a href="#"><span class="glyphicon glyphicon-list"></span> เพิ่มหน่วยงานรับชําระ</a></li>
				</ul>
				<div class="panel panel-default panal-radius">
					<form id="createShopFm" method="post" class="form-horizontal" role="form">
					<div class="panel-body">
						<div class="tab-content">
							<div role="tabpanel" class="tab-pane active" id="tab_1">
								<div class="form-horizontal">
									<div class="form-group">
										<label class="control-label col-sm-2">รหัสหน่วยงานสาขา :</label>
										<div class="col-sm-2"><input class="form-control" id="businessPlace" name="businessPlace"></div>
										<label class="control-label col-sm-2">รหัสพื้นที่ธุระกิจ :</label>
										<div class="col-sm-2"><input class="form-control" id="businessArea" name="businessArea"></div>
										<div class="col-sm-3"></div>
									</div>
									<div class="form-group">
										<label class="control-label col-sm-2">ศูนย์ต้นทุน :</label>
										<div class="col-sm-2"><input class="form-control" id="costCenter" name="costCenter"></div>
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
										<label class="control-label col-sm-2">ชื่อหน่วยงานสาขา :</label>
										<div class="col-sm-4">
											<input class="form-control" id="name" name="name">
										</div>
										<div class="col-sm-2"><input class="form-control" id="descAbvrTh" name="descAbvrTh"></div>
										<div class="col-sm-2"></div>
									</div>
									<div class="form-group">
										<label class="control-label col-sm-2">อธิบายเพิ่มเติม :</label>
										<div class="col-sm-6">
											<input class="form-control" id="description" name="description">
										</div>
										<div class="col-sm-4">
											<a id="saveShop" class="btn btn-primary"><span class="glyphicon glyphicon-plus-sign"></span> บันทึกข้อมูล</a>&nbsp;
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
		</div>							
	</div>
</section>
</body>
<script>
$(document).on("click", "a#searchShop", function() {
	runningMsgHide(); 
	if($('#businessPlaceForSh').val() == '' && $('#businessAreaForSh').val() == '' && $('#nameForSh').val() == '') {
		if($('#businessPlaceForSh').val() == '') { $('#businessPlaceForSh').attr("style", "color:#a94442"); $("#businessPlaceForSh").attr("style", "background-color:#ffffaa;border:solid 1px #a94442;padding:0 10px"); }
		if($('#businessAreaForSh').val() == '') { $('#businessAreaForSh').attr("style", "color:#a94442"); $("#businessAreaForSh").attr("style", "background-color:#ffffaa;border:solid 1px #a94442;padding:0 10px"); }
		if($('#nameForSh').val() == '') { $('#nameForSh').attr("style", "color:#a94442"); $("#nameForSh").attr("style", "background-color:#ffffaa;border:solid 1px #a94442;padding:0 10px"); }
			return false;
	} else { 
		$('#displayShopPanel').removeClass('hide');  $('#createShopPanel').addClass('hide'); 
		$('#businessPlaceForSh').removeAttr("style"); $('#businessAreaForSh').removeAttr("style"); $('#nameForSh').removeAttr("style"); 
// 		$("#shopTable").bootstrapTable("refresh", { url: "../findShopByBranchAttribute.json?businessPlace="+ $("#businessPlaceForSh").val() +"&businessArea="+ $("#businessAreaForSh").val() +"&name="+ $("#nameForSh").val() });
// 		setTimeout(function () { $("#shopTable").bootstrapTable("resetView"); }, 200);

		var dataSend={
			businessPlace:$("#businessPlaceForSh").val()
			,businessArea:$("#businessAreaForSh").val()
			,name:$("#nameForSh").val()
		};
		$.ajax({
			type: "POST",
			url: "../findShopByBranchAttribute.json",
			data: JSON.stringify(dataSend),
			dataType: "json",
			contentType: "application/json; charset=utf-8",
				success:function(data){
					$("#shopTable").bootstrapTable("load", data); 
			}
		});
	}
}).on("click", "a#createShop", function() {
	runningMsgHide(); 
	$('#searchShopPanel').addClass('hide'); $('#displayShopPanel').addClass('hide');  $('#createShopPanel').removeClass('hide');
	$('#businessPlaceForSh').removeAttr("style"); $('#businessAreaForSh').removeAttr("style"); $('#nameForSh').removeAttr("style");
	$("#id").val(''); $("#businessPlace").val(''); $("#businessArea").val(''); $("#name").val(''); $("#costCenter").val(''); $("#isPositive").val(''); $("#descAbvrTh").val(''); $("#description").val('');
}).on("click", "a#backToSearch", function() {
	runningMsgHide(); 
	$('#searchShopPanel').removeClass('hide'); $('#displayShopPanel').addClass('hide');  $('#createShopPanel').addClass('hide');	
	$('#businessPlaceForSh').removeAttr("style"); $('#businessAreaForSh').removeAttr("style"); $('#nameForSh').removeAttr("style"); 
}).on("click", "a#resetSearch", function() {
	runningMsgHide(); 
	$('#businessPlaceForSh').removeAttr("style"); $('#businessAreaForSh').removeAttr("style"); $('#nameForSh').removeAttr("style");
	$('#businessPlaceForSh').val(""); $('#businessAreaForSh').val(""); $('#nameForSh').val("");
}).on("click", "a#resetCreate", function() {
	runningMsgHide(); 
	$('#businessPlace').removeAttr("style"); $('#businessArea').removeAttr("style"); $('#name').removeAttr("style");
	$('#createShopFm')[0].reset();
}).on("click", "a#saveShop", function() {
	runningMsgHide(); 
	if($('#businessPlace').val() == '' && $('#businessArea').val() == '' && $('#name').val() == '') {
		if($('#businessPlace').val() == '') { $('#businessPlace').attr("style", "color:#a94442"); $("#businessPlace").attr("style", "background-color:#ffffaa;border:solid 1px #a94442;padding:0 10px"); }
		if($('#businessArea').val() == '') { $('#businessArea').attr("style", "color:#a94442"); $("#businessArea").attr("style", "background-color:#ffffaa;border:solid 1px #a94442;padding:0 10px"); }
		if($('#name').val() == '') { $('#name').attr("style", "color:#a94442"); $("#name").attr("style", "background-color:#ffffaa;border:solid 1px #a94442;padding:0 10px"); }
			return false;
	} else { $('#businessPlace').removeAttr("style"); $('#businessArea').removeAttr("style"); $('#name').removeAttr("style");
		var dataSend={
			id:$("#id").val(),	
			businessPlace:$("#businessPlace").val(),
			businessArea:$("#businessArea").val(),
			name:$("#name").val(),
			costCenter:$("#costCenter").val(),
			isPositive:$("#isPositive").val(),
			descAbvrTh:$("#descAbvrTh").val(),
			description:$("#description").val()
 		};
		$.ajax({
			type: "POST",
			url: "../saveShopByBranchAttribute.json",
			data: JSON.stringify(dataSend),
			dataType: "json",
			contentType: "application/json; charset=utf-8",
				success:function(data){
					$('#createShopFm')[0].reset();
					$('#alertSuccessAreaMsg').removeClass('hide');
					$('#alertDangerAreaMsg').addClass('hide');
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
