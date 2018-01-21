<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=application.getInitParameter("episName")%></title>
        <link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="resources/bootstrap-table/src/bootstrap-table.css" rel="stylesheet" type="text/css"/>
        <link href="resources/css/datepicker.min.css" rel="stylesheet" type="text/css"/>
        <link href="resources/custom.css" rel="stylesheet" type="text/css"/>
        <script src="resources/jquery.min.js" type="text/javascript"></script>
        <script src="resources/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="resources/bootstrap-table/src/bootstrap-table.js"></script>
        <script src="resources/js/bootstrap-datepicker.min.js"></script>
        <script src="resources/custom.js" type="text/javascript"></script>
        <script src="resources/js/mustache.min.js" type="text/javascript"></script>
        <!-- x-editable (bootstrap) -->
      <!-- <link href="resources/css/bootstrap-editable.css" rel="stylesheet" type="text/css">
       <script src="resources/js/bootstrap-editable.js" type="text/javascript"></script> -->
    </head>
<style>
.btn-file {
    position: relative;
    overflow: hidden;
}
.btn-file input[type=file] {
    position: absolute;
    top: 0;
    right: 0;
    min-width: 100%;
    min-height: 100%;
    font-size: 100px;
    text-align: right;
    filter: alpha(opacity=0);
    opacity: 0;
    outline: none;
    background: white;
    cursor: inherit;
    display: block;
}
#tableContainer {
  display: none;
}
.label-normal {
  display: inline;
  padding: .2em .6em .3em;
  font-size: 100%;
  font-weight: normal;
  line-height: 1;
  color: #484848;
  text-align: center;
  white-space: nowrap;
  vertical-align: baseline;
  border-radius: .25em;
}
.label-bold {
  display: inline;
  padding: .2em .6em .3em;
  font-size: 100%;
  font-weight: bold;
  line-height: 1;
  color: #484848;
  text-align: center;
  white-space: nowrap;
  vertical-align: baseline;
  border-radius: .25em;
}

.col-pad{
  position: relative;
  min-height: 1px;
  padding-left: 15px;
  /*padding-right: 5px;*/
}

