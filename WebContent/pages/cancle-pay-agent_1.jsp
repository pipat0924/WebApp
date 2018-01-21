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
                        <li><i>ยกเลิกตัวแทนรับชำระ</i></li>
                        <li class="active"><i>ค้นหาตัวแทนรับชำระ</i></li>
                        
</ol>








                   
                </div>
            </div>


<!-- copy -->

 <button type="button" class="btn btn-primary btn-warning"  data-toggle="modal" data-target="#CustomerSearch"><span class="glyphicon glyphicon-search"></span>  ค้นหา</button>
                    <div class="panel panel-default panal-radius">
                        <div class="panel-body">
                            <div class="col-md-12">
                                <div class="form-horizontal">
                                    <div class="form-group">
                                        <label class="control-label col-sm-2" >ประเภทตัวแทนรับชำระ :</label>
                                        <div class="col-sm-2">

                                            <div class="btn-group">
                        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                            <span class="selection">การไฟฟ้าส่วนภูมิภาค</span><span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu" id='testmenu'>
                            <li><a href="#">การไฟฟ้านครหลวง</a></li>
                            <li><a href="#">การประปาส่วนภูมิภาค</a></li>
                            <li><a href="#">การประปานครหลวง</a></li>
                        </ul>
                    </div>
                                        </div>

                                        <label class="control-label col-sm-2">เลขที่ใบเสร็จรับเงิน :</label>
                                        <div class="col-sm-2">
                                            <input class="form-control" value="EPCE015099125">
                                        </div>

                                        <div class="col-sm-2">
                                            <button class="btn btn-primary"><span class="glyphicon glyphicon-search"></span>  ค้นหา</button>

                                        </div>
                                       
                                       
                                    </div>

                                    <!-- <div class="form-group">
                                        <label class="control-label col-sm-2"></label>
                                        <div class="col-sm-2">
                                            <button class="btn btn-primary"><span class="glyphicon glyphicon-search"></span>  ค้นหา</button>
                                        </div>
                                    </div>
 -->

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <ul class="list-inline pull-right list-menu-set">
                <li><a href="cancle-pay-agent_2.jsp"><span class="glyphicon glyphicon-plus-sign"></span> เพิ่มรายการการยกเลิก</a></li>
                <li><a href="sumary-pay-counter_electric.jsp"><span class="glyphicon glyphicon-th-list"></span> สรุปรายการการยกเลิก</a> <span class="badge badge_modefil">1</span></li>
            </ul>


<!-- endcopy -->













            <!-- <ul class="list-inline pull-right list-menu-set">
                <li><a href="cancle-pay-counter_electric.jsp"><span class="glyphicon glyphicon-log-out"></span> กลับไปเพิ่มรายการ</a></li>
                <li><a href="#modal_authen" data-toggle="modal" data-target="#modal_authen"><span class="glyphicon glyphicon-th-list"></span> ยกเลิกรายการ</a></li>
                <li><a href="#"><span class="glyphicon glyphicon-print"></span> บันทึกและการพิมพ์</a></li>
            </ul> -->


            <!-- <div class="row">
                <div class="col-md-12">
                    <ul class="nav nav-tabs" role="tablist">
                             <li class="col-md-2"><label class="label-panal label-warning">เหตุผลการยกเลิก</label></li>
                            <li class="col-md-3">
                                <select class="form-control">
                                    <option>ชื่อ-ที่อยู่ผิด</option>
                                    <option>2</option>
                                    <option>3</option>
                                    <option>4</option>
                                    <option>5</option>
                                </select></li>
                        
                    </ul> -->
				


                    
                    <table id="table_1"
                           data-row-style="rowStyle"
                           data-toggle="table"
                           data-detail-view="true"
                           data-detail-formatter="detailFormatter"
                           data-classes="table table-hover table-striped"
                           >
                        <thead>
                            <tr>
                                <th data-field="a" data-align="center" class="">#</th>
                                <th data-field="b" data-align="center" class="">เลขที่ใบเสร็จรับเงิน</th>
                                <th data-field="c" data-align="center" class="">วันที่รับชำระ</th>
                                <th data-field="e" data-align="center" class="">เลขที่ลูกค้า</th>
                                <th data-field="f" data-halign="center" data-align="left" class="">ชื่อลูกค้า</th>
                                <th data-field="g" data-align="center" class="">วิธีการชำระเงิน</th>
                                <th data-field="h" data-align="center" class="">ภาษีมูลค่าเพิ่ม</th>
                                <th data-field="i" data-halign="center" data-align="right" class="">ยอดเงิน</th>
                                <th data-field="k" data-halign="center" data-align="right" data-align="center"class="col-xs-1">สาขาที่รับชำระ</th>
                                <th data-field="l" data-align="center" class="col-xs-1">สถานะ</th>
                            </tr>



                        </thead>
                        <tbody>
                                                          
                            <tr>
							  
                                <td>
								
  <input type="radio" name="optradio">

                                <td>EPCE015099125</td>
                                <td>02-08-2559</td>
                                <td>015099125</td>
                                <td>ณัฎฐ์อนงค์ สุทธิเชษฐ์</td>
                                <td>เงินสด</td>
                                <td>550.00</td>
                                <td>558.50</td>
                                <td>ศบล.แจ้งวัฒนะ</td>
                                <td>ปกติ</td>
                            </tr>


                        <span style="display: none;" id="desc0">
                            <pre><strong class="bold">Description</strong></pre>
                        </span>
                        </tr>

                        </tbody>
                    </table>

                </div>
            </div>

            <!--------------------------------------->
            <!--------------------------------------->

        </section>
        <!------------------------->
        <!------------------------->

        <div class="modal fade" role="dialog" id="modal_authen">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title">Authentication</h4>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="col-md-2">

                                </div>
                                <div class="col-md-10">
                                    <div class="form-horizontal">
                                        <div class="form-group">
                                            <label class="col-sm-4 control-label">ผู้อนุมัติ :</label>
                                            <div class="col-sm-8">
                                                <input type="text" class="form-control">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-4 control-label">รหัสผ่าน :</label>
                                            <div class="col-sm-8">
                                                <input type="password" class="form-control">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" onclick="window.location.href='cancel-pay-1-stap_3.jsp'; return false;"><span class="glyphicon glyphicon-ok-circle"></span> ตกลง</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal"><span class="glyphicon glyphicon-remove-circle"></span> ยกเลิก</button>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
        </div><!-- /.modal --> 

        

       <footer style="position: absolute; bottom: 0; width: 100%">
            <nav class="navbar navbar-default" style="position: relative; bottom: 0; margin-top: 60px; text-align: center; background-color: #f39c12;">
                <div class="container-fluid">
                    <p class="navbar-text" style="color: #fff">Copyright 2015 CAT Telecom Public Co., Ltd CAT</p>
                </div>
            </nav>
        </footer>



    </body>
    <script type="text/javascript">
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
