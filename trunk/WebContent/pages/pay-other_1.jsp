<!DOCTYPE html>
<%@page import="th.net.cat.epis.controller.EpContextHolder"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <link href="resources/bootstrap-table/src/bootstrap-table.css" rel="stylesheet" type="text/css" />
    <link href="resources/custom.css" rel="stylesheet" type="text/css" />
    <link href="resources/select2.min.css" rel="stylesheet" type="text/css"/>
    <script src="resources/jquery.min.js" type="text/javascript"></script>
    <script src="resources/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="resources/bootstrap-table/src/bootstrap-table.js"></script>
    <script src="resources/custom.js" type="text/javascript"></script>
    <script src="resources/select2.min.js" type="text/javascript"></script>
    <style>
        .pointer {
            cursor: pointer;
        }
    </style>
</head>
<body>
<header class="header_page"></header>
<section class="container-fluid menu">
    <!-- <button class="menu-toggle btn btn-sm btn-default" style="margin-bottom: 5px"><span class="glyphicon glyphicon-step-forward"></span></button> -->
    <%-- <%@include  file="menu.jsp" %> --%>
    <div class="row">
        <div class="col-md-12 tab-modefile">
            <ol class="breadcrumb">
                <li><i>รับชำระอื่นๆ</i></li>
                <li class="active">ค้นหาข้อมูลลูกค้า</li>
                <li>สรุปรายการรับชำระเงิน</li>
                <li>เลือกวิธีการรับชำระ</li>
                <li>ผลการรับชำระ</li>
            </ol>
            <div id="message"></div>
            <ul class="nav nav-tabs " role="tablist">
                <li role="presentation" class="active"><a href="#tab_cus" aria-controls="tab_cus" role="tab" data-toggle="tab"><span class="glyphicon glyphicon-filter"></span> ค้นหาข้อมูล</a></li>
            </ul>
            <div class="panel panel-default panal-radius">
                <div class="panel-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-horizontal">
                                <div class="form-group">
                                    <label class="control-label col-sm-2">เลขที่ลูกค้า (Customer Account) :</label>
                                    <div class="col-sm-2"><input id="inputBillNo" class="form-control" disable></div>
                                    <div class="col-sm-8">
                                        <a id="buttonSearchBillNo" class="btn btn-primary"><span class="glyphicon glyphicon-search"></span> ค้นหา</a>&nbsp;&nbsp;
                                        <a id="advanceSearch" class="btn btn-success" data-toggle="modal" data-target="#customerSearch"><span class="glyphicon glyphicon-zoom-in"></span> ค้นหาเพิ่มเติม</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <ul id="panelNavigation" class="list-inline pull-right list-menu-set" style="display: none">
        <li><a id="buttonSummaryPayment" class="btn btn-link"><span class="glyphicon glyphicon-th-list"></span> สรุปรายการรับชำระ</a><span class="badge badge_modefil">0</span></li>
        <li><a id="buttonProcessPayment" class="btn btn-link"><span class="glyphicon glyphicon-edit"></span> ดำเนินการรับชำระ</a></li>
    </ul>
    <div class="row">
        <div id="panelCustomerInfo" class="col-md-12 tab-modefile" style="display: none">
            <ul class="nav nav-tabs" role="tablist">
                <li role="presentation" class="active"><a aria-controls="tab_cus" role="tab" data-toggle="tab"><span class="glyphicon glyphicon-user"></span> ข้อมูลลูกค้า</a></li>
            </ul>
            <div class="tab-content">
                <div role="tabpanel" class="tab-pane active" id="tab_cus">
                    <div class="panel panel-default panal-radius">
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="form-horizontal">
                                        <div class="form-group">
                                            <label class="control-label col-sm-2">เลขที่ลูกค้า :</label>
                                            <div class="col-sm-2"><input id="inputCustomerBillNo" class="form-control" disabled="disabled"></div>
                                            <label class="control-label col-sm-2">ชื่อลูกค้า :</label>
                                            <div class="col-sm-5"><input id="inputCustomerName" class="form-control" disabled="disabled"></div>
                                            <input type="hidden" id="inputCustomerType">
                                        </div>

                                        <div class="form-group">
                                            <label class="control-label col-sm-2">Tax ID :</label>
                                            <div class="col-sm-2"><input id="inputCustomerTaxNo" maxlength="13" class="form-control" disabled="disabled"></div>
                                            <label class="control-label col-sm-2">สาขา :</label>
                                            <div class="col-sm-2"><input id="inputCustomerBranch" maxlength="5" class="form-control" disabled="disabled"></div>
                                        </div>

                                        <div class="form-group">
                                            <label class="control-label col-sm-2">กลุ่มผู้ใช้บริการ :</label>
                                            <div class="col-sm-2">
                                                <select class="form-control" id="inputCustomerSegment" disabled="disabled"></select>
                                            </div>
                                            <label class="control-label col-sm-2">VAT Rate :</label>
                                            <div class="col-sm-2">
                                                <select id="inputCustomerVatRate" class="form-control" disabled="disabled">
                                                    <option>7</option>
                                                    <option>0</option>
                                                    <option>Non VAT</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-2">ที่อยู่ :</label>
                                            <div class="col-sm-6"><textarea id="inputCustomerAddress" class="form-control" disabled="disabled"></textarea></div>
                                            <div class="col-sm-3">
                                                <div class="checkbox">
                                                    <label> <input type="checkbox" id="isAllowEdit" name="isAllowEdit" onclick="allowEditCustomerData()">แก้ไขข้อมูลลูกค้าเพื่อแสดงในใบเสร็จ</label>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-sm-6"><input id="inputCustomerGroupCode" class="form-control" type="hidden"></input></div>
                                            <div class="col-sm-6"><input id="inputCustomerGroupName" class="form-control" type="hidden"></input></div>
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
    <div id="panelReceiptDetails" class="row" style="display: none">
        <div class="col-md-12 tab-modefile">
            <!-- Nav tabs -->
            <ul class="nav nav-tabs" role="tablist">
                <li role="presentation" class="active"><a href="#tab_1"
                                                          aria-controls="tab_1" role="tab" data-toggle="tab"><span
                        class="glyphicon glyphicon-shopping-cart"></span> รายการรับชำระ</a></li>
                <!--<li role="presentation" class=""><a href="#tab_2"
                    aria-controls="tab_2" role="tab" data-toggle="tab" onClick="copyCustomerProfile()"><span
                        class="glyphicon glyphicon-list"></span> รายละเอียดใบเสร็จรับเงิน</a></li>-->
            </ul>
            <!-- Tab panes -->
            <div class="panel panel-default panal-radius">
                <div class="panel-body">
                    <div class="tab-content">
                        <div role="tabpanel" class="tab-pane active" id="tab_1">
                            <div class="form-horizontal">
                                <div class="form-group">
                                    <label class="control-label col-sm-2">ประเภทรายได้ :</label>
                                    <div class="col-sm-2" id="serviceNameDiv"><select id="inputServiceType" class="form-control"></select></div>
                                    <label class="control-label col-sm-2">หน่วยงานรับรายได้ :</label>
                                    <div class="col-sm-2" id="serviceDepartmentDiv">
                                        <select id="inputServiceDepartment" class="form-control"></select>
                                    </div>
                                    <label class="control-label col-sm-2">เงินส่วนลดก่อน VAT :</label>
                                    <div class="input-group col-sm-2" style="width: 15.9%; padding-left: 15px;">
                                    	<span class="input-group-addon" style="padding:2px 4px;">฿</span>
                                    	<input id="inputServiceDiscount" class="form-control text-right">
                                   	</div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-2">ชื่อบริการ :</label>
                                    <div class="col-sm-2" id="serviceNameDiv"><select class="form-control" id="inputServiceName"></select></div>
                                    <label class="control-label col-sm-2">จำนวนรายการ :</label>
                                    <div class="col-sm-2"><input id="inputServiceMoreData" class="form-control text-right"></div>
                                    <div class="col-sm-1"><select id="inputServiceUnit" class="form-control"></select></div>
                                    <label class="control-label col-sm-1"><input type="checkbox" hidden="hidden" name="checkboxAdditionalDiscount"><span class="glyphicon glyphicon-lock"></span>&nbsp;ส่วนลดพิเศษ :</label>
                                    <div class="col-sm-2" style="padding-left: 15px;">
	                                    <div class="input-group">
	                                    	<span class="input-group-addon" style="padding:2px 4px;">฿</span>
	                                    	<input id="inputSpecialDiscount" class="form-control text-right" disabled>
	                                   	</div>
                                   	</div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-2">จำนวนเงินต่อหน่วย :</label>
                                    <div class="col-sm-2" style="padding-left: 15px;">
	                                    <div class="input-group">
	                                    	<span class="input-group-addon" style="padding:2px 4px;">฿</span>
	                                    	<input id="inputServiceAmount" class="form-control">
	                                   	</div>
                                   	</div>
                                    <label class="control-label col-sm-2">ภาษีหัก ณ ที่จ่าย :</label>
                                    <div class="col-sm-2" style="padding-left: 15px;">
	                                    <div class="input-group">
	                                    	<span class="input-group-addon" style="padding:2px 4px;">฿</span>
	                                    	<input id="inputServiceDeduction" class="form-control text-right">
	                                   	</div>
                                   	</div>
                                    <div class="col-sm-1"><input type="button" id="buttonCalculateWt" class="btn btn-info" value="คำนวณ"></div>
                                    <div class="col-sm-2 col-sm-offset-1"><a id="buttonAddBillingList" class="btn btn-info"> <span class="glyphicon glyphicon-plus-sign"></span> เพิ่มรายการรับชำระ</a></div>
                                </div>
                                <div class="form-group">
                                    <div class="radio col-sm-2 col-sm-offset-2">
                                        <label><input type="radio" name="vatRadio" value="exclude" checked><b>ก่อน vat</b></label>
                                        <label><input type="radio" name="vatRadio" value="include"><b>หลัง vat</b> </label>
                                    </div>
                                    <label class="control-label col-sm-2">ประเภทสกุลเงิน :</label>
                                    <div class="col-sm-5">
                                        <select id="currencySelect" class="form-control"></select>
                                        <span id="txtRate" style=" display:none;"></span>
                                    </div>
                                </div>
                            </div>
                            <br /> <br />

                            <table id="tableBillingList" data-row-style="rowStyle" data-toggle="table"
                                   data-classes="table table-hover table-striped" data-pagination="true" data-page-size="5" data-page-list="[5, 10, 20, 50, 100, 200]" data-cache="false">
                                <thead>
                                <tr>
                                    <th data-align="center" data-field="id" data-formatter="runningFormatter">#</th>
                                    <th data-field="serviceType">ประเภทบริการ</th>
                                    <th data-field="serviceName" data-formatter="stringInputFormatter">ชื่อบริการ</th>
                                    <th data-field="serviceMoreData" data-align="right">จำนวนรายการ</th>
                                    <th data-field="serviceAmount" data-align="right" data-formatter="numberFormatter">จำนวนเงินต่อหน่วย (ก่อน Vat)</th>
                                    <th data-field="serviceDiscount" data-align="right" data-formatter="numberFormatter">เงินส่วนลด</th>
                                    <th data-field="serviceVat" data-align="right" data-formatter="numberFormatterForVat">ภาษีมูลค่าเพิ่ม</th>
                                    <th data-field="serviceDeduction" data-align="right" data-formatter="numberFormatter">ภาษีหัก ณ ที่จ่าย</th>
                                    <th data-field="serviceTotalCharge" data-align="right" data-formatter="numberFormatter">ยอดเงินรวม</th>
                                    <th data-field="serviceSpecialDiscount" data-align="right" data-formatter="numberInputAuthenFormatter">ส่วนลดพิเศษ</th>
                                    <th data-align="center" data-formatter="operateButtonFormatter"></th>
                                </tr>
                                </thead>
                            </table>
                        </div>
                        <!--
                        <div role="tabpanel" class="tab-pane" id="tab_2">
                            <div class="form-horizontal">
                                <div class="form-group">
                                    <label class="control-label col-sm-2">เลขที่ลูกค้า :</label>
                                    <div class="col-sm-2">
                                        <input class="form-control" id="inputCustomerCustNo1" disabled="disabled">
                                    </div>
                                    <label class="control-label col-sm-2">ชื่อลูกค้า :</label>
                                    <div class="col-sm-4">
                                        <input class="form-control" id="inputCustomerName1" disabled="disabled">
                                    </div>
                                    <div class="col-sm-2">
                                    <div class="checkbox">
                                        <label> <input type="checkbox" id="isAllowEdit" name="isAllowEdit" onclick="allowEditCustomerData()">แก้ไขข้อมูลลูกค้า</label>
                                    </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-2">Tax ID :</label>
                                    <div class="col-sm-2">
                                        <input class="form-control" id="inputCustomerTaxNo1" maxlength="13" disabled="disabled">
                                    </div>
                                    <label class="control-label col-sm-2">สาขาที่  :</label>
                                    <div class="col-sm-2">
                                        <input class="form-control" id="inputCustomerBranch1" maxlength="5" disabled="disabled">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-2">กลุ่มผู้ใช้บริการ  :</label>
                                    <div class="col-sm-2">
                                        <select class="form-control" id="inputCustomerGroup1" disabled="disabled"></select>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-2">ที่อยู่ :</label>
                                    <div class="col-sm-6">
                                        <textarea class="form-control" id="inputCustomerAddress1" name="inputCustomerAddress1" disabled="disabled"></textarea>
                                    </div>										
                                </div>
                            </div>
                            <br /> <br />
                        </div>-->
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div id="panelSummaryInfo" class="row" style="display: none">
        <div class="col-md-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <span class="glyphicon glyphicon-shopping-cart"></span>
                    สรุปยอดเงินที่ต้องชำระ
                </div>
                <div class="panel-body">
                    <div class="form-horizontal">
                        <!-- <input id="inputAmount" class="form-control text-right" disabled> -->
                        <div class="form-group">
                            <label class="control-label col-sm-10">ยอดเงินก่อนหักส่วนลด  :</label>
                            <div class="col-sm-2"><input id="preItemsDiscount" class="form-control text-right" disabled></div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-10">ส่วนลด :</label>
                            <div class="col-sm-2"><input id="itemsDiscount" class="form-control text-right" disabled></div>
                        </div>
                        <div class="form-group">
                        	<label class="control-label col-sm-10">ส่วนลดพิเศษ  :</label>
                            <div class="col-sm-2"><input id="inputAdditionalDiscount" class="form-control text-right" disabled></div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-10">ยอดเงินที่ต้องชำระก่อนภาษีมูลค่าเพิ่ม  :</label>
                            <div class="col-sm-2"><input id="inputCharge" class="form-control text-right" disabled></div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-10">ภาษีมูลค่าเพิ่ม  :</label>
                            <div class="col-sm-2"><input id="inputVat" class="form-control text-right" disabled></div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-10">ยอดเงินที่ต้องชำระรวมภาษีมูลค่าเพิ่ม :</label>
                            <div class="col-sm-2"><input id="inputTotalCharge" class="form-control text-right" disabled></div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-10">ภาษีหัก ณ ที่จ่าย :</label>
                            <div class="col-sm-2"><input id="inputDeduction" class="form-control text-right" disabled></div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-2">ข้อความเพิ่มเติมในใบเสร็จ :</label>
                            <div class="col-sm-5"><input id="inputAdditionalRemark" class="form-control"></div>
                            <%--<label class="control-label col-sm-3"><input type="checkbox" name="checkboxAdditionalDiscount">&nbsp;&nbsp;ลูกค้ารับภาระภาษี &nbsp;&nbsp;<span class="glyphicon glyphicon-lock"></span>&nbsp;ส่วนลด :</label>
                            <div class="col-sm-2"><input id="inputAdditionalDiscount" class="form-control text-right" disabled></div>--%>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- --------- -->
