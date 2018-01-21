<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>EPIS Back Office : Officer Management UI.</title>
<link href="../pages/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="../pages/resources/bootstrap-table/src/bootstrap-table.css" rel="stylesheet" type="text/css" />
<link href="../pages/resources/custom.css" rel="stylesheet" type="text/css" />
<script src="../pages/resources/jquery.min.js" type="text/javascript"></script>
<script src="../pages/resources/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="../pages/resources/bootstrap-table/src/bootstrap-table.js"></script>
<script src="../pages/resources/custom.js" type="text/javascript"></script>
<script>
	function runningFormatter(val, row, ind) { return ind + 1; }
	function runningMsgHide() { $('#alertSuccessAreaMsg').addClass('hide');  $('#alertDuplicateMsg').addClass('hide');$('#alertDangerAreaMsg').addClass('hide');  $('#alertPasswordAreaMsg').addClass('hide');  $('#alertShopAndPosAreaMsg').addClass('hide'); }
	function isPositiveFormatter(val) {var target; if("0" == val) {target = "ระงับใช้งาน";} else if("1" == val) { target = "เปิดใช้งาน";} return target; }
	function principalFormatter(val) { return val.name; }
	function rowsFormatter(id) {return '<a id="officerEdit" style="cursor:pointer" onClick="findOfficerDetailById(\''+ id +'\')" class="btn-sm btn-info"><i class="glyphicon glyphicon-edit"></i> แก้ไข</a>'; }
	function findOfficerDetailById(id) {
		$.get("../findOfficerDetailById.json", { "id": id }, function(res) {console.log(res);
			$('#searchOfficerPanel').addClass('hide'); $('#displayOfficerPanel').addClass('hide');  $('#createOfficerPanel').removeClass('hide');  $('#resetCreate').addClass('disabled');
			$("#id").val(res.id); $("#code").val(res.code); $("#isPositive").val(res.isPositive); $("#userName").val(res.name); $("#principal").val(res.principal.id); $("#givenName").val(res.givenName); $("#familyName").val(res.familyName); $("#password").val(res.password); $("#description").val(res.description);
			if(res.verifyFlag == "Y"){
				$("#verifyFlag").prop('checked', true);
			}else{
				$("#verifyFlag").prop('checked', false);
			}
			if(res.machines != null && res.machines.length > 0){
				$("#shop").val(res.machines[0].shop.id).change();
				var machinesId = [];
				$.map(res.machines, function(o, i){machinesId[i] = o.id});
				setTimeout(checkingPosTable, 500, machinesId);
			}
		});
	}
	function checkingPosTable(machinesId){
		view.posTable.checking(machinesId);
	}
	function idFormatter(id) {
		return '<input type="hidden" id="id" name="id" value="'+ id +'"/>';
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
                    <!-- ise no.138 -->
                    <li class="active"><i>เจ้าหน้าที่รับชำระ</i></li>
                    <!-- end ise no.138 -->
                </ol>
	        </div>
	    </div>
		<div class="row">
			<div class="col-md-12 tab-modefile">
			
			<div id="alertDuplicateMsg" class="hide alert alert-danger"><span class="glyphicon glyphicon-ok-sign"></span> รหัสผู้ใช้งานซ้ำ</div>
				<div id="alertSuccessAreaMsg" class="hide alert alert-success"><span class="glyphicon glyphicon-ok-sign"></span> บันทึกเรียบร้อย</div>
				<div id="alertDangerAreaMsg" class="hide alert alert-danger"><span class="glyphicon glyphicon-remove-sign"></span> เกิดข้อผิดพลาด</div>
				<div id="alertPasswordAreaMsg" class="hide alert alert-danger"><span class="glyphicon glyphicon-remove-sign"></span> เกิดข้อผิดพลาดสำหรับรหัสผ่าน</div>
				<div id="alertShopAndPosAreaMsg" class="hide alert alert-danger"><span class="glyphicon glyphicon-remove-sign"></span> เกิดข้อผิดพลาดสำหรับเครื่องรับชำระเงิน</div>
				<!-- Search -->
				<div id="searchOfficerPanel">
				<ul class="nav nav-tabs" role="tablist">
					<li role="presentation" class="active"><a href="#"><span class="glyphicon glyphicon-filter"></span> ค้นหาข้อมูลผู้ใช้งาน</a></li>
				</ul>
				<div class="panel panel-default panal-radius">
					<div class="panel-body">
						<div class="tab-content">
							<div role="tabpanel" class="tab-pane active" id="tab_1">
								<div class="form-horizontal">
									<div class="form-group">
										<label class="control-label col-sm-2">รหัสพนักงาน :</label>
										<div class="col-sm-2"><input class="form-control" id="codeForSh" name="codeForSh"></div>
										<label class="control-label col-sm-2">รหัสผู้ใช้งาน :</label>
										<div class="col-sm-2"><input class="form-control" id="userNameForSh" name="userNameForSh"></div>
										<div class="col-sm-3"></div>
									</div>
									<div class="form-group">
										<label class="control-label col-sm-2">ชื่อ :</label>
										<div class="col-sm-2"><input class="form-control" id="givenNameForSh" name="givenNameForSh"></div>
										<label class="control-label col-sm-2">นามสกุล :</label>
										<div class="col-sm-2"><input class="form-control" id="familyNameForSh" name="familyNameForSh"></div>
										<div class="col-sm-4">
										<a id="searchOfficer" class="btn btn-primary"><span class="glyphicon glyphicon-search"></span> ค้นหาข้อมูล</a>&nbsp;
										<a id="resetSearch" class="btn btn-danger"><span class="glyphicon glyphicon-minus-sign"></span> ยกเลิก</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<a id="createOfficer" class="btn btn-success"><span class="glyphicon glyphicon-file"></span> เพิ่มรายการ</a></div>
									</div>
								</div>
	                   		</div>
						</div> 
					</div>	
				</div>
				</div>
				<!-- Display -->
				<div id="displayOfficerPanel" class="hide tab-content">
					<div role="tabpanel" class="tab-pane active" id="tab-2-1">
					<table id="officerTable" data-toggle="table" data-toolbar="#officerToolbar" data-search="true" data-show-pagination-switch="true" data-show-refresh="true" data-show-toggle="true" data-show-columns="true" data-show-export="true" data-pagination="true" data-height="400" data-cache="false">
                       <thead>
                           <tr>
								<th data-field="code" data-align="center">รหัสพนักงาน</th>
								<th data-field="givenName" data-align="left">ชื่อผู้ใช้งาน</th>
								<th data-field="familyName" data-align="left">นามสกุล</th>
								<th data-field="name" data-align="center">รหัสผู้ใช้งาน</th>
								<th data-field="principal" data-formatter="principalFormatter" data-align="center">กลุ่มสิทธิ์ผู้ใช้งาน</th>
								<th data-field="isPositive" data-formatter="isPositiveFormatter" data-align="center">สถานะ</th>
								<th data-field="id" data-formatter="rowsFormatter" data-align="center"></th>
							</tr>
                       </thead>
                   </table>
                   </div>
                </div>
				<!-- Create -->
				<div id="createOfficerPanel" class="hide">
				<ul class="nav nav-tabs" role="tablist">
					<li role="presentation" class="active"><a href="#"><span class="glyphicon glyphicon-list"></span> เพิ่มข้อมูลผู้ใช้งาน</a></li>
				</ul>
				<div class="panel panel-default panal-radius">
					<form id="createOfficerFm" method="post" class="form-horizontal" role="form">
					<div class="panel-body">
						<div class="tab-content">
							<div role="tabpanel" class="tab-pane active" id="tab_1">
								<div class="form-horizontal">
									<div class="form-group">
										<label class="control-label col-sm-2">รหัสพนักงาน :</label>
										<div class="col-sm-2"><input class="form-control" id="code" name="code"></div>
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
										<label class="control-label col-sm-2">รหัสผู้ใช้งาน :</label>
										<div class="col-sm-2"><input class="form-control" id="userName" name="userName"></div>
										<label class="control-label col-sm-2">กลุ่มสิทธิ์ผู้ใช้งาน :</label>
										<div class="col-sm-2">
											<select class="form-control" id="principal" name="principal"></select>
										</div>
										<div class="col-sm-3"></div>
									</div>
									<div class="form-group">
										<label class="control-label col-sm-2">ชื่อผู้ใช้งาน :</label>
										<div class="col-sm-2"><input class="form-control" id="givenName" name="givenName"></div>
										<label class="control-label col-sm-2">นามสกุล :</label>
										<div class="col-sm-2"><input class="form-control" id="familyName" name="familyName"></div>
										<div class="col-sm-3"></div>
									</div>
									<div class="form-group">
										<!-- ise no.138 -->
										<label class="control-label col-sm-2">รหัสผ่าน :</label>
										<!-- end ise no.138 -->
										<div class="col-sm-2"><input type="password" class="form-control" id="password" name="password"></div>
										<!-- ise no.138 -->
										<label class="control-label col-sm-2">ยืนยันรหัสผ่าน :</label>
										<!-- end ise no.138 -->
										<div class="col-sm-2"><input type="password" class="form-control" id="passwordAi" name="passwordAi"></div>
										<div class="col-sm-3"></div>
									</div>
									<div class="form-group">
										<label class="control-label col-sm-2">อธิบายเพิ่มเติม :</label>
										<div class="col-sm-6">
											<input class="form-control" id="description" name="description">
										</div>
										<div class="col-sm-4">
											<a id="saveOfficer" class="btn btn-primary"><span class="glyphicon glyphicon-plus-sign"></span> บันทึกข้อมูล</a>&nbsp;
											<a id="resetCreate" class="btn btn-danger"><span class="glyphicon glyphicon-minus-sign"></span> ยกเลิก</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<a id="backToSearch" class="btn btn-success"><span class="glyphicon glyphicon-search"></span> ค้นหาข้อมูล</a>
										</div>
										<input type="hidden" id="id" name="id" value="0"/>
									</div>
									<div class="form-group">
										<div class="col-sm-2"></div>
										<label class="control-label col-sm-6" ><input type="checkbox" id="verifyFlag" name="verifyFlag">&nbsp;&nbsp;&nbsp;ระบุตัวตน</label>
										<div class="col-sm-4"></div>
									</div>
									<div class="form-group">
										<label class="control-label col-sm-2">ชื่อหน่วยงานรับชําระ :</label>
										<div class="col-sm-6">
											<select class="form-control" id="shop" name="shop"></select>
										</div>
										<div class="col-sm-4"></div>
									</div>
									<div class="form-group">
										<div class="col-sm-2"></div>
										<div class="col-sm-6">
											<table id="posTable" data-toggle="table" data-toolbar="#posToolbar" data-pagination="true" data-height="300" data-cache="false">
						                       <thead>
						                           <tr>
						                           		<th data-field="checked" data-checkbox="true"></th>
														<th data-field="no" data-align="center">รหัสเครื่องรับชำระเงิน</th>
														<th data-field="name" data-align="left">ชื่อเครื่องรับชำระเงิน</th>
														<th data-field="mac" data-align="center">Mac Address</th>
														<th data-field="isPositive" data-formatter="isPositiveFormatter" data-align="left">สถานะ</th>
														<th data-field="id" data-formatter="idFormatter" data-align="center"></th>
													</tr>
						                       </thead>
						                   </table>
										</div>
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
		obj.elem.append('<option data-index="-1" data-key="-1" value="-1">กรุณาเลือก</option>');
		$.each(array,function(i,o){ 
			obj.elem.append('<option data-index="'+ i +'" data-key="'+ o.id +'" value="'+ o.id +'">'+ o.name +'</option>')
		}); 
	}
	data = obj.elem.change(window[el.substring(1) +"ChangeEvent"]).find("option").map(function(i, opt){ return { key: opt.getAttribute("data-key"), value: opt.value, text: opt.textContent } });
	}
	function BootstrapTable(el, options) { var obj = this, rawData = [], successFunc = function(){}, checkEvt = function(e){ console.log(e) }, uncheckEvt = checkEvt; obj.el = el; obj.elem = $(el).bootstrapTable(options = $.extend({ uniqueId: "id", responseHandler: function(res){ if (!res) return []; if (res.constructor === Array) return res; if (!res.data || res.data.constructor !== Array) return []; return res.data }, onLoadSuccess: function(res){ rawData = res; obj.resetView(400); successFunc(res) }, onCheck: window[el.substring(1) +"CheckEvent"], onUncheck: window[el.substring(1) +"UncheckEvent"], onCheckAll: window[el.substring(1) +"CheckAllEvent"], onUncheckAll: window[el.substring(1) +"UncheckAllEvent"] }, options));
	obj.clear = function() { obj.elem.bootstrapTable("removeAll"); return obj }; obj.remove = function(index){ obj.elem.bootstrapTable("remove", { field: "index", values: [index] }); return this }; obj.resetView = function(ms){ setTimeout(function(){ obj.elem.bootstrapTable("resetView") }, ms || 200); return this }; obj.isEmpty = function(){ return obj.data().length == 0 }
	obj.showLoad = function() { this.elem.bootstrapTable("showLoading"); return this }; obj.hideLoad = function() { this.elem.bootstrapTable("hideLoading"); return this };
	obj.update = function(rec){ var id = rec.id, index = rec.index, row; if ((row = obj.getId(id)) != null) { obj.elem.bootstrapTable("updateRow", { "index": row.index, "row": $.extend(row, rec) }) } else if ((row = obj.data()[index]) != null) { obj.elem.bootstrapTable("updateRow", { "index": index, "row": $.extend(row, rec) }) } else obj.insert(rec); return this }; obj.insert = function(row) { var data = obj.elem.bootstrapTable("getData"); obj.elem.bootstrapTable("insertRow", { "index": data.length, "row": $.extend(row, { "index": data.length }) }); return this }
	obj.find = function(field, value) { var data = obj.elem.bootstrapTable("getData"); return $.map(data, function(o,i){ return o[field] === value ? o : null }) }
	obj.map = function(col) { var resultMap = {}; $.each(obj.data(), function(ind, row){ resultMap[row[col]] = row }); return resultMap }
	obj.rawData = function() { if (arguments.length == 1) { this.elem.bootstrapTable("load", rawData = arguments[0]); return this } return $.map(this.elem.bootstrapTable("getData"), function(row){ return row }) }; obj.getSelections = function(){ return obj.elem.bootstrapTable("getAllSelections") }
	obj.data = function() { var data = []; obj.elem.find("tbody tr").each(function(i,o){ var row = o, record = []; for (var j = 0, n = row.childNodes.length; j < n; j++) { var col = row.childNodes[j], val = ""; if (col.childNodes.length != 1) val = ""; else if (col.childNodes[0].nodeType == 3) val = $.trim(col.childNodes[0].textContent); else if (col.childNodes[0].nodeName == "INPUT") { var elm = col.childNodes[0], isText = elm.type == "text"; val = $.trim(isText ? elm.value : ((elm.type == "checkbox" || elm.type == "radio") && elm.checked ? elm.value : "")) } else if (col.childNodes[0].nodeName == "DIV") val = $.trim(col.childNodes[0].childNodes[0].value); record.push(val) } data.push(record) }); return data };
	obj.selected = function() { var data = []; obj.elem.find("tbody tr").find("input[type=checkbox]:checked, input[type=radio]:checked").each(function(i,o){ var row = o.parentNode.parentNode, record = []; for (var j = 0, n = row.childNodes.length; j < n; j++) { var col = row.childNodes[j], val = ""; if (col.childNodes.length != 1) val = ""; else if (col.childNodes[0].nodeType == 3) val = $.trim(col.childNodes[0].textContent); else if (col.childNodes[0].nodeName == "INPUT") val = $.trim(col.childNodes[0].value); else if (col.childNodes[0].nodeName == "DIV") val = $.trim(col.childNodes[0].childNodes[0].value); record.push(val) } data.push(record) }); return data };
	obj.getId = function(id) { var o = obj.elem.bootstrapTable("getRowByUniqueId", id); if ($.type(o) == "array") return null; return o[options.uniqueId] == id ? o : null }; obj.delId = function(id) { obj.elem.bootstrapTable("removeByUniqueId", id); return this }; // options: { uniqueId: "fieldName" }
	obj.filter = function(func) { var filteredRow, filteredData = []; for (var i = 0, m = rawData.length; i < m; i++) { if (filteredRow = func(rawData[i])) filteredData.push(filteredRow) } obj.elem.bootstrapTable("load", filteredData); return this }
	obj.sum = function(isAll, colName) { var sum = 0; $.each(this.elem.bootstrapTable(isAll ? "getData" : "getSelections"), function(i,o){ sum += (o[colName] || 0) }); return sum };
	obj.sumInput = function(index) { var sum = 0; obj.elem.find("tbody tr").each(function(i,o){ var val = o.children[index].children[0].value.replace(/[,]/g, ""); sum += (!$.isNumeric(val) ? 0 : parseFloat(val, 10)) }); return sum }
	obj.http = function(url, urlParams, succFunc) { if (succFunc) successFunc = succFunc; obj.elem.bootstrapTable("refresh", { "url": url, "query": urlParams }); return this }
	obj.expand = function() { obj.elem.find(".detail-icon i.icon-plus").click(); return this }; obj.collapse = function() { obj.elem.find(".detail-icon i.icon-minus").click(); return this };
	obj.checkAll = function(){ var checked = arguments.length == 0 || arguments[0] == true || ($.type(arguments[0]) == "boolean" ? arguments[0] : arguments[0] == "true"), data = obj.elem.bootstrapTable("getData"); $.each(obj.elem.bootstrapTable("getOptions").columns[0], function(i, col){ if (col.checkbox) $.each(data, function(j, row){ row[col.field] = checked }) }); return this }; obj.uncheckAll = function(){ obj.checkAll(false); return this }
	obj.checking = function(posIdList){ var data = []; $.map(obj.elem.bootstrapTable("getData"), function(o, i){ $.map(posIdList,(function(v,j){if(v == o.id){o.checked = true;}})); data[i] = o; }); obj.rawData(data); }
	obj.elem.on("check.bs.table uncheck.bs.table check-all.bs.table uncheck-all.bs.table", window[el.substring(1) +"CheckBoxEvent"])
	}
	(function(){ $(window["setup"]) })();
		new DropDown("#principal").init("../findPrincipalList.json");
		new DropDown("#shop").init("../findShopList.json");
		self.posTable = new BootstrapTable("#posTable");
	return this;
})(jQuery);
$(document).on("click", "a#searchOfficer", function() {
	runningMsgHide(); 
	if($('#codeForSh').val() == '' && $('#userNameForSh').val() == '' && $('#givenNameForSh').val() == '' && $('#familyNameForSh').val() == '') {
		if($('#codeForSh').val() == '') { $('#codeForSh').attr("style", "color:#a94442"); $("#codeForSh").attr("style", "background-color:#ffffaa;border:solid 1px #a94442;padding:0 10px"); }
		if($('#userNameForSh').val() == '') { $('#userNameForSh').attr("style", "color:#a94442"); $("#userNameForSh").attr("style", "background-color:#ffffaa;border:solid 1px #a94442;padding:0 10px"); }
		if($('#givenNameForSh').val() == '') { $('#givenNameForSh').attr("style", "color:#a94442"); $("#givenNameForSh").attr("style", "background-color:#ffffaa;border:solid 1px #a94442;padding:0 10px"); }
		if($('#familyNameForSh').val() == '') { $('#familyNameForSh').attr("style", "color:#a94442"); $("#familyNameForSh").attr("style", "background-color:#ffffaa;border:solid 1px #a94442;padding:0 10px"); }
			return false;
	} else {

		$('#displayOfficerPanel').removeClass('hide');  $('#createOfficerPanel').addClass('hide'); 
		$('#codeForSh').removeAttr("style"); $('#userNameForSh').removeAttr("style"); $('#givenNameForSh').removeAttr("style"); $('#familyNameForSh').removeAttr("style");
// 		$("#officerTable").bootstrapTable("refresh", { url: "../findOfficerByBranchAttribute.json?code="+ $("#codeForSh").val() +"&userName="+ $("#userNameForSh").val() +"&givenName="+ $("#givenNameForSh").val() +"&familyName="+ $("#familyNameForSh").val() });
// 		setTimeout(function () { $("#officerTable").bootstrapTable("resetView"); }, 200);

		var dataSend={
			code:$("#codeForSh").val()
			,name:$("#userNameForSh").val()
			,givenName:$("#givenNameForSh").val()
			,familyName:$("#familyNameForSh").val()
		};
		$.ajax({
			type: "POST",
			url: "../findOfficerByBranchAttribute.json",
			data: JSON.stringify(dataSend),
			dataType: "json",
			contentType: "application/json; charset=utf-8",
				success:function(data){
					$("#officerTable").bootstrapTable("load", data); 
			}
		});
	}
}).on("click", "a#createOfficer", function() {
	runningMsgHide(); 
	$('#searchOfficerPanel').addClass('hide'); $('#displayOfficerPanel').addClass('hide');  $('#createOfficerPanel').removeClass('hide');
	$('#codeForSh').removeAttr("style"); $('#userNameForSh').removeAttr("style"); $('#givenNameForSh').removeAttr("style"); $('#familyNameForSh').removeAttr("style");
	$("#id").val(''); $("#code").val(''); $("#isPositive").val(''); $("#userName").val(''); $("#principal").val(''); $("#givenName").val(''); $("#familyName").val(''); $("#password").val(''); $("#passwordAi").val(''); $("#description").val(''); $("#verifyFlag").prop('checked', false);$("#shop").val('-1'); view.posTable.rawData([]);
}).on("click", "a#backToSearch", function() {
	runningMsgHide(); 
	$('#searchOfficerPanel').removeClass('hide'); $('#displayOfficerPanel').addClass('hide');  $('#createOfficerPanel').addClass('hide');		
	$('#codeForSh').removeAttr("style"); $('#userNameForSh').removeAttr("style"); $('#givenNameForSh').removeAttr("style"); $('#familyNameForSh').removeAttr("style"); 
}).on("click", "a#resetSearch", function() {
	runningMsgHide(); 
	$('#codeForSh').removeAttr("style"); $('#userNameForSh').removeAttr("style"); $('#givenNameForSh').removeAttr("style"); $('#familyNameForSh').removeAttr("style");
	$('#codeForSh').val(""); $('#userNameForSh').val(""); $('#givenNameForSh').val(""); $('#familyNameForSh').val("");
}).on("click", "a#resetCreate", function() {
	runningMsgHide(); 
	$('#code').removeAttr("style"); $('#userName').removeAttr("style"); $('#principal').removeAttr("style"); $('#givenName').removeAttr("style"); $('#familyName').removeAttr("style"); $('#password').removeAttr("style");
	$('#createOfficerFm')[0].reset();
}).on("click", "a#saveOfficer", function() { 
	runningMsgHide(); 
	if($('#code').val() == '' && $('#userName').val() == '' && $('#principal').val() == null && $('#givenName').val() == '' && $('#familyName').val() == '' && $('#password').val() == '' && $('#passwordAi').val() == '') {
		if($('#code').val() == '') { $('#code').attr("style", "color:#a94442"); $("#code").attr("style", "background-color:#ffffaa;border:solid 1px #a94442;padding:0 10px"); }
		if($('#userName').val() == '') { $('#userName').attr("style", "color:#a94442"); $("#userName").attr("style", "background-color:#ffffaa;border:solid 1px #a94442;padding:0 10px"); }
		if($('#principal').val() == null) { $('#principal').attr("style", "color:#a94442"); $("#principal").attr("style", "background-color:#ffffaa;border:solid 1px #a94442;padding:0 10px"); }
		if($('#givenName').val() == '') { $('#givenName').attr("style", "color:#a94442"); $("#givenName").attr("style", "background-color:#ffffaa;border:solid 1px #a94442;padding:0 10px"); }
		if($('#familyName').val() == '') { $('#familyName').attr("style", "color:#a94442"); $("#familyName").attr("style", "background-color:#ffffaa;border:solid 1px #a94442;padding:0 10px"); }
		if($('#password').val() == '') { $('#password').attr("style", "color:#a94442"); $("#password").attr("style", "background-color:#ffffaa;border:solid 1px #a94442;padding:0 10px"); }
		if($('#passwordAi').val() == '') { $('#passwordAi').attr("style", "color:#a94442"); $("#passwordAi").attr("style", "background-color:#ffffaa;border:solid 1px #a94442;padding:0 10px"); }
			return false;
	} else { $('#code').removeAttr("style"); $('#userName').removeAttr("style"); $('#principal').removeAttr("style"); $('#givenName').removeAttr("style"); $('#familyName').removeAttr("style"); $('#password').removeAttr("style");
		var favorite = [];
		if($('#password').val() != $('#passwordAi').val()) {
			$('#alertPasswordAreaMsg').removeClass('hide');
			return false;
		} else {
			var posList = view.posTable.getSelections();
            if (posList.length == 0) {
            	$('#alertShopAndPosAreaMsg').removeClass('hide');
        		return false;
        	} else {
        		var posListx = view.posTable.rawData();
        		for (var i = 0, m = posList.length; i < m; i++) {
        			favorite.push(posList[i]["id"]);
        		} // alert("My favourite sports are: " + favorite.join("|"));
        	}
			var dataPrincipal = {id: $("#principal").val(), name : ""};
			var dataSend={
				id:$("#id").val(),	
				code:$("#code").val(),
				name:$("#userName").val(),
				principal:dataPrincipal,
				givenName:$("#givenName").val(),
				familyName:$("#familyName").val(),
				isPositive:$("#isPositive").val(),
				description:$("#password").val()+":#1"+ $("#shop").val() +"|"+ favorite.join("|") +":#2"+$("#description").val(),
				verifyFlag:$("#verifyFlag").is(':checked')?"Y":"N"
	 		};
			$.ajax({
				type: "POST",
				url: "../saveOfficerByBranchAttribute.json",
				data: JSON.stringify(dataSend),
				dataType: "json",
				contentType: "application/json; charset=utf-8",
					success:function(data){
					if(data != 0 ){
						$('#createOfficerFm')[0].reset();
						$("#shop").val('-1');
						view.posTable.rawData([]);
						$('#alertSuccessAreaMsg').removeClass('hide');
						$('#alertDangerAreaMsg').addClass('hide');
						$('#alertPasswordAreaMsg').addClass('hide');
						$('#alertShopAndPosAreaMsg').addClass('hide');
						$('#alertDuplicateMsg').addClass('hide');
					}else{
						$('#alertDuplicateMsg').removeClass('hide');
					}
				},
		        	error: function(jqxhr) {
		        		$('#alertDangerAreaMsg').removeClass('hide');
		        		$('#alertSuccessAreaMsg').addClass('hide');
		        		$('#alertPasswordAreaMsg').addClass('hide');
		        		$('#alertShopAndPosAreaMsg').addClass('hide');
		        		$('#alertDuplicateMsg').addClass('hide');
		        }
			});
		}
	}
});
$('#shop').on('change', function() {
	$("#posTable").bootstrapTable("refresh", { url: "../findPosByShopAttribute.json?shop="+ $("#shop").val() });
	setTimeout(function () { $("#posTable").bootstrapTable("resetView"); }, 200);
});
</script>
</html>