</style>
    <body>
        <header class="header_page"></header>
        <section class="container-fluid menu">
            <!-- <button class="menu-toggle btn btn-sm btn-default" style="margin-bottom: 5px"><span class="glyphicon glyphicon-step-forward"></span></button> -->
            <%-- <%@include  file="menu.jsp" %> --%>
            <div class="row">
                <div class="col-md-12 tab-modefile">
                	<ol class="breadcrumb">
                        <li><i>บันทึกคำร้องขอปรับลดหนี้</i></li>
                        <li class="active">ค้นหาข้อมูลการปรับลดหนี้</li>
                        <li>บันทึกรายละเอียดขอปรับลดหนี้</li>
                        <li>สรุปการขอปรับลดหนี้</li>
                        <li>ผลการขอปรับลดหนี้</li>
                    </ol>
                    <div id="mainMessageDialog"></div>
                    <span id="fileMsgTest" style="display:inline-block;"></span>
                    <div id="panelSearchReceipt">
                    <!-- Nav tabs -->
                    <ul class="nav nav-tabs" role="tablist">
                        <li role="presentation" class="active">
                        	<a href="#tab_cus" aria-controls="tab_cus" role="tab" data-toggle="tab"><span class="glyphicon glyphicon-filter"></span> ค้นหาข้อมูล</a>
                        </li>
                    </ul>
                    <div class="tab-content">
                        <div role="tabpanel" class="tab-pane active" id="tab_cus">
                            <div class="panel panel-default panal-radius">
                                <div class="panel-body">
                                    <div class="row">
		                                <div class="col-md-12">
		                                    <div class="form-horizontal">
		                                        <div class="form-group">
		                                            <label class="control-label col-sm-2">เลขที่ใบแจ้งค่าบริการ  :</label>
		                                            <div class="col-sm-2">
		                                                <input class="form-control" value=""  id="billNo">
		                                            </div>
		                                            <label class="control-label col-sm-2" >เลขที่ใบเสร็จรับเงิน :</label>
		                                            <div class="col-sm-2">
		                                                <input class="form-control" value="" id="receiptNo">
		                                            </div>
		                                            <div class="col-sm-4">
		                                            <button id="search" type="button" class="btn btn-primary" >
		                                            	<span class="glyphicon glyphicon-search"></span> ค้นหา</button>&nbsp;&nbsp;
		                                            <button type="button" class="btn btn-success"  data-toggle="modal" data-target="#CustomerSearch">
		                                            
		                                            	<span class="glyphicon glyphicon-zoom-in"></span> ค้นหาเพิ่มเติม</button></div>
		                                            	<!--  <button type="button" class="btn btn-success"  id="buttonTest" onclick="testClick()">Test</button> -->
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
            </div>
            <!--------------------------------------->
            <ul id="panelNavigation1" class="list-inline pull-right list-menu-set">
                <li><a id="addAdjustReceiptList" class="btn btn-link"><span class="glyphicon glyphicon-plus-sign"></span> เพิ่มรายการการขอปรับลดหนี้</a></li>
               <!--  <li><a id="addAdjustReceiptList" href="adjust-payment_2.jsp" class="btn btn-link"><span class="glyphicon glyphicon-plus-sign"></span> เพิ่มรายการการขอปรับลดหนี้</a></li>  -->
              <!--  <li><a id="sumAdjustReceiptList" href="adjust-payment_3.jsp" class="btn btn-link"><span class="glyphicon glyphicon-th-list"></span> สรุปรายการขอปรับลดหนี้</a></li> --> <!-- <span class="badge badge_modefil">2</span></li> -->
            </ul>
            <!--------------------------------------->
            <div class="hide row" id="panelAdjustReceiptList">
	                <!-- Tab panes -->
	                	<div class="col-md-12 tab-modefile">
		                <div class="panel-heading">
		                    <h3 class="panel-title"><span class="glyphicon glyphicon-shopping-cart"></span> รายการขอปรับลดหนี้</h3>
		                </div>
	                    <div role="tabpanel" class="tab-pane active" id="tab-2-1">
	                        <table id="tableAdjustPaymentList"
	                               data-row-style="rowStyle"
	                               data-toggle="table"
	                               data-classes="table table-hover table-striped"
	                               
	                               >
	                            <thead>
	                                <tr>
	                                	<th data-field="checked"  data-radio="true"></th>
		                                <th data-formatter="runningFormatter" data-align="center">#</th>
		                                <th data-field="billRefNo" data-align="center">เลขที่ใบแจ้งค่าบริการ</th>
		                                <th data-field="receiptNo" data-align="center">เลขที่ใบเสร็จรับเงิน</th>
		                                <th data-field="updatePrintDate" data-align="center">วันที่รับชำระ</th>
		                                <th data-field="accountNo" data-align="center">เลขที่ลูกค้า</th>
		                                <th data-field="accountName" data-align="center">ชื่อลูกค้า</th>
		                                <th data-field="paymentCase" data-align="center">วิธีการชำระเงิน</th>
		                                <th data-field="totalCharge" data-align="center">ยอดเงิน</th>
		                                <th data-field="shopPaymentName" data-align="center">สถานที่รับชำระ</th>
		                                <th data-field="paymentReceiver" data-align="center">ผู้รับชำระ</th>
		                                <!-- <th data-field="status" data-align="center" data-formatter="statusFormatter">สถานะ</th> -->
		                                <th data-field="status" data-align="center" >สถานะ</th>
	                                </tr>
	                            </thead>
	                            <!-- <tbody>
	                                <tr>
	                                    <td><input type="radio" name="optradio"></td>
	                                    <td>1</td>
	                                    <td>255900001</td>
	                                    <td>EP0171701F150714000020</td>
	                                    <td>05/02/2016 12:45</td>
	                                    <td>200006599</td>
	                                    <td>นาย วสันตืชาย วงค์คำเดือน</td>
	                                    <td>เงินสด</td>
	                                    <td>1,070.00</td>
	                                    <td>ศบล.แจ้งวัฒนะ</td>
	                                    <td>EPIS2016</td>
	                                    <td>ปกติ</td>
		                            </tr>
		                            <tr>
	                                    <td><input type="radio" name="optradio"></td>
	                                    <td>2</td>
	                                    <td>200058590</td>
	                                    <td>EP0171701F150714000021</td>
	                                    <td>10/01/2016 10:45</td>
	                                    <td>200006599</td>
	                                    <td>นาย วสันตืชาย วงค์คำเดือน</td>
	                                    <td>เงินสด</td>
	                                    <td>1,070.00</td>
	                                    <td>ศบล.แจ้งวัฒนะ</td>
	                                    <td>EPIS2016</td>
	                                    <td>ปกติ</td>
		                            </tr>
	                            </tbody> -->
	                        </table>
	                    </div>
	                </div>
            
        </div>
        <div id="panelCustomer" class="hide row">
            <div class="col-md-12 tab-modefile">
            	 <ul class="nav nav-tabs" role="tablist">
	                <li role="presentation" class="active">
	                	<a href="#tab_cus" aria-controls="tab_cus" role="tab" data-toggle="tab"><span class="glyphicon glyphicon-user"></span>  ข้อมูลลูกค้า</a>
	                </li>
	            </ul>
	            <div class="tab-content">
	                <div role="tabpanel" class="tab-pane active" id="tab_cus">
	                    <div class="panel panel-default panal-radius">
	                        <div class="panel-body">
	                            <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-horizontal">
                                                <div class="form-group">
                                                    <label class="control-label col-sm-2" >เลขที่ลูกค้า :</label>
                                                    <div class="col-sm-2">
                                                        <input class="form-control" id="accountNo" readonly="">
                                                    </div>
                                                    <label class="control-label col-sm-1" >ชื่อลูกค้า :</label>
                                                    <div class="col-sm-3">
                                                        <input class="form-control" id="custName" readonly="">
                                                    </div>
                                                   <!--  <label class="control-label col-sm-2" >เลขที่คำร้อง :</label>
                                                    <div class="col-sm-2">
                                                        <input class="form-control" value="EP0171701AJ50714001" disabled="">
                                                    </div>  -->
                                                    <label class="control-label col-sm-2" >กลุ่มลูกค้า :</label>
                                                    <div class="col-sm-2">
                                                        <input class="form-control" id="accountType" disabled="">
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    
                                                 <!--    <label class="control-label col-sm-4" ></label>
                                                    <label class="control-label col-sm-2" >วันที่บันทึกคำร้อง :</label>
													<div class="col-sm-2">
                                                        <div class="input-group">
                                                            <input class="form-control" value="25/02/2016" disabled="">
                                                            <span class="input-group-btn">
                                                                <button class="btn btn-default" type="button"><span class="glyphicon glyphicon-calendar"></span></button>
                                                            </span>
                                                        </div>
                                                    </div> -->
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
        <!--------------------------------------->
	    <ul id="panelNavigation2" class="list-inline pull-right list-menu-set hide row">
	        <li><a id="addAdjustReceiptList" href="adjust-payment_1.jsp" class="btn btn-link"><span class="glyphicon glyphicon-arrow-left"></span> กลับไปเพิ่มรายการ</a></li>
	        <li><a id="selectAdjustReceiptList" class="btn btn-link"><span class="glyphicon glyphicon-plus-sign"></span> เลือกรายการขอปรับลดหนี้</a></li>
	        <li style="padding-left:22px;"></li>	
	    </ul>
	    <!--------------------------------------->
        <div class="row hide" id="panelAdjustReceiptList2">
			<div class="col-md-12 tab-modefile">
                            <div class="form-inline">
                                <label >เหตุผลการขอปรับลดหนี้ :</label>
                                <select  id="inputAdjustReasonType" class="form-control">
                                </select>
                            </div>
                        	
                        	<div class="form-group">
                        		<label >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;เงื่อนไข :</label>
                                <label class="label-normal" style="text-align: left">
                                     <input type="radio" name="radioAdjustCondition" id="radioAdjCndt1" value="01" > หักล้างหนี้ค้างชำระ
                                 </label>
                                <label class="label-normal" style="text-align: left">
                                     <input type="radio" name="radioAdjustCondition" id="radioAdjCndt2" value="02" > คืนเงิน
                                 </label>
                                <label class="label-normal" style="text-align: left">
                                     <input type="radio" name="radioAdjustCondition" id="radioAdjCndt3" value="03" > บันทึกยอด  Advance
                                 </label>
                             </div> 
                    <div class="modal fade" role="dialog" id="selectAdjustConditionModal">
						<div class="modal-dialog">
			                <div class="modal-content">
			                    <div class="modal-header">
			                        <button type="button" class="close" data-dismiss="modal" aria-label="Close" onclick="closeModal(true);"><span aria-hidden="true">&times;</span></button>
			                        <h4 class="modal-title"><span class="glyphicon glyphicon-exclamation-sign"></span> ลดหนี้ (คืนเงิน)</h4>
			                    </div>
			                    <div id="confirmMessage"></div>
			                    <div class="modal-body">
			                        <div class="row">
			                            <div class="col-md-12">
			                                <div class="form-horizontal">
			                                    <div class="form-group">
			                                    	<label >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ประเภทการคืนเงิน :</label>
			                                        <label class="label-normal" style="text-align: left">
			                                            <input type="radio" name="radioAdjRefundType" id="radioAdjRefundOptions1" value="01" checked=""> เงินสด/เช็ค
			                                        </label>
			                                        <label class="label-normal" style="text-align: left">
			                                            <input type="radio" name="radioAdjRefundType" id="radioAdjRefundOptions2" value="02"> โอน
			                                        </label>
			                                    </div>
			                                    <div class="form-group">
			                                        <label class="col-sm-4 control-label" id="labelAccountName">ชื่อบัญชี :</label>
			                                        <div class="col-sm-6">
			                                            <input id="inputAccountName" type="text" class="form-control" >
			                                        </div>
			                                    </div>
			                                    <div class="form-group">
			                                        <label class="col-sm-4 control-label" id="labelAccountNo">เลขที่บัญชี :</label>
			                                        <div class="col-sm-6">
			                                            <input id="inputAccountNo" type="text" class="form-control" >
			                                        </div>
			                                    </div>
			                                    <div class="form-group">
			                                        <label class="col-sm-4 control-label" id="labelAdjustRefundBankType" >ธนาคาร :</label>
			                                        <div class="col-sm-6">
			                                            <select class="form-control" id="inputAdjustRefundBankType">
			                                            </select>
			                                        </div>
			                                    </div>
			                                </div>
			                            </div>
			                        </div>
			                    </div>
			                    <div class="modal-footer">
			                    <button id="buttonConfirmAdjustCntn" type="button" class="btn btn-success" data-dismiss="modal" onclick="closeModal(false);">
					                <span class="glyphicon glyphicon-ok-circle"></span> ตกลง
					            </button>
							</div>
			                </div><!-- /.modal-content -->
			            </div><!-- /.modal-dialog -->
					</div>
					<ul id="invoiceDetailsTab" class="nav nav-tabs" role="tablist">
						<li role="presentation" class="active""><a href="#tab-2-1" aria-controls="tab-2-1" role="tab" data-toggle="tab"><span class="glyphicon glyphicon-briefcase"></span> รายการใบแจ้งค่าใช้บริการ</a></li>
					</ul>
					<div class="tab-content">
						<div role="tabpanel" class="tab-pane active" id="tab-2-2">
						<table id="tableAdjustPaymentList2"
	                          data-row-style="rowStyle"
							data-toggle="table" 
							data-detail-view="true"
	                          data-detail-formatter="detailFormatter"
	                          data-classes="table table-hover table-striped"
	                          data-detail-view="true"
	                          >
	                       <thead>
	                           <tr>
									<th data-field="billRefNo" data-sortable="true">เลขที่ใบแจ้ง<br>ค่าใช้บริการ</th>
									<th data-field="issueDt" data-sortable="true">วันจัดทำใบแจ้ง<br>ค่าใช้บริการ</th>
									<th data-field="dueDt" data-sortable="true" data-align="right">วันครบ<br>กำหนด</th>
									<th data-field="charge" data-sortable="true" data-align="right" data-formatter="numberFormatter">ยอดก่อน<br>ภาษี</th>
									<th data-field="discount" data-sortable="true" data-align="right" data-formatter="numberFormatter">เงิน<br>ส่วนลด</th>
									<th data-field="vat" data-sortable="true" data-align="right" data-formatter="numberFormatter">ภาษีมูลค่า<br>เพิ่ม</th>
									<th data-field="totalCharge" data-sortable="true" data-align="right" data-formatter="numberFormatter">ยอดเงิน<br>รวมภาษี</th>
									<th data-field="balanceDue" data-sortable="true" data-align="right" data-formatter="numberFormatter">จำนวนเงิน<br>คงค้าง</th>
									<th data-field="totalCharge" data-sortable="true" data-align="right" data-formatter="numberFormatter">ยอดเงิน<br>ที่ต้องชำระ</th>
									<th data-field="deduction" data-sortable="true" data-align="right" data-formatter="numberFormatter">ภาษีหัก<br>ณ ที่จ่าย</th>
									<th data-field="billCycle" data-align="center">รอบการใช้งาน</th>
									<th data-field="adjustAmount" data-align="center"  data-formatter="moneyInputFormatter" >เงินขอปรับลด</th>
									<!-- <th  data-align="center" data-field="adjustAmount" data-type="text" >เงินขอปรับลด</th>  -->
								</tr>
	                       </thead>
	                   </table>
	                   </div>
	               </div>    
            </div> 
		</div>
		  <div class="hide row" id="panelAdjustDocument">
		<!--<div class="row" id="panelAdjustDocumentTest"> -->
             <div class="col-md-12 tab-modefile">
                 <!-- Nav tabs -->
               <ul class="nav nav-tabs" role="tablist">
                   <li role="presentation" class="active"><a href="#tab-2-1" aria-controls="tab-2-1" role="tab" data-toggle="tab"><span class="glyphicon glyphicon-th-list"></span> เอกสารประกอบการขอปรับลดหนี้</a></li>
               </ul>
               <!-- Tab panes -->
               <div class="tab-content">
	                   <div role="tabpanel" class="tab-pane active" id="tab-2-3">
	                       <div class="panel panel-default panal-radius">
	                           <div class="panel-body">
	                               <div class="form-horizontal">
	                               	   <form method="POST" enctype="multipart/form-data" id="docAdjOriginateFileUploadForm">	
		                                   <div class="form-group">
		                                           <div class="form-inline" >
		                                           		<div class="checkbox">
			                                           		<label>
			                                                   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" id="checkDocAdjOriginate" value="ADJ-01"> คำร้องขอปรับลดหนี้ของผู้ใช้บริการหรือบันทึกขอปรับลดหนี้ของหน่วยงานต้นเรื่อง
			                                                </label>
		                                                </div>
			                                       		<span class="btn btn-default btn-file" id="docAdjOriginateFileButton">
													    Browse <input type="file" name="file" id="docAdjOriginateFile">
													    	
														</span>
														<span id="docAdjOriginateFileName" ></span>
														<span id="docAdjOriginateFileMsg" style="display:inline-block;"></span>
		                                       		</div>
		                                   </div>
	                                   </form>
	                                   <form method="POST" enctype="multipart/form-data" id="docTerminateFileUploadForm">
		                                   <div class="form-group">
		                                       <div class="form-inline">
		                                           <div class="checkbox">
		                                               <label>
		                                                   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" id="checkDocTerminate" value="ADJ-02"> เอกสารการขอยกเลิกใช้บริการ/ กสท แจ้งปิดบริการ
		                                               </label>
		                                           </div>
		                                           &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		                                           <span class="btn btn-default btn-file" id="docTerminateButton">
														    Browse <input type="file" name="file" id="docTerminateFile">
												   </span>
												   <span id="docTerminateFileName" ></span>
												   <span id="docTerminateFileMsg" style="display:inline-block;"></span>
												</div>
		                                   </div>
	                                   </form>
	                                   <form method="POST" enctype="multipart/form-data" id="docVerifyFileUploadForm">	
		                                   <div class="form-group">
		                                       <div class="form-inline">
		                                           <div class="checkbox">
		                                               <label>
		                                                   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" id="checkDocVerify" value="ADJ-03"> ผลตรวจสอบการใช้บริการของฝ่ายเทคนิค (ถ้ามี)
		                                               </label>
		                                           </div>
		                                           &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		                                           <span class="btn btn-default btn-file" id="docVerifyButton">
														    Browse <input type="file" name="file" id="docVerifyFile">
												   </span>
												   <span id="docVerifyFileName" ></span>
												   <span id="docVerifyFileMsg" style="display:inline-block;"></span>
		                                       </div>
		                                   </div>
	                                   </form>
	                                   <form method="POST" enctype="multipart/form-data" id="docAdjApvFileUploadForm">
	                                   <div class="form-group">
	                                       <div class="form-inline">
	                                           <div class="checkbox">
	                                               <label>
	                                                   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" id="checkDocAdjApv" value="ADJ-04"> บันทึกการอนุมัติให้ปรับลดหนี้ 
	                                               </label>
	                                           </div>
	                                           &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                                           <span class="btn btn-default btn-file" id="docAdjApvButton">
														    Browse <input type="file" name="file" id="docAdjApvFile">
											   </span>
											   <span id="docAdjApvFileName" ></span>
											   <span id="docAdjApvFileMsg" style="display:inline-block;"></span>
	                                       </div>
	                                   </div>
	                                   </form>
	                                   <form method="POST" enctype="multipart/form-data" id="docOtherFileUploadForm">
	                                   <div class="form-group">
	                                       <div class="form-inline">
	                                           <div class="checkbox">
	                                               <label>
	                                                   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" id="checkDocOther" value="ADJ-99"> อื่นๆ
	                                               </label>
	                                               <label >
	                                                  	โปรดระบุ&nbsp;&nbsp;<input class="form-control" value="" size="40" id="attDesc"/>
	                                           </label>
	                                           </div>
	                                           &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                                           <span class="btn btn-default btn-file" id="docOtherButton">
														    Browse <input type="file" name="file" id="docOtherFile">
											   </span>
											   <span id="docOtherFileName" ></span>
											   <span id="docOtherFileMsg" style="display:inline-block;"></span>
	                                       </div>
	                                        <div class="form-inline">
	                                          	
	                                        </div>
	                                   </div>
	                                   </form>
	                               </div>
	                           </div>
	                       </div>
	                   </div>
	                   <input type="button" value="Submit" id="btnSubmit" hidden="hidden"/>
					</form>
               </div>
           </div>	
         </div>
         <!--------------------------------------->
	    <ul id="panelNvigateAdjustSummary" class="list-inline pull-right list-menu-set">
	        <!-- <li><a id="selectConfirmAdjustReceiptList" class="btn btn-link"><span class="glyphicon glyphicon-th-list"></span> ดำเนินการขอปรับลดหนี้</a></li>  -->
	        <li><a href="#confirmAdjust" data-toggle="modal"><span class="glyphicon glyphicon-th-list"></span> ดำเนินการขอปรับลดหนี้</a></li>
	        <li style="padding-left:22px;"></li>
	    </ul>
	    <!--------------------------------------->
        
		<div class="hide row" id="panelAdjustSummary">
			<div class="col-md-12 tab-modefile">
					<div class="panel-heading">
	                    <h3 class="panel-title"><span class="glyphicon glyphicon-shopping-cart"></span> รายการขอปรับลดหนี้</h3>
	                </div>
					<table id="tableAdjustPaymentListSummary"
                          data-row-style="rowStyle"
                          data-toggle="table"
                          data-detail-view="true"
	                      data-detail-formatter="detailFormatterDisplay"
                          data-classes="table table-hover table-striped"
                          >
                       <thead>
                           <tr>
                            <th data-field="runningFormatter" data-align="center">#</th>
                            <th data-field="billRefNo" data-align="center">เลขที่ใบแจ้งค่าบริการ</th>
                            <th data-field="receiptNo" data-align="center">เลขที่ใบเสร็จรับเงิน</th>
                            <th data-field="accountNo" data-align="center">เลขที่ลูกค้า</th>
                            <th data-field="accountName" data-align="center">ชื่อลูกค้า</th>
		                    <th data-field="paymentCase" data-align="center">วิธีการชำระเงิน</th>
                            <th data-field="totalCharge" data-align="center">จำนวนเงิน</th>
                            <th data-field="adjustAmount" data-align="center">เงินขอปรับลด</th>
                            <th data-field="shopPaymentName" data-align="center">สถานที่รับชำระ</th>
                            <th data-field="paymentReceiver" data-align="center">ผู้รับชำระ</th>
                            <th data-field="reason" data-align="center">เหตุผลการขอปรับลดหนี้</th>
                           </tr>
                       </thead>
                     <!--   <tbody>
                           <tr>
                               <td>1</td>
                               <td>255900001</td>
                               <td>EP0171701F150714000020</td>
                               <td>200006599</td>
                               <td>นาย วสันตืชาย วงค์คำเดือน</td>
	                           <td>เงินสด</td>
                               <td>1,070.00</td>
                               <td>800.00</td>
                               <td>ศบล.แจ้งวัฒนะ</td>
                               <td>EPIS2016</td>
                               <td>คิดค่าบริการผิด และ ขอปรับลด</td>
                        	</tr>
                       </tbody>  -->
                   </table>
			</div>
		</div>
		<div class="modal fade" role="dialog" id="confirmAdjust">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title"><span class="glyphicon glyphicon-warning-sign"></span> ข้อความแจ้งเตือน</h4>
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
												ยืนยันขอปรับลดหนี้</label>
												
										</div>
									</div>
								</div>
								<div class="col-md-2"></div>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<!-- <a id="adjustReceiptList" href="adjust-payment_4.jsp" class="btn btn-success"><span class="glyphicon glyphicon-ok-circle"></span> ตกลง</a>  -->
						<button id="selectConfirmAdjustReceiptList" type="button" class="btn btn-success" data-dismiss="modal">
		                <span class="glyphicon glyphicon-ok-circle"></span> ตกลง
		            </button>&nbsp;&nbsp;
						<a class="btn btn-danger" data-dismiss="modal"><span class="glyphicon glyphicon-remove-circle"></span> ยกเลิก</a>
					</div>
				</div>
		</div>
	</div>
		<!--------------------------------------->
	    <ul id="panelNvigateAdjustSummaryResult" class="list-inline pull-right list-menu-set">
	        <li><a id="addAdjustReceiptList" href="adjust-payment_1.jsp" class="btn btn-link"><span class="glyphicon glyphicon-arrow-left"></span> กลับไปขอปรับลดหนี้</a></li>
	        <li style="padding-left:22px;"></li>
	    </ul>
	    <!--------------------------------------->
		<div class="hide row" id="panelAdjustSummaryResult">
			<div class="col-md-12 tab-modefile">
					<div class="panel-heading">
	                    <h3 class="panel-title"><span class="glyphicon glyphicon-shopping-cart"></span> ผลของการขอปรับลดหนี้</h3>
	                </div>
					<table id="tableAdjustPaymentListSummaryResult"
                          data-row-style="rowStyle"
                          data-toggle="table"
                          data-detail-view="true"
	                      data-detail-formatter="detailFormatterDisplay"
                          data-classes="table table-hover table-striped"
                          >
                       <thead>
                           <tr>
                            <th data-field="runningFormatter" data-align="center">#</th>
                            <th data-field="amountAdjustNo" data-align="center">เลขที่ขอปรับลดหนี้</th>
                            <th data-field="billRefNo" data-align="center">เลขที่ใบแจ้งค่าบริการ</th>
                            <th data-field="receiptNo" data-align="center">เลขที่ใบเสร็จรับเงิน</th>
                            <th data-field="custNo" data-align="center">เลขที่ลูกค้า</th>
                            <th data-field="custName" data-align="center">ชื่อลูกค้า</th>
                            <th data-field="payMethod" data-align="center">วิธีการชำระเงิน</th>
                            <th data-field="totalAmt" data-align="center">จำนวนเงิน</th>
                            <th data-field="adjustAmt" data-align="center">เงินขอปรับลด</th>
                            <th data-field="branch" data-align="center">สาขาที่รับชำระ</th>
                            <th data-field="userName" data-align="center">ผู้รับชำระ</th>
                            <th data-field="status" data-align="center">สถานะ</th>
                           </tr>
                       </thead>
                  <!--      <tbody>
                           <tr>
                               <td>1</td>
                               <td>EP0171701AJ50714001</td>
                               <td>255900001</td>
                               <td>EP0171701F150714000020</td>
                               <td>200006599</td>
                               <td>นาย วสันตืชาย วงค์คำเดือน</td>
                               <td>เงินสด</td>
                               <td>1,070.00</td>
                               <td>800.00</td>
                               <td>ศบล.แจ้งวัฒนะ</td>
                               <td>EPIS2016</td>
                               <td><span class="glyphicon glyphicon-ok-circle"></span> รอผลการอนุมัติ</td>
                        	</tr>
                       </tbody>  -->
                   </table>
			</div>
		
    </section>
    <!------------------------->
    <div class="modal fade" role="dialog" id="CustomerSearch">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title"><span class="glyphicon glyphicon-user"></span> ค้นหาลูกค้า</h4>
                </div>
                <div class="modal-body">
                	<div id="advanceSearchMessageDialog"></div>
                    <div class="tab-modefile">
                        <!-- Nav tabs -->
                        <ul class="nav nav-tabs" role="tablist">
                            <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">เลขที่ใบแจ้งค่าบริการ</a></li>
                            <li role="presentation"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">หมายเลขบริการ</a></li>
                            <li role="presentation"><a href="#messages" aria-controls="messages" role="tab" data-toggle="tab">ข้อมูลลูกค้า</a></li>
                        </ul>

                        <!-- Tab panes -->
                        <div class="panel panel-default">
                            <div class="panel-body">
                                <div class="tab-content border-tab-content">
                                    <div role="tabpanel" class="tab-pane active" id="home">
                                        <div class="form-inline">
                                            <div class="form-group">
                                                <label>เลขที่ใบแจ้งค่าบริการ :</label>
                                                <div class="input-group">
                                                    <input type="text" id="advSrcBillNo" class="form-control" >
                                                    <span class="input-group-btn">
                                                        <button id="advSrcBillNoBtn" type="button" class="btn btn-info"><span class="glyphicon glyphicon-search"></span> ค้นหา</button>
                                                    </span>
                                                </div><!-- /input-group -->
                                            </div>
                                        </div><br>
                                        
                                        <div class="row">
                                            <div class="col-md-12">
                                                <table id="advSrcBillNoResultList" data-toggle="table" data-pagination="true" data-page-size="5" data-page-list="[5, 10, 20, 50, 100, 200]" data-cache="false">
                                                    <thead>
                                                        <tr>
                                                            <th data-formatter="runningFormatter">#</th>
                                                    		<th data-field="invoiceNo">เลขที่ใบแจ้งค่าบริการ</th>
                                                    		<th data-field="billingNo">เลขที่ลูกค้า</th>
                                                    		<th data-field="billingName">ชื่อลูกค้า</th>
                                                        <!--     <th>ประเภทบริการ</th> -->
                                                            <th data-field="billGroup">Billing Group</th>
                                                    		<th data-align="center" data-formatter="SelectButton"></th>
                                                        </tr>
                                                    </thead>
                                               <!--      <tbody>
                                                        <tr>
                                                            <td>1</td>
                                                            <td>255900001</td>
                                                            <td>700005673</td>
                                                            <td>บริษัท AEC จำกัด</td>
                                                            <td>Inmarsat-C</td>
                                                            <td>LN</td>
                                                            <td><button type="button" class="btn btn-success btn-xs">เลือก</button></td>
                                                        </tr>
                                                        <tr>
                                                            <td>2</td>
                                                            <td>255900002</td>
                                                            <td>700005675</td>
                                                            <td>บริษัท พี เอ็น แท้งก์เกอร์จำกัด</td>
                                                            <td>Inmarsat-C</td>
                                                            <td>LN</td>
                                                            <td><button type="button" class="btn btn-success btn-xs">เลือก</button></td>
                                                        </tr>
                                                        <tr>
                                                            <td>3</td>
                                                            <td>255900099</td>
                                                            <td>700005675</td>
                                                            <td>บริษัท พี เอ็น แท้งก์เกอร์จำกัด</td>
                                                            <td>Inmarsat-C</td>
                                                            <td>LN</td>
                                                            <td><button type="button" class="btn btn-success btn-xs">เลือก</button></td>
                                                        </tr>
                                                    </tbody>  -->
                                                </table>
                                            </div>
                                        </div>
                                    </div>

                                    <div role="tabpanel" class="tab-pane" id="profile">
                                        <div class="form-horizontal">
                                            <div class="form-group">
                                                <label class="control-label col-sm-2" >ประเภทบริการ :</label>
                                                <div class="col-sm-4">
                                                    <select id="advSrcSvcType" class="form-control"></select>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-sm-2" >หมายเลขบริการ :</label>
                                                <div class="col-sm-4">
                                                    <div class="input-group">
                                                        <input id="advSrcSvcNo" type="text" class="form-control" >
                                                        <span class="input-group-btn">
                                                            <button id="advSrcSvcNoBtn" type="button" class="btn btn-info"><span class="glyphicon glyphicon-search"></span> ค้นหา</button>
                                                        </span>
                                                    </div><!-- /input-group -->
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-sm-2" ></label>
                                                <div class="col-sm-4">
                                                    <label class="checkbox-inline"> <input type="checkbox" name="advSrcSvcProperty" value="1"> Property1</label>
                                                	<label class="checkbox-inline"> <input type="checkbox" name="advSrcSvcProperty" value="2"> Property2</label>
                                                </div>
                                            </div>
                                        </div><br>
                                        

                                                <div class="row">
                                                    <div class="col-md-12">
                                                 		<table id="advSrcSvcNoResultList" name="tableproperty" class="table table-hover" data-toggle="table" data-pagination="true" data-page-size="5" data-page-list="[5, 10, 20, 50, 100, 200]" data-cache="false">
                                                			<thead>
				                                                <tr>
				                                                    <th data-formatter="runningFormatter">#</th>
				                                                    <th data-field="propertyOne">หมายเลขบริการ<br>(Property One)</th>
				                                                    <th data-field="propertyTwo">Property Two</th>
				                                                    <th data-field="catBillAcctNo">เลขที่ลูกค้า</th>
				                                                    <th data-field="customerName">ชื่อลูกค้า</th>
				                                                    <th data-field="billingGroupFull">Billing Group</th>
				                                                    <th data-align="center" data-formatter="SelectButton"></th>
				                                                </tr>
                                                			</thead>
                                            			</table>
                                                    </div>
                                                </div>
                                    </div>

                                    <div role="tabpanel" class="tab-pane" id="messages">
                                        <div class="form-horizontal">
											<div class="form-group">
												<label class="control-label col-sm-3">เลขที่ลูกค้า :</label>
												<div class="col-sm-3"><input class="form-control" id="advSrcCusNo"></div>
												<label class="control-label col-sm-3">เลขประจำตัวผู้เสียภาษี :</label>
												<div class="col-sm-3"><input class="form-control" id="advSrcCusTaxId"></div>
											</div>
											<div class="form-group">
												<label class="control-label col-sm-3">ชื่อลูกค้า :</label>
												<div class="col-sm-3"><input class="form-control" id="advSrcCusFirstName"></div>
												<label class="control-label col-sm-3">นามสกุล :</label>
												<div class="col-sm-3"><input class="form-control" id="advSrcCusLastName"></div>
											</div>
											<div class="form-group">
												<label class="control-label col-sm-3">ชื่อนิติบุคคล/ราชการ :</label>
												<div class="col-sm-6"><input class="form-control" id="advSrcOrgName"></div>
											</div>
											<div class="form-group">
												<label class="control-label col-sm-3">กลุ่มผู้ใช้บริการ :</label>
												<div class="col-sm-4">
													<select id="advSrcCusType" class="form-control" >
														<option value="" selected>กรุณาเลือกกลุ่มผู้ใช้บริการ</option>
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
												<div class="col-sm-5">
													<a id="advSrcCusNoBtn" class="btn btn-info pull-right"><span class="glyphicon glyphicon-search"></span> ค้นหา</a>
												</div>
											</div>
										</div>

                                        <br>

                                        <div class="row">
                                            <div class="col-md-12">
                                                <table class="table table-hover">
                                                    <thead>
                                                        <tr>
                                                            <th>#</th>
                                                            <th>เลขที่ลูกค้า</th>
                                                            <th>ชื่อลูกค้า</th>
                                                            <th>ประเภทบริการ</th>
                                                            <th>Billing Group</th> 
                                                            <th></th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <tr>
                                                            <td>1</td>
                                                            <td>700005673</td>
                                                            <td>บริษัท AEC จำกัด</td>
                                                            <td>Inmarsat-C</td>
                                                            <td>LN</td>
                                                            <td><button type="button" class="btn btn-success btn-xs">เลือก</button></td>
                                                        </tr>
                                                        <tr>
                                                            <td>2</td>
                                                            <td>700005675</td>
                                                            <td>บริษัท พี เอ็น แท้งก์เกอร์จำกัด</td>
                                                            <td>Inmarsat-C</td>
                                                            <td>LN</td>
                                                            <td><button type="button" class="btn btn-success btn-xs">เลือก</button></td>
                                                        </tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>

                                        
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

				<i class="pull-right"><span class="glyphicon glyphicon-filter"></span> สามารถใช้เครื่องหมาย ? แทนหนึ่งตัวอักษร หรือ *
						แทนหลายตัวอักษรในการค้นหา (กรุณาป้อนข้อมูลที่ต้องการค้นหาอย่างน้อย 4 ตัวอักษร)</i><br>
                </div>
				
            </div>
        </div>
    </div>
   
