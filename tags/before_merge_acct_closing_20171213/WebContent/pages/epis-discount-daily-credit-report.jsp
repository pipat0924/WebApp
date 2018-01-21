<%@page import="java.util.List"%>
<%@page import="th.net.cat.epis.controller.EpContextHolder"%>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>รายงานส่วนลดประจำวัน (ขายเชื่อ)</title>
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
		        $('.stDate').datepicker({
		             format: 'dd/mm/yyyy',
		             lang: 'th',
		             startDate: '01/01/2016',
		             endDate: '30/12/2025'
		        });
		        $('.stDate').datepicker('setDate', new Date());
		        $('.stDate').datepicker('update');
		        $('.stDate').val('');
		        $('a#printReport').prop('disabled', true);	
		        $('a#printXls').prop('disabled', true);
		    });
		    
			function runningFormatter(val, row, ind) {
				$('a#printReport').prop('disabled', false);
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
                <div class="col-md-12">
                    <ol class="breadcrumb">
                    	<!-- <li><i>รายงานการรับชำระเงิน</i></li> -->
                        <li class="active"><i>รายงานส่วนลดประจำวัน (ขายเชื่อ)</i></li>
                    </ol>
                    <div class="panel panel-default">
                    	<!-- <div class="panel-heading">ข้อมูลไม่ปิดบัญชีการรับชำระเงิน</div> -->
                    	<div class="panel-body">
                    		<div class="row">
                    			<div class="col-md-12">
                    			<form id=documentReportForm method="post" class="form-horizontal" role="form">
									<div class="form-group">
										<label class="control-label col-sm-2">วันที่รับชำระ :</label>
										<div class="col-sm-2">
				                           <div class="input-group input-append date stDate">
				                               <input type="text" class="form-control" id="startDate" name="startDate" value="" />
				                               <span class="input-group-addon add-on"><span class="glyphicon glyphicon-calendar"></span></span>
				                           </div>
										</div>
										<label class="control-label col-sm-2" >ถึงวันที่ :</label>
										<div class="col-sm-2">
                                         <div class="input-group input-append date stDate">
                                           <input type="text" class="form-control" id="toDate" name="toDate" value="" />
				                           <span class="input-group-addon add-on"><span class="glyphicon glyphicon-calendar"></span></span>
                                         </div>
                                         </div>
                                         <div class="col-sm-4">
                                         	<a id="inquiryReport" class="btn btn-primary glyphicon glyphicon-search"> ค้นหาข้อมูล</a>
                                           	
                                           	<!-- <a id="printReport" class="btn btn-success glyphicon glyphicon-print"> แสดงรายงาน</a> 
                                       	 -->
										</div>
                                      </div>
                                      <div class="form-group">
                                      	<label class="control-label col-sm-2">สาขารับชำระ :</label>
                                      	<div class="col-sm-2">
											<select class="form-control" id="shop" name="shop"></select>
                                        </div>
										<label class="control-label col-sm-2">เจ้าหน้าที่ :</label>
										<div class="col-sm-2">
											<select class="form-control" id="officer" name="officer"></select>
										</div>
								  	</div>
								</form>
                    			</div>	
                    		</div>
                    	</div>
            		</div>
            	    <a onclick="printReport()" class="btn btn-success glyphicon glyphicon-print" style="padding: 2px 5px;margin-bottom: -5em;">
            			<span style="font-size:12px;padding-left: 1px;">PDF</span>
           			</a>
           			<a onclick="printXls()" id="printXls" class="btn btn-success glyphicon glyphicon-floppy-disk" style="padding: 2px 5px;margin-bottom: -5em;">
            			<span style="font-size:12px;padding-left: 1px;">Excel</span>
           			</a>
	            	<div class="table-responsive">
			            <table id="DiscountDailyCreditReport"
			              data-toggle="table"
			              data-single-select="true"
			              data-classes="table table-hover table-condensed "
			              data-search="true"
			              data-show-pagination-switch="true"
			              data-show-refresh="true"
			              data-show-toggle="true"
			              data-show-columns="true"
			              data-show-export="false"
			              data-show-footer="false"
			              data-pagination="true"
			              data-height="525">
				          <thead>
					          <tr>
				                   <th data-align="center" data-formatter="runningFormatter">ลำดับที่</th>
				                    <th data-align="left" data-field="receiptNo" data-sortable="true">เลขที่ใบเสร็จรับเงิน/ใบกำกับภาษี</th>
				                  <th data-align="center" data-field="accountNo" data-sortable="true">เลขที่สัญญา (BA/CA)</th>
				                   <th data-align="left" data-field="receiptName" data-sortable="true">ชื่อผู้ซื้อสินค้า/ผู้รับบริการ</th>
				                  <th data-align="center" data-field="invoiceNo" data-sortable="true">เลขที่ใบแจ้งหนี้/ชื่อบริการ</th>
				                   <th data-align="right" data-field="totalCharge" data-sortable="true" data-formatter="numberFormatter">มูลค่าบริการ</th>
				                   <th data-align="right" data-field="discount" data-sortable="true" data-formatter="numberFormatter">ส่วนลดตามบริการ (billing)</th>				    
				                   <th data-align="right" data-field="after_sale_discount" data-sortable="true" data-formatter="numberFormatter">ส่วนลดหลังการขาย(Billing)</th>				               
				                   <th data-align="center" data-field="attributes" data-formatter="statusFormatter" data-sortable="true">สถานะ</th>
				                   
					           </tr>
				           </thead>
			          	</table>
			          </div>
            	</div>
            </div>
            <form action="../printDiscountDailyCreditReport.pdf" method="post" target="_printForm">
            	<input type="hidden" id="printPdf" name="printPdf" value="1"/>
            	<input type="hidden" id="startDate" name="startDate"/>
            	<input type="hidden" id="toDate" name="toDate"/>
            	<input type="hidden" id="period" name="period"/>
            	<input type="hidden" id="shop" name="shop"/>
            	<input type="hidden" id="officer" name="officer"/>
            </form>
            
            <form action="../printDiscountDailyCreditReport.xls" method="post" target="_printForm">
            	<input type="hidden" id="printPdf" name="printXLS" value="1"/>
            	<input type="hidden" id="startDate" name="startDate"/>
            	<input type="hidden" id="toDate" name="toDate"/>
            	<input type="hidden" id="period" name="period"/>
            	<input type="hidden" id="shop" name="shop"/>
            	<input type="hidden" id="officer" name="officer"/>
            	<input type="hidden" id="serviceType" name="serviceType" value="CAT"/>
            </form>
            <script type="text/javascript">
            function statusFormatter(val, row, ind) {return row.attributes.indexOf("R") > -1 ? "ยกเลิก" : (row.attributes.indexOf("A") > -1 ? "-" : "-") }
            	$(document).on("click", "a#inquiryReport", function() {
            		$("#DiscountDailyCreditReport").bootstrapTable("refresh", 
            				{ url: "../getDiscountDailyCreditReport.json?printPdf=0"
            						+"&startDate="+$("#startDate").val().replace(/([0-9]{2}).([0-9]{2}).([0-9]{4})/g,"$1-$2-$3")
            						+"&toDate="+$("#toDate").val().replace(/([0-9]{2}).([0-9]{2}).([0-9]{4})/g,"$1-$2-$3")
            						+"&period="+$("#period option:selected").val()
            						+"&shop="+$("#shop option:selected").val()
            						+"&officer="+$("#officer option:selected").val(), cache: false });
            			setTimeout(function() { 
            				$("#DiscountDailyCreditReport").bootstrapTable("resetView")
            		}, 200);
            	})
            	function printReport() {
            		document.forms[1].startDate.value = $("#startDate").val().replace(/([0-9]{2}).([0-9]{2}).([0-9]{4})/g,"$1-$2-$3");
            		document.forms[1].toDate.value = $("#toDate").val().replace(/([0-9]{2}).([0-9]{2}).([0-9]{4})/g,"$1-$2-$3");
            		document.forms[1].period.value = $("#period option:selected").val();
            		document.forms[1].shop.value = $("#shop option:selected").val();
            		document.forms[1].officer.value = $("#officer option:selected").val();
            		document.forms[1].submit();
            	};
            	
            	function printXls() {       
            		document.forms[2].startDate.value = $("#startDate").val().replace(/([0-9]{2}).([0-9]{2}).([0-9]{4})/g,"$1-$2-$3");
            		document.forms[2].toDate.value = $("#toDate").val().replace(/([0-9]{2}).([0-9]{2}).([0-9]{4})/g,"$1-$2-$3");
            		document.forms[2].period.value = $("#period option:selected").val();
            		document.forms[2].shop.value = $("#shop option:selected").val();
            		document.forms[2].officer.value = $("#officer option:selected").val();
            		document.forms[2].submit();
            		
            	};
            	var view = (function($){
            		function DropDown(el, url) { var obj = this, data = [{ key: "", value: "", text: "Please Select" }]; obj.el = el; obj.elem = $(el);
            		obj.data = function(array) { if ($.type(array) == "array") { setup(data = array); return this } return data; };
            		obj.init = function(url) { if (url) $.get(url, function(res) { setup(data = res.data) }); else setup(data); return this };
            		obj.disable = function(flag) { obj.elem.attr("disabled", (flag == null ? true : flag)); return this }
            		obj.enable = function() { obj.disable(false); return this }
            		obj.index = function() { if (arguments.length == 1) { var maxLen = data.length, index = arguments[0]; obj.elem.find("option").each(function(i, opt){ opt.removeAttribute("selected") }).eq(index < 0 ? maxLen + index : index).attr("selected", true); return this } return $.inArray("selected", obj.elem.find("option").map(function(i, opt){ return opt.selected ? "selected" : "no-selected" })) }
            		obj.selected = function(){ return data[obj.index()]; }; 
            		obj.val = function() { return obj.elem.val(); }; 
            		obj.key = function(){ if (arguments.length == 0) { var key = obj.elem.find("option:selected").data("key"); if (typeof key === "string" && key === "undefined") { var item = data[obj.index()]; key = item && item.key ? item.key : item.id } return key } var key = arguments[0]; for (var i = 0, m = data.length; i < m; i++) { var item = data[i]; if (item.key && item.key == key) { obj.index(i); break; } if (item.id && item.id == key) { obj.index(i); break; } } return this }
            		
            		   //// AUTOMATIC REGISTER FORMATTER FUNCTION ////
                    window.get = function(url, params, success, msgDialog, preCheck){ $.ajax({ "url": url, "type": "GET", "data": params, "error": (msgDialog || { "valid": function(jqXHR, textStatus, errorThrow){ console.log(jqXHR); console.log("textStatus: "+ textStatus); console.log("errorThrow:"); console.log(errorThrow) } }).valid, "success": function(res){ var isNotJson = res.constructor == String; console.log(res); (preCheck || function(o){})(res); if (isNotJson) res = { "statusCode": "99", "errorList": ["No matching data found."] }; if (msgDialog && !msgDialog.valid(res)) { return; } success(res) } }) }
                    window.post = function(url, params, success, msgDialog, preCheck){ $.ajax({ "url": url, "type": "POST", "data": params, "error": (msgDialog || { "valid": function(jqXHR, textStatus, errorThrow){ console.log(jqXHR); console.log("textStatus: "+ textStatus); console.log("errorThrow:"); console.log(errorThrow) } }).valid, "success": function(res){ var isNotJson = res.constructor == String; console.log(res); (preCheck || function(o){})(res); if (isNotJson) res = { "statusCode": "99", "errorList": ["No matching data found."] }; if (msgDialog && !msgDialog.valid(res)) { return; } success(res) } }) }
                    window.add = function(num1, num2, dec) { if (!dec) dec = 2; return parseFloat((num1 + num2).toFixed(dec), 10); }; window.subtract = function(num1, num2, dec){ if (!dec) dec = 2; return parseFloat((num1 - num2).toFixed(dec), 10); }; window.multiply = function(num1, num2, dec){ if (!dec) dec = 2; return parseFloat((num1 * num2).toFixed(dec), 10); }; window.divide = function(num1, num2, dec){ if (!dec) dec = 2; return parseFloat((num1 / num2).toFixed(dec), 10); }
                    window.find = function(array, propName, obj){ var tmp; for (var i = 0, m = array.length; i < m; i++) { if (array[i][propName] == obj[propName]) tmp = array[i]; } if (tmp == null) { array.push(tmp = obj); } return tmp; }
                    window.toMap = function(array, propNames){ var map = {}, prop; function key(obj){ var p = []; for (var i = 0, m = propNames.length; i < m; i++) { p.push(obj[propNames[i]]) } return p.join() }; for (var i = 0, m = array.length; i < m; i++) { prop = key(array[i]); map[prop] = array[i] } return map }
                    window.stripToNumber = function(str) { return $.type(str) === "string" && $.isNumeric(str = str.replace(/[^0-9\.]/g, "")) ? parseFloat(str, 10) : 0 }
                    window.runningFormatter = function(val, row, ind) { return ind + 1 }
                    window.numberFormatter = function(val, row, ind) { return !$.isNumeric(stripToNumber(val)) ? "0.00" : parseFloat(val, 10).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,') }
                    window.numberFormatterWithCurrency = function(val, row, ind) {
                        var currencyCode = "฿";
                        if(row.currencyCode == "1"){
                            currencyCode = "$";
                        }
                        return !$.isNumeric(stripToNumber(val)) ? "0.00"+currencyCode : parseFloat(val, 10).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,')+currencyCode }
                    window.numberFormatter2 = function(val, row, ind) {
                        return !$.isNumeric(stripToNumber(val)) || val == 0 ? "-" : parseFloat(val, 10).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,') }
                    window.stringInputFormatter = function(val, row, ind) { return '<input value="'+ $.trim(val) +'" maxLength="40" class="form-control">' }
                    window.numberInputFormatter = function(val, row, ind) { return '<input value="'+ self.utils.numberFormat(parseFloat(val || "0.00", 10)) +'" maxLength="10" style="width:100px" class="form-control text-right" onkeypress="return $.isNumeric(String.fromCharCode(event.which)) || (event.which == 46 && this.value.indexOf(\'.\') == -1)" onfocus="(function(o){ o.value = o.value.replace(/[,]/g, \'\') })(this)" onblur="(function(o){ o.value = parseFloat(o.value, 10).toFixed(2).replace(/(\\d)(?=(\\d{3})+\\.)/g, \'$1,\'); })(this)">' }
                    window.moneyInputFormatter = function(val, row, ind) { var value = parseFloat(val || "0.00", 10).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, "$1,"); var currencySymbol = "฿"; if (row!=null){var currencySymbol = chkCurrencySymbol(row.currencyCode); console.log("symbol is >>>>>> "+currencySymbol)};return '<div class="input-group"><span class="input-group-addon" id="basic-addon1" style="padding:2px 4px;">' + currencySymbol + '</span><input value="'+ value +'" id="received_'+ind+'"  maxLength="10" style="width:100px" class="form-control text-right" onkeypress="return $.isNumeric(String.fromCharCode(event.which)) || (event.which == 46 && this.value.indexOf(\'.\') == -1)" onkeyup="if (event.which == 13) this.blur()" onfocus="(function(o){ o.value = o.value.replace(/[,]/g, \'\'); o.select() })(this)" onblur="(function(o){ o.value = parseFloat(o.value, 10).toFixed(2).replace(/(\\d)(?=(\\d{3})+\\.)/g, \'$1,\'); o.style.backgroundColor = o.value !== \''+ value +'\' ? \'yellow\' : \'\'; var table, elem = o; while (table == null) { elem = elem.parentNode; if (elem.tagName == \'TABLE\') table = elem; } (window[table.id +\'InputBlurEvent\'] || function(t){ console.log(t) })(table) })(this)"></div>' }
                    window.modifyButtonFormatter = function(val, row, ind) { return '<a class="btn btn-link modList" onclick=\'(function(elem, row, ind){ var table = $("#"+ $(elem).closest("table")[0].id); window[table[0].id +"ModifyEvent"](table, row, ind) })(this, '+ JSON.stringify(row) +', '+ ind +')\'><span class="glyphicon glyphicon-pencil"></span></a>' }
                    window.removeButtonFormatter = function(val, row, ind) { return '<a class="btn btn-link delList" onclick=\'(function(elem, row, ind){ $("#"+ $(elem).closest("table")[0].id).bootstrapTable("remove", { field: "index", values: [ind] }) })(this, '+ JSON.stringify(row) +', '+ ind +')\'><span class="glyphicon glyphicon-trash"></span></a>' }
                    window.LoadingPanel = function(id) { var obj = this, loadCnt = 0, setupFunc, loadFunc; function checkElemReady(){ if ((obj.elem = $("#"+ id).css("font", "")).length != 1) { alert("The element hasn't insert into HTML DOM."); throw "The element hasn't insert into HTML DOM."; } clearTimeout(setupFunc); clearInterval(loadFunc) }; return { "elem": null, "finish": function(html){ checkElemReady(); (this.elem ? this.elem : this.elem = $("#"+ id)).css("font", "").html(html); return this }, "toString": function(){ var elem; setupFunc = setTimeout(function(){ loadFunc = setInterval(function(){ (elem || (elem = document.getElementById(id))).innerHTML = "Loading"+ Array(++loadCnt).join(".."); if (loadCnt > 60) loadCnt = 2 }, 250) }, 1000); return "<div id='"+ id +"' style='font:BOLD 16pt Arial'></div>" } } }
                    window.remarkIconFormatter = function(val, row, ind) {if(row.remark != null){return '<a  style="cursor: pointer" onclick="modalPopupRemark('+"'"+row.remark+"'"+')"><span class="glyphicon glyphicon-envelope" aria-hidden="true"></span></a>';}}
            		
            		var roleName = "<%=EpContextHolder.getContext().getRoleName().toString()%>";
            		
            		function setup(array) { obj.elem.find("*").remove();
            		console.log("roleName : "+roleName);
	        			obj.elem.append('<option data-index="-1" data-key="-1" value="all">ทั้งหมด</option>');            		
            			$.each(array,function(i,o){
            				var tempShop = '${epContext.branchCode}'; 
            				var tempUser = '${epContext.userName}'; 
            				if(o.category == 'source.service.name') {
            					if(o.descAbvrEn == 'CAT'){
	            					obj.elem.append('<option data-index="'+ i +'" data-key="'+ o.code +'" value="'+ o.mapCode2 +'">'+ o.descFullTh +'</option>')
            					}
            				
            				} else {
            					
            					
            					if (o.no != null) {
            						var username = "<%=EpContextHolder.getContext().getUserName().toString()%>";
            						var selectted ="";
            						
            						
            						console.log("tempUser : "+tempUser);
            						if(name == username){
            							selectted = 'selected="selected"'
            						}           		
            						
           							obj.elem.append('<option data-index="'+ i +'" data-key="'+ o.id +'" value="'+ o.no +'"'+selectted+'>'+ o.name +'</option>');
            						if(roleName != "Suppervisor")
            							{
               							$("#officer").val(tempUser);         
            							}
            						$("#shop").val(tempShop)

            					} else {

           							obj.elem.append('<option data-index="'+ i +'" data-key="'+ o.id +'" value="'+ o.no +'">'+ o.name +'</option>');
            					}

           					}
            			}); 
            		}
            		data = obj.elem.change(window[el.substring(1) +"ChangeEvent"]).find("option").map(function(i, opt){ return { key: opt.getAttribute("data-key"), value: opt.value, text: opt.textContent } });
            		}
            		(function(){ $(window["setup"]) })();
            		var strComboValue = [ 
            			<% 
            			    List<String> options = EpContextHolder.getContext().getOwners();
            			    for (int i = 0; i < options.size(); i++) {
            			%>
            			        "<%=options.get(i)%>"
            			<%
            			        if (i + 1 < options.size()) {
            			%>
            			            ,
            			<%
            			        }
            			    }
            			%>
            			    ];
            		
            			var selectted = $("#officer").val();
            			var dataselect =[];
            			var i = 0;
            			for(i = 0; i<strComboValue.length ; i++){
            				var data = {"no":strComboValue[i],"name":strComboValue[i]};           				
            				dataselect.push(data);

            			}
            			new DropDown("#officer").data(dataselect);
            			if(i < 2){
            				new DropDown("#officer").disable();
            			}
            			new DropDown("#shop").init("../findShopList.json");
            			new DropDown("#soureType").init("../findSourceType.json");
            			new DropDown("#shop").disable();
            		return this;
            	})(jQuery);
            </script>
	</body>
</html>
