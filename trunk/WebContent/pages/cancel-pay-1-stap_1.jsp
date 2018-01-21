<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="resources/bootstrap-table/src/bootstrap-table.css" rel="stylesheet" type="text/css"/>
        <link href="resources/custom.css" rel="stylesheet" type="text/css"/>
        <script src="resources/jquery.min.js" type="text/javascript"></script>
        <script src="resources/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="resources/bootstrap-table/src/bootstrap-table.js"></script>
        <script src="resources/custom.js" type="text/javascript"></script>
    </head>
    <body>
        <header class="header_page"></header>
        <section class="container-fluid menu">
            <!-- <button class="menu-toggle btn btn-sm btn-default" style="margin-bottom: 5px"><span class="glyphicon glyphicon-step-forward"></span></button> -->
    <%-- <%@include  file="menu.jsp" %> --%>
            <div class="row">
                <div class="col-md-12 tab-modefile">
                    <ol id="breadcrumb" class="breadcrumb">
						<li><i>ยกเลิกรับชำระค่าบริการ</i></li>
						<li class="active">ค้นหาข้อมูลการรับชำระ</li>
						<li>ระบุเหตุผลการยกเลิกรับชำระ</li>
						<li>ผลการยกเลิกรับชำระ</li>
					</ol>
					<div id="mainMessageDialog"></div>
                    <div class="tab-content">
                        <div role="tabpanel" class="tab-pane active" id="tab_cus">
                        	<ul class="nav nav-tabs" role="tablist">
		                        <li role="presentation" class="active">
		                        	<a href="#tab_cus" aria-controls="tab_cus" role="tab" data-toggle="tab"><span class="glyphicon glyphicon-filter"></span> ค้นหาข้อมูล</a>
		                        </li>
		                    </ul>
                            <div class="panel panel-default panal-radius">
                                <div class="panel-body">
                                    <div class="row">
		                                <div class="col-md-12">
		                                    <div class="form-horizontal">
		                                        <div class="form-group">
		                                            <label class="control-label col-sm-2">เลขที่ใบแจ้งค่าบริการ  :</label>
		                                            <div class="col-sm-2"><input class="form-control" id="billNo"></div>
		                                            <label class="control-label col-sm-2" >เลขที่ใบเสร็จรับเงิน :</label>
		                                            <div class="col-sm-2"><input class="form-control" id="receiptNo"></div>
		                                            <div class="col-sm-4">
		                                            <a id="search" class="btn btn-primary"><span class="glyphicon glyphicon-search"></span> ค้นหา</a>&nbsp;&nbsp;
		                                            <a type="button" class="btn btn-success" data-toggle="modal" data-target="#customerSearch"><span class="glyphicon glyphicon-zoom-in"></span> ค้นหาเพิ่มเติม</a></div>
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
            <ul id="navigatePanel" class="hide list-inline pull-right list-menu-set">
                <li><a id="addCancelReceiptList" href="cancel-pay-1-stap_2.jsp" class="btn btn-link"><span class="glyphicon glyphicon-plus-sign"></span> เพิ่มรายการการยกเลิก</a></li>
                <li><a id="sumCancelReceiptList" href="cancel-pay-1-stap_2.jsp" class="btn btn-link"><span class="glyphicon glyphicon-th-list"></span> สรุปรายการการยกเลิก</a><span class="badge badge_modefil">1</span></li>
            </ul>
            <div id="customerPanel" class="hide row">
                <div class="col-md-12">
                    <div class="panel-heading">
                    <h3 class="panel-title"><span class="glyphicon glyphicon-shopping-cart"></span> รายการรับชำระค่าบริการ</h3>
                </div>
                    <table id="receiptList" data-row-style="rowStyle" data-toggle="table" data-detail-view="true" data-detail-formatter="detailFormatter" data-classes="table table-hover table-striped">
                        <thead>
                            <tr>
                            	<th data-field="checked" data-checkbox="true"></th>
                                <th data-formatter="runningFormatter">#</th>
                                <th data-field="no">เลขที่ใบเสร็จรับเงิน</th>
                                <th data-field="date" data-align="center">วันที่รับชำระ</th>
                                <th data-field="custNo" data-align="center">เลขที่ลูกค้า</th>
                                <th data-field="custName">ชื่อลูกค้า</th>
                                <th data-field="payMethod">วิธีการชำระเงิน</th>
                                <th data-field="total" data-align="right" data-formatter="numberFormatter">ยอดเงิน</th>
                                <th data-field="branch" data-align="center">สถานที่รับชำระ</th>
                                <th data-field="username">ผู้รับชำระ</th>
                                <th data-field="status" data-align="center">สถานะ</th>
                            </tr>
                        </thead>
                    </table>
                </div>
            </div>
        </section>
        <div class="modal fade" role="dialog" id="modal_authen">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title">Authentication</h4>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="col-md-2">
                                </div>
                                <div class="col-md-10">
                                    <div class="form-horizontal">
                                        <div class="form-group">
                                            <label class="col-sm-4 control-label">ผู้อนุมัติ :</label>
                                            <div class="col-sm-8">
                                                <input type="text" class="form-control">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-4 control-label">รหัสผ่าน :</label>
                                            <div class="col-sm-8">
                                                <input type="password" class="form-control">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" ><span class="glyphicon glyphicon-ok-circle"></span> ตกลง</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal"><span class="glyphicon glyphicon-remove-circle"></span> ยกเลิก</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal fade" role="dialog" id="customerSearch">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title"><span class="glyphicon glyphicon-user"></span> ค้นหาลูกค้า</h4>
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

	<div id="detailsTableTemplate" class="hidden">
	<table class="table table-striped table-bordered">
		<thead>
			<tr>
			<th class="text-center">รายการ</th>
			<th class="text-center">จำนวน</th>
			<th class="text-center">ภาษีมูลค่าเพิ่ม</th>
			<th class="text-center">ส่วนลด</th>
			<th class="text-center">จำนวนเงิน</th>
			</tr>
		</thead>
		<tbody loop="true">
			<tr><td>Invoice No.: {no}</td><td class="text-right">{amount:numberFormatter}</td><td class="text-right">{vat:numberFormatter}</td><td class="text-right">{discount:numberFormatter}</td><td class="text-right">{totalCharge:numberFormatter}</td></tr>
		</tbody>
	</table>
	</div>
