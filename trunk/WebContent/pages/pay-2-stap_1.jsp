<%-- 
    Document   : pay-service-charge
    Created on : Dec 15, 2015, 3:27:39 PM
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
                        <li><i>รับชำระค่าบริการล่วงหน้า</i></li>
                        <li class="active">ค้นหาข้อมูลลูกค้า</li>
                        <li>เลือกวิธีการรับชำระ</li>
                        <li>ผลการรับชำระ</li>
                    </ol>
                    <button type="button" class="btn btn-info btn-advan"  data-toggle="modal" data-target="#CustomerSearch"><span class="glyphicon glyphicon-search"></span>  ค้นหา</button>
                    <div class="panel panel-default panal-radius">
                        <div class="panel-body">
                            <div class="form-inline">
                                <div class="form-group">
                                    <label>เลขที่ลูกค้า : </label>
                                    <div class="input-group">
                                        <input type="text" class="form-control" value="666578">
                                        <span class="input-group-btn">
                                            <button type="button" class="btn btn-info"><span class="glyphicon glyphicon-search"></span> ค้นหา</button>
                                        </span>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <ul class="list-inline pull-right">
                <li><a href="#"><span class="glyphicon glyphicon-edit"></span> ดำเนินการรับชำระ</a></li>
            </ul>


            <div class="row">
                <div class="col-md-12 tab-modefile">
                    <!-- Nav tabs -->
                    <ul class="nav nav-tabs" role="tablist">
                        <li role="presentation" class="active"><a href="#tab_cus" aria-controls="tab_cus" role="tab" data-toggle="tab">ข้อมูลลูกค้า</a></li>    </ul>
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
                                                    <label class="control-label col-sm-2" ></label>
                                                    <div class="col-sm-2"> </div>
                                                    <label class="control-label col-sm-2" >หน่วยงานคิดตามหนี้ :</label>
                                                    <div class="col-sm-2">
                                                        <input class="form-control" value="" disabled="disabled">
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

                                                    <label class="control-label col-sm-2" >ยอดค้างชำระ (รวม VAT) :</label>
                                                    <div class="col-sm-2">
                                                        <input class="form-control" style="text-align: right" value="1,070.00" disabled="disabled">
                                                    </div>
                                                </div>

                                                <div class="form-group">
                                                    <label class="control-label col-sm-2" >Billing Group :</label>
                                                    <div class="col-sm-2">
                                                        <input class="form-control" value="Active" disabled="disabled">
                                                    </div>
                                                    <label class="control-label col-sm-2" ></label>
                                                    <div class="col-sm-2"> </div>
                                                    <label class="control-label col-sm-2" >ยอดค่างชำระล่วงหน้า :</label>
                                                    <div class="col-sm-2">
                                                        <input class="form-control" value="" disabled="disabled">
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label col-sm-2" >สถานะลูกค้า :</label>
                                                    <div class="col-sm-2">
                                                        <input class="form-control" value="" disabled="disabled">
                                                    </div>
                                                    <label class="control-label col-sm-2" ></label>
                                                    <div class="col-sm-2"> </div>
                                                    <label class="control-label col-sm-2" >สกุลเงิน :</label>
                                                    <div class="col-sm-2">
                                                        <input class="form-control" value="" disabled="disabled">
                                                    </div>

                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label col-sm-2" ></label>
                                                    <div class="col-sm-2"> </div>
                                                    <label class="control-label col-sm-2" ></label>
                                                    <div class="col-sm-2"> </div>
                                                    <label class="control-label col-sm-2" >VAT Rete :</label>
                                                    <div class="col-sm-2">
                                                        <input class="form-control" style="text-align: right" value="7" disabled="disabled">
                                                    </div>

                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label col-sm-2" >หมายเหตุ</label>
                                                    <div class="col-sm-4">
                                                        <input class="form-control">
                                                    </div>
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
                                            <li role="presentation"><a href="#tab2" aria-controls="tab2" role="tab" data-toggle="tab">ที่อยู่สำหรับใบแจ้งค่าใช้บริการ</a></li>
                                            <li role="presentation">
                                                <div class="checkbox">
                                                    <label>
                                                        &nbsp;<input type="checkbox"> เปลี่ยนแปลงที่อยู่ในใบกำกับภาษี
                                                    </label>
                                                </div>
                                            </li>
                                        </ul>

                                        <!-- Tab panes -->
                                        <div class="tab-content">
                                            <div role="tabpanel" class="tab-pane active" id="tab1">
                                                <textarea class="form-control textarea-tab">19 SCB PARK PLAZA 5TH, TOWER III RATCHADAPISEK CHATUCHAK CHATUCHAK BANGKOK 10900</textarea>
                                            </div>
                                            <div role="tabpanel" class="tab-pane" id="tab2">
                                                <textarea class="form-control textarea-tab">ที่อยู่สำหรับใบแจ้งค่า</textarea>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!--------------------------------------->
                    <!--------------------------------------->
                </div>
            </div>
        </div>
        <!--------------------------------------->
        <!--------------------------------------->
        <br/>
        <!------------------------->
        <!----------ยอดที่ต้องชำระ--------------->
    </section>

    <div class="modal fade" role="dialog" id="CustomerSearch">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">ค้นหาลูกค้า</h4>
                </div>
                <div class="modal-body">
                    <div class="tab-modefile">
                        <!-- Nav tabs -->
                        <ul class="nav nav-tabs" role="tablist">
                        <!-- ice fixed code no.109 'ทุกระบบต้องเลขที่ใบแจ้งค่าใช้บริการใบแจ้งค่าใช้บริการ" -->
                            <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">เลขที่ใบแจ้งค่าใช้บริการ</a></li>
                            <!-- end ice fixed code no.109 'ทุกระบบต้องเลขที่ใบแจ้งค่าใช้บริการใบแจ้งค่าใช้บริการ" -->
                            <li role="presentation"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">หมายเลขบริการ</a></li>
                            <li role="presentation"><a href="#messages" aria-controls="messages" role="tab" data-toggle="tab">ข้อมูลลูกค้า</a></li>
                        </ul>

                        <!-- Tab panes -->
                        <div class="panel panel-default">
                            <div class="panel-body">
                                <div class="tab-content border-tab-content">
                                    <div role="tabpanel" class="tab-pane active" id="home">
                                        <div class="form-inline">
                                            <div class="form-group">
                                                <label>เลขที่ใบแจ้งค่าบริการ :</label>
                                                <div class="input-group">
                                                    <input type="text" class="form-control">
                                                    <span class="input-group-btn">
                                                        <button type="button" class="btn btn-info"><span class="glyphicon glyphicon-search"></span> ค้นหา</button>
                                                    </span>
                                                </div><!-- /input-group -->
                                            </div>
                                        </div>
                                    </div>
                                    <div role="tabpanel" class="tab-pane" id="profile">
                                        <div class="form-horizontal">
                                            <div class="form-group">
                                                <label class="control-label col-sm-2" >ประเภทบริการ :</label>
                                                <div class="col-sm-4">
                                                    <select class="form-control">
                                                        <option>1</option>
                                                        <option>2</option>
                                                        <option>3</option>
                                                        <option>4</option>
                                                        <option>5</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-sm-2" >หมายเลขบริการ :</label>
                                                <div class="col-sm-4">
                                                    <div class="input-group">
                                                        <input type="text" class="form-control">
                                                        <span class="input-group-btn">
                                                            <button type="button" class="btn btn-info"><span class="glyphicon glyphicon-search"></span> ค้นหา</button>
                                                        </span>
                                                    </div><!-- /input-group -->
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div role="tabpanel" class="tab-pane" id="messages">
                                        <div class="form-horizontal">
                                            <div class="form-group">
                                                <label class="control-label col-sm-3" >เลขที่ลูกค้า :</label>
                                                <div class="col-sm-3">
                                                    <input class="form-control">
                                                </div>
                                                <label class="control-label col-sm-3" >เลขประจำตัวผู้เสียภาษี :</label>
                                                <div class="col-sm-3">
                                                    <input class="form-control">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-sm-3" >ชื่อลูกค้า :</label>
                                                <div class="col-sm-3">
                                                    <input class="form-control">
                                                </div>
                                                <label class="control-label col-sm-3" >นามสกุล :</label>
                                                <div class="col-sm-3">
                                                    <input class="form-control">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-sm-3" >นิติบุคคล/ราชการ :</label>
                                                <div class="col-sm-3">
                                                    <input class="form-control">
                                                </div>
                                                <div class="col-sm-6">
                                                    <button type="button" class="btn btn-info pull-right"><span class="glyphicon glyphicon-search"></span> ค้นหา</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <br/>
                    <label class="label-panal label-success">ผลการค้นหา</label>
                    <div class="panel panel-default panal-radius">
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-md-12">
                                    <table class="table table-hover">
                                        <thead>
                                            <tr>
                                                <th>#</th>
                                                <th>ลำดับ</th>
                                                <th>เลขที่ลูกค้า</th>
                                                <th>ชื่อลูกค้า</th>
                                                <th>ที่อยู่</th> 
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td><input type="radio" name="optionsRadios" id="optionsRadios1" value="option1" checked> </td>
                                                <td>1</td>
                                                <td>6665278</td>
                                                <td>CHEVRONTHAILAND</td>
                                                <td>19 SCB PARK PLAZA 5TH, TOWER III RATCHADA</td>
                                            </tr>
                                            <tr>
                                                <td><input type="radio" name="optionsRadios" id="optionsRadios2" value="option2">  </td>
                                                <td>2</td>
                                                <td>6665278</td>
                                                <td>CHEVRONTHAILAND</td>
                                                <td>19 SCB PARK PLAZA 5TH, TOWER III RATCHADA</td>
                                            </tr>
                                            <tr>
                                                <td><input type="radio" name="optionsRadios" id="optionsRadios2" value="option2">  </td>
                                                <td>3</td>
                                                <td>6665278</td>
                                                <td>CHEVRONTHAILAND</td>
                                                <td>19 SCB PARK PLAZA 5TH, TOWER III RATCHADA</td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer" style="border: none">
                    <button type="button" class="btn btn-default" data-dismiss="modal"><span class="glyphicon glyphicon-ok-circle"></span> เลือกรายการลูกค้า</button>
                    <button type="button" class="btn btn-danger" data-dismiss="modal"><span class="glyphicon glyphicon-remove-circle"></span> ปิด</button>
                </div>
            </div>
        </div>
    </div>



    <div id="status_print">
        <div style="float: none; margin: 0 auto;">
            <div class="panel panel-default vertical-center panel_status_print ">
                <div class="panel-heading">พิมพ์</div>
                <div class="panel-body">
                    <h4> กำลังพิมพ์ใบเสร็จรับเงิน...</h4>
                </div>
            </div>
        </div>
    </div>



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
    $(document).ready(function () {
        $("#status_print").hide();
    });
    function submit_payment() {
        $("#status_print").show();
        setTimeout(' $("#status_print").hide()', 3000);
    }
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
