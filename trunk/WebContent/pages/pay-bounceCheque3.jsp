<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <link href="resources/bootstrap-table/src/bootstrap-table.css" rel="stylesheet" type="text/css" />
    <link href="resources/css/datepicker.min.css" rel="stylesheet" type="text/css" />
    <link href="resources/custom.css" rel="stylesheet" type="text/css" />
    <script src="resources/jquery.min.js" type="text/javascript"></script>
    <script src="resources/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="resources/bootstrap-table/src/bootstrap-table.js" type="text/javascript"></script>
    <script src="resources/js/bootstrap-datepicker.min.js" type="text/javascript"></script>
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
                        <li><i>เช็คขัดข้อง</i></li>
                        <li>รับชำระลูกหนี้ SAP รายตัว</li>
                        <li>สรุปการรับชำระเงิน</li>
                        <li class="active">เลือกวิธีการรับชำระ</li>
                        <li>ผลการรับชำระ</li>
                    </ol>
                </div>
            </div>
            <div id="mainMessageDialog"></div>
            <div id="summaryPanel" style="display: none">
                <ul class="list-inline pull-right list-menu-set">
                    <li><a href="pay-bounceCheque.jsp?new"><span class="glyphicon glyphicon glyphicon-arrow-left"></span> กลับไปหน้าการรับชำระ</a></li>
                </ul>
                <div class="row">
                    <div class="col-md-12">
                        <div class="panel panel-success">
                            <div class="panel-heading">ผลการรับชำระเงิน</div>
                            <div class="panel-body">
                                <table id="receiptList" data-row-style="rowStyle" data-toggle="table" data-detail-view="true" data-detail-formatter="detailFormatter"
                                    data-classes="table table-hover table-striped">
                                    <thead>
                                        <tr>
                                            <th data-align="center" data-formatter="runningFormatter">#</th>
                                            <th data-field="customerNo">เลขที่ลูกค้า</th>
                                            <th data-field="customerName">ชื่อลูกค้า</th>
                                            <th data-field="invoiceCount">จำนวนใบแจ้งค่าใช้บริการ</th>
                                            <th data-field="receiptNo">เลขที่ใบเสร็จรับเงิน/ใบกำกับภาษี</th>
                                            <th data-field="receiptAmount" data-align="right">จำนวนเงินที่รับชำระ</th>
                                            <th data-field="receiptStatus">สถานะการรับชำระ</th>
                                        </tr>
                                    </thead>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <ul id="navigationPanel" class="list-inline pull-right list-menu-set">
                <li><a href="pay-bounceCheque2.jsp" class="btn btn-link"><span class="glyphicon glyphicon-th-list"></span> สรุปรายการรับชำระ</a></li>
                <li><a id="submitPayment" class="btn btn-link"><span class="glyphicon glyphicon-print"></span> บันทึกและพิมพ์</a></li>
                <li><a id="submitPaymentEng" class="btn btn-link"><span class="glyphicon glyphicon-print"></span> บันทึกและพิมพ์(อังกฤษ)</a></li>
                <li><a id="cancelPaymentBtn" class="btn btn-link"><span class="glyphicon glyphicon-remove-circle"></span> ยกเลิกรายการ</a></li>
                <li><a id="creditLimit" class="btn btn-link"><span class="glyphicon glyphicon-remove-circle"></span> Credit Limit</a></li>
            </ul>
            <div id="fillDataInputPanel" class="row">
                <div class="col-md-5">
                    <label class="label-panal label-warning">รายการหัก</label>
                    <div id="setTaxType" class="btn-group">
                        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                        <span class="selection">ภาษีหัก ณ ที่จ่าย</span> <span class="caret"></span>
                    </button>
                        <ul class="dropdown-menu" id='testmenu'>
                            <li><a href="#">ภาษีหัก ณ ที่จ่าย</a></li>
                            <!-- GFMIS -->
                            <li><a href="#">ค่าธรรมเนียม</a></li>
                            <li><a href="#">ค่าปรับ</a></li>
                            <li><a href="#">เงินประกันผลงาน</a></li>
                            <li><a href="#">ค่าตอบแทนการรับชำระเงิน</a></li>
                        </ul>
                    </div>
                    <!--                     <a id="addTaxType" class="btn btn-link"><span class="glyphicon glyphicon-plus-sign"></span> เพิ่มรายการหัก</a> -->
                    <div class="panel panel-default panal-radius">
                        <div class="panel-body" style="padding-right:0;padding-left:0">
                            <div class="col-md-12">
                                <div role="tabpanel" class="tab-pane hide" id="withholdingTaxPanel">
                                    <div class="form-horizontal">
                                        <div class="form-group">
                                            <label class="control-label col-sm-6">ประเภทภาษีหัก ณ ที่จ่าย :</label>
                                            <div class="col-sm-6">
                                                <label class="radio-inline"><input type="radio" name="withholdingTaxType" data-label="69 ทวิ" data-type="69BIS" id="69tvi"><b> 69 ทวิ</b></label>&nbsp;&nbsp;
                                                <label class="radio-inline"><input type="radio" name="withholdingTaxType" data-label="3 เตรส" data-type="3TREDECIM" id="3trs" ><b> 3 เตรส</b></label>&nbsp;&nbsp;
                                                <label class="radio-inline"><input type="radio" name="withholdingTaxType" data-label="69 ตรี" data-type="69TRE" id="69tri"><b> 69 ตรี</b></label>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-3">เลขที่ลูกค้า :</label>
                                            <div class="col-sm-3"><select id="cutomerNo" class="form-control"></select></div>
                                            <label class="control-label col-sm-3">เลขที่ใบแจ้งค่าใช้บริการ :</label>
                                            <div class="col-sm-3"><select id="withholdingInvoiceNo" class="form-control"></select></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-9">เลขที่เอกสาร :</label>
                                            <div class="col-sm-3"><input id="withholdingTaxNo" type="text" class="form-control"></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-3">จำนวนเงินบาท :</label>
                                            <div class="col-sm-3"><input id="amountIn" class="form-control text-right"></div>
                                            <label class="control-label col-sm-3">จำนวนเงินต่างประเทศ :</label>
                                            <div class="col-sm-3"><input id="amountOut" class="form-control text-right"></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-3">อัตราแลกเปลี่ยน :</label>
                                            <div class="col-sm-3"><input id="changeRate" class="form-control text-right"></div>
                                            <label class="control-label col-sm-3">สกุลเงินต่างประเทศ :</label>
                                            <div class="col-sm-3"><select id="currencyOut" class="form-control"></select></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-6"></label>
                                            <div class="col-sm-6 text-right"><a id="addWithholdingTaxNo" class="btn btn-link"><span class="glyphicon glyphicon-th-list"></span>  เพิ่มรายการภาษีหัก ณ ที่จ่าย</a></div>
                                        </div>
                                    </div>
                                    <table id="withholdingTaxList" class="table table-hover">
                                        <thead>
                                            <tr>
                                                <th data-align="center" data-running-no="true">#</th>
                                                <th>เลขที่ใบแจ้งค่าใช้บริการ</th>
                                                <th>เลขที่เอกสาร</th>
                                                <th>ประเภทหัก ณ ที่จ่าย</th>
                                                <th data-align="right" data-number-format="true">จำนวนเงินต่างประเทศ</th>
                                                <th data-align="right" data-number-format="true">จำนวนเงินบาท</th>
                                                <th></th>
                                            </tr>
                                        </thead>
                                    </table>
                                </div>
                                <!-- GFMIS -->
                                <div role="tabpanel" class="tab-pane hide" id="feeTaxPanel">
                                    <div class="form-horizontal">
                                        <div class="form-group hide">
                                            <label class="control-label col-sm-6">ประเภทค่าธรรมเนียม :</label>
                                            <div class="col-sm-5">
                                                <label class="radio-inline"><input type="radio" name="feeTaxType" data-label="ขาออก" checked><b> ขาออก</b></label>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-9">ศูนย์ต้นทุน :</label>
                                            <div class="col-sm-3"><select id="feeDepartmentCost" class="form-control"></select></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-3">จำนวนเงินบาท :</label>
                                            <div class="col-sm-3"><input id="feeTaxAmountIn" class="form-control text-right"></div>
                                            <label class="control-label col-sm-3">จำนวนเงินต่างประเทศ :</label>
                                            <div class="col-sm-3"><input id="feeTaxAmountOut" class="form-control text-right"></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-3">อัตราแลกเปลี่ยน :</label>
                                            <div class="col-sm-3"><input class="form-control text-right" id="feeExchangeRateInput" value="0.00"></div>
                                            <label class="control-label col-sm-3">สกุลเงินต่างประเทศ :</label>
                                            <div class="col-sm-3"><select class="form-control" id="feeCurrencyTypeSelect" onchange="addExchangRate()"></select></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-3">ภาษีซื้อ :</label>
                                            <div class="col-sm-3"><input id="feeBuyTaxAmount" class="form-control text-right"></div>
                                            <label class="control-label col-sm-3">ภาษีหัก ณ ที่จ่าย :</label>
                                            <div class="col-sm-3"><input id="feeBuyTaxAmountVat" class="form-control text-right"></div>
                                        </div>
                                        <!-- <div class="form-group">
                                            <label class="control-label col-sm-6">ภาษีหัก ณ ที่จ่าย :</label>
                                            <div class="col-sm-5"><input id="feeWTAmount" class="form-control text-right"></div>
                                        </div> -->
                                        <div class="form-group">
                                            <label class="control-label col-sm-6"></label>
                                            <div class="col-sm-6 text-right"><a id="addFeeTaxNo" class="btn btn-link"><span class="glyphicon glyphicon-th-list"></span> เพิ่มรายการค่าธรรมเนียม</a></div>
                                        </div>
                                    </div>
                                    <table id="feeTaxList" class="table table-hover">
                                        <thead>
                                            <tr>
                                                <th data-align="center" data-running-no="true">#</th>
                                                <th>ศูนย์ต้นทุน</th>
                                                <th style="display:none;">ประเภทค่าธรรมเนียม</th>
                                                <th>ภาษีหัก ณ ที่จ่าย</th>
                                                <th>ภาษีซื้อ</th>
                                                <th data-align="right" data-number-format="true">จำนวนเงินต่างประเทศ</th>
                                                <th data-align="right" data-number-format="true">จำนวนเงินบาท</th>
                                                <th></th>
                                            </tr>
                                        </thead>
                                    </table>
                                </div>
                                <div role="tabpanel" class="tab-pane hide" id="penaltyTaxPanel">
                                    <div class="form-horizontal">
                                        <div class="form-group hide">
                                            <label class="control-label col-sm-6">ประเภทค่าปรับ :</label>
                                            <div class="col-sm-5">
                                                <!--                                                 <label class="radio-inline"><input type="radio" name="penaltyTaxType" data-label="ขาเข้า" checked><b> ขาเข้า</b></label>&nbsp;&nbsp;&nbsp; -->
                                                <label class="radio-inline"><input type="radio" name="penaltyTaxType" data-label="ขาออก" checked><b> ขาออก</b></label>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <!-- ice fixed code no.109 'ทุกระบบต้องเลขที่ใบแจ้งค่าใช้บริการใบแจ้งค่าใช้บริการ" -->
                                            <label class="control-label col-sm-6">เลขที่ใบแจ้งค่าใช้บริการ :</label>
                                            <!-- end ice fixed code no.109 'ทุกระบบต้องเลขที่ใบแจ้งค่าใช้บริการใบแจ้งค่าใช้บริการ" -->
                                            <div class="col-sm-5">
                                                <select id="penaltyInvoiceNo" class="form-control"></select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-6">จำนวนเงิน :</label>
                                            <div class="col-sm-5">
                                                <input id="penaltyTaxAmount" class="form-control text-right">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-6"></label>
                                            <div class="col-sm-5"><a id="addPenaltyTaxNo" class="btn btn-link"><span class="glyphicon glyphicon-th-list"></span>  เพิ่มรายการค่าปรับ</a></div>
                                        </div>
                                    </div>
                                    <table id="penaltyTaxList" class="table table-hover">
                                        <thead>
                                            <tr>
                                                <th data-running-no="true">#</th>
                                                <!-- ice fixed code no.109 'ทุกระบบต้องเลขที่ใบแจ้งค่าใช้บริการใบแจ้งค่าใช้บริการ" -->
                                                <th>เลขที่ใบแจ้งค่าใช้บริการ</th>
                                                <!-- end ice fixed code no.109 'ทุกระบบต้องเลขที่ใบแจ้งค่าใช้บริการใบแจ้งค่าใช้บริการ" -->
                                                <th style="display:none;">ประเภทค่าปรับ</th>
                                                <th data-align="right" data-number-format="true">จำนวนเงิน</th>
                                                <th></th>
                                            </tr>
                                        </thead>
                                    </table>
                                </div>
                                <div role="tabpanel" class="tab-pane hide" id="retentionTaxPanel">
                                    <div class="form-horizontal">
                                        <div class="form-group hide">
                                            <label class="control-label col-sm-6">ประเภทเงินประกันผลงาน :</label>
                                            <div class="col-sm-5">
                                                <!--                                                 <label class="radio-inline"><input type="radio" name="retentionTaxType" data-label="ขาเข้า" checked><b> ขาเข้า</b></label>&nbsp;&nbsp;&nbsp; -->
                                                <label class="radio-inline"><input type="radio" name="retentionTaxType" data-label="ขาออก" checked><b> ขาออก</b></label>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <!-- ice fixed code no.109 'ทุกระบบต้องเลขที่ใบแจ้งค่าใช้บริการใบแจ้งค่าใช้บริการ" -->
                                            <label class="control-label col-sm-6">เลขที่ใบแจ้งค่าใช้บริการ :</label>
                                            <!-- end ice fixed code no.109 'ทุกระบบต้องเลขที่ใบแจ้งค่าใช้บริการใบแจ้งค่าใช้บริการ" -->
                                            <div class="col-sm-5">
                                                <select id="retentionInvoiceNo" class="form-control"></select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-6">จำนวนเงิน :</label>
                                            <div class="col-sm-5">
                                                <input id="retentionTaxAmount" class="form-control text-right">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-6"></label>
                                            <div class="col-sm-5"><a id="addRetentionTaxNo" class="btn btn-link"><span class="glyphicon glyphicon-th-list"></span>  เพิ่มรายการเงินประกันผลงาน</a></div>
                                        </div>
                                    </div>
                                    <table id="retentionTaxList" class="table table-hover">
                                        <thead>
                                            <tr>
                                                <th data-running-no="true">#</th>
                                                <!-- ice fixed code no.109 'ทุกระบบต้องเลขที่ใบแจ้งค่าใช้บริการใบแจ้งค่าใช้บริการ" -->
                                                <th>เลขที่ใบแจ้งค่าใช้บริการ</th>
                                                <!-- end ice fixed code no.109 'ทุกระบบต้องเลขที่ใบแจ้งค่าใช้บริการใบแจ้งค่าใช้บริการ" -->
                                                <th style="display: none;">ประเภทเงินประกันผลงาน</th>
                                                <th data-align="right" data-number-format="true">จำนวนเงิน</th>
                                                <th></th>
                                            </tr>
                                        </thead>
                                    </table>
                                </div>
                                <div role="tabpanel" class="tab-pane hide" id="compensationTaxPanel">
                                    <div class="form-horizontal">
                                        <div class="form-group">
                                            <label class="control-label col-sm-6">ศูนย์ต้นทุน :</label>
                                            <div class="col-sm-5">
                                                <select id="compensationDepartmentCost" class="form-control"></select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-6">จำนวนเงิน :</label>
                                            <div class="col-sm-5">
                                                <input id="compensationTaxAmount" class="form-control text-right">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-6"></label>
                                            <div class="col-sm-5"><a id="addCompensationTaxNo" class="btn btn-link"><span class="glyphicon glyphicon-th-list"></span>  เพิ่มรายการค่าตอบแทน</a></div>
                                        </div>
                                    </div>
                                    <table id="compensationTaxList" class="table table-hover">
                                        <thead>
                                            <tr>
                                                <th data-running-no="true">#</th>
                                                <th>ศูนย์ต้นทุน</th>
                                                <th data-align="right" data-number-format="true">จำนวนเงิน</th>
                                                <th></th>
                                            </tr>
                                        </thead>
                                    </table>
                                </div>
                                <a class="btn btn-primary pull-right" id="addTaxType" style="margin-top:1em"><span class="glyphicon glyphicon-plus-sign"></span> เพิ่มรายการหัก</a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-7">
                    <label class="label-panal label-warning">วิธีการรับชำระ</label>
                    <div id="setPayType" class="btn-group">
                        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                        <span class="selection">เงินสด</span> <span class="caret"></span>
                    </button>
                        <ul class="dropdown-menu" id='testmenu2'>
                            <li><a href="#">เงินสด</a></li>
                            <li><a href="#">เช็ค</a></li>
                            <li><a href="#">บัตรเครดิต</a></li>
                            <li><a href="#">เงินโอนในประเทศ</a></li>
                            <li><a href="#">เงินโอนต่างประเทศ</a></li>
                        </ul>
                    </div>
                    <!--                     <a id="addPayType" class="btn btn-link"><span class="glyphicon glyphicon-plus-sign"></span> เพิ่มวิธีการรับชำระ</a> -->
                    <div class="panel panel-default panal-radius">
                        <div class="panel-body" style="padding-right: 0; padding-left: 0;">
                            <div class="col-md-12">
                                <div role="tabpanel" class="tab-pane hide" id="payType1">
                                    <div class="form-horizontal">
                                        <div class="form-group">
                                            <label class="control-label col-sm-3">จำนวนเงินบาท :</label>
                                            <div class="col-sm-3">
                                                <input id="payCashAmountBath" class="form-control text-right" onblur="convertAmountCash()">
                                            </div>
                                            <label class="control-label col-sm-3">จำนวนเงินต่างประเทศ :</label>
                                            <div class="col-sm-3">
                                                <input id="payCashAmount" class="form-control text-right" onblur="convertAmountCashBath()">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-9">อัตราแลกเปลี่ยน :</label>
                                            <div class="col-sm-3">
                                                <input id="payCashAmountRate" class="form-control text-right" onblur="convertAmountRateChange()">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div role="tabpanel" class="tab-pane hide" id="payType2">
                                    <div class="form-horizontal">
                                        <div class="form-group">
                                            <label class="control-label col-sm-2">รหัสธนาคาร :</label>
                                            <div class="col-sm-4"><select id="payChqBankCode" class="form-control"></select></div>
                                            <label class="control-label col-sm-2">เลขที่เช็ค :</label>
                                            <!-- ICE FIXED CODE ข้อ 23 เช็ค length = 7 -->
                                            <div class="col-sm-4"><input class="form-control" id="payChqNo" maxlength="7"></div>
                                            <!-- End ICE FIXED CODE ข้อ 23 -->
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-2">ชื่อธนาคาร :</label>
                                            <div class="col-sm-4"><select id="payChqBankName" class="form-control"></select></div>
                                            <label class="control-label col-sm-2">วันที่หน้าเช็ค :</label>
                                            <div class="col-sm-4">
                                                <div class="input-group input-append date" data-provide="datepicker" data-date-format="dd/mm/yyyy">
                                                    <input class="form-control" id="payChqDate" placeholder="dd/MM/yyyy" maxlength="10">
                                                    <span class="input-group-addon add-on"><span class="glyphicon glyphicon-calendar"></span></span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-2">สาขา :</label>
                                            <div class="col-sm-4"><input class="form-control" id="payChqBranch"></div>
                                            <label class="control-label col-sm-2">จำนวนเงิน :</label>
                                            <div class="col-sm-4"><input class="form-control text-right" id="payChqAmount"></div>
                                        </div>
                                    </div>
                                    <a class="btn btn-warning pull-right" id="payChqSubmit"><span class="glyphicon glyphicon-plus-sign"></span> เพิ่มรายการเช็ค</a>
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
                                            <label class="control-label col-sm-3">ประเภทบัตรเครดิต :</label>
                                            <div class="col-sm-3"><select class="form-control" id="payCCType"></select></div>
                                            <label class="control-label col-sm-3">เลขที่บัตร :</label>
                                            <div class="col-sm-3"><input class="form-control" id="payCCNo" maxlength="16"></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-3">ธนาคารเจ้าของเครื่อง (EDC) :</label>
                                            <div class="col-sm-3"><select class="form-control" id="payCCBankName"></select></div>
                                            <label class="control-label col-sm-3">จำนวนเงิน :</label>
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
                                <div role="tabpanel" class="tab-pane hide" id="payType4">
                                    <div class="form-horizontal">
                                        <div class="form-group">
                                            <label class="control-label col-sm-4">รหัสธนาคาร :</label>
                                            <div class="col-sm-3"><select class="form-control" id="payBankTxnfInputBankCode"></select></div>
                                            <label class="control-label col-sm-2">ชื่อธนาคาร :</label>
                                            <div class="col-sm-3"><select class="form-control" id="payBankTxnfInputBankName"></select></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-4">สาขา :</label>
                                            <div class="col-sm-3"><select class="form-control" id="payBankTxnfDropDownBankBrnh"></select></div>
                                            <label class="control-label col-sm-2">เลขที่อ้างอิง :</label>
                                            <div class="col-sm-3"><input class="form-control" id="payBankTxnfInputNo" maxlength="10"></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-4">รหัสบัญชีเงินฝากธนาคาร :</label>
                                            <div class="col-sm-3"><select class="form-control" id="payBankTxnfDropDownBankAcCd"></select></div>
                                            <label class="control-label col-sm-2">วันที่โอน :</label>
                                            <div class="col-sm-3">
                                                <div class="input-group input-append date" data-provide="datepicker" data-date-format="dd/mm/yyyy">
                                                    <input class="form-control" id="payBankTxnfInputDate" placeholder="dd/MM/yyyy" maxlength="10">
                                                    <span class="input-group-addon add-on"><span class="glyphicon glyphicon-calendar"></span></span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-4">เลขที่บัญชีเงินฝากธนาคาร :</label>
                                            <div class="col-sm-3"><select class="form-control" id="payBankTxnfDropDownBankAcct"></select></div>
                                            <label class="control-label col-sm-2">จำนวนเงิน :</label>
                                            <div class="col-sm-3"><input class="form-control text-right" id="payBankTxnfInputAmt"></div>
                                        </div>
                                    </div>
                                    <a class="btn btn-warning pull-right" id="payBankTxnfButtonSubmit"><span class="glyphicon glyphicon-plus-sign"></span> เพิ่มรายการอื่น ๆ</a>
                                    <table id="payBankTxnfTableList" class="table">
                                        <thead>
                                            <tr>
                                                <th data-align="center" data-running-no="true">#</th>
                                                <th>รหัสธนาคาร</th>
                                                <th>ชื่อธนาคาร</th>
                                                <th>สาขา</th>
                                                <th>เลขที่อ้างอิง</th>
                                                <th>วันที่โอน</th>
                                                <th data-align="right" data-number-format="true">จำนวนเงิน</th>
                                                <th></th>
                                            </tr>
                                        </thead>
                                    </table>
                                </div>
                                <div role="tabpanel" class="tab-pane hide" id="payType7">
                                    <div class="form-horizontal">
                                        <div class="form-group">
                                            <label class="control-label col-sm-3">สกุลเงินต่างประเทศ :</label>
                                            <div class="col-sm-3"><select class="form-control" id="currencyTypeSelect" onchange="addExchangRate()"></select></div>
                                            <label class="control-label col-sm-3">อัตราแลกเปลี่ยน :</label>
                                            <div class="col-sm-3"><input class="form-control text-right" id="exchangeRateInput" value="0.00" onblur="convertAmount()"></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-3">วันที่โอน :</label>
                                            <div class="col-sm-3">
                                                <div class="input-group input-append date" data-provide="datepicker" data-date-format="dd/mm/yyyy">
                                                    <input class="form-control" id="payDateInputDate" placeholder="dd/MM/yyyy" maxlength="10">
                                                    <span class="input-group-addon add-on"><span class="glyphicon glyphicon-calendar"></span></span>
                                                </div>
                                            </div>
                                            <label class="control-label col-sm-3">จำนวนเงินต่างประเทศ :</label>
                                            <div class="col-sm-3"><input class="form-control text-right" id="foreignAmountInput" onblur="convertAmount()"></div>
                                        </div>

                                        <div class="form-group">
                                            <label class="control-label col-sm-3">เลขที่บัญชีที่โอนเข้า :</label>
                                            <div class="col-sm-3"><select class="form-control" id="payBankTxnfDropDownBank"></select></div>
                                            <label class="control-label col-sm-3">จำนวนเงิน(บาท) :</label>
                                            <div class="col-sm-3"><input class="form-control text-right" id="thAmountInput" value="0.00"></div>
                                        </div>
                                    </div>
                                    <!-- <div class="form-group">
                                        <label class="control-label col-sm-3">
                            <input type="checkbox" id="billAvdChecked" name="advancedCheckbox" >
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ใบเสร็จรับเงินอย่างเดียว</label>
                                        <label class="control-label col-sm-3">
                                <input type="checkbox" id="vatAvdChecked" name="advancedCheckbox" >
                                &nbsp;&nbsp;&nbsp;ใบกำกับภาษีอย่างเดียว</label></div> -->
                                    <a class="btn btn-warning pull-right" id="payForeignButtonSubmit"><span class="glyphicon glyphicon-plus-sign"></span> เพิ่มรายการอื่น ๆ</a>
                                    <table id="payForeignTableList" class="table">
                                        <thead>
                                            <tr>
                                                <th data-align="center" data-running-no="true">#</th>
                                                <th>สกุลเงินต่างประเทศ</th>
                                                <th>อัตราแลกเปลี่ยน</th>
                                                <th>วันที่โอน</th>
                                                <th data-align="center" data-number-format="true">จำนวนเงินต่างประเทศ</th>
                                                <th data-align="right" data-number-format="true">จำนวนเงิน(บาท)</th>
                                                <th></th>
                                            </tr>
                                        </thead>
                                    </table>
                                </div>
                                <div role="tabpanel" class="tab-pane hide" id="payType9">
                                    <div class="form-horizontal">
                                        <div class="form-group">
                                            <label class="control-label col-sm-3">ช่องทางการชำระ :</label>
                                            <div class="col-sm-3"><select class="form-control" id="payOtherDropDownChannel"></select></div>
                                            <label class="control-label col-sm-3">เลขที่อ้างอิง :</label>
                                            <div class="col-sm-3"><input class="form-control" id="payOtherInputNo"></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-3">วันที่อ้างอิง :</label>
                                            <div class="col-sm-3">
                                                <div class="input-group input-append date" data-provide="datepicker" data-date-format="dd/mm/yyyy">
                                                    <input class="form-control" id="payOtherInputDate" placeholder="dd/MM/yyyy" maxlength="10">
                                                    <span class="input-group-addon add-on"><span class="glyphicon glyphicon-calendar"></span></span>
                                                </div>
                                            </div>
                                            <label class="control-label col-sm-3">จำนวนเงิน :</label>
                                            <div class="col-sm-3"><input class="form-control text-right" id="payOtherInputAmt"></div>
                                        </div>
                                    </div>
                                    <a class="btn btn-warning pull-right" id="payOtherButtonSubmit"><span class="glyphicon glyphicon-plus-sign"></span> เพิ่มรายการอื่น ๆ</a>
                                    <table id="payOtherTableList" class="table">
                                        <thead>
                                            <tr>
                                                <th data-align="center" data-running-no="true">#</th>
                                                <th>เลขที่อ้างอิง</th>
                                                <th>ช่องทางการรับชำระ</th>
                                                <th>วันที่รับชำระ</th>
                                                <th data-align="right" data-number-format="true">จำนวนเงิน</th>
                                                <th></th>
                                            </tr>
                                        </thead>
                                    </table>
                                </div>
                                <div role="tabpanel" class="tab-pane hide" id="payType10">
                                    <div class="form-horizontal">
                                        <div class="form-group">
                                            <label class="control-label col-sm-4">รหัสธนาคาร :</label>
                                            <div class="col-sm-3"><input class="form-control" id="payBankTxnfInputBankCodeGf" maxlength="5"></div>
                                            <label class="control-label col-sm-2">ชื่อธนาคาร :</label>
                                            <div class="col-sm-3"><select class="form-control" id="payBankTxnfInputBankNameGf"></select></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-4">สาขา :</label>
                                            <div class="col-sm-3"><select class="form-control" id="payBankTxnfDropDownBankBrnhGf"></select></div>
                                            <label class="control-label col-sm-2">เลขที่อ้างอิง :</label>
                                            <div class="col-sm-3"><input class="form-control" id="payBankTxnfInputNoGf" maxlength="10"></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-4">รหัสบัญชีเงินฝากธนาคาร :</label>
                                            <div class="col-sm-3"><select class="form-control" id="payBankTxnfDropDownBankAcCdGf"></select></div>
                                            <label class="control-label col-sm-2">วันที่โอน :</label>
                                            <div class="col-sm-3">
                                                <div class="input-group input-append date" data-provide="datepicker" data-date-format="dd/mm/yyyy">
                                                    <input class="form-control" id="payBankTxnfInputDateGf" placeholder="dd/MM/yyyy" maxlength="10">
                                                    <span class="input-group-addon add-on"><span class="glyphicon glyphicon-calendar"></span></span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-4">เลขที่บัญชีเงินฝากธนาคาร :</label>
                                            <div class="col-sm-3"><select class="form-control" id="payBankTxnfDropDownBankAcctGf"></select></div>
                                            <label class="control-label col-sm-2">จำนวนเงิน :</label>
                                            <div class="col-sm-3"><input class="form-control text-right" id="payBankTxnfInputAmtGf"></div>
                                        </div>
                                    </div>
                                    <a class="btn btn-warning pull-right" id="payBankTxnfButtonSubmitGf"><span class="glyphicon glyphicon-plus-sign"></span> เพิ่มรายการอื่น ๆ</a>
                                    <table id="payBankTxnfTableListGf" class="table">
                                        <thead>
                                            <tr>
                                                <th data-align="center" data-running-no="true">#</th>
                                                <th>รหัสธนาคาร</th>
                                                <th>ชื่อธนาคาร</th>
                                                <th>สาขา</th>
                                                <th>เลขที่อ้างอิง</th>
                                                <th>วันที่โอน</th>
                                                <th data-align="right" data-number-format="true">จำนวนเงิน</th>
                                                <th></th>
                                            </tr>
                                        </thead>
                                    </table>
                                </div>
                                <a class="btn btn-primary pull-right" id="addPayType" style="margin-top:1em"><span class="glyphicon glyphicon-plus-sign"></span> เพิ่มวิธีการรับชำระ</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div id="informationPanel" class="row">
                <div class="col-md-5">
                    <div class="panel panel-default">
                        <div class="panel-heading">สรุปรายการหัก</div>
                        <div class="panel-body">
                            <table id="deductionList" class="table table-hover">
                                <thead>
                                    <tr>
                                        <th data-running-no="true">#</th>
                                        <th>รายการหัก</th>
                                        <th data-align="right" data-number-format="true" class="text-right">จำนวนเงิน</th>
                                        <th></th>
                                    </tr>
                                </thead>
                            </table>
                        </div>
                    </div>
                </div>
                <div class='col-md-7'>
                    <div class="panel panel-default">
                        <div class="panel-heading">สรุปวิธีการรับชำระ</div>
                        <div class="panel-body">
                            <table id="payTypeList" class="table table-hover">
                                <thead>
                                    <tr>
                                        <th data-running-no="true">#</th>
                                        <th>วิธีการรับชำระ</th>
                                        <th data-align="right" data-number-format="false" class="text-right">จำนวนเงินต่างประเทศ</th>
                                        <th data-align="right" data-number-format="true" class="text-right">จำนวนเงินบาท</th>
                                        <th></th>
                                    </tr>
                                </thead>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <div id="receiptInfoPanel" class="row">
                <div class="col-md-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">สรุปยอดเงินที่ต้องชำระ</div>
                        <div class="col-md-9">
                            <div class="panel-body">
                                <div class="form-horizontal">
                                    <div class="form-group">
                                        <div class="control-label col-sm-12 text-right"><label>เงินตามสกุลที่บันทึก</label></div>
                                    </div>

                                    <!-- <div class="form-group" id="foreignTotalChargeDiv">
                                        <label class="control-label col-sm-9" id="foreignTotalChargeLabel">ยอดเงินที่ต้องชำระรวมภาษีมูลค่าเพิ่ม (Foreign)  :</label>
                                        <div class="col-sm-3">
                                            <input id="foreignTotalCharge" class="form-control text-right" disabled>
                                        </div>
                                    </div> -->
                                    <div class="form-group" id="foreignExchangeDiv">
                                        <label class="control-label col-sm-9">อัตราแลกเปลี่ยน  :</label>
                                        <div class="col-sm-3">
                                            <input id="foreignExchangeRate" class="form-control text-right" disabled>
                                        </div>
                                    </div>
                                    <input id="moneyToPay" hidden="hidden" />
                                    <div class="form-group">
                                        <label class="control-label col-sm-9">ยอดเงินก่อนหักส่วนลด :</label>
                                        <div class="col-sm-3">
                                            <input id="preItemsDiscount" class="form-control text-right" disabled="disabled">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-sm-9">ส่วนลด :</label>
                                        <div class="col-sm-3">
                                            <input id="itemsDiscount" class="form-control text-right" disabled="disabled">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label class="control-label col-sm-9">ยอดเงินที่ต้องชำระก่อนภาษีมูลค่าเพิ่ม :</label>
                                        <div class="col-sm-3">
                                            <input id="charge" class="form-control text-right" disabled="disabled">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-sm-9">ภาษีมูลค่าเพิ่ม :</label>
                                        <div class="col-sm-3">
                                            <input id="vat" class="form-control text-right" disabled="disabled">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-sm-9">ยอดเงินที่ต้องชำระรวมภาษีมูลค่าเพิ่ม :</label>
                                        <div class="col-sm-3">
                                            <input id="totalCharge" class="form-control text-right" disabled="disabled">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-sm-9">ภาษีหัก ณ ที่จ่าย  :</label>
                                        <div class="col-sm-3">
                                            <input id="deduct" class="form-control text-right" disabled>
                                        </div>
                                    </div>
                                    <div class="form-group" id="feeSummaryDiv">
                                        <label class="control-label col-sm-9">ค่าธรรมเนียม  :</label>
                                        <div class="col-sm-3">
                                            <input id="fee" class="form-control text-right">
                                        </div>
                                    </div>
                                    <div class="form-group" id="penaltySummaryDiv">
                                        <label class="control-label col-sm-9">ค่าปรับ  :</label>
                                        <div class="col-sm-3">
                                            <input id="penalty" class="form-control text-right">
                                        </div>
                                    </div>
                                    <!-- <div class="form-group" id="retentionSummaryDiv">
                                        <label class="control-label col-sm-9">เงินประกันผลงาน  :</label>
                                        <div class="col-sm-3">
                                            <input id="retention" class="form-control text-right" disabled>
                                        </div>
                                    </div> -->
                                    <div class="form-group" id="compensationSummaryDiv">
                                        <label class="control-label col-sm-9">ค่าตอบแทนการรับชำระเงิน  :</label>
                                        <div class="col-sm-3">
                                            <input id="compensation" class="form-control text-right">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <%--<label class="control-label col-sm-9">
                                        <div class="checkbox">
                                            <label>
                                                <input type="checkbox" id="check_dis_notused" disabled> <b>ลูกค้ารับภาระภาษี&nbsp;&nbsp;&nbsp;<span class="glyphicon glyphicon-lock"></span> อื่นๆ :</b>
                                            </label>
                                        </div>
                                    </label>--%>
                                            <div class="control-label col-sm-9">
                                                <label class="strong"><input type="radio" name="discountCheckbox" disabled="disabled" value="1"> รับภาระภาษีเต็มจำนวน</label>&nbsp;&nbsp;&nbsp;&nbsp;
                                                <label class="strong"><input type="radio" name="discountCheckbox" disabled="disabled" value="2"> รับภาระภาษีบางส่วน :</label>
                                            </div>
                                            <div class="col-sm-3">
                                                <input id="discount" class="form-control text-right" disabled="disabled">
                                            </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-sm-9">ยอดเงินที่ต้องชำระ :</label>
                                        <div class="col-sm-3">
                                            <input id="balanceDue" class="form-control text-right" disabled>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-sm-9">ยอดเงินรับมา :</label>
                                        <div class="col-sm-3">
                                            <input id="inputReceived" class="form-control text-right" disabled="disabled">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-sm-9">เงินทอน :</label>
                                        <div class="col-sm-3">
                                            <input id="change" class="form-control text-right" disabled="disabled">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="control-label col-sm-9">
                                            <label class="strong"><input type="radio" name="specialOptions" value="3"> รับชำระล่วงหน้า :</label>
                                        </div>
                                        <div class="col-sm-3">
                                            <input id="inputAdvanced" class="form-control text-right">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="col-md-3">
                            <div class="panel-body">
                                <div class="form-horizontal">
                                    <div class="form-group">
                                        <div class="control-label col-sm-12 text-right"><label>เงินสกุลในประเทศ</label></div>
                                    </div>
                                    <!-- <div class="form-group" id="foreignTotalChargeDivBath">
                                        <div class="col-sm-3"></div>
                                        <div class="col-sm-9">
                                            <input id="foreignTotalChargeBath" class="form-control text-right" disabled>
                                        </div>
                                    </div> -->
                                    <div class="form-group" id="foreignExchangeDivBath">
                                        <div class="col-sm-3"></div>
                                        <div class="col-sm-9">
                                            <input id="foreignExchangeRateBath" class="form-control text-right" disabled>
                                        </div>
                                    </div>
                                    <input id="moneyToPayBath" hidden="hidden" />
                                    <div class="form-group">
                                        <div class="col-sm-3"></div>
                                        <div class="col-sm-9">
                                            <input id="preItemsDiscountBath" class="form-control text-right" disabled="disabled">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-3"></div>
                                        <div class="col-sm-9">
                                            <input id="itemsDiscountBath" class="form-control text-right" disabled="disabled">
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="col-sm-3"></div>
                                        <div class="col-sm-9">
                                            <input id="chargeBath" class="form-control text-right" disabled="disabled">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-3"></div>
                                        <div class="col-sm-9">
                                            <input id="vatBath" class="form-control text-right" disabled="disabled">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-3"></div>
                                        <div class="col-sm-9">
                                            <input id="totalChargeBath" class="form-control text-right" disabled="disabled">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-3"></div>
                                        <div class="col-sm-9">
                                            <input id="deductBath" class="form-control text-right" disabled>
                                        </div>
                                    </div>
                                    <div class="form-group" id="feeSummaryDivBath">
                                        <div class="col-sm-3"></div>
                                        <div class="col-sm-9">
                                            <input id="feeBath" class="form-control text-right">
                                        </div>
                                    </div>
                                    <div class="form-group" id="penaltySummaryDivBath">
                                        <div class="col-sm-3"></div>
                                        <div class="col-sm-9">
                                            <input id="penaltyBath" class="form-control text-right">
                                        </div>
                                    </div>
                                    <!-- <div class="form-group" id="retentionSummaryDivBath">
                                        <div class="col-sm-3"></div>
                                        <div class="col-sm-9">
                                            <input id="retentionBath" class="form-control text-right" disabled>
                                        </div>
                                    </div> -->
                                    <div class="form-group" id="compensationSummaryDivBath">
                                        <div class="col-sm-3"></div>
                                        <div class="col-sm-9">
                                            <input id="compensationBath" class="form-control text-right">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="control-label col-sm-3">
                                            <!-- <label class="strong"><input type="radio" name="discountCheckbox" disabled="disabled" value="1B"> รับภาระภาษีเต็มจำนวน</label>&nbsp;&nbsp;&nbsp;&nbsp;
                                                <label class="strong"><input type="radio" name="discountCheckbox" disabled="disabled" value="2B"> รับภาระภาษีบางส่วน :</label> -->
                                        </div>
                                        <div class="col-sm-9">
                                            <input id="discountBath" class="form-control text-right" disabled="disabled">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-3"></div>
                                        <div class="col-sm-9">
                                            <input id="balanceDueBath" class="form-control text-right" disabled>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-3"></div>
                                        <div class="col-sm-9">
                                            <input id="inputReceivedBath" class="form-control text-right" disabled="disabled">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-sm-3"></div>
                                        <div class="col-sm-9">
                                            <input id="changeBath" class="form-control text-right" disabled="disabled">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="control-label col-sm-3">

                                        </div>
                                        <div class="col-sm-9">
                                            <input id="inputAdvancedBath" class="form-control text-right">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>

            <div class="modal fade" role="dialog" id="modal_authen">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title"><span class="glyphicon glyphicon-user"></span> Authentication</h4>
                        </div>
                        <div class="modal-body">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="col-md-2">

                                    </div>
                                    <div class="col-md-10">
                                        <div class="form-horizontal">
                                            <div class="form-group">
                                                <label class="col-sm-3 control-label">ผู้อนุมัติ :</label>
                                                <div class="col-sm-6">
                                                    <input type="text" class="form-control">
                                                </div>
                                                <label class="col-sm-3 control-label"></label>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-3 control-label">รหัสผ่าน :</label>
                                                <div class="col-sm-6">
                                                    <input type="password" class="form-control">
                                                </div>
                                                <label class="col-sm-3 control-label"></label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-success"><span class="glyphicon glyphicon-ok-circle"></span> ตกลง</button>
                            <button type="button" class="btn btn-danger" data-dismiss="modal"><span class="glyphicon glyphicon-remove-circle"></span> ยกเลิก</button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal fade" role="dialog" id="advancePayment">
                <div class="modal-dialog modal-lg">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title">เลือกรายการลูกค้าที่จะรับชำระล่วงหน้า</h4>
                        </div>
                        <div class="modal-body">
                            <div id="advancePaymentMessage"></div>
                            <div class="row">
                                <div class="col-md-12">
                                    <label>รายการลูกค้า</label>

                                    <table id="customerList" data-toggle="table">
                                        <thead>
                                            <tr>
                                                <th data-align="center" data-formatter="runningFormatter">#</th>
                                                <th data-field="custNo">เลขที่ลูกค้า</th>
                                                <th data-field="custName">ชื่อลูกค้า</th>
                                                <!--<th data-field="svcType">ประเภทบริการ</th>-->
                                                <th data-field="billGroup">Billing Group</th>
                                                <th data-field="advanceAmount" data-formatter="numberInputFormatter">ยอดชำระ</th>
                                                <th data-field="billCycle" data-formatter="billCycleInputFormatter">รอบการใช้งาน</th>
                                            </tr>
                                        </thead>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <a id="advancePaymentSubmit" class="btn btn-default"><span class="glyphicon glyphicon-ok-circle"></span> เลือกรายการ</a>
                            <a id="advancePaymentCancel" class="btn btn-default" data-dismiss="modal"><span class="glyphicon glyphicon-remove-circle"></span> ยกเลิก</a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal fade" role="dialog" id="otherProfitVat">
                <div class="modal-dialog modal-lg">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title">เลือกรายการลูกค้า</h4>
                        </div>
                        <div class="modal-body">
                            <div id="advancePaymentMessage2"></div>
                            <div class="row">
                                <div class="col-md-12">
                                    <label>รายการลูกค้า</label>

                                    <table id="customerList2" data-toggle="table">
                                        <thead>
                                            <tr>
                                                <th data-align="center" data-formatter="runningFormatter">#</th>
                                                <th data-field="custNo">เลขที่ลูกค้า</th>
                                                <th data-field="custName">ชื่อลูกค้า</th>
                                                <th data-field="billGroup">Billing Group</th>
                                                <th data-field="otherAmount" data-formatter="numberInputFormatter2">ยอดชำระ</th>
                                                <th data-formatter="stringInputFormatter" data-field="otherNote">เพิ่มเติม</th>
                                            </tr>
                                        </thead>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <a id="otherPaymentSubmit" class="btn btn-default"><span class="glyphicon glyphicon-ok-circle"></span> เลือกรายการ</a>
                            <a id="otherPaymentCancel" class="btn btn-default" data-dismiss="modal"><span class="glyphicon glyphicon-remove-circle"></span> ยกเลิก</a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal fade" role="dialog" id="creditLimitDialog">
                <div class="modal-dialog modal-lg">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title">เลือกรายการ Subscription</h4>
                        </div>
                        <div class="modal-body">
                            <div id="creditLimitMessage"></div>
                            <div class="row">
                                <div class="col-md-12">
                                    <label>รายการใบแจ้งหนี้ / Subscription</label>
                                    <table id="subScriptionListTb" data-maintain-selected="true" class="table table-hover">
                                        <thead>
                                            <tr>
                                                <!--
                                            <th  data-field="checked" data-checkbox="true"><input name="btSelectAll" type="checkbox"></th>
                                            -->
                                                <th data-formatter="checkboxFormatter"><input id="btSelectAll" type="checkbox"></th>

                                                <th data-field="ba">BA</th>
                                                <!-- ice fixed code no.109 'ทุกระบบต้องเลขที่ใบแจ้งค่าใช้บริการใบแจ้งค่าใช้บริการ" -->
                                                <th data-field="invoiceNo">เลขที่ใบแจ้งค่าใช้บริการ</th>
                                                <!-- end ice fixed code no.109 'ทุกระบบต้องเลขที่ใบแจ้งค่าใช้บริการใบแจ้งค่าใช้บริการ" -->
                                                <th data-field="subscriptionNo">Subscription</th>
                                            </tr>
                                        </thead>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <a id="creditLimitSubmit" class="btn btn-default"><span class="glyphicon glyphicon-ok-circle"></span> เลือกรายการ</a>
                            <a id="creditLimitCancel" class="btn btn-default" data-dismiss="modal"><span class="glyphicon glyphicon-remove-circle"></span> ยกเลิก</a>
                        </div>
                    </div>
                </div>
            </div>
    </section>