<!-- 
<div class="tab-content">
						<div role="tabpanel" class="tab-pane active" id="tab-2-2">
<table id="tableAdjustPaymentListTest" data-row-style="rowStyle" data-detail-view="true" data-detail-formatter="detailFormatter" data-classes="table table-hover table-striped" class="table table-hover table-striped">
	                       <thead style=""><tr><th class="detail" rowspan="1"><div class="fht-cell"></div></th><th style="" data-field="billRefNo"><div class="th-inner sortable both">เลขที่ใบแจ้ง<br>ค่าใช้บริการ</div><div class="fht-cell"></div></th><th style="" data-field="issueDt"><div class="th-inner sortable both">วันจัดทำใบแจ้ง<br>ค่าใช้บริการ</div><div class="fht-cell"></div></th><th style="text-align: right; " data-field="dueDt"><div class="th-inner sortable both">วันครบ<br>กำหนด</div><div class="fht-cell"></div></th><th style="text-align: right; " data-field="charge"><div class="th-inner sortable both">ยอดก่อน<br>ภาษี</div><div class="fht-cell"></div></th><th style="text-align: right; " data-field="discount"><div class="th-inner sortable both">เงิน<br>ส่วนลด</div><div class="fht-cell"></div></th><th style="text-align: right; " data-field="vat"><div class="th-inner sortable both">ภาษีมูลค่า<br>เพิ่ม</div><div class="fht-cell"></div></th><th style="text-align: right; " data-field="totalCharge"><div class="th-inner sortable both">ยอดเงิน<br>รวมภาษี</div><div class="fht-cell"></div></th><th style="text-align: right; " data-field="balanceDue"><div class="th-inner sortable both">จำนวนเงิน<br>คงค้าง</div><div class="fht-cell"></div></th><th style="text-align: right; " data-field="totalCharge"><div class="th-inner sortable both">ยอดเงิน<br>ที่ต้องชำระ</div><div class="fht-cell"></div></th><th style="text-align: right; " data-field="deduction"><div class="th-inner sortable both">ภาษีหัก<br>ณ ที่จ่าย</div><div class="fht-cell"></div></th><th style="text-align: center; " data-field="billCycle"><div class="th-inner ">รอบการใช้งาน</div><div class="fht-cell"></div></th><th style="text-align: center; " data-field="adjustAmount"><div class="th-inner ">เงินขอปรับลด</div><div class="fht-cell"></div></th></tr></thead>  
	                   <tbody><tr data-index="0"><td><a class="detail-icon" href="javascript:"><i class="glyphicon glyphicon-plus icon-plus"></i></a></td><td style="">200173785</td><td style="">2010-12-01</td><td style="text-align: right; ">2010-12-21</td><td style="text-align: right; ">0.00</td><td style="text-align: right; ">78.12</td><td style="text-align: right; ">14.06</td><td style="text-align: right; ">214.94</td><td style="text-align: right; ">0.00</td><td style="text-align: right; ">214.94</td><td style="text-align: right; ">0.00</td><td style="text-align: center; ">01/10/2010 - 31/10/2010</td><td style="text-align: center; "><div class="input-group"><span class="input-group-addon" id="basic-addon1" style="padding:2px 4px;">฿</span><a href="#" value="0.00" row-index="0" id="received_0" data-type="text" data-field="adjustAmount" data-name="adjustAmount" data-value="0.00">0.00</a></div></td></tr></tbody></table>
</div>
</div>  -->
<!--<p>TABLE 2</p>-->
<!--  <div id="tableContainer">
    <table id="detailTable" class="table table-striped table-bordered" style="width:90% !important">
    	<thead>
			<tr>
				<th data-field="productCode" class="text-center">รหัสผลิตภัณฑ์</th>
				<th class="text-center">ชื่อผลิตภัณฑ์</th>
				<th class="text-center">ผลิตภัณฑ์ย่อย</th>
				<th class="text-center">ชื่อผลิตภัณฑ์ย่อย</th>
				<th class="text-center">ประเภทรายได้</th>
				<th class="text-center">จำนวนเงิน</th>
				<th data-field="adjustAmount" data-align="center"  class="text-center" data-formatter="moneyInputFormatter" >เงินขอปรับลด</th>

			</tr>
		</thead>
    </table>
</div>  -->
<div id="tableContainer">
<table id="detailTable" class="table table-striped table-bordered" >
	<thead>
          <tr>
          <th data-field="productCode" class="text-center">รหัสผลิตภัณฑ์</th>
          </tr>
      </thead>
