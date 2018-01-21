<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>รายงานภาษีหัก ณ ที่จ่าย</title>
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
		        
		    });
		    
		    function dateFormatter(val) {
		    	if ($.isNumeric(val)) { 
		    		var dt = new Date(val), dd = dt.getDate().toString(), mm = (dt.getMonth() + 1).toString(), yyyy = dt.getFullYear().toString();
		    		var HH = dt.getHours().toString(), MM = dt.getMinutes().toString(), SS = dt.getSeconds().toString(); 
		    		return (dd[1]?"":"0") + dd +"/"+ (mm[1]?"":"0") + mm +"/"+ yyyy +" "+(HH[1]?"":"0")+HH+":"+(MM[1]?"":"0")+MM+":"+(SS[1]?"":"0")+SS; 
		    	} else if ($.type(val) == "string" && /[0-9]{2}.[0-9]{2}.[0-9]{4}.[0-9]{2}.[0-9]{2}.[0-9]{2}/g) { 
		    		return val 
		    	} else if ($.type == "date") { 
		    		return val 
		    	}
		    	return val 
		    }
		    
			function runningFormatter(val, row, ind) {
				return ind + 1;
			}
			function currencyFormatter(val, row, ind) {
				if("" == val) {
					return "";
				}
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
                    	<li><i>รายงานภาษี</i></li>
                        <li class="active"><i>รายงานภาษีหัก ณ ที่จ่าย</i></li>
                    </ol>
                    <div class="panel panel-default">
                    	<div class="panel-heading">รายงานภาษีหัก ณ ที่จ่าย</div>
                    	<div class="panel-body">
                    		<div class="row">
                    			<div class="col-md-12">
									<div class="form-group">
										<label class="control-label col-sm-2 text-right">วันที่รับชำระ :</label>
										<div class="col-sm-2">
					                           <div class="input-group input-append date" id="stDate">
					                               <input type="text" class="form-control" id="startDate" name="startDate" value="" />
					                               <span class="input-group-addon add-on"><span class="glyphicon glyphicon-calendar"></span></span>
					                           </div>
										</div>
										<label class="control-label col-sm-1 text-right">ถึงวันที่ :</label>
										<div class="col-sm-2">
					                           <div class="input-group input-append date" id="enDate">
					                               <input type="text" class="form-control" id="endDate" name="endDate" value="" />
					                               <span class="input-group-addon add-on"><span class="glyphicon glyphicon-calendar"></span></span>
					                           </div>
										</div>
										<!--  
										<label class="control-label col-sm-2" >ช่วงเวลาทำงาน :</label>
                                         <div class="col-sm-2">
                                             <select id="timePeriod" name="timePeriod" class="form-control" >
												<option value="0" selected>ทุกช่วง</option>
												<option value="1">ช่วงเช้า</option>
												<option value="2">ช่วงบ่าย</option>
											 </select>
                                         </div>
                                          -->
                                         <div class="col-sm-5">
                                         	<a id="inquiryReport" class="btn btn-primary glyphicon glyphicon-search"> ค้นหาข้อมูล</a>
                                         	
                                           	&nbsp;&nbsp;
                                           	<a id="showReport" class="btn btn-success glyphicon glyphicon-print"> แสดงรายงาน</a>
                                           	
                                           	&nbsp;&nbsp;
                                           	<a id="printReport" class="btn btn-success glyphicon glyphicon-export"> ออกรายงาน</a>
                                       	 </div>
								  	</div>
                    			</div>	
                    		</div>
                    	</div>
            		</div>
            
	            	<div class="table-responsive">
			            <table id="PaymentDeductDailyReport"
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
				                   <th data-align="center" data-field="deductionNo" data-formatter="deductionNoFormatterInput" data-sortable="true">เล่มที่/เลขที่</th>
				                   <th data-align="left" data-field="receiptName" data-sortable="true">ชื่อผู้มีหน้าที่หักภาษี ณ ที่จ่าย</th>
				                   <th data-align="right" data-field="amount3tredecim" data-sortable="true">ตามมาตรา 3 เตรส</th>
				                   <th data-align="right" data-field="amount69bis" data-sortable="true">ตามมาตรา 69 ทวิ</th>
				                   <th data-align="right" data-field="amount69tre" data-sortable="true">ตามมาตรา 69 ตรี</th>
				                   <th data-align="center" data-field="remark" data-sortable="true">หมายเหตุ</th>
					           </tr>
				           </thead>
			          	</table>
			          </div>
            	
            	</div>
            	
            	
            </div>
           </section>
            <script type="text/javascript">
            function updateRowDataTableAll(){
            	var table=$("#PaymentDeductDailyReport");
            	var data=table.bootstrapTable("getData");
            	for(var rowid=0;rowid<data.length;rowid++){
            		//data[rowid][name]=obj.value;
            		var deductionNoList=document.getElementsByName("deductionNo");
                	data[rowid]["deductionNo"]=deductionNoList[rowid].value;
       			 	table.bootstrapTable('updateRow', {
           				index: rowid,
           				row: data[rowid]
       				});
            	}
            	
            }
            function updateRowDataTable(obj,rowid) {
            	//if(event.keyCode == 13) {
            		var name=obj.getAttribute("name");
            		var deductionNoList=document.getElementsByName("deductionNo");
            		var amount3tredecimList=document.getElementsByName("amount3tredecim");
            		var amount69bisList=document.getElementsByName("amount69bis");
            		var amount69treList = document.getElementsByName("amount69tre");
            		var remark = document.getElementsByName("remark");
            		console.log('name='+name+' value['+obj.value+'] , rowid['+rowid+'] ,Remark='+remark);   
            		$table=$("#PaymentDeductDailyReport");
                	var data=$table.bootstrapTable("getData");
                	data[rowid][name]=obj.value;
                	data[rowid]["deductionNo"]=deductionNoList[rowid].value;
                	data[rowid]["amount3tredecim"]=amount3tredecimList[rowid].value;
                	data[rowid]["amount69bis"]=amount69bisList[rowid].value;
                	data[rowid]["amount69tre"]=amount69treList[rowid].value;
                	data[rowid]["remark"]=remarkList[rowid].value;
       			 	$table.bootstrapTable('updateRow', {
           				index: rowid,
           				row: data[rowid]
       				});
       				//$table.bootstrapTable("load",data)
               // }
            }
            function myDeductionNoFunction(obj,rowid) {
            	if(event.keyCode == 13) {
            	 console.log(obj)
            	 console.log(rowid)
           		 updateRowDataTable(obj,rowid) ;
            	}
            }
            function myFunction(obj,rowid) {
            	if(event.keyCode == 13) {
            		 updateRowDataTable(obj,rowid) ;
            		 /*
            		var name=obj.getAttribute("name");
            		var amount3tredecimList=document.getElementsByName("amount3tredecim");
            		var amount69bisList=document.getElementsByName("amount69bis");
            		var amount69treList = document.getElementsByName("amount69tre");
            		console.log('name='+name+' value['+obj.value+"] , rowid["+rowid+"]")   
            		$table=$("#PaymentDeductDailyReport");
                	var data=$table.bootstrapTable("getData");
                	data[rowid][name]=obj.value;
                	data[rowid]["amount3tredecim"]=amount3tredecimList[rowid].value;
                	data[rowid]["amount69bis"]=amount69bisList[rowid].value;
                	data[rowid]["amount69tre"]=amount69treList[rowid].value;
       			 	$table.bootstrapTable('updateRow', {
           				index: rowid,
           				row: data[rowid]
       				});
       				//$table.bootstrapTable("load",data)
                */
            	}
            	
            	var key = (event.which || event.keyCode || event.charCode || 0); 
          	     var ch = String.fromCharCode(key); 
          	  	if("0123456789.".indexOf(ch) == -1){
          		 	event.preventDefault();
                	return false;
          	  	}
            	 
            }
            function getValueFormatter(val) {
				if("" == val) {
					return "";
				}
				val=val.replace(/,/g, '');
		    	return parseFloat(val, 10).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,');
		    }
            function deductionNoFormatterInput(val,row,ind) {
            	
            	var value=$.trim(val); //getValueFormatter(val);
                return '<input type="text" class="text-center" onKeyPress="myDeductionNoFunction(this,'+ind+')" name="deductionNo" value="'+value+'"/>';
            }
            function amount3tredecimFormatterInput(val,row,ind) {
            	var value=getValueFormatter(val);
                return '<input type="text" class="text-right" onKeyPress="myFunction(this,'+ind+')" name="amount3tredecim" value="'+value+'"/>';
            }
            function amount69bisFormatterInput(val,row,ind) {
            	var value=getValueFormatter(val);
            	//var value=val;
                return '<input type="text"  class="text-right" onKeyPress="myFunction(this,'+ind+')" name="amount69bis" value="'+value+'"/>';
            }
            function amount69treFormatterInput(val,row,ind) {
            	// row.id
            	var value=getValueFormatter(val);
                    return '<input type="text"  class="text-right" onKeyPress="myFunction(this,'+ind+')" name="amount69tre" value="'+value+'"/>';
                }
            	$(document).on("click", "a#inquiryReport", function() {
            		$("#PaymentDeductDailyReport").bootstrapTable("refresh", 
            				{ url: "../getDailyPaymentDeductReport.json?printXls=0"
        							+"&sdate="+$("#startDate").val().replace(/([0-9]{2}).([0-9]{2}).([0-9]{4})/g,"$1-$2-$3")
        							+"&edate="+$("#endDate").val().replace(/([0-9]{2}).([0-9]{2}).([0-9]{4})/g,"$1-$2-$3")
            						//+"&period="+$("#timePeriod option:selected").val()
            						+"&bcode="+$("#branchCode option:selected").val()
            						+"&emp="+$("#agentCodeSelect").find(".selection").val()
            						+"&type=F", cache: false });
            			setTimeout(function() { 
            				$("#PaymentDeductDailyReport").bootstrapTable("resetView")
            		}, 500);
            	})
            	.on("click","a#showData", function() {
            		// view.table.PaymentDeductDailyReport.data(receiptList);	
            			//that.paymentDeductDailyReport = new BootstrapTable("#PaymentDeductDailyReport");
            			$table=$("#PaymentDeductDailyReport");
            			 var data=$table.bootstrapTable("getData");
            			 //alert(data)
            			 console.log(data)
            			 $table.bootstrapTable('updateRow', {
                				index: 1,
                				row: data[5]
            				});
 						//$table.bootstrapTable('refresh');
 						 data[0].receiptName="Chatchai";
 						  console.log(data)
 						$table.bootstrapTable("load",data)
            		})
            	.on("click", "#agentCode li a", function() {
            		$(this).parents(".btn-group").find('.selection').text($(this).text()); 
            		$(this).parents(".btn-group").find('.selection').val($(this).text());
            	})
            	$(document).on("click", "a#showReport", function() {
            		updateRowDataTableAll();
            		$table=$("#PaymentDeductDailyReport");
            		var data = $table.bootstrapTable("getData");
            		var dataSend = {
            				"sdate":$("#startDate").val().replace(/([0-9]{2}).([0-9]{2}).([0-9]{4})/g,"$1-$2-$3"),
            			    "data":data
            		};
            		$.ajax({
            			  type: "POST",
            			  url: "../printDailyPaymentDeductReportStore.json",
            			  data: JSON.stringify(dataSend),
            			  dataType: "json",
            			  contentType: "application/json; charset=utf-8",
            			  success:function(data){
            				  console.log(data)
            				  $("#tokenXls").val(data);
            				   document.forms[0].submit();
            			  }
            		});
            	});
            	$(document).on("click", "a#printReport", function() {
            		updateRowDataTableAll();
            		$table=$("#PaymentDeductDailyReport");
            		var data = $table.bootstrapTable("getData");
            		var dataSend = {
            				"sdate":$("#startDate").val().replace(/([0-9]{2}).([0-9]{2}).([0-9]{4})/g,"$1-$2-$3"),
            			    "data":data
            		};
            		$.ajax({
            			  type: "POST",
            			  //url: "../printDailyPaymentDeductReportWithUpdate.pdf",
            			  url: "../printDailyPaymentDeductReportStore1.json",
            			  data: JSON.stringify(dataSend),
            			  dataType: "json",
            			  contentType: "application/json; charset=utf-8",
            			  success:function(data){
            				  console.log(data)
            				  $("#tokenXls").val(data);
            				  
            				   document.forms[1].submit();
            			  }
            		});
            		//document.forms[0].sdate.value = $("#startDate").val().replace(/([0-9]{2}).([0-9]{2}).([0-9]{4})/g,"$1-$2-$3");
            		//document.forms[0].edate.value = $("#endDate").val().replace(/([0-9]{2}).([0-9]{2}).([0-9]{4})/g,"$1-$2-$3");
            		//document.forms[0].period.value = $("#timePeriod option:selected").val();
            		//document.forms[0].submit();
            	});
            </script>
            <!--  
            <form action="../printDailyPaymentDeductReport.pdf" method="post" target="_printForm">
             -->
             <form id="reprintPaymentReceiptPdfForm" action="../printDailyPaymentDeductReportWithUpdate.pdf" method="post" target="_printForm">
            	<input type="hidden" id="printPdf" name="printPdf" value="1"/>
            	<input type="hidden" id="sdate" name="sdate"/>
            	<input type="hidden" id="edate" name="edate"/>
            	<input type="hidden" id="tokenPdf" name="tokenPdf"/>
            	<!--  
            	<input type="hidden" id="period" name="period"/>
            	 -->
            </form>
             <form id="reprintPaymentReceiptXlsForm" action="../printDailyPaymentDeductReportWithUpdate.xls" method="post" target="_printForm1">
            	<input type="hidden" id="printXls" name="printXls" value="2"/>
            	<input type="hidden" id="sdate" name="sdate"/>
            	<input type="hidden" id="edate" name="edate"/>
            	<input type="hidden" id="tokenXls" name="tokenXls"/>
            </form>
	</body>
</html>
