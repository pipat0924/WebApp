<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="resources/bootstrap-table/src/bootstrap-table.css" rel="stylesheet" type="text/css"/>
        <link href="resources/css/datepicker.min.css" rel="stylesheet" type="text/css"/>
        <link href="resources/custom.css" rel="stylesheet" type="text/css"/>
        <script src="resources/jquery.min.js" type="text/javascript"></script>
        <script src="resources/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="resources/bootstrap-table/src/bootstrap-table.js"></script>
        <script src="resources/js/bootstrap-datepicker.min.js"></script>
        <script src="resources/custom.js" type="text/javascript"></script>
    </head>
    <body>
        <header class="header_page"></header>
        <section class="container-fluid menu">
            <!-- <button class="menu-toggle btn btn-sm btn-default" style="margin-bottom: 5px"><span class="glyphicon glyphicon-step-forward"></span></button> -->
            <%-- <%@include  file="menu.jsp" %> --%>
            <ol class="breadcrumb">
                <li><i>ประวัติการพิมพ์ใบเสร็จรับเงินซ้ำ/สำเนา/ใบแทน</i></li>
            </ol>
            <div id="mainMessageDialog"></div>
            <div class="row">
                <div class="col-md-12 tab-modefile">
                    <ul id="advanceSearchTab" class="nav nav-tabs" role="tablist">
                        <!--  
                        <li role="presentation"><a href="#tab_1" aria-controls="tab_1" role="tab" data-toggle="tab"><span class="glyphicon glyphicon-user"></span> ค้นหาจากข้อมูลลูกค้า</a></li>
                        <li role="presentation" class="active"><a href="#tab_2" aria-controls="tab_2" role="tab" data-toggle="tab"><span class="glyphicon glyphicon-briefcase"></span> ค้นหาจากใบแจ้งค่าใช้บริการ</a></li>
                         -->
                        <li role="presentation"class="active" ><a href="#tab_3" aria-controls="tab_3" role="tab" data-toggle="tab"><span class="glyphicon glyphicon-th-list"></span> ค้นหาข้อมูล</a></li>
                        <!--  
                        <li role="presentation"><a href="#tab_4" aria-controls="tab_4" role="tab" data-toggle="tab"><span class="glyphicon glyphicon-tasks"></span> ค้นหาจากข้อมูลการรับชำระ</a></li>
                         -->
                    </ul>
                    <div class="tab-content">
                        <div role="tabpanel" class="tab-pane " id="tab_1">
                            <div class="panel panel-default panal-radius">
                                <div class="panel-body">
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-horizontal">
                                                <div class="form-group">
                                                    <label class="control-label col-sm-2">เลขที่ลูกค้า :</label>
                                                    <div class="col-sm-3"><input id="searchCustomerNo" class="form-control"></div>
                                                    <label class="control-label col-sm-2" >เลขประจำตัวผู้เสียภาษี :</label>
                                                    <div class="col-sm-3"><input id="searchCustomerTaxNo" class="form-control"></div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label col-sm-2" >ชื่อลูกค้า :</label>
                                                    <div class="col-sm-3"><input id="searchCustomerFirstName" class="form-control"></div>
                                                    <label class="control-label col-sm-2" >นามสกุล :</label>
                                                    <div class="col-sm-3"><input id="searchCustomerLastName" class="form-control"></div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label col-sm-2" >ชื่อนิติบุคคล/ราชการ :</label>
                                                    <div class="col-sm-3"><input id="searchCustomerOrgName" class="form-control"></div>
                                                    <label class="control-label col-sm-2">กลุ่มผู้ใช้บริการ :</label>
                                                    <div class="col-sm-3">
                                                        <select id="searchCustomerSegment" name="CustomerType"
                                                            class="form-control" >
                                                            <option value="" disabled selected>กรุณาเลือกกลุ่มผู้ใช้บริการ</option>
                                                            <option value="Carrier">Carrier/Operator/NON POTs</option >
                                                            <option value="ISP">ISP</option >
                                                            <option value="MKT">Mkt Arm</option >
                                                            <option value="AGENT">ReSeller/Agent</option >
                                                            <option value="ORGANIZATIONINC">ธุรกิจ กสท</option >
                                                            <option value="ORGANIZATIONBASIC">ธุรกิจทั่วไป</option >
                                                            <option value="INDIVIDUAL">บุคคลทั่วไป</option >
                                                            <option value="EMBASSIES">สถานฑูต/องค์กรระหว่างประเทศ</option >
                                                            <option value="OFFICIAL">หน่วยงานรัฐ</option>
                                                        </select>
                                                    </div>
                                                    <div class="col-sm-2"><a id="searchCustomer" class="btn btn-primary pull-right"><span class="glyphicon glyphicon-search"></span> ค้นหาข้อมูล</a></div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div role="tabpanel" class="tab-pane" id="tab_2">
                            <div class="panel panel-default panal-radius">
                                <div class="panel-body">
                                    <div class="form-horizontal">
                                        <div class="form-group">
                                            <div class="col-sm-1"></div>
                                            <div class="col-sm-3 pull-left"><label class="control-label"><input type="radio" name="searchInvoiceOptions" value="1" checked> เลขที่ใบแจ้งค่าบริการ </label></div>
                                            <div class="col-sm-2"><input id="searchInvoiceNo" class="form-control"></div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-sm-1"></div>
                                            <div class="col-sm-3 pull-left"><label class="control-label"><input type="radio" name="searchInvoiceOptions" value="2"> วันที่จัดทำใบแจ้งค่าใช้บริการ&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;จากวันที่ :</label></div>
                                            <div class="col-sm-2">
                                                <div class="input-group input-append date" data-provide="datepicker" data-date-format="dd/mm/yyyy">
                                                    <input id="searchInvoiceStartDate" class="form-control">
                                                    <span class="input-group-btn"><a class="btn btn-default"><span class="glyphicon glyphicon-calendar"></span></a></span>
                                                </div>
                                            </div>
                                            <label class="control-label col-sm-1">ถึงวันที่ :</label>
                                            <div class="col-sm-2">
                                                <div class="input-group input-append date" data-provide="datepicker" data-date-format="dd/mm/yyyy">
                                                    <input id="searchInvoiceEndDate" class="form-control">
                                                    <span class="input-group-btn"><a class="btn btn-default"><span class="glyphicon glyphicon-calendar"></span></a></span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="col-sm-1"></div>
                                            <div class="col-sm-3 pull-left"><label class="control-label"><input type="radio" name="searchInvoiceOptions" value="3"> หมายเลขบริการ </label></div>
                                            <div class="col-sm-2"><input id="searchInvoiceServiceNo" class="form-control"></div>
                                            <div class="col-sm-6"><a id="searchInvoice" class="btn btn-primary pull-right"><span class="glyphicon glyphicon-search"></span> ค้นหาข้อมูล</a></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div role="tabpanel" class="tab-pane active" id="tab_3">
                            <div class="panel panel-default panal-radius">
                                <div class="panel-body">
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-horizontal">
                                                <div class="form-group">
                                                    <label class="control-label col-sm-2">เลขที่ใบเสร็จรับเงิน :</label>
                                                    <div class="col-sm-2"><input class="form-control" id="inputReceiptNo"></div>
                                                    <label class="control-label col-sm-2">รหัสผู้อนุมัติ :</label>
                                                    <div class="col-sm-2"><input class="form-control" id="inputApprover"></div>
                                                    <label class="control-label col-sm-1">รหัสผู้พิมพ์ :</label>
                                                    <div class="col-sm-2"><input class="form-control" id="inputUpdateUser"></div>
                                                </div>
                                                <div class="form-group">
                                                	<label class="control-label col-sm-2" >วันที่ทำการ:</label>
                                               		<div class="row">
		                                            	<div class="col-sm-2">
			                                                <div class="input-group input-append date" data-provide="datepicker" data-date-format="dd/mm/yyyy">
			                                                    <input id="searchStartDate" class="form-control">
			                                                    <span class="input-group-btn"><a class="btn btn-default"><span class="glyphicon glyphicon-calendar"></span></a></span>
			                                                </div>
			                                            </div>
			                                            <label class="control-label col-sm-2" style="padding-right:25px;margin-right:-10px;">ถึงวันที่ :</label>
			                                            <div class="col-sm-2">
			                                                <div class="input-group input-append date" data-provide="datepicker" data-date-format="dd/mm/yyyy">
			                                                    <input id="searchEndDate" class="form-control">
			                                                    <span class="input-group-btn"><a class="btn btn-default"><span class="glyphicon glyphicon-calendar"></span></a></span>
			                                                </div>
			                                            </div>
			                                            <div class="col-sm-2"><a id="buttonSearchByReceiptInfo" class="btn btn-primary pull-right"><span class="glyphicon glyphicon-search"></span> ค้นหาข้อมูล</a></div>
		                                            </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div role="tabpanel" class="tab-pane " id="tab_4">
                            <div class="panel panel-default panal-radius">
                                <div class="panel-body">
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-horizontal">
                                                <div class="form-group">
                                                    <label class="control-label col-sm-2">วันที่รับชำระเงิน&nbsp;&nbsp;จากวันที่ :</label>
                                                    <div class="col-sm-3"><div id="inputPaymentDateFrom"></div></div>
                                                    <label class="control-label col-sm-2">ถึงวันที่ :</label>
                                                    <div class="col-sm-3"><div id="inputPaymentDateTo"></div></div>
                                                    <div class="col-sm-2"><a id="buttonSearchByPaymentInfo" class="btn btn-primary pull-right"><span class="glyphicon glyphicon-search"></span> ค้นหาข้อมูล</a></div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12 tab-modefile">
                    <ul id="addressTab" class="nav nav-tabs" role="tablist">
                        <li role="presentation" class="active"><a href="#tab1" aria-controls="tab1" role="tab" data-toggle="tab"><span class="glyphicon glyphicon-envelope"></span> ประวัติการพิมพ์ใบเสร็จรับเงินซ้ำ/สำเนา/ใบแทน</a></li>
                       <!--  
                        <li role="presentation" class="">
                            <label> &nbsp;&nbsp;<a class="btn btn-link" id="reprintReceiptDialog"><span class="glyphicon glyphicon-retweet"></span> พิมพ์ใบเสร็จรับเงินซ้ำ</a></label>
                        </li>
                        <li role="presentation" class="">
                            <label> &nbsp;<a class="btn btn-link" id="printCopyOfReceiptDialog"><span class="glyphicon glyphicon-random"></span> พิมพ์สำเนาใบแทน</a></label>
                        </li>
                         -->
                    </ul>
                    <div class="tab-content">
                        <div class="panel panel-default panal-radius">
                            <div class="panel-body">
                                <div class="row">
                                    <div class="col-md-12">
                                    <!-- 
                                    <table id="tbShow" data-toolbar="#byProductToolbar"
								data-toggle="table" data-show-export="true"
								data-pagination="true" data-height="350" data-cache="false">
								<thead>
									<tr>
										<th data-align="center" data-formatter="invoiceClearingSelectedFormatter">เลือก</th>
										<th data-field="docCode" data-sortable="true">เลขที่เอกสาร</th>
										<th data-field="arAmount" data-align="right" data-sortable="true"  data-formatter="numberFormatter">ยอดหนี้</th>
										<th data-field="creditAmount" data-align="right"
											data-sortable="true"  data-formatter="numberFormatter">ลดหนี้</th>
										<th data-field="debitAmount" data-sortable="true"  data-formatter="numberFormatter">เพิ่มหนี้</th>
										<th data-field="paymentAmount" data-align="right"
											data-sortable="true"  data-formatter="numberFormatter">รับชำระแล้ว</th>
										<th data-field="arBalance" data-align="right"
											data-sortable="true"  data-formatter="numberFormatter">ค้างชำระ</th>
										<th data-field="writeOffStatus" data-align="center"
											data-sortable="true" >ตัดหนี้สูญ</th>
									</tr>
								</thead>
							</table>
							 -->
                                        <table id="receiptList" data-toggle="table" data-pagination="true" data-height="350" data-cache="false">
                                            <thead>
                                                <tr>
                                                <!-- 
                                                    <th data-field="checked" data-radio="true"></th>
                                                     -->
                                                    <th data-formatter="runningFormatter">#</th>
                                                    <!--  <th>สาขาที่ชำระเงิน</th>  -->
                                                    <th data-field="receiptno">เลขที่ใบเสร็จรับเงิน</th>
                                                    <th data-field="categoryName">ประเภทการพิมพ์</th>
                                                    <th data-align="center" data-field="updatedttmStr">วันที่ทำการ พิมพ์ซ้ำ/สำเนา/ใบแทน</th>
                                                    <th data-align="center" data-field="versionstamp">จำนวนครั้ง</th> 
                                                    <th data-align="center" data-field="approvedby">รหัสผู้อนุมัติ</th>
                                                    <th data-align="center" data-field="updateuser">รหัสผู้พิมพ์</th>
                                                    <th data-field="reason">เหตุผลในการพิมพ์</th>
                                                    <!--   
                                                    <th>เครื่องรับชำระ</th> 
                                                    <th data-number-format="true">ยอดเงิน</th> 
                                                    <th>สถานะ</th>
                                                     --> 
                                                </tr>
                                            </thead>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            
        </section>
        <div class="modal fade" role="dialog" id="dialogReprintReceipt">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title"><span class="glyphicon glyphicon-inbox"></span> ระบุเหตุผลการพิมพ์ซ้ำ/สำเนา</h4>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-horizontal">
                                    <div class="form-group">
                                        <label class="control-label col-sm-6" ><input type="radio" name="RadioOptions2" onclick="showHideElement()" id="1" value="manuscript"checked> ต้นฉบับ</label>
                                        <label class="control-label col-sm-2" ><input type="radio" name="RadioOptions2" onclick="showHideElement()" id="2" value="copy"> สำเนา</label>
                                    </div> 
                                    <div id="manuscriptElement" style="display:none">
                                        <div class="form-group">
                                            <label class="control-label col-sm-3" >เหตุผล : </label>
                                            <div class="col-sm-7">
                                                 <select class="form-control" id="inputReprintReason"></select>
                                            </div>
                                        </div>
                                        <!-- --> 
                                    
                                        <div>
                                            <label class="control-label col-sm-3" ></label>
                                            <div class="col-sm-7">
                                                <input type="checkbox" name="" id="reprint" value="" >&nbsp;&nbsp;&nbsp;พิมพ์ซ่อม  
                                            </div>  
                                        </div>
                                        <div><label class="control-label col-sm-10" ></label></div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-3">ชื่อผู้อนุมัติ: </label>
                                            <div class="col-sm-7">
                                                <input id="approveBy" class="form-control" value="" >
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-3" >รหัสผ่าน : </label>
                                            <div class="col-sm-7">
                                                <input type="password" class="form-control" value="" >
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            
                        </div>
                    </div>
                    <div class="modal-footer">
                        <a id="reprintReceipt" class="btn btn-success" ><span class="glyphicon glyphicon-print"></span> บันทึกและพิมพ์</a>
                        <a id="cancelReprintReceipt" class="btn btn-danger" data-dismiss="modal"><span class="glyphicon glyphicon-remove-circle"></span> ยกเลิก</a>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal fade" role="dialog" id="dialogPrintCopyOfReceipt">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title"><span class="glyphicon glyphicon-inbox"></span> ระบุเหตุผลการพิมพ์ใบแทน</h4>
                    </div>
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-horizontal">
                                    <!-- 
                                    <div class="form-group">
                                        <label class="control-label col-sm-6" ><input type="radio" name="RadioOptions2" onclick="showHideElement()" id="1" value="copy"checked> สำเนา</label>
                                        <label class="control-label col-sm-2" ><input type="radio" name="RadioOptions2" onclick="showHideElement()" id="2" value="substitute"> ใบแทน</label>
                                    </div> 
                                    <div id="substituteElement" style="display:none">  -->
                                    <div class="form-group">
                                        <label class="control-label col-sm-3" >เหตุผล : </label>
                                        <div class="col-sm-7">
                                             <select class="form-control" id="inputPrintCopyReceiptReason"></select>
                                        </div>
                                    </div>
                                    <!-- --> 
                                    
                                    <div>
                                        <label class="control-label col-sm-3" ></label>
                                        <div class="col-sm-7">
                                            <input type="checkbox" name="" id="reprint1" value="" >&nbsp;&nbsp;&nbsp;พิมพ์ซ่อม  
                                        </div>  
                                    </div>
                                    <div><label class="control-label col-sm-10" ></label></div>
                                    <div class="form-group">
                                        <label class="control-label col-sm-3">ชื่อผู้อนุมัติ: </label>
                                        <div class="col-sm-7">
                                            <input id="approveBy1" class="form-control" value="" >
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-sm-3" >รหัสผ่าน : </label>
                                        <div class="col-sm-7">
                                            <input type="password" class="form-control" value="" >
                                        </div>
                                    </div>
                                    <!-- </div> -->
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <a id="printCopyOfReceipt" class="btn btn-success" ><span class="glyphicon glyphicon-print"></span> บันทึกและพิมพ์</a>
                        <a id="cancelPrintCopyOfReceipt" class="btn btn-danger" data-dismiss="modal"><span class="glyphicon glyphicon-remove-circle"></span> ยกเลิก</a>
                    </div>
                </div>
            </div>
        </div>
    </body>
