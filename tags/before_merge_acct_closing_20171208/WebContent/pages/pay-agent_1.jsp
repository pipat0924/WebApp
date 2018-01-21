<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>EIPS Payment Agent Screen - 01</title>
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
            <ol class="breadcrumb">
                <li><i>ตัวแทนรับชำระ</i></li>
                <li class="active">ข้อมูลการชำระเงิน</li>
                <li>ดำเนินการรับชำระ</li>
                <li>ผลการรับชำระ</li>
            </ol>
            <div id="message"></div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12 tab-modefile">
            <!-- Nav tabs -->
            <ul class="nav nav-tabs" role="tablist">
                <li role="presentation" class="active"><a href="#" aria-controls="tab_cus" role="tab" data-toggle="tab"><span class="glyphicon glyphicon-barcode"></span> ตัวแทนรับชำระค่าบริการ</a></li>
            </ul>
            <div class="panel panel-default panal-radius">
                <div class="panel-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-horizontal">
                                <div class="form-group">
                                    <label class="control-label col-sm-2">ชื่อประเภทตัวแทน :</label>
                                    <div class="col-sm-6"><input class="form-control" id="inputAgentType" disabled></div>
                                    <div class="col-sm-4">
                                        <a id="buttonSearchBillNo" class="btn btn-primary"><span class="glyphicon glyphicon-search"></span> แสดงรายละเอียด</a>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-2">รหัส Barcode / QR Code:</label>
                                    <div class="col-sm-6"><input class="form-control" id="inputProdBarcode1"></div>
                                    <div class="col-sm-4"></div>
                                </div>
                                <%--<div class="form-group">
                                    <label class="control-label col-sm-2">รหัส  :</label>
                                    <div class="col-sm-6"><input class="form-control" id="inputProdBarcode2"></div>
                                    <div class="col-sm-4"></div>
                                </div>--%>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div id="panelReceiptDetails" style="display: none">
                <ul id="navigatePanel" class="list-inline pull-right list-menu-set">
                    <li><a id="buttonProcessPayment" class="btn btn-link"><span class="glyphicon glyphicon-edit"></span> ดำเนินการรับชำระ</a></li>
                    <li><a id="buttonCancelPayment" class="btn btn-link"><span class="glyphicon glyphicon-remove-sign"></span> ยกเลิกดำเนินการ</a></li>
                </ul><br>
                <ul id="customerInfoTab" class="nav nav-tabs" role="tablist">
                    <li role="presentation" class="active"><a href="#tab_cus" aria-controls="tab_cus" role="tab" data-toggle="tab"><span class="glyphicon glyphicon-briefcase"></span> ข้อมูลการรับชำระเงิน</a></li>
                    <%--<li role="presentation"><a href="#sub_script" aria-controls="sub_script" role="tab" data-toggle="tab"><span class="glyphicon glyphicon-user"></span> ข้อมูลสำหรับใบเสร็จรับเงิน</a></li>--%>
                </ul>
                <div class="tab-content">
                    <div role="tabpanel" class="tab-pane active" id="tab_cus">
                        <div class="panel panel-default panal-radius">
                            <div class="panel-body">
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-horizontal">
                                            <div class="form-horizontal">
                                                <input type="hidden" id="inputAgentTaxId">
                                                <input type="hidden" id="inputServiceType">
                                                <input type="hidden" id="inputServiceName">
                                                <div class="form-group">
                                                    <!-- ice fixed code no.109 'ทุกระบบต้องเลขที่ใบแจ้งค่าใช้บริการใบแจ้งค่าใช้บริการ" -->
                                                    <label class="control-label col-sm-2" >เลขที่ใบแจ้งค่าใช้บริการ :</label>
                                                    <!-- end ice fixed code no.109 'ทุกระบบต้องเลขที่ใบแจ้งค่าใช้บริการใบแจ้งค่าใช้บริการ" -->
                                                    <div class="col-sm-3">
                                                        <input class="form-control" id="inputInvoiceNo">
                                                    </div>
                                                    <label class="control-label col-sm-2">กำหนดวันชำระเงิน :</label>
                                                    <div class="col-sm-3">
                                                        <input class="form-control" id="inputDueDate">
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label col-sm-2" >Ref No.1 :</label>
                                                    <div class="col-sm-3">
                                                        <input class="form-control" id="inputRef1No">
                                                    </div>
                                                    <label class="control-label col-sm-2">จำนวนเงิน :</label>
                                                    <div class="col-sm-3">
                                                        <input class="form-control" id="inputServiceAmount">
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label col-sm-2" >Ref No.2 :</label>
                                                    <div class="col-sm-3">
                                                        <input class="form-control" id="inputRef2No">
                                                    </div>
                                                    <label class="control-label col-sm-2">ค่าธรรมเนียมรับชำระ :</label>
                                                    <div class="col-sm-3">
                                                        <input class="form-control text-right" id="inputServiceFee">
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label col-sm-2">ชื่อลูกค้า :</label>
                                                    <div class="col-sm-3"><input id="inputCustomerName" class="form-control"></div>
                                                    <label class="control-label col-sm-2">Tax ID ลูกค้า :</label>
                                                    <div class="col-sm-3"><input id="inputCustomerTaxNo" class="form-control" maxlength="13"></div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label col-sm-2">สาขา :</label>
                                                    <div class="col-sm-3"><input id="inputCustomerBranch" class="form-control"></div>
                                                    <label class="control-label col-sm-2">ที่อยู่ :</label>
                                                    <div class="col-sm-3"><textarea id="inputCustomerAddress" class="form-control"></textarea></div>
                                                    <div class="col-sm-2 text-right">
                                                        <a id="buttonAddBillingList" class="btn btn-info"> <span class="glyphicon glyphicon-plus-sign"></span> เพิ่มรายการรับชำระ</a>
                                                    </div>
                                                </div>

                                            </div>
                                        </div><br>
                                        <table id="tableBillingList" data-row-style="rowStyle" data-toggle="table"
                                               data-classes="table table-hover table-striped">
                                            <thead>
                                            <tr>
                                                <th data-align="center" data-field="id" data-ter="runningFormatter">#</th>
                                                <!-- ice fixed code no.109 'ทุกระบบต้องเลขที่ใบแจ้งค่าใช้บริการใบแจ้งค่าใช้บริการ" -->
                                                <th data-align="center" data-field="serviceNo">เลขที่ใบแจ้งค่าใช้บริการ</th>
                                                <!-- end ice fixed code no.109 'ทุกระบบต้องเลขที่ใบแจ้งค่าใช้บริการใบแจ้งค่าใช้บริการ" -->
                                                <%--<th data-align="center" data-field="accountNo">Ref No.1</th>
                                                <th data-align="center" data-field="accountNo">Ref No.2</th>--%>
                                                <th data-field="dueDate" data-align="center">กำหนดวันชำระเงิน</th>
                                                <th data-field="serviceFee" data-align="right" data-formatter="numberFormatter">ค่าธรรมเนียมรับชำระ</th>
                                                <th data-field="serviceAmount" data-align="right" data-formatter="numberFormatter">จำนวนเงิน</th>
                                                <th data-align="center" data-formatter="operateButtonFormatter"></th>
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
            <div id="panelSummaryInfo" style="display: none">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <span class="glyphicon glyphicon-shopping-cart"></span>
                        สรุปยอดเงินที่ต้องชำระ
                    </div>
                    <div class="panel-body">
                        <div class="form-horizontal">
                            <!-- <input id="inputAmount" class="form-control text-right" disabled> -->
                            <div class="form-group">
                                <label class="control-label col-sm-10">ยอดเงินที่ต้องชำระก่อนภาษีมูลค่าเพิ่ม  :</label>
                                <div class="col-sm-2"><input id="inputCharge" class="form-control text-right" disabled></div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-10">ภาษีมูลค่าเพิ่ม  :</label>
                                <div class="col-sm-2"><input id="inputVat" class="form-control text-right" disabled></div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-10">ค่าธรรมเนียมรับชำระ  :</label>
                                <div class="col-sm-2"><input id="inputFee" class="form-control text-right" disabled></div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-10">ยอดเงินที่ต้องชำระรวมภาษีมูลค่าเพิ่ม :</label>
                                <div class="col-sm-2"><input id="inputTotalCharge" class="form-control text-right" disabled></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>
