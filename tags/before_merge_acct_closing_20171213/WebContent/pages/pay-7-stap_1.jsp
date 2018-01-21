<%@page import="th.net.cat.epis.controller.EpContextHolder"%>
<%@page import="java.util.List"%>
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
        <link href="resources/select2.min.css" rel="stylesheet" type="text/css"/> 
        <script src="resources/jquery.min.js" type="text/javascript"></script>
        <script src="resources/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="resources/bootstrap-table/src/bootstrap-table.js"></script>
        <script src="resources/js/bootstrap-datepicker.min.js"></script>
        <script src="resources/custom.js" type="text/javascript"></script>
        <script src="resources/select2.min.js" type="text/javascript"></script>
    </head>
    <body>
        <header class="header_page"></header>
        <section class="container-fluid menu">
            <!-- <button class="menu-toggle btn btn-sm btn-default" style="margin-bottom: 5px"><span class="glyphicon glyphicon-step-forward"></span></button> -->
                <%-- <%@include  file="menu.jsp" %> --%>
            <div class="row">
                <div class="col-md-12 tab-modefile">
                    <ol class="breadcrumb">
                        <li><i>รับชำระค่าเติมเงินออนไลน์ (Top up)</i></li>
                        <li class="active">ตรวจสอบข้อมูลการเติมเงิน</li>
                        <li>เลือกวิธีการชำระ</li>
                        <li>ผลการชำระ</li>
                    </ol>
                    <div id="message"></div>
					<ul class="nav nav-tabs" role="tablist">
                        <li role="presentation" class="active">
                        	<a href="#tab_cus" aria-controls="tab_cus" role="tab" data-toggle="tab"><span class="glyphicon glyphicon-search"></span> ข้อมูลการเติมเงิน</a>
                        </li>
                    </ul>
                    <!-- criteria Search -->
                    <div class="tab-content">
                        <div role="tabpanel" class="tab-pane active" id="tab_cus">
                            <div class="panel panel-default panal-radius">
                                <div class="panel-body">
                                    <div class="row">
		                                <div class="col-md-12">
		                                    <div class="form-horizontal">
		                                        <div class="form-group">
			                                     <label class="control-label col-sm-2" >ประเภทบริการ :</label>
			                                     <div class="col-sm-3">
			                                         <select class="form-control" id="inputTopupService"></select>
			                                     </div>
			                                     <label class="control-label col-sm-2" >หมายเลขบริการ  :</label>
			                                     <div class="col-sm-2">
			                                         <input class="form-control" id="inputSearchServiceNo">
			                                     </div>
			                                     <div class="col-sm-2">
		                                            <a class="btn btn-primary" id="buttonSearchTopup"><span class="glyphicon glyphicon-ok-circle"></span>  ตรวจสอบข้อมูล</a>
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
			
			<!-- Tab 1 -->
            <%--<div class="row tab-modefile panel-body text-right hide" id="panelNavigate">--%>
			<div class="row tab-modefile panel-body text-right hide" id="panelNavigate">
            	<div class="form-group" style="padding-right:5px;"><a class="btn btn-link" id="buttonProceedPayment"><span class="glyphicon glyphicon-edit"></span> ดำเนินการรับชำระ</a></div>
            </div>
            <%--<div class="row hide" id="panelSpecific">--%>
			<div class="row" id="panelSpecific">
                <div class="col-md-12 tab-modefile" id="tabDetails">
                   <div class="panel-body">
                       <div class="row">
                           <div class="col-md-12">
                               <div class="form-horizontal">
								   <div class="form-group">
									   <label class="control-label col-sm-2" >เลขที่ลูกค้า :</label>
									   <div class="col-sm-2">
										   <input class="form-control" id="inputReceiptCustNo" maxlength="20" disabled="disabled">
									   </div>
									   <label class="control-label col-sm-2" >ชื่อลูกค้า :</label>
									   <div class="col-sm-2">
										   <input class="form-control" id="inputReceiptCustName" maxlength="200" disabled="disabled">
										   <input type="text" id="inputCustomerName" hidden>
									   </div>
									   <div class="col-sm-4">
										   <div class="checkbox">
											   <label>
												   &nbsp;&nbsp;&nbsp;<input type="checkbox" id="check1" name="check1" onclick="editReceiptInfo()">&nbsp;แก้ไขข้อมูลลูกค้าเพื่อแสดงในใบเสร็จ
											   </label>
										   </div>
									   </div>
								   </div>
								   <div class="form-group">
									   <label class="control-label col-sm-2" >เลขประจำตัวผู้เสียภาษี :</label>
									   <div class="col-sm-2">
										   <input class="form-control" id="inputReceiptTaxId" maxlength="13" disabled="disabled">
										   <input class="form-control" id="inputCustomerGroupCode" type="hidden">
										   <input class="form-control" id="inputCustomerGroupName" type="hidden">
									   </div>
									   <label class="control-label col-sm-2" >กลุ่มผู้ใช้บริการ :</label>
									   <div class="col-sm-2">
										   <%--<select class="form-control" id="selectResidential" disabled>
											   <option value="INDIVIDUAL">บุคคลทั่วไป</option>
											   <option value="ORGANIZATION">ธุรกิจทั่วไป</option>
											   <option value="GOVERNMENT">หน่วยงานรัฐ</option>
										   </select>--%>
												   <select class="form-control" id="inputCustomerSegment" disabled></select>

									   </div>
									   <div class="col-sm-2">
										   <a class="btn btn-primary" data-toggle="modal" data-target="#customerSearch"><span class="glyphicon glyphicon-search"></span> ค้นหาข้อมูลลูกค้า</a>
									   </div>
								   </div>
								   <div class="form-group">
									   <label class="control-label col-sm-2">สาขาที่ :</label>
									   <div class="col-sm-2">
										   <input class="form-control" id="inputReceiptCustBranch" maxlength="5" disabled="disabled">
									   </div>
									   <label class="control-label col-sm-2">หน่วยงานรับรายได้ :</label>
										<div class="col-sm-3" id="serviceDepartmentDiv">
											<select id="inputServiceDepartment" class="form-control"></select>
										</div>
								   </div>
								   <div class="form-group">
									   <label class="control-label col-sm-2">ที่อยู่ :</label>
									   <div class="col-sm-6">
										   <textarea class="form-control" disabled="disabled" id="inputReceiptAddress1" maxlength="200"></textarea>
									   </div>

								   </div>
                                   <div class="form-group">
                                       <label class="control-label col-sm-2" >ประเภทบริการ :</label>
                                       <div class="col-sm-2">
                                           <input class="form-control" id="inputServiceType" disabled>
                                       </div>
									   <%--<label class="control-label col-sm-2" >ชื่อลูกค้า :</label>
                                       <div class="col-sm-3">
                                           <input class="form-control" id="inputCustomerName" disabled>
                                       </div>--%>
                                   </div>
                                   <div class="form-group">
                                       <label class="control-label col-sm-2" >หมายเลขบริการ :</label>
                                       <div class="col-sm-2">
                                           <input class="form-control" id="inputServiceNo" disabled>
                                       </div>
                                       <label class="control-label col-sm-4" ></label>
                                   </div>
                                   <div class="form-group">
                                       <label class="control-label col-sm-2">โปรโมชั่น :</label>
                                       <div class="col-sm-2">
                                           <select class="form-control" id="inputPromotion">
											   <%--<select class="form-control" id="inputPromotion">--%>
                                               <option>my Top Up SIM Master</option>
                                           </select>
                                       </div>
                                       <input  id="inputGlCode" type="hidden">
                                   </div>
                                   <div class="form-group">
                                       <label class="control-label col-sm-2">จำนวนเงิน (บาท) :</label>
                                       <div class="col-sm-2">
                                           <select class="form-control" id="inputServiceCharge">
                                               <option>20.00</option>
                                               <option>50.00</option>
                                               <option>100.00</option>
                                           </select>
                                       </div>
                                   </div>
                                   <div class="form-group">
                                       	<label class="control-label col-sm-2" >ส่วนลด(ร้อยละ) :</label>
                                       	<div class="col-sm-2">
                                           	<input class="form-control" id="inputServiceDiscount" disabled>
                                      	</div>

                                   </div>
								   <div class="form-group">
									   <label class="control-label col-sm-2" >ข้อความเพิ่มเติมในใบเสร็จ :</label>
									   <div class="col-sm-5">
										   <input class="form-control" id="remark">
									   </div>
									   <div class="col-sm-2">
										   <button class="btn btn-primary" id="buttonAddTopupService"><span class="glyphicon glyphicon-plus-sign"></span>  เพิ่มรายการเติมเงินออนไลน์</button>
									   </div>
								   </div>
								   <!--by NSD 23-01-2017-->
								   <input type="text" id="vatRate0" hidden>
                               </div>
                           </div>
                       </div>
                        <br>
                       <div class="row">
							<div class="col-md-12 tab-modefile">
            					<table data-toggle="table" id="tableTopupServiceList" data-pagination="true" data-page-size="5" data-page-list="[5, 10, 20, 50, 100, 200]" data-cache="false">
					                <thead>
					                    <tr>
					                        <th data-valign="middle" data-align="center" data-formatter="runningFormatter">#</th>
					                        <th data-valign="middle" data-field="customerName" data-align="center">ชื่อลูกค้า</th>
					                        <th data-valign="middle" data-field="serviceNo" data-align="center">หมายเลขบริการ</th>
					                       <%-- <th data-valign="middle" data-field="serviceType" data-align="center">ประเภทบริการ</th>--%>
											<th data-valign="middle" data-field="serviceTypeName" data-align="center">ประเภทบริการ</th>
					                        <th data-valign="middle" data-field="promotion" data-align="left">โปรโมชั่น</th>
					                        <th data-valign="middle" data-field="excAmount" data-align="right" data-formatter="numberFormatter">จำนวนเงินก่อนหักส่วนลด</th><!--by NSD 03-02-2016-->
											<th data-valign="middle" data-field="discount" data-align="right" data-formatter="numberFormatter">ส่วนลด</th>
											<th data-valign="middle" data-field="excAmountIncDisc" data-align="right" data-formatter="numberFormatter">จำนวนเงินก่อนภาษีมูลค่าเพิ่ม</th><!---->
											<th data-valign="middle" data-field="vatAmount" data-align="right" data-formatter="numberFormatter">ภาษีมูลค่าเพิ่ม</th><!---->
					                        <th data-valign="middle" data-field="amount" data-align="right" data-formatter="numberFormatter">ยอดเงิน</th>
					                        <th data-valign="middle" data-align="center" data-width="80" data-formatter="removeButtonFormatter"></th>
					                    </tr>
					                </thead>
					            </table>
	    					</div>
						</div>
                   </div>
                   <!-- Tab 2 -->
	                   <div class="panel-body">
	                       <div class="row">
	                           <div class="col-md-12">
	                               <div class="form-horizontal">
	                                   <div class="form-group">
	                                       <%--<label class="control-label col-sm-2" >เลขที่ลูกค้า :</label>
	                                       <div class="col-sm-2">
	                                           <input class="form-control" id="inputReceiptCustNo" disabled="disabled">
	                                       </div>--%>
	                                       <%--<div class="col-sm-2">
	                                       	<a class="btn btn-primary" data-toggle="modal" data-target="#customerSearch"><span class="glyphicon glyphicon-search"></span> ค้นหาข้อมูลลูกค้า</a>	
	                                       </div>--%>
	                                   </div>
	                                   <div class="form-group">
	                                       <label class="control-label col-sm-2">ชื่อลูกค้า :</label>
	                                       <%--<div class="col-sm-6">
	                                           <input class="form-control" id="inputReceiptCustName" disabled="disabled">
	                                       </div>--%>
	                                       <%--<div class="col-sm-4">
                                        		<div class="checkbox">
						                           	<label>
						                               &nbsp;&nbsp;&nbsp;<input type="checkbox" id="check1" name="check1" onclick="editReceiptInfo()">&nbsp;แก้ไขข้อมูลลูกค้า
						                           	</label>
	                       						</div>
	                                       </div>--%>
	                                   </div>
	                                   <%--<div class="form-group">
	                                       <label class="control-label col-sm-2" >เลขประจำตัวผู้เสียภาษี :</label>
	                                       <div class="col-sm-2">
	                                           <input class="form-control" id="inputReceiptTaxId" disabled="disabled">
	                                       </div>
	                                       <label class="control-label col-sm-2" >กลุ่มผู้ใช้บริการ :</label>
	                                       <div class="col-sm-2">
	                                           <select class="form-control" id="selectResidential" disabled>
	                                               <option value="INDIVIDUAL">บุคคลทั่วไป</option>
	                                               <option value="ORGANIZATION">ธุรกิจทั่วไป</option>
	                                               <option value="GOVERNMENT">หน่วยงานรัฐ</option>
	                                           </select>
	                                       </div>
	                                   </div>--%>
	                                   <%--<div class="form-group">
	                                       <label class="control-label col-sm-2">สาขาที่ :</label>
	                                       <div class="col-sm-2">
	                                           <input class="form-control" id="inputReceiptCustBranch" disabled="disabled">
	                                       </div>
	                                   </div>
	                                   <div class="form-group">
	                                       <label class="control-label col-sm-2">ที่อยู่ :</label>
	                                       <div class="col-sm-6">
	                                           <input class="form-control" disabled="disabled" id="inputReceiptAddress1">
	                                       </div>

	                                   </div>--%>
	                                   <div class="form-group">
	                                       <label class="control-label col-sm-2"></label>
	                                       <div class="col-sm-6">
	                                           <input class="form-control" disabled="disabled" id="inputReceiptAddress2">
	                                       </div>
	                                       <div class="col-sm-4">
	                                        	<div class="checkbox">
						                            <label>
						                                &nbsp;&nbsp;&nbsp;<input type="checkbox" id="isFullReceipt" name="isFullReceipt">&nbsp;ใบเสร็จรับเงิน/ใบกำกับภาษีเต็มรูปแบบ
						                            </label>
						                        </div>
	                                       </div>
	                                   </div>
	                               </div>
	                           </div>
	                       </div>
	                   </div>
                </div>
            </div>
            <%--<div class="row hide" id="panelSummary">--%>
			<div class="row" id="panelSummary">
                <div class="col-md-12">
                    <div class="panel panel-default">
                        <div class="panel-heading"><span class="glyphicon glyphicon-shopping-cart"></span> สรุปยอดเงินที่ต้องชำระ</div>
                        <div class="panel-body">
                            <div class="form-horizontal">
	                            <div class="form-group">
	                                    <label class="control-label col-sm-10">จำนวนเงินก่อนหักส่วนลด :</label>
	                                <div class="col-sm-2">
	                                    <input class="form-control text-right" id="inputAmountExcDiscount" disabled="disabled">
	                                </div>
	                             </div>
								<div class="form-group">
									<label class="control-label col-sm-10">ส่วนลด :</label>
									<div class="col-sm-2">
										<input class="form-control text-right" id="proDiscAmount" disabled="disabled">
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-sm-10">จำนวนเงินก่อนภาษีมูลค่าเพิ่ม :</label>
									<div class="col-sm-2">
										<input class="form-control text-right" id="inputAmount" disabled="disabled">
									</div>
								</div>
							<%--<div class="form-group">
									<label class="control-label col-sm-10">ส่วนลดพิเศษ :</label>
									<div class="col-sm-2">
										<input class="form-control text-right" id="specDiscAmount" disabled="disabled">
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-sm-10">จำนวนเงินก่อนภาษีมูลค่าเพิ่ม :</label>
									<div class="col-sm-2">
										<input class="form-control text-right" id="excVatAmount" disabled="disabled">
									</div>
								</div>--%> 
	                             <div class="form-group">
	                                <label class="control-label col-sm-10">ภาษีมูลค่าเพิ่ม  :</label>
	                                <div class="col-sm-2">
	                                    <input class="form-control text-right" id="inputVat" disabled="disabled">
	                                </div>
	                            </div>
	                            <div class="form-group">
	                                <label class="control-label col-sm-10">จำนวนเงินรวมภาษีมูลค่าเพิ่ม  :</label>
	                                <div class="col-sm-2">
	                                    <input class="form-control text-right" id="inputTotalCharge" disabled="disabled">
	                               	</div>
	                            </div>
	                            <%--<div class="form-group">
	                                <label class="control-label col-sm-10"><input type="checkbox" disabled> ลูกค้ารับภาระภาษี <span class="glyphicon glyphicon-lock"></span> ส่วนลด :</label>
	                                <div class="col-sm-2">
	                                    <input class="form-control text-right" id="inputDiscount" disabled="disabled">
	                                </div>
	                            </div>--%>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
		<div class="modal fade" role="dialog" id="customerSearch">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title"><span class="glyphicon glyphicon-user"></span> ค้นหาลูกค้า</h4>
				</div>
				<div class="modal-body">
					<div id="advanceSearchMessageDialog"></div>
					<div class="tab-modefile">
						<ul class="nav nav-tabs" role="tablist">
							<li role="presentation" class="active"><a href="#messages" aria-controls="messages" role="tab" data-toggle="tab">ข้อมูลลูกค้า</a></li>
						</ul>
						<div class="panel panel-default">
							<div class="panel-body">
								<div class="tab-content border-tab-content">
									<div role="tabpanel" class="tab-pane active" id="messages">
										<div class="form-horizontal">
											<div class="form-group">
												<label class="control-label col-sm-3">เลขที่ลูกค้า (CA):</label>
												<div class="col-sm-3"><input class="form-control" id="advSrcCusNo"></div>
												<label class="control-label col-sm-3">เลขประจำตัวผู้เสียภาษี :</label>
												<div class="col-sm-3"><input class="form-control" id="advSrcCusTaxId"></div>
											</div>
											<div class="form-group">
												<label class="control-label col-sm-3">ชื่อลูกค้า/ชื่อนิติบุคคล/ราชการ :</label>
												<div class="col-sm-9"><input class="form-control" id="advSrcCusFirstName"></div>
												<!--
												<label class="control-label col-sm-3">นามสกุล :</label>
												<div class="col-sm-3"><input class="form-control" id="advSrcCusLastName"></div>
												-->
											</div>
											<div class="form-group">
												<label class="control-label col-sm-3">กลุ่มผู้ใช้บริการ :</label>
												<div class="col-sm-3">
													<select class="form-control" id="customerSegmentSearch"></select>
												</div>
												<div class="col-sm-3">
													<a id="advSrcCusNoBtn" class="btn btn-info pull-right"><span class="glyphicon glyphicon-search"></span> ค้นหา</a>
												</div>
											</div>
										</div>

										<br>

										<div class="row">
											<div class="col-md-12">
												<table id="advSrcCusNoResultList" class="table table-hover" data-toggle="table" data-pagination="true" data-page-size="5" data-page-list="[5, 10, 20, 50, 100, 200]" data-cache="false">
													<thead>
													<tr>
														<th data-formatter="runningFormatter">#</th>
														<th data-field="customerId">เลขที่ลูกค้า (CA)</th>
														<th data-field="customerAccountName">ชื่อลูกค้า</th>
														<th data-field="customerTaxNo">TAX ID</th>
														<th data-field="customerSegment">กลุ่มผู้ใช้บริการ</th>
														<th data-align="center" data-formatter="SelectButton"></th>
													</tr>

													</thead>
												</table>
											</div>
										</div>
									</div>
								</div>
							</div>

				<%--<div class="modal-body">
					<div id="advanceSearchMessageDialog"></div>
					<div class="tab-modefile">
						<ul class="nav nav-tabs" role="tablist">
							<li role="presentation" class="active"><a href="#messages" aria-controls="messages" role="tab" data-toggle="tab">ข้อมูลลูกค้า</a></li>
						</ul>
						<div class="panel panel-default">
							<div class="panel-body">
								<div class="tab-content border-tab-content">
									<div role="tabpanel" class="tab-pane active" id="messages">
										<div class="form-horizontal">
											<div class="form-group">
												<label class="control-label col-sm-3">เลขที่ลูกค้า (CA):</label>
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
												<div class="col-sm-3">
													<a id="advSrcCusNoBtn" class="btn btn-info pull-right"><span class="glyphicon glyphicon-search"></span> ค้นหา</a>
												</div>
											</div>
										</div>
										<br>
										<div class="row">
											<div class="col-md-12">
												<table id="advSrcCusNoResultList" class="table table-hover" data-toggle="table" data-pagination="true" data-page-size="5" data-page-list="[5, 10, 20, 50, 100, 200]" data-cache="false">
													<thead>
														<tr>
															<th data-formatter="runningFormatter">#</th>
															<th data-field="customerId">เลขที่ลูกค้า (CA)</th>
															<th data-field="customerAccountName">ชื่อลูกค้า</th>
															<th data-field="propLabel">ประเภทบริการ</th>
															<th data-field="billGroup">Billing Group</th>
															<th data-align="center" data-formatter="SelectButton"></th>
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
					<i class="pull-left"><span class="glyphicon glyphicon-filter"></span>
						กรุณาป้อนข้อมูลที่ต้องการค้นหาอย่างน้อย 4 ตัวอักษร
						</i>
					<br>
				</div>--%>
			</div>
		</div>
	</div>
    </body>
