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
	        <div class="col-md-12 tab-modefile">
	        	<ol class="breadcrumb">
                    <li><i>ยกเลิกรับชำระค่าบริการ (ต่อ)</i></li>
                    <li><i>ข้อมูลยกเลิกรับชำระค่าบริการ</i></li>
                    <li class="active">ดำเนินการออกใบเสร็จรับเงิน</li>
                </ol>
	        </div>
	    </div>
	    <!--------------------------------------->
	    <ul id="navigatePanel" class="list-inline pull-right list-menu-set">
	        <li><a id="addAppAdjustReceiptList" href="action-cancel-service-charge_1.jsp" class="btn btn-link"><span class="glyphicon glyphicon-arrow-left"></span> ย้อนกลับก่อนหน้า</a></li>
	        <li><a id="submitPayment" class="btn btn-link"><span class="glyphicon glyphicon-print"></span> บันทึกและพิมพ์</a></li>
	        <li style="padding-left:22px;"></li>	
	    </ul>
	    <!--------------------------------------->
		<div class="row">
			<div class="col-md-12 tab-modefile">
				<!-- Nav tabs -->
				<ul class="nav nav-tabs" role="tablist">
					<li role="presentation" class="active"><a href="#tab_1"
						aria-controls="tab_2" role="tab" data-toggle="tab" onClick="copyCustomerProfile()"><span
							class="glyphicon glyphicon-list"></span> รายละเอียดใบเสร็จรับเงิน</a></li>
				</ul>
					<div class="panel panel-default panal-radius">
					<div class="panel-body">
						<div class="tab-content">
							<div role="tabpanel" class="tab-pane active" id="tab_1">
								<div class="form-horizontal">
									<div class="form-group">
										<label class="control-label col-sm-2">เลขที่ใบเสร็จรับเงิน (เดิม) :</label>
										<div class="col-sm-2">
											<input class="form-control" id="inputReceiptNo1" value="EP0171701F150714000001" disabled="disabled">
										</div>
										<label class="control-label col-sm-2">วันเวลาที่พิมพ์  (เดิม) :</label>
										<div class="col-sm-2">
											<input class="form-control" id="inputPrintTime1" value="05/02/2016 12:45" disabled="disabled">
										</div>
										<div class="col-sm-2"></div>
									</div>
									<div class="form-group">
										<label class="control-label col-sm-2">เลขที่ลูกค้า :</label>
										<div class="col-sm-2">
											<input class="form-control" id="inputCustomerCustNo1" value="700006573" disabled="disabled">
										</div>
										<label class="control-label col-sm-2">Tax ID :</label>
										<div class="col-sm-2">
											<input class="form-control" id="inputCustomerTaxNo1" value="2147560B45156" disabled="disabled">
										</div>
										<div class="col-sm-2"></div>
									</div>
									<div class="form-group">
										<label class="control-label col-sm-2">ชื่อลูกค้า : </label>
										<div class="col-sm-6">
											<input class="form-control" id="inputCustomerName1" value="บริษัท ABC จำกัด">
										</div>										
									</div>
									<div class="form-group">
										<label class="control-label col-sm-2">ที่อยู่ :</label>
										<div class="col-sm-6">
											<textarea class="form-control" id="inputCustomerAddress1" name="inputCustomerAddress1">319 อาคารจัตุรัสจามจุรี ชั้น 22-41 ซอย ทดสอบข้อมูลซอย ถนนพญาไท  แขวงปทุมวัน เขตปทุมวัน กรุงเทพมหานคร 10330</textarea>
										</div>										
									</div>
								</div>
							</div>	
						</div>
					</div>
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
					<h4 class="modal-title">Confirm</h4>
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
											ยืนยันยกเลิกรับชำระค่าบริการ</label>
											
									</div>
								</div>
							</div>
							<div class="col-md-2"></div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<a id="cancelReceiptList" href="cancel-service-charge_4.jsp" class="btn btn-default"><span class="glyphicon glyphicon-ok-circle"></span> ตกลง</a>
					<a class="btn btn-default" data-dismiss="modal"><span class="glyphicon glyphicon-remove-circle"></span> ยกเลิก</a>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
