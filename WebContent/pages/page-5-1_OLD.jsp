<%-- 
    Document   : page-5-1
    Created on : Feb 1, 2016, 1:59:17 PM
    Author     : AmMie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="../scripts/bootstrap-table-1.8.1/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="../scripts/bootstrap-table-1.8.1/bootstrap-table/src/bootstrap-table.css" rel="stylesheet" type="text/css"/>
        <link href="../scripts/bootstrap-datepicker/jquery.datetimepicker.css" rel="stylesheet" type="text/css"/>
        <link href="../commons/custom.css" rel="stylesheet" type="text/css"/>
        <script src="../scripts/bootstrap-table-1.8.1/jquery.min.js" type="text/javascript"></script>
        <script src="../scripts/bootstrap-table-1.8.1/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="../scripts/bootstrap-table-1.8.1/bootstrap-table/src/bootstrap-table.js"></script>
        <script src="../scripts/bootstrap-datepicker/jquery.datetimepicker.js"></script>
        <script src="../commons/custom.js" type="text/javascript"></script>
    </head>
    <body>
        <header class="header_page"></header>
        <section class="container-fluid menu">
            <!-- <button class="menu-toggle btn btn-sm btn-default" style="margin-bottom: 5px"><span class="glyphicon glyphicon-step-forward"></span></button> -->
                <%-- <%@include  file="menu.jsp" %> --%>
            <div class="row">
                <div class="col-md-12">
                    <ol class="breadcrumb">
                        <li><i>ประวัติการรับชำระค่าใช้บริการ</i></li>
                    </ol>
                    <label class="label-panal label-success">ข้อมูลการรับชำระ</label>
                    <div class="panel panel-default panal-radius">
                        <div class="panel-body">
                            <div class="form-horizontal">
                                <div class="form-group">
                                    <label class="control-label col-sm-3" >เลขที่ใบเสร็จรับเงิน :</label>
                                    <div class="col-sm-2">
                                        <input class="form-control" value="" disabled="disabled">
                                    </div>
                                    <label class="control-label col-sm-3">วิธีการรับชำระ :</label>
                                    <div class="col-sm-2">
                                        <input class="form-control" value="" disabled="disabled">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="control-label col-sm-3" >วันที่รับชำระ :</label>
                                    <div class="col-sm-2">
                                        <input class="form-control" value="" disabled="disabled">
                                    </div>
                                    <label class="control-label col-sm-3">ประเภทการรับชำระ :</label>
                                    <div class="col-sm-2">
                                        <input class="form-control" value="" disabled="disabled">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-3" >สาขาที่รับชำระ :</label>
                                    <div class="col-sm-2">
                                        <input class="form-control" value="" disabled="disabled">
                                    </div>
                                    <label class="control-label col-sm-3">เจ้าหน้าที่ชำระ :</label>
                                    <div class="col-sm-2">
                                        <input class="form-control" value="" disabled="disabled">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-3" >เครื่องที่รับชำระ :</label>
                                    <div class="col-sm-2">
                                        <input class="form-control" value="" disabled="disabled">
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
                <!-- Nav tabs -->
                <ul class="nav nav-tabs" role="tablist">
                    <li role="presentation" class="active"><a href="#tab_cus" aria-controls="tab_cus" role="tab" data-toggle="tab">ข้อมูลลูกค้า</a></li>
                    <li role="presentation"><a href="#sub_script" aria-controls="sub_script" role="tab" data-toggle="tab">Subscription</a></li>
                </ul>
                <div class="tab-content">
                    <div role="tabpanel" class="tab-pane active" id="tab_cus">
                        <div class="panel panel-default panal-radius">
                            <div class="panel-body">
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="form-horizontal">
                                            <div class="form-group">
                                                <label class="control-label col-sm-2" >เลขที่ลูกค้า</label>
                                                <div class="col-sm-2">
                                                    <input class="form-control" value="66665278" disabled="disabled">
                                                </div>
                                                <label class="control-label col-sm-1">ชื่อลูกค้า</label>
                                                <div class="col-sm-7">
                                                    <input class="form-control" value="CHEVRON THAILAND EXPLORATION AND PRODUCTION LTD." disabled="disabled">
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <label class="control-label col-sm-2" >Tax ID :</label>
                                                <div class="col-sm-2">
                                                    <input class="form-control" value="Business" disabled="disabled">
                                                </div>
                                                <label class="control-label col-sm-2" >หน่วยงานคิดตามหนี้ :</label>
                                                <div class="col-sm-2">
                                                    <input class="form-control" value="" disabled="disabled">
                                                </div>
                                                <label class="control-label col-sm-2" >ยอดค้างชำระ :</label>
                                                <div class="col-sm-2">
                                                    <input class="form-control" style="text-align: right" value="1,070.00" disabled="disabled">
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <label class="control-label col-sm-2" >กลุ่มผู้ใช้บริการ :</label>
                                                <div class="col-sm-2">
                                                    <input class="form-control" value="" disabled="disabled">
                                                </div>
                                                <label class="control-label col-sm-2" ></label>
                                                <div class="col-sm-2">

                                                </div>
                                                <label class="control-label col-sm-2" >สกุลเงิน :</label>
                                                <div class="col-sm-2">
                                                    <input class="form-control" value="THB" disabled="disabled">
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <label class="control-label col-sm-2" >Billing Group :</label>
                                                <div class="col-sm-2">
                                                    <input class="form-control" value="Active" disabled="disabled">
                                                </div>

                                                <label class="control-label col-sm-2" ></label>
                                                <div class="col-sm-2"> </div>

                                                <label class="control-label col-sm-2" >VAT</label>
                                                <div class="col-sm-2">
                                                    <input class="form-control" style="text-align: right" value="7" disabled="disabled">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-sm-2" >สถานะลูกค้า :</label>
                                                <div class="col-sm-2">
                                                    <input class="form-control" value="" disabled="disabled">
                                                </div>

                                                <label class="control-label col-sm-2" ></label>
                                                <div class="col-sm-2"> </div>

                                                <label class="control-label col-sm-2" ></label>
                                                <div class="col-sm-2">

                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-sm-2" >หมายเหตุ</label>
                                                <div class="col-sm-10">
                                                    <input class="form-control">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!--------------------------------------->
                                <br/>
                                <!--------------------------------------->
                                <div class="row">
                                    <div class="col-md-12 tab-modefile">
                                        <!-- Nav tabs -->
                                        <ul class="nav nav-tabs" role="tablist">
                                            <li role="presentation" class="active"><a href="#tab1" aria-controls="tab1" role="tab" data-toggle="tab">ที่อยู่สำหรับใบเสร็จรับเงิน</a></li>
                                        </ul>

                                        <!-- Tab panes -->
                                        <div class="tab-content">
                                            <div role="tabpanel" class="tab-pane active" id="tab1">
                                                <textarea class="form-control textarea-tab">19 SCB PARK PLAZA 5TH, TOWER III RATCHADAPISEK CHATUCHAK CHATUCHAK BANGKOK 10900</textarea>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!--------------------------------------->
                    <!--------------------------------------->
                    <div role="tabpanel" class="tab-pane" id="sub_script">
                        <div class="panel panel-default panal-radius">
                            <div class="panel-body">
                                <table class="table table-striped">
                                    <thead>
                                        <tr>
                                            <td>ลำดับ</td>
                                            <td>หมายเหตุบริการ</td>
                                            <td>ประเภทบริการ</td>
                                            <td>สาถนะ</td>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td>1</td>
                                            <td>IMN 456700079</td>
                                            <td>INMARSAT</td>
                                            <td>Disconnected By Customer</td>
                                        </tr>
                                        <tr>
                                            <td>2</td>
                                            <td>IMN 4567000652</td>
                                            <td>INMARSAT</td>
                                            <td>Active By Customer</td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
        <!--------------------------------------->
        <!--------------------------------------->
        <div class="row">
            <div class="col-md-12">
                <label class="label-panal label-success">รายการใบแจ้งค่าใช้บริการ</label>
                <table id="table"
                       data-row-style="rowStyle"
                       data-toggle="table"
                       data-detail-view="true"
                       data-detail-formatter="detailFormatter"
                       data-classes="table table-hover table-striped"
                       >
                    <thead>
                        <tr>
                            <th data-field="a" data-sortable="true" class="">#</th>
                            <!-- ice fixed code no.109 'ทุกระบบต้องเลขที่ใบแจ้งค่าใช้บริการใบแจ้งค่าใช้บริการ" -->
                            <th data-field="b" data-sortable="true" class="">เลขที่ใบแจ้งค่าใช้บริการ</th>
                            <!-- end ice fixed code no.109 'ทุกระบบต้องเลขที่ใบแจ้งค่าใช้บริการใบแจ้งค่าใช้บริการ" -->
                            <th data-field="c" data-sortable="true" class="">วันที่จัดทำ</th>
                            <th data-field="d" data-sortable="true" class="">วันที่ครบกำหนด</th>
                            <th data-field="e" data-sortable="true" data-halign="center" data-align="right" class="">จำนวนเงินตั้งหนี้</th>
                            <th data-field="f" data-sortable="true" data-halign="center" data-align="right" class="">ภาษีมูลค่าเพิ่ม</th>
                            <th data-field="g" data-sortable="true" data-halign="center" data-align="right" class="">จำนวนเงินคงค้าง</th>
                            <th data-field="h" data-sortable="true" data-halign="center" data-align="right" class="">ยอดชำระ</th>
                            <th data-field="i" data-sortable="true" data-halign="center" data-align="right" class="">ภาษีหัก ณ ที่จ่าย</th>
                            <th data-field="j" data-sortable="true" class="col-xs-6">รอบการใช้งาน</th>
                            <th data-field="k" data-sortable="true" class="col-xs-1">ล็อคโดย</th>
                            <th data-field="l" data-sortable="true" class="col-xs-1">สถานะ</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td><input type="checkbox" class="checkbox" id="checkbox_37" onclick="show_price('37')"></td>
                            <td>2000024737</td>
                            <td>11/12/2015</td>
                            <td>31/12/2015</td>
                            <td>500.00</td>
                            <td>35.00</td>
                            <td><p id="text_price_37">500.00<p><input class="form-control text-right hidden" id="input_price_37" value="500"></td>
                            <td>535.00</td>
                            <td>0.00</td>
                            <td>01/11/2015 - 30/11/2015</td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td><input type="checkbox" class="checkbox" id="checkbox_38" onchange="show_price('38')"></td>
                            <td>2000024738</td>
                            <td>11/12/2015</td>
                            <td>31/12/2015</td>
                            <td>500.00</td>
                            <td>35.00</td>
                            <td><p id="text_price_38">500.00</p><input class="form-control  text-right hidden" id="input_price_38" value="500"></td>
                            <td>535.00</td>
                            <td>0.00</td>
                            <td>01/11/2015 - 30/11/2015</td>
                            <td></td>
                            <td></td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <br/>
        <!------------------------->
    </section>
    <footer style="">
        <nav class="navbar navbar-default" style="position: relative; bottom: 0; margin-top: 60px; text-align: center; background-color: #f39c12;">
            <div class="container-fluid">
                <p class="navbar-text" style="color: #fff">Copyright 2015 CAT Telecom Public Co., Ltd CAT</p>
            </div>
        </nav>
    </footer>
