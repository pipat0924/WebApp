<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>ประวัติการรับชำระค่าบริการอื่นๆ</title>
	<link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
	<link href="resources/bootstrap-table/src/bootstrap-table.css" rel="stylesheet" type="text/css"/>
	<link href="resources/custom.css" rel="stylesheet" type="text/css"/>
	<script src="resources/jquery.min.js" type="text/javascript"></script>
	<script src="resources/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
	<script src="resources/bootstrap-table/src/bootstrap-table.js"></script>
	<script src="resources/js/mustache.min.js" type="text/javascript"></script>
	<script src="resources/custom.js" type="text/javascript"></script>
</head>
<body>
<header class="header_page"></header>
<section class="container-fluid menu">
	<!-- <button class="menu-toggle btn btn-sm btn-default" style="margin-bottom: 5px"><span class="glyphicon glyphicon-step-forward"></span></button> -->
	<%-- <%@include  file="menu.jsp" %> --%>
	<div class="row">
		<div class="col-md-12 tab-modefile">
			<ol class="breadcrumb"><li><i>ประวัติการรับชำระค่าบริการอื่นๆ</i></li></ol>
			<div id="mainMessageDialog"></div>
			<ul class="nav nav-tabs" role="tablist">
				<li role="presentation" class="active"><a aria-controls="tab_cus" role="tab" data-toggle="tab"><span class="glyphicon glyphicon-filter"></span> ค้นหาข้อมูล</a></li>
			</ul>
			<div class="panel panel-default panal-radius">
				<div class="panel-body">
					<div class="row">
						<div class="col-md-12">
							<div class="form-horizontal">
								<label class="control-label col-sm-2">เลขที่ลูกค้า (Customer Account) :</label>
								<div class="col-sm-2"><input class="form-control" id="billNo"></div>
								<div class="col-sm-4">
									<a id="search" class="btn btn-primary"><span class="glyphicon glyphicon-search"></span> ค้นหา</a> &nbsp;&nbsp;
									<a id="advanceSearch" class="btn btn-success" data-toggle="modal" href="#advanceSearchDialog"><span class="glyphicon glyphicon-zoom-in"></span> ค้นหาเพิ่มเติม</a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div id="customerPanel" class="col-md-12 tab-modefile hide">
			<ul class="nav nav-tabs" role="tablist">
				<li role="presentation" class="active"><a aria-controls="tab_cus" role="tab" data-toggle="tab"><span class="glyphicon glyphicon-user"></span> ข้อมูลลูกค้า</a></li>
			</ul>
			<div class="tab-content">
				<div role="tabpanel" class="tab-pane active" id="tab_cus">
					<div class="panel panel-default panal-radius">
						<div class="panel-body">
							<div class="row">
								<div class="col-md-12">
									<div class="form-horizontal">
										<div class="form-group">
											<label class="control-label col-sm-2">เลขที่ลูกค้า :</label>
											<div class="col-sm-2"><input id="custNo" class="form-control" disabled="disabled"></div>
											<label class="control-label col-sm-2">ชื่อลูกค้า :</label>
											<div class="col-sm-5"><input id="custName" class="form-control" disabled="disabled"></div>
											<input type="hidden" id="inputCustomerType">
										</div>

										<div class="form-group">
											<label class="control-label col-sm-2">Tax ID :</label>
											<div class="col-sm-2"><input id="taxNo" maxlength="13" class="form-control" disabled="disabled"></div>
											<label class="control-label col-sm-2">สาขา :</label>
											<div class="col-sm-2"><input id="branch" maxlength="5" class="form-control" disabled="disabled"></div>
										</div>

										<div class="form-group">
											<label class="control-label col-sm-2">กลุ่มผู้ใช้บริการ :</label>
											<div class="col-sm-2">
												<select class="form-control" id="inputCustomerSegment" disabled="disabled"></select>
											</div>
											<label class="control-label col-sm-2">VAT Rate :</label>
											<div class="col-sm-2">
												<select id="inputCustomerVatRate" class="form-control" disabled="disabled">
													<option>7</option>
													<option>0</option>
													<option>Non VAT</option>
												</select>
											</div>
										</div>
										<div class="form-group">
											<label class="control-label col-sm-2">ที่อยู่ :</label>
											<div class="col-sm-6"><textarea id="receiptAddress" class="form-control" disabled="disabled"></textarea></div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="invoicePanel" class="hide row">
		<div class="col-md-12 tab-modefile">
			<ul id="invoiceDetailsTab" class="nav nav-tabs" role="tablist">
				<li role="presentation" class="active"><a href="#tab-1" aria-controls="tab-1" role="tab" data-toggle="tab"><span class="glyphicon glyphicon-th-list"></span> ประวัติการรับชำระ</a></li>
			</ul>
			<div class="tab-content">
				<div role="tabpanel" class="tab-pane active" id="tab-1">
					<table id="historyList" data-row-style="rowStyle" data-toggle="table"  data-maintain-selected="true" class="table table-hover" data-detail-view="true" data-detail-formatter="receiptsServiceFormatter" data-pagination="true" data-page-size="10" data-page-list="[10, 20, 50, 100, 200]" data-cache="false">
						<thead>
						<tr>
							<th data-formatter="runningFormatter">#</th>
							<th data-field="updateDt">วันที่ทำ<br>รายการ</th>
							<th data-field="receiptDt">วันที่ออกใบ<br>เสร็จรับเงิน</th>
							<th data-field="receiptNo">เลขที่ใบ<br>เสร็จรับเงิน</th>
							<th data-field="branch">สถานที่<br>รับชำระ</th>
							<th data-field="user">ผู้รับชำระ</th>
							<%--<th data-field="billCycle" data-align="center" data-formatter="serviceTypesFormatter">ชื่อบริการ</th>--%>
							<th data-field="totalPaid" data-align="right" data-formatter="numberFormatter">ยอดเงิน<br>ค่าบริการ</th>
							<th data-field="discount" data-align="right" >ส่วนลด</th>
							<th data-field="excDiscount" data-align="right" >ส่วนลด<br>พิเศษ</th>
							<th data-field="payMethod">วิธีการ<br>รับชำระ</th>
							<!--<th data-field="payType">ประเภทการ<br>รับชำระ</th>--> <!--Add this if want to show ประเภทการรับชำระ-->
							<th data-field="totalPaid" data-align="right" data-formatter="numberFormatter">ยอดชำระ</th>
							<th data-field="status">สถานะ</th>
							<th data-formatter="remarkIconFormatter" data-align="center">หมายเหตุ</th>
						</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
	</div>
</section>
<div class="modal fade" role="dialog" id="remarkModal">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title">หมายเหตุ</h4>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="col-md-12">
						<span id="remark"></span>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<a class="btn btn-default" data-dismiss="modal"><span class="glyphicon glyphicon-ok-circle"></span> Ok</a>
			</div>
		</div>
	</div>
</div>

