<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="resources/bootstrap-table/src/bootstrap-table.css" rel="stylesheet" type="text/css"/>
    <link href="resources/css/datepicker.min.css" rel="stylesheet" type="text/css"/>
    <link href="resources/custom.css" rel="stylesheet" type="text/css"/>
    <script src="resources/jquery.min.js" type="text/javascript"></script>
    <script src="resources/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="resources/bootstrap-table/src/bootstrap-table.js"></script>
    <script src="resources/js/bootstrap-datepicker.min.js"></script>
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
                        <li><i>รับชำระเงินโอนในประเทศ</i></li>
                        <li>ค้นหาข้อมูลลูกค้า</li>
                        <li class="active">สรุปรายการเงินโอน</li>
                        <li>ผลการรับชำระ</li>
                    </ol>
                </div>
            </div>
            <div id="panelSummaryInvoiceList">
            <ul class="list-inline pull-right">
                <li><a class="btn btn-link" href="pay-4-stap_1.jsp"><span class="glyphicon glyphicon-user"></span> ค้นหาข้อมูลลูกค้า</a></li>
                <li><a class="btn btn-link" id="buttonCreatePaymentAndPrint"><span class="glyphicon glyphicon-print"></span> บันทึกและพิมพ์</a></li>
                <li><a class="btn btn-link" href="pay-4-stap_1.jsp?new"><span class="glyphicon glyphicon-remove-circle"></span> ยกเลิกรายการ</a></li>
            </ul>
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-success">
                        <div class="panel-heading"><span class="glyphicon glyphicon-shopping-cart"></span> รายการรับชำระเงิน</div>
                        <div class="panel-body">
                            <table class="table table-condensed" id="tableBillingList">
                                <thead>
                                <tr>
                                    <th data-valign="middle" data-align="center" data-running-no="true">#</th>
                                    <th data-valign="middle">เลขที่ลูกค้า</th>
                                    <th data-valign="middle">ชื่อลูกค้า</th>
                                    <th data-valign="middle">เลขที่ใบแจ้งค่าใช้บริการ</th>
                                    <th data-valign="middle">รหัสธนาคาร</th>
                                    <th data-valign="middle">ชื่อธนาคาร</th>
                                    <th data-valign="middle">เลขที่อ้างอิง</th>
                                    <th data-valign="middle">วันที่โอนเงิน</th>
                                    <th data-valign="middle" data-align="right" data-number-format="true">จำนวนเงินโอน</th>
                                    <th data-valign="middle" data-align="right" data-number-format="true">จำนวนเงินหักอื่นๆ</th>
                                    <th data-align="center" data-modify-button="true" data-remove-button="true"></th>
                                </tr>
                                </thead>
                            </table>
                            <hr>
                            <div class="form-horizontal">
                            <div class="form-group">
                                        <label class="control-label col-sm-10">จำนวนเงินก่อนหักส่วนลด :</label>
                                <div class="col-sm-2">
                                    <input class="form-control text-right" id="inputAmount" disabled>
                                </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-10">ส่วนลด (Discount) :</label>
                                    <div class="col-sm-2">
                                        <input class="form-control text-right" id="inputDiscount" disabled>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-10">จำนวนเงินที่ต้องชำระก่อนภาษีมูลค่าเพิ่ม (Charge) :</label>
                                    <div class="col-sm-2">
                                        <input class="form-control text-right" id="inputCharge" disabled>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-10">ภาษีมูลค่าเพิ่ม (Vat) :</label>
                                    <div class="col-sm-2">
                                        <input class="form-control text-right" id="inputVat" disabled>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-10">จำนวนเงินทั้งสิ้น (Total Charge) :</label>
                                    <div class="col-sm-2">
                                        <input class="form-control text-right" id="inputTotalCharge" disabled>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-10">ภาษีหัก ณ.ที่จ่าย (WT) :</label>
                                    <div class="col-sm-2">
                                        <input class="form-control text-right"  id="inputDeduction" disabled>
                                    </div>
                                </div>
                                
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            </div>
            <div id="panelSummaryReceiptList" class="hide">
            <ul class="list-inline pull-right list-menu-set">
                <li><a href="pay-4-stap_1.jsp?new"><span class="glyphicon glyphicon glyphicon-arrow-left"></span> กลับไปหน้าการรับชำระ</a></li>
            </ul>
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-success">
                        <div class="panel-heading">รายการรับชำระเงิน</div>
                        <div class="panel-body">
                            <table id="tableReceiptList" class="table table-condensed">
                                <thead>
                                <tr>
                                    <th data-valign="middle" data-align="center" data-running-no="true">#</th>
                                    <th data-valign="middle">เลขที่ลูกค้า</th>
                                    <th data-valign="middle">ชื่อลูกค้า</th>
                                    <th data-valign="middle">เลขที่ใบแจ้งค่าใช้บริการ</th>
                                    <th data-valign="middle">รหัสธนาคาร</th>
                                    <th data-valign="middle">ชื่อธนาคาร</th>
                                    <th data-valign="middle">เลขที่ใบเสร็จรับเงิน/ใบกำกับภาษี</th>
                                    <th data-valign="middle" data-align="right" data-number-format="true">จำนวนเงินที่รับชำระ</th>
                                    <th data-valign="middle">สถานะการรับชำระ</th>
                                </tr>
                                </thead>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            </div>
        </section>
        
    </body>
