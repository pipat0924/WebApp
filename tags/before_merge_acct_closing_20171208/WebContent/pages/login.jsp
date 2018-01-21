<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Enterprise Payment Integration System</title>
	<link rel="stylesheet" href="resources/bootstrap/css/bootstrap.min.css">
	<script src="resources/jquery.min.js" type="text/javascript"></script>
	<script src="resources/bootstrap/js/bootstrap.min.js"></script>
	<script src="resources/js/validator.min.js"></script>
</head>
<body style="margin-top: 100px">
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-4" ></div>
            <div class="col-md-4" >
                <div class="panel panel-warning">
                    <!-- Default panel contents -->
                    <div class="panel-heading"><h4>Enterprise Payment Integration System</h4></div>
                    <div class="panel-body">
                    	<div id="sessionExpired" class="hide alert alert-warning">Your session is expired!</div>
                    	<div id="wrongAuthen" class="hide alert alert-danger">Username/Password incorrect!</div>
                        <form id="loginFrm" name="login" action="login" method="POST" role="form" data-toggle="validator" novalidate="true">
                            <div class="form-group">
                                <label class="control-label" for="Username">รหัสพนักงาน</label>
                                <input type="text" id="username" name="username" class="form-control" placeholder="กรุณากรอกรหัสพนักงาน" value="" required>
                            </div>
                            <div class="form-group ${(failedAuthen != null && failedAuthen)?'has-error has-feedback':''}">
                                <label class="control-label" for="Password">รหัสผ่าน</label>
                                <input type="password" id="password" name="password" class="form-control" placeholder="กรุณากรอกรหัสผ่าน" value="" required>
                            </div>
                            <div class="form-group text-right">
                            	<input type="submit" class="btn btn-primary" value="เข้าสู่ระบบ">
                            </div>
                            <div class="form-group text-right">
                                    V.0.0.3 [28/06/2017 01:56:07]
                            </div>

                            <div class="form-group text-right">
                            	<a href="../jnlp/cat-epis-agent.jnlp" class="btn btn-link">คลิก เพื่อติดตั้ง ส่วนประกอบเพิ่มเติม</a>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <div class="col-md-4" ></div>
        </div>
    </div>
</body>
</html>
<script type="text/javascript">
var view = (function($){
	self.utils = {
		queryString: function() {
			var pos = location.href.indexOf("?"), obj = {}; if (pos < 0) return obj;
			var params = location.href.slice(pos + 1).split("&");
			for (var i = 0, m = params.length; i < m; i++) {
				pos = params[i].indexOf("="); if (pos < 0) { obj[params[i]] = true; continue; }
				obj[params[i].substring(0, pos)] = params[i].substring(pos + 1);
			}
// 			if("SSO" == obj["ap"]){
// 				$("#username").val(obj["un"]);
// 				$("#password").val("");
// 			}
			return obj;
		}
	};
	return this;
})(jQuery);
if (window.self !== window.top) {
	window.top.location = "login.jsp?session_expired";
}
var queries = view.utils.queryString();
if (queries["session_expired"]) $("#sessionExpired").removeClass("hide");
if (queries["wrongAuthen"]) $("#wrongAuthen").removeClass("hide");
</script>