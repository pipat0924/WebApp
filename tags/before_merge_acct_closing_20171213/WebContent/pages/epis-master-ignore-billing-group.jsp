<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>รายงานสรุปการรับชำระเงินประจำวัน</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
		<link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
		<link href="resources/bootstrap-table/src/bootstrap-table.css" rel="stylesheet" type="text/css" />
		<link href="resources/custom.css" rel="stylesheet" type="text/css" />
		<script src="resources/jquery.min.js" type="text/javascript"></script>
		<script src="resources/bootstrap/js/bootstrap.min.js"></script>
		<script src="resources/bootstrap-table/src/bootstrap-table.js"></script>
		<script src="resources/custom.js" type="text/javascript"></script>
		<link href="resources/css/datepicker.min.css" rel="stylesheet" type="text/css" />
		<script src="resources/js/bootstrap-datepicker.min.js"></script>
        <script type="text/javascript">
		    $(document).ready(function() {
		    	$(document).on("click", "a#searchฺBillingGroupIgnore", function() {
            		// alert("click search")
            		 // view.dialog.mainMessageDialog.clear();
            		console.log($("#billingGroup").val());
            		$("#BillingGroupIgnore").bootstrapTable("refresh", 
            				{  url: "../getBillingGroupIgnore.json?"
            						+"billingGroup="+$("#billingGroup").val(), cache: false  });
            			setTimeout(function() { 
            				$("#BillingGroupIgnore").bootstrapTable("resetView")
            		}, 500);
            	})
            	.on("click", "#agentCode li a", function() {
            		$(this).parents(".btn-group").find('.selection').text($(this).text()); 
            		$(this).parents(".btn-group").find('.selection').val($(this).text());
            	})
            	.on("click", "a#sessionTest", function() {
            	   var aoe=view.session("printOnly");	
            		//alert(aoe)
            		console.log(aoe.id)
            		var printOnly={
            			"id":"xxxx",
            			"name":"Chatchai"
            		};
            		view.session("printOnly",printOnly);	
            	})
            	.on("click", "a#printReport", function() {
            		
            		document.forms[0].submit();
            	});
            	
		    	$( "#billingGroup" ).keypress(function( event ) {
	            	  if ( event.which == 13 ) {
	            	     event.preventDefault();
	            	    $("#searchฺBillingGroupIgnore").click(); 
	            	  }
	            	 
	            	});
		    	
		        $('#stDate').datepicker({
		             format: 'dd/mm/yyyy',
		             lang: 'th', // แสดงภาษาไทย
		             startDate: '01/01/2016',
		             endDate: '30/12/2025'
		        });
		        $('#stDate').datepicker('setDate', new Date());
		        $('#stDate').datepicker('update');
		        $('#stDate').val('');
		        
		        
		        $.get("../service/officers", function(res) {
		        	if (!res || !res._embedded || !res._embedded.officers || res._embedded.officers.length < 1) return;
		        	var user = res._embedded.officers[0];
		        	var str = "<li><a href=\"#\">All</a></li>";
		        	//var firstUser = "";
		        	$.each(res._embedded.officers, function(k,user){
		        		//if(k === 0) firstUser = user.code; 
		        		str += "<li><a href=\"#\">"+user.code+"</a></li>";
		        	});
		        	$('#agentCode').html(str);
		        	//$("#agentCodeSelect").find(".selection").val(firstUser);
		        	$("#agentCodeSelect").find(".selection").val("All");
		        });
		        
		        $("#searchฺBillingGroupIgnore").click(); 
		    });
		    
			function runningFormatter(val, row, ind) {
				return ind + 1;
			}
			function currencyFormatter(val, row, ind) {
		    	return parseFloat(val, 10).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,');
		    }
		    function numberFormatter(val, row, ind) {
		    	return parseFloat(val, 10).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,').replace(/\.00$/g, "");
		    }
		    function nameFormatter(val, row, ind) {
		    	return row.userName + " " + row.userFamilyname + " ("+row.userCode+")";
		    }
		    
       	</script>
    </head>
    <body>
        <header class="header_page"></header>
        <section class="container-fluid menu">
            <!-- <button class="menu-toggle btn btn-sm btn-default" style="margin-bottom: 5px"><span class="glyphicon glyphicon-step-forward"></span></button> -->
                <%-- <%@include  file="menu.jsp" %> --%>
            <div class="row"> 
                <div class="col-md-12 tab-modefile">
                    <ol class="breadcrumb">
                    	<li><i>ตั้งค่า</i></li>
                        <li class="active"><i> Ignore Billing Group</i></li>
                    </ol>
                    <div id="mainMessageDialog"></div>
                    <ul class="nav nav-tabs" role="tablist">
					<li role="presentation" class="active">
					<a aria-controls="tab_cus" role="tab" data-toggle="tab">
					<span class="glyphicon glyphicon-filter"></span> ค้นหาข้อมูล</a>
					</li>
					</ul>
					<div class="panel panel-default panal-radius">
						<div class="panel-body">
							<div class="row">
								<div class="col-md-12">
									<div class="form-horizontal">
										<div class="form-group">
											<label class="control-label col-sm-2">Billing Group Codexx:</label>
											<div class="col-sm-2"><input class="form-control" id="billingGroup"></div>
											<!--  
											<label class="control-label col-sm-2">Barcode / QRcode :</label>
											<div class="col-sm-2"><input class="form-control" id="barcode"></div>
											 -->
											<div class="col-sm-4">
												<a id="searchฺBillingGroupIgnore" class="btn btn-primary"><span class="glyphicon glyphicon-search"></span> ค้นหา</a>
												<!--  
												<a id="searchฺBillingGroupIgnoreThai" onClick="test()" class="btn btn-primary"><span class="glyphicon glyphicon-search"></span> ค้นหา</a> &nbsp;&nbsp;
												 -->
												<!--  
												<a id="advanceSearch" class="btn btn-success"><span class="glyphicon glyphicon-zoom-in"></span> ค้นหาเพิ่มเติม</a>
												 -->
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<%-- 
                    <div class="panel panel-default">
                    	<div class="panel-heading">Ignore Billing Group</div>
                    	<div class="panel-body">
                    		<div class="row">
                    			<div class="col-md-12">
                    			<form id=documentReportForm method="post" class="form-horizontal" role="form">
									<div class="form-group">
										<label class="control-label col-sm-2">วันที่รับชำระ :</label>
										<div class="col-sm-2">
					                           <div class="input-group input-append date" id="stDate">
					                               <input type="text" class="form-control" id="startDate" name="startDate" value="" />
					                               <span class="input-group-addon add-on"><span class="glyphicon glyphicon-calendar"></span></span>
					                           </div>
										</div>
										<label class="control-label col-sm-2" >ช่วงเวลาทำงาน :</label>
                                         <div class="col-sm-2">
                                             <select id="timePeriod" name="timePeriod" class="form-control" >
												<option value="0" selected>ทุกช่วง</option>
												<option value="1">ช่วงเช้า</option>
												<option value="2">ช่วงบ่าย</option>
											 </select>
                                         </div>
                                         <div class="col-sm-4">
                                         	<a id="inquiryReport" class="btn btn-primary glyphicon glyphicon-search"> ค้นหาข้อมูล</a>
                                           	&nbsp;&nbsp;
                                           	<a id="printReport" class="btn btn-success glyphicon glyphicon-print"> แสดงรายงาน</a> 
                                       	 </div>
                                      </div>
                                      <div class="form-group">
                                      	<label class="control-label col-sm-2">สาขารับชำระ :</label>
                                      	<div class="col-sm-2">
                                            <select id="branchCode" class="form-control" >
												<option value="00000" selected>สำนักงานใหญ่</option>
											</select>
                                        </div>
										<label class="control-label col-sm-2">เจ้าหน้าที่ :</label>
										<div class="col-sm-2">
											<div class="btn-group">
						                        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" id="agentCodeSelect">
						                            <span class="selection">All</span> 
						                            <span class="caret"></span>
						                        </button>
						                        <ul class="dropdown-menu" id="agentCode">
						                            <li><a href="#">All</a></li>
						                        </ul>
					                    	</div>
										</div>
								  	</div>
								</form>
                    			</div>	
                    		</div>
                    	</div>
            		</div>
            	    --%>
            	    <div class="col-sm-12 text-right" style="margin-bottom: 10px;">
						<a id="add" onclick="showAddEditBillgingGroupIgnore('0')" class="btn btn-primary"><span class="glyphicon glyphicon-plus"></span> เพิ่ม</a> &nbsp;&nbsp;
						<!--  
						<a id="printReport" class="btn btn-success glyphicon glyphicon-print"> แสดงรายงาน</a>
						<a id="sessionTest" class="btn btn-success glyphicon glyphicon-print"> Session Test</a>
						-->
					</div>
					<div class="clearfix"></div>
					<div class="span6"></div>
	            	<div class="table-responsive">
			            <table id="BillingGroupIgnore"
			              data-toggle="table"
			              data-single-select="true"
			              data-classes="table table-hover table-condensed "
			              data-search="false"
			              data-show-pagination-switch="false"
			              data-show-refresh="false"
			              data-show-toggle="false"
			              data-show-columns="false"
			              data-show-export="false"
			              data-show-footer="false"
			              data-pagination="true"
			              data-height="300">
				          <thead>
					          <tr>
				              	<th class="text-center" data-align="center" data-formatter="runningFormatter">ลำดับที่</th>
				                <th class="text-center" data-field="billingGroup" data-sortable="true"  >Billing Group Code</th>
				                <th class="text-center" data-field="fullName" data-sortable="true"  >Billing Full Name</th>
								<th class="text-center" data-field="billingGroupDesc" data-sortable="true"  >Remark</th>
								<th class="text-center" data-field="id"  data-formatter="manageRowFormatter" data-align="center"></th>
					          </tr>
				           </thead>
				           <!--  
				           <tbody>
				              <tr>
				              		<td>1</td>
				                    <td >TL</td>
			                    <td>รายละเอียด TL</td>
				                    <td>
										<a id="a"  style="cursor:pointer" onclick="showAddEditBillgingGroupIgnore('1')"  class="btn-sm btn-success">แก้ไข</a>
										<a id="e"  style="cursor:pointer" onclick="showConfirm('1')" class="btn-sm btn-danger">ลบทิ้ง</a></td>
				              </tr>
				              <tr>
				              		<td>2</td>
				                    <td>TL 2</td>
				                    <td>รายละเอียด TL2</td>
				                    <td>
										<a id="a"  style="cursor:pointer" onclick="showAddEditBillgingGroupIgnore('2')" class="btn-sm btn-success">แก้ไข</a>
										<a id="e" style="cursor:pointer" onclick="showConfirm('1')" class="btn-sm btn-danger" >ลบทิ้ง</a></td>
				              </tr>
				            </tbody>
				             -->
			          	</table>
			          </div>
            	
            	</div>
            	
            	
            </div>
         <div class="modal fade" role="dialog" id="AddEditBillgingGroupIgnore">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="AddEditBillgingGroupTitle"></h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-md-12">
							<div class="col-md-2"></div>
							<div class="col-md-12">
								<div class="form-horizontal">
									<div class="form-group">
									<input type="hidden" id="billGroupId"/>
									<label class="control-label col-sm-4">Billing Group Code: <font style="color: red;">*</font></label>

											<div id="billGroupElement" class="col-sm-8"><input class="form-control" placeholder="กรุณากรอกข้อมูล" id="billGroup"></div>
									<label class="control-label col-sm-4">Billing Full Name:</label>
										<div id="billGroupFullNameElement" class="col-sm-8"><input class="form-control" placeholder="" id="billGroupFullName"></div>

									<label class="control-label col-sm-4"> Remark:</label>
											<div class="col-sm-8"><input class="form-control" id="billGroupDesc"></div>
											<!--
										<label class="col-sm-12 control-label "
											style="font-size: 25px; text-align: center"><span
											class="glyphicon glyphicon-question-sign"></span>
											ยืนยันลบข้อมูล</label>
											-->
									</div>
								</div>
							</div>
							<div class="col-md-2"></div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<a id="cancelReceiptList" onclick="doSave()" class="btn btn-default"><span class="glyphicon glyphicon-ok-circle"></span> ตกลง</a>
					<a class="btn btn-default" data-dismiss="modal"><span class="glyphicon glyphicon-remove-circle"></span> ยกเลิก</a>
				</div>
			</div>
		</div>
	</div>
	   
       <div class="modal fade" role="dialog" id="confirmCancel">
       <input type="hidden" id="billingGroupForDelete"/> 
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">Confirm</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-md-12">
							<div class="col-md-2"></div>
							<div class="col-md-8">
								<div class="form-horizontal">
									<div class="form-group">
										<label class="col-sm-12 control-label "
											style="font-size: 25px; text-align: center"><span
											class="glyphicon glyphicon-question-sign"></span>
											ยืนยันลบข้อมูล</label>
											
									</div>
								</div>
							</div>
							<div class="col-md-2"></div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<a id="cancelReceiptList" onclick="doDelete()" class="btn btn-default"><span class="glyphicon glyphicon-ok-circle"></span> ตกลง</a>
					<a class="btn btn-default" data-dismiss="modal"><span class="glyphicon glyphicon-remove-circle"></span> ยกเลิก</a>
				</div>
			</div>
		</div>
	</div>
            <script type="text/javascript">
            	 
            	 
            	
            	function showConfirm(id){
            		$("#billingGroupForDelete").val(id);
            		$("#confirmCancel").modal("show");
            	}
            	function showAddEditBillgingGroupIgnore(id){
            		var title="เพิ่มข้อมูล";
            		$("#billGroupElement").removeClass("has-error");
            		$("#billGroupId").val("");
            		$("#billGroup").val("");
            		$("#billGroupDesc").val("");
					$("#billGroupFullName").val("");
            		if(id!='0'){
            			title="แก้ใขข้อมูล";
            			$.ajax({
              			  type: "GET",
              			  url: "../service/billing-group-ignore/"+id,
              			  //data: JSON.stringify(dataSend),
              			  dataType: "json",
              			  contentType: "application/json; charset=utf-8",
              			  success:function(data){
              				 // alert(data)
              				 console.log(data);
              				$("#billGroupId").val(data.id);
                    		$("#billGroup").val(data.billingGroup);
                    		$("#billGroupDesc").val(data.billingGroupDesc);
							  $("#billGroupFullName").val(data.fullName);
              				$("#AddEditBillgingGroupTitle").html(title);
                    		$("#AddEditBillgingGroupIgnore").modal("show");
              			  }
              			});
            		}else{
            			$("#AddEditBillgingGroupTitle").html(title);
                		$("#AddEditBillgingGroupIgnore").modal("show");
            		}
            		
            		
            	}
            	function doDelete(){
            		var id=$("#billingGroupForDelete").val();
            		
            		$.ajax({
            			  type: "DELETE",
            			  url: "../service/billing-group-ignore/"+id,
            			  dataType: "json",
            			  contentType: "application/json; charset=utf-8",
            			  success:function(data){
            				  view.dialog.mainMessageDialog.clear().success(["Delete Success "]).show();
            				   $("#searchฺBillingGroupIgnore").click(); 
            				   $("#confirmCancel").modal("hide");
            			  }
            			});
            	}
            	function test(){
            		var dataSend={
            				"billingGroup":"เทส"
            		}
            		$.ajax({
          			  type: "POST",
          			  url: "../getBillingGroupIgnorePOST.json",
          			  data: JSON.stringify(dataSend),
          			  dataType: "json",
          			  contentType: "application/json; charset=utf-8",
          			  success:function(data){
          				  alert(data.data.length)
          				 // view.dialog.mainMessageDialog.clear().success(["Delete Success "]).show();
          					//$table=$("#PaymentDeductDailyReport");
          					//$table.bootstrapTable("load",data);
          			  }
          			});
            	}
            	function doSave(){
            		
            		var billGroupId=$("#billGroupId").val();
            		console.log('into dosave'+billGroupId)
            		var billGroup = $.trim($("#billGroup").val());
            		if(billGroup.length==0){
            			$("#billGroupElement").addClass("has-error");
            			$("#billGroup").attr("placeholder","กรุณากรอกข้อมูล")
            			$("#billGroup").val(billGroup)
            			return false;
            		}
            			var dataSend={
            					id:billGroupId,
                 				billingGroup:$("#billGroup").val(),
                 				billingGroupDesc:$("#billGroupDesc").val(),
							    fullName:$("#billGroupFullName").val(),
                 		};
            			$.ajax({
                			  type: "POST",
                			  url: "../saveBillingGroupIgnore.json",
                			  data: JSON.stringify(dataSend),
                			  dataType: "json",
                			  contentType: "application/json; charset=utf-8",
                			  success:function(data){
                				  view.dialog.mainMessageDialog.clear().success(["Save Success "]).show();
                				   $("#searchฺBillingGroupIgnore").click(); 
                				   $("#AddEditBillgingGroupIgnore").modal("hide");
                			  }
                			  
                			});
            		
            		
            	}

            	var view = (function($){
            		var self = this, defaultErrorMessage = "An error occurred but there is no message response.";
            		self.session = function(key, val) { if (!val) { var val = window.sessionStorage.getItem(key); return val && (val.indexOf("{") > -1 || val.indexOf("[") > -1) ? JSON.parse(val) : val } window.sessionStorage.setItem(key, ($.type(val) != "object" && $.type(val) != "array" ? val : JSON.stringify(val)))	};
            		self.utils = {
            			guid: function(){ function r() { return Math.floor((Math.random() + 1) * 0x10000).toString(16).substring(1) } return r() + r() +"-"+ r() +"-"+ r() +"-"+ r() +"-"+ r() + r() + r() },
            			numberFormat: function(num, dec) { return $.type(num) !== "number" ? "0.00" : num.toFixed(dec || 2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,'); },
            			dateFormat: function() { var obj = arguments[0]; if ($.type(obj) == "string" && /[0-9]{2}.[0-9]{2}.[0-9]{4}/g) { return obj } else if ($.isNumeric(obj)) { var dt = new Date(obj), dd = dt.getDate().toString(), mm = (dt.getMonth() + 1).toString(), yyyy = dt.getFullYear().toString(); return (dd[1]?"":"0") + dd +"/"+ (mm[1]?"":"0") + mm +"/"+ yyyy } else if ($.type == "date") { return "" } return "" },
            			dateTimeFormat: function() { var obj = arguments[0], dt = $.type(obj) != "date" ? ($.isNumeric(obj) ? new Date(obj) : new Date()) : new Date(), dd = dt.getDate().toString(), MM = (dt.getMonth() + 1).toString(), yyyy = dt.getFullYear().toString(), hh = dt.getHours().toString(), mm = dt.getMinutes().toString(), ss = dt.getSeconds().toString(); return (dd[1]?"":"0") + dd +"/"+ (MM[1]?"":"0") + MM +"/"+ yyyy +" "+ (hh[1]?"":"0") + hh +":"+ (mm[1]?"":"0") + mm +":"+ (ss[1]?"":"0") + ss },
            			queryString: function() { var pos = location.href.indexOf("?"), obj = {}; if (pos < 0) return obj; var params = location.href.slice(pos + 1).split("&"); for (var i = 0, m = params.length; i < m; i++) { pos = params[i].indexOf("="); if (pos < 0) { obj[params[i]] = true; continue; } obj[params[i].substring(0, pos)] = params[i].substring(pos + 1) } return obj },
            			window: function(windowName, url, side) { window.windowOpened = (window.windowOpened || {}); var screenProp = ""; if (side == "left") { screenProp = "left=0,top=0,width="+ (screen.width/2) +",height="+ (screen.height-100) } else if (side == "right") { screenProp = "left="+ (screen.width/2-40) +",top=0,width="+ (screen.width/2+40) +",height="+ (screen.height-100) } window.windowOpened[windowName] = window.open(url, windowName, "menubar=no,scrollbars=no,resizable=0,"+ screenProp, false); return window.windowOpened }
            		};
            		//// AUTOMATIC REGISTER FORMATTER FUNCTION ////
            		window.get = function(url, params, success, msgDialog, preCheck){ $.ajax({ "url": url, "type": "GET", "data": params, "error": (msgDialog || { "valid": function(jqXHR, textStatus, errorThrow){ console.log(jqXHR); console.log("textStatus: "+ textStatus); console.log("errorThrow:"); console.log(errorThrow) } }).valid, "success": function(res){ var isNotJson = res.constructor == String; console.log(res); (preCheck || function(o){})(res); if (isNotJson) res = { "statusCode": "99", "errorList": ["No matching data found."] }; if (msgDialog && !msgDialog.valid(res)) { return; } success(res) } }) }
            		window.post = function(url, params, success, msgDialog, preCheck){ $.ajax({ "url": url, "type": "POST", "data": params, "error": (msgDialog || { "valid": function(jqXHR, textStatus, errorThrow){ console.log(jqXHR); console.log("textStatus: "+ textStatus); console.log("errorThrow:"); console.log(errorThrow) } }).valid, "success": function(res){ var isNotJson = res.constructor == String; console.log(res); (preCheck || function(o){})(res); if (isNotJson) res = { "statusCode": "99", "errorList": ["No matching data found."] }; if (msgDialog && !msgDialog.valid(res)) { return; } success(res) } }) }
            		window.add = function(num1, num2, dec) { if (!dec) dec = 2; return parseFloat((num1 + num2).toFixed(dec), 10); }; window.subtract = function(num1, num2, dec){ if (!dec) dec = 2; return parseFloat((num1 - num2).toFixed(dec), 10); }; window.multiply = function(num1, num2, dec){ if (!dec) dec = 2; return parseFloat((num1 * num2).toFixed(dec), 10); }; window.divide = function(num1, num2, dec){ if (!dec) dec = 2; return parseFloat((num1 / num2).toFixed(dec), 10); }
            		window.find = function(array, propName, obj){ var tmp; for (var i = 0, m = array.length; i < m; i++) { if (array[i][propName] == obj[propName]) tmp = array[i]; } if (tmp == null) { array.push(tmp = obj); } return tmp; }
            		window.toMap = function(array, propNames){ var map = {}, prop; function key(obj){ var p = []; for (var i = 0, m = propNames.length; i < m; i++) { p.push(obj[propNames[i]]) } return p.join() }; for (var i = 0, m = array.length; i < m; i++) { prop = key(array[i]); map[prop] = array[i] } return map }
            		window.stripToNumber = function(str) { return $.type(str) === "string" && $.isNumeric(str = str.replace(/[^0-9\.]/g, "")) ? parseFloat(str, 10) : 0 }
            		window.runningFormatter = function(val, row, ind) { return ind + 1 }
            		window.numberFormatter = function(val, row, ind) { return !$.isNumeric(stripToNumber(val)) ? "0.00" : parseFloat(val, 10).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,') }
            		window.stringInputFormatter = function(val, row, ind) { return '<input value="'+ $.trim(val) +'" maxLength="40" class="form-control">' }
            		window.numberInputFormatter = function(val, row, ind) { return '<input value="'+ self.utils.numberFormat(parseFloat(val || "0.00", 10)) +'" maxLength="10" style="width:100px" class="form-control text-right" onkeypress="return $.isNumeric(String.fromCharCode(event.which)) || (event.which == 46 && this.value.indexOf(\'.\') == -1)" onfocus="(function(o){ o.value = o.value.replace(/[,]/g, \'\') })(this)" onblur="(function(o){ o.value = parseFloat(o.value, 10).toFixed(2).replace(/(\\d)(?=(\\d{3})+\\.)/g, \'$1,\'); })(this)">' }
            		window.moneyInputFormatter = function(val, row, ind) { var value = parseFloat(val || "0.00", 10).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, "$1,"); return '<input value="'+ value +'" maxLength="10" style="width:100px" class="form-control text-right" onkeypress="return $.isNumeric(String.fromCharCode(event.which)) || (event.which == 46 && this.value.indexOf(\'.\') == -1)" onkeyup="if (event.which == 13) this.blur()" onfocus="(function(o){ o.value = o.value.replace(/[,]/g, \'\'); o.select() })(this)" onblur="(function(o){ o.value = parseFloat(o.value, 10).toFixed(2).replace(/(\\d)(?=(\\d{3})+\\.)/g, \'$1,\'); o.style.backgroundColor = o.value !== \''+ value +'\' ? \'yellow\' : \'\'; var table, elem = o; while (table == null) { elem = elem.parentNode; if (elem.tagName == \'TABLE\') table = elem; } (window[table.id +\'InputBlurEvent\'] || function(t){ console.log(t) })(table) })(this)">' }
            		window.modifyButtonFormatter = function(val, row, ind) { return '<a class="btn btn-link modList" onclick=\'(function(elem, row, ind){ var table = $("#"+ $(elem).closest("table")[0].id); window[table[0].id +"ModifyEvent"](table, row, ind) })(this, '+ JSON.stringify(row) +', '+ ind +')\'><span class="glyphicon glyphicon-pencil"></span></a>' }
            		window.removeButtonFormatter = function(val, row, ind) { return '<a class="btn btn-link delList" onclick=\'(function(elem, row, ind){ $("#"+ $(elem).closest("table")[0].id).bootstrapTable("remove", { field: "index", values: [ind] }) })(this, '+ JSON.stringify(row) +', '+ ind +')\'><span class="glyphicon glyphicon-trash"></span></a>' }
            		window.LoadingPanel = function(id) { var obj = this, loadCnt = 0, setupFunc, loadFunc; function checkElemReady(){ if ((obj.elem = $("#"+ id).css("font", "")).length != 1) { alert("The element hasn't insert into HTML DOM."); throw "The element hasn't insert into HTML DOM."; } clearTimeout(setupFunc); clearInterval(loadFunc) }; return { "elem": null, "finish": function(html){ checkElemReady(); (this.elem ? this.elem : this.elem = $("#"+ id)).css("font", "").html(html); return this }, "toString": function(){ var elem; setupFunc = setTimeout(function(){ loadFunc = setInterval(function(){ (elem || (elem = document.getElementById(id))).innerHTML = "Loading"+ Array(++loadCnt).join(".."); if (loadCnt > 60) loadCnt = 2 }, 250) }, 1000); return "<div id='"+ id +"' style='font:BOLD 16pt Arial'></div>" } } }
            		
            		function Message(el) { var obj = this, timeCnt = 0, loadFunc; obj.el = el; obj.elem = $(el);
            			obj.hide = function() { obj.elem.addClass("hide"); return obj };
            			obj.show = function(flag) { if (flag == null || flag) obj.elem.removeClass("hide"); else obj.hide(); return obj };
            			obj.clear = function() { obj.elem.find("*").remove(); obj.hide(); return obj };
            			obj.message = function(arr, cls) { $.each(arr, function(i,o) { var m = o; if ($.type(o) === "object") { m = (o.desc || o.messageDesc) +" [code="+ (o.code || o.messageCode) +"]" }; obj.elem.append('<div class="'+ cls +'">'+ m +'<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button></div>') }); return obj };
            			obj.error = function(arr) { return obj.message(arr, "alert alert-danger") };
            			obj.warn = function(arr) { return obj.message(arr, "alert alert-warning") };
            			obj.success = function(arr) { return obj.message(arr, "alert alert-success") };
            			obj.valid = function(jqXHR, textStatus, errorThrow){ var res = jqXHR; obj.stopLoad(); if (jqXHR && jqXHR.responseJSON) res = jqXHR.responseJSON; if (res) { var isNoData = false; if (res.statusCode && res.statusCode != "0") { obj.warn(res && $.type(res.warningList) === 'array' ? res.warningList : []).error(res && $.type(res.errorList) === 'array' ? res.errorList : ["An error occurred on the server side but there is no error message returned."]).show(); return false } if (res.data && res.statusCode == '0' && res.data.length < 1) isNoData = true; if ($.type(res._embedded) === 'object' && $.map(res._embedded,function(v,k){return v}).length < 1) isNoData = true; if (isNoData) { obj.warn(["There is no records of data."]).show(); return false } return true } obj.error(["An error occurred on the server side but there is no error message returned."]).show(); return false }
            			obj.hideLoad = function(){ obj.stopLoad().clear(); return this }; obj.stopLoad = function(){ clearInterval(loadFunc); return this }
            			obj.showLoad = function(msg){ obj.elem.append('<div id="'+ obj.el +'-loading" class="alert alert-warning">'+ bind(msg, 0) +'</div>'); timeCnt = 0; var elem = document.getElementById(obj.el +"-loading"); loadFunc = setInterval(function(){ elem.innerHTML = bind(msg, ++timeCnt) }, 1000); obj.show(); return this }
            			function bind(msg, timeCnt) { return msg.replace(/\{timeCnt\}/g, timeCnt) }
            		}
            		
            		function AuthenticationDialog(succFunc, failFunc, url, title, usrLabel, pwdLabel, okBtn, cnBtn) { var obj = this, done = succFunc, fail = failFunc, cancel = function(){}; obj.el = "modal_authentication";
            			var content = '<div class="row"><div class="col-md-12"><div class="col-md-offset-2 col-md-10"><div class="form-horizontal"><div class="form-group"><label class="col-sm-3 control-label">'+ (usrLabel || "User Name :") +'</label><div class="col-sm-6"><input type="text" class="form-control"></div></div><div class="form-group"><label class="col-sm-3 control-label">'+ (pwdLabel || "Password :") +'</label><div class="col-sm-6"><input type="password" class="form-control"></div></div></div></div></div></div>';
            			var hdrCS = '<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>';
            			var hdrTT = '<h4 class="modal-title"><span class="glyphicon glyphicon-user"></span> '+ (title || "Authentication") +'</h4>';
            			var divMG = '<div class="alert alert-danger hide"></div>';
            			var btnOK = '<button type="button" class="btn btn-success"><span class="glyphicon glyphicon-ok-circle"></span> '+ (okBtn || "OK") +'</button>';
            			var btnCN = '<button type="button" class="btn btn-danger" data-dismiss="modal"><span class="glyphicon glyphicon-remove-circle"></span> '+ (cnBtn || "Cancel") +'</button>';
            			obj.elem = $(document.body).append('<div class="modal fade" role="dialog" data-backdrop="static" id="'+ obj.el +'"><div class="modal-dialog"><div class="modal-content"><div class="modal-header">'+ hdrCS + hdrTT +'</div><div class="modal-body">'+ divMG + content +'</div><div class="modal-footer">'+ btnOK + btnCN +'</div></div></div></div>').find("#"+ obj.el);
            			obj.elem.find("button").eq(1).on({ click: function(){ var inputs = obj.elem.find("input"); $.post(url, { username: inputs.eq(0).val(), password: inputs.eq(1).val() }, function(res){ if (!res || res.statusCode != "0") { obj.elem.find(".alert").text("You don't have permission to access this feature. Please contact to Administration for more information.").removeClass("hide"); fail(res); return; } done(res); obj.hide() }) } }).end().filter(function(index){ return index == 0 || index == 2 }).on({ click: function(){ cancel() } })
            			obj.show = function(){ obj.elem.modal("show"); obj.elem.find("div.alert").addClass("hide").text(""); return this }; obj.hide = function(){ obj.elem.modal("hide"); return this }
            			obj.done = function(func) { done = func; return this }; obj.fail = function(func) { fail = func; return this }; obj.cancel = function(func) { cancel = func; return this }
            		}
            		
            		self.dialog = new(function(){
            			var that = this;
            	
            			that.mainMessageDialog = new Message("#mainMessageDialog");
            			
            			function Modal(el) {
            				this.el = el;
            				this.elem = $(el);
            				this.hide = function() { this.elem.modal("hide") };
            				this.show = function() { this.elem.modal("show") };
            			}
            		});
            		
            		self.contextPath = '${pageContext.request.contextPath}/';
            	
            		this.dialogAuthentication = new AuthenticationDialog(function(res){ console.log(res); }, function(res){  }, "../checkAuthorize.json");
            		return this;
            	})(jQuery);
 
            	function manageRowFormatter(value) {
                    // 16777215 == ffffff in decimal
                    var color = '#'+Math.floor(Math.random() * 6777215).toString(16);
                    return '<a id="a"  style="cursor:pointer" onclick="showAddEditBillgingGroupIgnore(\''+value+'\')" class="btn-sm btn-success">แก้ไข</a>'+
					'<a id="e" style="cursor:pointer" onclick="showConfirm(\''+value+'\')" class="btn-sm btn-danger" >ลบทิ้ง</a></td>';
                }
            </script>
            <!--  
             <form action="../printTestReport.pdf" method="post" target="_blank">
            	<input type="hidden" id="printPdf" name="printPdf" value="1"/>
            	<input type="hidden" id="type" name="type" value="FULL"/>
            	<input type="hidden" id="sdate" name="sdate"/>
            	<input type="hidden" id="period" name="period"/>
            </form>
             -->
	</body>
</html>
