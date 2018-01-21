<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>EPIS Back Office : Others Management UI.</title>
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
	function rowsFormatter(id) {return '<a id="othersEdit" style="cursor:pointer" onClick="findOthersDetailById(\''+ id +'\')" class="btn-sm btn-info"><i class="glyphicon glyphicon-edit"></i> แก้ไข</a>'; }
	function findOthersDetailById(id) {
		$.get("../findOthersDetailById.json", { "id": id }, function(res) {
			$('#searchOthersPanel').addClass('hide'); $('#displayOthersPanel').addClass('hide');  $('#createOthersPanel').removeClass('hide');  $('#resetCreate').addClass('disabled');
			$("#id").val(res.id); $("#category").val(res.category); $("#code").val(res.code); $("#descFullTh").val(res.descFullTh); 
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
                    <li class="active"><i>ข้อมูลอื่นๆ</i></li>
                </ol>
	        </div>
	    </div>
		<div class="row">
			
			<div class="col-md-12 tab-modefile">
				<div id="alertSuccessAreaMsg" class="hide alert alert-success"><span class="glyphicon glyphicon-ok-sign"></span> บันทึกเรียบร้อย</div>
				<div id="alertDangerAreaMsg" class="hide alert alert-danger"><span class="glyphicon glyphicon-remove-sign"></span> เกิดข้อผิดพลาด</div>
				<!-- Search -->
				<div id="searchOthersPanel">
				<ul class="nav nav-tabs" role="tablist">
					<li role="presentation" class="active"><a href="#"><span class="glyphicon glyphicon-filter"></span> ค้นหาข้อมูลอื่นๆ</a></li>
				</ul>
				<div class="panel panel-default panal-radius">
					<div class="panel-body">
						<div class="tab-content">
							<div role="tabpanel" class="tab-pane active" id="tab_1">
								<div class="form-horizontal">
									<div class="form-group">
										<label class="control-label col-sm-2">รหัสกลุ่มข้อมูล :</label>
										<div class="col-sm-2">
											<select class="form-control" id="categoryForSh" name="categoryForSh">
												<option value="enumCat.01">กลุ่มผู้ใช้บริการ</option>
												<option value="enumCat.02">ชื่อธนาคาร</option>
												<option value="enumCat.03">สาขาธนาคาร</option>
												<option value="enumCat.04">ประเภทบัตรเครดิต</option>
												<option value="enumCat.05">ธนาคารผู้ออกบัตร (EDC)</option>
												<option value="enumCat.06">รหัสบัญชีเงินฝากธนาคาร</option>
												<option value="enumCat.07">เลขที่บัญชีเงินฝากธนาคาร</option>
												<option value="enumCat.08">ช่องทางการชำระ</option>
												<option value="enumCat.09">ศูนย์ต้นทุน</option>
												<option value="enumCat.10">VAT Rate</option>
												<option value="enumCat.11">ประเภทบริการ</option>
												<option value="enumCat.12">ชื่อบริการ</option>
												<option value="enumCat.13">หน่วยงานรับรายได้</option>
												<option value="enumCat.14">หน่วยนับ</option>
												<option value="enumCat.15">เหตุผลการยกเลิกรับชำระ</option>
												<option value="enumCat.16">ข้อมูลสกุลเงิน</option>	
												<option value="creditlimit.category.code">Credit Limit Bill Group</option>	
											</select>
										</div>
										<label class="control-label col-sm-2">รหัสข้อความ :</label>
										<div class="col-sm-2"><input class="form-control" id="codeForSh" name="codeForSh"></div>
									</div>
									<div class="form-group">
										<label class="control-label col-sm-2">ชื่อข้อความ :</label>
										<div class="col-sm-6"><input class="form-control" id="descFullThForSh" name="descFullThForSh"></div>
										<div class="col-sm-4">
										<a id="searchOthers" class="btn btn-primary"><span class="glyphicon glyphicon-search"></span> ค้นหาข้อมูล</a>&nbsp;
										<a id="resetSearch" class="btn btn-danger"><span class="glyphicon glyphicon-minus-sign"></span> ยกเลิก</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<a id="createOthers" class="btn btn-success"><span class="glyphicon glyphicon-file"></span> เพิ่มรายการ</a></div>
									</div>
								</div>
	                   		</div>
						</div> 
					</div>	
				</div>
				</div>
				<!-- Display -->
				<div id="displayOthersPanel" class="hide tab-content">
					<div role="tabpanel" class="tab-pane active" id="tab-2-1">
					<table id="othersTable" data-toggle="table" data-toolbar="#othersToolbar" data-search="true" data-show-pagination-switch="true" data-show-refresh="true" data-show-toggle="true" data-show-columns="true" data-show-export="true" data-pagination="true" data-height="400" data-cache="false">
                       <thead>
                           <tr>
								<th data-field="category" data-align="center">รหัสกลุ่มข้อมูล</th>
								<th data-field="code" data-align="center">รหัสข้อความ</th>
								<th data-field="descFullTh" data-align="left">ชื่อข้อความ</th>
								<th data-field="id" data-formatter="rowsFormatter" data-align="center"></th>
							</tr>
                       </thead>
                   </table>
                   </div>
                </div> 
				<!-- Create -->
				<div id="createOthersPanel" class="hide">
				<ul class="nav nav-tabs" role="tablist">
					<li role="presentation" class="active"><a href="#"><span class="glyphicon glyphicon-list"></span> เพิ่มข้อมูลอื่นๆ</a></li>
				</ul>
				<div class="panel panel-default panal-radius">
					<form id="createOthersFm" method="post" class="form-horizontal" role="form">
					<div class="panel-body">
						<div class="tab-content">
							<div role="tabpanel" class="tab-pane active" id="tab_1">
								<div class="form-horizontal">
									<div class="form-group">
										<label class="control-label col-sm-2">รหัสกลุ่มข้อมูล :</label>
										<div class="col-sm-2">
											<select class="form-control" id="category" name="category">
												<option value="enumCat.01">กลุ่มผู้ใช้บริการ</option>
												<option value="enumCat.02">ชื่อธนาคาร</option>
												<option value="enumCat.03">สาขาธนาคาร</option>
												<option value="enumCat.04">ประเภทบัตรเครดิต</option>
												<option value="enumCat.05">ธนาคารเจ้าของเครื่อง (EDC)</option>
												<option value="enumCat.06">รหัสบัญชีเงินฝากธนาคาร</option>
												<option value="enumCat.07">เลขที่บัญชีเงินฝากธนาคาร</option>
												<option value="enumCat.08">ช่องทางการชำระ</option>
												<option value="enumCat.09">ศูนย์ต้นทุน</option>
												<option value="enumCat.10">VAT Rate</option>
												<option value="enumCat.11">ประเภทบริการ</option>
												<option value="enumCat.12">ชื่อบริการ</option>
												<option value="enumCat.13">หน่วยงานรับรายได้</option>
												<option value="enumCat.14">หน่วยนับ</option>
												<option value="enumCat.15">เหตุผลการยกเลิกรับชำระ</option>
												<option value="enumCat.16">ข้อมูลสกุลเงิน</option>	
												<option value="creditlimit.category.code">Credit Limit Bill Group</option>	
											</select>
										</div>
										<label class="control-label col-sm-2">รหัสข้อความ :</label>
										<div class="col-sm-2"><input class="form-control" id="code" name="code"></div>
									</div>
									<div class="form-group">
										<label class="control-label col-sm-2">ชื่อข้อความ :</label>
										<div class="col-sm-6"><input class="form-control" id="descFullTh" name="descFullTh"></div>
										<div class="col-sm-4">
											<a id="saveOthers" class="btn btn-primary"><span class="glyphicon glyphicon-plus-sign"></span> บันทึกข้อมูล</a>&nbsp;
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
$(document).on("click", "a#searchOthers", function() {
	runningMsgHide(); 
	if($('#categoryForSh').val() == null && $('#codeForSh').val() == '' && $('#descFullThForSh').val() == '') {
		if($('#categoryForSh').val() == null) { $('#categoryForSh').attr("style", "color:#a94442"); $("#categoryForSh").attr("style", "background-color:#ffffaa;border:solid 1px #a94442;padding:0 10px"); }
		if($('#codeForSh').val() == '') { $('#codeForSh').attr("style", "color:#a94442"); $("#codeForSh").attr("style", "background-color:#ffffaa;border:solid 1px #a94442;padding:0 10px"); }
		if($('#descFullThForSh').val() == '') { $('#descFullThForSh').attr("style", "color:#a94442"); $("#descFullThForSh").attr("style", "background-color:#ffffaa;border:solid 1px #a94442;padding:0 10px"); }
			return false;
	} else { 
		$('#displayOthersPanel').removeClass('hide');  $('#createOthersPanel').addClass('hide'); 
		$('#categoryForSh').removeAttr("style"); $('#codeForSh').removeAttr("style"); $('#descFullThForSh').removeAttr("style"); 
		$("#othersTable").bootstrapTable("refresh", { url: "../findOthersByAttribute.json?category="+ $("#categoryForSh").val() +"&code="+ $("#codeForSh").val() +"&descFullTh="+ $("#descFullThForSh").val() });
		setTimeout(function () { $("#othersTable").bootstrapTable("resetView"); }, 200);
	}
}).on("click", "a#createOthers", function() {
	runningMsgHide(); 
	$('#searchOthersPanel').addClass('hide'); $('#displayOthersPanel').addClass('hide');  $('#createOthersPanel').removeClass('hide');
	$('#categoryForSh').removeAttr("style"); $('#codeForSh').removeAttr("style"); $('#descFullThForSh').removeAttr("style");
	$("#id").val(''); $("#category").val(''); $("#code").val('');
}).on("click", "a#backToSearch", function() {
	runningMsgHide(); 
	$('#searchOthersPanel').removeClass('hide'); $('#displayOthersPanel').addClass('hide');  $('#createOthersPanel').addClass('hide');	
	$('#categoryForSh').removeAttr("style"); $('#codeForSh').removeAttr("style"); $('#descFullThForSh').removeAttr("style"); 
}).on("click", "a#resetSearch", function() {
	runningMsgHide(); 
	$('#categoryForSh').removeAttr("style"); $('#codeForSh').removeAttr("style"); $('#descFullThForSh').removeAttr("style");
	$('#categoryForSh').val(""); $('#codeForSh').val(""); $('#descFullThForSh').val("");
}).on("click", "a#resetCreate", function() {
	runningMsgHide(); 
	$('#category').removeAttr("style"); $('#code').removeAttr("style"); $('#descFullTh').removeAttr("style");
	$('#createOthersFm')[0].reset();
}).on("click", "a#saveOthers", function() {
	runningMsgHide(); 
	if($('#category').val() == null && $('#code').val() == '' && $('#descFullTh').val() == '') {
		if($('#category').val() == null) { $('#category').attr("style", "color:#a94442"); $("#category").attr("style", "background-color:#ffffaa;border:solid 1px #a94442;padding:0 10px"); }
		if($('#code').val() == '') { $('#code').attr("style", "color:#a94442"); $("#code").attr("style", "background-color:#ffffaa;border:solid 1px #a94442;padding:0 10px"); }
		if($('#descFullTh').val() == '') { $('#descFullTh').attr("style", "color:#a94442"); $("#descFullTh").attr("style", "background-color:#ffffaa;border:solid 1px #a94442;padding:0 10px"); }
			return false;
	} else { $('#category').removeAttr("style"); $('#code').removeAttr("style"); $('#descFullTh').removeAttr("style");
		var dataSend={
			id:$("#id").val(),	
			category:$("#category").val(),
			code:$("#code").val(),
			descFullTh:$("#descFullTh").val()
 		};
		$.ajax({
			type: "POST",
			url: "../saveOthersByAttribute.json",
			data: JSON.stringify(dataSend),
			dataType: "json",
			contentType: "application/json; charset=utf-8",
				success:function(data){
					$('#createOthersFm')[0].reset();
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