</html>
<script>
var view = (function($){
	var self = this, defaultErrorMessage = "An error occurred but there is no message response.";
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
	window.runningFormatter = function(val, row, ind) { return ind + 1 }
	window.numberFormatter = function(val, row, ind) { return !$.isNumeric(stripToNumber(val)) ? "0.00" : parseFloat(val, 10).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,') }
	window.stringInputFormatter = function(val, row, ind) { return '<input value="'+ $.trim(val) +'" maxLength="40" class="form-control">' }
	window.numberInputFormatter = function(val, row, ind) { return '<input value="'+ self.utils.numberFormat(parseFloat(val || "0.00", 10)) +'" maxLength="10" style="width:100px" class="form-control text-right" onkeypress="return $.isNumeric(String.fromCharCode(event.which)) || (event.which == 46 && this.value.indexOf(\'.\') == -1)" onfocus="(function(o){ o.value = o.value.replace(/[,]/g, \'\') })(this)" onblur="(function(o){ o.value = parseFloat(o.value, 10).toFixed(2).replace(/(\\d)(?=(\\d{3})+\\.)/g, \'$1,\'); })(this)">' }
	window.modifyButtonFormatter = function(val, row, ind) { return '<a class="btn btn-link modList" onclick=\'(function(elem, row, ind){ var table = $("#"+ $(elem).closest("table")[0].id); window[table[0].id +"ModifyEvent"](table, row, ind) })(this, '+ JSON.stringify(row) +', '+ ind +')\'><span class="glyphicon glyphicon-pencil"></span></a>' }
	window.removeButtonFormatter = function(val, row, ind) { return '<a class="btn btn-link delList" onclick=\'(function(elem, row, ind){ $("#"+ $(elem).closest("table")[0].id).bootstrapTable("remove", { field: "index", values: [ind] }) })(this, '+ JSON.stringify(row) +', '+ ind +')\'><span class="glyphicon glyphicon-trash"></span></a>' }
	function Breadcrumb(el) {
		var obj = this, list, index = 0;
		obj.el = el;
		obj.elem = $(el);
		obj.index = function(){ if(arguments.length == 1) { list.removeClass("active").eq(index = arguments[0]).addClass("active"); return obj } return index }
		list = obj.elem.find("li").each(function(i,o){ $(o).data("index", i) });
		index = list.filter(".active").data("index");
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
	function Panel(el) { var obj = this, dura = 500; obj.el = el; obj.elem = $(el);
		obj.hide = function(ms) { obj.elem.hide(ms || dura); return this }; obj.show = function(ms) { if (!ms || !$.isNumeric(ms)) ms = dura; if (ms >= 0) obj.elem.css("display", "none").removeClass("hide").removeClass("hidden").show(ms); else obj.hide(Math.abs(ms)); return this }
	}
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
				+ $.map(array, function(v,i){ var field = headers[i].field, value = v, colStyle = "", colWidth = 0;
					if (headers[i].runningNo) value = (obj.size() + 1);
					else if (headers[i].numberFormat) { value = !$.isNumeric(v) ? "0.00" : parseFloat(v, 10).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,'); }
					else if (headers[i].checkbox) value = replace(checkboxHtml, null, field, i, v); else if (headers[i].radio) value = replace(radioHtml, null, field, i, v);
					else if (headers[i].input) value = replace(inputHtml, 'style="width:100%;margin:-4px 0;text-align:'+ (headers[i].align || "left") +'"', field, i, v);
					if (headers[i].modifyButton) { colWidth += 45; value += '<a class="btn btn-link" onclick=\'(function(tableId, row, col){ window[tableId +"ModifyEvent"]($("#"+ tableId), row, col); })(this.parentNode.parentNode.parentNode.parentNode.parentNode.id, '+ JSON.stringify(array) +', this)\'><span class="glyphicon glyphicon-pencil"></span></a>'; } if (headers[i].removeButton) { colWidth += 45; value += '<a class="btn btn-link" onclick=\'(function(tableId, row, col){ window[tableId +"RemoveEvent"]($("#"+ tableId), row, col); col.parentNode.parentNode.parentNode.remove() })(this.parentNode.parentNode.parentNode.parentNode.parentNode.id, '+ JSON.stringify(array) +', this)\'><span class="glyphicon glyphicon-trash"></span></a>'; }
					return '<td style="'+ (!headers[i].valign ? "" : "vertical-align:"+ headers[i].valign +";") + (!colStyle ? "" : colStyle +";") + (colWidth < 1 ? "" : "width:"+ colWidth +"px;") +'"><div style="'+ (headers[i].align ? "text-align:"+ headers[i].align : "") +'">'+ value +'</div></td>' }).join("")
				+ (!showRemove ? "" : '<td style="width:40px;text-align:center"><a href="#" class="delList"><span class="glyphicon glyphicon-trash"></span></a></td>') 
			+"</tr>"); return obj }
		obj.find = function(key, cri) { return obj.elem.find("tbody tr").filter("["+ key +"="+ cri +"]") }
		obj.clear = function() { obj.elem.find("tbody tr").remove(); return obj }
		obj.remove = function(index) { this.elem.find("tbody tr").eq(index).remove(); $.each(headers,function(i,o){ if (o.runningNo) reorder(i) }); }
		obj.data = function() { var data = [];
			if (arguments.length != 1) { var rows = obj.elem.find("tbody tr");
				for (var i = 0, m = rows.length; i < m; i++) { var row = []; for (var j = 0, n = rows[i].children.length; j < n; j++) { row.push(extract(headers[j], rows[i].children[j])) } data.push(row) } return data;
			} for (var i = 0, m = (data = arguments[0]).length; i < m; i++) { obj.insert(data[i]); } return obj };
		obj.col = function(colIndex) { var rows = obj.elem.find("tbody tr"), data = []; for (var i = 0, m = rows.length; i < m; i++) { data.push(extract(headers[colIndex], rows[i].children[colIndex])) } return data }
		obj.size = function() { return obj.elem.find("tbody tr").length };
		obj.sum = function(index) { var sum = 0; this.elem.find("tbody tr").each(function(i, row){ var val = row.children[index].innerText.replace(/[,]/g, ""); sum += (isNaN(val) ? 0 : parseFloat(val, 10)) }); return sum }
		$(obj.el).on("click", ".delList", function(){ $(this).parent().parent().remove(); $.each(headers, function(i,p){ if (p.runningNo) reorder(i) }); onDel(obj.data()) });
		obj.elem.removeClass("table").removeClass("table-hover").addClass("table").addClass("table-hover");
		obj.elem.find("thead th").each(function(i,o){ var elem = $(o); headers.push({ "field": elem.data("field"), "valign": $.trim(elem.data("valign")), "align": $.trim(elem.data("align")), "modifyButton": elem.data("modifyButton") === true, "removeButton": elem.data("removeButton") === true, "runningNo": elem.data("runningNo") === true, "numberFormat": elem.data("numberFormat") === true, "checkbox": elem.data("checkbox") === true, "radio": elem.data("radio") === true, "input": elem.data("input") === true }) });
		function replace(str, style, field, index, value){ var s = str; if (style) s = s.replace("\{style\}", style); return s.replace("\{field\}", $.trim(field)).replace("\{index\}", index).replace("\{value\}", value) }
		function extract(prop, col) { if (prop.checkbox) { var elem = col.children[0].children[0]; return !elem.checked ? "" : elem.value } else if (prop.radio) { var elem = col.children[0].children[0]; return !elem.checked ? "" : elem.value } else if (prop.input) { var elem = col.children[0].children[0]; return elem.value } return col.children[0].innerText }
		if(obj.body.length < 1) { obj.elem.append("<tbody></tbody>"); obj.body = obj.elem.find("tbody") }
	}
	self.breadcrumb = new Breadcrumb("#breadcrumb");
	self.buttonCreatePaymentAndPrint = new Button("#buttonCreatePaymentAndPrint");
	self.inputAmount = new NumberInput("#inputAmount");
	self.inputDiscount = new NumberInput("#inputDiscount");
	self.inputCharge = new NumberInput("#inputCharge");
	self.inputVat = new NumberInput("#inputVat");
	self.inputTotalCharge = new NumberInput("#inputTotalCharge");
	self.inputTotalCharge2 = new NumberInput("#inputTotalCharge2");
	self.inputDeduction = new NumberInput("#inputDeduction");
	self.tableBillingList = new Table("#tableBillingList");
	self.tableReceiptList = new Table("#tableReceiptList");
	self.panelSummaryInvoiceList = new Panel("#panelSummaryInvoiceList");
	self.panelSummaryReceiptList = new Panel("#panelSummaryReceiptList");
	(function(){ $(window["setup"]) })();
	return this;
})(jQuery);

