<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><%=application.getInitParameter("episName")%></title>
    <link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="resources/bootstrap-table/src/bootstrap-table.css" rel="stylesheet" type="text/css"/>
    <link href="resources/css/datepicker.min.css" rel="stylesheet" type="text/css"/>
    <link href="resources/custom.css" rel="stylesheet" type="text/css"/>
    <script src="resources/jquery.min.js" type="text/javascript"></script>
    <script src="resources/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="resources/bootstrap-table/src/bootstrap-table.js"></script>
    <script src="resources/js/bootstrap-datepicker.min.js"></script>
    <script src="resources/js/mustache.min.js" type="text/javascript"></script>
    <script src="resources/custom.js" type="text/javascript"></script>

    <script type="text/javascript">
        $(document).ready(function() {
            $('#stDate,#stDateBackDate,#stDateTransac').datepicker({
                format: 'dd/mm/yyyy',
                lang: 'th',
                startDate: '01/01/2016',
                endDate: '30/12/2025'
            });
            $('#stDate').datepicker('setDate', new Date());
            $('#stDate').datepicker('update');
            $('#stDate').val('');
// 		        $('#displayCloseBranchPanel').addClass('hide');
            $('#stDateBackDate').datepicker('setDate', new Date());
            $('#stDateBackDate').datepicker('update');
            $('#stDateBackDate').val('');

            $('#stDateTransac').datepicker('setDate', new Date());
            $('#stDateTransac').datepicker('update');
            $('#stDateTransac').val('');

        });
    </script>