</html>
<script type="text/javascript">
var view = (function($){
	var self = this
	self.session = function(key, val) { if (!val) { var val = window.sessionStorage.getItem(key); return val && (val.indexOf("{") > -1 || val.indexOf("[") > -1) ? JSON.parse(val) : val } window.sessionStorage.setItem(key, ($.type(val) != "object" && $.type(val) != "array" ? val : JSON.stringify(val)))	};
	self.utils = {
		guid: function(){ function r() { return Math.floor((Math.random() + 1) * 0x10000).toString(16).substring(1) } return r() + r() +"-"+ r() +"-"+ r() +"-"+ r() +"-"+ r() + r() + r() },
		numberFormat: function(num, dec) { return $.type(num) !== "number" ? "0.00" : num.toFixed(dec || 2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,'); },
		dateFormat: function() { var obj = arguments[0]; if ($.type(obj) == "string" && /[0-9]{2}.[0-9]{2}.[0-9]{4}/g) { return obj } else if ($.isNumeric(obj)) { var dt = new Date(obj), dd = dt.getDate().toString(), mm = (dt.getMonth() + 1).toString(), yyyy = dt.getFullYear().toString(); return (dd[1]?"":"0") + dd +"/"+ (mm[1]?"":"0") + mm +"/"+ yyyy } else if ($.type == "date") { return "" } return "" },
		timeFormat: function() { var obj = arguments[0]; if ($.type(obj) == "string" && /[0-9]{2}.[0-9]{2}.[0-9]{4}/g) { return obj } else if ($.isNumeric(obj)) { var dt = new Date(obj), hh = dt.getHours().toString(), mm = dt.getMinutes().toString(), ss = dt.getSeconds().toString(); return (hh[1]?"":"0") + hh +":"+ (mm[1]?"":"0") + mm +":"+ (ss[1]?"":"0") + ss } else if ($.type == "date") { return "" } return "" },
		dateTimeFormat: function() { var obj = arguments[0], dt = $.type(obj) != "date" ? ($.isNumeric(obj) ? new Date(obj) : new Date()) : new Date(), dd = dt.getDate().toString(), MM = (dt.getMonth() + 1).toString(), yyyy = dt.getFullYear().toString(), hh = dt.getHours().toString(), mm = dt.getMinutes().toString(), ss = dt.getSeconds().toString(); return (dd[1]?"":"0") + dd +"/"+ (MM[1]?"":"0") + MM +"/"+ yyyy +" "+ (hh[1]?"":"0") + hh +":"+ (mm[1]?"":"0") + mm +":"+ (ss[1]?"":"0") + ss },
		queryString: function() { var pos = location.href.indexOf("?"), obj = {}; if (pos < 0) return obj; var params = location.href.slice(pos + 1).split("&"); for (var i = 0, m = params.length; i < m; i++) { pos = params[i].indexOf("="); if (pos < 0) { obj[params[i]] = true; continue; } obj[params[i].substring(0, pos)] = (params[i].substring(pos + 1) || true) } return obj },
		window: function(windowName, url, side) { window.windowOpened = (window.windowOpened || {}); var screenProp = ""; if (side == "left") { screenProp = "left=0,top=0,width="+ (screen.width/2) +",height="+ (screen.height-100) } else if (side == "right") { screenProp = "left="+ (screen.width/2-40) +",top=0,width="+ (screen.width/2+40) +",height="+ (screen.height-100) } window.windowOpened[windowName] = window.open(url, windowName, "menubar=no,scrollbars=no,resizable=0,"+ screenProp, false); return window.windowOpened }
	};
//// AUTOMATIC REGISTER FORMATTER FUNCTION ////
window.get = function(url, params, success, msgDialog, preCheck){ $.ajax({ "url": url, "type": "GET", "data": params, "error": msgDialog.valid, "success": function(res){ (preCheck || function(o){ console.log(o) })(res); if (msgDialog && !msgDialog.valid(res)) { return; } success(res) } }) }
window.getSync = function(url, params, success, msgDialog, preCheck){ $.ajax({ "url": url, "type": "GET", "data": params, "async": false, "error": (msgDialog || { "valid": function(jqXHR, textStatus, errorThrow){ console.log(jqXHR); console.log("textStatus: "+ textStatus); console.log("errorThrow:"); console.log(errorThrow) } }).valid, "success": function(res){ var isNotJson = res.constructor == String; console.log(res); (preCheck || function(o){})(res); if (isNotJson) res = { "statusCode": "99", "errorList": ["No matching data found."] }; if (msgDialog && !msgDialog.valid(res)) { return; } success(res) } }) }
window.post = function(url, params, success, msgDialog, preCheck){ $.ajax({ "url": url, "type": "POST", "data": params, "error": msgDialog.valid, "success": function(res){ (preCheck || function(o){ console.log(o) })(res); if (msgDialog && !msgDialog.valid(res)) { return; } success(res) } }) }
window.padLeft = function(obj, size, ch){ return padding(obj, size, ch, true) }; window.padRight = function(obj, size, ch){ return padding(obj, size, ch, false) }; function padding(obj, size, ch, isLeft) { var str = ""; if (!obj) str = ""; else if($.type(obj) == "string") str = obj; else if ($.type(obj) == "number") str = String(obj); if (str.length >= size) return str; var padded = Array(Math.max(size - str.length + 1, 0)).join(ch); return (isLeft ? padded : "") + str + (isLeft ? "" : padded) }
window.stripToNumber = function(str) { return $.type(str) === "string" && $.isNumeric(str = str.replace(/[,A-Za-z\(\)\[\]\{\}]/g, "")) ? parseFloat(str, 10) : 0 }
window.toDateString = function(ddMMyyyy){ return ddMMyyyy.replace(/(\d{2}).(\d{2}).(\d{4})/g, "$2/$1/$3") }
window.runningFormatter = function(val, row, ind) { return ind + 1 }
window.numberFormatter = function(val, row, ind) { return !$.isNumeric(stripToNumber(val)) ? "0.00" : parseFloat(val, 10).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,') }
window.stringInputFormatter = function(val, row, ind) { return '<input value="'+ $.trim(val) +'" maxLength="40" class="form-control">' }
window.numberInputFormatter = function(val, row, ind) { return '<input value="'+ self.utils.numberFormat(parseFloat(val || "0.00", 10)) +'" maxLength="10" style="width:100px" class="form-control text-right" onkeypress="return $.isNumeric(String.fromCharCode(event.which)) || (event.which == 46 && this.value.indexOf(\'.\') == -1)" onfocus="(function(o){ o.value = o.value.replace(/[,]/g, \'\') })(this)" onblur="(function(o){ o.value = parseFloat(o.value, 10).toFixed(2).replace(/(\\d)(?=(\\d{3})+\\.)/g, \'$1,\'); })(this)">' }
window.modifyButtonFormatter = function(val, row, ind) { return '<a class="btn btn-link modList" onclick=\'(function(elem, row, ind){ var table = $("#"+ $(elem).closest("table")[0].id); window[table[0].id +"ModifyEvent"](table, row, ind) })(this, '+ JSON.stringify(row) +', '+ ind +')\'><span class="glyphicon glyphicon-pencil"></span></a>' }
window.removeButtonFormatter = function(val, row, ind) {
	calculate();
	//return '<a class="btn btn-link delList" onclick=\'(function(elem, row, ind){ $("#"+ $(elem).closest("table")[0].id).bootstrapTable("remove", { field: "index", values: [ind] }) })(this, '+ JSON.stringify(row) +', '+ ind +')\'><span class="glyphicon glyphicon-trash"></span></a>'
	return '<a class="btn btn-link delList" onclick=\'deleteRow('+JSON.stringify(row)+')\'><span class="glyphicon glyphicon-trash"></span></a>'
}
function Breadcrumb(el) { var obj = this; obj.el = el; obj.elem = $(el); var list = obj.elem.find("li").each(function(i,o){ $(o).data("index", i) }), index = list.filter(".active").data("index");
	obj.index = function(){ if(arguments.length == 1) { list.removeClass("active").eq(index = arguments[0]).addClass("active"); return obj } return index }
	obj.next = function(){ obj.index(++index); return this }; obj.prev = function(){ obj.index(--index); return this }
}
function Button(el, url, inp, validate) { var obj = this, badge; obj.el = el; obj.elem = $(el);
	obj.hide = function() { this.elem.addClass("hide"); return this }; obj.show = function() { this.elem.removeClass("hide"); return this };
	obj.hideLoad = function(){ obj.elem.button("reset"); return this }; obj.showLoad = function(){ obj.elem.button("loading"); return this };
	obj.disable = function(flag) { if (flag == null || flag) this.elem.addClass("disabled").attr("disabled", true); else this.elem.removeClass("disabled").attr("disabled", false); return this }; obj.enable = function() { obj.disable(false); return this };
	obj.badge = function(val) { if (badge) badge.text(val) }; if ((badge = this.elem.next()).length == 0) badge = null;
	obj.elem.click(window[el.substring(1) +"ClickEvent"]);
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
function Panel(el) { var obj = this, dura = 500; obj.el = el; obj.elem = $(el);
	obj.hide = function(ms) { if (isHidden()) return this; obj.elem.hide(ms || dura); return this }; obj.show = function(ms) { if (!isHidden()) return this; if (!ms || !$.isNumeric(ms)) ms = dura; if (ms >= 0) obj.elem.css("display", "none").removeClass("hide").removeClass("hidden").show(ms); else obj.hide(Math.abs(ms)); return this }
	obj.slideDown = function(ms){ if (isHidden()) { obj.elem.css("display", "none").removeClass("hide").removeClass("hidden").slideDown(ms || dura) } return this }; obj.slideUp = function(ms){ if (!isHidden()) { obj.elem.slideUp(ms || dura) } return this }
	function isHidden() { return obj.elem.css("display") === "none" || obj.elem.hasClass("hide") || obj.elem.hasClass("hidden") }
}
function Input(el, maxLen, propPos) { var obj = this; obj.el = el; obj.elem = $(el);
	obj.error = function(flag) { if (arguments.length == 0 || flag) obj.elem.parent().addClass("has-error"); else obj.elem.parent().removeClass("has-error"); return this }
	obj.clear = function() { obj.val(""); return this }; obj.isEmpty = function() { return $.trim(obj.val()) === "" }; obj.isNumeric = function() { return $.isNumeric(obj.val()) }
	obj.nextFocus = function(nextElem) { if (nextElem && nextElem.constructor.name == "Input") { obj.elem.keyup(function(e){ var key = (e.which || e.keyCode || e.charCode || 0); if (key == 13) nextElem.elem.focus(); return true }) } return this };
	obj.click = function(func) { obj.elem.click(func); return this }
	obj.readOnly = function(flag) { obj.elem.attr("readonly", (flag == null ? true : flag)); return this }
	obj.disable = function(flag) { obj.elem.attr("disabled", (flag == null ? true : flag)); return this }
	obj.enable = function() { obj.disable(false); return this }
	obj.val = function() { if (arguments.length == 1) { this.elem.val(arguments[0]) } return $.trim(this.elem.val()) }
	obj.get = function(propName) { if ($.type(propPos[propName]) !== "array" || propPos[propName].length !== 2) return ""; return obj.val().substring(propPos[propName][0], propPos[propName][1]) }
	obj.elem.keyup(function(e){ var func = (window[el.substring(1) +"KeyUpEvent"] || function(){}); func((e.which || e.keyCode || e.charCode || 0), obj.elem) }); if ($.isNumeric(maxLen)) { obj.elem.attr("maxLength", maxLen) }
}
function NumberInput(el, dec) { var obj = this, decimal = (dec == null ? 2 : dec); obj.el = el; obj.elem = $(el).addClass("text-right");
	obj.val = function() { if (arguments.length == 0) return parseFloat(strip(this.elem.val() || "0"), 10); this.elem.val(format(arguments[0])); return this }
	obj.decimal = function(dec) { decimal = dec }; obj.format = format;
	obj.disable = function(){ obj.elem.attr("disabled", (arguments.length != 1 ? true : arguments[0])); return obj }; obj.enable = function(){ obj.disable(false); return obj }; obj.readOnly = function(flag) { obj.elem.attr("readonly", (flag == null ? true : flag)); return this }
	obj.elem.keypress(function(e) { var key = (e.which || e.keyCode || e.charCode || 0); var ch = String.fromCharCode(key); return "0123456789.".indexOf(ch) > -1 });
	obj.elem.focus(function(){ this.value = strip(this.value); this.select() });
	obj.elem.blur(function(){ this.value = format(this.value) });
	function format(val) { var str = ($.isNumeric(val) ? parseFloat(val, 10) : obj.val()).toFixed(decimal == 0 ? 1 : decimal).replace(/(\d)(?=(\d{3})+\.)/g, '$1,'); return decimal == 0 ? str.substring(0, str.lastIndexOf(".")) : str };
	function strip(str) { return (str || "").replace(/[,]/g, "") }
	if (obj.elem.val() == "") obj.elem.val("0" + (decimal < 1 ? "" : "."+ Array(decimal + 1).join("0")));
}
function DropDown(el, url) { var obj = this, data = [{ key: "", value: "", text: "กรุณาเลือก" }]; obj.el = el; obj.elem = $(el);
	obj.data = function(array) { if ($.type(array) == "array") { setup(data = array); return this } return data; }
	obj.init = function(url) { if (url) $.get(url, function(res) { setup(data = res.data) }); else setup(data); return this };
	obj.initV2 = function(url) { if (url) $.get(url, function(res) { setupV2(data = res.data) }); else setupV2(data); return this };
	obj.index = function() { if (arguments.length == 1) { var maxLen = data.length, index = arguments[0]; obj.elem.find("option").each(function(i, opt){ opt.removeAttribute("selected") }).eq(index < 0 ? maxLen + index : index).attr("selected", true); return this } return $.inArray("selected", obj.elem.find("option").map(function(i, opt){ return opt.selected ? "selected" : "no-selected" })) }
	obj.disable = function(){ obj.elem.attr("disabled", (arguments.length != 1 ? true : arguments[0])); return obj }; obj.enable = function(){ obj.disable(false); return obj };
	obj.selected = function(){ return data[obj.index()]; }; obj.val = function() { return obj.elem.val(); }; obj.key = function() { return obj.elem.find("option:selected").data("key") }
	function setup(array) { 
		obj.elem.find("*").remove();
		
		$.each(array,function(i,o){		
			var tempBA = '${epContext.branchArea}'; 
			if(o.property2 != null){
				var selected ="";
				if(o.key == tempBA){
				console.log("o.key : "+o.key+" BA : "+tempBA)
				selected = 'selected="selected"'
				}				
				obj.elem.append('<option data-index="'+ i +'" data-key="'+ o.property2 +'" value="'+ o.value +'"'+selected+'>'+ o.property2 +" : " +o.value  +'</option>');
			}else{
			
			obj.elem.append('<option data-index="'+ i +'" data-key="'+ o.key +'" value="'+ o.value +'">'+ (o.text || o.value) +'</option>') 
			}
		}); 
		}
		
	function setupV2(array) { obj.elem.find("*").remove();
		obj.elem.append('<option data-index="-1" data-key="-1" value="-1">ทั้งหมด</option>');
		$.each(array,function(i,o){ obj.elem.append('<option data-index="'+ i +'" data-key="'+ o.id +'" value="'+ o.id +'">'+ (o.text || o.value) +'</option>') }); }
	data = obj.elem.change(window[el.substring(1) +"ChangeEvent"]).find("option").map(function(i, opt){ return { key: opt.getAttribute("data-key"), value: opt.value, text: opt.textContent } });
}
function Modal(el) {
	this.el = el;
	this.elem = $(el);
	this.hide = function() { this.elem.modal("hide") };
	this.show = function() { this.elem.modal("show") };
}
function Tab(el, array) { var obj = this, pane = $(el), index = 0; obj.el = el.substring(1) +"-tab"; obj.elem = pane.append('<ul class="nav nav-tabs" role="tablist" id="'+ obj.el +'"></ul>').find("#"+ obj.el); var content = pane.append('<div class="tab-content"></div>').find(".tab-content"), tabs, panels = pane.find('div.panel-body').remove(); 
	obj.reset = function(){ return obj.index(0) }; obj.index = function() { if (arguments.length == 1) { index = arguments[0]; tabs.removeClass("active").eq(index).find("a").click(); return this } return index }
	obj.show = function(ind) { obj.elem.find("a").eq(ind).tab("show"); return this; }
	$.each(array /* [{ name: "", icon: "" }] */, function(i, o){ obj.elem.append('<li role="presentation"><a role="tab" data-toggle="tab" href="#'+ obj.el + i +'" data-index="'+ i +'"><span class="glyphicon glyphicon-'+ o.icon +'"></span> '+ o.name +'</a></li>') })
	$.each(array, function(i, o){ content.append('<div role="tabpanel" class="tab-pane" id="'+ obj.el + i +'"><div class="panel panel-default panal-radius"><div class="panel-body">'+ (!panels[i] ? "" : panels[i].innerHTML) +'</div></div></div>') });
	(tabs = obj.elem.find("li[role=presentation]")).eq(0).addClass("active"); (panels = content.find('div[role=tabpanel]')).eq(0).addClass("active"); obj.elem.on("show.bs.tab", function(e){ index = $(e.target).data("index");  }); tabs.find("a").click(window[el.substring(1) +"ChangeEvent"]);
}
function Table(el) {
	var obj = this, headers = [], data = [], onApn = function() {}, onDel = onApn, loadCnt = 0, loadInt, loadFunc = function(){ obj.body.find("div#loading").html("Loading"+ Array(++loadCnt).join(".")); if (loadCnt > 8) loadCnt = 0; };
	var checkboxHtml = '<input type="checkbox" name="{field}" value="{value}" data-index="{index}">', radioHtml = '<input type="radio" name="{field}" value="{value}" data-index="{index}">', inputHtml = '<input name="{field}" value="{value}" {style} data-index="{index}">';
	obj.el = el; obj.elem = $(el); obj.body = obj.elem.find("tbody");
	obj.onAppend = function(func) { onApn = func }; obj.onDelete = function(func) { onDel = func };
	obj.hideLoad = function() { obj.elem.find("tbody tr").remove(); clearInterval(loadInt); return obj };
	obj.showLoad = function(){ obj.clear(); obj.elem.find("tbody").append("<tr><td colspan='"+ headers.length +"'><div id='loading' style='text-align:center;font:BOLD 16pt Arial'>Loading</div></td></tr>"); loadCnt = 0; loadInt = setInterval(loadFunc, 500); return obj };
	function reorder(index) { obj.body.find("tr").each(function(i,o){ $(o).find("td").eq(index).text(i + 1) }) }
	obj.insert = function(array, showRemove, attrs) {
		if (arguments.length < 1) array = $.map(headers,function(o){ return " " });
		obj.body.append("<tr "+ $.map($.extend(attrs, {}), function(v, k){ return k +"='"+ v +"'" }).join(" ") +">"
			+ $.map(array, function(v,i){ var field = headers[i].field, value = v, colStyle = "", colWidth = 0;
				if (headers[i].runningNo) value = (obj.size() + 1);
				else if (headers[i].numberFormat) { value = !$.isNumeric(v) ? "0.00" : parseFloat(v, 10).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,'); }
				else if (headers[i].checkbox) value = replace(checkboxHtml, null, field, i, v); else if (headers[i].radio) value = replace(radioHtml, null, field, i, v);
				else if (headers[i].input) value = replace(inputHtml, 'style="width:100%;margin:-4px 0;text-align:'+ (headers[i].align || "left") +'"', field, i, v);
				if (headers[i].modifyButton) { colWidth += 45; value += '<a class="btn btn-link" onclick=\'(function(tableId, row, col){ window[tableId +"ModifyEvent"]($("#"+ tableId), row, col); })(this.parentNode.parentNode.parentNode.parentNode.parentNode.id, '+ JSON.stringify(array) +', this)\'><span class="glyphicon glyphicon-pencil"></span></a>'; } if (headers[i].removeButton) { colWidth += 45; value += '<a class="btn btn-link" onclick=\'(function(tableId, row, col){ window[tableId +"RemoveEvent"]($("#"+ tableId), row, col); col.parentNode.parentNode.parentNode.remove() })(this.parentNode.parentNode.parentNode.parentNode.parentNode.id, '+ JSON.stringify(array) +', this)\'><span class="glyphicon glyphicon-trash"></span></a>'; }
				return '<td style="'+ (!headers[i].valign ? "" : "vertical-align:"+ headers[i].valign +";") + (!colStyle ? "" : colStyle +";") + (colWidth < 1 ? "" : "width:"+ colWidth +"px;") +'"><div style="'+ (headers[i].align ? "text-align:"+ headers[i].align : "") +'">'+ value +'</div></td>' }).join("")
			+ (!showRemove ? "" : '<td style="width:40px;text-align:center"><a href="#" class="delList"><span class="glyphicon glyphicon-trash"></span></a></td>') 
		+"</tr>"); return obj }
	obj.find = function(key, cri) { return obj.elem.find("tbody tr").filter("["+ key +"="+ cri +"]") }
	obj.clear = function() { obj.elem.find("tbody tr").remove(); return obj }
	obj.remove = function(index) { this.elem.find("tbody tr").eq(index).remove(); $.each(headers,function(i,o){ if (o.runningNo) reorder(i) }); }
	obj.data = function() { var data = [];
		if (arguments.length != 1) { var rows = obj.elem.find("tbody tr");
			for (var i = 0, m = rows.length; i < m; i++) { var row = []; for (var j = 0, n = rows[i].children.length; j < n; j++) { row.push(extract(headers[j], rows[i].children[j])) } data.push(row) } return data;
		} for (var i = 0, m = (data = arguments[0]).length; i < m; i++) { obj.insert(data[i]); } return obj };
	obj.col = function(colIndex) { var rows = obj.elem.find("tbody tr"), data = []; for (var i = 0, m = rows.length; i < m; i++) { data.push(extract(headers[colIndex], rows[i].children[colIndex])) } return data }
	obj.size = function() { return obj.elem.find("tbody tr").length };
	obj.sum = function(index) { var sum = 0; this.elem.find("tbody tr").each(function(i, row){ var val = row.children[index].innerText.replace(/[,]/g, ""); sum += (isNaN(val) ? 0 : parseFloat(val, 10)) }); return sum }
	$(obj.el).on("click", ".delList", function(){ $(this).parent().parent().remove(); $.each(headers, function(i,p){ if (p.runningNo) reorder(i) }); onDel(obj.data()) });
	obj.elem.removeClass("table").removeClass("table-hover").addClass("table").addClass("table-hover");
	obj.elem.find("thead th").each(function(i,o){ var elem = $(o); headers.push({ "field": elem.data("field"), "valign": $.trim(elem.data("valign")), "align": $.trim(elem.data("align")), "modifyButton": elem.data("modifyButton") === true, "removeButton": elem.data("removeButton") === true, "runningNo": elem.data("runningNo") === true, "numberFormat": elem.data("numberFormat") === true, "checkbox": elem.data("checkbox") === true, "radio": elem.data("radio") === true, "input": elem.data("input") === true }) });
	function replace(str, style, field, index, value){ var s = str; if (style) s = s.replace("\{style\}", style); return s.replace("\{field\}", $.trim(field)).replace("\{index\}", index).replace("\{value\}", value) }
	function extract(prop, col) { if (prop.checkbox) { var elem = col.children[0].children[0]; return !elem.checked ? "" : elem.value } else if (prop.radio) { var elem = col.children[0].children[0]; return !elem.checked ? "" : elem.value } else if (prop.input) { var elem = col.children[0].children[0]; return elem.value } return col.children[0].innerText }
	if(obj.body.length < 1) { obj.elem.append("<tbody></tbody>"); obj.body = obj.elem.find("tbody") }
}
function BootstrapTable(el, options) { var obj = this, checkEvt = function(e){ console.log(e) }, uncheckEvt = checkEvt; obj.el = el; obj.elem = $(el).bootstrapTable(options = $.extend(options, { uniqueId: "id", onCheck: window[el.substring(1) +"CheckEvent"], onUncheck: window[el.substring(1) +"UncheckEvent"], onCheckAll: window[el.substring(1) +"CheckAllEvent"], onUncheckAll: window[el.substring(1) +"UncheckAllEvent"] }));
	obj.clear = function() { obj.elem.bootstrapTable("removeAll"); return obj }; obj.remove = function(index){ obj.elem.bootstrapTable("remove", { field: "index", values: [index] }); return this }; obj.resetView = function(ms){ setTimeout(function(){ obj.elem.bootstrapTable("resetView") }, ms || 200); return this }; obj.isEmpty = function(){ return obj.data().length == 0 }
	obj.showLoad = function() { this.elem.bootstrapTable("showLoading"); return this }; obj.hideLoad = function() { this.elem.bootstrapTable("hideLoading"); return this };
	obj.update = function(rec){ var id = rec.id, index = rec.index, row; if ((row = obj.getId(id)) != null) { obj.elem.bootstrapTable("updateRow", { "index": row.index, "row": $.extend(row, rec) }) } else if ((row = obj.data()[index]) != null) { obj.elem.bootstrapTable("updateRow", { "index": index, "row": $.extend(row, rec) }) } else obj.insert(rec); return this }; obj.insert = function(row) { var data = obj.elem.bootstrapTable("getData"); obj.elem.bootstrapTable("insertRow", { "index": data.length, "row": $.extend(row, { "index": data.length }) }); return this }
	obj.find = function(field, value) { var data = obj.elem.bootstrapTable("getData"); return $.map(data, function(o,i){ return o[field] === value ? o : null }) }
	obj.map = function(func) { var resultMap = {}; return $.map(obj.data(), $.type(func) === "string" ? function(row, ind){ return resultMap[func] = row } : func)}
	obj.data = function() { if (arguments.length == 1) { this.elem.bootstrapTable("load", arguments[0]); return this } return $.map(this.elem.bootstrapTable("getData"), function(row){ return row }) };
	obj.selected = function() { var data = []; obj.elem.find("tbody tr").find("input[type=checkbox]:checked, input[type=radio]:checked").each(function(i,o){ var row = o.parentNode.parentNode, record = []; for (var j = 0, n = row.childNodes.length; j < n; j++) { var col = row.childNodes[j], val = ""; if (col.childNodes.length != 1) val = ""; else if (col.childNodes[0].nodeType == 3) val = $.trim(col.childNodes[0].textContent); else if (col.childNodes[0].nodeName == "INPUT") val = $.trim(col.childNodes[0].value); else if (col.childNodes[0].nodeName == "DIV") val = $.trim(col.childNodes[0].childNodes[0].value); record.push(val) } data.push(record) }); return data };
	obj.getSelections = function(){ return obj.elem.bootstrapTable("getAllSelections") }
	obj.getId = function(id) { var o = obj.elem.bootstrapTable("getRowByUniqueId", id); if ($.type(o) == "array") return null; return o[options.uniqueId] == id ? o : null }; obj.delId = function(id) { obj.elem.bootstrapTable("removeByUniqueId", id); return this }; // options: { uniqueId: "fieldName" }
	obj.sum = function(isAll, colName) { var sum = 0; $.each(this.elem.bootstrapTable(isAll ? "getData" : "getSelections"), function(i,o){ sum += (o[colName] || 0) }); return sum };
	obj.sumInput = function(index) { var sum = 0; obj.elem.find("tbody tr").each(function(i,o){ var val = o.children[index].children[0].value.replace(/[,]/g, ""); sum += (!$.isNumeric(val) ? 0 : parseFloat(val, 10)) }); return sum }
	obj.http = function(url, urlParams, successFunc) { if (successFunc) obj.elem.bootstrapTable("refreshOptions", { onLoadSuccess: successFunc }); obj.elem.bootstrapTable("refresh", { "url": url, "query": urlParams }); return this }
	obj.expand = function() { obj.elem.find(".detail-icon i.icon-plus").click(); return this }; obj.collapse = function() { obj.elem.find(".detail-icon i.icon-minus").click(); return this };
	obj.checkAll = function(){ var checked = arguments.length == 0 || arguments[0] == true || ($.type(arguments[0]) == "boolean" ? arguments[0] : arguments[0] == "true"), data = obj.elem.bootstrapTable("getData"); $.each(obj.elem.bootstrapTable("getOptions").columns[0], function(i, col){ if (col.checkbox) $.each(data, function(j, row){ row[col.field] = checked }) }); return this }; obj.uncheckAll = function(){ obj.checkAll(false); return this }
	obj.elem.on("check.bs.table uncheck.bs.table check-all.bs.table uncheck-all.bs.table", window[el.substring(1) +"CheckBoxEvent"])
	obj.removeRow = function(index) { obj.elem.find("tbody").each(function(i,o){ o.children[index].remove(); }); return this; };
}
	function ListboxBillingGroup(el, url) { var obj = this, data = [{ key: "", value: "", text: "กรุณาเลือก" }]; obj.el = el; obj.elem = $(el);
		obj.data = function(array) { if ($.type(array) == "array") { setup(data = array); return this } return data; };
		obj.init = function(url) { if (url) $.get(url, function(res) { setup(data = res.data) }); else setup(data); return this };
		obj.index = function() { if (arguments.length == 1) { var maxLen = data.length, index = arguments[0]; obj.elem.find("option").each(function(i, opt){ opt.removeAttribute("selected") }).eq(index < 0 ? maxLen + index : index).attr("selected", true); return this } return $.inArray("selected", obj.elem.find("option").map(function(i, opt){ return opt.selected ? "selected" : "no-selected" })) }
		obj.disable = function(){ obj.elem.attr("disabled", (arguments.length != 1 ? true : arguments[0])); return obj }; obj.enable = function(){ obj.disable(false); return obj };
		obj.selected = function(){ return data[obj.index()]; }; obj.val = function() { return obj.elem.val(); }; obj.key = function(){ if (arguments.length == 0) { var key = obj.elem.find("option:selected").data("key"); if (typeof key === "string" && key === "undefined") { var item = data[obj.index()]; key = item && item.key ? item.key : item.id } return key } var key = arguments[0]; for (var i = 0, m = data.length; i < m; i++) { var item = data[i]; if (item.key && item.key == key) { obj.index(i); break; } if (item.id && item.id == key) { obj.index(i); break; } } return this }
		function setup(array) { obj.elem.find("*").remove(); $.each(array,function(i,o){ obj.elem.append('<option data-index="'+ i +'" data-key="'+ o.key +'" value="'+ o.value +'">'+ (o.value) +'</option>') }); }
		data = obj.elem.change(window[el.substring(1) +"ChangeEvent"]).find("option").map(function(i, opt){ return { key: opt.getAttribute("data-key"), value: opt.value, text: opt.textContent } });
	}

	this.message = new Message("#message");
	this.panelNavigate = new Panel("#panelNavigate");
	this.panelSpecific = new Panel("#panelSpecific");
	this.panelSummary = new Panel("#panelSummary");
	//this.tabDetails = new Tab("#tabDetails", [{ "name": "ข้อมูลบริการ", "icon": "briefcase" },{ "name": "รายละเอียดใบเสร็จรับเงิน", "icon": "tasks" }]);
	this.tabDetails = new Tab("#tabDetails", [{ "name": "ข้อมูลบริการ", "icon": "briefcase" }]);//by NSD 24-01-2017
	this.buttonSearchTopup = new Button("#buttonSearchTopup");
	this.buttonProceedPayment = new Button("#buttonProceedPayment");
	this.buttonAddTopupService = new Button("#buttonAddTopupService");
	this.inputTopupService = new DropDown("#inputTopupService").init("../findTopupServiceType.json");
	this.inputSearchServiceNo = new Input("#inputSearchServiceNo");
	this.inputServiceType = new Input("#inputServiceType");
	this.inputServiceDepartment = new DropDown("#inputServiceDepartment").init("../MasterData/findByGroupKey.json?groupKey=BUSINESS_AREA");
	this.inputCustomerName = new Input("#inputCustomerName");
	this.inputServiceNo = new Input("#inputServiceNo");
	this.inputPromotion = new DropDown("#inputPromotion");
	this.inputServiceCharge = new DropDown("#inputServiceCharge");
	this.inputServiceDiscount = new NumberInput("#inputServiceDiscount");
	this.inputReceiptCustNo = new Input("#inputReceiptCustNo");
	this.inputReceiptCustName = new Input("#inputReceiptCustName");
	this.inputCustomerGroupCode = new Input("#inputCustomerGroupCode");
	this.inputCustomerGroupName = new Input("#inputCustomerGroupName");
	this.inputReceiptTaxId = new Input("#inputReceiptTaxId");
	this.inputReceiptCustBranch = new Input("#inputReceiptCustBranch");
	this.inputReceiptAddress1 = new Input("#inputReceiptAddress1");
	this.inputReceiptAddress2 = new Input("#inputReceiptAddress2");
	this.inputAmount = new NumberInput("#inputAmount");
	//this.inputCustomerSegment = new DropDown("#inputCustomerSegment").initV2("../findCustomerSegmentList.json");

	this.inputAmountExcDiscount = new NumberInput("#inputAmountExcDiscount");//by NSD 06-02-2017
	this.remark = new Input("#remark");//by NSD 22-02-2017
	this.proDiscAmount = new NumberInput("#proDiscAmount");

	this.inputVat = new NumberInput("#inputVat");
	this.inputTotalCharge = new NumberInput("#inputTotalCharge");
	this.inputDiscount = new NumberInput("#inputDiscount");
	this.tableTopupServiceList = new BootstrapTable("#tableTopupServiceList");
	this.vatRate0 = new Input("#vatRate0");
	
	self.customerSearch = new Modal("#customerSearch");
	self.advanceSearch = new Button("#advanceSearch");
	self.advSrcCusNoBtn = new Button("#advSrcCusNoBtn");
	self.advanceSearchMessageDialog = new Message("#advanceSearchMessageDialog");
	self.advSrcCusNo = new Input("#advSrcCusNo");
	self.advSrcCusTaxId = new Input("#advSrcCusTaxId");
	self.advSrcCusFirstName = new Input("#advSrcCusFirstName");
	self.advSrcCusLastName = new Input("#advSrcCusLastName");
	self.advSrcOrgName = new Input("#advSrcOrgName");
	self.advSrcCusNoResultList = new BootstrapTable("#advSrcCusNoResultList");
	self.customerSegmentSearch = new DropDown("#customerSegmentSearch").initV2("../findCustomerSegmentList.json");

	$('#inputServiceDepartment').select2();

	
	$.ajax({
		type: "GET",
		url: "../findAccountCategoryList.json",
		//data: dataSend,
		dataType: "json",
		contentType: "application/json; charset=utf-8",
		success:function(res){
			console.log("show findAccountCategoryList"+ JSON.stringify(res.data));
			self.inputCustomerSegment = new ListboxBillingGroup("#inputCustomerSegment").data(res.data);
			//self.advInputCustomerSegment = new ListboxBillingGroup("#advInputCustomerSegment").data(res.data);
		}
	});


	(function(){ $(window["setup"]) })();
	return this;
})(jQuery);

function advanceSearchClickEvent() {
	view.advanceSearchMessageDialog.clear();	
	view.advSrcCusNoResultList.hideLoad().clear();
}

/*function advSrcCusNoBtnClickEvent() {
	view.advanceSearchMessageDialog.clear();
	var url = "", params, searchByCustName = false, searchByOrg = false;
	var customer = {"no": view.advSrcCusNo.val(), "id": '3B3687C1B34F72EDE05400144F67BEE6'};
	//if (!view.advSrcCusNo.isEmpty())         { url = "../service/bill-profiles/search/customer"; params = { "customer": customer }}
	//by NSD 27-01-2017
	if (!view.advSrcCusNo.isEmpty())  {
		url = "../findBillProfileCustomer.json";
		params = { "ty": false, "fn": view.advSrcOrgName.val() , "ln": "", "custNo":view.advSrcCusNo.val()};

	}
	else if (!view.advSrcCusTaxId.isEmpty()) { url = "../service/bill-profiles/search/tax"; params = { "tax": view.advSrcCusTaxId.val() } }
	else if (!view.advSrcCusFirstName.isEmpty() || !view.advSrcCusLastName.isEmpty()) { url = "../findBillProfileByName.json"; params = { "ty": true, "fn": view.advSrcCusFirstName.val() , "ln": view.advSrcCusLastName.val() }; searchByCustName = true}
	else if (!view.advSrcOrgName.isEmpty())  { url = "../findBillProfileByName.json"; params = { "ty": false, "fn": view.advSrcOrgName.val() , "ln": "" }; searchByOrg = true }
	else { view.advanceSearchMessageDialog.error(["กรุณากรอกรายละเอียดอย่างน้อย 1 ช่องการค้นหา"]).show(); return; }
	view.advSrcCusNoResultList.clear().showLoad();
	view.advanceSearchMessageDialog.clear().showLoad("ระบบกำลังรอข้อมูลจาก CRM : {timeCnt} วินาที")
	
	function ResponseHandler(res) {
		//console.log('5552701');
		console.log(res);
		//console.log('END2701');
		//view.advSrcCusNoResultList.hideLoad().data($.map(res._embedded.billProfiles, function(o,i){
		view.advSrcCusNoResultList.hideLoad().data($.map(res.data, function(o,i){//by NSD 27-01-2017
			return {
				 "acctNo": o.no
				,"customerId": o.customer.no
				,"customerAccountName": o.customerAccountName
				,"propLabel": o.type
				,"billGroup": o.billGroup + " " + $.trim(o.billGroupFull)
				,"billNo": o.customer.no
			};
		}));
	}
	function CustNameResponseHandler(res) {
		if(res && res.data) {
			view.advSrcCusNoResultList.hideLoad().data($.map(res.data, function(o,i){
				return {
					 "acctNo": o.no
					,"customerId": o.customer.no
					,"customerAccountName": o.customerAccountName
					,"propLabel": o.type
					,"billGroup": o.billGroup + " " + $.trim(o.billGroupFull)
					,"billNo": o.customer.no
				};
			}));
		}
	}
	function OrgResponseHandler(res) {
		if(res && res.data) {
			view.advSrcCusNoResultList.hideLoad().data($.map(res.data, function(o,i){
				return {
					 "acctNo": o.no
					,"customerId": o.customer.no
					,"customerAccountName": o.billFirstName
					,"propLabel": o.type
					,"billGroup": o.billGroup + " " + $.trim(o.billGroupFull)
					,"billNo": o.customer.no
				};
			}));
		}
	}
	
	if(searchByCustName){
		searchByCustName = false;
		get(url, params, CustNameResponseHandler, view.advanceSearchMessageDialog, function(){ view.advanceSearchMessageDialog.hideLoad(); view.advSrcCusNoResultList.hideLoad() });
	} else if(searchByOrg) {
		 searchByOrg = false;
		 get(url, params, OrgResponseHandler, view.advanceSearchMessageDialog, function(){ view.advanceSearchMessageDialog.hideLoad(); view.advSrcCusNoResultList.hideLoad() });
	} else {
		get(url, params, ResponseHandler, view.advanceSearchMessageDialog, function(){ view.advanceSearchMessageDialog.hideLoad(); view.advSrcCusNoResultList.hideLoad() });
	}
}*/

function advSrcCusNoBtnClickEvent() {
	view.advanceSearchMessageDialog.clear();


	var url = "", params, searchByCustId = false, searchByCustName = false, searchByOrg = false;
	//if (!view.advSrcCusNo.isEmpty())         { url = "../service/bill-profiles/search/no-list"; params = { "no": view.advSrcCusNo.val() }}
	/*
	 if (!view.advSrcCusNo.isEmpty())         { url = "../service/cust-profiles/search/no"; params = { "no": view.advSrcCusNo.val() }; searchByCustId = true; }
	 else if (!view.advSrcCusTaxId.isEmpty()) { url = "../service/bill-profiles/search/tax"; params = { "tax": view.advSrcCusTaxId.val() }; }
	 else if (!view.advSrcCusFirstName.isEmpty() || !view.advSrcCusLastName.isEmpty()) { url = "../findBillProfileByName.json"; params = { "ty": true, "fn": view.advSrcCusFirstName.val() , "ln": view.advSrcCusLastName.val() }; searchByCustName = true}
	 else if (!view.advSrcOrgName.isEmpty())  { url = "../findBillProfileByName.json"; params = { "ty": false, "fn": view.advSrcOrgName.val() , "ln": "" }; searchByOrg = true }
	 else { view.advanceSearchMessageDialog.error(["กรุณากรอกรายละเอียดอย่างน้อย 1 ช่องการค้นหา"]).show(); return; }
	 */
	console.log("customerSegmentSearch key="+view.customerSegmentSearch.key());
	if(!view.advSrcCusNo.isEmpty() || !view.advSrcCusTaxId.isEmpty() || !view.advSrcCusFirstName.isEmpty()
	//   || !view.customerSegmentSearch.val()
	){
		url = "../findCustomerProfile.json"; params = {
			"customerFullName": view.advSrcCusFirstName.val() ,
			"customerNumber": view.advSrcCusNo.val(),
			"idRegisterNumber": view.advSrcCusTaxId.val(),
			"catCustomerSegment": view.customerSegmentSearch.key()
		};

	}else{
		view.advanceSearchMessageDialog.error(["กรุณากรอกรายละเอียดอย่างน้อย 1 ช่องการค้นหา"]).show(); return;
	}

	view.advSrcCusNoResultList.clear().showLoad();
	view.advanceSearchMessageDialog.clear().showLoad("ระบบกำลังรอข้อมูลจาก CRM : {timeCnt} วินาที")
	//alert("xxx "+view.advSrcCusNo.isEmpty());
	/*
	 if (!view.input.advSrcCusNo.isEmpty())         { url = "../service/cust-profiles/search/no"; params = { "no": view.input.advSrcCusNo.val() }; searchByCustId = true; }
	 else if (!view.input.advSrcCusTaxId.isEmpty()) { url = "../service/bill-profiles/search/tax"; params = { "tax": view.input.advSrcCusTaxId.val() } }
	 else if (!view.input.advSrcCusFirstName.isEmpty() || !view.input.advSrcCusLastName.isEmpty()) { url = "../findBillProfileByName.json"; params = { "ty": true, "fn": view.input.advSrcCusFirstName.val() , "ln": view.input.advSrcCusLastName.val() }; searchByCustName = true}
	 else if (!view.input.advSrcOrgName.isEmpty())  { url = "../findBillProfileByName.json"; params = { "ty": false, "fn": view.input.advSrcOrgName.val() , "ln": "" }; searchByOrg = true }
	 else { view.dialog.advanceSearchMessageDialog.error(["กรุณากรอกรายละเอียดอย่างน้อย 1 ช่องการค้นหา"]).show(); return; }
	 view.table.advSrcCusNoResultList.clear().showLoad();
	 view.dialog.advanceSearchMessageDialog.clear().showLoad("ระบบกำลังรอข้อมูลจาก CRM : {timeCnt} วินาที")
	 */
	function ResponseHandler(res) {
        console.log('first res');console.log(res);
		view.advSrcCusNoResultList.hideLoad().data($.map(res._embedded.billProfiles, function(o,i){
			return {
				"acctNo": o.no
				,"customerId": o.customer.no
				,"customerAccountName": o.customerAccountName
				,"customerTaxNo": o.taxRegisterNo
				,"customerSegment": (o.customer.segment!=null)?o.customer.segment.text:""
				,"billNo": o.customer.no
				,"customerSegmentId":o.customer.segment.id
			};
		}));
	}

	function CustNoResponseHandler(res) {
       // console.log('5555 first res custno');console.log(res);
		if(res && res._embedded.customerProfiles) {
			var customer = res._embedded.customerProfiles[0];
			console.log("into CustNoResponseHandler-->"+customer.id);
			console.log(customer);
			var url = "../service/bill-profiles/search/custId", params = { "custId": customer.id };
			get(url, params, ResponseHandler, view.advanceSearchMessageDialog, function(){ view.advanceSearchMessageDialog.hideLoad(); view.advSrcCusNoResultList.hideLoad() });
		}
	}
	function CustNameResponseHandler(res) {
        //console.log('5555 first res custname');console.log(res);
		if(res && res.data) {
			view.advSrcCusNoResultList.hideLoad().data($.map(res.data, function(o,i){
				return {
					// "acctNo": o.no
					"customerId": o.customer.no
					,"customerAccountName": o.customer.fullName
					,"customerTaxNo": o.customer.cardNo
					,"customerSegment": (o.customer.segment!=null)?o.customer.segment.text:""
					,"customerSegmentId":o.customer.segment.id
					,"addresses":o.customer.addresses
					,"groupCode":o.customer.groupCode
					,"groupName":o.customer.groupName
				};
			}));
		}
	}

	function OrgResponseHandler(res) {
        //console.log('5555 first res custno');console.log(res);
		if(res && res.data) {
			view.advSrcCusNoResultList.hideLoad().data($.map(res.data, function(o,i){
				//console.log(o.customer.segment);
				return {
					"acctNo": o.no
					,"customerId": o.customer.no
					,"customerAccountName": o.customerAccountName
					,"customerTaxNo": o.taxRegisterNo
					,"customerSegment": (o.customer.segment!=null)?o.customer.segment.text:""
					,"billNo": o.customer.no
					,"customerSegmentId":o.customer.segment.id
				};
			}));
		}
	}
	console.log(" active search searchByCustName["+searchByCustName+"]")
	console.log(" active search searchByOrg["+searchByOrg+"]")
	console.log(" active search searchByCustId["+searchByCustId+"]")
	get(url, params, CustNameResponseHandler, view.advanceSearchMessageDialog, function(){ view.advanceSearchMessageDialog.hideLoad(); view.advSrcCusNoResultList.hideLoad() });
}

function SelectButton(val, row, ind) {
	var row1 = JSON.stringify(row);//JSON.stringify(row);
	row1 = row1.replace("'", "");
	//row1 = row1.replace(/[\\"']/g, '\\$&').replace(/\u0000/g, '\\0');
	return "<a class='btn btn-success btn-xs' onclick='BillSelectButtonEvent("+row1 +")'>เลือก</a>";
}
function BillSelectButtonEvent(bill) {
	//var bill = JSON.parse(bill1);
	//alert('access testing');
   // console.log('xxxx');console.log(bill);console.log('xxxx');
    view.inputReceiptCustNo.val(bill.customerId);
    view.inputReceiptCustName.val(bill.customerAccountName);
    view.inputReceiptTaxId.val(bill.customerTaxNo);
    view.inputCustomerGroupCode.val(bill.groupCode);
    view.inputCustomerGroupName.val(bill.groupName);
	view.inputCustomerSegment.key(bill.customerSegmentId);

	var addr = bill.addresses[0];
	//view.inputReceiptAddress1.val($.trim(addr.no) +" "+ $.trim(addr.moo) +" "+ $.trim(addr.village) +" "+ $.trim(addr.soi) +" "+ $.trim(addr.road) +" "+ $.trim(addr.tumbon) +" "+ $.trim(addr.amphur) +" "+ $.trim(addr.province) +" "+ $.trim(addr.postCode));

	function ResponseHandler(res) {
        //alert('response');console.log('query address');
		//console.log('zzz5555');
		console.log(res);
		addr = res._embedded.customerAddresses[0];
		//console.log(res._embedded.customerAddresses);console.log('zzz5555');
		view.inputReceiptAddress1.val($.trim(addr.no) +" "+ $.trim(addr.moo) +" "+ $.trim(addr.village) +" "+ $.trim(addr.soi) +" "+ $.trim(addr.road) +" "+ $.trim(addr.tumbon) +" "+ $.trim(addr.amphur) +" "+ $.trim(addr.province) +" "+ $.trim(addr.postCode));
		//if(bill.customerAccountName)
		view.inputReceiptCustName.val(addr.customerFullName);
	}
	console.log(params);
	var url = "../service/cust-addresses/search/customerNo", params = { "customerNo": bill.customerId };
	get(url, params, ResponseHandler, view.message);
	view.customerSearch.hide();
}
/*function BillSelectButtonEvent(bill) {
	view.inputBillNo.val(bill.customerId);
	view.buttonSearchBillNo.elem.click();
	view.customerSearch.hide();
}*/
function BillSelectButtonWithParamEvent(customerId) {
	view.inputBillNo.val(customerId);
	view.buttonSearchBillNo.elem.click();
	view.customerSearch.hide();
}
function buttonSearchTopupClickEvent() {

	//TEST
// 	view.panelNavigate.slideDown(400);
// 	view.panelSpecific.slideDown(800);
// 	view.panelSummary.slideDown(1200);
// 	view.inputServiceType.val("My CAT Telecom");
// 	view.inputCustomerName.val("Test Offline");
// 	view.inputReceiptCustName.val("Test Offline");
// 	view.inputServiceNo.val(view.inputSearchServiceNo.val());
// 	view.inputServiceDiscount.val(0);
	//END TEST
	//view.panelNavigate.hide();
	//view.panelSpecific.hide();
	//view.panelSummary.hide();

	if ($.isEmptyObject(parent.episVatRate)) {
		getSync("../service/enums/search/category", {"category": "enumCat.10"}, function (res) {
			if (res) {
				$.map(res._embedded.enums, function (v, k) {
					parent.episVatRate[v.mapCode1] = v;
				});
			}
		});
	}
	if (parent.episVatRate["current"]) {
		view.currentVatRate = parseFloat(parent.episVatRate["current"].mapCode2, 10);
	} else {
		view.message.error(["ระบบยังไม่ได้ระบุ Vat Rate สำหรับระบบ EPIS กรุณาติดต่อ admin"]).show();
		return false;
	}
	if (view.inputSearchServiceNo.val() == '') {
		view.message.error(["กรุณากรอกหมายเลขบริการ"]);
	}else{
		view.message.clear();
	get("../findTopupServiceNo.json", {
		"serviceName": view.inputTopupService.val(),
		"serviceNo": view.inputSearchServiceNo.val()
	}, function (res) {
		clearCustomerCriteria();
		var customer = res;
		//console.log('555search555');
		console.log(res);
		//console.log('555search555');
		view.panelNavigate.slideDown(400);
		view.panelSpecific.slideDown(800);
		view.panelSummary.slideDown(1200);
		view.inputServiceType.val(view.inputTopupService.selected().text);
		view.inputCustomerName.val(customer.firstName + " " + customer.lastName);
		view.inputReceiptCustName.val(customer.firstName + " " + customer.lastName);
		view.inputServiceNo.val(view.inputSearchServiceNo.val());
		view.inputServiceDiscount.val(0);
		view.inputPromotion.data(res.data);
		view.inputServiceCharge.data([{
			key: "",
			value: "0",
			text: "กรุณาเลือก"
		}].concat((view.amounts = res.amounts)[0].list));
	}, view.message, function () {
		view.message.clear()
	});
}
 	
}
function clearCustomerCriteria(){
	view.inputReceiptTaxId.val('');
	view.inputReceiptCustBranch.val('');
	view.inputReceiptAddress1.val('');
	view.remark.val('');
	$("#check1").prop("checked", false);
	editReceiptInfo();
}
function inputPromotionChangeEvent() {
	var index = view.inputPromotion.index();
	view.inputServiceCharge.data([{ key: "", value: "0", text: "กรุณาเลือก"}].concat(view.amounts[index].list));
}
function inputServiceChargeChangeEvent() {
	view.inputServiceDiscount.val(view.inputServiceCharge.val())
}
function buttonProceedPaymentClickEvent() {
	var receiptCustomerCustNo = view.inputReceiptCustNo.val();
/*	if(receiptCustomerCustNo == "") {
		view.message.clear().error(["กรุณาตรวจสอบ รายละเอียดใบเสร็จรับเงิน ก่อนดำเนินการต่อไป"]).show();
		return;
	}*/
	if (view.tableTopupServiceList.data().length < 1) {
		view.message.error(["กรุณาเพิ่มหมายเลขบริการ ที่ต้องการทำรายการ อย่างน้อย 1 รายการ"]).show();
		return;
	}
/* 
	console.log("no : "+ view.inputReceiptCustNo.val());
	get("../searchCustomerProfile.json", { "billingNo": view.inputReceiptCustNo.val() }, function(res) {
        var profile = res.data[0];
        if(profile != null) {
            console.log(profile);
            view.inputCustomerGroupCode.val(profile.groupCode);
           // view.inputCustomerGroupName.val(profile.groupName);
        }
    }, view.message, function() { view.buttonSearchBillNo.hideLoad() });
	
	console.log("code : "+ view.inputCustomerGroupCode.val());

	
	 */
	
	var serviceList = [];
	var billingList = [];
	/*if(view.inputReceiptCustName =='' || view.inputReceiptAddress1.val() == '' ){
		fullTypeReceipt = false;
	}else{
		if($('#selectResidential').val().toLowerCase()=='individual'){
			//alert('บุคคลธรรมดา');
			fullTypeReceipt = true;
		}else if($('#selectResidential').val().toLowerCase()=='organization'){
			if(view.inputReceiptTaxId.val() == '' || view.inputReceiptCustBranch.val()==''){
				fullTypeReceipt = false;
			}else{
				//alert('นิติบุคคล');
				fullTypeReceipt = true;
			}
		}else{
			//alert('อย่างอื่น');
			fullTypeReceipt = true;
		}
	}*/

	 /*billingList = [{
		 "custNo": view.inputReceiptCustNo.val()
		,"custName": view.inputReceiptCustName.val()
		,"custType": $('#selectResidential').val()
		,"custTaxId": view.inputReceiptTaxId.val()
		,"custBranch": view.inputReceiptCustBranch.val()
		,"address1": view.inputReceiptAddress1.val()
		,"address2": view.inputReceiptAddress2.val()
		,"vatRate": view.currentVatRate
		,"remark": ""
		,"split": false
		,"isFullTypeReceipt": fullTypeReceipt//document.getElementById('isFullReceipt').checked
		,"serviceList": view.tableTopupServiceList.data()
	}];*/
	serviceList = view.tableTopupServiceList.data();
	//console.log("list : "+serviceList.data());
	$.each(serviceList, function(i, dt){
		var dtList = []; var fullTypeReceipt = false;
		dtList[0] = dt;
		var custType = '';
		if(dt.customerName =='' || dt.address1 == '' ){
			fullTypeReceipt = false;
		}else{
			if(dt.acctCatLkp =='3'){
				fullTypeReceipt = true; //custType = 'INDIVIDUAL';
			}else if(dt.acctCatLkp =='1'){
				if(dt.custTaxId == '' || dt.custBranch==''){
					fullTypeReceipt = false;
				}else{
					fullTypeReceipt = true;
				}
			}else{
				fullTypeReceipt = true;
			}
		}

		var billingObj = {
			"custNo": dt.custNo
			,"custName": dt.customerName
			,"custType": dt.custType
			,"custTaxId": dt.custTaxId
			,"custBranch": dt.custBranch
			,"address1": dt.address1
			,"address2": ""
			,"vatRate": view.currentVatRate
			,"remark": dt.remark
			,"promotion": dt.promotion//by NSD 22-02-2017
			,"split": false
			,"isFullTypeReceipt": fullTypeReceipt//document.getElementById('isFullReceipt').checked
			,"acctCatLkp": dt.acctCatLkp
			,"catCustomerSegment": dt.catCustomerSegment
			,"custCategoryDesc": dt.custCategoryDesc  
			,"glCode" : document.getElementById("inputGlCode").value
			,"groupCode" : dt.groupCode
			,"groupName" : dt.groupName
			,"serviceList": dtList//view.tableTopupServiceList.data()
		};
	billingList.push(billingObj);
	});
	/*billingList = [{
		"custNo": view.inputReceiptCustNo.val()
		,"custName": view.inputReceiptCustName.val()
		,"custType": $('#selectResidential').val()
		,"custTaxId": view.inputReceiptTaxId.val()
		,"custBranch": view.inputReceiptCustBranch.val()
		,"address1": view.inputReceiptAddress1.val()
		,"address2": view.inputReceiptAddress2.val()
		,"vatRate": view.currentVatRate
		,"remark": ""
		,"split": false
		,"isFullTypeReceipt": fullTypeReceipt//document.getElementById('isFullReceipt').checked
		,"serviceList": view.tableTopupServiceList.data()
	}];*/
	view.session("billingList", billingList);
	view.session("inputVat", view.inputVat.val());
	view.session("inputAmount", view.inputAmount.val());
	view.session("vatRate0", parseFloat(view.vatRate0.val()));
	//console.log('55555');
	console.log(billingList);
	//console.log('55555');
	location.href = "pay-7-stap_2.jsp";
}
function buttonAddTopupServiceClickEvent() {
	var amt = parseFloat(view.inputServiceCharge.selected().text);
	var vatAmount = amt*7/107;
	var excAmount = amt - vatAmount;
	var disExcVatAmt = excAmount*view.inputServiceDiscount.val()/100;
	var excAmountIncDisc = excAmount - disExcVatAmt;
	var vatIncDisAmount = excAmountIncDisc*7/100;
	var amtIncDisIncVat = excAmountIncDisc + vatIncDisAmount;
	var custType = '';
	if(view.inputCustomerSegment.key()=='3'){
		custType = 'INDIVIDUAL';
	}else if(view.inputCustomerSegment.key()=='2'){
		custType = 'STATEAGENCY';
	}else{
		custType = 'ORGANIZATION';
	}

	if(validateAddCriteria()){
	view.tableTopupServiceList.insert({
		 "customerName": view.inputReceiptCustName.val()//view.inputCustomerName.val()
		,"serviceNo": view.inputServiceNo.val()
		,"serviceType": view.inputTopupService.val()//view.inputTopupService.selected().text by NSD 16-02-2017
		,"promotion": view.inputPromotion.val()
		,"discount": view.inputServiceDiscount.val()
		//,"amount": stripToNumber(view.inputServiceCharge.selected().text)
		,"amount": amtIncDisIncVat
		,"glAccount": document.getElementById("inputGlCode").value
		//by NSD 01-02-2017
		,"custNo": view.inputReceiptCustNo.val()
		,"custType": custType//$('#selectResidential').val()
		,"custTaxId": view.inputReceiptTaxId.val()
		,"groupCode": view.inputCustomerGroupCode.val()
		,"groupName": view.inputCustomerGroupName.val()
		,"custBranch": view.inputReceiptCustBranch.val()
		,"address1": view.inputReceiptAddress1.val()
		,"excAmount": excAmount
		,"discount": disExcVatAmt
		,"excAmountIncDisc": excAmountIncDisc
		,"vatAmount": vatIncDisAmount
		,"serviceTypeName": view.inputServiceType.val() //by NSD 16-02-2017
		,"remark": view.remark.val()//by NSD 22-02-2017
        ,"amtIncVat": view.inputServiceCharge.key()
        ,"custCategoryDesc": $("#inputCustomerSegment option:selected").text()
        ,"acctCatLkp": view.inputCustomerSegment.key()
        ,"catCustomerSegment": view.inputCustomerSegment.key()
        ,"profitCode": view.inputServiceDepartment.key()
        ,"profitName": $('#inputServiceDepartment').val()

	});
	calculate();
	}
	view.session("isStateAgency", view.inputCustomerSegment.key());// by Maew 05-10-2017
}
function validateAddCriteria(){
	var serviceList = view.tableTopupServiceList.data();
	var serviceNo = view.inputServiceNo.val();
	var isValid = true;
	view.message.clear();
	$.each(serviceList, function(i, dt){
		if(serviceNo == dt.serviceNo){
			view.message.error(["หมายเลขบริการซ้ำกัน"]).show();
			isValid = false;
		}
	});
	//alert('value'+view.inputServiceCharge.val()+' key'+view.inputServiceCharge.key()+' data'+view.inputServiceCharge.data());
	if(view.inputServiceCharge.key() == ""){
		view.message.error(["กรุณาเลือกจำนวนเงิน"]).show();
		isValid = false;
	}
	if(isValid){view.message.clear();}
	return isValid;
}

function calculate() {
	var topupList = view.tableTopupServiceList.data(), totalCharge = 0, discount = 0, inputAmountExcDiscount = 0, proDisc = 0;
	/*console.log('555');
	console.log(topupList);
	console.log('555');*/
	for (var i = 0, m = topupList.length; i < m; i++) {
		totalCharge += topupList[i].amount;
		//discount += topupList[i].discount;
		proDisc += topupList[i].discount;
		inputAmountExcDiscount+= topupList[i].excAmount;//by NSD 06-02-2017
	}
	view.inputAmount.val(totalCharge * 0.93);
	view.inputVat.val(totalCharge * 0.07);
	//by NSD 23-01-2017
	view.inputAmount.val(totalCharge * 100/(100+parseFloat(view.vatRate0.val())));
	view.inputVat.val(totalCharge * parseFloat(view.vatRate0.val())/(100+parseFloat(view.vatRate0.val())));

	view.inputTotalCharge.val(totalCharge);
	//view.inputDiscount.val(discount);
	view.proDiscAmount.val(proDisc);
	view.inputAmountExcDiscount.val(inputAmountExcDiscount);
}

function setup() {
	if (view.utils.queryString()["new"]) {
		view.session("billingList", []);
	}
}

function editReceiptInfo(){
    var check = document.getElementById('check1').checked ;
    if(check == true){
    	view.inputReceiptCustName.enable();
    	view.inputReceiptTaxId.enable();
    	view.inputReceiptCustBranch.enable();
    	view.inputReceiptAddress1.enable();
    	view.inputReceiptAddress2.enable();
		view.inputCustomerSegment.enable();
    	$('#selectResidential').prop("disabled", false);
    }else{
    	view.inputReceiptCustName.disable();
    	view.inputReceiptTaxId.disable();
    	view.inputReceiptCustBranch.disable();
    	view.inputReceiptAddress1.disable();
    	view.inputReceiptAddress2.disable();
		view.inputCustomerSegment.disable(true);
    	$('#selectResidential').prop("disabled", true);
    }
}
function segmentFormatter(val, row, ind) { return (row.segment)? row.segment.text : "-";}
function groupFormatter(val, row, ind) { return (row.group)? row.group.text: "-";}

	$(document).ready(function () {
		var category = 'vat.type';
		var params = {
			"category" : category
		}
		$.ajax({
			type: 'GET',
			//data: JSON.stringify(params),
			url: '../findVatRateByCategory.json?category='+category,
			success: function (data) {
				//view.vatRate.val(parseFloat(data.enumDTO.data[0].mapCode2));
				//alert(view.vatRate.value());
				view.vatRate0.val(parseFloat(data.enumDTO.data[0].mapCode2));
				//alert(view.vatRate0.val());
			}
		})
		
		
		 $("#inputTopupService").change(function(){
			 
		 
			 get("../findGlByServiceCode.json", {
					"serviceCode": view.inputTopupService.val()
				}, function (data) {
					
					console.log(data);
					console.log("glCode : " + data.data[0].glCode);
					document.getElementById("inputGlCode").value = data.data[0].glCode;
				}, view.message, function () {
					//view.message.clear()
				});
		    });
		
		
	});
	
	
	function deleteRow(row){
		//alert(row.serviceNo);
		$('#tableTopupServiceList').bootstrapTable('remove', {
			field: 'serviceNo',
			values: [row.serviceNo]
		});
		calculate();
		/*$('#tableTopupServiceList').bootstrapTable('remove', {
			field: 'serviceNo',
			values: [row.serviceNo]
		});*/
	}
	
</script>