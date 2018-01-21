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
	var criteriaSelectorArray = ['#categoryCrt'
						         ,'#codeCrt'
						         ,'#mapCode1Crt'
						         ,'#mapCode2Crt'
						         ,'#mapCode3Crt'
						         ,'#mapCode4Crt'
						         ,'#descAbvrEnCrt'
						         ,'#descAbvrThCrt'
						         ,'#descFullEnCrt'
						         ,'#descFullThCrt'];
	
	var paramSelectorArray = ['#category'
						         ,'#code'
						         ,'#mapCode1'
						         ,'#mapCode2'
						         ,'#mapCode3'
						         ,'#mapCode4'
						         ,'#descAbvrEn'
						         ,'#descAbvrTh'
						         ,'#descFullEn'
						         ,'#descFullTh'
	//						         ,'#isPositive'
						         ,'#id'];
	
// 	function runningFormatter(val, row, ind) { return ind + 1; }
	function runningMsgHide() { $('#alertSuccessAreaMsg').addClass('hide'); $('#alertDangerAreaMsg').addClass('hide'); }
	function isPositiveFormatter(val) {var target; if("0" == val) {target = "ระงับใช้งาน";} else if("1" == val) { target = "เปิดใช้งาน";} return target; }
// 	function principalFormatter(val) { return val.name; }
	function rowsFormatter(id) {return '<a id="editData" style="cursor:pointer" onClick="findDetailById(\''+ id +'\')" class="btn-sm btn-info"><i class="glyphicon glyphicon-edit"></i> แก้ไข</a>'; }
	function findDetailById(id) {
		$.get("../findEnumById.json", { "id": id }, function(res) {
			hidePanel('#searchPanel');
			hidePanel('#displayPanel');
			showPanel('#addOrEditPanel');
			$('#resetParam').addClass('disabled');

// 			$("#id").val(res.data[0].id); 
// 			$("#category").val(res.data[0].category); 
// 			$("#code").val(res.data[0].code); 
// 			$("#mapCode1").val(res.data[0].mapCode1); 
// 			$("#mapCode2").val(res.data[0].mapCode2); 
// 			$("#isPositive").val(res.data[0].isPositive); 
			
			setParamValue(paramSelectorArray, res.data[0]);
		});
	}	
