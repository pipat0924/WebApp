<%-- 
    Document   : page-6-1
    Created on : Feb 1, 2016, 2:11:28 PM
    Author     : AmMie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="bootstrap-table-1.8.1/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="bootstrap-table-1.8.1/bootstrap-table/src/bootstrap-table.css" rel="stylesheet" type="text/css"/>
        <link href="bootstrap-datepicker/jquery.datetimepicker.css" rel="stylesheet" type="text/css"/>
        <link href="option/custom.css" rel="stylesheet" type="text/css"/>
        <script src="bootstrap-table-1.8.1/jquery.min.js" type="text/javascript"></script>
        <script src="bootstrap-table-1.8.1/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="bootstrap-table-1.8.1/bootstrap-table/src/bootstrap-table.js"></script>
        <script src="bootstrap-datepicker/jquery.datetimepicker.js"></script>
        <script src="option/custom.js" type="text/javascript"></script>
    </head>
    <body>
        <header class="header_page"></header>
        <section class="container-fluid menu">
            <!-- <button class="menu-toggle btn btn-sm btn-default" style="margin-bottom: 5px"><span class="glyphicon glyphicon-step-forward"></span></button> -->
                <%-- <%@include  file="menu.jsp" %> --%>
            <ol class="breadcrumb">
                <li><i>บันทึกคำร้อง</i></li>
                <li class="active">ค้นหาข้อมูลอ้างอิง</li>
            </ol>
            <div class="row">
                <div class="col-md-12 tab-modefile">
                    <!-- Nav tabs -->
                    <ul class="nav nav-tabs" role="tablist">
                        <li role="presentation" class="active" ><a href="#tab_1" aria-controls="tab_1" role="tab" data-toggle="tab">ค้นหาจากข้อมูลลูกค้า</a></li>
                        <li role="presentation"><a href="#tab_2" aria-controls="tab_2" role="tab" data-toggle="tab">ค้นหาจากใบแจ้งค่าใช้บริการ</a></li>
                        <li role="presentation"><a href="#tab_3" aria-controls="tab_3" role="tab" data-toggle="tab">ค้นหาจากใบเสร็จรับเงิน</a></li>
                        <li role="presentation"><a href="#tab_4" aria-controls="tab_4" role="tab" data-toggle="tab">ค้นหาจากข้อมูลการรับชำระ</a></li>

                    </ul>
                    <div class="tab-content">
                        <div role="tabpanel" class="tab-pane active" id="tab_1">
                            <div class="panel panel-default panal-radius">

                                <div class="panel-body">
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-horizontal">
                                                <div class="form-group">
                                                    <label class="control-label col-sm-2" >เลขที่ลูกค้า :</label>
                                                    <div class="col-sm-3">
                                                        <input class="form-control" value="700006573">
                                                    </div>
                                                    <label class="control-label col-sm-2" >เลขประจำตัวผู้เสียภาษี :</label>
                                                    <div class="col-sm-3">
                                                        <input class="form-control">
                                                    </div>
                                                    <div class="col-sm-2">
                                                        <button type="button" class="btn btn-primary pull-right"><span class="glyphicon glyphicon-search"></span> ค้นหา</button>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label col-sm-2" >ชื่อลูกค้า :</label>
                                                    <div class="col-sm-3">
                                                        <input class="form-control">
                                                    </div>
                                                    <label class="control-label col-sm-2" >นามสกุล :</label>
                                                    <div class="col-sm-3">
                                                        <input class="form-control">
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label col-sm-2" >ชื่อนิติบุคคล/ราชการ :</label>
                                                    <div class="col-sm-6">
                                                        <input class="form-control" value="บริษัท AEC จำกัด">
                                                    </div>

                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>


                            
                            
                        </div>
                        <div role="tabpanel" class="tab-pane " id="tab_2">
                            <div class="panel panel-default panal-radius">
                                <div class="panel-body">
                                    <div class="form-horizontal">
                                        <div class="form-group">
                                            <label class="control-label col-sm-3" >
                                                	เลขที่ใบแจ้งค่าบริการ :
                                            </label>
                                            <div class="col-sm-2">
                                                <input class="form-control" value="">
                                            </div>
                                            <label class="control-label col-sm-6">
                                                <button class="btn btn-primary"><span class="glyphicon glyphicon-search"></span> ค้นหา</button>
                                            </label>
                                        </div>

                                        <div class="form-group">
                                            <label class="control-label col-sm-3" >
                                                	วันที่จัดทำใบแจ้งค่าใช้บริการ&nbsp;&nbsp;จากวันที่ :
                                            </label>
                                            <div class="col-sm-2">
                                                <div class="input-group">
                                                    <input class="form-control" value="" >
                                                    <span class="input-group-btn">
                                                        <button class="btn btn-default" type="button"><span class="glyphicon glyphicon-calendar"></span></button>
                                                    </span>
                                                </div>
                                            </div>
                                            <label class="control-label col-sm-1">ถึงวันที่ :</label>
                                            <div class="col-sm-2">
                                                <div class="input-group">
                                                    <input class="form-control" value="" >
                                                    <span class="input-group-btn">
                                                        <button class="btn btn-default" type="button"><span class="glyphicon glyphicon-calendar"></span></button>
                                                    </span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-3" >
                                                 	หมายเลขบริการ :
                                            </label>
                                            <div class="col-sm-2">
                                                <input class="form-control" value="">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        
                        <div role="tabpanel" class="tab-pane" id="tab_3">
                            <div class="panel panel-default panal-radius">

                                <div class="panel-body">
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-horizontal">
                                                <div class="form-group">
                                                    <label class="control-label col-sm-2" >เลขที่ใบเสร็จรับเงิน :</label>
                                                    <div class="col-sm-3">
                                                        <input class="form-control" value="700006573">
                                                    </div>
                                                    <label class="control-label col-sm-2" >เลขประจำตัวผู้เสียภาษี :</label>
                                                    <div class="col-sm-3">
                                                        <input class="form-control">
                                                    </div>
                                                    <div class="col-sm-2">
                                                        <button type="button" class="btn btn-primary pull-right"><span class="glyphicon glyphicon-search"></span> ค้นหา</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>


                            
                            
                        </div>
                        
                        <div role="tabpanel" class="tab-pane" id="tab_4">
                            <div class="panel panel-default panal-radius">
                                <div class="panel-body">
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-horizontal">
                                                <div class="form-group">
                                                    <label class="control-label col-sm-3" >
                                                		วันที่รับชำระค่าใช้บริการ&nbsp;&nbsp;จากวันที่ :
		                                            </label>
		                                            <div class="col-sm-2">
		                                                <div class="input-group">
		                                                    <input class="form-control" value="" >
		                                                    <span class="input-group-btn">
		                                                        <button class="btn btn-default" type="button"><span class="glyphicon glyphicon-calendar"></span></button>
		                                                    </span>
		                                                </div>
		                                            </div>
		                                            <label class="control-label col-sm-1">ถึงวันที่ :</label>
		                                            <div class="col-sm-2">
		                                                <div class="input-group">
		                                                    <input class="form-control" value="" >
		                                                    <span class="input-group-btn">
		                                                        <button class="btn btn-default" type="button"><span class="glyphicon glyphicon-calendar"></span></button>
		                                                    </span>
		                                                </div>
		                                            </div>
                                                    <div class="col-sm-2">
                                                        <button type="button" class="btn btn-primary pull-right"><span class="glyphicon glyphicon-search"></span> ค้นหา</button>
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
            <!--------------------------------->

            <div class="row">
                <div class="col-md-12">
                    <label class="label-panal label-success">ประวัติการรับชำระ</label>
                    <label class="label-panal"><button class="btn btn-lg btn-link" id="btn_print_1" onclick="window.location.href='revert_payment_2.html'; return false;"><span class="glyphicon glyphicon-file"></span> บันทึกปรับลดหนี้</button></label>
                    <label class="label-panal"><button class="btn btn-lg btn-link" id="btn_print_2" onclick="window.location.href='revert_payment_1.html'; return false;"><span class="glyphicon glyphicon-retweet"></span> บันทึกขอเงินคืน</button></label>
                    <div class="panel panel-default panal-radius">
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-md-12">
                                    <table class="table table-hover">
                                        <thead>
                                            <tr>
                                            	<th></th>
                                                <th>#</th>
                                                <th>สาขาที่ชำระเงิน</th>
                                                <th>เลขที่ใบเสร็จรับเงิน</th>
                                                <th>วันที่รับชำระ</th>
                                                <th>วันที่บันทึกข้อมูล</th> 
                                                <th>ชื่อลูกค้า</th> 
                                                <th>วิธีการชำระเงิน</th> 
                                                <th>เครื่องรับชำระ</th> 
                                                <th>ประเภทการชำระเงิน</th> 
                                                <th>ยอดเงิน</th> 
                                                <th>สถานะ</th> 
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td><input type="radio" name="optionsRadios" id="optionsRadios1" value="option1" checked> </td>
                                                <td>1</td>
                                                <td>DEVI 1</td>
                                                <td>EPO171701F150714000001</td>
                                                <td>12/01/2016</td>
                                                <td>12/01/2016</td>
                                                <td>บริษัท AEC จำกัด</td>
                                                <td>เงินสด</td>
                                                <td>01</td>
                                                <td>ชำระเต็มจำนวน</td>
                                                <td>1,000.00</td>
                                                <td>Active</td>
                                            </tr>
                                            <!--                                            <tr>
                                                                                            <td><input type="radio" name="optionsRadios" id="optionsRadios2" value="option2">  </td>
                                                                                            <td>DEVI 2</td>
                                                                                            <td>276598680804</td>
                                                                                            <td></td>
                                                                                            <td></td>
                                                                                            <td></td>
                                                                                            <td></td>
                                                                                            <td></td>
                                                                                            <td></td>
                                                                                            <td></td>
                                                                                            <td></td>
                                                                                        </tr>
                                                                                        <tr>
                                                                                            <td><input type="radio" name="optionsRadios" id="optionsRadios3" value="option3">  </td>
                                                                                            <td>DEVI 3</td>
                                                                                            <td>689456223</td>
                                                                                            <td></td>
                                                                                            <td></td>
                                                                                            <td></td>
                                                                                            <td></td>
                                                                                            <td></td>
                                                                                            <td></td>
                                                                                            <td></td>
                                                                                            <td></td>
                                                                                        </tr>-->
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <div class="modal fade" role="dialog" id="modal_print_1">
            <div class="modal-dialog modal-sm">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title">เหตุผลการพิมพ์ใบเสร็จรับเงินซ้ำ</h4>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label>เหตุผล :</label>
                                    <select class="form-control">
                                        <option>เครื่องพิมพ์ขัดข้อง</option>
                                        <option>2</option>
                                        <option>3</option>
                                        <option>4</option>
                                        <option>5</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" ><span class="glyphicon glyphicon-print"></span> ตกลง</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal"><span class="glyphicon glyphicon-remove-circle"></span> ยกเลิก</button>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
        </div><!-- /.modal -->

        <div class="modal fade" role="dialog" id="modal_print_2">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title">เหตุผลการพิมพ์</h4>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-horizontal">
                                    <div class="form-group">
                                        <label class="control-label col-sm-3" >
                                            <input type="radio" name="RadioOptions" id="" value="" checked> สำเนา :
                                        </label>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-sm-3" >
                                            <input type="radio" name="RadioOptions" id="" value="" > ใบแทน
                                        </label>
                                        <label class="control-label col-sm-2" >เหตุผล :</label>
                                        <div class="col-sm-4">
                                            <select class="form-control">
                                                <option>เครื่องพิมพ์ขัดข้อง</option>
                                                <option>2</option>
                                                <option>3</option>
                                                <option>4</option>
                                                <option>5</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" ><span class="glyphicon glyphicon-print"></span> ตกลง</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal"><span class="glyphicon glyphicon-remove-circle"></span> ยกเลิก</button>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
        </div><!-- /.modal -->

    </body>
    <script>
        $("#btn_print_1").click(function () {
            $("#modal_print_1").modal("show");
        });
        $("#btn_print_2").click(function () {
            $("#modal_print_2").modal("show");
        });
    </script>
</html>