</body>
<script type="text/javascript">
    var $table = $('#myTable');

    $table.on('expand-row.bs.table', function (e, index, row, $detail) {
        var res = $("#desc" + index).html();
        $detail.html(res);
    });
    function detail_invoice(refno) {
        $.ajax({
            type: 'POST',
            url: 'detail_invoice.jsp',
            data: {refno: refno},
            success: function (data) {
                //alert(data);
                $("#show_detail_invoice_" + refno).prepend(data);
                $("#show_detail_invoice_" + refno).removeClass("hidden");
            }
        });
    }
    function show_price(refno) {
        // alert(refno);
        if ($("#checkbox_" + refno).prop("checked")) {
            $("#text_price_" + refno).addClass("hidden");
            $("#input_price_" + refno).removeClass("hidden");
            $("#input_price_" + refno).focus();
        } else {
            $("#text_price_" + refno).removeClass("hidden");
            $("#input_price_" + refno).addClass("hidden");
        }
    }
    $("#btn_save_c").click(function () {
        //alert("ok");
        var dataSet = {
            c_Num: $("#c_Num").val(),
            c_IdBank: $("#c_IdBank").val(),
            c_Bank: $("#c_Bank").val(),
            c_Bank_branch: $("#c_Bank_branch").val(),
            c_Date: $("#c_Date").val(),
            c_money: $("#c_money").val()
        };
        $.ajax({
            type: 'POST',
            url: 'data_chack.jsp',
            data: dataSet,
            success: function (data) {
                //alert(data);
                $("#data_chack_show").prepend(data);
                $("#c_Num").val("");
                $("#c_IdBank").val("");
                $("#c_Bank").val("");
                $("#c_Bank_branch").val("");
                $("#c_Date").val("");
                $("#c_money").val("");
            }
        });
    });
    $("#c_Date").datetimepicker({
        timepicker: false,
        format: 'd/m/Y', // กำหนดรูปแบบวันที่ ที่ใช้ เป็น 00-00-0000			
        lang: 'th', // แสดงภาษาไทย
        scrollMonth: true,
        // minDate: '0', //เลือกวันย้อนหลัง
        // maxDate: '0'
    });
</script>
</html>
