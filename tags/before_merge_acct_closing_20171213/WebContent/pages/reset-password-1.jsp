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
            <div class="col-md-4"></div>
            <div class="col-md-4">
				<!-- #F36F21 #e2cbbc -->
                <div class="panel" style="background-color: #e2cbbc">
                    <!-- Default panel contents -->
                    <div class="panel-body">
                    	<div id="sessionExpired" class="hide alert alert-warning">Your session is expired!</div>
                    	<div id="wrongAuthen" class="hide alert alert-danger">รหัสผ่านเดิม ไม่ถูกต้อง!</div>
                    	<div style="background-color: #F36F21; font-size: 16px; font-weight: 480; color: #ffffff; vertical-align: bottom; padding-left: 22px;"><img src="resources/CATTelecom_Logo.png"/>Enterprise Payment Integration System</div>
	                   	<div class="col-xs-12" style="padding-right: 5px;"><hr></div>
	                   	<div class="row">
                    		<div class="col-md-1"></div>
							<div class="col-md-11" style="padding-right: 32px;">
		                        <form id="loginFrm" name="login" action="login" method="POST" role="form" data-toggle="validator" novalidate="true">
		                            <div class="form-group">
		                                <label class="control-label" for="Username"><span class="glyphicon glyphicon-user"></span> รหัสพนักงาน</label>
		                                <input type="text" id="username" name="username" class="form-control" placeholder="กรุณากรอกรหัสพนักงาน" value="${pageContext.request.userPrincipal.name}" readonly required>
		                            </div>
		                            <div class="form-group ${(failedAuthen != null && failedAuthen)?'has-error has-feedback':''}">
		                                <label class="control-label" for="Password"><span class="glyphicon glyphicon-th-list"></span> รหัสผ่านเดิม</label>
		                                <input type="password" id="password" name="password" class="form-control" placeholder="กรุณากรอกรหัสผ่านเดิม" value="" required>
		                            </div>
		                            <div class="form-group ${(failedAuthen != null && failedAuthen)?'has-error has-feedback':''}">
		                                <label class="control-label" for="Password"><span class="glyphicon glyphicon-th-list"></span> รหัสผ่านใหม่</label>
		                                <input type="password" id="confirmpassword" name="confirmpassword" class="form-control" placeholder="กรุณากรอกรหัสผ่านใหม่" value="" required>
		                            </div>
		                            <div class="form-group text-right">
										<a id="cancelPass" class="btn btn-danger"><span class="glyphicon glyphicon-off"></span> ยกเลิก</a>
		                            	<a id="resetPass" class="btn btn-primary"><span class="glyphicon glyphicon-off"></span> ดำเนินการเปลี่ยน</a>
		                            </div>
		                        </form>
                       		</div>
                       	</div>			
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
	function Button(el) { var obj = this, badge; obj.el = el; obj.elem = $(el);
		obj.hide = function() { this.elem.addClass("hide"); return this }; obj.show = function() { this.elem.removeClass("hide"); return this };
		obj.hideLoad = function(){ obj.elem.button("reset"); return this }; obj.showLoad = function(){ obj.elem.button("loading"); return this };
		obj.disable = function(flag) { if (flag == null || flag) this.elem.addClass("disabled").attr("disabled", true); else this.elem.removeClass("disabled").attr("disabled", false); return this }; obj.enable = function() { obj.disable(false); return this };
		obj.badge = function(val) { if (badge) badge.text(val) }; if ((badge = this.elem.next()).length == 0) badge = null;
		obj.elem.click(window[el.substring(1) +"ClickEvent"]);
	}
	function Input(el, maxLen, propPos) { var obj = this; obj.el = el; obj.elem = $(el);
		obj.error = function(flag) { if (arguments.length == 0 || flag) obj.elem.parent().addClass("has-error"); else obj.elem.parent().removeClass("has-error"); return this }
		obj.clear = function() { obj.val(""); return this }; obj.isEmpty = function() { return $.trim(obj.val()) === "" }; obj.isNumeric = function() { return $.isNumeric(obj.val()) }
		obj.nextFocus = function(nextElem) { if (nextElem && "Input|Button".indexOf(nextElem.constructor.name) > -1) { obj.elem.keyup(function(e){ var key = (e.which || e.keyCode || e.charCode || 0); if (key == 13) nextElem.elem.focus(); return true }) } return this };
		obj.click = function(func) { obj.elem.click(func); return this }
		obj.readOnly = function(flag) { obj.elem.attr("readonly", (flag == null ? true : flag)); return this }
		obj.disable = function(flag) { obj.elem.attr("disabled", (flag == null ? true : flag)); return this }
		obj.enable = function() { obj.disable(false); return this }
		obj.val = function() { if (arguments.length == 1) { this.elem.val(arguments[0]) } return $.trim(this.elem.val()) }
		obj.get = function(propName) { if ($.type(propPos[propName]) !== "array" || propPos[propName].length !== 2) return ""; return obj.val().substring(propPos[propName][0], propPos[propName][1]) }
		obj.elem.blur(window[el.substring(1) +"BlurEvent"]); obj.elem.keyup(function(e){ var func = (window[el.substring(1) +"KeyUpEvent"] || function(){}); func((e.which || e.keyCode || e.charCode || 0), obj.elem) }); obj.elem.focus(function(){ this.select() }); if ($.isNumeric(maxLen)) { obj.elem.attr("maxLength", maxLen) }
	}
	self.input = new(function(){
		var that = this;
		that.username = new Input("#username");
		that.password = new Input("#password");
		that.confirmpassword = new Input("#confirmpassword");
	});
	self.button = new(function(){
		var that = this;
		that.resetPass = new Button("#resetPass");
	});
	return this;
})(jQuery);
function resetPassClickEvent() {
    //$('#loginFrm').validator()

	if(!view.input.username.isEmpty() && !view.input.password.isEmpty() && !view.input.confirmpassword.isEmpty()) {
		var dataSend={
			username:$("#username").val(),
			password:$("#password").val(),
			confirmpassword:$("#confirmpassword").val()
 		};
		console.log(dataSend)
		$.ajax({
			type: "POST",
			url: "../resetPassword.json",
			data: JSON.stringify(dataSend),
			dataType: "json",
			contentType: "application/json; charset=utf-8",
				success:function(data){
                    console.log("data->"+data)
			    if(data==0)
                    $('#wrongAuthen').removeClass('hide');
			    else
				    window.top.location = "logout";
			},
	        	error: function(jqxhr) {
	        		$('#alertDangerAreaMsg').removeClass('hide');
	        }
		});
	} else {
		$("#wrongRepass").removeClass("hide");
	}
}
function cancelPassClickEvent() { 
	window.target = "_page";
	window.location = "Dashboard.jsp?new"; 
}
$(document).ready(function () {
    $("#password").focus();
   // $("#cancelPass").hide();
    $("#cancelPass").on("click",function() {

        window.location.href="main.jsp";
    });
});
cancelPass
</script>