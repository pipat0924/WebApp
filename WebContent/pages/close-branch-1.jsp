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
                $('#stDate,#stDateTransac').datepicker({
                    format: 'dd/mm/yyyy',
                    lang: 'th',
                    startDate: '01/01/2016',
                    endDate: '30/12/2025'
                });

// 		        $('#displayCloseBranchPanel').addClass('hide');
                $('#stDateTransac').datepicker('setDate', new Date());
                $('#stDateTransac').datepicker('update');
                $('#stDateTransac').val('');
            });
        </script>
        <style>
            .selected2 {
                background-color: yellow;
            }
        </style>
    </head>
    <body>
    <section class="container-fluid menu">
        <div id="mainMessageDialog"></div>
        <div id="message"></div>
        <div id="displayCloseBranchPanel">
            <div class="col-md-12">
                <div class="panel panel-default">
                    <div class="panel-heading"><span class="glyphicon glyphicon-list-alt"></span> ปิดบัญชีประจำวัน</div>
                    <div id="dialogMainMessage"></div>
                    <div class="form-horizontal">
                        <div class="form-group padding-top">
                            <div class="form-group">
                                <label class="control-label col-sm-2">วันที่รับชำระ:</label>
                                <div class="col-sm-2">
                                    <input id="transactionDate" class="hide">
                                    <div class="input-group input-append date" id="stDate">
                                        <input type="text" class="form-control" id="startDate" name="startDate" value="">
                                        <span class="input-group-addon add-on"><span class="glyphicon glyphicon-calendar"></span></span>
                                    </div>
                                </div>
                                <label id="labelTransDate" class="control-label col-sm-1 hide">วันที่ทำรายการ:</label>
                                <div id="inputTransDate"class="col-sm-2 hide">
                                    <input id="transactionDateSdt" class="hide">
                                    <div class="input-group input-append date" id="stDateTransac">
                                        <input type="text" class="form-control" id="stDateTrans" name="startDate" value="">
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
                                <div class="col-sm-1 col-offset-2 text-right">
                                    <a id="reprintTransMoneyReceipt" class="btn btn-info disabled"><i class="glyphicon glyphicon-print"></i>  พิมพ์ใบส่งเงิน</a>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-10 col-md-offset-1">
                                    <%--<div class="table-responsive">
                                        <table id="DailyPaymentReport"
                                          data-toggle="table"
                                          data-single-select="true"
                                          data-classes="table">
                                          <thead>
                                              <tr>
                                                   <th data-align="center" data-formatter="runningFormatter">#</th>
                                                   &lt;%&ndash;<th data-align="center" data-sortable="true" data-field="closingDate">วันที่รับชำระ </th>&ndash;%&gt;
                                                   <th data-align="center" data-sortable="true" data-field="macNo">POS No.</th>
                                                  <th data-align="center" data-sortable="true" data-field="totalPaymentAmount">ยอดเงินรับชำระ </th>
                                                  &lt;%&ndash;<th data-align="center" data-sortable="true" data-formatter="endDayCloseDtl"></th>&ndash;%&gt;
                                               </tr>
                                           </thead>
                                           &lt;%&ndash;<tbody>
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
                                           </tbody>&ndash;%&gt;
                                          </table>
                                      </div>--%>
                                </div>
                            </div>
                        </div>

                        <div id="dtlPanel">

                            <div class="form-group padding-top alert alert-warning removeMargin"  style="margin-right: 0;margin-left:0;">
                                <div class="form-group">
                                    <input hidden value="" id="inputClosingId" />
                                    <label class="control-label col-sm-3">เจ้าหน้าที่รับชำระ :</label>
                                    <div class="col-sm-3"><input class="form-control" id="inputOfficerName" readonly ></div>
                                    <label class="control-label col-sm-2">วันที่ปิดรอบ :</label>
                                    <div class="col-sm-3"><input class="form-control" id="inputCurrentDate" readonly ></div>
                                </div>
                                <input id="inputBranchArea" hidden>
                                <div class="form-group" hidden>
                                    <label class="control-label col-sm-3">เครื่องสำหรับปิดบัญชี:</label>
                                    <div class="col-sm-3"><input class="form-control" id="inputPOSMachineName" readonly></div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-3">สถานที่รับชำระ :</label>
                                    <div class="col-sm-3"><input class="form-control" id="inputBranch" readonly ></div>
                                    <label class="control-label col-sm-2">เวลาปิดรอบ :</label>
                                    <div class="col-sm-3"><input class="form-control" id="inputCurrentTime" readonly ></div>
                                </div>
                            </div>
                            <div id="CurrencyMoneyTransferPanel" class="form-group">
                                <div class="form-group">
                                    <label class="control-label col-sm-3">เงินโอนในประเทศ</label>
                                    <div class="col-sm-1"><label class="control-label">=</label></div>
                                    <div class="col-sm-2"><input class="form-control text-right" id="inputTotalBackDateAmount" readonly></div>
                                    <div class="col-sm-1"><label class="control-label">บาท</label></div>
                                    <label class="control-label col-sm-2">จำนวน</label>
                                    <div class="col-sm-2"><input class="form-control text-right" id="inputTotalBackDate" readonly></div>
                                    <div class="col-sm-1"><label class="control-label">รายการ</label></div>
                                    <%--<label class="control-label col-sm-3"></label>--%>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-3">เงินโอนต่างประเทศ (USD)</label>
                                    <div class="col-sm-1"><label class="control-label">=</label></div>
                                    <div class="col-sm-1"><input class="form-control text-right" id="inputTotalUsAmount" readonly></div>
                                    <div class="col-sm-1"><input class="form-control text-right" id="inputTotalUsToThAmount" readonly></div>
                                    <div class="col-sm-1"><label class="control-label">บาท</label></div>
                                    <label class="control-label col-sm-2">จำนวน</label>
                                    <div class="col-sm-2"><input class="form-control text-right" id="inputTotalUs" readonly></div>
                                    <div class="col-sm-1"><label class="control-label">รายการ</label></div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-3">(EUR)</label>
                                    <div class="col-sm-1"><label class="control-label">=</label></div>
                                    <div class="col-sm-1"><input class="form-control text-right" id="inputTotalEuAmount" readonly></div>
                                    <div class="col-sm-1"><input class="form-control text-right" id="inputTotalEuTothAmount" readonly></div>
                                    <div class="col-sm-1"><label class="control-label">บาท</label></div>
                                    <label class="control-label col-sm-2">จำนวน</label>
                                    <div class="col-sm-2"><input class="form-control text-right" id="inputTotalEur" readonly></div>
                                    <div class="col-sm-1"><label class="control-label">รายการ</label></div>
                                </div>
                            </div>

                            <%--back date เงินโอนในประเทศ(ยต.)--%>
                            <div id="backDateTransferInPanel" class="form-group">
                                <div class="form-group">
                                    <label class="control-label col-sm-3">เงินโอนในประเทศ</label>
                                    <div class="col-sm-1"><label class="control-label">=</label></div>
                                    <div class="col-sm-2"><input class="form-control text-right" id="inputTotalBackdateTransInAmount" readonly></div>
                                    <div class="col-sm-1"><label class="control-label">บาท</label></div>
                                    <label class="control-label col-sm-2">จำนวน</label>
                                    <div class="col-sm-2"><input class="form-control text-right" id="inputTotalBackDateTranIn" readonly></div>
                                    <div class="col-sm-1"><label class="control-label">รายการ</label></div>
                                    <%--<label class="control-label col-sm-3"></label>--%>
                                </div>
                            </div>


                            <div id="generalUserPanel" class="form-group" >
                                <div class="form-group">
                                    <label class="control-label col-sm-3">เงินสด</label>
                                    <div class="col-sm-1"><label class="control-label">=</label></div>
                                    <div class="col-sm-2"><input class="form-control" id="inputTotalCashAmount" readonly></div>
                                    <div class="col-sm-1"><label class="control-label">บาท</label></div>
                                    <label class="control-label col-sm-3"></label>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-3">เช็ค</label>
                                    <div class="col-sm-1"><label class="control-label">=</label></div>
                                    <div class="col-sm-2"><input class="form-control" id="inputTotalChequeAmount" readonly></div>
                                    <div class="col-sm-1"><label class="control-label">บาท</label></div>
                                    <label class="control-label col-sm-1">จำนวน</label>
                                    <div class="col-sm-2"><input class="form-control" id="inputTotalCheque" readonly></div>
                                    <div class="col-sm-1"><label class="control-label">ฉบับ</label></div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-3">บัตรเครดิต</label>
                                    <div class="col-sm-1"><label class="control-label">=</label></div>
                                    <div class="col-sm-2"><input class="form-control" id="inputTotalCreditCardAmount" readonly></div>
                                    <div class="col-sm-1"><label class="control-label">บาท</label></div>
                                    <label class="control-label col-sm-1">จำนวน</label>
                                    <div class="col-sm-2"><input class="form-control" id="inputTotalCreditCard" readonly></div>
                                    <div class="col-sm-1"><label class="control-label">รายการ</label></div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-3">ธนาณัติ</label>
                                    <div class="col-sm-1"><label class="control-label">=</label></div>
                                    <div class="col-sm-2"><input class="form-control" id="inputTotalMoneyOrderAmount" readonly></div>
                                    <div class="col-sm-1"><label class="control-label">บาท</label></div>
                                    <label class="control-label col-sm-1">จำนวน</label>
                                    <div class="col-sm-2"><input class="form-control" id="inputTotalMoneyOrder" readonly></div>
                                    <div class="col-sm-1"><label class="control-label">ฉบับ</label></div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-3">เงินโอนในประเทศ</label>
                                    <div class="col-sm-1"><label class="control-label">=</label></div>
                                    <div class="col-sm-2"><input class="form-control" id="inputTotalBankTxnfAmount" readonly ></div>
                                    <div class="col-sm-1"><label class="control-label">บาท</label></div>
                                    <label class="control-label col-sm-1">จำนวน</label>
                                    <div class="col-sm-2"><input class="form-control" id="inputTotalBankTxnf" readonly ></div>
                                    <div class="col-sm-1"><label class="control-label">รายการ</label></div>
                                </div>
                                <%--<div class="form-group">--%>
                                <%--<label class="control-label col-sm-3">เงินโอนต่างประเทศ</label>--%>
                                <%--<div class="col-sm-1"><label class="control-label">=</label></div>--%>
                                <%--<div class="col-sm-2"><input class="form-control" id="inputTotalForeignBankTxnfAmount" readonly ></div>--%>
                                <%--<div class="col-sm-1"><label class="control-label">บาท</label></div>--%>
                                <%--<label class="control-label col-sm-1">จำนวน</label>--%>
                                <%--<div class="col-sm-2"><input class="form-control" id="inputTotalForeignBankTxnf" readonly ></div>--%>
                                <%--<div class="col-sm-1"><label class="control-label">ฉบับ</label></div>--%>
                                <%--</div>--%>
                                <div class="form-group">
                                    <label class="control-label col-sm-3">ตั๋วแลกเงิน</label>
                                    <div class="col-sm-1"><label class="control-label">=</label></div>
                                    <div class="col-sm-2"><input class="form-control" id="inputTotalBillExchangeAmount" readonly></div>
                                    <div class="col-sm-1"><label class="control-label">บาท</label></div>
                                    <label class="control-label col-sm-1">จำนวน</label>
                                    <div class="col-sm-2"><input class="form-control" id="inputTotalBillExchange" readonly></div>
                                    <div class="col-sm-1"><label class="control-label">ฉบับ</label></div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-3">คูปอง</label>
                                    <div class="col-sm-1"><label class="control-label">=</label></div>
                                    <div class="col-sm-2"><input class="form-control" id="inputTotalCouponAmount" readonly></div>
                                    <div class="col-sm-1"><label class="control-label">บาท</label></div>
                                    <label class="control-label col-sm-1">จำนวน</label>
                                    <div class="col-sm-2"><input class="form-control" id="inputTotalCoupon" readonly></div>
                                    <div class="col-sm-1"><label class="control-label">ฉบับ</label></div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-3">offset</label>
                                    <div class="col-sm-1"><label class="control-label">=</label></div>
                                    <div class="col-sm-2"><input class="form-control" id="inputTotalOffsetAmount" readonly></div>
                                    <div class="col-sm-1"><label class="control-label">บาท</label></div>
                                    <label class="control-label col-sm-1">จำนวน</label>
                                    <div class="col-sm-2"><input class="form-control" id="inputTotalOffset" readonly></div>
                                    <div class="col-sm-1"><label class="control-label">ฉบับ</label></div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-3">อื่นๆ</label>
                                    <div class="col-sm-1"><label class="control-label">=</label></div>
                                    <div class="col-sm-2"><input class="form-control" id="inputTotalOtherAmount" readonly></div>
                                    <div class="col-sm-1"><label class="control-label">บาท</label></div>
                                    <label class="control-label col-sm-1">จำนวน</label>
                                    <div class="col-sm-2"><input class="form-control" id="inputTotalOther" readonly></div>
                                    <div class="col-sm-1"><label class="control-label">ฉบับ</label></div>
                                </div>
                                <%--<div class="form-group">
                                    <label class="control-label col-sm-3">ยอดรวมสุทธิ</label>
                                    <div class="col-sm-1"><label class="control-label">=</label></div>
                                    <div class="col-sm-2"><input class="form-control" id="inputTotalAmount" readonly></div>
                                    <div class="col-sm-1"><label class="control-label">บาท</label></div>
                                </div>--%>
                            </div>

                            <div class="form-group" id="backDateMoneyTransferPanel">
                                <div class="form-group" >
                                    <label class="control-label col-sm-3">เงินโอน GFMIS</label>
                                    <div class="col-sm-1"><label class="control-label">=</label></div>
                                    <div class="col-sm-2"><input class="form-control" id="inputTotalTransGfmisAmount" readonly></div>
                                    <div class="col-sm-1"><label class="control-label">บาท</label></div>
                                    <label class="control-label col-sm-2">จำนวน</label>
                                    <div class="col-sm-2"><input class="form-control" id="inputTotalTransGfmis" readonly></div>
                                    <div class="col-sm-1"><label class="control-label">รายการ</label></div>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="form-group">
                                    <label class="control-label col-sm-3">ภาษีเงินได้หัก ณ ที่จ่าย 3 เตรส</label>
                                    <div class="col-sm-1"><label class="control-label">=</label></div>
                                    <div class="col-sm-2"><input class="form-control" id="inputTotal3TredecimAmount" readonly ></div>
                                    <div class="col-sm-1"><label class="control-label">บาท</label></div>
                                    <label class="control-label col-sm-3"></label>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-3">ภาษีเงินได้หัก ณ ที่จ่าย 69 ทวิ</label>
                                    <div class="col-sm-1"><label class="control-label">=</label></div>
                                    <div class="col-sm-2"><input class="form-control" id="inputTotal69BisAmount" readonly ></div>
                                    <div class="col-sm-1"><label class="control-label">บาท</label></div>
                                    <label class="control-label col-sm-3"></label>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-3">ภาษีเงินได้หัก ณ ที่จ่าย 69 ตรี</label>
                                    <div class="col-sm-1"><label class="control-label">=</label></div>
                                    <div class="col-sm-2"><input class="form-control" id="inputTotal69TreAmount" readonly></div>
                                    <div class="col-sm-1"><label class="control-label">บาท</label></div>
                                    <label class="control-label col-sm-3"></label>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="form-group">
                                    <label class="control-label col-sm-3" style="font-size: 16px;">รวมชำระค่าใช้บริการของหน่วยงานอื่น</label>
                                    <div class="col-sm-1"><label class="control-label">=</label></div>
                                    <div class="col-sm-2"><input class="form-control" id="inputAgentTotalPaymentAmount" readonly ></div>
                                    <div class="col-sm-1"><label class="control-label">บาท</label></div>
                                    <label class="control-label col-sm-3"></label>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="form-group">
                                    <label class="control-label col-sm-3" style="font-size: 18px;">รวมชำระค่าใช้บริการทั้งสิ้น</label>
                                    <div class="col-sm-1"><label class="control-label">=</label></div>
                                    <div class="col-sm-2"><input class="form-control" id="inputTotalPaymentAmount" readonly ></div>
                                    <div class="col-sm-1"><label class="control-label">บาท</label></div>
                                    <label class="control-label col-sm-3"></label>
                                </div>
                            </div>

                            <div class="form-group padding-top alert alert-warning removeMargin" style="margin-right: 0;margin-left:0;">
                                <div class="form-group">
                                    <label class="control-label col-sm-3">จำนวนใบเสร็จรับเงิน/ใบกำกับภาษี</label>
                                    <div class="col-sm-1"><label class="control-label"></label></div>
                                    <div class="col-sm-2"><input class="form-control text-right" id="inputTotalPaidReceiptWithTaxInvoice" readonly=""></div>
                                    <div class="col-sm-1"><label class="control-label">ฉบับ</label></div>
                                    <label class="control-label col-sm-2">จำนวนใบเสร็จรับเงิน/ใบกำกับภาษี(ตัวแทน)</label>
                                    <div class="col-sm-2"><input class="form-control text-right" id="inputAgentTotalReceiptWithTaxInvoice" readonly=""></div>
                                    <div class="col-sm-1"><label class="control-label">ฉบับ</label></div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-3">จำนวนใบเสร็จรับเงิน/ใบกำกับภาษี (ที่ยกเลิก)</label>
                                    <div class="col-sm-1"><label class="control-label"></label></div>
                                    <div class="col-sm-2"><input class="form-control" id="inputTotalCancelledReceiptWithTaxInvoice" readonly></div>
                                    <div class="col-sm-1"><label class="control-label">ฉบับ</label></div>
                                    <label class="control-label col-sm-2">จำนวนใบเสร็จรับเงิน/ใบกำกับภาษีอย่างย่อ(ตัวแทน)</label>
                                    <div class="col-sm-2"><input class="form-control text-right" id="inputAgentTotalReceiptWithTaxInvoiceBrief" readonly=""></div>
                                    <div class="col-sm-1"><label class="control-label">ฉบับ</label></div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-3">จำนวนใบเสร็จรับเงิน/ใบกำกับภาษีอย่างย่อ</label>
                                    <div class="col-sm-1"><label class="control-label"></label></div>
                                    <div class="col-sm-2"><input class="form-control" id="inputTotalPaidReceiptWithTaxInvoiceBrief" readonly></div>
                                    <div class="col-sm-1"><label class="control-label">ฉบับ</label></div>
                                    <label class="control-label col-sm-3"></label>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-3">จำนวนใบเสร็จรับเงิน/ใบกำกับภาษีอย่างย่อ (ที่ยกเลิก)</label>
                                    <div class="col-sm-1"><label class="control-label"></label></div>
                                    <div class="col-sm-2"><input class="form-control" id="inputTotalCancelledReceiptWithTaxInvoiceBrief" readonly></div>
                                    <div class="col-sm-1"><label class="control-label">ฉบับ</label></div>
                                    <label class="control-label col-sm-3"></label>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-3">จำนวนใบกำกับภาษี</label>
                                    <div class="col-sm-1"><label class="control-label"></label></div>
                                    <div class="col-sm-2"><input class="form-control" id="inputTotalPaidTaxInvoice" readonly></div>
                                    <div class="col-sm-1"><label class="control-label">ฉบับ</label></div>
                                    <label class="control-label col-sm-3"></label>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-3">จำนวนใบกำกับภาษี (ที่ยกเลิก)</label>
                                    <div class="col-sm-1"><label class="control-label"></label></div>
                                    <div class="col-sm-2"><input class="form-control" id="inputTotalCancelledTaxInvoice" readonly></div>
                                    <div class="col-sm-1"><label class="control-label">ฉบับ</label></div>
                                    <label class="control-label col-sm-3"></label>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-3">จำนวนใบเสร็จรับเงิน</label>
                                    <div class="col-sm-1"><label class="control-label"></label></div>
                                    <div class="col-sm-2"><input class="form-control" id="inputTotalPaidReceipt" readonly ></div>
                                    <div class="col-sm-1"><label class="control-label">ฉบับ</label></div>
                                    <label class="control-label col-sm-3"></label>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-3">จำนวนใบเสร็จรับเงิน(ที่ยกเลิก)</label>
                                    <div class="col-sm-1"><label class="control-label"></label></div>
                                    <div class="col-sm-2"><input class="form-control" id="inputTotalCancelledReceipt" readonly></div>
                                    <div class="col-sm-1"><label class="control-label">ฉบับ</label></div>
                                    <label class="control-label col-sm-3"></label>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-12 margin-bott">
                                    <label class="control-label col-md-6"><a href="#confirmClosing" id="btnToClosing" data-toggle="modal" class="btn btn-success" disabled="disabled"><span class="glyphicon glyphicon-ok-circle"></span> ปิดบัญชี</a></label>
                                    <label class="control-label col-md-6 text-right"><a href="Dashboard.jsp" class="btn btn-black"><span class="glyphicon glyphicon-chevron-left"></span>&nbsp;&nbsp; กลับสู่หน้าจอหลัก</a></label>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
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
                                    <label class="col-sm-12 control-label " style="font-size: 25px; text-align: center"><span class="glyphicon glyphicon-question-sign"></span> ยืนยันทำการปิดบัญชี</label>
                                </div>
                                <div class="form-group">
                                    <label class="col-sm-12 control-label " style="text-align: center">ยอดรับชำระทั้งสิ้น: <span id="amtSummaryId"></span> บาท</label>
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

    </div>
    <div id="dialog" style="display: none" align="center">
            Do you want to delete this record?
    </div>
