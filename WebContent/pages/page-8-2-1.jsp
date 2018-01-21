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
                <li >ค้นหาข้อมูลอ้างอิง</li>
                <li class="active">บันทึกคำร้องของคืนเงิน</li>
            </ol>
            <div class="row">
                <div class="col-md-12 tab-modefile">
                    <!-- Nav tabs -->
                    <ul class="nav nav-tabs" role="tablist">
                        <li role="presentation" class="active" ><a href="#tab_1" aria-controls="tab_1" role="tab" data-toggle="tab">รายละเอียดลูกค้า</a></li>
                        <li role="presentation">
                        <li class="list-inline pull-right list-menu-set">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="col-sm-6">
                                        <button type="button" class="btn btn-primary pull-right"><span class="glyphicon glyphicon-floppy-disk"></span> บันทึก</button> 
                                    </div>
                                    <div class="col-sm-6">
                                        <button type="button" class="btn btn-danger pull-right" onclick="window.location.href='revert_payment.html'"><span class="glyphicon glyphicon-remove"></span> ยกเลิก</button>   
                                    </div>
                                </div>
                            </div>
                        </li>
                        </li>
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
                                                    <div class="col-sm-2">
                                                        <input class="form-control" value="700006573" disabled="">
                                                    </div>
                                                    <label class="control-label col-sm-1" >ชื่อลูกค้า :</label>
                                                    <div class="col-sm-3">
                                                        <input class="form-control" value="บริษัท AEC จำกัด" disabled="">
                                                    </div>
                                                    <label class="control-label col-sm-2" >เลขที่คำร้อง :</label>
                                                    <div class="col-sm-2">
                                                        <input class="form-control" value="EPA000001" disabled="">
                                                    </div>

                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label col-sm-2" >กลุ่มลูกค้า :</label>
                                                    <div class="col-sm-2">
                                                        <input class="form-control" disabled="">
                                                    </div>
                                                    <label class="control-label col-sm-4" ></label>
                                                    <label class="control-label col-sm-2" >วันที่บันทึกคำร้อง :</label>
                                                    <div class="col-sm-2">
                                                        <div class="input-group">
                                                            <input class="form-control" value="12/01/2016" disabled="">
                                                            <span class="input-group-btn">
                                                                <button class="btn btn-default" type="button"><span class="glyphicon glyphicon-calendar"></span></button>
                                                            </span>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                <div class="col-sm-8">
                                                    </div>
                                                    <label class="control-label col-sm-2" >สถานะ :</label>
                                                    <div class="col-sm-2">
                                                        <input class="form-control" value="รออนุมัติ" disabled="">
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
                                                <input type="radio" name="RadioOptions1" id="RadioOptions1" value="" checked> เลขที่ใบแจ้งค่าบริการ :
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
                                                <input type="radio" name="RadioOptions1" id="RadioOptions2" value=""> วันที่จัดทำใบแจ้งค่าใช้บริการ&nbsp;&nbsp;จากวันที่ :
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
                                                <input type="radio" name="RadioOptions1" id="RadioOptions3" value="" > หมายเลขบริการ :
                                            </label>
                                            <div class="col-sm-2">
                                                <input class="form-control" value="">
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
                    <div class="panel panel-default panal-radius">
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-md-12">
                                    <ul class="nav nav-tabs" role="tablist">
                                        <li class="col-md-3"><label class="label-panal label-warning">รายละเอียดการรับชำระค่าบริการ</label></li>
                                        <li class="col-md-6">
                                            <div class="form-inline">
                                                <!--<div class="form-group">-->
                                                <label >เหตุผลการร้องเรียน* :</label>
                                                <select class="form-control">
                                                    <option>คิดค่าบริการผิด และขอคืนเงิน</option>
                                                    <option>2</option>
                                                    <option>3</option>
                                                    <option>4</option>
                                                    <option>5</option>
                                                </select>
                                                <!--</div>-->
                                            </div>
                                        </li>
                                        <li class="col-md-3">
                                            <div class="form-inline">
                                                <label >ส่งต่อหน่วยงาน :</label>
                                                <select class="form-control">
                                                    <option>ลง.</option>
                                                    <option>2</option>
                                                    <option>3</option>
                                                    <option>4</option>
                                                    <option>5</option>
                                                </select>
                                            </div>
                                        </li>
                                    </ul>                                  
                                    <table id="table_1"
                                           data-row-style="rowStyle"
                                           data-toggle="table"
                                           data-detail-view="true"
                                           data-detail-formatter="detailFormatter"
                                           data-classes="table table-hover table-striped"
                                           >
                                        <thead>
                                            <tr>
                                            <th data-field="a1" class=""></th>
                                                <th data-field="a" class="">#</th>
                                                <th data-field="b" class="">เลขที่ใบแจ้งค่าใช้บริการ</th>
                                                <th data-field="c"  class="">วันที่จัดทำใบแจ้งค่าใช้บริการ</th>
                                                <th data-field="e" data-halign="center" data-align="right" class="">วันครบกำหนด</th>
                                                <th data-field="f" data-halign="center" data-align="right" class="">ยอดก่อนภาษี</th>
                                                <th data-field="g" data-halign="center" data-align="right" class="">ส่วนลด</th>
                                                <th data-field="h" data-halign="center" data-align="right" class="">ภาษีมูลค่าเพิ่ม</th>
                                                <th data-field="i" data-halign="center" data-align="right" class="">ยอดเงินรวมภาษี</th>
                                                <th data-field="k" class="col-xs-1">จำนวนเงินคงค้าง</th>
                                                <th data-field="l" class="col-xs-1">ยอดชำระ</th>
                                                <th data-field="j" class="col-xs-6">ภาษีหัก ณ ที่จ่าย</th>
                                                <th data-field="q" class="col-xs-6">รอบการใช้งาน</th>
                                                <th data-field="m" class="col-xs-1">สถานะ</th>
                                                <th data-field="n" class="col-xs-1">จำนวนเงินที่ต้องการปรับลด</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td><input type="checkbox" class="checkbox" id="checkbox_37" onclick="show_price('37')"></td>
                                                <td>1</td>
                                                <td>255900001</td>
                                                <td>11/12/2015</td>
                                                <td>31/12/2015</td>
                                                <td>1,000.00</td>
                                                <td>0.00</td>
                                                <td>70.00</td>
                                                <td>1,070.00</td>
                                                <td>0.00</td>
                                                <td>1,070.00</td>
                                                <td>0.00</td>
												<td>Active</td>
                                                <td>01/01/2016-30/01/2016</td>
												
                                                
                                                
                                                <td><input class="form-control" value="" style="text-align: right"></td>
                                        <span style="display: none;" id="desc0">
                                            <pre><strong class="bold">Description</strong></pre>
                                        </span>
                                        </tr>

                                        </tbody>
                                    </table>
                                    <br>
                                    <div class="col-md-12">
                                        <div class="form-inline">
                                            <label class="col-md-1">หมายเหตุ* :</label>
                                            <input class="form-control col-md-11" value="" >
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
                    <li role="presentation" class="active"><a href="#tab-2-1" aria-controls="tab-2-1" role="tab" data-toggle="tab">วิธีการรับเงินคืน</a></li>
                    <li role="presentation" class=""><a href="#tab-2-2" aria-controls="tab-2-2" role="tab" data-toggle="tab">เอกสารแนบพร้อมรับรองสำเนาถูกต้อง</a></li>
                </ul>
                <!-- Tab panes -->
                <div class="tab-content">
                    <div role="tabpanel" class="tab-pane active" id="tab-2-1">
                       <div class="panel panel-default panal-radius">
                                <div class="panel-body">
                                    <div class="form-horizontal">
                                        <div class="form-group">
                                            <label class="control-label col-sm-2"  style="text-align: left">
                                                <input type="radio" name="RadioOptions1" id="RadioOptions1" value="" checked> รับเงินสดที่ สาขา :
                                            </label>
                                            <div class="col-sm-3">
                                                 <select class="form-control">
                                                    <option>ศบล.แจ้งวัฒนะ</option>
                                                    <option>2</option>
                                                    <option>3</option>
                                                    <option>4</option>
                                                    <option>5</option>
                                                </select>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="control-label col-sm-2" style="text-align: left" >
                                                <input type="radio" name="RadioOptions1" id="RadioOptions2" value=""> เงินโอนเข้าบัญชีธนาคาร
                                            </label>
                                            <div class="col-sm-3">
                                                 <select class="form-control">
                                                    <option>xxxxxxxxx</option>
                                                    <option>2</option>
                                                    <option>3</option>
                                                    <option>4</option>
                                                    <option>5</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-2" >
                                                 สาขา :
                                            </label>
                                            <div class="col-sm-3">
                                                <input class="form-control" value="">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-2" >
                                                 ชื่อบัญชี :
                                            </label>
                                            <div class="col-sm-3">
                                                <input class="form-control" value="">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-2" >
                                                 เลขที่บัญชี :
                                            </label>
                                            <div class="col-sm-3">
                                                <input class="form-control" value="">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        
                    </div>

                    <div role="tabpanel" class="tab-pane" id="tab-2-2">
                      
                        
                    </div>

            </div>
        </div>
            
        </section>

    </body>
    <script>
        $("#btn_print_1").click(function () {
            $("#modal_print_1").modal("show");
        });
        $("#btn_print_2").click(function () {
            $("#modal_print_2").modal("show");
        });
        
        function detailFormatter(val, row, ind) {
	    	return    	'<table class="table table-striped table-bordered">'+
					    	'<thead>'+
					    		'<tr>'+
					    		'<th class="text-center">ลำดับที่</th>'+
					    		'<th class="text-center">รหัสผลิตภัณฑ์</th>'+
					    		'<th class="text-center">ชื่อผลิตภัณฑ์</th>'+
					    		'<th class="text-center">ผลิตภัณฑ์ย่อย</th>'+
					    		'<th class="text-center">ชื่อผลิตภัณฑ์ย่อย</th>'+
					    		'<th class="text-center">ประเภทรายได้</th>'+
					    		'<th class="text-right">จำนวนเงิน</th>'+
					    		'<th class="text-center">จำนวนเงินที่ต้องการปรับลด</th>'+
					    		'</tr>'+
					    	'</thead>'+
					    	'<tbody>'+
					    		'<tr><td class="text-center">1</td><td class="text-center">102010403</td><td>บ.INMARSAT</td><td class="text-center">3</td><td>บ.INMARSAT-C</td><td>01</td><td class="text-right">1,000.00</td><td><input class="form-control" value="" style="text-align: right"></td></tr>'+
					    	'</tbody>'+
				    	'</table>';	
	    }
    </script>
</html>
