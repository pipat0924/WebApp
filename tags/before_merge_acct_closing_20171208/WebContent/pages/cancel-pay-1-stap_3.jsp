<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP Page</title>
<link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="resources/bootstrap-table/src/bootstrap-table.css" rel="stylesheet" type="text/css" />
<link href="resources/custom.css" rel="stylesheet" type="text/css" />
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
			<div class="col-md-12">
				<ol id="breadcrumb" class="breadcrumb">
					<li><i>ยกเลิกรับชำระค่าบริการ</i></li>
					<li>ค้นหาข้อมูลการรับชำระ</li>
					<li>ระบุเหตุผลการยกเลิกรับชำระ</li>
					<li class="active">ผลการยกเลิกรับชำระ</li>
				</ol>
				<div id="mainMessageDialog"></div>
			</div>
		</div>
		<ul id="navigationPanel3" class="list-inline pull-right list-menu-set">
			<li><a id="confirmCancelBtn" href="#confirmCancel" data-toggle="modal" class="btn btn-link"><span class="glyphicon glyphicon-th-list"></span> ดำเนินการยกเลิก</a></li>
		</ul>
		<ul id="navigationPanel4" class="list-inline pull-right list-menu-set" style="display: none">
			<li><a href="cancel-pay-1-stap_1.jsp?new"><span class="glyphicon glyphicon-log-out"></span> กลับไปเพิ่มรายการ</a></li>
		</ul>
		<div class="row">
			<div class="col-md-12">
				<div class="panel-heading">
                    <h3 class="panel-title"><span class="glyphicon glyphicon-shopping-cart"></span> รายการขอยกเลิกรับชำระ</h3>
                </div>
				<table id="cancelList" data-toggle="table" data-detail-view="true" data-detail-formatter="detailFormatter" data-classes="table table-hover table-striped">
					<thead>
						<tr>
                               <th data-formatter="runningFormatter">#</th>
                               <th data-field="no" data-align="center">เลขที่ใบเสร็จรับเงิน</th>
                               <th data-field="date" data-align="center">วันที่รับชำระ</th>
                               <th data-field="custNo" data-align="center">เลขที่ลูกค้า</th>
                               <th data-field="custName">ชื่อลูกค้า</th>
                               <th data-field="payMethod">วิธีการชำระเงิน</th>
                               <th data-field="total" data-align="right" data-formatter="numberFormatter">ยอดเงิน</th>
                               <th data-field="branch" data-align="center">สถานที่รับชำระ</th>
                               <th data-field="username">ผู้รับชำระ</th>
                               <th data-field="status" data-align="center">เหตุผลการยกเลิก</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</section>

	<div class="modal fade" role="dialog" id="confirmCancel">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title"><span class="glyphicon glyphicon-warning-sign"></span> ข้อความแจ้งเตือน</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-md-12">
							<div class="col-md-2"></div>
							<div class="col-md-10">
								<div class="form-horizontal">
									<div class="form-group">
										<label class="col-sm-12 control-label "
											style="font-size: 25px; text-align: center"><span
											class="glyphicon glyphicon-question-sign"></span>
											ยืนยันการรับชำระยกเลิกหรือไม่</label>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<a id="cancelReceiptList" class="btn btn-success"><span class="glyphicon glyphicon-ok-circle"></span> ตกลง</a>
					<a class="btn btn-danger" data-dismiss="modal"><span class="glyphicon glyphicon-remove-circle"></span> ยกเลิก</a>
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
	var self = this, defaultErrorMessage = "An error occurred but there is no message response.";
	self.breadcrumb = new(function(){
		var that = this;
		that.breadcrumb = new Breadcrumb("#breadcrumb");
		function Breadcrumb(el) {
			var obj = this, list, index = 0;
			obj.el = el;
			obj.elem = $(el);
			obj.index = function(){ if(arguments.length == 1) { list.removeClass("active").eq(index = arguments[0]).addClass("active"); return obj } return index }
			list = obj.elem.find("li").each(function(i,o){ $(o).data("index", i) });
			index = list.filter(".active").data("index");
		}
	});
	self.button = new(function(){
		var that = this;
		that.confirmCancelBtn = new Button("#confirmCancelBtn");
		that.cancelReceiptList = new Button("#cancelReceiptList");
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
	});
	self.dialog = new(function(){
		var that = this;
		that.mainMessageDialog = new Message("#mainMessageDialog");
		that.confirmCancel = new Modal("#confirmCancel");
		function Modal(el) {
			this.el = el;
			this.elem = $(el);
			this.hide = function() { this.elem.modal("hide") };
			this.show = function() { this.elem.modal("show") };
		}
	});
	self.panel = new(function(){
		var that = this;
		that.navigationPanel3 = new Panel("#navigationPanel3");
		that.navigationPanel4 = new Panel("#navigationPanel4");
		function Panel(el) { var obj = this, dura = 300; obj.el = el; obj.elem = $(el);
			obj.hide = function(ms) { obj.elem.hide(ms || dura); return this }; obj.show = function(ms) { obj.elem.show(ms || dura); return this }
		}
	});
	self.table = new(function(){
		var that = this;
		that.cancelList = new BootstrapTable("#cancelList");
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
	});
	//// AUTOMATIC REGISTER FORMATTER FUNCTION ////
	window.runningFormatter = function(val, row, ind) { return ind + 1 }
	window.numberFormatter = function(val, row, ind) { return !$.isNumeric(val) ? "0.00" : parseFloat(val, 10).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,') }
	window.stringInputFormatter = function(val, row, ind) { return '<input value="'+ $.trim(val) +'" maxLength="40" class="form-control">' }
	window.numberInputFormatter = function(val, row, ind) { return '<input value="'+ (val || "0.00") +'" maxLength="10" class="form-control text-right" onkeypress="return $.isNumeric(String.fromCharCode(event.which)) || (event.which == 46 && this.value.indexOf(\'.\') == -1)" onfocus="(function(o){ o.value = o.value.replace(/[,]/g, \'\') })(this)" onblur="(function(o){ o.value = parseFloat(o.value, 10).toFixed(2).replace(/(\\d)(?=(\\d{3})+\\.)/g, \'$1,\'); })(this)">' }
	function Message(el) {
		var obj = this;
		obj.el = el;
		obj.elem = $(el);
		obj.hide = function() { obj.elem.addClass("hide"); return obj };
		obj.show = function(flag) { if (flag == null || flag) obj.elem.removeClass("hide"); else obj.hide(); return obj };
		obj.clear = function() { obj.elem.find("*").remove(); obj.hide(); return obj };
		obj.message = function(arr, cls) { $.each(arr, function(i,o) { var m = o; if ($.type(o) === "object") { m = (o.desc || o.messageDesc) +" [code="+ (o.code || o.messageCode) +"]" }; obj.elem.append('<div class="'+ cls +'">'+ m +'<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button></div>') }); return obj };
		obj.error = function(arr) { return obj.message(arr, "alert alert-danger") };
		obj.warn = function(arr) { return obj.message(arr, "alert alert-warning") };
		obj.success = function(arr) { return obj.message(arr, "alert alert-success") };
	}
	self.utils = {
		guid: function(){ function r() { return Math.floor((Math.random() + 1) * 0x10000).toString(16).substring(1) } return r() + r() +"-"+ r() +"-"+ r() +"-"+ r() +"-"+ r() + r() + r() },
		numberFormat: function(num, dec) { return $.type(num) !== "number" ? "0.00" : num.toFixed(dec || 2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,'); },
		dateFormat: function() { var obj = arguments[0]; if ($.type(obj) == "string" && /[0-9]{2}.[0-9]{2}.[0-9]{4}/g) { return obj } else if ($.isNumeric(obj)) { var dt = new Date(obj), dd = dt.getDate().toString(), mm = (dt.getMonth() + 1).toString(), yyyy = dt.getFullYear().toString(); return (dd[1]?"":"0") + dd +"/"+ (mm[1]?"":"0") + mm +"/"+ yyyy } else if ($.type == "date") { return "" } return "" },
		dateTimeFormat: function() { var obj = arguments[0], dt = $.type(obj) != "date" ? ($.isNumeric(obj) ? new Date(obj) : new Date()) : new Date(), dd = dt.getDate().toString(), MM = (dt.getMonth() + 1).toString(), yyyy = dt.getFullYear().toString(), hh = dt.getHours().toString(), mm = dt.getMinutes().toString(), ss = dt.getSeconds().toString(); return (dd[1]?"":"0") + dd +"/"+ (MM[1]?"":"0") + MM +"/"+ yyyy +" "+ (hh[1]?"":"0") + hh +":"+ (mm[1]?"":"0") + mm +":"+ (ss[1]?"":"0") + ss },
		queryString: function() { var pos = location.href.indexOf("?"), obj = {}; if (pos < 0) return obj; var params = location.href.slice(pos + 1).split("&"); for (var i = 0, m = params.length; i < m; i++) { pos = params[i].indexOf("="); if (pos < 0) { obj[params[i]] = true; continue; } obj[params[i].substring(0, pos)] = params[i].substring(pos + 1) } return obj },
		window: function(windowName, url, side) { window.windowOpened = (window.windowOpened || {}); var screenProp = ""; if (side == "left") { screenProp = "left=0,top=0,width="+ (screen.width/2) +",height="+ (screen.height-100) } else if (side == "right") { screenProp = "left="+ (screen.width/2-40) +",top=0,width="+ (screen.width/2+40) +",height="+ (screen.height-100) } window.windowOpened[windowName] = window.open(url, windowName, "menubar=no,scrollbars=no,resizable=0,"+ screenProp, false); return window.windowOpened }
	};
	self.session = function(key, val) {
		if (!val) {
			var val = window.sessionStorage.getItem(key);
			return val && (val.indexOf("{") > -1 || val.indexOf("[") > -1) ? JSON.parse(val) : val;
		}
		window.sessionStorage.setItem(key, 
			($.type(val) != "object" && $.type(val) != "array" ? val : JSON.stringify(val)))
	};
	self.contextPath = '${pageContext.request.contextPath}/';
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
	return this;
})(jQuery);

view.button.cancelReceiptList.click(function() {
	var button = $(this).button("loading");
	view.dialog.mainMessageDialog.clear();
	var params = {}
	$.each(view.table.cancelList.data(), function(i,o){
		params["receipts["+ i +"].id"] = o.id;
		params["receipts["+ i +"].no"] = o.no;
	});
	$.post("../cancelReceiptList.json", params, function(res){
		button.button("reset");
		view.dialog.confirmCancel.hide();
		if (!res || res.statusCode != "0") { view.dialog.mainMessageDialog.error(res.errorList).warn(res.warningList).success(res.successList).show(); return; }
		view.panel.navigationPanel3.hide(0);
		view.panel.navigationPanel4.show(0);
		view.breadcrumb.breadcrumb.index(3);
		view.dialog.mainMessageDialog.success(["The receipt list have cancelled successfully."]).show();
		view.session("cancelList", []);
	});
});
$(function(){
	var cancelList = view.session("cancelList");
	view.table.cancelList.data(cancelList);
});

function detailFormatter(ind, row) {
	var loadPanel = new LoadingPanel(view.utils.guid());
	$.get(row.invoices, function(res){
		loadPanel.finish(templateId("detailsTableTemplate").bind(res._embedded.invoices))
	});
	return loadPanel;
}
</script>
</html>
