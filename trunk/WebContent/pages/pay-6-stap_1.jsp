<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
        <div class="col-md-12 tab-modefile">
            <ol class="breadcrumb">
                <li><i>รับชำระค่าบริการคงสิทธิเลขหมายโทรศัพท์เคลื่อนที่</i></li>
                <li class="active">ค้นหาข้อมูล Order</li>
                <li>เลือกวิธีการชำระ</li>
                <li >ผลการชำระ</li>
            </ol>
            <div id="message"></div>
            <ul class="nav nav-tabs" role="tablist">
                <li role="presentation" class="active">
                    <a href="#tab_cus" aria-controls="tab_cus" role="tab" data-toggle="tab"><span class="glyphicon glyphicon-search"></span> ค้นหาข้อมูล Order</a>
                </li>
            </ul>
            <div class="tab-content">
                <div role="tabpanel" class="tab-pane active" id="tab_cus">
                    <div class="panel panel-default panal-radius">
                        <div class="panel-body">
                            <div class="form-horizontal">
                                <div class="form-group">
                                    <label class="control-label col-sm-2" >Order ID :</label>
                                    <div class="col-sm-3"><input class="form-control" id="inputSearchOrderId"></div>
                                    <label class="control-label col-sm-1" >MDN :</label>
                                    <div class="col-sm-3"><input class="form-control" id="inputSearchMDN"></div>                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-2" >ชื่อลูกค้า: </label>
                                    <div class="col-sm-3"><input class="form-control" id="inputSearchName"></div>
                                    <label class="control-label col-sm-1" >นามสกุล: </label>
                                    <div class="col-sm-3"><input class="form-control" id="inputSearchSurname"></div>
                                    <div class="col-sm-3"><a class="btn btn-primary" id="buttonSearchMDN"><span class="glyphicon glyphicon-ok-circle"></span>  ค้นหาข้อมูล</a></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="row hide panel-body text-right" id="panelNavigate">
        <a class="btn btn-link" id="buttonGoToPaymentProcess"><span class="glyphicon glyphicon-edit"></span> ดำเนินการรับชำระ</a>
    </div>
    <div class="row hide" id="panelDetails">
        <div class="col-md-12 tab-modefile">
            <ul class="nav nav-tabs" role="tablist" id="tabDetails">
                <li role="presentation" class="active"><a href="#tab_1" aria-controls="tab_1" role="tab" data-toggle="tab"><span class="glyphicon glyphicon-briefcase"></span> รายละเอียด Order</a></li>
                <!--<li role="presentation"><a href="#tab_2" aria-controls="tab_2" role="tab" data-toggle="tab" onClick="copyCustomerProfile()"><span class="glyphicon glyphicon-tasks"></span> รายละเอียดใบเสร็จรับเงิน</a></li>-->
            </ul>
            <div class="panel panel-default panal-radius">
                <div class="panel-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="tab-content">
                                <div role="tabpanel" class="tab-pane active" id="tab_1">
                                    <div class="form-horizontal">
                                        <div class="form-group">
                                            <label class="control-label col-sm-2">Order ID :</label>
                                            <div class="col-sm-2">
                                                <input class="form-control" disabled="disabled" id="inputCustomerOrderId">
                                                <span id="txtRefOrderId" style=" display:none;"></span>
                                                <input id="inputCustomerOrderIdRef" hidden>
                                                <input id="agentAddressCode" hidden>
                                            </div>
                                            <div class="checkbox col-sm-1" ><label><input type="checkbox" id="repeatOrder" disabled> Repeat Order</label></div>
                                            <div class="col-sm-2">
                                                <div class="checkbox"><label><input type="checkbox" id="checkEditCustomerData" name="checkEditCustomerData" onclick="allowEditCustomerData()">&nbsp;แก้ไขข้อมูลลูกค้า</label></div>
                                            </div>
                                        </div>
                                         <div class="form-group" id="repData">
                                            <label class="control-label col-sm-2">Repeat Order ID :</label>
                                            <div class="col-sm-2" >
                                              <select class="form-control" id="inputRef" > </select>
                                              <input type="hidden" id = "repOrder"/>
                                             </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-2">ชื่อลูกค้า :</label>
                                            <div class="col-sm-2"><input class="form-control" disabled="disabled" id="inputCustomerName"></div>
                                            <label class="control-label col-sm-2">กลุ่มลูกค้า :</label>
                                            <div class="col-sm-2"><select class="form-control" id="inputCustomerSegment" disabled="disabled"></select></div>
                                            <input type="hidden" id="customerType">
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-2">Tax Id :</label>
                                            <div class="col-sm-2"><input class="form-control" disabled="disabled" id="inputCustomerTaxId" maxlength="13"></div>
                                            <label class="control-label col-sm-2">สาขาที่ :</label>
                                            <div class="col-sm-2"><input class="form-control" disabled="disabled" id="inputCustomerBranch" maxlength="5"></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-2">ที่อยู่ :</label>
                                            <div class="col-sm-6"><textarea class="form-control" disabled="disabled" id="inputCustomerAddress"></textarea></div>
                                        </div>
                                        <%--<div class="form-group">
                                            <label class="control-label col-sm-2"></label>
                                            <div class="col-sm-6"><input class="form-control" disabled="disabled" id="inputCustomerAddress2"></div>
                                        </div>--%>
                                    </div>
                                    <br/>
                                    <div class="col-md-10">
                                        <ul class="nav nav-tabs " role="tablist">
                                            <li role="presentation" class="active"><a href="#tab1" aria-controls="tab1" role="tab" data-toggle="tab"> รายการหมายเลขโทรศัพท์</a></li>
                                        </ul>
                                        <div class="panel panel-default panal-radius">
                                            <div class="panel-body">
                                                <div class="row">
                                                    <div class="col-md-12">
                                                        <table class="table" id="mobileNumberList">
                                                            <thead>
                                                            <tr>
                                                                <th data-valign="middle" data-running-no="true">#</th>
                                                                <th data-valign="middle">MDN</th>
                                                                <th data-valign="middle">หมายเลข ICCID</th>
                                                                <th data-valign="middle" data-align="right" data-number-format="true" class="text-right">จำนวนเงินรวม</th>
                                                                <th data-valign="middle" data-modify-button="false"></th>
                                                                <th data-valign="middle" data-remove-button="false"></th>
                                                            </tr>
                                                            </thead>
                                                        </table>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                </div>

                                <div role="tabpanel" class="tab-pane" id="tab_2">
                                    <div class="form-horizontal">
                                        <div class="form-group">
                                            <label class="control-label col-sm-2" >Order ID :</label>
                                            <div class="col-sm-2">
                                                <input class="form-control" disabled id="inputReceiptCustNo">
                                            </div>
                                            <div class="col-sm-4">
                                                <%--<div class="checkbox"><label>&nbsp;&nbsp;&nbsp;<input type="checkbox" id="checkEditCustomerData" name="checkEditCustomerData" onclick="allowEditCustomerData()">&nbsp;แก้ไขข้อมูลลูกค้า</label></div>--%>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-2">ชื่อลูกค้า :</label>
                                            <div class="col-sm-2">
                                                <input class="form-control" disabled="disabled" id="inputReceiptCustName" maxlength="100">
                                            </div>
                                            <label class="control-label col-sm-2">กลุ่มผู้ใช้บริการ :</label>
                                            <div class="col-sm-2">
                                                <select class="form-control" id="inputReceiptSegment" disabled="disabled"></select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-2" >Tax Id :</label>
                                            <div class="col-sm-2">
                                                <input class="form-control" id="inputReceiptTaxId" disabled="disabled" maxlength="13">
                                            </div>
                                            <label class="control-label col-sm-2" >สาขาที่ :</label>
                                            <div class="col-sm-2">
                                                <input class="form-control" id="inputReceiptBranch" disabled="disabled" maxlength="5" align="left">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-2">ที่อยู่ :</label>
                                            <div class="col-sm-6">
                                                <input class="form-control" id="inputReceiptAddress" name="inputReceiptAddress" disabled="disabled" maxlength="50">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-2"></label>
                                            <div class="col-sm-6">
                                                <input class="form-control" id="inputReceiptAddress2" name="inputReceiptAddress2" disabled="disabled" maxlength="50">
                                            </div>
                                            <div class="col-sm-4">
                                                <div class="checkbox">
                                                    <label>
                                                        &nbsp;&nbsp;&nbsp;<input type="checkbox" id="isFullReceipt" name="isFullReceipt" checked >&nbsp;ใบเสร็จรับเงิน/ใบกำกับภาษีเต็มรูปแบบ
                                                    </label>
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
        </div>
    </div>
    <div class="row hide" id="panelSummary">
        <div class="col-md-12">
            <div class="panel panel-default">
                <div class="panel-heading"><span class="glyphicon glyphicon-shopping-cart"></span> สรุปยอดเงินที่ต้องชำระ</div>
                <div class="panel-body">
                    <div class="form-horizontal">
                        <div class="form-group">
                            <label class="control-label col-sm-2">ข้อความเพิ่มเติมในใบเสร็จ :</label>
                            <div class="col-sm-5"><input id="inputAdditionalRemark" class="form-control"></div>
                            <label class="control-label col-sm-2 col-sm-offset-1">ยอดเงินก่อนภาษีมูลค่าเพิ่ม  :</label>
                            <div class="col-sm-2">
                                <input class="form-control text-right" id="inputAmount" disabled="disabled">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-10">ภาษีมูลค่าเพิ่ม  :</label>
                            <div class="col-sm-2">
                                <input class="form-control text-right" id="inputVat" disabled="disabled">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-10">ยอดเงินรวมภาษีมูลค่าเพิ่ม  :</label>
                            <div class="col-sm-2">
                                <input class="form-control text-right" id="inputTotalCharge" disabled="disabled">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-10">ยอดเงินทั้งสิ้น :</label>
                            <div class="col-sm-2">
                                <input class="form-control text-right" id="inputBalance" disabled="disabled">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-10">ยอดเงินชำระ :</label>
                            <div class="col-sm-2">
                                <input class="form-control text-right" id="inputReceived" disabled="disabled">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="modal fade" role="dialog" id="multiCustomSearch">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><span class="glyphicon glyphicon-user"></span> ค้นหาลูกค้า</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-12">
                        <table id="resultList" class="table table-hover" data-toggle="table" data-pagination="true" data-page-size="5" data-page-list="[5, 10, 20, 50, 100, 200]">
                            <thead>
                            <tr>
                                <th data-formatter="runningFormatter">#</th>
                                <th data-field="orderId">Order ID</th>
                                <th data-field="taxId">Tax Id</th>
                                <th data-field="name">Name</th>
                            </tr>
                            </thead>
                        </table>
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
        window.getSync = function(url, params, success, msgDialog, preCheck){ $.ajax({ "url": url, "type": "GET", "data": params, "async": false, "error": (msgDialog || { "valid": function(jqXHR, textStatus, errorThrow){ console.log(jqXHR); console.log("textStatus: "+ textStatus); console.log("errorThrow:"); console.log(errorThrow) } }).valid, "success": function(res){ var isNotJson = res.constructor == String; console.log(res); (preCheck || function(o){})(res); if (isNotJson) res = { "statusCode": "99", "errorList": ["No matching data found."] }; if (msgDialog && !msgDialog.valid(res)) { return; } success(res) } }) }
        window.post = function(url, params, success, msgDialog, preCheck){ $.ajax({ "url": url, "type": "POST", "data": params, "error": msgDialog.valid, "success": function(res){ (preCheck || function(o){ console.log(o) })(res); if (msgDialog && !msgDialog.valid(res)) { return; } success(res) } }) }
        window.padLeft = function(obj, size, ch){ return padding(obj, size, ch, true) }; window.padRight = function(obj, size, ch){ return padding(obj, size, ch, false) }; function padding(obj, size, ch, isLeft) { var str = ""; if (!obj) str = ""; else if($.type(obj) == "string") str = obj; else if ($.type(obj) == "number") str = String(obj); if (str.length >= size) return str; var padded = Array(Math.max(size - str.length + 1, 0)).join(ch); return (isLeft ? padded : "") + str + (isLeft ? "" : padded) }
        window.stripToNumber = function(str) { return $.type(str) === "string" && $.isNumeric(str = str.replace(/[,A-Za-z\(\)\[\]\{\}]/g, "")) ? parseFloat(str, 10) : 0 }
        window.toDateString = function(ddMMyyyy){ return ddMMyyyy.replace(/(\d{2}).(\d{2}).(\d{4})/g, "$2/$1/$3") }
        window.runningFormatter = function(val, row, ind) { return ind + 1 }
        window.numberFormatter = function(val, row, ind) { return !$.isNumeric(stripToNumber(val)) ? "0.00" : parseFloat(val, 10).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,') }
        window.stringInputFormatter = function(val, row, ind) { return '<input value="'+ $.trim(val) +'" maxLength="40" class="form-control">' }
        window.numberInputFormatter = function(val, row, ind) { return '<input value="'+ self.utils.numberFormat(parseFloat(val || "0.00", 10)) +'" maxLength="10" style="width:100px" class="form-control text-right" onkeypress="return $.isNumeric(String.fromCharCode(event.which)) || (event.which == 46 && this.value.indexOf(\'.\') == -1)" onfocus="(function(o){ o.value = o.value.replace(/[,]/g, \'\') })(this)" onblur="(function(o){ o.value = parseFloat(o.value, 10).toFixed(2).replace(/(\\d)(?=(\\d{3})+\\.)/g, \'$1,\'); })(this)">' }
        window.modifyButtonFormatter = function(val, row, ind) { return '<a class="btn btn-link modList" onclick=\'(function(elem, row, ind){ var table = $("#"+ $(elem).closest("table")[0].id); window[table[0].id +"ModifyEvent"](table, row, ind) })(this, '+ JSON.stringify(row) +', '+ ind +')\'><span class="glyphicon glyphicon-pencil"></span></a>' }
        window.removeButtonFormatter = function(val, row, ind) { return '<a class="btn btn-link delList" onclick=\'(function(elem, row, ind){ $("#"+ $(elem).closest("table")[0].id).bootstrapTable("remove", { field: "index", values: [ind] }) })(this, '+ JSON.stringify(row) +', '+ ind +')\'><span class="glyphicon glyphicon-trash"></span></a>' }
        function Button(el, url, inp, validate) { var obj = this, badge; obj.el = el; obj.elem = $(el);
            obj.hide = function() { this.elem.addClass("hide"); return this }; obj.show = function() { this.elem.removeClass("hide"); return this };
            obj.hideLoad = function(){ obj.elem.button("reset"); return this }; obj.showLoad = function(){ obj.elem.button("loading"); return this };
            obj.disable = function(flag) { if (flag == null || flag) this.elem.addClass("disabled").attr("disabled", true); else this.elem.removeClass("disabled").attr("disabled", false); return this }; obj.enable = function() { obj.disable(false); return this };
            obj.badge = function(val) { if (badge) badge.text(val) }; if ((badge = this.elem.next()).length == 0) badge = null;
            obj.elem.click(window[el.substring(1) +"ClickEvent"]);
        }
        function Panel(el) { var obj = this, dura = 500; obj.el = el; obj.elem = $(el);
            obj.hide = function(ms) { if (isHidden()) return this; obj.elem.hide(ms || dura); return this }; obj.show = function(ms) { if (!isHidden()) return this; if (!ms || !$.isNumeric(ms)) ms = dura; if (ms >= 0) obj.elem.css("display", "none").removeClass("hide").removeClass("hidden").show(ms); else obj.hide(Math.abs(ms)); return this }
            obj.slideDown = function(ms){ if (isHidden()) { obj.elem.css("display", "none").removeClass("hide").removeClass("hidden").slideDown(ms || dura) } return this }; obj.slideUp = function(ms){ if (!isHidden()) { obj.elem.slideUp(ms || dura) } return this }
            function isHidden() { return obj.elem.css("display") === "none" || obj.elem.hasClass("hide") || obj.elem.hasClass("hidden") }
        }
        function Message(el) { var obj = this, timeCnt = 0, loadFunc; obj.el = el; obj.elem = $(el);
            obj.hide = function() { obj.elem.addClass("hide"); return obj };
            obj.show = function(flag) { if (flag == null || flag) obj.elem.removeClass("hide"); else obj.hide(); return obj };
            obj.clear = function() { obj.elem.find("*").remove(); obj.hide(); return obj };
            obj.message = function(arr, cls) {
                $.each(arr, function(i,o) { var m = o; if ($.type(o) === "object") {
                    m = "ไม่พบข้อมูล"//(o.desc || o.messageDesc) +" [code="+ (o.code || o.messageCode) +"]"
                }; obj.elem.append('<div class="'+ cls +'">'+ m +'<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button></div>') }); return obj
            };

            obj.error = function(arr) { return obj.message(arr, "alert alert-danger") };
            obj.warn = function(arr) { return obj.message(arr, "alert alert-warning") };
            obj.success = function(arr) { return obj.message(arr, "alert alert-success") };
            obj.valid = function(jqXHR, textStatus, errorThrow){ var res = jqXHR; obj.stopLoad(); if (jqXHR && jqXHR.responseJSON) res = jqXHR.responseJSON; if (res) { var isNoData = false; if (res.statusCode && res.statusCode != "0") { obj.warn(res && $.type(res.warningList) === 'array' ? res.warningList : []).error(res && $.type(res.errorList) === 'array' ? res.errorList : ["An error occurred on the server side but there is no error message returned."]).show(); return false } if (res.data && res.statusCode == '0' && res.data.length < 1) isNoData = true; if ($.type(res._embedded) === 'object' && $.map(res._embedded,function(v,k){return v}).length < 1) isNoData = true; if (isNoData) { return false } return true } obj.error(["An error occurred on the server side but there is no error message returned."]).show(); return false }
            obj.hideLoad = function(){ obj.stopLoad().clear(); return this }; obj.stopLoad = function(){ clearInterval(loadFunc); return this }
            obj.showLoad = function(msg){ obj.elem.append('<div id="'+ obj.el +'-loading" class="alert alert-warning">'+ bind(msg, 0) +'</div>'); timeCnt = 0; var elem = document.getElementById(obj.el +"-loading"); loadFunc = setInterval(function(){ elem.innerHTML = bind(msg, ++timeCnt) }, 1000); obj.show(); return this }
            function bind(msg, timeCnt) { return msg.replace(/\{timeCnt\}/g, timeCnt) }
        }
        function Input(el, maxLen, propPos) { var obj = this; obj.el = el; obj.elem = $(el);
            obj.error = function(flag) { if (arguments.length == 0 || flag) obj.elem.parent().addClass("has-error"); else obj.elem.parent().removeClass("has-error"); return this }
            obj.clear = function() { obj.val(""); return this }; obj.isEmpty = function() { return $.trim(obj.val()) === "" }; obj.isNumeric = function() { return $.isNumeric(obj.val()) }
            obj.nextFocus = function(nextElem) { if (nextElem && nextElem.constructor.name == "Input") { obj.elem.keyup(function(e){ var key = (e.which || e.keyCode || e.charCode || 0); if (key == 13) nextElem.elem.focus(); return true }) } return this };
            obj.click = function(func) { obj.elem.click(func); return this }
            obj.readOnly = function(flag) { obj.elem.attr("readonly", (flag == null ? true : flag)); return this }
            obj.disable = function(flag) { obj.elem.attr("disabled", (flag == null ? true : flag)); return this }
            obj.enable = function() { obj.disable(false); return this }
            obj.val = function() { if (arguments.length == 1) { this.elem.val(arguments[0]) } return $.trim(this.elem.val()) }
            obj.get = function(propName) { if ($.type(propPos[propName]) !== "array" || propPos[propName].length !== 2) return ""; return obj.val().substring(propPos[propName][0], propPos[propName][1]) }
            obj.elem.keyup(function(e){ var func = (window[el.substring(1) +"KeyUpEvent"] || function(){}); func((e.which || e.keyCode || e.charCode || 0), obj.elem) }); if ($.isNumeric(maxLen)) { obj.elem.attr("maxLength", maxLen) }
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
        function DropDown(el, url) { var obj = this, data = [{ key: "", value: "", text: "กรุณาเลือก" }]; obj.el = el; obj.elem = $(el);
            obj.data = function(array) { if ($.type(array) == "array") { setup(data = array); return this } return data; };
            obj.dataV2 = function(array) { if ($.type(array) == "array") { setupV2(data = array); return this } return data; };
      		obj.init = function(url) { if (url) $.get(url, function(res) { console.log("url : "+url); console.log("url : "+JSON.stringify(res)); setup(data = res.data) }); else setup(data); return this };                

            //obj.init = function(url) { if (url) $.get(url, function(res) { setup(data = res.data) }); else setup(data); return this };
            obj.initV2 = function(url) { if (url) $.get(url, function(res) { setupV2(data = res.data) }); else setupV2(data); return this };
            obj.index = function() { if (arguments.length == 1) { var maxLen = data.length, index = arguments[0]; obj.elem.find("option").each(function(i, opt){ opt.removeAttribute("selected") }).eq(index < 0 ? maxLen + index : index).attr("selected", true); return this } return $.inArray("selected", obj.elem.find("option").map(function(i, opt){ return opt.selected ? "selected" : "no-selected" })) }
            obj.disable = function(){ obj.elem.attr("disabled", (arguments.length != 1 ? true : arguments[0])); return obj }; obj.enable = function(){ obj.disable(false); return obj };
            obj.selected = function(){ return data[obj.index()]; }; obj.val = function() { return obj.elem.val(); }; obj.key = function(){ if (arguments.length == 0) { var key = obj.elem.find("option:selected").data("key"); if (typeof key === "string" && key === "undefined") { var item = data[obj.index()]; key = item && item.key ? item.key : item.id } return key } var key = arguments[0]; for (var i = 0, m = data.length; i < m; i++) { var item = data[i]; if (item.key && item.key == key) { obj.index(i); break; } if (item.id && item.id == key) { obj.index(i); break; } } return this }
            obj.clear = function() { obj.val(""); return this }; obj.isEmpty = function() { return $.trim(obj.val()) === "" }; obj.isNumeric = function() { return $.isNumeric(obj.val()) }
            function setup(array) { obj.elem.find("*").remove(); $.each(array,function(i,o){ obj.elem.append('<option data-index="'+ i +'" data-key="'+ o.key +'" value="'+ o.value +'">'+ (o.text || o.value) +'</option>') }); }
            function setupV2(array) { obj.elem.find("*").remove(); $.each(array,function(i,o){ obj.elem.append('<option data-index="'+ i +'" data-key="'+ o.id +'" value="'+ o.id +'">'+ (o.text || o.value) +'</option>') }); }
            data = obj.elem.change(window[el.substring(1) +"ChangeEvent"]).find("option").map(function(i, opt){ return { key: opt.getAttribute("data-key"), value: opt.value, text: opt.textContent } });
        }
        function Tab(el, array) { var obj = this, pane = $(el), index = 0; obj.el = el.substring(1) +"-tab"; obj.elem = pane.append('<ul class="nav nav-tabs" role="tablist" id="'+ obj.el +'"></ul>').find("#"+ obj.el); var content = pane.append('<div class="tab-content"></div>').find(".tab-content"), tabs, panels = pane.find('div.panel-body').remove();
            obj.reset = function(){ return obj.index(0) }; obj.index = function() { if (arguments.length == 1) { index = arguments[0]; tabs.removeClass("active").eq(index).find("a").click(); return this } return index }
            obj.show = function(ind) { obj.elem.find("a").eq(ind).tab("show"); return this; }
            $.each(array /* [{ name: "", icon: "" }] */, function(i, o){ obj.elem.append('<li role="presentation"><a role="tab" data-toggle="tab" href="#'+ obj.el + i +'" data-index="'+ i +'"><span class="glyphicon glyphicon-'+ o.icon +'"></span> '+ o.name +'</a></li>') })
            $.each(array, function(i, o){ content.append('<div role="tabpanel" class="tab-pane" id="'+ obj.el + i +'"><div class="panel panel-default panal-radius"><div class="panel-body">'+ (!panels[i] ? "" : panels[i].innerHTML) +'</div></div></div>') });
            (tabs = obj.elem.find("li[role=presentation]")).eq(0).addClass("active"); (panels = content.find('div[role=tabpanel]')).eq(0).addClass("active"); obj.elem.on("show.bs.tab", function(e){ index = $(e.target).data("index");  }); tabs.find("a").click(window[el.substring(1) +"ChangeEvent"]);
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
        function Modal(el) {
    		this.el = el;
    		this.elem = $(el);
    		this.hide = function() { this.elem.modal("hide") };
    		this.show = function() { this.elem.modal("show") };
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
        this.message = new Message("#message");
        this.panelNavigate = new Panel("#panelNavigate");
        this.panelDetails = new Panel("#panelDetails");
        this.panelSummary = new Panel("#panelSummary");
        this.tabDetails = $("#tabDetails a").each(function(i, o){ $(o).data("index", i) }).on("show.bs.tab", tabDetailsChangeEvent);
        this.buttonGoToPaymentProcess = new Button("#buttonGoToPaymentProcess");
        this.buttonSearchMDN = new Button("#buttonSearchMDN");
        this.inputSearchOrderId = new Input("#inputSearchOrderId");
        this.inputSearchMDN = new Input("#inputSearchMDN");
        this.inputSearchName = new Input("#inputSearchName");
        this.inputSearchSurname = new Input("#inputSearchSurname");
        this.inputCustomerOrderId = new Input("#inputCustomerOrderId");
        this.inputCustomerOrderIdRef = new Input("#inputCustomerOrderIdRef");
        this.agentAddressCode = new Input("#agentAddressCode");
        this.inputCustomerName = new Input("#inputCustomerName");
        this.inputCustomerTaxId = new Input("#inputCustomerTaxId");
// 	this.inputCustomerSegment = new Input("#inputCustomerSegment");
        this.inputCustomerSegment = new DropDown("#inputCustomerSegment").initV2("../findCustomerSegmentList.json");
        this.customerType = new Input("#customerType");
        this.inputCustomerBranch = new Input("#inputCustomerBranch");
        this.inputCustomerAddress = new Input("#inputCustomerAddress");
        this.inputCustomerAddress2 = new Input("#inputCustomerAddress2");
        this.inputReceiptCustNo = new Input("#inputReceiptCustNo");
        this.inputReceiptCustName = new Input("#inputReceiptCustName");
// 	this.inputReceiptSegment = new Input("#inputReceiptSegment");
        this.inputReceiptSegment = new DropDown("#inputReceiptSegment");
        this.inputReceiptTaxId = new Input("#inputReceiptTaxId");
        this.inputReceiptBranch = new Input("#inputReceiptBranch");
        this.inputReceiptAddress = new Input("#inputReceiptAddress");
        this.inputReceiptAddress2 = new Input("#inputReceiptAddress2");
        this.inputAmount = new NumberInput("#inputAmount");
        this.inputVat = new NumberInput("#inputVat");
        this.inputTotalCharge = new NumberInput("#inputTotalCharge");
        this.inputBalance = new NumberInput("#inputBalance");
        this.inputReceived = new NumberInput("#inputReceived");
        this.tableMobileNumberList = new Table("#mobileNumberList");
        this.currentVatRate = 0.00;
        this.inputAdditionalRemark = new Input("#inputAdditionalRemark");
        this.multiCustomSearch = new Modal("#multiCustomSearch");
        this.tableResultList = new Table("#resultList");
        this.inputRef = new Input("#inputRef");
      //  this.inputRef = new DropDown("#inputRef").init("../findMobileNumberInfo.json?orderId = "+view.inputSearchOrderId.val()+",mdn= "+ view.inputSearchMDN.val()+", name = " + view.inputSearchName.val()+" ,  surname ="+ view.inputSearchSurname.val()+", repeatOrderFlag="+  repeatOrderFlag);
        this.repOrder = new Input("#repOrder");

        (function(){ $(window["setup"]) })();
        return this;
    })(jQuery);

    function tabDetailsChangeEvent(e) {
    }
    function inputSearchOrderIdKeyUpEvent() {
    }
    function inputSearchMDNKeyUpEvent() {
    }
    function buttonSearchMDNClickEvent() {
        view.message.clear();
        view.tableMobileNumberList.clear();
        view.panelNavigate.hide(1);
        view.panelDetails.hide(1);
        view.panelSummary.hide(1);
        view.inputTotalCharge.val(0);
        view.inputBalance.val(0);
        view.inputReceived.val(0);
        view.inputVat.val(0);
        view.inputAmount.val(0);
        view.tableResultList.clear();
        view.inputRef.clear();

        var hasOrderId = !view.inputSearchOrderId.isEmpty(), hasMDN = !view.inputSearchMDN.isEmpty(), name = !view.inputSearchName.isEmpty(), surname = !view.inputSearchSurname.isEmpty();
        
        var customer;
        if (hasOrderId && view.inputSearchOrderId.val().length < 4) {
            view.message.error(["โปรดระบุ 'Order ID' อย่างน้อย 4 ตัวอักษร"]).show();
            return false;
        } else if (!hasOrderId && hasMDN && view.inputSearchMDN.val().length < 4) {
            view.message.error(["โปรดระบุ 'MDN' อย่างน้อย 4 ตัวอักษร"]).show();
            return false;
        } else if (!hasOrderId && !hasMDN && !name && !surname) {
            view.message.error(["โปรดระบุเงื่อนไขการค้นหา"]).show();
            return false;
        }

        if ($.isEmptyObject(parent.episVatRate)) {
            getSync("../service/enums/search/category", {"category": "enumCat.10"}, function (res) {
                if (res) {
                    $.map(res._embedded.enums, function (v, k) {
                        parent.episVatRate[v.mapCode1] = v;
                    });
                }
            });
        }
        console.log("parent.episVatRate['current'] : " + parent.episVatRate["current"]);
        if (parent.episVatRate["current"]) {
            view.currentVatRate = parseFloat(parent.episVatRate["current"].mapCode2, 10);
        }
        else {
            view.message.error(["ระบบยังไม่ได้ระบุ Vat Rate สำหรับระบบ EPIS กรุณาติดต่อ admin"]).show();
            return false;
        }
        var repeatOrderFlag = "N";
        // alert($("#repeatOrder").val());
        if(document.getElementById("repeatOrder").checked){
            repeatOrderFlag = "Y";
        }
        get("../findMobileNumberInfo.json", {
            "orderId": view.inputSearchOrderId.val(),
            "mdn": view.inputSearchMDN.val(),
            "name": view.inputSearchName.val(),
            "surname": view.inputSearchSurname.val(),
            "repeatOrderFlag": repeatOrderFlag
        }, function (res) {
            console.log('response 01-03-2017'); console.log(res); console.log('response 01-03-2017');
            console.log("findMobileNumberInfo lgh >>> "+res.data.length);
            if(res.statusCode=="1"){
                alert('error');
            }
            
            if (res.data.length > 1) {
        		//console.log("if");
				view.multiCustomSearch.show();
            	/* view.tableCancelReceiptList2.data(res.data); */
            	for (var i = 0, m = res.data.length; i < m; i++) {
                    var custom = res.data[i];
                    view.tableResultList.insert([i+1, custom.orderId, custom.taxId, custom.name]);
                }
            	$("#resultList").on("click", "tr", function(){
            		console.log($(this).index());
            		customer = res.data[$(this).index()]
            		view.multiCustomSearch.hide();
            		view.tableMobileNumberList.clear();
            		pushCustomSearch(customer)
           	    });
           	} else {         	
           		customer = res.data[0]
           		pushCustomSearch(customer);
            }
            
        }, view.message)

    }
    /*ICE ADD FUNCTION
     function round(value){
     return (Math.round(value*10)/10).toFixed(2);
     }end ICE ADD FUNCTION*/
   // var refOrderId = null;
    function pushCustomSearch(customer) {
    	 view.panelNavigate.slideDown();
         view.panelDetails.slideDown();
         view.panelSummary.slideDown();
         //var customer = res.data[0];
         var serviceList = customer.data;
        
         //refOrderId = null;
         //refOrderId = customer.orderIdRef;
         
         if (customer.orderIdRef != 0) {
        	 $('#repeatOrder').prop("checked", true);
        	 $('#inputRef').empty();
              for (var i = 0, m = customer.data.length; i < m; i++) {
            	  if(customer.orderIdRef[i] != 0){
                 	 $('#inputRef').append('<option value="'+customer.orderIdRef+'">' + customer.orderIdRef  +'</option>');
            	  }else{
            	 // console.log("else"); 
            	  }            
            }

             $('#repData').attr("style", "display");            
            // $('#txtRefOrderId').text(" * Reference Order ID : "+customer.orderIdRef);
            // $('#txtRefOrderId').removeAttr("style", "display:none;");
            // $('#txtRefOrderId').attr("style", "color:red; font-size:13px;");
         } else {
             $('#repeatOrder').prop("checked", false);
             //$('#txtRefOrderId').attr("style", "display:none;");
            // $('#repData').attr("style", "display:none;");
            $('#repData').attr("style", "display:none;");
         }
         
         view.inputCustomerOrderId.val(customer.orderId);
         view.inputCustomerOrderIdRef.val(customer.orderIdRef);
         view.agentAddressCode.val(customer.agentAddressCode);
         view.inputCustomerName.val(customer.name);
         view.inputCustomerTaxId.val(customer.taxId);
         //view.inputCustomerSegment.val(customer.group);
         /*
          var custType = customer.type;
          if (customer.group) {
          var segmentId = customer.group;
          view.inputCustomerSegment.key(segmentId);
          if (segmentId == "2") custType = "stateagency";
          }
          view.customerType.val(custType);
          */
         // view.customerType.val(customer.categoryName);
         view.customerType.val("organization");
         view.inputCustomerBranch.val(customer.branch);
         // view.inputCustomerBranch.val(customer.branch.replace(/\s/g,''));
         //alert('1'+customer.branch+'2');
         // view.inputCustomerAddress.val(customer.address+' '+customer.address2);
         view.inputCustomerAddress.val(customer.address);
         // view.inputCustomerAddress2.val(customer.address2);
         var
             inputTotalChargeExcVat = 0.00;
         for (var i = 0, m = serviceList.length; i < m; i++) {
             var service = serviceList[i];
             view.tableMobileNumberList.insert(["-", service.mdn, service.iccid, service.amount, "", ""]);
             var amtExcVat = service.amount* 100/( 100+ view.currentVatRate);
             amtExcVat = amtExcVat.toFixed(2);
             inputTotalChargeExcVat = parseFloat(amtExcVat) + inputTotalChargeExcVat;
         }
         /*ICE FIX CODE NO.79*/
         var
             totalCharge = view.tableMobileNumberList.sum(3);
         view.inputTotalCharge.val(totalCharge);
         view.inputBalance.val(totalCharge);
         view.inputVat.val((totalCharge * view.currentVatRate) / (1 +view.currentVatRate));
         view.inputAmount.val((totalCharge / (1+ view.currentVatRate)));

         // NSD
         //alert(inputTotalChargeExcVat+' '+view.currentVatRate);
         view.inputAmount.val(inputTotalChargeExcVat);
         view.inputVat.val(totalCharge-inputTotalChargeExcVat);
         /* end ICE FIX CODE NO.79*/	
         
         
    }
    function buttonGoToPaymentProcessClickEvent() {
        var receiptCustomerCustNo = view.inputReceiptCustNo.val();
        view.message.clear();
        if(view.inputCustomerName.val() == '' || view.inputCustomerAddress.val() == '') {
            view.message.error(["กรุณาตรวจสอบ รายละเอียดใบเสร็จรับเงิน ก่อนดำเนินการต่อไป"]).show();
            //alert(view.customerType.val());
            return;
        }else if(view.inputCustomerSegment.val() == '1' && (view.inputCustomerTaxId.val() == '' || view.inputCustomerBranch.val()=='')){
            view.message.error(["กรุณาตรวจสอบ รายละเอียดใบเสร็จรับเงิน ก่อนดำเนินการต่อไป"]).show();
            return;
        }
        var mobileNumberList = view.tableMobileNumberList.data();
        if (mobileNumberList.length < 1) {
            view.message.error(["ระบบจะต้องมีรายการ Mobile No. ที่ต้องการรับชำระก่อน อย่างน้อย 1 รายการ"]).show();
            return;
        }
        // console.log('555552');console.log(buildBillingList(mobileNumberList));console.log('555553');
        view.message.clear();
        view.session("billingList", buildBillingList(mobileNumberList));
        view.session("chargeExcVat", view.inputAmount.val());
        view.session("vatAmt", view.inputVat.val());
        view.session("refOrderId", view.inputRef.val() );
        view.session("orderId", view.inputCustomerOrderId.val());

        location.href = "pay-6-stap_2.jsp";
    } // pay-6-stap_2
    function buildBillingList(mobileNumberList) {
        var billingList = [], serviceList = [];
        $.each(mobileNumberList, function(i, row){
            serviceList.push({ "mobileNo": row[1], "iccid": row[2], "amount": stripToNumber(row[3]), "refTransId": view.inputCustomerOrderIdRef.val() });
        });

        /*billingList.push({
         "custNo": view.inputReceiptCustNo.val()
         ,"custName": view.inputReceiptCustName.val()
         ,"custTaxId": view.inputReceiptTaxId.val()
         ,"custBranch": view.inputReceiptBranch.val()
         ,"custSegment": view.inputReceiptSegment.key()
         ,"custType": view.customerType.val()
         ,"address1": view.inputReceiptAddress.val()
         ,"address2": view.inputReceiptAddress2.val()
         ,"vatRate": view.currentVatRate
         ,"remark": ""
         ,"split": false
         ,"isFullTypeReceipt": document.getElementById('isFullReceipt').checked
         ,"serviceList": serviceList
         });*/
        billingList.push({
            "custNo": view.inputCustomerOrderId.val()
            ,"custName": view.inputCustomerName.val()
            ,"custTaxId": view.inputCustomerTaxId.val()
            ,"custBranch": view.inputCustomerBranch.val()
            ,"custSegment": view.inputCustomerSegment.key()
            ,"custType": view.customerType.val()
            ,"address1": view.inputCustomerAddress.val()
            ,"address2": ''
            ,"agentAddressCode": view.agentAddressCode.val()
            ,"vatRate": view.currentVatRate
            ,"remark": ""
            ,"split": false
            ,"isFullTypeReceipt": document.getElementById('isFullReceipt').checked
            ,"acctCatLkp": view.inputCustomerSegment.key()
            ,"catCustomerSegment": view.inputCustomerSegment.key()
            ,"custCategoryDesc": $("#inputCustomerSegment option:selected").text()
            ,"serviceList": serviceList
            ,"additionalRemark": view.inputAdditionalRemark.val()
            ,"refOrderId": view.inputRef.val()
        });
        view.session("isStateAgency", view.inputCustomerSegment.key());//by Maew 05-10-2017
        // console.log('555552'+view.inputCustomerOrderIdRef.val());console.log(buildBillingList(serviceList));console.log('555553'+view.agentAddressCode.val());
        return billingList;
    }
    function allowEditCustomerData() {
        var check = document.getElementById('checkEditCustomerData').checked ;
        if(check == true){
            /*view.inputReceiptCustName.enable();
             view.inputReceiptTaxId.enable();
             view.inputReceiptSegment.enable();
             view.inputReceiptBranch.enable();
             view.inputReceiptAddress.enable();
             view.inputReceiptAddress2.enable();*/
            view.inputCustomerName.enable();
            view.inputCustomerTaxId.enable();
            view.inputCustomerSegment.enable();
            view.inputCustomerBranch.enable();
            view.inputCustomerAddress.enable();

        } else {
            /*view.inputReceiptCustName.disable(true);
             view.inputReceiptTaxId.disable(true);
             view.inputReceiptSegment.disable(true);
             view.inputReceiptBranch.disable(true);
             view.inputReceiptAddress.disable(true);
             view.inputReceiptAddress2.disable(true);*/
            view.inputCustomerName.disable(true);
            view.inputCustomerTaxId.disable(true);
            view.inputCustomerSegment.disable(true);
            view.inputCustomerBranch.disable(true);
            view.inputCustomerAddress.disable(true);
        }
    }
    function copyCustomerProfile() {
        var receiptCustomerCustNo = view.inputReceiptCustNo.val();
        if(receiptCustomerCustNo == "") {
            view.inputReceiptCustNo.val(view.inputCustomerOrderId.val());
            view.inputReceiptCustName.val( view.inputCustomerName.val());
            view.inputReceiptTaxId.val(view.inputCustomerTaxId.val());
            view.inputReceiptBranch.val(view.inputCustomerBranch.val());
            view.inputReceiptSegment.data(view.inputCustomerSegment.data());
            view.inputReceiptAddress.val(view.inputCustomerAddress.val());
            view.inputReceiptAddress2.val(view.inputCustomerAddress2.val());
        }
    }
    function setup() {
        if (view.utils.queryString()["new"]) {
            view.session("billingList", []);
            view.session("refOrderId", null);
            sessionStorage.clear();
        }
    }

</script>