<script>
$(document).ready(function () {
	$(document).on("click", "#buttonSearchByReceiptInfo", function() {
		buttonSearchByReceiptInfoClickEvent();
	}).on('keypress', "#inputReceiptNo , #inputUpdateUser, #inputApprover",function(e) {
		if (e.keyCode == 13) {
			buttonSearchByReceiptInfoClickEvent();
			  e.preventDefault();
		    //or return false;
		  }
	});
});
function runningFormatter(value, row, index) {
 	return index + 1;
 }
function buttonSearchByReceiptInfoClickEvent() {
    var receiptNo = $.trim($("#inputReceiptNo").val());
    var inputApprover = $.trim($("#inputApprover").val());
    var inputUpdateUser = $.trim($("#inputUpdateUser").val());
    var searchStartDate = $.trim($("#searchStartDate").val());
    var searchEndDate = $.trim($("#searchEndDate").val());
  
    var dataSend={
    		receiptno:receiptNo,
    		updateuser:inputUpdateUser,
    		searchStartDate:searchStartDate,
    		searchEndDate:searchEndDate,
            approvedby:inputApprover
		};
    console.log(dataSend)
	$.ajax({
		  type: "POST",
		  url: "../getReprintHistory.json",
		  data: JSON.stringify(dataSend),
		  dataType: "json",
		  contentType: "application/json; charset=utf-8",
		  success:function(data){
			 console.log(data.data) 
			 //  var table=$("#receiptList");
			    //	var data=$table.bootstrapTable("getData");
			    $("#receiptList").bootstrapTable("load",data.data);
			    setTimeout(function() {
			    	$("#receiptList").bootstrapTable("resetView");
	            }, 600);
				
		  }
		  
		});
    
}

</script>
</html>