<script type="text/javascript">
	var agentData2 = "";
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
        window.get = function(url, params, success, msgDialog, preCheck){ $.ajax({ "url": url, "type": "GET", "data": params, "error": (msgDialog || { "valid": function(jqXHR, textStatus, errorThrow){ console.log(jqXHR); console.log("textStatus: "+ textStatus); console.log("errorThrow:"); console.log(errorThrow) } }).valid, "success": function(res){ var isNotJson = res.constructor == String; console.log(res); (preCheck || function(o){})(res); if (isNotJson) res = { "statusCode": "99", "errorList": ["No matching data found."] }; if (msgDialog && !msgDialog.valid(res)) { return; } success(res) } }) }
        window.post = function(url, params, success, msgDialog, preCheck){ $.ajax({ "url": url, "type": "POST", "data": params, "error": (msgDialog || { "valid": function(jqXHR, textStatus, errorThrow){ console.log(jqXHR); console.log("textStatus: "+ textStatus); console.log("errorThrow:"); console.log(errorThrow) } }).valid, "success": function(res){ var isNotJson = res.constructor == String; console.log(res); (preCheck || function(o){})(res); if (isNotJson) res = { "statusCode": "99", "errorList": ["No matching data found."] }; if (msgDialog && !msgDialog.valid(res)) { return; } success(res) } }) }
        window.add = function(num1, num2, dec) { if (!dec) dec = 2; return parseFloat((num1 + num2).toFixed(dec), 10); }; window.subtract = function(num1, num2, dec){ if (!dec) dec = 2; return parseFloat((num1 - num2).toFixed(dec), 10); }; window.multiply = function(num1, num2, dec){ if (!dec) dec = 2; return parseFloat((num1 * num2).toFixed(dec), 10); }; window.divide = function(num1, num2, dec){ if (!dec) dec = 2; return parseFloat((num1 / num2).toFixed(dec), 10); }
        window.find = function(array, propName, obj){ var tmp; for (var i = 0, m = array.length; i < m; i++) { if (array[i][propName] == obj[propName]) tmp = array[i]; } if (tmp == null) { array.push(tmp = obj); } return tmp; }
        window.toMap = function(array, propNames){ var map = {}, prop; function key(obj){ var p = []; for (var i = 0, m = propNames.length; i < m; i++) { p.push(obj[propNames[i]]) } return p.join() }; for (var i = 0, m = array.length; i < m; i++) { prop = key(array[i]); map[prop] = array[i] } return map }
        window.stripToNumber = function(str) { return $.type(str) === "string" && $.isNumeric(str = str.replace(/[^0-9\.]/g, "")) ? parseFloat(str, 10) : 0 }
        window.toDateString = function(ddMMyyyy){ return ddMMyyyy.replace(/(\d{2}).(\d{2}).(\d{4})/g, "$2/$1/$3") }
        window.runningFormatter = function(val, row, ind) { return ind + 1 }
        window.numberFormatter = function(val, row, ind) { return !$.isNumeric(stripToNumber(val)) ? "0.00" : parseFloat(val, 10).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,') }
        window.stringInputFormatter = function(val, row, ind) { return '<input value="'+ $.trim(val) +'" maxLength="40" class="form-control">' }
        window.numberInputFormatter = function(val, row, ind) { return '<input value="'+ self.utils.numberFormat(parseFloat(val || "0.00", 10)) +'" maxLength="10" style="width:100px" class="form-control text-right" onkeypress="return $.isNumeric(String.fromCharCode(event.which)) || (event.which == 46 && this.value.indexOf(\'.\') == -1)" onfocus="(function(o){ o.value = o.value.replace(/[,]/g, \'\') })(this)" onblur="(function(o){ o.value = parseFloat(o.value, 10).toFixed(2).replace(/(\\d)(?=(\\d{3})+\\.)/g, \'$1,\'); })(this)">' }
        window.moneyInputFormatter = function(val, row, ind) { var value = parseFloat(val || "0.00", 10).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, "$1,"); return '<input value="'+ value +'" maxLength="10" style="width:100px" class="form-control text-right" onkeypress="return $.isNumeric(String.fromCharCode(event.which)) || (event.which == 46 && this.value.indexOf(\'.\') == -1)" onkeyup="if (event.which == 13) this.blur()" onfocus="(function(o){ o.value = o.value.replace(/[,]/g, \'\'); o.select() })(this)" onblur="(function(o){ o.value = parseFloat(o.value, 10).toFixed(2).replace(/(\\d)(?=(\\d{3})+\\.)/g, \'$1,\'); o.style.backgroundColor = o.value !== \''+ value +'\' ? \'yellow\' : \'\'; var table, elem = o; while (table == null) { elem = elem.parentNode; if (elem.tagName == \'TABLE\') table = elem; } (window[table.id +\'InputBlurEvent\'] || function(t){ console.log(t) })(table) })(this)">' }
        window.modifyButtonFormatter = function(val, row, ind) { return '<a class="btn btn-link modList" onclick=\'(function(elem, row, ind){ var table = $("#"+ $(elem).closest("table")[0].id); window[table[0].id +"ModifyEvent"](table, row, ind) })(this, '+ JSON.stringify(row) +', '+ ind +')\'><span class="glyphicon glyphicon-pencil"></span></a>' }
        window.removeButtonFormatter = function(val, row, ind) { return '<a class="btn btn-link delList" onclick=\'(function(elem, row, ind){ $("#"+ $(elem).closest("table")[0].id).bootstrapTable("remove", { field: "index", values: [ind] }) })(this, '+ JSON.stringify(row) +', '+ ind +')\'><span class="glyphicon glyphicon-trash"></span></a>' }
        window.LoadingPanel = function(id) { var obj = this, loadCnt = 0, setupFunc, loadFunc; function checkElemReady(){ if ((obj.elem = $("#"+ id).css("font", "")).length != 1) { alert("The element hasn't insert into HTML DOM."); throw "The element hasn't insert into HTML DOM."; } clearTimeout(setupFunc); clearInterval(loadFunc) }; return { "elem": null, "finish": function(html){ checkElemReady(); (this.elem ? this.elem : this.elem = $("#"+ id)).css("font", "").html(html); return this }, "toString": function(){ var elem; setupFunc = setTimeout(function(){ loadFunc = setInterval(function(){ (elem || (elem = document.getElementById(id))).innerHTML = "Loading"+ Array(++loadCnt).join(".."); if (loadCnt > 60) loadCnt = 2 }, 250) }, 1000); return "<div id='"+ id +"' style='font:BOLD 16pt Arial'></div>" } } }
        window.operateButtonFormatter = function(val, row, ind) {
            return "<a href='#' onclick='removeFromServiceList("+row.id+")'><span class='glyphicon glyphicon-trash' aria-hidden='true'></span></a>"
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
        function DropDown(el, url) { var obj = this, data = [{ key: "", value: "", text: "Please Select" }]; obj.el = el; obj.elem = $(el);
            obj.data = function(array) { if ($.type(array) == "array") { setup(data = array); return this } return data; };
            obj.init = function(url) { if (url) $.get(url, function(res) { setup(data = res.data) }); else setup(data); return this };
            obj.disable = function(flag) { obj.elem.attr("disabled", (flag == null ? true : flag)); return this }
            obj.enable = function() { obj.disable(false); return this }
            obj.index = function() { if (arguments.length == 1) { var maxLen = data.length, index = arguments[0]; obj.elem.find("option").each(function(i, opt){ opt.removeAttribute("selected") }).eq(index < 0 ? maxLen + index : index).attr("selected", true); return this } return $.inArray("selected", obj.elem.find("option").map(function(i, opt){ return opt.selected ? "selected" : "no-selected" })) }
            obj.selected = function(){ return data[obj.index()]; };
            obj.val = function() { return obj.elem.val(); };
            obj.key = function(){ if (arguments.length == 0) { var key = obj.elem.find("option:selected").data("key"); if (typeof key === "string" && key === "undefined") { var item = data[obj.index()]; key = item && item.key ? item.key : item.id } return key } var key = arguments[0]; for (var i = 0, m = data.length; i < m; i++) { var item = data[i]; if (item.key && item.key == key) { obj.index(i); break; } if (item.id && item.id == key) { obj.index(i); break; } } return this }
            function setup(array) { obj.elem.find("*").remove();
                $.each(array,function(i,o){
                    if(o.category == 'payagent.service.category') {
                        obj.elem.append('<option data-index="'+ i +'" data-key="'+ o.code +'" value="'+ o.descFullTh +'">'+ o.descFullTh +'</option>')
                    } else if(o.category == 'payagent.service.name') {
                        obj.elem.append('<option data-index="'+ i +'" data-key="'+ o.code +'" value="'+ o.descFullTh +'">'+ o.descFullTh +'</option>')
                    } else {
                        obj.elem.append('<option data-index="'+ i +'" data-key="'+ o.key +'" value="'+ o.value +'">'+ (o.text || o.value) +'</option>')
                    }
                });
            }
            data = obj.elem.change(window[el.substring(1) +"ChangeEvent"]).find("option").map(function(i, opt){ return { key: opt.getAttribute("data-key"), value: opt.value, text: opt.textContent } });
        }
        function CheckBox(el) { var obj = this; obj.el = el; obj.elem = $(el); obj.elem.click(window[obj.elem.attr("name") +"ClickEvent"])
            obj.disable = function() { obj.elem.attr("disabled", arguments.length == 0 || (arguments.length == 1 && (arguments[0] == "true" || arguments[0] == true))); return this }
            obj.contains = function(val) { return $.inArray(val, obj.val()) > -1 };
            obj.val = function() { return obj.elem.filter(":checked").map(function(i,o){ return o.value }) };
            obj.unchecked = function(){ obj.elem.each(function(i,o){ o.checked = false }); return this }
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
            obj.elem.blur(window[el.substring(1) +"BlurEvent"]); obj.elem.keyup(function(e){ var func = (window[el.substring(1) +"KeyUpEvent"] || function(){}); func((e.which || e.keyCode || e.charCode || 0), obj.elem) }); obj.elem.focus(function(){ this.select() }); if ($.isNumeric(maxLen)) { obj.elem.attr("maxLength", maxLen) }
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
            obj.elem.blur(window[el.substring(1) +"BlurEvent"]); obj.elem.keyup(function(e){ var key = (e.which || e.keyCode || e.charCode || 0), func = (window[el.substring(1) +"KeyUpEvent"] || function(){}); func(key, obj.elem); if (key == 13) this.blur() }); obj.elem.focus(function(){ this.select() })
            if (obj.elem.val() == "") obj.elem.val("0" + (decimal < 1 ? "" : "."+ Array(decimal + 1).join("0")));
        }
        function Message(el) { var obj = this, timeCnt = 0, loadFunc; obj.el = el; obj.elem = $(el);
            obj.hide = function() { obj.elem.addClass("hide"); return obj }; obj.show = function(flag) { if (flag == null || flag) obj.elem.removeClass("hide"); else obj.hide(); return obj };
            obj.clear = function() { obj.elem.find("*").remove(); obj.hide(); return obj };
            obj.message = function(arr, cls) { $.each(arr, function(i,o) { var m = o; if ($.type(o) === "object") { m = (o.desc || o.messageDesc) +" [code="+ (o.code || o.messageCode) +"]" }; obj.elem.append('<div class="'+ cls +'">'+ m +'<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button></div>') }); window.scrollTo(0, 0); return obj };
            obj.error = function(arr) { return obj.message(arr, "alert alert-danger") };
            obj.warn = function(arr) { return obj.message(arr, "alert alert-warning") };
            obj.success = function(arr) { return obj.message(arr, "alert alert-success") };
            obj.valid = function(jqXHR, textStatus, errorThrow){ var res = jqXHR; obj.stopLoad(); if (jqXHR && jqXHR.responseJSON) res = jqXHR.responseJSON; if (res) { var isNoData = false; if (res.statusCode && res.statusCode != "0") { obj.warn(res && $.type(res.warningList) === 'array' ? res.warningList : []).error(res && $.type(res.errorList) === 'array' ? res.errorList : ["An error occurred on the server side but there is no error message returned."]).show(); return false } if (res.data && res.statusCode == '0' && res.data.length < 1) isNoData = true; if ($.type(res._embedded) === 'object' && $.map(res._embedded,function(v,k){return v}).length < 1) isNoData = true; if (isNoData) { return false } return true } obj.error(["An error occurred on the server side but there is no error message returned."]).show(); return false }
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
        function BootstrapTable(el) { var obj = this, checkEvt = function(e){ console.log(e) }, uncheckEvt = checkEvt; obj.el = el; obj.elem = $(el);
            obj.clear = function() { obj.elem.bootstrapTable("removeAll"); return obj }
            obj.showLoad = function() { this.elem.bootstrapTable("showLoading"); return this }; obj.hideLoad = function() { this.elem.bootstrapTable("hideLoading"); return this };
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
            obj.remove = function(ind){ obj.elem.bootstrapTable('remove', { field  : 'id', values : [ind] }); return this; }
        }
        function Modal(el) {
            this.el = el;
            this.elem = $(el);
            this.hide = function() { this.elem.modal("hide") };
            this.show = function() { this.elem.modal("show") };
        }
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
        (function(){ $(window["setup"]) })();
        // TO SUPPORT IDE OUTLINE.
        self.message = new Message("#message");
        //self.inputAgentType = new DropDown("#inputAgentType").data([{ key: "AGENT000", value: "การไฟฟ้านครหลวง" }, { key: "", value: "การไฟฟ้าส่วนภูมิภาค" }, { key: "", value: "การประปานครหลวง" }, { key: "", value: "การประปาส่วนภูมิภาค" }]);
        self.inputAgentType = new Input("#inputAgentType");
        self.buttonSearchBillNo = new Button("#buttonSearchBillNo");
        self.buttonAddBillingList = new Button("#buttonAddBillingList");
        self.buttonProcessPayment = new Button("#buttonProcessPayment");
        self.buttonCancelPayment = new Button("#buttonCancelPayment");

        self.inputProdBarcode1 = new Input("#inputProdBarcode1");
        /*self.inputProdBarcode2 = new Input("#inputProdBarcode2");*/

        self.inputAgentTaxId = new Input("#inputAgentTaxId");

        self.inputServiceType = new Input("#inputServiceType");
        self.inputServiceName = new Input("#inputServiceName");
        /*self.inputServiceNo = new Input("#inputServiceNo");
         self.inputAccountNo = new Input("#inputAccountNo");*/
        self.inputInvoiceNo = new Input("#inputInvoiceNo");
        self.inputRef1No = new Input ("#inputRef1No");
        self.inputRef2No = new Input ("#inputRef2No")
        self.inputDueDate = new Input("#inputDueDate");
        self.inputServiceFee = new NumberInput("#inputServiceFee");
        self.inputServiceUnit = new Input("#inputServiceUnit");
        self.inputServiceAmount = new NumberInput("#inputServiceAmount");

        /*self.inputCustomerBillNo = new Input("#inputCustomerBillNo");*/
        self.inputCustomerName = new Input("#inputCustomerName");
        self.inputCustomerTaxNo = new Input("#inputCustomerTaxNo");
        self.inputCustomerBranch = new Input("#inputCustomerBranch");
        //self.inputCustomerType = new DropDown("#inputCustomerType").data([{ key: "INDIVIDUAL", value: "INDIVIDUAL" }, { key: "ORGANIZATION", value: "ORGANIZATION" }]);
        self.inputCustomerAddress = new Input("#inputCustomerAddress");

        self.inputCharge = new NumberInput("#inputCharge");
        self.inputVat = new NumberInput("#inputVat");
        self.inputFee = new NumberInput("#inputFee");
        self.inputTotalCharge = new NumberInput("#inputTotalCharge");

        self.panelReceiptDetails = new Panel("#panelReceiptDetails");
        self.panelSummaryInfo = new Panel("#panelSummaryInfo");

        self.tableBillingList = new BootstrapTable("#tableBillingList");
        return this;
    })(jQuery);
    loadAgent();
    function loadAgent(){
        var queryString = self.utils.queryString();
        $.get("../loadMenuListPaymentAgent.json", function (res) {
            var agents = res.data;
            console.log("agent >>>>>>>>>>>>>>>>> ")
            console.log(agents)
            window.session("agentDatas", agents);
            for (var i = 0; i < agents.length; i++) {
                if(queryString.id == agents[i].id){
                    view.inputAgentType.val(agents[i].name);
                    agentData2 = agents[i];
                    window.session("agentData", agents[i]);
                    break;
                }
            }
        })
    }

    $(document).ready(function() {
        $("#inputProdBarcode1").keydown( function () {
            buttonSearchBillNoClickEvent();
        })
         $("#inputProdBarcode1").click( function () {
            buttonSearchBillNoClickEvent();
        })
        $("#inputProdBarcode1").mousedown( function () {
            buttonSearchBillNoClickEvent();
        })
    })

    function setup() {
        /*var isNew = view.utils.queryString()["new"];
         if (isNew) {
         view.session("billingList", []);
         view.buttonProcessPayment.disable(true);
         }*/
        view.session("billingList", []);
        view.buttonProcessPayment.disable(true);

        var category = 'vat.type';
        console.log("category >>");
        console.log(category);
        console.log("<<category");
        $.ajax({
            type: 'GET',
            url: '../findVatRateByCategory.json?category='+category,
            success: function (data) {
                console.log("xxxxxxxxxxx>");
                console.log(data);
                console.log("<xxxxxxxxxxx");
                console.log("MMM>");
                console.log(data.enumDTO.data[0].mapCode2);
                console.log("<xxx");
                var vatRate = '7.0';
                if(data.enumDTO){
                    console.log("if <<<<");
                    vatRate = parseFloat(data.enumDTO.data[0].mapCode2);
                }else{
                    console.log("else <<<<");
                    vatRate = parseFloat(data.data[0].mapCode2);
                }
                view.session("vatRate", vatRate);
            }
        })
    }
    function buttonSearchBillNoClickEvent() {
        if(view.inputProdBarcode1.val().trim() == '') return;
        var panelActived = "0";
        var billBars = view.inputProdBarcode1.val();
        var serviceNo = '', accountNo = '', dueDate = '', serviceFee = 0, serviceUnit = 0, serviceAmount = 0, customerTaxNo = '', serviceType = '', serviceName = '';

//         var agentData = window.session("agentData");
		var agentData  ="";
      	    agentData  = agentData2;
        var agentDatas = window.session("agentDatas");
        var taxIdPos = "";
        var taxId = "";
        var chkTaxid = '',chkTaxid2 = false;
        if(agentData == null || agentData == ""){
        	taxId =  billBars.substring(1,14);
        }else{
		        taxIdPos = agentData.taxIdPos;
		        if (taxIdPos) {
		        	taxIdPos = taxIdPos.split("-");
		        	taxId = billBars.substring(parseInt(taxIdPos[0]-1), parseInt(taxIdPos[1]));
		        }
        }
        if(chkTaxid != taxId){
//         if(agentData.taxId != taxId){
//             for (key in agentDatas){
//                 obj = agentDatas[key];
//                 if(taxId == obj.taxId){
//                     view.inputAgentType.val(obj.name);
//                     view.session("agentData", obj);
//                     break;
//                 }
//             }
//         }
        	for (var i = 0; i < agentDatas.length; i++) {
                if(agentDatas[i].taxId == taxId){
                	agentData = "";
                	agentData = agentDatas[i];
                	view.session("agentData",  agentDatas[i]);
                	chkTaxid2 = true;
                    break;
                }
            }
        
        }
        
        var  amountPos = agentData.amountPos,ref1='', ref2='', invoiceNo='', dueDate='', amount='';
        ref1 = billBars.substring(parseInt(agentData.ref1St-1), parseInt(agentData.ref1End));
        ref2 = billBars.substring(parseInt(agentData.ref2St-1), parseInt(agentData.ref2End));
        invoiceNo = billBars.substring(parseInt(agentData.invoiceNoSt-1), parseInt(agentData.invoiceNoEnd));
        dueDate = billBars.substring(parseInt(agentData.dueDateSt-1), parseInt(agentData.dueDateEnd));
        amount = billBars.substring(parseInt(agentData.amountSt-1), parseInt(agentData.amountEnd));
//         if (amountPos!==null){
//             amountPos = amountPos.split("-");
//             amount = billBars.substring(parseInt(amountPos[0]-1), billBars.length);
// 	     }
        panelActived = "1";
        ref1No = ref1;
        ref2No = ref2;
        var dateSplit = dueDate;

        if(dueDate !== ""){
        	dueDate = dateSplit.substring(0, 2) + '/' + dateSplit.substring(2, 4) + '/' + dateSplit.substring(4, 6);
        }
        serviceType = agentData.code;
        serviceName = "รับชำระใบแจ้งค่าบริการ "+invoiceNo;
        serviceFee = 0.00;
        serviceAmount = amount / 100.0;
        agentTaxId = taxId;

        if(chkTaxid2 == true){
	        if(panelActived == "1") {
	            view.panelSummaryInfo.hide(1);
	            view.panelReceiptDetails.slideDown(500);
	            view.inputServiceType.val(serviceType);
	            view.inputServiceName.val(serviceName);
	            view.inputInvoiceNo.val(invoiceNo);
	            view.inputRef1No.val(ref1No);
	            view.inputRef2No.val(ref2No);
	            view.inputDueDate.val(dueDate);
	            view.inputServiceFee.val(serviceFee);
	            view.inputServiceAmount.val(serviceAmount);
	            $("#panelReceiptDetails").show();
	            $("#inputAgentType").val(agentData.companyName);
	            view.inputAgentTaxId.val(agentTaxId);
	        } else {
	            view.inputProdBarcode1.val("");
	            view.panelSummaryInfo.hide(1);
	            view.panelReceiptDetails.hide(1);
	        }
        }else{
        	$("#inputAgentType").val("ไม่พบข้อมูลตัวแทนรับชำระ");
			$("#panelReceiptDetails").hide();
        }
    }
    function buttonAddBillingListClickEvent() {
        view.panelSummaryInfo.show(1);
        var vatRate = parseInt(view.session("vatRate"));
        var serviceVatAmount = divide(multiply(view.inputServiceAmount.val() , vatRate, 2), (vatRate + 100), 2);
        view.tableBillingList.insert(
            {"id": view.tableBillingList.data().length,
                "serviceNo": view.inputInvoiceNo.val(),
                "serviceName": "รับชำระใบแจ้งค่าบริการ "+view.inputInvoiceNo.val(),
                "ref1": view.inputRef1No.val(),
                "ref2": view.inputRef2No.val(),
                "dueDate": view.inputDueDate.val(),
                "serviceFee": view.inputServiceFee.val(),
                "serviceMoreData": 1,
                "serviceAmount": view.inputServiceAmount.val(),
                "serviceVatAmount": serviceVatAmount,
                "feeFlg": null
            });
        view.buttonProcessPayment.enable();
        calculate();
    }
    function removeFromServiceList(id) {
        view.tableBillingList.remove(id);
        calculate();
    }
    function calculate() {
        var vatRate = parseInt(view.session("vatRate"));
        var data = view.tableBillingList.data(), amount = 0, charge = 0, vat = 0, fee = 0, totalCharge = 0;
        for (var i = 0, m = data.length; i < m; i++) {
            amount += ((data[i].serviceAmount));
            fee += ((data[i].serviceFee));
        }
        vat = divide(multiply(amount, vatRate, 2) , (vatRate + 100), 2);
        feeVat = divide(multiply(fee , vatRate, 2) , (vatRate + 100), 2);
        feeCharge = fee - feeVat;
        charge = amount - vat;
        totalCharge = amount + fee;
        view.inputCharge.val(charge);
        view.inputVat.val(vat);
        view.inputFee.val(fee);
        view.inputTotalCharge.val(totalCharge);
    }
    function buttonProcessPaymentClickEvent() {
        buildBillingList();
        location.href = "pay-agent_2.jsp"
    }
    function buttonCancelPaymentClickEvent() {
        var agentData = view.session("agentData");
        var params = "id="+agentData.id;
        location.href = "pay-agent_1.jsp?"+params;
    }
    function buildBillingList() {
        var billingList = view.session("billingList");
        var agentData = view.session("agentData");
        var vatRate = parseInt(view.session("vatRate"));
        var serviceList = [];
        $.each(view.tableBillingList.data(), function(i,o){
            serviceList.push(o);
        });
        console.log(billingList);

        var rctCustType;
        if(view.inputCustomerTaxNo.val() == "") rctCustType = "INDIVIDUAL";
        else rctCustType = "ORGANIZATION";
        billingList.push(
            {"rctCustNo": "",
                "rctCustType": rctCustType,
                "rctCustName": view.inputCustomerName.val(),
                "rctTaxId": view.inputCustomerTaxNo.val(),
                "rctBranch": view.inputCustomerBranch.val(),
                "rctAddr": view.inputCustomerAddress.val(),
                "remark": '',
                "split": false,
                "vatAmount": view.inputVat.val(),
                "feeAmount": view.inputFee.val(),
                "agentAddressCode": agentData.code,
                "serviceList" : serviceList,
                "agentTaxNo" : agentData.taxId,
                "feesIncome": view.inputServiceFee.val(),
                "ref1": agentData.code
            });

        if(view.inputFee.val() > 0){
            var feeServiceList = [];
            feeServiceList.push({
                "id": 0,
                "serviceNo": view.inputInvoiceNo.val(),
                "serviceName": "ค่าธรรมเนียมการรับชำระ ใบแจ้งค่าบริการ  "+view.inputInvoiceNo.val(),
                "ref1": "",
                "ref2": "",
                "dueDate": "",
                "serviceFee": 0,
                "serviceMoreData": 1,
                "serviceAmount": view.inputFee.val(),
                "serviceVatAmount": divide(multiply(view.inputFee.val() , vatRate, 2) , (vatRate + 100), 2),
                "feeFlg": "FEE"
            });
            billingList.push(
                { "rctCustNo": "",
                    "rctCustType": rctCustType,
                    "rctCustName": view.inputCustomerName.val(),
                    "rctTaxId": view.inputCustomerTaxNo.val(),
                    "rctBranch": view.inputCustomerBranch.val(),
                    "rctAddr": view.inputCustomerAddress.val(),
                    "remark": "",
                    "split": false,
                    "vatAmount": 0,
                    "feeAmount": 0,
                    "agentAddressCode": agentData.code,
                    "serviceList" : feeServiceList,
                    "agentTaxNo" : agentData.taxId,
                    "feesIncome": "",
                    "ref1": agentData.code
                });
        }
        view.session("billingList", billingList);
    }

</script>
</html>
