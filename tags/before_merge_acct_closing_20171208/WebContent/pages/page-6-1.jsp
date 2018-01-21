<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
            <ol class="breadcrumb">
                <li><i>พิมพ์ใบเสร็จรับเงินซ้ำ/สำเนาใบเสร็จรับเงิน/ใบแทน</i></li>
            </ol>
            <div id="mainMessageDialog"></div>
            <div class="row">
                <div class="col-md-12 tab-modefile">
                   
                    <ul id="advanceSearchTab" class="nav nav-tabs" role="tablist">
                        <li role="presentation"><a href="#tab_1" aria-controls="tab_1" role="tab" data-toggle="tab"><span class="glyphicon glyphicon-user"></span> ค้นหาจากข้อมูลลูกค้า</a></li>
                        <li role="presentation" class="active"><a href="#tab_2" aria-controls="tab_2" role="tab" data-toggle="tab"><span class="glyphicon glyphicon-briefcase"></span> ค้นหาจากใบแจ้งค่าใช้บริการ</a></li>
                        <li role="presentation"><a href="#tab_3" aria-controls="tab_3" role="tab" data-toggle="tab"><span class="glyphicon glyphicon-th-list"></span> ค้นหาจากใบเสร็จรับเงิน</a></li>
                        <li role="presentation"><a href="#tab_4" aria-controls="tab_4" role="tab" data-toggle="tab"><span class="glyphicon glyphicon-tasks"></span> ค้นหาจากข้อมูลการรับชำระ</a></li>
                    </ul>
                    <div class="tab-content">
                        <div role="tabpanel" class="tab-pane " id="tab_1">
                            <div class="panel panel-default panal-radius">
                                <div class="panel-body">
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-horizontal">
                                    
                                                <div class="form-group">
                                                    <label class="control-label col-sm-2">เลขที่ลูกค้า :</label>
                                                    <div class="col-sm-3"><input id="searchCustomerNo" class="form-control"></div>
                                                    <label class="control-label col-sm-2" >เลขประจำตัวผู้เสียภาษี :</label>
                                                    <div class="col-sm-3"><input id="searchCustomerTaxNo" class="form-control"></div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label col-sm-2" >ชื่อลูกค้า/นามสกุล/ชื่อนิติบุลคล/ราชการ :</label>
                                                    <div class="col-sm-3"><input id="searchCustomerFirstName" class="form-control"></div>
                                                    <label class="control-label col-sm-2">กลุ่มผู้ใช้บริการ :</label>
                                                    <div class="col-sm-3">
                                                        <select id="searchCustomerSegment" name="CustomerType"
                                                                class="form-control" >
                                                            <option value="" disabled selected>กรุณาเลือกกลุ่มผู้ใช้บริการ</option>
                                                        </select>
                                                    </div>
                                                    <div class="col-sm-2"><a id="searchCustomer" class="btn btn-primary pull-right"><span class="glyphicon glyphicon-search"></span> ค้นหาข้อมูล</a></div>
                                                </div>
                                                <div class="form-group hide">
                                                    <label class="control-label col-sm-2" >ชื่อนิติบุคคล/ราชการ :</label>
                                                    <div class="col-sm-3"><input id="searchCustomerOrgName" class="form-control"></div>
                                                    <label class="control-label col-sm-2">กลุ่มผู้ใช้บริการ :</label>
                                                    <%--<div class="col-sm-3">
                                                        <select id="searchCustomerSegment" name="CustomerType"
                                                            class="form-control" >
                                                            <option value="" disabled selected>กรุณาเลือกกลุ่มผู้ใช้บริการ</option>
                                                        </select>
                                                    </div>--%>
                                                    <label class="control-label col-sm-2" >นามสกุล :</label>
                                                    <div class="col-sm-3"><input id="searchCustomerLastName" class="form-control"></div>

                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div role="tabpanel" class="tab-pane active" id="tab_2">
                            <div class="panel panel-default panal-radius">
                                <div class="panel-body">
                                    <div class="form-horizontal">
                                        <div class="form-group">
                                            <div class="col-sm-1"></div>
                                            <div class="col-sm-3 pull-left"><label class="control-label"><input type="radio" name="searchInvoiceOptions" value="1" checked> เลขที่ใบแจ้งค่าบริการ </label></div>
                                            <div class="col-sm-2"><input id="searchInvoiceNo" class="form-control"></div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-sm-1"></div>
                                            <div class="col-sm-3 pull-left"><label class="control-label"><input type="radio" name="searchInvoiceOptions" value="2"> วันที่จัดทำใบแจ้งค่าใช้บริการ&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;จากวันที่ :</label></div>
                                            <div class="col-sm-2">
                                                <div class="input-group input-append date" data-provide="datepicker" data-date-format="dd/mm/yyyy">
                                                    <input id="searchInvoiceStartDate" class="form-control" placeholder="dd/MM/yyyy">
                                                    <span class="input-group-btn"><a class="btn btn-default"><span class="glyphicon glyphicon-calendar"></span></a></span>
                                                </div>
                                            </div>
                                            <label class="control-label col-sm-1">ถึงวันที่ :</label>
                                            <div class="col-sm-2">
                                                <div class="input-group input-append date" data-provide="datepicker" data-date-format="dd/mm/yyyy">
                                                    <input id="searchInvoiceEndDate" class="form-control" placeholder="dd/MM/yyyy">
                                                    <span class="input-group-btn"><a class="btn btn-default"><span class="glyphicon glyphicon-calendar"></span></a></span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-sm-1"></div>
                                            <div class="col-sm-3 pull-left"><label class="control-label"><input type="radio" name="searchInvoiceOptions" value="3"> หมายเลขบริการ </label></div>
                                            <div class="col-sm-2"><input id="searchInvoiceServiceNo" class="form-control"></div>
                                            <div class="col-sm-6"><a id="searchInvoice" class="btn btn-primary pull-right"><span class="glyphicon glyphicon-search"></span> ค้นหาข้อมูล</a></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div role="tabpanel" class="tab-pane " id="tab_3">
                            <div class="panel panel-default panal-radius">
                                <div class="panel-body">
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-horizontal">
                                                <div class="form-group">
                                                    <label class="control-label col-sm-2">เลขที่ใบเสร็จรับเงิน :</label>
                                                    <div class="col-sm-3"><input class="form-control" id="inputReceiptNo"></div>
                                                    <label class="control-label col-sm-2">เลขประจำตัวผู้เสียภาษี :</label>
                                                    <div class="col-sm-3"><input class="form-control" id="inputTaxNo"></div>
                                                    <div class="col-sm-2"><a id="buttonSearchByReceiptInfo" class="btn btn-primary pull-right"><span class="glyphicon glyphicon-search"></span> ค้นหาข้อมูล</a></div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div role="tabpanel" class="tab-pane " id="tab_4">
                            <div class="panel panel-default panal-radius">
                                <div class="panel-body">
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-horizontal">
                                                <div class="form-group">
                                                    <label class="control-label col-sm-2">วันที่รับชำระเงิน&nbsp;&nbsp;จากวันที่ :</label>
                                                    <div class="col-sm-3"><div id="inputPaymentDateFrom"></div></div>
                                                    <label class="control-label col-sm-2">ถึงวันที่ :</label>
                                                    <div class="col-sm-3"><div id="inputPaymentDateTo"></div></div>
                                                    <div class="col-sm-2"><a id="buttonSearchByPaymentInfo" class="btn btn-primary pull-right"><span class="glyphicon glyphicon-search"></span> ค้นหาข้อมูล</a></div>
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
            <div class="row">
                <div class="col-md-12 tab-modefile">
                    <ul id="addressTab" class="nav nav-tabs" role="tablist">
                        <li role="presentation" class="active"><a href="#tab1" aria-controls="tab1" role="tab" data-toggle="tab"><span class="glyphicon glyphicon-envelope"></span> ประวัติการรับชำระค่าใช้บริการ</a></li>
                        <li role="presentation" class="">
                            <label> &nbsp;&nbsp;<a class="btn btn-link" id="reprintReceiptDialog"><span class="glyphicon glyphicon-retweet"></span> พิมพ์ใบเสร็จรับเงินซ้ำ</a></label>
                        </li>
                        <li role="presentation" class="">
                            <label> &nbsp;<a class="btn btn-link" id="printCopyOfReceiptDialog"><span class="glyphicon glyphicon-random"></span> พิมพ์ใบแทน</a></label>
                        </li>
                    </ul>
                    <div class="tab-content">
                        <div class="panel panel-default panal-radius">
                            <div class="panel-body">
                                <div class="row">
                                    <div class="col-md-12">
                                        <table id="receiptList" class="table table-hover" style="margin-bottom:0">
                                            <thead>
                                                <tr>
                                                    <th data-field="checked" data-radio="true"></th>
                                                    <th data-running-no="true">#</th>
                                                    <th>สถานที่รับชำระ</th> <!-- -->
                                                    <th>เลขที่ใบเสร็จรับเงิน</th>
                                                    <!--
                                                    <th>วันที่รับชำระ</th>
                                                    <th>วันที่บันทึกข้อมูล</th>
                                                    -->
                                                    <th>วันที่ทำรายการ</th>
                                                    <th>วันที่ออกใบเสร็จรับเงิน</th>
                                                    <th>ชื่อลูกค้า</th> 
                                                    <th>วิธีการชำระเงิน</th> 
                                                    <th>ประเภทการรับชำระ</th> <!-- -->
                                                    <th data-number-format="true">ยอดชำระ</th>  <!-- -->
                                                    <th>สถานะ</th>
                                                    <th data-align="center">หมายเหตุ</th>
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
            
        </section>
        <div class="modal fade" role="dialog" id="remarkModal">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title">หมายเหตุ</h4>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-md-12">
                                <span id="remark"></span>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <a class="btn btn-default" data-dismiss="modal"><span class="glyphicon glyphicon-ok-circle"></span> Ok</a>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal fade" role="dialog" id="dialogReprintReceipt">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title"><span class="glyphicon glyphicon-inbox"></span> ระบุเหตุผลการพิมพ์ซ้ำ/สำเนา</h4>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-horizontal">
                                    <div class="form-group">
                                        <label class="control-label col-sm-6" ><input type="radio" name="RadioOptions2" onclick="showHideElement()" id="radio1" value="manuscript"checked> ต้นฉบับ</label>
                                        <label class="control-label col-sm-2" ><input type="radio" name="RadioOptions2" onclick="showHideElement()" id="radio2" value="copy"> สำเนา</label>
                                    </div> 
                                    <div id="manuscriptElement" style="display:none">
                                        <div class="form-group">
                                            <label class="control-label col-sm-3" >เหตุผล : </label>
                                            <div class="col-sm-7">
                                                 <select class="form-control" id="inputReprintReason"></select>
                                            </div>
                                        </div>
                                        <!--  
                                    
                                        <div>
                                            <label class="control-label col-sm-3" ></label>
                                            <div class="col-sm-7">
                                                <input type="checkbox" name="" id="reprint" value="" >&nbsp;&nbsp;&nbsp;พิมพ์ซ่อม  
                                            </div>  
                                        </div>-->
                                        <div><label class="control-label col-sm-10" ></label></div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-3">ชื่อผู้อนุมัติ: </label>
                                            <div class="col-sm-7">
                                                <input id="approveBy" class="form-control" value="" >
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-3" >รหัสผ่าน : </label>
                                            <div class="col-sm-7">
                                                <input id="inputPass" type="password" class="form-control" value="" >
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            
                        </div>
                    </div>
                    <div class="modal-footer">
                        <a id="reprintReceipt" class="btn btn-success" ><span class="glyphicon glyphicon-print"></span> บันทึกและพิมพ์</a>
                        <a id="cancelReprintReceipt" class="btn btn-danger" data-dismiss="modal"><span class="glyphicon glyphicon-remove-circle"></span> ยกเลิก</a>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal fade" role="dialog" id="dialogPrintCopyOfReceipt">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title"><span class="glyphicon glyphicon-inbox"></span> ระบุเหตุผลการพิมพ์ใบแทน</h4>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-horizontal">
                                    <!-- 
                                    <div class="form-group">
                                        <label class="control-label col-sm-6" ><input type="radio" name="RadioOptions2" onclick="showHideElement()" id="1" value="copy"checked> สำเนา</label>
                                        <label class="control-label col-sm-2" ><input type="radio" name="RadioOptions2" onclick="showHideElement()" id="2" value="substitute"> ใบแทน</label>
                                    </div> 
                                    <div id="substituteElement" style="display:none">  -->
                                    <div class="form-group">
                                        <label class="control-label col-sm-3" >เหตุผล : </label>
                                        <div class="col-sm-7">
                                             <select class="form-control" id="inputPrintCopyReceiptReason"></select>
                                        </div>
                                    </div>
                                    <!-- --> 
                                    
                                    <div>
                                        <label class="control-label col-sm-3" ></label>
                                        <div class="col-sm-7">
                                            <input type="checkbox" name="" id="repPrint" value="" >&nbsp;&nbsp;&nbsp;พิมพ์ซ่อม
                                        </div>  
                                    </div>
                                    <div><label class="control-label col-sm-10" ></label></div>
                                    <div class="form-group">
                                        <label class="control-label col-sm-3">ชื่อผู้อนุมัติ: </label>
                                        <div class="col-sm-7">
                                            <input id="approveBy1" class="form-control" value="" >
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-sm-3" >รหัสผ่าน : </label>
                                        <div class="col-sm-7">
                                            <input id="inputPass1" type="password" class="form-control" value="" >
                                        </div>
                                    </div>
                                    <!-- </div> -->
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <a id="printCopyOfReceipt" class="btn btn-success" ><span class="glyphicon glyphicon-print"></span> บันทึกและพิมพ์</a>
                        <a id="cancelPrintCopyOfReceipt" class="btn btn-danger" data-dismiss="modal"><span class="glyphicon glyphicon-remove-circle"></span> ยกเลิก</a>
                    </div>
                </div>
            </div>
        </div>
    </body>
