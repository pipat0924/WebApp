<%-- 
    Document   : payment
    Created on : Feb 10, 2016, 1:57:26 PM
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
    <style>
        thead th {
            background-color: #fff;
            color: black;
        }

    </style>
    <body>
        <header class="header_page"></header>
        <section class="container-fluid menu">
            <!-- <button class="menu-toggle btn btn-sm btn-default" style="margin-bottom: 5px"><span class="glyphicon glyphicon-step-forward"></span></button> -->
                <%-- <%@include  file="menu.jsp" %> --%>
            <div class="row">
                <div class="col-md-12">
                    <ol class="breadcrumb">
                        <li><i>รับชำระค่าบริการ</i></li>
                        <li>ค้นหาข้อมูลลูกค้า</li>
                        <li>สรุปรายการรับชำระเงิน</li>
                        <li class="active">เลือกวิธีการรับชำระ</li>
                        <li>ผลการรับชำระ</li>
                    </ol>
                </div>
            </div>
            <ul class="list-inline pull-right">
                <li><a href="pay-service-charge_2.jsp"><span class="glyphicon glyphicon-th-list"></span> สรุปรายการรับชำระ</a></li>
                <li><a href="#"><span class="glyphicon glyphicon-print"></span> บันทึกและพิมพ์</a></li>
                <li><a href="#"><span class="glyphicon glyphicon-remove-circle"></span> ยกเลิกรายการ</a></li>
            </ul>
            <br/><br/>

            <div class="row">
                <div class="col-md-5">
                    <label class="label-panal label-warning">รายการหัก</label>

                    <div class="btn-group">
                        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                            <span class="selection">ภาษีหัก ณ ที่จ่าย</span><span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu" id='testmenu'>
                            <li><a href="#" onclick="showTab1('1')">ภาษีหัก ณ ที่จ่าย</a></li>
                            <li><a href="#" onclick="showTab1('2')">อื่น ๆ</a></li>
                            <li><a href="#" onclick="showTab1('3')">offset</a></li>
                        </ul>
                    </div>
                    <ul class="dropdown-menu hidden" id='myTab1'>
                        <li><a href="#tab-1-1" class="active" aria-controls="tab-1-1" data-toggle="tab">ภาษีหัก ณ ที่จ่าย</a></li>
                        <li><a href="#tab-1-2" aria-controls="tab-1-2"  data-toggle="tab">อื่น ๆ</a></li>
                        <li><a href="#tab-1-3" aria-controls="tab-1-3"  data-toggle="tab">offset</a></li>
                    </ul>
                    <div class="panel panel-default panal-radius">
                        <div class="panel-body" style="padding-right: 0; padding-left: 0;">
                            <div class="col-md-12">
                                <!-- Tab panes -->
                                <div class="tab-content">
                                    <div role="tabpanel" class="tab-pane active" id="tab-1-1">
                                        <div class="form-horizontal">
                                            <div class="form-group">
                                                <label class="control-label col-sm-6" >ประเภทภาษีหัก ณ ที่จ่าย :</label>
                                                <div class="col-sm-5">
                                                    <label class="radio-inline">
                                                        <input type="radio" name="RadioOptions" id="Radio1" value="option1" checked><b> 69 ทวิ</b>
                                                    </label>&nbsp;&nbsp;&nbsp;
                                                    <label class="radio-inline">
                                                        <input type="radio" name="RadioOptions" id="Radio2" value="option2"><b> 3 เตรส</b>
                                                    </label>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-sm-6" >เลขที่เอกสาร :</label>
                                                <div class="col-sm-5">
                                                    <input type="text" class="form-control">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-sm-6" >จำนวนเงิน :</label>
                                                <div class="col-sm-5">
                                                    <input type="text" class="form-control">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-sm-6" ></label>
                                                <div class="col-sm-5">
                                                    <button class="btn btn-link"><span class="glyphicon glyphicon-th-list"></span>  เพิ่มรายการภาษีหัก ณ ที่จ่าย</button>
                                                </div>
                                            </div>
                                        </div>
                                        <table class="table">
                                            <thead>
                                                <tr>
                                                    <th>#</th>
                                                    <th>เลขที่เอกสาร</th>
                                                    <th>ประเภทหักภาษี ณ ที่จ่าย</th>
                                                    <th>จำนวนเงิน</th>
                                                    <th></th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td>1</td>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td><a href="#"><span class="glyphicon glyphicon-trash"></span></a></td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>

                                    <div role="tabpanel" class="tab-pane" id="tab-1-2">
                                        <div class="form-horizontal">
                                            <div class="form-group">
                                                <label class="control-label col-sm-3" >รายการ :</label>
                                                <div class="col-sm-3">
                                                    <input type="text" class="form-control">
                                                </div>
                                                <label class="control-label col-sm-3" >จำนวนเงิน :</label>
                                                <div class="col-sm-3">
                                                    <input type="text" class="form-control">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-sm-3" >ศูนย์ต้นทน :</label>
                                                <div class="col-sm-3">
                                                    <input type="text" class="form-control">
                                                </div>
                                                <label class="control-label col-sm-3">ภาษีหัก ณ ที่จ่าย :</label>
                                                <div class="col-sm-3">
                                                    <input type="text" class="form-control">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-sm-9" >ภาษีซื้อ :</label>
                                                <div class="col-sm-3">
                                                    <input type="text" class="form-control">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-sm-8" ></label>
                                                <div class="col-sm-2">
                                                    <button class="btn btn-link"><span class="glyphicon glyphicon-th-list"></span> เพิ่มรายการหักอื่น ๆ</button>
                                                </div>
                                            </div>
                                        </div>
                                        <table class="table">
                                            <thead>
                                                <tr>
                                                    <th>#</th>
                                                    <th>รายการ</th>
                                                    <th>รหัสศูนย์ต้นทุน</th>
                                                    <th>ภาษีหัก ณ ที่จ่าย</th>
                                                    <th>จำนวนเงิน</th>
                                                    <th></th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td>1</td>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td><a href="#"><span class="glyphicon glyphicon-trash"></span></a></td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>

                                    <div role="tabpanel" class="tab-pane" id="tab-1-3">
                                        <div class="form-horizontal">
                                            <div class="form-group">
                                                <label class="control-label col-sm-8" >เลขที่เอกสารอ้างอิง :</label>
                                                <div class="col-sm-4">
                                                    <input type="text" class="form-control">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-sm-8" >จำนวนเงิน :</label>
                                                <div class="col-sm-4">
                                                    <input type="text" class="form-control">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-sm-8" ></label>
                                                <div class="col-sm-2">
                                                    <button class="btn btn-link"><span class="glyphicon glyphicon-th-list"></span> เพิ่มรายการหักอื่น ๆ</button>
                                                </div>
                                            </div>
                                        </div>
                                        <table class="table">
                                            <thead>
                                                <tr>
                                                    <th>#</th>
                                                    <th>รายการ</th>
                                                    <th>รหัสศูนย์ต้นทุน</th>
                                                    <th>ภาษีหัก ณ ที่จ่าย</th>
                                                    <th>จำนวนเงิน</th>
                                                    <th></th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td>1</td>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td><a href="#"><span class="glyphicon glyphicon-trash"></span></a></td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-md-7">
                    <label class="label-panal label-warning">ข้อมูลการเติมเงิน</label>

                    <!-- Single button -->
                    <div class="btn-group">
                        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            รายการ <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu">
                            <li><a href="#tab1" aria-controls="tab1" data-toggle="tab">เงินสด</a></li>
                            <li><a href="#tab2" aria-controls="tab2"  data-toggle="tab">เช็ค</a></li>
                            <li><a href="#tab3" aria-controls="tab3"  data-toggle="tab">บัตรเครดิต</a></li>
                            <li><a href="#tab4" aria-controls="tab3"  data-toggle="tab">ธนาณัติ</a></li>
                            <li><a href="#tab5" aria-controls="tab5"  data-toggle="tab">ตั๋วแลกเงิน</a></li>
                            <li><a href="#tab6" aria-controls="tab6"  data-toggle="tab">คูปอง</a></li>
                            <li><a href="#tab7" aria-controls="tab7"  data-toggle="tab">อื่น ๆ</a></li>
                            <li><a href="#tab8" aria-controls="tab8"  data-toggle="tab">เงินโอนในประเทศ</a></li>
                        </ul>
                    </div>
                    
                    <div class="panel panel-default panal-radius">
                        <div class="panel-body" style="padding-right: 0; padding-left: 0;">
                            <div class="col-md-12">
                                <div class="tab-content" >

                                    <div role="tabpanel" class="tab-pane fade in active" id="tab1">
                                        <div class="form-horizontal">
                                            <div class="form-group">
                                                <label class="control-label col-sm-8" >จำนวนเงิน :</label>
                                                <div class="col-sm-4">
                                                    <input type="text" class="form-control">
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div role="tabpanel" class="tab-pane fade" id="tab2">
                                        <div class="form-horizontal">
                                            <div class="form-group">
                                                <label class="control-label col-sm-2" >รหัสธนาคาร</label>
                                                <div class="col-sm-4">
                                                    <select class="form-control">
                                                        <option>1</option>
                                                        <option>2</option>
                                                        <option>3</option>
                                                        <option>4</option>
                                                        <option>5</option>
                                                    </select>
                                                </div>
                                                <label class="control-label col-sm-2" >เลขที่เช็ค</label>
                                                <div class="col-sm-4">
                                                    <input class="form-control" id="" value="">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-sm-2" >ชื่อธนาคาร</label>
                                                <div class="col-sm-4">
                                                    <select class="form-control">
                                                        <option>1</option>
                                                        <option>2</option>
                                                        <option>3</option>
                                                    </select>
                                                </div>
                                                <label class="control-label col-sm-2" >วันที่หน้าเช็ค</label>
                                                <div class="col-sm-4">
                                                    <div class="input-group">
                                                        <input class="form-control" id="" value="">
                                                        <span class="input-group-btn">
                                                            <button class="btn btn-default" type="button"><span class="glyphicon glyphicon-calendar"></span></button>
                                                        </span>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-sm-2" >สาขา</label>
                                                <div class="col-sm-4">
                                                    <input class="form-control" id="" value="">
                                                </div>
                                                <label class="control-label col-sm-2" >จำนวนเงิน</label>
                                                <div class="col-sm-4">
                                                    <input class="form-control input-sm input_number" id="" value="">
                                                </div>
                                            </div>
                                        </div>
                                        <button class="btn btn-warning pull-right" id=""><span class="glyphicon glyphicon-plus-sign"></span> เพิ่มรายการอื่น ๆ</button>
                                        <br/><br/>
                                        <table class="table">
                                            <thead>
                                                <tr>
                                                    <th>#</th>
                                                    <th>รหัสธนาคาร</th>
                                                    <th>ชื่อธนาคาร</th>
                                                    <th>สาขา</th>
                                                    <th>เลขที่เช็ค</th>
                                                    <th>วันที่หน้าเช็ค</th>
                                                    <th>จำนวนเงิน</th>
                                                    <th></th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td>1</td>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td><a href="#"><span class="glyphicon glyphicon-trash"></span></a></td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>

                                    <div role="tabpanel" class="tab-pane fade" id="tab3">
                                        <div class="form-horizontal">
                                            <div class="form-group">
                                                <label class="control-label col-sm-3" >ประเภทบัตรเครดิต :</label>
                                                <div class="col-sm-3">
                                                    <select class="form-control">
                                                        <option>1</option>
                                                        <option>2</option>
                                                        <option>3</option>
                                                    </select>
                                                </div>
                                                <label class="control-label col-sm-3" >เลขที่บัตร :</label>
                                                <div class="col-sm-3">
                                                    <input class="form-control" id="" value="">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-sm-3" >EDC :</label>
                                                <div class="col-sm-3">
                                                    <select class="form-control">
                                                        <option>1</option>
                                                        <option>2</option>
                                                        <option>3</option>
                                                    </select>
                                                </div>
                                                <label class="control-label col-sm-3" >จำนวนเงิน :</label>
                                                <div class="col-sm-3">
                                                    <input class="form-control" id="" value="">
                                                </div>
                                            </div>
                                        </div>
                                        <button class="btn btn-warning pull-right" id=""><span class="glyphicon glyphicon-plus-sign"></span> เพิ่มรายการบัตรเครดิต</button>
                                        <br/><br/>
                                        <table class="table">
                                            <thead>
                                                <tr>
                                                    <th>#</th>
                                                    <th>ประเภทบัตรเครดิต</th>
                                                    <th>เลขที่บัตร</th>
                                                    <th>EDC</th>
                                                    <th>จำนวนเงิน</th>
                                                    <th></th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td>1</td>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td><a href="#"><span class="glyphicon glyphicon-trash"></span></a></td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>

                                    <div role="tabpanel" class="tab-pane fade" id="tab4">
                                        <div class="form-horizontal">
                                            <div class="form-group">
                                                <label class="control-label col-sm-2" >วันที่ธนาณัติ</label>
                                                <div class="col-sm-4">
                                                    <div class="input-group">
                                                        <input class="form-control" id="" value="">
                                                        <span class="input-group-btn">
                                                            <button class="btn btn-default" type="button"><span class="glyphicon glyphicon-calendar"></span></button>
                                                        </span>
                                                    </div>
                                                </div>
                                                <label class="control-label col-sm-2" >เลขที่ธนาณัติ</label>
                                                <div class="col-sm-4">
                                                    <input class="form-control" id="" value="">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-sm-2" >รหัสไปรษณีย์</label>
                                                <div class="col-sm-4">
                                                    <input class="form-control" id="" value="">
                                                </div>
                                                <label class="control-label col-sm-2" >จำนวนเงิน</label>
                                                <div class="col-sm-4">
                                                    <input class="form-control" id="" value="">
                                                </div>
                                            </div>
                                        </div>
                                        <button class="btn btn-warning pull-right" id=""><span class="glyphicon glyphicon-plus-sign"></span> เพิ่มรายการธนาณัติ</button>
                                        <br/><br/>
                                        <table class="table">
                                            <thead>
                                                <tr>
                                                    <th>#</th>
                                                    <th>เลขที่ธนาณัติ</th>
                                                    <th>วันที่ธนาณัติ</th>
                                                    <th>รหัสไปรษณีต้นทาง</th>
                                                    <th>จำนวนเงิน</th>
                                                    <th></th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td>1</td>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td><a href="#"><span class="glyphicon glyphicon-trash"></span></a></td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>

                                    <div role="tabpanel" class="tab-pane fade" id="tab5">
                                        <div class="form-horizontal">
                                            <div class="form-group">
                                                <label class="control-label col-sm-3" >วันที่ตั๋วแลกเงิน :</label>
                                                <div class="col-sm-3">
                                                    <div class="input-group">
                                                        <input class="form-control" id="" value="">
                                                        <span class="input-group-btn">
                                                            <button class="btn btn-default" type="button"><span class="glyphicon glyphicon-calendar"></span></button>
                                                        </span>
                                                    </div>
                                                </div>
                                                <label class="control-label col-sm-3" >เลขที่ตั๋วแลกเงิน :</label>
                                                <div class="col-sm-3">
                                                    <input class="form-control" id="" value="">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-sm-3" >รหัสไปรษณีย์ :</label>
                                                <div class="col-sm-3">
                                                    <input class="form-control" id="" value="">
                                                </div>
                                                <label class="control-label col-sm-3" >จำนวนเงิน :</label>
                                                <div class="col-sm-3">
                                                    <input class="form-control" id="" value="">
                                                </div>
                                            </div>
                                        </div>
                                        <button class="btn btn-warning pull-right" id=""><span class="glyphicon glyphicon-plus-sign"></span> เพิ่มรายการตั๋วแลกเงิน</button>
                                        <br/><br/>
                                        <table class="table">
                                            <thead>
                                                <tr>
                                                    <th>#</th>
                                                    <th>เลขที่ตั๋วแลกเงิน</th>
                                                    <th>วันที่ตั๋วแลกเงิน</th>
                                                    <th>รหัสไปรษณีต้นทาง</th>
                                                    <th>จำนวนเงิน</th>
                                                    <th></th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td>1</td>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td><a href="#"><span class="glyphicon glyphicon-trash"></span></a></td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>


                                    <div role="tabpanel" class="tab-pane fade" id="tab6">
                                        <div class="form-horizontal">
                                            <div class="form-group">
                                                <label class="control-label col-sm-9" >เลขที่คูปอง</label>
                                                <div class="col-sm-3">
                                                    <input class="form-control" value="">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-sm-9" >จำนวนเงิน</label>
                                                <div class="col-sm-3">
                                                    <input class="form-control input_number" value="1,100.00">
                                                </div>
                                            </div>
                                        </div>
                                        <button class="btn btn-warning pull-right" id=""><span class="glyphicon glyphicon-plus-sign"></span> เพิ่มรายการคูปอง</button>
                                        <br/><br/>
                                        <table class="table">
                                            <thead>
                                                <tr>
                                                    <th>#</th>
                                                    <th>เลขที่คูปอง</th>
                                                    <th>รายละเอียด</th>
                                                    <th>วันที่หมดอายุ</th>
                                                    <th>จำนวนเงิน</th>
                                                    <th></th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td>1</td>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td><a href="#"><span class="glyphicon glyphicon-trash"></span></a></td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>

                                    <div role="tabpanel" class="tab-pane fade" id="tab7">
                                        <div class="form-horizontal">
                                            <div class="form-group">
                                                <label class="control-label col-sm-3" >ช่องทางการชำระ :</label>
                                                <div class="col-sm-3">
                                                    <select class="form-control">
                                                        <option>2C2P</option>
                                                        <option>111</option>
                                                        <option>222</option>
                                                    </select>
                                                </div>
                                                <label class="control-label col-sm-3" >เลขที่อ้างอิง :</label>
                                                <div class="col-sm-3">
                                                    <input class="form-control" value="">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-sm-3" >วันที่อ้างอิง :</label>
                                                <div class="col-sm-3">
                                                    <div class="input-group">
                                                        <input class="form-control" id="" value="">
                                                        <span class="input-group-btn">
                                                            <button class="btn btn-default" type="button"><span class="glyphicon glyphicon-calendar"></span></button>
                                                        </span>
                                                    </div>
                                                </div>
                                                <label class="control-label col-sm-3" >จำนวนเงิน :</label>
                                                <div class="col-sm-3">
                                                    <input class="form-control" value="">
                                                </div>
                                            </div>
                                        </div>
                                        <button class="btn btn-warning pull-right" id=""><span class="glyphicon glyphicon-plus-sign"></span> เพิ่มรายการอื่น ๆ</button>
                                        <br/><br/>
                                        <table class="table">
                                            <thead>
                                                <tr>
                                                    <th>#</th>
                                                    <th>เลขที่อ้างอิง</th>
                                                    <th>ช่องทางการรับชำระ</th>
                                                    <th>วันที่รับชำระ</th>
                                                    <th>จำนวนเงิน</th>
                                                    <th></th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td>1</td>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td><a href="#"><span class="glyphicon glyphicon-trash"></span></a></td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>

                                    <div role="tabpanel" class="tab-pane fade" id="tab8">
                                        <div class="form-horizontal">
                                            <div class="form-group">
                                                <label class="control-label col-sm-4" >รหัสธนาคาร :</label>
                                                <div class="col-sm-3">
                                                    <input class="form-control" value="">
                                                </div>
                                                <label class="control-label col-sm-2" >ชื่อธนาคาร :</label>
                                                <div class="col-sm-3">
                                                    <select class="form-control">
                                                        <option>กรุงเทพ</option>
                                                        <option>111</option>
                                                        <option>222</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-sm-4" >สาขา :</label>
                                                <div class="col-sm-3">
                                                    <input class="form-control" value="">
                                                </div>
                                                <label class="control-label col-sm-2" >เลขที่อ้างอิง :</label>
                                                <div class="col-sm-3">
                                                    <input class="form-control" value="">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-sm-4" >รหัสบัญชีเงินฝากธนาคาร :</label>
                                                <div class="col-sm-3">
                                                    <input class="form-control" value="">
                                                </div>
                                                <label class="control-label col-sm-2" >วันที่โอน :</label>
                                                <div class="col-sm-3">
                                                    <div class="input-group">
                                                        <input class="form-control" id="" value="">
                                                        <span class="input-group-btn">
                                                            <button class="btn btn-default" type="button"><span class="glyphicon glyphicon-calendar"></span></button>
                                                        </span>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-sm-4" >เลขที่บัญชีเงินฝากธนาคาร :</label>
                                                <div class="col-sm-3">
                                                    <input class="form-control" value="">
                                                </div>
                                                <label class="control-label col-sm-2" >จำนวนเงิน :</label>
                                                <div class="col-sm-3">
                                                    <input class="form-control" value="">
                                                </div>
                                            </div>
                                        </div>
                                        <button class="btn btn-warning pull-right" id=""><span class="glyphicon glyphicon-plus-sign"></span> เพิ่มรายการอื่น ๆ</button>
                                        <br/><br/>
                                        <table class="table">
                                            <thead>
                                                <tr>
                                                    <th>#</th>
                                                    <th>รหัสธนาคาร</th>
                                                    <th>ชื่อธนาคาร</th>
                                                    <th>สาขา</th>
                                                    <th>เลขที่อ้างอิง</th>
                                                    <th>วันที่โอน</th>
                                                    <th>จำนวนเงิน</th>
                                                    <th></th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td>1</td>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td></td>
                                                    <td><a href="#"><span class="glyphicon glyphicon-trash"></span></a></td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-5">
                    <div class="panel panel-default">
                        <div class="panel-heading">สรุปรายการหัก</div>
                        <div class="panel-body">
                            <table class="table">
                                <thead>
                                    <tr>
                                        <th>#</th>
                                        <th>รายการหัก</th>
                                        <th>จำนวนเงิน</th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>1</td>
                                        <td></td>
                                        <td></td>
                                        <td><a href="#"><span class="glyphicon glyphicon-trash"></span></a></td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>

                <div class='col-md-7'>
                    <div class="panel panel-default">
                        <div class="panel-heading">สรุปวิธีการรับชำระ</div>
                        <div class="panel-body">
                            <table class="table">
                                <thead>
                                    <tr>
                                        <th>#</th>
                                        <th>วิธีการรับชำระ</th>
                                        <th>จำนวนเงิน</th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>1</td>
                                        <td></td>
                                        <td></td>
                                        <td><a href="#"><span class="glyphicon glyphicon-trash"></span></a></td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">สรุปยอดเงินที่ต้องชำระ</div>
                        <div class="panel-body">
                            <div class="form-horizontal">
                                <div class="form-group">
                                    <label class="control-label col-sm-9" >จำนวนเงินก่อนภาษีมูลค่าเพิ่ม :</label>
                                    <div class="col-sm-2">
                                        <input class="form-control" style="text-align: right; " value="535.00" disabled="disabled">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-9" >ภาษีมูลค่าเพิ่ม (VAT) :</label>
                                    <div class="col-sm-2">
                                        <input class="form-control" style="text-align: right; " value="0.00" disabled="disabled">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-9" >จำนวนเงินที่ต้องชำระรวมภาษีมูลค่าเพิ่ม :</label>
                                    <div class="col-sm-2">
                                        <input class="form-control" style="text-align: right; " value="0.00" disabled="disabled">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-9" >ภาษีหัก ณ ที่จ่าย (WT) :</label>
                                    <div class="col-sm-2">
                                        <input class="form-control" style="text-align: right; " value="0.00" disabled="disabled">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-9">  
                                        <div class="checkbox">
                                            <label>
                                                <input type="checkbox" id="check_dis"> <b>ลูกค้ารับภาษี&nbsp;&nbsp;&nbsp;<span class="glyphicon glyphicon-lock"></span> ส่วนลด :</b>
                                            </label>
                                        </div>
                                    </label>
                                    <div class="col-sm-2">
                                        <input class="form-control" style="text-align: right; " value="0.00" disabled="disabled">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-9" >ยอดเงินรับมา :</label>
                                    <div class="col-sm-2">
                                        <input class="form-control" style="text-align: right; " value="0.00" disabled="disabled">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-9" >เงินทอน :</label>
                                    <div class="col-sm-2">
                                        <input class="form-control" style="text-align: right; " value="0.00" disabled="disabled">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-9" >
                                        <label class="radio-inline">
                                            <input type="radio" name="Radio" id="Radio1" value="" checked> <b>รายได้อื่นไม่มีภาษี</b>
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio" name="Radio" id="Radio2" value=""> <b>รายได้อื่นมีภาษี</b>
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio" name="Radio" id="Radio3" value=""> <b>รับชำระล่วงหน้า :</b>
                                        </label>
                                    </label>
                                    <div class="col-sm-2">
                                        <input class="form-control" style="text-align: right; " value="0.00" disabled="disabled">
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
        $("#check_dis").change(function () {
            if ($(this).prop("checked")) {
                // alert('ok');
                $("#modal_authen").modal('show');
            }
        });
        $("#testmenu li a").click(function () {

            $(this).parents(".btn-group").find('.selection').text($(this).text());
            $(this).parents(".btn-group").find('.selection').val($(this).text());

        });
        function showTab1(tab) {
            // $('#myTab1 a[href="#tab-1-3"]').tab('show');
            $('#myTab1 a[href="#tab-1-' + tab + '"]').tab('show');
        }
    </script>
</html>