</body>

</html>
<script type="text/javascript">
    var view = (function ($) {
        var self = this;
        self.session = function (key, val) { if (!val) { var val = window.sessionStorage.getItem(key); return val && (val.indexOf("{") > -1 || val.indexOf("[") > -1) ? JSON.parse(val) : val } window.sessionStorage.setItem(key, ($.type(val) != "object" && $.type(val) != "array" ? val : JSON.stringify(val))) };
        self.utils = {
            guid: function () { function r() { return Math.floor((Math.random() + 1) * 0x10000).toString(16).substring(1) } return r() + r() + "-" + r() + "-" + r() + "-" + r() + "-" + r() + r() + r() },
            numberFormat: function (num, dec) { return $.type(num) !== "number" ? "0.00" : num.toFixed(dec || 2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,'); },
            dateFormat: function () { var obj = arguments[0]; if ($.type(obj) == "string") { var regGroupYYYYMMDD = /([0-9]{4}).([0-9]{2}).([0-9]{2})/g; var regGroupDDMMYYYY = /([0-9]{2}).([0-9]{2}).([0-9]{4})/g; var match = regGroupYYYYMMDD.exec(obj); if (match == null) { match = regGroupDDMMYYYY.exec(obj); dd = match[1]; mm = match[2]; yyyy = match[3]; } else { dd = match[3]; mm = match[2]; yyyy = match[1]; } return (dd[1] ? "" : "0") + dd + "/" + (mm[1] ? "" : "0") + mm + "/" + yyyy } else if ($.isNumeric(obj)) { var dt = new Date(obj), dd = dt.getDate().toString(), mm = (dt.getMonth() + 1).toString(), yyyy = dt.getFullYear().toString(); return (dd[1] ? "" : "0") + dd + "/" + (mm[1] ? "" : "0") + mm + "/" + yyyy } else if ($.type == "date") { return "" } return "" },
            dateTimeFormat: function () { var obj = arguments[0], dt = $.type(obj) != "date" ? ($.isNumeric(obj) ? new Date(obj) : new Date()) : new Date(), dd = dt.getDate().toString(), MM = (dt.getMonth() + 1).toString(), yyyy = dt.getFullYear().toString(), hh = dt.getHours().toString(), mm = dt.getMinutes().toString(), ss = dt.getSeconds().toString(); return (dd[1] ? "" : "0") + dd + "/" + (MM[1] ? "" : "0") + MM + "/" + yyyy + " " + (hh[1] ? "" : "0") + hh + ":" + (mm[1] ? "" : "0") + mm + ":" + (ss[1] ? "" : "0") + ss },
            queryString: function () { var pos = location.href.indexOf("?"), obj = {}; if (pos < 0) return obj; var params = location.href.slice(pos + 1).split("&"); for (var i = 0, m = params.length; i < m; i++) { pos = params[i].indexOf("="); if (pos < 0) { obj[params[i]] = true; continue; } obj[params[i].substring(0, pos)] = params[i].substring(pos + 1) } return obj },
            window: function (windowName, url, side) { window.windowOpened = (window.windowOpened || {}); var screenProp = ""; if (side == "left") { screenProp = "left=0,top=0,width=" + (screen.width / 2) + ",height=" + (screen.height - 100) } else if (side == "right") { screenProp = "left=" + (screen.width / 2 - 40) + ",top=0,width=" + (screen.width / 2 + 40) + ",height=" + (screen.height - 100) } window.windowOpened[windowName] = window.open(url, windowName, "menubar=no,scrollbars=no,resizable=0," + screenProp, false); return window.windowOpened }
        };
        //// AUTOMATIC REGISTER FORMATTER FUNCTION ////
        window.get = function (url, params, success, msgDialog, preCheck) { $.ajax({ "url": url, "type": "GET", "data": params, "error": msgDialog.valid, "success": function (res) { (preCheck || function (o) { console.log(o) })(res); if (msgDialog && !msgDialog.valid(res)) { return; } success(res) } }) }
        window.getSync = function (url, params, success, msgDialog, preCheck) { $.ajax({ "url": url, "type": "GET", "data": params, "async": false, "error": (msgDialog || { "valid": function (jqXHR, textStatus, errorThrow) { console.log(jqXHR); console.log("textStatus: " + textStatus); console.log("errorThrow:"); console.log(errorThrow) } }).valid, "success": function (res) { var isNotJson = res.constructor == String; console.log(res); (preCheck || function (o) { })(res); if (isNotJson) res = { "statusCode": "99", "errorList": ["No matching data found."] }; if (msgDialog && !msgDialog.valid(res)) { return; } success(res) } }) }
        window.post = function (url, params, success, msgDialog, preCheck) { $.ajax({ "url": url, "type": "POST", "data": params, "error": msgDialog.valid, "success": function (res) { (preCheck || function (o) { console.log(o) })(res); if (msgDialog && !msgDialog.valid(res)) { return; } success(res) } }) }
        window.add = function (num1, num2, dec) { if (!dec) dec = 2; return parseFloat((num1 + num2).toFixed(dec), 10); }; window.subtract = function (num1, num2, dec) { if (!dec) dec = 2; return parseFloat((num1 - num2).toFixed(dec), 10); }; window.multiply = function (num1, num2, dec) { if (!dec) dec = 2; return parseFloat((num1 * num2).toFixed(dec), 10); }; window.divide = function (num1, num2, dec) { if (!dec) dec = 2; return parseFloat((num1 / num2).toFixed(dec), 10); }
        window.stripToNumber = function (str) { return $.type(str) === "string" && $.isNumeric(str = str.replace(/[,]/g, "")) ? parseFloat(str, 10) : 0 }
        window.runningFormatter = function (val, row, ind) { return ind + 1 }; window.nullToDashedFormatter = function (val) { return val != null && (val == $.trim(val)).length > 0 ? val : "-" }
        window.numberFormatter = function (val) { return !$.isNumeric(stripToNumber(val)) ? "0.00" : parseFloat(val, 10).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,') }
        /*window.stringInputFormatter = function(val, row, ind) { return '<input id="custNote" value="'+ $.trim(val) +'" maxLength="100" class="form-control">' }*/
        window.stringInputFormatter = function (val, row, ind) {
            var index = "otherNote" + ind;
            return '<input id="' + index + '" value="เงินยกให้กสท" maxLength="100" class="form-control">'
        }
        window.billCycleInputFormatter = function (val, row, ind) {
            var index = "billCycle" + ind;
            return '<input id="' + index + '" value="" maxLength="140" class="form-control">'
        }
        window.numberInputFormatter = function (val, row, ind) { return '<input value="' + self.utils.numberFormat(parseFloat(val || "0.00", 10)) + '" maxLength="10" style="width:100px" class="form-control text-right" onkeypress="return $.isNumeric(String.fromCharCode(event.which)) || (event.which == 46 && this.value.indexOf(\'.\') == -1)" onfocus="(function(o){ o.value = o.value.replace(/[,]/g, \'\') })(this)" onblur="(function(o){ o.value = parseFloat(o.value, 10).toFixed(2).replace(/(\\d)(?=(\\d{3})+\\.)/g, \'$1,\'); })(this)">' }
        window.numberInputFormatter2 = function (val, row, ind) {
            var index = "otherAmt" + ind;
            return '<input id="' + index + '" value="' + self.utils.numberFormat(parseFloat(val || "0.00", 10)) + '" maxLength="10" style="width:100px" class="form-control text-right" onkeypress="return $.isNumeric(String.fromCharCode(event.which)) || (event.which == 46 && this.value.indexOf(\'.\') == -1)" onfocus="(function(o){ o.value = o.value.replace(/[,]/g, \'\') })(this)" onblur="(function(o){ o.value = parseFloat(o.value, 10).toFixed(2).replace(/(\\d)(?=(\\d{3})+\\.)/g, \'$1,\'); })(this)">'
        }
        window.modifyButtonFormatter = function (val, row, ind) { return '<a class="btn btn-link modList" onclick=\'(function(elem, row, ind){ var table = $("#"+ $(elem).closest("table")[0].id); window[table[0].id +"ModifyEvent"](table, row, ind) })(this, ' + JSON.stringify(row) + ', ' + ind + ')\'><span class="glyphicon glyphicon-pencil"></span></a>' }
        window.removeButtonFormatter = function (val, row, ind) { return '<a class="btn btn-link delList" onclick=\'(function(elem, row, ind){ $("#"+ $(elem).closest("table")[0].id).bootstrapTable("remove", { field: "index", values: [ind] }) })(this, ' + JSON.stringify(row) + ', ' + ind + ')\'><span class="glyphicon glyphicon-trash"></span></a>' }
        function Button(el) {
            var obj = this, badge; obj.el = el; obj.elem = $(el);
            obj.hide = function () { this.elem.addClass("hide"); return this }; obj.show = function () { this.elem.removeClass("hide"); return this };
            obj.hideLoad = function () { obj.elem.button("reset"); return this }; obj.showLoad = function () { obj.elem.button("loading"); return this };
            obj.disable = function (flag) { if (flag == null || flag) this.elem.addClass("disabled").attr("disabled", true); else this.elem.removeClass("disabled").attr("disabled", false); return this }; obj.enable = function () { obj.disable(false); return this };
            obj.badge = function (val) { if (badge) badge.text(val) }; if ((badge = this.elem.next()).length == 0) badge = null;
            obj.elem.click(window[el.substring(1) + "ClickEvent"]);
        }
        function Radio(el) {
            var obj = this; obj.el = el; obj.elem = $(el);
            obj.disable = function () { if (arguments.length == 2 && $.isNumeric(arguments[0])) obj.elem.eq(arguments[0]).attr("disabled", (arguments[1] != null && $.type(arguments[1]) == 'boolean' ? arguments[1] : false)); return this }; obj.enable = function () { obj.disable(false); return this }
            obj.label = function () { return this.elem.filter(":checked").data("label") };
            obj.type = function () { return this.elem.filter(":checked").data("type") };
            obj.val = function () { var s = this.elem.filter(":checked"), val = s.val(); return !val ? s.data("label") : val };
            obj.checked = function (index) { obj.elem.eq(index).attr("checked", true); return this }
            obj.unchecked = function () { obj.elem.each(function (i, o) { o.checked = false }); return this }
            obj.click = function (evt) { obj.elem.each(function (i, o) { $(o).click(function (e) { if (o.checked) evt(o.value) }) }); return this }
        }
        function Input(el, maxLen, propPos) {
            var obj = this;
            obj.el = el;
            obj.elem = $(el);
            obj.error = function (flag) { if (arguments.length == 0 || flag) obj.elem.parent().addClass("has-error"); else obj.elem.parent().removeClass("has-error"); return this }
            obj.clear = function () { obj.val(""); return this }
            obj.empty = function () { return $.trim(obj.val()) === "" }
            obj.click = function (func) { obj.elem.click(func); return this }
            obj.readOnly = function (flag) { obj.elem.attr("readonly", (flag == null ? true : flag)); return this }
            obj.disable = function (flag) { obj.elem.attr("disabled", (flag == null ? true : flag)); return this }
            obj.enable = function () { obj.disable(false); return this }
            obj.val = function () { if (arguments.length == 1) { this.elem.val(arguments[0]) } return this.elem.val() }
            obj.get = function (propName) { if ($.type(propPos[propName]) !== "array" || propPos[propName].length !== 2) return ""; return obj.val().substring(propPos[propName][0], propPos[propName][1]) }
            if ($.isNumeric(maxLen)) { obj.elem.attr("maxLength", maxLen) }
        }
        function NumberInput(el, dec) {
            var obj = this, decimal = (dec || 2); this.el = el; this.elem = $(el);
            obj.val = function () { if (arguments.length == 0) return parseFloat(strip(this.elem.val() || "0"), 10); this.elem.val(format(arguments[0])); }
            obj.decimal = function (dec) { decimal = dec }; obj.format = format;
            obj.disable = function () { obj.elem.attr("disabled", (arguments.length < 1 ? true : arguments[0])); return obj }; obj.enable = function () { obj.disable(false); return obj }
            obj.hide = function () { this.elem.addClass("hide"); return this }; obj.show = function () { this.elem.removeClass("hide"); return this };
            obj.elem.keypress(function (e) { var key = (e.which || e.keyCode || e.charCode || 0); var ch = String.fromCharCode(key); return "0123456789.".indexOf(ch) > -1 });
            obj.elem.focus(function () { this.value = strip(this.value); this.select() });
            obj.elem.blur(function () { this.value = format(this.value) });
            function format(val) { return ($.isNumeric(val) ? parseFloat(val, 10) : obj.val()).toFixed(decimal).replace(/(\d)(?=(\d{3})+\.)/g, '$1,'); };
            function strip(str) { return (str || "").replace(/[,]/g, "") }
            if (obj.elem.val() == "") obj.elem.val("0.00");
        }
        function DropDown(el, url) {
            var obj = this, data = [{ key: "", value: "", text: "Please Select" }]; obj.el = el; obj.elem = $(el);
            obj.data = function (array) { if ($.type(array) == "array") { setup(data = array); return this } return data; };
            obj.init = function (url, v) { if (url) $.get(url, function (res) { if (res != null) { if (res.data[0].rateCode != null) { exchange = res.data; } } setup(data = res.data, v); }); else setup(data, v); return this };
            
            obj.initV3 = function(url) { if (url)
                        	$.ajax({
                        	    url : url,
                        	    type : "get",
                        	    data: { },
                        	    async: false,
                        	    success : function(res) {
                        	    	setupV1(data = res.data)
                        	    },
                        	    error: function() {
                        	       connectionError();
                        	    }
                        	 });
                        else setupV1(data); return this };

            obj.error = function (flag) { if (arguments.length == 0 || flag) obj.elem.parent().addClass("has-error"); else obj.elem.parent().removeClass("has-error"); return this }
            obj.disable = function (flag) { obj.elem.attr("disabled", (flag == null ? true : flag)); return this }
            obj.enable = function () { obj.disable(false); return this }
            obj.index = function () { if (arguments.length == 1) { var maxLen = data.length, index = arguments[0]; obj.elem.find("option").each(function (i, opt) { opt.removeAttribute("selected") }).eq(index < 0 ? maxLen + index : index).attr("selected", true); return this } return $.inArray("selected", obj.elem.find("option").map(function (i, opt) { return opt.selected ? "selected" : "no-selected" })) }
            obj.selected = function () { return data[obj.index()]; };
            obj.val = function () { return obj.elem.val(); };
            obj.key = function () { if (arguments.length == 0) { var key = obj.elem.find("option:selected").data("key"); if (typeof key === "string" && key === "undefined") { var item = data[obj.index()]; key = item && item.key ? item.key : item.id } return key } var key = arguments[0]; for (var i = 0, m = data.length; i < m; i++) { var item = data[i]; if (item.key && item.key == key) { obj.index(i); break; } if (item.id && item.id == key) { obj.index(i); break; } } return this }
            function setup(array, v) {
                obj.elem.find("*").remove();
                //$("#currencyTypeSelect").append('<option data-index="0" data-key="0" value="0">กรุณาเลือก</option>');
                obj.elem.append('<option data-index="-1" data-key="-1" value="">กรุณาเลือก</option>');
                $.each(array, function (i, o) {
                    if (o.category == 'bank.name') {
                        if (v == 'name') {
                            obj.elem.append('<option data-index="' + i + '" data-key="' + o.code + '" value="' + o.descFullTh + '"e>' + o.descFullTh + '</option>')
                        }
                        else {
                            obj.elem.append('<option data-index="' + i + '" data-key="' + o.code + '" value="' + o.code + '">' + o.code + '</option>')
                        }
                    }
                    else {
                        if (o.rateCode != null) {
                            x = i;
                            x++;
                            obj.elem.append('<option data-index="' + x + '" data-key="' + x + '" value="' + o.message + '">' + o.message + '</option>')
                        } else {
                            obj.elem.append('<option data-index="' + i + '" data-key="' + o.key + '" value="' + o.value + '">' + o.value + '</option>')
                        }
                    }
                });
            }
            function setupV1(array) { obj.elem.find("*").remove();
            		$.each(array,function(i,o){ obj.elem.append('<option data-index="' + i + '" data-key="' + i + '" value="' + o.message + '">' + o.message + '</option>') }); 
                    }
            this.init(url);
        }
        function Message(el) {
            var obj = this, timeCnt = 0, loadFunc; obj.el = el; obj.elem = $(el);
            obj.hide = function () { obj.elem.addClass("hide"); return obj };
            obj.show = function (flag) { if (flag == null || flag) obj.elem.removeClass("hide"); else obj.hide(); return obj };
            obj.clear = function () { obj.elem.find("*").remove(); obj.hide(); return obj };
            obj.message = function (arr, cls) { $.each(arr, function (i, o) { var m = o; if ($.type(o) === "object") { m = (o.desc || o.messageDesc) + " [code=" + (o.code || o.messageCode) + "]" }; obj.elem.append('<div class="' + cls + '">' + m + '<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button></div>') }); return obj };
            obj.error = function (arr) { return obj.message(arr, "alert alert-danger") };
            obj.warn = function (arr) { return obj.message(arr, "alert alert-warning") };
            obj.success = function (arr) { return obj.message(arr, "alert alert-success") };
            obj.valid = function (jqXHR, textStatus, errorThrow) { var res = jqXHR; if (jqXHR && jqXHR.responseJSON) res = jqXHR.responseJSON; if (res && (res.statusCode == '0' || $.type(res._embedded) === 'object')) return true; obj.warn(res && $.type(res.warningList) === 'array' ? res.warningList : []).error(res && $.type(res.errorList) === 'array' ? res.errorList : ["An error occurred on the server side but there is no error message returned."]).show(); return false }
            obj.hideLoad = function () { obj.stopLoad().clear(); return this }; obj.stopLoad = function () { clearInterval(loadFunc); return this }
            obj.showLoad = function (msg) { obj.elem.append('<div id="' + obj.el + '-loading" class="alert alert-warning">' + bind(msg, 0) + '</div>'); timeCnt = 0; var elem = document.getElementById(obj.el + "-loading"); loadFunc = setInterval(function () { elem.innerHTML = bind(msg, ++timeCnt) }, 1000); obj.show(); return this }
            function bind(msg, timeCnt) { return msg.replace(/\{timeCnt\}/g, timeCnt) }
        }
        function Table(el) {
            var obj = this, headers = [], data = [], onApn = function () { }, onDel = onApn, loadCnt = 0, loadInt, loadFunc = function () { obj.body.find("div#loading").html("Loading" + Array(++loadCnt).join(".")); if (loadCnt > 8) loadCnt = 0; };
            var checkboxHtml = '<input type="checkbox" name="{field}" value="{value}" data-index="{index}">', radioHtml = '<input type="radio" name="{field}" value="{value}" data-index="{index}">', inputHtml = '<input name="{field}" value="{value}" {style} data-index="{index}">';
            obj.el = el; obj.elem = $(el); obj.body = obj.elem.find("tbody");
            obj.onAppend = function (func) { onApn = func }; obj.onDelete = function (func) { onDel = func };
            obj.hideLoad = function () { obj.elem.find("tbody tr").remove(); clearInterval(loadInt); return obj };
            obj.showLoad = function () { obj.clear(); obj.elem.find("tbody").append("<tr><td colspan='" + headers.length + "'><div id='loading' style='text-align:center;font:BOLD 16pt Arial'>Loading</div></td></tr>"); loadCnt = 0; loadInt = setInterval(loadFunc, 500); return obj };
            function reorder(index) { obj.body.find("tr").each(function (i, o) { $(o).find("td").eq(index).text(i + 1) }) }
            obj.insert = function (array, showRemove, attrs) {
                if (arguments.length < 1) array = $.map(headers, function (o) { return " " });
                obj.body.append("<tr " + $.map($.extend(attrs, {}), function (v, k) { return k + "='" + v + "'" }).join(" ") + ">"
                    + $.map(array, function (v, i) {
                        var field = headers[i].field, value = v, colStyle = "", colWidth = 0;
                        if (headers[i].runningNo) value = (obj.size() + 1);
                        else if (headers[i].numberFormat) { value = !$.isNumeric(v) ? "0.00" : parseFloat(v, 10).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,'); }
                        else if (headers[i].checkbox) value = replace(checkboxHtml, null, field, i, v); else if (headers[i].radio) value = replace(radioHtml, null, field, i, v);
                        else if (headers[i].input) value = replace(inputHtml, 'style="width:100%;margin:-4px 0;text-align:' + (headers[i].align || "left") + '"', field, i, v);
                        if (headers[i].modifyButton) { colWidth += 45; value += '<a class="btn btn-link" onclick=\'(function(tableId, row, col){ window[tableId +"ModifyEvent"]($("#"+ tableId), row, col); })(this.parentNode.parentNode.parentNode.parentNode.parentNode.id, ' + JSON.stringify(array) + ', this)\'><span class="glyphicon glyphicon-pencil"></span></a>'; } if (headers[i].removeButton) { colWidth += 45; value += '<a class="btn btn-link" onclick=\'(function(tableId, row, col){ window[tableId +"RemoveEvent"]($("#"+ tableId), row, col); col.parentNode.parentNode.parentNode.remove() })(this.parentNode.parentNode.parentNode.parentNode.parentNode.id, ' + JSON.stringify(array) + ', this)\'><span class="glyphicon glyphicon-trash"></span></a>'; }
                        return '<td style="' + (!headers[i].valign ? "" : "vertical-align:" + headers[i].valign + ";") + (!colStyle ? "" : colStyle + ";") + (colWidth < 1 ? "" : "width:" + colWidth + "px;") + '"><div style="' + (headers[i].align ? "text-align:" + headers[i].align : "") + '">' + value + '</div></td>'
                    }).join("")
                    + (!showRemove ? "" : '<td style="width:40px;text-align:center"><a href="#" class="delList"><span class="glyphicon glyphicon-trash"></span></a></td>')
                    + "</tr>"); return obj
            }
            obj.find = function (key, cri) { return obj.elem.find("tbody tr").filter("[" + key + "=" + cri + "]") }
            obj.clear = function () { obj.elem.find("tbody tr").remove(); return obj }
            obj.remove = function (index) { this.elem.find("tbody tr").eq(index).remove(); $.each(headers, function (i, o) { if (o.runningNo) reorder(i) }); }
            obj.data = function () {
                var data = [];
                if (arguments.length != 1) {
                    var rows = obj.elem.find("tbody tr");
                    for (var i = 0, m = rows.length; i < m; i++) { var row = []; for (var j = 0, n = rows[i].children.length; j < n; j++) { row.push(extract(headers[j], rows[i].children[j])) } data.push(row) } return data;
                } for (var i = 0, m = (data = arguments[0]).length; i < m; i++) { obj.insert(data[i]); } return obj
            };
            obj.col = function (colIndex) { var rows = obj.elem.find("tbody tr"), data = []; for (var i = 0, m = rows.length; i < m; i++) { data.push(extract(headers[colIndex], rows[i].children[colIndex])) } return data }
            obj.size = function () { return obj.elem.find("tbody tr").length };
            obj.typeSum = function (typeIndex) { return stripToNumber(obj.find("type", typeIndex).find("td:eq(2) div").text()); }
            obj.deduct = function (index) { var sum = 0; this.elem.find("tbody tr").each(function (i, row) { var val = row.children[index].innerText.replace(/[,]/g, ""); sum += (isNaN(val) ? 0 : parseFloat((val * -1), 10)) }); return sum };
            obj.calcBalance = function (typeindex, index) { var sum = 0; var gettingIncome = 'ขาเข้า'; this.elem.find("tbody tr").each(function (i, row) { var type = row.children[typeindex].innerText.replace(/[]/g, ""); var multiplier = (gettingIncome == type.trim()) ? 1 : -1; var val = row.children[index].innerText.replace(/[,]/g, ""); sum += (isNaN(val) ? 0 : parseFloat((val * multiplier), 10)) }); return sum };
            obj.sum = function (index) { var sum = 0; this.elem.find("tbody tr").each(function (i, row) { var val = row.children[index].innerText.replace(/[,]/g, ""); sum += (isNaN(val) ? 0 : parseFloat(val, 10)) }); return sum }
            $(obj.el).on("click", ".delList", function () { $(this).parent().parent().remove(); $.each(headers, function (i, p) { if (p.runningNo) reorder(i) }); onDel(obj.data()) });
            obj.elem.removeClass("table").removeClass("table-hover").addClass("table").addClass("table-hover");
            obj.elem.find("thead th").each(function (i, o) { var elem = $(o); headers.push({ "field": elem.data("field"), "valign": $.trim(elem.data("valign")), "align": $.trim(elem.data("align")), "modifyButton": elem.data("modifyButton") === true, "removeButton": elem.data("removeButton") === true, "runningNo": elem.data("runningNo") === true, "numberFormat": elem.data("numberFormat") === true, "checkbox": elem.data("checkbox") === true, "radio": elem.data("radio") === true, "input": elem.data("input") === true }) });
            function replace(str, style, field, index, value) { var s = str; if (style) s = s.replace("\{style\}", style); return s.replace("\{field\}", $.trim(field)).replace("\{index\}", index).replace("\{value\}", value) }
            function extract(prop, col) { if (prop.checkbox) { var elem = col.children[0].children[0]; return !elem.checked ? "" : elem.value } else if (prop.radio) { var elem = col.children[0].children[0]; return !elem.checked ? "" : elem.value } else if (prop.input) { var elem = col.children[0].children[0]; return elem.value } return col.childNodes[0].nodeType === 3 ? col.textContent : col.children[0].innerHTML }
            if (obj.body.length < 1) { obj.elem.append("<tbody></tbody>"); obj.body = obj.elem.find("tbody") }
        }
        function Div(el) {
            var obj = this; obj.el = el; obj.elem = $(el);
            obj.hide = function () { obj.elem.addClass("hide"); return obj };
            obj.show = function (flag) { if (flag == null || flag) obj.elem.removeClass("hide"); else obj.hide(); return obj };
        }
        self.breadcrumb = new (function () {
            var that = this;
            that.breadcrumb = new Breadcrumb("#breadcrumb");
            function Breadcrumb(el) {
                var obj = this, list, index = 0;
                obj.el = el;
                obj.elem = $(el);
                obj.index = function () { if (arguments.length == 1) { list.removeClass("active").eq(index = arguments[0]).addClass("active"); return obj } return index }
                list = obj.elem.find("li").each(function (i, o) { $(o).data("index", i) });
                index = list.filter(".active").data("index");
            }
        });
        self.button = new (function () {
            var that = this;
            that.addTaxType = new Button("#addTaxType");
            that.setTaxType = new ButtonGroup("#setTaxType");
            that.addWithholdingTaxNo = new Button("#addWithholdingTaxNo");
            that.addFeeTaxNo = new Button("#addFeeTaxNo");
            that.addPenaltyTaxNo = new Button("#addPenaltyTaxNo");
            that.addRetentionTaxNo = new Button("#addRetentionTaxNo");
            that.addCompensationTaxNo = new Button("#addCompensationTaxNo");
            that.addPayType = new Button("#addPayType");
            that.setPayType = new ButtonGroup("#setPayType");
            that.payChqSubmit = new Button("#payChqSubmit");
            that.payCCSubmit = new Button("#payCCSubmit");
            that.submitPayment = new Button("#submitPayment");
            that.creditLimit = new Button("#creditLimit");
            that.submitPaymentEng = new Button("#submitPaymentEng");
            that.advancePaymentSubmit = new Button("#advancePaymentSubmit");
            that.otherPaymentSubmit = new Button("#otherPaymentSubmit");
            that.advancePaymentCancel = new Button("#advancePaymentCancel");
            that.creditLimitSubmit = new Button("#creditLimitSubmit");
            that.creditLimitCancel = new Button("#creditLimitCancel");
            function ButtonGroup(el) {
                var obj = this, index = 0;
                obj.el = el;
                obj.elem = $(el);
                obj.val = function () { return obj.elem.find(".selection").text() }
                obj.index = function () { if (arguments.length == 1) { obj.list[arguments[0]].click() } return index }
                obj.list = obj.elem.find(".dropdown-menu a").each(function (i) { $(this).click(function () { index = i }) });
                obj.hideIndex = function () { if (arguments.length == 1) { var selected = obj.elem.find(".dropdown-menu a")[arguments[0]]; $(selected).addClass("hide"); } return index }
            }
        });
        self.dialog = new (function () {
            var that = this;
            that.mainMessageDialog = new Message("#mainMessageDialog");
            that.advancePaymentMessage = new Message("#advancePaymentMessage");
            that.advancePaymentMessage2 = new Message("#advancePaymentMessage2");
            that.crditLimitMessage = new Message("#crditLimitMessage");
            that.advancePayment = new Modal("#advancePayment", true);
            that.otherProfitVat = new Modal("#otherProfitVat", true);
            that.creditLimitDialog = new Modal("#creditLimitDialog", true);
            function Modal(el, static) {
                var obj = this; obj.el = el; obj.elem = $(el);
                obj.hide = function () { this.elem.modal("hide") };
                obj.show = function () { this.elem.modal("show") };
                if (static) obj.elem.data("backdrop", "static")
            }
        });
        self.panel = new (function () {
            var that = this;
            that.summaryPanel = new Panel("#summaryPanel");
            that.navigationPanel = new Panel("#navigationPanel");
            that.fillDataInputPanel = new Panel("#fillDataInputPanel");
            that.informationPanel = new Panel("#informationPanel");
            that.linkPanel = new Panel("#linkPanel");
            that.receiptInfoPanel = new Panel("#receiptInfoPanel");
            that.tax = new FadePanel(self.button.setTaxType, "#withholdingTaxPanel", "#feeTaxPanel", "#penaltyTaxPanel", "#retentionTaxPanel", "#compensationTaxPanel");
            that.pay = new FadePanel(self.button.setPayType, "#payType1", "#payType2", "#payType3", "#payType4", "#payType5", "#payType6", "#payType7", "#payType9", "#payType8", "#payType10", "#payType11");
            function Panel(el) {
                var obj = this, dura = 500; obj.el = el; obj.elem = $(el);
                obj.hide = function (ms) { obj.elem.hide(ms || dura); return this }; obj.show = function (ms) { obj.elem.show(ms || dura); return this }
            }
            function FadePanel() {
                var obj = this, index = 0, dura = 200;
                obj.els = $.map(arguments, function (o, i) { return $.type(o) !== "string" ? null : o });
                obj.elems = $(obj.els.join(","));
                obj.hide = function () { obj.elems.addClass("hide"); return this }; obj.show = function () { obj.elems.removeClass("hide"); return this }
                obj.prev = function () { obj.index(index - 1); return this }; obj.next = function () { obj.index(index + 1); return this }
                obj.index = function (ind) { if (ind === index) return this; var target = obj.elems.length > ind && ind > -1 ? ind : (ind >= obj.elems.length ? 0 : obj.elems.length - 1); obj.elems.eq(index).fadeOut(dura, function () { obj.elems.eq(index = target).removeClass("hide").css("display", "") }); return this }
                obj.elems.eq(index).hide().removeClass("hide").show(dura);
                arguments[0].list.each(function (i, o) { $(o).click(function () { obj.index(i) }) })
            }
        });
        self.radio = new (function () {
            var that = this;
            that.withholdingTaxType = new Radio("[name=withholdingTaxType]");
            that.feeTaxType = new Radio("[name=feeTaxType]");
            that.penaltyTaxType = new Radio("[name=penaltyTaxType]");
            that.retentionTaxType = new Radio("[name=retentionTaxType]");
            that.specialOptions = new Radio("[name=specialOptions]");
        });
        self.input = new (function () {
            var that = this;
            that.payCashAmount = new NumberInput("#payCashAmount");
            that.payCashAmountBath = new NumberInput("#payCashAmountBath");
            that.payCashAmountRate = new Input("#payCashAmountRate");
            that.payChqBankCode = new DropDown("#payChqBankCode").init("../findBankNameList.json", "code");//.data([{ key: "0", value: "001" },{ key: "1", value: "002" },{ key: "2", value: "003" }]);
            that.payChqBankName = new DropDown("#payChqBankName").init("../findBankNameList.json", "name");//.data([{ key: "0", value: "กรุณาเลือกธนาคาร" },{ key: "1", value: "กรุงเทพ" },{ key: "2", value: "กสิกรไทย" }]);
            that.payChqNo = new Input("#payChqNo");
            that.payChqDate = new Input("#payChqDate");
            that.payChqBranch = new Input("#payChqBranch");
            that.payChqAmount = new NumberInput("#payChqAmount");
            that.payCCType = new DropDown("#payCCType").data([{ key: "1", value: "VISA" }, { key: "2", value: "MASTER" }]);
            that.payCCBankName = new DropDown("#payCCBankName").data([{ key: "1", value: "กรุงเทพ" }, { key: "2", value: "กสิกรไทย" }]);
            that.payCCNo = new Input("#payCCNo");
            that.payCCAmount = new NumberInput("#payCCAmount");
            that.withholdingTaxNo = new Input("#withholdingTaxNo");
            that.amountIn = new NumberInput("#amountIn");
            that.amountOut = new NumberInput("#amountOut");
            that.withholdingTaxAmount = new NumberInput("#withholdingTaxAmount");
            that.withholdingInvoiceNo = new DropDown("#withholdingInvoiceNo");
            that.currencyOut = new DropDown("#currencyOut");
            that.feeCurrencyTypeSelect = new DropDown("#feeCurrencyTypeSelect");
            that.cutomerNo = new DropDown("#cutomerNo");

            that.feeDepartmentCost = new DropDown("#feeDepartmentCost").data([{ key: "1", value: "0000 จต." }]);
            that.feeTaxAmount = new NumberInput("#feeTaxAmount");
            that.feeTaxAmountIn = new NumberInput("#feeTaxAmountIn");
            that.feeBuyTaxAmountVat = new NumberInput("#feeBuyTaxAmountVat");
            that.feeTaxAmountOut = new NumberInput("#feeTaxAmountOut");
            that.feeWTAmount = new NumberInput("#feeWTAmount");
            that.feeBuyTaxAmount = new NumberInput("#feeBuyTaxAmount");
            that.penaltyInvoiceNo = new DropDown("#penaltyInvoiceNo");
            that.penaltyTaxAmount = new NumberInput("#penaltyTaxAmount");
            that.retentionInvoiceNo = new DropDown("#retentionInvoiceNo");
            that.retentionTaxAmount = new NumberInput("#retentionTaxAmount");
            that.compensationDepartmentCost = new DropDown("#compensationDepartmentCost").data([{ key: "1", value: "0000 จต." }]);
            that.compensationTaxAmount = new NumberInput("#compensationTaxAmount");
            that.discount = new NumberInput("#discount");
            that.discountBath = new NumberInput("#discountBath");
            that.charge = new NumberInput("#charge");
            that.changeRate = new NumberInput("#changeRate");
            that.chargeBath = new NumberInput("#chargeBath");

            that.preItemsDiscount = new NumberInput("#preItemsDiscount");
            that.preItemsDiscountBath = new NumberInput("#preItemsDiscountBath");
            that.moneyToPay = new NumberInput("#moneyToPay");
            that.moneyToPayBath = new NumberInput("#moneyToPayBath");
            that.itemsDiscount = new NumberInput("#itemsDiscount");
            that.itemsDiscountBath = new NumberInput("#itemsDiscountBath");

            that.vat = new NumberInput("#vat");
            that.vatBath = new NumberInput("#vatBath");
            that.totalCharge = new NumberInput("#totalCharge");
            that.totalChargeBath = new NumberInput("#totalChargeBath");
            that.deduct = new NumberInput("#deduct");
            that.deductBath = new NumberInput("#deductBath");
            that.fee = new NumberInput("#fee");
            that.feeBath = new NumberInput("#feeBath");
            that.penalty = new NumberInput("#penalty");
            that.penaltyBath = new NumberInput("#penaltyBath");
            that.retention = new NumberInput("#retention");
            that.retentionBath = new NumberInput("#retentionBath");
            that.compensation = new NumberInput("#compensation");
            that.compensationBath = new NumberInput("#compensationBath");
            that.balanceDue = new NumberInput("#balanceDue");
            that.balanceDueBath = new NumberInput("#balanceDueBath");
            that.change = new NumberInput("#change");
            that.changeBath = new NumberInput("#changeBath");
            that.foreignTotalCharge = new NumberInput("#foreignTotalCharge");
            that.foreignTotalChargeBath = new NumberInput("#foreignTotalChargeBath");
            that.foreignExchangeRate = new Input("#foreignExchangeRate");
            that.foreignExchangeRateBath = new Input("#foreignExchangeRateBath");
            that.payBankTxnfDropDownBankCode = new DropDown("#payBankTxnfInputBankCode").init("../findBankNameList.json", "code");
            that.payBankTxnfDropDownBankName = new DropDown("#payBankTxnfInputBankName").init("../findBankNameList.json", "name");//.data([{ key: "0", value: "กรุณาเลือกธนาคาร" },{ key: "006", value: "กรุงไทย" }]);
            that.val = function () { if (arguments.length == 1) { $.each(arguments[0], function (k, v) { $("#" + k).val(v) }) } };
        });
        self.table = new (function () {
            var that = this;
            that.deductionList = new Table("#deductionList");
            that.payTypeList = new Table("#payTypeList");
            that.withholdingTaxList = new Table("#withholdingTaxList");
            that.feeTaxList = new Table("#feeTaxList");
            that.penaltyTaxList = new Table("#penaltyTaxList");
            that.retentionTaxList = new Table("#retentionTaxList");
            that.compensationTaxList = new Table("#compensationTaxList");
            that.payChqList = new Table("#payChqList");
            that.payCCList = new Table("#payCCList");
            that.customerList = new BootstrapTable("#customerList");
            that.customerList2 = new BootstrapTable("#customerList2");
            that.subScriptionListTb = new Table("#subScriptionListTb");
            //that.subScriptionListTb = new BootstrapTable("#subScriptionListTb");
            that.receiptList = new BootstrapTable("#receiptList");
            function BootstrapTable(el) {
                var obj = this, checkEvt = function (e) { console.log(e) }, uncheckEvt = checkEvt;
                obj.el = el;
                obj.elem = $(el);
                obj.clear = function () { obj.elem.bootstrapTable("removeAll"); return obj }
                obj.showLoad = function () { this.elem.bootstrapTable("showLoading"); return this };
                obj.hideLoad = function () { this.elem.bootstrapTable("hideLoading"); return this };
                obj.data = function () { if (arguments.length == 1) { this.elem.bootstrapTable("load", arguments[0]); } return this.elem.bootstrapTable("getData") };
                obj.selected = function () { return this.elem.bootstrapTable("getSelections") };
                obj.sum = function (isAll, colName) { var sum = 0; $.each(this.elem.bootstrapTable(isAll ? "getData" : "getSelections"), function (i, o) { sum += (o[colName] || 0) }); return sum };
                obj.sumInput = function (index) { var sum = 0; obj.elem.find("tbody tr").each(function (i, o) { var val = o.children[index].children[0].value.replace(/[,]/g, ""); sum += (!$.isNumeric(val) ? 0 : parseFloat(val, 10)) }); return sum }
                obj.expand = function () { obj.elem.find(".detail-icon i.icon-plus").click(); return this };
                obj.collapse = function () { obj.elem.find(".detail-icon i.icon-minus").click(); return this };
                obj.checkboxEvent = function (func) { this.elem.on("check.bs.table uncheck.bs.table check-all.bs.table uncheck-all.bs.table", func); return this };
                obj.checkEvent = function (func) { checkEvt = func; return this };
                obj.uncheckEvent = function (func) { uncheckEvt = func; return this };
                obj.checkboxEvent(function (e) { if (e.type === "check") checkEvt(e); if (e.type === "uncheck") uncheckEvt(e) });
            }
        });
        self.div = new (function () {
            var that = this;
            that.feeSummaryDiv = new Div("#feeSummaryDiv");
            that.penaltySummaryDiv = new Div("#penaltySummaryDiv");
            that.retentionSummaryDiv = new Div("#retentionSummaryDiv");
            that.compensationSummaryDiv = new Div("#compensationSummaryDiv");
            that.foreignTotalChargeDiv = new Div("#foreignTotalChargeDiv");
            that.foreignExchangeDiv = new Div("#foreignExchangeDiv");
        });

        (function () { $(window["setup"]) })();
        self.payMoneyOrderButtonSubmit = new Button("#payMoneyOrderButtonSubmit");
        self.payMoneyOrderInputDate = new Input("#payMoneyOrderInputDate");
        self.payMoneyOrderInputNo = new Input("#payMoneyOrderInputNo");
        self.payMoneyOrderInputPostCode = new Input("#payMoneyOrderInputPostCode");
        self.payMoneyOrderInputAmount = new NumberInput("#payMoneyOrderInputAmount");
        self.payMoneyOrderTableList = new Table("#payMoneyOrderTableList");
        self.payBillExchgButtonSubmit = new Button("#payBillExchgButtonSubmit");
        self.payBillExchgInputDate = new Input("#payBillExchgInputDate");
        self.payBillExchgInputNo = new Input("#payBillExchgInputNo");
        self.payBillExchgInputPostCode = new Input("#payBillExchgInputPostCode");
        self.payBillExchgInputAmount = new NumberInput("#payBillExchgInputAmount");
        self.payBillExchgTableList = new Table("#payBillExchgTableList");
        self.payCouponButtonSubmit = new Button("#payCouponButtonSubmit");
        self.payCouponInputNo = new Input("#payCouponInputNo");
        self.payCouponInputAmt = new NumberInput("#payCouponInputAmt");
        self.payCouponTableList = new Table("#payCouponTableList");
        self.payBankTxnfInputNo = new Input("#payBankTxnfInputNo");
        self.payBankTxnfInputAmt = new NumberInput("#payBankTxnfInputAmt");
        self.payBankTxnfInputDate = new Input("#payBankTxnfInputDate");
        self.payBankTxnfDropDownBank = new DropDown("#payBankTxnfDropDownBank").data([{ key: "01", value: "121-2-11111-2" }]);
        self.payBankTxnfDropDownBankBrnh = new DropDown("#payBankTxnfDropDownBankBrnh").data([{ key: "0123", value: "ปากเกร็ด" }]);
        self.payBankTxnfDropDownBankAcct = new DropDown("#payBankTxnfDropDownBankAcct").data([{ key: "1", value: "123-6-02069-3" }]);
        self.payBankTxnfDropDownBankAcCd = new DropDown("#payBankTxnfDropDownBankAcCd").data([{ key: "1", value: "11021044" }]);
        self.payBankTxnfButtonSubmit = new Button("#payBankTxnfButtonSubmit");
        self.payBankTxnfTableList = new Table("#payBankTxnfTableList");
        self.payOffsetDocumentNo = new Input("#payOffsetDocumentNo");
        self.payOffsetAccountCode = new Input("#payOffsetAccountCode");
        self.payOffsetAmount = new NumberInput("#payOffsetAmount");
        self.payOffsetAccountName = new Input("#payOffsetAccountName");
        self.payOffsetButtonSubmit = new Button("#payOffsetButtonSubmit");
        self.payOffsetTableList = new Table("#payOffsetTableList");
        self.payOtherDropDownChannel = new DropDown("#payOtherDropDownChannel");
        self.payOtherInputNo = new Input("#payOtherInputNo");
        self.payOtherInputDate = new Input("#payOtherInputDate");
        self.payOtherInputAmt = new NumberInput("#payOtherInputAmt");
        self.payOtherButtonSubmit = new Button("#payOtherButtonSubmit");
        self.payOtherTableList = new Table("#payOtherTableList");
        self.inputReceived = new NumberInput("#inputReceived");
        self.inputReceivedBath = new NumberInput("#inputReceivedBath");
        self.inputAdvanced = new NumberInput("#inputAdvanced");
        self.inputAdvancedBath = new NumberInput("#inputAdvancedBath");

        self.payBankTxnfInputBankCodeGf = new Input("#payBankTxnfInputBankCodeGf");
        self.payBankTxnfInputNoGf = new Input("#payBankTxnfInputNoGf");
        self.payBankTxnfInputAmtGf = new NumberInput("#payBankTxnfInputAmtGf");
        self.payBankTxnfInputDateGf = new Input("#payBankTxnfInputDateGf");
        self.payBankTxnfDropDownBankNameGf = new DropDown("#payBankTxnfInputBankNameGf").data([{ key: "006", value: "กรุงไทย" }]);
        self.payBankTxnfDropDownBankBrnhGf = new DropDown("#payBankTxnfDropDownBankBrnhGf").data([{ key: "0123", value: "ปากเกร็ด" }]);
        self.payBankTxnfDropDownBankAcctGf = new DropDown("#payBankTxnfDropDownBankAcctGf").data([{ key: "1", value: "123-6-02069-3" }]);
        self.payBankTxnfDropDownBankAcCdGf = new DropDown("#payBankTxnfDropDownBankAcCdGf").data([{ key: "1", value: "11021044" }]);
        self.payBankTxnfButtonSubmitGf = new Button("#payBankTxnfButtonSubmitGf");
        self.payBankTxnfTableListGf = new Table("#payBankTxnfTableListGf");

        self.currencyTypeSelect = new DropDown("#currencyTypeSelect").initV3("../exchangeRateList.json");//.data([{ key: "0", value: "กรุณาเลือกสกุลเงิน" },{ key: "001", value: "USD" }]);
        self.exchangeRateInput = new Input("#exchangeRateInput");
        self.payDateInputDate = new Input("#payDateInputDate");
        self.foreignAmountInput = new NumberInput("#foreignAmountInput");
        self.thAmountInput = new NumberInput("#thAmountInput");
        self.payForeignButtonSubmit = new Button("#payForeignButtonSubmit");
        self.payForeignTableList = new Table("#payForeignTableList");

        self.allowedCurrenyCode = true;
        self.containsForeignCurrencyCode = false;

        $('#payChqBankCode').click(function () {
            var key = view.input.payChqBankCode.key();
            $('#payChqBankName option:selected').prop('selected', false);
            $('#payChqBankName option[data-key=' + key + ']').prop('selected', true);
        });
        $('#payChqBankName').click(function () {
            var key = view.input.payChqBankName.key();
            $('#payChqBankCode option:selected').prop('selected', false);
            $('#payChqBankCode option[data-key=' + key + ']').prop('selected', true);
        });
        $('#payBankTxnfInputBankCode').click(function () {
            var key = view.input.payBankTxnfDropDownBankCode.key();
            $('#payBankTxnfInputBankName option:selected').prop('selected', false);
            $('#payBankTxnfInputBankName option[data-key=' + key + ']').prop('selected', true);
        });
        $('#payBankTxnfInputBankName').click(function () {
            var key = view.input.payBankTxnfDropDownBankName.key();
            $('#payBankTxnfInputBankCode option:selected').prop('selected', false);
            $('#payBankTxnfInputBankCode option[data-key=' + key + ']').prop('selected', true);
        });

        return this;
    })(jQuery);

    $("#testmenu li a").click(function () { $(this).parents(".btn-group").find('.selection').text($(this).text()); $(this).parents(".btn-group").find('.selection').val($(this).text()); });
    $("#testmenu2 li a").click(function () { $(this).parents(".btn-group").find('.selection').text($(this).text()); $(this).parents(".btn-group").find('.selection').val($(this).text()); });

    var bounceChequeBean;
    var vatRDGET;
    var rateChangeRD;
    var dataRateList;

    $(document).ready(function () {
    	$(document).on("click", "#cancelPaymentBtn", function() {
    	    view.session("bounceCheque", []);
    	    window.location.href="pay-bounceCheque.jsp?new"
    	});
  
        var bounceCheque = view.session("bounceCheque");
        bounceChequeBean = bounceCheque;
        vatRDGET = bounceCheque[0].vatRD;
        var prepaid = bounceCheque[0].prepaid;
        var invoiceNoList = [];
        var cutomerNoOpt = [];
        var arAccountCodeOpt;
        var dateRateRD;

        $("#69tvi").prop('checked', 'checked');

        var sumVat = 0, sumVat2 = 0, sumBalance = 0, sumBalance2 = 0, sumTotalCharge = 0, sumTotalCharge2 = 0;
        for (var rowid = 0; rowid < bounceCheque.length; rowid++) {
            var arAccountCodeOpt = (bounceCheque[rowid]["arAccountCode"]);
            cutomerNoOpt.push({ key: arAccountCodeOpt, value: arAccountCodeOpt });
            for (var i = 0; i < bounceCheque[rowid].invoiceList.length; i++) {
                var amountChange2 = bounceCheque[rowid].invoiceList[i]["amountARout"];
                
                var amountCaloutS = stripToNumberWithFormat(bounceCheque[rowid].invoiceList[i]["amountPay"]);
                var rateChange = rateChangeRDGet = bounceCheque[0].rDGet;
                                 
                // var receivedValue = stripToNumberWithFormat($("#received_" + rowid).val());
                var amountBath = (rateChange*amountCaloutS);
                
                sumTotalCharge += (amountBath);
                sumTotalCharge2 += (amountCaloutS);
                
                var amountARin = calculateVatFromIncluded((amountBath));
                var amountARout = calculateVatFromIncluded(amountChange2);
                
                var amountARoutCal = calculateVatFromIncluded((amountCaloutS));
                
                sumBalance += amountARin;
                sumBalance2 += amountARoutCal;
                
                sumVat += stripToNumberWithFormat((amountBath) - amountARin);
                sumVat2 += stripToNumberWithFormat((amountCaloutS) - amountARoutCal);

                var docHeadOpt = (bounceCheque[rowid].invoiceList[i]["docHead"]);

                invoiceNoList.push({ key: docHeadOpt, value: docHeadOpt });
            }
        }
        var keyCurr = bounceCheque[0].invoiceList[0]["currency"];
        // if(bounceCheque[0].invoiceList[0]["currency"]=="USD"){keyCurr = "1";}
        // else if(bounceCheque[0].invoiceList[0]["currency"]=="THB") {keyCurr = "3";}else{keyCurr = "2";}
            $.ajax({url: "../exchangeRateList.json", type: "GET", data: {}, async: false,
                    success: function (res) {
                        var data = res.data; for(var i=0; i<data.length; i++){
                            dataRateList = data;
                            if(data[i].message==bounceCheque[0].invoiceList[0]["currency"]){
                                rateChangeRD = data[i].rateUnit;
                                dateRateRD = data[i].updateDttm; dateRateRD = dateRateRD.substring(8,10)+"/"+dateRateRD.substring(5,7)+"/"+dateRateRD.substring(0,4);}
                    }}, error: function () { console.log("error"); }
                });

        if(bounceCheque[0].invoiceList[0]["currency"] != null && (bounceCheque[0].invoiceList[0]["currency"].indexOf("TH") == -1)){
			view.dialog.mainMessageDialog.warn(["กำลังทำรายการ Invoice สกุลเงินต่างประเทศด้วยอัตราแลกเปลี่ยน "+bounceCheque[0].invoiceList[0]["currency"]+" ("+rateChangeRD+") ตามอัตราแลกเปลี่ยนวันที่ "+dateRateRD]).show();
		}
        var x = $("#currencyTypeSelect").val();
        $("#currencyTypeSelect").val(keyCurr);
        // view.currencyTypeSelect.val(keyCurr);
        view.exchangeRateInput.val(rateChangeRD);
        view.input.payCashAmountRate.val(rateChangeRD);
        view.input.foreignExchangeRate.val(rateChangeRD);
        view.input.foreignExchangeRateBath.val(rateChangeRD);
        view.input.payCashAmount.val(sumTotalCharge2+prepaid);
        view.input.payCashAmountBath.val((sumTotalCharge2+prepaid)*rateChangeRD);
        view.input.preItemsDiscount.val((sumBalance2));
        view.input.preItemsDiscountBath.val((sumBalance));
        view.input.moneyToPay.val(sumTotalCharge2);
        view.input.totalCharge.val(sumTotalCharge2);
        view.input.moneyToPayBath.val(sumTotalCharge);
        view.input.totalChargeBath.val(sumTotalCharge);
        view.input.payChqAmount.val(sumTotalCharge2+prepaid);
        view.input.payCCAmount.val(sumTotalCharge2+prepaid);
        view.input.balanceDue.val(sumTotalCharge2);
        view.input.balanceDueBath.val(sumTotalCharge);

        view.payBankTxnfInputAmt.val(sumTotalCharge2+prepaid);
        view.foreignAmountInput.val(sumTotalCharge2+prepaid);
        // view.thAmountInput.val(sumTotalCharge);
        view.input.charge.val((sumBalance2));
        view.input.chargeBath.val((sumBalance));
        view.input.vat.val(vatGet((sumBalance2), sumTotalCharge2));
        view.input.vatBath.val(vatGet((sumBalance), sumTotalCharge));

        addExchangRateBefore();

        view.input.withholdingTaxAmount.val(deduct);
        view.input.amountIn.val();
        view.input.amountOut.val();

        view.input.withholdingInvoiceNo.data(invoiceNoList);
        view.input.penaltyInvoiceNo.data(invoiceNoList);
        view.input.retentionInvoiceNo.data(invoiceNoList);
        view.input.cutomerNo.data(cutomerNoOpt);

        view.table.payTypeList.onDelete(function (data) {
            if (data.length < 1) {
                view.button.submitPayment.disable();
                view.button.submitPaymentEng.disable();
            }
            calculate();
        });

        view.button.submitPayment.disable();
        view.button.submitPaymentEng.disable();

    });

    // function calForSumCheque() {
    //     var amountChange = data[rowid]["amountARin"];
    //             var amountChange2 = data[rowid]["amountARout"];
    // }

    function calculateVatFromIncluded(ammount) {
        var rtVatBalance = 0;
        var vatSum = (100+vatRDGET);
        rtVatBalance = (ammount * 100) / vatSum;
        return rtVatBalance;
    }

    function vatGet(afterAmmount, amount) {
        var rtVatBalance = 0;
        rtVatBalance = stripToNumberWithFormat(amount) - stripToNumberWithFormat(afterAmmount);
        return rtVatBalance;
    }

    function stripToNumberWithFormat(str) {
        var strFormat = str + "";
        strFormat = strFormat.replace(/[^0-9\.]/g, "");
        return parseFloat(strFormat, 10);
    }

    function addTaxTypeClickEvent() {
        var index = view.button.setTaxType.index(), value = 0;
        if (index === 0) { value = view.table.withholdingTaxList.deduct(5); window.wthList = view.table.withholdingTaxList.data() }
        else if (index === 1) { value = view.table.feeTaxList.calcBalance(2, 5); window.feeList = view.table.feeTaxList.data() }
        else if (index === 2) { value = view.table.penaltyTaxList.calcBalance(2, 3); window.pntyList = view.table.penaltyTaxList.data() }
        else if (index === 3) { value = view.table.retentionTaxList.calcBalance(2, 3); payAmt = "-" }
        else if (index === 4) { value = view.table.compensationTaxList.deduct(2); payAmt = view.foreignAmountInput.val() }
        view.table.deductionList.find("type", index).remove();
        view.table.deductionList.insert(["-", view.button.setTaxType.val(), value], true, { "type": index });
        updateDeduction();
        calculate();
    }

    function addPayTypeClickEvent() {
        console.log(view.input.payCashAmount.val());
        console.log(view.input.payCashAmountBath.val());

        var index = view.button.setPayType.index(), payAmt = view.input.payCashAmount.val(), payAmtBath = view.input.payCashAmountBath.val(), rateChange = view.input.payCashAmountRate.val();
        if (index == 1) { window["payChqListData"] = view.table.payChqList.data(); payAmt = "-" }
        else if (index == 2) { window["payCCListData"] = view.table.payCCList.data(); payAmt = view.table.payCCList.sum(4); payAmtBath = "-"; }
        else if (index == 3) { window["payBankTxnfTableListData"] = view.payBankTxnfTableList.data(); payAmt = view.payBankTxnfTableList.sum(6); payAmtBath = "-";}
        else if (index == 4) { window["payForeignTableListData"] = view.payForeignTableList.data(); payAmt = view.payForeignTableList.sum(4); payAmtBath = view.payForeignTableList.sum(5); }
        view.table.payTypeList.find("method", index).remove();
        view.table.payTypeList.insert(["-", view.button.setPayType.val(), payAmt, payAmtBath], true, { method: index });
        view.inputReceived.val(view.table.payTypeList.sum(2));
        view.inputReceivedBath.val(view.table.payTypeList.sum(3));
        view.input.foreignExchangeRateBath.val(rateChange);
        if (view.allowedCurrenyCode) {
            view.button.submitPayment.enable();
            view.button.submitPaymentEng.enable();
        }
        updateDeduction();
        calculate();
    }

    function updateDeduction() {
        view.input.deduct.val(view.table.deductionList.typeSum(0));
        view.input.fee.val(view.table.deductionList.typeSum(1));
        view.input.penalty.val(view.table.deductionList.typeSum(2));
        view.input.retention.val(view.table.deductionList.typeSum(3));
        view.input.compensation.val(view.table.deductionList.typeSum(4));
    }

    function calculate() {
        var balanceDue = 0;
        var balanceDueBath = 0;
        if (view.input.totalCharge.val() > 0) {
            balanceDueBath = view.input.moneyToPayBath.val();
            balanceDue = view.input.moneyToPay.val();
        } else {
            balanceDueBath = (view.input.totalChargeBath.val() * 100) / 100;
            balanceDue = (view.input.totalCharge.val() * 100) / 100;
        }
        var received = view.table.payTypeList.sum(2);
        var received3 = view.table.payTypeList.sum(3);
        var foreignExchangeRate = view.table.payTypeList.sum(4);
        var cash = stripToNumber(view.table.payTypeList.find("method", 0).find("td:eq(2) div").text());
        var cashBath = stripToNumber(view.table.payTypeList.find("method", 0).find("td:eq(3) div").text());
        var nonCash = ((received * 100) - (cash * 100)) / 100;
        var advanced = view.inputAdvanced.val();
        var change = 0;
        var changeBath = 0;
        var sumCurrentDeduct = view.table.deductionList.sum(2);
        var afterSaleDiscount = view.input.discount.val();
        balanceDue += sumCurrentDeduct;
        //balanceDue -= afterSaleDiscount;//comment by NSD 04-04-2017
        console.log("Initial: change-> " + change + ", changeBath-> " + changeBath + ", cash-> " + cash + ", nonCash->" + nonCash + ", adv->" + advanced);
        if (advanced > 0) {
            change = (((received * 100) - (balanceDue * 100)) - (advanced * 100)) / 100;
            if (change > cash) change = cash;
            if (change < 0) {
                advanced = ((advanced * 100) + (change * 100)) / 100; // this will decrease advanced value
                change = 0;
            }
            if (received <= 0) {
                advanced = 0;
                change = 0;
            } else if (nonCash > 0) {
                var diff = ((nonCash * 100) - (balanceDue * 100)) / 100;
                if (diff > 0) {
                    var newadvanced = diff;
                    if (newadvanced > advanced) advanced = newadvanced;
                    console.log("step A1: change-> " + change + ", cash-> " + cash + ", adv->" + advanced);
                } else {
                    advanced = 0;
                    change = cash > 0 ? ((cash * 100) + (diff * 100)) / 100 : 0;
                    if (change < 0) change = 0;
                    console.log("step A2: change-> " + change + ", cash-> " + cash + ", adv->" + advanced);
                }
            }
            console.log("step A: change-> " + change + ", cash-> " + cash + ", adv->" + advanced);
        } else if (nonCash > 0) {
            var diff = ((nonCash * 100) - (balanceDue * 100)) / 100;
            if (diff > 0) {
                advanced = diff;
                change = cash;
                console.log("step B: change-> " + change + ", cash-> " + cash + ", adv->" + advanced);
            } else {
                advanced = 0;
                change = cash > 0 ? ((cash * 100) + (diff * 100)) / 100 : 0;
                if (change < 0) change = 0;
                console.log("step C: change-> " + change + ", cash-> " + cash + ", adv->" + advanced);
            }
        } else {
            var diff = ((cash * 100) - (balanceDue * 100)) / 100;
            var diffBath = ((cashBath * 100) - (balanceDueBath * 100)) / 100;
            advanced = 0;
            change = (diff < 0) ? 0 : diff;
            changeBath = (diffBath < 0) ? 0 : diffBath;
            console.log("step D: change-> " + change + ", cash-> " + cash + ", adv->" + advanced);
        }
        view.input.balanceDue.val(balanceDue);
        view.inputReceived.val(received);
        view.inputReceivedBath.val(received3);
        view.input.change.val(change);
        view.input.changeBath.val(changeBath);
        view.inputAdvanced.disable(advanced > 0).val(advanced);
        view.inputAdvancedBath.disable(advanced > 0).val(advanced*view.input.payCashAmountRate.val());
        view.radio.specialOptions.disable(2, change == 0 && advanced == 0);
        view.radio.specialOptions.disable(1, change == 0 && advanced == 0);//by NSD 02-04-2017
        view.radio.specialOptions.disable(0, change == 0 && advanced == 0);

        var debtAmt = Math.max(0, balanceDue - view.inputReceived.val());
        var debtAmtBath = Math.max(0, balanceDueBath - view.inputReceivedBath.val());
        view.input.payCashAmount.val(debtAmt);
        view.input.payCashAmountBath.val(debtAmtBath);
        view.input.payChqAmount.val(debtAmtBath);
        view.input.payCCAmount.val(debtAmt);
        view.payMoneyOrderInputAmount.val(debtAmtBath);
        view.payBillExchgInputAmount.val(debtAmtBath);
        view.payCouponInputAmt.val(debtAmtBath);
        view.payBankTxnfInputAmt.val(debtAmt);
        view.foreignAmountInput.val(debtAmt);
        view.thAmountInput.val(debtAmtBath);
        view.payBankTxnfInputAmtGf.val(debtAmtBath);
        view.payOffsetAmount.val(debtAmtBath);
        view.payOtherInputAmt.val(debtAmtBath);
        view.input.foreignExchangeRate.val(1.00);
        view.input.foreignExchangeRateBath.val(view.input.payCashAmountRate.val());
        view.input.payCashAmountRate.val(rateChangeRD);

        if (change == 0 && advanced == 0) {
            view.radio.specialOptions.unchecked();
        }
    }

    function addWithholdingTaxNoClickEvent() {
        if (validateAddWithholdingTaxNo()) {
            var taxType = view.radio.withholdingTaxType.label(); // , view.input.withholdingTaxAmount.val()
            view.table.withholdingTaxList.insert(["-", view.input.withholdingInvoiceNo.val(), view.input.withholdingTaxNo.val(), taxType,view.input.amountOut.val(), view.input.amountIn.val() ], true, { "type": view.radio.withholdingTaxType.type() });
        }
    }

    function addFeeTaxNoClickEvent() {
        view.table.feeTaxList.insert(["-", view.input.feeDepartmentCost.val(), view.input.feeBuyTaxAmountVat.val(), view.input.feeWTAmount.val(), view.input.feeBuyTaxAmount.val(), view.input.feeTaxAmountOut.val(), view.input.feeTaxAmountIn.val()], true);
        $('#feeTaxList td:nth-child(4)').hide();
    }

    function validateAddWithholdingTaxNo() {
        var isValid = true;
        return isValid;
    }

    var checkSpecial = "";

    function submitPayment(receiptLang) {
        console.log(receiptLang);
        var bounceChequeDTO = {};
        var payBounceChequeDTO = {};
        var payBounceChequeDTOList = [];
        var detailARCustomerDTOList = [];
        var detailARCustomerDTO = {};

        for (var j = 0; j < bounceChequeBean.length; j++) {
            // for (var k = 0; k < bounceChequeBean[j].detailARCustomer.length; k++) {
            detailARCustomerDTO = {};

            detailARCustomerDTO = {
                "arAccountCode": bounceChequeBean[j].arAccountCode
                , "arName": bounceChequeBean[j].arName
                , "taxId": bounceChequeBean[j].taxId
                , "glAccount": bounceChequeBean[j].glAccount
                , "arGroup": bounceChequeBean[j].arGroup
                , "regionKey1": bounceChequeBean[j].regionKey1
                , "branchAR": bounceChequeBean[j].branchAR
                , "address": bounceChequeBean[j].address
                , "remark": bounceChequeBean[j].remark
            };
            detailARCustomerDTOList.push(detailARCustomerDTO);
            // }
            var paymentCase = "";
    var methodIndex = 0, cashRow = view.table.payTypeList.find("method", 0);
    var chequeRow = view.table.payTypeList.find("method", 1);
    var creditRow = view.table.payTypeList.find("method", 2);
    var moneyTransferRow = view.table.payTypeList.find("method", 3);
    var moneyForeignRow = view.table.payTypeList.find("method", 4);

    var allPaycase = [ ["CASH", "CC", "เงินสด"], ["CHEQUE", "CH", "เช็ค"], ["CREDITCARD", "CR", "บัตรเครดิต"], ["MONEYORDER", "MO", "ธนาณัติ"], ["BILLEXCHANGE", "BX", "ตั๋วแลกเงิน"], ["COUPON", "CP", "คูปอง"], ["BANKTRANSFER", "TR", "เงินโอนในประเทศ"], ["OFFSET", "OF", "ofset"], ["FOREIGNTRANSFER", "TF", "เงินโอนต่างประเทศ"], ["OTHER", "OT", "อื่นๆ"], ["BANKTRANSFER", "GF", "เงินโอน (GFMIS)"]], payAdvance = {};

    var change = 0 , amount = 0, countPay = 0, payIndex = [], amounts = [], receivedMoney = view.inputReceived.val(), balanceDue = view.input.balanceDue.val(), advance = view.inputAdvanced.val();
    change = Number(receivedMoney) - Number(balanceDue);

    for(var i=0;i<11;i++) { var payCashFlg = false, paycase = []; paycase[i] = view.table.payTypeList.find("method", i); amounts[i] = paycase[i].find("td:eq(2) div").text().replace(/[,]/g, ""); if (paycase[i].length > 0) { payIndex[countPay] = i; countPay++; } }
    if (countPay > 0) { if (countPay > 1) { if(payIndex.indexOf(0)!='-1') { change = Number(receivedMoney) - Number(balanceDue); amounts[payIndex.indexOf(0)] = Number(amounts[payIndex.indexOf(0)]) - change; payAdvance = allPaycase[payIndex.indexOf(0)]; /*if (amounts[payIndex.indexOf(0)]<0) { Math.max(amounts); }*/ } else { amounts[payIndex[0]] = Number(amounts[payIndex[0]])- change; payAdvance = allPaycase[payIndex[0]];} } else { if(payIndex.indexOf(0)!='-1') { change = Number(receivedMoney) - Number(balanceDue); amounts[payIndex.indexOf(0)] = Number(amounts[payIndex.indexOf(0)]) - change; payAdvance = allPaycase[payIndex.indexOf(0)]; } else { amounts[payIndex[0]] = Number(amounts[payIndex[0]])- change; payAdvance = allPaycase[payIndex[0]]; } } }

    var method = {};
    var methods = [];

        $.map((window["payChqListData"] || []), function (o, i) { // For: Cheque
        console.log("1Test1");
            if (chequeRow.length > 0) {
                method = {
                    "type": "CHEQUE"
                    , "code": "CH"
                    , "name": "เช็ค"
                    , "chequeNo": $.trim(o[4])
                    , "amount": o[6].replace(/[,]/g, "")
                    , "payChqBankCode": $.trim(o[1])
                    , "payChqBankName": $.trim(o[2])
                    , "payChqBranch": $.trim(o[3])
                    , "payChqDate": $.trim(o[5])
                };

                paymentCase = paymentCase.concat("เช็ค " + $.trim(o[2]) + " เลขที่: " + $.trim(o[4]) + " + ");
                methods.push(method);
            }
        });
        $.map((window["payCCListData"] || []), function (o, i) { // For: Credit Card
            console.log("2Test2");
            if (creditRow.length > 0) {
                method = {
                    "type": "CREDITCARD"
                    , "code": "CR"
                    , "name": "บัตรเครดิต"
                    , "cardType": $.trim(o[1])
                    , "cardNo": $.trim(o[2])
                    , "bankName": $.trim(o[3])
                    , "amount": o[4].replace(/[,]/g, "")
                };
                
                paymentCase = paymentCase.concat("บัตรเครดิต " + $.trim(o[1]) + " เลขที่: " + $.trim(o[2]) + " + ");
                methods.push(method);
            }
        });
        $.map((window["payBankTxnfTableListData"] || []), function (o, i) { // For: Money Transfer
            console.log("3Test3");
            if (moneyTransferRow.length > 0) {
                method = {
                    "type": "BANKTRANSFER"
                    , "code": "TR"
                    , "name": "เงินโอนในประเทศ"
                    , "bankAcct": o[4]
                    , "transferDt": o[5]
                    , "isBackDt": "${epContext.roleName}" == "GFMISAGENT"
                    , "amount": o[6].replace(/[,]/g, "")
                };
                
                paymentCase = paymentCase.concat("เงินโอนในประเทศ + ");
                methods.push(method);
            }
        });
        $.map((window["payForeignTableListData"] || []), function (o, i) {
            console.log("4Test4");
            if (moneyForeignRow.length > 0) {
                method = {
                    "type": "BANKTRANSFER"
                    , "code": "TF"
                    , "name": "เงินโอนต่างประเทศ"
                    , "transferDt": o[3]
                    , "isBackDt": "${epContext.roleName}" == "GFMISAGENT"
                    , "amount": o[5].replace(/[,]/g, "")
                };
                
                paymentCase = paymentCase.concat("เงินโอนต่างประเทศ + ");
                methods.push(method);
            }
        });

        if (cashRow.length == 1) { // For: Cash
            method = {
                    "type": "CASH"
                    , "code": "CC"
                    , "name": "เงินสด"
                    , "amount": amounts[0]
                };
                
            paymentCase = paymentCase.concat("เงินสด + ");
            methods.push(method);
        }
        var deduction = Math.abs(view.input.deduct.val());
        var watPaymentCaseConclude = "";
        if(deduction > 0) {
            var watPaymentCaseArr = watPaymentCase.substring(0, watPaymentCase.length-2).split(" + ");
            if(watPaymentCaseArr.length > 0) {
                watPaymentCaseConclude = " + ";
                watPaymentCaseConclude = watPaymentCaseConclude.concat($.each(watPaymentCaseArr, function(i,o){ return o }).join(" + "));
            }
        }
        var paymentCaseArr = paymentCase.substring(0, paymentCase.length-3).split(" + ");
        var paymentCaseConclude = $.each(paymentCaseArr, function(i,o){ return o }).join(" + ") + (deduction > 0? watPaymentCaseConclude: "");
            
            var ppaymentCase;
            for (var i = 0; i < bounceChequeBean[j].invoiceList.length; i++) {
                payBounceChequeDTO = {};

                //var deduction = 1; // deduction what???????????
                if(deduction > 0) {
				var watPaymentCase = paymentCase.concat("WT ");
				$.map((window["wthList"] || []), function(o, i) {
			        if($.trim(o[1]) == invoice.no){
			            if($.trim(o[2]).length>0)
			        	    watPaymentCase = watPaymentCase.concat("เลขที่: "+$.trim(o[2])+" + ");
			            else
                            watPaymentCase = watPaymentCase.concat(""+$.trim(o[2])+" + ");
			        	console.log('con watPaymentCase : '+watPaymentCase);
			        } else if($.trim(o[1]) == ''){//กรุณาเลือก
                        if($.trim(o[2]).length>0)
			        	    watPaymentCase = watPaymentCase.concat("เลขที่: "+$.trim(o[2])+" + ");
                        else
                            watPaymentCase = watPaymentCase.concat(""+$.trim(o[2])+" + ");
			        }
			        ppaymentCase = watPaymentCase.substring(0, watPaymentCase.length-3);
				});
			} else {
				ppaymentCase = paymentCase.substring(0, paymentCase.length-3);
			}

                payBounceChequeDTO = {
                    "docHead": bounceChequeBean[j].invoiceList[i].docHead
                    , "refDate": bounceChequeBean[j].invoiceList[i].refDate
                    , "serviceKey3": bounceChequeBean[j].invoiceList[i].serviceKey3
                    , "currency": bounceChequeBean[j].invoiceList[i].currency
                    , "amountARin": bounceChequeBean[j].invoiceList[i].amountARin
                    , "rateChange": view.input.payCashAmountRate.val() //bounceChequeBean[j].invoiceList[i].rateChange
                    , "amountARout": bounceChequeBean[j].invoiceList[i].amountARout
                    , "currencyChangeDateDate": bounceChequeBean[j].invoiceList[i].currencyChangeDate
                    , "docNo": bounceChequeBean[j].invoiceList[i].docNo
                    , "accountYear": bounceChequeBean[j].invoiceList[i].accountYear
                    , "docDateDate": bounceChequeBean[j].invoiceList[i].docDate
                    , "passDate": bounceChequeBean[j].invoiceList[i].passDate
                    , "vatCode": bounceChequeBean[j].invoiceList[i].vatCode
                    , "tradingPartner": bounceChequeBean[j].invoiceList[i].tradingPartner
                    , "message": bounceChequeBean[j].invoiceList[i].message
                    , "orgCode": bounceChequeBean[j].invoiceList[i].orgCode
                    , "docType": bounceChequeBean[j].invoiceList[i].docType
                    , "secment": bounceChequeBean[j].invoiceList[i].secment
                    , "prodKey2": bounceChequeBean[j].invoiceList[i].prodKey2
                    , "arAccountCode": bounceChequeBean[j].invoiceList[i].arAccountCode
                    , "arName": bounceChequeBean[j].invoiceList[i].arName
                    , "glAccount": bounceChequeBean[j].invoiceList[i].glAccount
                    , "taxId": bounceChequeBean[j].invoiceList[i].taxId
                    , "arGroup": bounceChequeBean[j].invoiceList[i].arGroup
                    , "regionKey1": bounceChequeBean[j].invoiceList[i].regionKey1
                    , "branchAR": bounceChequeBean[j].invoiceList[i].branchAR
                    , "address": bounceChequeBean[j].invoiceList[i].address
                    , "recordStatus": bounceChequeBean[j].invoiceList[i].recordStatus
                    , "currencyCode": bounceChequeBean[j].invoiceList[i].currencyCode
                    , "arAccountCode" : bounceChequeBean[j].arAccountCode // เพี้ยนแน่ๆT^T
                    , "paymentCase" : ppaymentCase

                    , "charge": (bounceChequeBean[j].invoiceList[i].amountPay) - vatGet(calculateVatFromIncluded(bounceChequeBean[j].invoiceList[i].amountPay), bounceChequeBean[j].invoiceList[i].amountPay)  //calculateVatFromIncluded(bounceChequeBean[j].invoiceList[i].amountARout)
                    , "chargeBath": (calculateVatFromIncluded(bounceChequeBean[j].invoiceList[i].amountPay))*view.input.payCashAmountRate.val()
                    , "vat": vatGet(calculateVatFromIncluded(bounceChequeBean[j].invoiceList[i].amountPay), bounceChequeBean[j].invoiceList[i].amountPay)
                    , "amountTotalPay": (bounceChequeBean[j].invoiceList[i].amountTotalPay)
                    , "amountPay": (bounceChequeBean[j].invoiceList[i].amountPay) //
                    , "vatRD": vatRDGET
                    , "amount": calculateVatFromIncluded(bounceChequeBean[j].invoiceList[i].amountPay)
					, "localBalDueSap":(parseFloat(bounceChequeBean[j].invoiceList[i].amountARin))-(parseFloat(view.input.payCashAmountRate.val())*parseFloat(bounceChequeBean[j].invoiceList[i].amountPay))
                	, "transBalDueSap":(parseFloat(bounceChequeBean[j].invoiceList[i].amountARout)-parseFloat(bounceChequeBean[j].invoiceList[i].amountPay))
               		, "vatBath":vatGet(calculateVatFromIncluded(bounceChequeBean[j].invoiceList[i].amountPay), bounceChequeBean[j].invoiceList[i].amountPay) *parseFloat(view.input.payCashAmountRate.val())
               		, "totalChargeBath":parseFloat(bounceChequeBean[j].invoiceList[i].amountPay)*parseFloat(view.input.payCashAmountRate.val())
                };

                payBounceChequeDTOList.push(payBounceChequeDTO);
            }
        }
        console.log(payBounceChequeDTOList);
        // flow มันแปลกๆ เพราะมัน not space u know? : my note T^T
        payBounceChequeDTO = {};

        payBounceChequeDTO = {
            //          summarySapAr            //
            "foreignExchangeRate": view.input.foreignExchangeRate.val()
            , "preItemsDiscount": view.input.preItemsDiscount.val()
            , "itemsDiscount": view.input.itemsDiscount.val()
            , "charge": view.input.charge.val()
            , "vat": view.input.vat.val()
            , "totalCharge": view.input.totalCharge.val()
            , "deduct": view.input.deduct.val()
            , "fee": view.input.fee.val()
            , "penalty": view.input.penalty.val()
            , "compensation": view.input.compensation.val()
            , "discount": view.input.discount.val()
            , "balanceDue": view.input.balanceDue.val()
            , "inputReceived": view.inputReceived.val()
            , "change": view.input.change.val()
            , "inputAdvanced": view.inputAdvanced.val()

            , "foreignExchangeRateBath": view.input.foreignExchangeRateBath.val()
            , "preItemsDiscountBath": view.input.preItemsDiscountBath.val()
            , "itemsDiscountBath": view.input.itemsDiscountBath.val()
            , "chargeBath": view.input.chargeBath.val()
            , "vatBath": view.input.vatBath.val()
            , "totalChargeBath": view.input.totalChargeBath.val()
            , "deductBath": view.input.deductBath.val()
            , "feeBath": view.input.feeBath.val()
            , "penaltyBath": view.input.penaltyBath.val()
            , "compensationBath": view.input.compensationBath.val()
            , "discountBath": view.input.discountBath.val()
            , "balanceDueBath": view.input.balanceDueBath.val()
            , "inputReceivedBath": view.inputReceivedBath.val()
            , "changeBath": view.input.changeBath.val()
            , "inputAdvancedBath": view.inputAdvancedBath.val()
            , "rateChange": view.input.payCashAmountRate.val()
            
        };
	console.log(payBounceChequeDTO);
        bounceChequeDTO = {
            "account": "1001"
            , "paymentCase": paymentCaseConclude
            , "payBounceChequeDTO": payBounceChequeDTO
            , "payBounceChequeDTOList": payBounceChequeDTOList
            , "detailARCustomerDTOList": detailARCustomerDTOList
            , "detailARCustomerDTO": detailARCustomerDTO
            , "methods": methods

        };
		console.log(bounceChequeDTO);
        $.ajax({
            url: "../createPaySAPAr",
            type: "POST",
            data: JSON.stringify(bounceChequeDTO),
            async: false,
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            error: view.dialog.mainMessageDialog.valid,
            success: function (res) {
                // if (!view.dialog.mainMessageDialog.valid(res)) {
                // console.log("fucking bug");
                    // return;
                // }

                view.dialog.mainMessageDialog.clear();
                $(document.body).append('<form action="../printSAPAR.pdf" method="post" target="_printForm"></form>');
                var form = document.forms[0];

                function input(n, v) {
                    var input = document.createElement("input");
                    input.type = "hidden";
                    input.name = n;
                    input.value = v;
                    return input
                }

                // form.appendChild(input("receipts[" + i + "].id", o.id));
                // form.appendChild(input("bounceChequeDTO", bounceChequeDTO));

                var resList = [];
                resList.push(res);
                $.each(resList, function (i, o) {
                    form.appendChild(input("docHead", o.docHead));
                    form.appendChild(input("payBounceChequeDTO.foreignExchangeRate", o.payBounceChequeDTO.foreignExchangeRate));
                    form.appendChild(input("payBounceChequeDTO.preItemsDiscount", o.payBounceChequeDTO.preItemsDiscount));
                    form.appendChild(input("payBounceChequeDTO.itemsDiscount", o.payBounceChequeDTO.itemsDiscount));
                    form.appendChild(input("payBounceChequeDTO.charge", o.payBounceChequeDTO.charge));
                    form.appendChild(input("payBounceChequeDTO.vat", vatRDGET));//o.payBounceChequeDTO.vat));
                    form.appendChild(input("payBounceChequeDTO.totalCharge", o.payBounceChequeDTO.totalCharge));
                    form.appendChild(input("payBounceChequeDTO.deduct", o.payBounceChequeDTO.deduct));
                    form.appendChild(input("payBounceChequeDTO.fee", o.payBounceChequeDTO.fee));
                    form.appendChild(input("payBounceChequeDTO.penalty", o.payBounceChequeDTO.penalty));
                    form.appendChild(input("payBounceChequeDTO.compensation", o.payBounceChequeDTO.compensation));
                    form.appendChild(input("payBounceChequeDTO.discount", o.payBounceChequeDTO.discount));
                    form.appendChild(input("payBounceChequeDTO.balanceDue", o.payBounceChequeDTO.balanceDue));
                    form.appendChild(input("payBounceChequeDTO.inputReceived", o.payBounceChequeDTO.inputReceived));
                    form.appendChild(input("payBounceChequeDTO.change", o.payBounceChequeDTO.change));
                    form.appendChild(input("payBounceChequeDTO.inputAdvanced", o.payBounceChequeDTO.inputAdvanced));

                    form.appendChild(input("payBounceChequeDTO.foreignExchangeRateBath", o.payBounceChequeDTO.foreignExchangeRateBath));
                    form.appendChild(input("payBounceChequeDTO.preItemsDiscountBath", o.payBounceChequeDTO.preItemsDiscountBath));
                    form.appendChild(input("payBounceChequeDTO.itemsDiscountBath", o.payBounceChequeDTO.itemsDiscountBath));
                    form.appendChild(input("payBounceChequeDTO.chargeBath", o.payBounceChequeDTO.chargeBath));
                    form.appendChild(input("payBounceChequeDTO.vatBath", o.payBounceChequeDTO.vatBath));
                    form.appendChild(input("payBounceChequeDTO.totalChargeBath", o.payBounceChequeDTO.totalChargeBath));
                    form.appendChild(input("payBounceChequeDTO.deductBath", o.payBounceChequeDTO.deductBath));
                    form.appendChild(input("payBounceChequeDTO.feeBath", o.payBounceChequeDTO.feeBath));
                    form.appendChild(input("payBounceChequeDTO.penaltyBath", o.payBounceChequeDTO.penaltyBath));
                    form.appendChild(input("payBounceChequeDTO.compensationBath", o.payBounceChequeDTO.compensationBath));
                    form.appendChild(input("payBounceChequeDTO.discountBath", o.payBounceChequeDTO.discountBath));
                    form.appendChild(input("payBounceChequeDTO.balanceDueBath", o.payBounceChequeDTO.balanceDueBath));
                    form.appendChild(input("payBounceChequeDTO.inputReceivedBath", o.payBounceChequeDTO.inputReceivedBath));
                    form.appendChild(input("payBounceChequeDTO.changeBath", o.payBounceChequeDTO.changeBath));
                    form.appendChild(input("payBounceChequeDTO.inputAdvancedBath", o.payBounceChequeDTO.inputAdvancedBath));

                    form.appendChild(input("payBounceChequeDTO.rateChange", o.payBounceChequeDTO.rateChange));
                    
                    for (var k = 0; k < o.payBounceChequeDTOList.length; k++) {
                        form.appendChild(input("payBounceChequeDTOList[" + k + "].docHead", o.payBounceChequeDTOList[k].docHead));
                        form.appendChild(input("payBounceChequeDTOList[" + k + "].refDate", o.payBounceChequeDTOList[k].refDate));
                        form.appendChild(input("payBounceChequeDTOList[" + k + "].serviceKey3", o.payBounceChequeDTOList[k].serviceKey3));
                        form.appendChild(input("payBounceChequeDTOList[" + k + "].currency", o.payBounceChequeDTOList[k].currency));
                        form.appendChild(input("payBounceChequeDTOList[" + k + "].amountARin", o.payBounceChequeDTOList[k].amountARin));
                        form.appendChild(input("payBounceChequeDTOList[" + k + "].rateChange", o.payBounceChequeDTOList[k].rateChange));
                        form.appendChild(input("payBounceChequeDTOList[" + k + "].amountARout", o.payBounceChequeDTOList[k].amountARout));
                        form.appendChild(input("payBounceChequeDTOList[" + k + "].currencyChangeDate", o.payBounceChequeDTOList[k].currencyChangeDateDate));
                        form.appendChild(input("payBounceChequeDTOList[" + k + "].docNo", o.payBounceChequeDTOList[k].docNo));
                        form.appendChild(input("payBounceChequeDTOList[" + k + "].accountYear", o.payBounceChequeDTOList[k].accountYear));
                        form.appendChild(input("payBounceChequeDTOList[" + k + "].docDate", o.payBounceChequeDTOList[k].docDate));
                        form.appendChild(input("payBounceChequeDTOList[" + k + "].passDate", o.payBounceChequeDTOList[k].passDate));
                        form.appendChild(input("payBounceChequeDTOList[" + k + "].vatCode", o.payBounceChequeDTOList[k].vatCode));
                        form.appendChild(input("payBounceChequeDTOList[" + k + "].tradingPartner", o.payBounceChequeDTOList[k].tradingPartner));
                        form.appendChild(input("payBounceChequeDTOList[" + k + "].message", o.payBounceChequeDTOList[k].message));

                        form.appendChild(input("payBounceChequeDTOList[" + k + "].vat", rateChangeRD));//o.payBounceChequeDTOList[k].vat));
                        form.appendChild(input("payBounceChequeDTOList[" + k + "].charge", (calculateVatFromIncluded(o.payBounceChequeDTOList[k].amountARin))));

                        form.appendChild(input("payBounceChequeDTOList[" + k + "].orgCode", o.payBounceChequeDTOList[k].orgCode));
                        form.appendChild(input("payBounceChequeDTOList[" + k + "].docType", o.payBounceChequeDTOList[k].docType));
                        form.appendChild(input("payBounceChequeDTOList[" + k + "].secment", o.payBounceChequeDTOList[k].secment));
                        form.appendChild(input("payBounceChequeDTOList[" + k + "].prodKey2", o.payBounceChequeDTOList[k].prodKey2));
                        form.appendChild(input("payBounceChequeDTOList[" + k + "].arAccountCode", o.payBounceChequeDTOList[k].arAccountCode));
                        form.appendChild(input("payBounceChequeDTOList[" + k + "].arName", o.payBounceChequeDTOList[k].arName));
                        form.appendChild(input("payBounceChequeDTOList[" + k + "].glAccount", o.payBounceChequeDTOList[k].glAccount));
                        form.appendChild(input("payBounceChequeDTOList[" + k + "].taxId", o.payBounceChequeDTOList[k].taxId));
                        form.appendChild(input("payBounceChequeDTOList[" + k + "].arGroup", o.payBounceChequeDTOList[k].arGroup));
                        form.appendChild(input("payBounceChequeDTOList[" + k + "].regionKey1", o.payBounceChequeDTOList[k].regionKey1));
                        form.appendChild(input("payBounceChequeDTOList[" + k + "].branchAR", o.payBounceChequeDTOList[k].branchAR));
                        form.appendChild(input("payBounceChequeDTOList[" + k + "].address", o.payBounceChequeDTOList[k].address));

                        form.appendChild(input("payBounceChequeDTOList[" + k + "].vatRD", o.payBounceChequeDTOList[k].vatRD));
                        form.appendChild(input("payBounceChequeDTOList[" + k + "].amountPay", o.payBounceChequeDTOList[k].amountPay));
                        form.appendChild(input("payBounceChequeDTOList[" + k + "].vatBath", (o.payBounceChequeDTOList[k].amountARin - calculateVatFromIncluded(o.payBounceChequeDTOList[k].amountARin))));
                        // form.appendChild(input("payBounceChequeDTOList[" + k + "].inputReceivedBath", o.payBounceChequeDTOList[k].inputReceivedBath));
                        // form.appendChild(input("payBounceChequeDTOList[" + k + "].changeBath", o.payBounceChequeDTOList[k].changeBath));
                        // form.appendChild(input("payBounceChequeDTOList[" + k + "].inputAdvancedBath", o.payBounceChequeDTOList[k].inputAdvancedBath));
                    }

                    // form.appendChild(input("detailARCustomerDTO.arAccountCode", o.detailARCustomerDTO.arAccountCode));
                    // form.appendChild(input("detailARCustomerDTO.arName", o.detailARCustomerDTO.arName));
                    // form.appendChild(input("detailARCustomerDTO.taxId", o.detailARCustomerDTO.taxId));
                    // form.appendChild(input("detailARCustomerDTO.glAccount", o.detailARCustomerDTO.glAccount));
                    // form.appendChild(input("detailARCustomerDTO.arGroup", o.detailARCustomerDTO.arGroup));
                    // form.appendChild(input("detailARCustomerDTO.regionKey1", o.detailARCustomerDTO.regionKey1));
                    // form.appendChild(input("detailARCustomerDTO.branchAR", o.detailARCustomerDTO.branchAR));
                    // form.appendChild(input("detailARCustomerDTO.address", o.detailARCustomerDTO.address));
                    for (var k = 0; k < o.detailARCustomerDTOList.length; k++) {
                        form.appendChild(input("detailARCustomerDTOList[" + k + "].arAccountCode", o.detailARCustomerDTOList[k].arAccountCode));
                        form.appendChild(input("detailARCustomerDTOList[" + k + "].arName", o.detailARCustomerDTOList[k].arName));
                        form.appendChild(input("detailARCustomerDTOList[" + k + "].taxId", o.detailARCustomerDTOList[k].taxId));
                        form.appendChild(input("detailARCustomerDTOList[" + k + "].glAccount", o.detailARCustomerDTOList[k].glAccount));
                        form.appendChild(input("detailARCustomerDTOList[" + k + "].arGroup", o.detailARCustomerDTOList[k].arGroup));
                        form.appendChild(input("detailARCustomerDTOList[" + k + "].regionKey1", o.detailARCustomerDTOList[k].regionKey1));
                        form.appendChild(input("detailARCustomerDTOList[" + k + "].branchAR", o.detailARCustomerDTOList[k].branchAR));
                        form.appendChild(input("detailARCustomerDTOList[" + k + "].address", o.detailARCustomerDTOList[k].address));
                        form.appendChild(input("detailARCustomerDTOList[" + k + "].reportNo", o.detailARCustomerDTOList[k].reportNo));
                        form.appendChild(input("detailARCustomerDTOList[" + k + "].upDDate", o.detailARCustomerDTOList[k].upDDate));
                        form.appendChild(input("detailARCustomerDTOList[" + k + "].branchName", o.detailARCustomerDTOList[k].branchName));
                    }
                    // form.appendChild(input("payBounceChequeDTOList[" + i + "].billingServiceName", o.billingServiceName)); //by NSD 24-03-20
                    // form.appendChild(input("payBounceChequeDTOList[" + i + "].flgHeader", o.flgHeader));
                    // form.appendChild(input("payBounceChequeDTOList", o.payBounceChequeDTOList));
                    // form.appendChild(input("detailARCustomerDTOList", o.detailARCustomerDTOList));
                });

                form.submit();

                view.breadcrumb.breadcrumb.index(4);
                view.panel.navigationPanel.hide(200);
                view.panel.fillDataInputPanel.hide(400);
                view.panel.linkPanel.hide(200);
                view.panel.informationPanel.hide(600);
                view.panel.receiptInfoPanel.hide(800);
                view.panel.summaryPanel.show(1000);

                var receiptList = [],
                invoice;
                var customer = [];
                var bounceChequeT = view.session("bounceCheque");
                $.each(resList, function(i, r) {
	                // invoice = r.payBounceChequeDTOList[0];
	                var invCount = 0;
	                custIndex = 0;
	                for (i = 0; i < r.payBounceChequeDTOList.length; i++) {
	                    // if (r.payBounceChequeDTOList[i].billCycle != null) {
	                        invCount++;
	                    // }
	                }

                    for (var aa=0; aa<r.detailARCustomerDTOList.length; aa++) {
                        while (customer = bounceChequeT[custIndex]) {
	                    if (customer.arAccountCode == r.detailARCustomerDTOList[aa].arAccountCode) {
	                        receiptList.push({
	                            "customerNo": r.detailARCustomerDTOList[custIndex].arAccountCode,
	                            "customerName": r.detailARCustomerDTOList[custIndex].arName,
	                            "invoiceCount": invCount //r.invoices.length
	                                ,
	                            "receiptNo": r.detailARCustomerDTOList[custIndex].reportNo,
	                            "receiptAmount": view.utils.numberFormat(r.payBounceChequeDTO.totalChargeBath),
	                            "receiptStatus": '<span class="glyphicon glyphicon-ok-circle"></span> บันทึกลงระบบสำเร็จ',
	                            "invoices": r.createPaymentResultDTO.data[0].invoices,
                                "invoice": r.createPaymentResultDTO
	                        });
	                    }
	                    custIndex++;
	                }
                    }
	                
	            });
	            view.table.receiptList.data(receiptList);
	        
            }
        });
        view.session("bounceCheque", []);
    }

    function submitPaymentClickEvent() {
        submitPayment("TH");
    }
    function submitPaymentEngClickEvent() {
        submitPayment("ENG");
    }

    function payChqSubmitClickEvent() {
	if(validationChqSubmit()) view.table.payChqList.insert(["-", view.input.payChqBankCode.val(), view.input.payChqBankName.val(), view.input.payChqBranch.val(), view.input.payChqNo.val(), view.input.payChqDate.val(), view.input.payChqAmount.val()], true)
    }
    function validationChqSubmit() {
        var isValid = true;
        if(view.input.payChqNo.val() == "") {view.input.payChqNo.error(true); isValid = false;} else {view.input.payChqNo.error(false);}
        if(view.input.payChqDate.val() == "") {view.input.payChqDate.error(true); isValid = false;} else {view.input.payChqDate.error(false);}
        return isValid;
    }

    function payCCSubmitClickEvent() {
        if(validatePayCCSubmit()) view.table.payCCList.insert(["-", view.input.payCCType.val(), view.input.payCCNo.val(), view.input.payCCBankName.val(), view.input.payCCAmount.val()], true)
    }
    function validatePayCCSubmit() {
        var isValid = true;
        if(view.input.payCCType.index() === 0) {view.input.payCCType.error(true); isValid = false;} else {view.input.payCCType.error(false);}
        if(view.input.payCCNo.val() == "" || view.input.payCCNo.val() < 16) {view.input.payCCNo.error(true); isValid = false;} else {view.input.payCCNo.error(false);}
        if(view.input.payCCBankName.index() === 0) {view.input.payCCBankName.error(true); isValid = false; } else {view.input.payCCBankName.error(false);}
        return isValid;
    }

    function payBankTxnfButtonSubmitClickEvent() {
	if(validatePayBankTxnfButtonSubmit()) view.payBankTxnfTableList.insert(["-", view.input.payBankTxnfDropDownBankCode.val(), view.input.payBankTxnfDropDownBankName.val(), view.payBankTxnfDropDownBankBrnh.val(), view.payBankTxnfInputNo.val(), view.payBankTxnfInputDate.val(), view.payBankTxnfInputAmt.val()], true);
    }
    function validatePayBankTxnfButtonSubmit() {
        var isValid = true;
        if(view.input.payBankTxnfDropDownBankCode.val() == "") { view.input.payBankTxnfDropDownBankCode.error(true); isValid = false; } else {view.input.payBankTxnfDropDownBankCode.error(false); }
        if(view.input.payBankTxnfDropDownBankName.index() === 0) { view.input.payBankTxnfDropDownBankName.error(true); isValid = false; } else {view.input.payBankTxnfDropDownBankName.error(false); }
        if(view.payBankTxnfDropDownBankBrnh.index() === 0) { view.payBankTxnfDropDownBankBrnh.error(true); isValid = false; } else {view.payBankTxnfDropDownBankBrnh.error(false); }
        //if(view.payBankTxnfInputNo.val() == "") { view.payBankTxnfInputNo.error(true); isValid = false; } else {view.payBankTxnfInputNo.error(false); } //by NSD 17-02-2017
        if(view.payBankTxnfDropDownBankAcCd.index() === 0) { view.payBankTxnfDropDownBankAcCd.error(true); isValid = false; } else {view.payBankTxnfDropDownBankAcCd.error(false); }
        if(view.payBankTxnfInputDate.val() == "") { view.payBankTxnfInputDate.error(true); isValid = false; } else {view.payBankTxnfInputDate.error(false); }
        if(view.payBankTxnfDropDownBankAcct.index() === 0) { view.payBankTxnfDropDownBankAcct.error(true); isValid = false; } else {view.payBankTxnfDropDownBankAcct.error(false); }
        return isValid;
    }

    function payForeignButtonSubmitClickEvent() {
	if(validatePayForeignButtonSubmit()) view.payForeignTableList.insert(["-", $("#currencyTypeSelect").val()/*view.currencyTypeSelect.val()*/, view.exchangeRateInput.val(), view.payDateInputDate.val(), view.foreignAmountInput.val(), view.thAmountInput.val()], true);
    }
    function validatePayForeignButtonSubmit() {
        var isValid = true;
        if($("#currencyTypeSelect").val() == 0) { $("#currencyTypeSelect").css("border-color","#a94442");/*view.currencyTypeSelect.error(true);*/ isValid = false; } else { $("#currencyTypeSelect").css("border-color","#ccc");/*view.currencyTypeSelect.error(false);*/ }
        if(view.payBankTxnfDropDownBank.index() === 0) { view.payBankTxnfDropDownBank.error(true); isValid = false; } else {view.payBankTxnfDropDownBank.error(false); }
        if(view.payDateInputDate.val() == "") { view.payDateInputDate.error(true); isValid = false; } else {view.payDateInputDate.error(false); }
        return isValid;
    }

    function addExchangRate(){
    var Type = $('#currencyTypeSelect').val();
    console.log(Type);
    for(var i=0;i<exchange.length;i++) {
        console.log(exchange[i].message);
        if (Type == exchange[i].message) {
            view.exchangeRateInput.val(exchange[i].rateUnit);
            break;
        } else {
            view.exchangeRateInput.val('0.00');
        }
    }
    convertAmount();
    }

    function addExchangRateBefore(){
    var Type = $('#currencyTypeSelect').val();
    console.log(Type);
    for(var i=0;i<dataRateList.length;i++) {
        console.log(dataRateList[i].message);
        if (Type == dataRateList[i].message) {
            view.exchangeRateInput.val(dataRateList[i].rateUnit);
            break;
        } else {
            view.exchangeRateInput.val('0.00');
        }
    }
    convertAmount();
    }

    function convertAmount() {
        var rate = view.exchangeRateInput.val();        
        var amount = view.foreignAmountInput.val();
        var sum = 0;
        if (amount != "") {
            sum = rate*amount;
        } else {
            sum = 0*amount;
        }
        view.thAmountInput.val(sum);
    }

    function convertAmountCash() {
        var rate = view.input.payCashAmountRate.val();
        var amount = view.input.payCashAmount.val();
        var amountBath = view.input.payCashAmountBath.val();
        var sum = 0;
        if (amountBath != "") {
            sum = amountBath/rate;
        } else {
            sum = 0*amountBath;
        }
        view.input.payCashAmount.val(sum);
    }

    function convertAmountCashBath() {
        var rate = view.input.payCashAmountRate.val();
        var amount = view.input.payCashAmount.val();
        var amountBath = view.input.payCashAmountBath.val();
        var sum = 0;
        if (amount != "") {
            sum = rate*amount;
        } else {
            sum = 0*amount;
        }
        view.input.payCashAmountBath.val(sum);
    }

    function convertAmountRateChange() {
        var rate = view.input.payCashAmountRate.val();
        var amount = view.input.payCashAmount.val();
        var amountBath = view.input.payCashAmountBath.val();
        var sum = 0;
        var sumBath = 0;
        if (amount != "" && amountBath != "") {
            sumBath = rate*amount;
            sum = amountBath/rate;
        } else {
            sumBath = 0*amount;
            sum = 0*amountBath;
        }
        view.input.payCashAmountBath.val(sumBath);
        // view.input.payCashAmount.val(sum);
    }
    function addPenaltyTaxNoClickEvent() {
        view.table.penaltyTaxList.insert(["-", view.input.penaltyInvoiceNo.val(), view.radio.penaltyTaxType.label(), view.input.penaltyTaxAmount.val()], true);
        $('#penaltyTaxList td:nth-child(3)').hide();
    }

    function calculateVatFromIncluded(ammount) {
            var rtVatBalance = 0;
            var sumVat = (100+vatRDGET);
            rtVatBalance = (ammount * 100) / sumVat;
            return rtVatBalance;
        }

        function detailFormatter(val, row, ind) {
            var details = '<table class="table table-striped table-bordered">'
                + '<thead>'
                + '<tr>'
                + '<th class="text-center">เลขที่ใบแจ้งค่าใช้บริการ</th>'
                // + '<th class="text-center">วันที่เอกสาร</th>'
                // + '<th class="text-center">วันที่ครบกำหนด</th>'
                + '<th class="text-right">ยอดก่อนภาษี</th>'
                + '<th class="text-right">ภาษีมูลค่าเพิ่ม</th>'
                + '<th class="text-right">ยอดเงินรวมภาษี</th>'
                + '<th class="text-right">ส่วนลดหลังการขาย</th>'

                + '<th class="text-right">ยอดชำระ</th>'
                + '<th class="text-right">ภาษีหัก ณ ที่จ่าย</th>'
                + '<th class="text-right">จำนวนเงินคงค้าง</th>'
                + '<th class="text-center">รอบการใช้งาน</th>' + '</tr>'
                + '</thead>' + '<tbody>';
            for (var i = 0, m = row.invoices.length; i < m; i++) {
                var multipy = 1 ;
            
                var totalCharge = 0, amtBeforeVat =0, amtVat =0, flgExchange = false;
                if(vatRDGET==null || vatRDGET == 0.00){
                    vatRDGET = 1.0;
                }else{
                    totalCharge = row.invoices[i].totalCharge*vatRDGET;
                    if(row.invoices[i].vatRate!=null)
                        amtVat = totalCharge*row.invoices[i].vatRate/(100+vatRDGET);
                    amtBeforeVat = totalCharge - amtVat;
                    flgExchange = true;
                }
                multipy = vatRDGET;
                if(row.invoices[i].recordStatus=='A')
                    multipy = 1.0;
                //alert(multipy)
                var docDate = row.invoices[i].docDate, dueDt = row.invoices[i].dueDt;
                details += '<tr>'
                    + '<td class="text-center">'+ $.trim(row.invoices[i].docHead) +'</td>'
                    // + '<td class="text-center">'+ (docDate == null ? "-" : docDate) +'</td>'
                    // + '<td class="text-center">'+ (dueDt == null ? "-" : view.utils.dateFormat(dueDt)) +'</td>'
                    + '<td class="text-right">'+ view.utils.numberFormat( calculateVatFromIncluded(row.invoices[i].debtAmount)) +'</td>'
                    + '<td class="text-right">'+ (row.invoices[i].vat == null ? "-" : view.utils.numberFormat(row.invoices[i].vat)) +'</td>'
                    + '<td class="text-right">'+ view.utils.numberFormat(row.invoices[i].debtAmount) +'</td>'
                    + '<td class="text-right">'+ view.utils.numberFormat(row.invoices[i].afterSaleDiscount) +'</td>'
                    + '<td class="text-right">'+ view.utils.numberFormat(row.invoices[i].totalCharge-row.invoices[i].afterSaleDiscount-(row.invoices[i].afterSaleDiscVat == null? 0 : row.invoices[i].afterSaleDiscVat)) +'</td>'
                    + '<td class="text-right">'+ view.utils.numberFormat(row.invoices[i].deduction) +'</td>'
                    + '<td class="text-right">'+ view.utils.numberFormat(row.invoices[i].balanceDue) +'</td>'
                    + '<td class="text-center">'+ (row.invoices[i].refDate == null? "-" : $.trim(row.invoices[i].refDate))+'</td>'
                    + '</tr>';
            }
            return details + '</tbody>'
                + '</table>';
        }

</script>