// 	function idFormatter(id) {
// 		return '<input type="hidden" id="id" name="id" value="'+ id +'"/>';
// 	}
	function loadCategory() {
		$.get("../loadAllCategoryGroup.json", function(res) {
			$("#categoryCrt option").remove(); 
			$("#category option").remove();
			$("#categoryCrt").append($("<option></option>").text("- กรุณาเลือก -").val(""));
			$("#category").append($("<option></option>").text("- กรุณาเลือก -").val(""));
			$.each(res, function(index, item) {
				$("#categoryCrt").append($("<option></option>").text(item).val(item));
				$("#category").append($("<option></option>").text(item).val(item));
			});
		});
	}
	
	function clearCriteriaStyle(){
		clearStyle(criteriaSelectorArray);
	}
	function clearParamStyle(){
		clearStyle(paramSelectorArray);
	}
	function setCriteriaWarningStyle(){
		setWarningStyle(criteriaSelectorArray);
	}
	function clearCriteriaValue(){
		clearValue(criteriaSelectorArray);
	}
	function clearParamValue(){
		clearValue(paramSelectorArray);
	}
	function isCriteriaEmptyValue(){
		return isEmptyValue(criteriaSelectorArray);
	}
	function isParamEmptyValue(){
		return isEmptyValue(paramSelectorArray);
	}
	function getCriteriaDataForSend(selectorArray){
		var dataSend = {};
		$.each(selectorArray, function(index, selector) {
			dataSend[selector.replace('#','').replace('Crt','')] = $(selector).val();
		});
		return dataSend;
	}
	function getParamDataForSend(selectorArray){
		var dataSend = {};
		$.each(selectorArray, function(index, selector) {
			dataSend[selector.replace('#','')] = $(selector).val();
		});
		return dataSend;
	}
	function isParamEmptyRequireField(){
		var paramRequireField = paramSelectorArray.slice();
		var index = paramRequireField.indexOf('#id');
		if (index > -1) {
			paramRequireField.splice(index, 1);
		}
		return isEmptyRequireField(paramRequireField);
	}
	function setParamValue(selectorArray, data){
		$.each(selectorArray, function(index, selector) {
			$(selector).val( eval('data.'+selector.replace('#','')) );
		});
	}
	
	function clearStyle(selectorArray){
		$.each(selectorArray, function(index, selector) {
			$(selector).removeAttr("style");
		});
	}
	function setWarningStyle(selectorArray){
		$.each(selectorArray, function(index, selector) {
			$(selector).attr("style", "color:#a94442"); 
			$(selector).attr("style", "background-color:#ffffaa;border:solid 1px #a94442;padding:0 10px");
		});
	}
	function clearValue(selectorArray){
		$.each(selectorArray, function(index, selector) {
			$(selector).val('');
		});
	}
	function isEmptyValue(selectorArray){
		var result = true;
		$.each(selectorArray, function(index, selector) {
			if($.trim($(selector).val()) != ''){
				result = false;
			}
		});
		return result;
	}
	function isEmptyRequireField(selectorArray){
		var emptyList = [];
		$.each(selectorArray, function(index, selector) {
			if($.trim($(selector).val()) == ''){
				emptyList.push(selector);
			}
		});
		if(emptyList.length > 0){
			setWarningStyle(emptyList);
			return true;
		}else{
			return false;
		}
	}
	function hidePanel(selector){
		$(selector).addClass('hide');  
	}
	function showPanel(selector){
		$(selector).removeClass('hide');
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
                    <li class="active"><i>ข้อมูลผู้ใช้งาน</i></li>
                </ol>
	        </div>
	    </div>
		<div class="row">
			<div class="col-md-12 tab-modefile">
				<div id="alertSuccessAreaMsg" class="hide alert alert-success"><span class="glyphicon glyphicon-ok-sign"></span> บันทึกเรียบร้อย</div>
				<div id="alertDangerAreaMsg" class="hide alert alert-danger"><span class="glyphicon glyphicon-remove-sign"></span> เกิดข้อผิดพลาด</div>
				<!-- Search -->
				<div id="searchPanel">
				<ul class="nav nav-tabs" role="tablist">
					<li role="presentation" class="active"><a href="#"><span class="glyphicon glyphicon-filter"></span> ค้นหาข้อมูลผู้ใช้งาน</a></li>
				</ul>
				<div class="panel panel-default panal-radius">
					<div class="panel-body">
						<div class="tab-content">
							<div role="tabpanel" class="tab-pane active" id="tab_1">
								<div class="form-horizontal">
									<div class="form-group">
										<label class="control-label col-sm-2">Category :</label>
										<div class="col-sm-2">
											<select class="form-control" id="categoryCrt" name="categoryCrt"></select>
										</div>
										<label class="control-label col-sm-2">Message Code :</label>
										<div class="col-sm-2"><input class="form-control" id="codeCrt" name="codeCrt"></div>
										<div class="col-sm-4"></div>
									</div>
									<div class="form-group">
										<label class="control-label col-sm-2">Map Code 1 :</label>
										<div class="col-sm-2"><input class="form-control" id="mapCode1Crt" name="mapCode1Crt"></div>
										<label class="control-label col-sm-2">Map Code 2 :</label>
										<div class="col-sm-2"><input class="form-control" id="mapCode2Crt" name="mapCode2Crt"></div>
										<div class="col-sm-4"></div>
									</div>
									<div class="form-group">
										<label class="control-label col-sm-2">Map Code 3 :</label>
										<div class="col-sm-2"><input class="form-control" id="mapCode3Crt" name="mapCode3Crt"></div>
										<label class="control-label col-sm-2">Map Code 4 :</label>
										<div class="col-sm-2"><input class="form-control" id="mapCode4Crt" name="mapCode4Crt"></div>
										<div class="col-sm-4"></div>
									</div>
									<div class="form-group">
										<label class="control-label col-sm-2">Description (EN) :</label>
										<div class="col-sm-2"><input class="form-control" id="descAbvrEnCrt" name="descAbvrEnCrt"></div>
										<label class="control-label col-sm-2">Description (TH) :</label>
										<div class="col-sm-2"><input class="form-control" id="descAbvrThCrt" name="descAbvrThCrt"></div>
										<div class="col-sm-4"></div>
									</div>
									<div class="form-group">
										<label class="control-label col-sm-2">Description Full (EN) :</label>
										<div class="col-sm-2"><input class="form-control" id="descFullEnCrt" name="descFullEnCrt"></div>
										<label class="control-label col-sm-2">Description Full (TH) :</label>
										<div class="col-sm-2"><input class="form-control" id="descFullThCrt" name="descFullThCrt"></div>
										<div class="col-sm-4">
											<a id="search" class="btn btn-primary"><span class="glyphicon glyphicon-search"></span> ค้นหาข้อมูล</a>&nbsp;
											<a id="resetCriteria" class="btn btn-danger"><span class="glyphicon glyphicon-minus-sign"></span> ยกเลิก</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<a id="create" class="btn btn-success"><span class="glyphicon glyphicon-file"></span> เพิ่มรายการ</a>
										</div>
									</div>
								</div>
	                   		</div>
						</div> 
					</div>	
				</div>
				</div>
				<!-- Display -->
				<div id="displayPanel" class="hide tab-content">
					<div role="tabpanel" class="tab-pane active" id="tab-2-1">
					<table id="searchResultTable" data-toggle="table" data-toolbar="#glToolbar" data-search="true" data-show-pagination-switch="true" data-show-refresh="true" data-show-toggle="true" data-show-columns="true" data-show-export="true" data-pagination="true" data-height="400" data-cache="false">
                       <thead>
                           <tr>
                           		<th data-field="category" data-align="center">Category</th>
								<th data-field="code" data-align="left">Message Code</th>
								<th data-field="mapCode1" data-align="left">Map Code 1</th>
								<th data-field="mapCode2" data-align="left">Map Code 2</th>
								<th data-field="mapCode3" data-align="left">Map Code 3</th>
								<th data-field="mapCode4" data-align="left">Map Code 4</th>
								<th data-field="descAbvrEn" data-align="center">Description (EN)</th>
								<th data-field="descAbvrTh" data-align="center">Description (TH)</th>
								<th data-field="descFullEn" data-align="center">Description Full (EN)</th>
								<th data-field="descFullTh" data-align="center">Description Full (TH)</th>
								<th data-field="isPositive" data-formatter="isPositiveFormatter" data-align="center">สถานะ</th>
								<th data-field="id" data-formatter="rowsFormatter" data-align="center"></th>
							</tr>
                       </thead>
                   </table>
                   </div>
                </div>
				<!-- Create -->
				<div id="addOrEditPanel" class="hide">
				<ul class="nav nav-tabs" role="tablist">
					<li role="presentation" class="active"><a href="#"><span class="glyphicon glyphicon-list"></span> เพิ่มข้อมูลผู้ใช้งาน</a></li>
				</ul>
				<div class="panel panel-default panal-radius">
					<form id="addOrEditForm" method="post" class="form-horizontal" role="form">
					<div class="panel-body">
						<div class="tab-content">
							<div role="tabpanel" class="tab-pane active" id="tab_1">
								<div class="form-horizontal">
									<div class="form-group">
										<label class="control-label col-sm-2">Category :</label>
										<div class="col-sm-2">
											<select class="form-control" id="category" name="category"></select>
										</div>
										<label class="control-label col-sm-2">Message Code :</label>
										<div class="col-sm-2"><input class="form-control" id="code" name="code"></div>
										<div class="col-sm-4"></div>
									</div>
									<div class="form-group">
										<label class="control-label col-sm-2">Map Code 1 :</label>
										<div class="col-sm-2"><input class="form-control" id="mapCode1" name="mapCode1"></div>
										<label class="control-label col-sm-2">Map Code 2 :</label>
										<div class="col-sm-2"><input class="form-control" id="mapCode2" name="mapCode2"></div>
										<div class="col-sm-4"></div>
									</div>
									<div class="form-group">
										<label class="control-label col-sm-2">Map Code 3 :</label>
										<div class="col-sm-2"><input class="form-control" id="mapCode3" name="mapCode3"></div>
										<label class="control-label col-sm-2">Map Code 4 :</label>
										<div class="col-sm-2"><input class="form-control" id="mapCode4" name="mapCode4"></div>
										<div class="col-sm-4"></div>
									</div>
									<div class="form-group">
										<label class="control-label col-sm-2">Description (EN) :</label>
										<div class="col-sm-2"><input class="form-control" id="descAbvrEn" name="descAbvrEn"></div>
										<label class="control-label col-sm-2">Description (TH) :</label>
										<div class="col-sm-2"><input class="form-control" id="descAbvrTh" name="descAbvrTh"></div>
										<div class="col-sm-4"></div>
									</div>
									<div class="form-group">
										<label class="control-label col-sm-2">Description Full (EN) :</label>
										<div class="col-sm-2"><input class="form-control" id="descFullEn" name="descFullEn"></div>
										<label class="control-label col-sm-2">Description Full (TH) :</label>
										<div class="col-sm-2"><input class="form-control" id="descFullTh" name="descFullTh"></div>
										<div class="col-sm-4"></div>
									</div>
									<div class="form-group">
										<label class="control-label col-sm-2">สถานะ :</label>
										<div class="col-sm-2">
											<select class="form-control" id="isPositive" name="isPositive">
                        						<option value="">- กรุณาเลือก -</option>
                        						<option value="1">เปิดใช้งาน</option>
                        						<option value="0">ระงับใช้งาน</option>
                        					</select>	
										</div>
										<div class="col-sm-4"></div>
										<div class="col-sm-4">
											<a id="save" class="btn btn-primary"><span class="glyphicon glyphicon-plus-sign"></span> บันทึกข้อมูล</a>&nbsp;
											<a id="resetParam" class="btn btn-danger"><span class="glyphicon glyphicon-minus-sign"></span> ยกเลิก</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<a id="backToSearch" class="btn btn-success"><span class="glyphicon glyphicon-search"></span> ค้นหาข้อมูล</a>
										</div>
										<input type="hidden" id="id" name="id" value=""/>
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
	obj.elem.on("check.bs.table uncheck.bs.table check-all.bs.table uncheck-all.bs.table", window[el.substring(1) +"CheckBoxEvent"])
	}
	(function(){ $(window["setup"]) })();