</table>
</div>
<script id="template" type="x-tmpl-mustache">
	<table id="billProduct"  class="table table-striped table-bordered" style="width:100% !important">
		<thead>
			<tr>
				<th class="text-center">รหัสผลิตภัณฑ์</th>
				<th class="text-center">ชื่อผลิตภัณฑ์</th>
				<th class="text-center">ผลิตภัณฑ์ย่อย</th>
				<th class="text-center">ชื่อผลิตภัณฑ์ย่อย</th>
				<th class="text-center">ประเภทรายได้</th>
				<th class="text-center">รหัสศูนย์ต้นทุน</th>
				<th class="text-center">จำนวนเงิน</th>
				<th class="text-center">เงินขอปรับลด</th>

			</tr>
		</thead>
		<tbody>
		{{#invoices}}
			<tr>
			<td class="text-center">{{productCode}}</td>
			<td class="text-center">{{productName}}</td>
			<td class="text-center">{{subProductCode}}</td>
			<td class="text-center">{{subProductName}}</td>
			<td class="text-center">{{revTypeCode}}</td>
			<td class="text-center"><input fieldvalue="" value="{{costCenter}}" rowindex="{{contrno}}:{{billRefNo}}:{{productCode}}:{{subProductCode}}:{{revTypeCode}}:{{accountCodeNew}}:{{tradingPart}}:{{postingDate}}:{{region}}" id="costCenter" fieldname="costCenter" data-field="costCenter" style="width:100px" class="form-control text-right" onkeyup="if (event.which == 13) this.blur()" onblur="(function(o){ updateCellCostCenter($(o));})(this)"></td>
			<td class="text-right">{{amount}}</td>
			<td ><div class="input-group text-right"><span class="input-group-addon" id="basic-addon1" style="padding:2px 4px;">฿</span><input fieldvalue="0.00" value="{{adjustAmount}}" rowindex="{{contrno}}:{{billRefNo}}:{{productCode}}:{{subProductCode}}:{{revTypeCode}}:{{accountCodeNew}}:{{tradingPart}}:{{postingDate}}:{{region}}" id="adjustAmountProd" fieldname="adjustAmount" data-field="adjustAmount" maxlength="10" style="width:100px" class="form-control text-right adjustAmount" onkeypress="return $.isNumeric(String.fromCharCode(event.which)) || (event.which == 46 &amp;&amp; this.value.indexOf('.') == -1)" onkeyup="if (event.which == 13) this.blur()" onfocus="(function(o){ o.value = o.value.replace(/[,]/g, ''); o.select() })(this)" onblur="(function(o){ o.value = parseFloat(o.value, 10).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,'); console.log(' o.value >> '+ o.value+' , value >> '+value);updateCellProduct($(o)); (window[table.id +'InputBlurEvent'] || function(t){ console.log('table >> '+t);updateTotalAdjustAmt($(o));})(table) })(this)"></div></td>
			</tr>
		{{/invoices}}
		</tbody>
	</table>
	</script>
<script id="templateDisplay" type="x-tmpl-mustache">
	<table id="billProductDisplay"  class="table table-striped table-bordered" style="width:100% !important">
		<thead>
			<tr>
				<th class="text-center">รหัสผลิตภัณฑ์</th>
				<th class="text-center">ชื่อผลิตภัณฑ์</th>
				<th class="text-center">ผลิตภัณฑ์ย่อย</th>
				<th class="text-center">ชื่อผลิตภัณฑ์ย่อย</th>
				<th class="text-center">ประเภทรายได้</th>
				<th class="text-center">รหัสศูนย์ต้นทุน</th>
				<th class="text-center">จำนวนเงิน</th>
				<th class="text-center">เงินขอปรับลด</th>

			</tr>
		</thead>
		<tbody>
		{{#invoices}}
			<tr>
			<td class="text-center">{{productCode}}</td>
			<td class="text-center">{{productName}}</td>
			<td class="text-center">{{subProductCode}}</td>
			<td class="text-center">{{subProductName}}</td>
			<td class="text-center">{{revTypeCode}}</td>
			<td class="text-center">{{costCenter}}</td>
			<td class="text-right">{{amount}}</td>
			<td class="text-right">{{adjustAmount}}</td>	
			</tr>
		{{/invoices}}
		</tbody>
	</table>
</script>
</body>
<script type="text/javascript">
    function detailFormatterDemo(val, row, ind) {
    	return    	'<table class="table table-striped table-bordered">'+
				    	'<thead>'+
				    		'<tr>'+
				    		'<th class="text-center">รายการ</th>'+
				    		'<th class="text-center">จำนวนเงิน</th>'+
				    		'<th class="text-center">ภาษีมูลค่าเพิ่ม</th>'+
				    		'<th class="text-center">ส่วนลด</th>'+
				    		'<th class="text-center">จำนวนเงินทั้งสิ้น</th>'+
				    		'</tr>'+
				    	'</thead>'+
				    	'<tbody>'+
				    		'<tr><td class="text-center">Inv No. 200058589 : Sub No. 864460704</td><td class="text-center">1,000.00</td><td class="text-center">70.00</td><td class="text-center">0.00</td><td class="text-center">1,070.00</td></tr>'+
				    	'</tbody>'+
			    	'</table>';	
    }
var view = (function($){
	var self = this; 
	self.session = function(key, val) { if (!val) { var val = window.sessionStorage.getItem(key); return val && (val.indexOf("{") > -1 || val.indexOf("[") > -1) ? JSON.parse(val) : val } window.sessionStorage.setItem(key, ($.type(val) != "object" && $.type(val) != "array" ? val : JSON.stringify(val)))	};
	self.utils = {
		guid: function(){ function r() { return Math.floor((Math.random() + 1) * 0x10000).toString(16).substring(1) } return r() + r() +"-"+ r() +"-"+ r() +"-"+ r() +"-"+ r() + r() + r() },
		numberFormat: function(num, dec) { return $.type(num) !== "number" ? "0.00" : num.toFixed(dec || 2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,'); },
		dateFormat: function() { var obj = arguments[0]; if ($.type(obj) == "string" && /[0-9]{2}.[0-9]{2}.[0-9]{4}/g) { return obj } else if ($.isNumeric(obj)) { var dt = new Date(obj), dd = dt.getDate().toString(), mm = (dt.getMonth() + 1).toString(), yyyy = dt.getFullYear().toString(); return (dd[1]?"":"0") + dd +"/"+ (mm[1]?"":"0") + mm +"/"+ yyyy } else if ($.type == "date") { return "" } return "" },
		dateTimeFormat: function() { var obj = arguments[0], dt = $.type(obj) != "date" ? ($.isNumeric(obj) ? new Date(obj) : new Date()) : new Date(), dd = dt.getDate().toString(), MM = (dt.getMonth() + 1).toString(), yyyy = dt.getFullYear().toString(), hh = dt.getHours().toString(), mm = dt.getMinutes().toString(), ss = dt.getSeconds().toString(); return (dd[1]?"":"0") + dd +"/"+ (MM[1]?"":"0") + MM +"/"+ yyyy +" "+ (hh[1]?"":"0") + hh +":"+ (mm[1]?"":"0") + mm +":"+ (ss[1]?"":"0") + ss },
		queryString: function() { var pos = location.href.indexOf("?"), obj = {}; if (pos < 0) return obj; var params = location.href.slice(pos + 1).split("&"); for (var i = 0, m = params.length; i < m; i++) { pos = params[i].indexOf("="); if (pos < 0) { obj[params[i]] = true; continue; } obj[params[i].substring(0, pos)] = params[i].substring(pos + 1) } return obj },
		window: function(windowName, url, side) { window.windowOpened = (window.windowOpened || {}); var screenProp = ""; if (side == "left") { screenProp = "left=0,top=0,width="+ (screen.width/2) +",height="+ (screen.height-100) } else if (side == "right") { screenProp = "left="+ (screen.width/2-40) +",top=0,width="+ (screen.width/2+40) +",height="+ (screen.height-100) } window.windowOpened[windowName] = window.open(url, windowName, "menubar=no,scrollbars=no,resizable=0,"+ screenProp, false); return window.windowOpened }
	};  	
   	window.runningFormatter = function(val, row, ind) { return ind + 1 }
   	window.get = function(url, params, success, msgDialog, preCheck){ $.ajax({ "url": url, "type": "GET", "data": params, "error": (msgDialog || { "valid": function(jqXHR, textStatus, errorThrow){ console.log(jqXHR); console.log("textStatus: "+ textStatus); console.log("errorThrow:"); console.log(errorThrow) } }).valid, "success": function(res){ var isNotJson = res.constructor == String; console.log(res); (preCheck || function(o){})(res); if (isNotJson) res = { "statusCode": "99", "errorList": ["No matching data found."] }; if (msgDialog && !msgDialog.valid(res)) { return; } success(res) } }) }
	window.post = function(url, params, success, msgDialog, preCheck){ $.ajax({ "url": url, "type": "POST", "data": params, "error": (msgDialog || { "valid": function(jqXHR, textStatus, errorThrow){ console.log(jqXHR); console.log("textStatus: "+ textStatus); console.log("errorThrow:"); console.log(errorThrow) } }).valid, "success": function(res){ var isNotJson = res.constructor == String; console.log(res); (preCheck || function(o){})(res); if (isNotJson) res = { "statusCode": "99", "errorList": ["No matching data found."] }; if (msgDialog && !msgDialog.valid(res)) { return; } success(res) } }) }
	window.stripToNumber = function(str) { return $.type(str) === "string" && $.isNumeric(str = str.replace(/[,]/g, "")) ? parseFloat(str, 10) : 0 }
	window.toDateString = function(ddMMyyyy){ return ddMMyyyy.replace(/(\d{2}).(\d{2}).(\d{4})/g, "$2/$1/$3") }
	window.runningFormatter = function(val, row, ind) { return ind + 1 }
	window.numberFormatter = function(val, row, ind) { return !$.isNumeric(stripToNumber(val)) ? "0.00" : parseFloat(val, 10).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,') }
	window.dateFormatter = function(val, row, ind){ if (!val) return ""; if ((/(\d{4}).(\d{2}).(\d{2}).*/g).test(val)) return val.replace(/(\d{4}).(\d{2}).(\d{2}).*/g, "$3/$2/$1"); return val }
	window.timeFormatter = function(val, row, ind){ if (!val) return ""; if ((/.*T(\d{2}).(\d{2}).(\d{2}).*/g).test(val)) return val.replace(/.*T(\d{2}).(\d{2}).(\d{2}).*/g, "$1:$2:$3"); return val }
	window.dateTimeFormatter = function(val, row, ind){ if (!val) return ""; if ((/(\d{4}).(\d{2}).(\d{2})T(\d{2}).(\d{2}).(\d{2}).*/g).test(val)) return val.replace(/(\d{4}).(\d{2}).(\d{2})T(\d{2}).(\d{2}).(\d{2}).*/g, "$3/$2/$1 $4:$5:6"); return val }
	window.stringInputFormatter = function(val, row, ind) { return '<input value="'+ $.trim(val) +'" maxLength="40" class="form-control">' }
	window.numberInputFormatter = function(val, row, ind) { return '<input value="'+ self.utils.numberFormat(parseFloat(val || "0.00", 10)) +'" maxLength="10" style="width:100px" class="form-control text-right" onkeypress="return $.isNumeric(String.fromCharCode(event.which)) || (event.which == 46 && this.value.indexOf(\'.\') == -1)" onfocus="(function(o){ o.value = o.value.replace(/[,]/g, \'\') })(this)" onblur="(function(o){ o.value = parseFloat(o.value, 10).toFixed(2).replace(/(\\d)(?=(\\d{3})+\\.)/g, \'$1,\'); })(this)">' }
	window.modifyButtonFormatter = function(val, row, ind) { return '<a class="btn btn-link modList" onclick=\'(function(elem, row, ind){ var table = $("#"+ $(elem).closest("table")[0].id); window[table[0].id +"ModifyEvent"](table, row, ind) })(this, '+ JSON.stringify(row) +', '+ ind +')\'><span class="glyphicon glyphicon-pencil"></span></a>' }
	window.removeButtonFormatter = function(val, row, ind) { return '<a class="btn btn-link delList" onclick=\'(function(elem, row, ind){ $("#"+ $(elem).closest("table")[0].id).bootstrapTable("remove", { field: "index", values: [ind] }) })(this, '+ JSON.stringify(row) +', '+ ind +')\'><span class="glyphicon glyphicon-trash"></span></a>' }
	window.LoadingPanel = function(id) { var obj = this, loadCnt = 0, setupFunc, loadFunc; function checkElemReady(){if ((obj.elem = $("#"+ id).css("font", "")).length != 1) { alert("The element hasn't insert into HTML DOM."); throw "The element hasn't insert into HTML DOM."; } clearTimeout(setupFunc); clearInterval(loadFunc) }; return { "elem": null, "finish": function(html){ checkElemReady(); (this.elem ? this.elem : this.elem = $("#"+ id)).css("font", "").html(html); return this }, "toString": function(){ var elem; setupFunc = setTimeout(function(){ loadFunc = setInterval(function(){ (elem || (elem = document.getElementById(id))).innerHTML = "Loading"+ Array(++loadCnt).join(".."); if (loadCnt > 60) loadCnt = 2 }, 250) }, 1000); return "<div id='"+ id +"' style='font:BOLD 16pt Arial'></div>" } } }
	//window.moneyInputFormatter = function(val, row, ind) { var value = parseFloat(val || "0.00", 10).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, "$1,"); var currencySymbol = "฿"; if (row!=null){var currencySymbol = chkCurrencySymbol(row.currencyCode); console.log("symbol is >>>>>> "+currencySymbol)};return '<div class="input-group"><span class="input-group-addon" id="basic-addon1" style="padding:2px 4px;">' + currencySymbol + '</span><input value="'+ value +'" id="received_'+ind+'"  maxLength="10" style="width:100px" class="form-control text-right" onkeypress="return $.isNumeric(String.fromCharCode(event.which)) || (event.which == 46 && this.value.indexOf(\'.\') == -1)" onkeyup="if (event.which == 13) this.blur()" onfocus="(function(o){ o.value = o.value.replace(/[,]/g, \'\'); o.select() })(this)" onblur="(function(o){ o.value = parseFloat(o.value, 10).toFixed(2).replace(/(\\d)(?=(\\d{3})+\\.)/g, \'$1,\'); o.style.backgroundColor = o.value !== \''+ value +'\' ? \'yellow\' : \'\'; var table, elem = o; while (table == null) { elem = elem.parentNode; if (elem.tagName == \'TABLE\') table = elem; } (window[table.id +\'InputBlurEvent\'] || function(t){ console.log(t) })(table) })(this)"></div>' }
	//window.moneyInputFormatter = function(val, row, ind) { var value = parseFloat(val || "0.00", 10).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, "$1,"); var currencySymbol = "฿"; if (row!=null){var currencySymbol = chkCurrencySymbol(row.currencyCode); console.log("symbol is >>>>>> "+currencySymbol)};return '<div class="input-group"><span class="input-group-addon" id="basic-addon1" style="padding:2px 4px;">' + currencySymbol + '</span><input fieldValue="'+ value+ '" value="'+value+'" rowIndex=' + ind +' id="received_'+ind+'" fieldName="adjustAmount" data-field="adjustAmount"  maxLength="10" style="width:100px" class="form-control text-right adjustAmount" onkeypress="return $.isNumeric(String.fromCharCode(event.which)) || (event.which == 46 && this.value.indexOf(\'.\') == -1)" onkeyup="if (event.which == 13) this.blur()" onfocus="(function(o){ o.value = o.value.replace(/[,]/g, \'\'); o.select() })(this)" onblur="(function(o){ o.value = parseFloat(o.value, 10).toFixed(2).replace(/(\\d)(?=(\\d{3})+\\.)/g, \'$1,\'); o.style.backgroundColor = o.value !== \''+ value +'\' ? \'yellow\' : \'\'; var table, elem = o; while (table == null && elem != null) {console.log(\'elem > \'+elem); console.log(\' elem.tagName >> \'+elem.tagName) ;elem = elem.parentNode;console.log(\'elem (2) >>\'+elem); if (elem!= null && elem.tagName == \'TABLE\') table = elem; console.log(\'elem (exit) >>\'+elem) } (window[table.id +\'InputBlurEvent\'] || function(t){ console.log(\'table >> \'+t) })(table) })(this)" onchange="updateCell($(this))"></div>' }
	window.moneyInputFormatter = function(val, row, ind) { var value = parseFloat(val || "0.00", 10).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, "$1,"); var currencySymbol = "฿"; if (row!=null){var currencySymbol = chkCurrencySymbol(row.currencyCode); console.log("symbol is >>>>>> "+currencySymbol)};return '<div class="input-group"><span class="input-group-addon" id="basic-addon1" style="padding:2px 4px;">' + currencySymbol + '</span><input fieldValue="'+ value+ '" value="'+value+'" rowIndex=' + ind +' id="received_'+ind+'" fieldName="adjustAmount" data-field="adjustAmount"  maxLength="10" style="width:100px" class="form-control text-right adjustAmount" onkeypress="return $.isNumeric(String.fromCharCode(event.which)) || (event.which == 46 && this.value.indexOf(\'.\') == -1)" onkeyup="if (event.which == 13) this.blur()" onfocus="(function(o){ o.value = o.value.replace(/[,]/g, \'\'); o.select() })(this)" onblur="(function(o){ o.value = parseFloat(o.value, 10).toFixed(2).replace(/(\\d)(?=(\\d{3})+\\.)/g, \'$1,\'); console.log(\' o.value >> \'+ o.value+\' , value >> \'+value);updateCell($(o)); (window[table.id +\'InputBlurEvent\'] || function(t){ console.log(\'table >> \'+t) })(table) })(this)" ></div>' } //onchange="updateCell($(this))"
   	function AuthenticationDialog(succFunc, failFunc, url, title, usrLabel, pwdLabel, okBtn, cnBtn) { var obj = this, httpUrl = url, done = succFunc, fail = failFunc, cancel = function(){}, inputs; obj.el = "modal_authentication";
		var content = '<div class="row"><div class="col-md-12"><div class="col-md-offset-2 col-md-10"><div class="form-horizontal"><div class="form-group"><label class="col-sm-3 control-label">'+ (usrLabel || "User Name :") +'</label><div class="col-sm-6"><input type="text" id="userName" class="form-control"></div></div><div class="form-group"><label class="col-sm-3 control-label">'+ (pwdLabel || "Password :") +'</label><div class="col-sm-6"><input type="password" id="passAuthen" class="form-control"></div></div></div></div></div></div>';
		var hdrCS = '<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>';
		var hdrTT = '<h4 class="modal-title"><span class="glyphicon glyphicon-user"></span> '+ (title || "Authentication") +'</h4>';
		var divMG = '<div class="alert alert-danger hide"></div>';
		var btnOK = '<button type="button" class="btn btn-success"><span class="glyphicon glyphicon-ok-circle"></span> '+ (okBtn || "OK") +'</button>';
		var btnCN = '<button type="button" class="btn btn-danger" data-dismiss="modal"><span class="glyphicon glyphicon-remove-circle"></span> '+ (cnBtn || "Cancel") +'</button>';
        obj.elem = $(document.body).append('<div class="modal fade" role="dialog" data-backdrop="static" id="'+ obj.el +'"><div class="modal-dialog"><div class="modal-content"><div class="modal-header">'+ hdrCS + hdrTT +'</div><div class="modal-body">'+ divMG + content +'</div><div class="modal-footer">'+ btnOK + btnCN +'</div></div></div></div>').find("#"+ obj.el); inputs = obj.elem.find("input").keyup(function(e){ var key = (e.which || e.keyCode || e.charCode || 0); if (key != 13) return true; obj.elem.find("input, button").eq(e.target.type == "text" ? 2 : 3).focus(); return true }); buttons = obj.elem.find("button");
		buttons.eq(1).on({ click: function(){ view.input.userAuthen.val($("#userName").val()); console.log($("#userName").val()+" <<<>>> "+inputs.eq(0).val()+" <<<>>> "+inputs.eq(1).val()); $.post(httpUrl, { username: inputs.eq(0).val(), password: inputs.eq(1).val() }, function(res){ if (!res || res.statusCode != "0") { obj.elem.find(".alert").text("You don't have permission to access this feature. Please contact to Administration for more information.").removeClass("hide"); fail(res); return; } done(res); obj.hide() }) } }).end().filter(function(index){ return index == 0 || index == 2 }).on({ click: function(){ cancel() } })
		obj.url = function(url){ httpUrl = url; return this }; obj.show = function(){ obj.elem.modal("show"); obj.elem.find("div.alert").addClass("hide").text(""); inputs.val(""); return this }; obj.hide = function(){ obj.elem.modal("hide"); return this }
		obj.done = function(func) { done = func; return this }; obj.fail = function(func) { fail = func; return this }; obj.cancel = function(func) { cancel = func; return this }
	}
	function Breadcrumb(el) { var obj = this; obj.el = el; obj.elem = $(el == null ? ".breadcrumb" : el); var list = obj.elem.find("li").each(function(i,o){ $(o).data("index", i) }), index = list.filter(".active").data("index");
		obj.index = function(){ if(arguments.length == 1) { list.removeClass("active").eq(index = arguments[0]).addClass("active"); return obj } return index }
		obj.next = function(){ obj.index(++index); return this }; obj.prev = function(){ obj.index(--index); return this }
	}
	function BootstrapTable(el, options) { var obj = this, rawData = [], successFunc = function(){}, checkEvt = function(e){ console.log(e) }, uncheckEvt = checkEvt; obj.el = el; obj.elem = $(el).bootstrapTable(options = $.extend({ uniqueId: "id", responseHandler: function(res){ if (!res) return []; if (res.constructor === Array) return res; if (!res.data || res.data.constructor !== Array) return []; return res.data }, onLoadSuccess: function(res){ rawData = res; obj.resetView(400); successFunc(res) }, onCheck: window[el.substring(1) +"CheckEvent"], onUncheck: window[el.substring(1) +"UncheckEvent"], onCheckAll: window[el.substring(1) +"CheckAllEvent"], onUncheckAll: window[el.substring(1) +"UncheckAllEvent"] }, options));
		obj.clear = function() { obj.elem.bootstrapTable("removeAll"); return obj }; obj.remove = function(index){ obj.elem.bootstrapTable("remove", { field: "index", values: [index] }); return this }; obj.resetView = function(ms){ setTimeout(function(){ obj.elem.bootstrapTable("resetView") }, ms || 200); return this }; obj.isEmpty = function(){ return obj.data().length == 0 }
		obj.showLoad = function() { this.elem.bootstrapTable("showLoading"); return this }; obj.hideLoad = function() { this.elem.bootstrapTable("hideLoading"); return this };
		obj.update = function(rec){ var id = rec.id, index = rec.index, row; if ((row = obj.getId(id)) != null) { obj.elem.bootstrapTable("updateRow", { "index": row.index, "row": $.extend(row, rec) }) } else if ((row = obj.data()[index]) != null) { obj.elem.bootstrapTable("updateRow", { "index": index, "row": $.extend(row, rec) }) } else obj.insert(rec); return this }; obj.insert = function(row) { var data = obj.elem.bootstrapTable("getData"); obj.elem.bootstrapTable("insertRow", { "index": data.length, "row": $.extend(row, { "index": data.length }) }); return this }
		obj.find = function(field, value) { var data = obj.elem.bootstrapTable("getData"); return $.map(data, function(o,i){ return o[field] === value ? o : null }) }
		obj.map = function(col) { var resultMap = {}; $.each(obj.data(), function(ind, row){ resultMap[row[col]] = row }); return resultMap }
		obj.rawData = function() { if (arguments.length == 1) { this.elem.bootstrapTable("load", rawData = arguments[0]); return this } return $.map(this.elem.bootstrapTable("getData"), function(row){ return row }) }; obj.getSelections = function(){ return obj.elem.bootstrapTable("getAllSelections") }
		obj.data = function() { var data = []; obj.elem.find("tbody tr").each(function(i,o){ var row = o, record = []; for (var j = 0, n = row.childNodes.length; j < n; j++) { var col = row.childNodes[j], val = ""; if (col.childNodes.length != 1) val = ""; else if (col.childNodes[0].nodeType == 3) val = $.trim(col.childNodes[0].textContent); else if (col.childNodes[0].nodeName == "INPUT") { var elm = col.childNodes[0], isText = elm.type == "text"; val = $.trim(isText ? elm.value : ((elm.type == "checkbox" || elm.type == "radio") && elm.checked ? elm.value : "")) } else if (col.childNodes[0].nodeName == "DIV") val = $.trim(col.childNodes[0].childNodes[0].value); record.push(val) } data.push(record) }); return data };
		obj.selected = function() { var data = []; obj.elem.find("tbody tr").find("input[type=checkbox]:checked, input[type=radio]:checked").each(function(i,o){ var row = o.parentNode.parentNode, record = []; for (var j = 0, n = row.childNodes.length; j < n; j++) { var col = row.childNodes[j], val = ""; if (col.childNodes.length != 1) val = ""; else if (col.childNodes[0].nodeType == 3) val = $.trim(col.childNodes[0].textContent); else if (col.childNodes[0].nodeName == "INPUT") val = $.trim(col.childNodes[0].value); else if (col.childNodes[0].nodeName == "DIV") val = $.trim(col.childNodes[0].childNodes[0].value); record.push(val) } data.push(record) }); return data };
		obj.getId = function(id) { var o = obj.elem.bootstrapTable("getRowByUniqueId", id); if ($.type(o) == "array") return null; return o[options.uniqueId] == id ? o : null }; obj.delId = function(id) { obj.elem.bootstrapTable("removeByUniqueId", id); return this }; // options: { uniqueId: "fieldName" }
		obj.filter = function(func) { var filteredRow, filteredData = []; for (var i = 0, m = rawData.length; i < m; i++) { if (filteredRow = func(rawData[i])) filteredData.push(filteredRow) } obj.elem.bootstrapTable("load", filteredData); return this }
		obj.sum = function(isAll, colName) { var sum = 0; $.each(this.elem.bootstrapTable(isAll ? "getData" : "getSelections"), function(i,o){ sum += (o[colName] || 0) }); return sum };
		obj.sumInput = function(index) { var sum = 0; obj.elem.find("tbody tr").each(function(i,o){ var val = o.children[index].children[0].value.replace(/[,]/g, ""); sum += (!$.isNumeric(val) ? 0 : parseFloat(val, 10)) }); return sum }
		obj.http = function(url, urlParams, succFunc) { if (succFunc) successFunc = succFunc; obj.elem.bootstrapTable("refresh", { "url": url, "query": urlParams }); return this }
		obj.expand = function() { obj.elem.find(".detail-icon i.icon-plus").click(); return this }; obj.collapse = function() { obj.elem.find(".detail-icon i.icon-minus").click(); return this };
		obj.checkAll = function(){ var checked = arguments.length == 0 || arguments[0] == true || ($.type(arguments[0]) == "boolean" ? arguments[0] : arguments[0] == "true"), data = obj.elem.bootstrapTable("getData"); $.each(obj.elem.bootstrapTable("getOptions").columns[0], function(i, col){ if (col.checkbox) $.each(data, function(j, row){ row[col.field] = checked }) }); return this }; obj.uncheckAll = function(){ obj.checkAll(false); return this }
		obj.elem.on("check.bs.table uncheck.bs.table check-all.bs.table uncheck-all.bs.table", window[el.substring(1) +"CheckBoxEvent"])
	}
	function Button(el) { var obj = this, badge; obj.el = el; obj.elem = $(el);
		obj.hide = function() { this.elem.addClass("hide"); return this }; obj.show = function() { this.elem.removeClass("hide"); return this };
		obj.hideLoad = function(){ obj.elem.button("reset"); return this }; obj.showLoad = function(){ obj.elem.button("loading"); return this };
		obj.disable = function(flag) { if (flag == null || flag) this.elem.addClass("disabled").attr("disabled", true); else this.elem.removeClass("disabled").attr("disabled", false); return this }; obj.enable = function() { obj.disable(false); return this };
		obj.badge = function(val) { if (badge) badge.text(val) }; if ((badge = this.elem.next()).length == 0) badge = null;
		obj.elem.click(window[el.substring(1) +"ClickEvent"]);
	}
	function Panel(el, hide) { var obj = this, dura = 500, counter = 0, cntFunc; obj.el = el; obj.elem = $(el); if ($.inArray(obj.elem[0].tagName.toLowerCase(), ["ol", "ul"]) > -1) { obj.elem.removeAttr("id").css("display", "").removeClass("hide").removeClass("hidden"); obj.elem = obj.elem.wrap('<div id="'+ el.substring(1) +'"></div>').parent(); } var children = obj.elem.children(), bodyEl = el.substring(1) +'-body', body = obj.elem.append('<div style="'+ (hide == null || hide ? "display: none" : "") +'" id="'+ bodyEl +'"></div>').find("#"+ bodyEl).append(children), progressType, progress = obj.elem.append('<div class="progress" style="display: none"><div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width: 0%"><span class="sr-only">0% Complete</span></div></div>').find(".progress");
		obj.center = function(){ obj.elem.removeClass(["text-left", "text-right"]).addClass("text-center"); return this }; obj.right = function(){ obj.elem.removeClass(["text-left", "text-center"]).addClass("text-right"); return this }
		obj.hide = function(ms) { if (isHidden()) return this; body.hide(ms || dura); return this }; obj.show = function(ms) { if (!isHidden()) return this; if (!ms || !$.isNumeric(ms)) ms = dura; if (ms >= 0) body.css("display", "none").removeClass("hide").removeClass("hidden").show(ms); else obj.hide(Math.abs(ms)); return this }
		obj.slideDown = function(ms){ if (isHidden()) { body.css("display", "none").removeClass("hide").removeClass("hidden").slideDown(ms || dura, arguments[1] || function(){}) } return this }; obj.slideUp = function(ms){ if (!isHidden()) { body.slideUp(ms || dura, arguments[1] || function(){}) } return this }
		obj.progress = function(){ return { "elem": progress, "show": function(ms){ body.slideUp(ms || 10); progress.slideDown(); return this }, "hide": function(ms){ body.slideDown(ms || 10); progress.slideUp(); return this }
			,"success": function(){ return this.style("progress-bar-success") }, "info": function(){ return this.style("progress-bar-info") }, "warn": function(){ return this.style("progress-bar-warning") }, "error": function(){ return this.style("progress-bar-danger") }, "plain": function(){ return obj.style("") }, "style": function(clsName) { $(this.elem[0].childNodes[0]).removeClass(progressType).addClass(progressType = clsName); return this }
			,"percent": function(perc){ if (!$.isNumeric(perc) || (perc > 100 || perc < 0)) return this; this.elem[0].childNodes[0].style.width = perc +"%"; return this }
			,"timeCnt": function(seconds) { var o = this; this.percent(counter = 0); cntFunc = setInterval(function(){ o.percent(100/seconds * ++counter); if (seconds < counter) { clearInterval(cntFunc); o.hide(1000) } }, 1000); return this }
		} }; function isHidden() { return body.css("display") === "none" || body.hasClass("hide") || body.hasClass("hidden") }; obj.elem.css("display", "").removeClass("hide").removeClass("hidden");
	}
	function Message(el) { var obj = this, timeCnt = 0, loadFunc; obj.el = el; obj.elem = $(el);
		obj.hide = function() { obj.elem.addClass("hide"); return obj };
		obj.show = function(flag) { if (flag == null || flag) obj.elem.removeClass("hide"); else obj.hide(); return obj };
		obj.clear = function() { obj.elem.find("*").remove(); obj.hide(); return obj };
		obj.message = function(arr, cls) { $.each(arr, function(i,o) { var m = o; if ($.type(o) === "object") { m = (o.desc || o.messageDesc) +" [code="+ (o.code || o.messageCode) +"]" }; obj.elem.append('<div class="'+ cls +'">'+ m +'<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button></div>') }); return obj };
		obj.error = function(arr) { return obj.message(arr, "alert alert-danger") };
		obj.warn = function(arr) { return obj.message(arr, "alert alert-warning") };
		obj.success = function(arr) { return obj.message(arr, "alert alert-success") };
		obj.successMini = function(arr) { return obj.message(arr, "alert-success") };
		obj.valid = function(jqXHR, textStatus, errorThrow){ var res = jqXHR; obj.stopLoad(); if (jqXHR && jqXHR.responseJSON) res = jqXHR.responseJSON; if (res) { var isNoData = false; if (res.statusCode && res.statusCode != "0") { obj.warn(res && $.type(res.warningList) === 'array' ? res.warningList : []).error(res && $.type(res.errorList) === 'array' ? res.errorList : ["An error occurred on the server side but there is no error message returned."]).show(); return false } if (res.data && res.statusCode == '0' && res.data.length < 1) isNoData = true; if ($.type(res._embedded) === 'object' && $.map(res._embedded,function(v,k){return v}).length < 1) isNoData = true; if (isNoData) { obj.warn(["There is no records of data."]).show(); return false } return true } obj.error(["An error occurred on the server side but there is no error message returned."]).show(); return false }
		obj.hideLoad = function(){ obj.stopLoad().clear(); return this }; obj.stopLoad = function(){ clearInterval(loadFunc); return this }
		obj.showLoad = function(msg){ obj.elem.append('<div id="'+ obj.el +'-loading" class="alert alert-warning">'+ bind(msg, 0) +'</div>'); timeCnt = 0; var elem = document.getElementById(obj.el +"-loading"); loadFunc = setInterval(function(){ elem.innerHTML = bind(msg, ++timeCnt) }, 1000); obj.show(); return this }
		function bind(msg, timeCnt) { return msg.replace(/\{timeCnt\}/g, timeCnt) }
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
		obj.elem.keyup(function(e){ var func = (window[el.substring(1) +"KeyUpEvent"] || function(){}); func((e.which || e.keyCode || e.charCode || 0), obj.elem) }); if ($.isNumeric(maxLen)) { obj.elem.attr("maxLength", maxLen) }
	}

    function DropDown(el, url) { var obj = this, data = [{ key: "", value: "", text: "Please Select" }]; obj.el = el; obj.elem = $(el);
        obj.data = function(array) { if ($.type(array) == "array") { setup(data = array); return this } return data; };
        obj.init = function(url, v) {
            if (url) $.get(url, function(res)
            { setup(data = res.data, v);}); else setup(data, v); return this };
        obj.disable = function(flag) { obj.elem.attr("disabled", (flag == null ? true : flag)); return this }
        obj.enable = function() { obj.disable(false); return this }
        obj.index = function() { if (arguments.length == 1) { var maxLen = data.length, index = arguments[0]; obj.elem.find("option").each(function(i, opt){ opt.removeAttribute("selected") }).eq(index < 0 ? maxLen + index : index).attr("selected", true); return this } return $.inArray("selected", obj.elem.find("option").map(function(i, opt){ return opt.selected ? "selected" : "no-selected" })) }
        obj.selected = function(){ return data[obj.index()]; };
        obj.val = function() { return obj.elem.val(); };
        obj.key = function() { if (arguments.length == 0) { var key = obj.elem.find("option:selected").data("key"); if (typeof key === "string" && key === "undefined") { var item = data[obj.index()]; key = item && item.key ? item.key : item.id } return key } var key = arguments[0]; for (var i = 0, m = data.length; i < m; i++) { var item = data[i]; if (item.key && item.key == key) { obj.index(i); break; } if (item.id && item.id == key) { obj.index(i); break; } } return this }
        function setup(array, v) {
            obj.elem.find("*").remove();
            obj.elem.append('<option data-index="-1" data-key="-1" value="">กรุณาเลือก</option>');
            $.each(array,function(i,o){
             
                    obj.elem.append('<option data-index="'+ i +'" data-key="'+ o.key +'" value="'+ o.key +'">'+ o.value +'</option>')
            });
        }
        data = obj.elem.change(window[el.substring(1) +"ChangeEvent"]).find("option").map(function(i, opt){ return { key: opt.getAttribute("data-key"), value: opt.value, text: opt.textContent } });
    }
    
    function CheckBox(el) { var obj = this; obj.el = el; obj.elem = $(el); obj.elem.click(window[obj.elem.attr("name") +"ClickEvent"])
        obj.disable = function() { obj.elem.attr("disabled", arguments.length == 0 || (arguments.length == 1 && (arguments[0] == "true" || arguments[0] == true))); return this }
        obj.contains = function(val) { return $.inArray(val, obj.val()) > -1 };
        obj.val = function() { return obj.elem.filter(":checked").map(function(i,o){ return o.value }) };
        obj.unchecked = function(){ obj.elem.each(function(i,o){ o.checked = false }); return this }
    }
    
    function Listbox(el, url) {
    var obj = this, data = [ {
        key : "",
        value : "กรุณาเลือก"
    } ];
    obj.el = el;
    obj.elem = $(el);
    obj.data = function(array) {
        if ($.type(array) == "array") {
            setup(data = array);
            return this;
        }
        return data;
    };
    obj.init = function(url) {
        if (url)
            $.get(url, function(res) {
                data = res;
                if(res.data != null){
                	data = res.data;
                }
                
                data.splice(0, 0, {key : "", value : "กรุณาเลือก"});
                setup(data);
            });
        else
            setup(data);
        return this;
    };
    obj.index = function() {
        if (arguments.length == 1) {
            var maxLen = data.length, index = arguments[0];
            obj.elem.find("option").each(function(i, opt) {
                opt.removeAttribute("selected")
            }).eq(index < 0 ? maxLen + index : index).attr("selected", true);
            return this
        }
        return $.inArray("selected", obj.elem.find("option").map(
            function(i, opt) {
                return opt.selected ? "selected" : "no-selected"
            }))
    }
    obj.selected = function() {
        return data[obj.index()];
    };
    obj.val = function() {
        return obj.elem.val();
    };
    obj.key = function() {
        if (arguments.length == 0) {
            var key = obj.elem.find("option:selected").data("key");
            if (typeof key === "string" && key === "undefined") {
                var item = data[obj.index()];
                key = item && item.key ? item.key : item.id
            }
            return key
        }
        var key = arguments[0];
        for (var i = 0, m = data.length; i < m; i++) {
            var item = data[i];
            if (item.key && item.key == key) {
                obj.index(i);
                break;
            }
            if (item.id && item.id == key) {
                obj.index(i);
                break;
            }
        }
        return this
    }
    
    function setup(array) {
        obj.elem.find("*").remove();
        $.each(array, function(i, o) {
            obj.elem.append('<option data-index="' + i + '" data-key="'
                + o.key + '" value="' + o.key + '">' + (o.value)
                + '</option>')
        });
    }
    data = obj.elem.change(window[el.substring(1) + "OnChange"]).find("option")
        .map(function(i, opt) {
            return {
                key : opt.getAttribute("data-key"),
                value : opt.value,
                text : opt.textContent
            }
        });
    obj.error = function(flag) {
        if (flag == true) {
            obj.elem.parent().addClass("has-error");
            return true;
        } else if (flag == false) {
            obj.elem.parent().removeClass("has-error");
            return false;
        } else if (obj.val() == '') {
            obj.elem.parent().addClass("has-error");
            return true;
        } else {
            obj.elem.parent().removeClass("has-error");
            return false;
        }
    }
}
/*function Listbox(el, url) { var obj = this, data = []; obj.el = el; obj.elem = $(el);
    obj.data = function(array) { if ($.type(array) == "array") { setup(data = array); return this } return data; };
    obj.init = function(url) { if (url) $.get(url, function(res) { setup(data = res.data) }); else setup(data); return this };
    obj.index = function() { if (arguments.length == 1) { var maxLen = data.length, index = arguments[0]; obj.elem.find("option").each(function(i, opt){ opt.removeAttribute("selected") }).eq(index < 0 ? maxLen + index : index).attr("selected", true); return this } return $.inArray("selected", obj.elem.find("option").map(function(i, opt){ return opt.selected ? "selected" : "no-selected" })) }
    obj.selected = function(){ return data[obj.index()]; }; obj.val = function() { return obj.elem.val(); }; obj.key = function(){ if (arguments.length == 0) { var key = obj.elem.find("option:selected").data("key"); if (typeof key === "string" && key === "undefined") { var item = data[obj.index()]; key = item && item.key ? item.key : item.id } return key } var key = arguments[0]; for (var i = 0, m = data.length; i < m; i++) { var item = data[i]; if (item.key && item.key == key) { obj.index(i); break; } if (item.id && item.id == key) { obj.index(i); break; } } return this }
    function setup(array) {
    obj.elem.find("*").remove();
    obj.elem.append('<option data-index="-1" data-key="-1" value="-1">ทั้งหมด</option>');
    $.each(array,function(i,o){ obj.elem.append('<option data-index="'+ i +'" data-key="'+ o.key +'" value="'+ o.key +'">'+ (o.value) +'</option>') }); }
    data = obj.elem.change(window[el.substring(1) +"ChangeEvent"]).find("option").map(function(i, opt){ return { key: opt.getAttribute("data-key"), value: opt.value, text: opt.textContent } });
}*/
        
	self.dialog = new(function(){
		var that = this;
		that.advanceSearchMessageDialog = new Message("#advanceSearchMessageDialog");
		that.advSrcReceipt = new Modal("#advSrcReceipt");
		that.CustomerSearch = new Modal("#CustomerSearch");
		function Modal(el) {
			this.el = el;
			this.elem = $(el);
			this.hide = function() { this.elem.modal("hide") };
			this.show = function() { this.elem.modal("show") };
		}
	});
	self.radio = new(function(){
		var that = this;
		that.radioAdjustCondition = new Radio("[name=radioAdjustCondition]");
		that.radioAdjRefundType = new Radio("[name=radioAdjRefundType]");
		function Radio(el) {
			this.el = el;
			this.elem = $(el);
			this.label = function() { return this.elem.filter(":checked").data("label") };
			this.val = function() { var s = this.elem.filter(":checked"),val = s.val(); return !val ? s.data("label") : val };
		}
	});
	self.input = new(function(){
		var that = this;
		that.advSrcCusNo = new Input("#advSrcCusNo");
		that.advSrcTaxNo = new Input("#advSrcTaxNo");
		that.advSrcCusFirstName = new Input("#advSrcCusFirstName");
		that.advSrcCusLastName = new Input("#advSrcCusLastName");
		that.advSrcOrgName = new Input("#advSrcOrgName");
		that.custNo = new Input("#custNo");
		that.billNo = new Input("#billNo").nextFocus(self.buttonSearch);
        that.receiptNo = new Input("#receiptNo").nextFocus(self.buttonSearch);
        that.userAuthen = new Input("#userAuthen");
        that.custName = new Input("#custName");
        that.accountNo = new Input("#accountNo");
        that.accountType = new Input("#accountType");
        that.advSrcBillNo = new Input("#advSrcBillNo");
        that.advSrcSvcNo = new Input("#advSrcSvcNo");
        
        //Adjust Refund
        that.inputAccountName = new Input("#inputAccountName");
        that.inputAccountNo = new Input("#inputAccountNo");
        that.attDesc = new Input("#attDesc");
        
    });
	self.tab = new(function(){
		var that = this;
		that.advSrcReceiptTab = new Tab("#advSrcReceiptTab");
		function Tab(el) {
			var obj = this, index = 0, change = function(e){ console.log(e) };
			obj.el = el;
			obj.elem = $(el);
			obj.index = function() { if (arguments.length == 1) { index = arguments[0]; obj.elem.find("li").removeClass("active").find("a").eq(index).click(); return this } return index }
			obj.show = function(ind) { obj.elem.find("a").eq(ind).tab("show"); return this; }
			obj.change = function(func) { change = func; return this }
			obj.elem.find("a").each(function(i,o){ $(o).data("index", i) });
			obj.elem.find("li").each(function(i,o){ if ($(o).hasClass("active")) index = i; });
			obj.elem.on("show.bs.tab", function(e){ index = $(e.target).data("index"); change(e) });
		}
	});
	
	self.table = new(function(){
			var that = this;
			function Table(el) {
				var obj = this, headers = [], data = [], onApn = function() {}, onDel = onApn, loadCnt = 0, loadInt, loadFunc = function(){ obj.body.find("div#loading").html("Loading"+ Array(++loadCnt).join(".")); if (loadCnt > 8) loadCnt = 0; };
				var checkboxHtml = '<input type="checkbox" name="{field}" data-index="{index}" value="{value}">', radioHtml = '<input type="radio" name="{field}" data-index="{index}" value="{value}">';
				obj.el = el; obj.elem = $(el); obj.body = obj.elem.find("tbody");
				obj.onAppend = function(func) { onApn = func }; obj.onDelete = function(func) { onDel = func };
				obj.hideLoad = function() { obj.elem.find("tbody tr").remove(); clearInterval(loadInt); return obj };
				obj.showLoad = function(){ obj.elem.find("tbody").append("<tr><td colspan='"+ headers.length +"'><div id='loading' style='text-align:center;font:BOLD 16pt Arial'>Loading</div></td></tr>"); loadCnt = 0; loadInt = setInterval(loadFunc, 500); return obj };
				function reorder(index) { obj.body.find("tr").each(function(i,o){ $(o).find("td").eq(index).text(i + 1) }) }
				obj.insert = function(array, showRemove, attrs) {
					if (arguments.length < 1) array = $.map(headers,function(o){ return " " }); var b = obj.elem.find("tbody");
					b.append("<tr "+ $.map($.extend(attrs, {}), function(v, k){ return k +"='"+ v +"'" }).join(" ") +">"
							+ $.map(array, function(v,i){
								var field = headers[i].field, value = v;
								if (headers[i].runningNo) value = (obj.size() + 1);
								else if (headers[i].numberFormat) { value = !$.isNumeric(v) ? "0.00" : parseFloat(v, 10).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,'); }
								else if (headers[i].checkbox) value = replace(checkboxHtml, field, i, v);
								else if (headers[i].radio) value = replace(radioHtml, field, i, v);
								return '<td><div style="'+ (headers[i].align ? "text-align:"+ headers[i].align : "") +'">'+ value +'</div></td>' }).join("")
							+ (!showRemove ? "" : '<td style="width:40px;text-align:center"><a href="#" class="delList"><span class="glyphicon glyphicon-trash"></span></a></td>')
							+"</tr>"); return obj
				};
				obj.find = function(key, cri) { return obj.elem.find("tbody tr").filter("["+ key +"="+ cri +"]") }
				obj.clear = function() { obj.elem.find("tbody tr").remove(); return obj }
				obj.remove = function(index) { this.elem.find("tbody tr").eq(index).remove(); $.each(headers,function(i,o){ if (o.runningNo) reorder(i) }); }
				obj.data = function() { var data = [];
					if (arguments.length != 1) { var rows = obj.elem.find("tbody tr");
						for (var i = 0, m = rows.length; i < m; i++) { var row = []; for (var j = 0, n = rows[i].children.length; j < n; j++) row.push(rows[i].children[j].innerText); data.push(row); }
						return data;
					} for (var i = 0, m = (data = arguments[0]).length; i < m; i++) { obj.insert(data[i]); }
				};
				obj.size = function() { return obj.elem.find("tbody tr").length };
				obj.sum = function(index) { var sum = 0; this.elem.find("tbody tr").each(function(i, row){ var val = $(row).find("td").eq(index).text(); sum += (isNaN(val) ? 0 : parseFloat(val, 10)) }); return sum }
				$(obj.el).on("click", ".delList", function(){ $(this).parent().parent().remove(); reorder(); onDel(obj.data()) });
				obj.elem.find("thead th").each(function(i,o){ var elem = $(o);
					headers.push({ "field": elem.data("field"), "align": $.trim(elem.data("align")), "runningNo": elem.data("runningNo") === true, "numberFormat": elem.data("numberFormat") === true, "checkbox": elem.data("checkbox") === true, "radio": elem.data("radio") === true })
				}); function replace(str, field, index, value){ return str.replace("\{field\}", $.trim(field)).replace("\{index\}", index).replace("\{value\}", value) }
				if(obj.body.length < 1) { obj.elem.append("<tbody></tbody>"); obj.body = obj.elem.find("tbody") }
			}
		});
	self.checkbox = new(function(){
            var that = this;
            that.advSrcSvcProperty = new CheckBox("[name=advSrcSvcProperty]");
            that.splitReceiptDocument = new CheckBox("#splitReceiptDocument");
            that.receiptDocumentOnly = new CheckBox("#receiptDocumentOnly");
            that.receiptInvoiceDocumentOnly = new CheckBox("#receiptInvoiceDocumentOnly");
            that.checkDocAdjOriginate = new CheckBox("#checkDocAdjOriginate");
        });
	self.breadcrumb = new Breadcrumb();
	self.mainMessageDialog = new Message("#mainMessageDialog");
	
	self.fileMsgTest = new Message("#fileMsgTest");//For testing only
	self.panelNavigation1 = new Panel("#panelNavigation1");
	self.panelNavigation2 = new Panel("#panelNavigation2");
	self.panelAdjustReceiptList = new Panel("#panelAdjustReceiptList");
	self.panelAdjustReceiptList2 = new Panel("#panelAdjustReceiptList2");
	self.panelAdjustDocument = new Panel("#panelAdjustDocument");//For testing only
	self.panelAdjustSummary = new Panel("#panelAdjustSummary");
	self.panelNvigateAdjustSummary = new Panel("#panelNvigateAdjustSummary");
	self.panelNvigateAdjustSummaryResult = new Panel("#panelNvigateAdjustSummaryResult");
	self.panelAdjustSummaryResult = new Panel("#panelAdjustSummaryResult");
	
	
	self.panelCustomer = new Panel("#panelCustomer");
	self.buttonSearch = new Button("#search");
	self.buttonAddAdjustReceiptList = new Button("#addAdjustReceiptList");
	self.selectAdjustReceiptList = new Button("#selectAdjustReceiptList");
	self.selectConfirmAdjustReceiptList = new Button("#selectConfirmAdjustReceiptList");
	self.advSrcBillNoBtn = new Button("#advSrcBillNoBtn");
	
	self.tableAdjustPaymentList = new BootstrapTable("#tableAdjustPaymentList");
	self.tableAdjustPaymentList2 = new BootstrapTable("#tableAdjustPaymentList2");
	self.advSrcBillNoResultList = new BootstrapTable("#advSrcBillNoResultList");
	self.tableAdjustPaymentListSummary = new BootstrapTable("#tableAdjustPaymentListSummary");
	self.tableAdjustPaymentListSummaryResult = new BootstrapTable("#tableAdjustPaymentListSummaryResult");
	self.advSrcSvcNoResultList = new BootstrapTable("#advSrcSvcNoResultList");
	self.dialogAuthentication = new AuthenticationDialog().url("../checkAuthorize.json");
	
	self.inputAdjustReasonType = new Listbox("#inputAdjustReasonType").init("../MasterData/getGroupData?key=ADJUST_DEBT_REASON_TYPE");//.data([{ key: "", value: "- กรุณาเลือกระบุเหตุผลการยกเลิก -"}, { key: "wrongName", value: "ชื่อ-ที่อยู่ ไม่ถูกต้อง"},{ key: "wrongInvoice", value: "ผิดใบแจ้งค่าใช้บริการ" }]);
	self.advSrcSvcType = new Listbox("#advSrcSvcType").init("../findAllPrintingInvDisplay.json");
	
	self.panelSearchReceipt  = new Panel("#panelSearchReceipt", false);
	
	self.btnSubmit = new Button("#btnSubmit");//For testing only
	
	//Message for input adjust detail e.g. bank transfer account number
	self.confirmMessage = new Message("#confirmMessage");
	self.buttonConfirmAdjustCntn = new Button("#buttonConfirmAdjustCntn");
	
	self.inputAdjustRefundBankType = new Listbox("#inputAdjustRefundBankType").init("../MasterData/getGroupData?key=BANK_TYPE");
	
	//File Browse Message
	self.docAdjOriginateFileMsg = new Message("#docAdjOriginateFileMsg");
    self.docTerminateFileMsg = new Message("#docTerminateFileMsg");
    self.docVerifyFileMsg = new Message("#docVerifyFileMsg");
    self.docAdjApvFileMsg = new Message("#docAdjApvFileMsg");
    self.docOtherFileMsg = new Message("#docOtherFileMsg");
    
    return this;
})(jQuery);
function statusFormatter(val, row, ind) { return row.attributes.indexOf("R") > -1 ? "ยกเลิกสำเร็จ" : (row.attributes.indexOf("C") > -1 ? "ปกติ" : "รอหักล้าง") }
function chkCurrencySymbol(str){
        console.log("currency code: "+str);
        var url = "../getSymbolByCurrencyCode.json"; params = { "code": str };
        var symbol="฿";
        var data = [];
        console.log(url);
        console.log(params);
        // var exchangeRate = null;
        $.ajax({
            url: url,
            type: "GET",
            data: {
                "code": str
            },
            async: false,
            success: function(res) {
                window.getSymbolByCurrencyCodeObj = res.data[0];
                console.log(window.getSymbolByCurrencyCodeObj);
                symbol = window.getSymbolByCurrencyCodeObj.currencySymbol;
                symbol = symbol!=null?symbol:"฿"
            },
            error: function(){
                symbol = "฿"
            }
        });
        console.log("symbol =================> "+symbol);
        return symbol;
    }
function searchClickEvent(res){
	var billNo = view.input.billNo.val();
	var receiptNo = view.input.receiptNo.val();
	view.mainMessageDialog.clear();
	view.tableAdjustPaymentList.clear();
	view.tableAdjustPaymentList.showLoad();
	if (billNo.length < 4 && receiptNo.length < 4) {
		view.mainMessageDialog.error(["กรุณากรอกข้อมูลอย่างน้อย 4 ตัวอักษร"]).show();
		return;
	}
	
	var url = "../findInvoiceReceiptAdjustAmountList.json", params = {"no": receiptNo,"invNo": billNo,"payType":"SERVICE_CHARGE"};
	get(url, params, function(res){
	    console.log("DATA >> "+JSON.stringify(res));
		view.panelAdjustReceiptList.slideDown(500, function(){ view.panelNavigation1.slideDown() });
		var cancelList = (view.session("cancelList") || []);
		view.buttonAddAdjustReceiptList.disable();
        view.tableAdjustPaymentList.rawData(res.data);
		view.tableAdjustPaymentList.hideLoad();
	}, view.mainMessageDialog, function(){ view.mainMessageDialog.clear() });
}

function tableAdjustPaymentListCheckEvent() {
	var receiptList = view.tableAdjustPaymentList.getSelections();
	view.buttonAddAdjustReceiptList.disable(receiptList.length == 0 || receiptList[0]['statusCode'] == 'F' ); //รอหักล้าง
}
function tableAdjustPaymentListUncheckEvent() {
	var receiptList = view.tableAdjustPaymentList.selected();
	view.buttonAddAdjustReceiptList.disable(receiptList.length == 0);
}
var receiptSelect = {};
function addAdjustReceiptListClickEvent() {
	var receiptList = view.tableAdjustPaymentList.getSelections();
    receiptSelect = view.tableAdjustPaymentList.getSelections();
    console.log("----------------------------DATA--------------------");
    console.log(receiptList);
    console.log(receiptList[0].addrLine1);
	if (receiptList.length == 0) {
		view.mainMessageDialog.clear().error(["กรุณาเลือกรายการใบเสร็จก่อนทำการขอปรับลดหนี้"]).show();
		return false;
	}
	for (var i = 0, m = receiptList.length; i < m; i++) {
		if (receiptList[i].statusCode.indexOf("C") == -1) {
			view.mainMessageDialog.clear().error(["ระบบไม่อนุญาตให้เลือกรายการที่ยังไม่พร้อมทำการยกเลิก"]).show();
			return false;
		}
		if (receiptList[i].statusCode.indexOf("R") >= 0) {
			view.mainMessageDialog.clear().success(["รายการที่เลือกยกเลิกสำเร็จแล้ว"]).show();
			return false;
		}
	}

	view.tableAdjustPaymentList2.rawData(receiptList);
	
	var rows = $("#tableAdjustPaymentList2 > tbody > tr");
	rows.each(function(n, row){
		console.log("n = "+n +" , row = "+JSON.stringify(row));
	});
	
	view.breadcrumb.next();
	view.panelSearchReceipt.slideUp(400);
	view.panelNavigation1.slideUp(800);
	view.panelAdjustReceiptList.slideUp(1200);
	view.panelCustomer.slideDown(1600);
	view.input.custName.val(receiptList[0].accountName);
	view.input.accountNo.val(receiptList[0].accountNo);
	/*if(receiptList[0].accountType == 'INDIVIDUAL'){
		view.input.accountType.val("บุคคลทั่วไป");
	}else{
		view.input.accountType.val("นิติบุคคล");
	}*/
	view.input.accountType.val(receiptList[0].accountType);
	
	view.panelAdjustDocument.slideDown(1800);
	
	view.panelAdjustReceiptList2.slideDown(2000, function(){ view.panelNavigation2.slideDown(400) });
	
	
	return false;
}

function selectAdjustReceiptListClickEvent() {
	var receiptList = view.tableAdjustPaymentList2.getSelections();
    receiptSelect = view.tableAdjustPaymentList2.getSelections();
    var table=$("#tableAdjustPaymentList2");
    receiptList = table.bootstrapTable("getData");
    console.log("----------------------------DATA--------------------");
    console.log(receiptList);
    console.log("----------------------------DATA2--------------------");
    console.log(view.tableAdjustPaymentList2.data());
	if (receiptList.length == 0) {
		view.mainMessageDialog.clear().error(["กรุณาเลือกรายการใบเสร็จก่อนทำการขอปรับลดหนี้"]).show();
		return false;
	}
	for (var i = 0, m = receiptList.length; i < m; i++) {
		if (receiptList[i].statusCode.indexOf("C") == -1) {
			view.mainMessageDialog.clear().error(["ระบบไม่อนุญาตให้เลือกรายการที่ยังไม่พร้อมทำการยกเลิก"]).show();
			return false;
		}
		if (receiptList[i].statusCode.indexOf("R") >= 0) {
			view.mainMessageDialog.clear().success(["รายการที่เลือกยกเลิกสำเร็จแล้ว"]).show();
			return false;
		}
	}

	if (view.inputAdjustReasonType.index() == 0) {
		view.mainMessageDialog.clear().error(["กรุณาเลือกเหตุผลของการทำรายการก่อน"]).show();
		return false;
	}

	for (var i = 0; i < receiptList.length; i++) {
		console.log("(receiptList["+i+"] = "+ receiptList[i].adjustAmount);
		if (receiptList[i].adjustAmount == "0.00" || receiptList[i].adjustAmount == null) {
			view.mainMessageDialog.clear().error(["กรุณาเลือกกรอกจำนวนเงินขอปรับลด"]).show();
			return false;
		}	
	}

	view.mainMessageDialog.clear();
	//Set reason
	for (var i = 0; i < receiptList.length; i++) {
		receiptList[i]["reason"]=$("#inputAdjustReasonType :selected").html();
	}
	view.tableAdjustPaymentListSummary.rawData(receiptList);
	
	var rows = $("#tableAdjustPaymentListSummary > tbody > tr");
	rows.each(function(n, row){
		console.log("n = "+n +" , row = "+row);
	});
	
	view.breadcrumb.next();
	view.panelCustomer.slideUp(1400);
	view.panelNavigation2.slideUp(1500);
	view.panelAdjustReceiptList2.slideUp(1600);
	view.panelAdjustDocument.slideUp(1700);
	view.panelAdjustSummary.slideDown(2000, function(){ view.panelNvigateAdjustSummary.slideDown(400) });
	
	return false;
}

function selectConfirmAdjustReceiptListClickEvent() {
	
    var reason = $("#inputAdjustReasonType :selected").html();
    var adjReason = $("#inputAdjustReasonType :selected").val();

	//view.dialogAuthentication.show().done(function(){
		var adjustList = view.tableAdjustPaymentListSummary.rawData();
		
		//Prepare data
		var params = [];
		var data = new Map();
		var header = new Map();
		var receiptId = "";
		var APPROVE_WAITING = "01";
		var fields = ["adjustAmount","receiptId","paymentId","invoiceId","accountId","billCycle","billRefNo","totalCharge"];
		for (var i = 0, m = adjustList.length; i < m; i++) {
			var myParam = new Map();
			for(var j = 0 ; j < fields.length ; j++){
				console.log("field : "+fields[j]+">>"+adjustList[i][fields[j]]);
				
				if(fields[j] == "receiptId"){
					receiptId = adjustList[i][fields[j]];
				}
				
				if(fields[j] == "receiptId" || fields[j] == "accountId"){
					header[fields[j]] = adjustList[i][fields[j]];//Set data in header.
				}else {
					myParam[fields[j]] = adjustList[i][fields[j]];
				}
				
				//Set default approve amount for editing.
				if(fields[j] == "adjustAmount"){
					myParam["approveAmount"] = adjustList[i][fields[j]];
				}
			}
			
			myParam["adjustStatus"] = APPROVE_WAITING;
			
			params[i] = myParam;
			console.log("myParam : "+i+":"+JSON.stringify(myParam));
			console.log("params : "+i+":"+JSON.stringify(params));
		}
		console.log("receiptId : "+receiptId);
		header["adjustType"] = "01";//deduct debt tax
		if(receiptId == "" || receiptId == 0 || receiptId == "0" || receiptId == null || receiptId == undefined){
			header["adjustType"] = "02";//deduct credit note
		}
		
		console.log("adjustList >> "+JSON.stringify(adjustList));
		console.log("account no >> "+adjustList[0]["accountNo"]);
		if(adjustList != null && adjustList.length > 0){
			header["accountNo"] = adjustList[0]["accountNo"];
		}
		
		header["adjustStatus"] = APPROVE_WAITING ;
		header["adjustReason"] = adjReason;
		data["records"] = params ;
		data["header"] = header ;
		
		header["adjustConditionType"] = view.radio.radioAdjustCondition.val();
		header["adjustRefundType"] = view.radio.radioAdjRefundType.val();
		header["bankAcctName"] = view.input.inputAccountName.val();
		header["bankAcctNo"] = view.input.inputAccountNo.val();
		header["bankType"] = self.inputAdjustRefundBankType.val();
		
		//Prepare record detail
		var recordDetailList = [];
		
		$.each(invProductMap, function(k, v) {
			var dataRcDt = new Map();
			var valArr = k.split(":");
			dataRcDt["contrno"] =  valArr[0];
			dataRcDt["biRef"] = valArr[1];
			dataRcDt["prodCd"] = valArr[2];
			dataRcDt["subProdCd"] = valArr[3];
			dataRcDt["revTypeCode"] = valArr[4];
			dataRcDt["accountCodeNew"] = valArr[5];
			dataRcDt["costCenter"] = invCostCenterMap[k];
			dataRcDt["tradingPart"] = valArr[6];
			dataRcDt["postingDate"] = valArr[7];
			dataRcDt["region"] = valArr[8];
			dataRcDt["adjustAmount"] = v.replace(",","");//Remove comma from data.
			dataRcDt["approveAmount"] = v.replace(",","");//Remove comma from data.
			recordDetailList[recordDetailList.length] = dataRcDt ;
		});
		
		//Set invoiceid to adjust amount detail.
		for (var i = 0; i < adjustList.length; i++) {
			for(var j = 0 ; j < recordDetailList.length ; j++ ){
				if(adjustList[i]["billRefNo"] == recordDetailList[j]["biRef"]){
					recordDetailList[j]["invoiceId"] = adjustList[i]["invoiceId"];
					recordDetailList[j]["paymentId"] = adjustList[i]["paymentId"];
				}
			}
		}
		
		data["recordDetailList"] = recordDetailList ;
		
		var attMapList = [];
		var ki = 0 ;
		for(var key in attMap) {
			console.log("attMap >> "+JSON.stringify(attMap[key]));
			attMapList[ki] = attMap[key];
			ki++;
		}
		data["attMapList"] = attMapList ;
		
		console.log("data >> "+JSON.stringify(data));
		console.log("recordDetailList >> "+JSON.stringify(recordDetailList));
		console.log("-- Ready to post! --");
    	console.log(params);
		post("../createAmountAdjustment.json", data, function(res){
			console.log("selectConfirmAdjustReceiptListClickEvent return");
			console.log("response >> "+JSON.stringify(res));
			
			//Call create document
			if(res.statusCode == "0"){
				//Set result into table. 
				view.breadcrumb.next();
				view.panelNvigateAdjustSummary.slideUp(800);
				view.panelAdjustSummary.slideUp(900);
				view.panelNvigateAdjustSummaryResult.slideDown(1200);
				view.panelAdjustSummaryResult.slideDown(1300);
				view.tableAdjustPaymentListSummaryResult.rawData(res.data);
				
				//Call create report.
				var data = new Map();
				data["id"] = res.param["id"];
				console.log("response id >> "+res.param["id"]);
				$(document.body).append('<form action="../createAmountAdjustmentReport.json" method="post" target="_printForm" id="frmCreatePdf"></form>');
     			var form = document.getElementById('frmCreatePdf');
     			function input(n,v) { var input = document.createElement("input"); input.type = "hidden"; input.name = n; input.value = v; return input }
				form.appendChild(input('header["id"]', res.param["id"]));
				form.submit();
			}
			
		});
	//});
}


function updateCell(caller) {
  var table = caller.parents('table');
  
   var newData = {
   'rowIndex': caller.attr('rowIndex'), 'fieldName': 'adjustAmount', 'fieldValue': caller.val()
  };
  
  $('#tableAdjustPaymentList2').bootstrapTable('updateCell',newData);
}

function testClick(){
	var data = new Map();
	var header = new Map();
	data["header"] = header ;
	header["id"] = "3";
	$(document.body).append('<form action="../createAmountAdjustmentReport.json" method="post" target="_printForm"></form>');
     var form = document.forms[0]; function input(n,v) { var input = document.createElement("input"); input.type = "hidden"; input.name = n; input.value = v; return input }
     form.submit();
}

function detailFormatter55555(index, row, element) {
	var guid = view.utils.guid();
    var loadPanel = new LoadingPanel(guid);
	console.log('#tbl_'+row.billRefNo+", length > "+$('#tbl_'+row.billRefNo).length);
	if($('#tbl_'+row.billRefNo).length == 0){
		var $table = $('#detailTable').clone().prop({ id: "tbl_"+row.billRefNo});
		
		$.get("../findProduct.json", { "no": row.billRefNo,"source":"IBACSS" }, function(res){
			console.log("params : "+JSON.stringify(res));
			console.log('>>>> #tbl_'+row.billRefNo+", length > "+$table.length);
			console.log('table length : '+$('#detailTable').length);
			console.log("table clone data id : "+$table.attr('id'));
			$table.bootstrapTable({
	     		data: res.data
	 		});    		
		});
  		
  	}
  	loadPanel.finish($(element).html($table.show()));
  	return loadPanel;
}


function detailFormatter22(val, row, ind) {
        var guid = view.utils.guid();
        var loadPanel = new LoadingPanel(guid);
        var $table = $('#template').clone().prop({ id: "tbl_"+row.billRefNo});
        $.get("../findProduct.json", { "no": row.billRefNo,"source":"IBACSS" }, function(res){
            loadPanel.finish(Mustache.render($('#template').html(), { "invoices": $.map(res.data, function(o, i){
                return { "productCode": o.productCode, "productName": o.productName, "subProductCode": o.subProductCode, "subProductName": o.subProductName, "revTypeCode":o.revTypeCode , "revTypeName": o.revTypeName, "amount": view.utils.numberFormat(o.amount) }
            }) }));
        });
        return loadPanel;
 }
var invPanel = {};

var invProductMap = {};
var invCostCenterMap = {};
var totalMap = {};
function updateCellProduct(caller) {
  var index = caller.attr('rowIndex');
  var adjustAmt = caller.val();
  console.log("updateCellProduct>> adjust amount : "+adjustAmt+" , rowIndex : "+index+" , invProductMap["+index+"] = "+invProductMap[index]);
  var newData = {
   'rowIndex': caller.attr('rowIndex'), 'fieldName': 'adjustAmount', 'fieldValue': caller.val()
  };
  if(adjustAmt != "0.00" && adjustAmt != ""){
  	invProductMap[index] = adjustAmt;
  }else{
  	
  	return;
  }
  
  var curInv = index.split(":")[1];
  
  //Update total adjustment when invoice is the same.
  updateTotalAdjustment(curInv);
  console.log("updateCellProduct (2)>>invProductMap > "+JSON.stringify(invProductMap));
}

function updateTotalAdjustment(curInv){
  var total = 0.00 ;
  $.each(invProductMap, function(k, v) {
    var inv = k.split(":")[1];
    	console.log("inv = "+inv);
    	if(inv == curInv){
    		var amt = parseFloat(v.replace(/,/g,'')); 
    		console.log("amt = "+amt);
    		total += amt;
    	}
  });
  
  var adjustList = view.tableAdjustPaymentList2.rawData();
  for(var i = 0 ; i < adjustList.length ; i++ ){
  	if(adjustList[i]["billRefNo"] == curInv){
  		totalMap[i] = total;
  		break;
  	}
  }
  console.log("updateTotalAdjustment - > total >>  "+total);
  console.log("updateTotalAdjustment - > adjustList >>  "+JSON.stringify(adjustList));

}

function updateTotalAdjustAmt(caller){
	var index = caller.attr('rowIndex');	
	var curInv = index.split(":")[1];	
	var adjustList = view.tableAdjustPaymentList2.rawData();
	for(var i = 0 ; i < adjustList.length ; i++ ){
		if(adjustList[i]["billRefNo"] == curInv){
			var newData = {
	 				'rowIndex': ''+i, 'fieldName': 'adjustAmount', 'fieldValue': totalMap[i]
			};
	
			$('#tableAdjustPaymentList2').bootstrapTable('updateCell',newData);
			break;
		}
	}
}

function updateCellCostCenter(caller){
	var index = caller.attr('rowIndex');
	var costCenter = caller.val();
	invCostCenterMap[index] = costCenter;
}

function detailFormatter(val, row, ind) {
	return detailFormatterMain(val, row, ind,'#template');
}

function detailFormatterDisplay(val, row, ind) {
	return detailFormatterMain(val, row, ind,'#templateDisplay');
}

function detailFormatterMain(val, row, ind,templateName) {
        var guid = view.utils.guid();
        var loadPanel = new LoadingPanel(guid);
        //Check existing table id.
         console.log("detailFormatter >> row = "+JSON.stringify(row) +" , ind = "+ind);
        var $table = $(templateName).clone();
        $.get("../findProductList.json", { "billRefNo": row.billRefNo}, function(res){
        
        	console.log("initial res:"+ JSON.stringify(res));
            loadPanel.finish(Mustache.render($table.html(), { "invoices": $.map(res.data, function(o, i){
            	var index = o.contrno+":"+o.billRefNo+":"+o.productCode+":"+o.subProductCode+":"+o.revTypeCode+":"+o.accountCodeNew +":"+ o.tradingPart+":"+o.postingDate+":"+o.region;
            	console.log("invProductMap["+index+"] , previous value :"+ invProductMap[index]+" , invCostCenterMap["+index+"] , previous value :"+ invCostCenterMap[index]);
            	if(invCostCenterMap[index]== null){
            		invCostCenterMap[index] = o.costCenter ;
            	}
                return { "productCode": o.productCode, "productName": o.productName, "subProductCode": o.subProductCode, "subProductName": o.subProductName, "revTypeCode":o.revTypeCode , "revTypeName": o.revTypeName, "amount": view.utils.numberFormat(o.amount) , "billRefNo": o.billRefNo  , "adjustAmount": invProductMap[index]== null ? "0.00":invProductMap[index],"contrno":o.contrno , "accountCodeNew":o.accountCodeNew , "costCenter":invCostCenterMap[index]== null ? o.costCenter : invCostCenterMap[index] , "tradingPart":o.tradingPart , "postingDate":o.postingDate , "region":o.region}
            }) }));
        });
        return loadPanel;
}
 
 function advSrcBillNoBtnClickEvent() {
 	 view.dialog.advanceSearchMessageDialog.clear();
        if (view.input.advSrcBillNo.val().length < 6) {
            view.dialog.advanceSearchMessageDialog.error(["โปรดระบุ 'เลขที่ใบแจ้งค่าบริการ' อย่างน้อย 6 ตัวอักษร"]).show();
            return false;
        }
        else{
            var billNo = view.input.advSrcBillNo.val();
            var chs=0;
            for(var i=0; i<billNo.length; i++){
                var ch = billNo.charAt(i);
                if(Number(parseFloat(ch))==ch){chs+=1;}
            }
            if(chs<6){
                view.dialog.advanceSearchMessageDialog.error(["โปรดระบุ 'เลขที่ใบแจ้งค่าบริการ' มีตัวเลขอย่างน้อย 6 ตัว"]).show();
                return false;
            }
        }
        view.dialog.advanceSearchMessageDialog.clear();
        view.advSrcBillNoResultList.clear().showLoad();
        view.dialog.advanceSearchMessageDialog.clear().showLoad("ระบบกำลังรอข้อมูลจาก CRM : {timeCnt} วินาที")
        var dataSend = {
            "invNo": view.input.advSrcBillNo.val()
        };

        $.ajax({
            type: "GET",
            url: "../findInvoiceBillingWriteOffByNo.json",
            data: dataSend,
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            success:function(res){
                console.log("res -->"+JSON.stringify(res));
                view.advSrcBillNoResultList.hideLoad().rawData(res.data);
                view.dialog.advanceSearchMessageDialog.hideLoad();
            }
        });
 }
 
 function SelectButton(val, row, ind) {
        return "<a class='btn btn-success btn-xs' onclick='BillSelectButtonWithParamEvent("+ row.invoiceNo +","+row.billingNo+")'>เลือก</a>";
 }
 
 function BillSelectButtonWithParamEvent(billNo,acctNo) {
        view.input.billNo.val(billNo);
        view.search.click();
        view.dialog.CustomerSearch.hide();
  }
  
  jQuery(function ($) {
    $(document).on('click', 'input:radio[id^="radioAdjCndt"]', function (event) {
    	var radioAdjCtnVal = view.radio.radioAdjustCondition.val();
        console.log("radioAdjCtnVal : "+radioAdjCtnVal);
        if(radioAdjCtnVal == "02"){//คืนเงิน
        	$('#selectAdjustConditionModal').modal('show'); 
        	view.confirmMessage.clear();//clear error message
        	var radioAdjRefundVal = view.radio.radioAdjRefundType.val();
        	console.log("radioAdjRefundVal : "+radioAdjRefundVal);
        	if(radioAdjRefundVal == "02"){//โอน
        		showBankInfo();
        	}else{
        		self.inputAdjustRefundBankType.val("");
				view.input.inputAccountNo.val("");
				view.input.inputAccountName.val("");
				hideBankInfo();
        	}
        }
    });
  });
  
  var isClose = false;
  function closeModal(c){
		isClose = c;
  }

  //Check condition before close modal
  $('#selectAdjustConditionModal').on('hide.bs.modal', function(e){
	   	 console.log("hide modal>> isClose : "+isClose);
	     if(!isClose){
	     	e.preventDefault();
	    	e.stopImmediatePropagation();
	     	return false; 
	     }
  });	
  
function buttonConfirmAdjustCntnClickEvent(){
	console.log("buttonConfirmAdjustCntnClickEvent...start");
	var radioAdjRefundVal = view.radio.radioAdjRefundType.val();
	var radioAdjCtnVal = view.radio.radioAdjustCondition.val();
	var bankTypeVal = self.inputAdjustRefundBankType.val();
	var bankAcctNoVal = view.input.inputAccountNo.val();
	var bankAcctNameVal = view.input.inputAccountName.val();
	if(isClose || 
		( radioAdjRefundVal =="02" && radioAdjCtnVal =="02" 
			&& (
				bankTypeVal.length == 0 || bankAcctNoVal.length == 0
					||  bankAcctNameVal.length == 0 
				)
		)
	 ){
	 	console.log("buttonConfirmAdjustCntnClickEvent...show error message");
    	view.confirmMessage.clear();
		view.confirmMessage.error(["กรุณากรอกข้อมูลธนาคารให้ถูกต้อง"]).show();
		return;
	}else{
		isClose = true;
	}
}

function hideBankInfo(){
	$("#inputAccountName").hide();
   	$("#inputAccountNo").hide();
   	$("#labelAccountName").hide();
   	$("#labelAccountNo").hide();
   	$("#inputAdjustRefundBankType").hide();
   	$("#labelAdjustRefundBankType").hide();
}

function showBankInfo(){
	$("#inputAccountName").show();
    $("#inputAccountNo").show();
    $("#labelAccountName").show();
    $("#labelAccountNo").show();
    $("#inputAdjustRefundBankType").show();
    $("#labelAdjustRefundBankType").show();	
}

jQuery(function ($) {
    $(document).on('click', 'input:radio[id^="radioAdjRefundOptions"]', function (event) {
    	var radioAdjRefundVal = view.radio.radioAdjRefundType.val();
        console.log("radioAdjRefundVal : "+radioAdjRefundVal);
        if(radioAdjRefundVal == "02"){//โอน
        	//Show input text
        	showBankInfo();
        }else{
        	console.log("inputAdjustRefundBankType value (1) : "+self.inputAdjustRefundBankType.val());
			view.input.inputAccountNo.val("");
			view.input.inputAccountName.val("");
			document.getElementById('inputAdjustRefundBankType').selectedIndex = 0;
        	hideBankInfo();
        }
    });
});


$('#myFile').bind('change', function() {
  view.fileMsg.clear();
  var fileSize = this.files[0].size;
  $("#fileName").html("");
  if(fileSize > 5242880){
  	//Set error message
  	view.fileMsg.clear().error(["ระบบไม่อนุญาตให้ upload file ที่มีขนาดมากกว่า 5 MB"]).show();
  	$("#fileName").html(this.value);
  	this.value = "";
  	return false;
  }
  
  $("#fileName").html(this.value);
  
  //Auto upload file but dont insert detail into database.
  uploadFile();
  
  return true;	
});
  var uploadResult = null;
  function uploadFile(){
  	attMap = new Map();
  	var form = $('#fileUploadForm')[0];
    var data = new FormData(form);
    var dataMap = {};
    dataMap["attachPath"] = "ATT_ADJUST_PATH";
    data.append("headerId", "This is some extra data, testing");
    data.append("isTemp", "Y");
    var isSuccess = true;
    data.append("jsonData", JSON.stringify(dataMap));
    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: "../uploadFile",
        data: data,
        processData: false, //prevent jQuery from automatically transforming the data into a query string
        contentType: false,
        cache: false,
        timeout: 600000,
        success: function (retData) {
        	console.log("retData : "+ JSON.stringify(retData));
			var msgArr = ["OK"]
        	view.fileMsg.clear().success(msgArr).show();
        	attMap = retData.commonStatus.data[0];
        	console.log("attMap : "+ JSON.stringify(attMap));
        
        },
        error: function (e) {

            $("#result").text(e.responseText);
            console.log("ERROR : ", e);
            $("#btnSubmit").prop("disabled", false);
            isSuccess = false;

        }
    });
  }


var attMap = new Map();
//Disable file browse by default.
$('#docAdjOriginateFile').prop('disabled', true);
$('#docAdjOriginateFileButton').addClass('disabled');

//Disable file browse by default.
$('#docAdjOriginateFile').prop('disabled', true);
$('#docTerminateButton').addClass('disabled');

//Disable file browse by default.
$('#docVerifyFile').prop('disabled', true);
$('#docVerifyButton').addClass('disabled');

//Disable file browse by default.
$('#docAdjApvFile').prop('disabled', true);
$('#docAdjApvButton').addClass('disabled');

//Disable file browse by default.
$('#docOtherFile').prop('disabled', true);
$('#docOtherButton').addClass('disabled');

$('#attDesc').prop('disabled', true);
$('#attDesc').addClass('disabled');


//binds to onchange event of input field
$('#docAdjOriginateFile').bind('change', function() {
	uploadAttFile('#docAdjOriginateFile',this,"#docAdjOriginateFileName",view.docAdjOriginateFileMsg,"#docAdjOriginateFileUploadForm","#checkDocAdjOriginate");	
  	return true;	
});

//binds to onchange event of input field
$('#docTerminateFile').bind('change', function() {
	uploadAttFile('#docTerminateFile',this,"#docTerminateFileName",view.docTerminateFileMsg,"#docTerminateFileUploadForm","#checkDocTerminate");	
  	return true;	
});

//binds to onchange event of input field
$('#docVerifyFile').bind('change', function() {
	uploadAttFile('#docVerifyFile',this,"#docVerifyFileName",view.docVerifyFileMsg,"#docVerifyFileUploadForm","#checkDocVerify");	
  	return true;	
});

//binds to onchange event of input field
$('#docAdjApvFile').bind('change', function() {
	uploadAttFile('#docAdjApvFile',this,"#docAdjApvFileName",view.docAdjApvFileMsg,"#docAdjApvFileUploadForm","#checkDocAdjApv");	
  	return true;	
});

//binds to onchange event of input field
$('#docOtherFile').bind('change', function() {
	uploadAttFile('#docOtherFile',this,"#docOtherFileName",view.docOtherFileMsg,"#docOtherFileUploadForm","#checkDocOther");	
  	return true;	
});

//binds to onchange event of check box
$('#checkDocAdjOriginate').change(function () {
	setFileDetail('#docAdjOriginateFile','#docAdjOriginateFileButton',"#docAdjOriginateFileName",'#checkDocAdjOriginate',view.docAdjOriginateFileMsg);
});

//binds to onchange event of check box
$('#checkDocTerminate').change(function () {
    setFileDetail('#docTerminateFile','#docTerminateButton',"#docTerminateFileName",'#checkDocTerminate',view.docTerminateFileMsg);
});

//binds to onchange event of check box
$('#checkDocVerify').change(function () {
    setFileDetail('#docVerifyFile','#docVerifyButton',"#docVerifyFileName",'#checkDocVerify',view.docVerifyFileMsg);
});

//binds to onchange event of check box
$('#checkDocAdjApv').change(function () {
    setFileDetail('#docAdjApvFile','#docAdjApvButton',"#docAdjApvFileName",'#checkDocAdjApv',view.docAdjApvFileMsg);
});

//binds to onchange event of check box
$('#checkDocOther').change(function () {
    setFileDetail('#docOtherFile','#docOtherButton',"#docOtherFileName",'#checkDocOther',view.docOtherFileMsg);
    
    if ($('#checkDocOther').prop("checked")) {
    	$('#attDesc').prop('disabled', false);
		$('#attDesc').removeClass('disabled');
    }else{
    	$('#attDesc').prop('disabled', true);
		$('#attDesc').addClass('disabled');
		view.input.attDesc.val('');
    }
});

$("#docAdjOriginateFileMsg").bind('closed.bs.alert', function(){
        alert("Alert message box has been closed.");
});

//If check box is checked then enable Browse button otherwise disable and clear data.
function setFileDetail(fileId , fileButtinId , fileNameId , checkId , msgObj){
    if ($(checkId).prop("checked")) {
    	$(fileButtinId).removeClass('disabled');
    	$(fileId).prop('disabled', false);
    }else{
    	$(fileButtinId).addClass('disabled');
    	$(fileNameId).html("");
    	msgObj.hide();
    	$(fileId).prop('disabled', true);
    	//Delete data from map.
    	delete attMap[fileId];
    }
} 

function uploadAttFile(fileId , obj,fileName,fileMsg,fileUploadForm,checkId){
	var fileSize = obj.files[0].size;
	$(fileName).html("");
	if(fileSize > 5242880){
		//Set error message
  	  	fileMsg.clear().error(["ระบบไม่อนุญาตให้ upload file ที่มีขนาดมากกว่า 5 MB"]).show();
		$(fileName).html(obj.value);
  		obj.value = "";
  		
  		$('#docAdjOriginateFileMsg').on('closed.bs.alert', function () {
  			alert("test");
		});
  		
  		return false;
	}
	$(fileName).html(obj.value);
	
  	var form = $(fileUploadForm)[0];
    var data = new FormData(form);
    var dataMap = {};
    dataMap["attachPath"] = "ATT_ADJUST_PATH";
    dataMap["attachCode"] = $(checkId).val();
    console.log("checkDoc.value > "+$(checkId).val());
    data.append("headerId", "This is some extra data, testing");
    data.append("isTemp", "Y");
    var isSuccess = true;
    data.append("jsonData", JSON.stringify(dataMap));
    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: "../uploadFile",
        data: data,
        processData: false, //prevent jQuery from automatically transforming the data into a query string
        contentType: false,
        cache: false,
        timeout: 600000,
        success: function (retData) {
        	console.log("retData : "+ JSON.stringify(retData));
			var msgArr = ["OK"]
        	fileMsg.clear().successMini(msgArr).show();
        	attMap[fileId] = retData.commonStatus.data[0];
        	console.log("attMap : "+ JSON.stringify(attMap));
        
        },
        error: function (e) {

            $("#result").text(e.responseText);
            console.log("ERROR : ", e);
            $("#btnSubmit").prop("disabled", false);
            isSuccess = false;

        }
    });
	
}
</script>
</html>
