<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
                        <li><i>รับชำระค่าบริการคงสิทธิเลขหมายโทรศัพท์เคลื่อนที่</i></li>
                        <li>ค้นหาข้อมูล Order</li>
                        <li >เลือกวิธีการชำระ</li>
                        <li class="active">ผลการชำระ</li>
                    </ol>
                </div>
            </div>
            <ul class="list-inline pull-right list-menu-set">
                <li><a href="payment_mnp.html"><span class="glyphicon glyphicon glyphicon-arrow-left"></span> กลับไปหน้าการรับชำระ</a></li>
            </ul>
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-success">
                        <div class="panel-heading">รายการรับชำระเงิน</div>
                        <div class="panel-body">
                             <table id="table"
	                               data-row-style="rowStyle"
	                               data-toggle="table"
	                               data-classes="table table-hover table-striped"
	                               >
                              <thead >
	                                <tr>
	                                    <th data-field="a" class="">#</th>
	                                    <th data-field="b" data-halign="center" data-align="center" class="">เลขที่ใบเสร็จรับเงิน / ใบกำกับภาษี</th>
	                                    <th data-field="c" data-halign="center" data-align="center" class="">MDN</th>
	                                    <th data-field="e" data-halign="center" data-align="center" class="">หมายเลข ICCID</th>
	                                    <th data-field="f" data-halign="center" data-align="right" class="">จำนวนเงินรวม</th>
	                                    <th data-field="h" data-halign="center" data-align="center" class="col-md-1" >สถานะ</th>
	                                </tr>
	                            </thead>
	                            <tbody>
	                                <tr>
	                                    <td>1</td>
	                                    <td>MNP171701F60330000002</td>
	                                    <td>818490031</td>
	                                    <td>8952002331102200000</td>
	                                    <td>29.00</td>
	                                    <td>Success</td>    
	                                </tr>
	                                <tr>
	                                    <td>2</td>
	                                    <td>MNP171701F60330000002</td>
	                                    <td>818490032</td>
	                                    <td>8952002331102200075</td>
	                                    <td>29.00</td>
	                                    <td>Success</td>    
	                                </tr>
	                                <tr>
	                                    <td>3</td>
	                                    <td>MNP171701F60330000002</td>
	                                    <td>818490033</td>
	                                    <td>8952002331102200000</td>
	                                    <td>29.00</td>
	                                    <td>Success</td>    
	                                </tr>
	                            </tbody>
                            </table>
                        </div>
                    </div>

                </div>
            </div>
        </section>
        
    </body>
    <script type="text/javascript">

    </script>
</html>
