<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
		<link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
		<link href="resources/bootstrap-table/src/bootstrap-table.css" rel="stylesheet" type="text/css" />
		<link href="resources/custom.css" rel="stylesheet" type="text/css" />
		<script src="resources/jquery.min.js" type="text/javascript"></script>
		<script src="resources/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
		<script src="resources/bootstrap-table/src/bootstrap-table.js"></script>
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
                        <li><i>ตัวแทนรับชำระ</i></li>
                        <li><i>ค่าไฟฟ้าส่วนภูมิภาค</i></li>
                        <li>ดำเนินการรับชำระ</li>
                        <li class="active">ผลการรับชำระ</li>
                    </ol>
                </div>
            </div>
            <ul class="list-inline pull-right list-menu-set">
                <li><a id="buttonBackToPayment" class="btn btn-link"><span class="glyphicon glyphicon glyphicon-arrow-left"></span> กลับไปหน้าการรับชำระ</a></li>
            </ul>

            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-success">
                        <div class="panel-heading"><span class="glyphicon glyphicon-th-list"></span>  รายการรับชำระเงิน</div>
                        <div class="panel-body">


                                    <table id="table"
                                           data-row-style="rowStyle"
                                           data-toggle="table"
                                           data-classes="table table-hover table-striped"
                                           >
                                        <thead>
                                            <tr >
                                                <th>#</th>
                                                <th>ใบแจ้งค่าบริการเลขที่</th>
												<th  data-align="center" class="">วันที่รับชำระ</th>
                                                <th>ชื่อลูกค้า</th>
                                                <th>กลุ่มผู้ใช้บริการ</th>
												<th>หน่วยที่ใช้</th>
                                                <th>จำนวนเงิน</th>
                                                <th>ภาษี</th>
                                                <th>จำนวนเงินสุทธิ</th>
                                                <th>สถานะ</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td>1</td>
                                                <td>015099125</td>
												<td>02-08-2559</td>
                                                <td>ณัฎฐ์อนงค์ สุทธิเชษฐ์</td>
                                                <td>บุคลธรรมดา</td>
                                                <td>000147</td>
                                                <td>550.00</td>
                                                <td>38.50</td>
                                                <td>558.50</td>
												<td>ชำระสำเร็จ</td>
                                            </tr>
                                        </tbody>
                                    </table>
                        </div>
                    </div>

                </div>
            </div>

        </section>
    </body>
</html>

<script type="text/javascript">
    var view = (function($){
        var self = this, defaultErrorMessage = "An error occurred but there is no message response.";
        self.session = function(key, val) { if (!val) { var val = window.sessionStorage.getItem(key); return val && (val.indexOf("{") > -1 || val.indexOf("[") > -1) ? JSON.parse(val) : val } window.sessionStorage.setItem(key, ($.type(val) != "object" && $.type(val) != "array" ? val : JSON.stringify(val)))	};

        self.buttonBackToPayment = new Button("#buttonBackToPayment");
        return this;
    })(jQuery);
    function buttonBackToPaymentClickEvent() {
        var agentData = view.session("agentData");
        var params = "id="+agentData.id;
        location.href = "pay-agent_1.jsp?"+params;
    }
</script>