</head>
<body>
<section class="container-fluid menu">
    <div id="mainMessageDialog"></div>
    <div id="message"></div>
    <div id="displayNormalCloseBranchPanel">
        <div class="col-md-12">
            <div class="panel panel-default">
                <div class="panel-heading"><span class="glyphicon glyphicon-list-alt"></span> ปิดบัญชีการรับชำระประจำวัน (รายการปกติ)</div>
                <%--<div id="normalDayEndClosingPanel">
                    <ul class="nav nav-tabs" role="tablist">
                        <li role="presentation" class="active"><a href="#"><span class="glyphicon glyphicon-filter"></span> รายการปกติ</a></li>
                    </ul>
                    <div class="panel panel-default panal-radius">--%>
                <div id="dialogMainMessage"></div>
                <div class="form-horizontal">
                    <div class="form-group  padding-top">
                        <div class="form-group col-md-12 margin-bott">
                            <label class="control-label col-sm-2">วันที่รับชำระ:</label>
                            <div class="col-sm-3">
                                <input type="text" id="transactionDate" class="hide">
                                <div class="input-group input-append date" id="stDate">
                                    <input type="text" class="form-control" id="startDate" name="startDate" value="">
                                    <span class="input-group-addon add-on"><span class="glyphicon glyphicon-calendar"></span></span>
                                </div>
                            </div>
                            <div class="col-sm-1">
                                <a id="inquiryDayEndClosing" class="btn btn-primary glyphicon glyphicon-search"> ค้นหาข้อมูล</a>
                            </div>
                            <label class="control-label col-sm-2">สถานะปิดรอบ:</label>
                            <div class="col-sm-2">
                                <input type="text" class="form-control" id="closeStatus" name="closeStatus" value="" readonly>
                            </div>
                            <div class="col-sm-2 text-right">
                                <a id="reprintTransMoneyReceipt" class="btn btn-info disabled"><i class="glyphicon glyphicon-print"></i>  พิมพ์ใบส่งเงิน</a>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12 fix-table" style="margin-left:15px;">
                                <div class="table-responsive" style="margin-right:30px;" hidden>
                                    <div>
                                        <span>รายการ Terminal Report ตามเครื่อง</span>
                                    </div>
                                    <table id="tableTotalbyPos" class="table teble-highlight"
                                           data-toggle="table"
                                           data-classes="table">
                                        <thead>
                                        <tr>
                                            <th data-align="center" data-formatter="runningFormatter">#</th>
                                            <th data-align="center" data-sortable="true" data-field="posNo">เครื่อง POS </th>
                                            <th data-align="center" data-sortable="true" data-field="totalCash" data-formatter="numberFormatter">เงินสด</th>
                                            <th data-align="center" data-sortable="true" data-field="totalCheque" data-formatter="numberFormatter">เช็ค</th>
                                            <th data-align="center" data-sortable="true" data-field="totalCredit" data-formatter="numberFormatter">บัตรเครดิต</th>
                                            <th data-align="center" data-sortable="true" data-field="totalMoneyOrder" data-formatter="numberFormatter">ธนาณัติ</th>
                                            <th data-align="center" data-sortable="true" data-field="totalBillExchange" data-formatter="numberFormatter">ตั๋วแลกเงิน</th>
                                            <th data-align="center" data-sortable="true" data-field="totalCoupon" data-formatter="numberFormatter">คูปอง</th>
                                            <th data-align="center" data-sortable="true" data-field="totalMoneyTransfer" data-formatter="numberFormatter">เงินโอนในประเทศ</th>
                                            <th data-align="center" data-sortable="true" data-field="totalOffset" data-formatter="numberFormatter">offset</th>
                                            <%--<th data-align="center" data-sortable="true" data-field="totalForeignTransfer" data-formatter="numberFormatter">เงินโอนต่างประเทศ</th>--%>
                                            <th data-align="center" data-sortable="true" data-field="totalWt3tred" data-formatter="numberFormatter">WT 3 เตรส</th>
                                            <th data-align="center" data-sortable="true" data-field="totalWt69bis" data-formatter="numberFormatter">WT 69 ทวิ</th>
                                            <th data-align="center" data-sortable="true" data-field="totalWt69tre" data-formatter="numberFormatter">WT 69 ตรี</th>
                                            <th data-align="center" data-sortable="true" data-field="backDateTotalAmount" data-formatter="numberFormatter">เงินโอน ​Back Date</th>

                                            <%--<th data-align="center" data-sortable="true" data-formatter="endDayCloseDtl"></th>--%>
                                        </tr>
                                        </thead>
                                        <%--<tbody>
                                                <tr>
                                                    <td></td>
                                                    <td>01/02/2017</td>
                                                    <td>POS01</td>
                                                </tr>
                                                <tr>
                                                    <td></td>
                                                    <td>03/02/2017</td>
                                                    <td>POS02</td>
                                                </tr>
                                        </tbody>--%>
                                    </table>
                                </div>
                                <div class="table-responsive" style="margin-right:30px;">
                                    <%--<div>
                                        <span>รายการ Cashier Report</span>
                                    </div>--%>
                                    <table id="tableTotalbyEmp" class="table teble-highlight"
                                           data-toggle="table"
                                           data-classes="table table-bordered">
                                        <thead>
                                        <tr>
                                            <th data-align="center" data-formatter="runningFormatter">#</th>
                                            <th data-align="center" data-sortable="true" data-field="userName">พนักงาน </th>
                                            <th data-align="center" data-sortable="true" data-field="docStatus" data-formatter="desStatus">สถานะ</th>
                                            <th data-align="right" data-sortable="true" data-field="totalCash" data-formatter="numberFormatter">เงินสด</th>
                                            <th data-align="right" data-sortable="true" data-field="totalCheque" data-formatter="numberFormatter">เช็ค</th>
                                            <th data-align="right" data-sortable="true" data-field="totalCredit" data-formatter="numberFormatter">บัตรเครดิต</th>
                                            <th data-align="right" data-sortable="true" data-field="totalMoneyOrder" data-formatter="numberFormatter">ธนาณัติ</th>
                                            <th data-align="right" data-sortable="true" data-field="totalBillExchange" data-formatter="numberFormatter">ตั๋วแลกเงิน</th>
                                            <th data-align="right" data-sortable="true" data-field="totalCoupon" data-formatter="numberFormatter">คูปอง</th>
                                            <th data-align="right" data-sortable="true" data-field="totalMoneyTransfer" data-formatter="numberFormatter">เงินโอนในประเทศ</th>
                                            <th data-align="right" data-sortable="true" data-field="totalOffset" data-formatter="numberFormatter">offset</th>
                                            <%--<th data-align="right" data-sortable="true" data-field="totalForeignTransfer" data-formatter="numberFormatter">เงินโอนต่างประเทศ</th>--%>
                                            <th data-align="right" data-sortable="true" data-field="totalWt3tred" data-formatter="numberFormatter">WT 3 เตรส</th>
                                            <th data-align="right" data-sortable="true" data-field="totalWt69bis" data-formatter="numberFormatter">WT 69 ทวิ</th>
                                            <th data-align="right" data-sortable="true" data-field="totalWt69tre" data-formatter="numberFormatter">WT 69 ตรี</th>
                                            <th data-align="right" data-sortable="true" data-field="agentTotalAmount" data-formatter="numberFormatter">เงินรับชำระหน่วยงานอื่น</th>
                                            <%--<th data-align="right" data-sortable="true" data-field="backDateTotalAmount" data-formatter="numberFormatter">เงินโอน ​Back Date</th>--%>
                                            <th data-align="right" data-sortable="true" data-formatter="summaryEachEmp">รวม</th>
                                            <%--<th data-align="center" data-sortable="true" data-formatter="endDayCloseDtl"></th>--%>
                                        </tr>
                                        </thead>
                                        <tfoot hidden="hidden" id="summaryFooter">
                                        <tr>
                                            <td colspan="3" align="right"><span style="font-weight: bold; color: black;">รวม</span></td>
                                            <td align="right"><span id="sumTotalCashId"></span></td>
                                            <td align="right"><span id="totalChequeId"></span></td>
                                            <td align="right"><span id="totalCreditId"></span></td>
                                            <td align="right"><span id="totalMoneyOrderId"></span></td>
                                            <td align="right"><span id="totalBillExchangeId"></span></td>
                                            <td align="right"><span id="totalCouponId"></span></td>
                                            <td align="right"><span id="totalMoneyTransferId"></span></td>
                                            <td align="right"><span id="totalOffsetId"></span></td>
                                            <%--<td align="right"><span id="totalForeignTransferId"></span></td>--%>
                                            <td align="right"><span id="totalWt3tredId"></span></td>
                                            <td align="right"><span id="totalWt69bisId"></span></td>
                                            <td align="right"><span id="totalWt69treId"></span></td>
                                            <td align="right"><span id="agentTotalAmountId"></span></td>
                                            <td align="right"><span id="totalAllSummaryId"></span></td>
                                        </tr>
                                        </tfoot>
                                    </table>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-12" style="text-align: center;">
                                <label class="control-label"><a id="btnToClosing" class="btn btn-success" disabled="disabled"><span class="glyphicon glyphicon-ok-circle"></span> ปิดบัญชีสิ้นวัน</a></label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </div>
    <div id="displayBackDateCloseBranchPanel">
        <div class="col-md-12">
            <div class="panel panel-default">
                <div class="panel-heading"><span class="glyphicon glyphicon-list-alt"></span> ปิดบัญชีการรับชำระประจำวัน (รายการ Back Date)</div>
                <%--<div id="normalDayEndClosingPanel">
                    <ul class="nav nav-tabs" role="tablist">
                        <li role="presentation" class="active"><a href="#"><span class="glyphicon glyphicon-filter"></span> รายการปกติ</a></li>
                    </ul>
                    <div class="panel panel-default panal-radius">--%>
                <div id="dialogMainMessage"></div>
                <div class="form-horizontal">
                    <div class="form-group padding-top">
                        <div class="form-group col-md-12 margin-bott">
                            <label class="control-label col-sm-2">วันที่ปิดบัญชี:</label>
                            <div class="col-sm-3">
                                <input type="text" id="transactionDateBackDate" class="hide">
                                <div class="input-group input-append date" id="stDateBackDate">
                                    <input type="text" class="form-control" id="startDateBackDate" name="startDateBackDate" value="">
                                    <span class="input-group-addon add-on"><span class="glyphicon glyphicon-calendar"></span></span>
                                </div>
                            </div>
                            <%--<label class="control-label col-sm-1">วันที่รับชำระ:</label>
                            <div class="col-sm-2">
                                <input type="text" id="transactionDateBackDate" hidden>
                                <div class="input-group input-append date" id="stDateBackDate">
                                    <input type="text" class="form-control"  name="startDateBackDate" value="">
                                    <span class="input-group-addon add-on"><span class="glyphicon glyphicon-calendar"></span></span>
                                </div>
                            </div>
                            <label class="control-label col-sm-1">วันที่ทำรายการ:</label>
                            <div class="col-sm-2">
                                <input type="text" id="transactionDateTransac" hidden>
                                <div class="input-group input-append date" id="stDateTransac">
                                    <input type="text" class="form-control" id="startDate" name="startDate" value="">
                                    <span class="input-group-addon add-on"><span class="glyphicon glyphicon-calendar"></span></span>
                                </div>
                            </div>--%>
                            <div class="col-sm-1">
                                <a id="inquiryShopClosingBackdate" class="btn btn-primary glyphicon glyphicon-search"> ค้นหาข้อมูล</a>
                            </div>
                            <label class="control-label col-sm-2">สถานะปิดรอบ:</label>
                            <div class="col-sm-2">
                                <input type="text" class="form-control" id="closeStatusBackDt" name="closeStatusBackDt" value="" readonly>
                            </div>
                            <div class="col-sm-2">
                                <a id="viewClosingList" class="btn btn-danger hide"><i class="glyphicon glyphicon-list"></i>  ดูรายการที่ยังไม่ปิดบัญชี</a>
                            </div>
                            <div class="col-sm-2 text-right">
                                <a id="reprintTransMoneyReceiptBackDt" class="btn btn-info disabled"><i class="glyphicon glyphicon-print"></i> พิมพ์ใบส่งเงิน</a>
                            </div>

                        </div>
                        <div class="form-group" >
                            <div class="col-md-12 fix-table"  style="margin-left:15px;">
                                <div class="table-responsive" style="margin-right:30px;" id="focusTableTotalbyEmpBackDate">
                                    <%--<div>
                                        <span>รายการ Cashier Report</span>
                                    </div>--%>
                                    <table id="tableTotalbyEmpBackDate" class="table teble-highlight"
                                           data-toggle="table"
                                           data-classes="table table-bordered"
                                           >
                                        <thead>
                                        <tr>
                                            <th data-align="center" data-formatter="runningFormatter">#</th>
                                            <th data-align="center" data-checkbox="true" data-field="checked" data-formatter="stateFormatter">&nbsp;</th>
                                            <th data-align="center" data-sortable="true" data-field="userName">พนักงาน </th>
                                            <th data-align="center" data-sortable="true" data-field="receiptDtStr">วันที่รับชำระ</th>
                                            <th data-align="center" data-sortable="true" data-field="closingDtStr">วันที่ทำรายการ</th>
                                            <th data-align="center" data-sortable="true" data-field="docStatus" data-formatter="desStatus">สถานะ</th>
                                            <%--<th data-align="right" data-sortable="true" data-field="totalCash" data-formatter="numberFormatter">เงินสด</th>
                                            <th data-align="right" data-sortable="true" data-field="totalCheque" data-formatter="numberFormatter">เช็ค</th>
                                            <th data-align="right" data-sortable="true" data-field="totalCredit" data-formatter="numberFormatter">บัตรเครดิต</th>
                                            <th data-align="right" data-sortable="true" data-field="totalMoneyOrder" data-formatter="numberFormatter">ธนาณัติ</th>
                                            <th data-align="right" data-sortable="true" data-field="totalBillExchange" data-formatter="numberFormatter">ตั๋วแลกเงิน</th>
                                            <th data-align="right" data-sortable="true" data-field="totalCoupon" data-formatter="numberFormatter">คูปอง</th>
                                            <th data-align="right" data-sortable="true" data-field="totalMoneyTransfer" data-formatter="numberFormatter">เงินโอนในประเทศ</th>
                                            <th data-align="right" data-sortable="true" data-field="totalOffset" data-formatter="numberFormatter">offset</th>
                                            <th data-align="right" data-sortable="true" data-field="totalForeignTransfer" data-formatter="numberFormatter">เงินโอนต่างประเทศ</th>--%>
                                            <th data-align="right" data-sortable="true" data-field="totalWt3tred" data-formatter="numberFormatter">WT 3 เตรส</th>
                                            <th data-align="right" data-sortable="true" data-field="totalWt69bis" data-formatter="numberFormatter">WT 69 ทวิ</th>
                                            <th data-align="right" data-sortable="true" data-field="totalWt69tre" data-formatter="numberFormatter">WT 69 ตรี</th>
                                            <th data-align="right" data-sortable="true" data-field="transGfmisTotalAmount" data-formatter="numberFormatter">เงินโอน ​GFMIS</th>
                                            <th data-align="right" data-sortable="true" data-field="backDateTotalAmount" data-formatter="numberFormatter">เงินโอนในประเทศ</th>
                                            <th data-align="right" data-sortable="true" data-field="transForeignThTotalAmount" data-formatter="numberFormatter">เงินโอนต่างประเทศ</th>
                                            <th data-align="right" data-sortable="true" data-field="totalSumByEmp" data-formatter="summaryEachEmpBackDate2">รวม</th>
                                            <th data-align="center" data-sortable="true" data-formatter="closeButtonFormatter"></th>
                                            <%--<th data-align="center" data-sortable="true" data-formatter="endDayCloseDtl"></th>--%>
                                        </tr>
                                        </thead>
                                        <tfoot hidden="hidden" id="summaryFooterBackDate">
                                        <tr>
                                            <td colspan="6" align="right"><span style="font-weight: bold; color: black;">รวม</span></td>
                                            <%--<td align="right"><span id="sumTotalCashId"></span></td>
                                            <td align="right"><span id="totalChequeId"></span></td>
                                            <td align="right"><span id="totalCreditId"></span></td>
                                            <td align="right"><span id="totalMoneyOrderId"></span></td>
                                            <td align="right"><span id="totalBillExchangeId"></span></td>
                                            <td align="right"><span id="totalCouponId"></span></td>
                                            <td align="right"><span id="totalMoneyTransferId"></span></td>
                                            <td align="right"><span id="totalOffsetId"></span></td>
                                            <td align="right"><span id="totalForeignTransferId"></span></td>--%>
                                            <td align="right"><span id="totalWt3tredBackDateId"></span></td>
                                            <td align="right"><span id="totalWt69bisBackDateId"></span></td>
                                            <td align="right"><span id="totalWt69treBackDateId"></span></td>
                                            <td align="right"><span id="totalGfmisBackDateId"></span></td>
                                            <td align="right"><span id="totalTransferBackDateId"></span></td>
                                            <td align="right"><span id="totalForeignThBackDateId"></span></td>
                                            <td align="right"><span id="totalAllSummaryBackDateId"></span></td>
                                            <td align="right"></td>
                                        </tr>
                                        </tfoot>
                                    </table>
                                    <table id="tableHistTotalbyEmpBackDate" class="table table-striped hide"
                                           data-toggle="table"
                                           data-classes="table table-bordered">
                                        <thead>
                                        <tr>
                                            <th data-align="center" data-formatter="runningFormatter">#</th>
                                            <th data-align="center" data-sortable="true" data-field="shopClosingDtStr">วันที่ปิดบัญชี</th>
                                            <th data-align="center" data-sortable="true" data-field="userName">พนักงาน </th>
                                            <th data-align="center" data-sortable="true" data-field="receiptDtStr">วันที่รับชำระ</th>
                                            <th data-align="center" data-sortable="true" data-field="closingDtStr">วันที่ทำรายการ</th>
                                            <th data-align="center" data-sortable="true" data-field="docStatus" data-formatter="desStatus">สถานะ</th>
                                            <th data-align="right" data-sortable="true" data-field="totalWt3tred" data-formatter="numberFormatter">WT 3 เตรส</th>
                                            <th data-align="right" data-sortable="true" data-field="totalWt69bis" data-formatter="numberFormatter">WT 69 ทวิ</th>
                                            <th data-align="right" data-sortable="true" data-field="totalWt69tre" data-formatter="numberFormatter">WT 69 ตรี</th>
                                            <th data-align="right" data-sortable="true" data-field="transGfmisTotalAmount" data-formatter="numberFormatter">เงินโอน ​GFMIS</th>
                                            <th data-align="right" data-sortable="true" data-field="backDateTotalAmount" data-formatter="numberFormatter">เงินโอนในประเทศ</th>
                                            <th data-align="right" data-sortable="true" data-field="transForeignThTotalAmount" data-formatter="numberFormatter">เงินโอนต่างประเทศ</th>
                                            <th data-align="right" data-sortable="true" data-formatter="summaryEachEmpBackDate">รวม</th>
                                            <%--<th data-align="right" data-sortable="true" data-formatter="closeButtonFormatter"></th>--%>
                                            <%--<th data-align="center" data-sortable="true" data-formatter="endDayCloseDtl"></th>--%>
                                        </tr>
                                        </thead>
                                        <tfoot id="histSummaryFooterBackDate">
                                        <tr>
                                            <td colspan="6" align="right"><span style="font-weight: bold; color: black;">รวม</span></td>
                                            <td align="right"><span id="histTotalWt3tredBackDateId"></span></td>
                                            <td align="right"><span id="histTotalWt69bisBackDateId"></span></td>
                                            <td align="right"><span id="histTotalWt69treBackDateId"></span></td>
                                            <td align="right"><span id="histTotalGfmisBackDateId"></span></td>
                                            <td align="right"><span id="histTotalTransferBackDateId"></span></td>
                                            <td align="right"><span id="histTotalForeignThBackDateId"></span></td>
                                            <td align="right"><span id="histTotalAllSummaryBackDateId"></span></td>
                                        </tr>
                                        </tfoot>
                                    </table>

                                </div>
                            </div>
                        </div>


                        <div class="form-group">
                            <div class="col-sm-12" style="text-align: center;">
                                <label class="control-label"><a id="btnToClosingBackDate" class="btn btn-success" disabled="disabled"><span class="glyphicon glyphicon-ok-circle"></span> ปิดบัญชีสิ้นวัน BackDate</a></label>
                                <%--<label class="control-label"><a href="Dashboard.jsp" class="btn btn-danger"><span class="glyphicon glyphicon-remove-circle"></span> กลับสู่หน้าจอหลัก</a></label>--%>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="form-group text-right">
        <div class="col-sm-12">
            <label class="control-label"><a href="Dashboard.jsp" class="btn btn-black"><span class="glyphicon glyphicon-chevron-left"></span>&nbsp;&nbsp; กลับสู่หน้าจอหลัก</a></label>
        </div>
    </div>

