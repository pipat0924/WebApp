<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
    <html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>รับชำระลูกหนี้ SAP รายตัว</title>
        <link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <link href="resources/bootstrap-table/src/bootstrap-table.css" rel="stylesheet" type="text/css" />
        <link href="resources/custom.css" rel="stylesheet" type="text/css" />
        <script src="resources/jquery.min.js" type="text/javascript"></script>
        <script src="resources/bootstrap/js/bootstrap.min.js"></script>
        <script src="resources/bootstrap-table/src/bootstrap-table.js"></script>
        <script src="resources/js/mustache.min.js" type="text/javascript"></script>
        <script src="resources/custom.js" type="text/javascript"></script>
    </head>

    <body>
        <header class="header_page"></header>
        <section class="container-fluid menu">
           <!-- <button class="menu-toggle btn btn-sm btn-default" style="margin-bottom: 5px"><span class="glyphicon glyphicon-step-forward"></span></button> -->
    <%-- <%@include  file="menu.jsp" %> --%>
                <form id="bounceChequeForm" name="bounceChequeForm" method="post" class="form-horizontal" role="form">
                    <div class="row">
                        <div class="col-md-12 tab-modefile">
                            <ol class="breadcrumb">
                                <li><i>เช็คขัดข้อง</i></li>
                                <li class="active">รับชำระลูกหนี้ SAP รายตัว</li>
                                <li class="">สรุปการรับชำระเงิน</li>
                                <li>เลือกวิธีการรับชำระ</li>
                                <li>ผลการรับชำระ</li>
                            </ol>
                            <div id="mainMessageDialog"></div>
                            <div id="billingGroupMessageDialog"></div>
                            <ul class="nav nav-tabs" role="tablist">
                                <li role="presentation" class="active"><a aria-controls="tab_cus" role="tab" data-toggle="tab"><span class="glyphicon glyphicon-filter"></span> ค้นหาข้อมูล</a></li>
                            </ul>
                            <div class="panel panel-default panal-radius">
                                <div class="panel-body">
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-horizontal">
                                                <div class="form-group">
                                                    <div class="col-sm-5">
                                                        <label class="control-label col-sm-6">เลขที่ลูกค้า(รหัสลูกหนี้):</label>
                                                        <div class="col-sm-6"><input class="form-control" id="account" name="account"></div>
                                                    </div>
                                                    <div class="col-sm-5">
                                                        <label class="control-label col-sm-6">เลขที่ใบแจ้งหนี้ (Doc. Header Text) :</label>
                                                        <div class="col-sm-6"><input class="form-control" id="docHead" name="docHead"></div>
                                                    </div>

                                                    <div class="col-sm-2">
                                                        <a id="search1" class="btn btn-primary" onclick="searchBounceCheque()"><span class="glyphicon glyphicon-search"></span> ค้นหา</a>&nbsp;&nbsp;
                                                        <a id="advanceSearch" class="btn btn-success"><span class="glyphicon glyphicon-zoom-in"></span> ค้นหาเพิ่มเติม</a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <ul id="navigatePanel" class="hide list-inline pull-right list-menu-set">
                        <li><a id="addPayment" class="btn btn-link"><span class="glyphicon glyphicon-plus-sign" ></span> เพิ่มรายการรับชำระ</a></li>
                        <li><a id="summaryPayment" class="btn btn-link"><span class="glyphicon glyphicon-th-list"></span> สรุปรายการรับชำระ</a>
                            <span class="badge badge_modefil">1</span>
                        </li>
                        <li><a id="submitPayment" class="btn btn-link"><span class="glyphicon glyphicon-edit"></span> ดำเนินการรับชำระ</a></li>
                    </ul>

                    <!-- <div class="row"> -->
                        <div id="customerARPanel" class="hide row">
                            <div class="col-md-12 tab-modefile">
                                <ul id="customerInfoTab" class="nav nav-tabs" role="tablist">
                                    <li role="presentation" class="active"><a href="#tab_cus" aria-controls="tab_cus" role="tab" data-toggle="tab"><span class="glyphicon glyphicon-user"></span> ข้อมูลลูกค้า</a></li>
                                </ul>
                                <div class="tab-content">
                                    <div role="tabpanel" class="tab-pane active" id="tab_cus">
                                        <div class="panel panel-default panal-radius">
                                            <div class="panel-body">
                                                <div class="row">
                                                    <div class="col-md-12">
                                                        <div class="form-horizontal">
                                                            <div class="form-group">
                                                                <label class="control-label col-sm-2">เลขที่ลูกค้า (รหัสลูกหนี้) :</label>
                                                                <div class="col-sm-2"><input id="arAccountCode" class="form-control" disabled="disabled"></div>
                                                                <label class="control-label col-sm-2">ชื่อลูกค้า (ชื่อลูกหนี้) :</label>
                                                                <div class="col-sm-6"><input id="arName" class="form-control"></div>
                                                                <input type="hidden" id=""><input type="hidden" id="acctCatLkp">
                                                                <input type="hidden" id="catCustomerSegment">
                                                            </div>
                                                            <div class="form-group">
                                                                <label class="control-label col-sm-2">Tax ID :</label>
                                                                <div class="col-sm-2"><input id="taxId" class="form-control" maxlength="13"></div>
                                                                <label class="control-label col-sm-2">บัญชีกระทบยอด :</label>
                                                                <div class="col-sm-2"><input id="glAccount" class="form-control" maxlength="5"></div>
                                                                <label class="control-label col-sm-2">กลุ่มผู้ใช้บริการ (กลุ่มลูกค้า) :</label>
                                                                <div class="col-sm-2">
                                                                    <select class="form-control" id="arGroup" disabled></select>
                                                                </div>
                                                            </div>
                                                            <div class="form-group">
                                                                <label class="control-label col-sm-2">หน่วยงานติดตามหนี้ (คีย์อ้างอิง1) :</label>
                                                                <div class="col-sm-2"><input id="regionKey1" class="form-control" disabled></div>
                                                                <label class="control-label col-sm-2">สาขาลูกหนี้ :</label>
                                                                <div class="col-sm-2"><input id="branchAR" class="form-control"></div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <br />
                                                <div class="row">
                                                    <div class="col-md-12 tab-modefile">
                                                        <ul id="addressTab" class="nav nav-tabs" role="tablist">
                                                            <li role="presentation" class="active"><a href="#tab1" aria-controls="tab1" role="tab" data-toggle="tab"><span class="glyphicon glyphicon-home"></span> ที่อยู่สำหรับใบเสร็จรับเงิน</a></li>
                                                            <li role="presentation" class="">
                                                                <div class="checkbox"><label> &nbsp;&nbsp;<input id="changeReceiptAddress" type="checkbox">เปลี่ยนแปลงที่อยู่ในใบเสร็จรับเงิน/ใบกำกับภาษี</label></div>
                                                            </li>
                                                            <li role="presentation" class="">
                                                                <div class="checkbox" style="margin-left: 20px"><label> &nbsp;<input id="splitReceiptDocument" type="checkbox" value="true">แยกใบเสร็จรับเงิน/ใบกำกับภาษีตามเลขที่ใบแจ้งหนี้ (Doc. Header Text)</label></div>
                                                            </li>
                                                        </ul>
                                                        <div class="tab-content">
                                                            <div role="tabpanel" class="tab-pane active" id="tab1"><textarea id="address" disabled="disabled" class="form-control textarea-tab"></textarea></div>
                                                            <div role="tabpanel" class="tab-pane" id="tab2"><textarea id="invoiceAddress" class="form-control textarea-tab"></textarea></div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div id="bouncePanel" class="hide row">
                            <div class="col-md-12 tab-modefile">
                                <ul id="bounceChequeTab" class="nav nav-tabs">
                                    <li class="active"><a href="#tab_bounce" data-toggle="tab"><span class="glyphicon glyphicon-briefcase"></span> รายการใบแจ้งสินค้าใช้บริการ</a></li>
                                    <li class=""><a href="#tab-history" data-toggle="tab"><span class="glyphicon glyphicon-briefcase"></span> ประวัติการรับชำระ</a></li>
                                </ul>

                                <div class="tab-content">
                                    <div class="tab-pane active" id="tab_bounce">
                                        <table id="invoiceList" data-maintain-selected="true" class="table table-hover" data-toggle="table" data-detail-view="true"
                                            data-detail-formatter="invoiceServiceFormatter" data-pagination="true" data-page-size="5"
                                            data-page-list="[5, 10, 20, 50, 100, 200]" data-cache="false">
                                            <thead>
                                                <tr>
                                                    <th data-field="checked" data-checkbox="true">&nbsp;</th>
                                                    <th data-field="docHead" data-align="center" data-sortable="true">เลขที่ใบแจ้งหนี้<br>(Doc.Header Text)</th>
                                                    <th data-field="refDate" data-sortable="true" data-align="center">รอบการใช้งาน<br>การอ้างอิง</th>
                                                    <th data-field="serviceKey3" data-align="center" data-sortable="true">บริการ<br>(คีย์อ้างอิง3)</th>
                                                    <th data-field="currency" data-align="center" data-sortable="true">สกุลเงิน</th>
                                                    <th data-field="amountARout" data-sortable="true" data-align="center" data-formatter="numberFormatter">จำนวนเงิน<br>ตามสกุลที่บันทึก</th>
                                                    <th data-field="rateChange" data-align="center" data-sortable="true" data-formatter="numberFormatter">อัตรา<br>การแลกเปลี่ยน</th>
                                                    <th data-field="amountARin" data-sortable="true" data-align="center" data-formatter="numberFormatter">จำนวนเงิน<br>สกุลในประเทศ</th>
                                                    <th data-field="amountTotalPay" data-sortable="true" data-align="center" data-formatter="numberFormatter">ยอดเงินคงเหลือ<br>ตามสกุลที่บันทึก</th>
                                                    <th data-field="amountPay" data-sortable="true" data-align="center" data-formatter="moneyInputFormatter">ยอดชำระ<br>ตามสกุลที่บันทึก</th>
                                                    <th data-field="currencyChangeDate" data-sortable="true" data-align="right">วันที่แปลงค่า</th>
                                                    <th data-field="docNo" data-sortable="true" data-align="left">เลขที่เอกสาร</th>
                                                    <th data-field="accountYear" data-align="center" data-sortable="true">ปีบัญชี</th>
                                                    <th data-field="docDate" data-sortable="true" data-align="left">วันที่เอกสาร</th>
                                                    <th data-field="passDate" data-sortable="true" data-align="left">วันที่ผ่านรายการ</th>
                                                    <th data-field="vatCode" data-align="center" data-sortable="true">รหัสภาษี</th>
                                                    <th data-field="message" data-align="center" data-sortable="true">ข้อความ</th>

                                                </tr>
                                            </thead>
                                            <tbody>
                                            </tbody>
                                        </table>
                                    </div>

                                    <div class="tab-pane" id="tab-history">
                                        <table id="historyList" data-toggle="table" data-classes="table table-hover table-striped" data-pagination="true" data-page-size="5"
                                            data-page-list="[5, 10, 20, 50, 100, 200]" data-cache="false">
                                            <thead>
                                                <tr>
                                                    <th data-formatter="runningFormatter">#</th>
                                                    <th data-field="payDate" data-align="center">วันที่ทำ<br>รายการ</th>
                                                    <th data-field="receiptDate" data-align="center">วันที่ออกใบ<br>เสร็จรับเงิน</th>
                                                    <th data-field="receiptNo" data-align="center">เลขที่ใบ<br>เสร็จรับเงิน</th>
                                                    <th data-field="branch" data-align="center">สถานที่<br>รับชำระ</th>
                                                    <th data-field="user" data-align="center">ผู้รับชำระ</th>
                                                    <th data-field="arNo" data-align="center">เลขที่<br>ใบแจ้งหนี้</th>
                                                    <th data-field="serviceKey3" data-align="center">บริการ<br>(คีย์อ้างอิง3)</th>
                                                    <th data-field="refDate" data-align="center">รอบการใช้งาน</th>
                                                    <th data-field="payAmountAR" data-align="center" data-formatter="numberFormatter">ยอดเงินตาม<br>ใบแจ้งหนี้</th>
                                                    <th data-field="logPay" data-align="center">วิธีการ<br>ชำระ</th>
                                                    <th data-field="payType" data-align="center">ประเภทการ<br>รับชำระ</th>
                                                    <th data-field="amountCurrency" data-formatter="numberFormatter" data-align="center">ยอดชำระเงิน<br>ตามสกุลที่บันทึก</th>
                                                    <th data-field="vat" data-formatter="numberFormatter" data-align="center">ภาษีมูลค่าเพิ่ม<br>จากการรับชำระ </th>
                                                    <th data-field="currency" data-align="center">สกุลเงิน</th>
                                                    <th data-field="rateChange" data-align="center" data-formatter="numberFormatter">อัตราการแลกเปลี่ยน</th>
                                                    <th data-field="changeDate" data-align="center">วันที่แปลงค่า</th>
                                                    <th data-field="amountCurrencyin" data-align="center" data-formatter="numberFormatter">ยอดชำระเงินสกุลในประเทศ</th>
                                                    <th data-field="vatIn" data-align="center" data-formatter="numberFormatter">ภาษีมูลค่าเพิ่มจากการรับชำระ</th>
                                                    <th data-field="docNo" data-align="center">เลขที่เอกสาร</th>
                                                    <th data-field="accountYeah" data-align="center">ปีบัญชี</th>
                                                    <th data-field="status" data-align="center">สถานะ</th>
                                                    <th data-field="remark" data-align="center">หมายเหตุ</th>
                                                    <th data-field="massage" data-align="center">ข้อความ</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>

                            </div>
                        </div>

                        <div id="summaryPanel" class="hide row">
                            <div class="col-md-12">
                                <div class="panel panel-default">
                                    <div class="panel-heading"><span class="glyphicon glyphicon-shopping-cart"></span> สรุปยอดเงินที่ต้องชำระ</div>
                                    <div class="panel-body">
                                        <div class="form-horizontal">
                                            <div class="form-group">
                                                
                                                <label class="control-label col-sm-10">จำนวนเงินตามสกุลที่บันทึก</label>
                                                <label class="control-label col-sm-2">จำนวนเงินสกุลในประเทศ</label>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-sm-2">
                                    <input type="checkbox" id="advancedCheckbox" name="advancedCheckbox" >
                                    &nbsp;&nbsp;&nbsp;รับชำระเงินล่วงหน้า</label>
                                                <div class="col-sm-2"><input id="advancedAmount" class="form-control"></div>
                                                <label class="control-label col-sm-4">ยอดเงินที่ต้องชำระก่อนภาษีมูลค่าเพิ่ม  :</label>
                                                <div class="col-sm-2"><input id="sumBalance2" class="form-control text-right" disabled></div>
                                                <div class="col-sm-2"><input id="sumBalance" class="form-control text-right" disabled></div>
                                            </div>

                                            <div class="form-group">
                                                <label class="control-label col-sm-2">รอบการใช้งาน</label>
                                                <div class="col-sm-2"><input id="billCycle" class="form-control" maxlength="140"></div>
                                                <label class="control-label col-sm-4">ภาษีมูลค่าเพิ่ม  :</label>
                                                <div class="col-sm-2"><input id="sumVat2" class="form-control text-right" disabled></div>
                                                <div class="col-sm-2"><input id="sumVat" class="form-control text-right" disabled></div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-sm-8">ยอดเงินที่ต้องชำระรวมภาษีมูลค่าเพิ่ม :</label>
                                                <div class="col-sm-2"><input id="sumTotalCharge2" class="form-control text-right" disabled></div>
                                                <div class="col-sm-2"><input id="sumTotalCharge" class="form-control text-right" disabled></div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-sm-2">ข้อความเพิ่มเติมในใบเสร็จ :</label>
                                                <div class="col-sm-4"><input id="inputAdditionalRemark" class="form-control"></div>
                                                <div class="col-sm-2"></div>
                                                <label class="control-label col-sm-4" id="mainMessageDialogSummary"></label>
                                                <!-- <div class="col-sm-2"></div>
                                                <div class="col-sm-2"></div> -->
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    <!-- </div> -->
                </form>
        </section>
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
                                <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">เลขที่ใบแจ้งค่าบริการ</a></li>
                                <!-- <li role="presentation"><a href="#profiles" id="profile" aria-controls="profiles" role="tab" data-toggle="tab">หมายเลขบริการ</a></li> -->
                                <li role="presentation"><a href="#messages" id="message" aria-controls="messages" role="tab" data-toggle="tab">ข้อมูลลูกค้า</a></li>
                            </ul>
                            <div class="panel panel-default">
                                <div class="panel-body">
                                    <div class="tab-content border-tab-content">
                                        <div role="tabpanel" class="tab-pane active" id="home">
                                            <div class="form-inline col-md-12">
                                                <div class="form-group col-md-12">
                                                    <label>เลขที่ใบแจ้งหนี้ (Doc. Header Text) :</label>
                                                    <div class="input-group col-md-8">
                                                        <input type="text" class="form-control" id="avdDocHead" placeholder="กรุณาป้อนข้อมูลที่ต้องการค้นหาอย่างน้อย 6 ตัวอักษร">
                                                        <span class="input-group-btn"><a id="advSrcDocHeaderBtn" class="btn btn-info"><span class="glyphicon glyphicon-search"></span>ค้นหา</a>
                                                        </span>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <table id="advSrcDocHeaderResultList" data-toggle="table" data-pagination="true" data-page-size="5" data-page-list="[5, 10, 20, 50, 100, 200]"
                                                        data-cache="false">
                                                        <thead>
                                                            <tr>
                                                                <th data-formatter="runningFormatter">#</th>
                                                                <th data-field="arAccountCode">เลขที่ลูกค้า(รหัสลูกหนี้)</th>
                                                                <th data-field="docHead">เลขที่ใบแจ้งหนี้ (Doc. Header Text)</th>
                                                                <th data-field="arName">ชื่อลูกค้า (ชื่อลูกหนี้)</th>
                                                                <th data-field="regionKey1">หน่วยงานติดตามหนี้</th>
                                                                <th data-align="center" data-formatter="SelectButton"></th>
                                                            </tr>
                                                        </thead>
                                                    </table>
                                                </div>
                                            </div>
                                        </div>
                                        <div role="tabpanel" class="tab-pane" id="messages">
                                            <div class="form-horizontal">
                                                <div class="form-group">
                                                    <label class="control-label col-sm-3">เลขที่ลูกค้า(รหัสลูกหนี้):</label>
                                                    <div class="col-sm-3"><input class="form-control" id="avdAccount"></div>
                                                    <!-- <label class="control-label col-sm-3">เลขที่ใบแจ้งหนี้ (Doc. Header Text) :</label>
                                                    <div class="col-sm-3"><input class="form-control" id="avdDocHead"></div> -->
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label col-sm-3">ชื่อลูกค้า (ชื่อลูกหนี้) :</label>
                                                    <div class="col-sm-6"><input class="form-control" id="avdArName"></div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label col-sm-3">หน่วยงานติดตามหนี้ :</label>
                                                    <div class="col-sm-3"><input class="form-control" id="avdRegionKey1"></div>
                                                    <div class="col-sm-6">
                                                        <a id="advSrcSubDetailBtn" class="btn btn-info pull-right"><span class="glyphicon glyphicon-search"></span> ค้นหา</a>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <!-- <label class="control-label col-sm-3">กลุ่มผู้ใช้บริการ :</label>
                                                    <div class="col-sm-6"><select class="form-control" id="advInputCustomerSegment"></select></div> -->
                                                    <!-- <div class="col-sm-3">
                                                        <a id="advSrcCusNoBtn" class="btn btn-info pull-right"><span class="glyphicon glyphicon-search"></span> ค้นหา</a>
                                                    </div> -->
                                                </div>
                                            </div>

                                            <br>

                                            <div class="row">
                                                <div class="col-md-12">
                                                    <table id="advSrcSubDetailResultList" class="table table-hover" data-toggle="table" data-pagination="true" data-page-size="5"
                                                        data-page-list="[5, 10, 20, 50, 100, 200]" data-cache="false">
                                                        <thead>
                                                            <tr>
                                                                <th data-formatter="runningFormatter">#</th>
                                                                <th data-field="arAccountCode">เลขที่ลูกค้า(รหัสลูกหนี้)</th>
                                                                <th data-field="docHead">เลขที่ใบแจ้งหนี้ (Doc. Header Text)</th>
                                                                <th data-field="arName">ชื่อลูกค้า (ชื่อลูกหนี้)</th>
                                                                <th data-field="regionKey1">หน่วยงานติดตามหนี้</th>
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
                        <i class="pull-left"><span class="glyphicon glyphicon-filter"></span>
                            กรุณาป้อนข้อมูลที่ต้องการค้นหาอย่างน้อย 1 อย่าง
                        </i>
                        <br>
                    </div>
                </div>
            </div>
        </div>
        <script id="template" type="x-tmpl-mustache">
            <table class="table table-striped table-bordered" style="width:70% !important">
                <thead>
                    <tr>
                        <th class="text-center">รหัสผลิตภัณฑ์</th>
                        <th class="text-center">ชื่อผลิตภัณฑ์</th>
                        <th class="text-center">ผลิตภัณฑ์ย่อย</th>
                        <th class="text-center">ชื่อผลิตภัณฑ์ย่อย</th>
                        <th class="text-center">ประเภทรายได้</th>
                        <th class="text-center">ชื่อประเภทรายได้</th>
                        <th class="text-center">จำนวนเงินก่อนภาษีมูลค่าเพิ่ม</th>
                    </tr>
                </thead>
                <tbody>
                    {{#invoices}}
                    <tr>
                        <td class="text-center">{{productCode}}</td>
                        <td>{{productName}}</td>
                        <td class="text-center">{{subProductCode}}</td>
                        <td>{{subProductName}}</td>
                        <td>{{revTypeCode}}</td>
                        <td>{{revTypeName}}</td>
                        <td class="text-right">{{amount}}</td>
                    </tr>
                    {{/invoices}}
                </tbody>
            </table>
        </script>
    </body>
    <script type="text/javascript">
        var view = (function ($) {
            var self = this, defaultErrorMessage = "An error occurred but there is no message response.";
            self.session = function (key, val) { if (!val) { var val = window.sessionStorage.getItem(key); return val && (val.indexOf("{") > -1 || val.indexOf("[") > -1) ? JSON.parse(val) : val } window.sessionStorage.setItem(key, ($.type(val) != "object" && $.type(val) != "array" ? val : JSON.stringify(val))) };
            self.utils = {
                guid: function () { function r() { return Math.floor((Math.random() + 1) * 0x10000).toString(16).substring(1) } return r() + r() + "-" + r() + "-" + r() + "-" + r() + "-" + r() + r() + r() },
                numberFormat: function (num, dec) { return $.type(num) !== "number" ? "0.00" : num.toFixed(dec || 2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,'); },
                dateFormat: function () { var obj = arguments[0]; if ($.type(obj) == "string" && /[0-9]{2}.[0-9]{2}.[0-9]{4}/g) { return obj } else if ($.isNumeric(obj)) { var dt = new Date(obj), dd = dt.getDate().toString(), mm = (dt.getMonth() + 1).toString(), yyyy = dt.getFullYear().toString(); return (dd[1] ? "" : "0") + dd + "/" + (mm[1] ? "" : "0") + mm + "/" + yyyy } else if ($.type == "date") { return "" } return "" },
                dateTimeFormat: function () { var obj = arguments[0], dt = $.type(obj) != "date" ? ($.isNumeric(obj) ? new Date(obj) : new Date()) : new Date(), dd = dt.getDate().toString(), MM = (dt.getMonth() + 1).toString(), yyyy = dt.getFullYear().toString(), hh = dt.getHours().toString(), mm = dt.getMinutes().toString(), ss = dt.getSeconds().toString(); return (dd[1] ? "" : "0") + dd + "/" + (MM[1] ? "" : "0") + MM + "/" + yyyy + " " + (hh[1] ? "" : "0") + hh + ":" + (mm[1] ? "" : "0") + mm + ":" + (ss[1] ? "" : "0") + ss },
                queryString: function () { var pos = location.href.indexOf("?"), obj = {}; if (pos < 0) return obj; var params = location.href.slice(pos + 1).split("&"); for (var i = 0, m = params.length; i < m; i++) { pos = params[i].indexOf("="); if (pos < 0) { obj[params[i]] = true; continue; } obj[params[i].substring(0, pos)] = params[i].substring(pos + 1) } return obj },
                window: function (windowName, url, side) { window.windowOpened = (window.windowOpened || {}); var screenProp = ""; if (side == "left") { screenProp = "left=0,top=0,width=" + (screen.width / 2) + ",height=" + (screen.height - 100) } else if (side == "right") { screenProp = "left=" + (screen.width / 2 - 40) + ",top=0,width=" + (screen.width / 2 + 40) + ",height=" + (screen.height - 100) } window.windowOpened[windowName] = window.open(url, windowName, "menubar=no,scrollbars=no,resizable=0," + screenProp, false); return window.windowOpened }
            };
            //// AUTOMATIC REGISTER FORMATTER FUNCTION ////
            window.get = function (url, params, success, msgDialog, preCheck) { $.ajax({ "url": url, "type": "GET", "data": params, "error": (msgDialog || { "valid": function (jqXHR, textStatus, errorThrow) { console.log(jqXHR); console.log("textStatus: " + textStatus); console.log("errorThrow:"); console.log(errorThrow) } }).valid, "success": function (res) { var isNotJson = res.constructor == String; console.log(res); (preCheck || function (o) { })(res); if (isNotJson) res = { "statusCode": "99", "errorList": ["No matching data found."] }; if (msgDialog && !msgDialog.valid(res)) { return; } success(res) } }) }
            window.post = function (url, params, success, msgDialog, preCheck) { $.ajax({ "url": url, "type": "POST", "data": params, "error": (msgDialog || { "valid": function (jqXHR, textStatus, errorThrow) { console.log(jqXHR); console.log("textStatus: " + textStatus); console.log("errorThrow:"); console.log(errorThrow) } }).valid, "success": function (res) { var isNotJson = res.constructor == String; console.log(res); (preCheck || function (o) { })(res); if (isNotJson) res = { "statusCode": "99", "errorList": ["No matching data found."] }; if (msgDialog && !msgDialog.valid(res)) { return; } success(res) } }) }
            window.add = function (num1, num2, dec) { if (!dec) dec = 2; return parseFloat((num1 + num2).toFixed(dec), 10); }; window.subtract = function (num1, num2, dec) { if (!dec) dec = 2; return parseFloat((num1 - num2).toFixed(dec), 10); }; window.multiply = function (num1, num2, dec) { if (!dec) dec = 2; return parseFloat((num1 * num2).toFixed(dec), 10); }; window.divide = function (num1, num2, dec) { if (!dec) dec = 2; return parseFloat((num1 / num2).toFixed(dec), 10); }
            window.find = function (array, propName, obj) { var tmp; for (var i = 0, m = array.length; i < m; i++) { if (array[i][propName] == obj[propName]) tmp = array[i]; } if (tmp == null) { array.push(tmp = obj); } return tmp; }
            window.toMap = function (array, propNames) { var map = {}, prop; function key(obj) { var p = []; for (var i = 0, m = propNames.length; i < m; i++) { p.push(obj[propNames[i]]) } return p.join() }; for (var i = 0, m = array.length; i < m; i++) { prop = key(array[i]); map[prop] = array[i] } return map }
            window.stripToNumber = function (str) { return $.type(str) === "string" && $.isNumeric(str = str.replace(/[^0-9\.]/g, "")) ? parseFloat(str, 10) : 0 }
            window.runningFormatter = function (val, row, ind) { return ind + 1 }
            window.numberFormatter = function (val, row, ind) { return !$.isNumeric(stripToNumber(val)) ? "0.00" : parseFloat(val, 10).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,') }
            window.numberFormatterr = function (val, row, ind) { return !$.isNumeric(stripToNumber(val)) ? "0.0000" : parseFloat(val, 10).toFixed(4).replace(/(\d)(?=(\d{3})+\.)/g, '$1,') }
            window.numberFormatterWithCurrency = function (val, row, ind) {
                var currencyCode = "฿";
                if (row.currencyCode == "1") {
                    currencyCode = "$";
                }
                return !$.isNumeric(stripToNumber(val)) ? "0.00" + currencyCode : parseFloat(val, 10).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,') + currencyCode
            }
            window.numberFormatter2 = function (val, row, ind) {
                return !$.isNumeric(stripToNumber(val)) || val == 0 ? "-" : parseFloat(val, 10).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,')
            }
            window.stringInputFormatter = function (val, row, ind) { return '<input value="' + $.trim(val) + '" maxLength="40" class="form-control">' }
            window.numberInputFormatter = function (val, row, ind) { return '<input value="' + self.utils.numberFormat(parseFloat(val || "0.00", 10)) + '" maxLength="10" style="width:100px" class="form-control text-right" onkeypress="return $.isNumeric(String.fromCharCode(event.which)) || (event.which == 46 && this.value.indexOf(\'.\') == -1)" onfocus="(function(o){ o.value = o.value.replace(/[,]/g, \'\') })(this)" onblur="(function(o){ o.value = parseFloat(o.value, 10).toFixed(2).replace(/(\\d)(?=(\\d{3})+\\.)/g, \'$1,\'); })(this)">' }
            window.moneyInputFormatter = function (val, row, ind) { var value = parseFloat(val || "0.00", 10).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, "$1,"); var currencySymbol = "฿"; if (row != null) { var currencySymbol = chkCurrencySymbol(row.currencyCode); console.log("symbol is >>>>>> " + currencySymbol) }; return '<div class="input-group"><span class="input-group-addon" id="basic-addon1" style="padding:2px 4px;">' + currencySymbol + '</span><input value="' + value + '" id="received_' + ind + '"  maxLength="10" style="width:100px" class="form-control text-right" onkeypress="return $.isNumeric(String.fromCharCode(event.which)) || (event.which == 46 && this.value.indexOf(\'.\') == -1)" onkeyup="if (event.which == 13) this.blur()" onfocus="(function(o){ o.value = o.value.replace(/[,]/g, \'\'); o.select() })(this)" onblur="(function(o){ o.value = parseFloat(o.value, 10).toFixed(2).replace(/(\\d)(?=(\\d{3})+\\.)/g, \'$1,\'); o.style.backgroundColor = o.value !== \'' + value + '\' ? \'yellow\' : \'\'; var table, elem = o; while (table == null) { elem = elem.parentNode; if (elem.tagName == \'TABLE\') table = elem; } (window[table.id +\'InputBlurEvent\'] || function(t){ console.log(t) })(table) })(this)"></div>' }
            window.modifyButtonFormatter = function (val, row, ind) { return '<a class="btn btn-link modList" onclick=\'(function(elem, row, ind){ var table = $("#"+ $(elem).closest("table")[0].id); window[table[0].id +"ModifyEvent"](table, row, ind) })(this, ' + JSON.stringify(row) + ', ' + ind + ')\'><span class="glyphicon glyphicon-pencil"></span></a>' }
            window.removeButtonFormatter = function (val, row, ind) { return '<a class="btn btn-link delList" onclick=\'(function(elem, row, ind){ $("#"+ $(elem).closest("table")[0].id).bootstrapTable("remove", { field: "index", values: [ind] }) })(this, ' + JSON.stringify(row) + ', ' + ind + ')\'><span class="glyphicon glyphicon-trash"></span></a>' }
            window.LoadingPanel = function (id) { var obj = this, loadCnt = 0, setupFunc, loadFunc; function checkElemReady() { if ((obj.elem = $("#" + id).css("font", "")).length != 1) { alert("The element hasn't insert into HTML DOM."); throw "The element hasn't insert into HTML DOM."; } clearTimeout(setupFunc); clearInterval(loadFunc) }; return { "elem": null, "finish": function (html) { checkElemReady(); (this.elem ? this.elem : this.elem = $("#" + id)).css("font", "").html(html); return this }, "toString": function () { var elem; setupFunc = setTimeout(function () { loadFunc = setInterval(function () { (elem || (elem = document.getElementById(id))).innerHTML = "Loading" + Array(++loadCnt).join(".."); if (loadCnt > 60) loadCnt = 2 }, 250) }, 1000); return "<div id='" + id + "' style='font:BOLD 16pt Arial'></div>" } } }
            window.remarkIconFormatter = function (val, row, ind) { if (row.remark != null) { return '<a  style="cursor: pointer" onclick="modalPopupRemark(' + "'" + row.remark + "'" + ')"><span class="glyphicon glyphicon-envelope" aria-hidden="true"></span></a>'; } }
            function Button(el) {
                var obj = this, badge; obj.el = el; obj.elem = $(el);
                obj.hide = function () { this.elem.addClass("hide"); return this }; obj.show = function () { this.elem.removeClass("hide"); return this };
                obj.hideLoad = function () { obj.elem.button("reset"); return this }; obj.showLoad = function () { obj.elem.button("loading"); return this };
                obj.disable = function (flag) { if (flag == null || flag) this.elem.addClass("disabled").attr("disabled", true); else this.elem.removeClass("disabled").attr("disabled", false); return this }; obj.enable = function () { obj.disable(false); return this };
                obj.badge = function (val) { if (badge) badge.text(val) }; if ((badge = this.elem.next()).length == 0) badge = null;
                obj.elem.click(window[el.substring(1) + "ClickEvent"]);
            }
            function Message(el) {
                var obj = this, timeCnt = 0, loadFunc; obj.el = el; obj.elem = $(el);
                obj.hide = function () { obj.elem.addClass("hide"); return obj };
                obj.show = function (flag) { if (flag == null || flag) obj.elem.removeClass("hide"); else obj.hide(); return obj };
                obj.clear = function () { obj.elem.find("*").remove(); obj.hide(); return obj };
                obj.message = function (arr, cls) { $.each(arr, function (i, o) { var m = o; if ($.type(o) === "object") { m = (o.desc || o.messageDesc) + " [code=" + (o.code || o.messageCode) + "]" }; obj.elem.append('<div class="' + cls + '">' + m + '<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button></div>') }); window.scrollTo(0, 0); return obj };
                obj.error = function (arr) { return obj.message(arr, "alert alert-danger") };
                obj.warn = function (arr) { return obj.message(arr, "alert alert-warning") };
                obj.success = function (arr) { return obj.message(arr, "alert alert-success") };
                obj.valid = function (jqXHR, textStatus, errorThrow) { var res = jqXHR; obj.stopLoad(); if (jqXHR && jqXHR.responseJSON) res = jqXHR.responseJSON; if (res) { var isNoData = false; if (res.statusCode && res.statusCode != "0") { obj.warn(res && $.type(res.warningList) === 'array' ? res.warningList : []).error(res && $.type(res.errorList) === 'array' ? res.errorList : ["An error occurred on the server side but there is no error message returned."]).show(); return false } if (res.data && res.statusCode == '0' && res.data.length < 1) isNoData = true; if ($.type(res._embedded) === 'object' && $.map(res._embedded, function (v, k) { return v }).length < 1) isNoData = true; if (isNoData) { return false } return true } obj.error(["An error occurred on the server side but there is no error message returned."]).show(); return false }
                obj.hideLoad = function () { obj.stopLoad().clear(); return this }; obj.stopLoad = function () { clearInterval(loadFunc); return this }
                obj.showLoad = function (msg) { obj.elem.append('<div id="' + obj.el + '-loading" class="alert alert-warning">' + bind(msg, 0) + '</div>'); timeCnt = 0; var elem = document.getElementById(obj.el + "-loading"); loadFunc = setInterval(function () { elem.innerHTML = bind(msg, ++timeCnt) }, 1000); obj.show(); return this }
                function bind(msg, timeCnt) { return msg.replace(/\{timeCnt\}/g, timeCnt) }
            }
            function Panel(el, hide) {
                var obj = this, dura = 500, counter = 0, cntFunc; obj.el = el; obj.elem = $(el); if ($.inArray(obj.elem[0].tagName.toLowerCase(), ["ol", "ul"]) > -1) { obj.elem.removeAttr("id").css("display", "").removeClass("hide").removeClass("hidden"); obj.elem = obj.elem.wrap('<div id="' + el.substring(1) + '"></div>').parent(); } var children = obj.elem.children(), bodyEl = el.substring(1) + '-body', body = obj.elem.append('<div style="' + (hide == null || hide ? "display: none" : "") + '" id="' + bodyEl + '"></div>').find("#" + bodyEl).append(children), progressType, progress = obj.elem.append('<div class="progress" style="display: none"><div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width: 0%"><span class="sr-only">0% Complete</span></div></div>').find(".progress");
                obj.center = function () { obj.elem.removeClass(["text-left", "text-right"]).addClass("text-center"); return this }; obj.right = function () { obj.elem.removeClass(["text-left", "text-center"]).addClass("text-right"); return this }
                obj.hide = function (ms) { if (isHidden()) return this; body.hide(ms || dura); return this }; obj.show = function (ms) { if (!isHidden()) return this; if (!ms || !$.isNumeric(ms)) ms = dura; if (ms >= 0) body.css("display", "none").removeClass("hide").removeClass("hidden").show(ms); else obj.hide(Math.abs(ms)); return this }
                obj.slideDown = function (ms) { if (isHidden()) { body.css("display", "none").removeClass("hide").removeClass("hidden").slideDown(ms || dura, arguments[1] || function () { }) } return this }; obj.slideUp = function (ms) { if (!isHidden()) { body.slideUp(ms || dura, arguments[1] || function () { }) } return this }
                obj.progress = function () {
                    return {
                        "elem": progress, "show": function (ms) { body.slideUp(ms || 10); progress.slideDown(); return this }, "hide": function (ms) { body.slideDown(ms || 10); progress.slideUp(); return this }
                        , "success": function () { return this.style("progress-bar-success") }, "info": function () { return this.style("progress-bar-info") }, "warn": function () { return this.style("progress-bar-warning") }, "error": function () { return this.style("progress-bar-danger") }, "plain": function () { return obj.style("") }, "style": function (clsName) { $(this.elem[0].childNodes[0]).removeClass(progressType).addClass(progressType = clsName); return this }
                        , "percent": function (perc) { if (!$.isNumeric(perc) || (perc > 100 || perc < 0)) return this; this.elem[0].childNodes[0].style.width = perc + "%"; return this }
                        , "timeCnt": function (seconds) { var o = this; this.percent(counter = 0); cntFunc = setInterval(function () { o.percent(100 / seconds * ++counter); if (seconds < counter) { clearInterval(cntFunc); o.hide(1000) } }, 1000); return this }
                    }
                }; function isHidden() { return body.css("display") === "none" || body.hasClass("hide") || body.hasClass("hidden") }; obj.elem.css("display", "").removeClass("hide").removeClass("hidden");
            }
            function CheckBox(el) {
                var obj = this; obj.el = el; obj.elem = $(el); obj.elem.click(window[obj.elem.attr("name") + "ClickEvent"])
                obj.disable = function () { obj.elem.attr("disabled", arguments.length == 0 || (arguments.length == 1 && (arguments[0] == "true" || arguments[0] == true))); return this }
                obj.contains = function (val) { return $.inArray(val, obj.val()) > -1 };
                obj.val = function () { return obj.elem.filter(":checked").map(function (i, o) { return o.value }) };
                obj.unchecked = function () { obj.elem.each(function (i, o) { o.checked = false }); return this }
            }
            function Input(el, maxLen, propPos) {
                var obj = this; obj.el = el; obj.elem = $(el);
                obj.error = function (flag) { if (arguments.length == 0 || flag) obj.elem.parent().addClass("has-error"); else obj.elem.parent().removeClass("has-error"); return this }
                obj.clear = function () { obj.val(""); return this }; obj.isEmpty = function () { return $.trim(obj.val()) === "" }; obj.isNumeric = function () { return $.isNumeric(obj.val()) }
                obj.nextFocus = function (nextElem) { if (nextElem && "Input|Button".indexOf(nextElem.constructor.name) > -1) { obj.elem.keyup(function (e) { var key = (e.which || e.keyCode || e.charCode || 0); if (key == 13) nextElem.elem.focus(); return true }) } return this };
                obj.click = function (func) { obj.elem.click(func); return this }
                obj.readOnly = function (flag) { obj.elem.attr("readonly", (flag == null ? true : flag)); return this }
                obj.disable = function (flag) { obj.elem.attr("disabled", (flag == null ? true : flag)); return this }
                obj.enable = function () { obj.disable(false); return this }
                obj.val = function () { if (arguments.length == 1) { this.elem.val(arguments[0]) } return $.trim(this.elem.val()) }
                obj.get = function (propName) { if ($.type(propPos[propName]) !== "array" || propPos[propName].length !== 2) return ""; return obj.val().substring(propPos[propName][0], propPos[propName][1]) }
                obj.elem.blur(window[el.substring(1) + "BlurEvent"]); obj.elem.keyup(function (e) { var func = (window[el.substring(1) + "KeyUpEvent"] || function () { }); func((e.which || e.keyCode || e.charCode || 0), obj.elem) }); obj.elem.focus(function () { this.select() }); if ($.isNumeric(maxLen)) { obj.elem.attr("maxLength", maxLen) }
            }
            function NumberInput(el, dec) {
                var obj = this, decimal = (dec == null ? 2 : dec); obj.el = el; obj.elem = $(el).addClass("text-right");
                obj.val = function () { if (arguments.length == 0) return parseFloat(strip(this.elem.val() || "0"), 10); this.elem.val(format(arguments[0])); return this }
                obj.decimal = function (dec) { decimal = dec }; obj.format = format;
                obj.disable = function () { obj.elem.attr("disabled", (arguments.length != 1 ? true : arguments[0])); return obj }; obj.enable = function () { obj.disable(false); return obj }; obj.readOnly = function (flag) { obj.elem.attr("readonly", (flag == null ? true : flag)); return this }
                obj.elem.keypress(function (e) { var key = (e.which || e.keyCode || e.charCode || 0); var ch = String.fromCharCode(key); return "0123456789.".indexOf(ch) > -1 });
                obj.elem.focus(function () { this.value = strip(this.value); this.select() });
                obj.elem.blur(function () { this.value = format(this.value) });
                function format(val) { var str = ($.isNumeric(val) ? parseFloat(val, 10) : obj.val()).toFixed(decimal == 0 ? 1 : decimal).replace(/(\d)(?=(\d{3})+\.)/g, '$1,'); return decimal == 0 ? str.substring(0, str.lastIndexOf(".")) : str };
                function strip(str) { return (str || "").replace(/[,]/g, "") }
                obj.elem.blur(window[el.substring(1) + "BlurEvent"]); obj.elem.keyup(function (e) { var key = (e.which || e.keyCode || e.charCode || 0), func = (window[el.substring(1) + "KeyUpEvent"] || function () { }); func(key, obj.elem); if (key == 13) this.blur() }); obj.elem.focus(function () { this.select() })
                if (obj.elem.val() == "") obj.elem.val("0" + (decimal < 1 ? "" : "." + Array(decimal + 1).join("0")));
            }
            function DropDown(el, url) {
                var obj = this, data = [{ key: "", value: "", text: "Please Select" }]; obj.el = el; obj.elem = $(el);
                obj.data = function (array) { if ($.type(array) == "array") { setup(data = array); return this } return data; };
                obj.init = function (url) { if (url) $.get(url, function (res) { setup(data = res.data) }); else setup(data); return this };
                obj.index = function () { if (arguments.length == 1) { var maxLen = data.length, index = arguments[0]; obj.elem.find("option").each(function (i, opt) { opt.removeAttribute("selected") }).eq(index < 0 ? maxLen + index : index).attr("selected", true); return this } return $.inArray("selected", obj.elem.find("option").map(function (i, opt) { return opt.selected ? "selected" : "no-selected" })) }
                obj.selected = function () { return data[obj.index()]; }; obj.val = function () { return obj.elem.val(); }; obj.key = function () { if (arguments.length == 0) { var key = obj.elem.find("option:selected").data("key"); if (typeof key === "string" && key === "undefined") { var item = data[obj.index()]; key = item && item.key ? item.key : item.id } return key } var key = arguments[0]; for (var i = 0, m = data.length; i < m; i++) { var item = data[i]; if (item.key && item.key == key) { obj.index(i); break; } if (item.id && item.id == key) { obj.index(i); break; } } return this }
                function setup(array) { obj.elem.find("*").remove(); $.each(array, function (i, o) { obj.elem.append('<option data-index="' + i + '" data-key="' + o.key + '" value="' + o.value + '">' + (o.text || o.value) + '</option>') }); }
                data = obj.elem.change(window[el.substring(1) + "ChangeEvent"]).find("option").map(function (i, opt) { return { key: opt.getAttribute("data-key"), value: opt.value, text: opt.textContent } });
            }
            function Listbox(el, url) {
                var obj = this, data = []; obj.el = el; obj.elem = $(el);
                obj.data = function (array) { if ($.type(array) == "array") { setup(data = array); return this } return data; };
                obj.init = function (url) { if (url) $.get(url, function (res) { setup(data = res.data) }); else setup(data); return this };
                obj.index = function () { if (arguments.length == 1) { var maxLen = data.length, index = arguments[0]; obj.elem.find("option").each(function (i, opt) { opt.removeAttribute("selected") }).eq(index < 0 ? maxLen + index : index).attr("selected", true); return this } return $.inArray("selected", obj.elem.find("option").map(function (i, opt) { return opt.selected ? "selected" : "no-selected" })) }
                obj.selected = function () { return data[obj.index()]; }; obj.val = function () { return obj.elem.val(); }; obj.key = function () { if (arguments.length == 0) { var key = obj.elem.find("option:selected").data("key"); if (typeof key === "string" && key === "undefined") { var item = data[obj.index()]; key = item && item.key ? item.key : item.id } return key } var key = arguments[0]; for (var i = 0, m = data.length; i < m; i++) { var item = data[i]; if (item.key && item.key == key) { obj.index(i); break; } if (item.id && item.id == key) { obj.index(i); break; } } return this }
                function setup(array) {
                    //array.unshift({ key: "", value: "กรุณาเลือก"});
                    obj.elem.find("*").remove();
                    obj.elem.append('<option data-index="-1" data-key="-1" value="-1">ทั้งหมด</option>');
                    $.each(array, function (i, o) { obj.elem.append('<option data-index="' + i + '" data-key="' + o.key + '" value="' + o.key + '">' + (o.value) + '</option>') });
                }
                data = obj.elem.change(window[el.substring(1) + "ChangeEvent"]).find("option").map(function (i, opt) { return { key: opt.getAttribute("data-key"), value: opt.value, text: opt.textContent } });
            }
            function ListboxBillingGroup(el, url) {
                var obj = this, data = [{ key: "", value: "", text: "กรุณาเลือก" }]; obj.el = el; obj.elem = $(el);
                obj.data = function (array) { if ($.type(array) == "array") { setup(data = array); return this } return data; };
                obj.init = function (url) { if (url) $.get(url, function (res) { setup(data = res.data) }); else setup(data); return this };
                obj.index = function () { if (arguments.length == 1) { var maxLen = data.length, index = arguments[0]; obj.elem.find("option").each(function (i, opt) { opt.removeAttribute("selected") }).eq(index < 0 ? maxLen + index : index).attr("selected", true); return this } return $.inArray("selected", obj.elem.find("option").map(function (i, opt) { return opt.selected ? "selected" : "no-selected" })) }
                obj.selected = function () { return data[obj.index()]; }; obj.val = function () { return obj.elem.val(); }; obj.key = function () { if (arguments.length == 0) { var key = obj.elem.find("option:selected").data("key"); if (typeof key === "string" && key === "undefined") { var item = data[obj.index()]; key = item && item.key ? item.key : item.id } return key } var key = arguments[0]; for (var i = 0, m = data.length; i < m; i++) { var item = data[i]; if (item.key && item.key == key) { obj.index(i); break; } if (item.id && item.id == key) { obj.index(i); break; } } return this }
                function setup(array) {
                    obj.elem.find("*").remove();
                    obj.elem.append('<option data-index="-1" data-key="-1" value="-1">ทั้งหมด</option>');
                    $.each(array, function (i, o) { obj.elem.append('<option data-index="' + i + '" data-key="' + o.key + '" value="' + o.key + '">' + (o.value) + '</option>') });
                }
                data = obj.elem.change(window[el.substring(1) + "ChangeEvent"]).find("option").map(function (i, opt) { return { key: opt.getAttribute("data-key"), value: opt.value, text: opt.textContent } });
            }
            function BootstrapTable(el, options) {
                var obj = this, rawData = [], successFunc = function () { }, checkEvt = function (e) { console.log(e) }, uncheckEvt = checkEvt; obj.el = el; obj.elem = $(el).bootstrapTable(options = $.extend({ uniqueId: "id", responseHandler: function (res) { if (!res) return []; if (res.constructor === Array) return res; if (!res.data || res.data.constructor !== Array) return []; return res.data }, onLoadSuccess: function (res) { rawData = res; obj.resetView(400); successFunc(res) }, onCheck: window[el.substring(1) + "CheckEvent"], onUncheck: window[el.substring(1) + "UncheckEvent"], onCheckAll: window[el.substring(1) + "CheckAllEvent"], onUncheckAll: window[el.substring(1) + "UncheckAllEvent"] }, options));
                obj.clear = function () { obj.elem.bootstrapTable("removeAll"); return obj }; obj.remove = function (index) { obj.elem.bootstrapTable("remove", { field: "index", values: [index] }); return this }; obj.resetView = function (ms) { setTimeout(function () { obj.elem.bootstrapTable("resetView") }, ms || 200); return this }; obj.isEmpty = function () { return obj.data().length == 0 }
                obj.showLoad = function () { this.elem.bootstrapTable("showLoading"); return this }; obj.hideLoad = function () { this.elem.bootstrapTable("hideLoading"); return this };
                obj.update = function (rec) { var id = rec.id, index = rec.index, row; if ((row = obj.getId(id)) != null) { obj.elem.bootstrapTable("updateRow", { "index": row.index, "row": $.extend(row, rec) }) } else if ((row = obj.data()[index]) != null) { obj.elem.bootstrapTable("updateRow", { "index": index, "row": $.extend(row, rec) }) } else obj.insert(rec); return this }; obj.insert = function (row) { var data = obj.elem.bootstrapTable("getData"); obj.elem.bootstrapTable("insertRow", { "index": data.length, "row": $.extend(row, { "index": data.length }) }); return this }
                obj.find = function (field, value) { var data = obj.elem.bootstrapTable("getData"); return $.map(data, function (o, i) { return o[field] === value ? o : null }) }
                obj.map = function (col) { var resultMap = {}; $.each(obj.data(), function (ind, row) { resultMap[row[col]] = row }); return resultMap }
                obj.rawData = function () { if (arguments.length == 1) { this.elem.bootstrapTable("load", rawData = arguments[0]); return this } return $.map(this.elem.bootstrapTable("getData"), function (row) { return row }) }; obj.getSelections = function () { return obj.elem.bootstrapTable("getAllSelections") }
                obj.data = function () { var data = []; obj.elem.find("tbody tr").each(function (i, o) { var row = o, record = []; for (var j = 0, n = row.childNodes.length; j < n; j++) { var col = row.childNodes[j], val = ""; if (col.childNodes.length != 1) val = ""; else if (col.childNodes[0].nodeType == 3) val = $.trim(col.childNodes[0].textContent); else if (col.childNodes[0].nodeName == "INPUT") { var elm = col.childNodes[0], isText = elm.type == "text"; val = $.trim(isText ? elm.value : ((elm.type == "checkbox" || elm.type == "radio") && elm.checked ? elm.value : "")) } else if (col.childNodes[0].nodeName == "DIV") val = $.trim(col.childNodes[0].childNodes[0].value); record.push(val) } data.push(record) }); return data };
                obj.selected = function () { var data = []; obj.elem.find("tbody tr").find("input[type=checkbox]:checked, input[type=radio]:checked").each(function (i, o) { var row = o.parentNode.parentNode, record = []; for (var j = 0, n = row.childNodes.length; j < n; j++) { var col = row.childNodes[j], val = ""; if (col.childNodes.length != 1) val = ""; else if (col.childNodes[0].nodeType == 3) val = $.trim(col.childNodes[0].textContent); else if (col.childNodes[0].nodeName == "INPUT") val = $.trim(col.childNodes[0].value); else if (col.childNodes[0].nodeName == "DIV") val = $.trim(col.childNodes[0].childNodes[0].value); record.push(val) } data.push(record) }); return data };
                obj.getId = function (id) { var o = obj.elem.bootstrapTable("getRowByUniqueId", id); if ($.type(o) == "array") return null; return o[options.uniqueId] == id ? o : null }; obj.delId = function (id) { obj.elem.bootstrapTable("removeByUniqueId", id); return this }; // options: { uniqueId: "fieldName" }
                obj.filter = function (func) { var filteredRow, filteredData = []; for (var i = 0, m = rawData.length; i < m; i++) { if (filteredRow = func(rawData[i])) filteredData.push(filteredRow) } obj.elem.bootstrapTable("load", filteredData); return this }
                obj.sum = function (isAll, colName) { var sum = 0; $.each(this.elem.bootstrapTable(isAll ? "getData" : "getSelections"), function (i, o) { sum += (o[colName] || 0) }); return sum };
                obj.sumInput = function (index) { var sum = 0; obj.elem.find("tbody tr").each(function (i, o) { var val = o.children[index].children[0].value.replace(/[,]/g, ""); sum += (!$.isNumeric(val) ? 0 : parseFloat(val, 10)) }); return sum }
                obj.http = function (url, urlParams, succFunc) { if (succFunc) successFunc = succFunc; obj.elem.bootstrapTable("refresh", { "url": url, "query": urlParams }); return this }
                obj.expand = function () { obj.elem.find(".detail-icon i.icon-plus").click(); return this }; obj.collapse = function () { obj.elem.find(".detail-icon i.icon-minus").click(); return this };
                obj.checkAll = function () { var checked = arguments.length == 0 || arguments[0] == true || ($.type(arguments[0]) == "boolean" ? arguments[0] : arguments[0] == "true"), data = obj.elem.bootstrapTable("getData"); $.each(obj.elem.bootstrapTable("getOptions").columns[0], function (i, col) { if (col.checkbox) $.each(data, function (j, row) { row[col.field] = checked }) }); return this }; obj.uncheckAll = function () { obj.checkAll(false); return this }
                obj.elem.on("check.bs.table uncheck.bs.table check-all.bs.table uncheck-all.bs.table", window[el.substring(1) + "CheckBoxEvent"])
            }
            function AuthenticationDialog(succFunc, failFunc, url, title, usrLabel, pwdLabel, okBtn, cnBtn) {
                var obj = this, done = succFunc, fail = failFunc, cancel = function () { }; obj.el = "modal_authentication";
                var content = '<div class="row"><div class="col-md-12"><div class="col-md-offset-2 col-md-10"><div class="form-horizontal"><div class="form-group"><label class="col-sm-3 control-label">' + (usrLabel || "User Name :") + '</label><div class="col-sm-6"><input type="text" id="discApprUser" class="form-control"></div></div><div class="form-group"><label class="col-sm-3 control-label">' + (pwdLabel || "Password :") + '</label><div class="col-sm-6"><input type="password" class="form-control"></div></div></div></div></div></div>';
                var hdrCS = '<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>';
                var hdrTT = '<h4 class="modal-title"><span class="glyphicon glyphicon-user"></span> ' + (title || "Authentication") + '</h4>';
                var divMG = '<div class="alert alert-danger hide"></div>';
                var btnOK = '<button type="button" class="btn btn-success"><span class="glyphicon glyphicon-ok-circle"></span> ' + (okBtn || "OK") + '</button>';
                var btnCN = '<button type="button" class="btn btn-danger" data-dismiss="modal"><span class="glyphicon glyphicon-remove-circle"></span> ' + (cnBtn || "Cancel") + '</button>';
                obj.elem = $(document.body).append('<div class="modal fade" role="dialog" data-backdrop="static" id="' + obj.el + '"><div class="modal-dialog"><div class="modal-content"><div class="modal-header">' + hdrCS + hdrTT + '</div><div class="modal-body">' + divMG + content + '</div><div class="modal-footer">' + btnOK + btnCN + '</div></div></div></div>').find("#" + obj.el);
                obj.elem.find("button").eq(1).on({ click: function () { var inputs = obj.elem.find("input"); $.post(url, { username: inputs.eq(0).val(), password: inputs.eq(1).val() }, function (res) { if (!res || res.statusCode != "0") { obj.elem.find(".alert").text("You don't have permission to access this feature. Please contact to Administration for more information.").removeClass("hide"); fail(res); return; } done(res); obj.hide() }) } }).end().filter(function (index) { return index == 0 || index == 2 }).on({ click: function () { cancel() } })
                obj.show = function () { obj.elem.modal("show"); obj.elem.find("div.alert").addClass("hide").text(""); return this }; obj.hide = function () { obj.elem.modal("hide"); return this }
                obj.done = function (func) { done = func; return this }; obj.fail = function (func) { fail = func; return this }; obj.cancel = function (func) { cancel = func; return this }
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
            self.button = new (function () {
                var that = this;
                that.search = new Button("#search");
                that.advanceSearch = new Button("#advanceSearch");
                that.advSrcDocHeaderBtn = new Button("#advSrcDocHeaderBtn");
                that.advSrcSvcNoBtn = new Button("#advSrcSvcNoBtn");
                that.advSrcSubDetailBtn = new Button("#advSrcSubDetailBtn");
                that.addPayment = new Button("#addPayment");
                that.summaryPayment = new Button("#summaryPayment");
                that.submitPayment = new Button("#submitPayment");
            });
            self.dialog = new (function () {
                var that = this;
                that.customerSearch = new Modal("#customerSearch");
                that.navigatePanel = new Panel("#navigatePanel");
                that.bouncePanel = new Panel("#bouncePanel");
                that.customerARPanel = new Panel("#customerARPanel");
                // that.customerPanel = new Panel("#customerPanel");
                // that.detailsPanel = new Panel("#detailsPanel");
                that.summaryPanel = new Panel("#summaryPanel");
                that.mainMessageDialog = new Message("#mainMessageDialog");
                that.mainMessageDialogSummary = new Message("#mainMessageDialogSummary");
                that.advanceSearchMessageDialog = new Message("#advanceSearchMessageDialog");
                that.billingGroupMessageDialog = new Message("#billingGroupMessageDialog");
                that.remarkModal = new Modal("#remarkModal", true);
                function Modal(el) {
                    this.el = el;
                    this.elem = $(el);
                    this.hide = function () { this.elem.modal("hide") };
                    this.show = function () { this.elem.modal("show") };
                }
            });
            self.checkbox = new (function () {
                var that = this;
                that.advSrcSvcProperty = new CheckBox("[name=advSrcSvcProperty]");
                that.splitReceiptDocument = new CheckBox("#splitReceiptDocument");
                that.receiptDocumentOnly = new CheckBox("#receiptDocumentOnly");
                that.receiptInvoiceDocumentOnly = new CheckBox("#receiptInvoiceDocumentOnly");
            });
            self.input = new (function () {
                var that = this;
                that.arAccountCode = new Input("#arAccountCode");
                that.arName = new Input("#arName");
                that.taxId = new Input("#taxId");
                that.glAccount = new Input("#glAccount");
                that.arGroup = new Input("#arGroup");
                that.regionKey1 = new Input("#regionKey1");
                that.branchAR = new Input("#branchAR");
                that.address = new Input("#address");

                that.advSrcBillNo = new Input("#advSrcBillNo");
//                 that.advSrcSvcType = new Listbox("#advSrcSvcType").init("../findAllPrintingInvDisplay.json");
                that.advSrcSvcNo = new Input("#advSrcSvcNo");
                that.advSrcCusNo = new Input("#advSrcCusNo");
                that.advSrcCusTaxId = new Input("#advSrcCusTaxId");
                that.advSrcCusFirstName = new Input("#advSrcCusFirstName");
                that.advSrcCusLastName = new Input("#advSrcCusLastName");
                that.advSrcOrgName = new Input("#advSrcOrgName");
                that.billNo = new Input("#billNo", 18);
                that.barcode = new Input("#barcode", 62, { "billNo": [16, 34], "invoiceNo": [34, 52] });
                that.customerNo = new Input("#customerNo");
                that.egpNo = new Input("#egpNo");
                that.egpContract = new Input("#egpContract");
                that.customerName = new Input("#customerName");
                that.taxId = new Input("#taxId");
                that.branch = new Input("#branch");
                that.billingGroup = new Input("#billingGroup");
                that.changeReceiptAddress = new Input("#changeReceiptAddress");
                that.receiptAddress = new Input("#receiptAddress");
                that.invoiceAddress = new Input("#invoiceAddress");
                that.balance = new NumberInput("#balance");
                that.discountAmount = new NumberInput("#discountAmount");
                that.beforeDiscountAmount = new NumberInput("#beforeDiscountAmount");
                that.vat = new NumberInput("#vat");
                that.preDiscount = new NumberInput("#preDiscount");
                that.totalCharge = new NumberInput("#totalCharge");
                that.sumBalance = new NumberInput("#sumBalance");
                that.sumBalance2 = new NumberInput("#sumBalance2");
                that.sumTotalCharge = new NumberInput("#sumTotalCharge");
                that.sumTotalCharge2 = new NumberInput("#sumTotalCharge2");
                that.sumVat = new NumberInput("#sumVat");
                that.sumVat2 = new NumberInput("#sumVat2");
                that.deduct = new NumberInput("#deduct");
                that.val = function () { if (arguments.length == 1) { $.each(arguments[0], function (k, v) { $("#" + k).val(v) }) } }
            });
            self.tab = new (function () {
                var that = this;
                that.customerInfoTab = new Tab("#customerInfoTab");
                that.addressTab = new Tab("#addressTab");
                that.invoiceDetailsTab = new Tab("#invoiceDetailsTab");
                function Tab(el) {
                    var obj = this, index = 0, change = function (e) { console.log(e) }, initEvts = [];
                    obj.el = el;
                    obj.elem = $(el);
                    obj.index = function () { if (arguments.length == 1) { index = arguments[0]; obj.elem.find("li").removeClass("active").find("a").eq(index).click(); return this } return index }
                    obj.show = function (ind) { obj.elem.find("a").eq(ind).tab("show"); return this; }
                    obj.change = function (func) { change = func; return this }
                    obj.init = function (ind, evt) { initEvts[ind] = evt; return obj }
                    obj.elem.find("a").each(function (i, o) { $(o).data("index", i) });
                    obj.elem.find("li").each(function (i, o) { if ($(o).hasClass("active")) index = i; initEvts.push(function () { console.log("index: " + i) }) });
                    obj.elem.on("show.bs.tab", function (e) { index = $(e.target).data("index"); change(e); initEvts[index]() });
                }
            });
            self.radio = new (function () {
                var that = this;
                that.discountCheckbox = new Radio("[name=discountCheckbox]");
            });
            self.table = new (function () {
                var that = this;
                that.advSrcBillNoResultList = new BootstrapTable("#advSrcBillNoResultList");
                that.advSrcDocHeaderResultList = new BootstrapTable("#advSrcDocHeaderResultList");
                that.advSrcSubDetailResultList = new BootstrapTable("#advSrcSubDetailResultList");
                that.invoiceList = new BootstrapTable("#invoiceList");
                that.historyList = new BootstrapTable("#historyList");
                function Table(el) {
                    var obj = this, headers = [], data = [], onApn = function () { }, onDel = onApn, loadCnt = 0, loadInt, loadFunc = function () { obj.body.find("div#loading").html("Loading" + Array(++loadCnt).join(".")); if (loadCnt > 8) loadCnt = 0; };
                    var checkboxHtml = '<input type="checkbox" name="{field}" data-index="{index}" value="{value}">', radioHtml = '<input type="radio" name="{field}" data-index="{index}" value="{value}">';
                    obj.el = el; obj.elem = $(el); obj.body = obj.elem.find("tbody");
                    obj.onAppend = function (func) { onApn = func }; obj.onDelete = function (func) { onDel = func };
                    obj.hideLoad = function () { obj.elem.find("tbody tr").remove(); clearInterval(loadInt); return obj };
                    obj.showLoad = function () { obj.elem.find("tbody").append("<tr><td colspan='" + headers.length + "'><div id='loading' style='text-align:center;font:BOLD 16pt Arial'>Loading</div></td></tr>"); loadCnt = 0; loadInt = setInterval(loadFunc, 500); return obj };
                    function reorder(index) { obj.body.find("tr").each(function (i, o) { $(o).find("td").eq(index).text(i + 1) }) }
                    obj.insert = function (array, showRemove, attrs) {
                        if (arguments.length < 1) array = $.map(headers, function (o) { return " " }); var b = obj.elem.find("tbody");
                        b.append("<tr " + $.map($.extend(attrs, {}), function (v, k) { return k + "='" + v + "'" }).join(" ") + ">"
                            + $.map(array, function (v, i) {
                                var field = headers[i].field, value = v;
                                if (headers[i].runningNo) value = (obj.size() + 1);
                                else if (headers[i].numberFormat) { value = !$.isNumeric(v) ? "0.00" : parseFloat(v, 10).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,'); }
                                else if (headers[i].checkbox) value = replace(checkboxHtml, field, i, v);
                                else if (headers[i].radio) value = replace(radioHtml, field, i, v);
                                return '<td><div style="' + (headers[i].align ? "text-align:" + headers[i].align : "") + '">' + value + '</div></td>'
                            }).join("")
                            + (!showRemove ? "" : '<td style="width:40px;text-align:center"><a href="#" class="delList"><span class="glyphicon glyphicon-trash"></span></a></td>')
                            + "</tr>"); return obj
                    };
                    obj.find = function (key, cri) { return obj.elem.find("tbody tr").filter("[" + key + "=" + cri + "]") }
                    obj.clear = function () { obj.elem.find("tbody tr").remove(); return obj }
                    obj.remove = function (index) { this.elem.find("tbody tr").eq(index).remove(); $.each(headers, function (i, o) { if (o.runningNo) reorder(i) }); }
                    obj.data = function () {
                        var data = [];
                        if (arguments.length != 1) {
                            var rows = obj.elem.find("tbody tr");
                            for (var i = 0, m = rows.length; i < m; i++) { var row = []; for (var j = 0, n = rows[i].children.length; j < n; j++) row.push(rows[i].children[j].innerText); data.push(row); }
                            return data;
                        } for (var i = 0, m = (data = arguments[0]).length; i < m; i++) { obj.insert(data[i]); }
                    };
                    obj.size = function () { return obj.elem.find("tbody tr").length };
                    obj.sum = function (index) { var sum = 0; this.elem.find("tbody tr").each(function (i, row) { var val = $(row).find("td").eq(index).text(); sum += (isNaN(val) ? 0 : parseFloat(val, 10)) }); return sum }
                    $(obj.el).on("click", ".delList", function () { $(this).parent().parent().remove(); reorder(); onDel(obj.data()) });
                    obj.elem.find("thead th").each(function (i, o) {
                        var elem = $(o);
                        headers.push({ "field": elem.data("field"), "align": $.trim(elem.data("align")), "runningNo": elem.data("runningNo") === true, "numberFormat": elem.data("numberFormat") === true, "checkbox": elem.data("checkbox") === true, "radio": elem.data("radio") === true })
                    }); function replace(str, field, index, value) { return str.replace("\{field\}", $.trim(field)).replace("\{index\}", index).replace("\{value\}", value) }
                    if (obj.body.length < 1) { obj.elem.append("<tbody></tbody>"); obj.body = obj.elem.find("tbody") }
                }
            });
            self.contextPath = '${pageContext.request.contextPath}/';
            self.acctCatLkp = new Input("#acctCatLkp");
            self.customerType = new Input("#customerType");
            self.catCustomerSegment = new Input("#catCustomerSegment");
            self.custInfoInputAccruedAmount = new NumberInput("#custInfoInputAccruedAmount");
            self.custInfoInputAdvancedAmount = new NumberInput("#custInfoInputAdvancedAmount");
            self.custInfoInputStatus = new Input("#custInfoInputStatus");
            self.custInfoInputCurrencyCode = new Input("#custInfoInputCurrencyCode");
            self.custInfoInputVatRate = new Input("#custInfoInputVatRate");
            self.advancedCheckbox = new CheckBox("[name=advancedCheckbox]");
            self.changeReceiptAddress = new CheckBox("[name=changeReceiptAddress]");
            self.advancedAmount = new NumberInput("#advancedAmount").disable();
            self.billCycle = new Input("#billCycle").disable();
            self.checkboxAdditionalDiscount = new CheckBox("#checkboxAdditionalDiscount");
            self.inputAdditionalDiscount = new NumberInput("#inputAdditionalDiscount");
            self.inputCollectionUnit = new Input("#inputCollectionUnit");
            self.advanceInvSelected = null;

            self.inputAdditionalRemark = new Input("#inputAdditionalRemark");
            this.dialogAuthentication = new AuthenticationDialog(function (res) { console.log(res); }, function (res) { }, "../checkAuthorize.json");

            return this;
        })(jQuery);

        $('.cusPanel').hide();
        $('.navPanel').hide();
        // view.dialog.navigatePanel.hide();
        var detailARCustomerDTO;
        var payBounceChequeDTOList;
        var subPayBounceChequeDTOList;
        var isChequeNextList;
        var currencyCode;
        var vatRDGet;
        var rateChangeRDGet;


        function addPaymentClickEvent() {
            var isPrepaid = view.advancedCheckbox.contains("on");

            if(isPrepaid) {
                addPaymentAdvv();
            }else{ 
                view.session("bounceCheque", bounceChequeList());
                var s = view.session("bounceCheque");
                console.log(s);
                console.log(view.session("bounceCheque"));
                console.log("Test addpaymebt + ::: 55555555555 ::: +");
                location.href = 'pay-bounceCheque2.jsp'; 
            }

        }

        function addPaymentAdvv() {
            var table = $("#invoiceList");
            var invoiceList = false;
            var data = table.bootstrapTable("getData");

            for (var rowid = 0; rowid < data.length; rowid++) {
                var checked = data[rowid]["checked"];
                if (checked) {invoiceList = true;}else{invoiceList=false;}
            }

            if(invoiceList) {
                view.session("bounceCheque", bounceChequeList());
                var s = view.session("bounceCheque");
                console.log(s);
                console.log(view.session("bounceCheque"));
                console.log("Test addpaymebt + ::: 55555555555 ::: +");
                location.href = 'pay-bounceCheque2.jsp';
            }else{view.dialog.mainMessageDialog.clear().error(["เอาให้ครบก่อนสิ"]).show(); }

        }

        function advanceSearchClickEvent() {
            $("#avdDocHead").val("");
            var clearDT = [];
            view.table.advSrcDocHeaderResultList.rawData(clearDT);

            view.dialog.advanceSearchMessageDialog.clear();
            view.dialog.customerSearch.show();
        }

        var chkAdvSearchHeader = false;
        var advSearchChk;

        function advSrcDocHeaderBtnClickEvent() {
            view.dialog.advanceSearchMessageDialog.clear();
            
            console.log("555555555555555555555555" + "   advSrcDocHeaderBtn");

            var avdDocHead = $("#avdDocHead").val();
            var dataSend = {
                "avdDocHead": avdDocHead,
                "accountNo": "",
                "cusName": "",
                "avdRegionKey1": "",
                "chk":true
            };
            if (avdDocHead == "") {
                view.dialog.advanceSearchMessageDialog.error(["ใส่มาก่อนสิ"]).show(); return;
            } else {
                view.dialog.advanceSearchMessageDialog.showLoad("ระบบกำลังรอข้อมูลจาก Sap : {timeCnt} วินาที");

                $.ajax({
                    type: "GET",
                    url: "../avdSearchPayBounceCheque",
                    data: dataSend,
                    dataType: "json",
                    contentType: "application/json; charset=utf-8",
                    success: function (res) {
//                         console.log("success :: success");
//                         console.log(res.detailARCustomerDTO.arName);

                        advSearchChk = res;
                        view.table.advSrcDocHeaderResultList.rawData(res.payBounceChequeDTOList);
                        view.dialog.advanceSearchMessageDialog.hideLoad();
                    }
                });
            }
            chkAdvSearchHeader = true;
        }

        function advSrcSubDetailBtnClickEvent() {
            view.dialog.advanceSearchMessageDialog.clear();

            var accountNo = $("#avdAccount").val();
            var cusName = $("#avdArName").val();
            var avdRegionKey1 = $("#avdRegionKey1").val();

            var dataSend = {
                "avdDocHead": "",
                "accountNo": accountNo,
                "cusName": cusName,
                "avdRegionKey1": avdRegionKey1,
                "chk":false
            };

            if ($("#avdAccount").val() == "" && $("#avdArName").val() == "" && $("#avdRegionKey1").val() == "") {
                view.dialog.advanceSearchMessageDialog.error(["ไส่มาก่อนสิ"]).show(); return;
            }else{
                view.dialog.advanceSearchMessageDialog.clear().showLoad("ระบบกำลังรอข้อมูลจาก Test : {timeCnt} วินาที");

                $.ajax({
                    type: "GET",
                    url: "../avdSearchPayBounceCheque",
                    data: dataSend,
                    dataType: "json",
                    contentType: "application/json; charset=utf-8",
                    success: function (res) {
//                     	setdataSearch(res);
                        advSearchChk = res;
                        view.table.advSrcSubDetailResultList.rawData(res.payBounceChequeDTOList);
                        view.dialog.advanceSearchMessageDialog.hideLoad();

                    }
                });
            }

            chkAdvSearchHeader = false;
        }

        function SelectButton(val, row, ind) {
            return "<a class='btn btn-success btn-xs' onclick='BillSelectButtonWithParamEvent(" + row.docHead + ")'>เลือก</a>";
        }

        function SelectButton2(val, row, ind) {
            var docHead = ""; 
            return "<a class='btn btn-success btn-xs' onclick='BillSelectButtonWithParamEvent(" + docHead +"," + row.avdAccount +","+ row.cusName +","+ row.avdRegionKey1 +")'>เลือก</a>";
        }

        function BillSelectButtonWithParamEvent(docHead) {
            if (docHead == "") {
            } else {
                for (var i = 0; i < advSearchChk.payBounceChequeDTOList.length; i++) {
                    if (advSearchChk.payBounceChequeDTOList[i].docHead == docHead) {

                        payBounceChequeDTOList = advSearchChk.payBounceChequeDTOList[i];
                        var showList = [];
                        showList.push(advSearchChk.payBounceChequeDTOList[i]);
                        setCusDetail(advSearchChk.detailARCustomerDTO);
                        var invoiceData =[];
                        if (showList.length > 0) {
                            invoiceData = $.map(showList, invoiceListMapper);
                            console.log(invoiceData);
                        }
                        
                        
//                         view.table.invoiceList.rawData(showList);
                        view.table.invoiceList.rawData(invoiceData);
                        view.table.historyList.rawData(advSearchChk.historyARDTOList);

                        // var payBounceChequeDTOLists = [];
                        // for (var i = 0; i < payBounceChequeDTOList.length; i++) {
                        //     payBounceChequeDTOList[i].amountARin = numberFormatterr(payBounceChequeDTOList[i].amountARin);
                        //     payBounceChequeDTOLists.push(payBounceChequeDTOList[i]);
                        // }

                        selectZap();
                        view.dialog.customerSearch.hide();
                        
                        $.ajax({url: "../exchangeRateList.json", type: "GET", data: {}, async: false,
                            success: function (res) {
                                dataM = res.data;
                            }, error: function () { console.log("error"); }
                        });
                        for(var i=0; i<dataM.length; i++){
                            if(dataM[i].message==advSearchChk.payBounceChequeDTOList[0].currency){dateRateRD = dataM[i].updateDttm; dateRateRD = dateRateRD.substring(8,10)+"/"+dateRateRD.substring(5,7)+"/"+dateRateRD.substring(0,4); rateChangeRD = dataM[i].rateUnit;}
                        }
                        $('.cusPanel').show();
                        $('.navPanel').show();
                        view.dialog.mainMessageDialog.clear();
                        view.dialog.mainMessageDialogSummary.clear();
                        rateChangeRDGet = rateChangeRD;
                      		 view.dialog.mainMessageDialogSummary.clear().warn(["อัตราการแลกเปลี่ยน ณ วันที่ : "+dateRateRD +" = "+rateChangeRD]).show();
	                        $("#customerARPanel").show();
	                        $("#bouncePanel").show();
	                        $("#summaryPanel").show();
	                        $("#navigatePanel").show();
                    }
                }
                console.log(docHead + " " + docHead + " " + docHead + " = docHead");
            }
        }

        function selectZap() {
            view.button.addPayment.disable();
            view.button.summaryPayment.disable();
            view.button.submitPayment.disable();

            view.button.summaryPayment.badge(9);

            view.dialog.customerARPanel.show(900);
            view.dialog.bouncePanel.show(1000);
            view.dialog.summaryPanel.show(1200);
            view.dialog.navigatePanel.slideDown();
            // view.dialog.customerSearch.show();
        }

        function searchBounceCheque() {
            $('.cusPanel').hide();
            $('.navPanel').hide();
            var bounceChequeDTO = { account: $("#account").val(), docHead: $("#docHead").val() };
            var chk = true;
            // var chk = chkNullpointInput();
            view.dialog.mainMessageDialog.clear();
            view.dialog.mainMessageDialogSummary.clear();
            if ($("#account").val().length == 0 && $("#docHead").val().length == 0) {
                view.dialog.mainMessageDialog.error(["โปรดระบุ 'เลขที่ลูกค้า'"]).show();
                chk = false;
                return false;
            }
            view.input.sumBalance2.val(0.00);
            view.input.sumBalance.val(0.00);
            view.input.sumVat2.val(0.00);
            view.input.sumVat.val(0.00);
            view.input.sumTotalCharge2.val(0.00);
            view.input.sumTotalCharge.val(0.00);
            var dataM = [];
            var rateChangeRD;
            var dateRateRD;
            view.dialog.mainMessageDialog.clear().showLoad("ระบบกำลังรอข้อมูล SAP: {timeCnt} วินาที")
            $.ajax({url: "../exchangeRateList.json", type: "GET", data: {}, async: false,
                    success: function (res) {
                        dataM = res.data;
                    }, error: function () { console.log("error"); }
                });
            
            if (chk) {
//                 if (bounceChequeDTO.docHead != "") { commend by got 21/01/2018
//                     bounceChequeDTO.account = "";
//                 }
			console.log("bounceChequeDTO");
			console.log(bounceChequeDTO);
                $.ajax({
                    type: "POST",
                    url: "../searchBounceCheque",
                    data: JSON.stringify(bounceChequeDTO),
                    dataType: "json",
                    async: false,
                    contentType: "application/json; charset=utf-8",
                    success: function (res) {
                        if(res.detailARCustomerDTO!=null && res.payBounceChequeDTOList.length >0){
                        payBounceChequeDTOList = res.payBounceChequeDTOList;
                        // subPayBounceChequeDTOList = payBounceChequeDTOList.subPayBounceChequeDTOList;
                        vatRDGet = res.vatRD;
                        setCusDetail(res.detailARCustomerDTO);
                        var invoiceData = [];
                        var historyData = [];
                        if (res.payBounceChequeDTOList.length > 0) {
							invoiceData = res.data;
                            invoiceData = $.map(res.payBounceChequeDTOList, invoiceListMapper);
                            console.log(invoiceData);
                        }
                        if (res.historyARDTOList.length > 0) {
                        	historyData = res.data;
                        	historyData = $.map(res.historyARDTOList, historyListMapper);
                            console.log(historyData);
                        }
//                         view.table.invoiceList.rawData(res.payBounceChequeDTOList);
// view.table.historyList.rawData(res.historyARDTOList);
						view.table.invoiceList.rawData(invoiceData);
                        view.table.historyList.rawData(historyData);

                        for(var i=0; i<dataM.length; i++){
                            if(dataM[i].message==res.payBounceChequeDTOList[0].currency){dateRateRD = dataM[i].updateDttm; dateRateRD = dateRateRD.substring(8,10)+"/"+dateRateRD.substring(5,7)+"/"+dateRateRD.substring(0,4); rateChangeRD = dataM[i].rateUnit;}
                        }
                        // var payBounceChequeDTOLists = [];
                        // for (var i = 0; i < payBounceChequeDTOList.length; i++) {
                        //     payBounceChequeDTOList[i].amountARin = numberFormatterr(payBounceChequeDTOList[i].amountARin);
                        //     payBounceChequeDTOLists.push(payBounceChequeDTOList[i]);
                        // }

                        selectZap();
                        var summaryList = (view.session("bounceCheque") || []).length;
                        view.button.summaryPayment.disable(summaryList == 0).badge(summaryList);
                        view.button.submitPayment.disable(summaryList == 0);
                        $('.cusPanel').show();
                        $('.navPanel').show();
                        rateChangeRDGet = rateChangeRD;
                        view.dialog.mainMessageDialogSummary.clear().warn(["อัตราการแลกเปลี่ยน ณ วันที่ : "+dateRateRD +" = "+rateChangeRD]).show();
                        // view.dialog.mainMessageDialog.clear();
	                        $("#customerARPanel").show();
	                        $("#bouncePanel").show();
	                        $("#summaryPanel").show();
	                        $("#navigatePanel").show();
                        }else {
                        	 $('.cusPanel').hide();
                             $('.navPanel').hide();
                             $("#customerARPanel").hide();
                             $("#bouncePanel").hide();
                             $("#summaryPanel").hide();
                             $("#navigatePanel").hide();
                        	view.dialog.mainMessageDialog.clear().error(["ไม่พบข้อมูล"]).show();
                        }
                        // if(res==null){view.dialog.mainMessageDialog.clear().error(["ไม่พบข้อมูล"]).show();}
                        view.dialog.mainMessageDialog.stopLoad();
                    }, error: function () { view.dialog.mainMessageDialog.clear().error(["ไม่พบข้อมูล"]).show(); }
                });

            }

        }
		function setdataSearch(res){
			if(res.detailARCustomerDTO!=null && res.payBounceChequeDTOList.length >0){
                payBounceChequeDTOList = res.payBounceChequeDTOList;
                vatRDGet = res.vatRD;
                setCusDetail(res.detailARCustomerDTO);
                var invoiceData = [];
                var historyData = [];
                if (res.payBounceChequeDTOList.length > 0) {
					invoiceData = res.data;
                    invoiceData = $.map(res.payBounceChequeDTOList, invoiceListMapper);
                    console.log(invoiceData);
                }
                if (res.historyARDTOList.length > 0) {
                	historyData = res.data;
                	historyData = $.map(res.historyARDTOList, historyListMapper);
                    console.log(historyData);
                }

				view.table.invoiceList.rawData(invoiceData);
                view.table.historyList.rawData(historyData);

                for(var i=0; i<dataM.length; i++){
                    if(dataM[i].message==res.payBounceChequeDTOList[0].currency){dateRateRD = dataM[i].updateDttm; dateRateRD = dateRateRD.substring(8,10)+"/"+dateRateRD.substring(5,7)+"/"+dateRateRD.substring(0,4); rateChangeRD = dataM[i].rateUnit;}
                }
                selectZap();
                var summaryList = (view.session("bounceCheque") || []).length;
                view.button.summaryPayment.disable(summaryList == 0).badge(summaryList);
                view.button.submitPayment.disable(summaryList == 0);
                $('.cusPanel').show();
                $('.navPanel').show();
                rateChangeRDGet = rateChangeRD;
                view.dialog.mainMessageDialogSummary.clear().warn(["อัตราการแลกเปลี่ยน ณ วันที่ : "+dateRateRD +" = "+rateChangeRD]).show();
                    $("#customerARPanel").show();
                    $("#bouncePanel").show();
                    $("#summaryPanel").show();
                    $("#navigatePanel").show();
                }else {
                	 $('.cusPanel').hide();
                     $('.navPanel').hide();
                     $("#customerARPanel").hide();
                     $("#bouncePanel").hide();
                     $("#summaryPanel").hide();
                     $("#navigatePanel").hide();
                	view.dialog.mainMessageDialog.clear().error(["ไม่พบข้อมูล"]).show();
                }
                view.dialog.mainMessageDialog.stopLoad();
		}
        
        
        
        function setCusDetail(detailARCustomerDTO) {
            $('#arAccountCode').val(detailARCustomerDTO.arAccountCode);
            $('#arName').val(detailARCustomerDTO.arName);
            $('#taxId').val(detailARCustomerDTO.taxId);
            $('#glAccount').val(detailARCustomerDTO.glAccount);

            $('#arGroup').append($('<option>', {
                value: 1,
                text: detailARCustomerDTO.arGroup
            }));

            $('#searchBounceCheque').val("");
            $('#regionKey1').val(detailARCustomerDTO.regionKey1);
            $('#branchAR').val(detailARCustomerDTO.branchAR);
            $('#address').val(detailARCustomerDTO.address);

        }

        function tablePayBounceCheque() {
            for (var i = 0; i < payBounceChequeDTOList.length; i++) {
                console.log(payBounceChequeDTOList[i].message);
            }
            return payBounceChequeDTOList;
        }

        function invoiceListInputBlurEvent() { calculate() }
        function invoiceListCheckEvent(row) {
            var listSize = view.table.invoiceList.selected().length;
            view.button.addPayment.disable(listSize <= 0);
            calculate();
        }
        function invoiceListUncheckEvent(row) {
            var listSize = view.table.invoiceList.selected().length;
            view.button.addPayment.disable(listSize <= 0);
            calculate();
        }
        function invoiceListCheckAllEvent() {
            invoiceListCheckEvent(null);
        }
        function invoiceListUncheckAllEvent() {
            invoiceListUncheckEvent(null);
        }
        function calculate() {
            var invoiceMap = {}; $.each(view.table.invoiceList.rawData(), function (i, o) { invoiceMap[o.invoiceNo] = o });
            //var data = view.table.invoiceList.data();
            var sumVat = 0, sumVat2 = 0, sumBalance = 0, sumBalance2 = 0, sumTotalCharge = 0, sumTotalCharge2 = 0;
            var table = $("#invoiceList");
            var data = table.bootstrapTable("getData");
            var sumAmount = 0;

            for (var rowid = 0; rowid < data.length; rowid++) {
                var checked = data[rowid]["checked"];
                if (checked) {

                    var amountChange = data[rowid]["amountARin"];
                    var amountChange2 = data[rowid]["amountARout"];

                    var amountCaloutS = data[rowid]["amountPay"];
                    var rateChange = rateChangeRDGet;
                    console.log(rateChange);
                    
                    var receivedValue = stripToNumberWithFormat($("#received_" + rowid).val());
                    var amountBath = (rateChange*receivedValue);

                    sumTotalCharge += (amountBath);
                    sumTotalCharge2 += (receivedValue);

                    var amountARin = calculateVatFromIncluded((amountBath));
                    var amountARout = calculateVatFromIncluded(amountChange2);

                    var amountARoutCal = calculateVatFromIncluded((receivedValue));

                    sumBalance += amountARin;
                    sumBalance2 += amountARoutCal;

                    sumVat += stripToNumberWithFormat((amountBath) - amountARin);
                    sumVat2 += stripToNumberWithFormat((receivedValue) - amountARoutCal);

                }

            }

            // $('#sumBalance').val(!$.isNumeric(stripToNumber(sumBalance)) ? "0.00" : parseFloat(sumBalance, 10).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,'));
            // $('#sumBalance2').val(!$.isNumeric(stripToNumber(sumBalance2)) ? "0.00" : parseFloat(sumBalance2, 10).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,'));
            view.input.sumBalance.val(sumBalance);
            view.input.sumBalance2.val(sumBalance2);
            // $('#sumVat').val(!$.isNumeric(stripToNumber(sumVat)) ? "0.00" : parseFloat(sumVat, 10).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,'));
            // $('#sumVat2').val(!$.isNumeric(stripToNumber(sumVat2)) ? "0.00" : parseFloat(sumVat2, 10).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,'));
            view.input.sumVat.val(sumVat);
            view.input.sumVat2.val(sumVat2);
            // $('#sumTotalCharge').val(!$.isNumeric(stripToNumber(sumTotalCharge)) ? "0.00" : parseFloat(sumTotalCharge, 10).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,'));
            // $('#sumTotalCharge2').val(!$.isNumeric(stripToNumber(sumTotalCharge2)) ? "0.00" : parseFloat(sumTotalCharge2, 10).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,'));
            view.input.sumTotalCharge.val(sumTotalCharge);
            view.input.sumTotalCharge2.val(sumTotalCharge2);

        }

        // sub 2 position
        function subAmountBra(number, digits) {
            var multiplier = Math.pow(10, digits),
                adjustedNum = number * multiplier,
                truncatedNum = Math[adjustedNum < 0 ? 'ceil' : 'floor'](adjustedNum);

            return truncatedNum / multiplier;
        };

        function stripToNumberWithFormat(str) {
            var strFormat = str + "";
            strFormat = strFormat.replace(/[^0-9\.]/g, "");
            return parseFloat(strFormat, 10);
        }

        function calculateVatFromIncluded(ammount) {
        	
        if(vatRDGet == null && typeof(myVariable) == "undefined"){
        	vatRDGet =parseFloat("7");
        }
            var rtVatBalance = 0;
            var sunVat = (100+vatRDGet);
            rtVatBalance = (ammount * 100) / sunVat;
            return rtVatBalance;
        }

        function chkCurrencySymbol(str) {
            console.log("currency code: " + str);
            var url = "../getSymbolByCurrencyCodeSap.json"; params = { "code": str };
            var symbol = "฿";
            var data = [];
            console.log(url);
            console.log(params);
            if(str != undefined){
            	$.ajax({url: url, type: "GET", data: {"code": str},
                    async: false,
                    success: function (res) {
                        window.getSymbolByCurrencyCodeObj = res.data[0];
                        console.log(window.getSymbolByCurrencyCodeObj);
                        symbol = window.getSymbolByCurrencyCodeObj.currencySymbol;
                        symbol = symbol != null ? symbol : "฿"
                    },
                    error: function () {
                        symbol = "฿"
                    }
                });
            }else{
            	
            }
            
            console.log("symbol =================> " + symbol);
            return symbol;
        }

        function chkNullpointInput() {
            var chk = false;
            view.dialog.mainMessageDialog.clear();
            if (chk) {

            }
            if ($("#account").val().length == 0 || $("#docHead").val().length != 0) {
                view.dialog.mainMessageDialog.error(["โปรดระบุ 'เลขที่ลูกค้า'"]).show();
                return false;
            } else if ($("#docHead").val().length == 0 || $("#account").val().length != 0) {
                view.dialog.mainMessageDialog.error(["โปรดระบุ 'เลขที่ใบแจ้งหนี้ (Doc. Header Text)' ด้วยรูปแบบที่ถูกต้อง"]).show();
                return false;
            }
            return chk;
        }

        var chkAdvanced = false;
        document.getElementById('advancedCheckbox').onchange = function () {
            var advancedText = ["advancedAmount", "billCycle"];
            for (var i = 0; i < advancedText.length; i++) {
                document.getElementById(advancedText[i]).disabled = !this.checked;
            }
            if(chkAdvanced){view.advancedAmount.val(0.00);view.billCycle.val("");}
            chkAdvanced = true;
        };

        var chkAddress = false;
        var beforeEditAddress;
        document.getElementById('changeReceiptAddress').onchange = function () {
            var advancedAddressText = ["address"];
            var num = 0;
            
            if(chkAddress){
                num = 1;
                $('#address').val(beforeEditAddress);
            }
            for (var i = 0; i < advancedAddressText.length; i++) {
                chkAddress = true;
                document.getElementById(advancedAddressText[i]).disabled = !this.checked;
                if(num===1){
                    chkAddress = false;
                }else{beforeEditAddress = view.input.address.val();}
            }

        };

        function invoiceServiceFormatter(val, row, ind) {
            var guid = view.utils.guid();
            var loadPanel = new LoadingPanel(guid);
            var subPayBounceChequeDTO;
            var payBounceChequeDTO;
            for (var i = 0; i < payBounceChequeDTOList.length; i++) {
                if (row.docHead === payBounceChequeDTOList[i].docHead) {
                    payBounceChequeDTO = payBounceChequeDTOList[i]
                }
            }
            var result;
            $.get("../findProductCH", { "docHead": row.docHead }, function (res) {
                loadPanel.finish(Mustache.render($('#template').html(), {
                    "invoices": $.map(res.subPayBounceChequeDTOList, function (o, i) {
                        result = res.subPayBounceChequeDTOList;
                        console.log(result.revTypeName + "Test subPayBounceChequeDTOList ::: ::: ::: ::: ::: ::: :::");
                        return {
                            "productCode": o.productCode,
                            "productName": o.productName,
                            "subProductCode": o.subProductCode,
                            "subProductName": o.subProductName,
                            "revTypeCode": o.revTypeCode,
                            "revTypeName": o.revTypeName,
                            "amount": o.amount
                        }
                    })
                }));
            });
            return loadPanel;
        }

        function bounceChequeList() {
            var invoiceMap = {}; $.each(view.table.invoiceList.rawData(), function (i, o) { invoiceMap[o.invoiceNo] = o });
            var isPrepaid = view.advancedCheckbox.contains("on");
            view.session("bounceChequeList", []);
            var bounceChequeList = view.session("bounceChequeList"), detailARCustomer, payBounceChequeLists, payBounceCheque, vatRD;
            var table = $("#invoiceList");
            currencyCode = 12;

            detailARCustomer = find(bounceChequeList, "arAccountCode", { "arAccountCode": view.input.arAccountCode.val() });
            detailARCustomer.arName = view.input.arName.val();
            detailARCustomer.taxId = view.input.taxId.val();
            detailARCustomer.glAccount = view.input.glAccount.val();
            detailARCustomer.arGroup = view.input.arGroup.val();
            detailARCustomer.regionKey1 = view.input.regionKey1.val();
            detailARCustomer.branchAR = view.input.branchAR.val();
            detailARCustomer.address = view.input.address.val();
            detailARCustomer.remark = view.inputAdditionalRemark.val();
            detailARCustomer.vatRD = vatRDGet;
            detailARCustomer.rDGet = rateChangeRDGet;
            detailARCustomer.prepaid = view.advancedAmount.val();

            detailARCustomer.invoiceList = (detailARCustomer.invoiceList || []);
            var data = table.bootstrapTable("getData");
            if (data.length > 0) {
                detailARCustomer.invoiceDisplay = data[0]["invoiceDisplay"];
            } else {
                detailARCustomer.invoiceDisplay = invDisplay;
            }
            detailARCustomer.receiptFormat = view.session("receiptFormat");//by NSD 25-04-2017

            bounceChequeList.payBounceCheque = (bounceChequeList.payBounceCheque || []);
            for (var rowid = 0; rowid < data.length; rowid++) {
                var checked = data[rowid]["checked"];
                if (checked) {
                    currencyCode = data[rowid]["currency"]=="THB"?12:1;
                    var docHead = data[rowid]["docHead"];
                    payBounceChequeLists = find(detailARCustomer.invoiceList, "docHead", { "docHead": docHead });
                    payBounceChequeLists.refDate = data[rowid]["refDate"];
                    payBounceChequeLists.serviceKey3 = data[rowid]["serviceKey3"];
                    payBounceChequeLists.currency = data[rowid]["currency"];
                    payBounceChequeLists.amountARin = data[rowid]["amountARin"];
                    payBounceChequeLists.rateChange = data[rowid]["rateChange"];
                    payBounceChequeLists.amountARout = data[rowid]["amountARout"];
                    payBounceChequeLists.currencyChangeDate = data[rowid]["currencyChangeDate"];
                    payBounceChequeLists.docNo = data[rowid]["docNo"];
                    payBounceChequeLists.accountYear = data[rowid]["accountYear"];
                    payBounceChequeLists.docDate = data[rowid]["docDate"];
                    payBounceChequeLists.passDate = data[rowid]["passDate"];
                    payBounceChequeLists.vatCode = data[rowid]["vatCode"];
                    payBounceChequeLists.tradingPartner = data[rowid]["tradingPartner"];
                    payBounceChequeLists.message = data[rowid]["message"];
                    payBounceChequeLists.orgCode = data[rowid]["orgCode"];
                    payBounceChequeLists.docType = data[rowid]["docType"];
                    payBounceChequeLists.secment = data[rowid]["secment"];
                    payBounceChequeLists.prodKey2 = data[rowid]["prodKey2"];
                    payBounceChequeLists.recordStatus = data[rowid]["recordStatus"];
                    payBounceChequeLists.arAccountCode = "";
                    payBounceChequeLists.arName = "";
                    payBounceChequeLists.glAccount = "";
                    payBounceChequeLists.taxId = "";
                    payBounceChequeLists.arGroup = "";
                    payBounceChequeLists.regionKey1 = "";
                    payBounceChequeLists.branchAR = "";
                    payBounceChequeLists.address = "";
                    payBounceChequeLists.currencyCode = currencyCode;
                    payBounceChequeLists.attributes = "";
                    payBounceChequeLists.amountTotalPay = data[rowid]["amountTotalPay"];
                    payBounceChequeLists.amountPay = $("#received_" + rowid).val();
                }
            }

            return bounceChequeList;
        }
        function invoiceListMapper(o) {
        	var datePeoiud = o.refDate;
            var year = datePeoiud.substring(0, 4);
            var mount = datePeoiud.substring(4, 6);
            var day = datePeoiud.substring(6, 8);

            var yearN = datePeoiud.substring(8, 12);
            var mountN = datePeoiud.substring(12, 14);
            var dayN = datePeoiud.substring(14, 16);
            if (o.amountARout == "0" || o.amountARout == 0 || o.amountARout == null)
                return null;
			return {
					"docHead":o.docHead
               		,"refDate":day+"/"+mount+"/"+year+" - "+dayN +"/"+mountN+"/"+yearN       
                    ,"serviceKey3":o.serviceKey3       
                    ,"currency":o.currency           
                    ,"amountARout":o.amountARout         
                    ,"rateChange":o.rateChange          
                    ,"amountARin":o.amountARin        
                    ,"amountTotalPay":o.amountTotalPay     
                    ,"amountPay":o.amountPay           
                    ,"currencyChangeDate":o.currencyChangeDate  
                    ,"docNo":o.docNo               
                    ,"accountYear":o.accountYear         
                    ,"docDate":o.docDate             
                    ,"passDate":o.passDate            
                    ,"vatCode":o.vatCode             
                    ,"message":o.message 
                    ,"currencyCode":o.currencyCode 
                    	};
        }
        function historyListMapper(o) {
        	var datePeoiud = o.refDate;
            var year = datePeoiud.substring(0, 4);
            var mount = datePeoiud.substring(4, 6);
            var day = datePeoiud.substring(6, 8);

            var yearN = datePeoiud.substring(8, 12);
            var mountN = datePeoiud.substring(12, 14);
            var dayN = datePeoiud.substring(14, 16);
			return {
				"payDate":o.datecCreate
				,"receiptDate":o.dateInvoice   
				,"receiptNo":o.receiptNo     
				,"branch":o.branch
				,"user":o.user
				,"arNo":o.arNo
				,"serviceKey3":o.serviceKey3  
				,"refDate":day+"/"+mount+"/"+year+" - "+dayN +"/"+mountN+"/"+yearN
				,"payAmountAR":o.payAmountAR 
				,"logPay":o.logPay
				,"payType":o.payType      
				,"amountCurrency":o.amountCurrency
				,"vat":o.vat   
				,"currency":o.currency        
				,"rateChange":o.rateChange      
				,"changeDate":o.dateExrate    
				,"amountCurrencyin":o.amountCurrencyin
				,"vatIn":o.vatIn
				,"docNo":o.docNo
				,"accountYeah":o.accountYeah   
				,"status":o.status
				,"remark":o.remark
				,"massage":o.massage
               };
        }
    </script>

    </html>