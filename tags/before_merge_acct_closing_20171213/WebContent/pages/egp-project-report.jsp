<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>รายงานรายรับโครงการ/สัญญา บช.1</title>
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
		        date.setDate(date.getDate()-1); 
		        $('#stDate').datepicker('setDate', date);
		        
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
            <!--  <button class="menu-toggle btn btn-sm btn-default" style="margin-bottom: 5px"><span class="glyphicon glyphicon-step-forward"></span></button>-->
                <!--%@include  file="menu.jsp" %-->
            <div class="row">
                <div class="col-md-12">
                    <ol class="breadcrumb">
                    	<li><i>รายงานรายรับโครงการ/สัญญา บช.1</i></li>
                        <li class="active"><i>รายงานรายรับโครงการ/สัญญา บช.1</i></li>
                    </ol>
                    <div class="panel panel-default">
                    	<div class="panel-heading">ข้อมูลการรับชำระเงิน บช.1</div>
                    	<div class="panel-body">
                    		<div class="row">
                    			<div class="col-md-12">
                    			<form id=documentReportForm method="post" class="form-horizontal" role="form">
									<div class="form-group">
									<div class="control-label col-sm-2">
									</div>
										<label class="control-label col-sm-1" style="width:13%">วันที่รับชำระ:</label>
										<div class="col-sm-2" style="width:13%">
				                           <div class="input-group input-append date" id="stDate">
				                               <input type="text" class="form-control" id="startDate" name="startDate" value="">
				                               <span class="input-group-addon add-on"><span class="glyphicon glyphicon-calendar"></span></span>
				                           </div>
										</div>
																			
										<label class="control-label col-sm-1" style="width: 9%;">ถึงวันที่:</label>
                                         <div class="col-sm-2" style="width:13%">
				                           <div class="input-group input-append date" id="enDate">
				                               <input type="text" class="form-control" id="endDate" name="endDate" value="">
				                               <span class="input-group-addon add-on"><span class="glyphicon glyphicon-calendar"></span></span>
				                           </div>
										</div>
										
                                       <div class="col-sm-2">
                                         	<a id="inquiryReport" class="btn btn-primary glyphicon glyphicon-search"> ค้นหาข้อมูล</a>
                                       	</div>
                                      </div>
                                 <div class="form-group">
                                      <div class="control-label col-sm-2">
									</div>
									<label class="control-label col-sm-2" style="width:11%">ประเภทการรับชำระ:</label>
										<div class="col-sm-2">
											<select class="form-control" id="soureType" name="soureType"></select>
										</div>
									
									<label class="control-label col-sm-1" style="width:10%">สถานที่รับชำระ:</label>
                                      	<div class="col-sm-3" style="width:20%">
											<select class="form-control" id="shop" name="shop"></select>
                                        </div>
									
                                      </div>
								</form>
                    			</div>	
                    		</div>
                    	</div>
            		</div>
            		<!-- <a id="printReport" class="btn btn-success glyphicon glyphicon-print" style="padding: 2px 5px;margin-bottom: -5em;">
            			<span style="font-size:12px;padding-left: 1px;">PDF</span>
           			</a> -->
           			<a id="printXls" class="btn btn-success glyphicon glyphicon-print" style="padding: 2px 5px;margin-bottom: -5em;">
            			<span style="font-size:12px;padding-left: 1px;"> Excel</span>
           			</a>
	            	<div class="table-responsive">
			            <table id="EgpProjectReport"
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
				          	  		<th colspan="4" data-align="center"></th>
				          	  		<th colspan="2" data-align="center">ระยะเวลาในสัญญา</th>
				          	  		<th colspan="7" data-align="center"></th>
				          	  		<th colspan="4" data-align="center">ตั้งหนี้</th>
				          	  		<th colspan="4" data-align="center">ปรับลดหนี้</th>
				          	  		<th colspan="5" data-align="center">ข้อมูลรับชำระ</th>
				          	  		<th colspan="3" data-align="center">ลูกหนี้คงเหลือ</th>
				          	  		<th colspan="3" data-align="center">(ลง.)กรอก ค้างรับ</th>
				          	  </tr>	
					          <tr>
				                   <th data-align="center" data-formatter="runningFormatter">ลำดับที่</th>
				                   <th data-align="center" data-field="egpNo" data-sortable="true">เลขที่ e-GP</th>
				                   <th data-align="center" data-field="egpContract" data-sortable="true">เลขที่สัญญา (บช.1)</th>
				                   <th data-align="center" data-field="receiptName" data-sortable="true">ชื่อผู้ใช้บริการ</th>
				                   
				                   <th data-align="center" data-field="startDate"  data-sortable="true">เริ่มวันที่</th>
				                   <th data-align="center" data-field="endDate" data-sortable="true">สิ้นสุดวันที่</th>
				                   <th data-align="center" data-field="egpValue" data-formatter="numberFormatter" data-sortable="true">มูลค่าสัญญา (รวม VAT)</th>
				                   
				                   <th data-align="center" data-field="accountNo" data-sortable="true">BA</th>
				                   <th data-align="center" data-field="invoiceNo" data-sortable="true">Invoice</th>
				                   <th data-align="center" data-field="billGroup" data-sortable="true">Service</th>
				                   <th data-align="center" data-field="issueDate" data-sortable="true">Issue date</th>
				                   <th data-align="center" data-field="dueDate" data-sortable="true">Due date</th>
				                   <th data-align="center" data-field="billCycle" data-sortable="true">Period</th>
				                   
				                   <th data-align="center" data-field="beginCharge" data-formatter="numberFormatter" data-sortable="true">Revenue</th>
				                   <th data-align="center" data-field="beginVat" data-formatter="numberFormatter" data-sortable="true">VAT</th>
				                   <th data-align="center" data-field="beginTotalCharge" data-formatter="numberFormatter" data-sortable="true">Amount (รวม VAT)</th>
				                   <th data-align="center" data-field="posting_date" data-sortable="true">Posting date</th>
				                   
				                   <th data-align="center" data-field="adjCharge"   data-formatter="currencyFormatter" data-sortable="true">Revenue</th>
				                   <th data-align="center" data-field="adjVat"   data-formatter="currencyFormatter" data-sortable="true">VAT</th>
				                   <th data-align="center" data-field="adjTotalCharge"  data-formatter="currencyFormatter"  data-sortable="true">Amount (รวม VAT)</th>
				                   <th data-align="center" data-field="adjPostingDate" data-sortable="true">Posting date</th>
				                   
				                   <th data-align="center" data-field="receiptDate" data-sortable="true">วันที่รับชำระ</th>
				                   <th data-align="center" data-field="branchName" data-sortable="true">สถานที่รับชำระ</th>
				                   <th data-align="center" data-field="charge" data-formatter="numberFormatter" data-sortable="true">จำนวนเงินก่อน VAT</th>
				                   <th data-align="center" data-field="vat" data-formatter="numberFormatter" data-sortable="true">VAT</th>
				                   <th data-align="center" data-field="totalCharge" data-formatter="numberFormatter" data-sortable="true">จำนวนเงินรวม VAT</th>
				                   
				                   <th data-align="center" data-field="netCharge"  data-formatter="currencyFormatter" data-sortable="true">จำนวนเงินก่อน VAT</th>
				                   <th data-align="center" data-field="netVat"  data-formatter="currencyFormatter" data-sortable="true">VAT</th>
				                   <th data-align="center" data-field="netTotalCharge"  data-formatter="currencyFormatter" data-sortable="true">จำนวนเงินรวม VAT</th>
				                   
				                   <th data-align="center" data-sortable="true">Revenue</th>
				                   <th data-align="center" data-sortable="true">VAT</th>
				                   <th data-align="center" data-sortable="true">จำนวนเงินรวม VAT</th>
					           </tr>
				           </thead>
			          	</table>
			          </div>
            	</div>
            </div>
            <form action="../printEgpProjectReport.pdf" method="post" target="_printForm">
            	<input type="hidden" id="printPdf" name="printPdf" value="1"/>
            	<input type="hidden" id="startDate" name="startDate"/>
            	<input type="hidden" id="endDate" name="endDate"/>
            	<input type="hidden" id="shop" name="shop"/>
            </form>
            <form action="../printEgpProjectReport.xls" method="post" target="_printForm1">
            	<input type="hidden" id="printXLS" name="printXLS" value="1"/>
            	<input type="hidden" id="startDate" name="startDate"/>
            	<input type="hidden" id="endDate" name="endDate"/>
            	<input type="hidden" id="shop" name="shop"/>
            	<input type="hidden" id="soureType" name="soureType"/>
            </form>
            <script type="text/javascript">
            	$(document).on("click", "a#inquiryReport", function() {
            		$("#EgpProjectReport").bootstrapTable("refresh", 
            				{ url: "../getEgpProjectReport.json?printPdf=0"
            						+"&startDate="+$("#startDate").val().replace(/([0-9]{2}).([0-9]{2}).([0-9]{4})/g,"$1-$2-$3")
            						+"&endDate="+$("#endDate").val().replace(/([0-9]{2}).([0-9]{2}).([0-9]{4})/g,"$1-$2-$3")
            						+"&shop="+$("#shop option:selected").val()
            						+"&soureType=" +$("#soureType option:selected").val(), cache: false });
            			setTimeout(function() { 
            				$("#EgpProjectReport").bootstrapTable("resetView")
            		}, 200);
            	}).on("click", "a#printReport", function() {
            		document.forms[1].startDate.value = $("#startDate").val().replace(/([0-9]{2}).([0-9]{2}).([0-9]{4})/g,"$1-$2-$3");
				  	document.forms[1].endDate.value = $("#endDate").val().replace(/([0-9]{2}).([0-9]{2}).([0-9]{4})/g,"$1-$2-$3");
              		document.forms[1].shop.value = $("#shop option:selected").val();
              		document.forms[1].soureType.value = $("#soureType option:selected").val();
            		document.forms[1].submit();
            	}).on("click", "a#printXls", function() {
            		console.log("excel");
            		document.forms[2].startDate.value = $("#startDate").val().replace(/([0-9]{2}).([0-9]{2}).([0-9]{4})/g,"$1-$2-$3");
				  	document.forms[2].endDate.value = $("#endDate").val().replace(/([0-9]{2}).([0-9]{2}).([0-9]{4})/g,"$1-$2-$3");
              		document.forms[2].shop.value = $("#shop option:selected").val();
              		document.forms[2].soureType.value = $("#soureType option:selected").val();
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
            		function setup(array) { obj.elem.find("*").remove(); 
	            		obj.elem.append('<option data-index="-1" data-key="-1" value="-1">ทั้งหมด</option>');
	        			$.each(array,function(i,o){
	        				var tempShop = '${epContext.branchCode}'; 
	        				if(o.category == 'source.service.name') {
	        					if (o.mapCode4 == 'EGP') {
	            					obj.elem.append('<option data-index="'+ i +'" data-key="'+ o.code +'" value="'+ o.mapCode2 +'">'+ o.descFullTh +'</option>')
            					}	        				
	        					} else {
	        					if (o.no != null) {
	       							obj.elem.append('<option data-index="'+ i +'" data-key="'+ o.id +'" value="'+ o.no +'">'+ o.name +'</option>');
	        						$("#shop").val(tempShop)
	        					} else {
	       							obj.elem.append('<option data-index="'+ i +'" data-key="'+ o.id +'" value="'+ o.id +'">'+ o.name +'</option>');
	        					}
	       					}
	        			});
            		}
            		data = obj.elem.change(window[el.substring(1) +"ChangeEvent"]).find("option").map(function(i, opt){ return { key: opt.getAttribute("data-key"), value: opt.value, text: opt.textContent } });
            		}
            		(function(){ $(window["setup"]) })();
            			new DropDown("#shop").init("../findShopList.json");
            			new DropDown("#officer").init("../findOfficerList.json");
            			new DropDown("#soureType").init("../findSourceType.json");
            		return this;
            	})(jQuery);
            </script>
        </section>
	</body>
</html>
