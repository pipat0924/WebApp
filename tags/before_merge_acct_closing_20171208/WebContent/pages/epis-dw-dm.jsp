<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ออกรายงาน dw-dm</title>
<link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<link href="resources/bootstrap-table/src/bootstrap-table.css"
	rel="stylesheet" type="text/css" />
<link href="resources/custom.css" rel="stylesheet" type="text/css" />
<script src="resources/jquery.min.js" type="text/javascript"></script>
<script src="resources/bootstrap/js/bootstrap.min.js"></script>
<script src="resources/bootstrap-table/src/bootstrap-table.js"></script>
<script src="resources/js/mustache.min.js" type="text/javascript"></script>
<script src="resources/ExportCSVFromJson.js" type="text/javascript"></script>
<script src="resources/custom.js" type="text/javascript"></script>
<script type="text/javascript">
function runningMsgHide() { $('#alertSuccessAreaMsg').addClass('hide'); $('#alertDangerAreaMsg').addClass('hide'); }

</script>
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
						<li role="presentation" class="active"><a href="#"><span
								class="glyphicon glyphicon-list"></span> DW & DM</a></li>
					</ul>
					<div class="panel panel-default panal-radius">
						<div class="panel-body">
							<div class="tab-content">
								<div role="tabpanel" class="tab-pane active" id="tab_1">
									<div class="form-horizontal">
										<div class="form-group"></div>
										<div class="form-group">
											<div class="col-sm-4"></div>
											<label class="control-label col-sm-2" style="width: 8%">เดือน
												:</label>
											<div class="col-sm-2" style="width: 10%">
												<select class="form-control" id="month" name="month"
													required>
													<option value="">เลือก</option>
													<option value="01">มกราคม</option>
													<option value="02">กุมภาพันธ์</option>
													<option value="03">มีนาคม</option>
													<option value="04">เมษายน</option>
													<option value="05">พฤษภาคม</option>
													<option value="06">มิถุนายน</option>
													<option value="07">กรกฎาคม</option>
													<option value="08">สิงหาคม</option>
													<option value="09">กันยายน</option>
													<option value="10">ตุลาคม</option>
													<option value="11">พฤศจิกายน</option>
													<option value="12">ธันวาคม</option>
												</select>
											</div>
											<label class="control-label col-sm-1">ปี</label>
											<div class="col-sm-1">
												<input id="year" type="text" class="form-control"
													maxlength="4">
											</div>
											<div class="col-sm-1">
												<input id="postDate" name= "postDate" type="hidden" class="form-control"
													>
											</div>
										</div>
										<div class="form-group"></div>
										<div class="form-group">
											<div class="col-sm-5"></div>
											<div class="col-sm-3">
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<a id="searchData" class="btn btn-success"><span
													class="glyphicon glyphicon-download-alt"></span> Export
													DW-DM</a>
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<a id="resetData" class="btn btn-danger"><span
													class="glyphicon glyphicon-minus-sign"></span> Clear</a>
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
<script>
 $(document).on("click", "a#searchData", function() {
        runningMsgHide();
        if($('#month').val() == '' || $('#year').val() == '') {
            if($('#month').val() == '') { $('#month').attr("style", "color:#a94442"); $("#month").attr("style", "background-color:#ffffaa;border:solid 1px #a94442;padding:0 10px"); }
            if($('#year').val() == '') { $('#year').attr("style", "color:#a94442"); $("#year").attr("style", "background-color:#ffffaa;border:solid 1px #a94442;padding:0 10px"); }
            return false;
        } else {
        	
        	var postDate = document.getElementById("postDate");
        	 var month = document.getElementById("month");
        	 var year = document.getElementById("year");
        	 var pdate = year.value+month.value;
        	 
        	 console.log("postDate : "+pdate);
        	 var docName = "BILL_PAYMENT_IBACSS_"+pdate;
        	
        	 postDate.value = pdate;
        	 
            var dataSend={
            		postDate:$("#postDate").val()
            		
            };
           
            console.log("dataSend: " + dataSend);
            $.ajax({
                type: "POST",
                url: "../findDwListJSON.json",
                data: dataSend,
                success:function(data){
                	
                	console.log(data);
                   new FuncJsonToTxt(data, docName, ["BILLING_ACC_ID", "INVOICE_NO","CUSTOMER_GROUP","BILLGROUP","SEGMENT_CODE","PRODUCT_CODE","SUB_PRODUCT_CODE","REVENUE_TYPE_CODE","INVDATE","DUEDATE","PAYMENT_LOCATION_CODE","PAY_TYPE_CODE","PAYMENT_DATETIME","PERIOD","PAY_AMOUNT","VAT_AMOUNT","TOTAL AMOUN","COLLECTION_CODE","REGION_SAP","STATUS_FLAG","ACCOUNT_CODE_NEW","JOBDATE"], ["contrno", "arRef","customerGroup","billGroup","segmentCode","productCode","subProductCode","revenueTypeCode","invDate","dueDate","payLocation","payType","payDate","usagePeriod","payAmount","payVat","payTotalamount","collectionCode","region","statusFlag","glAccount","postDate"]);

                  //  $("#unitTable").bootstrapTable("load", data);
                }
            });
        }
        }).on("click", "a#resetData", function() {
        runningMsgHide();
        $('#month').removeAttr("style"); $('#year').removeAttr("style");
        $('#month').val(""); $('#year').val("");
    });

   </script>


</html>