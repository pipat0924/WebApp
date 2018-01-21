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
        <link href="resources/css/MonthPicker.css" rel="stylesheet" type="text/css" />
        <link href="resources/css/jquery-ui.css" rel="stylesheet" type="text/css" />
        <script src="resources/jquery.min.js" type="text/javascript"></script>
   <!-- <script src="resources/js/jquery-1.12.1.min.js"></script>  -->
        <script src="resources/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="resources/bootstrap-table/src/bootstrap-table.js"></script>
        <script src="resources/js/bootstrap-datepicker.min.js"></script>
        <script src="resources/custom.js" type="text/javascript"></script>
        <script src="resources/js/mustache.min.js" type="text/javascript"></script>
        <script src="resources/js/jquery-ui.min.js" type="text/javascript"></script>
        <script src="resources/js/jquery.maskedinput.min.js" type="text/javascript"></script>
		<script src="resources/js/MonthPicker.js" type="text/javascript"></script>        
    </head>
    <body>
        <header class="header_page"></header>
        <section class="container-fluid menu">
            <!-- <button class="menu-toggle btn btn-sm btn-default" style="margin-bottom: 5px"><span class="glyphicon glyphicon-step-forward"></span></button> -->
            <%-- <%@include  file="menu.jsp" %> --%>
            <div class="row">
                <div class="col-md-12 tab-modefile">
                	<ol class="breadcrumb">
                        <li><i>อนุมัติการร้องขอปรับลดหนี้</i></li>
                        <li class="active">ค้นหาข้อมูลคำร้องขอปรับลดหนี้</li>
                        <li>รายละเอียดขอปรับลดหนี้</li>
                        <li>สรุปการอนุมัติปรับลดหนี้</li>
                        <li>ผลการอนุมัติปรับลดหนี้</li>
                    </ol>
                    <div id="mainMessageDialog"></div>
                    <!-- Nav tabs -->
                    <div id="panelAdjustAmountSearchList" class="display">
	                    <ul class="nav nav-tabs" role="tablist">
	                        <li role="presentation" class="active">
	                        	<a href="#tab_cus" aria-controls="tab_cus" role="tab" data-toggle="tab"><span class="glyphicon glyphicon-filter"></span> ค้นหาข้อมูล</a>
	                        </li>
	                    </ul>
	                    <div class="tab-content" >
	                        <div role="tabpanel" class="tab-pane active" id="tab_cus">
	                            <div class="panel panel-default panal-radius">
	                                <div class="panel-body">
	                                    <div class="row">
			                                <div class="col-md-12">
			                                    <div class="form-horizontal">
			                                        <div class="form-group">
			                                            <label class="control-label col-sm-2">เลขที่ใบแจ้งค่าบริการ  :</label>
			                                            <div class="col-sm-2">
			                                                <input id="invoiceNo" class="form-control" value="" disable>
			                                            </div>
			                                            <label class="control-label col-sm-2" >เลขที่ใบเสร็จรับเงิน :</label>
			                                            <div class="col-sm-2">
			                                                <input id="receiptNo" class="form-control" value="">
			                                            </div>
			                                            <div class="col-sm-4">
			                                            <button id="search" type="button" class="btn btn-primary" >
			                                            	<span class="glyphicon glyphicon-search"></span> ค้นหา</button>&nbsp;&nbsp;
			                                            <button type="button" class="btn btn-success"  data-toggle="modal" data-target="#CustomerSearch">
			                                            	<span class="glyphicon glyphicon-zoom-in"></span> ค้นหาเพิ่มเติม</button></div>
			                                        </div>
			                                        <div class="form-group">
			                                            <label class="control-label col-sm-2">เลขที่ขอปรับลดหนี้  :</label>
			                                            <div class="col-sm-2">
			                                                <input id="amountAdjustHeaderNo" class="form-control" value="" disable>
			                                            </div>
			                                            <label class="control-label col-sm-2" >เลขที่ลูกค้า :</label>
			                                            <div class="col-sm-2">
			                                                <input id="accountNo" class="form-control" value="">
			                                            </div>
			                                           <!--  <div class="col-sm-4"></div>
					                                    <input type="text" id="MonthFormat" class='Default' value='201512' style='width:150px;'>  -->
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
            <ul id="navigateAdjustAmountList" class="list-inline pull-right list-menu-set">
            	<li><a id="buttonAddAdjustReceiptAprvList" class="btn btn-link"><span class="glyphicon glyphicon-plus-sign"></span> เพิ่มรายการอนุมัติขอปรับลดหนี้</a></li>
                <!-- <li><a id="addAdjustReceiptList" href="approve-adjust-payment_2.jsp" class="btn btn-link"><span class="glyphicon glyphicon-plus-sign"></span> เพิ่มรายการอนุมัติขอปรับลดหนี้</a></li>  -->
                <!-- <li><a id="sumAppAdjustReceiptList" href="approve-adjust-payment_3.jsp" class="btn btn-link"><span class="glyphicon glyphicon-th-list"></span> สรุปรายการอนุมัติปรับลดหนี้</a><span class="badge badge_modefil">2</span></li>  -->
            </ul>
            <!--------------------------------------->
            <div class="row" id="panelAdjustAmountList">
	                <!-- Tab panes -->
	                	<div class="col-md-12 tab-modefile">
		                <div class="panel-heading">
		                    <h3 class="panel-title"><span class="glyphicon glyphicon-shopping-cart"></span> รายการขออนุมัติปรับลดหนี้</h3>
		                </div>
	                    <div role="tabpanel" class="tab-pane active" id="tab-2-1">
	                        <table id="tableAdjustAmountList"
	                               data-row-style="rowStyle"
	                               data-toggle="table"
	                               data-classes="table table-hover table-striped"
	                               >
	                            <thead>
	                                <tr>
	                                	<th data-field="checked"  data-radio="true"></th>
		                                <!-- <th data-field="runningFormatter" data-align="center">#</th>  -->
		                                <th data-field="running" data-formatter="runningFormatter" data-align="center">#</th>
		                                <th data-field="headerNo" data-align="center">เลขที่ขอปรับลดหนี้</th>
		                                <th data-field="billRefNo" data-align="center">เลขที่ใบแจ้งค่าบริการ</th>
		                                <th data-field="receiptNo" data-align="center">เลขที่ใบเสร็จรับเงิน</th>
		                                <th data-field="receiptDate" data-align="center">วันที่รับชำระ</th>
		                                <th data-field="accountNo" data-align="center">เลขที่ลูกค้า</th>
		                                <th data-field="custName" data-align="center">ชื่อลูกค้า</th>
		                                <th data-field="payMethod" data-align="center">วิธีการชำระเงิน</th>
		                                <th data-field="adjustAmount" data-align="center">ยอดเงิน</th>
		                                <th data-field="branchName" data-align="center">สถานที่รับชำระ</th>
		                                <th data-field="createUser" data-align="center">ผู้ขอปรับลด</th>
		                                <th data-field="createDate" data-align="center">วันขอปรับลด</th>
		                                <th data-field="status" data-align="center">สถานะ</th>
	                                </tr>
	                            </thead>
	                      <!--       <tbody>
	                                <tr>
	                                    <td><input type="radio" name="optradio"></td>
	                                    <td>1</td>
	                                    <td>EP0171701AJ50714001</td>
	                                    <td>255900001</td>
	                                    <td>EP0171701F150714000020</td>
	                                    <td>05/02/2016</td>
	                                    <td>200006599</td>
	                                    <td>นาย วสันตืชาย วงค์คำเดือน</td>
	                                    <td>เงินสด</td>
	                                    <td>1,070.00</td>
	                                    <td>ศบล.แจ้งวัฒนะ</td>
	                                    <td>EPIS2016</td>
	                                    <td>05/02/2016</td>
	                                    <td>รออนุมัติ</td>
		                            </tr>
		                            <tr>
	                                    <td><input type="radio" name="optradio"></td>
	                                    <td>2</td>
	                                    <td>EP0171701AJ50714002</td>
	                                    <td>200058590</td>
	                                    <td>EP0171701F150714000021</td>
	                                    <td>10/01/2016</td>
	                                    <td>200006599</td>
	                                    <td>นาย วสันตืชาย วงค์คำเดือน</td>
	                                    <td>เงินสด</td>
	                                    <td>1,070.00</td>
	                                    <td>ศบล.แจ้งวัฒนะ</td>
	                                    <td>EPIS2016</td>
	                                    <td>10/01/2016</td>
	                                    <td>รออนุมัติ</td>
		                            </tr>
	                            </tbody>
	                             -->
	                        </table>
	                    </div>
	                </div>
        </div>
    <div id="panelCustomer" class="hide row">
     	<div class="col-md-12 tab-modefile">
		    <ul class="nav nav-tabs" role="tablist">
			    <li role="presentation" class="active">
			    	<a href="#tab_cus" aria-controls="tab_cus" role="tab" data-toggle="tab"><span class="glyphicon glyphicon-user"></span>  ขอมูลลูกค้า</a>
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
		                                               <input id="accountNoDisp" class="form-control" disabled="">
		                                           </div>
		                                           <label class="control-label col-sm-1" >ชื่อลูกค้า :</label>
		                                           <div class="col-sm-3">
		                                               <input id="custNameDisp" class="form-control" disabled="">
		                                           </div>
		                                           <label class="control-label col-sm-2" >เลขที่คำร้อง :</label>
		                                           <div class="col-sm-2">
		                                               <input id="headerNoDisp" class="form-control" disabled="">
		                                           </div>
		
		                                       </div>
		                                       <div class="form-group">
		                                           <label class="control-label col-sm-2" >กลุ่มลูกค้า :</label>
		                                           <div class="col-sm-2">
		                                               <input id="accountTypeDisp" class="form-control" disabled="">
		                                           </div>
		                                           <label class="control-label col-sm-4" ></label>
		                                           <label class="control-label col-sm-2" >วันที่บันทึกคำร้อง :</label>
		                                           <div class="col-sm-2">
		                                               <div class="input-group">
		                                                   <input id="createDateDisp" class="form-control" disabled="">
		                                                   <span class="input-group-btn">
		                                                       <button class="btn btn-default" type="button"><span class="glyphicon glyphicon-calendar"></span></button>
		                                                   </span>
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
    </div> 
    <ul id="panelNavigateEdit" class="list-inline pull-right list-menu-set">
        <li><a id="addAppAdjustReceiptList" href="approve-adjust-payment_1.jsp" class="btn btn-link"><span class="glyphicon glyphicon-arrow-left"></span> กลับไปเพิ่มรายการ</a></li>
        <li><a id="selectApvAdjustAmountReason" href="#selectApvAdjustAmountReasonModal"  data-toggle="modal"><span class="glyphicon glyphicon-plus-sign"></span> เลือกรายการขอปรับลดหนี้</a></li>
        <li style="padding-left:22px;"></li>	
    </ul>
	<div class="row hide" id="panelAdjustAmountListEdit">
		<div class="col-md-12 tab-modefile">
				<ul id="invoiceDetailsTab" class="nav nav-tabs" role="tablist">
					<li role="presentation" class="active""><a href="#tab-2-1" aria-controls="tab-2-1" role="tab" data-toggle="tab"><span class="glyphicon glyphicon-briefcase"></span> รายการใบแจ้งค่าใช้บริการ</a></li>
					<li class="col-md-10">
                           <div class="form-inline">
                               <label >เหตุผลการขอปรับลดหนี้:</label>
                               <select id="inputAdjustReasonType" class="form-control" disabled="true">
                                   <!-- <option>คิดค่าบริการผิด และ ขอคืนเงิน</option>  -->
                               </select>
                           </div>
                       </li>
				</ul>
				<div class="tab-content">
					<div role="tabpanel" class="tab-pane active" id="tab-2-2">
        				<table id="tableAdjustAmountEditList" data-row-style="rowStyle" data-toggle="table" data-detail-view="true" data-detail-formatter="detailFormatter" data-classes="table table-hover table-striped">
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
									<th data-field="adjustAmount" data-align="center">เงินขอปรับลด</th>
									<th data-field="approveAmount" data-align="center" data-formatter="moneyInputFormatter">เงินอนุมัติ</th>
								</tr>
	                       </thead>
	                  <!--      <tbody>
	                           <tr>
	                               <td>255900001</td>
	                               <td>02/02/2016</td>
	                               <td>22/02/2016</td>
	                               <td>1000.00</td>
	                               <td>0.00</td>
	                               <td>70.00</td>
	                               <td>1070.00</td>
	                               <td>0.00</td>
	                               <td>1070.00</td>
	                               <td>0.00</td>
	                               <td>01/01/2016-20/01/2016</td>
	                               <td>800.00</td>
	                               <td><input class="text-right" value="800.00" style="width:85px"></td>
	                        	</tr>
	                       </tbody>  -->
           				</table>
            		</div>
               </div>    
           </div>   
	</div>
	
	<div class="modal fade" role="dialog" id="selectApvAdjustAmountReasonModal">
			<div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close" onclick="closeModal(true);"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title"><span class="glyphicon glyphicon-exclamation-sign"></span> ตรวจสอบคำร้อง</h4>
                    </div>
                    <div id="confirmMessage"></div>
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-horizontal">
                                    <div class="form-group">
                                        <label class="control-label col-sm-1" ></label>
                                        <label class="control-label col-sm-4" style="text-align: left">
                                            <input type="radio" name="radioApvStatus" id="radioOptions1" value="02" checked=""> อนุมัติการขอปรับลดหนี้
                                        </label>
                                        <label class="control-label col-sm-1" ></label>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-sm-1" ></label>
                                        <label class="col-sm-4 control-label">เหตุผลการอนุมัติ :</label>
                                        <div class="col-sm-6">
                                            <select class="form-control" id="inputApvReasonType">
                                            	
                                            </select>
                                        </div>
                                        <label class="control-label col-sm-1" ></label>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-sm-1" ></label>
                                        <label class="control-label col-sm-11" style="text-align: left">
                                            <input type="radio" name="radioApvStatus" id="radioOptions2" value="03"> ไม่อนุมัติการขอปรับลดหนี้
                                        </label>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-sm-1" ></label>
                                        <label class="col-sm-4 control-label">เหตุผลการไม่อนุมัติ :</label>
                                        <div class="col-sm-6">
                                            <select class="form-control" id="inputRejectReasonType">
                                            	
                                            </select>
                                        </div>
                                        <label class="control-label col-sm-1" ></label>
                                    </div><hr>
                                    <div class="form-group">
                                        <label class="control-label col-sm-1" ></label>
                                        <label class="col-sm-4 control-label">หมายเหตุ :</label>
                                        <div class="col-sm-6">
                                            <input id="inputReasonApvModal" type="text" class="form-control" >
                                        </div>
                                        <label class="control-label col-sm-1" ></label>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-sm-1" ></label>
                                        <label class="col-sm-4 control-label">เลขที่อ้างอิง ลง. :</label>
                                        <div class="col-sm-6">
                                            <input id="inputAnnotation" type="text" class="form-control" >
                                        </div>
                                        <label class="control-label col-sm-1" ></label>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-sm-1" ></label>
                                        <label class="col-sm-4 control-label">พื้นที่เขตปรับลด :</label>
                                        <div class="col-sm-6">
                                            <select class="form-control" id="inputAdjustAreaType">
                                            	
                                            </select>
                                        </div>
                                        <label class="control-label col-sm-1" ></label>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-sm-1" ></label>
                                        <label class="col-sm-4 control-label">เลขที่เขตปรับลด :</label>
                                        <div class="col-sm-6">
                                            <input id="inputAdjustAreaNo" type="text" class="form-control" >
                                        </div>
                                        <label class="control-label col-sm-1" ></label>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-sm-1" ></label>
                                        <label class="col-sm-4 control-label">บังคับปรับลด :</label>
                                        <div class="col-sm-6">
                                            <select class="form-control" id="inputAdjustForceType">
                                            	
                                            </select>
                                        </div>
                                        <label class="control-label col-sm-1" ></label>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-sm-1" ></label>
                                        <label class="col-sm-4 control-label">ประเภทการปรับลดของไฟล์ข้อมูล :</label>
                                        <div class="col-sm-6">
                                            <select class="form-control" id="inputExportFileAdjustType">
                                            	
                                            </select>
                                        </div>
                                        <label class="control-label col-sm-1" ></label>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-sm-1" ></label>
                                        <label class="col-sm-4 control-label">รอบเดือนปรับลด :</label>
                                        <div class="col-sm-6" >
                                            <input id="inputPeriodDate" class="Default form-control" placeholder="yyyyMM">
                                        </div>
                                        <label class="control-label col-sm-1" ></label>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                    <button id="buttonApvAdjustAmountReason" type="button" class="btn btn-success" data-dismiss="modal" onclick="closeModal(false);">
		                <span class="glyphicon glyphicon-ok-circle"></span> ตกลง
		            </button>&nbsp;&nbsp;
				<!-- 	<a id="apvAdjustReceiptList" class="btn btn-success" data-dismiss="modal" onclick="apvAdjustReceiptListEvent(this);"><span class="glyphicon glyphicon-ok-circle"></span> ตกลง</a>  -->
					<a class="btn btn-danger" data-dismiss="modal" onclick="closeModal(true);"><span class="glyphicon glyphicon-remove-circle"></span> ยกเลิก</a>
				</div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
		</div>
		 <!--------------------------------------->
	    <ul id="panelNavigateEditSummaryList" class="list-inline pull-right list-menu-set">
	        <li><a href="#confirmAdjustSummaryModal" data-toggle="modal"><span class="glyphicon glyphicon-th-list"></span> ดำเนินการอนุมัติขอปรับลดหนี้</a></li>
	        <li style="padding-left:22px;"></li>
	    </ul>
	    <!--------------------------------------->
	    <div class="row hide" id="panelEditSummaryList">
	    	<div class="col-md-12 tab-modefile">
					<div class="panel-heading">
	                    <h3 class="panel-title"><span class="glyphicon glyphicon-shopping-cart"></span> รายการขออนุมัติปรับลดหนี้</h3>
	                </div>
	                <div class="tab-content">
						<div role="tabpanel" class="tab-pane active" id="tab-2-2">
							<table id="tableEditSummaryList"
		                          data-row-style="rowStyle"
		                          data-toggle="table"
		                          data-detail-view="true"
			                      data-detail-formatter="detailFormatterDisplay"
		                          data-classes="table table-hover table-striped"
		                          >
		                       <thead>
		                           <tr>
		                            <th data-field="billRefNo" data-align="center">เลขที่ใบแจ้งค่าบริการ</th>
		                            <th data-field="receiptNo" data-align="center">เลขที่ใบเสร็จรับเงิน</th>
		                            <th data-field="accountNo" data-align="center">เลขที่ลูกค้า</th>
		                            <th data-field="accountName" data-align="center">ชื่อลูกค้า</th>
		                            <th data-field="payMethod" data-align="center">วิธีการชำระเงิน</th>
		                            <th data-field="totalCharge" data-align="center">จำนวนเงิน</th>
		                            <th data-field="adjustAmount" data-align="center">เงินขอปรับลด</th>
		                            <th data-field="approveAmount" data-align="center">เงินอนุมัติ</th>
		                            <th data-field="branchName" data-align="center">สถานที่รับชำระ</th>
				                    <th data-field="createUser" data-align="center">ผู้ขอปรับลด</th>
		                            <th data-field="status" data-align="center">สถานะ</th>
		                           </tr>
		                       </thead>
		                       <!-- 
		                       <tbody>
		                           <tr>
		                               <td>255900001</td>
		                               <td>EP0171701F150714000020</td>
		                               <td>200006599</td>
		                               <td>นาย วสันตืชาย วงค์คำเดือน</td>
		                               <td>เงินสด</td>
		                               <td>1,070.00</td>
		                               <td>800.00</td>
		                               <td>800.00</td>
		                               <td>ศบล.แจ้งวัฒนะ</td>
			                           <td>EPIS2016</td>
		                               <td>รอยีนยันอนุมัติ</td>
		                        	</tr>
		                       </tbody> -->
		                   </table>
	                   </div>
                   </div>
               	</div>
           	</div>
    </section>
    <!-- Nav tabs -->
    <!------------------------->
    <div class="modal fade" role="dialog" id="confirmAdjustSummaryModal">
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
											ยืนยันอนุมัติขอปรับลดหนี้</label>
											
									</div>
								</div>
							</div>
							<div class="col-md-2"></div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button id="buttonApvAdjustAmountConfirm" type="button" class="btn btn-success" data-dismiss="modal">
		                <span class="glyphicon glyphicon-ok-circle"></span> ตกลง
		            </button>&nbsp;&nbsp;
					<a class="btn btn-danger" data-dismiss="modal"><span class="glyphicon glyphicon-remove-circle"></span> ยกเลิก</a>
				</div>
			</div>
		</div>
	</div>
	<!--------------------------------------->
    <ul id="panelNavigateEditSummaryResultList" class="list-inline pull-right list-menu-set">
        <li><a id="addAppAdjustReceiptList" href="approve-adjust-payment_1.jsp" class="btn btn-link"><span class="glyphicon glyphicon-arrow-left"></span> กลับไปอนุมัติขอปรับลดหนี้</a></li>
        <li style="padding-left:22px;"></li>
    </ul>
    <div id="remarkAdjustDesc"></div>
    <!--------------------------------------->
	<div class="row" id="panelEditSummaryResultList">
		<div class="col-md-12 tab-modefile">
				<div class="panel-heading">
                    <h3 class="panel-title"><span class="glyphicon glyphicon-shopping-cart"></span> ผลของการขอปรับลดหนี้</h3>
                </div>
				<table id="tableEditSummaryResultList"
                         data-row-style="rowStyle"
                         data-toggle="table"
                         data-detail-view="true"
                      data-detail-formatter="detailFormatterDisplay"
                         data-classes="table table-hover table-striped"
                         >
                      <thead>
                          <tr>
                           <th data-field="headerNo" data-align="center">เลขที่อนุมัติขอปรับลดหนี้</th>
                           <th data-field="billRefNo" data-align="center">เลขที่ใบแจ้งค่าบริการ</th>
                           <th data-field="receiptNo" data-align="center">เลขที่ใบเสร็จรับเงิน</th>
                           <th data-field="accountNo" data-align="center">เลขที่ลูกค้า</th>
                           <th data-field="accountName" data-align="center">ชื่อลูกค้า</th>
                           <th data-field="payMethod" data-align="center">วิธีการชำระเงิน</th>
                           <th data-field="totalCharge" data-align="center">จำนวนเงิน</th>
                           <th data-field="adjustAmount" data-align="center">เงินขอปรับลด</th>
                           <th data-field="approveAmount" data-align="center">เงินอนุมัติ</th>
                           <th data-field="branchName" data-align="center">สถานที่รับชำระ</th>
	                    	<th data-field="createUser" data-align="center">ผู้ขอปรับลด</th>
                           <th data-field="status" data-align="center">สถานะ</th>
                          </tr>
                      </thead>
                 <!-- <tbody>
                          <tr>
                              <td>EP0171701AP50714090</td>
                              <td>255900001</td>
                              <td>EP0171701F150714000020</td>
                              <td>200006599</td>
                              <td>นาย วสันตืชาย วงค์คำเดือน</td>
                              <td>เงินสด</td>
                              <td>1,070.00</td>
                              <td>800.00</td>
                              <td>800.00</td>
                              <td>ศบล.แจ้งวัฒนะ</td>
                           <td>EPIS2016</td>
                              <td><span class="glyphicon glyphicon-ok-circle"></span> อนุมัติ</td>
                       	</tr>
                      </tbody> -->
                  </table>
			</div>
		</div>
      <div class="modal fade" role="dialog" id="CustomerSearch">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title"><span class="glyphicon glyphicon-user"></span> ค้นหาลูกค้า</h4>
                </div>
                <div class="modal-body">
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
                                                    <input type="text" class="form-control" value="2559*">
                                                    <span class="input-group-btn">
                                                        <button type="button" class="btn btn-info"><span class="glyphicon glyphicon-search"></span> ค้นหา</button>
                                                    </span>
                                                </div><!-- /input-group -->
                                            </div>
                                        </div><br>
                                        
                                        <div class="row">
                                            <div class="col-md-12">
                                                <table class="table table-striped">
                                                    <thead>
                                                        <tr>
                                                            <th>#</th>
                                                            <th>เลขที่ใบแจ้งค่าบริการ</th>
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
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>

                                    <div role="tabpanel" class="tab-pane" id="profile">
                                        <div class="form-horizontal">
                                            <div class="form-group">
                                                <label class="control-label col-sm-2" >ประเภทบริการ :</label>
                                                <div class="col-sm-4">
                                                    <select class="form-control">
                                                        <option>INMARSAT-C</option>
                                                        <option>2</option>
                                                        <option>3</option>
                                                        <option>4</option>
                                                        <option>5</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-sm-2" >หมายเลขบริการ :</label>
                                                <div class="col-sm-4">
                                                    <div class="input-group">
                                                        <input type="text" class="form-control" value="456700*">
                                                        <span class="input-group-btn">
                                                            <button type="button" class="btn btn-info"><span class="glyphicon glyphicon-search"></span> ค้นหา</button>
                                                        </span>
                                                    </div><!-- /input-group -->
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-sm-2" ></label>
                                                <div class="col-sm-4">
                                                    <label class="checkbox-inline">
                                                        <input type="checkbox" id="inlineCheckbox1" value="option1"> Property1
                                                    </label>
                                                    <label class="checkbox-inline">
                                                        <input type="checkbox" id="inlineCheckbox2" value="option2"> Property2
                                                    </label>
                                                </div>
                                            </div>
                                        </div><br>
                                        

                                                <div class="row">
                                                    <div class="col-md-12">
                                                        <table class="table table-striped">
                                                            <thead>
                                                                <tr>
                                                                    <th>#</th>
                                                                    <th>หมายเลขบริการ</th>
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
                                                                    <td>456700100</td>
                                                                    <td>700005673</td>
                                                                    <td>บริษัท AEC จำกัด</td>
                                                                    <td>Inmarsat-C</td>
                                                                    <td>LN</td>
                                                                    <td><button type="button" class="btn btn-success btn-xs">เลือก</button></td>
                                                                </tr>
                                                                <tr>
                                                                    <td>2</td>
                                                                    <td>456700200</td>
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
				<th class="text-center">เงินอนุมัติ</th>

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
			<td class="text-center"><input fieldvalue="" value="{{costCenter}}" rowindex="{{adjAmtDtlId}}:{{contrno}}:{{billRefNo}}:{{productCode}}:{{subProductCode}}" id="costCenter" fieldname="costCenter" data-field="costCenter" style="width:100px" class="form-control text-right" onkeyup="if (event.which == 13) this.blur()" onblur="(function(o){ updateCellCostCenter($(o));})(this)"></td>
			<td class="text-right">{{amount}}</td>
			<td class="text-right">{{adjustAmount}}</td>
			<td ><div class="input-group text-right"><span class="input-group-addon" id="basic-addon1" style="padding:2px 4px;">฿</span><input fieldvalue="0.00" value="{{approveAmount}}" rowindex="{{adjAmtDtlId}}:{{contrno}}:{{billRefNo}}:{{productCode}}:{{subProductCode}}" id="approveAmountProd" fieldname="approveAmount" data-field="approveAmount" maxlength="10" style="width:100px" class="form-control text-right adjustAmount" onkeypress="return $.isNumeric(String.fromCharCode(event.which)) || (event.which == 46 &amp;&amp; this.value.indexOf('.') == -1)" onkeyup="if (event.which == 13) this.blur()" onfocus="(function(o){ o.value = o.value.replace(/[,]/g, ''); o.select() })(this)" onblur="(function(o){ o.value = parseFloat(o.value, 10).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,'); console.log(' o.value >> '+ o.value+' , value >> '+value); (window[table.id +'InputBlurEvent'] || function(t){ console.log('table >> '+t);updateTotalAdjustAmt($(o));})(table) })(this)"></div></td>
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
				<th class="text-center">เงินอนุมัติ</th>

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
			<td class="text-right">{{approveAmount}}</td>
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
   	window.runningFormatter = function(val, row, ind) {return ind + 1 }
   	window.get = function(url, params, success, msgDialog, preCheck){ $.ajax({ "url": url, "type": "GET", "data": params, "error": (msgDialog || { "valid": function(jqXHR, textStatus, errorThrow){ console.log(jqXHR); console.log("textStatus: "+ textStatus); console.log("errorThrow:"); console.log(errorThrow) } }).valid, "success": function(res){ var isNotJson = res.constructor == String; console.log(res); (preCheck || function(o){})(res); if (isNotJson) res = { "statusCode": "99", "errorList": ["No matching data found."] }; if (msgDialog && !msgDialog.valid(res)) { return; } success(res) } }) }
	window.post = function(url, params, success, msgDialog, preCheck){ $.ajax({ "url": url, "type": "POST", "data": params, "error": (msgDialog || { "valid": function(jqXHR, textStatus, errorThrow){ console.log(jqXHR); console.log("textStatus: "+ textStatus); console.log("errorThrow:"); console.log(errorThrow) } }).valid, "success": function(res){ var isNotJson = res.constructor == String; console.log(res); (preCheck || function(o){})(res); if (isNotJson) res = { "statusCode": "99", "errorList": ["No matching data found."] }; if (msgDialog && !msgDialog.valid(res)) { return; } success(res) } }) }
	window.stripToNumber = function(str) { return $.type(str) === "string" && $.isNumeric(str = str.replace(/[,]/g, "")) ? parseFloat(str, 10) : 0 }
	window.toDateString = function(ddMMyyyy){ return ddMMyyyy.replace(/(\d{2}).(\d{2}).(\d{4})/g, "$2/$1/$3") }
	window.numberFormatter = function(val, row, ind) { return !$.isNumeric(stripToNumber(val)) ? "0.00" : parseFloat(val, 10).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,') }
	window.dateFormatter = function(val, row, ind){ if (!val) return ""; if ((/(\d{4}).(\d{2}).(\d{2}).*/g).test(val)) return val.replace(/(\d{4}).(\d{2}).(\d{2}).*/g, "$3/$2/$1"); return val }
	window.timeFormatter = function(val, row, ind){ if (!val) return ""; if ((/.*T(\d{2}).(\d{2}).(\d{2}).*/g).test(val)) return val.replace(/.*T(\d{2}).(\d{2}).(\d{2}).*/g, "$1:$2:$3"); return val }
	window.dateTimeFormatter = function(val, row, ind){ if (!val) return ""; if ((/(\d{4}).(\d{2}).(\d{2})T(\d{2}).(\d{2}).(\d{2}).*/g).test(val)) return val.replace(/(\d{4}).(\d{2}).(\d{2})T(\d{2}).(\d{2}).(\d{2}).*/g, "$3/$2/$1 $4:$5:6"); return val }
	window.stringInputFormatter = function(val, row, ind) { return '<input value="'+ $.trim(val) +'" maxLength="40" class="form-control">' }
	window.numberInputFormatter = function(val, row, ind) { return '<input value="'+ self.utils.numberFormat(parseFloat(val || "0.00", 10)) +'" maxLength="10" style="width:100px" class="form-control text-right" onkeypress="return $.isNumeric(String.fromCharCode(event.which)) || (event.which == 46 && this.value.indexOf(\'.\') == -1)" onfocus="(function(o){ o.value = o.value.replace(/[,]/g, \'\') })(this)" onblur="(function(o){ o.value = parseFloat(o.value, 10).toFixed(2).replace(/(\\d)(?=(\\d{3})+\\.)/g, \'$1,\'); })(this)">' }
	window.modifyButtonFormatter = function(val, row, ind) { return '<a class="btn btn-link modList" onclick=\'(function(elem, row, ind){ var table = $("#"+ $(elem).closest("table")[0].id); window[table[0].id +"ModifyEvent"](table, row, ind) })(this, '+ JSON.stringify(row) +', '+ ind +')\'><span class="glyphicon glyphicon-pencil"></span></a>' }
	window.removeButtonFormatter = function(val, row, ind) { return '<a class="btn btn-link delList" onclick=\'(function(elem, row, ind){ $("#"+ $(elem).closest("table")[0].id).bootstrapTable("remove", { field: "index", values: [ind] }) })(this, '+ JSON.stringify(row) +', '+ ind +')\'><span class="glyphicon glyphicon-trash"></span></a>' }
	window.LoadingPanel = function(id) { var obj = this, loadCnt = 0, setupFunc, loadFunc; function checkElemReady(){if ((obj.elem = $("#"+ id).css("font", "")).length != 1) { alert("The element hasn't insert into HTML DOM."); throw "The element hasn't insert into HTML DOM."; } clearTimeout(setupFunc); clearInterval(loadFunc) }; return { "elem": null, "finish": function(html){ checkElemReady(); (this.elem ? this.elem : this.elem = $("#"+ id)).css("font", "").html(html); return this }, "toString": function(){ var elem; setupFunc = setTimeout(function(){ loadFunc = setInterval(function(){ (elem || (elem = document.getElementById(id))).innerHTML = "Loading"+ Array(++loadCnt).join(".."); if (loadCnt > 60) loadCnt = 2 }, 250) }, 1000); return "<div id='"+ id +"' style='font:BOLD 16pt Arial'></div>" } } }
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
    
    function Listbox(el,isReq) {
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
	                if(isReq == null || !isReq){
	                	data.splice(0, 0, {key : "", value : "กรุณาเลือก"});
	                }
	                
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
	        var defaultValue = "";
	        $.each(array, function(i, o) {
	        	if(o.initialValue == "Y"){
	        		defaultValue = o.key ; 
	        	}
	            obj.elem.append('<option data-index="' + i + '" data-key="'
	                + o.key + '" value="' + o.key + '">' + (o.value)
	                + '</option>');
	           
	        });
	        if(defaultValue != ""){
	        	obj.elem.val(defaultValue);
	        }
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
	    
	    obj.add = function(key,value) {
	    	obj.elem.append('<option data-index="' + key + '" data-key="'
	                + key + '" value="' + key + '">' + (value)
	                + '</option>');
	    }
	}
        
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
		that.radioApvStatus = new Radio("[name=radioApvStatus]");  
		function Radio(el) {
			this.el = el;
			this.elem = $(el);
			this.label = function() { return this.elem.filter(":checked").data("label") };
			this.val = function() { var s = this.elem.filter(":checked"),val = s.val(); return !val ? s.data("label") : val };
		}
	});
	self.input = new(function(){
		var that = this;
        
        that.invoiceNo = new Input("#invoiceNo");
        that.receiptNo = new Input("#receiptNo");
        that.amountAdjustHeaderNo = new Input("#amountAdjustHeaderNo");
        that.accountNo = new Input("#accountNo");
        that.inputPeriodDate = new Input("#inputPeriodDate");
        
        
        //Customer Information Display 
        that.custNameDisp = new Input("#custNameDisp");
		that.accountNoDisp = new Input("#accountNoDisp");
		that.headerNoDisp = new Input("#headerNoDisp");
		that.createDateDisp = new Input("#createDateDisp");
        that.accountTypeDisp = new Input("#accountTypeDisp");
        
        //Approve modal
        that.inputReasonApvModal = new Input("#inputReasonApvModal");
        that.inputAnnotation = new Input("#inputAnnotation");
        that.inputAdjustAreaNo = new Input("#inputAdjustAreaNo");
	
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
        });
	self.breadcrumb = new Breadcrumb();
	self.search = new Button("#search");
	self.mainMessageDialog = new Message("#mainMessageDialog");
	self.confirmMessage = new Message("#confirmMessage");
	
	self.panelAdjustAmountSearchList = new Panel("#panelAdjustAmountSearchList",false);
	
	self.navigateAdjustAmountList = new Panel("#navigateAdjustAmountList");
	self.panelAdjustAmountList = new Panel("#panelAdjustAmountList");
	self.buttonAddAdjustReceiptAprvList = new Button("#buttonAddAdjustReceiptAprvList");
	self.panelCustomer = new Panel("#panelCustomer");
	self.panelNavigateEdit = new Panel("#panelNavigateEdit");
	self.panelAdjustAmountListEdit = new Panel("#panelAdjustAmountListEdit");
	
	self.apvAdjustReceiptList = new Button("#apvAdjustReceiptList");
	self.buttonApvAdjustAmountReason = new Button("#buttonApvAdjustAmountReason");
	self.selectApvAdjustAmountReason = new Button("#selectApvAdjustAmountReason");
	
	self.inputAdjustReasonType = new Listbox("#inputAdjustReasonType",true);//.init("../MasterData/getGroupData?key=ADJUST_DEBT_REASON_TYPE");
	self.inputApvReasonType = new Listbox("#inputApvReasonType").init("../MasterData/getGroupData?key=ADJUST_APPROVE_REASON_TYPE");
	self.inputRejectReasonType = new Listbox("#inputRejectReasonType").init("../MasterData/getGroupData?key=ADJUST_REJECT_REASON_TYPE");
	self.inputAdjustAreaType = new Listbox("#inputAdjustAreaType").init("../MasterData/getGroupData?key=ADJUST_AREA_TYPE");
	self.inputAdjustForceType = new Listbox("#inputAdjustForceType",true).init("../MasterData/getGroupData?key=ADJUST_FORCE_TYPE");
	self.inputExportFileAdjustType = new Listbox("#inputExportFileAdjustType").init("../MasterData/getGroupData?key=ADJUST_FILE_EXPORT_TYPE");
	
	self.tableAdjustAmountList = new BootstrapTable("#tableAdjustAmountList");
	self.tableAdjustAmountEditList = new BootstrapTable("#tableAdjustAmountEditList");
	
	self.panelNavigateEditSummaryList = new Panel("#panelNavigateEditSummaryList");
	self.tableEditSummaryList = new BootstrapTable("#tableEditSummaryList");
	self.panelEditSummaryList = new Panel("#panelEditSummaryList"); 
	
	self.buttonApvAdjustAmountConfirm = new Button("#buttonApvAdjustAmountConfirm");
	
	self.panelNavigateEditSummaryResultList = new Panel("#panelNavigateEditSummaryResultList");
	self.panelEditSummaryResultList = new Panel("#panelEditSummaryResultList");
	self.tableEditSummaryResultList = new BootstrapTable("#tableEditSummaryResultList");
	
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
    	console.log("searchClickEvent");
    	var invoiceNo = view.input.invoiceNo.val();
		var receiptNo = view.input.receiptNo.val();
		var amountAdjustHeaderNo = view.input.amountAdjustHeaderNo.val();
		var accountNo = view.input.accountNo.val();
		view.mainMessageDialog.clear();
		view.tableAdjustAmountList.clear();
		view.tableAdjustAmountList.showLoad();
		if (invoiceNo.length < 4 && receiptNo.length < 4 && amountAdjustHeaderNo.length < 4  && accountNo.length < 4) {
			view.mainMessageDialog.error(["Please fill in to input at least 4 characters."]).show();
			return;
		}
		
		var url = "../findAdjustAmountList.json", params = {"invoiceNo": invoiceNo,"receiptNo": receiptNo,"amountAdjustHeaderNo":amountAdjustHeaderNo , "accountNo":accountNo};
		get(url, params, function(res){
		    console.log("DATA >> "+JSON.stringify(res));
			view.panelAdjustAmountList.slideDown(500, function(){ view.navigateAdjustAmountList.slideDown() });
			view.buttonAddAdjustReceiptAprvList.disable();
	        view.tableAdjustAmountList.rawData(res.data);
			view.tableAdjustAmountList.hideLoad();
		}, view.mainMessageDialog, function(){ view.mainMessageDialog.clear() });
    }
    
    function tableAdjustAmountListCheckEvent() {
		//var adjustAmountList = view.tableAdjustAmountList.selected();
		var adjustAmountList = view.tableAdjustAmountList.getSelections();
		console.log("tableAdjustAmountListCheckEvent -> adjustAmountList >> "+JSON.stringify(adjustAmountList));
		view.buttonAddAdjustReceiptAprvList.disable(adjustAmountList.length == 0||adjustAmountList[0]["adjustStatus"]=="02");
	}
    
    function buttonAddAdjustReceiptAprvListClickEvent() {
    	var adjustAmountList = view.tableAdjustAmountList.getSelections();
    	console.log("adjustAmountList >> "+JSON.stringify(adjustAmountList));
    	//Set customer imformation
    	view.input.custNameDisp.val(adjustAmountList[0].custName);
    	view.input.accountNoDisp.val(adjustAmountList[0].accountNo);
    	view.input.headerNoDisp.val(adjustAmountList[0].headerNo);
    	view.input.createDateDisp.val(adjustAmountList[0].createDate);
    	//Set adjust reason default value.
    	//self.inputAdjustReasonType.val(adjustAmountList[0].reasonCd);
    	//self.inputAdjustReasonType.key(adjustAmountList[0].reason);
    	self.inputAdjustReasonType.add(adjustAmountList[0].reasonCd , adjustAmountList[0].reason);
    	/*if(adjustAmountList[0].custType == 'INDIVIDUAL'){
			view.input.accountTypeDisp.val("บุคคลทั่วไป");
		}else{
			view.input.accountTypeDisp.val("นิติบุคคล");
		}*/
    	
    	view.input.accountTypeDisp.val(adjustAmountList[0].acctCatDesc);
    	
    	//select invoice data
    	var params = new Map();
		
		var invoiceList = [];
		params["records"] = invoiceList;
		for (var i = 0; i < adjustAmountList.length; i++) {
			var dataMap = new Map();
			dataMap[""+adjustAmountList[i].billRefNo] = adjustAmountList[i].adjAmtId+"";
			invoiceList[i] = dataMap ;
		}
		console.log("params >> "+JSON.stringify(params));
    	
    	//Call select invoice
    	post("../findAdjustAmountEditList.json", params, function(res){
			console.log("selectConfirmAdjustReceiptListClickEvent return");
			console.log("response(1) >> "+JSON.stringify(res));
			
			//Set adjust amount into response
			for (var i = 0; i < adjustAmountList.length; i++) {
				console.log("response(2) >> ");
				for(var j = 0 ; j < res.data.length ; j++){
					if(res.data[j].billRefNo == adjustAmountList[i].billRefNo){
						res.data[j]["adjustAmount"] = adjustAmountList[i]["adjustAmount"];
						res.data[j]["approveAmount"] = adjustAmountList[i]["approveAmount"];
						res.data[j]["adjAmtId"] = adjustAmountList[i]["adjAmtId"];
						
					}
				}
			}
			console.log("response(3)");
			view.tableAdjustAmountEditList.rawData(res.data);
			console.log("response(4)");
			
    	});
    	
    	//set data into table
    	
    	view.breadcrumb.next();
    	
    	//view.navigateAdjustAmountSearchList.slideUp(1000);
    	view.panelAdjustAmountSearchList.slideUp(1000);
    	view.navigateAdjustAmountList.slideUp(1100);
    	view.panelAdjustAmountList.slideUp(1200);
    	
    	view.panelCustomer.slideDown(1300);
    	view.panelAdjustAmountListEdit.slideDown(1500, function(){ view.panelNavigateEdit.slideDown(1400) });
    }
    var invPanel = {};
	var invProductMap = {};
	var invCostCenterMap = {};
	var totalMap = {};
    function detailFormatter(val, row, ind) {
    	return detailFormatterMain(val, row, ind,'#template');
 	}
    
    function detailFormatterDisplay(val, row, ind) {
    	return detailFormatterMain(val, row, ind,'#templateDisplay');
    }
    
    function detailFormatterMain(val, row, ind , templateName) {
        var guid = view.utils.guid();
        var loadPanel = new LoadingPanel(guid);
        console.log("detailFormatterMain >> row = "+JSON.stringify(row) +" , ind = "+ind);
        var $table = $(templateName).clone();
        $.get("../findProductList.json", { "billRefNo": row.billRefNo }, function(res){
        
        	console.log("res:"+ JSON.stringify(res));
        	console.log("row.adjAmtId:"+ row.adjAmtId);
        	
        	//Get adjust amount detail
        	$.get("../findAdjustAmountDetailList.json", { "adjAmtId": row.adjAmtId}, function(resAdjustDetail){
        		console.log("resAdjustDetail:"+ JSON.stringify(resAdjustDetail));
        		
        		//Set adjust amount
        		var dataAdjust = resAdjustDetail.data;
        		var dataProd = res.data;
        		for(var i = 0 ; i < dataProd.length ; i++){
        			for(var j = 0 ; j < dataAdjust.length ; j++){
        				if(dataProd[i].productCode == dataAdjust[j].productCode 
        					&& dataProd[i].subProductCode == dataAdjust[j].subProductCode){
        					dataProd[i]["adjustAmount"] = dataAdjust[j]["adjustAmount"];
        					dataProd[i]["adjAmtDtlId"] = dataAdjust[j]["adjAmtDtlId"];
        					dataProd[i]["costCenter"] = dataAdjust[j]["costCenter"];
        				}
        			}
        		}
        		
	            loadPanel.finish(Mustache.render($table.html(), { "invoices": $.map(res.data, function(o, i){
	            	var index = o.adjAmtDtlId+":"+o.contrno+":"+o.billRefNo+":"+o.productCode+":"+o.subProductCode;
	            	console.log("index(1) : "+index+" , previous value(1) :"+ invProductMap[index]);
	            	/*if(invProductMap[index] == null || invProductMap[index] == undefined){
	            		invProductMap[index] = o.adjustAmount ;
	            	}*/
	            	console.log("index(2) : "+index+" , previous value(2) :"+ invProductMap[index]);
	                return { "productCode": o.productCode, "productName": o.productName, "subProductCode": o.subProductCode, "subProductName": o.subProductName, "revTypeCode":o.revTypeCode , "revTypeName": o.revTypeName, "amount": view.utils.numberFormat(o.amount) , "billRefNo": o.billRefNo  , "approveAmount": o.adjustAmount == null ? "0.00":invProductMap[index]==null ? o.adjustAmount : invProductMap[index],"contrno":o.contrno,"adjustAmount":o.adjustAmount,"adjAmtDtlId":o.adjAmtDtlId, "costCenter":invCostCenterMap[index]== null ? o.costCenter : invCostCenterMap[index] }
	            }) }));
            
           }); 
        });
        return loadPanel;
 	}
    
    var invPanel = {};

	var invProductMap = {};
	var invCostCenterMap = {};
	var totalMap = {};
	
	function updateTotalAdjustAmt(caller){
		console.log("updateTotalAdjustment");
		
  		//Update total adjustment when invoice is the same.
  		updateTotalAdjustment(caller);
  		
  		//Update total adjustment in table
  		updateTotalAdjustmentTable(caller);
	}
	
	function getCurInv(caller){
		var index = caller.attr('rowIndex');	
		var curInv = index.split(":")[2];	
		return curInv;
	}
	
	function updateTotalAdjustmentTable(caller){
		console.log("updateTotalAdjustmentTable(1)");
		var curInv = getCurInv(caller);	
		var apvAdjustAmt = caller.val();
		var adjustList = view.tableAdjustAmountEditList.rawData();
		for(var i = 0 ; i < adjustList.length ; i++ ){
			if(adjustList[i]["billRefNo"] == curInv){
				var newData = {
		 				'rowIndex': ''+i, 'fieldName': 'approveAmount', 'fieldValue': totalMap[i]
				};
		
				$('#tableAdjustAmountEditList').bootstrapTable('updateCell',newData);
				break;
			}
		}
		console.log("updateTotalAdjustmentTable(2)");
	}
	
	function updateTotalAdjustment(caller){
	  var curInv = getCurInv(caller);
	  var index = caller.attr('rowIndex');	
	  var apvAmt = caller.val();
	  
	  if(apvAmt != "0.00" && apvAmt != ""){
  		invProductMap[index] = apvAmt;
	  }else{
	  	return;
	  }
	  
	  //invProductMap[index] = apvAmt;
	  console.log("invProductMap["+index+"]="+invProductMap[index]);
	  var total = 0.00 ;
	  $.each(invProductMap, function(k, v) {
	    var inv = k.split(":")[2];
	    	console.log("inv = "+inv);
	    	if(inv == curInv){
	    		var amt = parseFloat(v.replace(/,/g,'')); 
	    		console.log("amt = "+amt);
	    		total += amt;
	    	}
	  });
	  
	  var adjustList = view.tableAdjustAmountEditList.rawData();
	  for(var i = 0 ; i < adjustList.length ; i++ ){
	  	if(adjustList[i]["billRefNo"] == curInv){
	  		totalMap[i] = total;
	  		break;
	  	}
	  }
	  console.log("updateTotalAdjustment - > total >>  "+total);
	  console.log("updateTotalAdjustment - > adjustList >>  "+JSON.stringify(adjustList));
	}
	
	function updateCellCostCenter(caller){
		var index = caller.attr('rowIndex');
		var costCenter = caller.val();
		invCostCenterMap[index] = costCenter;
	}
	
	function apvAdjustReceiptListEvent(caller) {
		console.log("apvAdjustReceiptListEvent...start");
		
	}
	
	function selectApvAdjustAmountReasonClickEvent(){
		console.log("selectApvAdjustAmountReasonClickEvent...start");
		$('#selectApvAdjustAmountReasonPopup').modal('show');
		console.log("selectApvAdjustAmountReasonClickEvent...start");
	}
	
	var isClose = false;
	function closeModal(c){
		isClose = c;
	}
	
	$('#selectApvAdjustAmountReasonModal').on('hide.bs.modal', function(e){
	   	 console.log("xxx self.inputApvReasonType.val() >> "+self.inputApvReasonType.val());
	     if(!isClose && (self.inputApvReasonType.val().length == 0
	     				 && self.inputRejectReasonType.val().length == 0	
	     			   )
	     	){
	     	e.preventDefault();
	    	e.stopImmediatePropagation();
	     	return false; 
	     }
	});
	
	function buttonApvAdjustAmountReasonClickEvent(){
		console.log("buttonApvAdjustAmountReasonClickEvent...start");
		if(isClose ||(self.inputApvReasonType.val().length == 0
	     				 && self.inputRejectReasonType.val().length == 0
	     			 )
	    ){
	    	view.confirmMessage.clear();
			view.confirmMessage.error(["กรุณาเลือกเหตุผล"]).show();
			return;
		}
		//$('#selectApvAdjustAmountReasonPopup').modal({'show':false});
		view.breadcrumb.next();
		view.mainMessageDialog.clear();
		view.tableEditSummaryList.clear();
		view.tableEditSummaryList.showLoad();
		console.log("approve remark >> "+view.input.inputReasonApvModal.val());
		
		view.panelCustomer.slideUp(700);
		view.panelNavigateEdit.slideUp(800);
		view.panelAdjustAmountListEdit.slideUp(900);
		
		view.panelNavigateEditSummaryList.slideDown(1000);
		view.panelEditSummaryList.slideDown(1100);
		var adjustAmountEditList = view.tableAdjustAmountEditList.rawData();
		
		//Set status and user 
		var adjustAmountList = view.tableAdjustAmountList.getSelections();
		adjustAmountEditList[0]["createUser"] = adjustAmountList[0]["createUser"];
		adjustAmountEditList[0]["status"] = adjustAmountList[0]["status"];	
		
		adjustAmountEditList[0]["receiptNo"] = adjustAmountList[0]["receiptNo"];		
		adjustAmountEditList[0]["payMethod"] = adjustAmountList[0]["payMethod"];		
		adjustAmountEditList[0]["headerNo"] = adjustAmountList[0]["headerNo"];	
		adjustAmountEditList[0]["branchName"] = adjustAmountList[0]["branchName"];	
		adjustAmountEditList[0]["adjustConditionType"] = adjustAmountList[0]["adjustConditionType"];		
			
		console.log("adjustAmountEditList - >   "+JSON.stringify(adjustAmountEditList));
		view.tableEditSummaryList.rawData(adjustAmountEditList);
		view.tableEditSummaryList.hideLoad();
		console.log("buttonApvAdjustAmountReasonClickEvent...end");
	}
	
	function buttonApvAdjustAmountConfirmClickEvent(){
		console.log("buttonApvAdjustAmountConfirmClickEvent...start");
		
		//Prepare data to approve.
		var params = [];
		var data = new Map();
		var header = new Map();
		//Prepare header data
		var adjustAmountList = view.tableAdjustAmountList.getSelections();
		header["id"] = adjustAmountList[0]["headerId"];
		var reason = "";
		var desc = "";
		var radioApv = view.radio.radioApvStatus.val();
		//radioApv = "02" > Approve
		var apvStatus = "ไม่อนุมัติ";
		if(radioApv == "02" && self.inputApvReasonType.val() != null && self.inputApvReasonType.val().length > 0){
			reason = self.inputApvReasonType.val();
			desc = self.inputApvReasonType.selected()["value"];
			apvStatus = "อนุมัติ";
		}else{
			reason = self.inputRejectReasonType.val();
			desc = self.inputRejectReasonType.selected()["value"];
		}
		
		
		header["adjustApvReason"] = reason;
		header["remark"] = view.input.inputReasonApvModal.val();
		header["adjustStatus"] = radioApv;
		header["annotation"] = view.input.inputAnnotation.val();
		header["adjustAreaType"] = self.inputAdjustAreaType.val();
		header["forceAdjType"] = self.inputAdjustForceType.val();
		header["exportFileAdjustType"] = self.inputExportFileAdjustType.val();
		header["adjustAreaNo"] = view.input.inputAdjustAreaNo.val();
		header["period"] = view.input.inputPeriodDate.val();
		data["header"] = header ;
		
		//Prepare record
		var adjustAmountEditList = view.tableAdjustAmountEditList.rawData();
		data["records"] = params ;
		for (var i = 0; i < adjustAmountEditList.length; i++) {
			var myParam = new Map();
			myParam["id"] = adjustAmountEditList[i]["adjAmtId"];
			myParam["approveAmount"] = adjustAmountEditList[i]["approveAmount"];
			myParam["adjustStatus"] = view.radio.radioApvStatus.val();
			params[i] = myParam;
		}
		
		//Prepare record detail
		var recordDetailList = [];
		 $.each(invProductMap, function(k, v) {
		 	var dataRcDt = new Map();
	    	dataRcDt["id"] = k.split(":")[0];
	    	dataRcDt["approveAmount"] = v.replace(",","");
	    	dataRcDt["costCenter"] = invCostCenterMap[k];
	    	recordDetailList[recordDetailList.length] = dataRcDt ;
	    });
	    data["recordDetailList"] = recordDetailList ;
		
		console.log("approve status - >   "+view.radio.radioApvStatus.val());
		console.log("desc status - >   "+desc);
		console.log("header -> adjustAmountList - >   "+JSON.stringify(adjustAmountList));
		console.log("record -> adjustAmountEditList - >   "+JSON.stringify(adjustAmountEditList));
		console.log("approve recordDetailList -> "+JSON.stringify(recordDetailList));
		//Call approve
		post("../approveAmountAdjustment.json", data, function(res){
			console.log("response >> "+JSON.stringify(res));
			if(res != null && res["statusCode"] == "0"){
				view.breadcrumb.next();
				view.panelNavigateEditSummaryList.slideUp(700);
				view.panelEditSummaryList.slideUp(800);
				view.panelNavigateEditSummaryResultList.slideDown(900);
				view.panelEditSummaryResultList.slideDown(1000);
				
				//Setting status label
				for(var i = 0 ; i < adjustAmountEditList.length ; i++ ){
					adjustAmountEditList[i]["status"] = apvStatus ;
				}
				view.tableEditSummaryResultList.rawData(adjustAmountEditList);
				
				//Setting remark label.
				var remarkAdjustDes = jQuery.grep(remarkAdjustDescArr, function(value) {
				 	return value["key"] == adjustAmountList[0]["adjustConditionType"] ;
				 });
				 $('#remarkAdjustDesc').text(remarkAdjustDes[0]["property1"]);
				
				//Create report
				if (radioApv == "02"){//Approve
					var data = new Map();
					$(document.body).append('<form action="../createAmountAdjustmentReportApv.json" method="post" target="_printForm" id="frmCreatePdf"></form>');
	     			var form = document.getElementById('frmCreatePdf');
	     			function input(n,v) { var input = document.createElement("input"); input.type = "hidden"; input.name = n; input.value = v; return input }
						form.appendChild(input('header["id"]', header["id"]));
						form.submit();
					} 
				
			}
		});
		
		console.log("buttonApvAdjustAmountConfirmClickEvent...end");
	}
$(document).ready(function() {
	$('.Default').MonthPicker();
	//$("#MonthFormat").MonthPicker({Button: false, MonthFormat : 'yymm'});
	$("#inputPeriodDate").MonthPicker({Button: false, MonthFormat : 'yymm'});
	
});

var remarkAdjustDescArr = [];
$.get("../MasterData/getGroupData?key=ADJUST_CONDITION_TYPE", function(res) {
	console.log("remarkAdjustDesc (1) "+JSON.stringify(res));
    remarkAdjustDescArr = res;
    if(res.data != null){
    	remarkAdjustDescArr = res.data;
    }
});

</script>
</html>