</section>

<div class="modal fade" role="dialog" id="confirmClosing">
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
                                <label class="col-sm-12 control-label " style="font-size: 25px; text-align: center"><span class="glyphicon glyphicon-question-sign"></span> ยืนยันทำการปิดบัญชีสิ้นวัน</label>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-12 control-label " style="text-align: center">ยอดรับชำระทั้งสิ้น: <span id="lbSUser"></span> บาท</label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <a id="buttonCloseBranch" class="btn btn-success"><span class="glyphicon glyphicon-ok-circle"></span> ตกลง</a>
                <a class="btn btn-danger" data-dismiss="modal"><span class="glyphicon glyphicon-remove-circle"></span> ยกเลิก</a>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" role="dialog" id="alertCloseEmpAll">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close"  data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><span class="glyphicon glyphicon-warning-sign"></span> ข้อความแจ้งเตือน</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-12">
                        <div class="form-horizontal">
                            <div class="form-group">
                                <label class="col-sm-12 control-label " style="font-size: 25px; text-align: center"><span class="glyphicon glyphicon-question-sign"></span> มีรายการปิดบัญชี ระดับ User ที่ยังไม่ได้ปิดบัญชี ต้องการปิดบัญชีระดับ User หรือไม่</label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <a id="btnToConfirmCloseEmp" class="btn btn-success"><span class="glyphicon glyphicon-ok-circle"></span> ตกลง</a>
                <a id="btnCancelCloseEmp" class="btn btn-danger" data-dismiss="modal"><span class="glyphicon glyphicon-remove-circle"></span> ยกเลิก</a>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" role="dialog" id="confirmCloseEmp">
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
                                <label class="col-sm-12 control-label " style="font-size: 25px; text-align: center"><span class="glyphicon glyphicon-question-sign"></span> ยืนยันการปิดบัญชีระดับ User ต้องการปิดบัญชีระดับ User ทั้งหมดหรือไม่? </label>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-12 control-label " style="text-align: center">ยอดรับชำระทั้งสิ้น: <span id="lbsumUser"></span> บาท</label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <a id="btnToClosing0" class="btn btn-success"><span class="glyphicon glyphicon-ok-circle"></span> ตกลง</a>
                <a class="btn btn-danger" data-dismiss="modal"><span class="glyphicon glyphicon-remove-circle"></span> ยกเลิก</a>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" role="dialog" id="confirmClosingBackDate">
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
                                <label class="col-sm-12 control-label " style="font-size: 25px; text-align: center"><span class="glyphicon glyphicon-question-sign"></span> ต้องการปิดบัญชีสิ้นวัน backdate ที่มีสถานะ "Close" หรือไม่? </label>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-12 control-label " style="text-align: center">ยอดรับชำระทั้งสิ้น: <span id="lableSum"></span> บาท</label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <a id="buttonCloseBranchBackDate" class="btn btn-success"><span class="glyphicon glyphicon-ok-circle"></span> ตกลง</a>
                <a class="btn btn-danger" data-dismiss="modal"><span class="glyphicon glyphicon-remove-circle"></span> ยกเลิก</a>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" role="dialog" id="confirmCloseEmpBackDate">
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
                                <label class="col-sm-12 control-label " style="font-size: 25px; text-align: center"><span class="glyphicon glyphicon-question-sign"></span> ยืนยันปิดบัญชี</label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <a id="btnToClosing0BackDate" class="btn btn-success"><span class="glyphicon glyphicon-ok-circle"></span> ตกลง</a>
                <a class="btn btn-danger" data-dismiss="modal"><span class="glyphicon glyphicon-remove-circle"></span> ยกเลิก</a>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" role="dialog" id="bdEmpClose">
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
                                <label class="col-sm-12 control-label " style="font-size: 25px; text-align: center"><span class="glyphicon glyphicon-question-sign"></span> ยืนยันปิดบัญชีของพนักงานทีเลือก</label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <a onclick="saveBackDateEmpClose()" id="saveClosingBackDT" class="btn btn-success"><span class="glyphicon glyphicon-ok-circle"></span> ตกลง</a>
                <a class="btn btn-danger" data-dismiss="modal"><span class="glyphicon glyphicon-remove-circle"></span> ยกเลิก</a>
            </div>
        </div>
    </div>
</div>

<%--<div class="modal fade" role="dialog" id="alertUserAllClosing">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close"  data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><span class="glyphicon glyphicon-warning-sign"></span> ข้อความแจ้งเตือน</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-12">
                        <div class="form-horizontal">
                            <div class="form-group">
                                <label class="col-sm-12 control-label " style="font-size: 25px; text-align: center"><span class="glyphicon glyphicon-question-sign"></span> ยืนยันการปิดบัญชีระดับ User ต้องการปิดบัญชีระดับ User ทั้งหมดหรือไม่</label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <a id="buttonUserAllClosing" class="btn btn-success"><span class="glyphicon glyphicon-ok-circle"></span> ตกลง</a>
                <a id="btnCancelCloseEmp" class="btn btn-danger" data-dismiss="modal"><span class="glyphicon glyphicon-remove-circle"></span> ยกเลิก</a>
            </div>
        </div>
    </div>
</div>--%>


</div>
<div id="dialog" style="display: none" align="center">
        Do you want to delete this record?
