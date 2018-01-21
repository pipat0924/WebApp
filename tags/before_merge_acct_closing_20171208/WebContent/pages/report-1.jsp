<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>EPIS Report</title>
        <link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="resources/bootstrap-table/src/bootstrap-table.css" rel="stylesheet" type="text/css"/>
        <link href="bootstrap-datepicker/jquery.datetimepicker.css" rel="stylesheet" type="text/css"/>
        <link href="resources/custom.css" rel="stylesheet" type="text/css"/>
        <script src="resources/jquery.min.js" type="text/javascript"></script>
        <script src="resources/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="resources/bootstrap-table/src/bootstrap-table.js"></script>
        <script src="bootstrap-datepicker/jquery.datetimepicker.js"></script>
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
                        <li><i>รายงานการรับชำระเงิน</i></li>
                    </ol>
                    <div class="panel panel-default">
                        <div class="panel-heading">Enterprises Payment Integration System Report</div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="list-group">
                                        <a class="list-group-item disabled">รายงานรับชำระเงิน</a>
                                        <a href="epis-daily-report.jsp" class="list-group-item"><span class="glyphicon glyphicon-list-alt"></span> รายงานการรับชำระเงินผ่าน EPIS-ประจำวัน&nbsp;<span class="glyphicon glyphicon-ok"></a>
                                        <a href="#" class="list-group-item"><span class="glyphicon glyphicon-list-alt"></span> รายงาน บช.1</a>
                                    </div>

                                    <div class="list-group">
                                        <a class="list-group-item disabled">รายงานสรุปประจำวัน</a>
                                        <a href="epis-daily-summary-report.jsp" class="list-group-item"></span><span class="glyphicon glyphicon-list-alt"></span> รายงานสรุปการรับชำระเงินประจำวัน จำแนกตามเจ้าหน้าที่&nbsp;<span class="glyphicon glyphicon-ok"></a>
                                        <a href="#" class="list-group-item"><span class="glyphicon glyphicon-list-alt"></span> รายงานสรุปการรับชำระเงินประจำวัน จำแนกตามอัตราภาษี</a>
                                        <a href="#" class="list-group-item"><span class="glyphicon glyphicon-list-alt"></span> รายงานสรุปการรับชำระเงินประจำวัน จำแนกตามวิธีการรับชำระ</a>
                                    </div>

                                    <div class="list-group">
                                        <a class="list-group-item disabled">รายงานภาษีขาย</a>                                        
                						<a href="epis-daily-tax-sale-report.jsp" class="list-group-item"></span><span class="glyphicon glyphicon-list-alt"></span> รายงานภาษีขายใบเสร็จรับเงิน/ใบกำกับภาษีเต็มรูป ประจำวัน&nbsp;<span class="glyphicon glyphicon-ok"></a>
                						<a href="epis-daily-tax-abbreviate-sale-report.jsp" class="list-group-item"></span><span class="glyphicon glyphicon-list-alt"></span> รายงานภาษีขายใบเสร็จรับเงิน/ใบกำกับภาษีแบบย่อ ประจำวัน&nbsp;<span class="glyphicon glyphicon-ok"></a>
                                        <a href="#" class="list-group-item"><span class="glyphicon glyphicon-list-alt"></span> รายงานภาษีขายประจำเดือน รวมตามวันที่บันทึก</a>
                                    </div>

                                </div>
                                <div class="col-md-6">
                                    <div class="list-group">
                                        <a class="list-group-item disabled">รายงานภาษีหัก ณ ที่จ่าย</a>
                                        <a href="#" class="list-group-item"><span class="glyphicon glyphicon-list-alt"></span> รายงานภาษีเงินได้นิติบุคคลถูกหักภาษี ณ ที่จ่าย</a>
                                    </div>

                                    <div class="list-group">
                                        <a class="list-group-item disabled">รายงานสรุปเช็ค</a>
                                        <a href="#" class="list-group-item"><span class="glyphicon glyphicon-list-alt"></span> รายงานสรุปเช็คทั้งหมด</a>
                                    </div>

                                    <div class="list-group">
                                        <a class="list-group-item disabled">รายงานการปิดบัญชี</a>
                                        <a href="#" class="list-group-item"><span class="glyphicon glyphicon-list-alt"></span> รายงานการปิดบัญชีประจำวัน</a>
                                    </div>

                                </div>
                            </div>
                        </div>

                    </div>
                </div>

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