<div id="advanceSearchDialog" class="modal fade" role="dialog">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title">ค้นหาลูกค้า</h4>
			</div>
			<div class="modal-body">
				<div id="advanceSearchMessageDialog"></div>
				<div class="tab-modefile">
					<ul class="nav nav-tabs" role="tablist">
						<li role="presentation" class="active"><a href="#messages" aria-controls="messages" role="tab" data-toggle="tab">ข้อมูลลูกค้า</a></li>
					</ul>
					<div class="panel panel-default">
						<div class="panel-body">
							<div class="tab-content border-tab-content">
								<div role="tabpanel" class="tab-pane active" id="messages">
									<div class="form-horizontal">
										<div class="form-group">
											<label class="control-label col-sm-3">เลขที่ลูกค้า (CA):</label>
											<div class="col-sm-3"><input class="form-control" id="advSrcCusNo"></div>
											<label class="control-label col-sm-3">เลขประจำตัวผู้เสียภาษี :</label>
											<div class="col-sm-3"><input class="form-control" id="advSrcCusTaxId"></div>
										</div>
										<div class="form-group">
											<label class="control-label col-sm-3">ชื่อลูกค้า/ชื่อนิติบุคคล/ราชการ :</label>
											<div class="col-sm-9"><input class="form-control" id="advSrcCusFirstName"></div>
											<!--
                                            <label class="control-label col-sm-3">นามสกุล :</label>
                                            <div class="col-sm-3"><input class="form-control" id="advSrcCusLastName"></div>
                                            -->
										</div>
										<div class="form-group">
											<label class="control-label col-sm-3">กลุ่มผู้ใช้บริการ :</label>
											<div class="col-sm-3">
												<select class="form-control" id="customerSegmentSearch"></select>
											</div>
											<div class="col-sm-3">
												<a id="advSrcCusNoBtn" class="btn btn-info pull-right"><span class="glyphicon glyphicon-search"></span> ค้นหา</a>
											</div>
										</div>
									</div>

									<br>

									<div class="row">
										<div class="col-md-12">
											<table id="advSrcCusNoResultList" class="table table-hover" data-toggle="table" data-pagination="true" data-page-size="5" data-page-list="[5, 10, 20, 50, 100, 200]" data-cache="false">
												<thead>
												<tr>
													<th data-formatter="runningFormatter">#</th>
													<th data-field="customerId">เลขที่ลูกค้า (CA)</th>
													<th data-field="customerAccountName">ชื่อลูกค้า</th>
													<th data-field="customerTaxNo">TAX ID</th>
													<th data-field="customerSegment">กลุ่มผู้ใช้บริการ</th>
													<th data-align="center" data-formatter="SelectButton"></th>
												</tr>
												</thead>
											</table>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<i class="pull-left"><span class="glyphicon glyphicon-filter"></span>
					กรุณาป้อนข้อมูลที่ต้องการค้นหาอย่างน้อย 4 ตัวอักษร
				</i>
				<br>
			</div>
		</div>
	</div>
</div>

<div id="status_print" class="hidden">
	<div style="float: none; margin: 0 auto;">
		<div class="panel panel-default vertical-center panel_status_print ">
			<div class="panel-heading">พิมพ์</div>
			<div class="panel-body">
				<h4> กำลังพิมพ์ใบเสร็จรับเงิน...</h4>
			</div>
		</div>
	</div>
