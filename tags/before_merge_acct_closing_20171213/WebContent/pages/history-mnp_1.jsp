<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ประวัติการรับชำระค่าใช้บริการ</title>
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
                    <ol class="breadcrumb"><li><i>ประวัติการรับชำระ MNP</i></li></ol>
					<div id="mainMessageDialog"></div>
                    <ul class="nav nav-tabs" role="tablist">
					<li role="presentation" class="active"><a aria-controls="tab_cus" role="tab" data-toggle="tab"><span class="glyphicon glyphicon-filter"></span> ค้นหาข้อมูล</a></li>
					</ul>
					<div class="panel panel-default panal-radius">
						<div class="panel-body">
							<div class="row">
								<div class="col-md-12">
									<div class="form-horizontal">
										<div class="form-group">
	                            			<label class="control-label col-sm-2" >Order ID :</label>
	                            			<div class="col-sm-2"><input class="form-control" id="inputSearchOrderId"></div>
	                            			<label class="control-label col-sm-1" >MDN :</label>
	                            			<div class="col-sm-2"><input class="form-control" id="inputSearchMDN"></div>
											<div class="col-sm-5">
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
            </div>
            <div id="customerPanel" class="hide row">
                <div class="col-md-12 tab-modefile">
                    <ul id="customerInfoTab" class="nav nav-tabs" role="tablist">
                        <li role="presentation" class="active"><a href="#tab_cus" aria-controls="tab_cus" role="tab" data-toggle="tab"><span class="glyphicon glyphicon-user"></span> ข้อมูลลูกค้า</a></li>
                    </ul>
                    <div class="tab-content">
                        <div role="tabpanel" class="tab-pane active" id="tab_cus">
                            <div class="panel panel-default panal-radius">
                                <div class="panel-body">
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-horizontal">
                                                <div class="form-group">
                                                    <label class="control-label col-sm-2">Order ID :</label>
                                                    <div class="col-sm-2"><input class="form-control" disabled="disabled" id="inputCustomerOrderId"></div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label col-sm-2">ชื่อลูกค้า :</label>
                                                    <div class="col-sm-2"><input class="form-control" disabled="disabled" id="inputCustomerName"></div>
                                                    <label class="control-label col-sm-2">กลุ่มผู้ใช้บริการ :</label>
                                                    <div class="col-sm-2"><input class="form-control" id="inputCustomerSegment" disabled="disabled"></input></div>
                                                    <input type="hidden" id="customerType">
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label col-sm-2">Tax Id :</label>
                                                    <div class="col-sm-2"><input class="form-control" disabled="disabled" id="inputCustomerTaxId"></div>
                                                    <label class="control-label col-sm-2">สาขาที่ :</label>
                                                    <div class="col-sm-2"><input class="form-control" disabled="disabled" id="inputCustomerBranch"></div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label col-sm-2">ที่อยู่ :</label>
                                                    <%--<div class="col-sm-6"><input class="form-control" disabled="disabled" id="inputCustomerAddress"></div>--%>
													<div class="col-sm-6"><textarea class="form-control" disabled="disabled" id="inputCustomerAddress"></textarea></div>
                                                </div>
                                                <%--<div class="form-group">
                                                    <label class="control-label col-sm-2"></label>
                                                    <div class="col-sm-6"><input class="form-control" disabled="disabled" id="inputCustomerAddress2"></div>
                                                </div>--%>
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
                        <li role="presentation" class="active"><a href="#tab-2-2" aria-controls="tab-2-2" role="tab" data-toggle="tab"><span class="glyphicon glyphicon-th-list"></span> ประวัติการรับชำระ</a></li>
                    </ul>
                    <div class="tab-content">
                        <div role="tabpanel" class="tab-pane active" id="tab-2-2">
                            <table id="historyList" data-row-style="rowStyle" data-toggle="table" data-detail-view="true" data-detail-formatter="invoiceDetailsFormatter">
                              <thead>
								<tr>
	                                <th data-align="center" data-formatter="runningFormatter">#</th>
	                                <th data-field="no" data-align="center">เลขที่ใบเสร็จรับเงิน</th>
	                                <th data-field="docDttmStr" data-align="center">วันที่ออกใบเสร็จ</th>
                           			<th data-field="updateDttmStr" data-align="center">วันที่ทำรายการ</th>
	                                <th data-field="accountNo" data-align="center">Order ID</th>
	                                <th data-field="ref2" data-align="center">Repeat Order</th>
	                                <th data-field="accountName">ชื่อลูกค้า</th>
	                                <%--<th data-field="accountType" data-align="center" data-formatter="customerTypeFormatter">กลุ่มผู้ใช้บริการ</th>--%>
                                    <th data-field="custCategoryDesc" data-align="center">กลุ่มผู้ใช้บริการ</th>
	                                <th data-field="paymentCase" data-align="center">วิธีการชำระเงิน</th>
	                                <th data-field="totalCharge" data-align="center" data-formatter="numberFormatter">ยอดเงิน</th>
	                                <th data-field="branchName" data-align="center">สถานที่รับชำระ</th>
	                                <th data-field="updateUser" data-align="center">ผู้รับชำระ</th>
	                                <th data-field="status" data-align="center" data-formatter="statusFormatter">สถานะ</th>
									<th data-align="center" data-formatter="remarkIconFormatter">หมายเหตุ</th>
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
							<li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">เลขที่ใบแจ้งค่าบริการ</a></li>
							<li role="presentation"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">หมายเลขบริการ</a></li>
							<li role="presentation"><a href="#messages" aria-controls="messages" role="tab" data-toggle="tab">ข้อมูลลูกค้า</a></li>
						</ul>
						<div class="panel panel-default">
							<div class="panel-body">
								<div class="tab-content border-tab-content">
									<div role="tabpanel" class="tab-pane active" id="home">
										<div class="form-inline col-md-12">
											<div class="form-group col-md-12">
												<label>เลขที่ใบแจ้งค่าบริการ :</label>
												<div class="input-group col-md-8">
													<input type="text" class="form-control" id="advSrcBillNo" placeholder="กรุณาป้อนข้อมูลที่ต้องการค้นหาอย่างน้อย 4 ตัวอักษร"> 
													<span class="input-group-btn"><a id="advSrcBillNoBtn" class="btn btn-info"><span class="glyphicon glyphicon-search"></span> ค้นหา</a></span>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-md-12">
												<table id="advSrcBillNoResultList" data-toggle="table" data-pagination="true" data-page-size="5" data-page-list="[5, 10, 20, 50, 100, 200]" data-cache="false">
													<thead>
														<tr>
															<th data-formatter="runningFormatter">#</th>
															<th data-field="acctNo">เลขที่ใบแจ้งค่าบริการ</th>
															<th data-field="customerId">เลขที่ลูกค้า</th>
															<th data-field="customerAccountName">ชื่อลูกค้า</th>
															<th data-field="propLabel">ประเภทบริการ</th>
															<th data-field="billGroup">Billing Group</th>
															<th data-align="center" data-formatter="SelectButton"></th>
														</tr>
													</thead>
												</table>
											</div>
										</div>
									</div>
									<div role="tabpanel" class="tab-pane" id="profile">
										<div class="form-horizontal">
											<div class="form-group">
												<label class="control-label col-sm-2">ประเภทบริการ :</label>
												<div class="col-sm-4">
													<select id="advSrcSvcType" class="form-control"></select>
												</div>
											</div>
											<div class="form-group">
												<label class="control-label col-sm-2">หมายเลขบริการ :</label>
												<div class="col-sm-4">
													<div class="input-group">
														<input type="text" class="form-control" id="advSrcSvcNo">
														<span class="input-group-btn"><a id="advSrcSvcNoBtn" class="btn btn-info"><span class="glyphicon glyphicon-search"></span> ค้นหา</a></span>
													</div>
												</div>
											</div>
											<div class="form-group">
												<label class="control-label col-sm-2"></label>
												<div class="col-sm-4">
													<label class="checkbox-inline"> <input type="checkbox" name="advSrcSvcProperty" value="1"> Property1</label>
													<label class="checkbox-inline"> <input type="checkbox" name="advSrcSvcProperty" value="2"> Property2</label>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-md-12">
												<table id="advSrcSvcNoResultList" name="tableproperty" class="table table-hover" data-toggle="table" data-pagination="true" data-page-size="5" data-page-list="[5, 10, 20, 50, 100, 200]" data-cache="false">
													<thead>
														<tr>
															<th data-formatter="runningFormatter">#</th>
															<th data-field="propOne">หมายเลขบริการ</th>
															<th data-field="acctNo">เลขที่ลูกค้า</th>
															<th data-field="acctName">ชื่อลูกค้า</th>
															<th data-field="propLabel">ประเภทบริการ</th>
															<th data-field="billGroup">Billing Group</th>
															<th data-align="center" data-formatter="SelectButton"></th>
														</tr>
													</thead>
												</table>
											</div>
										</div>
									</div>
									<div role="tabpanel" class="tab-pane" id="messages">
										<div class="form-horizontal">
											<div class="form-group">
												<label class="control-label col-sm-3">เลขที่ลูกค้า :</label>
												<div class="col-sm-3"><input class="form-control" id="advSrcCusNo"></div>
												<label class="control-label col-sm-3">เลขประจำตัวผู้เสียภาษี :</label>
												<div class="col-sm-3"><input class="form-control" id="advSrcCusTaxId"></div>
											</div>
											<div class="form-group">
												<label class="control-label col-sm-3">ชื่อลูกค้า :</label>
												<div class="col-sm-3"><input class="form-control" id="advSrcCusFirstName"></div>
												<label class="control-label col-sm-3">นามสกุล :</label>
												<div class="col-sm-3"><input class="form-control" id="advSrcCusLastName"></div>
											</div>
											<div class="form-group">
												<label class="control-label col-sm-3">ชื่อนิติบุคคล/ราชการ :</label>
												<div class="col-sm-6"><input class="form-control" id="advSrcOrgName"></div>
											</div>
											<div class="form-group">
												<label class="control-label col-sm-3">กลุ่มผู้ใช้บริการ :</label>
												<div class="col-sm-4">
													<select id="advSrcCusType" class="form-control" >
														<option value="" selected>กรุณาเลือกกลุ่มผู้ใช้บริการ</option>
														<option value="Carrier">Carrier/Operator/NON POTs</option >
														<option value="ISP">ISP</option >
														<option value="MKT">Mkt Arm</option >
														<option value="AGENT">ReSeller/Agent</option >
														<option value="ORGANIZATIONINC">ธุรกิจ กสท</option >
														<option value="ORGANIZATIONBASIC">ธุรกิจทั่วไป</option >
														<option value="INDIVIDUAL">บุคคลทั่วไป</option >
														<option value="EMBASSIES">สถานฑูต/องค์กรระหว่างประเทศ</option >
														<option value="OFFICIAL">หน่วยงานรัฐ</option>
													</select>
												</div>
												<div class="col-sm-5">
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
															<th data-field="customerId">เลขที่ลูกค้า</th>
															<th data-field="customerAccountName">ชื่อลูกค้า</th>
															<th data-field="propLabel">ประเภทบริการ</th>
															<th data-field="billGroup">Billing Group</th>
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
					<i class="pull-right"><span class="glyphicon glyphicon-filter"></span>
						สามารถใช้เครื่องหมาย ? แทนหนึ่งตัวอักษร หรือ *
						แทนหลายตัวอักษรในการค้นหา (กรุณาป้อนข้อมูลที่ต้องการค้นหาอย่างน้อย 4 ตัวอักษร)
						</i><br>
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
	<table class="table table-striped table-bordered">
		<thead>
			<tr>
			<th class="text-left">MDN</th>
			<th class="text-left">ICCID</th>
			<th class="text-right">จำนวนเงินก่อนภาษีมูลค่าเพิ่ม</th>
			<th class="text-right">ภาษีมูลค่าเพิ่ม</th>
			<th class="text-right">ส่วนลด</th>
			<th class="text-right">จำนวนเงินรวม</th>
			</tr>
		</thead>
		<tbody>
		{{#invoices}}
			<tr><td>{{no}}</td>
			<td>{{iccid}}</td>
			<td class="text-right">{{amount}}</td>
			<td class="text-right">{{vat}}</td>
			<td class="text-right">{{discount}}</td>
			<td class="text-right">{{totalCharge}}</td></tr>
		{{/invoices}}
		</tbody>
	</table>
	</script>
</body>
<script type="text/javascript">
var view = (function($){
	var self = this, defaultErrorMessage = "An error occurred but there is no message response.";
	////AUTOMATIC REGISTER FORMATTER FUNCTION ////
	window.get = function(url, params, success, msgDialog, preCheck){ $.ajax({ "url": url, "type": "GET", "data": params, "error": msgDialog.valid, "success": function(res){ (preCheck || function(o){ console.log(o) })(res); if (msgDialog && !msgDialog.valid(res)) { return; } success(res) } }) }
	window.getSync = function(url, params, success, msgDialog, preCheck){ $.ajax({ "url": url, "type": "GET", "data": params, "async": false, "error": (msgDialog || { "valid": function(jqXHR, textStatus, errorThrow){ console.log(jqXHR); console.log("textStatus: "+ textStatus); console.log("errorThrow:"); console.log(errorThrow) } }).valid, "success": function(res){ var isNotJson = res.constructor == String; console.log(res); (preCheck || function(o){})(res); if (isNotJson) res = { "statusCode": "99", "errorList": ["No matching data found."] }; if (msgDialog && !msgDialog.valid(res)) { return; } success(res) } }) }
	window.stripToNumber = function(str) { return $.type(str) === "string" && $.isNumeric(str = str.replace(/[,]/g, "")) ? parseFloat(str, 10) : 0 }
	window.runningFormatter = function(val, row, ind) { return ind + 1 }
	//window.numberFormatter = function(val, row, ind) { return !$.isNumeric(val) ? "0.00" : parseFloat(val, 10).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,').replace(/\.00$/g, "") }
	window.numberFormatter = function(val, row, ind) { return !$.isNumeric(stripToNumber(val)) ? "0.00" : parseFloat(val, 10).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,') }
	window.dateFormatter = function(val, row, ind){ if (!val) return ""; if ((/(\d{4}).(\d{2}).(\d{2}).*/g).test(val)) return val.replace(/(\d{4}).(\d{2}).(\d{2}).*/g, "$3/$2/$1"); return val }
    window.remarkIconFormatter = function(val, row, ind) {if(row.remark != null){return '<a  style="cursor: pointer" onclick="modalPopupRemark('+"'"+row.remark+"'"+')"><span class="glyphicon glyphicon-envelope" aria-hidden="true"></span></a>';}}
	self.button = new(function(){
		var that = this;
		that.advanceSearch = new Button("#advanceSearch");
		that.advSearchSelectedBillNo = new Button("#advSearchSelectedBillNo");
		that.search = new Button("#search", "../service/customers/search/no");
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
				$.get(url, params, function(res){ obj.elem.button("reset"); done(res) });
			}
			if ((badge = this.elem.next()).length == 0) badge = null;
			obj.elem.click(eventHandler);
		}
	});
	
// 	function Button(el) { var obj = this, badge; obj.el = el; obj.elem = $(el);
// 		obj.hide = function() { this.elem.addClass("hide"); return this }; 
// 		obj.show = function() { this.elem.removeClass("hide"); return this };
// 		obj.hideLoad = function(){ obj.elem.button("reset"); return this }; 
// 		obj.showLoad = function(){ obj.elem.button("loading"); return this };
// 		obj.disable = function(flag) { if (flag == null || flag) this.elem.addClass("disabled").attr("disabled", true); else this.elem.removeClass("disabled").attr("disabled", false); return this }; 
// 		obj.enable = function() { obj.disable(false); return this };
// 		obj.badge = function(val) { if (badge) badge.text(val) }; if ((badge = this.elem.next()).length == 0) badge = null;
// 		obj.elem.click(window[el.substring(1) +"ClickEvent"]);
// 	}
	
	function Message(el) { var obj = this, timeCnt = 0, loadFunc; obj.el = el; obj.elem = $(el);
		obj.hide = function() { obj.elem.addClass("hide"); return obj };
		obj.show = function(flag) { if (flag == null || flag) obj.elem.removeClass("hide"); else obj.hide(); return obj };
		obj.clear = function() { obj.elem.find("*").remove(); obj.hide(); return obj };
		obj.message = function(arr, cls) { $.each(arr, function(i,o) { var m = o; if ($.type(o) === "object") { m = (o.desc || o.messageDesc) +" [code="+ (o.code || o.messageCode) +"]" }; obj.elem.append('<div class="'+ cls +'">'+ m +'<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button></div>') }); return obj };
		obj.error = function(arr) { return obj.message(arr, "alert alert-danger") };
		obj.warn = function(arr) { return obj.message(arr, "alert alert-warning") };
		obj.success = function(arr) { return obj.message(arr, "alert alert-success") };
		obj.valid = function(jqXHR, textStatus, errorThrow){ var res = jqXHR; obj.stopLoad(); if (jqXHR && jqXHR.responseJSON) res = jqXHR.responseJSON; if (res) { var isNoData = false; if (res.statusCode && res.statusCode != "0") { obj.warn(res && $.type(res.warningList) === 'array' ? res.warningList : []).error(res && $.type(res.errorList) === 'array' ? res.errorList : ["An error occurred on the server side but there is no error message returned."]).show(); return false } if (res.data && res.statusCode == '0' && res.data.length < 1) isNoData = true; if ($.type(res._embedded) === 'object' && $.map(res._embedded,function(v,k){return v}).length < 1) isNoData = true; if (isNoData) { obj.warn(["There is no records of data."]).show(); return false } return true } obj.error(["An error occurred on the server side but there is no error message returned."]).show(); return false }
		obj.hideLoad = function(){ obj.stopLoad().clear(); return this }; obj.stopLoad = function(){ clearInterval(loadFunc); return this }
		obj.showLoad = function(msg){ obj.elem.append('<div id="'+ obj.el +'-loading" class="alert alert-warning">'+ bind(msg, 0) +'</div>'); timeCnt = 0; var elem = document.getElementById(obj.el +"-loading"); loadFunc = setInterval(function(){ elem.innerHTML = bind(msg, ++timeCnt) }, 1000); obj.show(); return this }
		function bind(msg, timeCnt) { return msg.replace(/\{timeCnt\}/g, timeCnt) }
	}
	
	self.dialog = new(function(){
		var that = this;
		that.mainMessageDialog = new Message("#mainMessageDialog");
		that.advSearchMsgDialog = new Message("#advSearchMsgDialog");
		that.advanceSearchDialog = new Modal("#advanceSearchDialog");
		that.customerPanel = new Message("#customerPanel");
		that.invoicePanel = new Message("#invoicePanel");
        that.remarkModal = new Modal("#remarkModal", true);
// 		function Message(el) {
// 			var obj = this, timeCnt = 0, loadFunc; obj.el = el; obj.elem = $(el);
// 			obj.hide = function() { obj.elem.addClass("hide"); return obj };
// 			obj.show = function(flag) { if (flag == null || flag) obj.elem.removeClass("hide"); else obj.hide(); return obj };
// 			obj.clear = function() { obj.elem.find("*").remove(); obj.hide(); return obj };
// 			obj.message = function(arr, cls) { $.each(arr, function(i,m) { obj.elem.append('<div class="'+ cls +'">'+ m +'<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button></div>') }); return obj };
// 			obj.error = function(arr) { return obj.message(arr, "alert alert-danger") };
// 			obj.warn = function(arr) { return obj.message(arr, "alert alert-warning") };
// 			obj.success = function(arr) { return obj.message(arr, "alert alert-success") };
// 			obj.hideLoad = function(){ obj.stopLoad().clear(); return this }; obj.stopLoad = function(){ clearInterval(loadFunc); return this }
// 			obj.showLoad = function(msg){ obj.elem.append('<div id="'+ obj.el +'-loading" class="alert alert-warning">'+ bind(msg, 0) +'</div>'); timeCnt = 0; var elem = document.getElementById(obj.el +"-loading"); loadFunc = setInterval(function(){ elem.innerHTML = bind(msg, ++timeCnt) }, 1000); obj.show(); return this }
// 			obj.valid = function(jqXHR, textStatus, errorThrow){ 
// 				var res = jqXHR; obj.stopLoad(); 
// 				if (jqXHR && jqXHR.responseJSON) 
// 					res = jqXHR.responseJSON; 
// 				if (res) { 
// 					var isNoData = false; 
// 					if (res.statusCode && res.statusCode != "0") 
// 					{ 
// 						obj.warn(res && $.type(res.warningList) === 'array' ? res.warningList : [])
// 							.error(res && $.type(res.errorList) === 'array' ? res.errorList : ["An error occurred on the server side but there is no error message returned."]).show(); 
// 						return false 
// 					} 
// 					if (res.data && res.statusCode == '0' && res.data.length < 1) 
// 						isNoData = true; 
// 					if ($.type(res._embedded) === 'object' && $.map(res._embedded,function(v,k){return v}).length < 1) 
// 						isNoData = true; 
// 					if (isNoData) { return false } 
// 					return true 
// 				} 
// 				obj.error(["An error occurred on the server side but there is no error message returned."]).show(); return false }
// 		}
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
		that.searchOrderId = new Input("#inputSearchOrderId", 16);
		that.searchMDN = new Input("#inputSearchMDN", 10);
		that.custNo = new Input("#inputCustomerOrderId");
		that.custName = new Input("#inputCustomerName");
		that.taxNo = new Input("#inputCustomerTaxId");
		that.branch = new Input("#inputCustomerBranch");
		that.collectUnit = new Input("#collectUnit");
		that.accruedAmount = new NumberInput("#accruedAmount");
		that.billGroup = new Input("#billGroup");
		that.advancedAmount = new NumberInput("#advancedAmount");
		that.receiptAddress = new Input("#receiptAddress");
		that.invoiceAddress = new Input("#invoiceAddress");
		that.val = function() { if (arguments.length == 1) { $.each(arguments[0],function(k,v){ $("#"+ k).val(v) }) } };
		function DropDown(el, url) {
			var obj, data = [{ key: "", value: "Please Select" }];
			//obj.initV2 = function(url) { if (url) $.get(url, function(res) { setupV2(data = res.data) }); else setupV2(data); return this };
			this.el = el;
			this.elem = obj = $(el);
			this.data = function(array) { if ($.type(array) == "array") { setup(data = array); return this } return data; }
			this.init = function(url) { if (url) $.get(url, function(res) { setup(res.data) }); else setup(data); };
			this.index = function() { return obj.find("option:selected").data("index") }
			this.key = function() { return obj.find("option:selected").data("key") }
			this.val = function() { return obj.val(); }
			function setup(array) {
				obj.find("*").remove();
				$.each(array,function(i,o){
					obj.append('<option data-index="'+ i +'" data-key="'+ o.key +'" value="'+ o.value +'">'+ o.value +'</option>')
				});
			}
			//function setupV2(array) { obj.elem.find("*").remove(); $.each(array,function(i,o){ obj.elem.append('<option data-index="'+ i +'" data-key="'+ o.id +'" value="'+ o.id +'">'+ (o.text || o.value) +'</option>') }); }
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
		that.historyList = new BootstrapTable("#historyList");
		that.advSearchInvoiceList = new Table("#advSearchInvoiceList");
		that.advSearchServiceList = new Table("#advSearchServiceList");
		that.advSearchCustomerList = new Table("#advSearchCustomerList");
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
// 		function BootstrapTable(el) {
// 			var obj = this, checkEvt = function(e){ console.log(e) }, uncheckEvt = checkEvt;
// 			obj.el = el;
// 			obj.elem = $(el);
// 			obj.clear = function() { obj.elem.bootstrapTable("removeAll"); return obj }
// 			obj.showLoad = function() { this.elem.bootstrapTable("showLoading"); return this };
// 			obj.hideLoad = function() { this.elem.bootstrapTable("hideLoading"); return this };
// 			obj.data = function() { if (arguments.length == 1) { this.elem.bootstrapTable("load", arguments[0]); } return this.elem.bootstrapTable("getData") };
// 			obj.selected = function() { return this.elem.bootstrapTable("getSelections") };
// 			obj.sum = function(isAll, colName) { var sum = 0; $.each(this.elem.bootstrapTable(isAll ? "getData" : "getSelections"), function(i,o){ sum += (o[colName] || 0) }); return sum };
// 			obj.expand = function() { obj.elem.find(".detail-icon i.icon-plus").click(); return this };
// 			obj.collapse = function() { obj.elem.find(".detail-icon i.icon-minus").click(); return this };
// 			obj.checkboxEvent = function(func) { this.elem.on("check.bs.table uncheck.bs.table check-all.bs.table uncheck-all.bs.table", func); return this };
// 			obj.checkEvent = function(func) { checkEvt = func; return this };
// 			obj.uncheckEvent = function(func) { uncheckEvt = func; return this };
// 			obj.checkboxEvent(function(e) { if (e.type === "check") checkEvt(e); if (e.type === "uncheck") uncheckEvt(e) });
// 		}
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
	});
	function Input(el, maxLen, propPos) {
		var obj = this;
		obj.el = el;
		obj.elem = $(el);
		obj.clear = function() { obj.val(""); return this }
		obj.empty = function() { return $.trim(obj.val()) === "" }
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
	self.utils = {
		guid: function(){ function r() { return Math.floor((Math.random() + 1) * 0x10000).toString(16).substring(1) } return r() + r() +"-"+ r() +"-"+ r() +"-"+ r() +"-"+ r() + r() + r() },
		numberFormat: function(num, dec) { return $.type(num) !== "number" ? "0.00" : num.toFixed(dec || 2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,'); },
		dateFormat: function() { var obj = arguments[0]; if ($.type(obj) == "string" && /[0-9]{2}.[0-9]{2}.[0-9]{4}/g) { return obj } else if ($.isNumeric(obj)) { var dt = new Date(obj), dd = dt.getDate().toString(), mm = (dt.getMonth() + 1).toString(), yyyy = dt.getFullYear().toString(); return (dd[1]?"":"0") + dd +"/"+ (mm[1]?"":"0") + mm +"/"+ yyyy } else if ($.type == "date") { return "" } return "" },
		dateTimeFormat: function() { var obj = arguments[0], dt = $.type(obj) != "date" ? ($.isNumeric(obj) ? new Date(obj) : new Date()) : new Date(), dd = dt.getDate().toString(), MM = (dt.getMonth() + 1).toString(), yyyy = dt.getFullYear().toString(), hh = dt.getHours().toString(), mm = dt.getMinutes().toString(), ss = dt.getSeconds().toString(); return (dd[1]?"":"0") + dd +"/"+ (MM[1]?"":"0") + MM +"/"+ yyyy +" "+ (hh[1]?"":"0") + hh +":"+ (mm[1]?"":"0") + mm +":"+ (ss[1]?"":"0") + ss },
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
	self.inputCustomerAddress = new Input("#inputCustomerAddress");
	self.inputCustomerSegment = new Input("#inputCustomerSegment");
	return this;
})(jQuery);

view.button.search.validate(function() {
	var isSearchOrderId = !view.input.searchOrderId.empty();
	view.dialog.mainMessageDialog.clear();
	view.table.historyList.clear();
	
	if (isSearchOrderId && view.input.searchOrderId.val().length < 4) {
		view.dialog.mainMessageDialog.error(["โปรดระบุ 'Order ID' ด้วยรูปแบบที่ถูกต้อง"]).show();
		return false;
	} else if (!isSearchOrderId && view.input.searchMDN.val().length < 4) {
		view.dialog.mainMessageDialog.error(["โปรดระบุ 'MDN' ด้วยรูปแบบที่ถูกต้อง"]).show();
		return false;
	} 
	
	if( isSearchOrderId ) {
		view.button.search.inputs({ "no": (window.billNoSelected = view.input.searchOrderId.val()), "size" : 1 });
	} else {
		var params = { "payType": "MNP" }, service = "mdn-payType";
		params["mdn"] = view.input.searchMDN.val();
		params["size"] = 1;
		view.dialog.mainMessageDialog.clear();
		getSync("../service/receipts/search/"+ service, params, function(res){
			if(res) {
				console.log('55555555');console.log(res);console.log('55555555');
				var receipt = res._embedded.receipts[0];
				var accountNo = receipt.accountNo;
				view.button.search.inputs({ "no": (window.billNoSelected = accountNo), "size" : 1 });
			} else {
				view.dialog.mainMessageDialog.error(["ไม่พบ หมายเลขบริการ ที่ค้นหา"]).show();
			}
		}, view.dialog.mainMessageDialog);
	}
	return true;
}).done(function(res) {
	var profile = res._embedded.customers[0];
	console.log('xxxxx1');console.log(res);console.log('xxxxx1');
	view.dialog.mainMessageDialog.clear();
	view.dialog.customerPanel.show();
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
	view.custInfoInputCurrencyCode.val("N/A");
	view.custInfoInputVatRate.val("N/A");
	view.custInfoInputAccruedAmount.val("N/A");
	view.custInfoInputAdvancedAmount.val("N/A");		
	view.custInfoInputStatus.val("ปกติ");
	
	view.table.historyList.showLoad();
	var params = { "payType": "MNP" }, service = "cusNo-payType";
 	params["cusNo"] = billNoSelected;
	
	/*$.get("../service/receipts/search/"+ service, params, function(res){
		console.log('555');
		console.log(res);
		console.log('555');
		var receipts = res._embedded.receipts;
		view.dialog.invoicePanel.show();
		view.table.historyList.hideLoad().rawData(receipts);
		view.inputCustomerAddress.val(receipts[0].addrLine1);
        view.inputCustomerSegment.val(receipts[0].custCategoryDesc);
	}*/
	$.get("../findReceiptList.json", params, function(res){
				console.log('555');
				console.log(res);
				console.log('555');
				//var receipts = res._embedded.receipts;
				var receipts = res;//by NSD 17-03-2017
				view.dialog.invoicePanel.show();
				view.table.historyList.hideLoad().rawData(receipts);
				view.inputCustomerAddress.val(receipts[0].addrLine1);
				view.inputCustomerSegment.val(receipts[0].custCategoryDesc);
			}
	);
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
		view.table.advSearchInvoiceList.hideLoad().rawData(data);
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
		view.table.advSearchServiceList.hideLoad().rawData(data);
	}
	if (isProp1 && isProp2) {
		$.get("../service/bill-svcs/search/both", { "label": view.input.advSearchServiceLabel.val(), "one": svcNo, "two": svcNo }, ResponseHandler);
	} else if (isProp1) {
		$.get("../service/bill-svcs/search/one", { "label": view.input.advSearchServiceLabel.val(), "one": svcNo }, ResponseHandler);
	} else if (isProp2) {
		$.get("../service/bill-svcs/search/two", { "label": view.input.advSearchServiceLabel.val(), "two": svcNo }, ResponseHandler);
	} else {
// 		view.table.advSearchServiceList.hideLoad();
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
// 	$.get(url, params, function(res){
// 		var data = []; $.map(res._embedded.billProfiles, function(o,i){
// 			data.push([ o.no, o.customer.no, o.customer.fullName, o.customer.typeDesc, o.billGroup, o.no ])
// 		});
// 		view.table.advSearchCustomerList.hideLoad().rawData(data);
// 	});
});
view.tab.invoiceDetailsTab.init(0, function() {
	view.table.historyList.showLoad();
	var params = { "payType": "MNP" }, service = "cusNo-payType";
 	params["cusNo"] = billNoSelected
	get("../service/receipts/search/"+ service, params, function(res){
		var receipts = res._embedded.receipts;
		view.table.historyList.hideLoad().rawData(receipts);
	}, view.dialog.mainMessageDialog);
});
function customerTypeFormatter(val, row, ind) { return val.toLowerCase() == "organization" ? "นิติบุคคล" : "บุคคลธรรมดา" }
function statusFormatter(val, row, ind) { return row.attributes.indexOf("R") > -1 ? "ยกเลิกสำเร็จ" : (row.attributes.indexOf("C") > -1 ? "ปกติ" : "ไม่พร้อมทำรายการ") }
function invoiceDetailsFormatter(ind, row) {
	return Mustache.render($('#template').html(), { "invoices" : $.map(row.services, function(inv, i){
		return {
			 "no": inv.mdn
			,"iccid": inv.iccid
			,"amount": view.utils.numberFormat(inv.amount)
			,"vat": view.utils.numberFormat(inv.vat)
			,"discount": view.utils.numberFormat(inv.discount)
			,"totalCharge": view.utils.numberFormat(inv.totalCharge)
		}
	}) })
}
function HistoryListRowMapper(o,i) {
	return {
		"updateDt":	view.utils.dateTimeFormat(o.updatePrintDate)//o.updatePrintDate
		,"receiptDt": view.utils.dateTimeFormat(o.receiptPrintDate)//o.updatePrintDate
		,"receiptNo": o.receiptNo
		,"branch": o.shopPaymentName
		,"user": o.paymentReceiver
		,"invoiceNo": o.billRefNo
		,"billCycle": o.billCycle
		,"totalCharge": (o.billAmount || 0)
		,"payMethod": o.paymentMethod
		,"payType": o.paymentCategory
		,"totalPaid": (o.transAmount || 0)
		,"status": o.status
		,"remark": o.remark
	};
}
   $(document).ready(function () {
       $("#status_print").hide();
   });
   function submit_payment() {
       $("#status_print").show();
       setTimeout(' $("#status_print").hide()', 3000);
   }
   function dateTimeFormatter(value, row, index, options) {
	   //alert(value);
	return moment(value).format(options.style);
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
