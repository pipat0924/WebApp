<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <link href="resources/bootstrap-table/src/bootstrap-table.css" rel="stylesheet" type="text/css" />
    <link href="resources/css/datepicker.min.css" rel="stylesheet" type="text/css"/>
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
        <div class="col-md-12">
            <ol class="breadcrumb">
                <li><i>ตัวแทนรับชำระ</i></li>
                <li><i>ข้อมูลการชำระเงิน</i></li>
                <li class="active">ดำเนินการรับชำระ</li>
                <li>ผลการรับชำระ</li>
            </ol>
        </div>
    </div>
    <div id="message"></div>
    <div id="panelNavigation" class="row">
        <ul class="list-inline pull-right list-menu-set">
            <li><a id="buttonCreatePaymentAndPrint" class="btn btn-link"><span class="glyphicon glyphicon-print"></span> บันทึกและพิมพ์</a></li>
            <li><a id="buttonCancelPayment" class="btn btn-link"><span class="glyphicon glyphicon-remove-circle"></span> ยกเลิกรายการ</a></li>
        </ul>
    </div>
    <div id="panelPaymentDetails" class="row">
        <div class="col-md-12">
            <label class="label-panal label-warning">วิธีการรับชำระ</label>
            <div id="setPayType" class="btn-group">
                <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                    <span class="selection">เงินสด</span> <span class="caret"></span>
                </button>
                <ul class="dropdown-menu" id='testmenu2'>
                    <li><a href="#">เงินสด</a></li>
                            <li><a href="#">เช็ค</a></li>
                            <li><a href="#">บัตรเครดิต</a></li>
                            <!--  
                            <li><a href="#">ธนาณัติ</a></li>
                            <li><a href="#">ตั๋วแลกเงิน</a></li>
                            <li><a href="#">คูปอง</a></li>
                            <li><a href="#">เงินโอนในประเทศ</a></li>
                            <li><a href="#">offset</a></li>
                            <li><a href="#">เงินโอนต่างประเทศ</a></li>
                            <li><a href="#">อื่นๆ</a></li>
                            <li><a href="#">GFMIS</a></li>
                             -->
                </ul>
            </div>
            <div class="panel panel-default panal-radius">
                <div class="panel-body" style="padding-right: 0; padding-left: 0;">
                    <div class="col-md-12">
                        <div role="tabpanel" class="tab-pane hide" id="payType1">
                            <div class="form-horizontal">
                                <div class="form-group">
                                    <label class="control-label col-sm-8" >จำนวนเงิน :</label>
                                    <div class="col-sm-4">
                                        <input id="payCashAmount" class="form-control text-right">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div role="tabpanel" class="tab-pane hide" id="payType2">
								<div class="form-horizontal">
									<div class="form-group">
										<label class="control-label col-sm-2">รหัสธนาคาร :</label>
										<div class="col-sm-4">
											<select id="payChqBankCode" class="form-control"></select>
										</div>
										<label class="control-label col-sm-2">เลขที่เช็ค :</label>
										<!-- ICE FIXED CODE no.152 length chq = 7 -->
										<div class="col-sm-4">
											<input class="form-control" id="payChqNo" maxlength="7">
										</div>
										<!-- end ICE FIXED CODE no.152 length chq = 7 -->
									</div>
									<div class="form-group">
										<label class="control-label col-sm-2">ชื่อธนาคาร :</label>
										<div class="col-sm-4">
											<select id="payChqBankName" class="form-control"></select>
										</div>
										<label class="control-label col-sm-2">วันที่หน้าเช็ค
											:</label>
										<div class="col-sm-4">
											<div class="input-group input-append date"
												data-provide="datepicker" data-date-format="dd/mm/yyyy">
												<input class="form-control" id="payChqDate"
													placeholder="dd/MM/yyyy" maxlength="10"> <span
													class="input-group-addon add-on"><span
													class="glyphicon glyphicon-calendar"></span></span>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-sm-2">สาขา :</label>
										<div class="col-sm-4">
											<input class="form-control" id="payChqBranch">
										</div>
										<label class="control-label col-sm-2">จำนวนเงิน :</label>
										<div class="col-sm-4">
											<input class="form-control text-right" id="payChqAmount">
										</div>
									</div>
								</div>
								<a class="btn btn-warning pull-right" id="payChqSubmit"><span
									class="glyphicon glyphicon-plus-sign"></span> เพิ่มรายการเช็ค</a>
								<table id="payChqList" class="table">
									<thead>
										<tr>
											<th data-align="center" data-running-no="true">#</th>
											<th>รหัสธนาคาร</th>
											<th>ชื่อธนาคาร</th>
											<th>สาขา</th>
											<th>เลขที่เช็ค</th>
											<th>วันที่หน้าเช็ค</th>
											<th data-align="right" data-number-format="true">จำนวนเงิน</th>
											<th></th>
										</tr>
									</thead>
								</table>
							</div>
                        <div role="tabpanel" class="tab-pane hide" id="payType3">
                            <div class="form-horizontal">
                                <div class="form-group">
                                    <label class="control-label col-sm-3" >ประเภทบัตรเครดิต :</label>
                                    <div class="col-sm-3"><select class="form-control" id="payCCType"></select></div>
                                    <label class="control-label col-sm-3" >เลขที่บัตร :</label>
                                    <div class="col-sm-3"><input class="form-control" id="payCCNo" maxlength="16"></div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-3" >ธนาคารเจ้าของเครื่อง (EDC) :</label>
                                    <div class="col-sm-3"><select class="form-control" id="payCCBankName"></select></div>
                                    <label class="control-label col-sm-3" >จำนวนเงิน :</label>
                                    <div class="col-sm-3"><input class="form-control text-right" id="payCCAmount"></div>
                                </div>
                            </div>
                            <a class="btn btn-warning pull-right" id="payCCSubmit"><span class="glyphicon glyphicon-plus-sign"></span> เพิ่มรายการบัตรเครดิต</a>
                            <table id="payCCList" class="table">
                                <thead>
                                <tr>
                                    <th data-align="center" data-running-no="true">#</th>
                                    <th>ประเภทบัตรเครดิต</th>
                                    <th>เลขที่บัตร</th>
                                    <th>EDC</th>
                                    <th data-align="right" data-number-format="true">จำนวนเงิน</th>
                                    <th></th>
                                </tr>
                                </thead>
                            </table>
                        </div>
                        <a id="addPayType" class="btn btn-primary pull-right" style="margin-top:1em"><span class="glyphicon glyphicon-plus-sign"></span> เพิ่มวิธีการรับชำระ</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div id="panelMethodSummary" class="row">
        <div class='col-md-12'>
            <div class="panel panel-default">
                <div class="panel-heading">สรุปวิธีการรับชำระ</div>
                <div class="panel-body">
                    <table id="payTypeList" class="table table-hover" data-row-style="rowStyle" data-classes="table table-hover table-striped" >
                        <thead>
                        <tr>
                            <th data-running-no="true">#</th>
                            <th>วิธีการรับชำระ</th>
                            <th data-align="right" data-number-format="true" class="text-right">จำนวนเงิน</th>
                            <th></th>
                        </tr>
                        </thead>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <div id="panelPaymentSummary" class="row">
        <div class="col-md-12">
            <div class="panel panel-default">
                <div class="panel-heading">สรุปยอดเงินที่ต้องชำระ</div>
                <div class="panel-body">
                    <div class="form-horizontal">
                        <div class="form-group">
                            <label class="control-label col-sm-10" >ยอดเงินที่ต้องชำระก่อนภาษีมูลค่าเพิ่ม :</label>
                            <div class="col-sm-2">
                                <input id="charge" class="form-control text-right" disabled="disabled">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-10" >ภาษีมูลค่าเพิ่ม :</label>
                            <div class="col-sm-2">
                                <input id="vat" class="form-control text-right" disabled="disabled">
                            </div>
                        </div>
                        <div class="form-group" id="feeSummaryDiv">
                            <label class="control-label col-sm-10" >ค่าธรรมเนียม  :</label>
                            <div class="col-sm-2">
                                <input id="fee" class="form-control text-right" disabled>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-10" >ยอดเงินที่ต้องชำระรวมภาษีมูลค่าเพิ่ม :</label>
                            <div class="col-sm-2">
                                <input id="totalCharge" class="form-control text-right" disabled="disabled">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-10" >ยอดเงินที่ต้องชำระ :</label>
                            <div class="col-sm-2">
                                <input id="balanceDue" class="form-control text-right" disabled>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-10" >ยอดเงินรับมา :</label>
                            <div class="col-sm-2">
                                <input id="inputReceived" class="form-control text-right" disabled="disabled">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-10" >เงินทอน :</label>
                            <div class="col-sm-2">
                                <input id="change" class="form-control text-right" disabled="disabled">
                            </div>
                        </div>
                    </div>
                </div>์
            </div>
        </div>
    </div>
    <div id="panelSummaryPaymentList" style="display: none">
        <ul class="list-inline pull-right list-menu-set">
            <li><a id="buttonBackToPayment" class="btn btn-link"><span class="glyphicon glyphicon glyphicon-arrow-left"></span> กลับไปหน้าการรับชำระ</a></li>
        </ul>
        <div class="row">
            <div class="col-md-12">
                <div class="panel panel-success">
                    <div class="panel-heading">ผลการรับชำระเงิน</div>
                    <div class="panel-body">
                        <table id="tableSummaryReceipts" data-row-style="rowStyle" data-toggle="table" data-classes="table table-hover table-striped">
                            <thead>
                            <tr>
                                <th data-align="center" data-formatter="runningFormatter">#</th>
                                <%--<th data-field="custNo">เลขที่ลูกค้า</th>--%>
                                <th data-field="custName">ชื่อลูกค้า</th>
                                <th data-field="receiptNo">เลขที่ใบเสร็จรับเงิน/ใบกำกับภาษี</th>
                                <th data-field="receiptAmount" data-align="right">จำนวนเงินที่รับชำระ</th>
                                <th data-field="status">สถานะ</th>
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
        function Radio(el) { var obj = this; obj.el = el; obj.elem = $(el);
            obj.disable = function(){ if (arguments.length == 2 && $.isNumeric(arguments[0])) obj.elem.eq(arguments[0]).attr("disabled", (arguments[1] != null && $.type(arguments[1]) == 'boolean' ? arguments[1] : false)); return this }; obj.enable = function(){ obj.disable(false); return this }
            obj.label = function() { return this.elem.filter(":checked").data("label") };
            obj.val = function() { var s = this.elem.filter(":checked"),val = s.val(); return !val ? s.data("label") : val };
            obj.checked = function(index) { obj.elem.eq(index).attr("checked", true); return this }
            obj.unchecked = function() { obj.elem.each(function(i,o){ o.checked = false }); return this }
            obj.click = function(evt) { obj.elem.each(function(i,o){ $(o).click(function(e){ if (o.checked) evt(o.value) }) }); return this }
        }
        function ButtonGroup(el) {
            var obj = this, index = 0;
            obj.el = el;
            obj.elem = $(el);
            obj.val = function() { return obj.elem.find(".selection").text() }
            obj.index = function() { if (arguments.length == 1) { obj.list[arguments[0]].click() } return index }
            obj.list = obj.elem.find(".dropdown-menu a").each(function(i){ $(this).click(function(){ index = i }) });
            obj.hideIndex = function() { if (arguments.length == 1) { var selected = obj.elem.find(".dropdown-menu a")[arguments[0]]; $(selected).addClass("hide");} return index }
        }
        /*
        function DropDown(el, url) {
            var obj, data = [{ key: "-1", value: "กรุณาเลือก" }];
            this.el = el;
            this.elem = obj = $(el);
            this.error = function(flag) { if (arguments.length == 0 || flag) obj.parent().addClass("has-error"); else obj.parent().removeClass("has-error"); return this }
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
            this.init(url);
        }
        */
        function DropDown(el, url) { var obj = this, data = [{ key: "", value: "", text: "Please Select" }]; obj.el = el; obj.elem = $(el);
        obj.data = function(array) { if ($.type(array) == "array") { setup(data = array); return this } return data; };
        obj.init = function(url, v) {if (url) $.get(url, function(res){ if(res!=null){if(res.data[0].rateCode!=null){ exchange = res.data; }} setup(data = res.data, v);}); else setup(data, v); return this };
        obj.error = function(flag) { if (arguments.length == 0 || flag) obj.elem.parent().addClass("has-error"); else obj.elem.parent().removeClass("has-error"); return this }
        obj.disable = function(flag) { obj.elem.attr("disabled", (flag == null ? true : flag)); return this }
        obj.enable = function() { obj.disable(false); return this }
        obj.index = function() { if (arguments.length == 1) { var maxLen = data.length, index = arguments[0]; obj.elem.find("option").each(function(i, opt){ opt.removeAttribute("selected") }).eq(index < 0 ? maxLen + index : index).attr("selected", true); return this } return $.inArray("selected", obj.elem.find("option").map(function(i, opt){ return opt.selected ? "selected" : "no-selected" })) }
        obj.selected = function(){ return data[obj.index()]; };
        obj.val = function() { return obj.elem.val(); };
        obj.key = function(){ if (arguments.length == 0) { var key = obj.elem.find("option:selected").data("key"); if (typeof key === "string" && key === "undefined") { var item = data[obj.index()]; key = item && item.key ? item.key : item.id } return key } var key = arguments[0]; for (var i = 0, m = data.length; i < m; i++) { var item = data[i]; if (item.key && item.key == key) { obj.index(i); break; } if (item.id && item.id == key) { obj.index(i); break; } } return this }
        function setup(array, v) {
            obj.elem.find("*").remove();
            obj.elem.append('<option data-index="-1" data-key="-1" value="">กรุณาเลือก</option>');
            $.each(array,function(i,o){
                if(o.category == 'bank.name') {
                    if(v == 'name'){
                        obj.elem.append('<option data-index="'+ i +'" data-key="'+ o.code +'" value="'+ o.descFullTh +'"e>'+ o.descFullTh +'</option>')
                    }
                    else{
                        obj.elem.append('<option data-index="'+ i +'" data-key="'+ o.code +'" value="'+ o.code +'">'+ o.code +'</option>')
                    }
                }
                else{
                	
                	 if(o.rateCode!=null){
                         x = i;
                         x++;
                         obj.elem.append('<option data-index="' + x + '" data-key="' + x + '" value="' + o.message + '">' + o.message + '</option>')
                     } else {
                         obj.elem.append('<option data-index="' + i + '" data-key="' + o.key + '" value="' + o.value + '">' + o.value + '</option>')
                     }
                //    obj.elem.append('<option data-index="'+ i +'" data-key="'+ o.key +'" value="'+ o.value +'"e>'+ o.value +'</option>')
                }
            });
        }
        this.init(url);
        //data = obj.elem.change(window[el.substring(1) +"ChangeEvent"]).find("option").map(function(i, opt){ return { key: opt.getAttribute("data-key"), value: opt.value, text: opt.textContent } });
    }
        function Input(el, maxLen, propPos) { var obj = this; obj.el = el; obj.elem = $(el);
            obj.error = function(flag) { if (arguments.length == 0 || flag) obj.elem.parent().addClass("has-error"); else obj.elem.parent().removeClass("has-error"); return this }
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
        function Message(el) { var obj = this, timeCnt = 0, loadFunc; obj.el = el; obj.elem = $(el);
            obj.hide = function() { obj.elem.addClass("hide"); return obj };
            obj.show = function(flag) { if (flag == null || flag) obj.elem.removeClass("hide"); else obj.hide(); return obj };
            obj.clear = function() { obj.elem.find("*").remove(); obj.hide(); return obj };
            obj.message = function(arr, cls) { $.each(arr, function(i,o) { var m = o; if ($.type(o) === "object") { m = (o.desc || o.messageDesc) +" [code="+ (o.code || o.messageCode) +"]" }; obj.elem.append('<div class="'+ cls +'">'+ m +'<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button></div>') }); return obj };
            obj.error = function(arr) { return obj.message(arr, "alert alert-danger") };
            obj.warn = function(arr) { return obj.message(arr, "alert alert-warning") };
            obj.success = function(arr) { return obj.message(arr, "alert alert-success") };
            obj.valid = function(jqXHR, textStatus, errorThrow){ var res = jqXHR; if (jqXHR && jqXHR.responseJSON) res = jqXHR.responseJSON; if (res && (res.statusCode == '0' || $.type(res._embedded) === 'object')) return true; obj.warn(res && $.type(res.warningList) === 'array' ? res.warningList : []).error(res && $.type(res.errorList) === 'array' ? res.errorList : ["An error occurred on the server side but there is no error message returned."]).show(); return false }
            obj.hideLoad = function(){ obj.stopLoad().clear(); return this }; obj.stopLoad = function(){ clearInterval(loadFunc); return this }
            obj.showLoad = function(msg){ obj.elem.append('<div id="'+ obj.el +'-loading" class="alert alert-warning">'+ bind(msg, 0) +'</div>'); timeCnt = 0; var elem = document.getElementById(obj.el +"-loading"); loadFunc = setInterval(function(){ elem.innerHTML = bind(msg, ++timeCnt) }, 1000); obj.show(); return this }
            function bind(msg, timeCnt) { return msg.replace(/\{timeCnt\}/g, timeCnt) }
        }
        function FadePanel() { var obj = this, index = 0, dura = 200;
            obj.els = $.map(arguments, function(o,i){ return $.type(o) !== "string" ? null : o });
            obj.elems = $(obj.els.join(","));
            obj.hide = function() { obj.elems.addClass("hide"); return this }; obj.show = function() { obj.elems.removeClass("hide"); return this }
            obj.prev = function() { obj.index(index - 1); return this }; obj.next = function() { obj.index(index + 1); return this }
            obj.index = function(ind) { if (ind === index) return this;console.log(obj.elems); var target = obj.elems.length > ind && ind > -1 ? ind : (ind >= obj.elems.length ? 0 : obj.elems.length - 1); obj.elems.eq(index).fadeOut(dura, function(){ obj.elems.eq(index = target).removeClass("hide").css("display", "") }); return this }
            obj.elems.eq(index).hide().removeClass("hide").show(dura);
            arguments[0].list.each(function(i,o){ $(o).click(function(){ obj.index(i) }) })
        }
        function Panel(el) { var obj = this, dura = 500; obj.el = el; obj.elem = $(el);
            obj.hide = function(ms) { obj.elem.hide(ms || dura); return this }; obj.show = function(ms) { obj.elem.show(ms || dura); return this }
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
                    + $.map(array, function(v,i){ var field = headers[i].field, value = v;
                        if (headers[i].runningNo) value = (obj.size() + 1);
                        else if (headers[i].numberFormat) { value = !$.isNumeric(v) ? "0.00" : parseFloat(v, 10).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,'); }
                        else if (headers[i].checkbox) value = replace(checkboxHtml, null, field, i, v);
                        else if (headers[i].radio) value = replace(radioHtml, null, field, i, v);
                        else if (headers[i].input) value = replace(inputHtml, 'style="width:100%;margin:-4px 0;text-align:'+ (headers[i].align || "left") +'"', field, i, v);
                        return '<td><div style="'+ (headers[i].align ? "text-align:"+ headers[i].align : "") +'">'+ value +'</div></td>' }).join("")
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
            obj.typeSum =  function(typeIndex) { return stripToNumber(obj.find("type", typeIndex).find("td:eq(2) div").text());}
            obj.deduct = function(index) { var sum = 0; this.elem.find("tbody tr").each(function(i, row){ var val = row.children[index].innerText.replace(/[,]/g, ""); sum += (isNaN(val) ? 0 : parseFloat((val * -1), 10)) }); return sum };
            obj.calcBalance = function(typeindex,index) { var sum = 0; var gettingIncome = 'ขาเข้า'; this.elem.find("tbody tr").each(function(i, row){ var type = row.children[typeindex].innerText.replace(/[]/g, ""); var multiplier = (gettingIncome == type.trim()) ? 1 : -1; var val = row.children[index].innerText.replace(/[,]/g, ""); sum += (isNaN(val) ? 0 : parseFloat((val * multiplier), 10)) }); return sum };
            obj.sum = function(index) { var sum = 0; this.elem.find("tbody tr").each(function(i, row){ var val = row.children[index].innerText.replace(/[,]/g, ""); sum += (isNaN(val) ? 0 : parseFloat(val, 10)) }); return sum }
            $(obj.el).on("click", ".delList", function(){ $(this).parent().parent().remove(); $.each(headers, function(i,p){ if (p.runningNo) reorder(i) }); onDel(obj.data()) });
            obj.elem.removeClass("table").removeClass("table-hover").addClass("table").addClass("table-hover");
            obj.elem.find("thead th").each(function(i,o){ var elem = $(o); headers.push({ "field": elem.data("field"), "align": $.trim(elem.data("align")), "runningNo": elem.data("runningNo") === true, "numberFormat": elem.data("numberFormat") === true, "checkbox": elem.data("checkbox") === true, "radio": elem.data("radio") === true, "input": elem.data("input") === true }) });
            function replace(str, style, field, index, value){ var s = str; if (style) s = s.replace("\{style\}", style); return s.replace("\{field\}", $.trim(field)).replace("\{index\}", index).replace("\{value\}", value) }
            function extract(prop, col) { if (prop.checkbox) { var elem = col.children[0].children[0]; return !elem.checked ? "" : elem.value } else if (prop.radio) { var elem = col.children[0].children[0]; return !elem.checked ? "" : elem.value } else if (prop.input) { var elem = col.children[0].children[0]; return elem.value } return col.children[0].innerText }
            if(obj.body.length < 1) { obj.elem.append("<tbody></tbody>"); obj.body = obj.elem.find("tbody") }
        }
        function BootstrapTable(el) { var obj = this, checkEvt = function(e){ console.log(e) }, uncheckEvt = checkEvt; obj.el = el; obj.elem = $(el);
            obj.clear = function() { obj.elem.bootstrapTable("removeAll"); return obj }; obj.remove = function(index){ obj.elem.bootstrapTable("remove", { field: "index", values: [index] }); return this }
            obj.showLoad = function() { this.elem.bootstrapTable("showLoading"); return this }; obj.hideLoad = function() { this.elem.bootstrapTable("hideLoading"); return this };
            obj.find = function(field, value) { var data = obj.elem.bootstrapTable("getData"); return $.map(data, function(o,i){ return o[field] === value ? o : null }) }
            obj.insert = function(row) { var data = obj.elem.bootstrapTable("getData"); obj.elem.bootstrapTable("insertRow", { "index": data.length, "row": row }); return this }
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
        function Div(el) {
            var obj = this; obj.el = el; obj.elem = $(el);
            obj.hide = function() { obj.elem.addClass("hide"); return obj };
            obj.show = function(flag) { if (flag == null || flag) obj.elem.removeClass("hide"); else obj.hide(); return obj };
        }
        // TO SUPPORT ECLIPSE OUTLINE.
        self.breadcrumb = new Breadcrumb("#breadcrumb");
        self.message = new Message("#message");

        self.buttonSubmitPayment = new Button("#submitPayment");
        self.panelNavigation = new Panel("#panelNavigation");
        self.panelPaymentDetails = new Panel("#panelPaymentDetails");
        self.panelPaymentSummary = new Panel("#panelPaymentSummary");
        self.panelMethodSummary = new Panel("#panelMethodSummary");
        self.panelSummaryPaymentList = new Panel("#panelSummaryPaymentList");
        self.buttonSetPayType = new ButtonGroup("#setPayType");
        self.panelPayType = new FadePanel(self.buttonSetPayType, "#payType1", "#payType2","#payType3");


        self.radioSpecialOptions = new Radio("[name=specialOptions]");

        self.inputPayCashAmount = new NumberInput("#payCashAmount");
        self.inputPayCCType = new DropDown("#payCCType").data([{ key: "1", value: "VISA" },{ key: "2", value: "MASTER" }]);
        self.inputPayCCBankName = new DropDown("#payCCBankName").data([{ key: "1", value: "กรุงเทพ" },{ key: "2", value: "กสิกรไทย" }]);
        
        self.inputPayChqBankCode = new DropDown("#payChqBankCode").init("../findBankNameList.json", "code");
        self.inputPayChqBankName = new DropDown("#payChqBankName").init("../findBankNameList.json", "name");
        self.inputPayChqNo = new Input("#payChqNo");
        self.inputPayChqDate = new Input("#payChqDate");
        self.inputPayChqBranch = new Input("#payChqBranch");
        self.inputPayChqAmount = new NumberInput("#payChqAmount");
        
        self.inputPayCCNo = new Input("#payCCNo");
        self.inputPayCCAmount = new NumberInput("#payCCAmount");
        self.buttonPayChqSubmit = new Button("#payChqSubmit");
        self.buttonPayCCSubmit = new Button("#payCCSubmit");
        self.buttonAddPayType = new Button("#addPayType");

        self.inputCharge = new NumberInput("#charge");
        self.inputVat = new NumberInput("#vat");
        self.inputTotalCharge = new NumberInput("#totalCharge");
        self.inputFee = new NumberInput("#fee");
        self.inputBalanceDue = new NumberInput("#balanceDue");
        self.inputChange = new NumberInput("#change");
        self.inputReceived = new NumberInput("#inputReceived");

        self.tablePayTypeList = new Table("#payTypeList");
        self.tablePayChqList = new Table("#payChqList");
        self.tablePayCCList = new Table("#payCCList");
        self.tableSummaryReceipts = new BootstrapTable("#tableSummaryReceipts");

        self.feeSummaryDiv = new Div("#feeSummaryDiv");

        self.buttonPayMoneyOrderButtonSubmit = new Button("#payMoneyOrderButtonSubmit");
        self.inputPayMoneyOrderInputDate = new Input("#payMoneyOrderInputDate");
        self.inputPayMoneyOrderInputNo = new Input("#payMoneyOrderInputNo");
        self.inputPayMoneyOrderInputPostCode = new Input("#payMoneyOrderInputPostCode");
        self.inputPayMoneyOrderInputAmount = new NumberInput("#payMoneyOrderInputAmount");
        self.tablePayMoneyOrderTableList = new Table("#payMoneyOrderTableList");

        self.buttonCreatePaymentAndPrint = new Button("#buttonCreatePaymentAndPrint");
        self.buttonCancelPayment = new Button("#buttonCancelPayment");
        self.buttonBackToPayment = new Button("#buttonBackToPayment");

        self.amount = 0;
        (function(){ $(window["setup"]) })();
        $('#payChqBankCode').click(function () {
            var key = view.inputPayChqBankCode.key();
            $('#payChqBankName option:selected').prop('selected', false);
            $('#payChqBankName option[data-key='+key+']').prop('selected', true);
        });

        $('#payChqBankName').click(function () {
            var key = view.inputPayChqBankName.key();
            $('#payChqBankCode option:selected').prop('selected', false);
            $('#payChqBankCode option[data-key='+key+']').prop('selected', true);
        });
        return this;
    })(jQuery);

    function setup() {
        view.tablePayTypeList.onDelete(function(data) {
            if (data.length < 1) view.buttonSubmitPayment.disable();
            calculate();
        });

        view.buttonSubmitPayment.disable();
        ///////////////////////////////////////////////
        var billingList = view.session("billingList"), afterSaleDiscount = 0, amount = 0, charge = 0, vat = 0, fee = 0, totalCharge = 0, deduction = 0, feeAmount= 0 ;
        $.each(billingList, function(i,o){
            vat += o.vatAmount;
            $.each(o.serviceList, function(j,p){
                if(p.serviceNo != "FEE") amount += (p.serviceAmount);
                else fee = p.serviceAmount;
                feeAmount = p.serviceAmount
            });
        });

        view.amount = amount;
        view.inputCharge.val(charge = amount - vat);
        view.inputVat.val(vat);
        view.inputFee.val(feeAmount);
        view.inputTotalCharge.val(totalCharge = amount + fee);
        view.inputBalanceDue.val(totalCharge);

        var debtAmt = Math.max(0, view.inputBalanceDue.val() - view.inputReceived.val());
        view.inputPayCashAmount.val(debtAmt);
        view.inputPayCCAmount.val(debtAmt);
        view.inputPayChqAmount.val(debtAmt);
    }
    $("#testmenu li a").click(function () { $(this).parents(".btn-group").find('.selection').text($(this).text()); $(this).parents(".btn-group").find('.selection').val($(this).text()); });
    $("#testmenu2 li a").click(function () { $(this).parents(".btn-group").find('.selection').text($(this).text()); $(this).parents(".btn-group").find('.selection').val($(this).text()); });
    function buttonCreatePaymentAndPrintClickEvent() {
        if (view.inputBalanceDue.val() > view.inputReceived.val()) {
            view.message.clear().error(["ระบบไม่อนุญาตให้จ่าย เนื่องจากจำนวนเงินไม่ถูกต้อง"]).show();
            return false;
        }

        var balanceDue = view.inputBalanceDue.val(), received = view.inputReceived.val(), change = view.inputChange.val();
        var params = {
            "amount": view.amount - view.inputVat.val()
            ,"payAmount": view.inputTotalCharge.val()
            ,"discount": ""
            ,"vatAmount": view.inputVat.val()
            ,"totalPaid": balanceDue
            ,"receiveAmount": received
            ,"remainAmount": change
            ,"paymentCase": $.map(view.tablePayTypeList.data(), function(a,i){ return a[1] }).join(" + ")
            ,"remark": ""
        };
        
        var paymentCase = "",methodIndex = '0';
        var chequeRow = view.tablePayTypeList.find("method", 1);
        var creditRow = view.tablePayTypeList.find("method", 2);
        // <!-- Pay Method. -->
        var change = 0 , amount = 0, countPay = 0, payIndex = [], amounts = [], receivedMoney = view.inputReceived.val(), balanceDue = $("#balanceDue").val()/*advance = view.inputAdvanced.val()*/;
    change = Number(receivedMoney) - Number(balanceDue);
    
	for(var i=0;i<11;i++) { var payCashFlg = false, paycase = []; paycase[i] = view.tablePayTypeList.find("method", i); amounts[i] = paycase[i].find("td:eq(2) div").text().replace(/[,]/g, ""); if (paycase[i].length > 0) { payIndex[countPay] = i; countPay++; } }
	if (countPay > 0) { if (countPay > 1) { if(payIndex.indexOf(0)!='-1') {change = Number(receivedMoney) - Number(balanceDue); amounts[payIndex.indexOf(0)] = Number(amounts[payIndex.indexOf(0)]) - change; payAdvance = "0"; if (amounts[payIndex.indexOf(0)]<0) { Math.max(amounts); } } else { amounts[payIndex[0]] = Number(amounts[payIndex[0]])- change; payAdvance = "1";} } else { if(payIndex.indexOf(0)!='-1') { change = Number(receivedMoney) - Number(balanceDue); amounts[payIndex.indexOf(0)] = Number(amounts[payIndex.indexOf(0)]) - change; payAdvance = "0"; } else { amounts[payIndex[0]] = Number(amounts[payIndex[0]])- change; payAdvance = "0"; } } }

        $.map((window["payChqListData"] || []), function(o, i) { // For: Cheque
    		if(chequeRow.length > 0){	// Fix by EPIS8 23/12/2016 refer issue no.57
    			params["methods["+ methodIndex +"].type"] = "CHEQUE";
    			params["methods["+ methodIndex +"].code"] = "CH";
    			params["methods["+ methodIndex +"].name"] = "เช็ค";
    			params["methods["+ methodIndex +"].chequeNo"] = $.trim(o[4]);
    			params["methods["+ methodIndex +"].amount"] = amounts[1];
    			params["methods["+ methodIndex +"].payChqBankCode"] = $.trim(o[1]);
    			params["methods["+ methodIndex +"].payChqBankName"] = $.trim(o[2]);
    			params["methods["+ methodIndex +"].payChqBranch"] = $.trim(o[3]);
    			params["methods["+ methodIndex++ +"].payChqDate"] = $.trim(o[5]);

    			paymentCase = paymentCase.concat("เช็ค "+$.trim(o[2])+" เลขที่: "+$.trim(o[4])+" + ");
    		}
    	});
        console.log("creditRow-->")
        console.log(creditRow.length)
    	$.map((window["payCCListData"] || []), function(o, i) { // For: Credit Card
    		if(creditRow.length > 0){	// Fix by EPIS8 23/12/2016 refer issue no.57
    			params["methods["+ methodIndex +"].type"] = "CREDITCARD";
    			params["methods["+ methodIndex +"].code"] = "CR";
    			params["methods["+ methodIndex +"].name"] = "บัตรเครดิต";
    			params["methods["+ methodIndex +"].cardType"] = $.trim(o[1]);
    			params["methods["+ methodIndex +"].cardNo"] = $.trim(o[2]);
    			params["methods["+ methodIndex +"].bankName"] = $.trim(o[3]);
    			params["methods["+ methodIndex +"].docNo"] = $.trim(o[2]);
    			params["methods["+ methodIndex++ +"].amount"] = amounts[2];
    			paymentCase = paymentCase.concat("บัตรเครดิต "+$.trim(o[1])+" เลขที่: "+$.trim(o[2])+" + ");
    		}
    	});
    	$.map((window["payMoneyOrderTableListData"] || []), function(o, i) { // For: Money Order
    		if(moneyOrderRow.length > 0){	// Fix by EPIS8 23/12/2016 refer issue no.57
    			params["methods["+ methodIndex +"].type"] = "MONEYORDER";
    			params["methods["+ methodIndex +"].code"] = "MO";
    			params["methods["+ methodIndex +"].name"] = "ธนาณัติ";
    			params["methods["+ methodIndex +"].mnyOrderNo"] = $.trim(o[1]);
    			params["methods["+ methodIndex +"].mnyOrderDt"] = o[2] +" 00:00:00";
    			params["methods["+ methodIndex +"].postCode"] = $.trim(o[3]);
    			params["methods["+ methodIndex++ +"].amount"] = amounts[3];
    			paymentCase = paymentCase.concat("ธนาณัติ เลขที่: "+$.trim(o[1])+" + ");
    		}
    	});
    	$.map((window["payBillExchgTableListData"] || []), function(o, i) { // For: Bill of Exchange
    		if(billExchangeRow.length > 0){	// Fix by EPIS8 23/12/2016 refer issue no.57
    			params["methods["+ methodIndex +"].type"] = "BILLEXCHANGE";
    			params["methods["+ methodIndex +"].code"] = "BX";
    			params["methods["+ methodIndex +"].name"] = "ตั๋วแลกเงิน";
    			params["methods["+ methodIndex +"].trnfNo"] = $.trim(o[1]);
    			params["methods["+ methodIndex +"].transferDt"] = o[2] +" 00:00:00";
    			params["methods["+ methodIndex +"].postCode"] = $.trim(o[3]);
    			params["methods["+ methodIndex++ +"].amount"] = amounts[4];
    			paymentCase = paymentCase.concat("ตั๋วแลกเงิน เลขที่: "+$.trim(o[1])+" + ");
    		}
    	});
    	$.map((window["payCouponTableListData"] || []), function(o, i) { // For: Coupon
    		if(couponRow.length > 0){	// Fix by EPIS8 23/12/2016 refer issue no.57
    			params["methods["+ methodIndex +"].type"] = "COUPON";
    			params["methods["+ methodIndex +"].code"] = "CP";
    			params["methods["+ methodIndex +"].name"] = "คูปอง";
    			params["methods["+ methodIndex +"].couponNo"] = $.trim(o[1]);
    			params["methods["+ methodIndex++ +"].amount"] = amounts[5];
    			paymentCase = paymentCase.concat("คูปอง เลขที่: "+$.trim(o[1])+" + ");
    		}
    	});
    	$.map((window["payBankTxnfTableListData"] || []), function(o, i) { // For: Money Transfer
    		if(moneyTransferRow.length > 0){	// Fix by EPIS8 23/12/2016 refer issue no.57
    			params["methods["+ methodIndex +"].type"] = "BANKTRANSFER";
    			params["methods["+ methodIndex +"].code"] = "TR";
    			params["methods["+ methodIndex +"].name"] = "เงินโอนในประเทศ";
    			params["methods["+ methodIndex +"].bankAcct"] = o[4];
    			params["methods["+ methodIndex +"].transferDt"] = o[5] +" 00:00:00";
    			params["methods["+ methodIndex +"].isBackDt"] = "${epContext.roleName}" == "GFMISAGENT";
    			params["methods["+ methodIndex++ +"].amount"] = amounts[6];
    			paymentCase = paymentCase.concat("เงินโอนในประเทศ + ");
    		}
    	});
        $.map((window["payBankTxnfTableListGfData"] || []), function(o, i) {
    		if(moneyTransferGFRow.length > 0){
    			params["methods["+ methodIndex +"].type"] = "BANKTRANSFER";
    			params["methods["+ methodIndex +"].code"] = "GF";
    			params["methods["+ methodIndex +"].name"] = "เงินโอน (GFMIS)";
    			params["methods["+ methodIndex +"].bankAcct"] = o[4];
    			params["methods["+ methodIndex +"].transferDt"] = o[5] +" 00:00:00";
    			params["methods["+ methodIndex +"].isBackDt"] = "${epContext.roleName}" == "GFMISAGENT";
    			params["methods["+ methodIndex++ +"].amount"] = amounts[10];
    			paymentCase = paymentCase.concat("เงินโอน (GFMIS) + ");
    		}
    	});
    	$.map((window["payOffsetTableListData"] || []), function(o, i) { // For: Offset
    		if(offsetRow.length > 0){	// Fix by EPIS8 23/12/2016 refer issue no.57
    			params["methods["+ methodIndex +"].type"] = "OFFSET";
    			params["methods["+ methodIndex +"].code"] = "OF";
    			params["methods["+ methodIndex +"].name"] = "offset";
    			params["methods["+ methodIndex +"].offsetDocumentNo"] = o[1];
    			params["methods["+ methodIndex +"].offsetAccountCode"] = o[2];
    			params["methods["+ methodIndex +"].offsetAccountName"] = o[3];
    			params["methods["+ methodIndex++ +"].amount"] = amounts[7];
    			paymentCase = paymentCase.concat("offset "+o[1]+" + "); // add Document No
    		}
    	});
    	$.map((window["payOtherTableListData"] || []), function(o, i) { // For: Other Type
    		if(otherRow.length > 0){	// Fix by EPIS8 23/12/2016 refer issue no.57
    			params["methods["+ methodIndex +"].type"] = "OTHER";
    			params["methods["+ methodIndex +"].code"] = "OT"; 
    			params["methods["+ methodIndex +"].name"] = "อื่นๆ";
    			params["methods["+ methodIndex +"].otherNo"] = $.trim(o[1]);
    			params["methods["+ methodIndex +"].otherType"] = $.trim(o[2]);
    			params["methods["+ methodIndex +"].otherDt"] = $.trim(o[3]);
    			params["methods["+ methodIndex++ +"].amount"] = amounts[9];
    			paymentCase = paymentCase.concat("อื่นๆ + ");
    		}
    	});
    	$.map((window["payForeignTableListData"] || []), function(o, i) {
    	    console.log("moneyForeignRow >>> ");
    	    console.log(moneyForeignRow.length);
    		if(moneyForeignRow.length > 0){
                console.log("moneyForeignRow +++ true");
    			params["methods["+ methodIndex +"].type"] = "FOREIGNTRANSFER";
    			params["methods["+ methodIndex +"].code"] = "TF";
    			params["methods["+ methodIndex +"].name"] = "เงินโอนต่างประเทศ";
                params["methods["+ methodIndex +"].transferDt"] = o[3] +" 00:00:00";
                params["methods["+ methodIndex +"].isBackDt"] = "${epContext.roleName}" == "GFMISAGENT";
    			params["methods["+ methodIndex++ +"].amount"] = amounts[8];
    			paymentCase = paymentCase.concat("เงินโอนต่างประเทศ + ");
    		}
    	});
    	var cashRow = view.tablePayTypeList.find("method", 0);
        if (cashRow.length == 1) { // For: Cash
            params["methods["+ methodIndex +"].type"] = "CASH";
            params["methods["+ methodIndex +"].code"] = "CC";
            params["methods["+ methodIndex +"].name"] = "เงินสด";
            params["methods["+ methodIndex++ +"].amount"] = amounts[0];//cashRow[0].payAmount;
            paymentCase = paymentCase.concat("เงินสด + ");
        }
        paymentCase = paymentCase.substring(0, paymentCase.length-3);
        console.log("methodIndex: "+methodIndex);
        params["paymentCase"] = paymentCase;
        console.log("step4 Payment set.");
        // <!-- Invoice List. -->
        var custIndex = 0, billingList = view.session("billingList"), receiptList = [];
        while (customer = billingList[custIndex]) {
            console.log("customer")
            console.log(customer)
            params["customers["+ custIndex +"].custNo"] = customer.rctCustNo;
            params["customers["+ custIndex +"].custName"] = customer.rctCustName;
            params["customers["+ custIndex +"].custType"] = customer.rctCustType;
            params["customers["+ custIndex +"].remark"] = customer.remark;
            params["customers["+ custIndex +"].split"] = customer.split;
            params["customers["+ custIndex +"].agentAddressCode"] = customer.agentAddressCode;
            params["customers["+ custIndex +"].taxNo"] = customer.rctTaxId;
            params["customers["+ custIndex +"].custBranch"] = customer.rctBranch;
            params["customers["+ custIndex +"].address1"] = customer.rctAddr;
            params["customers["+ custIndex +"].agentTaxNo"] = customer.agentTaxNo;
            params["customers["+ custIndex +"].feesIncome"] = customer.feesIncome;
            params["customers["+ custIndex +"].ref1"] = customer.ref1;
            for (var serviceIndex = 0, n = customer.serviceList.length; serviceIndex < n; serviceIndex++) {
                var service = customer.serviceList[serviceIndex];
                console.log(service);
                params["customers["+ custIndex +"].services["+ serviceIndex +"].code"] = service.serviceNo;
                params["customers["+ custIndex +"].services["+ serviceIndex +"].name"] = service.serviceName;
                params["customers["+ custIndex +"].services["+ serviceIndex +"].ref1"] = service.ref1;
                params["customers["+ custIndex +"].services["+ serviceIndex +"].ref2"] = service.ref2;
                params["customers["+ custIndex +"].services["+ serviceIndex +"].moreData"] = service.serviceMoreData;
                params["customers["+ custIndex +"].services["+ serviceIndex +"].amount"] = service.serviceAmount - service.serviceVatAmount;
                params["customers["+ custIndex +"].services["+ serviceIndex +"].discount"] = 0;
                params["customers["+ custIndex +"].services["+ serviceIndex +"].charge"] = service.serviceAmount - service.serviceVatAmount;
                params["customers["+ custIndex +"].services["+ serviceIndex +"].vat"] = service.serviceVatAmount;
                params["customers["+ custIndex +"].services["+ serviceIndex +"].totalCharge"] = service.serviceAmount;
                params["customers["+ custIndex +"].services["+ serviceIndex +"].deduction"] = 0;
                params["customers["+ custIndex +"].services["+ serviceIndex +"].feeFlg"] = service.feeFlg;
            }
            custIndex++;
        }
        console.log("step5 Service inv List set.");
        console.log("-- Ready to post! --");
        console.log(params);
        //return false;
        post("../createPaymentAgent.json", params, function(res){
            view.message.clear();
            $(document.body).append('<form action="../printPaymentAgentReceipt.pdf" method="post" target="_printForm"></form>');
            var form = document.forms[0]; function input(n,v) { var input = document.createElement("input"); input.type = "hidden"; input.name = n; input.value = v; return input }
            $.each(res.data, function(i,o){
                form.appendChild(input("receipts["+ i +"].id", o.id));
            });
            form.submit();
            view.breadcrumb.index(4);
            view.panelNavigation.hide(0);
            view.panelPaymentDetails.hide(0);
            view.panelPaymentSummary.hide(0);
            view.panelMethodSummary.hide(0);
            view.panelSummaryPaymentList.show(400);
            var data = [];
            $.each(res.data, function(i,r){
                data.push({ "custNo": r.accountNo
                    ,"custName": r.accountName
                    ,"receiptNo": r.no
                    ,"receiptAmount": view.utils.numberFormat(r.totalCharge)
                    ,"status": '<span class="glyphicon glyphicon-ok-circle"></span> บันทึกลงระบบสำเร็จ'})
            });
            view.tableSummaryReceipts.data(data);
        }, view.message);
    }
    function buttonCancelPaymentClickEvent() {
        var agentData = view.session("agentData");
        var params = "id="+agentData.id;
        location.href = "pay-agent_1.jsp?"+params;
    }
    function buttonBackToPaymentClickEvent() {
        var agentData = view.session("agentData");
        var params = "id="+agentData.id;
        location.href = "pay-agent_1.jsp?"+params;
    }
    function addPayTypeClickEvent() {
        var index = view.buttonSetPayType.index(), payAmt = view.inputPayCashAmount.val();
        if (index == 1)      { window["payChqListData"] = view.tablePayChqList.data();                         payAmt = view.tablePayChqList.sum(6)        }
    	else if (index == 2) { window["payCCListData"] = view.tablePayCCList.data();                           payAmt = view.tablePayCCList.sum(4)         }
        //if (index == 1) { window["payCCListData"] = view.tablePayCCList.data();}
        view.tablePayTypeList.find("method", index).remove();
        view.tablePayTypeList.insert(["-", view.buttonSetPayType.val(), payAmt], true, { method: index });
        view.inputReceived.val(view.tablePayTypeList.sum(2));
        view.buttonSubmitPayment.enable();
        calculate();
    }
    function payChqSubmitClickEvent() {
        /* if(view.inputPayChqDate.val() == "") { view.inputPayChqDate.error(true); return; }
        view.inputPayChqDate.error(false);
        view.tablePayChqList.insert(["-", view.inputPayChqBankCode.val(), view.inputPayChqBankName.val(), view.inputPayChqBranch.val(), view.inputPayChqNo.val(), view.inputPayChqDate.val(), view.inputPayChqAmount.val()], true)
     */
     if(validationChqSubmit()) view.tablePayChqList.insert(["-", view.inputPayChqBankCode.val(), view.inputPayChqBankName.val(), view.inputPayChqBranch.val(), view.inputPayChqNo.val(), view.inputPayChqDate.val(), view.inputPayChqAmount.val()], true)
    }
    function validationChqSubmit() {
    	var isValid = true;
    	if(view.inputPayChqNo.val() == "") {view.inputPayChqNo.error(true); isValid = false;} else {view.inputPayChqNo.error(false);}
    	if(view.inputPayChqDate.val() == "") {view.inputPayChqDate.error(true); isValid = false;} else {view.inputPayChqDate.error(false);}
    	return isValid;
    }
    function payCCSubmitClickEvent() {
        view.tablePayCCList.insert(["-", view.inputPayCCType.val(), view.inputPayCCNo.val(), view.inputPayCCBankName.val(), view.inputPayCCAmount.val()], true)
    }
    function calculate() {
        var balanceDue = ((view.inputTotalCharge.val() * 100)) / 100;
        var received = view.tablePayTypeList.sum(2);
        var cash = stripToNumber(view.tablePayTypeList.find("method", 0).find("td:eq(2) div").text());
        var nonCash = ((received * 100) - (cash * 100))/100;
        var change = 0;
        console.log("Initial: change-> "+change+", cash-> "+cash+", nonCash->"+nonCash);
        if (nonCash > 0) {
            var diff = ((nonCash * 100) - (balanceDue * 100))/100;
            if( diff > 0) {
                change = cash;
                console.log("step B: change-> "+change+", cash-> "+cash);
            } else {
                change = cash > 0 ? ((cash * 100) + (diff * 100))/100 : 0;
                if(change < 0) change = 0;
                console.log("step C: change-> "+change+", cash-> "+cash);
            }
        } else {
            var diff = ((cash * 100) - (balanceDue * 100))/100;
            change = (diff < 0) ? 0 : diff;
            console.log("step D: change-> "+change+", cash-> "+cash);
        }
        view.inputBalanceDue.val(balanceDue);
        view.inputReceived.val(received);
        view.inputChange.val(change);

        var debtAmt = Math.max(0, view.inputBalanceDue.val() - view.inputReceived.val());
        view.inputPayCashAmount.val(debtAmt);
        view.inputPayCCAmount.val(debtAmt);
        view.inputPayChqAmount.val(debtAmt);
    }
    function detailFormatter(val, row, ind) {
        var details = '<table class="table table-striped table-bordered">'
            + '<thead>'
            + '<tr>'
            + '<th class="text-center"> ประเภทบริการ </th>'
            + '<th class="text-center"> ชื่อบริการ </th>'
            + '<th class="text-right"> จำนวนรายการ </th>'
            + '<th class="text-right"> จำนวนเงินต่อหน่วย </th>'
            + '<th class="text-right"> ภาษีมูลค่าเพิ่ม </th>'
            + '<th class="text-right"> ส่วนลด </th>'
            + '<th class="text-right"> ยอดเงินรวม </th>'
            + '</tr>'
            + '</thead>' + '<tbody>';
        for (var i = 0, m = row.services.length; i < m; i++) {
            //var issueDt = row.invoices[i].issueDt, dueDt = row.invoices[i].dueDt;
            details += '<tr>'
                + '<td>' + $.trim(row.services[i].serviceType) + '</td>'
                + '<td>' + $.trim(row.services[i].serviceName) + '</td>'
                + '<td class="text-right">' + row.services[i].serviceMoreData + '</td>'
                + '<td class="text-right">' + view.utils.numberFormat(row.services[i].serviceAmount) + '</td>'
                + '<td class="text-right">' + view.utils.numberFormat(row.services[i].serviceVat) + '</td>'
                + '<td class="text-right">' + view.utils.numberFormat(row.services[i].serviceDiscount) + '</td>'
                + '<td class="text-right">' + view.utils.numberFormat(row.services[i].serviceDeduction) + '</td>'
                + '<td class="text-right">' + view.utils.numberFormat(row.services[i].serviceTotalCharge) + '</td>'
                + '</tr>';
        }
        return details + '</tbody></table>';
    }
</script>