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
<script src="resources/js/mustache.min.js" type="text/javascript"></script>
<script src="resources/custom.js" type="text/javascript"></script>
</head>
<body>
	<header class="header_page"></header>
	<section class="container-fluid menu">
	    <!-- <button class="menu-toggle btn btn-sm btn-default" style="margin-bottom: 5px"><span class="glyphicon glyphicon-step-forward"></span></button> -->
	    <%-- <%@include  file="menu.jsp" %> --%>
	    <div class="row">
	        	<ol id="breadcrumb" class="breadcrumb">
                    <li><i>ยกเลิกรับชำระค่าบริการ</i></li>
                    <li>ค้นหาข้อมูลการรับชำระ</li>
                    <li class="active">ระบุเหตุผลการยกเลิกรับชำระ</li>
                    <li>สรุปการยกเลิกรับชำระ</li>
                    <li>ผลการยกเลิกรับชำระ</li>
                </ol>
        </div>
        <div id="message"></div>
        <div id="panelReasonType" class="row">
	        <div class="col-md-12 tab-modefile">
	            <ul class="nav nav-tabs" role="tablist">
	                <li role="presentation" class="active">
	                	<a href="#tab_cus" aria-controls="tab_cus" role="tab" data-toggle="tab"><span class="glyphicon glyphicon-folder-open"></span>  เหตุผลยกเลิกรับชำระ</a>
	                </li>
	            </ul>
	            <div class="tab-content">
	                <div role="tabpanel" class="tab-pane active" id="tab_cus">
	                    <div class="panel panel-default panal-radius">
	                        <div class="panel-body">
	                            <div class="row">
		                          <div class="col-md-12">
		                              <div class="form-horizontal">
		                                  <div class="form-group">
		                                      <label class="control-label col-sm-2">โปรดระบุตัวเลือก  :</label>
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
	    <ul id="panelNavigate2" class="list-inline pull-right">
	        <li><a id="addCancelReceiptList" href="cancel-service-charge_1.jsp" class="btn btn-link"><span class="glyphicon glyphicon-arrow-left"></span> กลับไปเพิ่มรายการ</a></li>
	        <li><a id="buttonProceedReceiptList" class="btn btn-link"><span class="glyphicon glyphicon-remove-sign"></span> เลือกรายการการยกเลิก</a></li>
	    </ul>
	    <ul id="panelNavigate3" class="hide list-inline pull-right">
	        <li><a href="#confirmCancel" data-toggle="modal" class="btn btn-link"><span class="glyphicon glyphicon-th-list"></span> ดำเนินการยกเลิก</a></li>
	    </ul>
	    <ul id="panelNavigate4" class="hide list-inline pull-right">
	        <li><a id="buttonSearchReceiptList" href="cancel-service-charge_1.jsp?new" class="btn btn-link"><span class="glyphicon glyphicon-arrow-left"></span> กลับไปยกเลิกรับชำระค่าบริการ</a></li>
	    </ul>
		<div class="row">
			<div class="col-md-12 tab-modefile">
					<div class="panel-heading">
	                    <h3 class="panel-title"><span class="glyphicon glyphicon-shopping-cart"></span> รายการรับชำระ</h3>
	                </div>
					<table id="receiptList"></table>
			</div>
		</div>
		<input type="hidden" id="userAuthen"/>
	</section>

	<div class="modal fade" role="dialog" id="confirmCancel">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title"><span class="glyphicon glyphicon-warning-sign"></span> ข้อความแจ้งเตือน</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-md-12">
							<div class="form-horizontal">
								<div class="form-group">
									<label class="col-sm-12 control-label " style="font-size: 25px; text-align: center"><span class="glyphicon glyphicon-question-sign"></span> ยืนยันยกเลิกรับชำระค่าบริการ</label>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<a id="buttonCancelReceiptList" class="btn btn-success"><span class="glyphicon glyphicon-ok-circle"></span> ตกลง</a>
					<a class="btn btn-danger" data-dismiss="modal"><span class="glyphicon glyphicon-remove-circle"></span> ยกเลิก</a>
				</div>
			</div>
		</div>
	</div>