<script>
var view = (function($){
    var self = this, defaultErrorMessage = "An error occurred but there is no message response.";
    self.session = function(key, val) { if (!val) { var val = window.sessionStorage.getItem(key); return val && (val.indexOf("{") > -1 || val.indexOf("[") > -1) ? JSON.parse(val) : val } window.sessionStorage.setItem(key, ($.type(val) != "object" && $.type(val) != "array" ? val : JSON.stringify(val)))  };
    self.utils = {
        guid: function(){ function r() { return Math.floor((Math.random() + 1) * 0x10000).toString(16).substring(1) } return r() + r() +"-"+ r() +"-"+ r() +"-"+ r() +"-"+ r() + r() + r() },
        numberFormat: function(num, dec) { return $.type(num) !== "number" ? "0.00" : num.toFixed(dec || 2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,'); },
        dateFormat: function() { var obj = arguments[0]; if ($.type(obj) == "string" && /[0-9]{2}.[0-9]{2}.[0-9]{4}/g) { return obj } else if ($.isNumeric(obj)) { var dt = new Date(obj), dd = dt.getDate().toString(), mm = (dt.getMonth() + 1).toString(), yyyy = dt.getFullYear().toString(); return (dd[1]?"":"0") + dd +"/"+ (mm[1]?"":"0") + mm +"/"+ yyyy } else if ($.type == "date") { return "" } return "" },
        dateTimeFormat: function() { var obj = arguments[0], dt = $.type(obj) != "date" ? ($.isNumeric(obj) ? new Date(obj) : new Date()) : new Date(), dd = dt.getDate().toString(), MM = (dt.getMonth() + 1).toString(), yyyy = dt.getFullYear().toString(), hh = dt.getHours().toString(), mm = dt.getMinutes().toString(), ss = dt.getSeconds().toString(); return (dd[1]?"":"0") + dd +"/"+ (MM[1]?"":"0") + MM +"/"+ yyyy +" "+ (hh[1]?"":"0") + hh +":"+ (mm[1]?"":"0") + mm +":"+ (ss[1]?"":"0") + ss },
        queryString: function() { var pos = location.href.indexOf("?"), obj = {}; if (pos < 0) return obj; var params = location.href.slice(pos + 1).split("&"); for (var i = 0, m = params.length; i < m; i++) { pos = params[i].indexOf("="); if (pos < 0) { obj[params[i]] = true; continue; } obj[params[i].substring(0, pos)] = params[i].substring(pos + 1) } return obj },
        window: function(windowName, url, side) { window.windowOpened = (window.windowOpened || {}); var screenProp = ""; if (side == "left") { screenProp = "left=0,top=0,width="+ (screen.width/2) +",height="+ (screen.height-100) } else if (side == "right") { screenProp = "left="+ (screen.width/2-40) +",top=0,width="+ (screen.width/2+40) +",height="+ (screen.height-100) } window.windowOpened[windowName] = window.open(url, windowName, "menubar=no,scrollbars=no,resizable=0,"+ screenProp, false); return window.windowOpened }
    };
    //// AUTOMATIC REGISTER FORMATTER FUNCTION ////
    window.get = function(url, params, success, msgDialog, preCheck){ $.ajax({ "url": url, "type": "GET", "data": params, "error": msgDialog.valid, "success": function(res){
        var res2 = calculateAmt(res);
        //console.log('55555555');console.log(res); console.log(res2); console.log('5555555');
        (preCheck || function(o){ console.log(o) })(res); if (msgDialog && !msgDialog.valid(res)) { return; } success(res)
    } }) }
    window.post = function(url, params, success, msgDialog, preCheck){ $.ajax({ "url": url, "type": "POST", "data": params, "error": msgDialog.valid, "success": function(res){ (preCheck || function(o){ console.log(o) })(res); if (msgDialog && !msgDialog.valid(res)) { return; } success(res) } }) }
    window.stripToNumber = function(str) { return $.type(str) === "string" && $.isNumeric(str = str.replace(/[,]/g, "")) ? parseFloat(str, 10) : 0 }
    window.toDateString = function(ddMMyyyy){ return ddMMyyyy.replace(/(\d{2}).(\d{2}).(\d{4})/g, "$2/$1/$3") }
    window.runningFormatter = function(val, row, ind) { return ind + 1 }
    window.numberFormatter = function(val, row, ind) { return !$.isNumeric(stripToNumber(val)) ? "0.00" : parseFloat(val, 10).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,') }
    window.dateFormatter = function(val, row, ind){ if (!val) return ""; if ((/(\d{4}).(\d{2}).(\d{2}).*/g).test(val)) return val.replace(/(\d{4}).(\d{2}).(\d{2}).*/g, "$3/$2/$1"); return val }
    window.timeFormatter = function(val, row, ind){ if (!val) return ""; if ((/.*T(\d{2}).(\d{2}).(\d{2}).*/g).test(val)) return val.replace(/.*T(\d{2}).(\d{2}).(\d{2}).*/g, "$1:$2:$3"); return val }
    window.dateTimeFormatter = function(val, row, ind){ if (!val) return ""; if ((/(\d{4}).(\d{2}).(\d{2})T(\d{2}).(\d{2}).(\d{2}).*/g).test(val)) return val.replace(/(\d{4}).(\d{2}).(\d{2})T(\d{2}).(\d{2}).(\d{2}).*/g, "$3/$2/$1 $4:$5:$6"); return val }
    
    window.stringInputFormatter = function(val, row, ind) { return '<input value="'+ $.trim(val) +'" maxLength="40" class="form-control">' }
    window.numberInputFormatter = function(val, row, ind) { return '<input value="'+ self.utils.numberFormat(parseFloat(val || "0.00", 10)) +'" maxLength="10" style="width:100px" class="form-control text-right" onkeypress="return $.isNumeric(String.fromCharCode(event.which)) || (event.which == 46 && this.value.indexOf(\'.\') == -1)" onfocus="(function(o){ o.value = o.value.replace(/[,]/g, \'\') })(this)" onblur="(function(o){ o.value = parseFloat(o.value, 10).toFixed(2).replace(/(\\d)(?=(\\d{3})+\\.)/g, \'$1,\'); })(this)">' }
    window.modifyButtonFormatter = function(val, row, ind) { return '<a class="btn btn-link modList" onclick=\'(function(elem, row, ind){ var table = $("#"+ $(elem).closest("table")[0].id); window[table[0].id +"ModifyEvent"](table, row, ind) })(this, '+ JSON.stringify(row) +', '+ ind +')\'><span class="glyphicon glyphicon-pencil"></span></a>' }
    window.removeButtonFormatter = function(val, row, ind) { return '<a class="btn btn-link delList" onclick=\'(function(elem, row, ind){ $("#"+ $(elem).closest("table")[0].id).bootstrapTable("remove", { field: "index", values: [ind] }) })(this, '+ JSON.stringify(row) +', '+ ind +')\'><span class="glyphicon glyphicon-trash"></span></a>' }
    window.remarkIconFormatter = function(val) {if(val != null){return '<a  style="cursor: pointer" onclick="modalPopupRemark('+"'"+val+"'"+')"><span class="glyphicon glyphicon-envelope" aria-hidden="true"></span></a>';} else{ return "-"}}
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
    function Message(el) { var obj = this, timeCnt = 0, loadFunc; obj.el = el; obj.elem = $(el);
        obj.hide = function() { obj.elem.addClass("hide"); return obj };
        obj.show = function(flag) { if (flag == null || flag) obj.elem.removeClass("hide"); else obj.hide(); return obj };
        obj.clear = function() { obj.elem.find("*").remove(); obj.hide(); return obj };
        obj.message = function(arr, cls) { $.each(arr, function(i,o) { var m = o; if ($.type(o) === "object") { m = (o.desc || o.messageDesc) +" [code="+ (o.code || o.messageCode) +"]" }; obj.elem.append('<div class="'+ cls +'">'+ m +'<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button></div>') }); return obj };
        obj.error = function(arr) { return obj.message(arr, "alert alert-danger") };
        obj.warn = function(arr) { return obj.message(arr, "alert alert-warning") };
        obj.success = function(arr) { return obj.message(arr, "alert alert-success") };
        obj.valid = function(jqXHR, textStatus, errorThrow){ var res = jqXHR; obj.stopLoad(); if (jqXHR && jqXHR.responseJSON) res = jqXHR.responseJSON; if (res) { var isNoData = false; if (res.data && res.statusCode == '0' && res.data.length < 1) isNoData = true; if ($.type(res._embedded) === 'object' && $.map(res._embedded,function(v,k){return v}).length < 1) isNoData = true; if (isNoData) { obj.warn(["There is no records of data."]).show(); return false } obj.clear(); return true } obj.warn(res && $.type(res.warningList) === 'array' ? res.warningList : []).error(res && $.type(res.errorList) === 'array' ? res.errorList : ["An error occurred on the server side but there is no error message returned."]).show(); return false }
        obj.hideLoad = function(){ obj.stopLoad().clear(); return this }; obj.stopLoad = function(){ clearInterval(loadFunc); return this }
        obj.showLoad = function(msg){ obj.elem.append('<div id="'+ obj.el +'-loading" class="alert alert-warning">'+ bind(msg, 0) +'</div>'); timeCnt = 0; var elem = document.getElementById(obj.el +"-loading"); loadFunc = setInterval(function(){ elem.innerHTML = bind(msg, ++timeCnt) }, 1000); obj.show(); return this }
        function bind(msg, timeCnt) { return msg.replace(/\{timeCnt\}/g, timeCnt) }
    }
    function Modal(el, static) { var obj = this; obj.el = el; obj.elem = $(el);
        obj.hide = function() { this.elem.modal("hide") };
        obj.show = function() { this.elem.modal("show") };
        if (static) obj.elem.data("backdrop", "static")
    }
    function Input(el, maxLen, propPos) {
        var obj = this;
        obj.el = el;
        obj.elem = $(el);
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
    function DateInput(el) { var obj = this; obj.el = el; obj.elem = document.getElementById(el.replace("#", "")); obj.elem.setAttribute("class", "input-group input-append date"); obj.elem.setAttribute("data-provide", "datepicker"); obj.elem.setAttribute("data-date-format", "dd/mm/yyyy"); obj.elem.setAttribute("data-date-autoclose", "true"); obj.elem.innerHTML = '<input class="form-control" id="'+ el +'-input" placeholder="dd/MM/yyyy" maxlength="10"><span class="input-group-btn"><a class="btn btn-default"><span class="glyphicon glyphicon-calendar"></span></a></span>'; obj.input = obj.elem.childNodes[0]; obj.button = obj.elem.childNodes[1].childNodes[0];
        obj.disable = function(){ var disabled = arguments.length == 0 || arguments[0] == true || arguments[0] == "true"; obj.input.disabled = disabled; obj.button.setAttribute("class", "btn btn-default"+ (disabled ? " disabled" : "")); if (disabled) obj.elem.removeAttribute("data-provide"); else obj.elem.setAttribute("data-provide", "datepicker"); return this }
        obj.enable = function(){ obj.disable(false); return this }
        obj.val = function() { if (arguments.length != 1) return obj.input.value; obj.input.value = arguments[0]; return this }
    }
    function DropDown(el, url) { var obj = this, data = [{ key: "", value: "", text: "Please Select" }]; obj.el = el; obj.elem = $(el);
		obj.data = function(array) { if ($.type(array) == "array") { setup(data = array); return this } return data; };
		obj.init = function(url) { if (url) $.get(url, function(res) { setup(data = res.data);/* console.log(res); */ }); else setup(data); return this };
		obj.disable = function(flag) { obj.elem.attr("disabled", (flag == null ? true : flag)); return this }
		obj.enable = function() { obj.disable(false); return this }
		obj.index = function() { if (arguments.length == 1) { var maxLen = data.length, index = arguments[0]; obj.elem.find("option").each(function(i, opt){ opt.removeAttribute("selected") }).eq(index < 0 ? maxLen + index : index).attr("selected", true); return this } return $.inArray("selected", obj.elem.find("option").map(function(i, opt){ return opt.selected ? "selected" : "no-selected" })) }
		obj.selected = function(){ return data[obj.index()]; }; 
		obj.val = function() { return obj.elem.val(); }; 
		obj.key = function(){ if (arguments.length == 0) { var key = obj.elem.find("option:selected").data("key"); if (typeof key === "string" && key === "undefined") { var item = data[obj.index()]; key = item && item.key ? item.key : item.id } return key } var key = arguments[0]; for (var i = 0, m = data.length; i < m; i++) { var item = data[i]; if (item.key && item.key == key) { obj.index(i); break; } if (item.id && item.id == key) { obj.index(i); break; } } return this }
		function setup(array) { obj.elem.find("*").remove();
                obj.elem.append('<option data-index="0" data-key="0" value="">กรุณาเลือกกลุ่มผู้ใช้บริการ</option>');
				$.each(array,function(i,o){
                    x = i + 1;
					if(o.category == 'payothers.service.category') {
						obj.elem.append('<option data-index="'+ x +'" data-key="'+ o.code +'" value="'+ o.descFullTh +'">'+ o.descFullTh +'</option>')
					} else if(o.category == 'payothers.service.name') {
						obj.elem.append('<option data-index="'+ x +'" data-key="'+ o.code +'" value="'+ o.descFullTh +'">'+ o.descFullTh +'</option>')
					} else { 
						obj.elem.append('<option data-index="'+ x +'" data-key="'+ o.key +'" value="'+ o.value +'">'+ (o.text || o.value) +'</option>')
					} 
				}); 
		}
		data = obj.elem.change(window[el.substring(1) +"ChangeEvent"]).find("option").map(function(i, opt){ return { key: opt.getAttribute("data-key"), value: opt.value, text: opt.textContent } });
	}
    self.assert = {
         requiredField: function(obj, paramName, msg) { if (!obj) return false; if ($.trim(obj.val()) === "") { obj.error(); throw (msg || "The '{name}' input field is required!.").replace("\{name\}", paramName); } obj.error(false); return true }
        ,invalidFormat: function(obj, format, paramName, msg) { if (!obj) return false; if (!new RegExp(format, "g").test($.trim(obj.val()))) { obj.error(); throw (msg || "The '{name}' input field is fill invalid data!.").replace("\{name\}", paramName); } obj.error(false); return true }
    };
    self.button = new(function(){
        var that = this;
        that.reprintReceiptDialog = new Button("#reprintReceiptDialog");
        that.reprintReceipt = new Button("#reprintReceipt");
        that.cancelReprintReceipt = new Button("#cancelReprintReceipt");
        that.printCopyOfReceiptDialog = new Button("#printCopyOfReceiptDialog");
        that.printCopyOfReceipt = new Button("#printCopyOfReceipt");
        that.cancelPrintCopyOfReceipt = new Button("#cancelPrintCopyOfReceipt");
        that.searchCustomer = new Button("#searchCustomer");
        that.searchInvoice = new Button("#searchInvoice");
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
        that.mainMessageDialog = new Message("#mainMessageDialog");
        that.remarkModal = new Modal("#remarkModal", true);
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
        that.searchInvoiceOptions = new Radio("[name=searchInvoiceOptions]");
        function Radio(el) {
            this.el = el;
            this.elem = $(el);
            this.label = function() { return this.elem.filter(":checked").data("label") };
            this.val = function() { var s = this.elem.filter(":checked"),val = s.val(); return !val ? s.data("label") : val };
        }
    });
    self.input = new(function(){
        var that = this;
        that.searchInvoiceNo = new Input("#searchInvoiceNo", 20);
        that.searchInvoiceStartDate = new Input("#searchInvoiceStartDate", 10);
        that.searchInvoiceEndDate = new Input("#searchInvoiceEndDate", 10);
        that.searchInvoiceServiceNo = new Input("#searchInvoiceServiceNo", 30);
        that.searchCustomerNo = new Input("#searchCustomerNo", 20);
        that.searchCustomerTaxNo = new Input("#searchCustomerTaxNo", 20);
        that.searchCustomerFirstName = new Input("#searchCustomerFirstName", 40);
        that.searchCustomerLastName = new Input("#searchCustomerLastName", 80);
        that.searchCustomerOrgName = new Input("#searchCustomerOrgName", 40);
        that.searchCustomerSegment = new DropDown("#searchCustomerSegment").init("../findAccountCategoryList.json");
        that.customerNo = new Input("#customerNo");
        that.customerName = new Input("#customerName");
        that.customerTaxNo = new Input("#customerTaxNo");
        that.customerBranch = new Input("#customerBranch");
        that.customerCollectUnit = new Input("#customerCollectUnit");
        that.customerSegment = new DropDown("#customerSegment");
        that.customerAccruedAmount = new Input("#customerAccruedAmount");
        that.customerBillGroup = new Input("#customerBillGroup");
        that.customerAdvancedAmount = new Input("#customerAdvancedAmount");
        that.customerStatus = new Input("#customerStatus");
        that.customerCurrency = new Input("#customerCurrency");
        that.customerRemark = new Input("#customerRemark");
        that.customerVatRate = new Input("#customerVatRate");
        that.customerAddress = new Input("#customerAddress");
        that.val = function() { if (arguments.length == 1) { $.each(arguments[0],function(k,v){ $("#"+ k).val(v) }) } };
        function NumberInput(el, dec) {
            var obj = this, decimal = (dec || 2);
            this.el = el;
            this.elem = $(el);
            this.val = function() { if (arguments.length == 0) return parseFloat(strip(this.elem.val() || "0"), 10); this.elem.val(format(arguments[0])); }
            this.decimal = function(dec) { decimal = dec };
            this.format = format;
            this.elem.keypress(function(e) { var key = (e.which || e.keyCode || e.charCode || 0); var ch = String.fromCharCode(key); return "0123456789.".indexOf(ch) > -1 });
            this.elem.focus(function(){ this.select() });
            this.elem.blur(function(){ this.value = format(this.value) });
            function format(val) { return ($.isNumeric(val) ? parseFloat(val, 10) : obj.val()).toFixed(decimal).replace(/(\d)(?=(\d{3})+\.)/g, '$1,'); };
            function strip(str) { return (str || "").replace(/[,]/g, "") }
            this.elem.val("0.00");
        }
    });
    self.tab = new(function(){
        var that = this;
        that.advanceSearchTab = new Tab("#advanceSearchTab");
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
    });
    self.table = new(function(){
        var that = this;
        that.receiptList = new Table("#receiptList");
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
            obj.sum = function(index) { var sum = 0; this.elem.find("tbody tr").each(function(i, row){ var val = $(row).find("td").eq(index).text(); sum += (isNaN(val) ? 0 : parseFloat(val, 10)) }); return sum }
            $(obj.el).on("click", ".delList", function(){ $(this).parent().parent().remove(); reorder(); onDel(obj.data()) });
            obj.elem.find("thead th").each(function(i,o){ var elem = $(o);
                headers.push({ "field": elem.data("field"), "align": $.trim(elem.data("align")), "runningNo": elem.data("runningNo") === true, "numberFormat": elem.data("numberFormat") === true, "checkbox": elem.data("checkbox") === true, "radio": elem.data("radio") === true, "input": elem.data("input") === true })
            }); function replace(str, style, field, index, value){ var s = str; if (style) s = s.replace("\{style\}", style); return s.replace("\{field\}", $.trim(field)).replace("\{index\}", index).replace("\{value\}", value) }
            function extract(prop, col) { if (prop.checkbox) { var elem = col.children[0].children[0]; return !elem.checked ? "" : elem.value } else if (prop.radio) { var elem = col.children[0].children[0]; return !elem.checked ? "" : elem.value } else if (prop.input) { var elem = col.children[0].children[0]; return elem.value } return col.children[0].innerText }
            if(obj.body.length < 1) { obj.elem.append("<tbody></tbody>"); obj.body = obj.elem.find("tbody") }
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
        //// AUTOMATIC REGISTER FORMATTER FUNCTION ////
        window.runningFormatter = function(val, row, ind) { return ind + 1 }
        window.numberFormatter = function(val, row, ind) { return !$.isNumeric(val) ? "0.00" : parseFloat(val, 10).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,').replace(/\.00$/g, "") }
    });
    self.contextPath = '${pageContext.request.contextPath}/';
    self.dialogReprintReceipt = new Modal("#dialogReprintReceipt");
    self.dialogPrintCopyOfReceipt = new Modal("#dialogPrintCopyOfReceipt");
    self.buttonSearchByReceiptInfo = new Button("#buttonSearchByReceiptInfo");
    self.buttonSearchByPaymentInfo = new Button("#buttonSearchByPaymentInfo");
    self.inputReceiptNo = new Input("#inputReceiptNo");
    self.inputTaxNo = new Input("#inputTaxNo");
    self.inputPaymentDateFrom = new DateInput("#inputPaymentDateFrom");
    self.inputPaymentDateTo = new DateInput("#inputPaymentDateTo");
    self.inputReprintReason = new DropDown("#inputReprintReason");//.data([{ key: "", value: "ใบกำกับหาย" }]);
    self.inputPrintCopyReceiptReason = new DropDown("#inputPrintCopyReceiptReason");//.init("../findServiceReasonCategoryList.json");//.data([{ key: "", value: "-กรุณาระบุเหตุผล-" },{ key: "case1", value: "สูญหาย" },{ key: "case2", value: "ถูกทำลาย" },{ key: "case3", value: "ชำรุดในสาระสำคัญ" }]);
    return this;
})(jQuery);

view.tab.advanceSearchTab.change(function() {
    view.dialog.mainMessageDialog.clear()
});
view.button.searchCustomer.click(function() {

    var custNo = view.input.searchCustomerNo.val(), taxNo = view.input.searchCustomerTaxNo.val(), url = "", custName = view.input.searchCustomerFirstName.val(), custLastName = view.input.searchCustomerLastName.val()
            , custOrgName = view.input.searchCustomerOrgName.val(), custSegment = view.input.searchCustomerSegment.key();
    view.table.receiptList.showLoad();
    var params = {}; var cusNo = null, cusName = null, taxNoS = null, cusActSeg = null;
    /*if (custNo.length > 0) { url = "../service/receipts/search/custNo?custNo="+ custNo;
    } else if (taxNo.length > 0) { url = "../service/receipts/search/taxNo?taxNo="+ taxNo;
    } else if (custName.length > 0) { url = "../service/receipts/search/custName?custName="+ custName;
    } else if (custLastName.length > 0) { url = "../service/receipts/search/custName?custName="+ custLastName;
    } else if (custOrgName.length > 0) { url = "../service/receipts/search/custName?custName="+ custOrgName;
    } else if (custSegment!="0") { url = "../service/receipts/search/cusActSeg?cusActSeg="+ custSegment;
    } else {
        return;
    }*/
    if(custNo.length > 0){
        cusNo = custNo;
    }
    if(custName.length > 0){
        cusName = custName
    }
    if(taxNo.length > 0){
        taxNoS = taxNo
    }
    if(custSegment!="0"){
        cusActSeg = custSegment;
    }
    params = {"custName": cusName, "custNo": cusNo, "taxNo": taxNoS, "cusActSeg": cusActSeg};
    url = "../cusAllCri.json";
    //console.log('555555');console.log(params);
    get(url, params, ReceiptInfoResponseHandler, view.dialog.mainMessageDialog, function(res){ 
	    view.table.receiptList.hideLoad() 
	    console.log(res)
    });

    /*view.dialog.mainMessageDialog.clear();
    view.button.reprintReceiptDialog.disable().badge(0);
    view.button.printCopyOfReceiptDialog.disable();
    view.table.receiptList.clear();
    var url = "", params;
    if (!view.input.searchCustomerNo.empty())         { url = "../service/bill-profiles/search/no"; params = { "no": view.input.searchCustomerNo.val() } }
    else if (!view.input.searchCustomerTaxNo.empty()) { url = "../service/bill-profiles/search/tax"; params = { "tax": view.input.searchCustomerTaxNo.val() } }
    else if (!view.input.searchCustomerFirstName.empty() || !view.input.searchCustomerLastName.empty()) {
        url = "../service/bill-profiles/search/name"; params = { "firstName": view.input.searchCustomerFirstName.val(), "lastName": view.input.searchCustomerLastName.val() }
    }
    else if (!view.input.searchCustomerOrgName.empty())  { url = "../service/bill-profiles/search/org"; params = { "org": view.input.searchCustomerOrgName.val() } }
    else { view.dialog.mainMessageDialog.error(["Please fill in customer details at least 1 input."]).show(); return; }
    view.table.receiptList.showLoad();
    $.get(url, params, function(res){
        view.dialog.mainMessageDialog.clear();
        if (!res || !res._embedded || res._embedded.billProfiles.length < 1) { view.dialog.mainMessageDialog.error(["There is no data for your search criteria."]).show(); return; }
        if (res._embedded.billProfiles.length != 1) { view.dialog.mainMessageDialog.error(["Your criteria is insufficient. Please add more details for accurate customer information."]).show(); return; }
        var bill = res._embedded.billProfiles[0];
        view.input.customerNo.val(bill.no);
        view.input.customerName.val(bill.customerAccountName);
        view.input.customerTaxNo.val(bill.taxRegisterNo);
        view.input.customerBranch.val(bill.branchId);
        view.input.customerBillGroup.val(bill.billGroup);
        view.input.customerStatus.val("Active");
        get("../service/receipts/search/custNo", { "custNo": bill.no }, ReceiptInfoResponseHandler, view.dialog.mainMessageDialog, function(){ view.table.receiptList.hideLoad() });
    });*/
});
view.button.searchInvoice.click(function() {
    var index = view.radio.searchInvoiceOptions.val(), url, params;
    view.dialog.mainMessageDialog.clear();
    view.button.reprintReceiptDialog.disable().badge(0);
    view.button.printCopyOfReceiptDialog.disable();
    view.table.receiptList.clear();
    var paymentDateFr = view.input.searchInvoiceStartDate.val(), paymentDateTo = view.input.searchInvoiceEndDate.val()
    try {
        if (index === "1" && view.assert.requiredField(view.input.searchInvoiceNo, "Invoice No")) { url = "../searchReceiptsByinvNo.json"; params = { "invNo": view.input.searchInvoiceNo.val() }
     } else if (index === "2" && view.assert.invalidFormat(view.input.searchInvoiceStartDate, "[0-9]{2}/[0-9]{2}/[0-9]{4}", "Invoice Start Date") && view.assert.invalidFormat(view.input.searchInvoiceEndDate, "[0-9]{2}/[0-9]{2}/[0-9]{4}", "Invoice End Date"))
        /*{ url = ""; params = {}
        } */
        { url = "../service/receipts/search/issueDt?from="+ paymentDateFr.replace(/(\d{2}).(\d{2}).(\d{4})/g, "$2/$1/$3") +" 00:00:00&to="+ paymentDateTo.replace(/(\d{2}).(\d{2}).(\d{4})/g, "$2/$1/$3") +" 23:59:59";
        }
        else if (index === "3" && view.assert.requiredField(view.input.searchInvoiceServiceNo, "Service No")) { url = "../searchReceiptByInvSubNo2.json"; params = {"invSubNo":view.input.searchInvoiceServiceNo.val()}
        }
        get(url, params, ReceiptInfoResponseHandler, view.dialog.mainMessageDialog, function(){ view.table.receiptList.hideLoad() });
    } catch(err) {
        view.dialog.mainMessageDialog.error([err]).show()
    }
});
function paymentCaseFormatter(attributes){
    var str="";
    if(attributes.indexOf("F") > -1 ){
        str="ชำระเต็มจำนวน";
    }else if(attributes.indexOf("A") > -1 ){
        str="ชำระล่วงหน้า";
    }else {
        str = "ชำระบางส่วน";
    }

    return str;
   // ชำระเต็มจำนวน
    // ชำระล่่วงหน้า
    // ชำระบางส่วน
}

function dateTimeFormatterV2(dateFrom) {
    var datesStr = dateFrom.split("-");
    console.log(datesStr.length);
    if(datesStr.length>0){
        return datesStr[2]+"/"+datesStr[1]+"/"+datesStr[0]
    }
    else
        return "";
}
function ReceiptInfoResponseHandler(res) {
    window["receipts"] = res.content;
//     console.log(res._embedded.receipts);
    var data = []; $.map(res.content, function(o,i){
        if (o.attributes.indexOf("R") < 0) {
            console.log("xyz->"+o.payment.type);
            console.log("updateDttm"+o.updateDttm);
            if(o.name == null){o.name = "-"}
            console.log(o.name);
            data.push([i, "-", o.branchName, o.no, dateTimeFormatterV2(o.updateDttm), dateTimeFormatterV2(o.updateDttm), o.name, o.paymentCase, paymentCaseFormatter(o.attributes), o.totalCharge, "ปกติ", remarkIconFormatter(o.remark)])
        }
            //data.push([i, "-", o.branchName, o.no, dateTimeFormatter(o.updateDttm), dateTimeFormatter(o.updateDttm), o.name, o.paymentCase, "", o.totalPaid, "Active"])
    });
    view.table.receiptList.data(data);
}
view.table.receiptList.elem.on("click", "input[type=radio]", function(){
    var val = parseInt(this.value, 10), receipts = (window["receipts"] || []), receipt = window["receiptSelected"] = receipts[val];
    view.button.reprintReceiptDialog.enable().badge(receipt.reprint);
    view.button.printCopyOfReceiptDialog.enable();
});
$(function(){
    view.button.reprintReceiptDialog.disable().badge(0);
    view.button.printCopyOfReceiptDialog.disable();
});
function buttonSearchByReceiptInfoClickEvent() {
    var receiptNo = view.inputReceiptNo.val(), taxNo = view.inputTaxNo.val(), url = "";
    if (receiptNo.length > 0) { url = "../searchReceiptByNo.json?no="+ receiptNo;
    } else if (taxNo.length > 0) { url = "../searchReceiptByTaxNo.json?taxNo="+ taxNo;
    } else {
        return;
    }
    get(url, null, ReceiptInfoResponseHandler, view.dialog.mainMessageDialog, function(){ view.table.receiptList.hideLoad() });
}
function buttonSearchByPaymentInfoClickEvent() {
    var paymentDateFr = view.inputPaymentDateFrom.val(), paymentDateTo = view.inputPaymentDateTo.val(), url = "";
    if (paymentDateFr.length > 0 && paymentDateTo.length > 0) { url = "../searchPaymentByDate.json?fromDate="+ paymentDateFr.replace(/(\d{2}).(\d{2}).(\d{4})/g, "$2/$1/$3") +" 00:00:00&toDate="+ paymentDateTo.replace(/(\d{2}).(\d{2}).(\d{4})/g, "$2/$1/$3") +" 23:59:59";
    } else {
        return;
    }
    get(url, null, ReceiptInfoResponseHandler, view.dialog.mainMessageDialog, function(){ view.table.receiptList.hideLoad() });
}
function reprintReceiptDialogClickEvent() { 
	findListReason("#inputReprintReason");
	view.dialogReprintReceipt.show(), view.$("#manuscriptElement").show(); 
	$('#radio2').attr('checked', false);
	$('#radio1').attr('checked', true);
	$('#approveBy').val('');
    $('#inputPass').val('');
    $('#reprint').attr('checked', false);
}
function reprintReceiptClickEvent() {
view.dialog.mainMessageDialog.clear();
	var category=$('input[name=RadioOptions2]:checked').val()
    var inputReprintReason=$("#inputReprintReason").val();
    var approveBy=$("#approveBy").val();
     var inputPass=$("#inputPass").val();
    
	var reprint="0";
    if($("#reprint").prop('checked')){
        reprint="1";
    }
    var substitute="false";
    var categoryName ="";
    var reason = "";
    var approvedby = "";
    var form;
    //alert("Reprint ["+category+"]")
 if(category=='manuscript'){
    		$(document.body).append('<form id="reprintPaymentReceiptForm" action="../reprintPaymentReceipt.pdf" method="post" target="_printForm"></form>');
    	    form = document.getElementById('reprintPaymentReceiptForm');
    	    function input(n,v) { var input = document.createElement("input"); input.type = "hidden"; input.name = n; input.value = v; return input }
    	    var i = 0, o = window["receiptSelected"];
    	    form.appendChild(input("id", o.id));
    	    form.appendChild(input("receiptFormat", "receive_wh"));

    	    categoryName="REPRINT";
    	    reason = inputReprintReason;
    		approvedby = approveBy;
		    var trsreprintDTO={
					receiptid:o.id,
					reason:reason,
					categoryName:categoryName,  //COPY, SUBSTITUE , REPRINT
					approvedby:approvedby,
					password:inputPass
					
			}
			$.ajax({
				  type: "POST",
				  url: "../saveReprint.json",
				  data: JSON.stringify(trsreprintDTO),
				  dataType: "json",
				  async:false,
				  contentType: "application/json; charset=utf-8",
				  success:function(data){
				  	console.log("OK");
					console.log(data);
					if(data.Status == 'SUCCESS'){
		
				
						  $( "#reprintPaymentReceiptForm" ).submit();
						  $("#reprintPaymentReceiptForm").remove();
						
					}else{
						 view.dialog.mainMessageDialog.error(["ไม่สามารถอนุมัติ ทำรายการได้"]).show();
						 $("#reprintPaymentReceiptForm").remove();
					}
					view.dialogReprintReceipt.hide()
					
				  }
				});
    	} else {
    	
    		var receiptFormat="receive_wh"; // receive + wh [receive_wh] , receive only [receive_only] ,  wh only [wh_only]
    		$(document.body).append('<form id="copyprintPaymentReceiptForm" action="../copyprintPaymentReceipt.pdf" method="post" target="_printForm"></form>');
    		//var form = document.forms[0];
    		form = document.getElementById('copyprintPaymentReceiptForm');
    		function input(n,v) { var input = document.createElement("input"); input.type = "hidden"; input.name = n; input.value = v; return input }
    		var i = 0, o = window["receiptSelected"];
    		form.appendChild(input("id", o.id));
    		form.appendChild(input("substitute", substitute));
    		form.appendChild(input("receiptFormat", receiptFormat));
    		form.appendChild(input("reason", inputReprintReason));
    		form.appendChild(input("approveBy", approveBy));
    		form.appendChild(input("reprint", reprint));
    		form.submit();
 			$("#copyprintPaymentReceiptForm").remove();
    		categoryName="COPY";
    		  var trsreprintDTO={
					receiptid:o.id,
					reason:reason,
					categoryName:categoryName,  //COPY, SUBSTITUE , REPRINT
					approvedby:approvedby,
					password:inputPass
					
			}
    		$.ajax({
				  type: "POST",
				  url: "../saveReprint.json",
				  data: JSON.stringify(trsreprintDTO),
				  dataType: "json",
				  async:false,
				  contentType: "application/json; charset=utf-8",
				  success:function(data){
				  	console.log("OK");
					console.log(data);
					if(data.Status == 'SUCCESS'){
		
				
						  $( "#copyprintPaymentReceiptForm" ).submit();
						  $("#copyprintPaymentReceiptForm").remove();
						
					}else{
						 view.dialog.mainMessageDialog.error(["ไม่สามารถอนุมัติ ทำรายการได้"]).show();
						 $("#copyprintPaymentReceiptForm").remove();
					}
					view.dialogReprintReceipt.hide()
					
				  }
				});
    	
    	}
    console.log(categoryName)
    console.log(reason)
    console.log(approvedby)
 	// save printting history
 	var  o = window["receiptSelected"];
	
    //var o = window["receiptSelected"];
    //window.open("../reprintPaymentReceipt.pdf?id="+o.id;, "Print Form", null, null);
    
}
function showHideElement(){
    var category=$('input[name=RadioOptions2]:checked').val()
    
    if(category=='manuscript'){
        $("#manuscriptElement").show();
    }else
        $("#manuscriptElement").hide();
}
function printCopyOfReceiptDialogClickEvent() { 
	$('#approveBy1').val('');
    $('#inputPass1').val('');
    $('#repPrint').attr('checked', false);
    findListReason("#inputPrintCopyReceiptReason");
	view.dialogPrintCopyOfReceipt.show();
	var substitute="false";

    var  o = window["receiptSelected"];
    // save printting history
    var trsreprintDTO={
        receiptid:o.id
    }

    $.ajax({
        type: "POST",
        url: "../chkRepairPrint.json",
        data: JSON.stringify(trsreprintDTO),
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        success:function(data){
            if(data>0){
                console.log("unDisabled");
                $("#repPrint").attr("disabled", false);
            } else {
                console.log("Disabled");
                $("#repPrint").attr("disabled", true);
            }
        }
    });
}
	
function printCopyOfReceiptClickEvent() {
    view.dialog.mainMessageDialog.clear();
    var category=$('input[name=RadioOptions2]:checked').val()
    var inputPrintCopyReceiptReason=$("#inputPrintCopyReceiptReason").val();
    var approveBy=$("#approveBy1").val();
     var inputPass1=$("#inputPass1").val();
    var reprint="0";
    if($("#repPrint").prop('checked')){
        reprint="1";
    }
    
    //alert(inputPrintCopyReceiptReason)
    var substitute="false";
   /*  if(category=='substitute')*/
    	substitute="true"; 
    //alert("AOE"+substitute)
    console.log("inputPrintCopyReceiptReason["+inputPrintCopyReceiptReason+"]"+
            "approveBy["+approveBy+"]"+
            "reprint["+reprint+"]");
   // return false;
    var receiptFormat="receive_wh"; // receive + wh [receive_wh] , receive only [receive_only] ,  wh only [wh_only]
	
    var  o = window["receiptSelected"];
	// save printting history
	var trsreprintDTO={
			receiptid:o.id,
			reason:inputPrintCopyReceiptReason,
			categoryName:"SUBSTITUE", //COPY, SUBSTITUE , REPRINT
			approvedby:approveBy,
			password:inputPass1,
            reprintflg:reprint
	}
	//alert(trsreprintDTO)
	$.ajax({
		  type: "POST",
		  url: "../saveReprint.json",
		  data: JSON.stringify(trsreprintDTO),
		  dataType: "json",
		  contentType: "application/json; charset=utf-8",
		  success:function(data){
			console.log(data);
			if(data.Status == 'SUCCESS'){
					$(document.body).append('<form accept-charset="UTF-8" id="copyprintPaymentReceiptForm" action="../copyprintPaymentReceipt.pdf" method="post" target="_printForm"></form>');
						//var form = document.forms[0];
						var form = document.getElementById('copyprintPaymentReceiptForm');
						function input(n,v) { var input = document.createElement("input"); input.type = "hidden"; input.name = n; input.value = v; return input }
						var i = 0;
						form.appendChild(input("id", o.id));
						form.appendChild(input("substitute", substitute));
						form.appendChild(input("receiptFormat", receiptFormat));
						form.appendChild(input("reason", inputPrintCopyReceiptReason));
						form.appendChild(input("approveBy", approveBy));
						form.appendChild(input("reprint", reprint));
						form.submit();
						
						$("#copyprintPaymentReceiptForm").remove();
				}else{
						view.dialog.mainMessageDialog.error(["ไม่สามารถอนุมัติ ทำรายการได้"]).show();
				}
				view.dialogPrintCopyOfReceipt.hide()
			 }
		});
	
    /*
    get("../service/receipts/search/no", { no: "" }, function(){
        $(document.body).append('<form action="../printPaymentOthersReceipt.pdf" method="post" target="_printForm"></form>');
        var form = document.forms[0]; function input(n,v) { var input = document.createElement("input"); input.type = "hidden"; input.name = n; input.value = v; return input }
        $.each(res.data, function(i,o){
            form.appendChild(input("receipts["+ i +"].no", o.no));
            form.appendChild(input("receipts["+ i +"].name", o.name));
            form.appendChild(input("receipts["+ i +"].accountNo", o.accountNo));
            form.appendChild(input("receipts["+ i +"].accountName", o.accountName));
            form.appendChild(input("receipts["+ i +"].taxNo", o.taxNo));
            form.appendChild(input("receipts["+ i +"].paymentCase", o.paymentCase));
            form.appendChild(input("receipts["+ i +"].branchName", o.branchName));
            form.appendChild(input("receipts["+ i +"].shopPayment", o.shopPayment));
            form.appendChild(input("receipts["+ i +"].addrLine1", o.addrLine1));
            form.appendChild(input("receipts["+ i +"].addrLine2", o.addrLine2));
            form.appendChild(input("receipts["+ i +"].remark", o.remark));
            form.appendChild(input("receipts["+ i +"].amount", o.amount));
            form.appendChild(input("receipts["+ i +"].discount", o.discount));
            form.appendChild(input("receipts["+ i +"].charge", o.charge));
            form.appendChild(input("receipts["+ i +"].vatRate", o.vatRate));
            form.appendChild(input("receipts["+ i +"].vat", o.vat));
            form.appendChild(input("receipts["+ i +"].totalCharge", o.totalCharge));
            form.appendChild(input("receipts["+ i +"].balanceDue", o.balanceDue));
            form.appendChild(input("receipts["+ i +"].totalPaid", o.totalPaid));
            form.appendChild(input("receipts["+ i +"].received", o.received));
            form.appendChild(input("receipts["+ i +"].change", o.change));
            form.appendChild(input("receipts["+ i +"].advanced", o.advanced));
            $.each(o.services, function(j,p){
                form.appendChild(input("receipts["+ i +"].services["+ j +"].productCode", p.productCode));
                form.appendChild(input("receipts["+ i +"].services["+ j +"].productName", p.productName));
                form.appendChild(input("receipts["+ i +"].services["+ j +"].productSubCode", p.productSubCode));
                form.appendChild(input("receipts["+ i +"].services["+ j +"].productSubName", p.productSubName));
                form.appendChild(input("receipts["+ i +"].services["+ j +"].incomeType", p.incomeType));
                form.appendChild(input("receipts["+ i +"].services["+ j +"].amount", p.amount));
                form.appendChild(input("receipts["+ i +"].services["+ j +"].discount", p.discount));
                form.appendChild(input("receipts["+ i +"].services["+ j +"].vat", p.vat));
                form.appendChild(input("receipts["+ i +"].services["+ j +"].totalCharge", p.totalCharge));
                form.appendChild(input("receipts["+ i +"].services["+ j +"].deduction", p.deduction));
            });
        });
        form.submit();
    }, view.dialog.mainMessageDialog)
    */
}

function findListReason(id){
    var categoryReason="reason.service.category";
    if(id=="#inputReprintReason"){
        categoryReason="reason.repeate.service.category";
    }
    /* */
	// $.getJSON("../findServiceReasonCategoryList.json").done(function(response){
    $.getJSON("../findServiceReasonByCategoryList.json",{"category":categoryReason}).done(function(response){
		console.log("ID: "+id);
		$(id).empty(); 
		$(id).append(new Option("กรุณาเลือกเหตุผล", "")); 
		$.each(response.data, function(i, item) { 
		var option = new Option(item.descFullTh, item.descFullTh); 
		//console.log("code:"+item.code+"  desc:"+item.descFullTh);
		$(id).append($(option)); 
		});	
	});

}
function calculateAmt(res) {
    var receiptsNew = [];
    for(i=0;i< res.content.length;i++){
        var receiptDtl = res.content[i];
        var paidAmt = parseFloat(receiptDtl.totalCharge)-(parseFloat(receiptDtl.afterSaleDiscount!=null?receiptDtl.afterSaleDiscount:0)+parseFloat(receiptDtl.afterSaleDiscVat!=null?receiptDtl.afterSaleDiscVat:0));
        receiptDtl.totalCharge = paidAmt;
        receiptDtl.received = paidAmt;
        receiptsNew.push(receiptDtl);
    }
    res.content = receiptsNew;
    return res;
}
function modalPopupRemark(remark){
    if(remark != "null"){
        document.getElementById("remark").textContent=remark;
    }else{
        document.getElementById("remark").textContent="ไม่มีหมายเหตุ";
    }
    view.dialog.remarkModal.show();
}
</script>
</html>