</div>
<script id="template" type="x-tmpl-mustache">
	<table  class="table table-striped table-bordered" style="width:98% !important">
		<thead>
			<tr>
				<th class="text-center">ประเภทบริการ</th>
				<th class="text-center">ชื่อบริการ</th>
				<th class="text-center">จำนวนรายการ</th>
				<th class="text-center">จำนวนเงินต่อหน่วย</th>
				<th class="text-center">ภาษีมูลค่าเพิ่ม</th>
				<th class="text-center">ส่วนลด</th>
				<th class="text-center">ภาษีหัก ณ ที่จ่าย</th>
				<th class="text-center">ยอดเงินรวม (ก่อนส่วนลดพิเศษ)</th>
			</tr>
		</thead>
		<tbody>
		{{#receipts}}
			<tr>
			<td>{{serviceName}}</td>
			<td>{{productName}}</td>
            <td class="text-center">{{serviceQty}}</td>
            <td class="text-right">{{amountPerUnit}}</td>
            <td class="text-right">{{vat}}</td>
            <td class="text-right">{{discount}}</td>
            <td class="text-right">{{deduction}}</td>
            <td class="text-right">{{totalCharge}}</td>
			</tr>
		{{/receipts}}
		</tbody>
	</table>
	</script>

</body>
<script type="text/javascript">
    var view = (function($){
        var self = this, defaultErrorMessage = "An error occurred but there is no message response.";
        ////AUTOMATIC REGISTER FORMATTER FUNCTION ////
        window.get = function(url, params, success, msgDialog, preCheck){ $.ajax({ "url": url, "type": "GET", "data": params, "error": (msgDialog || { "valid": function(jqXHR, textStatus, errorThrow){ console.log(jqXHR); console.log("textStatus: "+ textStatus); console.log("errorThrow:"); console.log(errorThrow) } }).valid, "success": function(res){ var isNotJson = res.constructor == String; console.log(res); (preCheck || function(o){})(res); if (isNotJson) res = { "statusCode": "99", "errorList": ["No matching data found."] }; if (msgDialog && !msgDialog.valid(res)) { return; } success(res) } }) }
        window.getSync = function(url, params, success, msgDialog, preCheck){ $.ajax({ "url": url, "type": "GET", "data": params, "async": false, "error": (msgDialog || { "valid": function(jqXHR, textStatus, errorThrow){ console.log(jqXHR); console.log("textStatus: "+ textStatus); console.log("errorThrow:"); console.log(errorThrow) } }).valid, "success": function(res){ var isNotJson = res.constructor == String; console.log(res); var isCont = (preCheck || function(o){ return true; })(res); if(isCont) {if (isNotJson) res = { "statusCode": "99", "errorList": ["No matching data found."] }; if (msgDialog && !msgDialog.valid(res)) { return; } success(res) } }}) };
        //window.LoadingPanel = function(id) { var obj = this, loadCnt = 0, setupFunc, loadFunc; function checkElemReady(){ if ((obj.elem = $("#"+ id).css("font", "")).length != 1) { alert("The element hasn't insert into HTML DOM."); throw "The element hasn't insert into HTML DOM."; } clearTimeout(setupFunc); clearInterval(loadFunc) }; return { "elem": null, "finish": function(html){ checkElemReady(); (this.elem ? this.elem : this.elem = $("#"+ id)).css("font", "").html(html); return this }, "toString": function(){ var elem; setupFunc = setTimeout(function(){ loadFunc = setInterval(function(){ (elem || (elem = document.getElementById(id))).innerHTML = "Loading"+ Array(++loadCnt).join(".."); if (loadCnt > 60) loadCnt = 2 }, 250) }, 1000); return "<div id='"+ id +"' style='font:BOLD 16pt Arial'></div>" } } }
        window.LoadingPanel =
            function(id) {
                var obj = this, loadCnt = 0, setupFunc, loadFunc;
                console.log("=============nn=============");
                console.log(obj);
                function checkElemReady(){
                    console.log("=============VV=============");
                    console.log(($("#"+ id).css("font", "")).length);
                    console.log("id is : "+id)
                    if ((obj.elem = $("#"+ id).css("font", "")).length != 1) {
                        alert("The element hasn't insert into HTML DOM.");
                        throw "The element hasn't insert into HTML DOM."; }
                    clearTimeout(setupFunc);
                    clearInterval(loadFunc) };
                return { "elem": null, "finish": function(html){
                    checkElemReady(); (this.elem ? this.elem : this.elem = $("#"+ id)).css("font", "").html(html);
                    return this
                },
                    "toString": function(){
                        var elem; setupFunc = setTimeout(function(){
                            loadFunc = setInterval(function(){
                                (elem || (elem = document.getElementById(id))).innerHTML = "Loading"+ Array(++loadCnt).join("..");
                                if (loadCnt > 60) loadCnt = 2 }, 250) }, 1000); return "<div id='"+ id +"' style='font:BOLD 16pt Arial'></div>"
                    }
                }
            }
        

        self.button = new(function(){
            var that = this;
            that.advanceSearch = new Button("#advanceSearch");
            that.advSearchSelectedBillNo = new Button("#advSearchSelectedBillNo");
			that.search = new Button("#search", "../service/customers/search/no");
//            that.search = new Button("#search", "../service/cust-profiles/search/no");
            that.advSearchInvoiceSubmit = new Button("#advSearchInvoiceSubmit");
            that.advSearchServiceSubmit = new Button("#advSearchServiceSubmit");
            that.advSearchCustomerSubmit = new Button("#advSearchCustomerSubmit");
            function Button(el, url, inp, validate) {
                var obj = this, inputs = inp, val = (validate || function(){ return true }), badge, done = function(res) { console.log(res) };
                obj.el = el;
                obj.elem = $(el);
                obj.inputs = function(object) { inputs = object; return this };
                obj.click = function(func) { this.elem.click(func) };
                obj.hide = function() { this.elem.addClass("hide"); return this };
                obj.show = function() { this.elem.removeClass("hide"); return this };
                obj.disable = function(flag) { if (flag == null || flag) this.elem.addClass("disabled"); else this.elem.removeClass("disabled"); return this };
                obj.enable = function() { this.elem.removeClass("disabled"); return this };
                obj.validate = function(func) { val = func; return obj }
                obj.done = function(func) { done = func };
                obj.badge = function(val) { if (badge) badge.text(val) };
                function eventHandler (e) {
                    if (!val(inputs)) return;
                    if (!url) return;
                    obj.elem.button("loading");
                    var params = {}; $.each(inputs, function(k,v){
                        if ($.type(v) === "object" && v.val) params[k] = v.val();
                        else if ($.type(v) === "array")      params[k] = v.join("|");
                        else if ($.type(v) === "function")   params[k] = v();
                        else                                 params[k] = v;
                    });
                    console.log(url);
                    $.get(url, params, function(res){ obj.elem.button("reset"); done(res) });
                    return;
                }
                if ((badge = this.elem.next()).length == 0) badge = null;
                obj.elem.click(eventHandler);
            }
        });
        self.dialog = new(function(){
            var that = this;
            that.mainMessageDialog = new Message("#mainMessageDialog");
            that.advSearchMsgDialog = new Message("#advSearchMsgDialog");
            that.advanceSearchDialog = new Modal("#advanceSearchDialog");
            that.customerPanel = new Message("#customerPanel");
            that.invoicePanel = new Message("#invoicePanel");
            that.advanceSearchMessageDialog = new Message("#advanceSearchMessageDialog");
            that.remarkModal = new Modal("#remarkModal", true);
            function Message(el) {
                var obj = this, timeCnt = 0, loadFunc;
                obj.el = el;
                obj.elem = $(el);
                obj.hide = function() { obj.elem.addClass("hide"); return obj };
                obj.show = function(flag) { if (flag == null || flag) obj.elem.removeClass("hide"); else obj.hide(); return obj };
                obj.clear = function() { obj.elem.find("*").remove(); obj.hide(); return obj };
                obj.message = function(arr, cls) { $.each(arr, function(i,m) { obj.elem.append('<div class="'+ cls +'">'+ m +'<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button></div>') }); return obj };
                obj.error = function(arr) { return obj.message(arr, "alert alert-danger") };
                obj.warn = function(arr) { return obj.message(arr, "alert alert-warning") };
                obj.success = function(arr) { return obj.message(arr, "alert alert-success") };
                obj.valid = function(jqXHR, textStatus, errorThrow){ var res = jqXHR; obj.stopLoad(); if (jqXHR && jqXHR.responseJSON) res = jqXHR.responseJSON; if (res) { var isNoData = false; if (res.statusCode && res.statusCode != "0") { obj.warn(res && $.type(res.warningList) === 'array' ? res.warningList : []).error(res && $.type(res.errorList) === 'array' ? res.errorList : ["An error occurred on the server side but there is no error message returned."]).show(); return false } if ($.type(res._embedded) === 'object' && $.map(res._embedded,function(v,k){return v}).length < 1) isNoData = true; if (!isNoData && res.data && res.statusCode == '0' && res.data.length < 1) isNoData = true;  if (isNoData) { obj.warn(["There is no records of data."]).show(); return false } return true } obj.error(["An error occurred on the server side but there is no error message returned."]).show(); return false }
                obj.hideLoad = function(){ obj.stopLoad().clear(); return this }; obj.stopLoad = function(){ clearInterval(loadFunc); return this }
                obj.showLoad = function(msg){ obj.elem.append('<div id="'+ obj.el +'-loading" class="alert alert-warning">'+ bind(msg, 0) +'</div>'); timeCnt = 0; var elem = document.getElementById(obj.el +"-loading"); loadFunc = setInterval(function(){ elem.innerHTML = bind(msg, ++timeCnt) }, 1000); obj.show(); return this }
                function bind(msg, timeCnt) { return msg.replace(/\{timeCnt\}/g, timeCnt) }
            }
            function Modal(el) {
                this.el = el;
                this.elem = $(el);
                this.hide = function() { this.elem.modal("hide") };
                this.show = function() { this.elem.modal("show") };
            }
            function Panel(el) {
                this.el = el;
                this.elem = $(el);
                this.hide = function() { this.elem.addClass("hide") };
                this.show = function() { this.elem.removeClass("hide") };
            }
        });
        self.checkbox = new(function(){
            var that = this;
            that.advSearchServiceProp = new CheckBox("[name=advSearchServiceProp]")
            function CheckBox(el) {
                var obj = this;
                obj.el = el;
                obj.elem = $(el);
                obj.contains = function(val) { return $.inArray(val, obj.val()) > -1 };
                obj.val = function() { return obj.elem.filter(":checked").map(function(i,o){ return o.value }) };
            }
        });
        function DropDown(el, url) { var obj = this, data = [{ key: "", value: "", text: "Please Select" }]; obj.el = el; obj.elem = $(el);
            obj.data = function(array) { if ($.type(array) == "array") { setup(data = array); return this } return data; };
            obj.init = function(url) {
                if (url) $.get(url, function(res) { setup(data = res.data);}); else setup(data); return this };
            obj.disable = function(flag) { obj.elem.attr("disabled", (flag == null ? true : flag)); return this }
            obj.enable = function() { obj.disable(false); return this }
            obj.index = function() { if (arguments.length == 1) { var maxLen = data.length, index = arguments[0]; obj.elem.find("option").each(function(i, opt){ opt.removeAttribute("selected") }).eq(index < 0 ? maxLen + index : index).attr("selected", true); return this } return $.inArray("selected", obj.elem.find("option").map(function(i, opt){ return opt.selected ? "selected" : "no-selected" })) }
            obj.selected = function(){ return data[obj.index()]; };
            obj.val = function() { return obj.elem.val(); };
            obj.key = function(){ if (arguments.length == 0) { var key = obj.elem.find("option:selected").data("key"); if (typeof key === "string" && key === "undefined") { var item = data[obj.index()]; key = item && item.key ? item.key : item.id } return key } var key = arguments[0]; for (var i = 0, m = data.length; i < m; i++) { var item = data[i]; if (item.key && item.key == key) { obj.index(i); break; } if (item.id && item.id == key) { obj.index(i); break; } } return this }
            function setup(array) { obj.elem.find("*").remove();
                obj.elem.append('<option data-index="-1" data-key="-1" value="-1">ทั้งหมด</option>');
                $.each(array,function(i,o){
                    obj.elem.append('<option data-index="'+ i +'" data-key="'+ o.id +'" value="'+ o.id +'">'+ (o.text || o.value) +'</option>')
                });
            }
            data = obj.elem.change(window[el.substring(1) +"ChangeEvent"]).find("option").map(function(i, opt){ return { key: opt.getAttribute("data-key"), value: opt.value, text: opt.textContent } });
        }
        self.input = new(function(){
            var that = this;
            that.advSearchInvoiceNo = new Input("#advSearchInvoiceNo");
            that.advSearchServiceNo = new Input("#advSearchServiceNo");
            that.advSearchServiceLabel = new DropDown("#advSearchServiceLabel").data([{ key: "Inmarsat Name", value: "Inmarsat Name"},{ key: "SLA", value: "SLA"},{ key: "Bundle Service Description", value: "Bundle Service Description"},{ key: "Bundle Service Flag", value: "Bundle Service Flag"},{ key: "Owner", value: "Owner"},{ key: "Brand", value: "Brand"},{ key: "Market Name", value: "Market Name"}]);
            that.advSearchCustomerNo = new Input("#advSearchCustomerNo");
            that.advSearchTaxNo = new Input("#advSearchTaxNo");
            that.advSearchCustFirstName = new Input("#advSearchCustFirstName");
            that.advSearchCustLastName = new Input("#advSearchCustLastName");
            that.advSearchOrgName = new Input("#advSearchOrgName");
            that.billNo = new Input("#billNo", 18);
            that.custNo = new Input("#custNo");
            that.custName = new Input("#custName");
            that.taxNo = new Input("#taxNo");
            that.branch = new Input("#branch");
            that.collectUnit = new Input("#collectUnit");
            that.accruedAmount = new NumberInput("#accruedAmount");
            that.billGroup = new Input("#billGroup");
            that.advancedAmount = new NumberInput("#advancedAmount");
            that.receiptAddress = new Input("#receiptAddress");
            that.invoiceAddress = new Input("#invoiceAddress");
            that.val = function() { if (arguments.length == 1) { $.each(arguments[0],function(k,v){ $("#"+ k).val(v) }) } };
            function DropDown(el, url) {
                var obj, data = [{ key: "", value: "Please Select" }];
                this.el = el;
                this.elem = obj = $(el);
                this.data = function(array) { if ($.type(array) == "array") { setup(data = array); return this } return data; }
                this.init = function(url) { if (url) $.get(url, function(res) { setup(res.data) }); else setup(data);};
                this.index = function() { return obj.find("option:selected").data("index") }
                this.key = function() { return obj.find("option:selected").data("key") }
                this.val = function() { return obj.val(); }
                function setup(array) {
                    obj.find("*").remove();
                    $.each(array,function(i,o){
                        obj.append('<option data-index="'+ i +'" data-key="'+ o.key +'" value="'+ o.value +'">'+ o.value +'</option>')
                    });
                }
                this.init(url);
            }
        });
        self.tab = new(function(){
            var that = this;
            that.advanceSearchTab = new Tab("#advanceSearchTab");
            that.customerInfoTab = new Tab("#customerInfoTab");
            that.invoiceDetailsTab = new Tab("#invoiceDetailsTab");
            function Tab(el) {
                var obj = this, index = 0, change = function(e){ console.log(e) }, initEvts = [];
                obj.el = el;
                obj.elem = $(el);
                obj.index = function() { if (arguments.length == 1) { index = arguments[0]; obj.elem.find("li").removeClass("active").find("a").eq(index).click(); return this } return index }
                obj.show = function(ind) { obj.elem.find("a").eq(ind).tab("show"); return this; }
                obj.change = function(func) { change = func; return this }
                obj.init = function(ind, evt) { initEvts[ind] = evt; return obj }
                obj.elem.find("a").each(function(i,o){ $(o).data("index", i) });
                obj.elem.find("li").each(function(i,o){ if ($(o).hasClass("active")) index = i; initEvts.push(function(){ console.log("index: "+ i) }) });
                obj.elem.on("show.bs.tab", function(e){ index = $(e.target).data("index"); change(e); initEvts[index]() });
            }
        });
        self.table = new(function(){
            var that = this;
            that.subscriptionList = new Table("#subscriptionList")
            that.invoiceList = new BootstrapTable("#invoiceList");
            that.historyList = new BootstrapTable("#historyList");
            that.changeList = new BootstrapTable("#changeList");
            that.advSearchInvoiceList = new Table("#advSearchInvoiceList");
            that.advSearchServiceList = new Table("#advSearchServiceList");
            that.advSearchCustomerList = new Table("#advSearchCustomerList");
            that.advSrcCusNoResultList = new BootstrapTable("#advSrcCusNoResultList");
            function Table(el) {
                var obj = this, headers = [], data = [], onApn = function() {}, onDel = onApn, loadCnt = 0, loadInt, loadFunc = function(){ obj.body.find("div#loading").html("Loading"+ Array(++loadCnt).join(".")); if (loadCnt > 8) loadCnt = 0; };
                var checkboxHtml = '<input type="checkbox" name="{field}" value="{value}" data-index="{index}">', radioHtml = '<input type="radio" name="{field}" value="{value}" data-index="{index}">', inputHtml = '<input name="{field}" value="{value}" {style} data-index="{index}">';
                obj.el = el; obj.elem = $(el); obj.body = obj.elem.find("tbody");
                obj.onAppend = function(func) { onApn = func }; obj.onDelete = function(func) { onDel = func };
                obj.hideLoad = function() { obj.elem.find("tbody tr").remove(); clearInterval(loadInt); return obj };
                obj.showLoad = function(){ obj.clear(); obj.elem.find("tbody").append("<tr><td colspan='"+ headers.length +"'><div id='loading' style='text-align:center;font:BOLD 16pt Arial'>Loading</div></td></tr>"); loadCnt = 0; loadInt = setInterval(loadFunc, 500); return obj };
                function reorder(index) { obj.body.find("tr").each(function(i,o){ $(o).find("td").eq(index).text(i + 1) }) }
                obj.insert = function(array, showRemove, attrs) {
                    if (arguments.length < 1) array = $.map(headers,function(o){ return " " });
                    obj.body.append("<tr "+ $.map($.extend(attrs, {}), function(v, k){ return k +"='"+ v +"'" }).join(" ") +">"
                        + $.map(array, function(v,i){
                            var field = headers[i].field, value = v;
                            if (headers[i].runningNo) value = (obj.size() + 1);
                            else if (headers[i].numberFormat) { value = !$.isNumeric(v) ? "0.00" : parseFloat(v, 10).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,'); }
                            else if (headers[i].checkbox) value = replace(checkboxHtml, null, field, i, v);
                            else if (headers[i].radio) value = replace(radioHtml, null, field, i, v);
                            else if (headers[i].input) value = replace(inputHtml, 'style="width:100%;margin:-4px 0;text-align:'+ (headers[i].align || "left") +'"', field, i, v);
                            return '<td><div style="'+ (headers[i].align ? "text-align:"+ headers[i].align : "") +'">'+ value +'</div></td>' }).join("")
                        + (!showRemove ? "" : '<td style="width:40px;text-align:center"><a href="#" class="delList"><span class="glyphicon glyphicon-trash"></span></a></td>')
                        +"</tr>"); return obj
                };
                obj.find = function(key, cri) { return obj.elem.find("tbody tr").filter("["+ key +"="+ cri +"]") }
                obj.clear = function() { obj.elem.find("tbody tr").remove(); return obj }
                obj.remove = function(index) { this.elem.find("tbody tr").eq(index).remove(); $.each(headers,function(i,o){ if (o.runningNo) reorder(i) }); }
                obj.data = function() { var data = [];
                    if (arguments.length != 1) { var rows = obj.elem.find("tbody tr");
                        for (var i = 0, m = rows.length; i < m; i++) { var row = []; for (var j = 0, n = rows[i].children.length; j < n; j++) { row.push(extract(headers[j], rows[i].children[j])) } data.push(row) }
                        return data;
                    } for (var i = 0, m = (data = arguments[0]).length; i < m; i++) { obj.insert(data[i]); } return obj
                };
                obj.size = function() { return obj.elem.find("tbody tr").length };
                obj.sum = function(index) { var sum = 0; this.elem.find("tbody tr").each(function(i, row){ var val = $(row).find("td").eq(index).text(); sum += (isNaN(val) ? 0 : parseFloat(val, 10)) }); return sum }
                $(obj.el).on("click", ".delList", function(){ $(this).parent().parent().remove(); reorder(); onDel(obj.data()) });
                obj.elem.find("thead th").each(function(i,o){ var elem = $(o);
                    headers.push({ "field": elem.data("field"), "align": $.trim(elem.data("align")), "runningNo": elem.data("runningNo") === true, "numberFormat": elem.data("numberFormat") === true, "checkbox": elem.data("checkbox") === true, "radio": elem.data("radio") === true, "input": elem.data("input") === true })
                }); function replace(str, style, field, index, value){ var s = str; if (style) s = s.replace("\{style\}", style); return s.replace("\{field\}", $.trim(field)).replace("\{index\}", index).replace("\{value\}", value) }
                function extract(prop, col) { if (prop.checkbox) { var elem = col.children[0].children[0]; return !elem.checked ? "" : elem.value } else if (prop.radio) { var elem = col.children[0].children[0]; return !elem.checked ? "" : elem.value } else if (prop.input) { var elem = col.children[0].children[0]; return elem.value } return col.children[0].innerText }
                if(obj.body.length < 1) { obj.elem.append("<tbody></tbody>"); obj.body = obj.elem.find("tbody") }
            }
            function BootstrapTable(el) {
                var obj = this, checkEvt = function(e){ console.log(e) }, uncheckEvt = checkEvt;
                obj.el = el;
                obj.elem = $(el);
                obj.clear = function() { obj.elem.bootstrapTable("removeAll"); return obj }
                obj.showLoad = function() { this.elem.bootstrapTable("showLoading"); return this };
                obj.hideLoad = function() { this.elem.bootstrapTable("hideLoading"); return this };
                obj.data = function() { if (arguments.length == 1) { this.elem.bootstrapTable("load", arguments[0]); } return this.elem.bootstrapTable("getData") };
                obj.selected = function() { return this.elem.bootstrapTable("getSelections") };
                obj.sum = function(isAll, colName) { var sum = 0; $.each(this.elem.bootstrapTable(isAll ? "getData" : "getSelections"), function(i,o){ sum += (o[colName] || 0) }); return sum };
                obj.expand = function() { obj.elem.find(".detail-icon i.icon-plus").click(); return this };
                obj.collapse = function() { obj.elem.find(".detail-icon i.icon-minus").click(); return this };
                obj.checkboxEvent = function(func) { this.elem.on("check.bs.table uncheck.bs.table check-all.bs.table uncheck-all.bs.table", func); return this };
                obj.checkEvent = function(func) { checkEvt = func; return this };
                obj.uncheckEvent = function(func) { uncheckEvt = func; return this };
                obj.checkboxEvent(function(e) { if (e.type === "check") checkEvt(e); if (e.type === "uncheck") uncheckEvt(e) });
            }
            //// AUTOMATIC REGISTER FORMATTER FUNCTION ////
            window.runningFormatter = function(val, row, ind) {return ind + 1 }
            window.numberFormatter = function(val, row, ind) { return !$.isNumeric(val) ? "0.00" : parseFloat(val, 10).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,').replace(/\.000$/g, "") }
            window.remarkIconFormatter = function(val, row, ind) {if(row.remark != null){return '<a  style="cursor: pointer" onclick="modalPopupRemark('+"'"+row.remark+"'"+')"><span class="glyphicon glyphicon-envelope" aria-hidden="true"></span></a>';}}

        });
        function Input(el, maxLen, propPos) {
            var obj = this;
            obj.el = el;
            obj.elem = $(el);
            obj.clear = function() { obj.val(""); return this }
            obj.empty = function() { return $.trim(obj.val()) === "" }
            obj.isEmpty = function() { return $.trim(obj.val()) === "" };
            obj.click = function(func) { obj.elem.click(func); return this }
            obj.readOnly = function(flag) { obj.elem.attr("readonly", (flag == null ? true : flag)); return this }
            obj.disable = function(flag) { obj.elem.attr("disabled", (flag == null ? true : flag)); return this }
            obj.enable = function() { obj.disable(false); return this }
            obj.val = function() { if (arguments.length == 1) { this.elem.val(arguments[0]) } return this.elem.val() }
            obj.get = function(propName) { if ($.type(propPos[propName]) !== "array" || propPos[propName].length !== 2) return ""; return obj.val().substring(propPos[propName][0], propPos[propName][1]) }
            if ($.isNumeric(maxLen)) { obj.elem.attr("maxLength", maxLen) }
        }
        function NumberInput(el, dec) { var obj = this, decimal = (dec || 2); obj.el = el; obj.elem = $(el).addClass("text-right");
            obj.val = function() { if (arguments.length == 0) return parseFloat(strip(this.elem.val() || "0"), 10); this.elem.val(format(arguments[0])); }
            obj.decimal = function(dec) { decimal = dec }; obj.format = format;
            obj.disable = function(){ obj.elem.attr("disabled", (arguments.length != 1 ? true : arguments[0])); return obj }; obj.enable = function(){ obj.disable(false); return obj }
            obj.elem.keypress(function(e) { var key = (e.which || e.keyCode || e.charCode || 0); var ch = String.fromCharCode(key); return "0123456789.".indexOf(ch) > -1 });
            obj.elem.focus(function(){ this.value = strip(this.value); this.select() });
            obj.elem.blur(function(){ this.value = format(this.value) });
            function format(val) { return ($.isNumeric(val) ? parseFloat(val, 10) : obj.val()).toFixed(decimal).replace(/(\d)(?=(\d{3})+\.)/g, '$1,'); };
            function strip(str) { return (str || "").replace(/[,]/g, "") }
            if (obj.elem.val() == "") obj.elem.val("0.00");
        }
        function Button(el, url, inp, validate) { var obj = this, inputs = inp, val = (validate || function(){ return true }), badge, done = function(res) { console.log(res) }; obj.el = el; obj.elem = $(el);
            obj.inputs = function(object) { inputs = object; return this };
            obj.click = function(func) { this.elem.click(func) };
            obj.hide = function() { this.elem.addClass("hide"); return this }; obj.show = function() { this.elem.removeClass("hide"); return this };
            obj.hideLoad = function(){ obj.elem.button("reset"); return this }; obj.showLoad = function(){ obj.elem.button("loading"); return this };
            obj.disable = function(flag) { if (flag == null || flag) this.elem.addClass("disabled"); else this.elem.removeClass("disabled"); return this };
            obj.enable = function() { this.elem.removeClass("disabled"); return this };
            obj.validate = function(func) { val = func; return obj }
            obj.done = function(func) { done = func };
            obj.badge = function(val) { if (badge) badge.text(val) };
            function eventHandler (e) {
                if (!val(inputs)) return;
                if (!url) return; obj.elem.button("loading");
                var params = {}; $.each(inputs, function(k,v){
                    if ($.type(v) === "object" && v.val) params[k] = v.val();
                    else if ($.type(v) === "array")      params[k] = v.join("|");
                    else if ($.type(v) === "function")   params[k] = v();
                    else                                 params[k] = v;
                }); $.get(url, params, function(res){ obj.elem.button("reset"); done(res) });
            }
            if ((badge = this.elem.next()).length == 0) badge = null;
            obj.elem.click(eventHandler).click(window[el.substring(1) +"ClickEvent"]);
        }
        self.utils = {
            guid: function(){ function r() { return Math.floor((Math.random() + 1) * 0x10000).toString(16).substring(1) } return r() + r() +"-"+ r() +"-"+ r() +"-"+ r() +"-"+ r() + r() + r() },
            numberFormat: function(num, dec) { return $.type(num) !== "number" ? "0.00" : num.toFixed(dec || 2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,'); },
            dateFormat: function(datetimeStr) { var obj = datetimeStr; if ($.type(obj) == "string" && /[0-9]{2}.[0-9]{2}.[0-9]{4}/g) { return obj } else if ($.isNumeric(obj)) { var dt = new Date(obj), dd = dt.getDate().toString(), mm = (dt.getMonth() + 1).toString(), yyyy = dt.getFullYear().toString(); return (dd[1]?"":"0") + dd +"/"+ (mm[1]?"":"0") + mm +"/"+ yyyy } else if ($.type == "date") { return "" } return "" },
            dateTimeFormat: function(datetime) {var obj = datetime, dt = $.type(obj) != "date" ? ($.isNumeric(obj) ? new Date(obj) : new Date()) : new Date(), dd = dt.getDate().toString(), MM = (dt.getMonth() + 1).toString(), yyyy = dt.getFullYear().toString(), hh = dt.getHours().toString(), mm = dt.getMinutes().toString(), ss = dt.getSeconds().toString(); return (dd[1]?"":"0") + dd +"/"+ (MM[1]?"":"0") + MM +"/"+ yyyy +" "+ (hh[1]?"":"0") + hh +":"+ (mm[1]?"":"0") + mm +":"+ (ss[1]?"":"0") + ss },
            queryString: function() { var pos = location.href.indexOf("?"), obj = {}; if (pos < 0) return obj; var params = location.href.slice(pos + 1).split("&"); for (var i = 0, m = params.length; i < m; i++) { pos = params[i].indexOf("="); if (pos < 0) { obj[params[i]] = true; continue; } obj[params[i].substring(0, pos)] = params[i].substring(pos + 1) } return obj },
            window: function(windowName, url, side) { window.windowOpened = (window.windowOpened || {}); var screenProp = ""; if (side == "left") { screenProp = "left=0,top=0,width="+ (screen.width/2) +",height="+ (screen.height-100) } else if (side == "right") { screenProp = "left="+ (screen.width/2-40) +",top=0,width="+ (screen.width/2+40) +",height="+ (screen.height-100) } window.windowOpened[windowName] = window.open(url, windowName, "menubar=no,scrollbars=no,resizable=0,"+ screenProp, false); return window.windowOpened }
        };
        self.contextPath = '${pageContext.request.contextPath}/';
// 	self.custInfoInputAccruedAmount = new NumberInput("#custInfoInputAccruedAmount");
// 	self.custInfoInputAdvancedAmount = new NumberInput("#custInfoInputAdvancedAmount");
        self.custInfoInputAccruedAmount = new Input("#custInfoInputAccruedAmount");
        self.custInfoInputAdvancedAmount = new Input("#custInfoInputAdvancedAmount");
        self.custInfoInputStatus = new Input("#custInfoInputStatus");
        self.custInfoInputCurrencyCode = new Input("#custInfoInputCurrencyCode");
        self.custInfoInputVatRate = new Input("#custInfoInputVatRate");
        self.payothersServiceNames = {};
        (function(){ $(window["setup"]) })();
        self.inputCustomerSegment = new DropDown("#inputCustomerSegment").init("../findCustomerSegmentList.json");
        self.customerSegmentSearch = new DropDown("#customerSegmentSearch").init("../findCustomerSegmentList.json");
        self.advSrcCusNoBtn = new Button("#advSrcCusNoBtn");

        self.advSrcCusNo = new Input("#advSrcCusNo");
        self.advSrcCusTaxId = new Input("#advSrcCusTaxId");
        self.advSrcCusFirstName = new Input("#advSrcCusFirstName");
        self.advSrcCusLastName = new Input("#advSrcCusLastName");
        self.advSrcOrgName = new Input("#advSrcOrgName");
        return this;
    })(jQuery);
    var dataList;
    view.button.search.validate(function() {
    	
        var isSearchBillNo = !view.input.billNo.empty();
        var billNo = view.input.billNo.val();
        view.dialog.mainMessageDialog.clear();
        view.dialog.customerPanel.hide();
        view.dialog.invoicePanel.hide();
        if (isSearchBillNo && billNo.length < 4) {
            view.dialog.mainMessageDialog.error(["โปรดระบุ 'เลขที่ลูกค้า' อย่างน้อย 4 ตัวอักษร"]).show();
            return false;
        }

        view.button.search.inputs({ "no": (window.billNoSelected = billNo), "size" : 1 });
        return true;

    }).done(function(res) {
        console.log(res)
        if(res && res._embedded.customers.length !== 0) {
        //if(res && res._embedded.customerProfiles.length !== 0) {
            var profile = res._embedded.customers[0];
            //var profile = res._embedded.customerProfiles[0];
            //var addr = $.extend(profile.addresses[0], {});
            //console.log(addr);
            view.dialog.mainMessageDialog.clear();
            view.dialog.customerPanel.show();
            view.dialog.invoicePanel.show();

			 view.input.custNo.val(profile.no);
			 view.input.custName.val(profile.name);
			 view.input.taxNo.val(profile.tax);
			 view.input.branch.val(profile.branch);
			 // 	view.input.collectUnit.val(profile.taxRegisterNo);
			 // 	view.input.accruedAmount.val(0);
			 view.input.billGroup.val(profile.billGroup);
			 // 	view.input.advancedAmount.val(0);
			 view.input.receiptAddress.val($.trim(profile.receiptAddr));
			 view.input.invoiceAddress.val($.trim(profile.invoiceAddr));
			/*
            view.input.custNo.val(profile.no);
            view.input.custName.val(profile.fullName);
            view.input.taxNo.val(profile.cardNo);
            view.input.branch.val(profile.branch);
            // 	view.input.collectUnit.val(profile.taxRegisterNo);
            // 	view.input.accruedAmount.val(0);
            view.input.billGroup.val(profile.billGroup);
            // 	view.input.advancedAmount.val(0);
            view.input.receiptAddress.val($.trim(addr.no) +" "+ $.trim(addr.moo) +" "+ $.trim(addr.village) +" "+ $.trim(addr.soi) +" "+ $.trim(addr.road) +" "+ $.trim(addr.tumbon) +" "+ $.trim(addr.amphur) +" "+ $.trim(addr.province) +" "+ $.trim(addr.postCode));
            view.input.invoiceAddress.val($.trim(profile.invoiceAddr));
            */

            view.custInfoInputCurrencyCode.val("N/A");
            view.custInfoInputVatRate.val("N/A");
            view.custInfoInputAccruedAmount.val("N/A");
            view.custInfoInputAdvancedAmount.val("N/A");
            view.custInfoInputStatus.val("ปกติ");

            $( "select[id='inputCustomerSegment'] option:selected" ).each(function() {
                $( this ).removeAttr("selected");
            });
            /*$('#inputCustomerSegment option[data-key='+profile.segment.id+']').attr('selected','selected');
            view.inputCustomerSegment.val(profile.segment.id);*/
            $('#inputCustomerSegment option[data-key='+profile.catCustomerSegment+']').attr('selected','selected');
            view.inputCustomerSegment.val(profile.catCustomerSegment);

            view.table.historyList.showLoad();

            if($.isEmptyObject(view.payothersServiceNames)) {
                $.get("../service/enums/search/category", { "category": "payothers.service.name" }, function(res1){
                    if(res1) {
                        $.map(res1._embedded.enums ,function(v,k){ view.payothersServiceNames[v.code] = v; });
                    }
                    $.get("../findPaymentOtherHistory.json", { "billingNo": billNoSelected }, function(res){
                        view.table.historyList.hideLoad().data($.map((res && res.data ? res.data : []), HistoryListRowMapper));
                        console.log("+++++++++++++++++++");
                        console.log(res);
                        dataList = res.data;
                    });
                });

            } else {
                $.get("../findPaymentOtherHistory.json", { "billingNo": billNoSelected }, function(res){
                    view.table.historyList.hideLoad().data($.map((res && res.data ? res.data : []), HistoryListRowMapper));
                    console.log("-----------------------");
                    console.log(res);
                    dataList = res.data;
                });
            }

        } else {
            view.dialog.mainMessageDialog.error(["ไม่พบ เลขที่ลูกค้า ที่ค้นหา"]).show();
        }
    });

    view.button.advanceSearch.click(function() {
        view.dialog.advSearchMsgDialog.clear();
        view.table.advSearchInvoiceList.clear();
        view.table.advSearchServiceList.clear();
        view.table.advSearchCustomerList.clear();
    });
    view.button.advSearchSelectedBillNo.click(function() {
        var tabInd = view.tab.advanceSearchTab.index(), bills;
        if (tabInd === 0) {
            bills = $.map(view.table.advSearchInvoiceList.data(), function(o,i){ return !o[0] ? null : o[0] });
        } else if (tabInd === 1) {
            bills = $.map(view.table.advSearchServiceList.data(), function(o,i){ return !o[0] ? null : o[0] });
        } else if (tabInd === 2) {
            bills = $.map(view.table.advSearchCustomerList.data(), function(o,i){ return !o[0] ? null : o[0] });
        }
        view.dialog.advSearchMsgDialog.clear();
        if (bills.length != 1) { view.dialog.advSearchMsgDialog.error(["Please select at least 1 record of the result list."]).show(); return }
        view.input.billNo.val(bills[0]);
        view.dialog.advanceSearchDialog.hide();
        view.button.search.elem.click();
    });
    view.button.advSearchInvoiceSubmit.click(function(res) {
        view.dialog.advSearchMsgDialog.clear();
        view.table.advSearchInvoiceList.showLoad();
        $.get("../service/bill-svcs/search/no", { "no": view.input.advSearchInvoiceNo.val() }, function(res){
            var data = []; $.map(res._embedded.billServices, function(o,i){
                data.push([ o.profile.no, o.profile.customer.no, o.profile.customer.fullName, o.propLabel, o.profile.billGroup,o.profile.no ])
            });
            view.table.advSearchInvoiceList.hideLoad().data(data);
        });
    });
    view.button.advSearchServiceSubmit.click(function() {
        var isProp1 = view.checkbox.advSearchServiceProp.contains("1");
        var isProp2 = view.checkbox.advSearchServiceProp.contains("2");
        var svcNo = view.input.advSearchServiceNo.val().replace("[\?\*]", "");
        view.dialog.advSearchMsgDialog.clear();
        if (svcNo.length < 4) {
            view.dialog.advSearchMsgDialog.error(["Please fill 'Service No' at least 4 characters."]).show();
            return;
        }
        view.table.advSearchServiceList.showLoad();
        function ResponseHandler(res) {
            var data = []; $.map(res._embedded.billServices, function(o,i){
                data.push([ o.propOne, o.profile.customer.no, o.profile.customer.fullName, o.propLabel, o.profile.billGroup, o.profile.no ])
            });
            view.table.advSearchServiceList.hideLoad().data(data);
        }
        if (isProp1 && isProp2) {
            $.get("../service/bill-svcs/search/both", { "label": view.input.advSearchServiceLabel.val(), "one": svcNo, "two": svcNo }, ResponseHandler);
        } else if (isProp1) {
            $.get("../service/bill-svcs/search/one", { "label": view.input.advSearchServiceLabel.val(), "one": svcNo }, ResponseHandler);
        } else if (isProp2) {
            $.get("../service/bill-svcs/search/two", { "label": view.input.advSearchServiceLabel.val(), "two": svcNo }, ResponseHandler);
        } else {
            view.table.advSearchServiceList.hideLoad();
            view.dialog.advSearchMsgDialog.error(["Please select 'Property1' or 'Property2' before proceed to search data."]).show();
        }
    });
    view.button.advSearchCustomerSubmit.click(function() {
        view.dialog.advSearchMsgDialog.clear();
        var url = "", params;
        if (!view.input.advSearchCustomerNo.empty())         { url = "../service/bill-profiles/search/custNo"; params = { "custNo": view.input.advSearchCustomerNo.val() } }
        else if (!view.input.advSearchTaxNo.empty()) { url = "../service/bill-profiles/search/tax"; params = { "tax": view.input.advSearchTaxNo.val() } }
        else if (!view.input.advSearchCustFirstName.empty() || !view.input.advSearchCustLastName.empty()) {
            url = "../service/bill-profiles/search/name"; params = { "firstName": view.input.advSearchCustFirstName.val(), "lastName": view.input.advSearchCustLastName.val() }
        }
        else if (!view.input.advSearchOrgName.empty())  { url = "../service/bill-profiles/search/org"; params = { "org": view.input.advSearchOrgName.val() } }
        else { view.dialog.advSearchMsgDialog.error(["Please fill in customer details at least 1 input."]).show(); return; }
        view.table.advSearchCustomerList.showLoad();
        $.get(url, params, function(res){
            var data = []; $.map(res._embedded.billProfiles, function(o,i){
                data.push([ o.no, o.customer.no, o.customer.fullName, o.customer.typeDesc, o.billGroup, o.no ])
            });
            view.table.advSearchCustomerList.hideLoad().data(data);
        });
    });
    view.tab.customerInfoTab.init(1, function(e) {
        view.table.subscriptionList.showLoad();
        $.get("../service/bill-svcs/search/no", { "no": billNoSelected }, function(res) {
            view.table.subscriptionList.hideLoad();
            if (!res || !res._embedded || !res._embedded.billServices || res._embedded.billServices.length < 1) { view.dialog.mainMessageDialog.error(["There is no result data."]).show(); return; }
            var data = []; $.each(res._embedded.billServices, function(i,o){ data.push(["-", o.propLabel, o.propOne, "Active"], false) });
            view.table.subscriptionList.data(data)
        });
    });
    view.tab.invoiceDetailsTab.init(0, function() {
    });
    view.tab.invoiceDetailsTab.init(1, function() {
        view.table.historyList.showLoad();
        $.get("../findPaymentHistory.json", { "billingNo": billNoSelected }, function(res) {
            view.table.historyList.hideLoad().data($.map((res && res.data ? res.data : []), HistoryListRowMapper));
        });
    });
    view.tab.invoiceDetailsTab.init(2, function() {
        view.table.changeList.showLoad();
        $.get("../findPaymentDetails.json", { "no": billNoSelected }, function(res) {
            view.table.changeList.hideLoad().data($.map((res && res.data ? res.data : []), function(o,i){
                return {
                    "paidDt": o
                    ,"invoiceNo": o
                    ,"receiptNo": o
                    ,"vat": o
                    ,"totalCharge": o
                    ,"processedDt": o
                    ,"branch": o
                    ,"user": o
                    ,"billCycle": o
                    ,"status": o
                    ,"trackingId": o
                };
            }));
        });
    });


    function advanceSearchClickEvent() {
        view.dialog.advanceSearchMessageDialog.clear();
        view.table.advSrcCusNoResultList.hideLoad().clear();
    }

    function advSrcCusNoBtnClickEvent() {
        view.dialog.advanceSearchMessageDialog.clear();

        var url = "", params, searchByCustId = false, searchByCustName = false, searchByOrg = false;
        //if (!view.advSrcCusNo.isEmpty())         { url = "../service/bill-profiles/search/no-list"; params = { "no": view.advSrcCusNo.val() }}
		/*
		 if (!view.advSrcCusNo.isEmpty())         { url = "../service/cust-profiles/search/no"; params = { "no": view.advSrcCusNo.val() }; searchByCustId = true; }
		 else if (!view.advSrcCusTaxId.isEmpty()) { url = "../service/bill-profiles/search/tax"; params = { "tax": view.advSrcCusTaxId.val() }; }
		 else if (!view.advSrcCusFirstName.isEmpty() || !view.advSrcCusLastName.isEmpty()) { url = "../findBillProfileByName.json"; params = { "ty": true, "fn": view.advSrcCusFirstName.val() , "ln": view.advSrcCusLastName.val() }; searchByCustName = true}
		 else if (!view.advSrcOrgName.isEmpty())  { url = "../findBillProfileByName.json"; params = { "ty": false, "fn": view.advSrcOrgName.val() , "ln": "" }; searchByOrg = true }
		 else { view.dialog.advanceSearchMessageDialog.error(["กรุณากรอกรายละเอียดอย่างน้อย 1 ช่องการค้นหา"]).show(); return; }
		 view.table.advSrcCusNoResultList.clear().showLoad();
		 view.dialog.advanceSearchMessageDialog.clear().showLoad("ระบบกำลังรอข้อมูลจาก CRM : {timeCnt} วินาที")
		 */
        console.log("customerSegmentSearch key="+view.customerSegmentSearch.key());
        if(!view.advSrcCusNo.isEmpty() || !view.advSrcCusTaxId.isEmpty() || !view.advSrcCusFirstName.isEmpty()
        //   || !view.customerSegmentSearch.val()
        ){
            url = "../findCustomerProfile.json"; params = {
                "customerFullName": view.advSrcCusFirstName.val() ,
                "customerNumber": view.advSrcCusNo.val(),
                "idRegisterNumber": view.advSrcCusTaxId.val(),
                "catCustomerSegment": view.customerSegmentSearch.key()
            };

        }else{
            view.dialog.advanceSearchMessageDialog.error(["กรุณากรอกรายละเอียดอย่างน้อย 1 ช่องการค้นหา"]).show(); return;
        }

        view.table.advSrcCusNoResultList.clear().showLoad();
        view.dialog.advanceSearchMessageDialog.clear().showLoad("ระบบกำลังรอข้อมูลจาก CRM : {timeCnt} วินาที")

        function ResponseHandler(res) {
            view.table.advSrcCusNoResultList.hideLoad().data($.map(res._embedded.billProfiles, function(o,i){
                return {
                    "acctNo": o.no
                    ,"customerId": o.customer.no
                    ,"customerAccountName": o.customerAccountName
                    ,"customerTaxNo": o.taxRegisterNo
                    ,"customerSegment": (o.customer.segment!=null)?o.customer.segment.text:""
                    ,"billNo": o.customer.no
                };
            }));
        }

        function CustNoResponseHandler(res) {
            if(res && res._embedded.customerProfiles) {
                var customer = res._embedded.customerProfiles[0];
                console.log("into CustNoResponseHandler-->"+customer.id);
                console.log(customer);
                var url = "../service/bill-profiles/search/custId", params = { "custId": customer.id };
                get(url, params, ResponseHandler, view.dialog.advanceSearchMessageDialog, function(){ view.dialog.advanceSearchMessageDialog.hideLoad(); view.table.advSrcCusNoResultList.hideLoad() });
            }
        }
        function CustNameResponseHandler(res) {
            if(res && res.data) {
                view.table.advSrcCusNoResultList.hideLoad().data($.map(res.data, function(o,i){
                    return {
                        // "acctNo": o.no
                        "customerId": o.customer.no
                        ,"customerAccountName": o.customer.fullName
                        ,"customerTaxNo": o.customer.cardNo
                        ,"customerSegment": (o.customer.segment!=null)?o.customer.segment.text:""
                        //,"billNo": o.customer.no
                    };
                }));
            }
        }
        function OrgResponseHandler(res) {
            if(res && res.data) {
                view.table.advSrcCusNoResultList.hideLoad().data($.map(res.data, function(o,i){
                    return {
                        "acctNo": o.no
                        ,"customerId": o.customer.no
                        ,"customerAccountName": o.customerAccountName
                        ,"customerTaxNo": o.taxRegisterNo
                        ,"customerSegment": (o.customer.segment!=null)?o.customer.segment.text:""
                        ,"billNo": o.customer.no
                    };
                }));
            }
        }
        console.log(" active search searchByCustName["+searchByCustName+"]")
        console.log(" active search searchByOrg["+searchByOrg+"]")
        console.log(" active search searchByCustId["+searchByCustId+"]")
        get(url, params, CustNameResponseHandler, view.dialog.advanceSearchMessageDialog, function(){ view.dialog.advanceSearchMessageDialog.hideLoad(); view.table.advSrcCusNoResultList.hideLoad() });
		/*
		 if(searchByCustName){
		 searchByCustName = false;
		 get(url, params, CustNameResponseHandler, view.dialog.advanceSearchMessageDialog, function(){ view.dialog.advanceSearchMessageDialog.hideLoad(); view.table.advSrcCusNoResultList.hideLoad() });
		 } else if(searchByOrg) {
		 searchByOrg = false;
		 get(url, params, OrgResponseHandler, view.dialog.advanceSearchMessageDialog, function(){ view.dialog.advanceSearchMessageDialog.hideLoad(); view.table.advSrcCusNoResultList.hideLoad() });
		 } else if(searchByCustId) {
		 searchByCustId = false;
		 get(url, params, CustNoResponseHandler, view.dialog.advanceSearchMessageDialog, function(){ view.dialog.advanceSearchMessageDialog.hideLoad(); view.table.advSrcCusNoResultList.hideLoad() });
		 }else {
		 get(url, params, ResponseHandler, view.dialog.advanceSearchMessageDialog, function(){ view.dialog.advanceSearchMessageDialog.hideLoad(); view.table.advSrcCusNoResultList.hideLoad() });
		 }
		 */

    }

    function SelectButton(val, row, ind) {
        //return "<a class='btn btn-success btn-xs' onclick='BillSelectButtonEvent("+ JSON.stringify(row) +")'>เลือก</a>";
        return "<a class='btn btn-success btn-xs' onclick='BillSelectButtonWithParamEvent("+ row.customerId +")'>เลือก</a>";
    }
    function BillSelectButtonEvent(bill) {
        //$("#billNo").val(bill.billNo);
        //$("#search").click();
        //$("#advanceSearchDialog").hide();
        view.input.billNo.val(bill.billNo);
        view.dialog.advanceSearchDialog.hide();
        view.button.search.elem.click();
    }
    function BillSelectButtonWithParamEvent(customerId) {
        $("#billNo").val(customerId);
        view.dialog.advanceSearchDialog.hide();
        view.button.search.elem.click();
    }

    function invoiceListMapper(o) {
        return {
            "invoiceNo": o.billNo
            ,"accountNo": o.accountNo
            ,"issueDt": view.utils.dateFormat(o.issueDate)
            ,"dueDt": view.utils.dateFormat(o.dueDate)
            ,"amount": parseFloat(o.amountBeforeTax, 10)
            ,"charge": parseFloat(o.amountAfterTax, 10)
            ,"discount": 0
            ,"vat": parseFloat(o.taxRate, 10)
            ,"totalCharge": parseFloat(o.amountAfterTax, 10)
            ,"balanceDue": parseFloat(o.balanceDue, 10)
            ,"totalPaid": parseFloat(o.totalDue, 10)
            ,"withholdingTax": parseFloat(o.taxAmount, 10)
            ,"billCycle": view.utils.dateFormat(o.chargeCycleFromDate) +" - "+ view.utils.dateFormat(o.chargeCycleToDate)
            ,"billRefNo": o.billNo
            ,"currencyCode": o.currencyCode
            ,"status": "ปกติ"
            ,"remark": o.remark
        };
    }
    function HistoryListRowMapper(o,i) {
        return {
            "updateDt":	view.utils.dateFormat(o.updatePrintDateStr)
            ,"receiptDt": view.utils.dateFormat(o.receiptPrintDateStr)
            ,"receiptNo": o.receiptNo
            ,"branch": o.shopPaymentName
            ,"user": o.paymentReceiver
            ,"invoiceNo": o.billRefNo
            ,"billCycle": o.billCycle
            ,"totalCharge": (o.billAmount || 0).toFixed( 2 )
            ,"discount": (o.discount || 0).toFixed( 2 )
            ,"excDiscount": (o.excDiscount || 0).toFixed( 2 )
            ,"payMethod": o.paymentMethod
            //,"payType": o.paymentCategory Add this if want to show ประเภทการรับชำระ
            ,"totalPaid": (o.transAmount || 0).toFixed( 2 )
            ,"status": o.status
            ,"remark": o.remark
        };
    }
    function serviceTypesFormatter(productName, row, ind) { return  (view.payothersServiceNames[productName])? view.payothersServiceNames[productName].descFullTh : productName  }
    $(document).ready(function () {
        $("#status_print").hide();
    });
    function submit_payment() {
        $("#status_print").show();
        setTimeout(' $("#status_print").hide()', 3000);
    }
    function receiptsServiceFormatter(val, row, ind) {
        var guid = view.utils.guid();
        var loadPanel = new LoadingPanel(guid);
        $.get("../xxx", { "billingNo": billNoSelected }, function(res){
            loadPanel.finish(Mustache.render($('#template').html(), { "receipts": $.map(dataList[val].service, function(o, i){
                return { "serviceName": o.serviceName , "productName": o.productName , "serviceQty": o.serviceQty, "amountPerUnit": view.utils.numberFormat(o.amount/o.serviceQty),"vat": view.utils.numberFormat(o.vat) ,"discount": view.utils.numberFormat(o.discount), "deduction": view.utils.numberFormat(o.deduction), "totalCharge": view.utils.numberFormat(o.totalCharge) }
            }) }));
        });
        
        return loadPanel;
    }
    function modalPopupRemark(remark){
        if(remark != "null"){
            document.getElementById("remark").textContent=remark;
        }else{
            document.getElementById("remark").textContent="ไม่มีหมายเหตุ";
        }
        view.dialog.remarkModal.show();
    }
    
    
   

</script>
</html>