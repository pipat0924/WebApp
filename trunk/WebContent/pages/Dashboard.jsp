<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="resources/bootstrap-table/src/bootstrap-table.css" rel="stylesheet" type="text/css"/>
        <link href="resources/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
        <link href="resources/custom.css" rel="stylesheet" type="text/css"/>
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
            <ol class="breadcrumb">
                <li><i>แผงแสดงงาน (Dashboard)</i></li>
            </ol>
            <div class="row">
                <div class="col-md-12">
                    <div class="col-xs-6 col-md-3">
                        <div class="thumbnail">
                            <span class="glyphicon glyphicon-edit" style="font-size:6em"></span>
							<p style="font-size:2em">อนุมัติคืนเงิน</p>
							<div style="font-size:42pt">
								<a href="approve-revert-payment_1.jsp">0</a>
							</div>
							<p style="font-size:2em">รายการ</p>
                        </div>
                    </div>
                    <div class="col-xs-6 col-md-3">
                        <div class="thumbnail">
                            <span class="glyphicon glyphicon-briefcase" style="font-size:6em"></span>
							<p style="font-size:2em">อนุมัติปรับลดหนี้</p>
							<div style="font-size:42pt">
								<a href="approve-adjust-payment_1.jsp">0</a>
							</div>
							<p style="font-size:2em">รายการ</p>
                        </div>
                    </div>
                    <div class="col-xs-6 col-md-3">
                        <div class="thumbnail">
                            <span class="glyphicon glyphicon-warning-sign" style="font-size:6em"></span>
							<p style="font-size:2em">เช็คขัดข้อง</p>
							<div style="font-size:42pt">
								<a href="#">0</a>
							</div>
							<p style="font-size:2em">รายการ</p>
                        </div>
                    </div>
					<div class="col-xs-6 col-md-3">
                        <div class="thumbnail">
                            <span class="glyphicon glyphicon-remove-sign" style="font-size:6em"></span>
							<p style="font-size:2em">ยกเลิกรับชำระเงิน</p>
							<div style="font-size:42pt">
								<a href="action-cancel-service-charge_1.jsp">0</a>
							</div>
							<p style="font-size:2em">รายการ</p>
                        </div>
                    </div>                    
                </div>
            </div>

    </body>
</html>
<script>
    var view = (function($){
        var self = this;
        self.session = function(key, val) { if (!val) { var val = window.sessionStorage.getItem(key); return val && (val.indexOf("{") > -1 || val.indexOf("[") > -1) ? JSON.parse(val) : val } window.sessionStorage.setItem(key, ($.type(val) != "object" && $.type(val) != "array" ? val : JSON.stringify(val)))	};
        (function(){ $(window["setup"]) })();
        return this;
    })(jQuery);

    var queryString = view.session("queryString");
    if(Object.keys(queryString).length > 0){
        location.href = "pay-service-charge.jsp";
    }
</script>