</body>
<script type="text/javascript">
var view = (function($){
	var self = this;
	self.session = function(key, val) { if (!val) { var val = window.sessionStorage.getItem(key); return val && (val.indexOf("{") > -1 || val.indexOf("[") > -1) ? JSON.parse(val) : val } window.sessionStorage.setItem(key, ($.type(val) != "object" && $.type(val) != "array" ? val : JSON.stringify(val)))	};
	self.utils = {
		guid: function(){ function r() { return Math.floor((Math.random() + 1) * 0x10000).toString(16).substring(1) } return r() + r() +"-"+ r() +"-"+ r() +"-"+ r() +"-"+ r() + r() + r() },
		numberFormat: function(num, dec) { return $.type(num) !== "number" ? "0.00" : num.toFixed(dec || 2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,'); },
		dateFormat: function() { var obj = arguments[0]; if ($.type(obj) == "string" && /[0-9]{2}.[0-9]{2}.[0-9]{4}/g) { return obj } else if ($.isNumeric(obj)) { var dt = new Date(obj), dd = dt.getDate().toString(), mm = (dt.getMonth() + 1).toString(), yyyy = dt.getFullYear().toString(); return (dd[1]?"":"0") + dd +"/"+ (mm[1]?"":"0") + mm +"/"+ yyyy } else if ($.type == "date") { return "" } return "" },
		dateTimeFormat: function() { var obj = arguments[0], dt = $.type(obj) != "date" ? ($.isNumeric(obj) ? new Date(obj) : new Date()) : new Date(), dd = dt.getDate().toString(), MM = (dt.getMonth() + 1).toString(), yyyy = dt.getFullYear().toString(), hh = dt.getHours().toString(), mm = dt.getMinutes().toString(), ss = dt.getSeconds().toString(); return (dd[1]?"":"0") + dd +"/"+ (MM[1]?"":"0") + MM +"/"+ yyyy +" "+ (hh[1]?"":"0") + hh +":"+ (mm[1]?"":"0") + mm +":"+ (ss[1]?"":"0") + ss },
		queryString: function() { var pos = location.href.indexOf("?"), obj = {}; if (pos < 0) return obj; var params = location.href.slice(pos + 1).split("&"); for (var i = 0, m = params.length; i < m; i++) { pos = params[i].indexOf("="); if (pos < 0) { obj[params[i]] = true; continue; } obj[params[i].substring(0, pos)] = params[i].substring(pos + 1) } return obj },
		window: function(windowName, url, side) { window.windowOpened = (window.windowOpened || {}); var screenProp = ""; if (side == "left") { screenProp = "left=0,top=0,width="+ (screen.width/2) +",height="+ (screen.height-100) } else if (side == "right") { screenProp = "left="+ (screen.width/2-40) +",top=0,width="+ (screen.width/2+40) +",height="+ (screen.height-100) } window.windowOpened[windowName] = window.open(url, windowName, "menubar=no,scrollbars=no,resizable=0,"+ screenProp, false); return window.windowOpened }
	};
	//// AUTOMATIC REGISTER FORMATTER FUNCTION ////
	window.get = function(url, params, success, msgDialog, preCheck){ $.ajax({ "url": url, "type": "GET", "data": params, "error": msgDialog.valid, "success": function(res){ (preCheck || function(o){ console.log(o) })(res); if (msgDialog && !msgDialog.valid(res)) { return; } success(res) } }) }
	window.post = function(url, params, success, msgDialog, preCheck){ $.ajax({ "url": url, "type": "POST", "data": params, "error": msgDialog.valid, "success": function(res){ (preCheck || function(o){ console.log(o) })(res); if (msgDialog && !msgDialog.valid(res)) { return; } success(res) } }) }
	window.stripToNumber = function(str) { return $.type(str) === "string" && $.isNumeric(str = str.replace(/[,]/g, "")) ? parseFloat(str, 10) : 0 }
	window.toDateString = function(ddMMyyyy){ return ddMMyyyy.replace(/(\d{2}).(\d{2}).(\d{4})/g, "$2/$1/$3") }
	window.runningFormatter = function(val, row, ind) { return ind + 1 }
	window.numberFormatter = function(val, row, ind) { return !$.isNumeric(stripToNumber(val)) ? "0.00" : parseFloat(val, 10).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,') }
	window.stringInputFormatter = function(val, row, ind) { return '<input value="'+ $.trim(val) +'" maxLength="40" class="form-control">' }
	window.numberInputFormatter = function(val, row, ind) { return '<input value="'+ self.utils.numberFormat(parseFloat(val || "0.00", 10)) +'" maxLength="10" style="width:100px" class="form-control text-right" onkeypress="return $.isNumeric(String.fromCharCode(event.which)) || (event.which == 46 && this.value.indexOf(\'.\') == -1)" onfocus="(function(o){ o.value = o.value.replace(/[,]/g, \'\') })(this)" onblur="(function(o){ o.value = parseFloat(o.value, 10).toFixed(2).replace(/(\\d)(?=(\\d{3})+\\.)/g, \'$1,\'); })(this)">' }
	window.modifyButtonFormatter = function(val, row, ind) { return '<a class="btn btn-link modList" onclick=\'(function(elem, row, ind){ var table = $("#"+ $(elem).closest("table")[0].id); window[table[0].id +"ModifyEvent"](table, row, ind) })(this, '+ JSON.stringify(row) +', '+ ind +')\'><span class="glyphicon glyphicon-pencil"></span></a>' }
	window.removeButtonFormatter = function(val, row, ind) { return '<a class="btn btn-link delList" onclick=\'(function(elem, row, ind){ $("#"+ $(elem).closest("table")[0].id).bootstrapTable("remove", { field: "index", values: [ind] }) })(this, '+ JSON.stringify(row) +', '+ ind +')\'><span class="glyphicon glyphicon-trash"></span></a>' }
	function Panel(el) { var obj = this, dura = 500; obj.el = el; obj.elem = $(el);
		obj.hide = function(ms) { obj.elem.hide(ms || dura); return this }; obj.show = function(ms) { if (!ms || !$.isNumeric(ms)) ms = dura; if (ms >= 0) obj.elem.css("display", "none").removeClass("hide").removeClass("hidden").show(ms); else obj.hide(Math.abs(ms)); return this }
	}
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
	self.button = new(function(){
		var that = this;
		that.search = new Button("#search");
		that.advSrcSubmitBtn = new Button("#advSrcSubmitBtn");
		that.advSrcCancelBtn = new Button("#advSrcCancelBtn");
		that.addCancelReceiptList = new Button("#addCancelReceiptList");
		that.sumCancelReceiptList = new Button("#sumCancelReceiptList");
		function Button(el, url, inp, validate) {
			var obj = this, url = url, inputs = inp, val = (validate || function(){ return true }), badge, done = function(res) { console.log(res) };
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
			if ((badge = this.elem.next()).length == 0) badge = null;
			if (url) {
				obj.elem.click(function(){
					if (!val(inputs)) return;
					obj.elem.button("loading");
					var params = {}; $.each(inputs, function(k,v){
						if ($.type(v) === "object" && v.val) params[k] = v.val();
						else if ($.type(v) === "array")      params[k] = v.join("|");
						else if ($.type(v) === "function")   params[k] = v();
						else                                 params[k] = v;
					});
					$.get(url, params, function(res){ obj.elem.button("reset"); done(res) });
				});
			}
		}
		function ButtonGroup(el) {
			var index = 0;
			this.el = el;
			this.elem = $(el);
			this.list = this.elem.find(".dropdown-menu a").each(function(i){ $(this).click(function(){ index = i }) });
			this.val = function() { return this.elem.find(".selection").text() };
			this.index = function() { if (arguments.length == 1) { this.list[arguments[0]].click() } return index };
		}
	});
	self.dialog = new(function(){
		var that = this;
		that.advanceSearchMessageDialog = new Message("#advanceSearchMessageDialog");
		that.customerSearch = new Modal("#customerSearch");
		that.navigatePanel = new Panel("#navigatePanel");
		that.customerPanel = new Panel("#customerPanel");
		function Modal(el) {
			this.el = el;
			this.elem = $(el);
			this.hide = function() { this.elem.modal("hide") };
			this.show = function() { this.elem.modal("show") };
		}
	});
	self.radio = new(function(){
		var that = this;
		that.withholdingTaxType = new Radio("[name=withholdingTaxType]");
		function Radio(el) {
			this.el = el;
			this.elem = $(el);
			this.label = function() { return this.elem.filter(":checked").data("label") };
			this.val = function() { var s = this.elem.filter(":checked"),val = s.val(); return !val ? s.data("label") : val };
		}
	});
	self.input = new(function(){
		var that = this;
		that.advSrcCusNo = new Input("#advSrcCusNo");
		that.advSrcTaxNo = new Input("#advSrcTaxNo");
		that.advSrcCusFirstName = new Input("#advSrcCusFirstName");
		that.advSrcCusLastName = new Input("#advSrcCusLastName");
		that.advSrcOrgName = new Input("#advSrcOrgName");
		that.custNo = new Input("#custNo");
		that.billNo = new Input("#billNo");
		that.receiptNo = new Input("#receiptNo");
		function Input(el) {
			var obj = this;
			obj.el = el;
			obj.elem = $(el);
			obj.empty = function() { return $.trim(obj.val()) === "" }
			obj.click = function(func) { obj.elem.click(func); return this }
			obj.readOnly = function(flag) { obj.elem.attr("readonly", (flag == null ? true : flag)); return this };
			obj.disable = function(flag) { obj.elem.attr("disabled", (flag == null ? true : flag)); return this };
			obj.enable = function() { obj.disable(false); return this };
			obj.val = function() { if (arguments.length == 1) { this.elem.val(arguments[0]) } return $.trim(this.elem.val()) };
		}
	});
	self.tab = new(function(){
		var that = this;
		that.advSrcReceiptTab = new Tab("#advSrcReceiptTab");
		function Tab(el) {
			var obj = this, index = 0, change = function(e){ console.log(e) };
			obj.el = el;
			obj.elem = $(el);
			obj.index = function() { if (arguments.length == 1) { index = arguments[0]; obj.elem.find("li").removeClass("active").find("a").eq(index).click(); return this } return index }
			obj.show = function(ind) { obj.elem.find("a").eq(ind).tab("show"); return this; }
			obj.change = function(func) { change = func; return this }
			obj.elem.find("a").each(function(i,o){ $(o).data("index", i) });
			obj.elem.find("li").each(function(i,o){ if ($(o).hasClass("active")) index = i; });
			obj.elem.on("show.bs.tab", function(e){ index = $(e.target).data("index"); change(e) });
		}
	});
	self.table = new(function(){
		var that = this;
		that.receiptList = new BootstrapTable("#receiptList");
		function Table(el) {
			this.el = el;
			this.elem = $(el);
			var obj = this.elem;
			function reorder() {
				var rows = obj.find("tbody tr");
				for (var i = 0, m = rows.length; i < m; i++) { $(rows[i]).find("td:eq(0)").text(i + 1) }
			};
			this.insert = function(array, showRemove) {
				var b = this.elem.find("tbody"); if(b.length < 1) { this.elem.append("<tbody></tbody>"); b = this.elem.find("tbody") }
				b.append("<tr>"
					+ (!runningNo ? "" : '<td>'+ (this.size() + 1) +'</td>')
					+ $.map(array, function(v,i){ return "<td>"+ v +"</td>" }).join("")
					+ (!showRemove ? "" : '<td style="width:40px;text-align:center"><a href="#" class="delList"><span class="glyphicon glyphicon-trash"></span></a></td>') 
				+"</tr>")
			};
			this.remove = function(index) { this.elem.find("tbody tr").eq(index).remove(); reorder() };
			this.data = function() {
				var data = [];
				if (arguments.length != 1) {
					var rows = this.elem.find("tbody tr");
					for (var i = 0, m = rows.length; i < m; i++) {
						var row = [];
						for (var j = 0, n = rows[i].children.length; j < n; j++) row.push(rows[i].children[j].innerText);
						data.push(row);
					}
					return data;
				}
				for (var i = 0, m = (data = arguments[0]).length; i < m; i++) { this.insert(data[i]); }
			};
			this.size = function() { return this.elem.find("tbody tr").length };
			$(this.el).on("click", ".delList", function(){ $(this).parent().parent().remove(); reorder() });
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
			obj.sumInput = function(index) { var sum = 0; obj.elem.find("tbody tr").each(function(i,o){ var val = o.children[index].children[0].value.replace(/[,]/g, ""); sum += (!$.isNumeric(val) ? 0 : parseFloat(val, 10)) }); return sum }
			obj.expand = function() { obj.elem.find(".detail-icon i.icon-plus").click(); return this };
			obj.collapse = function() { obj.elem.find(".detail-icon i.icon-minus").click(); return this };
			obj.checkboxEvent = function(func) { this.elem.on("check.bs.table uncheck.bs.table check-all.bs.table uncheck-all.bs.table", func); return this };
			obj.checkEvent = function(func) { checkEvt = func; return this };
			obj.uncheckEvent = function(func) { uncheckEvt = func; return this };
			obj.checkboxEvent(function(e) { if (e.type === "check") checkEvt(e); if (e.type === "uncheck") uncheckEvt(e) });
		}
	});
window.LoadingPanel = function (id) { var obj = this, loadCnt = 0, setupFunc, loadFunc; obj.elem = null;
	obj.finish = function(html){ checkElemReady(); obj.elem.css("font", "").html(html); }
	obj.toString = function(){ var elem; setupFunc = setTimeout(function(){ loadFunc = setInterval(function(){ (elem || (elem = document.getElementById(id))).innerHTML = "Loading"+ Array(++loadCnt).join("..") }, 250) }, 1000); return "<div id='"+ id +"' style='font:BOLD 16pt Arial'></div>" }
	function checkElemReady(){ if ((obj.elem = $("#"+ id).css("font", "")).length != 1) { alert("The element hasn't insert into HTML DOM."); throw "The element hasn't insert into HTML DOM."; } clearTimeout(setupFunc); clearInterval(loadFunc) }
}
window.templateId = function(id){ var template = document.getElementById(id).cloneNode(true); return {
		"bind": function(arrOfObj){ var stack = [], chld;
			function getCurr(c, i) { return c.childNodes.length > i ? c.childNodes[i] : null }
			function getNext(c   ) { return !c || c.nextSibling == null ? null : c.nextSibling }
			function getChld(e   ) { return e == null || e.childNodes.length < 1 ? null : e.childNodes[0] }
			function isElem(e)     { return e.nodeType === 1 }
			function parse(ex, obj){ var e = ex.substring(1, ex.length-1).split(":"), p = e[0], f = e[1]; if (f && window[f]) return window[f](obj[p]); return obj[p] }
			var curr = getCurr(template, 0), next = getNext(curr);
			while(curr != null) {
				if (!isElem(curr)) {
					curr = next; if (curr == null && stack.length > 0) { curr = getNext(stack.pop()); next = curr } 
					next = getNext(next); continue
				} chld = getChld(curr);
				if (curr.getAttribute("loop") == "true") {
					var html = curr.innerHTML, reg = (/\{[a-zA-Z0-9_\:]+\}/g), htmlContent = "", found, props = [];
					while (found = reg.exec(html)) { reg.lastIndex = (html.indexOf(found[0]) + found[0].length); props.push(found[0]); }
					for (var i = 0, m = (arrOfObj || []).length; i < m; i++) {
						var o = arrOfObj[i], htmlBinded = html;
						for (var j = props.length-1; j > -1; j--) {
							htmlBinded = htmlBinded.replace(props[j], parse(props[j], o))
						}
						htmlContent += htmlBinded;
					}
					curr.innerHTML = htmlContent
					chld = null;
				}
				if (chld != null) { stack.push(curr); curr = chld; next = getNext(curr); }
				else              { curr = next; next = getNext(next); }
			}
			return template.innerHTML;
		}
	};
}

	self.mainMessageDialog = new Message("#mainMessageDialog");
	return this;
})(jQuery);

view.button.search.click(function(res){
	var billNo = view.input.billNo.val();
	var receiptNo = view.input.receiptNo.val();
	view.mainMessageDialog.clear();
	if (billNo.length < 4 && receiptNo.length < 4) {
		view.mainMessageDialog.error(["Please fill in to input at least 4 characters."]).show();
		return;
	}
	var url = "", params = {};
	if (billNo.length > 3) { url = "../service/receipts/search/invNo"; params = { "invNo": billNo } }
	else { url = "../service/receipts/search/no"; params = { "no": receiptNo } }
	get(url, params, function(res){
		if (!res._embedded.receipts || res._embedded.receipts.length < 1) { view.mainMessageDialog.error(["There is no data for your search criteria."]).show(); return; }
		view.mainMessageDialog.clear();
		var cancelList = (view.session("cancelList") || []);
		view.button.addCancelReceiptList.disable();
		view.button.sumCancelReceiptList.disable(cancelList.length < 1).badge(cancelList.length);
		view.dialog.navigatePanel.show();
		view.dialog.customerPanel.show();
		var data = $.map(res._embedded.receipts, function(o){
			if ((o.attributes || "").indexOf("C") > -1) {
				return null;
			}
			return {
				 "id": o.id
				,"no": o.no
				,"date": view.utils.dateTimeFormat(o.updateDttm)
				,"custNo": o.accountNo
				,"custName": o.name
				,"payMethod": o.type
				,"total": o.received
				,"branch": o.shopPayment
				,"status": "ปกติ"
				,"payments": o.payments
				,"username": o.updateUser
				,"invoices": o._links.invoices.href
			};
		});
		view.table.receiptList.data(data);
	}, view.mainMessageDialog);
});
view.button.addCancelReceiptList.click(function() {
	var cancelList = view.session("cancelList");
	view.session("cancelList", cancelList.concat(view.table.receiptList.selected()));
});
view.table.receiptList.checkboxEvent(function() {
	var receiptList = view.table.receiptList.selected();
	if (receiptList.length > 0) {
		view.button.addCancelReceiptList.enable();
	}
});

$(function(){
	var isNew = view.utils.queryString()["new"];
	if (isNew) {
		view.session("cancelList", []);
	}
	var cancelList = view.session("cancelList");
	if (cancelList.length > 0) {
		view.button.addCancelReceiptList.disable();
		view.button.sumCancelReceiptList.badge(cancelList.length);
		view.dialog.navigatePanel.show();
	}
});

function detailFormatter(ind, row) {
	var loadPanel = new LoadingPanel(view.utils.guid());
	$.get(row.invoices, function(res){
		loadPanel.finish(templateId("detailsTableTemplate").bind(res._embedded.invoices))
	});
	return loadPanel;
}
// ---------------------------------------------------------------------
    var $table = $('#myTable');

    $table.on('expand-row.bs.table', function (e, index, row, $detail) {
        var res = $("#desc" + index).html();
        $detail.html(res);
    });
    $(document).ready(function () {
        $("#status_print").hide();
    });
    function submit_payment() {
        $("#status_print").show();
        setTimeout(' $("#status_print").hide()', 3000);
    }
    function detail_invoice(refno) {
        $.ajax({
            type: 'POST',
            url: 'detail_invoice.jsp',
            data: {refno: refno},
            success: function (data) {
                //alert(data);
                $("#show_detail_invoice_" + refno).prepend(data);
                $("#show_detail_invoice_" + refno).removeClass("hidden");
            }
        });
    }
    function show_price(refno) {
        // alert(refno);
        if ($("#checkbox_" + refno).prop("checked")) {
            $("#text_price_" + refno).addClass("hidden");
            $("#input_price_" + refno).removeClass("hidden");
            $("#input_price_" + refno).focus();
        } else {
            $("#text_price_" + refno).removeClass("hidden");
            $("#input_price_" + refno).addClass("hidden");
        }
    }
    $("#btn_save_c").click(function () {
        //alert("ok");
        var dataSet = {
            c_Num: $("#c_Num").val(),
            c_IdBank: $("#c_IdBank").val(),
            c_Bank: $("#c_Bank").val(),
            c_Bank_branch: $("#c_Bank_branch").val(),
            c_Date: $("#c_Date").val(),
            c_money: $("#c_money").val()
        };
        $.ajax({
            type: 'POST',
            url: 'data_chack.jsp',
            data: dataSet,
            success: function (data) {
                //alert(data);
                $("#data_chack_show").prepend(data);
                $("#c_Num").val("");
                $("#c_IdBank").val("");
                $("#c_Bank").val("");
                $("#c_Bank_branch").val("");
                $("#c_Date").val("");
                $("#c_money").val("");
            }
        });
    });
    
    function showTab1(tab) {
        // $('#myTab1 a[href="#tab-1-3"]').tab('show');
        $('#myTab1 a[href="#tab-1-' + tab + '"]').tab('show');
    }
    function showTab2(tab) {
        $('#myTab2 a[href="#tab' + tab + '"]').tab('show');
                // $('#myTab1 a[href="#tab-1-3"]').tab('show');

    }
</script>
</html>