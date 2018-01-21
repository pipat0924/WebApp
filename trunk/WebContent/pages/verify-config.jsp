<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>การตั้งค่า Verify</title>
<link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="resources/bootstrap-table/src/bootstrap-table.css" rel="stylesheet" type="text/css" />
<link href="resources/custom.css" rel="stylesheet" type="text/css" />
<script src="resources/jquery.min.js" type="text/javascript"></script>
<script src="resources/bootstrap/js/bootstrap.min.js"></script>
<script src="resources/bootstrap-table/src/bootstrap-table.js"></script>
<script src="resources/js/mustache.min.js" type="text/javascript"></script>
<script src="resources/custom.js" type="text/javascript"></script>
</head>
<body>
	<header class="header_page"></header>
	<section class="container-fluid menu">
		<!-- <button class="menu-toggle btn btn-sm btn-default" style="margin-bottom: 5px"><span class="glyphicon glyphicon-step-forward"></span></button> -->
    <%-- <%@include  file="menu.jsp" %> --%>
		
		<div class="row">
			<div class="col-md-12 tab-modefile">
				<div id="mainMessageDialog"></div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12 tab-modefile">
				<div>
					<ul class="nav nav-tabs" role="tablist">
						<li role="presentation" class="active"><a href="#"><span class="glyphicon glyphicon-list"></span> ตั้งค่าการระบุตัวตน</a></li>
					</ul>
					<div class="panel panel-default panal-radius">
						<div class="panel-body">
							<div class="tab-content">
								<div role="tabpanel" class="tab-pane active" id="tab_1">
									<div class="form-horizontal">
										<div class="form-group"></div>
										<div class="form-group">
											<div class="col-sm-4"></div>
											<label class="control-label col-sm-3" >
												<input type="checkbox" id="verifyFlag" name="verifyFlag">&nbsp;&nbsp;&nbsp;ระบุตัวตน
											</label>
											<div class="col-sm-5"></div>
										</div>
										<div class="form-group">
											<div class="col-sm-4"></div>
											<label class="control-label col-sm-2">รหัสระบุตัวตน : </label>
											<div class="col-sm-2"><input id="verifyKey" type="password" class="form-control" value="" ></div>
											<div class="col-sm-2"></div>
										</div>
										<div class="form-group">
											<div class="col-sm-4"></div>
											<label class="control-label col-sm-2">รหัสระบุตัวตนอีกครั้ง : </label>
											<div class="col-sm-2"><input id="confirmVerifyKey" type="password" class="form-control" value="" ></div>
											<div class="col-sm-2"></div>
										</div>
										<div class="form-group">
											<div class="col-sm-6"></div>
											<div class="col-sm-4">
												<a id="saveBtn" class="btn btn-primary"> ตกลง</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<a id="resetBtn" class="btn btn-danger"> ยกเลิก</a>
											</div>
											<div class="col-sm-2"></div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
</body>
</html>

<script type="text/javascript">
function Message(el) { var obj = this, timeCnt = 0, loadFunc; obj.el = el; obj.elem = $(el);
	obj.hide = function() { obj.elem.addClass("hide"); return obj };
	obj.show = function(flag) { if (flag == null || flag) obj.elem.removeClass("hide"); else obj.hide(); return obj };
	obj.clear = function() { obj.elem.find("*").remove(); obj.hide(); return obj };
	obj.message = function(arr, cls) { $.each(arr, function(i,o) { var m = o; if ($.type(o) === "object") { m = (o.desc || o.messageDesc) +" [code="+ (o.code || o.messageCode) +"]" }; obj.elem.append('<div class="'+ cls +'">'+ m +'<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button></div>') }); return obj };
	obj.error = function(arr) { return obj.message(arr, "alert alert-danger") };
	obj.warn = function(arr) { return obj.message(arr, "alert alert-warning") };
	obj.success = function(arr) { return obj.message(arr, "alert alert-success") };
	obj.valid = function(jqXHR, textStatus, errorThrow){ var res = jqXHR; if (jqXHR && jqXHR.responseJSON) res = jqXHR.responseJSON; if (res && (res.statusCode == '0' || $.type(res._embedded) === 'object')) return true; obj.warn(res && $.type(res.warningList) === 'array' ? res.warningList : []).error(res && $.type(res.errorList) === 'array' ? res.errorList : ["An error occurred on the server side but there is no error message returned."]).show(); return false }
	obj.hideLoad = function(){ obj.stopLoad().clear(); return this }; obj.stopLoad = function(){ clearInterval(loadFunc); return this }
	obj.showLoad = function(msg){ obj.elem.append('<div id="'+ obj.el +'-loading" class="alert alert-warning">'+ bind(msg, 0) +'</div>'); timeCnt = 0; var elem = document.getElementById(obj.el +"-loading"); loadFunc = setInterval(function(){ elem.innerHTML = bind(msg, ++timeCnt) }, 1000); obj.show(); return this }
	function bind(msg, timeCnt) { return msg.replace(/\{timeCnt\}/g, timeCnt) }
}