<div class="modal fade" role="dialog" id="customerSearch">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><span class="glyphicon glyphicon-user"></span> ค้นหาลูกค้า</h4>
            </div>
            <div class="modal-body">
                <div id="advanceSearchMessageDialog"></div>
                <div class="tab-modefile">
                    <ul class="nav nav-tabs" role="tablist">
                        <li role="presentation" class="active"><a href="#messages" aria-controls="messages" role="tab" data-toggle="tab">ข้อมูลลูกค้า</a></li>
                    </ul>
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="tab-content border-tab-content">
                                <div role="tabpanel" class="tab-pane active" id="messages">
                                    <div class="form-horizontal">
                                        <div class="form-group">
                                            <label class="control-label col-sm-3">เลขที่ลูกค้า (CA):</label>
                                            <div class="col-sm-3"><input class="form-control" id="advSrcCusNo"></div>
                                            <label class="control-label col-sm-3">เลขประจำตัวผู้เสียภาษี :</label>
                                            <div class="col-sm-3"><input class="form-control" id="advSrcCusTaxId"></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-3">ชื่อลูกค้า/ชื่อนิติบุคคล/ราชการ :</label>
                                            <div class="col-sm-9"><input class="form-control" id="advSrcCusFirstName"></div>
                                            <!--
                                            <label class="control-label col-sm-3">นามสกุล :</label>
                                            <div class="col-sm-3"><input class="form-control" id="advSrcCusLastName"></div>
                                            -->
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-3">กลุ่มผู้ใช้บริการ :</label>
                                            <div class="col-sm-3">
                                                <select class="form-control" id="customerSegmentSearch"></select>
                                            </div>
                                            <div class="col-sm-3">
                                                <a id="advSrcCusNoBtn" class="btn btn-info pull-right"><span class="glyphicon glyphicon-search"></span> ค้นหา</a>
                                            </div>
                                        </div>
                                    </div>

                                    <br>

                                    <div class="row">
                                        <div class="col-md-12">
                                            <table id="advSrcCusNoResultList" class="table table-hover" data-toggle="table" data-pagination="true" data-page-size="5" data-page-list="[5, 10, 20, 50, 100, 200]" data-cache="false">
                                                <thead>
                                                <tr>
                                                    <th data-formatter="runningFormatter">#</th>
                                                    <th data-field="customerId">เลขที่ลูกค้า (CA)</th>
                                                    <th data-field="customerAccountName">ชื่อลูกค้า</th>
                                                    <th data-field="customerTaxNo">TAX ID</th>
                                                    <th data-field="customerSegment">กลุ่มผู้ใช้บริการ</th>
                                                    <th data-align="center" data-formatter="SelectButton"></th>
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
                <%--<i class="pull-left"><span class="glyphicon glyphicon-filter"></span>
                    กรุณาป้อนข้อมูลที่ต้องการค้นหาอย่างน้อย 4 ตัวอักษร
                </i>--%>
                <br>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
	$("#inputServiceType").select2();
    $("#inputServiceName").select2();
    $('#inputServiceDepartment').select2();
    $('#currencySelect').select2();
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
        window.numberFormatterForVat = function(val, row, ind) {
            if(val == '0'){
                return "0.00";
            }else if(val == null){
                return "-";
            }
            else{
                return !$.isNumeric(stripToNumber(val)) ? "0.00" : parseFloat(val, 10).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,')
            }
        }
        window.stringInputFormatter = function(val, row, ind) { var id = row.id; console.log(id); return '<input value="'+ $.trim(val) +'" maxLength="100" class="form-control" onkeyup="updateTableBillingList('+id+', this.value)">' }
        window.numberInputFormatter = function(val, row, ind) { return '<input value="'+ self.utils.numberFormat(parseFloat(val || "0.00", 10)) +'" maxLength="10" style="width:100px" class="form-control text-right" onkeypress="return $.isNumeric(String.fromCharCode(event.which)) || (event.which == 46 && this.value.indexOf(\'.\') == -1)" onfocus="(function(o){ o.value = o.value.replace(/[,]/g, \'\') })(this)" onblur="(function(o){ o.value = parseFloat(o.value, 10).toFixed(2).replace(/(\\d)(?=(\\d{3})+\\.)/g, \'$1,\'); })(this)">' }
        window.numberInputAuthenFormatter = function(val, row, ind) { var id = row.id; return '<input id="itemSpecialDisc" name="itemSpecialDisc" value="'+ self.utils.numberFormat(parseFloat(val || "0.00", 10)) +'" maxLength="10" style="width:100px" class="form-control text-right" onchange="sumSpecialDiscount('+id+', this.value)" onkeypress="return $.isNumeric(String.fromCharCode(event.which)) || (event.which == 46 && this.value.indexOf(\'.\') == -1)" onfocus="(function(o){ o.value = o.value.replace(/[,]/g, \'\') })(this)" onblur="(function(o){ o.value = parseFloat(o.value, 10).toFixed(2).replace(/(\\d)(?=(\\d{3})+\\.)/g, \'$1,\'); })(this) " disabled >' }
        window.moneyInputFormatter = function(val, row, ind) { var value = parseFloat(val || "0.00", 10).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, "$1,"); return '<input value="'+ value +'" maxLength="10" style="width:100px" class="form-control text-right" onkeypress="return $.isNumeric(String.fromCharCode(event.which)) || (event.which == 46 && this.value.indexOf(\'.\') == -1)" onkeyup="if (event.which == 13) this.blur()" onfocus="(function(o){ o.value = o.value.replace(/[,]/g, \'\'); o.select() })(this)" onblur="(function(o){ o.value = parseFloat(o.value, 10).toFixed(2).replace(/(\\d)(?=(\\d{3})+\\.)/g, \'$1,\'); o.style.backgroundColor = o.value !== \''+ value +'\' ? \'yellow\' : \'\'; var table, elem = o; while (table == null) { elem = elem.parentNode; if (elem.tagName == \'TABLE\') table = elem; } (window[table.id +\'InputBlurEvent\'] || function(t){ console.log(t) })(table) })(this)">' }
        window.modifyButtonFormatter = function(val, row, ind) { return '<a class="btn btn-link modList" onclick=\'(function(elem, row, ind){ var table = $("#"+ $(elem).closest("table")[0].id); window[table[0].id +"ModifyEvent"](table, row, ind) })(this, '+ JSON.stringify(row) +', '+ ind +')\'><span class="glyphicon glyphicon-pencil"></span></a>' }
        window.removeButtonFormatter = function(val, row, ind) { return '<a class="btn btn-link delList" onclick=\'(function(elem, row, ind){ $("#"+ $(elem).closest("table")[0].id).bootstrapTable("remove", { field: "index", values: [ind] }) })(this, '+ JSON.stringify(row) +', '+ ind +')\'><span class="glyphicon glyphicon-trash"></span></a>' }
        window.LoadingPanel = function(id) { var obj = this, loadCnt = 0, setupFunc, loadFunc; function checkElemReady(){ if ((obj.elem = $("#"+ id).css("font", "")).length != 1) { alert("The element hasn't insert into HTML DOM."); throw "The element hasn't insert into HTML DOM."; } clearTimeout(setupFunc); clearInterval(loadFunc) }; return { "elem": null, "finish": function(html){ checkElemReady(); (this.elem ? this.elem : this.elem = $("#"+ id)).css("font", "").html(html); return this }, "toString": function(){ var elem; setupFunc = setTimeout(function(){ loadFunc = setInterval(function(){ (elem || (elem = document.getElementById(id))).innerHTML = "Loading"+ Array(++loadCnt).join(".."); if (loadCnt > 60) loadCnt = 2 }, 250) }, 1000); return "<div id='"+ id +"' style='font:BOLD 16pt Arial'></div>" } } }
        window.operateButtonFormatter = function(val, row, ind) {
            return "<a class='pointer' onclick='removeFromServiceList("+row.id+")'><span class='glyphicon glyphicon-trash' aria-hidden='true'></span></a>"
        }
        function AuthenticationDialog(succFunc, failFunc, url, title, usrLabel, pwdLabel, okBtn, cnBtn) { var obj = this, httpUrl = url, done = succFunc, fail = failFunc, cancel = function(){}, inputs; obj.el = "modal_authentication";
            var content = '<div class="row"><div class="col-md-12"><div class="col-md-offset-2 col-md-10"><div class="form-horizontal"><div class="form-group"><label class="col-sm-3 control-label">'+ (usrLabel || "User Name :") +'</label><div class="col-sm-6"><input type="text" class="form-control"></div></div><div class="form-group"><label class="col-sm-3 control-label">'+ (pwdLabel || "Password :") +'</label><div class="col-sm-6"><input type="password" class="form-control"></div></div></div></div></div></div>';
            var hdrCS = '<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>';
            var hdrTT = '<h4 class="modal-title"><span class="glyphicon glyphicon-user"></span> '+ (title || "Authentication") +'</h4>';
            var divMG = '<div class="alert alert-danger hide"></div>';
            var btnOK = '<button type="button" class="btn btn-success"><span class="glyphicon glyphicon-ok-circle"></span> '+ (okBtn || "OK") +'</button>';
            var btnCN = '<button type="button" class="btn btn-danger" data-dismiss="modal"><span class="glyphicon glyphicon-remove-circle"></span> '+ (cnBtn || "Cancel") +'</button>';
            obj.elem = $(document.body).append('<div class="modal fade" role="dialog" data-backdrop="static" id="'+ obj.el +'"><div class="modal-dialog"><div class="modal-content"><div class="modal-header">'+ hdrCS + hdrTT +'</div><div class="modal-body">'+ divMG + content +'</div><div class="modal-footer">'+ btnOK + btnCN +'</div></div></div></div>').find("#"+ obj.el); inputs = obj.elem.find("input").keyup(function(e){ var key = (e.which || e.keyCode || e.charCode || 0); if (key != 13) return true; obj.elem.find("input, button").eq(e.target.type == "text" ? 2 : 3).focus(); return true }); buttons = obj.elem.find("button");
            buttons.eq(1).on({ click: function(){ $.post(httpUrl, { username: inputs.eq(0).val(), password: inputs.eq(1).val() }, function(res){ if (!res || res.statusCode != "0") { obj.elem.find(".alert").text("You don't have permission to access this feature. Please contact to Administration for more information.").removeClass("hide"); fail(res); return; } done(res); obj.hide() }) } }).end().filter(function(index){ return index == 0 || index == 2 }).on({ click: function(){ cancel() } })
            obj.url = function(url){ httpUrl = url; return this }; obj.show = function(){ obj.elem.modal("show"); obj.elem.find("div.alert").addClass("hide").text(""); inputs.val(""); return this }; obj.hide = function(){ obj.elem.modal("hide"); return this }
            obj.done = function(func) { done = func; return this }; obj.fail = function(func) { fail = func; return this }; obj.cancel = function(func) { cancel = func; return this }
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
            /* obj.init = function(url, param) {
                if (url) $.get(url, function(res)
                {if(param == "serviceType") console.log(res); setup(data = res.data, param);}); else setup(data, param); return this }; */
      		obj.init = function(url) { if (url) $.get(url, function(res) { console.log("url : "+url); console.log("url : "+JSON.stringify(res)); setup(data = res.data) }); else setup(data); return this };                
      		obj.disable = function(flag) { obj.elem.attr("disabled", (flag == null ? true : flag)); return this }
    		//obj.init = function(url) { if (url) $.get(url, function(res) { setup(data = res.data) }); else setup(data); return this };

      		obj.enable = function() { obj.disable(false); return this }
            obj.index = function() { if (arguments.length == 1) { var maxLen = data.length, index = arguments[0]; obj.elem.find("option").each(function(i, opt){ opt.removeAttribute("selected") }).eq(index < 0 ? maxLen + index : index).attr("selected", true); return this } return $.inArray("selected", obj.elem.find("option").map(function(i, opt){ return opt.selected ? "selected" : "no-selected" })) }
            obj.selected = function(){ return data[obj.index()]; };
            obj.val = function() { return obj.elem.val(); };
            obj.key = function(){ if (arguments.length == 0) { var key = obj.elem.find("option:selected").data("key"); if (typeof key === "string" && key === "undefined") { var item = data[obj.index()]; key = item && item.key ? item.key : item.id } return key } var key = arguments[0]; for (var i = 0, m = data.length; i < m; i++) { var item = data[i]; if (item.key && item.key == key) { obj.index(i); break; } if (item.id && item.id == key) { obj.index(i); break; } } return this }
            function setup(array, param) {
                obj.elem.find("*").remove();
                $.each(array,function(i,o){
                    if(o.category == 'payothers.service.unit'){
                        obj.elem.append('<option data-index="" data-key="0" value="">ไม่เลือก</option>');
                        return false;
                    }
                    else{
                    	if (o.rateCode!=null) {
                    		return false;
                    	} else {
	                        obj.elem.append('<option data-index="" data-key="" value="">ทั้งหมด</option>');
	                        return false;
                    	}
                    }
                });
    			var tempBA = '${epContext.branchArea}'; 

                $.each(array,function(i,o){
	                if(o.groupKey != null){
						var selected ="";
						if(o.key == tempBA){
						//console.log("o.key : "+o.key+" BA : "+tempBA)
						selected = 'selected="selected"'
						}				
						obj.elem.append('<option data-index="'+ i +'" data-key="'+ o.key +'" data-code="'+ o.key +'" value="'+ o.value +'"'+selected+'>'+ o.property2 +" : " +o.value  +'</option>');
					} else if(o.source != null) {
						o.revenueTypeCode.length==1? code == '0'+o.revenueTypeCode:o.revenueTypeCode;
						obj.elem.append('<option data-index="'+ i +'" data-key="'+ o.revenueTypeCode +'" value="'+o.revenueTypeName +'">'+ o.revenueTypeCode +' - '+ o.revenueTypeName +'</option>')
					} else if(param == "serviceType"){
	                    obj.elem.append('<option data-index="'+ i +'" data-key="'+ o.revenueType +'" value="'+ o.description +'">'+ o.revenueType +' - '+ o.description +'</option>')
	                } else if(param == "serviceName"){
	                    obj.elem.append('<option data-index="'+ i +'" data-key="'+ o.serviceCode + '" data-glCode="'+ o.glCode +'" data-prodCode="'+ o.productCode +'" data-prodName="'+ o.productName +'" data-subProdCode="'+ o.subProductCode +'" data-subProdName="'+ o.subProductName +'" data-segCode="'+ o.segmentCode +'" data-segName="'+ o.segmentName +'" value="'+ o.serviceName +'">'+ o.serviceName +'</option>')
	                } else if(o.category == 'payothers.service.category' || o.category == 'payothers.service.name' || o.category == 'payothers.service.unit') {
	                    obj.elem.append('<option data-index="'+ i +'" data-key="'+ o.code +'" value="'+ o.descFullTh +'">'+ o.descFullTh +'</option>')
	                } else if(o.category == 'branch.central'|| o.category == 'business.place') {
	                    obj.elem.append('<option data-index="'+ i +'" data-key="'+ o.mapCode2 +'" data-code="'+ o.mapCode1 +'" value="'+ o.descAbvrTh+' '+o.descFullTh +'">'+ o.mapCode2+' - '+o.descAbvrTh+' '+o.descFullTh +'</option>')
	                } else if(o.rateCode!=null){
                        x = i;
                        x++;
                        obj.elem.append('<option data-index="' + x + '" data-key="' + x + '" value="' + o.code + '" data-rate="'+ o.rateUnit +'" data-date="'+ o.dateUsed +' " data-message="'+ o.message +'" data-symbol="'+ o.currencySymbol +'">' + o.message + '</option>');                        
                        $('#currencySelect').val("th_TH");
                    } else{
	                    obj.elem.append('<option data-index="'+ i +'" data-key="'+ (o.key||o.id) +'" value="'+ (o.key||o.id) +'">'+ (o.text || o.value) +'</option>')
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
        // TO SUPPORT ECLIPSE OUTLINE.
        self.message = new Message("#message");
        self.dialogAuthentication = new AuthenticationDialog().url("../checkAuthorize.json");
        self.buttonSearchBillNo = new Button("#buttonSearchBillNo");
        self.buttonSummaryPayment = new Button("#buttonSummaryPayment");
        self.buttonProcessPayment = new Button("#buttonProcessPayment");
        self.buttonCalculateWt = new Button("#buttonCalculateWt");
        self.buttonAddBillingList = new Button("#buttonAddBillingList");
        self.inputBillNo = new Input("#inputBillNo");
        self.inputCustomerBillNo = new Input("#inputCustomerBillNo");
        self.inputCustomerName = new Input("#inputCustomerName");
        self.inputCustomerType = new Input("#inputCustomerType");
        self.inputCustomerTaxNo = new Input("#inputCustomerTaxNo");
        self.inputCustomerBranch = new Input("#inputCustomerBranch");
        self.inputCustomerGroupCode = new Input("#inputCustomerGroupCode");
        self.inputCustomerGroupName = new Input("#inputCustomerGroupName");
        self.inputCustomerSegment = new DropDown("#inputCustomerSegment").init("../findAccountCategoryList.json");//init("../findCutomerSegmentList.json");
        self.customerSegmentSearch = new DropDown("#customerSegmentSearch").init("../findCustomerSegmentList.json");//init("../findAccountCategoryList.json");

        self.inputCustomerVatRate = new DropDown("#inputCustomerVatRate").data([{ key: "2", value: "0%" },{ key: "3", value: "Non VAT" }]);
        self.inputCustomerAddress = new Input("#inputCustomerAddress");
        self.inputCustomerCustNo1 = new Input("#inputCustomerCustNo1");
        self.inputCustomerName1 = new Input("#inputCustomerName1");
        self.inputCustomerTaxNo1 = new Input("#inputCustomerTaxNo1");
        self.inputCustomerBranch1 = new Input("#inputCustomerBranch1");
        self.inputCustomerGroup1 = new DropDown("#inputCustomerGroup1");
        self.inputCustomerAddress1 = new Input("#inputCustomerAddress1");
        self.inputServiceType = new DropDown("#inputServiceType").init("../findBySource.json?source=OTHER");//.data([{ key: "", value: "กรุณาเลือก" }, { key: "1", value: "ค่าถ่ายเอกสาร" }]);
        self.inputServiceName = new DropDown("#inputServiceName")//.init("../loadServiceNameMapGl.json", "serviceName");//.data([{ key: "", value: "กรุณาเลือก" }, { key: "11", value: "ค่าถ่ายเอกสาร", text: "ค่าถ่ายเอกสาร (11010XXXX)" }]);
        self.inputServiceDepartment = new DropDown("#inputServiceDepartment").init("../MasterData/findByGroupKey.json?groupKey=BUSINESS_AREA");// .data([{ key: "", value: "กรุณาเลือก" }, { key: "1", value: "สำนักงานใหญ่" }]);
        self.inputServiceMoreData = new NumberInput("#inputServiceMoreData", 0);
        self.inputServiceUnit = new DropDown("#inputServiceUnit").init("../findServiceUnitList.json")//.data([{ key: "", value: "" }, { key: "1", value: "ชุด" }, { key: "2", value: "ครั้ง" }, { key: "3", value: "ชม." }, { key: "4", value: "นาที" }, { key: "5", value: "วินาที" }]);
        self.inputServiceDiscount = new NumberInput("#inputServiceDiscount");
        self.inputServiceAmount = new NumberInput("#inputServiceAmount");
        self.inputServiceDeduction = new NumberInput("#inputServiceDeduction");
        self.inputAmount = new NumberInput("#inputAmount");
        self.inputCharge = new NumberInput("#inputCharge");
        self.preItemsDiscount = new NumberInput("#preItemsDiscount");
        self.itemsDiscount = new NumberInput("#itemsDiscount");
        self.inputVat = new NumberInput("#inputVat");
        self.inputTotalCharge = new NumberInput("#inputTotalCharge");
        self.inputDeduction = new NumberInput("#inputDeduction");
        self.inputAdditionalDiscount = new NumberInput("#inputAdditionalDiscount");
        self.checkboxAdditionalDiscount = new CheckBox("[name=checkboxAdditionalDiscount]");
        self.panelNavigation = new Panel("#panelNavigation");
        self.panelCustomerInfo = new Panel("#panelCustomerInfo");
        self.panelReceiptDetails = new Panel("#panelReceiptDetails");
        self.panelSummaryInfo = new Panel("#panelSummaryInfo");
        self.tableBillingList = new BootstrapTable("#tableBillingList");
        self.inputAdditionalRemark = new Input("#inputAdditionalRemark");
        self.customerSearch = new Modal("#customerSearch");
        self.advanceSearch = new Button("#advanceSearch");
        self.advSrcCusNoBtn = new Button("#advSrcCusNoBtn");
        self.advanceSearchMessageDialog = new Message("#advanceSearchMessageDialog");
        self.advSrcCusNo = new Input("#advSrcCusNo");
        self.advSrcCusTaxId = new Input("#advSrcCusTaxId");
        self.advSrcCusFirstName = new Input("#advSrcCusFirstName");
        self.advSrcCusLastName = new Input("#advSrcCusLastName");
        self.advSrcOrgName = new Input("#advSrcOrgName");
        self.advSrcCusNoResultList = new BootstrapTable("#advSrcCusNoResultList");
        self.inputSpecialDiscount = new NumberInput("#inputSpecialDiscount");
        self.itemSpecialDisc = new NumberInput("#itemSpecialDisc");
		self.currencySelect = new DropDown('#currencySelect').init("../exchangeRateList.json");

        return this;
    })(jQuery);

    function advanceSearchClickEvent() {
        view.advanceSearchMessageDialog.clear();
        view.advSrcCusNoResultList.hideLoad().clear();
    }

    function advSrcCusNoBtnClickEvent() {
        view.advanceSearchMessageDialog.clear();


        var url = "", params, searchByCustId = false, searchByCustName = false, searchByOrg = false;
        //if (!view.advSrcCusNo.isEmpty())         { url = "../service/bill-profiles/search/no-list"; params = { "no": view.advSrcCusNo.val() }}
        /*
         if (!view.advSrcCusNo.isEmpty())         { url = "../service/cust-profiles/search/no"; params = { "no": view.advSrcCusNo.val() }; searchByCustId = true; }
         else if (!view.advSrcCusTaxId.isEmpty()) { url = "../service/bill-profiles/search/tax"; params = { "tax": view.advSrcCusTaxId.val() }; }
         else if (!view.advSrcCusFirstName.isEmpty() || !view.advSrcCusLastName.isEmpty()) { url = "../findBillProfileByName.json"; params = { "ty": true, "fn": view.advSrcCusFirstName.val() , "ln": view.advSrcCusLastName.val() }; searchByCustName = true}
         else if (!view.advSrcOrgName.isEmpty())  { url = "../findBillProfileByName.json"; params = { "ty": false, "fn": view.advSrcOrgName.val() , "ln": "" }; searchByOrg = true } 
         else { view.advanceSearchMessageDialog.error(["กรุณากรอกรายละเอียดอย่างน้อย 1 ช่องการค้นหา"]).show(); return; }
         */
        console.log("customerSegmentSearch key="+view.customerSegmentSearch.key());
        if(!view.advSrcCusNo.isEmpty() || !view.advSrcCusTaxId.isEmpty() || !view.advSrcCusFirstName.isEmpty()
        //   || !view.customerSegmentSearch.val()
        ){
            url = "../findCustomerProfile.json"; params = {
                "customerFullName": view.advSrcCusFirstName.val() ,
                "customerNumber": view.advSrcCusNo.val(),
                "idRegisterNumber": view.advSrcCusTaxId.val(),
                "catCustomerSegment": view.customerSegmentSearch.key()
            };

        }else{
            view.advanceSearchMessageDialog.error(["กรุณากรอกรายละเอียดอย่างน้อย 1 ช่องการค้นหา"]).show(); return;
        }

        view.advSrcCusNoResultList.clear().showLoad();
        view.advanceSearchMessageDialog.clear().showLoad("ระบบกำลังรอข้อมูลจาก CRM : {timeCnt} วินาที")
        //alert("xxx "+view.advSrcCusNo.isEmpty());
        /*
         if (!view.input.advSrcCusNo.isEmpty())         { url = "../service/cust-profiles/search/no"; params = { "no": view.input.advSrcCusNo.val() }; searchByCustId = true; }
         else if (!view.input.advSrcCusTaxId.isEmpty()) { url = "../service/bill-profiles/search/tax"; params = { "tax": view.input.advSrcCusTaxId.val() } }
         else if (!view.input.advSrcCusFirstName.isEmpty() || !view.input.advSrcCusLastName.isEmpty()) { url = "../findBillProfileByName.json"; params = { "ty": true, "fn": view.input.advSrcCusFirstName.val() , "ln": view.input.advSrcCusLastName.val() }; searchByCustName = true}
         else if (!view.input.advSrcOrgName.isEmpty())  { url = "../findBillProfileByName.json"; params = { "ty": false, "fn": view.input.advSrcOrgName.val() , "ln": "" }; searchByOrg = true }
         else { view.dialog.advanceSearchMessageDialog.error(["กรุณากรอกรายละเอียดอย่างน้อย 1 ช่องการค้นหา"]).show(); return; }
         view.table.advSrcCusNoResultList.clear().showLoad();
         view.dialog.advanceSearchMessageDialog.clear().showLoad("ระบบกำลังรอข้อมูลจาก CRM : {timeCnt} วินาที")
         */
        function ResponseHandler(res) {
            view.advSrcCusNoResultList.hideLoad().data($.map(res._embedded.billProfiles, function(o,i){
                return {
                    "acctNo": o.no
                    ,"customerId": o.customer.no
                    ,"customerAccountName": o.customerAccountName
                    ,"customerTaxNo": o.taxRegisterNo
                    ,"customerSegment": (o.customer.segment!=null)?o.customer.segment.text:""
                    ,"billNo": o.customer.no
                };
            }));
        }

        function CustNoResponseHandler(res) {
            if(res && res._embedded.customerProfiles) {
                var customer = res._embedded.customerProfiles[0];
                console.log("into CustNoResponseHandler-->"+customer.id);
                console.log(customer);
                var url = "../service/bill-profiles/search/custId", params = { "custId": customer.id };
                get(url, params, ResponseHandler, view.advanceSearchMessageDialog, function(){ view.advanceSearchMessageDialog.hideLoad(); view.advSrcCusNoResultList.hideLoad() });
            }
        }
        function CustNameResponseHandler(res) {
            if(res && res.data) {
                view.advSrcCusNoResultList.hideLoad().data($.map(res.data, function(o,i){
                    return {
                        // "acctNo": o.no
                        "customerId": o.customer.no
                        ,"customerAccountName": o.customer.fullName
                        ,"customerTaxNo": o.customer.cardNo
                        ,"customerSegment": (o.customer.segment!=null)?o.customer.segment.text:""
                        //,"billNo": o.customer.no
                    };
                }));
            }
        }

        function OrgResponseHandler(res) {
            if(res && res.data) {
                view.advSrcCusNoResultList.hideLoad().data($.map(res.data, function(o,i){
                    //console.log(o.customer.segment);
                    return {
                        "acctNo": o.no
                        ,"customerId": o.customer.no
                        ,"customerAccountName": o.customerAccountName
                        ,"customerTaxNo": o.taxRegisterNo
                        ,"customerSegment": (o.customer.segment!=null)?o.customer.segment.text:""
                        ,"billNo": o.customer.no
                    };
                }));
            }
        }
        console.log(" active search searchByCustName["+searchByCustName+"]")
        console.log(" active search searchByOrg["+searchByOrg+"]")
        console.log(" active search searchByCustId["+searchByCustId+"]")
        get(url, params, CustNameResponseHandler, view.advanceSearchMessageDialog, function(){ view.advanceSearchMessageDialog.hideLoad(); view.advSrcCusNoResultList.hideLoad() });
        /*
         if(searchByCustName){
         searchByCustName = false;
         get(url, params, CustNameResponseHandler, view.advanceSearchMessageDialog, function(){ view.advanceSearchMessageDialog.hideLoad(); view.advSrcCusNoResultList.hideLoad() });
         } else if(searchByOrg) {
         searchByOrg = false;
         get(url, params, OrgResponseHandler, view.advanceSearchMessageDialog, function(){ view.advanceSearchMessageDialog.hideLoad(); view.advSrcCusNoResultList.hideLoad() });
         } else if(searchByCustId) {
         searchByCustId = false;
         get(url, params, CustNoResponseHandler, view.advanceSearchMessageDialog, function(){ view.advanceSearchMessageDialog.hideLoad(); view.advSrcCusNoResultList.hideLoad() });
         }else {
         get(url, params, ResponseHandler, view.advanceSearchMessageDialog, function(){ view.advanceSearchMessageDialog.hideLoad(); view.advSrcCusNoResultList.hideLoad() });
         }
         */
    }

    function SelectButton(val, row, ind) {
        //return "<a class='btn btn-success btn-xs' onclick='BillSelectButtonEvent("+ JSON.stringify(row) +")'>เลือก</a>";
        return "<a class='btn btn-success btn-xs' onclick='BillSelectButtonWithParamEvent("+ row.customerId +")'>เลือก</a>";
    }
    function BillSelectButtonEvent(bill) {
        view.inputBillNo.val(bill.customerId);
        view.buttonSearchBillNo.elem.click();
        view.customerSearch.hide();
    }
    function BillSelectButtonWithParamEvent(customerId) {
        view.inputBillNo.val(customerId);
        view.buttonSearchBillNo.elem.click();
        view.customerSearch.hide();
    }
    function setup() {
        var isNew = view.utils.queryString()["new"];
        if (isNew) {
            view.session("billingList", []);
        }
        var billingList = view.session("billingList");
        console.log(" billingList size =>"+billingList.length)
        if (billingList.length > 0) {
            view.buttonSummaryPayment.badge(billingList.length);
            view.panelNavigation.show();
        } else {
            view.buttonSummaryPayment.disable(true);
            view.buttonProcessPayment.disable(true);
        }
        var category = 'vat.type';
        $.ajax({
            type: 'GET',
            url: '../findVatRateByCategory.json?category='+category,
            success: function (data) {
                //console.log('555555');console.log(data);
                var vatRate = parseFloat(data.enumDTO.data[0].mapCode2);
                var vatDesc = data.enumDTO.data[0].descAbvrEn;
                //console.log(vatRate);
                $("#inputCustomerVatRate").append('<option data-index="'+ 1 +'" data-key="'+ 1 +'" value="'+ vatRate +'" selected>'+ vatDesc +'</option>')
            }
        })

    }
    function buttonSearchBillNoClickEvent() {
        if(!$.isNumeric(view.inputBillNo.val())) return;
        view.buttonSearchBillNo.showLoad();
        view.panelSummaryInfo.hide(1);
        view.panelReceiptDetails.hide(1);
        view.panelCustomerInfo.hide(1);
        view.panelNavigation.hide(1);
        view.message.clear().showLoad("ระบบกำลังรอข้อมูลจาก CRM : {timeCnt} วินาที ")
        /*
         //get("../service/cust-profiles/search/no", { "no": view.inputBillNo.val() }, function(res){
         get("../service/customers/search/no", { "no": view.inputBillNo.val() }, function(res){
         console.log(res);
         view.panelSummaryInfo.slideDown(1400);
         view.panelReceiptDetails.slideDown(1000);
         view.panelCustomerInfo.slideDown(600);
         view.panelNavigation.slideDown(200);
         /*
         var profile = res._embedded.customerProfiles[0], addr = $.extend(profile.addresses[0], {});
         view.inputCustomerBillNo.val(profile.no);
         view.inputCustomerName.val(profile.fullName);
         view.inputCustomerType.val(profile.type);
         //	view.inputCustomerSegment.key(profile.segment.id);
         $( "select[id='inputCustomerSegment'] option:selected" ).each(function() {
         $( this ).removeAttr("selected");
         });
         $('#inputCustomerSegment option[data-key='+profile.segment.id+']').attr('selected','selected');
         $('#inputCustomerSegment').val(profile.segment.id)

         view.inputCustomerTaxNo.val($.trim(addr.registerNo));
         view.inputCustomerAddress.val($.trim(addr.no) +" "+ $.trim(addr.moo) +" "+ $.trim(addr.village) +" "+ $.trim(addr.soi) +" "+ $.trim(addr.road) +" "+ $.trim(addr.tumbon) +" "+ $.trim(addr.amphur) +" "+ $.trim(addr.province) +" "+ $.trim(addr.postCode));

         if(res && res._embedded.customers.length !== 0) {
         var profile = res._embedded.customers[0];
         view.inputCustomerBillNo.val(profile.no);
         view.inputCustomerName.val(profile.name);
         view.inputCustomerType.val(profile.type);
         view.inputCustomerTaxNo.val(profile.tax);
         view.inputCustomerAddress.val(profile.receiptAddr);

         $( "select[id='inputCustomerSegment'] option:selected" ).each(function() { $( this ).removeAttr("selected");});
         $('#inputCustomerSegment option[data-key='+profile.catCustomerSegment+']').attr('selected','selected');
         view.inputCustomerSegment.val(profile.catCustomerSegment);
         }

         }, view.message, function() { view.buttonSearchBillNo.hideLoad() });
         */
        get("../searchCustomerProfile.json", { "billingNo": view.inputBillNo.val() }, function(res) {
            var profile = res.data[0];
            if(profile != null) {
                console.log(profile);
                view.panelSummaryInfo.slideDown(1400);
                view.panelReceiptDetails.slideDown(1000);
                view.panelCustomerInfo.slideDown(600);
                view.panelNavigation.slideDown(200);
                var addr = $.extend(profile.addresses[0], {});
                view.inputCustomerBillNo.val(profile.no);
                view.inputCustomerName.val(profile.fullName);
                view.inputCustomerType.val(profile.type);
                view.inputCustomerGroupCode.val(profile.groupCode);
                view.inputCustomerGroupName.val(profile.groupName);
                view.inputCustomerTaxNo.val($.trim(addr.registerNo));
                view.inputCustomerAddress.val($.trim(addr.no) + " " + $.trim(addr.moo) + " " + $.trim(addr.village) + " " + $.trim(addr.soi) + " " + $.trim(addr.road) + " " + $.trim(addr.tumbon) + " " + $.trim(addr.amphur) + " " + $.trim(addr.province) + " " + $.trim(addr.postCode));

                $('#inputCustomerSegment option:selected').prop('selected', false);
                $('#inputCustomerSegment option[data-key='+profile.segment.id+']').prop('selected', true);
                view.inputCustomerSegment.val(profile.segment.id);
            }
        }, view.message, function() { view.buttonSearchBillNo.hideLoad() });
        view.inputServiceMoreData.val(1);
        $('#serviceNameDiv span.select2-container').removeAttr('style');
        $('#serviceDepartmentDiv span.select2-container').removeAttr('style');
        $('#inputServiceDepartment option[data-code=${epContext.branchArea}]').prop('selected', true).trigger('change.select2');

    }

    function buttonCalculateWtClickEvent(){
        if (view.inputServiceMoreData.val() < 1) {
            view.message.error(["กรุณาระบุจำนวนรายการ ก่อนการเพิ่มรายการ"]).show();
        } else
        if (view.inputServiceAmount.val() < 1) {
            view.message.error(["กรุณาระบุจำนวนเงินต่อหน่วย ก่อนการเพิ่มรายการ"]).show();
        }else {
            view.message.clear();
        }
        var amount = view.inputServiceAmount.val();
        var amountUnit = view.inputServiceMoreData.val();
        var discount = view.inputServiceDiscount.val();
        var specialDiscount = view.inputSpecialDiscount.val();
        var sumDisc = discount + specialDiscount;
        var currencyRate = parseFloat($('#currencySelect option:selected').attr('data-rate'),10);
        if ($('input[name=vatRadio]:checked').val() == 'exclude') {
            var total = multiply(amount, amountUnit, 2);
            var wt = multiply(subtract(total, sumDisc, 2), 0.03, 2);
        }else{
            var vatRate = parseFloat(view.session("vatRate"));
            var total = multiply(amount, amountUnit, 2);
            var beforeVat = (total*100)/(100+vatRate);
            var wt = multiply(subtract(beforeVat, sumDisc, 2), 0.03, 2);
        }
        view.inputServiceDeduction.val(wt);
    }

    function buttonAddBillingListClickEvent() {
        var isValid = true;
        view.message.clear();
        if (view.inputServiceType.index() == 0) {
            view.message.error(["กรุณาระบุประเภทบริการ ก่อนการเพิ่มรายการ"]).show();
            isValid = false;
        } else
        if (view.inputServiceName.index() == 0) {
            view.message.error(["กรุณาระบุชื่อบริการ ก่อนการเพิ่มรายการ"]).show();
            isValid = false;
        } else
        if (view.inputServiceDepartment.index() == -1) {
            view.message.error(["กรุณาระบุหน่วยงานรับรายได้ ก่อนการเพิ่มรายการ"]).show();
            isValid = false;
        } else
        if (view.inputServiceMoreData.val() < 1) {
            view.message.error(["กรุณาระบุจำนวนรายการ ก่อนการเพิ่มรายการ"]).show();
            isValid = false;
        } else
        if (view.inputServiceAmount.val() < 1) {
            view.message.error(["กรุณาระบุจำนวนเงินต่อหน่วย ก่อนการเพิ่มรายการ"]).show();
            isValid = false;
        }
        
      	//check currency code
      	var datalist = view.tableBillingList.data(), billingList = view.session("billingList");
        if (datalist.length > 0) {
			console.log("+++++++ datalist ");
			console.log(datalist[0]);
        	if(datalist[0].currencyCode != view.currencySelect.val()){
            	view.message.clear();
            	view.message.error(["ไม่สามารถเพิ่มรายการได้ เนื่องจากระบุประเภทสกุลเงินต่างกัน"]).show();
            	isValid = false;
            } else {
            	isValid = true;
            }        	
	        /* for (var i = 0, m = datalist.length; i < m; i++) {
	            if(datalist[i].currencyCode != view.currencySelect.val()){
	            	view.message.clear();
	            	view.message.error(["ไม่สามารถเพิ่มรายการได้ เนื่องจากระบุประเภทสกุลเงินต่างกัน"]).show();
	            	isValid = false;
	            }
	        } */
    	} else if (billingList.length > 0) {
   			console.log("++++++ billing ");
   			console.log(billingList[0]);
   			if(billingList[0].serviceList[0].currencyCode != view.currencySelect.val()){
            	view.message.clear();
            	view.message.error(["ไม่สามารถเพิ่มรายการได้ เนื่องจากระบุประเภทสกุลเงินต่างกัน"]).show();
            	isValid = false;
            } else {
            	isValid = true;
            }
    	}
        if(!isValid) return isValid;
        // passes all validation
        var currencyRate =   parseFloat($('#currencySelect option:selected').attr('data-rate'),10);
        console.log("currencyRate->"+currencyRate)
        var amount = view.inputServiceAmount.val();
        amount = multiply(amount,currencyRate,2);
        console.log("amount->"+amount)
        var amountUnit = view.inputServiceMoreData.val();
        var discount = view.inputServiceDiscount.val();
        discount = multiply(discount,currencyRate,2); 
        var specialDiscount = view.inputSpecialDiscount.val();
        specialDiscount = multiply(specialDiscount,currencyRate,2); 
        
        var vatRate = view.inputCustomerVatRate.key();
        var vatRateCal = view.inputCustomerVatRate.val();
        var vatR;
        if(vatRate==1) {
            vatR = parseFloat(vatRateCal);
            if ($('input[name=vatRadio]:checked').val() == 'exclude') {
                var totalAfterDiscountAmount = subtract(multiply(amount, amountUnit, 2), discount, 2);
                var vat = multiply(totalAfterDiscountAmount, vatR/100, 2);//multiply(totalAfterDiscountAmount, 0.07, 2);
                var totalCharge = add(totalAfterDiscountAmount, vat, 2);
            }
            else {
                var amount = multiply(amount, divide(100, 100+vatR, 5), 2);//multiply(amount, divide(100, 107, 5), 2);  //ถอด Vat ออก
                var totalAfterDiscountAmount = subtract(multiply(amount, amountUnit, 2), discount, 2);
                var vat = multiply(totalAfterDiscountAmount, vatR/100, 2);//multiply(totalAfterDiscountAmount, 0.07, 2);
                var totalCharge = add(totalAfterDiscountAmount, vat, 2);
            }
        }
        else if(vatRate==2){
            vatR = parseFloat(0);
            var vat=0;
            totalCharge = subtract(multiply(amount, amountUnit, 2), discount, 2);
        }
        else {
            vatR = null;
            var vat = null; //for non vat set vat = null and view in - symbol
            totalCharge = subtract(multiply(amount, amountUnit, 2), discount, 2);
        }
        console.log("billingList >>>>>>>>>>> ");
        console.log(billingList);
        console.log("isValid>>>>>"+isValid);
        if (isValid){
	        view.tableBillingList.insert(
	            {"id": view.tableBillingList.data().length,
	                "serviceType": view.inputServiceType.val(),
	                "serviceTypeCode": view.inputServiceType.key(),
	                "serviceName": view.inputServiceName.val(),
	                "serviceNameCode": view.inputServiceName.key(),
	                "serviceMoreData": view.inputServiceMoreData.val(),
	                "serviceUnit": view.inputServiceUnit.val(),
	                "serviceAmount": amount,
	                "serviceVatRate": vatR,
	                "serviceVat": vat,
	                "serviceDiscount": multiply(view.inputServiceDiscount.val(),currencyRate,2),
	                "serviceDeduction": multiply(view.inputServiceDeduction.val(),currencyRate,2),
	                "serviceTotalCharge": totalCharge,
	                "profitCode": view.inputServiceDepartment.key(),
	                "profitName": $('#inputServiceDepartment').val(),
	                "productCode": $('#inputServiceName option:selected').attr('data-prodCode'),
	                "productName": $('#inputServiceName option:selected').attr('data-prodName'),
	                "subProductCode": $('#inputServiceName option:selected').attr('data-subProdCode'),
	                "subProductName": $('#inputServiceName option:selected').attr('data-subProdName'),
	                "segmentCode": $('#inputServiceName option:selected').attr('data-segCode'),
	                "segmentName": $('#inputServiceName option:selected').attr('data-segName'),
	                "glAccount": $('#inputServiceName option:selected').attr('data-glCode'),
	                "revenueTypeCode": view.inputServiceType.key(),
	                "revenueTypeName": view.inputServiceType.val(),
	                "serviceSpecialDiscount": multiply(view.inputSpecialDiscount.val(),currencyRate),
	                "currencyCode": view.currencySelect.val(),
	                "currencyRate": $('#currencySelect option:selected').attr('data-rate'),
	                "exchangeDateUsed": $('#currencySelect option:selected').attr('data-date'),
	                "currencyName": $('#currencySelect option:selected').attr('data-message')
	           });
        }
        view.buttonSummaryPayment.enable();
        view.buttonProcessPayment.enable();
        calculate();

        var discountCheckBox = $("input[name='checkboxAdditionalDiscount']");
        if (discountCheckBox.prop("checked")) {
            $("input[name='itemSpecialDisc']").prop('disabled', false);
        } else {
            $("input[name='itemSpecNialDisc']").prop('disabled', true);
        }

        clearData()
    }
    function clearData() {
        view.inputServiceType.init("../findBySource.json?source=OTHER");
        $("#inputServiceName").find('option').remove();
        $('#inputServiceDepartment option[data-code=${epContext.branchArea}]').prop('selected', true).trigger('change.select2');
        view.inputServiceAmount.val(0);
        view.inputServiceMoreData.val(1);
        $('input:radio[name=vatRadio]').filter('[value=exclude]').prop('checked', true);
        view.inputServiceDeduction.val(0);
        view.inputServiceDiscount.val(0);
        view.inputSpecialDiscount.val(0);
        view.inputServiceUnit.init("../findServiceUnitList.json");
    }
    function buttonSummaryPaymentClickEvent() {
        buildBillingList();
        location.href = "pay-other_2.jsp"
    }
    function buttonProcessPaymentClickEvent() {
        buildBillingList();
        location.href = "pay-other_3.jsp"
    }
    function buildBillingList() {
        var billingList = view.session("billingList");
        // copyCustomerProfile(); // check whether receipt values are ready
        var serviceList = [];
        $.each(view.tableBillingList.data(), function(i,o){
            console.log(o);
            serviceList.push(o);
        });
        if(serviceList.length != 0) {
            billingList.push(
                {
                    "custNo": view.inputCustomerBillNo.val(),
                    "custName": view.inputCustomerName.val(),
                    "custType": view.inputCustomerType.val(),
                    "specialDiscount": view.inputAdditionalDiscount.val(),
                    "groupCode" : view.inputCustomerGroupCode.val(),
                    "groupName" : view.inputCustomerGroupName.val(),
                    "rctCustNo": view.inputCustomerBillNo.val(),
                    "rctCustName": view.inputCustomerName.val(),
                    "rctTaxId": view.inputCustomerTaxNo.val(),
                    "rctBranch": view.inputCustomerBranch.val(),
                    "rctCustGrp": view.inputCustomerSegment.val(),
                    "rctAddr": view.inputCustomerAddress.val(),
                    "remark": view.inputAdditionalRemark.val(),
                    "split": false,
                    "custCategoryDesc": $("#inputCustomerSegment option:selected").text(),
                    "acctCatLkp": view.inputCustomerSegment.key(),
                    "catCustomerSegment": view.inputCustomerSegment.key(),
                    "vatRate": view.inputCustomerVatRate.key(),
                    "serviceList": serviceList
                });
        }
        view.session("billingList", billingList);
        view.session("isStateAgency", view.inputCustomerSegment.key());
    }
    function checkboxAdditionalDiscountClickEvent() {
        var discountCheckBox = $(this);
        if (discountCheckBox.prop("checked")) {
            view.dialogAuthentication.show().done(function(res) {
                view.inputSpecialDiscount.enable();
                $("input[name='itemSpecialDisc']").prop('disabled', false);
            }).fail(function(res) {
                view.inputSpecialDiscount.disable();
                $("input[name='itemSpecialDisc']").prop('disabled', true);
            }).cancel(function() {
                discountCheckBox.prop("checked", false);
                $("input[name='itemSpecialDisc']").prop('disabled', true);
            });
        } else {
            view.inputSpecialDiscount.disable().val(0);
            $("input[name='itemSpecialDisc']").prop('disabled', true);
            $("input[name='itemSpecialDisc']").val(0);
            calculate();
        }
    }

    function allowEditCustomerData() {
        var check = document.getElementById('isAllowEdit').checked ;
        if(check == true){
            view.inputCustomerName.enable();
            view.inputCustomerTaxNo.enable();
            view.inputCustomerBranch.enable();
            view.inputCustomerSegment.enable();
            view.inputCustomerVatRate.enable();
            view.inputCustomerAddress.enable();
        }else{
            view.inputCustomerName.disable(true);
            view.inputCustomerTaxNo.disable(true);
            view.inputCustomerBranch.disable(true);
            view.inputCustomerSegment.disable(true);
            view.inputCustomerVatRate.disable(true);
            view.inputCustomerAddress.disable(true);
        }
        /*if(check == true){
         view.inputCustomerName1.enable();
         view.inputCustomerTaxNo1.enable()
         view.inputCustomerBranch1.enable()
         view.inputCustomerGroup1.enable()
         view.inputCustomerAddress1.enable()
         }else{
         view.inputCustomerName1.disable(true);
         view.inputCustomerTaxNo1.disable(true);
         view.inputCustomerBranch1.disable(true);
         view.inputCustomerGroup1.disable(true);
         view.inputCustomerAddress1.disable(true);
         }*/
    }

    /*
     function copyCustomerProfile() {
     var receiptCustomerCustNo = view.inputCustomerCustNo1.val();
     if(receiptCustomerCustNo == "") {
     view.inputCustomerCustNo1.val(view.inputCustomerBillNo.val());
     view.inputCustomerName1.val( view.inputCustomerName.val());
     view.inputCustomerTaxNo1.val(view.inputCustomerTaxNo.val());
     view.inputCustomerBranch1.val(view.inputCustomerBranch.val());
     view.inputCustomerGroup1.data(view.inputCustomerSegment.data());
     view.inputCustomerAddress1.val(view.inputCustomerAddress.val());
     }
     }
     */

    function removeFromServiceList(id) {
        view.tableBillingList.remove(id);
        view.buttonSummaryPayment.disable(view.tableBillingList.data().length <= 0);
        view.buttonProcessPayment.disable(view.tableBillingList.data().length <= 0);
        calculate();
    }
    function calculate() {
        var data = view.tableBillingList.data(), amount = 0, discount = 0, charge = 0, vat = 0, totalCharge = 0, deduction = 0, specialDiscount = 0;
        for (var i = 0, m = data.length; i < m; i++) {
            if(data[i].serviceVat != ''){
                vat += data[i].serviceVat;
            }
            amount += ((data[i].serviceAmount)*(data[i].serviceMoreData));
            discount += data[i].serviceDiscount;
            deduction += data[i].serviceDeduction;
            specialDiscount += data[i].serviceSpecialDiscount;
        }

        var vatRate = view.inputCustomerVatRate.key();
        var vatRateCal = view.inputCustomerVatRate.val();
        var vatR = 0;
        if(vatRate == 1){
            vatR = parseFloat(vatRateCal);
        }
//        var specialDisc = view.inputAdditionalDiscount.val();
        var specialDisc = specialDiscount;
        if(specialDisc > 0){
            charge = amount - discount - specialDisc;
            vat = charge*vatR/100;
            totalCharge = charge + vat;
        }else{
            charge = amount - discount;
            totalCharge = charge + vat;
        }

        //totalCharge = charge + parseFloat(vat);
        view.inputAmount.val(amount);
        view.inputCharge.val(charge);
        view.inputVat.val(vat);
        view.inputTotalCharge.val(totalCharge);
        view.inputDeduction.val(deduction);
        view.itemsDiscount.val(discount);
        view.preItemsDiscount.val(amount);
        view.inputAdditionalDiscount.val(specialDiscount);
        view.session("vatRate", vatR);
    }
    function updateTableBillingList(id, newVal){
        var oldData = view.tableBillingList.data();
        for (var i = 0, m = oldData.length; i < m; i++) {
            if(oldData[i].id == id){
                oldData[i].serviceName = newVal;
            }
        }
    }
    $("#inputAdditionalDiscount").change(function(){
        calculate();
    });

    $('#inputServiceType').on('change', function () {
        console.log("revenueTypeCode "+view.inputServiceType.key())
        $("#inputServiceName").find('option').remove();
        $.get("../service/mapGlServiceType/search/revenueTypeCode", { "revenueTypeCode": view.inputServiceType.key() }, function(res){
            var data = res._embedded;
            var array = data.mapGLServiceTpyes;
            //console.log("length >>>> "+array.length)
            if (array.length>0) {
                $('#inputServiceName').append('<option data-index="" data-key="0" value="">ทั้งหมด</option>');
                $.each(array, function (i, o) {
                    $('#inputServiceName').append('<option data-index="' + i + '" data-key="' + o.serviceCode + '" data-glCode="' + o.glCode + '" data-prodCode="' + o.productCode + '" data-prodName="' + o.productName + '" data-subProdCode="' + o.subProductCode + '" data-subProdName="' + o.subProductName + '" data-segCode="' + o.segmentCode + '" data-segName="' + o.segmentName + '" value="' + o.serviceName + '">' + o.serviceName + '</option>')
                });
            }
        });
    });

    function sumSpecialDiscount(id, newVal) {
        var additionalDiscount = view.inputAdditionalDiscount.val();
        var sumSpecialDiscount = 0;
//        console.log("ยอด >>>>>>>>> "+$("#inputSpecialDiscount").val());
//        console.log("sum >>>>>>>>> "+sumSpecialDiscount);

        var oldData = view.tableBillingList.data();
        for (var i = 0, m = oldData.length; i < m; i++) {
            if(oldData[i].id == id){
                oldData[i].serviceSpecialDiscount = newVal;
            }

        }
        calculate();
    }

    /*function calVat(id) {
     if ($('input[name=vatRadio]:checked').val() == 'exclude') {
     var total = multiply(amount, amountUnit, 2);
     var wt = multiply(subtract(total, sumDisc, 2), 0.03, 2);
     }else{
     var vatRate = parseFloat(view.session("vatRate"));
     var total = multiply(amount, amountUnit, 2);
     var beforeVat = (total*100)/(100+vatRate);
     var wt = multiply(subtract(beforeVat, sumDisc, 2), 0.03, 2);
     }
     view.inputServiceDeduction.val(wt);
     }*/
     
     $('#currencySelect').on('change', function () {
    	 var rateCode = $('#currencySelect').val(), rateUnit = $('#currencySelect option:selected').attr('data-rate'), rateName = $('#currencySelect option:selected').attr('data-message'), symbol = $('#currencySelect option:selected').attr('data-symbol'), exchangeDateUsed = $('#currencySelect option:selected').attr('data-date');
    	 if (exchangeDateUsed) {
	        exchangeDateUsed = exchangeDateUsed.replace(/(\d{4})-(\d{1,2})-(\d{1,2})/, function(match,y,m,d) {
	        	return m + '/' + d + '/' + y;
	        });
         }
    	 if ($('#currencySelect').val() != "th_TH") {
             $('#txtRate').text("  อัตราแลกเปลี่ยน 1 "+rateName+" : "+rateUnit+" บาท ตามอัตราแลกเปลี่ยนวันที่ "+exchangeDateUsed);
             $('#txtRate').removeAttr("style", "display:none;");
             $('#txtRate').attr("style", "color:red; font-size:12px;");
         } else {
             $('#txtRate').attr("style", "display:none;");
         }
    	 
    	 $('.input-group span').text(symbol);
     });
     
    function serviceDiscountChangeEvent() {
	    var rateUnit = $('#currencySelect option:selected').attr('data-rate'), serviceDiscount = view.inputServiceDiscount.val();
	    view.inputServiceDiscount.val(multiply(serviceDiscount, rateUnit, 2));
    }

    function specialDiscountChangeEvent() {
	    var rateUnit = $('#currencySelect option:selected').attr('data-rate'), specialDiscount = view.inputSpecialDiscount.val();
	    view.inputSpecialDiscount.val(multiply(specialDiscount, rateUnit, 2));
    }

</script>
</html>

