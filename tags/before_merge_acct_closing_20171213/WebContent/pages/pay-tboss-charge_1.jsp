<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>รับชำระค่าบริการหนี้สูญ(TBOSS)</title>
<link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<link href="resources/bootstrap-table/src/bootstrap-table.css"
	rel="stylesheet" type="text/css" />
<link href="resources/custom.css" rel="stylesheet" type="text/css" />
<script src="resources/jquery.min.js" type="text/javascript"></script>
<script src="resources/bootstrap/js/bootstrap.min.js"></script>
<script src="resources/bootstrap-table/src/bootstrap-table.js"></script>
<script src="resources/js/mustache.min.js" type="text/javascript"></script>
<script src="resources/custom.js" type="text/javascript"></script>
</head>

<body>
	<header class="header_page"></header>
	<section class="container-fluid menu">
		<!-- <button class="menu-toggle btn btn-sm btn-default" style="margin-bottom: 5px"><span class="glyphicon glyphicon-step-forward"></span></button> -->
    <%-- <%@include  file="menu.jsp" %> --%>
		<form id="payServiceChargeForm" method="post" class="form-horizontal"
			role="form">
			<div class="row">
				<div class="col-md-12 tab-modefile">
					<ol class="breadcrumb">
						<li><i>รับชำระ  TBOSS</i></li>
						<li class="active">ค้นหาข้อมูลลูกค้า</li>
						<li>สรุปรายการรับชำระเงิน</li>
						<li>เลือกวิธีการรับชำระ</li>
						<li>ผลการรับชำระ</li>
					</ol>
					<div id="mainMessageDialog"></div>
					<div id="billingGroupMessageDialog"></div>
					<ul class="nav nav-tabs" role="tablist">
						<li role="presentation" class="active"><a
							aria-controls="tab_cus" role="tab" data-toggle="tab"><span
								class="glyphicon glyphicon-filter"></span> ค้นหาข้อมูล</a></li>
					</ul>

					<div class="panel panel-default panal-radius">
						<div class="panel-body">
							<div class="row">
								<div class="col-md-12">
									<div class="form-horizontal">
										<div class="form-group">
											<label class="control-label col-sm-2">เลขที่ลูกค้า:</label>
											<div class="col-sm-2">
												<input class="form-control" id="billNo">
											</div>
											<label class="control-label col-sm-2">ใบแจ้งค่าใช้บริการ :</label>
											<div class="col-sm-2">
												<input class="form-control" id="invNo">
											</div>
											<div class="col-sm-4">
												<a id="search" class="btn btn-primary"><span
													class="glyphicon glyphicon-search"></span> ค้นหา</a>
												&nbsp;&nbsp; <a id="advanceSearch" class="btn btn-success"><span
													class="glyphicon glyphicon-zoom-in"></span> ค้นหาเพิ่มเติม</a>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

			<ul id="navigatePanel"
				class="hide list-inline pull-right list-menu-set">
				<li><a id="addPayment" class="btn btn-link"><span
						class="glyphicon glyphicon-plus-sign"></span> เพิ่มรายการรับชำระ</a></li>
				<li><a id="summaryPayment" class="btn btn-link"><span
						class="glyphicon glyphicon-th-list"></span> สรุปรายการรับชำระ</a> <span
					class="badge badge_modefil">1</span></li>
				<li><a id="submitPayment" class="btn btn-link"><span
						class="glyphicon glyphicon-edit"></span> ดำเนินการรับชำระ</a></li>
			</ul>
			<div id="customerPanel" class="hide row">
				<div class="col-md-12 tab-modefile">
					<ul id="customerInfoTab" class="nav nav-tabs" role="tablist">
						<li role="presentation" class="active"><a href="#tab_cus"
							aria-controls="tab_cus" role="tab" data-toggle="tab"><span
								class="glyphicon glyphicon-user"></span> ข้อมูลลูกค้า</a></li>
						<li role="presentation"><a href="#sub_script"
							aria-controls="sub_script" role="tab" data-toggle="tab"><span
								class="glyphicon glyphicon-list"></span> Subscription</a></li>
					</ul>
					<div class="tab-content">
						<div role="tabpanel" class="tab-pane active" id="tab_cus">
							<div class="panel panel-default panal-radius">
								<div class="panel-body">
									<div class="row">
										<div class="col-md-12">
											<div class="form-horizontal">
												<div class="form-group">
													<label class="control-label col-sm-2">เลขที่ลูกค้า
														:</label>
													<div class="col-sm-2">
														<input id="customerNo" class="form-control"
															disabled="disabled">
													</div>
													<input type="hidden" id="souceType"> <label
														class="control-label col-sm-2">ชื่อลูกค้า :</label>
														<input type="hidden" id="customerNoacc">
													<div class="col-sm-6">
														<input id="customerName" class="form-control">
													</div>
													<input type="hidden" id="customerType"><input
														type="hidden" id="acctCatLkp"><input type="hidden"
														id="catCustomerSegment">
												</div>
												<div class="form-group">
													<label class="control-label col-sm-2">Tax ID :</label>
													<div class="col-sm-2">
														<input id="taxId" class="form-control" disable
															maxlength="13">
													</div>
													<label class="control-label col-sm-2">สาขา :</label>
													<div class="col-sm-2">
														<input id="branch" class="form-control" maxlength="5">
													</div>
													<label class="control-label col-sm-2">หน่วยงานติดตามหนี้
														:</label>
													<div class="col-sm-2">
														<input id="inputCollectionUnit" class="form-control"
															disabled>
													</div>
												</div>
												<div class="form-group">
													<label class="control-label col-sm-2">กลุ่มผู้ใช้บริการ
														:</label>
													<div class="col-sm-2">
														<select class="form-control" id="inputCustomerSegment"
															disabled></select>
													</div>
													<label class="control-label col-sm-2">เลขที่ e-GP</label>
													<div class="col-sm-2">
														<input id="egpNo" class="form-control" disabled>
													</div>
													<label class="control-label col-sm-2">ยอดค้างชำระ
														(รวม VAT) :</label>
													<div class="col-sm-2">
														<input id="custInfoInputAccruedAmount"
															class="form-control" disabled>
													</div>
												</div>
												<div class="form-group">
													<label class="control-label col-sm-2">Billing Group
														:</label>
													<div class="col-sm-2">
														<input id="billingGroup" class="form-control" disabled>
													</div>
													<label class="control-label col-sm-2">เลขที่สัญญา
														(บช.1)</label>
													<div class="col-sm-2">
														<input id="egpContract" class="form-control" disabled>
													</div>
													<label class="control-label col-sm-2">ยอดชำระล่วงหน้า
														:</label>
													<div class="col-sm-2">
														<input id="custInfoInputAdvancedAmount"
															class="form-control" disabled>
													</div>
												</div>
												<div class="form-group">
													<label class="control-label col-sm-2">สถานะลูกค้า :</label>
													<div class="col-sm-2">
														<input id="custInfoInputStatus" class="form-control"
															value="Active" disabled>
													</div>
													<label class="control-label col-sm-2"></label>
													<div class="col-sm-2"></div>
													<label class="control-label col-sm-2">สกุลเงิน :</label>
													<div class="col-sm-2">
														<input id="custInfoInputCurrencyCode" class="form-control"
															disabled>
													</div>
												</div>
												<div class="form-group">
													<label class="control-label col-sm-2 col-sm-offset-8">VAT
														Rate :</label>
													<div class="col-sm-2">
														<input id="custInfoInputVatRate" class="form-control"
															disabled>
													</div>
												</div>
											</div>
										</div>
									</div>
									<br />
									<div class="row">
										<div class="col-md-12 tab-modefile">
											<ul id="addressTab" class="nav nav-tabs" role="tablist">
												<li role="presentation" class="active"><a href="#tab1"
													aria-controls="tab1" role="tab" data-toggle="tab"><span
														class="glyphicon glyphicon-home"></span>
														ที่อยู่สำหรับใบเสร็จรับเงิน</a></li>
												<li role="presentation" class=""><a href="#tab2"
													aria-controls="tab2" role="tab" data-toggle="tab"><span
														class="glyphicon glyphicon-home"></span>
														ที่อยู่สำหรับใบแจ้งค่าใช้บริการ</a></li>
												<li role="presentation" class="">
													<div class="checkbox">
														<label> &nbsp;&nbsp;<input
															id="changeReceiptAddress" type="checkbox">เปลี่ยนแปลงที่อยู่ในใบเสร็จรับเงิน/ใบกำกับภาษี
														</label>
													</div>
												</li>
												<li role="presentation" class="">
													<div class="checkbox" style="margin-left: 20px">
														<label> &nbsp;<input id="splitReceiptDocument"
															type="checkbox" value="true">แยกใบเสร็จรับเงิน/ใบกำกับภาษีตามใบแจ้งค่าใช้บริการ
														</label>
													</div>
												</li>
												<li role="presentation" class="">
													<div class="checkbox" style="margin-left: 20px">
														<label> &nbsp;<input
															id="receiptInvoiceDocumentOnly" type="checkbox"
															value="true">ใบกำกับภาษีอย่างเดียว
														</label>
													</div>
												</li>
											</ul>
											<div class="tab-content">
												<div role="tabpanel" class="tab-pane active" id="tab1">
													<textarea id="receiptAddress"
														class="form-control textarea-tab" disabled></textarea>
												</div>
												<div role="tabpanel" class="tab-pane" id="tab2">
													<textarea id="invoiceAddress"
														class="form-control textarea-tab"></textarea>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div role="tabpanel" class="tab-pane" id="sub_script">
							<div class="panel panel-default panal-radius">
								<div class="panel-body">
									<table id="subscriptionList" class="table">
										<thead>
											<tr>
												<th data-running-no="true">#</th>
												<th>ประเภทบริการ</th>
												<th>หมายเลขบริการ</th>
												<th>สถานะ</th>
											</tr>
										</thead>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div id="detailsPanel" class="hide row">
				<div class="col-md-12 tab-modefile">
					<ul id="invoiceDetailsTab" class="nav nav-tabs" role="tablist">
						<li role="presentation" class="active"><a href="#tab-2-1"
							aria-controls="tab-2-1" role="tab" data-toggle="tab"><span
								class="glyphicon glyphicon-briefcase"></span>
								รายการใบแจ้งค่าใช้บริการ</a></li>
						<li role="presentation" class=""><a href="#tab-2-2"
							aria-controls="tab-2-2" role="tab" data-toggle="tab"><span
								class="glyphicon glyphicon-th-list"></span> ประวัติการรับชำระ</a></li>
						<li role="presentation" class=""><a href="#tab-2-3"
							aria-controls="tab-2-3" role="tab" data-toggle="tab"><span
								class="glyphicon glyphicon-tasks"></span> รายการเปลี่ยนแปลง</a></li>
					</ul>
					<div class="tab-content">
						<div role="tabpanel" class="tab-pane active" id="tab-2-1">
							<table id="invoiceList" name="tableinvoiceheader"
								data-maintain-selected="true" class="table table-hover"
								data-toggle="table" data-detail-view="true"
								data-detail-formatter="invoiceServiceFormatter"
								data-pagination="true" data-page-size="5"
								data-page-list="[5, 10, 20, 50, 100, 200]" data-cache="false">
								<thead>
									<tr>
										<th data-field="checked" data-checkbox="true">&nbsp;</th>
										<th data-field="invoiceNo" data-align="center"
											data-sortable="true">เลขที่ใบแจ้ง<br>ค่าใช้บริการ
										</th>
										<th data-field="charge" data-sortable="true"
											data-align="right" data-formatter="numberFormatter">ยอดก่อน<br>ภาษี
										</th>
										<th data-field="discount" data-sortable="true"
											data-align="right" data-formatter="numberFormatter">เงิน<br>ส่วนลด
										</th>
										<th data-field="vat" data-sortable="true" data-align="right"
											data-formatter="numberFormatter">ภาษีมูลค่า<br>เพิ่ม
										</th>
										<th data-field="totalCharge" data-sortable="true"
											data-align="right" data-formatter="numberFormatter">จำนวนเงิน<br>รวมภาษี
										</th>
										<th data-field="totalAdj" data-sortable="true"
											data-align="right" data-formatter="numberFormatter">ยอดเงิน<br>ปรับลด
										</th>
										<th data-field="balanceDue" data-sortable="true"
											data-align="right" data-formatter="numberFormatter">จำนวนเงิน<br>คงค้าง
										</th>
										<th data-field="balanceDue" data-sortable="true"
											data-align="right" data-formatter="moneyInputFormatter">ยอดชำระ</th>
										<th data-field="deduct" data-sortable="true"
											data-align="right" data-formatter="numberFormatter">ภาษีหัก<br>ณ
											ที่จ่าย
										</th>
										<th data-field="billCycle" data-align="center">งวดการใช้งาน</th>
										<th data-field="status" data-align="center"
											data-sortable="true">สถานะ</th>
										<th data-field="issueDt" data-align="center"
											data-sortable="true">วันจัดทำใบแจ้ง<br>ค่าใช้บริการ
										</th>
										<th data-field="dueDt" data-align="center"
											data-sortable="true">วันครบ<br>กำหนด
										</th>
										<th data-field="aging" data-align="center"
											data-sortable="true">ระยะเวลาคงค้าง<br>(วัน)
										</th>

									</tr>
								</thead>
							</table>
						</div>
						<div role="tabpanel" class="tab-pane" id="tab-2-2">
							<table id="historyList" data-toggle="table"
								data-classes="table table-hover table-striped" data-pagination="true" data-page-size="5" data-page-list="[5, 10, 20, 50, 100, 200]" data-cache="false">
								<thead>
									<tr>
										<th data-formatter="runningFormatter">#</th>
										<th data-field="updateDt">วันที่ทำ<br>รายการ
										</th>
										<th data-field="receiptDt">วันที่ออกใบ<br>เสร็จรับเงิน
										</th>
										<th data-field="receiptNo">เลขที่ใบ<br>เสร็จรับเงิน
										</th>
										<th data-field="branch">สถานที่<br>รับชำระ
										</th>
										<th data-field="user">ผู้รับชำระ</th>
										<th data-field="invoiceNo">เลขที่ใบแจ้ง<br>ค่าใช้บริการ
										</th>
										<th data-field="billCycle">งวดการใช้งาน</th>
										<th data-field="totalCharge" data-align="right"
											data-formatter="numberFormatterWithCurrency">ยอดเงินตามใบแจ้ง<br>ค่าบริการ
										</th>
									
										<th data-field="payMethod">วิธีการ<br>รับชำระ
										</th>
										<th data-field="payType">ประเภทการ<br>รับชำระ
										</th>
										<th data-field="totalPaid" data-align="right"
											data-formatter="numberFormatter">ยอดชำระ</th>
										<th data-field="vatAmt" data-align="right"
											data-formatter="numberFormatter2">ภาษีมูลค่าเพิ่ม<br>จากการรับชำระ
										</th>
										<th data-field="status">สถานะ</th>
										<th data-formatter="remarkIconFormatter" data-align="center">หมายเหตุ</th>
									</tr>
								</thead>
							</table>
						</div>
						<div role="tabpanel" class="tab-pane" id="tab-2-3">
							<table id="changeList" data-row-style="rowStyle"
								data-toggle="table"
								data-classes="table table-hover table-striped"
								data-search="true" data-pagination="true" data-page-size="5" data-page-list="[5, 10, 20, 50, 100, 200]" data-cache="false">
								<thead>
									<tr>
										<th data-field="paidDt">วันที่รับชำระเงิน</th>
										<th data-field="invoiceNo">เลขที่ใบแจ้ง<br>ค่าใช้บริการ	</th>
										<th data-field="totalCharge">จำนวนเงิน</th>
										<th data-field="processedDt">วันที่ประมวลผล</th>
										<th data-field="billCycle">งวดการใช้งาน</th>
										<th data-field="status">สถานะ</th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
			</div>
			<div id="summaryPanel" class="hide row">
				<div class="col-md-12">
					<div class="panel panel-default">
						<div class="panel-heading">
							<span class="glyphicon glyphicon-shopping-cart"></span>
							สรุปยอดเงินที่ต้องชำระ
						</div>
						<div class="panel-body">
							<div class="form-horizontal">
								<div class="form-group">
									<label class="control-label col-sm-10">ยอดเงินที่ต้องชำระก่อนภาษีมูลค่าเพิ่ม
										:</label>
									<div class="col-sm-2">
										<input id="balance" class="form-control text-right" disabled>
									</div>
								</div>
								<div class="form-group">

									<label class="control-label col-sm-10">ภาษีมูลค่าเพิ่ม
										:</label>
									<div class="col-sm-2">
										<input id="vat" class="form-control text-right" disabled>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-sm-10">ยอดเงินที่ต้องชำระรวมภาษีมูลค่าเพิ่ม
										:</label>
									<div class="col-sm-2">
										<input id="totalCharge" class="form-control text-right"
											disabled>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-sm-10">ภาษีหัก ณ
										ที่จ่าย :</label>
									<div class="col-sm-2">
										<input id="deduct" class="form-control text-right" disabled>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-sm-2">ข้อความเพิ่มเติมในใบเสร็จ
										:</label>
									<div class="col-sm-4">
										<input id="inputAdditionalRemark" class="form-control">
									</div>
									<div class="control-label col-sm-4">
										<label class="strong"><input type="radio"
											name="discountCheckbox" disabled="disabled" value="1">
											รับภาระภาษีเต็มจำนวน</label>&nbsp;&nbsp;&nbsp;&nbsp; <label
											class="strong"><input type="radio"
											name="discountCheckbox" disabled="disabled" value="2">
											รับภาระภาษีบางส่วน :</label>
										<%--<input type="checkbox" id="checkboxAdditionalDiscount" name="discountCheckbox" disabled>
                                    &nbsp;&nbsp;ลูกค้ารับภาระภาษี &nbsp;&nbsp;<span class="glyphicon glyphicon-lock"></span>--&nbsp;ส่วนลด :</label>
                                <div class="col-sm-2">--%>
									</div>
									<div class="col-sm-2">
										<input id="inputAdditionalDiscount"
											class="form-control text-right" disabled>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			</div>
		</form>
	</section>
	<div class="modal fade" role="dialog" id="customerSearch">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">
						<span class="glyphicon glyphicon-user"></span> ค้นหาลูกค้า
					</h4>
				</div>
				<div class="modal-body">
					<div id="advanceSearchMessageDialog"></div>
					<div class="tab-modefile">
						<ul class="nav nav-tabs" role="tablist">
							<li role="presentation" class="active"><a href="#home"
								aria-controls="home" role="tab" data-toggle="tab">เลขที่ใบแจ้งค่าบริการ</a></li>
							<li role="presentation"><a href="#profiles" id="profile"
								aria-controls="profiles" role="tab" data-toggle="tab">หมายเลขบริการ</a></li>
							<li role="presentation"><a href="#messages" id="message"
								aria-controls="messages" role="tab" data-toggle="tab">ข้อมูลลูกค้า</a></li>
						</ul>
						<div class="panel panel-default">
							<div class="panel-body">
								<div class="tab-content border-tab-content">
									<div role="tabpanel" class="tab-pane active" id="home">
										<div class="form-inline col-md-12">
											<div class="form-group col-md-12">
												<label>เลขที่ใบแจ้งค่าบริการ :</label>
												<div class="input-group col-md-8">
													<input type="text" class="form-control" id="advSrcBillNo"
														placeholder="กรุณาป้อนข้อมูลที่ต้องการค้นหาอย่างน้อย 6 ตัวอักษร">
													<span class="input-group-btn"><a
														id="advSrcBillNoBtn" class="btn btn-info"><span
															class="glyphicon glyphicon-search"></span> ค้นหา</a> </span>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-md-12">
												<table id="advSrcBillNoResultList" data-toggle="table"
													data-pagination="true" data-page-size="5"
													data-page-list="[5, 10, 20, 50, 100, 200]"
													data-cache="false">
													<thead>
														<tr>
															<th data-formatter="runningFormatter">#</th>
															<th data-field="acctNo">เลขที่ใบแจ้งค่าบริการ</th>
															<th data-field="customerId">เลขที่ลูกค้า</th>
															<th data-field="customerAccountName">ชื่อลูกค้า</th>
															<!--
                                                    <th data-field="propLabel">ประเภทบริการ</th>
                                                    -->
															<th data-field="billGroup">Billing Group</th>
															<th data-align="center" data-formatter="SelectButton"></th>
														</tr>
													</thead>
												</table>
											</div>
										</div>
									</div>
									<div role="tabpanel" class="tab-pane" id="profiles">
										<div class="form-horizontal">
											<div class="form-group">
												<!--
                                            <label class="control-label col-sm-2">ประเภทบริการ :</label>
                                            <div class="col-sm-4">
                                                <select id="advSrcSvcType" class="form-control"></select>
                                            </div>
                                            -->

												<label class="control-label col-sm-2">ชื่อบริการ :</label>
												<div class="col-sm-4">
													<select id="advSrcSvcType" class="form-control"></select>
												</div>
											</div>
											<div class="form-group">
												<label class="control-label col-sm-2">หมายเลขบริการ
													:</label>
												<div class="col-sm-4">
													<div class="input-group">
														<input type="text" class="form-control" id="advSrcSvcNo">
														<span class="input-group-btn"><a
															id="advSrcSvcNoBtn" class="btn btn-info"><span
																class="glyphicon glyphicon-search"></span> ค้นหา</a> </span>
													</div>
												</div>
											</div>
											<div class="form-group">
												<label class="control-label col-sm-2"></label>
												<div class="col-sm-4">
													<label class="checkbox-inline"> <input
														type="checkbox" name="advSrcSvcProperty" value="1">
														Property1
													</label> <label class="checkbox-inline"> <input
														type="checkbox" name="advSrcSvcProperty" value="2">
														Property2
													</label>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-md-12">
												<table id="advSrcSvcNoResultList" name="tableproperty"
													class="table table-hover" data-toggle="table"
													data-pagination="true" data-page-size="5"
													data-page-list="[5, 10, 20, 50, 100, 200]"
													data-cache="false">
													<thead>
														<tr>
															<th data-formatter="runningFormatter">#</th>
															<th data-field="propertyOne">หมายเลขบริการ<br>(Property
																One)
															</th>
															<th data-field="propertyTwo">Property Two</th>
															<th data-field="catBillAcctNo">เลขที่ลูกค้า</th>
															<th data-field="customerName">ชื่อลูกค้า</th>
															<!--
                                                    <th data-field="propLabel">ประเภทบริการ</th>
                                                    -->
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
												<label class="control-label col-sm-3">เลขที่ลูกค้า
													(BA) :</label>
												<div class="col-sm-3">
													<input class="form-control" id="advSrcCusNo">
												</div>
												<label class="control-label col-sm-3">เลขประจำตัวผู้เสียภาษี
													:</label>
												<div class="col-sm-3">
													<input class="form-control" id="advSrcCusTaxId">
												</div>
											</div>
											<div class="form-group">
												<label class="control-label col-sm-3">ชื่อลูกค้า :</label>
												<div class="col-sm-3">
													<input class="form-control" id="advSrcCusFirstName">
												</div>
												<label class="control-label col-sm-3">นามสกุล :</label>
												<div class="col-sm-3">
													<input class="form-control" id="advSrcCusLastName">
												</div>
											</div>
											<div class="form-group">
												<label class="control-label col-sm-3">ชื่อนิติบุคคล/ราชการ
													:</label>
												<div class="col-sm-6">
													<input class="form-control" id="advSrcOrgName">
												</div>
												<div class="col-sm-3"></div>
											</div>
											<div class="form-group">
												<label class="control-label col-sm-3">กลุ่มผู้ใช้บริการ
													:</label>
												<div class="col-sm-6">
													<select class="form-control" id="advInputCustomerSegment"></select>
												</div>
												<div class="col-sm-3">
													<a id="advSrcCusNoBtn" class="btn btn-info pull-right"><span
														class="glyphicon glyphicon-search"></span> ค้นหา</a>
												</div>
											</div>
										</div>

										<br>

										<div class="row">
											<div class="col-md-12">
												<table id="advSrcCusNoResultList" class="table table-hover"
													data-toggle="table" data-pagination="true"
													data-page-size="5"
													data-page-list="[5, 10, 20, 50, 100, 200]"
													data-cache="false">
													<thead>
														<tr>
															<th data-formatter="runningFormatter">#</th>
															<th data-field="customerId">เลขที่ลูกค้า (BA)</th>
															<th data-field="customerAccountName">ชื่อลูกค้า/ชื่อนิติบุคคล/ราชการ</th>
															<th data-field="customerTaxNo">TAX ID</th>
															<!--
                                                    <th data-field="propLabel">ประเภทบริการ</th>
                                                    -->
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
						กรุณาป้อนข้อมูลที่ต้องการค้นหาอย่างน้อย 6 ตัวอักษร </i> <br>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" role="dialog" id="remarkModal">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">หมายเหตุ</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-md-12">
							<span id="remark"></span>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<a class="btn btn-default" data-dismiss="modal"><span
						class="glyphicon glyphicon-ok-circle"></span> Ok</a>
				</div>
			</div>
		</div>
	</div>
	<script id="template" type="x-tmpl-mustache">
            <table class="table table-striped table-bordered" style="width:70% !important">
                <thead>
                    <tr>
                        <th class="text-center">รหัสผลิตภัณฑ์</th>
                        <th class="text-center">ชื่อผลิตภัณฑ์</th>
                        <th class="text-center">ผลิตภัณฑ์ย่อย</th>
                        <th class="text-center">ชื่อผลิตภัณฑ์ย่อย</th>
                        <th class="text-center">ประเภทรายได้</th>
                        <th class="text-center">ชื่อประเภทรายได้</th>
                        <th class="text-center">จำนวนเงินก่อนภาษีมูลค่าเพิ่ม</th>
                    </tr>
                </thead>
                <tbody>
                    {{#invoices}}
                    <tr>
                        <td class="text-center">{{productCode}}</td>
                        <td>{{productName}}</td>
                        <td class="text-center">{{subProductCode}}</td>
                        <td>{{subProductName}}</td>
                        <td>{{revTypeCode}}</td>
                        <td>{{revTypeName}}</td>
                        <td class="text-right">{{amount}}</td>
                    </tr>
                    {{/invoices}}
                </tbody>
            </table>
        </script>
</body>

</html>
<script type="text/javascript">
        var invDisplay = '';
        var view = (function ($) {
            var self = this, defaultErrorMessage = "An error occurred but there is no message response.";
            self.session = function (key, val) { if (!val) { var val = window.sessionStorage.getItem(key); return val && (val.indexOf("{") > -1 || val.indexOf("[") > -1) ? JSON.parse(val) : val } window.sessionStorage.setItem(key, ($.type(val) != "object" && $.type(val) != "array" ? val : JSON.stringify(val))) };
            self.utils = {
                guid: function () { function r() { return Math.floor((Math.random() + 1) * 0x10000).toString(16).substring(1) } return r() + r() + "-" + r() + "-" + r() + "-" + r() + "-" + r() + r() + r() },
                numberFormat: function (num, dec) { return $.type(num) !== "number" ? "0.00" : num.toFixed(dec || 2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,'); },
                dateFormat: function () { var obj = arguments[0]; if ($.type(obj) == "string" && /[0-9]{2}.[0-9]{2}.[0-9]{4}/g) { return obj } else if ($.isNumeric(obj)) { var dt = new Date(obj), dd = dt.getDate().toString(), mm = (dt.getMonth() + 1).toString(), yyyy = dt.getFullYear().toString(); return (dd[1] ? "" : "0") + dd + "/" + (mm[1] ? "" : "0") + mm + "/" + yyyy } else if ($.type == "date") { return "" } return "" },
                dateTimeFormat: function () { var obj = arguments[0], dt = $.type(obj) != "date" ? ($.isNumeric(obj) ? new Date(obj) : new Date()) : new Date(), dd = dt.getDate().toString(), MM = (dt.getMonth() + 1).toString(), yyyy = dt.getFullYear().toString(), hh = dt.getHours().toString(), mm = dt.getMinutes().toString(), ss = dt.getSeconds().toString(); return (dd[1] ? "" : "0") + dd + "/" + (MM[1] ? "" : "0") + MM + "/" + yyyy + " " + (hh[1] ? "" : "0") + hh + ":" + (mm[1] ? "" : "0") + mm + ":" + (ss[1] ? "" : "0") + ss },
                queryString: function () { var pos = location.href.indexOf("?"), obj = {}; if (pos < 0) return obj; var params = location.href.slice(pos + 1).split("&"); for (var i = 0, m = params.length; i < m; i++) { pos = params[i].indexOf("="); if (pos < 0) { obj[params[i]] = true; continue; } obj[params[i].substring(0, pos)] = params[i].substring(pos + 1) } return obj },
                window: function (windowName, url, side) { window.windowOpened = (window.windowOpened || {}); var screenProp = ""; if (side == "left") { screenProp = "left=0,top=0,width=" + (screen.width / 2) + ",height=" + (screen.height - 100) } else if (side == "right") { screenProp = "left=" + (screen.width / 2 - 40) + ",top=0,width=" + (screen.width / 2 + 40) + ",height=" + (screen.height - 100) } window.windowOpened[windowName] = window.open(url, windowName, "menubar=no,scrollbars=no,resizable=0," + screenProp, false); return window.windowOpened }
            };
            //// AUTOMATIC REGISTER FORMATTER FUNCTION ////
            window.get = function (url, params, success, msgDialog, preCheck) { $.ajax({ "url": url, "type": "GET", "data": params, "error": (msgDialog || { "valid": function (jqXHR, textStatus, errorThrow) { console.log(jqXHR); console.log("textStatus: " + textStatus); console.log("errorThrow:"); console.log(errorThrow) } }).valid, "success": function (res) { var isNotJson = res.constructor == String; console.log(res); (preCheck || function (o) { })(res); if (isNotJson) res = { "statusCode": "99", "errorList": ["No matching data found."] }; if (msgDialog && !msgDialog.valid(res)) { return; } success(res) } }) }
            window.post = function (url, params, success, msgDialog, preCheck) { $.ajax({ "url": url, "type": "POST", "data": params, "error": (msgDialog || { "valid": function (jqXHR, textStatus, errorThrow) { console.log(jqXHR); console.log("textStatus: " + textStatus); console.log("errorThrow:"); console.log(errorThrow) } }).valid, "success": function (res) { var isNotJson = res.constructor == String; console.log(res); (preCheck || function (o) { })(res); if (isNotJson) res = { "statusCode": "99", "errorList": ["No matching data found."] }; if (msgDialog && !msgDialog.valid(res)) { return; } success(res) } }) }
            window.add = function (num1, num2, dec) { if (!dec) dec = 2; return parseFloat((num1 + num2).toFixed(dec), 10); }; window.subtract = function (num1, num2, dec) { if (!dec) dec = 2; return parseFloat((num1 - num2).toFixed(dec), 10); }; window.multiply = function (num1, num2, dec) { if (!dec) dec = 2; return parseFloat((num1 * num2).toFixed(dec), 10); }; window.divide = function (num1, num2, dec) { if (!dec) dec = 2; return parseFloat((num1 / num2).toFixed(dec), 10); }
            window.find = function (array, propName, obj) { var tmp; for (var i = 0, m = array.length; i < m; i++) { if (array[i][propName] == obj[propName]) tmp = array[i]; } if (tmp == null) { array.push(tmp = obj); } return tmp; }
            window.toMap = function (array, propNames) { var map = {}, prop; function key(obj) { var p = []; for (var i = 0, m = propNames.length; i < m; i++) { p.push(obj[propNames[i]]) } return p.join() }; for (var i = 0, m = array.length; i < m; i++) { prop = key(array[i]); map[prop] = array[i] } return map }
            window.stripToNumber = function (str) { return $.type(str) === "string" && $.isNumeric(str = str.replace(/[^0-9\.]/g, "")) ? parseFloat(str, 10) : 0 }
            window.runningFormatter = function (val, row, ind) { return ind + 1 }
            window.numberFormatter = function (val, row, ind) { return !$.isNumeric(stripToNumber(val)) ? "0.00" : parseFloat(val, 10).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,') }
            window.numberFormatterWithCurrency = function (val, row, ind) {
                var currencyCode = "฿";
                if (row.currencyCode == "1") {
                    currencyCode = "$";
                }
                return !$.isNumeric(stripToNumber(val)) ? "0.00" + currencyCode : currencyCode + parseFloat(val, 10).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,')
            }
            window.numberFormatter2 = function (val, row, ind) {
                return !$.isNumeric(stripToNumber(val)) || val == 0 ? "-" : parseFloat(val, 10).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,')
            }
            window.stringInputFormatter = function (val, row, ind) { return '<input value="' + $.trim(val) + '" maxLength="40" class="form-control">' }
            window.numberInputFormatter = function (val, row, ind) { return '<input value="' + self.utils.numberFormat(parseFloat(val || "0.00", 10)) + '" maxLength="10" style="width:100px" class="form-control text-right" onkeypress="return $.isNumeric(String.fromCharCode(event.which)) || (event.which == 46 && this.value.indexOf(\'.\') == -1)" onfocus="(function(o){ o.value = o.value.replace(/[,]/g, \'\') })(this)" onblur="(function(o){ o.value = parseFloat(o.value, 10).toFixed(2).replace(/(\\d)(?=(\\d{3})+\\.)/g, \'$1,\'); })(this)">' }
            window.moneyInputFormatter = function (val, row, ind) { var value = parseFloat(val || "0.00", 10).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, "$1,"); var currencySymbol = "฿"; if (row != null) { var currencySymbol = chkCurrencySymbol(row.currencyCode); console.log("symbol is >>>>>> " + currencySymbol) }; return '<div class="input-group"><span class="input-group-addon" id="basic-addon1" style="padding:2px 4px;">' + currencySymbol + '</span><input value="' + value + '" id="received_' + ind + '"  maxLength="10" style="width:100px" class="form-control text-right" onkeypress="return $.isNumeric(String.fromCharCode(event.which)) || (event.which == 46 && this.value.indexOf(\'.\') == -1)" onkeyup="if (event.which == 13) this.blur()" onfocus="(function(o){ o.value = o.value.replace(/[,]/g, \'\'); o.select() })(this)" onblur="(function(o){ o.value = parseFloat(o.value, 10).toFixed(2).replace(/(\\d)(?=(\\d{3})+\\.)/g, \'$1,\'); o.style.backgroundColor = o.value !== \'' + value + '\' ? \'yellow\' : \'\'; var table, elem = o; while (table == null) { elem = elem.parentNode; if (elem.tagName == \'TABLE\') table = elem; } (window[table.id +\'InputBlurEvent\'] || function(t){ console.log(t) })(table) })(this)"></div>' }
            window.modifyButtonFormatter = function (val, row, ind) { return '<a class="btn btn-link modList" onclick=\'(function(elem, row, ind){ var table = $("#"+ $(elem).closest("table")[0].id); window[table[0].id +"ModifyEvent"](table, row, ind) })(this, ' + JSON.stringify(row) + ', ' + ind + ')\'><span class="glyphicon glyphicon-pencil"></span></a>' }
            window.removeButtonFormatter = function (val, row, ind) { return '<a class="btn btn-link delList" onclick=\'(function(elem, row, ind){ $("#"+ $(elem).closest("table")[0].id).bootstrapTable("remove", { field: "index", values: [ind] }) })(this, ' + JSON.stringify(row) + ', ' + ind + ')\'><span class="glyphicon glyphicon-trash"></span></a>' }
            window.LoadingPanel = function (id) { var obj = this, loadCnt = 0, setupFunc, loadFunc; function checkElemReady() { if ((obj.elem = $("#" + id).css("font", "")).length != 1) { alert("The element hasn't insert into HTML DOM."); throw "The element hasn't insert into HTML DOM."; } clearTimeout(setupFunc); clearInterval(loadFunc) }; return { "elem": null, "finish": function (html) { checkElemReady(); (this.elem ? this.elem : this.elem = $("#" + id)).css("font", "").html(html); return this }, "toString": function () { var elem; setupFunc = setTimeout(function () { loadFunc = setInterval(function () { (elem || (elem = document.getElementById(id))).innerHTML = "Loading" + Array(++loadCnt).join(".."); if (loadCnt > 60) loadCnt = 2 }, 250) }, 1000); return "<div id='" + id + "' style='font:BOLD 16pt Arial'></div>" } } }
            window.remarkIconFormatter = function (val, row, ind) { if (row.remark != null) { return '<a  style="cursor: pointer" onclick="modalPopupRemark(' + "'" + row.remark + "'" + ')"><span class="glyphicon glyphicon-envelope" aria-hidden="true"></span></a>'; } }
            function Button(el) {
                var obj = this, badge; obj.el = el; obj.elem = $(el);
                obj.hide = function () { this.elem.addClass("hide"); return this }; obj.show = function () { this.elem.removeClass("hide"); return this };
                obj.hideLoad = function () { obj.elem.button("reset"); return this }; obj.showLoad = function () { obj.elem.button("loading"); return this };
                obj.disable = function (flag) { if (flag == null || flag) this.elem.addClass("disabled").attr("disabled", true); else this.elem.removeClass("disabled").attr("disabled", false); return this }; obj.enable = function () { obj.disable(false); return this };
                obj.badge = function (val) { if (badge) badge.text(val) }; if ((badge = this.elem.next()).length == 0) badge = null;
                obj.elem.click(window[el.substring(1) + "ClickEvent"]);
            }
            function Message(el) {
                var obj = this, timeCnt = 0, loadFunc; obj.el = el; obj.elem = $(el);
                obj.hide = function () { obj.elem.addClass("hide"); return obj };
                obj.show = function (flag) { if (flag == null || flag) obj.elem.removeClass("hide"); else obj.hide(); return obj };
                obj.clear = function () { obj.elem.find("*").remove(); obj.hide(); return obj };
                obj.message = function (arr, cls) { $.each(arr, function (i, o) { var m = o; if ($.type(o) === "object") { m = (o.desc || o.messageDesc) + " [code=" + (o.code || o.messageCode) + "]" }; obj.elem.append('<div class="' + cls + '">' + m + '<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button></div>') }); window.scrollTo(0, 0); return obj };
                obj.error = function (arr) { return obj.message(arr, "alert alert-danger") };
                obj.warn = function (arr) { return obj.message(arr, "alert alert-warning") };
                obj.success = function (arr) { return obj.message(arr, "alert alert-success") };
                obj.valid = function (jqXHR, textStatus, errorThrow) { var res = jqXHR; obj.stopLoad(); if (jqXHR && jqXHR.responseJSON) res = jqXHR.responseJSON; if (res) { var isNoData = false; if (res.statusCode && res.statusCode != "0") { obj.warn(res && $.type(res.warningList) === 'array' ? res.warningList : []).error(res && $.type(res.errorList) === 'array' ? res.errorList : ["An error occurred on the server side but there is no error message returned."]).show(); return false } if (res.data && res.statusCode == '0' && res.data.length < 1) isNoData = true; if ($.type(res._embedded) === 'object' && $.map(res._embedded, function (v, k) { return v }).length < 1) isNoData = true; if (isNoData) { return false } return true } obj.error(["An error occurred on the server side but there is no error message returned."]).show(); return false }
                obj.hideLoad = function () { obj.stopLoad().clear(); return this }; obj.stopLoad = function () { clearInterval(loadFunc); return this }
                obj.showLoad = function (msg) { obj.elem.append('<div id="' + obj.el + '-loading" class="alert alert-warning">' + bind(msg, 0) + '</div>'); timeCnt = 0; var elem = document.getElementById(obj.el + "-loading"); loadFunc = setInterval(function () { elem.innerHTML = bind(msg, ++timeCnt) }, 1000); obj.show(); return this }
                function bind(msg, timeCnt) { return msg.replace(/\{timeCnt\}/g, timeCnt) }
            }
            function Panel(el, hide) {
                var obj = this, dura = 500, counter = 0, cntFunc; obj.el = el; obj.elem = $(el); if ($.inArray(obj.elem[0].tagName.toLowerCase(), ["ol", "ul"]) > -1) { obj.elem.removeAttr("id").css("display", "").removeClass("hide").removeClass("hidden"); obj.elem = obj.elem.wrap('<div id="' + el.substring(1) + '"></div>').parent(); } var children = obj.elem.children(), bodyEl = el.substring(1) + '-body', body = obj.elem.append('<div style="' + (hide == null || hide ? "display: none" : "") + '" id="' + bodyEl + '"></div>').find("#" + bodyEl).append(children), progressType, progress = obj.elem.append('<div class="progress" style="display: none"><div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width: 0%"><span class="sr-only">0% Complete</span></div></div>').find(".progress");
                obj.center = function () { obj.elem.removeClass(["text-left", "text-right"]).addClass("text-center"); return this }; obj.right = function () { obj.elem.removeClass(["text-left", "text-center"]).addClass("text-right"); return this }
                obj.hide = function (ms) { if (isHidden()) return this; body.hide(ms || dura); return this }; obj.show = function (ms) { if (!isHidden()) return this; if (!ms || !$.isNumeric(ms)) ms = dura; if (ms >= 0) body.css("display", "none").removeClass("hide").removeClass("hidden").show(ms); else obj.hide(Math.abs(ms)); return this }
                obj.slideDown = function (ms) { if (isHidden()) { body.css("display", "none").removeClass("hide").removeClass("hidden").slideDown(ms || dura, arguments[1] || function () { }) } return this }; obj.slideUp = function (ms) { if (!isHidden()) { body.slideUp(ms || dura, arguments[1] || function () { }) } return this }
                obj.progress = function () {
                    return {
                        "elem": progress, "show": function (ms) { body.slideUp(ms || 10); progress.slideDown(); return this }, "hide": function (ms) { body.slideDown(ms || 10); progress.slideUp(); return this }
                        , "success": function () { return this.style("progress-bar-success") }, "info": function () { return this.style("progress-bar-info") }, "warn": function () { return this.style("progress-bar-warning") }, "error": function () { return this.style("progress-bar-danger") }, "plain": function () { return obj.style("") }, "style": function (clsName) { $(this.elem[0].childNodes[0]).removeClass(progressType).addClass(progressType = clsName); return this }
                        , "percent": function (perc) { if (!$.isNumeric(perc) || (perc > 100 || perc < 0)) return this; this.elem[0].childNodes[0].style.width = perc + "%"; return this }
                        , "timeCnt": function (seconds) { var o = this; this.percent(counter = 0); cntFunc = setInterval(function () { o.percent(100 / seconds * ++counter); if (seconds < counter) { clearInterval(cntFunc); o.hide(1000) } }, 1000); return this }
                    }
                }; function isHidden() { return body.css("display") === "none" || body.hasClass("hide") || body.hasClass("hidden") }; obj.elem.css("display", "").removeClass("hide").removeClass("hidden");
            }
            function CheckBox(el) {
                var obj = this; obj.el = el; obj.elem = $(el); obj.elem.click(window[obj.elem.attr("name") + "ClickEvent"])
                obj.disable = function () { obj.elem.attr("disabled", arguments.length == 0 || (arguments.length == 1 && (arguments[0] == "true" || arguments[0] == true))); return this }
                obj.contains = function (val) { return $.inArray(val, obj.val()) > -1 };
                obj.val = function () { return obj.elem.filter(":checked").map(function (i, o) { return o.value }) };
                obj.unchecked = function () { obj.elem.each(function (i, o) { o.checked = false }); return this }
            }
            function Input(el, maxLen, propPos) {
                var obj = this; obj.el = el; obj.elem = $(el);
                obj.error = function (flag) { if (arguments.length == 0 || flag) obj.elem.parent().addClass("has-error"); else obj.elem.parent().removeClass("has-error"); return this }
                obj.clear = function () { obj.val(""); return this }; obj.isEmpty = function () { return $.trim(obj.val()) === "" }; obj.isNumeric = function () { return $.isNumeric(obj.val()) }
                obj.nextFocus = function (nextElem) { if (nextElem && "Input|Button".indexOf(nextElem.constructor.name) > -1) { obj.elem.keyup(function (e) { var key = (e.which || e.keyCode || e.charCode || 0); if (key == 13) nextElem.elem.focus(); return true }) } return this };
                obj.click = function (func) { obj.elem.click(func); return this }
                obj.readOnly = function (flag) { obj.elem.attr("readonly", (flag == null ? true : flag)); return this }
                obj.disable = function (flag) { obj.elem.attr("disabled", (flag == null ? true : flag)); return this }
                obj.enable = function () { obj.disable(false); return this }
                obj.val = function () { if (arguments.length == 1) { this.elem.val(arguments[0]) } return $.trim(this.elem.val()) }
                obj.get = function (propName) { if ($.type(propPos[propName]) !== "array" || propPos[propName].length !== 2) return ""; return obj.val().substring(propPos[propName][0], propPos[propName][1]) }
                obj.elem.blur(window[el.substring(1) + "BlurEvent"]); obj.elem.keyup(function (e) { var func = (window[el.substring(1) + "KeyUpEvent"] || function () { }); func((e.which || e.keyCode || e.charCode || 0), obj.elem) }); obj.elem.focus(function () { this.select() }); if ($.isNumeric(maxLen)) { obj.elem.attr("maxLength", maxLen) }
            }
            function NumberInput(el, dec) {
                var obj = this, decimal = (dec == null ? 2 : dec); obj.el = el; obj.elem = $(el).addClass("text-right");
                obj.val = function () { if (arguments.length == 0) return parseFloat(strip(this.elem.val() || "0"), 10); this.elem.val(format(arguments[0])); return this }
                obj.decimal = function (dec) { decimal = dec }; obj.format = format;
                obj.disable = function () { obj.elem.attr("disabled", (arguments.length != 1 ? true : arguments[0])); return obj }; obj.enable = function () { obj.disable(false); return obj }; obj.readOnly = function (flag) { obj.elem.attr("readonly", (flag == null ? true : flag)); return this }
                obj.elem.keypress(function (e) { var key = (e.which || e.keyCode || e.charCode || 0); var ch = String.fromCharCode(key); return "0123456789.".indexOf(ch) > -1 });
                obj.elem.focus(function () { this.value = strip(this.value); this.select() });
                obj.elem.blur(function () { this.value = format(this.value) });
                function format(val) { var str = ($.isNumeric(val) ? parseFloat(val, 10) : obj.val()).toFixed(decimal == 0 ? 1 : decimal).replace(/(\d)(?=(\d{3})+\.)/g, '$1,'); return decimal == 0 ? str.substring(0, str.lastIndexOf(".")) : str };
                function strip(str) { return (str || "").replace(/[,]/g, "") }
                obj.elem.blur(window[el.substring(1) + "BlurEvent"]); obj.elem.keyup(function (e) { var key = (e.which || e.keyCode || e.charCode || 0), func = (window[el.substring(1) + "KeyUpEvent"] || function () { }); func(key, obj.elem); if (key == 13) this.blur() }); obj.elem.focus(function () { this.select() })
                if (obj.elem.val() == "") obj.elem.val("0" + (decimal < 1 ? "" : "." + Array(decimal + 1).join("0")));
            }
            function DropDown(el, url) {
                var obj = this, data = [{ key: "", value: "", text: "Please Select" }]; obj.el = el; obj.elem = $(el);
                obj.data = function (array) { if ($.type(array) == "array") { setup(data = array); return this } return data; };
                obj.init = function (url) { if (url) $.get(url, function (res) { setup(data = res.data) }); else setup(data); return this };
                obj.index = function () { if (arguments.length == 1) { var maxLen = data.length, index = arguments[0]; obj.elem.find("option").each(function (i, opt) { opt.removeAttribute("selected") }).eq(index < 0 ? maxLen + index : index).attr("selected", true); return this } return $.inArray("selected", obj.elem.find("option").map(function (i, opt) { return opt.selected ? "selected" : "no-selected" })) }
                obj.selected = function () { return data[obj.index()]; }; obj.val = function () { return obj.elem.val(); }; obj.key = function () { if (arguments.length == 0) { var key = obj.elem.find("option:selected").data("key"); if (typeof key === "string" && key === "undefined") { var item = data[obj.index()]; key = item && item.key ? item.key : item.id } return key } var key = arguments[0]; for (var i = 0, m = data.length; i < m; i++) { var item = data[i]; if (item.key && item.key == key) { obj.index(i); break; } if (item.id && item.id == key) { obj.index(i); break; } } return this }
                function setup(array) { obj.elem.find("*").remove(); $.each(array, function (i, o) { obj.elem.append('<option data-index="' + i + '" data-key="' + o.key + '" value="' + o.value + '">' + (o.text || o.value) + '</option>') }); }
                data = obj.elem.change(window[el.substring(1) + "ChangeEvent"]).find("option").map(function (i, opt) { return { key: opt.getAttribute("data-key"), value: opt.value, text: opt.textContent } });
            }
            function Listbox(el, url) {
                var obj = this, data = []; obj.el = el; obj.elem = $(el);
                obj.data = function (array) { if ($.type(array) == "array") { setup(data = array); return this } return data; };
                obj.init = function (url) { if (url) $.get(url, function (res) { setup(data = res.data) }); else setup(data); return this };
                obj.index = function () { if (arguments.length == 1) { var maxLen = data.length, index = arguments[0]; obj.elem.find("option").each(function (i, opt) { opt.removeAttribute("selected") }).eq(index < 0 ? maxLen + index : index).attr("selected", true); return this } return $.inArray("selected", obj.elem.find("option").map(function (i, opt) { return opt.selected ? "selected" : "no-selected" })) }
                obj.selected = function () { return data[obj.index()]; }; obj.val = function () { return obj.elem.val(); }; obj.key = function () { if (arguments.length == 0) { var key = obj.elem.find("option:selected").data("key"); if (typeof key === "string" && key === "undefined") { var item = data[obj.index()]; key = item && item.key ? item.key : item.id } return key } var key = arguments[0]; for (var i = 0, m = data.length; i < m; i++) { var item = data[i]; if (item.key && item.key == key) { obj.index(i); break; } if (item.id && item.id == key) { obj.index(i); break; } } return this }
                function setup(array) {
                    //array.unshift({ key: "", value: "กรุณาเลือก"});
                    obj.elem.find("*").remove();
                    obj.elem.append('<option data-index="-1" data-key="-1" value="-1">ทั้งหมด</option>');
                    $.each(array, function (i, o) { obj.elem.append('<option data-index="' + i + '" data-key="' + o.key + '" value="' + o.key + '">' + (o.value) + '</option>') });
                }
                data = obj.elem.change(window[el.substring(1) + "ChangeEvent"]).find("option").map(function (i, opt) { return { key: opt.getAttribute("data-key"), value: opt.value, text: opt.textContent } });
            }
            function ListboxBillingGroup(el, url) {
                var obj = this, data = [{ key: "", value: "", text: "กรุณาเลือก" }]; obj.el = el; obj.elem = $(el);
                obj.data = function (array) { if ($.type(array) == "array") { setup(data = array); return this } return data; };
                obj.init = function (url) { if (url) $.get(url, function (res) { setup(data = res.data) }); else setup(data); return this };
                obj.index = function () { if (arguments.length == 1) { var maxLen = data.length, index = arguments[0]; obj.elem.find("option").each(function (i, opt) { opt.removeAttribute("selected") }).eq(index < 0 ? maxLen + index : index).attr("selected", true); return this } return $.inArray("selected", obj.elem.find("option").map(function (i, opt) { return opt.selected ? "selected" : "no-selected" })) }
                obj.selected = function () { return data[obj.index()]; }; obj.val = function () { return obj.elem.val(); }; obj.key = function () { if (arguments.length == 0) { var key = obj.elem.find("option:selected").data("key"); if (typeof key === "string" && key === "undefined") { var item = data[obj.index()]; key = item && item.key ? item.key : item.id } return key } var key = arguments[0]; for (var i = 0, m = data.length; i < m; i++) { var item = data[i]; if (item.key && item.key == key) { obj.index(i); break; } if (item.id && item.id == key) { obj.index(i); break; } } return this }
                function setup(array) {
                    obj.elem.find("*").remove();
                    obj.elem.append('<option data-index="-1" data-key="-1" value="-1">ทั้งหมด</option>');
                    $.each(array, function (i, o) { obj.elem.append('<option data-index="' + i + '" data-key="' + o.key + '" value="' + o.key + '">' + (o.value) + '</option>') });
                }
                data = obj.elem.change(window[el.substring(1) + "ChangeEvent"]).find("option").map(function (i, opt) { return { key: opt.getAttribute("data-key"), value: opt.value, text: opt.textContent } });
            }
            function BootstrapTable(el, options) {
                var obj = this, rawData = [], successFunc = function () { }, checkEvt = function (e) { console.log(e) }, uncheckEvt = checkEvt; obj.el = el; obj.elem = $(el).bootstrapTable(options = $.extend({ uniqueId: "id", responseHandler: function (res) { if (!res) return []; if (res.constructor === Array) return res; if (!res.data || res.data.constructor !== Array) return []; return res.data }, onLoadSuccess: function (res) { rawData = res; obj.resetView(400); successFunc(res) }, onCheck: window[el.substring(1) + "CheckEvent"], onUncheck: window[el.substring(1) + "UncheckEvent"], onCheckAll: window[el.substring(1) + "CheckAllEvent"], onUncheckAll: window[el.substring(1) + "UncheckAllEvent"] }, options));
                obj.clear = function () { obj.elem.bootstrapTable("removeAll"); return obj }; obj.remove = function (index) { obj.elem.bootstrapTable("remove", { field: "index", values: [index] }); return this }; obj.resetView = function (ms) { setTimeout(function () { obj.elem.bootstrapTable("resetView") }, ms || 200); return this }; obj.isEmpty = function () { return obj.data().length == 0 }
                obj.showLoad = function () { this.elem.bootstrapTable("showLoading"); return this }; obj.hideLoad = function () { this.elem.bootstrapTable("hideLoading"); return this };
                obj.update = function (rec) { var id = rec.id, index = rec.index, row; if ((row = obj.getId(id)) != null) { obj.elem.bootstrapTable("updateRow", { "index": row.index, "row": $.extend(row, rec) }) } else if ((row = obj.data()[index]) != null) { obj.elem.bootstrapTable("updateRow", { "index": index, "row": $.extend(row, rec) }) } else obj.insert(rec); return this }; obj.insert = function (row) { var data = obj.elem.bootstrapTable("getData"); obj.elem.bootstrapTable("insertRow", { "index": data.length, "row": $.extend(row, { "index": data.length }) }); return this }
                obj.find = function (field, value) { var data = obj.elem.bootstrapTable("getData"); return $.map(data, function (o, i) { return o[field] === value ? o : null }) }
                obj.map = function (col) { var resultMap = {}; $.each(obj.data(), function (ind, row) { resultMap[row[col]] = row }); return resultMap }
                obj.rawData = function () { if (arguments.length == 1) { this.elem.bootstrapTable("load", rawData = arguments[0]); return this } return $.map(this.elem.bootstrapTable("getData"), function (row) { return row }) }; obj.getSelections = function () { return obj.elem.bootstrapTable("getAllSelections") }
                obj.data = function () { var data = []; obj.elem.find("tbody tr").each(function (i, o) { var row = o, record = []; for (var j = 0, n = row.childNodes.length; j < n; j++) { var col = row.childNodes[j], val = ""; if (col.childNodes.length != 1) val = ""; else if (col.childNodes[0].nodeType == 3) val = $.trim(col.childNodes[0].textContent); else if (col.childNodes[0].nodeName == "INPUT") { var elm = col.childNodes[0], isText = elm.type == "text"; val = $.trim(isText ? elm.value : ((elm.type == "checkbox" || elm.type == "radio") && elm.checked ? elm.value : "")) } else if (col.childNodes[0].nodeName == "DIV") val = $.trim(col.childNodes[0].childNodes[0].value); record.push(val) } data.push(record) }); return data };
                obj.selected = function () { var data = []; obj.elem.find("tbody tr").find("input[type=checkbox]:checked, input[type=radio]:checked").each(function (i, o) { var row = o.parentNode.parentNode, record = []; for (var j = 0, n = row.childNodes.length; j < n; j++) { var col = row.childNodes[j], val = ""; if (col.childNodes.length != 1) val = ""; else if (col.childNodes[0].nodeType == 3) val = $.trim(col.childNodes[0].textContent); else if (col.childNodes[0].nodeName == "INPUT") val = $.trim(col.childNodes[0].value); else if (col.childNodes[0].nodeName == "DIV") val = $.trim(col.childNodes[0].childNodes[0].value); record.push(val) } data.push(record) }); return data };
                obj.getId = function (id) { var o = obj.elem.bootstrapTable("getRowByUniqueId", id); if ($.type(o) == "array") return null; return o[options.uniqueId] == id ? o : null }; obj.delId = function (id) { obj.elem.bootstrapTable("removeByUniqueId", id); return this }; // options: { uniqueId: "fieldName" }
                obj.filter = function (func) { var filteredRow, filteredData = []; for (var i = 0, m = rawData.length; i < m; i++) { if (filteredRow = func(rawData[i])) filteredData.push(filteredRow) } obj.elem.bootstrapTable("load", filteredData); return this }
                obj.sum = function (isAll, colName) { var sum = 0; $.each(this.elem.bootstrapTable(isAll ? "getData" : "getSelections"), function (i, o) { sum += (o[colName] || 0) }); return sum };
                obj.sumInput = function (index) { var sum = 0; obj.elem.find("tbody tr").each(function (i, o) { var val = o.children[index].children[0].value.replace(/[,]/g, ""); sum += (!$.isNumeric(val) ? 0 : parseFloat(val, 10)) }); return sum }
                obj.http = function (url, urlParams, succFunc) { if (succFunc) successFunc = succFunc; obj.elem.bootstrapTable("refresh", { "url": url, "query": urlParams }); return this }
                obj.expand = function () { obj.elem.find(".detail-icon i.icon-plus").click(); return this }; obj.collapse = function () { obj.elem.find(".detail-icon i.icon-minus").click(); return this };
                obj.checkAll = function () { var checked = arguments.length == 0 || arguments[0] == true || ($.type(arguments[0]) == "boolean" ? arguments[0] : arguments[0] == "true"), data = obj.elem.bootstrapTable("getData"); $.each(obj.elem.bootstrapTable("getOptions").columns[0], function (i, col) { if (col.checkbox) $.each(data, function (j, row) { row[col.field] = checked }) }); return this }; obj.uncheckAll = function () { obj.checkAll(false); return this }
                obj.elem.on("check.bs.table uncheck.bs.table check-all.bs.table uncheck-all.bs.table", window[el.substring(1) + "CheckBoxEvent"])
            }
            function AuthenticationDialog(succFunc, failFunc, url, title, usrLabel, pwdLabel, okBtn, cnBtn) {
                var obj = this, done = succFunc, fail = failFunc, cancel = function () { }; obj.el = "modal_authentication";
                var content = '<div class="row"><div class="col-md-12"><div class="col-md-offset-2 col-md-10"><div class="form-horizontal"><div class="form-group"><label class="col-sm-3 control-label">' + (usrLabel || "User Name :") + '</label><div class="col-sm-6"><input type="text" id="discApprUser" class="form-control"></div></div><div class="form-group"><label class="col-sm-3 control-label">' + (pwdLabel || "Password :") + '</label><div class="col-sm-6"><input type="password" class="form-control"></div></div></div></div></div></div>';
                var hdrCS = '<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>';
                var hdrTT = '<h4 class="modal-title"><span class="glyphicon glyphicon-user"></span> ' + (title || "Authentication") + '</h4>';
                var divMG = '<div class="alert alert-danger hide"></div>';
                var btnOK = '<button type="button" class="btn btn-success"><span class="glyphicon glyphicon-ok-circle"></span> ' + (okBtn || "OK") + '</button>';
                var btnCN = '<button type="button" class="btn btn-danger" data-dismiss="modal"><span class="glyphicon glyphicon-remove-circle"></span> ' + (cnBtn || "Cancel") + '</button>';
                obj.elem = $(document.body).append('<div class="modal fade" role="dialog" data-backdrop="static" id="' + obj.el + '"><div class="modal-dialog"><div class="modal-content"><div class="modal-header">' + hdrCS + hdrTT + '</div><div class="modal-body">' + divMG + content + '</div><div class="modal-footer">' + btnOK + btnCN + '</div></div></div></div>').find("#" + obj.el);
                obj.elem.find("button").eq(1).on({ click: function () { var inputs = obj.elem.find("input"); $.post(url, { username: inputs.eq(0).val(), password: inputs.eq(1).val() }, function (res) { if (!res || res.statusCode != "0") { obj.elem.find(".alert").text("You don't have permission to access this feature. Please contact to Administration for more information.").removeClass("hide"); fail(res); return; } done(res); obj.hide() }) } }).end().filter(function (index) { return index == 0 || index == 2 }).on({ click: function () { cancel() } })
                obj.show = function () { obj.elem.modal("show"); obj.elem.find("div.alert").addClass("hide").text(""); return this }; obj.hide = function () { obj.elem.modal("hide"); return this }
                obj.done = function (func) { done = func; return this }; obj.fail = function (func) { fail = func; return this }; obj.cancel = function (func) { cancel = func; return this }
            }
            function Radio(el) {
                var obj = this; obj.el = el; obj.elem = $(el);
                obj.disable = function () { if (arguments.length == 2 && $.isNumeric(arguments[0])) obj.elem.eq(arguments[0]).attr("disabled", (arguments[1] != null && $.type(arguments[1]) == 'boolean' ? arguments[1] : false)); return this }; obj.enable = function () { obj.disable(false); return this }
                obj.label = function () { return this.elem.filter(":checked").data("label") };
                obj.type = function () { return this.elem.filter(":checked").data("type") };
                obj.val = function () { var s = this.elem.filter(":checked"), val = s.val(); return !val ? s.data("label") : val };
                obj.checked = function (index) { obj.elem.eq(index).attr("checked", true); return this }
                obj.unchecked = function () { obj.elem.each(function (i, o) { o.checked = false }); return this }
                obj.click = function (evt) { obj.elem.each(function (i, o) { $(o).click(function (e) { if (o.checked) evt(o.value) }) }); return this }
            }
            self.button = new (function () {
                var that = this;
                that.search = new Button("#search");
                that.advanceSearch = new Button("#advanceSearch");
                that.advSrcBillNoBtn = new Button("#advSrcBillNoBtn");
                that.advSrcSvcNoBtn = new Button("#advSrcSvcNoBtn");
                that.advSrcCusNoBtn = new Button("#advSrcCusNoBtn");
                that.addPayment = new Button("#addPayment");
                that.summaryPayment = new Button("#summaryPayment");
                that.submitPayment = new Button("#submitPayment");
            });
            self.dialog = new (function () {
                var that = this;
                that.customerSearch = new Modal("#customerSearch");
                that.navigatePanel = new Panel("#navigatePanel");
                that.customerPanel = new Panel("#customerPanel");
                that.detailsPanel = new Panel("#detailsPanel");
                that.summaryPanel = new Panel("#summaryPanel");
                that.mainMessageDialog = new Message("#mainMessageDialog");
                that.advanceSearchMessageDialog = new Message("#advanceSearchMessageDialog");
                that.billingGroupMessageDialog = new Message("#billingGroupMessageDialog");
                that.remarkModal = new Modal("#remarkModal", true);
                function Modal(el) {
                    this.el = el;
                    this.elem = $(el);
                    this.hide = function () { this.elem.modal("hide") };
                    this.show = function () { this.elem.modal("show") };
                }
            });
            self.checkbox = new (function () {
                var that = this;
                that.advSrcSvcProperty = new CheckBox("[name=advSrcSvcProperty]");
                that.splitReceiptDocument = new CheckBox("#splitReceiptDocument");
                that.receiptDocumentOnly = new CheckBox("#receiptDocumentOnly");
                that.receiptInvoiceDocumentOnly = new CheckBox("#receiptInvoiceDocumentOnly");
            });
            self.input = new (function () {
                var that = this;
                that.advSrcBillNo = new Input("#advSrcBillNo");
                that.advSrcSvcType = new Listbox("#advSrcSvcType").init("../findAllPrintingInvDisplay.json");
                that.advSrcSvcNo = new Input("#advSrcSvcNo");
                that.souceType = new Input("#souceType");
                that.customerNoacc = new Input("#customerNoacc");
                that.advSrcCusTaxId = new Input("#advSrcCusTaxId");
                that.advSrcCusFirstName = new Input("#advSrcCusFirstName");
                that.advSrcCusLastName = new Input("#advSrcCusLastName");
                that.advSrcOrgName = new Input("#advSrcOrgName");
                that.billNo = new Input("#billNo", 18);
                that.barcode = new Input("#barcode", 62, { "billNo": [16, 34], "invoiceNo": [34, 52] });
                that.invNo = new Input("#invNo");
                that.customerNo = new Input("#customerNo");
                that.egpNo = new Input("#egpNo");
                that.egpContract = new Input("#egpContract");
                that.customerName = new Input("#customerName");
                that.taxId = new Input("#taxId");
                that.branch = new Input("#branch");
                that.billingGroup = new Input("#billingGroup");
                that.advSrcCusNo = new Input("#advSrcCusNo");
                that.changeReceiptAddress = new Input("#changeReceiptAddress");
                that.receiptAddress = new Input("#receiptAddress");
                that.invoiceAddress = new Input("#invoiceAddress");
                that.balance = new NumberInput("#balance");
                that.discountAmount = new NumberInput("#discountAmount");
                that.beforeDiscountAmount = new NumberInput("#beforeDiscountAmount");
                that.vat = new NumberInput("#vat");
                that.preDiscount = new NumberInput("#preDiscount");
                that.totalCharge = new NumberInput("#totalCharge");
                that.deduct = new NumberInput("#deduct");
                that.val = function () { if (arguments.length == 1) { $.each(arguments[0], function (k, v) { $("#" + k).val(v) }) } }
            });
            self.tab = new (function () {
                var that = this;
                that.customerInfoTab = new Tab("#customerInfoTab");
                that.addressTab = new Tab("#addressTab");
                that.invoiceDetailsTab = new Tab("#invoiceDetailsTab");
                function Tab(el) {
                    var obj = this, index = 0, change = function (e) { console.log(e) }, initEvts = [];
                    obj.el = el;
                    obj.elem = $(el);
                    obj.index = function () { if (arguments.length == 1) { index = arguments[0]; obj.elem.find("li").removeClass("active").find("a").eq(index).click(); return this } return index }
                    obj.show = function (ind) { obj.elem.find("a").eq(ind).tab("show"); return this; }
                    obj.change = function (func) { change = func; return this }
                    obj.init = function (ind, evt) { initEvts[ind] = evt; return obj }
                    obj.elem.find("a").each(function (i, o) { $(o).data("index", i) });
                    obj.elem.find("li").each(function (i, o) { if ($(o).hasClass("active")) index = i; initEvts.push(function () { console.log("index: " + i) }) });
                    obj.elem.on("show.bs.tab", function (e) { index = $(e.target).data("index"); change(e); initEvts[index]() });
                }
            });
            self.radio = new (function () {
                var that = this;
                that.discountCheckbox = new Radio("[name=discountCheckbox]");
            });
            self.table = new (function () {
                var that = this;
                that.advSrcBillNoResultList = new BootstrapTable("#advSrcBillNoResultList");
                that.advSrcSvcNoResultList = new BootstrapTable("#advSrcSvcNoResultList");
                that.advSrcCusNoResultList = new BootstrapTable("#advSrcCusNoResultList");
                that.subscriptionList = new Table("#subscriptionList");
                that.invoiceList = new BootstrapTable("#invoiceList");
                that.historyList = new BootstrapTable("#historyList");
                that.changeList = new BootstrapTable("#changeList");
                function Table(el) {
                    var obj = this, headers = [], data = [], onApn = function () { }, onDel = onApn, loadCnt = 0, loadInt, loadFunc = function () { obj.body.find("div#loading").html("Loading" + Array(++loadCnt).join(".")); if (loadCnt > 8) loadCnt = 0; };
                    var checkboxHtml = '<input type="checkbox" name="{field}" data-index="{index}" value="{value}">', radioHtml = '<input type="radio" name="{field}" data-index="{index}" value="{value}">';
                    obj.el = el; obj.elem = $(el); obj.body = obj.elem.find("tbody");
                    obj.onAppend = function (func) { onApn = func }; obj.onDelete = function (func) { onDel = func };
                    obj.hideLoad = function () { obj.elem.find("tbody tr").remove(); clearInterval(loadInt); return obj };
                    obj.showLoad = function () { obj.elem.find("tbody").append("<tr><td colspan='" + headers.length + "'><div id='loading' style='text-align:center;font:BOLD 16pt Arial'>Loading</div></td></tr>"); loadCnt = 0; loadInt = setInterval(loadFunc, 500); return obj };
                    function reorder(index) { obj.body.find("tr").each(function (i, o) { $(o).find("td").eq(index).text(i + 1) }) }
                    obj.insert = function (array, showRemove, attrs) {
                        if (arguments.length < 1) array = $.map(headers, function (o) { return " " }); var b = obj.elem.find("tbody");
                        b.append("<tr " + $.map($.extend(attrs, {}), function (v, k) { return k + "='" + v + "'" }).join(" ") + ">"
                            + $.map(array, function (v, i) {
                                var field = headers[i].field, value = v;
                                if (headers[i].runningNo) value = (obj.size() + 1);
                                else if (headers[i].numberFormat) { value = !$.isNumeric(v) ? "0.00" : parseFloat(v, 10).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,'); }
                                else if (headers[i].checkbox) value = replace(checkboxHtml, field, i, v);
                                else if (headers[i].radio) value = replace(radioHtml, field, i, v);
                                return '<td><div style="' + (headers[i].align ? "text-align:" + headers[i].align : "") + '">' + value + '</div></td>'
                            }).join("")
                            + (!showRemove ? "" : '<td style="width:40px;text-align:center"><a href="#" class="delList"><span class="glyphicon glyphicon-trash"></span></a></td>')
                            + "</tr>"); return obj
                    };
                    obj.find = function (key, cri) { return obj.elem.find("tbody tr").filter("[" + key + "=" + cri + "]") }
                    obj.clear = function () { obj.elem.find("tbody tr").remove(); return obj }
                    obj.remove = function (index) { this.elem.find("tbody tr").eq(index).remove(); $.each(headers, function (i, o) { if (o.runningNo) reorder(i) }); }
                    obj.data = function () {
                        var data = [];
                        if (arguments.length != 1) {
                            var rows = obj.elem.find("tbody tr");
                            for (var i = 0, m = rows.length; i < m; i++) { var row = []; for (var j = 0, n = rows[i].children.length; j < n; j++) row.push(rows[i].children[j].innerText); data.push(row); }
                            return data;
                        } for (var i = 0, m = (data = arguments[0]).length; i < m; i++) { obj.insert(data[i]); }
                    };
                    obj.size = function () { return obj.elem.find("tbody tr").length };
                    obj.sum = function (index) { var sum = 0; this.elem.find("tbody tr").each(function (i, row) { var val = $(row).find("td").eq(index).text(); sum += (isNaN(val) ? 0 : parseFloat(val, 10)) }); return sum }
                    $(obj.el).on("click", ".delList", function () { $(this).parent().parent().remove(); reorder(); onDel(obj.data()) });
                    obj.elem.find("thead th").each(function (i, o) {
                        var elem = $(o);
                        headers.push({ "field": elem.data("field"), "align": $.trim(elem.data("align")), "runningNo": elem.data("runningNo") === true, "numberFormat": elem.data("numberFormat") === true, "checkbox": elem.data("checkbox") === true, "radio": elem.data("radio") === true })
                    }); function replace(str, field, index, value) { return str.replace("\{field\}", $.trim(field)).replace("\{index\}", index).replace("\{value\}", value) }
                    if (obj.body.length < 1) { obj.elem.append("<tbody></tbody>"); obj.body = obj.elem.find("tbody") }
                }
            });
            self.contextPath = '${pageContext.request.contextPath}/';
            self.acctCatLkp = new Input("#acctCatLkp");
            self.customerType = new Input("#customerType");
            self.catCustomerSegment = new Input("#catCustomerSegment");
            self.custInfoInputAccruedAmount = new NumberInput("#custInfoInputAccruedAmount");
            self.custInfoInputAdvancedAmount = new NumberInput("#custInfoInputAdvancedAmount");
            self.custInfoInputStatus = new Input("#custInfoInputStatus");
            self.custInfoInputCurrencyCode = new Input("#custInfoInputCurrencyCode");
            self.custInfoInputVatRate = new Input("#custInfoInputVatRate");
            self.advancedCheckbox = new CheckBox("[name=advancedCheckbox]");
            self.advancedAmount = new NumberInput("#advancedAmount").disable();
            self.billCycle = new Input("#billCycle").disable();
            self.checkboxAdditionalDiscount = new CheckBox("#checkboxAdditionalDiscount");
            self.inputAdditionalDiscount = new NumberInput("#inputAdditionalDiscount");
            self.inputCollectionUnit = new Input("#inputCollectionUnit");
            self.advanceInvSelected = null;
            $.ajax({
                type: "GET",
                url: "../findAccountCategoryList.json",
                //data: dataSend,
                dataType: "json",
                contentType: "application/json; charset=utf-8",
                success: function (res) {
                    console.log("show findAccountCategoryList" + JSON.stringify(res.data));
                    self.inputCustomerSegment = new ListboxBillingGroup("#inputCustomerSegment").data(res.data);
                    self.advInputCustomerSegment = new ListboxBillingGroup("#advInputCustomerSegment").data(res.data);
                    doQueueSearch();
                }
            });

            self.inputAdditionalRemark = new Input("#inputAdditionalRemark");
            this.dialogAuthentication = new AuthenticationDialog(function (res) { console.log(res); }, function (res) { }, "../checkAuthorize.json");

            return this;
        })(jQuery);

        function doQueueSearch() {
            var queryString = view.session("queryString");
            if (Object.keys(queryString).length > 0) {
                if (queryString.ba != null && queryString.ba != "") {
                    view.input.billNo.val(queryString.ba);
                    searchClickEvent();
                } else if (queryString.txid != null && queryString.txid != "") {
                    view.input.advSrcCusTaxId.val(queryString.txid);
                    view.dialog.customerSearch.show();
                    $("#message").click();
                    advSrcCusNoBtnClickEvent();
                } else if (queryString.sn != null && queryString.sn != "") {
                    view.input.advSrcSvcNo.val(queryString.sn);
                    view.dialog.customerSearch.show();
                    $("#profile").click();
                    advSrcSvcNoBtnClickEvent();
                }
                view.session("queryString", []);
            }
        }

        function advanceSearchClickEvent() {
            view.dialog.advanceSearchMessageDialog.clear();
            // 	view.table.advSrcBillNoResultList.hideLoad().clear();
            // 	view.table.advSrcSvcNoResultList.hideLoad().clear();
            // 	view.table.advSrcCusNoResultList.hideLoad().clear();
        }
        function advSrcBillNoBtnClickEvent() {
            view.dialog.advanceSearchMessageDialog.clear();
            if (view.input.advSrcBillNo.val().length < 6) {
                view.dialog.advanceSearchMessageDialog.error(["โปรดระบุ 'เลขที่ใบแจ้งค่าบริการ' อย่างน้อย 6 ตัวอักษร"]).show();
                return false;
            }
            else {
                var billNo = view.input.advSrcBillNo.val();
                var chs = 0;
                for (var i = 0; i < billNo.length; i++) {
                    var ch = billNo.charAt(i);
                    if (Number(parseFloat(ch)) == ch) { chs += 1; }
                }
                if (chs < 6) {
                    view.dialog.advanceSearchMessageDialog.error(["โปรดระบุ 'เลขที่ใบแจ้งค่าบริการ' มีตัวเลขอย่างน้อย 6 ตัว"]).show();
                    return false;
                }
            }
            view.dialog.advanceSearchMessageDialog.clear();
            view.table.advSrcBillNoResultList.clear().showLoad();
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
                success: function (res) {
                    console.log(res);
                    //console.log("xxx3-->"+JSON.stringify(res))
                    var writeOffInvoiceData = [];
                    if (res.statusCode == '0') {
                        writeOffInvoiceData = $.map(res.data, avdInvoiceListMapper);
                    }
                    console.log("xxx4-->" + writeOffInvoiceData.length)

                    view.table.advSrcBillNoResultList.hideLoad().rawData(writeOffInvoiceData);

                    view.dialog.advanceSearchMessageDialog.hideLoad();
                }
            });

            /*
             get("../findInvoiceByNo.json", { "invNo": view.input.advSrcBillNo.val() }, function(res){
             view.table.advSrcBillNoResultList.hideLoad().rawData($.map(res.data, function(o,i){
             return {
             "acctNo": o.invoiceNo
             ,"customerId": o.billingNo
             ,"customerAccountName": o.billingName
             ,"propLabel": o.customerType
             ,"billGroup": o.billGroup
             ,"billNo": o.billingNo
             };
             }));
             }, view.dialog.advanceSearchMessageDialog, function(){ view.dialog.advanceSearchMessageDialog.hideLoad() });
             */
        }
        function advSrcSvcNoBtnClickEvent() {
            var isProp1 = view.checkbox.advSrcSvcProperty.contains("1");
            var isProp2 = view.checkbox.advSrcSvcProperty.contains("2");
            var svcNo = view.input.advSrcSvcNo.val().replace("[\?\*]", "");
            view.dialog.advanceSearchMessageDialog.clear();
            if (svcNo.length < 4) {
                view.dialog.advanceSearchMessageDialog.error(["กรุณากรอก 'Service No' อย่างน้อย 4 ตัวอักษร"]).show();
                return;
            }
            view.table.advSrcSvcNoResultList.clear().showLoad();
            function ResponseHandler(res) {
                view.table.advSrcSvcNoResultList.hideLoad().rawData($.map(res._embedded.billServices, function (o, i) {
                    return {
                        "propOne": o.propOne
                        , "acctNo": o.profile.no
                        , "acctName": o.profile.customerAccountName
                        , "propLabel": o.propLabel
                        , "billGroup": o.profile.billGroup + " " + $.trim(o.profile.billGroupFull)
                        , "billNo": o.profile.no
                    };
                }));
            }
            view.dialog.advanceSearchMessageDialog.clear();
            view.table.advSrcSvcNoResultList.clear().showLoad();
            view.dialog.advanceSearchMessageDialog.clear().showLoad("ระบบกำลังรอข้อมูลจาก CRM : {timeCnt} วินาที");
            if (view.input.advSrcSvcType.val() == null) var advSrcSvcType = "";
            else var advSrcSvcType = view.input.advSrcSvcType.val().replace(/-1/g, "");
            console.log("kkkk");
            console.log(advSrcSvcType);
            console.log("kkkk");

            $.get("../advanceSearchByService.json", {
                "billingGroups": advSrcSvcType, "serviceNo": svcNo, "isPropertyOne": isProp1, "isPropertyTwo": isProp2
            }, function (res) {
                var filteredData = [];
                for (var i = 0; i < res.data.length; i++) {
                    if (res.data[i].catBillAcctNo != null && res.data[i].catBillAcctNo.length > 0)
                        filteredData.push(res.data[i])
                }
                view.table.advSrcSvcNoResultList.hideLoad().rawData($.map(filteredData, function (o, i) {
                    return {
                        "propertyOne": o.propertyOne
                        , "propertyTwo": o.propertyTwo
                        , "catBillAcctNo": o.catBillAcctNo
                        , "customerName": o.customerName
                        , "billingGroupFull": o.billingGroupFull
                        , "billNo": o.catBillAcctNo
                        , "acctNo": o.catBillAcctNo
                    };
                }));
                //view.table.advSrcSvcNoResultList.hideLoad().rawData(res.data);
                view.dialog.advanceSearchMessageDialog.hideLoad();
                view.table.advSrcSvcNoResultList.hideLoad();
            });
            /*
             if (isProp1 && isProp2) {
             get("../service/bill-svcs/search/both", { "label": view.input.advSrcSvcType.val(), "one": svcNo, "two": svcNo }, ResponseHandler, view.dialog.advanceSearchMessageDialog, function(){ view.dialog.advanceSearchMessageDialog.hideLoad(); view.table.advSrcSvcNoResultList.hideLoad() });
             } else if (isProp1) {
             get("../service/bill-svcs/search/one", { "label": view.input.advSrcSvcType.val(), "one": svcNo }, ResponseHandler, view.dialog.advanceSearchMessageDialog, function(){ view.dialog.advanceSearchMessageDialog.hideLoad(); view.table.advSrcSvcNoResultList.hideLoad() });
             } else if (isProp2) {
             get("../service/bill-svcs/search/two", { "label": view.input.advSrcSvcType.val(), "two": svcNo }, ResponseHandler, view.dialog.advanceSearchMessageDialog, function(){ view.dialog.advanceSearchMessageDialog.hideLoad(); view.table.advSrcSvcNoResultList.hideLoad() });
             } else {
             view.table.advSrcSvcNoResultList.hideLoad();
             view.dialog.advanceSearchMessageDialog.error(["กรุณาเลือก 'Property1' หรือ 'Property2' ก่อนทำการค้นหา"]).show();
             }*/
        }
        function advSrcCusNoBtnClickEvent() {
            view.dialog.advanceSearchMessageDialog.clear();
            var url = "", params, searchByCustId = false, searchByCustName = false, searchByOrg = false;
            /*
            if (!view.input.advSrcCusNo.isEmpty())         { url = "../service/cust-profiles/search/no"; params = { "no": view.input.advSrcCusNo.val() }; searchByCustId = true; }
            else if (!view.input.advSrcCusTaxId.isEmpty()) { url = "../service/bill-profiles/search/tax"; params = { "tax": view.input.advSrcCusTaxId.val() } }
            else if (!view.input.advSrcCusFirstName.isEmpty() || !view.input.advSrcCusLastName.isEmpty()) { url = "../findBillProfileByName.json"; params = { "ty": true, "fn": view.input.advSrcCusFirstName.val() , "ln": view.input.advSrcCusLastName.val() }; searchByCustName = true}
            else if (!view.input.advSrcOrgName.isEmpty())  { url = "../findBillProfileByName.json"; params = { "ty": false, "fn": view.input.advSrcOrgName.val() , "ln": "" }; searchByOrg = true }
            else { view.dialog.advanceSearchMessageDialog.error(["กรุณากรอกรายละเอียดอย่างน้อย 1 ช่องการค้นหา"]).show(); return; }
            view.table.advSrcCusNoResultList.clear().showLoad();
            view.dialog.advanceSearchMessageDialog.clear().showLoad("ระบบกำลังรอข้อมูลจาก CRM : {timeCnt} วินาที")
            */
            if ((!view.input.advSrcCusFirstName.isEmpty() && !view.input.advSrcOrgName.isEmpty())
                || (!view.input.advSrcCusLastName.isEmpty() && !view.input.advSrcOrgName.isEmpty())

            ) {
                view.dialog.advanceSearchMessageDialog.error(["ไม่สามารถค้นหา ชื่อลูกค้า/นามสกุล และ ชื่อนิติบุคคล/ราชการ พร้อมกันได้"]).show(); return;
            }
            //console.log("customerSegmentSearch key="+view.advInputCustomerSegment.key());
            if (!view.input.advSrcCusNo.isEmpty() || !view.input.advSrcCusTaxId.isEmpty() || !view.input.advSrcCusFirstName.isEmpty()
                || !view.input.advSrcCusLastName.isEmpty() || !view.input.advSrcOrgName.isEmpty()
            ) {
                url = "../findBillingProfile.json"; params = {
                    "customerFirstName": view.input.advSrcCusFirstName.val(),
                    "customerLastName": view.input.advSrcCusLastName.val(),
                    "orgName": view.input.advSrcOrgName.val(),
                    "customerNumber": view.input.advSrcCusNo.val(),
                    "idRegisterNumber": view.input.advSrcCusTaxId.val(),
                    "catCustomerSegment": view.advInputCustomerSegment.key()
                };

            } else {
                view.dialog.advanceSearchMessageDialog.error(["กรุณากรอกรายละเอียดอย่างน้อย 1 ช่องการค้นหา"]).show(); return;
            }
            view.table.advSrcCusNoResultList.clear().showLoad();
            view.dialog.advanceSearchMessageDialog.clear().showLoad("ระบบกำลังรอข้อมูลจาก CRM : {timeCnt} วินาที")
            function ResponseHandler(res) {
                view.table.advSrcCusNoResultList.hideLoad().rawData($.map(res._embedded.billProfiles, function (o, i) {
                    return {
                        "acctNo": o.no
                        , "customerId": o.no
                        , "customerAccountName": o.customerAccountName
                        , "propLabel": o.type
                        , "billGroup": o.billGroup + " " + $.trim(o.billGroupFull)
                        , "billNo": o.no
                    };
                }));
            }
            function CustNoResponseHandler(res) {
                if (res && res._embedded.customerProfiles) {
                    var customer = res._embedded.customerProfiles[0];
                    console.log(customer);
                    var url = "../service/bill-profiles/search/custId", params = { "custId": customer.id };
                    get(url, params, ResponseHandler, view.dialog.advanceSearchMessageDialog, function () { view.dialog.advanceSearchMessageDialog.hideLoad(); view.table.advSrcCusNoResultList.hideLoad() });
                }
            }
            function CustNameResponseHandler(res) {
                if (res && res.data) {
                    view.table.advSrcCusNoResultList.hideLoad().rawData($.map(res.data, function (o, i) {
                        return {
                            "acctNo": o.no
                            , "customerId": o.no
                            , "customerAccountName": o.accountCategoryLookup == 3 ? ((o.billFirstName || '') + " " + (o.billLastName || '')) : (o.billCompName || "")
                            , "customerTaxNo": o.taxRegisterNo
                            , "customerSegment": (o.customer.segment != null) ? o.customer.segment.text : ""
                            , "billGroup": o.billGroupFull
                            , "billNo": o.no
                        };
                    }));
                }
            }
            function OrgResponseHandler(res) {
                if (res && res.data) {
                    view.table.advSrcCusNoResultList.hideLoad().rawData($.map(res.data, function (o, i) {
                        return {
                            "acctNo": o.no
                            , "customerId": o.no
                            , "customerAccountName": o.billFirstName
                            , "propLabel": o.type
                            , "billGroup": o.billGroup + " " + $.trim(o.billGroupFull)
                            , "billNo": o.no
                        };
                    }));
                }
            }
            get(url, params, CustNameResponseHandler, view.dialog.advanceSearchMessageDialog, function () {
                view.dialog.advanceSearchMessageDialog.hideLoad(); view.table.advSrcCusNoResultList.hideLoad()
            });
        }
        /*
        if(searchByCustName){
            searchByCustName = false;
            get(url, params, CustNameResponseHandler, view.dialog.advanceSearchMessageDialog, function(){ view.dialog.advanceSearchMessageDialog.hideLoad(); view.table.advSrcCusNoResultList.hideLoad() });
        } else if(searchByOrg) {
            searchByOrg = false;
            get(url, params, OrgResponseHandler, view.dialog.advanceSearchMessageDialog, function(){ view.dialog.advanceSearchMessageDialog.hideLoad(); view.table.advSrcCusNoResultList.hideLoad() });
        } else if(searchByCustId) {
            searchByCustId = false;
            get(url, params, CustNoResponseHandler, view.dialog.advanceSearchMessageDialog, function(){ view.dialog.advanceSearchMessageDialog.hideLoad(); view.table.advSrcCusNoResultList.hideLoad() });
        } else {
            get(url, params, ResponseHandler, view.dialog.advanceSearchMessageDialog, function(){ view.dialog.advanceSearchMessageDialog.hideLoad(); view.table.advSrcCusNoResultList.hideLoad() });
        }
        */

        function addPaymentClickEvent() {
            var selectList = view.table.invoiceList.getSelections();
            if (selectList.length < 1 && !view.advancedCheckbox.contains("on")) {
                view.dialog.mainMessageDialog.error(["กรุณาเลือกใบแจ้งค่าใช้บริการอย่างน้อย 1 รายการ"]).show();
                return false;
            }
            //var invoiceList = view.table.invoiceList.data();
            var table = $("#invoiceList");
            var invoiceList = table.bootstrapTable("getData");
            var selectedInvoiceNoList = [];
            for (var rowid = 0; rowid < invoiceList.length; rowid++) {
                var invoice = invoiceList[rowid];
                var checked = invoiceList[rowid]["checked"];
                if (checked) {
                    var invoiceNo = invoiceList[rowid]["invoiceNo"];
                    var balanceDue = stripToNumberWithFormat(invoiceList[rowid]["balanceDue"]);
                    //var receivedList = document.getElementsByName("received");
                    //var receivedValue =receivedList[rowid].value;
                    var receivedValue = $("#received_" + rowid).val()
                    var received = stripToNumberWithFormat(receivedValue);
                    console.log(balanceDue + "," + received)
                    console.log((balanceDue - received))

                    //if( subtract(balanceDue, received) < 0) {
                    if ((balanceDue - received) < 0) {

                        view.dialog.mainMessageDialog.error(["ระบบไม่อนุญาตให้ กำหนดจำนวน ยอดชำระ มากกว่า จำนวนเงินคงค้าง"]).show();
                        return false;
                    }
                    selectedInvoiceNoList.push(invoiceNo);
                }
            }
            console.log("selectedInvoiceNoList->" + selectedInvoiceNoList.toString());
            /*
             var selectedInvoiceNoList = [];
             for (var i = 0, m = invoiceList.length; i < m; i++) {
             var invoice = invoiceList[i], checked = invoice[1], invoiceNo = invoice[2], balanceDue = stripToNumber(invoice[10]), received = stripToNumber(invoice[11]);
             if (checked == "on" ) {
             if( subtract(balanceDue, received) < 0) {
             view.dialog.mainMessageDialog.error(["ระบบไม่อนุญาตให้ กำหนดจำนวน ยอดชำระ มากกว่า จำนวนเงินคงค้าง"]).show();
             return false;
             }
             selectedInvoiceNoList.push(invoiceNo);
             }
             }
             */
            // must have atleast 1 invoice no
            $.get("../findInvoiceNoInPayQueue.json", { "list": selectedInvoiceNoList.toString() }, function (res) {
                var invoiceReturnedList = $.map((res && res.data ? res.data : []), function (o) { if (o.status == null) { return o.invoiceNo; } });
                console.log(invoiceReturnedList);
//                 if (invoiceReturnedList && invoiceReturnedList.length !== 0) {
//                     view.dialog.mainMessageDialog.error(["ระบบรอหักล้างหนี้สำหรับใบแจ้งหนี้เลขที่ ".concat(invoiceReturnedList.toString())]).show();
//                     return false;
//                 } else {
                    var receiptDocumentOnly = $.inArray("true", view.checkbox.receiptDocumentOnly.val()) > -1;
                    var receiptInvoiceDocumentOnly = $.inArray("true", view.checkbox.receiptInvoiceDocumentOnly.val()) > -1;
                    console.log("receiptDocumentOnly[" + receiptDocumentOnly + "]");
                    console.log("receiptInvoiceDocumentOnly[" + receiptInvoiceDocumentOnly + "]");
                    var receiptFormat = "receive_wh";
                    if (receiptDocumentOnly && !receiptInvoiceDocumentOnly)
                        receiptFormat = "receive_only";
                    else if (!receiptDocumentOnly && receiptInvoiceDocumentOnly)
                        receiptFormat = "wh_only";
                    view.session("receiptFormat", receiptFormat);// receive + wh [receive_wh] , receive only [receive_only] ,  wh only [wh_only]
                    console.log("receiptFormat[" + receiptFormat + "]");
                    //return false;
                    // check duplicate pay in
                    $.get("../findInvoiceNoDuplicatePayQueue.json", { "list": selectedInvoiceNoList.toString() }, function (res) {
                        var invoiceReturnedList = $.map((res && res.data ? res.data : []), function (o) { //if(o.status == null){
                            return o.invNo;
                            //	}
                        });

                        if (invoiceReturnedList && invoiceReturnedList.length !== 0) {
                            view.dialog.mainMessageDialog.error(["ระบบกำลังมีการชำระหนี้สำหรับใบแจ้งหนี้เลขที่ ".concat(invoiceReturnedList.toString())]).show();
                            return false;
                        } else {
                            // lock inv
                            view.session("billingList", buildBillingList());
              
                            var billingList = view.session("billingList");
                            var invoiceLockList = [];
                            for (var i = 0, m = billingList.length; i < m; i++) {
                                for (var j = 0, n = billingList[i].invoiceList.length; j < n; j++) {
                                    var invoice = billingList[i].invoiceList[j];
                                    if (invoice.checked) {
                                        var invoiceLock = {
                                            invNo: invoice.no,
                                            mode: "ADD"
                                        }
                                        }
                                        invoiceLockList.push(invoiceLock)
                                    }
                                }
                            }
                            console.log(invoiceLockList);
                            console.log(JSON.stringify(invoiceLockList));
                            //var isStateAgency = false;

                            $.ajax({
                                type: "POST",
                                url: "../manageDuplicatePayQueue.json",
                                data: JSON.stringify(invoiceLockList),
                                dataType: "json",
                                contentType: "application/json; charset=utf-8",
                                success: function (data) {
                                    view.session("isStateAgency", view.inputCustomerSegment.key());
                                    location.href = 'pay-tboss-charge_2.jsp';
                                }
                            });
                    });
//                 }

            });
        }

        /*function discountCheckboxClickEvent() {
            var discountCheckBox = $(this);
            if (discountCheckBox.prop("checked")) {
                view.dialogAuthentication.show().done(function(res) {
                    view.inputAdditionalDiscount.enable();
                }).fail(function(res) {
                    view.inputAdditionalDiscount.disable();
                }).cancel(function() {
                    discountCheckBox.prop("checked", false)
                });
            } else {
                view.inputAdditionalDiscount.disable().val(0);
            }
        }*/
        function buildBillingList() {
            var invoiceMap = {}; $.each(view.table.invoiceList.rawData(), function (i, o) { invoiceMap[o.invoiceNo] = o });
            var isPrepaid = view.advancedCheckbox.contains("on");
            var billingList = view.session("billingList"), bill, invoice;
            // data = view.table.invoiceList.data();
            var table = $("#invoiceList");

            bill = find(billingList, "custNo", { "custNo": view.input.customerNo.val() });
            bill.custName = view.input.customerNoacc.val();
            bill.custNames = view.input.customerName.val(); 
            bill.custType = view.customerType.val();
            bill.acctCatLkp = view.acctCatLkp.val();//by NSD 02-03-2017
            bill.catCustomerSegment = view.catCustomerSegment.val();
            bill.billGroup = view.input.billingGroup.val();
            bill.additionalRemark = view.inputAdditionalRemark.val();
            bill.address1 = view.input.receiptAddress.val();
            bill.address2 = view.input.invoiceAddress.val();
            bill.prepaid = view.advancedAmount.val();
            bill.split = $.inArray("true", view.checkbox.splitReceiptDocument.val()) > -1;
            bill.taxId = view.input.taxId.val();
            bill.branch = view.input.branch.val();
            bill.collectionUnit = view.inputCollectionUnit.val();
            bill.souceType = view.input.souceType.val();
            /* start ADD e-GP */
            bill.egpNo = view.input.egpNo.val();
            bill.egpContract = view.input.egpContract.val();
            /* end ADD e-GP */
            bill.invoiceList = (bill.invoiceList || []);
            var data = table.bootstrapTable("getData");
            console.log('data from table'); console.log(data); console.log('data from table');
            if (data.length > 0) {
                bill.invoiceDisplay = data[0]["invoiceDisplay"];
            } else {
                bill.invoiceDisplay = invDisplay;
            }
            bill.receiptFormat = view.session("receiptFormat");//by NSD 25-04-2017

            for (var rowid = 0; rowid < data.length; rowid++) {
                var checked = data[rowid]["checked"];
                if (checked) {
                    var invoiceNoValue = data[rowid]["invoiceNo"];
                    var invoiceNo = invoiceNoValue, inv = invoiceMap[invoiceNo], vatRate = (inv ? inv.vatRate : 0), currencyCode = null, kenan = (inv ? inv.accountNo : 0);
                    invoice = find(bill.invoiceList, "no", { "no": invoiceNo });
                    //alert(invoice);
                    if (invoice.checked == null) invoice.checked = "on";
                    invoice.issueDate = data[rowid]["issueDt"];//data[i][3];
                    invoice.dueDate = data[rowid]["dueDt"];//data[i][4];
                    invoice.amount = stripToNumberWithFormat(data[rowid]["charge"]);//stripToNumber(data[i][5]);
                    invoice.discount = stripToNumberWithFormat(data[rowid]["discount"]);//stripToNumber(data[i][6]);
                    invoice.charge = invoice.amount - invoice.discount;
                    invoice.vat = stripToNumberWithFormat(data[rowid]["vat"]);//stripToNumber(data[i][7]);
                    invoice.vatRate = vatRate;
                    invoice.totalCharge = stripToNumberWithFormat(data[rowid]["totalCharge"]);//stripToNumber(data[i][8]);
                    invoice.balanceDue = stripToNumberWithFormat(data[rowid]["balanceDue"]);//stripToNumber(data[i][10]);
                    //var receivedList = document.getElementsByName("received");
                    //var receivedValue =receivedList[rowid].value;
                    invoice.deduction = stripToNumberWithFormat(data[rowid]["deduct"]);
                    var receivedValue = $("#received_" + rowid).val()
                    invoice.received = stripToNumberWithFormat(receivedValue);//stripToNumber(data[i][11]);
                    // Fix by EPIS8 26/12/2016 refer issue no 4, 6, 9
                    var isStateAgency = view.inputCustomerSegment.key() == "2";
                    if (invoice.deduction > 0) {
                        if (invoice.totalCharge != invoice.received) { invoice.deduction = invoice.balanceDue * ((isStateAgency) ? 0.01 : 0.03); }
                        else { invoice.deduction = stripToNumberWithFormat(data[rowid]["deduct"]); }//stripToNumber(data[i][12]);}
                    } else {
                        invoice.deduction = 0;
                    }
                    // End Fix by EPIS8 26/12/2016 refer issue no 4, 6, 9
                    invoice.billCycle = data[rowid]["billCycle"];//data[i][13];
                    invoice.status = data[rowid]["status"];//data[i][14];
                    invoice.billCycleSap = data[rowid]["billCycleSap"];
                    invoice.currencyCode = currencyCode;
                    invoice.kenan = kenan;
                    invoice.afterSaleDiscount = (view.afterSaleDiscountMap && view.afterSaleDiscountMap[invoiceNo] != null ? view.afterSaleDiscountMap[invoiceNo] : 0)
                    invoice.calculatedVat = calculateVatFromIncluded(invoice.received, invoice.vatRate, data[rowid]["invoiceVatDetails"]);//by NSD 20-04-2017
                    invoice.isStateAgency = isStateAgency;
                    invoice.subNoList = data[rowid]["subNoList"];
                    invoice.discApprUser = discApprUser;
                    invoice.discountType = chkDiscount;//by NSD 03-04-2017
                    if (chkDiscount == "2") {
                        invoice.afterSaleDiscVat = (invoice.afterSaleDiscount * vatRate / 100).toFixed(2);
                    } else {
                        invoice.afterSaleDiscVat = 0;
                    }
                    console.log('yyyyyy'); console.log(data[rowid]["invoiceNo"]); console.log(invoice); console.log('yyyyy');
                    invoice.invoiceVatDetails = data[rowid]["invoiceVatDetails"];
                    // alert(data[rowid]["subNoList"])
                }
            }


            if (isPrepaid) {
                var received = view.advancedAmount.val(), vatRate = stripToNumberWithFormat(view.custInfoInputVatRate.val()), vat = calculateVatFromIncluded(received, vatRate), charge = received - vat;
                invoice = find(bill.invoiceList, "no", { "no": "Advance Payment" });//{ "no": "-" });
                invoice.checked = true;
                invoice.issueDate = "-";
                invoice.dueDate = "-";
                invoice.amount = charge;
                invoice.discount = 0;
                invoice.charge = charge;
                invoice.vat = invoice.calculatedVat = vat;
                invoice.vatRate = vatRate;
                invoice.totalCharge = received;
                invoice.balanceDue = 0;
                invoice.received = received;
                invoice.deduction = 0;
                //invoice.billCycle = "Advance Payment";
                invoice.billCycle = view.billCycle.val()//by NSD 24-03-2017
                invoice.currencyCode = 12;
                invoice.kenan = view.kenan;
                invoice.afterSaleDiscount = 0;
            }
            return billingList;
        }
        function searchClickEvent() {
            // console.log("Test test = "+view.input.barcode);
            var isBarcode = !view.input.invNo.isEmpty();
            var totalamount = 0;
            var barCodeArr = isBarcode ? view.input.invNo.val().split(" ") : [];
            console.log("Barcode Array : " + barCodeArr);
            console.log("isBarcode : " + isBarcode);
            var billNo = isBarcode ? /*view.input.barcode.get("billNo")*/barCodeArr[1] : view.input.billNo.val();
            console.log("billNo : " + billNo);
            view.dialog.mainMessageDialog.clear();
            if (!isBarcode && billNo.length < 4) {
                view.dialog.mainMessageDialog.error(["โปรดระบุ 'เลขที่ลูกค้า' อย่างน้อย 4 ตัวอักษร"]).show();
                return false;
            } else if (billNo && isBarcode.length < 4) {
                view.dialog.mainMessageDialog.error(["โปรดระบุ 'Barcode / QRCode' ด้วยรูปแบบที่ถูกต้อง"]).show();
                return false;
            }
      
            view.input.balance.val(0);
            view.input.discountAmount.val(0);
            view.input.beforeDiscountAmount.val(0);
            view.input.vat.val(0);
            view.input.totalCharge.val(0);
            view.input.deduct.val(0);
            view.inputAdditionalDiscount.val(0);
            view.dialog.mainMessageDialog.clear().showLoad("ระบบกำลังรอข้อมูล: {timeCnt} วินาที")
            view.dialog.billingGroupMessageDialog.clear();
            window.billNoSelected = billNo;		// Added 20161218. Put param billNo for fixing search invoice.
            console.log("window.billNoSelected = " + window.billNoSelected);
			
            var dataSendCount = {
            		account_no: billNo,
            		ar_ref : view.input.invNo.val(),
            		source : "TBOSS",
                // barCodeArr:barCodeArr
            } 
            var output;
            $.ajax({
                type: "POST",
                async: false,
                url: "../searchottboss.json",
                data: JSON.stringify(dataSendCount),
                dataType: "json",
                contentType: "application/json; charset=utf-8",
                success: function (res) {
                    // console.log('===========data=========')
                    // console.log(data)
                    if (res.data.length > 0) {
                    	view.dialog.mainMessageDialog.stopLoad();
                    	view.tab.customerInfoTab.index(0);
                        view.dialog.customerPanel.slideDown(500, function () { view.dialog.navigatePanel.slideDown() });
                        view.dialog.detailsPanel.show(900);
                        view.dialog.summaryPanel.show(1200);
                        view.input.customerNo.val(res.data[0].account_no);
                        view.input.customerName.val(res.data[0].customer_name  + res.data[0].customer_lastname);
                        view.input.taxId.val(res.data[0].tax_id);
                        view.input.customerNoacc.val(res.data[0].customer_name);
                        view.custInfoInputAdvancedAmount.val(res.data[0].pay_advance);
                        view.inputCollectionUnit.val(res.data[0].region_name);
                        view.custInfoInputCurrencyCode.val(res.data[0].currancy_ar);
                        view.input.souceType.val(res.data[0].source);
                        view.input.taxId.val(res.data[0].tax_id);
                        view.input.branch.val(res.data[0].customer_branch_code);
                        view.input.billingGroup.val(res.data[0].billgroup);
                        
                        
                        for(var i = 0; i < res.data.length ; i++ ){
                        	
                        	totalamount = totalamount + res.data[i].balancedue;
                        	
                        }
                        view.custInfoInputAccruedAmount.val(totalamount);
                        if(res.data[0].customer_type_code == "ER1"){
                        	$("#inputCustomerSegment").val("3");
                        }else if (res.data[0].customer_type_code == "EC1"){
                        	$("#inputCustomerSegment").val("1");
                        }else{
                        	$("#inputCustomerSegment").val("2");
                        }
                        var individule = "บุคคลทั่วไป";
                        var  organization = "ธุรกิจทั่วไป";
                       	var  stateagency = "หน่วยงานรัฐ";
                       	
                       	if(res.data[0].customer_type_name == organization){
                       		view.acctCatLkp.val("1");
                       	}else{
                       		view.acctCatLkp.val("2");
                       	}
                        
                  
                        
                        if(res.data[0].customer_type_name == individule){
                        	view.customerType.val("INDIVIDUAL");
                        	
                        }else if(res.data[0].customer_type_name == organization){
                        	view.customerType.val("ORGANIZATION");
                        }else if(res.data[0].customer_type_name == stateagency){
                        	view.customerType.val("STATEAGENCY");
                        }else{
                        	 console.log('===========data=========' + individule  )
                        }

                        view.catCustomerSegment.val("1");
                        
                        
                        if (res.data[0].customer_status == "A") {
                            view.custInfoInputStatus.val("ปกติ");
                        } else {

                            view.custInfoInputStatus.val("ไม่ปกติ");
                        }



                        view.custInfoInputVatRate.val(res.data[0].vat_rate_ar + "%");
                        view.input.receiptAddress.val(res.data[0].customer_address);
                        view.input.invoiceAddress.val(res.data[0].customer_address);
                       
                        if (res.data.length > 0) {
                            invoiceData = $.map(res.data, invoiceListMapper);
                            console.log(invoiceData);
                        }
                        

                        
                        view.table.invoiceList.rawData(invoiceData);
                        var summaryList = (view.session("billingList") || []).length;
                        view.button.summaryPayment.disable(summaryList == 0).badge(summaryList);
                        view.button.submitPayment.disable(summaryList == 0);

                    }else{
                        view.dialog.navigatePanel.hide(1);
                        view.dialog.customerPanel.hide(1);
                        view.dialog.detailsPanel.hide(1);
                        view.dialog.summaryPanel.hide(1);
                        view.dialog.mainMessageDialog.clear();
                        view.dialog.mainMessageDialog.error(["No matching data found."]).show();
                    }
                    
                    
                }

            });



            // Credit Limit.
            get("../findSubscription.json", { "no": view.input.customerNo.val() }, function (res) {
                var data = []; $.each(res.data, function (i, o) { data.push([o.subscrNo]) });
                view.table.subscriptionList.data(data);
                view.session("subScriptionList", data);
            });
        }
        view.tab.customerInfoTab.init(1, function (e) {
            view.table.subscriptionList.showLoad();
            get("../findSubscription.json", { "no": view.input.customerNo.val()  }, function (res) {
                var data = []; $.each(res.data, function (i, o) { data.push(["-", o.subscrType, o.subscrNo, o.statusName]) });
                view.table.subscriptionList.data(data);
            }, view.dialog.mainMessageDialog, function () { view.table.subscriptionList.hideLoad() });
        });
        view.input.changeReceiptAddress.click(function () {
            view.tab.addressTab.show(0);
            view.input.receiptAddress.disable(!this.checked);
        });
        view.tab.invoiceDetailsTab.init(0, function () {
            // view.table.invoiceList.clear();
            view.table.invoiceList.showLoad();
             $.get("../searchInvoiceOTBOSS.json", { "invNo": billNoSelected , "source" : "TBOSS" }, function (res) {
                 		view.table.invoiceList.showLoad();
                var invoiceData = $.map((res && res.data ? res.data : []), invoiceListMapper)
                console.log("------> Invoice Data : " + invoiceData);
                if (invoiceData.length > 0) {
                    console.log("------>2 Invoice Data : x" + invoiceData + "x");
                }
                view.table.invoiceList.rawData(invoiceData);
                var combineInvoiceList = [];
                $.get("../findWriteOffInvoiceList.json", { "no": billNoSelected }, function (res) {
                    var writeOffInvoiceData = $.map((res && res.data ? res.data : []), writeOffInvoiceListMapper);
                    combineInvoiceList.push.apply(combineInvoiceList, invoiceData);
                    combineInvoiceList.push.apply(combineInvoiceList, writeOffInvoiceData);
                    console.log("writeOffInvoiceData>" + writeOffInvoiceData)
                    view.table.invoiceList.rawData(combineInvoiceList);
                    view.table.invoiceList.hideLoad();
                });
                 		view.table.invoiceList.showLoad();
             });
             	view.table.invoiceList.hideLoad();
        });
        view.tab.invoiceDetailsTab.init(1, function () {
            view.table.historyList.showLoad();
            $.get("../findPaymentOtbossHistory.json", { "billingNo": billNoSelected }, function (res) {
                view.table.historyList.hideLoad().rawData($.map((res && res.data ? res.data : []), function (o, i) {
                	 var datePeoiud = o.billCycle;
                	 var year = datePeoiud.substring(0, 4);
                     var mount = datePeoiud.substring(4, 6);
                    return {
                    	 
                        //"updateDt":	view.utils.dateTimeFormat(o.updatePrintDate)
                        //,"receiptDt": view.utils.dateTimeFormat(o.receiptPrintDate)
                    
                        "updateDt": o.updatePrintDateStr
                        , "receiptDt": o.receiptPrintDateStr
                        , "receiptNo": o.receiptNo
                        , "branch": o.shopPaymentName
                        , "user": o.paymentReceiver
                        , "invoiceNo": o.billRefNo
                        , "billCycle": mount + "/" + year
                        , "totalCharge": (o.billAmount || 0)
                        , "afterSaleDiscount": (o.afterSaleDiscount || 0)
                        , "payMethod": o.paymentMethod
                        , "payType": o.paymentCategory
                        , "totalPaid": ((o.transAmount || 0) - (o.afterSaleDiscount || 0))
                        , "vatAmt": (o.billAmountVat || 0)
                        //,"totalPaid": (o.transAmount || 0)
                        , "status": o.status
                        , "remark": o.remark
                        , "currencyCode": o.currencyCode
                    };
                }));
            });
        });
        view.tab.invoiceDetailsTab.init(2, function () {
            view.table.changeList.showLoad();
            $.get("../searchottbossdetails.json", { "account": view.input.customerNo.val() , "source" : "TBOSS" }, function (res) {  
                view.table.changeList.hideLoad().rawData($.map((res && res.data ? res.data : []), function (o, i) {
                    var vatRate = 0.00;
                    if(o.record_status == "R"){
                   	 o.record_status = "";
                    }
                    if(o.record_status == "CANCLE"){
                    	 o.record_status = "ยกเลิก";
                    }
                    var datePeoiud = o.sap_period;
                    var year = datePeoiud.substring(0, 4);
                    var mount = datePeoiud.substring(4, 6);
                    try {
                        vatRate = parseFloat(o.vatType);
                    } catch (err) {
                        vatRate = 0.00;
                    }
                    return {
                        "paidDt": view.utils.dateTimeFormat(o.paydate)
                        , "invoiceNo": o.ar_ref
                        , "totalCharge": o.total_amonut_paid.toFixed(2)
                        , "processedDt": view.utils.dateTimeFormat(o.update_date)
                        , "billCycle": mount + " / " + year
                        , "status": o.record_status

                    };
                }));
            });
        });
        $(document).ready(function () {

            // Added 20161201. Detect barcode scanner.
            var chars = [];
            document.body.addEventListener("keydown", function (e) {
                e = e || window.event;
                var key = e.which || e.keyCode; // keyCode detection
                var ctrl = e.ctrlKey ? e.ctrlKey : ((key === 17) ? true : false); // ctrl detection
                if (key == 77 && ctrl) {
                    //alert("Ctrl + M Pressed !");
                    // console.log("Ctrl + M (77) Pressed !")
                    chars.push(" ");
                }
            }, false);

            $("#barcode").keypress(function (e) {
                if (e.which === 13) {
                    // Barcode ends with CRLF (code = 13).
                    // If found key 13, we will put all barcode characters into textbox. And clear variable "chars = []".
                    // console.log("Prevent form submit.");
                    var barcode = chars.join("");
                    if (barcode.charAt(0) == "|") barcode = barcode.slice(1, barcode.length);
                    if (barcode.charAt(0) == " ") barcode = barcode.slice(1, barcode.length);
                    $("#barcode").val(barcode);
                    chars = [];
                    e.preventDefault();		// Prevent from submit.
                    searchClickEvent();
                } else if (e.which === 109) {
                    // On Firefox , it can detects key "m" while holding Ctrl button.
                    // Then we will ignore key "m".
                    // console.log(e.which + ": "+String.fromCharCode(e.which)+" --> Ignore this key.");
                } else {
                    // console.log(e.which + ": "+String.fromCharCode(e.which));
                    chars.push(String.fromCharCode(e.which));
                }
            });
            // End detect barcode scanner.

            var isNew = view.utils.queryString()["new"];
            var isManual = view.utils.queryString()["manual"]
            view.dialog.navigatePanel.hide();
            view.dialog.customerPanel.hide();
            view.dialog.detailsPanel.hide();
            view.dialog.summaryPanel.hide();
            if (isNew) {
                view.session("billingList", []);
            }
            if (isManual) {
                view.session("isManual", isManual);
            }
            if (!isNew && !isManual) { view.session("billingList", []); }
            var billingList = view.session("billingList");
            if (billingList.length > 0) {
                view.button.addPayment.disable();
                view.button.summaryPayment.badge(billingList.length);
                view.dialog.navigatePanel.show();
            }


        }).on("click", view.button.advanceSearch.el, function () { view.dialog.customerSearch.show() });
        function SelectButton(val, row, ind) {
            //return "<a class='btn btn-success btn-xs' onclick='BillSelectButtonEvent("+ JSON.stringify(row) +")'>เลือก</a>";
            return "<a class='btn btn-success btn-xs' onclick='BillSelectButtonWithParamEvent(" + row.billNo + "," + row.acctNo + ")'>เลือก</a>";
        }
        function BillSelectButtonEvent(bill) {
            view.input.billNo.val(bill.billNo);
            view.button.search.elem.click();
            view.dialog.customerSearch.hide();
            view.advanceInvSelected = bill.acctNo;
        }
        function BillSelectButtonWithParamEvent(billNo, acctNo) {
            view.input.billNo.val(billNo);
            view.button.search.elem.click();
            view.dialog.customerSearch.hide();
            view.advanceInvSelected = acctNo;
        }
        function avdInvoiceListMapper(o) {
            return {
                "acctNo": o.invoiceNo
                , "customerId": o.billingNo
                , "customerAccountName": o.billingName
                , "propLabel": o.customerType
                , "billGroup": o.billGroup
                , "billNo": o.billingNo
                , "tableID": "advSrcBillNoResultList"
            };
        }
        function invoiceListMapper(o) {
            var isStateAgency = view.inputCustomerSegment.key() == "2";
            var BeforeVat = o.balancedue * (7 / 107);
            var BeforeBalanceDue = o.balancedue - BeforeVat ;
            var deduct = BeforeVat * 0.00; // คำนวณจากตรงนี้นะครับ
            var datePeoiud = o.sap_period;
            var account_no = view.input.customerNo.val();
            var date = new Date();
            var date1 = new Date(o.duedate);
            date.toString('mm/yyyy/dd');
            date1.toString('mm/yyyy/dd');
            var totaldate = datePeoiud.substring(6);
            var year = datePeoiud.substring(0, 4);
            var mount = datePeoiud.substring(4, 6);

            var timeDiff = Math.abs(date.getTime() - date1.getTime());
            var diffDays = Math.ceil(timeDiff / (1000 * 3600 * 24));


	


            if (o.billNo == "0") {
                view.kenan = o.source;
            }

            if (o.billNo == "0" || o.balancedue == 0 || o.balancedue == null)
                return null;
            return {
                "invoiceNo": o.ar_ref
                , "accountNo": account_no
                , "issueDt": view.utils.dateFormat(o.invdate)
                , "dueDt": view.utils.dateFormat(o.duedate)
                , "amount": stripToNumberWithFormat(o.amountBeforeTax)
                , "charge": BeforeBalanceDue
                , "discount": stripToNumberWithFormat((o.discount || 0))
                , "vat": stripToNumberWithFormat(BeforeVat)
                , "vatRate": stripToNumberWithFormat((o.vat_rate_ar || "0"))
                , "balanceDue": stripToNumberWithFormat(o.balancedue)
                , "totalCharge": stripToNumberWithFormat(o.balancedue)
                , "totalAdj": stripToNumberWithFormat("0.00")
                , "totalPaid": stripToNumberWithFormat(o.total_amonut_paid)
                , "rentalCharge": o.rentalCharge
                , "usageCharge": o.usageCharge
                , "deduct": deduct
                , "billCycle": mount + " / " + year
                , "billCycleSap":  o.sap_period  
                , "billRefNo": o.source
                , "currencyCode": o.currencycode
                , "status": "หนี้สูญ"
                //,"aging":stripToNumberWithFormat(o.aging)

                , "aging": diffDays
                , "subNoList": o.subNoList
                , "invoiceDisplay": o.invoiceDisplay
                , "invoiceVatDetails": o.invoiceVatDetails
                , "taxTypeCode": o.taxTypeCode
            };
        }
        function writeOffInvoiceListMapper(o) {
            var isStateAgency = view.inputCustomerSegment.key() == "2";
            if (o.billNo == "0") {
                view.kenan = o.accountNo;
            }
            if (o.billNo == "0" || o.balanceDue == 0)
                return null;
            return {
                "invoiceNo": o.billNo
                , "accountNo": o.accountNo
                , "issueDt": view.utils.dateFormat(o.issueDate)
                , "dueDt": view.utils.dateFormat(o.dueDate)
                , "amount": stripToNumberWithFormat(o.amountBeforeTax)
                , "charge": stripToNumberWithFormat(o.amountBeforeTax) - stripToNumberWithFormat((o.taxAmount || 0))
                , "discount": stripToNumberWithFormat((o.discount || 0))
                , "vat": stripToNumberWithFormat(o.taxAmount)
                , "vatRate": stripToNumberWithFormat((o.taxRate || "0"))
                , "totalCharge": stripToNumberWithFormat(o.amountAfterTax)
                , "balanceDue": stripToNumberWithFormat(o.balanceDue)
                , "totalAdj": stripToNumberWithFormat(o.totalAdj)
                , "totalPaid": stripToNumberWithFormat(o.totalPaid)
                , "rentalCharge": o.rentalCharge
                , "usageCharge": o.usageCharge
                , "deduct": (o.rentalCharge * (isStateAgency ? 0.01 : 0.05)) + (o.usageCharge * (isStateAgency ? 0.01 : 0.03))
                , "billCycle": view.utils.dateFormat(o.chargeCycleFromDate) + " - " + view.utils.dateFormat(o.chargeCycleToDate)
                , "billRefNo": o.billNo
                , "currencyCode": o.currencycode
                , "status": "หนี้สูญ"
                , "aging": o.aging
                , "subNoList": o.subNoList
            };
        }
        function payTypeFormatter(val, row, ind) {
            var payType = !$.isNumeric(stripToNumber(val)) ? "-" : stripToNumber(val);
            var result;
            if (payType == 1) {
                result = "check/cash";
            } else if (payType == 2) {
                result = "credit card";
            } else if (payType == 3) {
                result = "direct debit (ตัดผ่านบัญชี/เครดิต)";
            } else if (payType == 4) {
                result = "debit card";
            } else {
                result = payType;
            }
            return result;
        }
        function changeListStatusFormatter(val, row, ind) {
            var status = !$.isNumeric(stripToNumber(val)) ? "-" : stripToNumber(val);
            var result;
            if (status == 1) {
                result = "distribution of an active payment";
            } else if (status == 2) {
                result = "distribution of an active postbill payment reversal";
            } else if (status == 3) {
                result = "distribution reversed due to later type-4 reallocation";
            } else if (status == 4) {
                result = "reallocation resulting in reversal of earlier distribution";
            } else if (status == 5) {
                result = "distribution reversed due to postbill payment reversal";
            } else if (status == 6) {
                result = "distribution reversed due to prebill payment reversal";
            } else if (status == 7) {
                result = "distribution of an active prebill payment reversal";
            } else {
                result = status;
            }
            return result;
        }
        function invoiceServiceFormatter(val, row, ind) {
            var guid = view.utils.guid();
            var loadPanel = new LoadingPanel(guid);
            $.get("../findProductOTBOSS.json", {"accountNo": row.accountNo, "nobil": row.invoiceNo , "billCycle" : row.billCycleSap}, function (res) {
                loadPanel.finish(Mustache.render($('#template').html(), { "invoices": $.map(res.data, function (o, i) {
                	if (o.amount == "0"){
      					 return null;
                	}else{
                        return { "productCode": o.productCode, "productName": o.productName, "subProductCode": o.subProductCode, "subProductName": o.subProductName, "revTypeCode": o.revTypeCode, "revTypeName": o.revTypeName, "amount": view.utils.numberFormat(o.amount) }
                	}
                	})
                }));
            });
            return loadPanel;
        }
        function invoiceListInputBlurEvent() { calculate() }
        function invoiceListCheckEvent(row) {
            var listSize = view.table.invoiceList.selected().length;
            view.checkboxAdditionalDiscount.disable(listSize != 1).unchecked();
            view.inputAdditionalDiscount.disable().val(0);
            //console.log('checked');console.log(row);console.log('checked');
            //by NSD 03-04-2017
            view.radio.discountCheckbox.disable(1, false);
            view.radio.discountCheckbox.disable(0, false);

            calculate();
        }
        function invoiceListUncheckEvent(row) {
            var listSize = view.table.invoiceList.selected().length;
            view.checkboxAdditionalDiscount.disable(listSize != 1).unchecked();
            view.inputAdditionalDiscount.disable().val(0);
            if (listSize != 1) {
                view.radio.discountCheckbox.disable(1, true);
                view.radio.discountCheckbox.disable(0, true);
                view.radio.discountCheckbox.unchecked();
            }

            calculate();
        }
        function invoiceListCheckAllEvent() {
            invoiceListCheckEvent(null);
        }
        function invoiceListUncheckAllEvent() {
            invoiceListUncheckEvent(null);
        }
        function calculateVatFromIncluded(received, vatRate, invoiceVatDetails) {
            var rtVat = 0;
            if (invoiceVatDetails != null && invoiceVatDetails.length > 1) {
                for (i1 = 0; i1 < invoiceVatDetails.length; i1++) {
                    rtVat += invoiceVatDetails[i1].vat;
                }
            } else {
                rtVat = (received / (100 + vatRate)) * vatRate;
            }
            return rtVat;
        }
        function calculate() {
            var invoiceMap = {}; $.each(view.table.invoiceList.rawData(), function (i, o) { invoiceMap[o.invoiceNo] = o });
            //var data = view.table.invoiceList.data();
            var sumVat = 0, sumReceived = 0, sumDeduct = 0, sumTotalCharge = 0, sumDiscount = 0;
            var table = $("#invoiceList");
            var data = table.bootstrapTable("getData");
            var sumAmount = 0;
            //console.log('xxxxx55555');console.log(data);console.log('xxxxx55555');
            //console.log("data size->"+data.length)
            for (var rowid = 0; rowid < data.length; rowid++) {
                var checked = data[rowid]["checked"];
                if (checked) {

                    var invoiceNo = data[rowid]["invoiceNo"];
                    var amount = data[rowid]["amount"];
                    var discount = data[rowid]["discount"];//by NSD 28-03-2017
                    var vatRate = invoiceMap[invoiceNo] == null ? 7 : invoiceMap[invoiceNo].vatRate;
                    var balanceDueValue = data[rowid]["balanceDue"];

                    // var receivedList = document.getElementsByName("received");
                    var receivedValue = $("#received_" + rowid).val();//receivedList[rowid].value;

                    var vatValue = data[rowid]["vat"];
                    var deductValue = data[rowid]["deduct"];
                    var totalChargeValue = data[rowid]["totalCharge"];
                    var invoiceVatDetails = data[rowid]["invoiceVatDetails"];
                    //console.log("receivedValue->" + receivedValue)
                    var balanceDue = stripToNumberWithFormat(balanceDueValue), received = stripToNumberWithFormat(receivedValue), vat = stripToNumberWithFormat(vatValue);
                    // console.log("received->" + received)
                    var deduct = stripToNumberWithFormat(deductValue);
                    var totalCharge = stripToNumberWithFormat(totalChargeValue);
                    console.log("invoiceNo: " + invoiceNo + "amount: " + amount + "deduct: " + deduct)
                    /*if (balanceDue != received)*/
                    vat = calculateVatFromIncluded(received, vatRate, invoiceVatDetails);
                    sumReceived += received;
                    sumVat += vat;
                    sumDeduct += deduct;
                    sumTotalCharge += totalCharge;
                    sumAmount += amount;
                    sumDiscount += discount;

                }
            }
            /*
             for (var i = 0, m = data.length; i < m; i++) {
             var checked = data[i][1] == "on", invoiceNo = data[i][2];
             var vatRate = invoiceMap[invoiceNo] == null ? 7 : invoiceMap[invoiceNo].vatRate;
             var balanceDue = stripToNumber(data[i][10]), received = stripToNumber(data[i][11]), vat = stripToNumber(data[i][7]), deduct = stripToNumber(data[i][12]), totalCharge = stripToNumber(data[i][8]);
             if (!checked) continue;
             vat = calculateVatFromIncluded(received, vatRate);
             sumReceived += received;
             sumVat += vat;
             sumDeduct += deduct;
             }
             */
            //console.log("sumReceived->"+sumReceived)
            var isStateAgency = view.inputCustomerSegment.key() == "2";
            console.log("received:" + received + " totalCharge :" + totalCharge + " sumReceived :" + sumReceived + " sumVat :" + sumVat + " balanceDue :" + balanceDue + " sumDeduct :" + sumDeduct)
            // Fix by EPIS8 WT Issue 19/01/2016
            if (received != balanceDue) {
                if (sumDeduct > 0)
                    sumDeduct = (sumReceived - sumVat) * ((isStateAgency) ? 0.01 : 0.03);
            }
            var balance = sumReceived - sumVat;
            if (sumDeduct > 0) {
                if (sumAmount != balance) {
                    sumDeduct = balance * ((isStateAgency) ? 0.01 : 0.03);
                }
            }
            // End Fix by EPIS8 WT Issue 19/01/2016
            // End Fix by EPIS8 WT Issue 19/01/2016

            view.input.balance.val(balance);
            view.input.vat.val(sumVat);
            view.input.totalCharge.val(sumReceived);
            // Fix by EPIS8 26/12/2016 refer issue no 4, 6, 9
            console.log("sumDeduct :" + sumDeduct);
            // End Fix by EPIS8 26/12/2016 refer issue no 4, 6, 9
            view.input.deduct.val(sumDeduct);
            view.input.discountAmount.val(sumDiscount);
            view.input.beforeDiscountAmount.val(balance + sumDiscount);
            view.button.addPayment.disable(sumTotalCharge <= 0);
        }
        function getRowSelected(eleId) {
            var table = $("#" + eleId);
            var data = table.bootstrapTable("getData");
            var dataReturn = [];
            for (var rowid = 0; rowid < data.length; rowid++) {
                var checked = data[rowid]["checked"];
                if (checked) {
                    dataReturn = data[rowid];
                    break;
                }
            }
            return dataReturn;
        }
        function inputAdditionalDiscountBlurEvent() {
            var data = view.table.invoiceList.selected();
            view.afterSaleDiscountMap = (view.afterSaleDiscountMap || {});
            view.afterSaleDiscountMap[$.trim(data[0][2])] = view.inputAdditionalDiscount.val();
        }
        function advancedCheckboxClickEvent() {
            view.advancedAmount.disable(!this.checked);
            view.billCycle.disable(!this.checked);
            view.button.addPayment.disable(!this.checked);
            if (!this.checked) {
                view.advancedAmount.val(0);
                view.billCycle.val('');
            }
        }
        function numberAgingFormatter(x) {
            return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
        }
        function stripToNumberWithFormat(str) {
            var strFormat = str + "";
            strFormat = strFormat.replace(/[^0-9\.]/g, "");
            return parseFloat(strFormat, 10);
        }

        function taxIdKeyUpEvent() { if (!view.input.taxId.isNumeric()) view.input.taxId.val(""); }
        function branchKeyUpEvent() { if (!view.input.branch.isNumeric()) view.input.branch.val(""); }
        function summaryPaymentClickEvent() { location.href = 'pay-service-charge_2.jsp'; }
        function submitPaymentClickEvent() { location.href = 'pay-service-charge_3.jsp'; }
        function convertToDate(dtNumber) {
            function pad(s) { return (s < 10) ? '0' + s : s; }
            var dt = new Date(dtNumber);
            return [pad(dt.getDate()), pad(dt.getMonth() + 1), dt.getFullYear()].join('/');
        }
        function chkCurrencySymbol(str) {
            console.log("currency code: " + str);
            var url = "../getSymbolByCurrencyCode.json"; params = { "code": str };
            var symbol = "฿";
            var data = [];
            console.log(url);
            console.log(params);
            if(str != undefined){
            	$.ajax({
                    url: url,
                    type: "GET",
                    data: {
                        "code": str
                    },
                    async: false,
                    success: function (res) {
                        window.getSymbolByCurrencyCodeObj = res.data[0];
                        console.log(window.getSymbolByCurrencyCodeObj);
                        symbol = window.getSymbolByCurrencyCodeObj.currencySymbol;
                        symbol = symbol != null ? symbol : "฿"
                    },
                    error: function () {
                        symbol = "฿"
                    }
                });
            }else{
            	
            }
            
            console.log("symbol =================> " + symbol);
            return symbol;
        }
        var chkDiscount = ""; var discApprUser = "";
        view.radio.discountCheckbox.click(function (val) {
            //var discountCheckBox = $(this);
            chkDiscount = val;
            if (val == "1" || val == "2") {
                view.dialogAuthentication.show().done(function (res) {
                    discApprUser = $("#discApprUser").val();
                    view.inputAdditionalDiscount.enable();
                }).fail(function (res) {
                    view.inputAdditionalDiscount.disable();
                }).cancel(function () {
                    //discountCheckBox.prop("checked", false)
                });
            } else {
                view.inputAdditionalDiscount.disable().val(0);
            }
        });
        function modalPopupRemark(remark) {
            if (remark != "null") {
                document.getElementById("remark").textContent = remark;
            } else {
                document.getElementById("remark").textContent = "ไม่มีหมายเหตุ";
            }
            view.dialog.remarkModal.show();
        }
    </script>