</body>
</html>
<script>
    var receiptDate = "";
    var transDate = "";
    var backDateStatus = '';
	$(document).on("click", "a#inquiryDayEndClosing", function() {
        if ($("#startDate").val()!= "") {
            receiptDate = $("#startDate").val();
        }
        if ($("#stDateTrans").val()!= "") {
            transDate = $("#stDateTrans").val();
        }
        console.log(">>>>>>>>>>>>>>>>>>>>>> "+receiptDate+" : "+transDate);
		//alert(searchDate);
		$.ajax({
			type: "GET",
			url: "../findEndDayClosingEmpGroup2.json?startDate="+$("#startDate").val().replace(/([0-9]{2}).([0-9]{2}).([0-9]{4})/g,"$1-$2-$3")+"&backDateStatus="+backDateStatus+"&searchTransDate="+$("#stDateTrans").val().replace(/([0-9]{2}).([0-9]{2}).([0-9]{4})/g,"$1-$2-$3"),
			dataType: "json",
			contentType: "application/json; charset=utf-8",
			success:function(data){
				//console.log('5555'); console.log(data.endDayClosingList);console.log('5555');
				if(data.endDayClosingList!=null && data.endDayClosingList.length > 0){
					accessDtl(data.endDayClosingList[0]);
					//$("#btnToClosing").prop('disabled', false);
					//$("#btnToClosing").removeAttribute("disabled");
					if(data.endDayClosingList[0].empClosing != null){
						$('#btnToClosing').attr('disabled', 'disabled');
					}else{
						$('#btnToClosing').removeAttr("disabled");
					}

				}else{
					$('#btnToClosing').attr('disabled', 'disabled');
					clearData();
				}
				//$("#tableTotalbyEmp").bootstrapTable("load", data.endDayClosingList);
			}
		});

	});

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
	self.reprintTransMoneyReceipt = new Button("#reprintTransMoneyReceipt");
	self.inputOfficerName = new Input("#inputOfficerName");
    self.inputClosingId = new Input("#inputClosingId");
	self.inputPageNo = new Input("#inputPageNo");
	self.inputPOSMachineName = new Input("#inputPOSMachineName");
	self.inputBranchArea = new Input("#inputBranchArea");
	self.inputCurrentDate = new Input("#inputCurrentDate");
	self.inputBranch = new Input("#inputBranch");
	self.inputCurrentTime = new Input("#inputCurrentTime");
	self.closeStatus = new Input("#closeStatus");
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
    self.inputTotalOffset = new NumberInput("#inputTotalOffset", 0);
    self.inputTotalOffsetAmount = new NumberInput("#inputTotalOffsetAmount");
	self.inputTotalAmount = new NumberInput("#inputTotalAmount");
	self.inputTotalBackDateAmount = new NumberInput("#inputTotalBackDateAmount");
	self.inputTotalBackDate = new NumberInput("#inputTotalBackDate", 0);
	self.inputTotalBackdateTransInAmount = new NumberInput("#inputTotalBackdateTransInAmount");
	self.inputTotalBackDateTranIn = new NumberInput("#inputTotalBackDateTranIn", 0);
	self.inputTotalPaidReceiptWithTaxInvoice = new NumberInput("#inputTotalPaidReceiptWithTaxInvoice", 0);
	self.inputTotalCancelledReceiptWithTaxInvoice = new NumberInput("#inputTotalCancelledReceiptWithTaxInvoice", 0);
	self.inputTotalPaidReceiptWithTaxInvoiceBrief = new NumberInput("#inputTotalPaidReceiptWithTaxInvoiceBrief", 0);
	self.inputTotalCancelledReceiptWithTaxInvoiceBrief = new NumberInput("#inputTotalCancelledReceiptWithTaxInvoiceBrief", 0);

	self.inputTotalTransGfmisAmount = new NumberInput("#inputTotalTransGfmisAmount");
	self.inputTotalTransGfmis = new NumberInput("#inputTotalTransGfmis", 0);
	self.inputTotalUsAmount = new NumberInput("#inputTotalUsAmount");
	self.inputTotalUsToThAmount = new NumberInput("#inputTotalUsToThAmount");
	self.inputTotalUs = new NumberInput("#inputTotalUs", 0);
	self.inputTotalEuAmount = new NumberInput("#inputTotalEuAmount");
	self.inputTotalEuTothAmount = new NumberInput("#inputTotalEuTothAmount");
	self.inputTotalEur = new NumberInput("#inputTotalEur", 0);

	self.inputTotalPaidTaxInvoice = new NumberInput("#inputTotalPaidTaxInvoice", 0);
	self.inputTotalCancelledTaxInvoice = new NumberInput("#inputTotalCancelledTaxInvoice", 0);
	self.inputTotalPaidTaxInvoiceBrief = new NumberInput("#inputTotalPaidTaxInvoiceBrief", 0);
	self.inputTotalCancelledTaxInvoiceBrief = new NumberInput("#inputTotalCancelledTaxInvoiceBrief", 0);

	self.inputTotalPaidReceipt = new NumberInput("#inputTotalPaidReceipt", 0);
	self.inputTotalCancelledReceipt = new NumberInput("#inputTotalCancelledReceipt", 0);

	self.inputAgentTotalPaymentAmount = new NumberInput("#inputAgentTotalPaymentAmount");
	self.inputAgentTotalReceiptWithTaxInvoice = new NumberInput("#inputAgentTotalReceiptWithTaxInvoice", 0);
	self.inputAgentTotalReceiptWithTaxInvoiceBrief = new NumberInput("#inputAgentTotalReceiptWithTaxInvoiceBrief", 0);
    this.dialogConfirmClosing = $("#confirmClosing")

	return this;
})(jQuery);
function buttonSearchClickEvent() {
// 	$('#displayCloseBranchPanel').removeClass('hide');
	$('#buttonCloseBranch').removeClass('disabled');
	get("../closeBranch.json", { closeDate: $("#startDate").val().replace(/([0-9]{2}).([0-9]{2}).([0-9]{4})/g,"$1-$2-$3") }, function(res){
		var data = res.data[0];
		console.log('xxxxx55555'); console.log(data); console.log('xxxxx55555');
		if("0.00" == data.totalPaymentAmount) { $('#buttonCloseBranch').addClass('disabled'); }
		view.inputOfficerName.val(data.officerName);
		view.inputPageNo.val(data.pageNo);
		view.inputPOSMachineName.val(data.posMachineName);
		view.inputBranchArea.val(data.branchCode);
		view.inputCurrentDate.val(view.utils.dateFormat(data.currentDttm));
		view.inputCurrentTime.val(view.utils.timeFormat(data.currentDttm));
		view.inputBranch.val(data.branch);
		//view.inputTotalPaymentAmount.val(data.totalPaymentAmount);
//		view.inputTotalPaymentAmount.val(data.excCancelTotalAmount);
		view.inputTotalPaymentAmount.val(data.total3TredecimAmount+data.total69BisAmount+data.total69TreAmount+data.totalCashAmount+data.totalChequeAmount
            +data.totalCreditCardAmount+data.totalMoneyOrderAmount+data.totalBankTxnfAmount+data.totalForeignBankTxnfAmount+data.totalBillExchangeAmount
            +data.totalOtherAmount+data.totalOffsetAmount+data.agentTotalAmount);
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
        view.inputTotalOffsetAmount.val(data.totalOffsetAmount);
        view.inputTotalOffset.val(data.totalOffset);

		view.inputTotalPaidReceiptWithTaxInvoice.val(data.receiveWhFullCount);
		view.inputTotalCancelledReceiptWithTaxInvoice.val(data.cancelReceiveWhFullCount);
		view.inputTotalPaidReceiptWithTaxInvoiceBrief.val(data.receiveWhAbvrCount);
		view.inputTotalCancelledReceiptWithTaxInvoiceBrief.val(data.cancelReceiveWhAbvrCount);
		view.inputTotalPaidReceipt.val(data.receiveOnlyCount+data.cancelReceiveOnlyCount);
		view.inputTotalCancelledReceipt.val(data.cancelReceiveOnlyCount);

		view.inputTotalPaidReceipt.val(data.totalPaidReceipt);
		view.inputTotalCancelledReceipt.val(data.totalCancelledReceipt);

		view.inputAgentTotalPaymentAmount.val(data.agentTotalAmount);
		view.inputAgentTotalReceiptWithTaxInvoice(data.agentReceiveFullAgentCount);
		view.inputAgentTotalReceiptWithTaxInvoiceBrief(data.agentReceiveAbvrCount);

	}, view.dialogMainMessage);
}
function buttonCloseBranchClickEvent() {
	$.get("../processEndOfDay.json", { closeDate: $("#startDate").val().replace(/([0-9]{2}).([0-9]{2}).([0-9]{4})/g,"$1-$2-$3") }, function(res) {
		location.href = "./logout";
	});
}
function clearData(){
	view.inputOfficerName.val("");
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
    view.inputTotalOffsetAmount.val(0);
    view.inputTotalOffset.val(0);
	view.inputTotalBackDateAmount.val(0);
	view.inputTotalBackDate.val(0);
	view.inputTotalBackdateTransInAmount.val(0);
	view.inputTotalBackDateTranIn.val(0);
	view.inputTotalPaidReceiptWithTaxInvoice.val(0);
	view.inputTotalCancelledReceiptWithTaxInvoice.val(0);
	view.inputTotalPaidReceiptWithTaxInvoiceBrief.val(0);
	view.inputTotalCancelledReceiptWithTaxInvoiceBrief.val(0);
	view.inputTotalPaidReceipt.val(0);
	view.inputTotalCancelledReceipt.val(0);
	view.inputTotalAmount.val(0);
    view.inputTotalTransGfmisAmount.val(0);
    view.inputTotalTransGfmis.val(0);
    view.inputTotalUsAmount.val(0);
    view.inputTotalUsToThAmount.val(0);
    view.inputTotalUs.val(0);
    view.inputTotalEuAmount.val(0);
    view.inputTotalEuTothAmount.val(0);
    view.inputTotalEur.val(0);
    view.inputAgentTotalPaymentAmount.val(0);
    view.inputAgentTotalReceiptWithTaxInvoice.val(0);
    view.inputAgentTotalReceiptWithTaxInvoiceBrief.val(0);
	view.closeStatus.val("");
	$("#btnToClosing").attr("disabled", "disabled");
}
var username = '${pageContext.request.userPrincipal.name}';
function setup(){
    if("${epContext.roleName}" == "GFMISAGENT") {
        $("#labelTransDate").removeClass("hide");
        $("#inputTransDate").removeClass('hide');
        $("#backDateMoneyTransferPanel").removeAttr("hidden");
        $("#generalUserPanel").attr("hidden", "hidden");
        $("#CurrencyMoneyTransferPanel").attr("hidden", "hidden");
        $("#backDateTransferInPanel").attr("hidden", "hidden");
        backDateStatus = 'Y';
    }else if("${epContext.roleName}" == "AGENT"){
        $("#labelTransDate").addClass('hide');
        $("#inputTransDate").addClass('hide');

        $("#backDateMoneyTransferPanel").attr("hidden", "hidden");
        $("#generalUserPanel").removeAttr("hidden");
        $("#CurrencyMoneyTransferPanel").attr("hidden", "hidden");
        $("#backDateTransferInPanel").attr("hidden", "hidden");

        $('#stDate').datepicker('setDate', new Date());
        $('#stDate').datepicker('update');
        $('#stDate').val('');
    }else  if("${epContext.roleName}" == "TRANSFER") {
        $("#labelTransDate").removeClass("hide");
        $("#inputTransDate").removeClass('hide');

        $("#backDateMoneyTransferPanel").attr("hidden", "hidden");
        $("#generalUserPanel").attr("hidden", "hidden");
        $("#CurrencyMoneyTransferPanel").removeAttr("hidden");
        $("#backDateTransferInPanel").attr("hidden", "hidden");
        backDateStatus = 'Y';


    }else if ("${epContext.roleName}" == "TRANSIN") {
        $("#labelTransDate").removeClass("hide");
        $("#inputTransDate").removeClass('hide');

        $("#backDateMoneyTransferPanel").attr("hidden", "hidden");
        $("#generalUserPanel").attr("hidden", "hidden");
        $("#CurrencyMoneyTransferPanel").attr("hidden", "hidden");
        $("#backDateTransferInPanel").removeAttr("hidden");
        backDateStatus = 'Y';
    } else {
        $("#labelTransDate").addClass('hide');
        $("#inputTransDate").addClass('hide');

        $("#backDateMoneyTransferPanel").attr("hidden", "hidden");
        $("#generalUserPanel").removeAttr("hidden");
        $("#CurrencyMoneyTransferPanel").attr("hidden", "hidden");

        $('#stDate').datepicker('setDate', new Date());
        $('#stDate').datepicker('update');
        $('#stDate').val('');
    }
	//$("#btnToClosing").prop("disabled", true);
	//alert(username);
	/*var posByEmpList;
	$.get("../service/end-day-closing/search/empCode", { "empCode": username, "docStatus":"W" }, function(res) {
		//console.log('endDayClosing5555xxx09052017');
		//console.log('aaaa');console.log(res);console.log('bbbb');
		//console.log('endDayClosing5555xxx09052017');
		posByEmpList = res._embedded.endDayClosings/!*._embedded.officers[0]*!/;
		$("#DailyPaymentReport").bootstrapTable("load", posByEmpList);


	});*/
}
function searchSummaryByDate(){
	var posByEmpList;
//	$.get("../service/end-day-closing/search/empCode", { "empCode": username, "docStatus":"W" }, function(res) {
	$.get("../service/end-day-closing/search/userName", { "userName": username, "docStatus":"W" }, function(res) {
		//console.log('endDayClosing5555xxx09052017');
		//console.log('aaaa');console.log(res);console.log('bbbb');
		//console.log('endDayClosing5555xxx09052017');
		posByEmpList = res._embedded.endDayClosings/*._embedded.officers[0]*/;
		$("#DailyPaymentReport").bootstrapTable("load", posByEmpList);


	});
}
function endDayCloseDtl(val, row, ind){
	//console.log('555xxx');console.log(val);console.log(row);console.log(ind);console.log('555xxx');
	return '<span class="glyphicon glyphicon-edit" style="cursor: pointer;" onclick=accessDtl('+JSON.stringify(row)+','+ind+')></span>';
}
function accessDtl(data, ind){
	console.log('555xxx');console.log(data);console.log('555xxx');
    view.inputClosingId.val(data.id);
    //view.inputOfficerName.val(data.empCode);
	//view.inputOfficerName.val(data.empName+"("+data.empCode+")");
    view.inputOfficerName.val(data.empName+"("+data.userName+")");
    //view.inputCurrentDate.val(data.closingDate);
	view.inputCurrentDate.val(data.closingDtStr);
	view.inputCurrentTime.val(data.closingTimeStr);
    //view.inputPOSMachineName.val(data.macNo);
	//view.inputPOSMachineName.val(data.posNo);
	view.inputPOSMachineName.val('${epContext.posNo}');
    view.inputBranch.val(data.shopName);
	/*if(data.empClosing == null){
		view.closeStatus.val("OPEN");
	}else{
		view.closeStatus.val("CLOSED");
        $("#reprintTransMoneyReceipt").removeAttr("disabled", "disabled");
	}*/

	if(data.docStatus == 'S'){
		view.closeStatus.val("CLOSED");
        view.reprintTransMoneyReceipt.enable();
        $("#btnToClosing").attr("disabled", "disabled");
	}else{
		view.closeStatus.val("OPEN");
        view.reprintTransMoneyReceipt.disable();
        $("#btnToClosing").removeAttr("disabled", "disabled");
	}

	view.inputBranchArea.val(data.branchCode);
    //view.inputTotalPaymentAmount.val(data.totalCharge);
//	view.inputTotalPaymentAmount.val(data.excCancelTotalAmount);
	view.inputTotalPaymentAmount.val(data.totalWt3tred+data.totalWt69bis+data.totalWt69tre+data.totalCash+data.totalCheque+data.totalCredit
        +data.totalMoneyOrder+data.totalMoneyTransfer+data.totalForeignTransfer+data.totalBillExchange+data.totalCoupon+data.totalOther
        +data.totalOffset+data.transGfmisTotalAmount+data.backDateTotalAmount+data.foreignUsToThTotalAmount+data.foreignEuToThTotalAmount+data.agentTotalAmount);
	$("#amtSummaryId").text(showNumberFormat(view.inputTotalPaymentAmount.val()));
    view.inputTotal3TredecimAmount.val(data.totalWt3tred);
    view.inputTotal69BisAmount.val(data.totalWt69bis);
    view.inputTotal69TreAmount.val(data.totalWt69tre);

    view.inputTotalCashAmount.val(data.totalCash);
    view.inputTotalChequeAmount.val(data.totalCheque);
    view.inputTotalCheque.val(data.totalChequeCount);
    view.inputTotalCreditCardAmount.val(data.totalCredit);
    view.inputTotalCreditCard.val(data.totalCreditCount);
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
    view.inputTotalOffsetAmount.val(data.totalOffset);
    view.inputTotalOffset.val(data.totalOffsetCount);

    //view.inputTotalPaidReceiptWithTaxInvoice.val(data.totalReceiptCount);
    //view.inputTotalCancelledReceiptWithTaxInvoice.val(data.totalCancelCnt);
	view.inputTotalPaidReceiptWithTaxInvoice.val(data.receiveWhFullCount);
	view.inputTotalCancelledReceiptWithTaxInvoice.val(data.cancelReceiveWhFullCount);
	view.inputTotalPaidReceiptWithTaxInvoiceBrief.val(data.receiveWhAbvrCount);
	view.inputTotalCancelledReceiptWithTaxInvoiceBrief.val(data.cancelReceiveWhAbvrCount);
	view.inputTotalPaidReceipt.val(data.receiveOnlyCount+data.cancelReceiveOnlyCount);
	view.inputTotalCancelledReceipt.val(data.cancelReceiveOnlyCount);

	view.inputTotalBackDateAmount.val(data.backDateTotalAmount);
	view.inputTotalBackDate.val(data.backDateTotalCount);
	view.inputTotalBackdateTransInAmount.val(data.backDateTotalAmount);
	view.inputTotalBackDateTranIn.val(data.backDateTotalCount);


	view.inputTotalTransGfmisAmount.val(data.transGfmisTotalAmount);
	view.inputTotalTransGfmis.val(data.transGfmisTotalCount);
	view.inputTotalUsAmount.val(data.foreignUsTotalAmount);
	view.inputTotalUsToThAmount.val(data.foreignUsToThTotalAmount);
	view.inputTotalUs.val(data.foreignUsTotalCount);
	view.inputTotalEuAmount.val(data.foreignEuTotalAmount);
	view.inputTotalEuTothAmount.val(data.foreignEuToThTotalAmount);
	view.inputTotalEur.val(data.foreignEuTotalCount);
	view.inputAgentTotalPaymentAmount.val(data.agentTotalAmount);
	view.inputAgentTotalReceiptWithTaxInvoice.val(data.agentReceiveFullAgentCount);
	view.inputAgentTotalReceiptWithTaxInvoiceBrief.val(data.agentReceiveAbvrCount);

    $("#dtlPanel").removeClass("hide");
	//$("#btnToClosing").removeProp("disabled");

    //$(this).addClass('selected');
    var rows = $('tr', $("#DailyPaymentReport"));
    rows.removeClass('selected2');
    rows.eq(ind+1).addClass('selected2');
}
function buttonCloseBranchClickEvent(){
    view.dialogConfirmClosing.modal("hide");

    var params = {
        "closingId": view.inputClosingId.val(),
        "searchDate": receiptDate,
        "searchTransDate": transDate,
        "posNo": view.inputPOSMachineName.val(),
        "branchCode": view.inputBranchArea.val(),
        "backDateStatus": backDateStatus
    }
    //console.log('xxxxxxparams');console.log(params);console.log('xxxxxxparams');
    $.ajax({
        url: "../createEndDayClosingEmp.json"
        , type: "POST"
        , data: params
        , async: false
        //,error: view.dialog.mainMessageDialog.valid
        , success: function (res) {
            console.log('responseResult5555');
            console.log(res);
            console.log('responseResult5555');
            //$("#dtlPanel").addClass("hide");
            setup();

           /* window.empClosingList = res;
            empClosingId = window.empClosingList.id;
            branchCode = window.empClosingList.branchCode;
            console.log("shopClosingId : "+shopClosingId);
            console.log("branchCode : "+branchCode);
            var subParams = {
                "searchDate" : $("#transactionDate").val(),
                "backDateStatus": "N"
            };


            if (res.empClosingList) {
                $.each(data, function(i, o) {
                    console.log(" >> empClosingList DATA <<");
                    console.log(o);
                    subParams["employees["+ i +"].empClosingId"] = o.id;
                    subParams["employees["+ i +"].branchCode"] = o.branchCode;
                    subParams["employees["+ i +"].closingEmpCode"] = o.empCode;
                });
            } else {
                $.each(data, function(i, o) {
                    console.log(" >> res DATA <<");
                    console.log(o);
                    subParams["employees["+ i +"].empClosingId"] = o.id;
                    subParams["employees["+ i +"].branchCode"] = o.branchCode;
                    subParams["employees["+ i +"].closingEmpCode"] = o.empCode;
                });
            }

            $.ajax({
                type: "POST",
                url: "../updateShopClosing.json",
                data: subParams,
                success: function (data) {
                    console.log("======================1");
                    console.log(data);
                    if (!data) {
                        view.message.clear().error(["ระบบอัพเดทข้อมูลไม่สำเร็จ"]).show();
                    }
                    console.log("======================2");

                }
            });*/

            view.message.clear().success(["ระบบทำการปิดบัญชีสำเร็จ"]).show();
            //view.dialog.mainMessageDialog.clear();
            clearData();

            $(document.body).append('<form action="../printEmpDayEndClosing.pdf" method="POST" target="_printForm"></form>');
            var form = document.forms[0];

            function input(n, v) {
                var input = document.createElement("input");
                input.type = "hidden";
                input.name = n;
                input.value = v;
                return input
            }

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
//                form.appendChild(input("empClosings[0].id", res.id));
            }

            form.submit();

            //view.message.focus();

            /*$(document.body).append('<form action="../printPaymentReceipt.pdf" method="post" target="_printForm"></form>');
            var form = document.forms[0]; function input(n,v) { var input = document.createElement("input"); input.type = "hidden"; input.name = n; input.value = v; return input }
            $.each(res.data, function(i,o){
                var rcptFormat = "";
                //by NSD 25-04-2017
                if(o.flgHeader == '1'){
                    rcptFormat = "receive_wh";
                }else if(o.flgHeader == '2'){
                    rcptFormat = "receive_only";
                }else{
                    rcptFormat = "wh_only";
                }
                form.appendChild(input("receipts["+ i +"].id", o.id));
                form.appendChild(input("receipts["+ i +"].billingServiceName", o.billingServiceName));//by NSD 24-03-20
                form.appendChild(input("receipts["+ i +"].flgHeader", o.flgHeader));

            });
            var custIndex =0, invoiceIndex = 0, customer, receiptList = [];
            var billingList = view.session("billingList");
            while (customer = billingList[custIndex]) {
                form.appendChild(input("customers["+ custIndex +"].custNo", customer.custNo));
                form.appendChild(input("customers["+ custIndex +"].egpNo", customer.egpNo));
                form.appendChild(input("customers["+ custIndex +"].egpContract", customer.egpContract));
                for (var j = 0, n = customer.invoiceList.length; j < n; j++) {
                    var invoice = customer.invoiceList[j];
                    form.appendChild(input("customers["+ custIndex +"].invoices["+ invoiceIndex +"].currencyCode", invoice.currencyCode));
                    invoiceIndex++;
                }
                custIndex++;
                invoiceIndex = 0;
            }

            form.submit();*/

        }
    });
}
function showNumberFormat(val){
	return !$.isNumeric(stripToNumber(val)) ? "0.00" : parseFloat(val, 10).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,') ;
}

