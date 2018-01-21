<%-- 
    Document   : epis-monthly-tax-abbreviate-sale-report
    Created on : Aug 17, 2017, 10:53:00 AM
    Author     : Pichanan Torsakul
--%>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.List"%>
<%@page import="th.net.cat.epis.controller.EpContextHolder"%>

<%
Calendar cal = Calendar.getInstance();
String YY = cal.get(Calendar.YEAR)+"";
%>
<!DOCTYPE html>
<html>
    <head>
    
        <title>รายงานภาษีขายใบเสร็จรับเงิน/ใบกำกับภาษีแบบย่อ รายเดือน</title>
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
		        $('#stDate').datepicker({
		             format: 'dd/mm/yyyy',
		             lang: 'th', // แสดงภาษาไทย
		             startDate: '01/01/2016',
		             endDate: '30/12/2025'
		        });
		        var date = new Date();
//		        date.setDate(date.getDate()-1);
//		        $('#stDate').datepicker('setDate', date);
		        
 		        $('#stDate').datepicker('setDate', new Date());
		        $('#stDate').datepicker('update');
		        $('#stDate').val('');
		        
		        $('#enDate').datepicker({
		             format: 'dd/mm/yyyy',
		             lang: 'th', // แสดงภาษาไทย
		             startDate: '01/01/2016',
		             endDate: '30/12/2025'
		        });
		        $('#enDate').datepicker('setDate', new Date());
		        $('#enDate').datepicker('update');
		        $('#enDate').val(''); 
		        $('a#printReport').prop('disabled', true);
		        $('a#printXls').prop('disabled', true);
		    });
			function runningFormatter(val, row, ind) {
				$('a#printReport').prop('disabled', false);
				$('a#printXls').prop('disabled', false);
				return ind + 1;
			}
			function currencyFormatter(val, row, ind) {
		    	return null!=val?parseFloat(val, 10).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,'):"-";
		    }
		    function numberFormatter(val, row, ind) {
		    	return parseFloat(val, 10).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,').replace(/\.00$/g, "");
		    }
		    function nameFormatter(val, row, ind) {
		    	return row.userName + " " + row.userFamilyname + " ("+row.userCode+")";
		    }
        	
       	</script>
       	<style>
        .valignMiddle {
            vertical-align: middle !important;
        }
    </style>
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
                        <li class="active"><i>รายงานภาษีขายใบเสร็จรับเงิน/ใบกำกับภาษีแบบย่อ รายเดือน</i></li>
                    </ol>
                    <div class="panel panel-default">
                    	<div class="panel-heading">รายงานภาษีขายใบเสร็จรับเงิน/ใบกำกับภาษีแบบย่อ  รายเดือน</div>
                    	<div class="panel-body">
                    		<div class="row">
                    			<div class="row">
                    			<div class="col-md-12">
									<form id=documentReportForm method="post" class="form-horizontal" role="form">
									<div class="form-group">
										<label class="control-label col-sm-3">ประจำเดือน :</label>
										<div class="col-md-4" style="width:7%;padding-right: 0px;padding-left: 0px;">
											<select class="form-control" id="month" name="month" style="padding:0;">
												<option data-index="0" data-key="01" value="01">มกราคม</option>
												<option data-index="1" data-key="02" value="02">กุมพาพันธ์</option>
												<option data-index="2" data-key="03" value="03">มีนาคม</option>
												<option data-index="3" data-key="04" value="04">เมษายน</option>
												<option data-index="4" data-key="05" value="05">พฤษภาคม</option>
												<option data-index="5" data-key="06" value="06">มิถุนายน</option>
												<option data-index="6" data-key="07" value="07">กรกฎาคม</option>
												<option data-index="7" data-key="08" value="08">สิงหาคม</option>
												<option data-index="8" data-key="09" value="09">กันยายน</option>
												<option data-index="9" data-key="10" value="10">ตุลาคม</option>
												<option data-index="10" data-key="11" value="11">พฤศจิกายน</option>
												<option data-index="11" data-key="12" value="12">ธันวาคม</option>
											</select>
										</div>
										<div class="control-label col-md-1" style="width:1%;padding-left: 5px;padding-right: 8px;font-weight: bolder;"></div>
										<div class="col-md-2" style="width:5%;padding-right: 0px;padding-left: 0px;">
											<input class="form-control" type="text" id="year" name="year" placeholder="YYYY" value="<%= YY %>"  maxlength="4">
											
											
										</div>
									<div class="control-label col-md-1" style="width:4%;padding-left: 5px;padding-right: 8px;font-weight: bolder;">* 2017</div>
                                      	<label class="control-label col-sm-2">สถานที่รับชำระ:</label>
                                      	<div class="col-sm-3" style="width:18%">
											<select class="form-control" id="shop" name="shop"></select>
                                        </div>
										
								  	</div>
								  	<div class="form-group">
								  	<label class="control-label col-sm-3" style="width:24%">เจ้าหน้าที่:</label>
										<div class="col-sm-2" style="width:26%">
											<select class="form-control" id="officer" name="officer"></select>
										</div>
										
										
										<div class="col-sm-1">
                                         	<a id="inquiryReport" class="btn btn-primary glyphicon glyphicon-search"> ค้นหาข้อมูล</a>
                                       	</div>
								  	</div>
								  	
								</form>
                    			</div>	
                    		</div>
                    		</div>
                    	</div>
            		</div>
            		<a id="printReport" class="btn btn-success glyphicon glyphicon-print" style="padding: 2px 5px;margin-bottom: -5em;">
            			<span style="font-size:12px;padding-left: 1px;">PDF</span>
           			</a>
           			<a id="printXls" class="btn btn-success glyphicon glyphicon-floppy-disk" style="padding: 2px 5px;margin-bottom: -5em;">
            			<span style="font-size:12px;padding-left: 1px;">Excel</span>
           			</a>
	            	<div class="table-responsive">
			            <table id="SalesTaxMonthlyReport"
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
				                   <th data-align="center" rowspan="2" class="valignMiddle"  data-formatter="runningFormatter">ลำดับที่</th>
				                   <th data-align="center" rowspan="2" class="valignMiddle"  data-field="receiptDate" data-sortable="true">วันที่รับชำระ</th>
				                   <th data-align="center" rowspan="2" class="valignMiddle"  data-field="posNo" data-sortable="true">Pos No</th>
				                   <th data-align="center" colspan="2" data-sortable="true">ใบกำกับภาษี</th>
				                   <th data-align="center" rowspan="2" class="valignMiddle"  data-field="cntReceipt" data-sortable="true">จำนวนใบกำกับภาษี</th>
				                   <th data-align="center" colspan="3" data-formatter="currencyFormatter" data-sortable="true">Total Amount</th>
					           </tr>
					            <tr>
                        <th data-align="center" data-field="receiptFr" data-sortable="true">From</th>
                        <th data-align="center" data-field="receiptTo" data-sortable="true">To</th>
                        <th data-align="center" data-field="charge" data-formatter="currencyFormatter"  data-sortable="true">Vat Excl.</th>
                        <th data-align="center" data-field="vat" 	data-formatter="currencyFormatter"  data-sortable="true">Vat</th>
                        <th data-align="center" data-field="totalCharge" data-formatter="currencyFormatter" data-sortable="true">Vat Incl.</th>
                    </tr>
				           </thead>
			          	</table>
			          </div>
            	</div>
            </div>
            <form action="../printSalesAbbrTaxMonthlyReport.pdf" method="post" target="_printForm">
            	<input type="hidden" id="printPdf" name="printPdf" value="1"/>
            	<input type="hidden" id="period" name="period"/>
            	<input type="hidden" id="shop" name="shop"/>
            	<input type="hidden" id="officer" name="officer"/>
            	<input type="hidden" id="type" name="type" value="ABVR"/>
            	<input type="hidden" id="serviceType" name="serviceType" value="CAT"/>
            </form>
            <form action="../printSalesAbbrTaxMonthlyReport.xls" method="post" target="_printForm">
            	<input type="hidden" id="printPdf" name="printPdf" value="1"/>
            	<input type="hidden" id="period" name="period"/>
            	<input type="hidden" id="shop" name="shop"/>
            	<input type="hidden" id="officer" name="officer"/>
            	<input type="hidden" id="type" name="type" value="ABVR"/>
            	<input type="hidden" id="serviceType" name="serviceType" value="CAT"/>
            </form>
            <script type="text/javascript">
            	$(document).on("click", "a#inquiryReport", function() {
            		
            		if($("#year").val().length < 4){
                       	alert("โปรดระบุ   ปี  ");
            		}
            		
            		$("#SalesTaxMonthlyReport").bootstrapTable("refresh", 
            				{ url: "../getMonthlySalesTaxReport.json?printPdf=0"
            						+"&period="+$("#month").val()+"-"+$("#year").val()
        							+"&shop="+$("#shop option:selected").val()
        							+"&type=ABVR"
        							+"&officer="+$("#officer option:selected").val()
            						+"&serviceType=CAT"
            						, cache: false });
            			setTimeout(function() { 
            				$("#SalesTaxMonthlyReport").bootstrapTable("resetView")
            		}, 200);
            	}).on("click", "a#printReport", function() {
            		document.forms[1].period.value = $("#month").val()+"-"+$("#year").val();
            		document.forms[1].shop.value = $("#shop option:selected").val();
            		document.forms[1].officer.value = $("#officer option:selected").val();
            		document.forms[1].submit();
            	}).on("click", "a#printXls", function() {
            		document.forms[2].period.value = $("#month").val()+"-"+$("#year").val();
            		document.forms[2].shop.value = $("#shop option:selected").val();
            		document.forms[2].officer.value = $("#officer option:selected").val();
            		document.forms[2].submit();
            	});
            	
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
            		
            		var roleName = "<%=EpContextHolder.getContext().getRoleName().toString()%>";
            		
            		function setup(array) { obj.elem.find("*").remove();
            		console.log("roleName : "+roleName);
	        			obj.elem.append('<option data-index="-1" data-key="-1" value="all">ทั้งหมด</option>');
	            		
            		
            			$.each(array,function(i,o){
            				var tempShop = '${epContext.branchCode}'; 
            				var tempUser = '${epContext.userName}'; 
            				//console.log("tempShop : "+tempShop);
            				if(o.category == 'source.service.name') {
            					if(o.descAbvrEn == 'CAT'){
	            					obj.elem.append('<option data-index="'+ i +'" data-key="'+ o.code +'" value="'+ o.mapCode2 +'">'+ o.descFullTh +'</option>')
            					}
            				
            				} else {
            					
            					
            					if (o.no != null) {
            						var username = "<%=EpContextHolder.getContext().getUserName().toString()%>";
            						var selectted ="";
            						
            						
            						//console.log("tempUser : "+tempUser);
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
         </section>
	</body>
</html>