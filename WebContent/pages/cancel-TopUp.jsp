<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><%=application.getInitParameter("episName")%></title>
<link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<link href="resources/bootstrap-table/src/bootstrap-table.css"
	rel="stylesheet" type="text/css" />
<link href="resources/css/datepicker.min.css" rel="stylesheet"
	type="text/css" />
<link href="resources/custom.css" rel="stylesheet" type="text/css" />
<script src="resources/jquery.min.js" type="text/javascript"></script>
<script src="resources/bootstrap/js/bootstrap.min.js"
	type="text/javascript"></script>
<script src="resources/bootstrap-table/src/bootstrap-table.js"></script>
<script src="resources/js/bootstrap-datepicker.min.js"></script>
<script src="resources/js/mustache.min.js" type="text/javascript"></script>
<script src="resources/custom.js" type="text/javascript"></script>
</head>
<body>
	<header class="header_page"></header>
	<section class="container-fluid menu">
		<!-- <button class="menu-toggle btn btn-sm btn-default" style="margin-bottom: 5px"><span class="glyphicon glyphicon-step-forward"></span></button> -->
    <%-- <%@include  file="menu.jsp" %> --%>
		<div class="row">
			<div class="col-md-12">
				<ol class="breadcrumb" id="breadcrumb">
					<li><i>ยกเลิกการเติมเงินออนไลน์ (Top up)</i></li>
					<li class="active">ค้นหาข้อมูลการรับชำระ</li>
					<li>ระบุเหตุผลการยกเลิกรับชำระ</li>
					<li>สรุปการยกเลิกรับชำระ</li>
					<li>ผลการยกเลิกรับชำระ</li>
				</ol>
				<div id="message"></div>
			</div>
		</div>
		<div class="row hide" id="panelSearchReceiptList">
			<div class="col-md-12">
				<label class="label-panal label-warning">ค้นหาข้อมูลการรับชำระ</label>
				<div class="panel panel-default panal-radius">
					<div class="panel-body">
						<div class="col-md-12">
							<div class="form-horizontal">
								<div class="form-group">
									<label class="control-label col-sm-2">ประเภทบริการ :</label>
									<div class="col-sm-2">
										<select class="form-control" id="inputTopupService"></select>
									</div>
									<label class="control-label col-sm-2">หมายเลขบริการ :</label>
									<div class="col-sm-2">
										<input class="form-control" id="inputSearchServiceNo">
									</div>
									<div class="col-sm-4">
										<button class="btn btn-primary" id="buttonSearchReceipt">
											<span class="glyphicon glyphicon-search"></span> ค้นหา
										</button>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-sm-2">เลขที่ใบเสร็จรับเงิน
										:</label>
									<div class="col-sm-2">
										<input class="form-control" id="inputSearchReceiptNo">
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<ul class="list-inline pull-right hide" id="panelNavigation1">
			<li><a id="addCancelReceiptList" class="btn btn-link"><span
					class="glyphicon glyphicon-plus-sign"></span> เพิ่มรายการการยกเลิก</a></li>
			<%--<li><a id="sumCancelReceiptList" class="btn btn-link"><span class="glyphicon glyphicon-th-list"></span> สรุปรายการการยกเลิก</a><span class="badge badge_modefil">0</span></li>--%>
		</ul>
		<div class="row hide" id="panelCancelReceiptList1">
			<div class="col-md-12">
				<div class="table-responsive">
					<table data-toggle="table" id="tableReceiptList" data-pagination="true" data-page-size="5" data-page-list="[5, 10, 20, 50, 100, 200]" data-cache="false">
						<thead>
							<tr>
								<th data-field="checked" data-radio="true"></th>
								<th data-align="center" data-formatter="runningFormatter">#</th>
								<th data-field="no" data-align="center">เลขที่ใบเสร็จรับเงิน</th>
								<th data-field="ref1" data-align="center">เลขที่อ้างอิง</th>
								<th data-field="updateDttm" data-align="center"
									data-formatter="dateFormatter">วันที่รับชำระ</th>
								<th data-field="accountNo" data-align="center">เลขที่ลูกค้า</th>
								<th data-field="accountName">ชื่อลูกค้า</th>
								<%--<th data-field="accountType" data-align="center" data-formatter="customerTypeFormatter">กลุ่มผู้ใช้บริการ</th>--%>
								<th data-field="custCategoryDesc" data-align="center">กลุ่มผู้ใช้บริการ</th>
								<%--<th data-field="services" data-align="center" data-formatter="serviceTypesFormatter">ชื่อบริการ</th>--%>
								<th data-field="totalCharge" data-align="center"
									data-formatter="numberFormatter">ยอดเงิน</th>
								<th data-field="branchName" data-align="center">สถานที่รับชำระ</th>
								<th data-field="updateUser" data-align="center">ผู้รับชำระ</th>
								<th data-field="status" data-align="center"
									data-formatter="statusFormatter">สถานะ</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
		<div class="row hide" id="panelCancelReasonType">
			<div class="col-md-12 tab-modefile">
				<ul class="nav nav-tabs" role="tablist">
					<li role="presentation" class="active"><a href="#tab_cus"
						aria-controls="tab_cus" role="tab" data-toggle="tab"><span
							class="glyphicon glyphicon-folder-open"></span>
							เหตุผลยกเลิกรับชำระ</a></li>
				</ul>
				<div class="tab-content">
					<div role="tabpanel" class="tab-pane active" id="tab_cus">
						<div class="panel panel-default panal-radius">
							<div class="panel-body">
								<div class="row">
									<div class="col-md-12">
										<div class="form-horizontal">
											<div class="form-group">
												<label class="control-label col-sm-2">โปรดระบุตัวเลือก
													:</label>
												<div class="col-sm-3">
													<select id="cancelReasonType" class="form-control"></select>
												</div>
												<div class="col-sm-7"></div>
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
		<ul class="list-inline pull-right hide" id="panelNavigation2">
			<%--<li><a id="buttonSearchReceiptList" href="cancel-TopUp.jsp?new" class="btn btn-link"><span class="glyphicon glyphicon-arrow-left"></span> กลับไปเพิ่มรายการ</a></li>--%>
			<li><a id="buttonProceedReceiptList" class="btn btn-link"><span
					class="glyphicon glyphicon-remove-sign"></span>
					เลือกรายการการยกเลิก</a></li>
		</ul>
		<div class="row hide" id="panelCancelReceiptList2">
			<div class="col-md-12 tab-modefile">
				<div class="panel-heading">
					<h3 class="panel-title">
						<span class="glyphicon glyphicon-shopping-cart"></span>
						รายการรับชำระ
					</h3>
				</div>
				<table data-toggle="table" data-detail-view="true"
					data-detail-formatter="serviceDetailsFormatter"
					id="tableCancelList1" data-pagination="true" data-page-size="5" data-page-list="[5, 10, 20, 50, 100, 200]" data-cache="false">
					<thead>
						<tr>
							<th data-align="center" data-formatter="runningFormatter">#</th>
							<th data-align="center" data-field="no">เลขที่ใบเสร็จรับเงิน</th>
							<th data-field="ref1" data-align="center">เลขที่อ้างอิง</th>
							<th data-align="center" data-formatter="dateFormatter"
								data-field="updateDttm">วันที่รับชำระ</th>
							<th data-align="center" data-field="accountNo">เลขที่ลูกค้า</th>
							<th data-field="accountName">ชื่อลูกค้า</th>
							<%--<th data-align="center" data-formatter="customerTypeFormatter" data-field="accountType">กลุ่มผู้ใช้บริการ</th>--%>
							<th data-align="center" data-field="custCategoryDesc">กลุ่มผู้ใช้บริการ</th>
							<%--<th data-align="center" data-formatter="serviceTypesFormatter" data-field="services">ชื่อบริการ</th>--%>
							<th data-align="right" data-formatter="numberFormatter"
								data-field="totalCharge">ยอดเงิน</th>
							<th data-align="center" data-field="branchName">สถานที่รับชำระ</th>
							<th data-align="center" data-field="updateUser">ผู้รับชำระ</th>
							<th data-align="center" data-formatter="statusFormatter"
								data-field="status">สถานะ</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
		<ul class="list-inline pull-right hide" id="panelNavigation3">
			<li><a href="#dialogConfirmCancel" data-toggle="modal"><span
					class="glyphicon glyphicon-th-list"></span> ดำเนินการยกเลิก</a></li>
			<li style="padding-left: 22px;"></li>
		</ul>
		<div class="row hide" id="panelConfirmCancelList">
			<div class="col-md-12 tab-modefile">
				<div class="panel-heading">
					<h3 class="panel-title">
						<span class="glyphicon glyphicon-shopping-cart"></span>
						รายการขอยกเลิกรับชำระ
					</h3>
				</div>
				<table data-toggle="table" data-detail-view="true"
					data-detail-formatter="serviceDetailsFormatter"
					id="tableCancelList2" data-pagination="true" data-page-size="5" data-page-list="[5, 10, 20, 50, 100, 200]" data-cache="false">
					<thead>
						<tr>
							<th data-align="center" data-formatter="runningFormatter">#</th>
							<th data-align="center" data-field="no">เลขที่ใบเสร็จรับเงิน</th>
							<th data-field="ref1" data-align="center">เลขที่อ้างอิง</th>
							<th data-align="center" data-formatter="dateFormatter"
								data-field="updateDttm">วันที่รับชำระ</th>
							<th data-align="center" data-field="accountNo">เลขที่ลูกค้า</th>
							<th data-field="accountName">ชื่อลูกค้า</th>
							<%--<th data-align="center" data-formatter="customerTypeFormatter" data-field="accountType">กลุ่มผู้ใช้บริการ</th>--%>
							<th data-align="center" data-field="custCategoryDesc">กลุ่มผู้ใช้บริการ</th>
							<%--<th data-align="center" data-formatter="serviceTypesFormatter" data-field="services">ชื่อบริการ</th>--%>
							<th data-align="right" data-formatter="numberFormatter"
								data-field="totalCharge">ยอดเงิน</th>
							<th data-align="center" data-field="branchName">สถานที่รับชำระ</th>
							<th data-align="center" data-field="updateUser">ผู้รับชำระ</th>
							<th data-align="center" data-field="cancelReasonDesc">เหตุผลการยกเลิก</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
		<ul class="list-inline pull-right hide" id="panelNavigation4">
			<li><a href="cancel-TopUp.jsp?new" class="btn btn-link"><span
					class="glyphicon glyphicon-arrow-left"></span>
					กลับไปยกเลิกการเติมเงินออนไลน์ </a></li>
			<li style="padding-left: 22px;"></li>
		</ul>
		<div class="row hide" id="panelResultCancelList">
			<div class="col-md-12 tab-modefile">
				<div class="panel-heading">
					<h3 class="panel-title">
						<span class="glyphicon glyphicon-shopping-cart"></span>
						ผลของการขอยกเลิกรับชำระ
					</h3>
				</div>
				<table data-toggle="table" data-detail-view="true"
					data-detail-formatter="serviceDetailsFormatter"
					id="tableCancelList3" data-pagination="true" data-page-size="5" data-page-list="[5, 10, 20, 50, 100, 200]" data-cache="false">
					<thead>
						<tr>
							<th data-align="center" data-formatter="runningFormatter">#</th>
							<th data-align="center" data-field="no">เลขที่ใบเสร็จรับเงิน</th>
							<th data-field="ref1" data-align="center">เลขที่อ้างอิง</th>
							<th data-align="center" data-formatter="dateFormatter"
								data-field="updateDttm">วันที่รับชำระ</th>
							<th data-align="center" data-field="accountNo">เลขที่ลูกค้า</th>
							<th data-field="accountName">ชื่อลูกค้า</th>
							<%--<th data-align="center" data-formatter="customerTypeFormatter" data-field="accountType">กลุ่มผู้ใช้บริการ</th>--%>
							<th data-align="center" data-field="custCategoryDesc">กลุ่มผู้ใช้บริการ</th>
							<%--<th data-align="center" data-formatter="serviceTypesFormatter" data-field="services">ชื่อบริการ</th>--%>
							<th data-align="right" data-formatter="numberFormatter"
								data-field="totalCharge">ยอดเงิน</th>
							<th data-align="center" data-field="branchName">สถานที่รับชำระ</th>
							<th data-align="center" data-field="updateUser">ผู้รับชำระ</th>
							<th data-align="center"
								data-formatter="cancelProcessStatusFormatter">สถานะ</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
		<input type="hidden" id="userAuthen" />
	</section>
	<div class="modal fade" role="dialog" id="modal_authen">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">Authentication</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-md-12">
							<div class="col-md-2"></div>
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
					<button type="button" class="btn btn-default">
						<span class="glyphicon glyphicon-ok-circle"></span> ตกลง
					</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">
						<span class="glyphicon glyphicon-remove-circle"></span> ยกเลิก
					</button>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade" role="dialog" data-back-drop="static"
		id="dialogConfirmCancel">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">
						<span class="glyphicon glyphicon-warning-sign"></span>
						ข้อความแจ้งเตือน
					</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-md-12">
							<div class="col-md-2"></div>
							<div class="col-md-8">
								<div class="form-horizontal">
									<div class="form-group">
										<label class="col-sm-12 control-label "
											style="font-size: 25px; text-align: center"><span
											class="glyphicon glyphicon-question-sign"></span>
											ยืนยันยกเลิกรับชำระ</label>
									</div>
								</div>
							</div>
							<div class="col-md-2"></div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<a id="buttonCancelReceiptList" data-dismiss="modal"
						class="btn btn-success"><span
						class="glyphicon glyphicon-ok-circle"></span> ตกลง</a> <a
						class="btn btn-danger" data-dismiss="modal"><span
						class="glyphicon glyphicon-remove-circle"></span> ยกเลิก</a>
				</div>
			</div>
		</div>
	</div>
	<script id="template" type="x-tmpl-mustache">
	<table class="table table-striped table-bordered">
		<thead>
			<tr>
			<th class="text-center">ประเภทบริการ </th>
			<%--<th class="text-center">ชื่อบริการ</th>--%>
			<th class="text-center">หมายเลขบริการ</th>
			<th class="text-right">จำนวน</th>
			<th class="text-right">ภาษีมูลค่าเพิ่ม</th>
			<th class="text-right">ส่วนลด</th>
			<th class="text-right">จำนวนเงิน</th>
			</tr>
		</thead>
		<tbody>
		{{#invoices}}
			<tr><td class="text-center">{{type}}</td>
			<%--<td class="text-center">{{name}}</td>--%>
			<td class="text-center">{{no}}</td>
			<td class="text-right">{{amount}}</td>
			<td class="text-right">{{vat}}</td>
			<td class="text-right">{{discount}}</td>
			<td class="text-right">{{totalCharge}}</td></tr>
		{{/invoices}}
		</tbody>
	</table>
	</script>
</body>
</html>
<script>
var view = (function($){
	self.session = function(key, val) { if (!val) { var val = window.sessionStorage.getItem(key); return val && (val.indexOf("{") > -1 || val.indexOf("[") > -1) ? JSON.parse(val) : val } window.sessionStorage.setItem(key, ($.type(val) != "object" && $.type(val) != "array" ? val : JSON.stringify(val)))	};
	self.utils = {
		guid: function(){ function r() { return Math.floor((Math.random() + 1) * 0x10000).toString(16).substring(1) } return r() + r() +"-"+ r() +"-"+ r() +"-"+ r() +"-"+ r() + r() + r() },
		numberFormat: function(num, dec) { return $.type(num) !== "number" ? "0.00" : num.toFixed(dec || 2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,'); },
		dateFormat: function() { var obj = arguments[0]; if ($.type(obj) == "string" && /[0-9]{2}.[0-9]{2}.[0-9]{4}/g) { return obj } else if ($.isNumeric(obj)) { var dt = new Date(obj), dd = dt.getDate().toString(), mm = (dt.getMonth() + 1).toString(), yyyy = dt.getFullYear().toString(); return (dd[1]?"":"0") + dd +"/"+ (mm[1]?"":"0") + mm +"/"+ yyyy } else if ($.type == "date") { return "" } return "" },
		timeFormat: function() { var obj = arguments[0]; if ($.type(obj) == "string" && /[0-9]{2}.[0-9]{2}.[0-9]{4}/g) { return obj } else if ($.isNumeric(obj)) { var dt = new Date(obj), hh = dt.getHours().toString(), mm = dt.getMinutes().toString(), ss = dt.getSeconds().toString(); return (hh[1]?"":"0") + hh +":"+ (mm[1]?"":"0") + mm +":"+ (ss[1]?"":"0") + ss } else if ($.type == "date") { return "" } return "" },
		dateTimeFormat: function() { var obj = arguments[0], dt = $.type(obj) != "date" ? ($.isNumeric(obj) ? new Date(obj) : new Date()) : new Date(), dd = dt.getDate().toString(), MM = (dt.getMonth() + 1).toString(), yyyy = dt.getFullYear().toString(), hh = dt.getHours().toString(), mm = dt.getMinutes().toString(), ss = dt.getSeconds().toString(); return (dd[1]?"":"0") + dd +"/"+ (MM[1]?"":"0") + MM +"/"+ yyyy +" "+ (hh[1]?"":"0") + hh +":"+ (mm[1]?"":"0") + mm +":"+ (ss[1]?"":"0") + ss },
		queryString: function() { var pos = location.href.indexOf("?"), obj = {}; if (pos < 0) return obj; var params = location.href.slice(pos + 1).split("&"); for (var i = 0, m = params.length; i < m; i++) { pos = params[i].indexOf("="); if (pos < 0) { obj[params[i]] = true; continue; } obj[params[i].substring(0, pos)] = (params[i].substring(pos + 1) || true) } return obj },
		window: function(windowName, url, side) { window.windowOpened = (window.windowOpened || {}); var screenProp = ""; if (side == "left") { screenProp = "left=0,top=0,width="+ (screen.width/2) +",height="+ (screen.height-100) } else if (side == "right") { screenProp = "left="+ (screen.width/2-40) +",top=0,width="+ (screen.width/2+40) +",height="+ (screen.height-100) } window.windowOpened[windowName] = window.open(url, windowName, "menubar=no,scrollbars=no,resizable=0,"+ screenProp, false); return window.windowOpened }
	};
	//// AUTOMATIC REGISTER FORMATTER FUNCTION ////
	window.get = function(url, params, success, msgDialog, preCheck){ $.ajax({ "url": url, "type": "GET", "data": params, "error": msgDialog.valid, "success": function(res){ (preCheck || function(o){ console.log(o) })(res); if (msgDialog && !msgDialog.valid(res)) { return; } success(res) } }) }
	window.post = function(url, params, success, msgDialog, preCheck){ $.ajax({ "url": url, "type": "POST", "data": params, "error": msgDialog.valid, "success": function(res){ (preCheck || function(o){ console.log(o) })(res); if (msgDialog && !msgDialog.valid(res)) { return; } success(res) } }) }
	window.padLeft = function(obj, size, ch){ return padding(obj, size, ch, true) }; window.padRight = function(obj, size, ch){ return padding(obj, size, ch, false) }; function padding(obj, size, ch, isLeft) { var str = ""; if (!obj) str = ""; else if($.type(obj) == "string") str = obj; else if ($.type(obj) == "number") str = String(obj); if (str.length >= size) return str; var padded = Array(Math.max(size - str.length + 1, 0)).join(ch); return (isLeft ? padded : "") + str + (isLeft ? "" : padded) }
	window.stripToNumber = function(str) { return $.type(str) === "string" && $.isNumeric(str = str.replace(/[,A-Za-z\(\)\[\]\{\}]/g, "")) ? parseFloat(str, 10) : 0 }
	window.toDateString = function(ddMMyyyy){ return ddMMyyyy.replace(/(\d{2}).(\d{2}).(\d{4})/g, "$2/$1/$3") }
	window.runningFormatter = function(val, row, ind) { return ind + 1 }
	window.numberFormatter = function(val, row, ind) { return !$.isNumeric(stripToNumber(val)) ? "0.00" : parseFloat(val, 10).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,') }
	window.dateFormatter = function(val, row, ind){ if (!val) return ""; if ((/(\d{4}).(\d{2}).(\d{2}).*/g).test(val)) return val.replace(/(\d{4}).(\d{2}).(\d{2}).*/g, "$3/$2/$1"); return val }
	window.timeFormatter = function(val, row, ind){ if (!val) return ""; if ((/.*T(\d{2}).(\d{2}).(\d{2}).*/g).test(val)) return val.replace(/.*T(\d{2}).(\d{2}).(\d{2}).*/g, "$1:$2:$3"); return val }
	window.dateTimeFormatter = function(val, row, ind){ if (!val) return ""; if ((/(\d{4}).(\d{2}).(\d{2})T(\d{2}).(\d{2}).(\d{2}).*/g).test(val)) return val.replace(/(\d{4}).(\d{2}).(\d{2})T(\d{2}).(\d{2}).(\d{2}).*/g, "$3/$2/$1 $4:$5:6"); return val }
	window.stringInputFormatter = function(val, row, ind) { return '<input value="'+ $.trim(val) +'" maxLength="40" class="form-control">' }
	window.numberInputFormatter = function(val, row, ind) { return '<input value="'+ self.utils.numberFormat(parseFloat(val || "0.00", 10)) +'" maxLength="10" style="width:100px" class="form-control text-right" onkeypress="return $.isNumeric(String.fromCharCode(event.which)) || (event.which == 46 && this.value.indexOf(\'.\') == -1)" onfocus="(function(o){ o.value = o.value.replace(/[,]/g, \'\') })(this)" onblur="(function(o){ o.value = parseFloat(o.value, 10).toFixed(2).replace(/(\\d)(?=(\\d{3})+\\.)/g, \'$1,\'); })(this)">' }
	window.selectButtonFormatter = function(val, row, ind) { return '<a class="btn btn-primary btn-xs selList" onclick=\'(function(elem, row, ind){ var table = $("#"+ $(elem).closest("table")[0].id); window[table[0].id +"SelectEvent"](table, row, ind) })(this, '+ JSON.stringify(row) +', '+ ind +')\'><span class="glyphicon glyphicon-check"></span> Select</a>' }
	window.modifyButtonFormatter = function(val, row, ind) { return '<a class="btn btn-primary btn-xs modList" onclick=\'(function(elem, row, ind){ var table = $("#"+ $(elem).closest("table")[0].id); window[table[0].id +"ModifyEvent"](table, row, ind) })(this, '+ JSON.stringify(row) +', '+ ind +')\'><span class="glyphicon glyphicon-pencil"></span> Modify</a>' }
	window.removeButtonFormatter = function(val, row, ind) { return '<a class="btn btn-primary btn-xs delList" onclick=\'(function(elem, row, ind){ $("#"+ $(elem).closest("table")[0].id).bootstrapTable("remove", { field: "index", values: [ind] }) })(this, '+ JSON.stringify(row) +', '+ ind +')\'><span class="glyphicon glyphicon-trash"></span> Remove</a>' }
	window.LoadingPanel = function(id) { var obj = this, loadCnt = 0, setupFunc, loadFunc; function checkElemReady(){ if ((obj.elem = $("#"+ id).css("font", "")).length != 1) { alert("The element hasn't insert into HTML DOM."); throw "The element hasn't insert into HTML DOM."; } clearTimeout(setupFunc); clearInterval(loadFunc) }; return { "elem": null, "finish": function(html){ checkElemReady(); this.elem.css("font", "").html(html); return this }, "toString": function(){ var elem; setupFunc = setTimeout(function(){ loadFunc = setInterval(function(){ (elem || (elem = document.getElementById(id))).innerHTML = "Loading"+ Array(++loadCnt).join(".."); if (loadCnt > 60) loadCnt = 2 }, 250) }, 1000); return "<div id='"+ id +"' style='font:BOLD 16pt Arial'></div>" } } }
function Breadcrumb(el) { var obj = this; obj.el = el; obj.elem = $(el == null ? ".breadcrumb" : el); var list = obj.elem.find("li").each(function(i,o){ $(o).data("index", i) }), index = list.filter(".active").data("index");
	obj.index = function(){ if(arguments.length == 1) { list.removeClass("active").eq(index = arguments[0]).addClass("active"); return obj } return index }
	obj.next = function(){ obj.index(++index); return this }; obj.prev = function(){ obj.index(--index); return this }
}
function Button(el) { var obj = this, badge; obj.el = el; obj.elem = $(el);
	obj.hide = function() { this.elem.addClass("hide"); return this }; obj.show = function() { this.elem.removeClass("hide"); return this };
	obj.hideLoad = function(){ obj.elem.button("reset"); return this }; obj.showLoad = function(){ obj.elem.button("loading"); return this };
	obj.disable = function(flag) { if (flag == null || flag) this.elem.addClass("disabled").attr("disabled", true); else this.elem.removeClass("disabled").attr("disabled", false); return this }; obj.enable = function() { obj.disable(false); return this };
	obj.badge = function(val) { if (badge) badge.text(val) }; if ((badge = this.elem.next()).length == 0) badge = null;
	obj.elem.click(window[el.substring(1) +"ClickEvent"]);
}
function AuthenticationDialog(succFunc, failFunc, url, title, usrLabel, pwdLabel, okBtn, cnBtn) { var obj = this, done = succFunc, fail = failFunc, cancel = function(){}, inputs; obj.el = "modal_authentication";
	var content = '<div class="row"><div class="col-md-12"><div class="col-md-offset-2 col-md-10"><div class="form-horizontal"><div class="form-group"><label class="col-sm-3 control-label">'+ (usrLabel || "User Name :") +'</label><div class="col-sm-6"><input type="text" class="form-control" id="userName"></div></div><div class="form-group"><label class="col-sm-3 control-label">'+ (pwdLabel || "Password :") +'</label><div class="col-sm-6"><input type="password" class="form-control"></div></div></div></div></div></div>';
	var hdrCS = '<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>';
	var hdrTT = '<h4 class="modal-title"><span class="glyphicon glyphicon-user"></span> '+ (title || "Authentication") +'</h4>';
	var divMG = '<div class="alert alert-danger hide"></div>';
	var btnOK = '<button type="button" class="btn btn-success"><span class="glyphicon glyphicon-ok-circle"></span> '+ (okBtn || "OK") +'</button>';
	var btnCN = '<button type="button" class="btn btn-danger" data-dismiss="modal"><span class="glyphicon glyphicon-remove-circle"></span> '+ (cnBtn || "Cancel") +'</button>';
	obj.elem = $(document.body).append('<div class="modal fade" role="dialog" data-backdrop="static" id="'+ obj.el +'"><div class="modal-dialog"><div class="modal-content"><div class="modal-header">'+ hdrCS + hdrTT +'</div><div class="modal-body">'+ divMG + content +'</div><div class="modal-footer">'+ btnOK + btnCN +'</div></div></div></div>').find("#"+ obj.el); inputs = obj.elem.find("input").keyup(function(e){ var key = (e.which || e.keyCode || e.charCode || 0); if (key != 13) return true; obj.elem.find("input, button").eq(e.target.type == "text" ? 2 : 3).focus(); return true }); buttons = obj.elem.find("button"); 
	buttons.eq(1).on({ click: function(){ $("#userAuthen").val($("#userName").val()); $.post(url, { username: inputs.eq(0).val(), password: inputs.eq(1).val() }, function(res){ if (!res || res.statusCode != "0") { obj.elem.find(".alert").text("You don't have permission to access this feature. Please contact to Administration for more information.").removeClass("hide"); fail(res); return; } done(res); obj.hide() }) } }).end().filter(function(index){ return index == 0 || index == 2 }).on({ click: function(){ cancel() } })
	obj.show = function(){ obj.elem.modal("show"); obj.elem.find("div.alert").addClass("hide").text(""); inputs.val(""); return this }; obj.hide = function(){ obj.elem.modal("hide"); return this }
	obj.done = function(func) { done = func; return this }; obj.fail = function(func) { fail = func; return this }; obj.cancel = function(func) { cancel = func; return this }
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
function Panel(el, hide) { var obj = this, dura = 500, counter = 0, cntFunc; obj.el = el; obj.elem = $(el); if ($.inArray(obj.elem[0].tagName.toLowerCase(), ["ol", "ul"]) > -1) { obj.elem.removeAttr("id").css("display", "").removeClass("hide").removeClass("hidden"); obj.elem = obj.elem.wrap('<div id="'+ el.substring(1) +'"></div>').parent(); } var children = obj.elem.children(), bodyEl = el.substring(1) +'-body', body = obj.elem.append('<div style="'+ (hide == null || hide ? "display: none" : "") +'" id="'+ bodyEl +'"></div>').find("#"+ bodyEl).append(children), progressType, progress = obj.elem.append('<div class="progress" style="display: none"><div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width: 0%"><span class="sr-only">0% Complete</span></div></div>').find(".progress");
	obj.center = function(){ obj.elem.removeClass(["text-left", "text-right"]).addClass("text-center"); return this }; obj.right = function(){ obj.elem.removeClass(["text-left", "text-center"]).addClass("text-right"); return this }
	obj.hide = function(ms) { if (isHidden()) return this; body.hide(ms || dura); return this }; obj.show = function(ms) { if (!isHidden()) return this; if (!ms || !$.isNumeric(ms)) ms = dura; if (ms >= 0) body.css("display", "none").removeClass("hide").removeClass("hidden").show(ms); else obj.hide(Math.abs(ms)); return this }
	obj.slideDown = function(ms){ if (isHidden()) { body.css("display", "none").removeClass("hide").removeClass("hidden").slideDown(ms || dura, arguments[1] || function(){}) } return this }; obj.slideUp = function(ms){ if (!isHidden()) { body.slideUp(ms || dura, arguments[1] || function(){}) } return this }
	obj.progress = function(){ return { "elem": progress, "show": function(ms){ body.slideUp(ms || 10); progress.slideDown(); return this }, "hide": function(ms){ body.slideDown(ms || 10); progress.slideUp(); return this }
		,"success": function(){ return this.style("progress-bar-success") }, "info": function(){ return this.style("progress-bar-info") }, "warn": function(){ return this.style("progress-bar-warning") }, "error": function(){ return this.style("progress-bar-danger") }, "plain": function(){ return obj.style("") }, "style": function(clsName) { $(this.elem[0].childNodes[0]).removeClass(progressType).addClass(progressType = clsName); return this }
		,"percent": function(perc){ if (!$.isNumeric(perc) || (perc > 100 || perc < 0)) return this; this.elem[0].childNodes[0].style.width = perc +"%"; return this }
		,"timeCnt": function(seconds) { var o = this; this.percent(counter = 0); cntFunc = setInterval(function(){ o.percent(100/seconds * ++counter); if (seconds < counter) { clearInterval(cntFunc); o.hide(1000) } }, 1000); return this }
	} }; function isHidden() { return body.css("display") === "none" || body.hasClass("hide") || body.hasClass("hidden") }; obj.elem.css("display", "").removeClass("hide").removeClass("hidden");
}
function Input(el, maxLen, propPos) { var obj = this; obj.el = el; obj.elem = $(el);
	obj.error = function(flag) { if (arguments.length == 0 || flag) obj.elem.parent().addClass("has-error"); else obj.elem.parent().removeClass("has-error"); return this }
	obj.clear = function() { obj.val(""); return this }; obj.isEmpty = function() { return $.trim(obj.val()) === "" }; obj.isNumeric = function() { return $.isNumeric(obj.val()) }
	obj.nextFocus = function(nextElem) { if (nextElem && "Input|Button".indexOf(nextElem.constructor.name) > -1) { obj.elem.keyup(function(e){ var key = (e.which || e.keyCode || e.charCode || 0); if (key == 13) nextElem.elem.focus(); return true }) } return this };
	obj.click = function(func) { obj.elem.click(func); return this }
	obj.readOnly = function(flag) { obj.elem.attr("readonly", (flag == null ? true : flag)); return this }
	obj.disable = function(flag) { obj.elem.attr("disabled", (flag == null ? true : flag)); return this }
	obj.enable = function() { obj.disable(false); return this }
	obj.val = function() { if (arguments.length == 1) { this.elem.val(arguments[0]) } return $.trim(this.elem.val()) }
	obj.get = function(propName) { if ($.type(propPos[propName]) !== "array" || propPos[propName].length !== 2) return ""; return obj.val().substring(propPos[propName][0], propPos[propName][1]) }
	obj.elem.keyup(function(e){ var func = (window[el.substring(1) +"KeyUpEvent"] || function(){}); func((e.which || e.keyCode || e.charCode || 0), obj.elem) }); obj.elem.focus(function(){ this.select() }); if ($.isNumeric(maxLen)) { obj.elem.attr("maxLength", maxLen) }
}
function NumberInput(el, dec) { var obj = this, decimal = (dec == null ? 2 : dec); obj.el = el; obj.elem = $(el).addClass("text-right");
	obj.val = function() { if (arguments.length == 0) return parseFloat(strip(this.elem.val() || "0"), 10); this.elem.val(format(arguments[0])); return this }
	obj.decimal = function(dec) { decimal = dec }; obj.format = format;
	obj.disable = function(){ obj.elem.attr("disabled", (arguments.length != 1 ? true : arguments[0])); return obj }; obj.enable = function(){ obj.disable(false); return obj }; obj.readOnly = function(flag) { obj.elem.attr("readonly", (flag == null ? true : flag)); return this }
	obj.elem.keypress(function(e) { var key = (e.which || e.keyCode || e.charCode || 0); var ch = String.fromCharCode(key); return "0123456789.".indexOf(ch) > -1 });
	obj.elem.focus(function(){ this.value = strip(this.value); this.select() });
	obj.elem.blur(function(){ this.value = format(this.value) });
	function format(val) { var str = ($.isNumeric(val) ? parseFloat(val, 10) : obj.val()).toFixed(decimal == 0 ? 1 : decimal).replace(/(\d)(?=(\d{3})+\.)/g, '$1,'); return decimal == 0 ? str.substring(0, str.lastIndexOf(".")) : str };
	function strip(str) { return (str || "").replace(/[,]/g, "") }
	if (obj.elem.val() == "") obj.elem.val("0" + (decimal < 1 ? "" : "."+ Array(decimal + 1).join("0")));
}
function DropDown(el, url) { var obj = this, data = [{ key: "", value: "", text: "Please Select" }]; obj.el = el; obj.elem = $(el);
	obj.data = function(array) { if ($.type(array) == "array") { setup(data = array); return this } return data; }
	obj.init = function(url) { if (url) $.get(url, function(res) { setup(data = res.data) }); else setup(data); return this };
	obj.initDFAll = function(url) { if (url) $.get(url, function(res) { setupDFAll(data = res.data) }); else setupDFAll(data); return this };
	obj.index = function() { if (arguments.length == 1) { var maxLen = data.length, index = arguments[0]; obj.elem.find("option").each(function(i, opt){ opt.removeAttribute("selected") }).eq(index < 0 ? maxLen + index : index).attr("selected", true); return this } return $.inArray("selected", obj.elem.find("option").map(function(i, opt){ return opt.selected ? "selected" : "no-selected" })) }
	obj.selected = function(){ return data[obj.index()]; }; obj.val = function() { return obj.elem.val(); }; obj.key = function() { return obj.elem.find("option:selected").data("key") }
	function setupDFAll(array) { obj.elem.find("*").remove();
		obj.elem.append('<option data-index="'+ 0 +'" data-key="" value="">ทั้งหมด</option>');
		$.each(array,function(i,o){ obj.elem.append('<option data-index="'+ i +'" data-key="'+ o.key +'" value="'+ o.value +'">'+ (o.text || o.value) +'</option>') }); }
	function setup(array) { obj.elem.find("*").remove();
        $.each(array,function(i,o){ obj.elem.append('<option data-index="'+ i +'" data-key="'+ o.key +'" value="'+ o.value +'">'+ (o.text || o.value) +'</option>') }); }
	data = obj.elem.change(window[el.substring(1) +"ChangeEvent"]).find("option").map(function(i, opt){ return { key: opt.getAttribute("data-key"), value: opt.value, text: opt.textContent } });
}
function Listbox(el, url) {
   var obj = this, data = [ { key : "", value : "กรุณาเลือก" } ]; obj.el = el; obj.elem = $(el);
   obj.data = function(array) { if ($.type(array) == "array") { setup(data = array); return this; } return data; };
   obj.init = function(url, param) { if (url) $.get(url, param, function(res) { data = res; data.splice(0, 0, {key : "", value : "กรุณาเลือก"}); setup(data); }); else setup(data); return this; };
   obj.index = function() { if (arguments.length == 1) { var maxLen = data.length, index = arguments[0]; obj.elem.find("option").each(function(i, opt) { opt.removeAttribute("selected") }).eq(index < 0 ? maxLen + index : index).attr("selected", true); return this } return $.inArray("selected", obj.elem.find("option").map( function(i, opt) { return opt.selected ? "selected" : "no-selected" })) }
   obj.selected = function() { return data[obj.index()]; };
   obj.val = function() { return obj.elem.val(); };
   obj.key = function() { if (arguments.length == 0) { var key = obj.elem.find("option:selected").data("key"); if (typeof key === "string" && key === "undefined") { var item = data[obj.index()]; key = item && item.key ? item.key : item.id } return key } var key = arguments[0]; for (var i = 0, m = data.length; i < m; i++) { var item = data[i]; if (item.key && item.key == key) { obj.index(i); break; } if (item.id && item.id == key) { obj.index(i); break; } } return this }
   function setup(array) { obj.elem.find("*").remove(); $.each(array, function(i, o) { obj.elem.append('<option data-index="' + i + '" data-key="' + o.key + '" value="' + o.key + '">' + (o.value) + '</option>') }); }
   data = obj.elem.change(window[el.substring(1) + "OnChange"]).find("option").map(function(i, opt) { return { key : opt.getAttribute("data-key"), value : opt.value, text : opt.textContent } });
   obj.error = function(flag) { if (flag == true) { obj.elem.parent().addClass("has-error"); return true; } else if (flag == false) { obj.elem.parent().removeClass("has-error"); return false; } else if (obj.val() == '') { obj.elem.parent().addClass("has-error"); return true; } else { obj.elem.parent().removeClass("has-error"); return false; } }
}
function BootstrapTable(el, options) { var obj = this, rawData = [], successFunc = function(){}, checkEvt = function(e){ console.log(e) }, uncheckEvt = checkEvt; obj.el = el; obj.elem = $(el).bootstrapTable(options = $.extend({ uniqueId: "id", responseHandler: function(res){ if (!res) return []; if (res.constructor === Array) return res; if (!res.data || res.data.constructor !== Array) return []; return res.data }, onLoadSuccess: function(res){ rawData = res; obj.resetView(400); successFunc(res) }, onCheck: window[el.substring(1) +"CheckEvent"], onUncheck: window[el.substring(1) +"UncheckEvent"], onCheckAll: window[el.substring(1) +"CheckAllEvent"], onUncheckAll: window[el.substring(1) +"UncheckAllEvent"] }, options));
	obj.clear = function() { obj.elem.bootstrapTable("removeAll"); return obj }; obj.remove = function(index){ obj.elem.bootstrapTable("remove", { field: "index", values: [index] }); return this }; obj.resetView = function(ms){ setTimeout(function(){ obj.elem.bootstrapTable("resetView") }, ms || 200); return this }; obj.isEmpty = function(){ return obj.data().length == 0 }
	obj.showLoad = function() { this.elem.bootstrapTable("showLoading"); return this }; obj.hideLoad = function() { this.elem.bootstrapTable("hideLoading"); return this };
	obj.update = function(rec){ var id = rec.id, index = rec.index, row; if ((row = obj.getId(id)) != null) { obj.elem.bootstrapTable("updateRow", { "index": row.index, "row": $.extend(row, rec) }) } else if ((row = obj.data()[index]) != null) { obj.elem.bootstrapTable("updateRow", { "index": index, "row": $.extend(row, rec) }) } else obj.insert(rec); return this }; obj.insert = function(row) { var data = obj.elem.bootstrapTable("getData"); obj.elem.bootstrapTable("insertRow", { "index": data.length, "row": $.extend(row, { "index": data.length }) }); return this }
	obj.find = function(field, value) { var data = obj.elem.bootstrapTable("getData"); return $.map(data, function(o,i){ return o[field] === value ? o : null }) }
	obj.map = function(col) { var resultMap = {}; $.each(obj.data(), function(ind, row){ resultMap[row[col]] = row }); return resultMap }
	obj.data = function() { if (arguments.length == 1) { this.elem.bootstrapTable("load", rawData = arguments[0]); return this } return $.map(this.elem.bootstrapTable("getData"), function(row){ return row }) }; obj.getSelections = function(){ return obj.elem.bootstrapTable("getAllSelections") }
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
	var paramGroup = {"key" : "TOPUP_CANCEL_REASON"};
	this.breadcrumb = new Breadcrumb();
	this.message = new Message("#message");
	this.dialogAuthentication = new AuthenticationDialog(null, null, "../checkAuthorize.json");
	this.dialogConfirmCancel = $("#dialogConfirmCancel");
	this.buttonSearchReceipt = new Button("#buttonSearchReceipt");
	this.buttonAddCancelReceiptList = new Button("#addCancelReceiptList");
	this.buttonSumCancelReceiptList = new Button("#sumCancelReceiptList");
	this.buttonProceedReceiptList = new Button("#buttonProceedReceiptList");
	this.buttonCancelReceiptList = new Button("#buttonCancelReceiptList");
	this.inputTopupService = new DropDown("#inputTopupService").initDFAll("../findTopupServiceType.json");
	this.inputSearchServiceNo = new Input("#inputSearchServiceNo");
	this.inputSearchReceiptNo = new Input("#inputSearchReceiptNo");
	
	this.cancelReasonType = new Listbox("#cancelReasonType").init("../MasterData/getGroupData",paramGroup);
	//this.inputCancelReasonType = new DropDown("#cancelReasonType").data([{ key: "", value: "- กรุณาเลือกระบุเหตุผลการยกเลิก -"}, { key: "wrongName", value: "ชื่อ-ที่อยู่ ไม่ถูกต้อง"},{ key: "wrongInvoice", value: "ผิดใบแจ้งค่าใช้บริการ" }]);
	this.panelSearchReceiptList = new Panel("#panelSearchReceiptList").slideDown();
	this.panelNavigation1 = new Panel("#panelNavigation1");
	this.panelNavigation2 = new Panel("#panelNavigation2");
	this.panelNavigation3 = new Panel("#panelNavigation3");
	this.panelNavigation4 = new Panel("#panelNavigation4");
	this.panelCancelReceiptList1 = new Panel("#panelCancelReceiptList1");
	this.panelCancelReceiptList2 = new Panel("#panelCancelReceiptList2");
	this.panelCancelReasonType = new Panel("#panelCancelReasonType");
	this.panelConfirmCancelList = new Panel("#panelConfirmCancelList");
	this.panelResultCancelList = new Panel("#panelResultCancelList");
	this.tableReceiptList = new BootstrapTable("#tableReceiptList");
	this.tableCancelList1 = new BootstrapTable("#tableCancelList1");
	this.tableCancelList2 = new BootstrapTable("#tableCancelList2");
	this.tableCancelList3 = new BootstrapTable("#tableCancelList3");
	return this;
})(jQuery);

function customerTypeFormatter(val, row, ind) { return val.toLowerCase() == "organization" ? "ธุรกิจทั่วไป" : "บุคคลทั่วไป" }
function serviceTypesFormatter(val, row, ind) { return $.map(val, function(row){ return row.productName }).join(", ") }
function statusFormatter(val, row, ind) { return row.attributes.indexOf("R") > -1 ? "ยกเลิกสำเร็จ" : (row.attributes.indexOf("C") > -1 ? "ปกติ" : "ไม่พร้อมทำรายการ") }
function serviceDetailsFormatter(ind, row) {
	return Mustache.render($('#template').html(), { "invoices" : $.map(row.services, function(inv, i){
		return {
			 "type": inv.serviceName//inv.serviceCode
			,"name": inv.serviceName+' '+inv.serviceNo
			,"no":inv.serviceNo
			,"amount": view.utils.numberFormat(inv.amount)
			,"vat": view.utils.numberFormat(inv.vat)
			,"discount": view.utils.numberFormat(inv.discount)
			,"totalCharge": view.utils.numberFormat(inv.totalCharge)
		}
	}) })
}
function buttonSearchReceiptClickEvent() {
	var url = "", params = {};
	view.message.clear();
	var ss = inputSearchReceiptNo.val();
	
	     if (!view.inputSearchServiceNo.isEmpty()||!view.inputSearchReceiptNo.isEmpty() ) { url = "../invPayTypeTopup.json";    params= { "serviceNo" : view.inputSearchServiceNo.val(),"receiptNo" : view.inputSearchReceiptNo.val(),  "serviceCode" : view.inputTopupService.val(), "payType": "TOPUP" } }
	else { view.message.error(["กรุณากรอกข้อมูล หมายเลขบริการ หรือ หมายเลขใบเสร็จก่อน"]).show(); return; }
	     get(url, params, function(res){
		view.panelCancelReceiptList1.slideDown(500, function(){ view.panelNavigation1.slideDown() });/* 
		var receipts = res; */
		view.tableReceiptList.data(res);
/*  		view.tableReceiptList.data($.map(receipts, function(receipt){
			//if (receipt.attributes.indexOf("R") > - 1) return null;
			if (view.inputTopupService.val()!='' && receipt.services[0].serviceCode != view.inputTopupService.val()) return null; 
			return receipt;
		})); */
	}, view.message);
}
function addCancelReceiptListClickEvent() {
	var receiptList = view.tableReceiptList.getSelections();
	if (receiptList.length == 0) {
		view.message.clear().error(["กรุณาเลือกรายการใบเสร็จก่อนทำการยกเลิก"]).show();
		return false;
	}
	for (var i = 0, m = receiptList.length; i < m; i++) {
		if (receiptList[i].attributes.indexOf("C") == -1) {
			view.message.clear().error(["ระบบไม่อนุญาตให้เลือกรายการที่ยังไม่พร้อมทำการยกเลิก"]).show();
			return false;
		}
		if (receiptList[i].attributes.indexOf("R") >= 0) {
			view.message.clear().success(["รายการที่เลือกยกเลิกสำเร็จแล้ว"]).show();
			return false;
		}
	}	
	
	var cancelList = view.session("cancelList").concat(view.tableReceiptList.getSelections());
	view.session("cancelList", cancelList);
	if (cancelList.length > 0) {
		view.breadcrumb.next();
		view.panelSearchReceiptList.slideUp();
		view.panelNavigation1.slideUp();
		view.panelCancelReceiptList1.slideUp(500, function(){
			view.panelCancelReasonType.slideDown();
			view.panelCancelReceiptList2.slideDown(500, function(){ view.panelNavigation2.slideDown() })
		});
		view.tableCancelList1.clear().data(view.tableReceiptList.getSelections());
	}
}
function sumCancelReceiptListClickEvent() {}
function tableReceiptListCheckEvent(row) { view.buttonAddCancelReceiptList.enable(); }
function tableReceiptListCheckAllEvent(row) { view.buttonAddCancelReceiptList.enable(); }
function tableReceiptListUncheckEvent(row) { view.buttonAddCancelReceiptList.disable(view.tableReceiptList.selected().length < 1); }
function tableReceiptListUncheckAllEvent(row) { view.buttonAddCancelReceiptList.disable(); }
function buttonProceedReceiptListClickEvent() {
	if (view.cancelReasonType.index() == 0) {
		view.message.clear().error(["กรุณาเลือกเหตุผลของการทำรายการก่อน"]).show();
		return false;
	}
	view.dialogAuthentication.show().done(function(){
		var receiptList = view.tableCancelList1.data();
	 	var data = view.cancelReasonType.selected();
	 	for (var i = 0, m = receiptList.length; i < m; i++) {
		 	receiptList[i]["cancelReasonCode"] = data.key;
		 	receiptList[i]["cancelReasonDesc"] = data.value;
		}
		view.breadcrumb.next();
		view.panelNavigation2.slideUp();
		view.panelCancelReasonType.slideUp();
		view.panelCancelReceiptList2.slideUp(500, function(){
			view.panelConfirmCancelList.slideDown(500, function(){ view.panelNavigation3.slideDown() })
		});
		view.tableCancelList2.clear().data(receiptList);
	});
}
function buttonCancelReceiptListClickEvent() {
	var params = { "userAuthen": $("#userAuthen").val() }
	$.each(view.tableCancelList2.data(), function(i,o){
		params["receipts["+ i +"].id"] = o.id;
		params["receipts["+ i +"].no"] = o.no;
	});
	post("../cancelPaymentTopup.json", params, function(res){
		view.message.clear().success(["The transaction is successfully processed."]).show();
		view.panelNavigation3.slideUp();
		view.panelConfirmCancelList.slideUp(500, function(){
			view.breadcrumb.next();
			view.panelResultCancelList.slideDown(500, function(){ view.panelNavigation4.slideDown() });
		});
		view.tableCancelList3.clear().data(view.tableCancelList2.data());
	}, view.message);
}
function cancelProcessStatusFormatter() { return '<span class="glyphicon glyphicon-ok-circle"></span> ยกเลิกสำเร็จ' }
$(function(){
	var isNew = view.utils.queryString()["new"];
	if (isNew) {
		view.session("cancelList", []);
	}
	var cancelList = view.session("cancelList");
	if (cancelList.length > 0) {
		//view.buttonSumCancelReceiptList.badge(cancelList.length);
		view.panelNavigation1.show();
	}
	view.buttonAddCancelReceiptList.disable();
});
</script>