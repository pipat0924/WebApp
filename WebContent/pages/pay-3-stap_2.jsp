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
                        <li><i>รับชำระเงินโอนต่างประเทศ</i></li>
                        <li>ค้นหาข้อมูลลูกค้า</li>
                        <li class="active">สรุปรายการเงินโอน</li>
                        <li>ผลการรับชำระ</li>
                    </ol>
                </div>
            </div>
            <ul class="list-inline pull-right">
                <li><a href="#"><span class="glyphicon glyphicon-user"></span> ค้นหาข้อมูลลูกค้า</a></li>
                <li><a href="#"><span class="glyphicon glyphicon-print"></span> บันทึกและพิมพ์</a></li>
                <li><a href="#"><span class="glyphicon glyphicon-remove-circle"></span> ยกเลิกรายการ</a></li>
            </ul>
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-success">
                        <div class="panel-heading">รายการรับชำระเงิน</div>
                        <div class="panel-body">
                            <table class="table table-condensed">
                                <thead>
                                    <tr>
                                        <th>#</th>
                                        <th>ธนาคาร</th>
                                        <th>สาขา</th>
                                        <th>รหัสบัญชีเงินฝาก</th>
                                        <th>หมายเลขบัญชีเงินฝาก</th>
                                        <th>ค่าธรรมเนียมภาษีซื้อ 7%</th>
                                        <th>หักปรับปรุงภาษีซื้อ 7%</th>
                                        <th>รหัสศูนย์ต้นทุน</th>
                                        <th>SAP DOC</th>
                                        <th>วันที่โอน</th>
                                        <th>เลขที่อ้างอิง</th>
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
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td>
                                            <button class="btn btn-link"><span class="glyphicon glyphicon-pencil"></span></button>
                                            <button class="btn btn-link"><span class="glyphicon glyphicon-trash"></span></button>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                            <hr>
                            <div class="form-horizontal">
                                <div class="form-group">
                                    <label class="control-label col-sm-10">ยอดเงินโอนทั้งสิ้น :</label>
                                    <div class="col-sm-2">
                                        <input class="form-control" value="" disabled="disabled">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-10">ค่าธรรมเนียม :</label>
                                    <div class="col-sm-2">
                                        <input class="form-control" value="" disabled="disabled">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-10">ภาษีซื้อ 7% :</label>
                                    <div class="col-sm-2">
                                        <input class="form-control" value="" disabled="disabled">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-10">หักปรับปรุงภาษีซื้อ 7% :</label>
                                    <div class="col-sm-2">
                                        <input class="form-control" value="" disabled="disabled">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
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

    </script>
</html>