var mainMessageDialog = new Message("#mainMessageDialog");


var user;
$.get("../loadCurrentOfficer.json", function(res){
	user = res;
	setObjectByVerifyFlag();
});

$("#verifyFlag").change(function() {
    if(this.checked) {
    	enableVerify();
    }else{
    	disableVerify();
    }
});
$('#saveBtn').on("click", function() {
	var isVerify = $("#verifyFlag").is(':checked');
	if(isVerify){
		if( isEmpty('#verifyKey') || isEmpty('#confirmVerifyKey')){
			if(isEmpty('#verifyKey')){
				setWarningStyle('#verifyKey');
			}
			if(isEmpty('#confirmVerifyKey')){
				setWarningStyle('#confirmVerifyKey');
			}
			mainMessageDialog.clear().error(["กรุณากรอกข้อมูลให้ครบถ้วน"]).show();
			return false;
		}else if($('#verifyKey').val() != $('#confirmVerifyKey').val()){
			mainMessageDialog.clear().error(["รหัสระบุตัวตนและรหัสระบุตัวตนอีกครั้งไม่ตรงกัน"]).show();
			return false;
		}
	}
	
	removeStyle('#verifyKey');
	removeStyle('#confirmVerifyKey');

	var dataSend={
		name:user.name
		,verifyFlag:$("#verifyFlag").is(':checked')?"Y":"N"
		,verifyKey:$("#verifyKey").val()
		};
	$.ajax({
		type: "POST",
		url: "../updateVerifyUser.json",
		data: JSON.stringify(dataSend),
		dataType: "json",
		contentType: "application/json; charset=utf-8",
			success:function(result){
				user = result;
				setObjectByVerifyFlag();
				mainMessageDialog.clear().success(["บันทึกข้อมูลเรียบร้อยแล้ว"]).show();
// 				$('#createOfficerFm')[0].reset();
// 				$('#alertSuccessAreaMsg').removeClass('hide');
// 				$('#alertDangerAreaMsg').addClass('hide');
// 				$('#alertPasswordAreaMsg').addClass('hide');
// 				$('#alertShopAndPosAreaMsg').addClass('hide');
		},
        	error: function(jqxhr) {
        		mainMessageDialog.clear().error(["เกิดข้อผิดพลาด"]).show();
//         		$('#alertDangerAreaMsg').removeClass('hide');
//         		$('#alertSuccessAreaMsg').addClass('hide');
//         		$('#alertPasswordAreaMsg').addClass('hide');
//         		$('#alertShopAndPosAreaMsg').addClass('hide');
        }
	});
	
});
$('#resetBtn').on("click", function() {
	setObjectByVerifyFlag();
});
function setObjectByVerifyFlag(){
	mainMessageDialog.clear().hide();
	if(user != null) {
		if(user.verifyFlag == "Y"){
			enableVerify();
		}else{
			disableVerify();
		}
	}
}
function enableVerify(){
	removeStyle('#verifyKey');
	removeStyle('#confirmVerifyKey');
	$('#verifyFlag').prop('checked', true);
	$('#verifyKey').val('');
	$('#verifyKey').prop('disabled','');
	$('#confirmVerifyKey').val('');
	$('#confirmVerifyKey').prop('disabled','');
}
function disableVerify(){
	removeStyle('#verifyKey');
	removeStyle('#confirmVerifyKey');
	$('#verifyFlag').prop('checked', false);
	$('#verifyKey').val('');
	$('#verifyKey').prop('disabled','disabled');
	$('#confirmVerifyKey').val('');
	$('#confirmVerifyKey').prop('disabled','disabled');
}
function setWarningStyle(selector){
	$(selector).attr("style", "color:#a94442"); 
	$(selector).attr("style", "background-color:#ffffaa;border:solid 1px #a94442;padding:0 10px");
}
function removeStyle(selector){
	$(selector).removeAttr("style");
}
function isEmpty(selector){
	if( $.trim($(selector).val()) == '' ){
		return true;
	}else{
		return false;
	}
}

</script>