function reprintTransMoneyReceiptClickEvent(){

    $.ajax({
        type: "GET",
        url: "../findDayEndClosingStatusClose.json?startDate="+$("#startDate").val().replace(/([0-9]{2}).([0-9]{2}).([0-9]{4})/g,"$1-$2-$3")+"&backDateStatus="+backDateStatus+"&searchTransDate="+$("#stDateTrans").val().replace(/([0-9]{2}).([0-9]{2}).([0-9]{4})/g,"$1-$2-$3"),
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        success:function(data){
            console.log('responseResult******');
            console.log(data);console.log('*****responseResult');

            $(document.body).append('<form action="../printEmpDayEndClosing.pdf" method="POST" target="_printForm"></form>');
            var form = document.forms[0]; function input(n,v) { var input = document.createElement("input"); input.type = "hidden"; input.name = n; input.value = v; return input }
//                form.appendChild(input("empClosings[0].id", res.id));
            var endDayClosingList = data.endDayClosingList;
            if (endDayClosingList.length >0) {
                $.each(endDayClosingList, function(i, o){
                    console.log("testtttt" +i);
                    console.log(o.empClosingId1);
                    form.appendChild(input("empClosings["+i+"].id", o.empClosingId1));
                });
                form.submit();
            }


        }
    });


    /*$.ajax({
        type: "GET",
        url: "../reprintEmpDayEndClosing.pdf?startDate="+$("#startDate").val().replace(/([0-9]{2}).([0-9]{2}).([0-9]{4})/g,"$1-$2-$3")+"&backDateStatus="+backDateStatus+"&searchTransDate="+$("#stDateTrans").val().replace(/([0-9]{2}).([0-9]{2}).([0-9]{4})/g,"$1-$2-$3"),
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        success:function(data){
            console.log("VVVVV data VVVV");
            console.log(data);
        }
    });
*/

}

</script>