<script id="template" type="x-tmpl-mustache">
	<table class="table table-striped table-bordered">
		<thead>
			<tr>
			<th class="text-left">รายการ</th>
			<th class="text-right">จำนวน</th>
			<th class="text-right">ภาษีมูลค่าเพิ่ม</th>
			<th class="text-right">ส่วนลด</th>
			<th class="text-right">จำนวนเงิน</th>
			</tr>
		</thead>
		<tbody>
		{{#invoices}}
			<tr><td>Invoice No.: {{no}}</td><td class="text-right">{{amount}}</td><td class="text-right">{{vat}}</td><td class="text-right">{{discount}}</td><td class="text-right">{{balanceDue}}</td></tr>
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
	window.dateTimeFormatter = function(val, row, ind){ if (!val) return ""; if ((/(\d{4}).(\d{2}).(\d{2})T(\d{2}).(\d{2}).(\d{2}).*/g).test(val)) return val.replace(/(\d{4}).(\d{2}).(\d{2})T(\d{2}).(\d{2}).(\d{2}).*/g, "$3/$2/$1 $4:$5:$6"); return val }
	window.stringInputFormatter = function(val, row, ind) { return '<input value="'+ $.trim(val) +'" maxLength="40" class="form-control">' }
	window.numberInputFormatter = function(val, row, ind) { return '<input value="'+ self.utils.numberFormat(parseFloat(val || "0.00", 10)) +'" maxLength="10" style="width:100px" class="form-control text-right" onkeypress="return $.isNumeric(String.fromCharCode(event.which)) || (event.which == 46 && this.value.indexOf(\'.\') == -1)" onfocus="(function(o){ o.value = o.value.replace(/[,]/g, \'\') })(this)" onblur="(function(o){ o.value = parseFloat(o.value, 10).toFixed(2).replace(/(\\d)(?=(\\d{3})+\\.)/g, \'$1,\'); })(this)">' }
	window.selectButtonFormatter = function(val, row, ind) { return '<a class="btn btn-primary btn-xs selList" onclick=\'(function(elem, row, ind){ var table = $("#"+ $(elem).closest("table")[0].id); window[table[0].id +"SelectEvent"](table, row, ind) })(this, '+ JSON.stringify(row) +', '+ ind +')\'><span class="glyphicon glyphicon-check"></span> Select</a>' }
	window.modifyButtonFormatter = function(val, row, ind) { return '<a class="btn btn-primary btn-xs modList" onclick=\'(function(elem, row, ind){ var table = $("#"+ $(elem).closest("table")[0].id); window[table[0].id +"ModifyEvent"](table, row, ind) })(this, '+ JSON.stringify(row) +', '+ ind +')\'><span class="glyphicon glyphicon-pencil"></span> Modify</a>' }
	window.removeButtonFormatter = function(val, row, ind) { return '<a class="btn btn-primary btn-xs delList" onclick=\'(function(elem, row, ind){ $("#"+ $(elem).closest("table")[0].id).bootstrapTable("remove", { field: "index", values: [ind] }) })(this, '+ JSON.stringify(row) +', '+ ind +')\'><span class="glyphicon glyphicon-trash"></span> Remove</a>' }
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
	function Breadcrumb(el) { var obj = this; obj.el = el; obj.elem = $(el); var list = obj.elem.find("li").each(function(i,o){ $(o).data("index", i) }), index = list.filter(".active").data("index");
		obj.index = function(){ if(arguments.length == 1) { list.removeClass("active").eq(index = arguments[0]).addClass("active"); return obj } return index }
		obj.next = function(){ obj.index(++index); return this }; obj.prev = function(){ obj.index(--index); return this }
	}
	function BootstrapTable(el, options) { var obj = this, checkEvt = function(e){ console.log(e) }, uncheckEvt = checkEvt; obj.el = el; obj.elem = $(el).bootstrapTable(options = $.extend(options, { uniqueId: "id", onCheck: window[el.substring(1) +"CheckEvent"], onUncheck: window[el.substring(1) +"UncheckEvent"], onCheckAll: window[el.substring(1) +"CheckAllEvent"], onUncheckAll: window[el.substring(1) +"UncheckAllEvent"] }));
		obj.clear = function() { obj.elem.bootstrapTable("removeAll"); return obj }; obj.remove = function(index){ obj.elem.bootstrapTable("remove", { field: "index", values: [index] }); return this }; obj.resetView = function(ms){ setTimeout(function(){ obj.elem.bootstrapTable("resetView") }, ms || 200); return this }; obj.isEmpty = function(){ return obj.data().length == 0 }
		obj.showLoad = function() { this.elem.bootstrapTable("showLoading"); return this }; obj.hideLoad = function() { this.elem.bootstrapTable("hideLoading"); return this };
		obj.update = function(rec){ var id = rec.id, index = rec.index, row; if ((row = obj.getId(id)) != null) { obj.elem.bootstrapTable("updateRow", { "index": row.index, "row": $.extend(row, rec) }) } else if ((row = obj.data()[index]) != null) { obj.elem.bootstrapTable("updateRow", { "index": index, "row": $.extend(row, rec) }) } else obj.insert(rec); return this }; obj.insert = function(row) { var data = obj.elem.bootstrapTable("getData"); obj.elem.bootstrapTable("insertRow", { "index": data.length, "row": $.extend(row, { "index": data.length }) }); return this }
		obj.find = function(field, value) { var data = obj.elem.bootstrapTable("getData"); return $.map(data, function(o,i){ return o[field] === value ? o : null }) }
		obj.map = function(func) { var resultMap = {}; return $.map(obj.data(), $.type(func) === "string" ? function(row, ind){ return resultMap[func] = row } : func)}
		obj.data = function() { if (arguments.length == 1) { this.elem.bootstrapTable("load", arguments[0]); return this } return $.map(this.elem.bootstrapTable("getData"), function(row){ return row }) };
		obj.selected = function() { var data = []; obj.elem.find("tbody tr").find("input[type=checkbox]:checked, input[type=radio]:checked").each(function(i,o){ var row = o.parentNode.parentNode, record = []; for (var j = 0, n = row.childNodes.length; j < n; j++) { var col = row.childNodes[j], val = ""; if (col.childNodes.length != 1) val = ""; else if (col.childNodes[0].nodeType == 3) val = $.trim(col.childNodes[0].textContent); else if (col.childNodes[0].nodeName == "INPUT") val = $.trim(col.childNodes[0].value); else if (col.childNodes[0].nodeName == "DIV") val = $.trim(col.childNodes[0].childNodes[0].value); record.push(val) } data.push(record) }); return data };
		obj.getId = function(id) { var o = obj.elem.bootstrapTable("getRowByUniqueId", id); if ($.type(o) == "array") return null; return o[options.uniqueId] == id ? o : null }; obj.delId = function(id) { obj.elem.bootstrapTable("removeByUniqueId", id); return this }; // options: { uniqueId: "fieldName" }
		obj.sum = function(isAll, colName) { var sum = 0; $.each(this.elem.bootstrapTable(isAll ? "getData" : "getSelections"), function(i,o){ sum += (o[colName] || 0) }); return sum };
		obj.sumInput = function(index) { var sum = 0; obj.elem.find("tbody tr").each(function(i,o){ var val = o.children[index].children[0].value.replace(/[,]/g, ""); sum += (!$.isNumeric(val) ? 0 : parseFloat(val, 10)) }); return sum }
		obj.http = function(url, urlParams, successFunc) { if (successFunc) obj.elem.bootstrapTable("refreshOptions", { onLoadSuccess: successFunc }); obj.elem.bootstrapTable("refresh", { "url": url, "query": urlParams }); return this }
		obj.expand = function() { obj.elem.find(".detail-icon i.icon-plus").click(); return this }; obj.collapse = function() { obj.elem.find(".detail-icon i.icon-minus").click(); return this };
		obj.checkAll = function(){ var checked = arguments.length == 0 || arguments[0] == true || ($.type(arguments[0]) == "boolean" ? arguments[0] : arguments[0] == "true"), data = obj.elem.bootstrapTable("getData"); $.each(obj.elem.bootstrapTable("getOptions").columns[0], function(i, col){ if (col.checkbox) $.each(data, function(j, row){ row[col.field] = checked }) }); return this }; obj.uncheckAll = function(){ obj.checkAll(false); return this }
		obj.elem.on("check.bs.table uncheck.bs.table check-all.bs.table uncheck-all.bs.table", window[el.substring(1) +"CheckBoxEvent"])
	}
	function Button(el) { var obj = this, badge; obj.el = el; obj.elem = $(el);
		obj.hide = function() { this.elem.addClass("hide"); return this }; obj.show = function() { this.elem.removeClass("hide"); return this };
		obj.hideLoad = function(){ obj.elem.button("reset"); return this }; obj.showLoad = function(){ obj.elem.button("loading"); return this };
		obj.disable = function(flag) { if (flag == null || flag) this.elem.addClass("disabled").attr("disabled", true); else this.elem.removeClass("disabled").attr("disabled", false); return this }; obj.enable = function() { obj.disable(false); return this };
		obj.badge = function(val) { if (badge) badge.text(val) }; if ((badge = this.elem.next()).length == 0) badge = null;
		obj.elem.click(window[el.substring(1) +"ClickEvent"]);
	}
	function DropDown(el, url) { var obj = this, data = [{ key: "", value: "", text: "Please Select" }]; obj.el = el; obj.elem = $(el);
		obj.data = function(array) { if ($.type(array) == "array") { setup(data = array); return this } return data; }
		obj.init = function(url) { if (url) $.get(url, function(res) { setup(res.data) }); else setup(data); };
		obj.index = function() { if (arguments.length == 1) { var maxLen = data.length, index = arguments[0]; obj.elem.find("option").each(function(i, opt){ opt.removeAttribute("selected") }).eq(index < 0 ? maxLen + index : index).attr("selected", true); return this } return $.inArray("selected", obj.elem.find("option").map(function(i, opt){ return opt.selected ? "selected" : "no-selected" })) }
		obj.selected = function(){ return data[obj.index()]; }; obj.val = function() { return obj.elem.val(); }; obj.key = function() { return obj.elem.find("option:selected").data("key") }
		function setup(array) { obj.elem.find("*").remove(); $.each(array,function(i,o){ obj.elem.append('<option data-index="'+ i +'" data-key="'+ o.key +'" value="'+ o.value +'">'+ (o.text || o.value) +'</option>') }); }
		data = obj.elem.change(window[el.substring(1) +"ChangeEvent"]).find("option").map(function(i, opt){ return { key: opt.getAttribute("data-key"), value: opt.value, text: opt.textContent } });
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
	function Panel(el) { var obj = this, dura = 500; obj.el = el; obj.elem = $(el);
		obj.hide = function(ms) { if (isHidden()) return this; obj.elem.hide(ms || dura); return this }; obj.show = function(ms) { if (!isHidden()) return this; if (!ms || !$.isNumeric(ms)) ms = dura; if (ms >= 0) obj.elem.css("display", "none").removeClass("hide").removeClass("hidden").show(ms); else obj.hide(Math.abs(ms)); return this }
		obj.slideDown = function(ms){ if (isHidden()) { obj.elem.css("display", "none").removeClass("hide").removeClass("hidden").slideDown(ms || dura) } return this }; obj.slideUp = function(ms){ if (!isHidden()) { obj.elem.slideUp(ms || dura) } return this }
		function isHidden() { return obj.elem.css("display") === "none" || obj.elem.hasClass("hide") || obj.elem.hasClass("hidden") }
	}
	window.LoadingPanel = function(id) { var obj = this, loadCnt = 0, setupFunc, loadFunc; obj.elem = null;
		obj.finish = function(html){ checkElemReady(); obj.elem.css("font", "").html(html); }
		obj.toString = function(){ var elem; setupFunc = setTimeout(function(){ loadFunc = setInterval(function(){ (elem || (elem = document.getElementById(id))).innerHTML = "Loading"+ Array(++loadCnt).join("..") }, 250) }, 1000); return "<div id='"+ id +"' style='font:BOLD 16pt Arial'></div>" }
		function checkElemReady(){ if ((obj.elem = $("#"+ id).css("font", "")).length != 1) { alert("The element hasn't insert into HTML DOM."); throw "The element hasn't insert into HTML DOM."; } clearTimeout(setupFunc); clearInterval(loadFunc) }
	}
	this.breadcrumb = new Breadcrumb("#breadcrumb");
	this.message = new Message("#message");
	this.dialogAuthentication = new AuthenticationDialog(null, null, "../checkAuthorize.json");
	this.dialogConfirmCancel = $("#confirmCancel")
	this.panelNavigate2 = new Panel("#panelNavigate2");
	this.panelNavigate3 = new Panel("#panelNavigate3");
	this.panelNavigate4 = new Panel("#panelNavigate4");
	this.panelReasonType = new Panel("#panelReasonType");
	this.buttonProceedReceiptList = new Button("#buttonProceedReceiptList");
	this.buttonSearchReceiptList = new Button("#buttonSearchReceiptList");
	this.buttonCancelReceiptList = new Button("#buttonCancelReceiptList");
	this.inputCancelReasonType = new DropDown("#cancelReasonType").data([{ key: "", value: "- กรุณาเลือกระบุเหตุผลการยกเลิก -"}, { key: "wrongName", value: "ชื่อ-ที่อยู่ ไม่ถูกต้อง"},{ key: "wrongInvoice", value: "ผิดใบแจ้งค่าใช้บริการ" }]);
	this.tableReceiptList = new BootstrapTable("#receiptList", { detailView: true, detailFormatter: invoiceDetailsFormatter, columns: [
		 { title: "#", formatter: runningFormatter, align: "center" }
		,{ title: "เลขที่ใบเสร็จรับเงิน", field: "no", align: "center" }
		,{ title: "วันที่รับชำระ", field: "updateDttm", align: "center", formatter: dateFormatter }
		,{ title: "เลขที่ลูกค้า", field: "accountNo", align: "center" }
		,{ title: "ชื่อลูกค้า", field: "accountName" }
		,{ title: "วิธีการชำระเงิน", field: "paymentCase", align: "center" }
		,{ title: "ยอดเงิน", field: "totalCharge", align: "center", formatter: numberFormatter }
		,{ title: "สถานที่รับชำระ", field: "branchName", align: "center" }
		,{ title: "ผู้รับชำระ", field: "updateUser", align: "center" }
// 		,{ title: "", field: "status", align: "center" }
	] });
	(function(){ $(window["setup"]) })();
	return this;
})(jQuery);
// <tr>
// <th data-field="runningFormatter" data-align="center">#</th>
// <th data-field="no" data-align="center">เลขที่ใบเสร็จรับเงิน</th>
// <th data-field="date" data-align="center">วันที่รับชำระ</th>
// <th data-field="custNo" data-align="center">เลขที่ลูกค้า</th>
// <th data-field="custName" data-align="center">ชื่อลูกค้า</th>
// <th data-field="payMethod" data-align="center">วิธีการชำระเงิน</th>
// <th data-field="total" data-align="center">ยอดเงิน</th>
// <th data-field="branch" data-align="center">สถานที่รับชำระ</th>
// <th data-field="username" data-align="center">ผู้รับชำระ</th>
// <th data-field="status" data-align="center">เหตุผลการยกเลิก</th>
// </tr>
//                            <tr>
//                             <th data-field="runningFormatter" data-align="center">#</th>
//                             <th data-field="no" data-align="center">เลขที่ใบเสร็จรับเงิน</th>
//                             <th data-field="date" data-align="center">วันที่รับชำระ</th>
//                             <th data-field="custNo" data-align="center">เลขที่ลูกค้า</th>
//                             <th data-field="custName" data-align="center">ชื่อลูกค้า</th>
//                             <th data-field="payMethod" data-align="center">วิธีการชำระเงิน</th>
//                             <th data-field="total" data-align="center">ยอดเงิน</th>
//                             <th data-field="branch" data-align="center">สถานที่รับชำระ</th>
// 		                    <th data-field="username" data-align="center">ผู้รับชำระ</th>
//                             <th data-field="status" data-align="center">สถานะ</th>
//                            </tr>

function buttonProceedReceiptListClickEvent() {
	view.dialogAuthentication.show().done(function(){
		view.panelReasonType.hide(1)
		view.panelNavigate2.hide(1);
		view.panelNavigate3.slideDown();
		view.breadcrumb.next();
	});
}
function buttonCancelReceiptListClickEvent() {
	view.buttonCancelReceiptList.showLoad();
	view.message.clear();
	var params = {"userAuthen":$("#userAuthen").val()}
	$.each(view.tableReceiptList.data(), function(i,o){
		params["receipts["+ i +"].id"] = o.id;
		params["receipts["+ i +"].no"] = o.no;
	});
	post("../cancelPaymentProduct.json", params, function(res){
		view.dialogConfirmCancel.modal("hide");
		if (!res || res.statusCode != "0") { view.dialog.mainMessageDialog.error(res.errorList).warn(res.warningList).success(res.successList).show(); return; }
		view.panelNavigate3.hide(1);
		view.panelNavigate4.slideDown();
		view.breadcrumb.next();
		view.message.clear().success(["The receipt list have cancelled successfully."]).show();
		view.session("cancelList", []);
		view.session("userAuthen", "");
	}, view.message, function(){ view.buttonCancelReceiptList.hideLoad() });
}
function invoiceDetailsFormatter(ind, row) {
	return Mustache.render($('#template').html(), { "invoices" : $.map(row.invoices, function(inv, i){
		return $.extend(inv, {
			 "no": inv.no
			,"amount": view.utils.numberFormat(inv.amount)
			,"vat": (inv.vat == null ? '-' : view.utils.numberFormat(inv.vat))
			,"discount": view.utils.numberFormat(inv.discount)
			,"balanceDue": view.utils.numberFormat(inv.balanceDue)
		})
	}) })
}
function setup() {
	var cancelList = view.session("cancelList");
	view.tableReceiptList.data(cancelList);
	var userAuthen = view.session("userAuthen");
	$("#userAuthen").val(userAuthen);
}
</script>