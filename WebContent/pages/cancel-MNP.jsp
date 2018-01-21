<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="resources/bootstrap-table/src/bootstrap-table.css" rel="stylesheet" type="text/css"/>
        <link href="resources/datepicker.min.css" rel="stylesheet" type="text/css"/>
        <link href="resources/custom.css" rel="stylesheet" type="text/css"/>
        <script src="resources/jquery.min.js" type="text/javascript"></script>
        <script src="resources/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="resources/bootstrap-table/src/bootstrap-table.js"></script>
        <script src="resources/bootstrap-datepicker.min.js"></script>
        <script src="resources/custom.js" type="text/javascript"></script>
    </head>
    <body>
        <header class="header_page"></header>
        <section class="container-fluid menu">
            <!-- <button class="menu-toggle btn btn-sm btn-default" style="margin-bottom: 5px"><span class="glyphicon glyphicon-step-forward"></span></button> -->
                <%-- <%@include  file="menu.jsp" %> --%>
            <div class="row">
                <div class="col-md-12">
                    <ol class="breadcrumb">
                        <li><i>ยกเลิกการรับชำระค่าบริการการคงสิทธิหมายเลขโทรศัพท์เคลื่อนที่</i></li>
                    </ol>
                    <label class="label-panal label-warning">ค้นหาข้อมูล Order</label>
                    <div class="panel panel-default panal-radius">
                        <div class="panel-body">
                            <div class="form-horizontal">
                                <div class="form-group">
                                    <label class="control-label col-sm-2" >
                                        <input type="radio" name="RadioOptions" id="" value="" checked> Order Id :
                                    </label>
                                    <div class="col-sm-2">
                                        <input class="form-control" value="021312091117188">
                                    </div>
                                    <label class="control-label col-sm-2">
                                        <input type="radio" name="RadioOptions" id="" value=""> MDN : 
                                    </label>
                                    <div class="col-sm-2">
                                        <input type="text" class="form-control" value="">
                                    </div>
                                    <label class="control-label col-sm-2">เลขที่ใบเสร็จรับเงิน :</label>
                                    <div class="col-sm-2">
                                        <input type="text" class="form-control" value="">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-2" >
                                        <input type="radio" name="RadioOptions" id="" value=""> Customer Name :
                                    </label>
                                    <div class="col-sm-2">
                                        <input class="form-control" value="">
                                    </div>
                                    <label class="control-label col-sm-2">
                                        <input type="radio" name="RadioOptions" id="" value=""> Surname : 
                                    </label>
                                    <div class="col-sm-2">
                                        <input type="text" class="form-control" value="">
                                    </div>
                                    <label class="control-label col-sm-2"></label>
                                    <div class="col-sm-2">
                                        <button class="btn btn-primary"><span class='glyphicon glyphicon-search'></span> ค้นหา</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <ul class="list-inline pull-right list-menu-set">
                <li><a href="" data-toggle="modal" data-target="#modal_authen"><span class="glyphicon glyphicon-remove-circle"></span> ยกเลิกการรับชำระ</a></li>
            </ul>


            <div class="row">
                <div class="col-md-12">
                    <label class="label-panal label-success">รายการรับชำระ</label>
                    <label class="label-panal" style="color: #000;">ระบุเหตุผลการยกเลิก</label>
                    <label class="label-panal">
                        <select class="form-control" style="margin: 0; ">
                            <option>ชื่อ-ที่อยู่ผิด</option>
                            <option>ผิดเลขหมาย</option>
                            <option>1</option>
                        </select>
                    </label>
                    <table id="table"
                         data-row-style="rowStyle"
                         data-toggle="table"
                         data-classes="table table-hover table-striped"
                         >
                      <thead >
                          <tr>
                              <th data-field="a" class="">#</th>
                              <th data-field="b" data-halign="center" data-align="center" class="">เลขที่ใบเสร็จรับเงิน</th>
                              <th data-field="c" data-halign="center" data-align="center" class="">Order ID</th>
                              <th data-field="e" data-halign="center" data-align="center" class="">MDN</th>
                              <th data-field="f" data-halign="center" data-align="left" class="">ชื่อลูกค้า</th>
                              <th data-field="g" data-halign="center" data-align="center" class="" >วันที่รับชำระ</th>
                              <th data-field="h" data-halign="center" data-align="center" class="" >ยอดเงินชำระ</th>
                              <th data-field="i" data-halign="center" data-align="center" class="col-md-1" ></th>
                          </tr>
                      </thead>
                      <tbody>
                          <tr>
                              <td>1</td>
                              <td>EP0171701F150714000020</td>
                              <td>021312091117188</td>
                              <td>48551245778</td>
                              <td>บริษัท AEC จำกัด</td>
                              <td>26/02/2016</td>
                              <td>39.00</td>
                              <td><button class="btn btn-link"><span class="glyphicon glyphicon-trash"></span></button></td>    
                          </tr>
                      </tbody>
                  </table>
                </div>
                
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
                    <button type="button" class="btn btn-default" ><span class="glyphicon glyphicon-ok-circle"></span> ตกลง</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal"><span class="glyphicon glyphicon-remove-circle"></span> ยกเลิก</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal --> 


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
