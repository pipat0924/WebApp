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
<script src="resources/js/bootstrap-datepicker.min.js"></script>
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
                    <li><i>อนุมัติการร้องขอคืนเงิน</i></li>
                    <li>ค้นหาข้อมูลคำร้องขอคืนเงิน</li>
                    <li>รายละเอียดขอคืนเงิน</li>
                    <li class="active">สรุปการอนุมัติขอคืนเงิน</li>
                    <li>ผลการอนุมัติขอคืนเงิน</li>
                </ol>
	        </div>
	    </div>
	    <!--------------------------------------->
	    <ul id="navigatePanel" class="list-inline pull-right list-menu-set">
	        <li><a href="#confirmAppRevert" data-toggle="modal"><span class="glyphicon glyphicon-th-list"></span> ดำเนินการอนุมัติขอคืนเงิน</a></li>
	        <li style="padding-left:22px;"></li>
	    </ul>
	    <!--------------------------------------->
        
		<div class="row">
			<div class="col-md-12 tab-modefile">
					<div class="panel-heading">
	                    <h3 class="panel-title"><span class="glyphicon glyphicon-shopping-cart"></span> รายการขออนุมัติคืนเงิน</h3>
	                </div>
					<table id="tableReqRevertList3"
                          data-row-style="rowStyle"
                          data-toggle="table"
                          data-detail-view="true"
	                      data-detail-formatter="detailFormatter"
                          data-classes="table table-hover table-striped"
                          >
                       <thead>
                           <tr>
                            <th data-field="invoiceno" data-align="center">เลขที่ใบแจ้งค่าบริการ</th>
                            <th data-field="receiptno" data-align="center">เลขที่ใบเสร็จรับเงิน</th>
                            <th data-field="accountno" data-align="center">เลขที่ลูกค้า</th>
                            <th data-field="accountname" data-align="center">ชื่อลูกค้า</th>
                            <th data-field="paymentcase" data-align="center">วิธีการชำระเงิน</th>
                            <th data-field="received" data-align="center">จำนวนเงิน</th>
                            <th data-field="revertAmt" data-align="right" data-formatter="numberFormatter">เงินขอคืน</th>
                            <th data-field="apprRevertAmt" data-align="right" data-formatter="numberFormatter">เงินอนุมัติ</th>
                            <th data-field="branchname" data-align="center">สถานที่รับชำระ</th>
		                    <th data-field="reqUser" data-align="center">ผู้ขอคืนเงิน</th>
                            <th data-field="docStatus" data-align="center">สถานะ</th>
                           </tr>
                       </thead>
                       <%--<tbody>
                           <tr>
                               <td>255900001</td>
                               <td>EP0171701F150714000001</td>
                               <td>200006599</td>
                               <td>นาย วสันตืชาย วงค์คำเดือน</td>
                               <td>เงินสด</td>
                               <td>1,070.00</td>
                               <td>1,000.00</td>
                               <td>1,000.00</td>
                               <td>ศบล.แจ้งวัฒนะ</td>
	                           <td>EPIS2016</td>
                               <td>รอยีนยันอนุมัติ</td>
                        	</tr>
                       </tbody>--%>
                   </table>
			</div>
	</section>

	<div class="modal fade" role="dialog" id="confirmAppRevert">
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
							<div class="col-md-8">
								<div class="form-horizontal">
									<div class="form-group">
										<label class="col-sm-12 control-label "
											style="font-size: 25px; text-align: center"><span
											class="glyphicon glyphicon-question-sign"></span>
											ยืนยันอนุมัติขอคืนเงิน</label>
											
									</div>
								</div>
							</div>
							<div class="col-md-2"></div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<a id="btnApprRevertPayment" class="btn btn-success"><span class="glyphicon glyphicon-ok-circle"></span> ตกลง</a>
					<a class="btn btn-danger" data-dismiss="modal"><span class="glyphicon glyphicon-remove-circle"></span> ยกเลิก</a>
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
	function Button(el) { var obj = this, badge; obj.el = el; obj.elem = $(el);
		obj.hide = function() { this.elem.addClass("hide"); return this }; obj.show = function() { this.elem.removeClass("hide"); return this };
		obj.hideLoad = function(){ obj.elem.button("reset"); return this }; obj.showLoad = function(){ obj.elem.button("loading"); return this };
		obj.disable = function(flag) { if (flag == null || flag) this.elem.addClass("disabled").attr("disabled", true); else this.elem.removeClass("disabled").attr("disabled", false); return this }; obj.enable = function() { obj.disable(false); return this };
		obj.badge = function(val) { if (badge) badge.text(val) }; if ((badge = this.elem.next()).length == 0) badge = null;
		obj.elem.click(window[el.substring(1) +"ClickEvent"]);
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
	self.tableReqRevertList3 = new BootstrapTable("#tableReqRevertList3");
	self.btnApprRevertPayment = new Button("#btnApprRevertPayment");
	return this;
})(jQuery);

view.input.cancelReasonType.data([{ key: "", value: "- กรุณาเลือกระบุเหตุผลการยกเลิก -"}, { key: "wrongName", value: "ชื่อ-ที่อยู่ ไม่ถูกต้อง"},{ key: "wrongInvoice", value: "จำนวนเงินรับชำระผิด" }]);