var view = (function($){
	var self = this, errorMsgEl = "errorMsg", defaultErrorMessage = "An error occurred but there is no message response.";
	self.button = new(function(){
		var that = this;
		that.setPayType = new ButtonGroup("#setPayType");
		that.cancelReceiptList = new Button("#cancelReceiptList");
		that.search = new Button("#search", "pay_service_charge.json", ["#customerRefNo"]);
		function Button(el, url, inputs) {
			var url = url, obj, done = function(res) { console.log(res) };
			this.el = el;
			this.elem = obj = $(el);
			this.click = function(func) { this.elem.click(func) };
			this.hide = function() { this.elem.addClass("hide") };
			this.show = function() { this.elem.removeClass("hide") };
			this.done = function(func) { done = func };
			if (url) {
				obj.click(function(){
					obj.button("loading");
					var errorMsg = $("#"+ errorMsgEl).addClass("hide").removeClass("alert alert-success alert-danger");
					var params = {}; $.each(inputs, function(i,n){ var elem = $(n);
						params[elem.attr("name")] = elem.val()
					});
					$.post(url, params, function(res){
						obj.button("reset");
						if (res.success) done(res);
						else { errorMsg.addClass("alert alert-danger").html(
							res.errorMsg ? res.errorMsg : "An error occurred but there is no message response.")
						}
					});
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
		that.requiredApprovalPanel = new RequiredApprovalModal("#requiredApprovalPanel", "#username", "#password", "#approvalBtn", "checkAuthorize.json");
		function Modal(el) {
			this.el = el;
			this.elem = $(el);
			this.hide = function() { this.elem.modal("hide") };
			this.show = function() { this.elem.modal("show") };
		}
		function RequiredApprovalModal(el, usrEl, pwdEl, btnEl, url) {
			var obj = this, msgDiv, smtBtn;
			var doneFunc = function(res){ console.log(res) }, failFunc = doneFunc;
			obj.el = el;
			obj.elem = $(el);
			obj.usrInp = $(usrEl).keyup(function(e){ if (e.which == 13) obj.pwdInp.focus() });
			obj.pwdInp = $(pwdEl).keyup(function(e){ if (e.which == 13) smtBtn.click() });
			obj.hide = function() { this.elem.modal("hide") };
			obj.show = function() { this.elem.modal("show") };
			obj.fail = function(func){ failFunc = func };
			obj.done = function(func){ doneFunc = func };
			obj.submit = smtBtn = $(btnEl);
			obj.submit.click(function(){
				smtBtn.button("loading"); msgDiv.addClass("hide").removeClass("alert alert-success alert-danger");
				$.post(url, { "username": obj.usrInp.val(), "password": obj.pwdInp.val() }, function(res){
					smtBtn.button("reset");
					if (!res.error) doneFunc(res); else {
						msgDiv.removeClass("hide").addClass("alert alert-danger").html(
						(res.error || defaultErrorMessage)) }
				});
			});
			if (obj.elem.length == 1) {
				obj.elem.find(".modal-header").prepend("<div id='messageResponse'></div>")
				msgDiv = obj.elem.find("#messageResponse").addClass("hide");
			}
		}
		function Panel(el) {
			this.el = el;
			this.elem = $(el);
			this.hide = function() { this.elem.addClass("hide") };
			this.show = function() { this.elem.removeClass("hide") };
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
		that.cancelReasonType = new DropDown("#cancelReasonType");
		that.val = function() { if (arguments.length == 1) { $.each(arguments[0],function(k,v){ $("#"+ k).val(v) }) } };
		function Input(el) {
			this.el = el;
			this.elem = $(el);
			this.val = function() { if (arguments.length == 1) { this.elem.val(arguments[0]) } return this.elem.val() };
		}
		function DropDown(el, url) {
			var obj, data = [{ key: "", value: "Please Select" }];
			this.el = el;
			this.elem = obj = $(el);
			this.data = function(array) { if ($.type(array) == "array") { setup(data = array) } return data; }
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
			this.init(url);
		}
	});
	self.table = new(function(){
		var that = this;
		that.cancelList = new BootstrapTable("#cancelList");
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
			return val.indexOf("{") > -1 || val.indexOf("[") > -1 ? JSON.parse(val) : val;
		}
		window.sessionStorage.setItem(key, 
			($.type(val) != "object" && $.type(val) != "array" ? val : JSON.stringify(val)))
	};
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

view.input.cancelReasonType.data([{ key: "", value: "- กรุณาเลือกระบุเหตุผลการยกเลิก -"}, { key: "wrongName", value: "ชื่อ-ที่อยู่ ไม่ถูกต้อง"},{ key: "wrongInvoice", value: "ผิดใบแจ้งค่าใช้บริการ" }]);
view.dialog.requiredApprovalPanel.done(function() {
	location.href = "cancel-pay-1-stap_3.jsp";
});

$(function(){
	var cancelList = view.session("cancelList");
	view.table.cancelList.data(cancelList);
});

function detailFormatter(ind, row) {
	var loadPanel = new LoadingPanel(view.utils.guid());
	$.get(row.invoices, function(res){
		var bindedTemplate = templateId("detailsTableTemplate").bind(res._embedded.invoices);
		console.log(bindedTemplate);
		loadPanel.finish(bindedTemplate);
	});
	return loadPanel;
}
function detailFormatter(val, row, ind) {
	return    	'<table class="table table-striped table-bordered">'+
			    	'<thead>'+
			    		'<tr>'+
			    		'<th class="text-center">รายการ</th>'+
			    		'<th class="text-center">จำนวนเงิน</th>'+
			    		'<th class="text-center">ภาษีมูลค่าเพิ่ม</th>'+
			    		'<th class="text-center">ส่วนลด</th>'+
			    		'<th class="text-center">จำนวนเงินทั้งสิ้น</th>'+
			    		'</tr>'+
			    	'</thead>'+
			    	'<tbody>'+
			    		'<tr><td class="text-center">Inv No. 200058589 : Sub No. 864460704</td><td class="text-center">399.00</td><td class="text-center">27.93</td><td class="text-center">0.00</td><td class="text-center">426.93</td></tr>'+
			    		'<tr><td class="text-center">Inv No. 200055037 : Sub No. 864460704</td><td class="text-center">399.00</td><td class="text-center">27.93</td><td class="text-center">0.00</td><td class="text-center">426.93</td></tr>'+
			    		'<tr><td class="text-center">Inv No. 200057589 : Sub No. 864460704</td><td class="text-center">399.00</td><td class="text-center">27.93</td><td class="text-center">0.00</td><td class="text-center">426.93</td></tr>'+
			    	'</tbody>'+
		    	'</table>';	
}
</script>
</html>