</div>
</body>
</html>
<script>
    var endDayClosingList = [];
    var employees = [];
    var searchDate,EmpCode, UserName, ReceiptDate, BranchCode;
    var checked = false;
    //var backDateStatus = '';
    var isOneBackDateNotClose = false;
    var summaryCash = 0, summaryCheque = 0, summaryCreditCard = 0, summaryMoneyOrder = 0, summaryBillExchange = 0, summaryCoupon = 0, summaryBankTransfer = 0;
    var summaryForeignTransfer = 0, summaryOffset = 0, summaryWt3tred = 0, summaryWt69tre = 0, summaryWt69bis = 0, summaryTotalAll = 0; summaryAgentTotalAmount = 0;
    $(document).on("click", "a#inquiryDayEndClosing", function() {
        view.message.clear()
        searchDate = $("#startDate").val();
        searchByDate();
    });
    function searchByDate(){
        summaryCash = 0; summaryCheque = 0; summaryCreditCard = 0; summaryMoneyOrder = 0; summaryBillExchange = 0; summaryCoupon = 0; summaryBankTransfer = 0;
        summaryForeignTransfer = 0; summaryOffset = 0; summaryWt3tred = 0; summaryWt69tre = 0; summaryWt69bis = 0;summaryTotalAll = 0; summaryAgentTotalAmount = 0;
        employees = [];
        var flgPriority1 = false;
        var flgPriority2 = false;
        $("#transactionDate").val($("#startDate").val().replace(/([0-9]{2}).([0-9]{2}).([0-9]{4})/g,"$1-$2-$3"));
        /*$.ajax({
         type: "GET",
         url: "../findEndDayClosingPosGroup.json?startDate="+$("#startDate").val().replace(/([0-9]{2}).([0-9]{2}).([0-9]{4})/g,"$1-$2-$3"),
         dataType: "json",
         contentType: "application/json; charset=utf-8",
         async: false,
         success:function(data){
         //console.log('5555'); console.log(data);console.log('5555');
         endDayClosingList = data.endDayClosingList;

         if(data.endDayClosingList!=null && data.endDayClosingList.length > 0){
         accessDtl(data.endDayClosingList[0]);
         if(data.endDayClosingList[0].shopClosing != null){
         //$('#btnToClosing').attr('disabled', 'disabled');
         view.closeStatus.val("CLOSED"); flgPriority1 = true;//already closed
         }else{
         //$('#btnToClosing').removeAttr("disabled");
         view.closeStatus.val("OPEN");
         }
         $('#summaryFooter').removeAttr("hidden");

         }else{
         flgPriority1 = true;//no data
         clearData();
         }
         $("#tableTotalbyPos").bootstrapTable("load", data.endDayClosingList);
         }
         });*/
        $.ajax({
            type: "GET",
            url: "../findEndDayClosingEmpGroup.json?startDate="+$("#startDate").val().replace(/([0-9]{2}).([0-9]{2}).([0-9]{4})/g,"$1-$2-$3"),
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            async: false,
            success:function(data){
                console.log('xxxx'); console.log(data.endDayClosingList);console.log('<<xxx');
                $("#tableTotalbyEmp").bootstrapTable("load", data.endDayClosingList);
                var countNotYetClosed = 0, flgStatus = false;
                if(data.endDayClosingList!=null && data.endDayClosingList.length > 0) {
                    if(data.endDayClosingList[0].shopClosing != null){
                        flgPriority1 = true;//already closed
                    }

                    $.each(data.endDayClosingList, function (i, o) {
                        if (o.docStatus == "W") {
                            //flgPriority2 = true;//can not close
                            /*employees[countNotYetClosed].empCode = o.empCode;
                             employees[countNotYetClosed].branchCode = o.branchCode;
                             employees[countNotYetClosed].closingId = o.closingId;*/
                            var employee = {
                                empCode: o.userName,
                                branchCode: o.branchCode,
                                closingId: o.id,
                                shopClosingId: o.shopClosingId1
                            }
                            employees.push(employee);
                            countNotYetClosed++;
                        }
                        if(o.shopClosingId1 == null){
                            flgStatus = true;
                        }

                        summaryCash += o.totalCash;
                        summaryCheque += o.totalCheque;
                        summaryCreditCard += o.totalCredit;
                        summaryMoneyOrder += o.totalMoneyOrder;
                        summaryBillExchange += o.totalBillExchange;
                        summaryCoupon += o.totalCoupon;
                        summaryBankTransfer += o.totalMoneyTransfer;
                        summaryForeignTransfer += o.totalForeignTransfer;
                        summaryOffset +=o.totalOffset;
                        summaryWt3tred += o.totalWt3tred;
                        summaryWt69bis += o.totalWt69bis;
                        summaryWt69tre += o.totalWt69tre;
                        summaryAgentTotalAmount += o.agentTotalAmount;

                    });

                    $('#summaryFooter').removeAttr("hidden");
                }else{
                    flgPriority1 = true;//no data
                    clearData();
                }

                if(flgStatus){
                    //$('#btnToClosing').removeAttr("disabled");
                    view.closeStatus.val("OPEN");
                    $('#btnToClosing').removeAttr("disabled");
                    view.reprintTransMoneyReceipt.disable();
                    isOneBackDateNotClose = true;
                }else{
                    view.closeStatus.val("CLOSED"); flgPriority1 = true;//already closed
                    $('#btnToClosing').attr('disabled', 'disabled');
                    view.reprintTransMoneyReceipt.enable();
                }
            }
        });
        summaryTotalAll = summaryCash+summaryCheque+summaryCreditCard+summaryMoneyOrder+summaryBillExchange+summaryCoupon+summaryBankTransfer
            +summaryOffset+summaryWt3tred+summaryWt69tre+summaryWt69bis+summaryForeignTransfer+summaryAgentTotalAmount+summaryTotalAll;
        $("#sumTotalCashId").text(showNumberFormat(summaryCash));
        $("#totalChequeId").text(showNumberFormat(summaryCheque));
        $("#totalCreditId").text(showNumberFormat(summaryCreditCard));
        $("#totalMoneyOrderId").text(showNumberFormat(summaryMoneyOrder));
        $("#totalBillExchangeId").text(showNumberFormat(summaryBillExchange));
        $("#totalCouponId").text(showNumberFormat(summaryCoupon));
        $("#totalMoneyTransferId").text(showNumberFormat(summaryBankTransfer));
        $("#totalOffsetId").text(showNumberFormat(summaryOffset));
        $("#totalWt3tredId").text(showNumberFormat(summaryWt3tred));
        $("#totalWt69bisId").text(showNumberFormat(summaryWt69bis));
        $("#totalWt69treId").text(showNumberFormat(summaryWt69tre));
        $("#agentTotalAmountId").text(showNumberFormat(summaryAgentTotalAmount));
        $("#totalForeignTransferId").text(showNumberFormat(summaryForeignTransfer));
        $("#totalAllSummaryId").text(showNumberFormat(summaryTotalAll));

        //var table = document.getElementById("tableTotalbyEmp");
        var rows = $("#tableTotalbyEmp").find("tbody>tr");
        //var rows = table.getElementsByTagName("tr");
        var tb = $("#tableTotalbyEmp").bootstrapTable("getData");
        $.each(tb, function(i, o){
            if(o.docStatus == 'W'){
                rows[i].style.backgroundColor = "yellow";
                //rows[i].style.color = "white";
            }
        });
    }
    function searchBackDateItems(){
        $.ajax({
            type: "GET",
            url: "../findEndDayClosingEmpGroupBackDate.json",
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            async: false,
            success:function(data){
                var summaryBackDate = 0, summaryWt3tredBackDate = 0, summaryWt69bisBackDate = 0, summaryWt69treBackDate = 0, summaryTotalAllBackDate = 0, summaryTransGfmisBackDate = 0, summaryTransForeignThBackDate = 0, summaryTransBackDate = 0;
                var flgBackDateCloseShop = true;
                isOneBackDateNotClose = false;
                console.log('5555BackDate'); console.log(data.endDayClosingList);console.log('5555BackDate');
                var flgStatus = true;
                $("#tableTotalbyEmpBackDate").bootstrapTable("load", data.endDayClosingList);
                if(data.endDayClosingList.length > 0){
                    $.each(data.endDayClosingList, function(i, o){
                        summaryWt3tredBackDate += o.totalWt3tred;
                        summaryWt69bisBackDate += o.totalWt69bis;
                        summaryWt69treBackDate += o.totalWt69tre;

                        summaryTransBackDate += o.backDateTotalAmount;
                        summaryTransGfmisBackDate += o.transGfmisTotalAmount;
                        summaryTransForeignThBackDate += o.transForeignThTotalAmount;
                        if(o.docStatus == 'S'){
                            flgBackDateCloseShop = false;
                        }
                        console.log("Check ShopClosing Id : "+o.shopClosingId1);
                        if (o.shopClosingId1 == null) {
                            console.log("* shop id "+i+">>> "+o.shopClosingId1);
                            flgStatus = false;
                        }
                    });
                    summaryTotalAllBackDate = summaryWt69treBackDate+summaryWt3tredBackDate+summaryWt69bisBackDate+summaryTransBackDate+summaryTransGfmisBackDate+summaryTransForeignThBackDate;
                    $("#totalWt3tredBackDateId").text(showNumberFormat(summaryWt3tredBackDate));
                    $("#totalWt69bisBackDateId").text(showNumberFormat(summaryWt69bisBackDate));
                    $("#totalWt69treBackDateId").text(showNumberFormat(summaryWt69treBackDate));
                    $("#totalTransferBackDateId").text(showNumberFormat(summaryTransBackDate));
                    $("#totalAllSummaryBackDateId").text(showNumberFormat(summaryTotalAllBackDate));
                    $("#totalGfmisBackDateId").text(showNumberFormat(summaryTransGfmisBackDate));
                    $("#totalForeignThBackDateId").text(showNumberFormat(summaryTransForeignThBackDate));
                    $('#summaryFooterBackDate').removeAttr("hidden");

                    /*$('#btnToClosingBackDate').attr("disabled", flgBackDateCloseShop);*/

                    markCloseStatus(flgStatus);

                    var rows = $("#tableTotalbyEmpBackDate").find("tbody>tr");
                     console.log("STEP 1 >>>>");
                     console.log(rows);
                     var tb = $("#tableTotalbyEmpBackDate").bootstrapTable("getData");
                     $.each(tb, function(i, o){
                         if(o.docStatus == 'W'){
                            rows[i].style.backgroundColor = "yellow";
                         }
                     });
                    $('.selected').removeClass('selected');
                }else{
                    $('#summaryFooterBackDate').attr("hidden", "hidden");
                }

            }
        });

    }

    var view = (function($){
        var self = this, defaultErrorMessage = "An error occurred but there is no message response.";
        self.session = function(key, val) { if (!val) { var val = window.sessionStorage.getItem(key); return val && (val.indexOf("{") > -1 || val.indexOf("[") > -1) ? JSON.parse(val) : val } window.sessionStorage.setItem(key, ($.type(val) != "object" && $.type(val) != "array" ? val : JSON.stringify(val)))	};
        self.utils = {
            guid: function(){ function r() { return Math.floor((Math.random() + 1) * 0x10000).toString(16).substring(1) } return r() + r() +"-"+ r() +"-"+ r() +"-"+ r() +"-"+ r() + r() + r() },
            numberFormat: function(num, dec) { return $.type(num) !== "number" ? "0.00" : num.toFixed(dec || 2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,'); },
            dateFormat: function() { var obj = arguments[0]; if ($.type(obj) == "string" && /[0-9]{2}.[0-9]{2}.[0-9]{4}/g) { return obj } else if ($.isNumeric(obj)) { var dt = new Date(obj), dd = dt.getDate().toString(), mm = (dt.getMonth() + 1).toString(), yyyy = dt.getFullYear().toString(); return (dd[1]?"":"0") + dd +"/"+ (mm[1]?"":"0") + mm +"/"+ yyyy } else if ($.type == "date") { return "" } return "" },
            timeFormat: function() { var obj = arguments[0]; if ($.type(obj) == "string" && /[0-9]{2}.[0-9]{2}.[0-9]{4}/g) { return obj } else if ($.isNumeric(obj)) { var dt = new Date(obj), hh = dt.getHours().toString(), mm = dt.getMinutes().toString(), ss = dt.getSeconds().toString(); return (hh[1]?"":"0") + hh +":"+ (mm[1]?"":"0") + mm +":"+ (ss[1]?"":"0") + ss } else if ($.type == "date") { return "" } return "" },
            dateTimeFormat: function() { var obj = arguments[0], dt = $.type(obj) != "date" ? ($.isNumeric(obj) ? new Date(obj) : new Date()) : new Date(), dd = dt.getDate().toString(), MM = (dt.getMonth() + 1).toString(), yyyy = dt.getFullYear().toString(), hh = dt.getHours().toString(), mm = dt.getMinutes().toString(), ss = dt.getSeconds().toString(); return (dd[1]?"":"0") + dd +"/"+ (MM[1]?"":"0") + MM +"/"+ yyyy +" "+ (hh[1]?"":"0") + hh +":"+ (mm[1]?"":"0") + mm +":"+ (ss[1]?"":"0") + ss },
            queryString: function() { var pos = location.href.indexOf("?"), obj = {}; if (pos < 0) return obj; var params = location.href.slice(pos + 1).split("&"); for (var i = 0, m = params.length; i < m; i++) { pos = params[i].indexOf("="); if (pos < 0) { obj[params[i]] = true; continue; } obj[params[i].substring(0, pos)] = params[i].substring(pos + 1) } return obj },
            window: function(windowName, url, side) { window.windowOpened = (window.windowOpened || {}); var screenProp = ""; if (side == "left") { screenProp = "left=0,top=0,width="+ (screen.width/2) +",height="+ (screen.height-100) } else if (side == "right") { screenProp = "left="+ (screen.width/2-40) +",top=0,width="+ (screen.width/2+40) +",height="+ (screen.height-100) } window.windowOpened[windowName] = window.open(url, windowName, "menubar=no,scrollbars=no,resizable=0,"+ screenProp, false); return window.windowOpened }
        };
        self.dialog = new(function(){
            var that = this;
            that.mainMessageDialog = new Message("#mainMessageDialog");
            function Modal(el, static) { var obj = this; obj.el = el; obj.elem = $(el);
                obj.hide = function() { this.elem.modal("hide") };
                obj.show = function() { this.elem.modal("show") };
                if (static) obj.elem.data("backdrop", "static")
            }
        });
        //// AUTOMATIC REGISTER FORMATTER FUNCTION ////
        window.get = function(url, params, success, msgDialog, preCheck){ $.ajax({ "url": url, "type": "GET", "data": params, "error": (msgDialog || { "valid": function(jqXHR, textStatus, errorThrow){ console.log(jqXHR); console.log("textStatus: "+ textStatus); console.log("errorThrow:"); console.log(errorThrow) } }).valid, "success": function(res){ var isNotJson = res.constructor == String; console.log(res); (preCheck || function(o){})(res); if (isNotJson) res = { "statusCode": "99", "errorList": ["No matching data found."] }; if (msgDialog && !msgDialog.valid(res)) { return; } success(res) } }) }
        window.post = function(url, params, success, msgDialog, preCheck){ $.ajax({ "url": url, "type": "POST", "data": params, "error": (msgDialog || { "valid": function(jqXHR, textStatus, errorThrow){ console.log(jqXHR); console.log("textStatus: "+ textStatus); console.log("errorThrow:"); console.log(errorThrow) } }).valid, "success": function(res){ var isNotJson = res.constructor == String; console.log(res); (preCheck || function(o){})(res); if (isNotJson) res = { "statusCode": "99", "errorList": ["No matching data found."] }; if (msgDialog && !msgDialog.valid(res)) { return; } success(res) } }) }
        window.stripToNumber = function(str) { return $.type(str) === "string" && $.isNumeric(str = str.replace(/[,]/g, "")) ? parseFloat(str, 10) : 0 }
        window.runningFormatter = function(val, row, ind) { return ind + 1 }
        window.numberFormatter = function(val, row, ind) { return !$.isNumeric(stripToNumber(val)) ? "0.00" : parseFloat(val, 10).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,') }
        window.stringInputFormatter = function(val, row, ind) { return '<input value="'+ $.trim(val) +'" maxLength="40" class="form-control">' }
        window.numberInputFormatter = function(val, row, ind) { return '<input value="'+ (val || "0.00") +'" maxLength="10" class="form-control text-right" onkeypress="return $.isNumeric(String.fromCharCode(event.which)) || (event.which == 46 && this.value.indexOf(\'.\') == -1)" onfocus="(function(o){ o.value = o.value.replace(/[,]/g, \'\') })(this)" onblur="(function(o){ o.value = parseFloat(o.value, 10).toFixed(2).replace(/(\\d)(?=(\\d{3})+\\.)/g, \'$1,\'); })(this)">' }
        window.modifyButtonFormatter = function(val, row, ind) { return '<a class="btn btn-link modList" onclick=\'(function(elem, row, ind){ var table = $("#"+ $(elem).closest("table")[0].id); window[table[0].id +"ModifyEvent"](table, row, ind) })(this, '+ JSON.stringify(row) +', '+ ind +')\'><span class="glyphicon glyphicon-pencil"></span></a>' }
        window.removeButtonFormatter = function(val, row, ind) { return '<a class="btn btn-link delList" onclick=\'(function(elem, row, ind){ $("#"+ $(elem).closest("table")[0].id).bootstrapTable("remove", { field: "index", values: [ind] }) })(this, '+ JSON.stringify(row) +', '+ ind +')\'><span class="glyphicon glyphicon-trash"></span></a>' }

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
        function Message(el) { if(!el) { el = "#message" } var obj = this, timeCnt = 0, loadFunc; obj.el = el; obj.elem = $(el);
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

        function Input(el, maxLen, propPos) { var obj = this; obj.el = el; obj.elem = $(el);
            obj.error = function(flag) { if (arguments.length == 0 || flag) obj.elem.parent().addClass("has-error"); else obj.elem.parent().removeClass("has-error"); return this }
            obj.clear = function() { obj.val(""); return this }
            obj.empty = function() { return $.trim(obj.val()) === "" }
            obj.click = function(func) { obj.elem.click(func); return this }
            obj.readOnly = function(flag) { obj.elem.attr("readonly", (flag == null ? true : flag)); return this }
            obj.disable = function(flag) { obj.elem.attr("disabled", (flag == null ? true : flag)); return this }
            obj.enable = function() { obj.disable(false); return this }
            obj.val = function() { if (arguments.length == 1) { this.elem.val(arguments[0]) } return $.trim(this.elem.val()) }
            obj.get = function(propName) { if ($.type(propPos[propName]) !== "array" || propPos[propName].length !== 2) return ""; return obj.val().substring(propPos[propName][0], propPos[propName][1]) }
            if ($.isNumeric(maxLen)) { obj.elem.attr("maxLength", maxLen) }
        }

        function NumberInput(el, dec) { var obj = this, decimal = (dec == null ? 2 : dec); obj.el = el; obj.elem = $(el).addClass("text-right");
            obj.val = function() { if (arguments.length == 0) return parseFloat(strip(this.elem.val() || "0.00"), 10); this.elem.val(format(arguments[0])); }
            obj.decimal = function(dec) { decimal = dec }; obj.format = format;
            obj.disable = function(){ obj.elem.attr("disabled", (arguments.length != 1 ? true : arguments[0])); return obj }; obj.enable = function(){ obj.disable(false); return obj }
            obj.elem.keypress(function(e) { var key = (e.which || e.keyCode || e.charCode || 0); var ch = String.fromCharCode(key); return "0123456789.".indexOf(ch) > -1 });
            obj.elem.focus(function(){ this.value = strip(this.value); this.select() });
            obj.elem.blur(function(){ this.value = format(this.value) });
            function format(val) { return ($.isNumeric(val) ? parseFloat(val, 10) : obj.val()).toFixed(decimal).replace(/(\d)(?=(\d{3})+\.)/g, '$1,'); };
            function strip(str) { return (str || "").replace(/[,]/g, "") }
            if (obj.elem.val() == "") obj.elem.val("0" + (dec == null || dec < 1 ? "" : "."+ Array(dec + 1).join("0")));
        }
        function DropDown(el, url) { var obj = this, data = [{ key: "", value: "", text: "Please Select" }]; obj.el = el; obj.elem = $(el);
            obj.data = function(array) { if ($.type(array) == "array") { setup(data = array); return this } return data; };
            obj.init = function(url) { if (url) $.get(url, function(res) { setup(data = res.data) }); else setup(data); return this };
            obj.index = function() { if (arguments.length == 1) { var maxLen = data.length, index = arguments[0]; obj.elem.find("option").each(function(i, opt){ opt.removeAttribute("selected") }).eq(index < 0 ? maxLen + index : index).attr("selected", true); return this } return $.inArray("selected", obj.elem.find("option").map(function(i, opt){ return opt.selected ? "selected" : "no-selected" })) }
            obj.selected = function(){ return data[obj.index()]; }; obj.val = function() { return obj.elem.val(); }; obj.key = function(){ if (arguments.length == 0) { var key = obj.elem.find("option:selected").data("key"); if (typeof key === "string" && key === "undefined") { var item = data[obj.index()]; key = item && item.key ? item.key : item.id } return key } var key = arguments[0]; for (var i = 0, m = data.length; i < m; i++) { var item = data[i]; if (item.key && item.key == key) { obj.index(i); break; } if (item.id && item.id == key) { obj.index(i); break; } } return this }
            function setup(array) { obj.elem.find("*").remove(); $.each(array,function(i,o){ obj.elem.append('<option data-index="'+ i +'" data-key="'+ o.key +'" value="'+ o.value +'">'+ (o.text || o.value) +'</option>') }); }
            data = obj.elem.change(window[el.substring(1) +"ChangeEvent"]).find("option").map(function(i, opt){ return { key: opt.getAttribute("data-key"), value: opt.value, text: opt.textContent } });
        }
        (function(){ $(window["setup"]) })();

        self.message = new Message();
        self.dialogMainMessage = new Message("#dialogMainMessage");
        self.buttonCloseBranch = new Button("#buttonCloseBranch");
        self.buttonSearch = new Button("#buttonSearch");
        self.inputOfficerName = new Input("#inputOfficerName");
        self.inputClosingId = new Input("#inputClosingId");
        self.inputPageNo = new Input("#inputPageNo");
        self.inputPOSMachineName = new Input("#inputPOSMachineName");
        self.inputCurrentDate = new Input("#inputCurrentDate");
        self.inputBranch = new Input("#inputBranch");
        self.inputCurrentTime = new Input("#inputCurrentTime");
        self.inputTotalPaymentAmount = new NumberInput("#inputTotalPaymentAmount");
        self.inputTotal3TredecimAmount = new NumberInput("#inputTotal3TredecimAmount");
        self.inputTotal69BisAmount = new NumberInput("#inputTotal69BisAmount");
        self.inputTotal69TreAmount = new NumberInput("#inputTotal69TreAmount");
        self.inputTotalCashAmount = new NumberInput("#inputTotalCashAmount");
        self.inputTotalChequeAmount = new NumberInput("#inputTotalChequeAmount");
        self.inputTotalCheque = new NumberInput("#inputTotalCheque", 0);
        self.inputTotalCreditCardAmount = new NumberInput("#inputTotalCreditCardAmount");
        self.inputTotalCreditCard = new NumberInput("#inputTotalCreditCard", 0);
        self.inputTotalMoneyOrderAmount = new NumberInput("#inputTotalMoneyOrderAmount");
        self.inputTotalMoneyOrder = new NumberInput("#inputTotalMoneyOrder", 0);
        self.inputTotalBankTxnfAmount = new NumberInput("#inputTotalBankTxnfAmount");
        self.inputTotalBankTxnf = new NumberInput("#inputTotalBankTxnf", 0);
        self.inputTotalForeignBankTxnfAmount = new NumberInput("#inputTotalForeignBankTxnfAmount");
        self.inputTotalForeignBankTxnf = new NumberInput("#inputTotalForeignBankTxnf", 0);
        self.inputTotalBillExchangeAmount = new NumberInput("#inputTotalBillExchangeAmount");
        self.inputTotalBillExchange = new NumberInput("#inputTotalBillExchange", 0);
        self.inputTotalCouponAmount = new NumberInput("#inputTotalCouponAmount");
        self.inputTotalCoupon = new NumberInput("#inputTotalCoupon", 0);
        self.inputTotalOtherAmount = new NumberInput("#inputTotalOtherAmount");
        self.inputTotalOther = new NumberInput("#inputTotalOther", 0);
        self.inputTotalPaidReceiptWithTaxInvoice = new NumberInput("#inputTotalPaidReceiptWithTaxInvoice", 0);
        self.inputTotalCancelledReceiptWithTaxInvoice = new NumberInput("#inputTotalCancelledReceiptWithTaxInvoice", 0);
        self.inputTotalPaidReceipt = new NumberInput("#inputTotalPaidReceipt", 0);
        self.inputTotalCancelledReceipt = new NumberInput("#inputTotalCancelledReceipt", 0);
        self.closeStatus = new Input("#closeStatus");
        self.closeStatusBackDt = new Input("#closeStatusBackDt");
        self.saveClosingBackDT = new Button("#saveClosingBackDT");
        self.btnToConfirmCloseEmp = new Button("#btnToConfirmCloseEmp");
        self.btnCancelCloseEmp = new Button("#btnCancelCloseEmp");
        self.buttonUserAllClosing = new Button("#buttonUserAllClosing");
        self.inquiryShopClosingBackdate = new Button("#inquiryShopClosingBackdate");
        self.viewClosingList = new Button("#viewClosingList");
        self.reprintTransMoneyReceipt = new Button("#reprintTransMoneyReceipt");
        self.reprintTransMoneyReceiptBackDt = new Button("#reprintTransMoneyReceiptBackDt");

        self.histTotalWt3tredBackDateId = new NumberInput("#histTotalWt3tredBackDateId", 0);
        self.histTotalWt69bisBackDateId = new NumberInput("#histTotalWt69bisBackDateId", 0);
        self.histTotalWt69treBackDateId = new NumberInput("#histTotalWt69treBackDateId", 0);
        self.histTotalTransferBackDateId = new NumberInput("#histTotalTransferBackDateId", 0);
        self.histTotalAllSummaryBackDateId = new NumberInput("#histTotalAllSummaryBackDateId", 0);
        self.histSummaryFooterBackDate = new NumberInput("#histSummaryFooterBackDate", 0);

        this.dialogConfirmClosing = $("#confirmClosing");
        this.dialogConfirmClosingBackDate = $("#confirmClosingBackDate");
        this.dialogConfirmCloseEmp = $("#confirmCloseEmp");
        this.dialogAlertCloseEmpAll = $("#alertCloseEmpAll");
        this.dialogbdEmpClose = $("#bdEmpClose");
        this.dialogAlertUserAllClosing = $("#alertUserAllClosing");

        return this;
    })(jQuery);
    function buttonSearchClickEvent() {
// 	$('#displayCloseBranchPanel').removeClass('hide');
        $('#buttonCloseBranch').removeClass('disabled');
        get("../closeBranch.json", { closeDate: $("#startDate").val().replace(/([0-9]{2}).([0-9]{2}).([0-9]{4})/g,"$1-$2-$3") }, function(res){
            var data = res.data[0];
            if("0.00" == data.totalPaymentAmount) { $('#buttonCloseBranch').addClass('disabled'); }
            view.inputOfficerName.val(data.officerName);
            view.inputPageNo.val(data.pageNo);
            view.inputPOSMachineName.val(data.posMachineName);
            view.inputCurrentDate.val(view.utils.dateFormat(data.currentDttm));
            view.inputCurrentTime.val(view.utils.timeFormat(data.currentDttm));
            view.inputBranch.val(data.branch);
            view.inputTotalPaymentAmount.val(data.totalPaymentAmount);
            view.inputTotal3TredecimAmount.val(data.total3TredecimAmount);
            view.inputTotal69BisAmount.val(data.total69BisAmount);
            view.inputTotal69TreAmount.val(data.total69TreAmount);
            view.inputTotalCashAmount.val(data.totalCashAmount);
            view.inputTotalChequeAmount.val(data.totalChequeAmount);
            view.inputTotalCheque.val(data.totalCheque);
            view.inputTotalCreditCardAmount.val(data.totalCreditCardAmount);
            view.inputTotalCreditCard.val(data.totalCreditCard);
            view.inputTotalMoneyOrderAmount.val(data.totalMoneyOrderAmount);
            view.inputTotalMoneyOrder.val(data.totalMoneyOrder);
            view.inputTotalBankTxnfAmount.val(data.totalBankTxnfAmount);
            view.inputTotalBankTxnf.val(data.totalBankTxnf);
            view.inputTotalForeignBankTxnfAmount.val(data.totalForeignBankTxnfAmount);
            view.inputTotalForeignBankTxnf.val(data.totalForeignBankTxnf);
            view.inputTotalBillExchangeAmount.val(data.totalBillExchangeAmount);
            view.inputTotalBillExchange.val(data.totalBillExchange);
            view.inputTotalOtherAmount.val(data.totalOtherAmount);
            view.inputTotalOther.val(data.totalOther);
            view.inputTotalPaidReceiptWithTaxInvoice.val(data.totalPaidReceiptWithTaxInvoice);
            view.inputTotalCancelledReceiptWithTaxInvoice.val(data.totalCancelledReceiptWithTaxInvoice);
            view.inputTotalPaidReceipt.val(data.totalPaidReceipt);
            view.inputTotalCancelledReceipt.val(data.totalCancelledReceipt);
        }, view.dialogMainMessage);
    }
    /*function buttonCloseBranchClickEvent() {
     $.get("../processEndOfDay.json", { closeDate: $("#startDate").val().replace(/([0-9]{2}).([0-9]{2}).([0-9]{4})/g,"$1-$2-$3") }, function(res) {
     location.href = "./logout";
     });
     }*/
    function clearData(){
        $('#btnToClosing').attr('disabled', 'disabled');
        $('#summaryFooter').attr('hidden', 'hidden');
        view.closeStatus.val("");
        $('#tableTotalbyEmp').bootstrapTable('removeAll');
        $('#tableTotalbyPos').bootstrapTable('removeAll');
        /*view.inputOfficerName.val("");
         view.inputPageNo.val("");
         view.inputPOSMachineName.val("");
         view.inputCurrentDate.val(view.utils.dateFormat(""));
         view.inputCurrentTime.val("");
         view.inputBranch.val("");

         view.inputTotalPaymentAmount.val(0);
         view.inputTotal3TredecimAmount.val(0);
         view.inputTotal69BisAmount.val(0);
         view.inputTotal69TreAmount.val(0);
         view.inputTotalCashAmount.val(0);
         view.inputTotalChequeAmount.val(0);
         view.inputTotalCheque.val(0);
         view.inputTotalCreditCardAmount.val(0);
         view.inputTotalCreditCard.val(0);
         view.inputTotalMoneyOrderAmount.val(0);
         view.inputTotalMoneyOrder.val(0);
         view.inputTotalBankTxnfAmount.val(0);
         view.inputTotalBankTxnf.val(0);
         view.inputTotalForeignBankTxnfAmount.val(0);
         view.inputTotalForeignBankTxnf.val(0);
         view.inputTotalBillExchangeAmount.val(0);
         view.inputTotalBillExchange.val(0);
         view.inputTotalOtherAmount.val(0);
         view.inputTotalOther.val(0);
         view.inputTotalPaidReceiptWithTaxInvoice.val(0);
         view.inputTotalCancelledReceiptWithTaxInvoice.val(0);
         view.inputTotalPaidReceipt.val(0);
         view.inputTotalCancelledReceipt.val(0);*/
    }
    function setup(){
        var username = '${pageContext.request.userPrincipal.name}';
        searchBackDateItems();
        //$("#btnToClosing").prop("disabled", true);
        //alert(username);
        /*var posByEmpList;
         $.get("../service/end-day-closing/search/empCode", { "empCode": username, "docStatus":"W" }, function(res) {
         //console.log('endDayClosing5555xxx09052017');
         console.log(res);
         //console.log('endDayClosing5555xxx09052017');
         posByEmpList = res._embedded.endDayClosings/!*._embedded.officers[0]*!/;
         $("#DailyPaymentReport").bootstrapTable("load", posByEmpList);


         });*/
    }
    function endDayCloseDtl(val, row, ind){
        //console.log('555xxx');console.log(val);console.log(row);console.log(ind);console.log('555xxx');
        return '<span class="glyphicon glyphicon-edit" style="cursor: pointer;" onclick=accessDtl('+JSON.stringify(row)+','+ind+')></span>';
    }
    function accessDtl(data, ind){
        //console.log('555xxx');console.log(data); console.log(ind);console.log('555xxx');
        view.inputClosingId.val(data.id);
//        view.inputOfficerName.val(data.empCode);
        view.inputOfficerName.val(data.userName);
        view.inputCurrentDate.val(data.closingDate);
        view.inputPOSMachineName.val(data.macNo);
        view.inputBranch.val(data.branchCode);

        view.inputTotalPaymentAmount.val(data.totalCharge);
        view.inputTotal3TredecimAmount.val(data.totalWt3tred);
        view.inputTotal69BisAmount.val(data.totalWt69bis);
        view.inputTotal69TreAmount.val(data.totalWt69tre);

        view.inputTotalCashAmount.val(data.totalCash);
        view.inputTotalChequeAmount.val(data.totalCheque);
        view.inputTotalCheque.val(data.totalChequeCount);
        view.inputTotalCreditCardAmount.val(data.totalCredit);
        view.inputTotalCreditCard.val(data.totalCreditCnt);
        view.inputTotalMoneyOrderAmount.val(data.totalMoneyOrder);
        view.inputTotalMoneyOrder.val(data.totalMoneyOrdCnt);
        view.inputTotalBankTxnfAmount.val(data.totalMoneyTransfer);
        view.inputTotalBankTxnf.val(data.totalMoneyTrnsCnt);
        view.inputTotalForeignBankTxnfAmount.val(data.totalForeignTransfer);
        view.inputTotalForeignBankTxnf.val(data.totalForeignTrnsCnt);
        view.inputTotalBillExchangeAmount.val(data.totalBillExchange);
        view.inputTotalBillExchange.val(data.totalBillExchCnt);
        view.inputTotalCouponAmount.val(data.totalCoupon);
        view.inputTotalCoupon.val(data.totalCouponCount);
        view.inputTotalOtherAmount.val(data.totalOther);
        view.inputTotalOther.val(data.totalOtherCount);

        view.inputTotalPaidReceiptWithTaxInvoice.val(data.totalReceiptCount);
        view.inputTotalCancelledReceiptWithTaxInvoice.val(data.totalCancelCnt);

        $("#dtlPanel").removeClass("hide");
        //$("#btnToClosing").removeProp("disabled");
        //$(this).addClass('selected');
        var rows = $('tr', $("#DailyPaymentReport"));
        rows.removeClass('selected2');
        rows.eq(ind+1).addClass('selected2');
    }
    function desStatus(val, row, ind){
        if(val == "W"){
            return "OPEN";
        }else if(val == "S"){
            return "CLOSED";
        }else{
            return val;
        }
    }
    function dateFormatter(val, row, ind){
        return view.utils.dateFormat(val);
    }

    function buttonCloseBranchClickEvent(){
        view.message.clear();
        view.dialogConfirmClosing.modal("hide");
        var params = {
            "searchDate" : $("#transactionDate").val(),
            //"endDayClosingList": endDayClosingList
        }
        //console.log('aaabbb555');console.log(params);console.log('aaabbb555');
        $.ajax({
            url: "../createShopClosing.json"
            ,type: "POST"
            ,data: params
            , success: function(res){
                console.log(res);
                setup();

                console.log(res);
                window.empClosingList = res;
                shopClosingId = window.empClosingList.id;
                branchCode = window.empClosingList.branchCode;
                console.log("shopClosingId : "+shopClosingId);
                console.log("branchCode : "+branchCode);
                var subParams = {
                    "shopClosingId" : shopClosingId,
                    "branchCode" : branchCode,
                    "searchDate" : $("#transactionDate").val(),
                    "backDateStatus": "N"
                };
                console.log("subParams");
                console.log(subParams);
                $.ajax({
                    type: "POST",
                    url: "../updateShopClosing.json",
                    data: subParams,
                    success: function (data) {
                        console.log("======================1");
                        console.log(data);
                        if (!data) {
                            view.message.clear().error(["ระบบทำการปิดบัญชีไม่สำเร็จ"]).show();
                        }
                        console.log("======================2");

                    }
                });
                view.message.clear().success(["ระบบทำการปิดบัญชีสำเร็จ"]).show();
                $(document.body).append('<form id="prindShopClosingId" action="../printShopDayEndClosing.pdf" method="GET" target="_blank"></form>');
                var form2 = document.getElementById("prindShopClosingId");//document.forms[0];
                function input(n,v) { var input = document.createElement("input"); input.type = "hidden"; input.name = n; input.value = v; return input }

                form2.appendChild(input("shopClosingId", res.id));
                form2.submit();
                clearData();
                //view.message.focus();
            }
        });
    }
    function summaryEachEmp(val, row, ind){
        //console.log('aaaa'); console.log(val); console.log(row);console.log(ind);console.log('bbbb');
        var sumByEmp = 0;
        sumByEmp = row.totalCash+row.totalCheque+row.totalCredit+row.totalMoneyOrder+row.totalBillExchange+row.totalMoneyTransfer+row.totalCoupon+row.totalOffset+row.totalForeignTransfer+row.totalWt3tred+row.totalWt69bis+row.totalWt69tre+row.agentTotalAmount;
        return showNumberFormat(sumByEmp);
    }
    function summaryEachEmpBackDate(val, row, ind){
        var sumByEmp = 0;
        sumByEmp = row.backDateTotalAmount+row.totalWt3tred+row.totalWt69bis+row.totalWt69tre+row.transGfmisTotalAmount+row.transForeignThTotalAmount;
//        alert(showNumberFormat(sumByEmp));
        return showNumberFormat(sumByEmp);
    }
    function summaryEachEmpBackDate2(val, row, ind){
        console.log(" Check Row >>>>");console.log(row);console.log("<<<< Check Row");
        row.totalSumByEmp = row.backDateTotalAmount+row.totalWt3tred+row.totalWt69bis+row.totalWt69tre+row.transGfmisTotalAmount+row.transForeignThTotalAmount;
        return showNumberFormat(row.totalSumByEmp);
    }
    function showNumberFormat(val){
        return !$.isNumeric(stripToNumber(val)) ? "0.00" : parseFloat(val, 10).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,') ;
    }
    /*function sumTotalCash(){
     return showNumberFormat(summaryCash);
     }*/
    $(document).on("click", "a#btnToClosing0", function() {

        view.dialogConfirmCloseEmp.modal("hide");
        var params = {
            "searchDate" : searchDate,//$("#transactionDate").val()//,
            //"employees" : employees
        }
        $.each(employees, function(i, o){
            if (o.shopClosingId == null){
//                params["employees["+i+"].empCode"] = o.empCode;
                params["employees["+i+"].empCode"] = o.userName;
                params["employees["+i+"].branchCode"] = o.branchCode;
                params["employees["+i+"].closingId"] = o.closingId;
            }
        });

        //console.log('xxxx555');console.log(params);console.log('xxxx555');
        $.ajax({
            url: "../createEndDayClosingEmpAll.json"
            ,type: "POST"
            ,data: params
            ,async: false
            //,error: view.dialog.mainMessageDialog.valid
            , success: function(res){
                view.dialogConfirmClosing.modal("show");
                console.log('responseResult5555');
                console.log(res);console.log('responseResult5555');
                setup();
                //view.message.clear().success(["ระบบทำการบันทึกข้อมูลสำเร็จ"]).show();
                //clearData();
                searchByDate();
                $(document.body).append('<form id="printEmpClosingId" action="../printEmpDayEndClosing.pdf" method="POST" target="_printForm"></form>');
                var form1 = document.getElementById("printEmpClosingId");//document.forms[0];
                function input(n,v) { var input = document.createElement("input"); input.type = "hidden"; input.name = n; input.value = v; return input }
                if (res.empClosingList) {
                    console.log(" if >>> res.empClosingList");
                    console.log(res.empClosingList);
                    $.each(res.empClosingList, function(i, o){
                        form1.appendChild(input("empClosings["+i+"].id", o.id));
                    });
                }else{
                    console.log(" else >>> res");
                    console.log(res);
                    $.each(res, function(i, o){
                        form1.appendChild(input("empClosings["+i+"].id", o.id));
                    });
                }
                form1.submit();

            }
        });

    });
    $(document).on("click", "a#btnToClosing", function() {
        if(isOneBackDateNotClose){
            var tb = $("#tableTotalbyEmp").bootstrapTable("getData"), flgCheck = false;
            var sumByEmp = 0,tmpSumOpen = 0, tmpSumClose = 0, sunAllCloseShop = 0;
            $.each(tb, function(i, o){
                if(o.docStatus == 'W' ){
                    tmpSumOpen += calculateCloseEmp(o);
                }else {
                    tmpSumClose += calculateCloseEmp(o);
                }

                if(o.shopClosingId1 == null) {
                    sunAllCloseShop += calculateCloseEmp(o);
                }

                if(o.docStatus == 'W'){
                    flgCheck = true;
                }

            });

            if (flgCheck) {
                sumByEmp = tmpSumOpen;
                view.dialogAlertCloseEmpAll.modal("show");
            }else{
                sumByEmp = tmpSumClose;
                view.dialogConfirmClosing.modal("show");
            }

            document.getElementById('lbsumUser').innerHTML = showNumberFormat(sumByEmp);
            document.getElementById('lbSUser').innerHTML = showNumberFormat(sunAllCloseShop);
        }

    });

    function calculateCloseEmp(o) {
        return o.totalCash+o.totalCheque+o.totalCredit+o.totalMoneyOrder+o.totalBillExchange+o.totalMoneyTransfer+o.totalCoupon+o.totalOffset+o.totalForeignTransfer+o.totalWt3tred+o.totalWt69bis+o.totalWt69tre+o.agentTotalAmount;
    }

    $(document).on("click", "#btnToClosingBackDate", function() {
        $table = $('#tableTotalbyEmpBackDate');
        var ids = $.map($table.bootstrapTable('getSelections'), function(row) {
            return row.totalSumByEmp;
        });

        var sum = 0.00;
        $.each(ids, function(i, o){
            sum += parseFloat(o);
        });

        if (sum > 0) {
            document.getElementById('lableSum').innerHTML = showNumberFormat(sum);
            view.dialogConfirmClosingBackDate.modal("show");
        }

    });

    /*$(document).on("click", "#alertBackDateCloseBtnId", function() {
        validateStep2();
    });*/
    $(document).on("click", "#buttonCloseBranchBackDate", function() {
        view.message.clear()
        var data = $('#tableTotalbyEmpBackDate').bootstrapTable('getData');
        var params = {"searchDate" : $("#transactionDate").val()}
        $.each(data, function(i, o) {
            console.log(" >> DATA <<");
            console.log(o);
            params["employees["+ i +"].checked"] = document.getElementsByName("btSelectItem")[i].checked;
            params["employees["+ i +"].closingId"] = o.id;
            params["employees["+ i +"].receiptDate"] = o.receiptDtStr;
            params["employees["+ i +"].branchCode"] = o.branchCode;
//            params["employees["+ i +"].closingEmpCode"] = o.empCode;
            params["employees["+ i +"].closingEmpCode"] = o.userName;
        });
        console.log(" >>>> params");
        console.log(params);

        $.ajax({
            url: "../createShopClosingBackDate.json"
            ,type: "POST"
            ,data: params
            , success: function(res){
                //setup();

                console.log("resp >>> Back date");
                console.log(res);
                console.log("resp <<< Back date");

                searchBackDateItems();
                view.dialogConfirmClosingBackDate.modal("hide");
                view.message.clear().success(["ระบบทำการปิดบัญชีสำเร็จ"]).show();
                $('#btnToClosingBackDate').attr("disabled", false);

                window.empClosingList = res;
                shopClosingId = window.empClosingList.id;
                branchCode = window.empClosingList.branchCode;

                var subParams = {
                    "shopClosingId" : shopClosingId,
                    "branchCode" : branchCode,
                    "searchDate" : $("#transactionDate").val(),
                    "backDateStatus": "Y"
                };
                console.log("subParams");
                console.log(subParams);
                $.ajax({
                    type: "POST",
                    url: "../updateShopClosing.json",
                    data: subParams,
                    success: function (data) {
                        console.log("++++++++++++++++++++555");
                        console.log(data);
                        if (!data) {
                            view.message.clear().error(["ระบบอัพเดทข้อมูลไม่สำเร็จ"]).show();
                        }
                        console.log("++++++++++++++++++++666");
                    }
                });

                $(document.body).append('<form id="prindShopClosingId" action="../printShopDayEndClosing.pdf" method="GET" target="_blank"></form>');
                var form2 = document.getElementById("prindShopClosingId");//document.forms[0];
                function input(n,v) { var input = document.createElement("input"); input.type = "hidden"; input.name = n; input.value = v; return input }

                form2.appendChild(input("shopClosingId", res.id));
                form2.submit();
                clearData();
                //view.message.focus();
            }
        });
    });
    /*function validateStep2(){
        if(validateIfCloseAll()){
            view.dialogConfirmClosing.modal("show");
            view.dialogConfirmCloseEmp.modal("hide");
        }else{
            view.dialogConfirmClosing.modal("hide");
            view.dialogConfirmCloseEmp.modal("show");
        }
    }
    function validateIfCloseAll(){
        if(employees.length > 0){
            return false;
        }else{
            return true;
        }
    }*/
    function closeButtonFormatter(val, row, ind){
        //alert(row.empCode);
        if(row.docStatus == 'W'){
            //return "<a class='btn btn-success' id='bdEmpClose"+ind+"' data-index='"+ind+"' data-emp-code='"+row.empCode+"' data-receipt-date='"+row.receiptDtStr+"'>ปิดบัญชี</a>";
//            return "<a class='btn btn-success' onclick=alertCloseBackDt('"+row.empCode+"','"+row.receiptDtStr+"','"+row.branchCode+"')>ปิดบัญชี User</a>";
            return "<a class='btn btn-success' onclick=alertCloseBackDt('"+row.empCode+"','"+row.userName+"','"+row.receiptDtStr+"','"+row.branchCode+"')>ปิดบัญชี User</a>";
            //        return "<a class='btn btn-success' id='bdEmpClose'>ปิดบัญชี</a>";	}else{
        } else {
            return "<a class='default-link glyphicon glyphicon-ok'></a>";
        }
    }

    function alertCloseBackDt(empCode, userName, receiptDate, branchCode) {
        view.dialogbdEmpClose.modal("show");
        EmpCode = empCode;
        UserName = userName;
        ReceiptDate = receiptDate;
        BranchCode = branchCode;

        $(document).on("click", "#saveClosingBackDT", function() {
            view.dialogbdEmpClose.modal("hide");

            saveBackDateEmpClose();

        });
    }

    function saveBackDateEmpClose(){
        var params = {
            "searchDate" : ReceiptDate,
            "empCode": EmpCode,
            "userName": UserName,
            "branchCode": BranchCode,
            "backDateStatus": "Y"
        }
        console.log("params ++++++>");
        console.log(params);

        $.ajax({
            url: "../createEndDayClosingEmp.json"
            ,type: "POST"
            ,data: params
            , async: false
            , success: function(res){
                console.log('responseResult5555');
                console.log(res);console.log('responseResult5555');

                $(document.body).append('<form action="../printEmpDayEndClosing.pdf" method="POST" target="_printForm"></form>');
                var form = document.forms[0]; function input(n,v) { var input = document.createElement("input"); input.type = "hidden"; input.name = n; input.value = v; return input }

                if (res.empClosingList) {
                    console.log(" if >>> res.empClosingList");
                    console.log(res.empClosingList);
                    $.each(res.empClosingList, function(i, o){
                        form.appendChild(input("empClosings["+i+"].id", o.id));
                    });
                }else{
                    console.log(" else >>> res");
                    console.log(res);
                    $.each(res, function(i, o){
                        form.appendChild(input("empClosings["+i+"].id", o.id));
                    });
//                    form.appendChild(input("empClosings[0].id", res.id));
//                    form.appendChild(input("closingId", res.id));
                }
                form.submit();
                searchBackDateItems();
            }
        });
    }

    function btnToConfirmCloseEmpClickEvent() {
        view.dialogConfirmCloseEmp.modal("show");
        view.dialogAlertCloseEmpAll.modal("hide");
//        validateStep2()
    }

    function btnCancelCloseEmpClickEvent() {
        view.dialogAlertCloseEmpAll.modal("hide");
    }

    function buttonUserAllClosingClickEvent() {
        view.dialogAlertCloseEmpAll.modal("hide");
        view.dialogAlertUserAllClosing.modal("hide");
//        validateStep2()
    }

    function inquiryShopClosingBackdateClickEvent() {
        view.message.clear();
        $('#tableHistTotalbyEmpBackDate').removeClass("hide").show(500);
        $('#tableTotalbyEmpBackDate').addClass("hide");
        view.viewClosingList.show(0);

        var summaryBackDate = 0, summaryWt3tredBackDate = 0, summaryWt69bisBackDate = 0, summaryWt69treBackDate = 0, summaryTotalAllBackDate = 0, summarytransGfmisBackDate = 0, summaryTransForeignThBackDate = 0, summaryTransBackDate = 0;
        var flgBackDateCloseShop = true;
        isOneBackDateNotClose = false;

        $.ajax({
            type: "GET",
            url: "../findShopClosingDate.json?startDateBackDate="+$("#startDateBackDate").val().replace(/([0-9]{2}).([0-9]{2}).([0-9]{4})/g,"$1-$2-$3"),
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            success:function(data){
                var flgStatus = true;
                var histtotalBackdateList = data.endDayClosingList.length;
                console.log('HistBackDate >>>'); console.log(data);console.log(data.endDayClosingList);console.log(data.endDayClosingList.length);console.log('<<< HistBackDate');
                $("#tableHistTotalbyEmpBackDate").bootstrapTable("load", data.endDayClosingList);
                if(histtotalBackdateList > 0){
                    $.each(data.endDayClosingList, function(i, o){
                        console.log("<===== if =====>"); console.log(o);
                        summaryBackDate += o.backDateTotalAmount;
                        summaryWt3tredBackDate += o.totalWt3tred;
                        summaryWt69bisBackDate += o.totalWt69bis;
                        summaryWt69treBackDate += o.totalWt69tre;

                        console.log("summarytransGfmisBackDate");
                        console.log(summarytransGfmisBackDate);
                        console.log("summaryTransForeignThBackDate");
                        console.log(summaryTransForeignThBackDate);
                        summaryTransBackDate += o.backDateTotalAmount;
                        summarytransGfmisBackDate += o.transGfmisTotalAmount;
                        summaryTransForeignThBackDate += o.transForeignThTotalAmount;
                        /*if(o.docStatus == 'S'){
                         flgBackDateCloseShop = false;
                         }else{
                         isOneBackDateNotClose = true;
                         }*/
                        console.log("Check ShopClosing Id : "+o.shopClosingId1);
                        /*if (o.shopClosingId1 == null) {
                         console.log("* shop id "+i+">>> "+o.shopClosingId1);
                         flgStatus = false;
                         }*/
                    });
                    summaryTotalAllBackDate = summaryWt69treBackDate+summaryWt3tredBackDate+summaryWt69bisBackDate+summaryBackDate+summarytransGfmisBackDate+summaryTransForeignThBackDate;
                    $("#histTotalWt3tredBackDateId").text(showNumberFormat(summaryWt3tredBackDate));
                    $("#histTotalWt69bisBackDateId").text(showNumberFormat(summaryWt69bisBackDate));
                    $("#histTotalWt69treBackDateId").text(showNumberFormat(summaryWt69treBackDate));
                    $("#histTotalTransferBackDateId").text(showNumberFormat(summaryBackDate));
                    $("#histTotalForeignThBackDateId").text(showNumberFormat(summaryTransForeignThBackDate));
                    $("#histTotalGfmisBackDateId").text(showNumberFormat(summarytransGfmisBackDate));
                    $("#histTotalAllSummaryBackDateId").text(showNumberFormat(summaryTotalAllBackDate));
                    $('#histSummaryFooterBackDate').show();

                    markCloseStatus(flgStatus);
                    $('#btnToClosingBackDate').attr('disabled', 'disabled');

                }else{
                    console.log("<===== eles =====>");
                    $('#histSummaryFooterBackDate').hide();
                    view.reprintTransMoneyReceiptBackDt.disable();
                }

                $('#btnToClosingBackDate').attr('disabled', 'disabled');
            }
        });

    }

    function viewClosingListClickEvent() {
        searchBackDateItems();
        $('#tableTotalbyEmpBackDate').removeClass("hide");
        $('#tableHistTotalbyEmpBackDate').addClass("hide");
        view.viewClosingList.hide(0);
    }

    function reprintTransMoneyReceiptClickEvent(){
        $.ajax({
            type: "GET",
            url: "../findEndDayClosingEmpGroup.json?startDate="+$("#startDate").val().replace(/([0-9]{2}).([0-9]{2}).([0-9]{4})/g,"$1-$2-$3"),
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            success:function(data){
                console.log('responseResult******');
                console.log(data);console.log('*****responseResult');

                $(document.body).append('<form id="prindShopClosingId" action="../reprintShopDayEndClosing.pdf" method="GET" target="_blank"></form>');
                var form2 = document.getElementById("prindShopClosingId");//document.forms[0];
                function input(n,v) { var input = document.createElement("input"); input.type = "hidden"; input.name = n; input.value = v; return input }

                /*console.log('>>>> data.shopClosingId1 ' +data.shopClosingId1);
                 form2.appendChild(input("shopClosingIdStr", data.shopClosingId1));
                 form2.submit();*/

                var endDayClosingList = data.endDayClosingList;
                console.log(" endDayClosingList >> "+endDayClosingList);
                if (endDayClosingList.length >0) {
                    var shopClosingId = null;
                    shopClosingId = data.endDayClosingList[0].shopClosingId1;
                    if (shopClosingId != null) {
                        form2.appendChild(input("shopClosingIdStr", shopClosingId));
                        form2.submit();
                        form2.remove();
                    }
                }
            }
        });

    }

    function reprintTransMoneyReceiptBackDtClickEvent(){

        $.ajax({
            type: "GET",
            url: "../findShopIdBackDate.json?startDateBackDate="+$("#startDateBackDate").val().replace(/([0-9]{2}).([0-9]{2}).([0-9]{4})/g,"$1-$2-$3"),
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            success:function(data){
                console.log('responseResult>>>>>>');
                console.log(data);console.log('<<<<<<responseResult');

                $(document.body).append('<form id="prindShopClosingId" action="../reprintShopDayEndClosing.pdf" method="GET" target="_blank"></form>');
                var form2 = document.getElementById("prindShopClosingId");//document.forms[0];
                function input(n,v) { var input = document.createElement("input"); input.type = "hidden"; input.name = n; input.value = v; return input }

                var endDayClosingList = data.endDayClosingList;
                console.log(" endDayClosingList >> "+endDayClosingList);
                if (endDayClosingList.length >0) {
                    var shopClosingId = null;
                    shopClosingId = data.endDayClosingList[0].shopClosingId1;
                    if (shopClosingId != null) {
                        form2.appendChild(input("shopClosingIdStr", shopClosingId));
                        form2.submit();
                        form2.remove();
                    }
                }

            }
        });

    }

    function markCloseStatus(flgStatus){
        if (flgStatus) {
            console.log("====>if");
            view.closeStatusBackDt.val("CLOSED")
            view.reprintTransMoneyReceiptBackDt.enable();
            $('#btnToClosingBackDate').attr('disabled', 'disabled');
        } else {
            console.log("====>else");
            view.closeStatusBackDt.val("OPEN")
            view.reprintTransMoneyReceiptBackDt.disable();
            $('#btnToClosingBackDate').removeAttr('disabled', 'disabled');
            isOneBackDateNotClose = true;
        }
    }

    /* $('#tableTotalbyEmpBackDate').on('check.bs.table', function (e, row, $element) {

     $($element).toggleClass('success');
     });
     */
    function stateFormatter(value, row, index) {

        if (row.docStatus == 'W') {
            checked = true;
            return {
                checked: false,
                disabled : true
            }
        } else {
            checked = false;
            return {
                checked: true,
                disabled : false
            }
        }

        return value;
    }


    $(function () {

        var $table = $('#tableTotalbyEmpBackDate,#tableTotalbyEmp,#tableHistTotalbyEmpBackDate');

        $table.on('click-row.bs.table', function (e, row, $element) {
            $('.info').removeClass('info');
            $($element).addClass('info');
        })
        .on('dbl-click-row.bs.table', function (e, row, $element) {
            $('.info').removeClass('info');
            $($element).removeClass('info');
        });


    });




</script>