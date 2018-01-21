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
                        <li><i>รับชำระค่าเติมเงินออนไลน์ (Top up)</i></li>
                        <li>ตรวจสอบข้อมูลการเติมเงิน</li>
                        <li>เลือกวิธีการชำระ</li>
                        <li class="active">ผลการรับชำระ</li>
                    </ol>
                </div>
            </div>
            <ul class="list-inline pull-right list-menu-set">
                <li><a href="payment_topup_2.html"><span class="glyphicon glyphicon glyphicon-arrow-left"></span> กลับไปหน้าการรับชำระ</a></li>
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
	                                    <th data-field="c" data-halign="center" data-align="center" class="">ประเภทบริการ</th>
	                                    <th data-field="d" data-halign="center" data-align="center" class="">ชื่อลูกค้า</th>
	                                    <th data-field="e" data-halign="center" data-align="center" class="">หมายเลขบริการ</th>
	                                    <th data-field="f" data-halign="center" data-align="left" class="">โปรโมชั่น</th>
	                                    <th data-field="g" data-halign="center" data-align="center" class="" >จำนวนเงิน</th>
	                                    <th data-field="h" data-halign="center" data-align="center" class="col-md-1" >สถานะ</th>
	                                </tr>
	                            </thead>
	                            <tbody>
	                                <tr>
	                                    <td>1</td>
	                                    <td>EP0171701F150714000001</td>
	                                    <td>my by CAT</td>
	                                    <td>นางสมจิตร จงใจหา</td>
	                                    <td>0864562315</td>
	                                    <td>my Top Up SIM Master</td>
	                                    <td>50.00</td>
	                                    <td>Success</td>    
	                                </tr>
	                                <tr>
	                                    <td>2</td>
	                                    <td>EP0171701F150714000006</td>
	                                    <td>my by CAT</td>
	                                    <td>นางน้ำทิพย์ ใสจริง</td>
	                                    <td>0891237895</td>
	                                    <td>my Top Up SIM Master</td>
	                                    <td>100.00</td>
	                                    <td>Success</td>    
	                                </tr>
	                                <tr>
	                                    <td>3</td>
	                                    <td>EP0171701F150714000007</td>
	                                    <td>my by CAT</td>
	                                    <td>นางสุขใจ มากมี</td>
	                                    <td>0952456987</td>
	                                    <td>my Top Up SIM Master</td>
	                                    <td>100.00</td>
	                                    <td>Success</td>    
	                                </tr>
	                                <tr>
	                                    <td>4</td>
	                                    <td>EP0171701F150714000008</td>
	                                    <td>my by CAT</td>
	                                    <td>นางสมคิด สุขดี</td>
	                                    <td>0891237895</td>
	                                    <td>my Top Up SIM Master</td>
	                                    <td>50.00</td>
	                                    <td>Success</td>    
	                                </tr>
	                                <tr>
	                                    <td>5</td>
	                                    <td>EP0171701F150714000009</td>
	                                    <td>my by CAT</td>
	                                    <td>นายสมหวัง ทรัพย์มั่งมี</td>
	                                    <td>0877895236</td>
	                                    <td>my Top Up SIM Master</td>
	                                    <td>50.00</td>
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