function setup() {
	var billingList = view.session("billingList"), amount = 0, discount = 0, charge = 0, vat = 0, totalCharge = 0, deduction = 0;
	$.each(billingList, function(i,o){
		var custNo = o.custNo;
		var custName = o.custName;
		$.each(o.invoiceList, function(j,p){
			amount += p.serviceAmount;
			discount += p.serviceDiscount;
			vat += p.serviceVat;
			deduction += p.serviceDeduction;
			view.tableBillingList.insert(["-", custNo, custName, p.invoiceNo, p.bankCode, p.bankName, p.bankRefNo, p.bankDate, p.bankAmnt, p.bankDeduct, ""]);
		});
	});
	view.inputAmount.val(amount);
	view.inputDiscount.val(discount);
	view.inputCharge.val(charge = amount - discount);
	view.inputVat.val(vat);
	view.inputTotalCharge.val(totalCharge = charge + vat);
	view.inputDeduction.val(deduction);
	view.inputTotalCharge2.val(totalCharge);
}
function buttonCreatePaymentAndPrintClickEvent() {
	var billingList = view.session("billingList");
	$.each(billingList, function(i,o){
		var custNo = o.custNo;
		var custName = o.custName;
		$.each(o.invoiceList, function(j,p){
			view.tableReceiptList.insert(["-", custNo, custName, p.invoiceNo, p.bankCode, p.bankName, "-", p.bankAmnt, "_"]);
		});
	});
	view.breadcrumb.index(3);
	view.panelSummaryInvoiceList.hide(0);
	view.panelSummaryReceiptList.show(400);
}
</script>