$(function(){
	//var cancelList = view.session("cancelList");
	//view.table.cancelList.data(cancelList);
	var revertReqList = view.session("revertReqList2");
	console.log(revertReqList);
	$("#accountNo").val(revertReqList[0].accountno);
	$("#accountName").val(revertReqList[0].accountname);
	/*if(revertReqList[0].accounttype=='ORGANIZATION'){
		$("#accountType").val('นิติบุคคล');
	}else if(revertReqList[0].accounttype == 'INDIVIDUAL'){
		$("#accountType").val('บุคคลธรรมดา');
	}else{
		$("#accountType").val(revertReqList[0].accounttype);
	}*/

	$("#tableReqRevertList3").bootstrapTable("load", revertReqList);
	//$("#reqRevertReason").val(revertReqList[0].reqReason);
	//$("#docDate").datepicker({format: 'dd/mm/yyyy'})
	//$("#docDate").datepicker("setDate", new Date());
});

function detailFormatterOld(val, row, ind) {
	return    	'<table class="table table-striped table-bordered">'+
			    	'<thead>'+
			    		'<tr>'+
			    		'<th class="text-center">รหัสผลิตภัณฑ์</th>'+
			    		'<th class="text-center">ชื่อผลิตภัณฑ์</th>'+
			    		'<th class="text-center">ผลิตภัณฑ์ย่อย</th>'+
			    		'<th class="text-center">ชื่อผลิตภัณฑ์ย่อย</th>'+
			    		'<th class="text-center">ประเภทรายได้</th>'+
			    		'<th class="text-center">จำนวนเงิน</th>'+
			    		'<th class="text-center">เงินขอคืน</th>'+
			    		'</tr>'+
			    	'</thead>'+
			    	'<tbody>'+
			    		'<tr><td class="text-center">102010403</td><td class="text-center">INMARSAT1</td><td class="text-center">3</td><td class="text-center">INMARSAT-1</td><td class="text-center">02</td><td class="text-center">800</td><td class="text-center">800.00</tr>'+
			    		'<tr><td class="text-center">102010500</td><td class="text-center">INMARSAT2</td><td class="text-center">4</td><td class="text-center">INMARSAT-2</td><td class="text-center">02</td><td class="text-center">200</td><td class="text-center">200.00</tr>'+
			    	'</tbody>'+
		    	'</table>';	
}
function detailFormatter(val, row, ind) {
    var htmlTableBody = '';
    $.each(row.productList, function(i, data){
        htmlTableBody = htmlTableBody+'<tr><td class="text-center">'+data.productCode+'</td><td class="text-center"></td><td class="text-center">'+data.subProductCode+'</td><td class="text-center"></td><td class="text-center">'+data.revTypeCode+'</td><td class="text-center">'+data.amount+'</td><td class="text-right">'+numberFormatter(data.revertAmt)+'</td><td class="text-right">'+numberFormatter(data.apprRevertAmount)+'</td></tr>';
    });
    return    	'<table class="table table-striped table-bordered">'+
            '<thead>'+
            '<tr>'+
            '<th class="text-center">รหัสผลิตภัณฑ์</th>'+
            '<th class="text-center">ชื่อผลิตภัณฑ์</th>'+
            '<th class="text-center">ผลิตภัณฑ์ย่อย</th>'+
            '<th class="text-center">ชื่อผลิตภัณฑ์ย่อย</th>'+
            '<th class="text-center">ประเภทรายได้</th>'+
            '<th class="text-center">จำนวนเงิน</th>'+
            '<th class="text-center">เงินที่ขอคืน</th>'+
			'<th class="text-center">เงินที่อนุมัติ</th>'+
            htmlTableBody+
            '</thead>'+
            '<tbody>'+


            /*'<tr><td class="text-center">102010403</td><td class="text-center">INMARSAT1</td><td class="text-center">3</td><td class="text-center">INMARSAT-1</td><td class="text-center">02</td><td class="text-center">800</td></tr>'+
             '<tr><td class="text-center">102010500</td><td class="text-center">INMARSAT2</td><td class="text-center">4</td><td class="text-center">INMARSAT-2</td><td class="text-center">02</td><td class="text-center">200</td></tr>'+*/
            '</tbody>'+
            '</table>';
}
function btnApprRevertPaymentClickEvent(){
	//alert('this is the last step');
	var data0 = $('#tableReqRevertList3').bootstrapTable('getData');
	var dataList = [];
	var dataSend = {};
    var apprFlg = view.session("apprFlg");
	$.each(data0, function(i, data){
		var obj = {
			invoiceno: data.invoiceno,
			receiptno: data.receiptno,
			accountno: data.accountno,
			accountname: data.accountname,
			paymentcase: data.paymentcase,
			totalcharge: data.totalcharge,
			revertAmt: data.revertAmt,
			branchname: data.branchname,
			reqReason: data.reqReason,
			productList: data.productList,
			received: data.received,
			billcycle: data.billcycle,

			issuedate: data.issuedate,
			duedate: data.duedate,
			charge: data.charge,
			discount: data.discount,
			vat: data.vat,
			balancedue: data.balancedue,
			deduct: data.deduct,
			issuedt: data.issuedt,

			apprRevertAmt: data.apprRevertAmt,
			revertReqNo: data.revertReqNo,
			docStatus: apprFlg,
			revertInvDtlId: data.revertInvDtlId,
            apprReason: view.session("apprReason")

		}
		dataList.push(obj);
	})
	console.log(dataList);


	$.ajax({
		type: "POST",
		url: "../save-revert-payment_appr.json",
		data: JSON.stringify(dataList),
		dataType: "json",
		contentType: "application/json; charset=utf-8",
		success: function(res){
			view.session("revertReqList3", res);
            view.session("apprFlg", apprFlg);
			location.href = "approve-revert-payment_4.jsp";
		}
	})
}

</script>
</html>