// 		new DropDown("#categoryCrt").init("../loadPaymentGlCategoryGroup.json");
// 		new DropDown("#principal").init("../findPrincipalList.json");
// 		new DropDown("#shop").init("../findShopList.json");
// 		self.posTable = new BootstrapTable("#posTable");
	return this;
})(jQuery);
$(document).on("click", "a#search", function() {
	runningMsgHide(); 
	if(isCriteriaEmptyValue()) {
		setCriteriaWarningStyle();
		return false;
	} else {
		hidePanel('#addOrEditPanel');
		showPanel('#displayPanel');
		clearCriteriaStyle();
		
		var dataSend = getCriteriaDataForSend(criteriaSelectorArray);
		$.ajax({
			type: "POST",
			url: "../findCategoryDetailByCriteria.json",
			data: JSON.stringify(dataSend),
			dataType: "json",
			contentType: "application/json; charset=utf-8",
				success:function(data){
					$("#searchResultTable").bootstrapTable("load", data); 
			}
		});
	}
}).on("click", "a#create", function() {
	runningMsgHide(); 
	hidePanel('#searchPanel');
	hidePanel('#displayPanel');
	showPanel('#addOrEditPanel');
	clearCriteriaStyle();
	clearParamValue();
}).on("click", "a#backToSearch", function() {
	runningMsgHide(); 
	hidePanel('#displayPanel');
	hidePanel('#addOrEditPanel');
	showPanel('#searchPanel');
	clearCriteriaStyle();
}).on("click", "a#resetCriteria", function() {
	runningMsgHide(); 
	clearCriteriaStyle();
	clearCriteriaValue();
}).on("click", "a#resetParam", function() {
	runningMsgHide(); 
	clearParamStyle();
	$('#addOrEditForm')[0].reset();
}).on("click", "a#save", function() { 
	runningMsgHide(); 
	if(isParamEmptyRequireField()) {
		return false;
	} else { 
		clearParamStyle();
		var dataSend = getParamDataForSend(paramSelectorArray);
		$.ajax({
			type: "POST",
			url: "../saveEnum.json",
			data: JSON.stringify(dataSend),
			dataType: "json",
			contentType: "application/json; charset=utf-8",
				success:function(data){
					$('#addOrEditForm')[0].reset();
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

loadCategory();
</script>
</html>

