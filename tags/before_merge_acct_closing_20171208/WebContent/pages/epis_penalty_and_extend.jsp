<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
	<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>รับชำระค่าบริการ</title>
		<link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
		<link href="resources/bootstrap-table/src/bootstrap-table.css" rel="stylesheet" type="text/css" />
		<link href="resources/custom.css" rel="stylesheet" type="text/css" />
		<script src="resources/jquery.min.js" type="text/javascript"></script>
		<script src="resources/bootstrap/js/bootstrap.min.js"></script>
		<script src="resources/bootstrap-table/src/bootstrap-table.js"></script>
		<script src="resources/js/mustache.min.js" type="text/javascript"></script>
		<script src="resources/custom.js" type="text/javascript"></script>
		<style>
			.pointer {
				cursor: pointer;
			}
		</style>
	</head>

	<body>
		<header class="header_page"></header>
		<section class="container-fluid menu">
			<!-- <button class="menu-toggle btn btn-sm btn-default" style="margin-bottom: 5px"><span class="glyphicon glyphicon-step-forward"></span></button> -->
    <%-- <%@include  file="menu.jsp" %> --%>
				<form id="penaltyExtendForm" method="post" class="form-horizontal" role="form">
					<div class="row">
						<div class="col-md-12 tab-modefile">
							<ol class="breadcrumb">
								<li><i>รับชำระค่าบริการ</i></li>
								<li class="active">ชำระค่าต่อ/ค่าปรับ</li>
							</ol>
							<div id="mainMessageDialog"></div>
							<div id="billingGroupMessageDialog"></div>
							<ul class="nav nav-tabs" role="tablist">
								<li role="presentation" class="active"><a aria-controls="tab_cus" role="tab" data-toggle="tab"><span class="glyphicon glyphicon-filter"></span> ค้นหาข้อมูล</a></li>
							</ul>
							<div class="panel panel-default panal-radius">
								<div class="panel-body">
									<div class="row">
										<div class="col-md-12">
											<div class="form-horizontal">
												<div class="form-group">
													<label class="control-label col-sm-3"> เลขที่ลูกค้า(Billing Account) :</label>
													<div class="col-sm-2"><input class="form-control" id="billNo" maxlength="18"></div>
													<div class="col-sm-4 col-sm-offset-1">
														<a id="search" class="btn btn-primary"><span class="glyphicon glyphicon-search"></span> ค้นหา</a> &nbsp;&nbsp;
														<a id="advanceSearch" class="btn btn-success"><span class="glyphicon glyphicon-zoom-in"></span> ค้นหาเพิ่มเติม</a>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>

					<div id="customerPanel" class="hide row">
						<div id="customerPanel-body">
							<div class="col-md-12 tab-modefile">
								<ul id="customerInfoTab" class="nav nav-tabs" role="tablist">
									<li role="presentation" class="active"><a href="#tab_cus" aria-controls="tab_cus" role="tab" data-toggle="tab"><span class="glyphicon glyphicon-user"></span> ข้อมูลลูกค้า</a></li>
									<li role="presentation" class=""><a href="#sub_script" aria-controls="sub_script" role="tab" data-toggle="tab"><span class="glyphicon glyphicon-list"></span> Subscription</a></li>
								</ul>
								<div class="tab-content">
									<div role="tabpanel" class="tab-pane active" id="tab_cus">
										<div class="panel panel-default panal-radius">
											<div class="panel-body">
												<div class="row">
													<div class="col-md-12">
														<div class="form-horizontal">
															<div class="form-group">
																<label class="control-label col-sm-2">เลขที่ลูกค้า :</label>
																<div class="col-sm-2"><input id="customerNo" class="form-control" disabled="disabled"></div>
																<label class="control-label col-sm-2">ชื่อลูกค้า :</label>
																<div class="col-sm-6"><input id="customerName" class="form-control"></div>
																<input type="hidden" id="customerType">
															</div>
															<div class="form-group">
																<label class="control-label col-sm-2">Tax ID :</label>
																<div class="col-sm-2"><input id="taxId" class="form-control" disable="" maxlength="13"></div>
																<label class="control-label col-sm-2">สาขา :</label>
																<div class="col-sm-2"><input id="branch" class="form-control" maxlength="5"></div>
																<label class="control-label col-sm-2">หน่วยงานติดตามหนี้ :</label>
																<div class="col-sm-2"><input id="inputCollectionUnit" class="form-control" disabled=""></div>
															</div>
															<div class="form-group">
																<label class="control-label col-sm-2">กลุ่มผู้ใช้บริการ :</label>
																<div class="col-sm-2">
																	<select class="form-control" id="inputCustomerSegment" disabled></select>
																	<%--<select class="form-control" id="inputCustomerSegment" disabled=""><option data-index="0" data-key="1" value="ธุรกิจทั่วไป">ธุรกิจทั่วไป</option><option data-index="1" data-key="2" value="หน่วยงานรัฐ" selected="selected">หน่วยงานรัฐ</option><option data-index="2" data-key="3" value="บุคคลทั่วไป">บุคคลทั่วไป</option><option data-index="3" data-key="4" value="Carrier/Operator/NON POTs">Carrier/Operator/NON POTs</option><option data-index="4" data-key="5" value="Mkt.Arm">Mkt.Arm</option><option data-index="5" data-key="6" value="ISP">ISP</option><option data-index="6" data-key="7" value="Reseller/Agent">Reseller/Agent</option><option data-index="7" data-key="8" value="ธุรกิจ กสท">ธุรกิจ กสท</option><option data-index="8" data-key="9" value="สถานฑูต/องค์กรระหว่างประเทศ">สถานฑูต/องค์กรระหว่างประเทศ</option></select>--%>
																</div>
																<label class="control-label col-sm-2">เลขที่ e-GP :</label>
																<div class="col-sm-2"><input id="egpNo" class="form-control" disabled=""></div>
																<label class="control-label col-sm-2">ยอดค้างชำระ (รวม VAT) :</label>
																<div class="col-sm-2"><input id="custInfoInputAccruedAmount" class="form-control text-right" disabled=""></div>
															</div>
															<div class="form-group">
																<label class="control-label col-sm-2">Billing Group :</label>
																<div class="col-sm-2"><input id="billingGroup" class="form-control" disabled=""></div>
																<label class="control-label col-sm-2">เลขที่สัญญา (บช.1) :</label>
																<div class="col-sm-2"><input id="egpContract" class="form-control" disabled=""></div>
																<label class="control-label col-sm-2">ยอดชำระล่วงหน้า :</label>
																<div class="col-sm-2"><input id="custInfoInputAdvancedAmount" class="form-control text-right" disabled=""></div>
															</div>
															<div class="form-group">
																<label class="control-label col-sm-2">สถานะลูกค้า :</label>
																<div class="col-sm-2"><input id="custInfoInputStatus" class="form-control" value="Active" disabled=""></div>
																<label class="control-label col-sm-2"></label>
																<div class="col-sm-2"></div>
																<label class="control-label col-sm-2">สกุลเงิน :</label>
																<div class="col-sm-2"><input id="custInfoInputCurrencyCode" class="form-control" value="THB" disabled=""></div>
															</div>
															<div class="form-group">
																<label class="control-label col-sm-2">หมายเหตุ :</label>
																<div class="col-sm-6"><input id="remark" class="form-control"></div>
																<label class="control-label col-sm-2">VAT Rate :</label>
																<div class="col-sm-2"><input id="custInfoInputVatRate" class="form-control" disabled=""></div>
															</div>
														</div>
													</div>
												</div>
												<br>
												<div class="row">
													<div class="col-md-12 tab-modefile">
														<ul id="addressTab" class="nav nav-tabs" role="tablist">
															<li role="presentation" class="active"><a href="#tab1" aria-controls="tab1" role="tab" data-toggle="tab"><span class="glyphicon glyphicon-home"></span> ที่อยู่สำหรับใบเสร็จรับเงิน</a></li>
															<%--<li role="presentation" class=""><a href="#tab2" aria-controls="tab2" role="tab" data-toggle="tab"><span class="glyphicon glyphicon-home"></span> ที่อยู่สำหรับใบแจ้งค่าใช้บริการ</a></li>--%>
														</ul>
														<div class="tab-content">
															<div role="tabpanel" class="tab-pane active" id="tab1"><textarea id="receiptAddress" class="form-control textarea-tab" disabled="disabled"></textarea></div>
															<%--<div role="tabpanel" class="tab-pane" id="tab2"><textarea id="invoiceAddress" class="form-control textarea-tab" disabled="disabled"></textarea></div>--%>
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
													<%--<tbody>
											<tr>
												<td><div style="">1</div></td>
												<td><div style="">XXXXX</div>
												</td><td><div style="">2251386</div>
												</td><td><div style="">Active</div></td>
											</tr>
											<tr>
												<td><div style="">2</div></td>
												<td><div style="">YYYYY</div></td>
												<td><div style="">2255555</div></td>
												<td><div style="">Active</div></td>
											</tr>
											<tr>
												<td><div style="">3</div>
												</td><td><div style="">ZZZZZ</div>
												</td><td><div style="">2259999</div>
												</td><td><div style="">Active</div></td>
											</tr>
										</tbody>--%>
												</table>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="progress" style="display: none">
							<div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100"
							 style="width: 0%">
								<span class="sr-only">0% Complete</span>
							</div>
						</div>
					</div>
					<div id="receiptDetailsPanel" class="hide row">
						<div style="" id="panelDetails-body">
							<div class="col-md-12 tab-modefile">
								<!-- Nav tabs -->
								<ul class="nav nav-tabs" role="tablist">
									<li role="presentation" class="active"><a href="#tab_1" aria-controls="tab_1" role="tab" data-toggle="tab"><span class="glyphicon glyphicon-shopping-cart"></span> บริการรับชำระ</a></li>
								</ul>
								<!-- Tab panes -->
								<div class="panel panel-default panal-radius">
									<div class="panel-body">
										<div class="tab-content">
											<div role="tabpanel" class="tab-pane active" id="tab_1">
												<div class="form-horizontal">
													<div class="form-group">
														<label class="control-label col-sm-2">ประเภทการชำระ :</label>
														<div class="col-sm-2" id="radioExtPnt"><label class="col-sm-5 radio-inline">
											      <input type="radio" name="pntextradio" id="radioExtPnt1" value="CHARGE001"> ค่าปรับ </label>
															<label class="col-sm-5 radio-inline">
											      <input type="radio" name="pntextradio" id="radioExtPnt2" value="CHARGE002"> ค่าต่อ </label>
														</div>
													</div>
													<div class="form-group">
														<%--<label class="control-label col-sm-2 hide">ประเภทบริการ :</label>--%>

															<label class="control-label col-sm-2">หมายเลขบริการ :</label>
															<%--<div class="col-sm-2"><input id="inputServiceName" class="form-control"></div>--%>
																<div class="col-sm-2"><select id="inputServiceName" class="form-control"></select></div>
																<label class="control-label col-sm-1">ยอดเงินรวม :</label>
																<div class="col-sm-1"><input id="inputAmount" class="form-control text-right" value="0.00"></div>
																<label class="control-label col-sm-1" style="text-align: left;">บาท</label>

																<div class="col-sm-2 hide"><input id="inputServiceType2" class="form-control"></div>
																<div class="col-sm-2 hide"><select id="inputServiceType" class="form-control"><option data-index="0" data-key="POSC0001" value="XXXXX">
														CAT CDMA Mobile</option><option data-index="1" data-key="POSC0002" value="YYYYY">YYYYY</option><option data-index="2" data-key="POSC0003" value="ZZZZZ">ZZZZZ</option></select></div>
																<div class="col-sm-1"></div>
													</div>
													<div class="form-group">
														<label class="control-label col-sm-2">รายละเอียดการรับชำระ :</label>
														<div class="col-sm-6"><textarea class="form-control" rows="2" id="inputPaymentDesc"></textarea></div>
														<div class="col-sm-2"><a id="buttonAddBillingList" class="btn btn-info"> <span class="glyphicon glyphicon-plus-sign"></span> เพิ่มรายการรับชำระ</a></div>
													</div>
												</div>
												<br> <br>
												<%--<div class="bootstrap-table" id="tableServiceList"><div class="fixed-table-toolbar"></div><div class="fixed-table-container" style="padding-bottom: 0px;"><div class="fixed-table-header" style="display: none;"><table></table></div><div class="fixed-table-body"><div class="fixed-table-loading" style="top: 1px;">Loading, please wait...</div><table id="tableBillingList" data-row-style="rowStyle" data-toggle="table" data-classes="table table-hover table-striped" class="table table-hover table-striped">--%>
													<table data-toggle="table" id="tableServiceList">
														<thead>
															<tr>
																<th style="text-align: center; " data-field="id" data-formatter="runningFormatter">
																	<div class="th-inner ">#</div>
																	<%--<div class="fht-cell"></div>--%>
																</th>
																<th style="" data-field="serviceType">
																	<div class="th-inner ">ประเภทบริการ</div>
																	<%--<div class="fht-cell"></div>--%>
																</th>
																<th style="" data-field="serviceName">
																	<div class="th-inner ">หมายเลขบริการ</div>
																	<%--<div class="fht-cell"></div>--%>
																</th>
																<th style="text-align: right; " data-field="serviceAmount">
																	<div class="th-inner ">ยอดเงินรวม</div>
																	<%--<div class="fht-cell"></div>--%>
																</th>
																<th style="text-align: center; " data-field="paymentDesc">
																	<div class="th-inner ">รายละเอียดการรับชำระ</div>
																	<%--<div class="fht-cell"></div>--%>
																</th>
																<th style="text-align: center; " data-field="9" data-formatter="removeButtonFormatter">
																	<div class="th-inner "></div>
																	<%--<div class="fht-cell"></div>--%>
																</th>
																<%--<th data-valign="middle" data-align="center" data-formatter="runningFormatter">#</th>
														<th data-valign="middle" data-field="serviceType" data-align="center">ประเภทบริการ</th>
														<th data-valign="middle" data-field="serviceName" data-align="center">หมายเลขบริการ</th>
														<th data-valign="middle" data-field="serviceAmount" data-align="center">ยอดเงินรวม</th>
														<th data-valign="middle" data-field="paymentDesc" data-align="left">รายละเอียดการรับชำระ</th>
														<th data-valign="middle" data-align="center" data-width="80" data-formatter="removeButtonFormatter"></th>--%>
															</tr>
														</thead>
														<%--<tbody>
												<tr data-index="0">
													<td style="text-align: center; ">1</td>
													<td style="">XXXXX</td>
													<td style="">2251386</td>
													<td style="text-align: right;">100.00</td>
													<td style="text-align: left;">ค่าปรับ - ชำระเงินเกินระยะเวลาที่กำหนด</td>
													<td style="text-align: center; "><a href="#" onclick="removeFromServiceList(0)"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span></a></td>
												</tr>
											</tbody>--%>
													</table>
											</div>
											<div class="fixed-table-footer" style="display: none;">
												<table>
													<tbody>
														<tr></tr>
													</tbody>
												</table>
											</div>
											<div class="fixed-table-pagination" style="display: none;"></div>
										</div>
									</div>
									<div class="clearfix"></div>
								</div>
							</div>
						</div>
					</div>
					</div>
					</div>
					<div class="progress" style="display: none">
						<div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100"
						 style="width: 0%"><span class="sr-only">0% Complete</span></div>
					</div>
					</div>
					<div id="payCaseSumPanel" class="hide row">
						<div style="" id="pauCaseSumPanel-body">
							<div id="payCasePanel" class="col-md-7">
								<label class="label-panal label-warning">วิธีการรับชำระ</label>
								<div id="setPayType" class="btn-group">
									<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
			              <span class="selection">เงินสด</span> <span class="caret"></span>
			          </button>
									<ul class="dropdown-menu" id="testmenu2">
										<li><a class="pointer">เงินสด</a></li>
										<li><a class="pointer">เช็ค</a></li>
										<li><a class="pointer">บัตรเครดิต</a></li>
										<li><a class="pointer">ธนาณัติ</a></li>
										<li><a class="pointer">ตั๋วแลกเงิน</a></li>
										<li><a class="pointer">คูปอง</a></li>
										<li><a class="pointer">เงินโอนในประเทศ</a></li>
										<li><a class="pointer">offset</a></li>
										<li><a class="pointer">อื่น ๆ</a></li>
									</ul>
								</div>

								<div class="panel panel-default panal-radius">
									<div class="panel-body" style="padding-right: 0; padding-left: 0;">
										<div class="col-md-12">
											<div role="tabpanel" class="tab-pane hide" id="payType1">
												<div class="form-horizontal">
													<div class="form-group">
														<label class="control-label col-sm-8">จำนวนเงิน :</label>
														<div class="col-sm-4">
															<input id="payCashAmount" class="form-control text-right">
														</div>
													</div>
												</div>
											</div>
											<div role="tabpanel" class="tab-pane hide" id="payType2">
												<div class="form-horizontal">
													<div class="form-group">
														<label class="control-label col-sm-2">รหัสธนาคาร :</label>
														<div class="col-sm-4"><select id="payChqBankCode" class="form-control"></select></div>
														<label class="control-label col-sm-2">เลขที่เช็ค :</label>
														<!-- ICE FIXED CODE ข้อ 23 เช็ค length = 7 -->
														<div class="col-sm-4"><input class="form-control" id="payChqNo" maxlength="7"></div>
														<!-- End ICE FIXED CODE ข้อ 23 -->
													</div>
													<div class="form-group">
														<label class="control-label col-sm-2">ชื่อธนาคาร :</label>
														<div class="col-sm-4"><select id="payChqBankName" class="form-control"></select></div>
														<label class="control-label col-sm-2">วันที่หน้าเช็ค :</label>
														<div class="col-sm-4">
															<div class="input-group input-append date" data-provide="datepicker" data-date-format="dd/mm/yyyy">
																<input class="form-control" id="payChqDate" placeholder="dd/MM/yyyy" maxlength="10">
																<span class="input-group-addon add-on"><span class="glyphicon glyphicon-calendar"></span></span>
															</div>
														</div>
													</div>
													<div class="form-group">
														<label class="control-label col-sm-2">สาขา :</label>
														<div class="col-sm-4"><input class="form-control" id="payChqBranch"></div>
														<label class="control-label col-sm-2">จำนวนเงิน :</label>
														<div class="col-sm-4"><input class="form-control text-right" id="payChqAmount"></div>
													</div>
												</div>
												<a class="btn btn-warning pull-right" id="payChqSubmit"><span class="glyphicon glyphicon-plus-sign"></span> เพิ่มรายการเช็ค</a>
												<table id="payChqList" class="table">
													<thead>
														<tr>
															<th data-align="center" data-running-no="true">#</th>
															<th>รหัสธนาคาร</th>
															<th>ชื่อธนาคาร</th>
															<th>สาขา</th>
															<th>เลขที่เช็ค</th>
															<th>วันที่หน้าเช็ค</th>
															<th data-align="right" data-number-format="true">จำนวนเงิน</th>
															<th></th>
														</tr>
													</thead>
												</table>
											</div>
											<div role="tabpanel" class="tab-pane hide" id="payType3">
												<div class="form-horizontal">
													<div class="form-group">
														<label class="control-label col-sm-3">ประเภทบัตรเครดิต :</label>
														<div class="col-sm-3"><select class="form-control" id="payCCType"></select></div>
														<label class="control-label col-sm-3">เลขที่บัตร :</label>
														<div class="col-sm-3"><input class="form-control" id="payCCNo" maxlength="16"></div>
													</div>
													<div class="form-group">
														<label class="control-label col-sm-3">ธนาคารเจ้าของเครื่อง (EDC) :</label>
														<div class="col-sm-3"><select class="form-control" id="payCCBankName"></select></div>
														<label class="control-label col-sm-3">จำนวนเงิน :</label>
														<div class="col-sm-3"><input class="form-control text-right" id="payCCAmount"></div>
													</div>
												</div>
												<a class="btn btn-warning pull-right" id="payCCSubmit"><span class="glyphicon glyphicon-plus-sign"></span> เพิ่มรายการบัตรเครดิต</a>
												<table id="payCCList" class="table">
													<thead>
														<tr>
															<th data-align="center" data-running-no="true">#</th>
															<th>ประเภทบัตรเครดิต</th>
															<th>เลขที่บัตร</th>
															<th>EDC</th>
															<th data-align="right" data-number-format="true">จำนวนเงิน</th>
															<th></th>
														</tr>
													</thead>
												</table>
											</div>
											<div role="tabpanel" class="tab-pane hide" id="payType4">
												<div class="form-horizontal">
													<div class="form-group">
														<label class="control-label col-sm-3">วันที่ธนาณัติ :</label>
														<div class="col-sm-3">
															<div class="input-group input-append date" data-provide="datepicker" data-date-format="dd/mm/yyyy">
																<input class="form-control" id="payMoneyOrderInputDate" placeholder="dd/MM/yyyy" maxlength="10">
																<span class="input-group-addon add-on"><span class="glyphicon glyphicon-calendar"></span></span>
															</div>
														</div> <label class="control-label col-sm-3">เลขที่ธนาณัติ :</label>
														<!-- ICE FIXED CODE ข้อ 24 length เลขธนาณัติ = 9 -->
														<div class="col-sm-3"><input class="form-control" id="payMoneyOrderInputNo" maxlength="9"></div>
														<!-- End ICE FIXED CODE ข้อ 24 -->
													</div>
													<div class="form-group">
														<label class="control-label col-sm-3">รหัสไปรษณีย์ต้นทาง :</label>
														<!-- ICE FIXED CODE ข้อ 24 length postcode = 5 -->
														<div class="col-sm-3"><input class="form-control" id="payMoneyOrderInputPostCode" maxlength="5"></div>
														<!-- end ICE FIXED CODE ข้อ 24  -->
														<label class="control-label col-sm-3">จำนวนเงิน :</label>
														<div class="col-sm-3"><input class="form-control text-right" id="payMoneyOrderInputAmount"></div>
													</div>
												</div>
												<a class="btn btn-warning pull-right" id="payMoneyOrderButtonSubmit"><span class="glyphicon glyphicon-plus-sign"></span> เพิ่มรายการธนาณัติ</a>
												<table id="payMoneyOrderTableList" class="table">
													<thead>
														<tr>
															<th data-align="center" data-running-no="true">#</th>
															<th>เลขที่ธนาณัติ</th>
															<th>วันที่ธนาณัติ</th>
															<th>รหัสไปรษณีต้นทาง</th>
															<th data-align="right" data-number-format="true">จำนวนเงิน</th>
															<th></th>
														</tr>
													</thead>
												</table>
											</div>
											<div role="tabpanel" class="tab-pane hide" id="payType5">
												<div class="form-horizontal">
													<div class="form-group">
														<label class="control-label col-sm-3">วันที่ตั๋วแลกเงิน :</label>
														<div class="col-sm-3">
															<div class="input-group input-append date" data-provide="datepicker" data-date-format="dd/mm/yyyy">
																<input class="form-control" id="payBillExchgInputDate" placeholder="dd/MM/yyyy" maxlength="10">
																<span class="input-group-addon add-on"><span class="glyphicon glyphicon-calendar"></span></span>
															</div>
														</div>
														<label class="control-label col-sm-3">เลขที่ตั๋วแลกเงิน :</label>
														<!-- ICE FIXED CODE ข้อ 22 length เลขที่ตั๋วแลกเงิน = 9 -->
														<div class="col-sm-3"><input class="form-control" id="payBillExchgInputNo" maxlength="9"></div>
														<!--  end ICE FIXED CODE ข้อ 22 -->
													</div>
													<div class="form-group">
														<label class="control-label col-sm-3">รหัสไปรษณีย์ต้นทาง :</label>
														<!-- ICE FIXED CODE ข้อ 22 length postcode = 5 -->
														<div class="col-sm-3"><input class="form-control" id="payBillExchgInputPostCode" maxlength="5"></div>
														<!--  end ICE FIXED CODE ข้อ 22 -->
														<label class="control-label col-sm-3">จำนวนเงิน :</label>
														<div class="col-sm-3"><input class="form-control text-right" id="payBillExchgInputAmount"></div>
													</div>
												</div>
												<a class="btn btn-warning pull-right" id="payBillExchgButtonSubmit"><span class="glyphicon glyphicon-plus-sign"></span> เพิ่มรายการตั๋วแลกเงิน</a>
												<table id="payBillExchgTableList" class="table">
													<thead>
														<tr>
															<th data-align="center" data-running-no="true">#</th>
															<th>เลขที่ตั๋วแลกเงิน</th>
															<th>วันที่ตั๋วแลกเงิน</th>
															<th>รหัสไปรษณีต้นทาง</th>
															<th data-align="right" data-number-format="true">จำนวนเงิน</th>
															<th></th>
														</tr>
													</thead>
												</table>
											</div>
											<div role="tabpanel" class="tab-pane hide" id="payType6">
												<div class="form-horizontal">
													<div class="form-group">
														<label class="control-label col-sm-9">เลขที่คูปอง :</label>
														<div class="col-sm-3"><input class="form-control" id="payCouponInputNo"></div>
													</div>
													<div class="form-group">
														<label class="control-label col-sm-9">จำนวนเงิน :</label>
														<div class="col-sm-3"><input class="form-control text-right" id="payCouponInputAmt"></div>
													</div>
												</div>
												<a class="btn btn-warning pull-right" id="payCouponButtonSubmit"><span class="glyphicon glyphicon-plus-sign"></span> เพิ่มรายการคูปอง</a>
												<table id="payCouponTableList" class="table">
													<thead>
														<tr>
															<th data-align="center" data-running-no="true">#</th>
															<th>เลขที่คูปอง</th>
															<th>รายละเอียด</th>
															<th>วันที่หมดอายุ</th>
															<th data-align="right" data-number-format="true">จำนวนเงิน</th>
															<th></th>
														</tr>
													</thead>
												</table>
											</div>
											<div role="tabpanel" class="tab-pane hide" id="payType7">
												<div class="form-horizontal">
													<div class="form-group">
														<label class="control-label col-sm-4">รหัสธนาคาร :</label>
														<div class="col-sm-3"><select class="form-control" id="payBankTxnfInputBankCode"></select></div>
														<label class="control-label col-sm-2">ชื่อธนาคาร :</label>
														<div class="col-sm-3"><select class="form-control" id="payBankTxnfInputBankName"></select></div>
													</div>
													<div class="form-group">
														<label class="control-label col-sm-4">สาขา :</label>
														<div class="col-sm-3"><select class="form-control" id="payBankTxnfDropDownBankBrnh"></select></div>
														<label class="control-label col-sm-2">เลขที่อ้างอิง :</label>
														<div class="col-sm-3"><input class="form-control" id="payBankTxnfInputNo" maxlength="10"></div>
													</div>
													<div class="form-group">
														<label class="control-label col-sm-4">รหัสบัญชีเงินฝากธนาคาร :</label>
														<div class="col-sm-3"><select class="form-control" id="payBankTxnfDropDownBankAcCd"></select></div>
														<label class="control-label col-sm-2">วันที่โอน :</label>
														<div class="col-sm-3">
															<div class="input-group input-append date" data-provide="datepicker" data-date-format="dd/mm/yyyy">
																<input class="form-control" id="payBankTxnfInputDate" placeholder="dd/MM/yyyy" maxlength="10">
																<span class="input-group-addon add-on"><span class="glyphicon glyphicon-calendar"></span></span>
															</div>
														</div>
													</div>
													<div class="form-group">
														<label class="control-label col-sm-4">เลขที่บัญชีเงินฝากธนาคาร :</label>
														<div class="col-sm-3"><select class="form-control" id="payBankTxnfDropDownBankAcct"></select></div>
														<label class="control-label col-sm-2">จำนวนเงิน :</label>
														<div class="col-sm-3"><input class="form-control text-right" id="payBankTxnfInputAmt"></div>
													</div>
												</div>
												<a class="btn btn-warning pull-right" id="payBankTxnfButtonSubmit"><span class="glyphicon glyphicon-plus-sign"></span> เพิ่มรายการอื่น ๆ</a>
												<table id="payBankTxnfTableList" class="table">
													<thead>
														<tr>
															<th data-align="center" data-running-no="true">#</th>
															<th>รหัสธนาคาร</th>
															<th>ชื่อธนาคาร</th>
															<th>สาขา</th>
															<th>เลขที่อ้างอิง</th>
															<th>วันที่โอน</th>
															<th data-align="right" data-number-format="true">จำนวนเงิน</th>
															<th></th>
														</tr>
													</thead>
												</table>
											</div>
											<div role="tabpanel" class="tab-pane hide" id="payType8">
												<div class="form-horizontal">
													<div class="form-group">
														<label class="control-label col-sm-3">เลขที่เอกสาร/ปี :</label>
														<div class="col-sm-3"><input id="payOffsetDocumentNo" class="form-control"></div>
														<label class="control-label col-sm-3">รหัสบัญชี :</label>
														<div class="col-sm-3"><input id="payOffsetAccountCode" class="form-control"></div>
													</div>
													<div class="form-group">
														<label class="control-label col-sm-3">ชื่อบัญชี :</label>
														<div class="col-sm-3"><input id="payOffsetAccountName" class="form-control text-right"></div>
														<label class="control-label col-sm-3">จำนวนเงิน :</label>
														<div class="col-sm-3"><input id="payOffsetAmount" class="form-control text-right"></div>
													</div>
													<div class="form-group">
														<label class="control-label col-sm-10"></label>
														<div class="col-sm-2"><a class="btn btn-warning pull-right" id="payOffsetButtonSubmit"><span class="glyphicon glyphicon-plus-sign"></span> เพิ่มรายการ offset</a></div>
													</div>
												</div>
												<table id="payOffsetTableList" class="table table-hover">
													<thead>
														<tr>
															<th data-running-no="true">#</th>
															<th>เลขที่เอกสาร/ปี</th>
															<th>รหัสบัญชี</th>
															<th>ชื่อบัญชี</th>
															<th data-align="right" data-number-format="true">จำนวนเงิน</th>
															<th></th>
														</tr>
													</thead>
												</table>
											</div>
											<div role="tabpanel" class="tab-pane hide" id="payType9">
												<div class="form-horizontal">
													<div class="form-group">
														<label class="control-label col-sm-3">ช่องทางการชำระ :</label>
														<div class="col-sm-3"><select class="form-control" id="payOtherDropDownChannel"></select></div>
														<label class="control-label col-sm-3">เลขที่อ้างอิง :</label>
														<div class="col-sm-3"><input class="form-control" id="payOtherInputNo"></div>
													</div>
													<div class="form-group">
														<label class="control-label col-sm-3">วันที่อ้างอิง :</label>
														<div class="col-sm-3">
															<div class="input-group input-append date" data-provide="datepicker" data-date-format="dd/mm/yyyy">
																<input class="form-control" id="payOtherInputDate" placeholder="dd/MM/yyyy" maxlength="10">
																<span class="input-group-addon add-on"><span class="glyphicon glyphicon-calendar"></span></span>
															</div>
														</div>
														<label class="control-label col-sm-3">จำนวนเงิน :</label>
														<div class="col-sm-3"><input class="form-control text-right" id="payOtherInputAmt"></div>
													</div>
												</div>
												<a class="btn btn-warning pull-right" id="payOtherButtonSubmit"><span class="glyphicon glyphicon-plus-sign"></span> เพิ่มรายการอื่น ๆ</a>
												<table id="payOtherTableList" class="table">
													<thead>
														<tr>
															<th data-align="center" data-running-no="true">#</th>
															<th>เลขที่อ้างอิง</th>
															<th>ช่องทางการรับชำระ</th>
															<th>วันที่รับชำระ</th>
															<th data-align="right" data-number-format="true">จำนวนเงิน</th>
															<th></th>
														</tr>
													</thead>
												</table>
											</div>
											<div role="tabpanel" class="tab-pane hide" id="payType10">
												<div class="form-horizontal">
													<div class="form-group">
														<label class="control-label col-sm-4">รหัสธนาคาร :</label>
														<div class="col-sm-3"><input class="form-control" id="payBankTxnfInputBankCodeGf" maxlength="5"></div>
														<label class="control-label col-sm-2">ชื่อธนาคาร :</label>
														<div class="col-sm-3"><select class="form-control" id="payBankTxnfInputBankNameGf"></select></div>
													</div>
													<div class="form-group">
														<label class="control-label col-sm-4">สาขา :</label>
														<div class="col-sm-3"><select class="form-control" id="payBankTxnfDropDownBankBrnhGf"></select></div>
														<label class="control-label col-sm-2">เลขที่อ้างอิง :</label>
														<div class="col-sm-3"><input class="form-control" id="payBankTxnfInputNoGf" maxlength="10"></div>
													</div>
													<div class="form-group">
														<label class="control-label col-sm-4">รหัสบัญชีเงินฝากธนาคาร :</label>
														<div class="col-sm-3"><select class="form-control" id="payBankTxnfDropDownBankAcCdGf"></select></div>
														<label class="control-label col-sm-2">วันที่โอน :</label>
														<div class="col-sm-3">
															<div class="input-group input-append date" data-provide="datepicker" data-date-format="dd/mm/yyyy">
																<input class="form-control" id="payBankTxnfInputDateGf" placeholder="dd/MM/yyyy" maxlength="10">
																<span class="input-group-addon add-on"><span class="glyphicon glyphicon-calendar"></span></span>
															</div>
														</div>
													</div>
													<div class="form-group">
														<label class="control-label col-sm-4">เลขที่บัญชีเงินฝากธนาคาร :</label>
														<div class="col-sm-3"><select class="form-control" id="payBankTxnfDropDownBankAcctGf"></select></div>
														<label class="control-label col-sm-2">จำนวนเงิน :</label>
														<div class="col-sm-3"><input class="form-control text-right" id="payBankTxnfInputAmtGf"></div>
													</div>
												</div>
												<a class="btn btn-warning pull-right" id="payBankTxnfButtonSubmitGf"><span class="glyphicon glyphicon-plus-sign"></span> เพิ่มรายการอื่น ๆ</a>
												<table id="payBankTxnfTableListGf" class="table">
													<thead>
														<tr>
															<th data-align="center" data-running-no="true">#</th>
															<th>รหัสธนาคาร</th>
															<th>ชื่อธนาคาร</th>
															<th>สาขา</th>
															<th>เลขที่อ้างอิง</th>
															<th>วันที่โอน</th>
															<th data-align="right" data-number-format="true">จำนวนเงิน</th>
															<th></th>
														</tr>
													</thead>
												</table>
											</div>
											<a class="btn btn-primary pull-right" id="addPayType" style="margin-top:1em"><span class="glyphicon glyphicon-plus-sign"></span> เพิ่มวิธีการรับชำระ</a>
											<%--<div class="panel-body">
									<table id="payTypeList" class="table table-hover">
										<thead>
										<tr>
											<th data-running-no="true">#</th>
											<th>วิธีการรับชำระ</th>
											<th data-align="right" data-number-format="true" class="text-right">จำนวนเงิน</th>
											<th></th>
										</tr>
										</thead>
									</table>
								</div>--%>



										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-12">
										<div class="panel panel-default">
											<div class="panel-heading">สรุปวิธีการรับชำระ</div>
											<div class="panel-body">
												<table id="payTypeList" class="table table-hover">
													<thead>
														<tr>
															<th data-running-no="true">#</th>
															<th>วิธีการรับชำระ</th>
															<th data-align="right" data-number-format="true" class="text-right">จำนวนเงิน</th>
															<th></th>
														</tr>
													</thead>
												</table>
											</div>
										</div>
									</div>
								</div>
							</div>

							<div id="summaryPanel" class="col-md-5">

								<div class="panel panel-default">
									<div class="panel-heading"><span class="glyphicon glyphicon-shopping-cart"></span> สรุปยอดเงินที่ต้องชำระ</div>
									<div class="panel-body">
										<div class="form-horizontal">
											<div class="form-group">
												<label class="control-label col-sm-8"> ยอดเงินที่ต้องชำระ :</label>
												<div class="col-sm-4"><input id="totalCharge" class="form-control text-right" disabled=""></div>
											</div>
											<div class="form-group">
												<label class="control-label col-sm-8">ยอดเงินรับมา :</label>
												<div class="col-sm-4">
													<input id="inputReceived" class="form-control text-right" value="0.00" disabled="disabled">
												</div>
											</div>
											<div class="form-group">
												<label class="control-label col-sm-8">เงินทอน :</label>
												<div class="col-sm-4">
													<input id="change" class="form-control text-right" value="0.00" disabled="disabled">
												</div>
											</div>
											<div>
												<ul id="navigationPanel" class="list-inline pull-right list-menu-set">
													<li><a id="submitPayment" class="btn btn-link"><span class="glyphicon glyphicon-print"></span> บันทึกและพิมพ์</a></li>
													<li><a id="cancelPaymentBtn" class="btn btn-link"><span class="glyphicon glyphicon-remove-circle"></span> ยกเลิกรายการ</a></li>
												</ul>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="progress" style="display: none">
							<div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100"
							 style="width: 0%"><span class="sr-only">0% Complete</span></div>
						</div>
					</div>
				</form>
		</section>

		<div id="panelSummaryPaymentList" style="display: none">
			<ul class="list-inline pull-right list-menu-set">
				<li><a href="epis_penalty_and_extend.jsp?new"><span class="glyphicon glyphicon glyphicon-arrow-left"></span> กลับไปหน้าการรับชำระ</a></li>
			</ul>
			<div class="row">
				<div class="col-md-12">
					<div class="panel panel-success">
						<div class="panel-heading">ผลการรับชำระเงิน</div>
						<div class="panel-body">
							<table id="tableSummaryReceipts" data-row-style="rowStyle" data-toggle="table" data-detail-view="true" data-detail-formatter="detailFormatter"
							 data-classes="table table-hover table-striped">
								<thead>
									<tr>
										<th data-align="center" data-formatter="runningFormatter">#</th>
										<th data-field="custNo">เลขที่ลูกค้า</th>
										<th data-field="custName">ชื่อลูกค้า</th>
										<th data-field="receiptNo">เลขที่ใบเสร็จรับเงิน</th>
										<th data-field="receiptAmount" data-align="right">จำนวนเงินที่รับชำระ</th>
										<th data-field="status">สถานะ</th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>

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
							    <!--  
								<li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">เลขที่ใบแจ้งค่าบริการ</a></li>
								 -->
								<li role="presentation"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">หมายเลขบริการ</a></li>
								<li role="presentation"><a href="#messages" aria-controls="messages" role="tab" data-toggle="tab">ข้อมูลลูกค้า</a></li>
							</ul>
							<div class="panel panel-default">
								<div class="panel-body">
									<div class="tab-content border-tab-content">
										<div role="tabpanel" class="tab-pane active" id="home">
											<div class="form-inline col-md-12">
												<div class="form-group col-md-12">
													<label>เลขที่ใบแจ้งค่าบริการ :</label>
													<div class="input-group col-md-8">
														<input type="text" class="form-control" id="advSrcBillNo" placeholder="กรุณาป้อนข้อมูลที่ต้องการค้นหาอย่างน้อย 4 ตัวอักษร">
														<span class="input-group-btn"><a id="advSrcBillNoBtn" class="btn btn-info"><span class="glyphicon glyphicon-search"></span>														ค้นหา</a>
														</span>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-md-12">
													<table id="advSrcBillNoResultList" data-toggle="table" data-pagination="true" data-page-size="5" data-page-list="[5, 10, 20, 50, 100, 200]"
													 data-cache="false">
														<thead>
															<tr>
																<th data-formatter="runningFormatter">#</th>
																<th data-field="acctNo">เลขที่ใบแจ้งค่าบริการ</th>
																<th data-field="customerId">เลขที่ลูกค้า</th>
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
										<div role="tabpanel" class="tab-pane" id="profile">
											<div class="form-horizontal">
												<div class="form-group">
													<label class="control-label col-sm-2">ประเภทบริการ :</label>
													<div class="col-sm-4">
														<select id="advSrcSvcType" class="form-control"></select>
													</div>
												</div>
												<div class="form-group">
													<label class="control-label col-sm-2">หมายเลขบริการ :</label>
													<div class="col-sm-4">
														<div class="input-group">
															<input type="text" class="form-control" id="advSrcSvcNo">
															<span class="input-group-btn"><a id="advSrcSvcNoBtn" class="btn btn-info"><span class="glyphicon glyphicon-search"></span>															ค้นหา</a>
															</span>
														</div>
													</div>
												</div>
												<div class="form-group">
													<label class="control-label col-sm-2"></label>
													<div class="col-sm-4">
														<label class="checkbox-inline"> <input type="checkbox" name="advSrcSvcProperty" value="1"> Property1</label>
														<label class="checkbox-inline"> <input type="checkbox" name="advSrcSvcProperty" value="2"> Property2</label>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-md-12">
													<table id="advSrcSvcNoResultList" name="tableproperty" class="table table-hover" data-toggle="table" data-pagination="true"
													 data-page-size="5" data-page-list="[5, 10, 20, 50, 100, 200]" data-cache="false">
														<thead>
															<tr>
																<th data-formatter="runningFormatter">#</th>
																<th data-field="propOne">หมายเลขบริการ</th>
																<th data-field="acctNo">เลขที่ลูกค้า</th>
																<th data-field="acctName">ชื่อลูกค้า</th>
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
										<div role="tabpanel" class="tab-pane" id="messages">
											<div class="form-horizontal">
												<div class="form-group">
													<label class="control-label col-sm-3">เลขที่ลูกค้า (BA) :</label>
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

													</div>
												</div>
												<div class="form-group">
													<label class="control-label col-sm-3">กลุ่มผู้ใช้บริการ :</label>
													<div class="col-sm-6"><select class="form-control" id="advInputCustomerSegment"></select></div>
													<div class="col-sm-3">
														<a id="advSrcCusNoBtn" class="btn btn-info pull-right"><span class="glyphicon glyphicon-search"></span> ค้นหา</a>
													</div>
												</div>
											</div>

											<br>

											<div class="row">
												<div class="col-md-12">
													<table id="advSrcCusNoResultList" class="table table-hover" data-toggle="table" data-pagination="true" data-page-size="5"
													 data-page-list="[5, 10, 20, 50, 100, 200]" data-cache="false">
														<thead>
															<tr>
																<th data-formatter="runningFormatter">#</th>
																<th data-field="customerId">เลขที่ลูกค้า</th>
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


		<script type="text/javascript">
			var paramSelectorArray = ['#inputPaymentDesc'
			];
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
				window.stringInputFormatter = function (val, row, ind) { return '<input value="' + $.trim(val) + '" maxLength="40" class="form-control">' }
				window.numberInputFormatter = function (val, row, ind) { return '<input value="' + self.utils.numberFormat(parseFloat(val || "0.00", 10)) + '" maxLength="10" style="width:100px" class="form-control text-right" onkeypress="return $.isNumeric(String.fromCharCode(event.which)) || (event.which == 46 && this.value.indexOf(\'.\') == -1)" onfocus="(function(o){ o.value = o.value.replace(/[,]/g, \'\') })(this)" onblur="(function(o){ o.value = parseFloat(o.value, 10).toFixed(2).replace(/(\\d)(?=(\\d{3})+\\.)/g, \'$1,\'); })(this)">' }
				window.moneyInputFormatter = function (val, row, ind) { var value = parseFloat(val || "0.00", 10).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, "$1,"); return '<input value="' + value + '" id="received_' + ind + '"  maxLength="10" style="width:100px" class="form-control text-right" onkeypress="return $.isNumeric(String.fromCharCode(event.which)) || (event.which == 46 && this.value.indexOf(\'.\') == -1)" onkeyup="if (event.which == 13) this.blur()" onfocus="(function(o){ o.value = o.value.replace(/[,]/g, \'\'); o.select() })(this)" onblur="(function(o){ o.value = parseFloat(o.value, 10).toFixed(2).replace(/(\\d)(?=(\\d{3})+\\.)/g, \'$1,\'); o.style.backgroundColor = o.value !== \'' + value + '\' ? \'yellow\' : \'\'; var table, elem = o; while (table == null) { elem = elem.parentNode; if (elem.tagName == \'TABLE\') table = elem; } (window[table.id +\'InputBlurEvent\'] || function(t){ console.log(t) })(table) })(this)">' }
				window.modifyButtonFormatter = function (val, row, ind) { return '<a class="btn btn-link modList" onclick=\'(function(elem, row, ind){ var table = $("#"+ $(elem).closest("table")[0].id); window[table[0].id +"ModifyEvent"](table, row, ind) })(this, ' + JSON.stringify(row) + ', ' + ind + ')\'><span class="glyphicon glyphicon-pencil"></span></a>' }
				window.removeButtonFormatterOld = function (val, row, ind) { return '<a class="btn btn-link delList" onclick=\'(function(elem, row, ind){ $("#"+ $(elem).closest("table")[0].id).bootstrapTable("remove", { field: "index", values: [ind] }) })(this, ' + JSON.stringify(row) + ', ' + ind + ')\'><span class="glyphicon glyphicon-trash"></span></a>' }
				window.removeButtonFormatter = function (val, row, ind) {
					return '<a class="btn btn-link delList" onclick=\'deleteRow(' + JSON.stringify(row) + ')\'><span class="glyphicon glyphicon-trash"></span></a>'
				}
				window.LoadingPanel = function (id) { var obj = this, loadCnt = 0, setupFunc, loadFunc; function checkElemReady() { if ((obj.elem = $("#" + id).css("font", "")).length != 1) { alert("The element hasn't insert into HTML DOM."); throw "The element hasn't insert into HTML DOM."; } clearTimeout(setupFunc); clearInterval(loadFunc) }; return { "elem": null, "finish": function (html) { checkElemReady(); (this.elem ? this.elem : this.elem = $("#" + id)).css("font", "").html(html); return this }, "toString": function () { var elem; setupFunc = setTimeout(function () { loadFunc = setInterval(function () { (elem || (elem = document.getElementById(id))).innerHTML = "Loading" + Array(++loadCnt).join(".."); if (loadCnt > 60) loadCnt = 2 }, 250) }, 1000); return "<div id='" + id + "' style='font:BOLD 16pt Arial'></div>" } } }
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
					obj.message = function (arr, cls) {
						$.each(arr, function (i, o) {
							var m = o; if ($.type(o) === "object") {
								m = (o.desc || o.messageDesc) + " [code=" + (o.code || o.messageCode) + "]"
							}; obj.elem.append('<div class="' + cls + '">' + m + '<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button></div>')
						}); window.scrollTo(0, 0); return obj
					};
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
					obj.init = function (url, v) { if (url) $.get(url, function (res) { if (res != null) { if (res.data[0].rateCode != null) { exchange = res.data; } } setup(data = res.data, v); }); else setup(data, v); return this };
					obj.error = function (flag) { if (arguments.length == 0 || flag) obj.elem.parent().addClass("has-error"); else obj.elem.parent().removeClass("has-error"); return this }
					obj.disable = function (flag) { obj.elem.attr("disabled", (flag == null ? true : flag)); return this }
					obj.enable = function () { obj.disable(false); return this }
					obj.index = function () { if (arguments.length == 1) { var maxLen = data.length, index = arguments[0]; obj.elem.find("option").each(function (i, opt) { opt.removeAttribute("selected") }).eq(index < 0 ? maxLen + index : index).attr("selected", true); return this } return $.inArray("selected", obj.elem.find("option").map(function (i, opt) { return opt.selected ? "selected" : "no-selected" })) }
					obj.selected = function () { return data[obj.index()]; };
					obj.val = function () { return obj.elem.val(); };
					obj.key = function () { if (arguments.length == 0) { var key = obj.elem.find("option:selected").data("key"); if (typeof key === "string" && key === "undefined") { var item = data[obj.index()]; key = item && item.key ? item.key : item.id } return key } var key = arguments[0]; for (var i = 0, m = data.length; i < m; i++) { var item = data[i]; if (item.key && item.key == key) { obj.index(i); break; } if (item.id && item.id == key) { obj.index(i); break; } } return this }
					function setup(array, v) {
						obj.elem.find("*").remove();
						//$("#currencyTypeSelect").append('<option data-index="0" data-key="0" value="0">กรุณาเลือก</option>');
						obj.elem.append('<option data-index="-1" data-key="-1" value="">กรุณาเลือก</option>');
						$.each(array, function (i, o) {
							if (o.category == 'bank.name') {
								if (v == 'name') {
									obj.elem.append('<option data-index="' + i + '" data-key="' + o.code + '" value="' + o.descFullTh + '"e>' + o.descFullTh + '</option>')
								}
								else {
									obj.elem.append('<option data-index="' + i + '" data-key="' + o.code + '" value="' + o.code + '">' + o.code + '</option>')
								}
							}
							else {
								if (o.rateCode != null) {
									x = i;
									x++;
									obj.elem.append('<option data-index="' + x + '" data-key="' + x + '" value="' + o.message + '">' + o.message + '</option>')
								} else {
									obj.elem.append('<option data-index="' + i + '" data-key="' + o.key + '" value="' + o.value + '">' + o.value + '</option>')
								}
							}
						});
					}
					this.init(url);
				}
				function Listbox(el, url) {
					var obj = this, data = [{ key: "", value: "", text: "กรุณาเลือก" }]; obj.el = el; obj.elem = $(el);
					obj.data = function (array) { if ($.type(array) == "array") { setup(data = array); return this } return data; };
					obj.init = function (url) { if (url) $.get(url, function (res) { setup(data = res.data) }); else setup(data); return this };
					obj.index = function () { if (arguments.length == 1) { var maxLen = data.length, index = arguments[0]; obj.elem.find("option").each(function (i, opt) { opt.removeAttribute("selected") }).eq(index < 0 ? maxLen + index : index).attr("selected", true); return this } return $.inArray("selected", obj.elem.find("option").map(function (i, opt) { return opt.selected ? "selected" : "no-selected" })) }
					obj.selected = function () { return data[obj.index()]; }; obj.val = function () { return obj.elem.val(); }; obj.key = function () { if (arguments.length == 0) { var key = obj.elem.find("option:selected").data("key"); if (typeof key === "string" && key === "undefined") { var item = data[obj.index()]; key = item && item.key ? item.key : item.id } return key } var key = arguments[0]; for (var i = 0, m = data.length; i < m; i++) { var item = data[i]; if (item.key && item.key == key) { obj.index(i); break; } if (item.id && item.id == key) { obj.index(i); break; } } return this }
					function setup(array) { obj.elem.find("*").remove(); $.each(array, function (i, o) { obj.elem.append('<option data-index="' + i + '" data-key="' + o.name + '" value="' + o.name + '">' + (o.value) + '</option>') }); }
					data = obj.elem.change(window[el.substring(1) + "ChangeEvent"]).find("option").map(function (i, opt) { return { key: opt.getAttribute("data-key"), value: opt.value, text: opt.textContent } });
				}
				function ListboxBillingGroup(el, url) {
					var obj = this, data = [{ key: "", value: "", text: "กรุณาเลือก" }]; obj.el = el; obj.elem = $(el);
					obj.data = function (array) { if ($.type(array) == "array") { setup(data = array); return this } return data; };
					obj.init = function (url) { if (url) $.get(url, function (res) { setup(data = res.data) }); else setup(data); return this };
					obj.index = function () { if (arguments.length == 1) { var maxLen = data.length, index = arguments[0]; obj.elem.find("option").each(function (i, opt) { opt.removeAttribute("selected") }).eq(index < 0 ? maxLen + index : index).attr("selected", true); return this } return $.inArray("selected", obj.elem.find("option").map(function (i, opt) { return opt.selected ? "selected" : "no-selected" })) }
					obj.selected = function () { return data[obj.index()]; }; obj.val = function () { return obj.elem.val(); }; obj.key = function () { if (arguments.length == 0) { var key = obj.elem.find("option:selected").data("key"); if (typeof key === "string" && key === "undefined") { var item = data[obj.index()]; key = item && item.key ? item.key : item.id } return key } var key = arguments[0]; for (var i = 0, m = data.length; i < m; i++) { var item = data[i]; if (item.key && item.key == key) { obj.index(i); break; } if (item.id && item.id == key) { obj.index(i); break; } } return this }
					function setup(array) { obj.elem.find("*").remove(); $.each(array, function (i, o) { obj.elem.append('<option data-index="' + i + '" data-key="' + o.key + '" value="' + o.value + '">' + (o.value) + '</option>') }); }
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
					var content = '<div class="row"><div class="col-md-12"><div class="col-md-offset-2 col-md-10"><div class="form-horizontal"><div class="form-group"><label class="col-sm-3 control-label">' + (usrLabel || "User Name :") + '</label><div class="col-sm-6"><input type="text" class="form-control"></div></div><div class="form-group"><label class="col-sm-3 control-label">' + (pwdLabel || "Password :") + '</label><div class="col-sm-6"><input type="password" class="form-control"></div></div></div></div></div></div>';
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
					that.setPayType = new ButtonGroup("#setPayType");
					that.buttonAddBillingList = new Button("#buttonAddBillingList");
					that.addPayType = new Button("#addPayType");

					function ButtonGroup(el) {
						var obj = this, index = 0;
						obj.el = el;
						obj.elem = $(el);
						obj.val = function () { return obj.elem.find(".selection").text() }
						obj.index = function () { if (arguments.length == 1) { obj.list[arguments[0]].click() } return index }
						obj.list = obj.elem.find(".dropdown-menu a").each(function (i) { $(this).click(function () { index = i }) });
						obj.hideIndex = function () { if (arguments.length == 1) { var selected = obj.elem.find(".dropdown-menu a")[arguments[0]]; $(selected).addClass("hide"); } return index }
					}
				});
				self.dialog = new (function () {
					var that = this;
					that.customerSearch = new Modal("#customerSearch");
					that.customerPanel = new Panel("#customerPanel");
					that.receiptDetailsPanel = new Panel("#receiptDetailsPanel");
					that.payCaseSumPanel = new Panel("#payCaseSumPanel");
					//that.summaryPanel = new Panel("#summaryPanel");//by NSD 27-02-2017
					that.mainMessageDialog = new Message("#mainMessageDialog");
					that.advanceSearchMessageDialog = new Message("#advanceSearchMessageDialog");
					that.billingGroupMessageDialog = new Message("#billingGroupMessageDialog");
					function Modal(el) {
						this.el = el;
						this.elem = $(el);
						this.hide = function () { this.elem.modal("hide") };
						this.show = function () { this.elem.modal("show") };
					}
				});
				self.panel = new (function () {
					var that = this;
					/*that.summaryPanel = new Panel("#summaryPanel");
					 that.navigationPanel = new Panel("#navigationPanel");
					 that.fillDataInputPanel = new Panel("#fillDataInputPanel");
					 that.informationPanel = new Panel("#informationPanel");
					 that.linkPanel = new Panel("#linkPanel");
					 that.receiptInfoPanel = new Panel("#receiptInfoPanel");
					 that.tax = new FadePanel(self.button.setTaxType, "#withholdingTaxPanel","#feeTaxPanel", "#penaltyTaxPanel", "#retentionTaxPanel", "#compensationTaxPanel");*/
					that.pay = new FadePanel(self.button.setPayType, "#payType1", "#payType2", "#payType3", "#payType4", "#payType5", "#payType6", "#payType7", "#payType9", "#payType8");
					function Panel(el) {
						var obj = this, dura = 500; obj.el = el; obj.elem = $(el);
						obj.hide = function (ms) { obj.elem.hide(ms || dura); return this }; obj.show = function (ms) { obj.elem.show(ms || dura); return this }
					}
					function FadePanel() {
						var obj = this, index = 0, dura = 200;
						obj.els = $.map(arguments, function (o, i) { return $.type(o) !== "string" ? null : o });
						obj.elems = $(obj.els.join(","));
						obj.hide = function () { obj.elems.addClass("hide"); return this }; obj.show = function () { obj.elems.removeClass("hide"); return this }
						obj.prev = function () { obj.index(index - 1); return this }; obj.next = function () { obj.index(index + 1); return this }
						obj.index = function (ind) { if (ind === index) return this; var target = obj.elems.length > ind && ind > -1 ? ind : (ind >= obj.elems.length ? 0 : obj.elems.length - 1); obj.elems.eq(index).fadeOut(dura, function () { obj.elems.eq(index = target).removeClass("hide").css("display", "") }); return this }
						obj.elems.eq(index).hide().removeClass("hide").show(dura);
						arguments[0].list.each(function (i, o) { $(o).click(function () { obj.index(i) }) })
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
					that.payChqBankCode = new DropDown("#payChqBankCode").init("../findBankNameList.json", "code");//.data([{ key: "0", value: "001" },{ key: "1", value: "002" },{ key: "2", value: "003" }]);
					that.payChqBankName = new DropDown("#payChqBankName").init("../findBankNameList.json", "name");//.data([{ key: "0", value: "กรุณาเลือกธนาคาร" },{ key: "1", value: "กรุงเทพ" },{ key: "2", value: "กสิกรไทย" }]);
					that.payCCType = new DropDown("#payCCType").data([{ key: "1", value: "VISA" }, { key: "2", value: "MASTER" }]);
					that.payCCBankName = new DropDown("#payCCBankName").data([{ key: "1", value: "กรุงเทพ" }, { key: "2", value: "กสิกรไทย" }]);
					that.advSrcBillNo = new Input("#advSrcBillNo");
					that.advSrcSvcType = new DropDown("#advSrcSvcType").data([{ key: "Inmarsat Name", value: "Inmarsat Name" }, { key: "SLA", value: "SLA" }, { key: "Bundle Service Description", value: "Bundle Service Description" }, { key: "Bundle Service Flag", value: "Bundle Service Flag" }, { key: "Owner", value: "Owner" }, { key: "Brand", value: "Brand" }, { key: "Market Name", value: "Market Name" }]);
					that.advSrcSvcNo = new Input("#advSrcSvcNo");
					that.advSrcCusNo = new Input("#advSrcCusNo");
					that.advSrcCusTaxId = new Input("#advSrcCusTaxId");
					that.advSrcCusFirstName = new Input("#advSrcCusFirstName");
					that.advSrcCusLastName = new Input("#advSrcCusLastName");
					that.advSrcOrgName = new Input("#advSrcOrgName");
					that.billNo = new Input("#billNo", 18);
					that.barcode = new Input("#barcode", 62, { "billNo": [16, 34], "invoiceNo": [34, 52] });
					that.customerNo = new Input("#customerNo");
					that.egpNo = new Input("#egpNo");
					that.egpContract = new Input("#egpContract");
					that.customerName = new Input("#customerName");
					that.taxId = new Input("#taxId");
					that.branch = new Input("#branch");
					that.billingGroup = new Input("#billingGroup");
					that.changeReceiptAddress = new Input("#changeReceiptAddress");
					that.receiptAddress = new Input("#receiptAddress");
					that.invoiceAddress = new Input("#invoiceAddress");
					that.balance = new NumberInput("#balance");
					that.vat = new NumberInput("#vat");
					that.preDiscount = new NumberInput("#preDiscount");
					that.totalCharge = new NumberInput("#totalCharge");
					that.inputReceived = new NumberInput("#inputReceived");
					that.change = new NumberInput("#change");
					that.deduct = new NumberInput("#deduct");
					that.payCashAmount = new NumberInput("#payCashAmount");

					self.payBankTxnfDropDownBankBrnh = new DropDown("#payBankTxnfDropDownBankBrnh").data([{ key: "0123", value: "ปากเกร็ด" }]);
					self.payBankTxnfDropDownBankAcct = new DropDown("#payBankTxnfDropDownBankAcct").data([{ key: "1", value: "123-6-02069-3" }]);
					self.payBankTxnfDropDownBankAcCd = new DropDown("#payBankTxnfDropDownBankAcCd").data([{ key: "1", value: "11021044" }]);
					that.payBankTxnfDropDownBankCode = new DropDown("#payBankTxnfInputBankCode").init("../findBankNameList.json", "code");
					that.payBankTxnfDropDownBankName = new DropDown("#payBankTxnfInputBankName").init("../findBankNameList.json", "name");//.data([{ key: "0", value: "กรุณาเลือกธนาคาร" },{ key: "006", value: "กรุงไทย" }]);
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
				self.table = new (function () {
					var that = this;
					that.advSrcBillNoResultList = new BootstrapTable("#advSrcBillNoResultList");
					that.advSrcSvcNoResultList = new BootstrapTable("#advSrcSvcNoResultList");
					that.advSrcCusNoResultList = new BootstrapTable("#advSrcCusNoResultList");
					that.subscriptionList = new Table("#subscriptionList");
					that.invoiceList = new BootstrapTable("#invoiceList");
					that.historyList = new BootstrapTable("#historyList");
					that.changeList = new BootstrapTable("#changeList");
					that.payTypeList = new Table("#payTypeList");

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
				self.customerType = new Input("#customerType");
				self.custInfoInputAccruedAmount = new NumberInput("#custInfoInputAccruedAmount");
				self.custInfoInputAdvancedAmount = new NumberInput("#custInfoInputAdvancedAmount");
				self.custInfoInputStatus = new Input("#custInfoInputStatus");
				self.custInfoInputCurrencyCode = new Input("#custInfoInputCurrencyCode");
				self.custInfoInputVatRate = new Input("#custInfoInputVatRate");
				self.advancedCheckbox = new CheckBox("[name=advancedCheckbox]");
				self.advancedAmount = new NumberInput("#advancedAmount").disable();
				self.checkboxAdditionalDiscount = new CheckBox("#checkboxAdditionalDiscount");
				self.inputAdditionalDiscount = new NumberInput("#inputAdditionalDiscount");
				self.inputCollectionUnit = new Input("#inputCollectionUnit");
				self.panelSummaryPaymentList = new Panel("#panelSummaryPaymentList");
				self.tableSummaryReceipts = new BootstrapTable("#tableSummaryReceipts");

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
					}
				});
				this.inputServiceType = new DropDown("#inputServiceType");
				//this.inputServiceName = new Input("#inputServiceName");
				this.inputServiceName = new DropDown("#inputServiceName");
				this.inputServiceType = new DropDown("#inputServiceType");
				this.inputAmount = new NumberInput("#inputAmount");
				this.inputPaymentDesc = new Input("#inputPaymentDesc");
				this.tableServiceList = new BootstrapTable("#tableServiceList");
				this.totalCharge = new NumberInput("#totalCharge");
				this.inputReceived = new NumberInput("#inputReceived");
				this.change = new NumberInput("#change");
				this.remark = new Input("#remark");
				this.pntextradio = new Radio("[name=pntextradio]");
				//this.customerNo = new Input("#customerNo");

				self.inputAdditionalRemark = new Input("#inputAdditionalRemark");
				this.dialogAuthentication = new AuthenticationDialog(function (res) { console.log(res); }, function (res) { }, "../checkAuthorize.json");

				$('#payChqBankCode').click(function () {
					var key = view.input.payChqBankCode.key();
					$('#payChqBankName option:selected').prop('selected', false);
					$('#payChqBankName option[data-key=' + key + ']').prop('selected', true);
				});
				$('#payChqBankName').click(function () {
					var key = view.input.payChqBankName.key();
					$('#payChqBankCode option:selected').prop('selected', false);
					$('#payChqBankCode option[data-key=' + key + ']').prop('selected', true);
				});
				$('#payBankTxnfInputBankCode').click(function () {
					var key = view.input.payBankTxnfDropDownBankCode.key();
					$('#payBankTxnfInputBankName option:selected').prop('selected', false);
					$('#payBankTxnfInputBankName option[data-key=' + key + ']').prop('selected', true);
				});
				$('#payBankTxnfInputBankName').click(function () {
					var key = view.input.payBankTxnfDropDownBankName.key();
					$('#payBankTxnfInputBankCode option:selected').prop('selected', false);
					$('#payBankTxnfInputBankCode option[data-key=' + key + ']').prop('selected', true);
				});

				return this;
			})(jQuery);

			$(document).ready(function () {

				// Added 20161201. Detect barcode scanner.
				var chars = [];
				document.body.addEventListener("keydown", function (e) {
					e = e || window.event;
					var key = e.which || e.keyCode; // keyCode detection
					var ctrl = e.ctrlKey ? e.ctrlKey : ((key === 17) ? true : false); // ctrl detection
					if (key == 77 && ctrl) {
						chars.push(" ");
					}
				}, false);

				$("#barcode").keypress(function (e) {
					if (e.which === 13) {
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
				view.dialog.customerPanel.hide();
				view.dialog.receiptDetailsPanel.hide();
				view.dialog.payCaseSumPanel.hide();
				view.button.submitPayment.disable();
				if (isNew) {
					view.session("billingList", []);
				}
				if (isManual) {
					view.session("isManual", isManual);
				}
				var billingList = view.session("billingList");
				if (billingList.length > 0) {
					view.button.addPayment.disable();
					view.button.summaryPayment.badge(billingList.length);
					//view.dialog.navigatePanel.show();
				}
			}).on("click", view.button.advanceSearch.el, function () { view.dialog.customerSearch.show() });

			function searchClickEvent() {
				// console.log("Test test = "+view.input.barcode);
				var isBarcode = !view.input.barcode.isEmpty();
				var barCodeArr = isBarcode ? view.input.barcode.val().split(" ") : [];
				console.log("Barcode Array : " + barCodeArr);
				console.log("isBarcode : " + isBarcode);
				var billNo = isBarcode ? /*view.input.barcode.get("billNo")*/barCodeArr[1] : view.input.billNo.val();
				console.log("billNo : " + billNo);
				view.dialog.mainMessageDialog.clear();
				if (!isBarcode && billNo.length < 4) {
					view.dialog.mainMessageDialog.error(["โปรดระบุ 'เลขที่ลูกค้า' อย่างน้อย 4 ตัวอักษร"]).show();
					return false;
				} else if (isBarcode && billNo.length < 4) {
					view.dialog.mainMessageDialog.error(["โปรดระบุ 'Barcode / QRCode' ด้วยรูปแบบที่ถูกต้อง"]).show();
					return false;
				}
				view.dialog.customerPanel.hide(1);
				view.dialog.payCaseSumPanel.hide(1);


				view.input.balance.val(0);
				view.input.vat.val(0);
				view.input.totalCharge.val(0);
				view.input.deduct.val(0);
				view.inputAdditionalDiscount.val(0);
				view.dialog.mainMessageDialog.clear().showLoad("ระบบกำลังรอข้อมูลจาก CRM : {timeCnt} วินาที")
				view.dialog.billingGroupMessageDialog.clear();
				window.billNoSelected = billNo;		// Added 20161218. Put param billNo for fixing search invoice.
				console.log("window.billNoSelected = " + window.billNoSelected);
				get("../service/bill-profiles/search/no", { "no": (window.billNoSelected = billNo) }, function (res) {
					//alert('there is repsonse back');
					var profile = res;
					console.log("bill Group[" + $.trim(profile.billGroup) + "]")
					var dataSendCount = {
						"billingGroup": $.trim(profile.billGroup)
					};
					$.ajax({
						type: "POST",
						url: "../countBillingGroupIgnore.json",
						data: JSON.stringify(dataSendCount),
						dataType: "json",
						contentType: "application/json; charset=utf-8",
						success: function (data) {
							if (data > 0) {
								view.dialog.billingGroupMessageDialog.error(["This Billing Group is ignored."]).show();
							} else {
								view.dialog.receiptDetailsPanel.show();//by NSD 27-02-2017
								view.dialog.payCaseSumPanel.show();
								//  view.dialog.summaryPanel.show();
								view.session("billingGroup", $.trim(profile.billGroupFull));
								view.session("billingCurrency", profile.currency);
								//view.session("receiptFormat","2");// 0 = receive + wh , 1=receive only , 2= wh only
								view.tab.customerInfoTab.index(0);
								view.dialog.customerPanel.slideDown(500, function () { /* view.dialog.navigatePanel.slideDown() */ });

								//view.dialog.detailsPanel.show(900);

								/*var summaryList = (view.session("billingList") || []).length;
								view.button.addPayment.disable();
								view.button.summaryPayment.disable(summaryList == 0).badge(summaryList);
								view.button.submitPayment.disable(summaryList == 0);
								view.table.invoiceList.showLoad();*/

								view.input.receiptAddress.disable();
								view.input.invoiceAddress.disable();
								view.input.customerNo.val(profile.no);
								view.input.egpContract.val(profile.egpContact);
								view.input.egpNo.val(profile.egpNumber);
								view.input.customerName.val(profile.customerAccountName);
								view.input.taxId.val(profile.taxRegisterNo);
								view.input.branch.val(profile.branchId);
								view.input.billingGroup.val($.trim(profile.billGroup) + " - " + $.trim(profile.billGroupFull)/*.replace(new RegExp("^"+ profile.billGroup, "g"), profile.billGroup +" -")*/);
								view.inputCollectionUnit.val(profile.collectionUnit);
								view.custInfoInputCurrencyCode.val(profile.currency);
								view.custInfoInputVatRate.val(profile.vatType);
								view.custInfoInputStatus.val("ปกติ");
								view.inputCustomerSegment.key(profile.accountCategoryLookup);
								view.advancedCheckbox.unchecked();
								view.advancedAmount.disable();



								//	 		get(profile._links.customer.href, {}, function(customer){
								//view.input.egpNo.val(customer.no);
								var custType = profile.type;
								if (profile.accountCategoryLookup == "2") custType = "stateagency";
								//	 			if (customer.segment) {
								//	 				var segmentId = customer.segment.id;
								//	 				view.inputCustomerSegment.key(segmentId);
								//	 				if (segmentId == "2") custType = "stateagency";
								//	 			}
								view.customerType.val(custType);
								// Added and commented 20161218 2 Lines Below. Put param billNo for fixing search invoice.
								// get("../service/bill-addrs/search/no", { "no": view.input.billNo.val() }, function(res){
								get("../service/bill-addrs/search/no", { "no": billNoSelected }, function (res) {
									var addr = res._embedded.billAddresses[0];
									view.input.receiptAddress.val($.trim(addr.vatAddrLine1) + " " + $.trim(addr.vatAddrLine2) + " " + $.trim(addr.vatAddrLine3) + " " + $.trim(addr.vatAddrLine4) + " " + $.trim(addr.vatKhetAmphur) + " " + $.trim(addr.vatProvince) + " " + $.trim(addr.vatPostCode));
									view.input.invoiceAddress.val($.trim(addr.billAddrLine1) + " " + $.trim(addr.billAddrLine2) + " " + $.trim(addr.billAddrLine3) + " " + $.trim(addr.billAddrLine4) + " " + $.trim(addr.billKhetAmphur) + " " + $.trim(addr.billProvince) + " " + $.trim(addr.billPostCode));
								}, view.dialog.mainMessageDialog);
								console.log("------>billNoSelected : " + billNoSelected + "");
								// Added and commented 20161218 2 Lines Below. Put param billNo for fixing search invoice.
								// get("../findInvoiceList.json", { "no": view.input.billNo.val() }, function(res){
								var dataSend = {
									"no": billNoSelected
								};
								$.ajax({
									type: "GET",
									url: "../findInvoiceList.json",
									data: dataSend,
									dataType: "json",
									contentType: "application/json; charset=utf-8",
									success: function (res) {
										/*var invoiceData = [];
										if(res.data.length>0)
											invoiceData = $.map(res.data, invoiceListMapper);
										    
										if(invoiceData.length > 0) view.table.invoiceList.rawData(invoiceData);
										view.advancedCheckbox.disable(invoiceData.length > 0);*/
										var accruedAmount = 0, advancedAmount = 0; $.each(res.data, function (i, o) { accruedAmount += parseFloat(o.balanceDue, 10); if (o.billNo == "0") advancedAmount += Math.abs(parseFloat(o.totalPaid, 10)); });
										view.custInfoInputAccruedAmount.val(accruedAmount);
										view.custInfoInputAdvancedAmount.val(advancedAmount);

										var combineInvoiceList = [];
										$.ajax({
											type: "GET",
											url: "../findWriteOffInvoiceList.json",
											data: dataSend,
											dataType: "json",
											contentType: "application/json; charset=utf-8",
											success: function (res) {
												/*var writeOffInvoiceData = [];
												if(res.data.length>0)
													writeOffInvoiceData = $.map(res.data, writeOffInvoiceListMapper);
	  
												combineInvoiceList.push.apply(combineInvoiceList,invoiceData);
												combineInvoiceList.push.apply(combineInvoiceList,writeOffInvoiceData);
												if(isBarcode){
													var defaultInv = barCodeArr[2].substr(0, barCodeArr[2].length-8);
													$.each(combineInvoiceList, function(i,o){
														if(o.invoiceNo == defaultInv){
															o.checked = true;
														}
													});
													invoiceListCheckEvent(null);
												}
												view.table.invoiceList.rawData(combineInvoiceList);
												view.advancedCheckbox.disable(combineInvoiceList.length > 0);*/

												$.each(res.data, function (i, o) { accruedAmount += parseFloat(o.balanceDue, 10); if (o.billNo == "0") advancedAmount += Math.abs(parseFloat(o.totalPaid, 10)); });
												view.custInfoInputAccruedAmount.val(accruedAmount);
												view.custInfoInputAdvancedAmount.val(advancedAmount);

												/*view.table.invoiceList.hideLoad();
												view.tab.invoiceDetailsTab.show(0);*/
											}
										});

									}
								});

							}
						}

					});
				}, view.dialog.mainMessageDialog);
				// Credit Limit.
				get("../findSubscription.json", { "no": (window.billNoSelected = billNo) }, function (res) {
					//var data = []; $.each(res.data, function(i,o){ data.push([o.subscrNo]) });
					var serNoList = [];
					var data = []; $.each(res.data, function (i, o) { data.push(["-", o.subscrType, o.subscrNo, o.statusName]); serNoList.push({ key: o.subscrType, value: o.subscrNo }) });//by NSD 14-03-2017
					console.log(data); console.log('xxxxx');
					view.table.subscriptionList.data(data);
					console.log(view.table.subscriptionList.data());
					view.inputServiceName.data(serNoList);
					view.session("subScriptionList", data);
				});
			}
			view.tab.customerInfoTab.init(1, function (e) {
				view.table.subscriptionList.showLoad();
				get("../findSubscription.json", { "no": billNoSelected }, function (res) {
					var data = []; $.each(res.data, function (i, o) { data.push(["-", o.subscrType, o.subscrNo, o.statusName]) });
					view.table.subscriptionList.data(data);
				}, view.dialog.mainMessageDialog, function () { view.table.subscriptionList.hideLoad() });
			});

			$("#testmenu2 li a").click(function () { $(this).parents(".btn-group").find('.selection').text($(this).text()); $(this).parents(".btn-group").find('.selection').val($(this).text()); });
			function buttonAddBillingListClickEvent() {
				//console.log(view.inputServiceName.val()+' 551 '+view.inputServiceType.val()+' 552 '+view.inputAmount.val()+' 553 '+view.inputPaymentDesc.val());

				if (!isRadioExtPnt()) {
					return false;
				} else if (isParamEmptyRequireField()) {
					return false;
				} else {

					/*$("#tableServiceList").bootstrapTable('insertRow', {
						index: 1,
						row: {
		
							serviceName: '55TTT',
							serviceType: 'OK'
						}
					});*/
					var paymentDesc = view.inputPaymentDesc.val();
					if (paymentDesc == '') {
						paymentDesc = view.pntextradio.val() + 'หมายเลขบริการ ' + view.inputServiceName.val();
					}
					view.tableServiceList.insert({
						"serviceName": view.inputServiceName.val()//view.inputCustomerName.val()
						//,"serviceNo": view.inputServiceNo.val()
						, "serviceType": view.inputServiceName.key()//view.inputServiceType.selected().text.trim()//view.inputTopupService.selected().text by NSD 16-02-2017
						//,"promotion": view.inputPromotion.val()
						//,"discount": view.inputServiceDiscount.val()
						, "amount": numberFormatter(view.inputAmount.val())
						, "serviceAmount": numberFormatter(view.inputAmount.val())
						, "servicename": view.pntextradio.val()
						//by NSD 01-02-2017
						, "custNo": view.input.customerNo.val()
						, "custType": view.customerType.val()//$('#inputCustomerSegment').val()
						, "custTaxId": view.input.taxId.val()
						, "custBranch": view.input.branch.val()
						, "address1": view.input.receiptAddress.val()
						//,"excAmount": excAmount
						//,"discount": disExcVatAmt
						//,"excAmountIncDisc": excAmountIncDisc
						//,"vatAmount": vatIncDisAmount
						//,"serviceTypeName": view.inputServiceType.val() //by NSD 16-02-2017
						, "paymentDesc": paymentDesc//view.inputPaymentDesc.val()//by NSD 22-02-2017
						, "remark": view.remark.val()
						, "custName": view.input.customerName.val()
						, "custCategoryDesc": $("#inputCustomerSegment option:selected").text()
						, "ref1": view.pntextradio.val()

					});
					calculate();
				}
			}
			function calculate() {
				var billingList = []; //var dtList = [];
				// temporary calculate
				var serviceList = $("#tableServiceList").bootstrapTable("getData");//view.tableServiceList.data();
				//var receivedAmt = view.inputReceived.val();
				var receivedAmt = view.table.payTypeList.sum(2);
				var changeAmt = view.change.val();
				var ttCharge = 0.00;
				console.log(serviceList);
				var custNoInitial = "";
				$.each(serviceList, function (i, data) {
					ttCharge += parseFloat(data.serviceAmount);

					var billingObj = {
						"custNo": data.custNo
						, "custName": data.custName
						, "custType": data.custType
						, "custTaxId": data.custTaxId
						, "custBranch": data.custBranch
						, "address1": data.address1
						, "address2": ""
						//,"vatRate": view.currentVatRate
						, "remark": data.remark
						//,"promotion": dt.promotion//by NSD 22-02-2017
						//,"split": false
						, "isFullTypeReceipt": true//document.getElementById('isFullReceipt').checked
						, "serviceList": serviceList//view.tableTopupServiceList.data()
						, "custCategoryDesc": data.custCategoryDesc
					};
					if (custNoInitial != billingObj.custNo) {
						billingList.push(billingObj);
						custNoInitial = billingObj.custNo;
					}

				});
				if (receivedAmt > ttCharge) {
					changeAmt = receivedAmt - ttCharge;
				}
				view.change.val(changeAmt);
				view.totalCharge.val(ttCharge);
				view.input.payCashAmount.val(ttCharge);
				view.session("billingList", billingList);
			}
			function validateAddCriteria() {
				return true;
			}
			function deleteRow(row) {
				$('#tableServiceList').bootstrapTable('remove', {
					field: 'serviceName',
					values: [row.serviceName]
				});
				calculate();
			}
			function addPayTypeClickEvent() {
				var index = view.button.setPayType.index(), payAmt = view.input.payCashAmount.val();
				if (index == 1) { window["payChqListData"] = view.table.payChqList.data(); payAmt = view.table.payChqList.sum(6) }
				else if (index == 2) { window["payCCListData"] = view.table.payCCList.data(); payAmt = view.table.payCCList.sum(4) }
				else if (index == 3) { window["payMoneyOrderTableListData"] = view.payMoneyOrderTableList.data(); payAmt = view.payMoneyOrderTableList.sum(4) }
				else if (index == 4) { window["payBillExchgTableListData"] = view.payBillExchgTableList.data(); payAmt = view.payBillExchgTableList.sum(4) }
				else if (index == 5) { window["payCouponTableListData"] = view.payCouponTableList.data(); payAmt = view.payCouponTableList.sum(4) }
				else if (index == 6) { window["payBankTxnfTableListData"] = view.payBankTxnfTableList.data(); payAmt = view.payBankTxnfTableList.sum(6) }
				else if (index == 9) { window["payBankTxnfTableListGfData"] = view.payBankTxnfTableListGf.data(); payAmt = view.payBankTxnfTableListGf.sum(6) }
				else if (index == 7) { window["payOffsetTableListData"] = view.payOffsetTableList.data(); payAmt = view.payOffsetTableList.sum(4) }
				else if (index == 8) { window["payOtherTableListData"] = view.payOtherTableList.data(); payAmt = view.payOtherTableList.sum(4) }
				view.table.payTypeList.find("method", index).remove();
				view.table.payTypeList.insert(["-", view.button.setPayType.val(), payAmt], true, { method: index });
				view.inputReceived.val(view.table.payTypeList.sum(2));
				//if(view.allowedCurrenyCode) {
				view.button.submitPayment.enable();
				//view.button.submitPaymentEng.enable();
				//}
				//updateDeduction();
				calculate();
			}

			function submitPaymentClickEvent() {
				submitPayment("TH");
			}
			function submitPayment(receiptLang) {
				var balanceDue = view.input.totalCharge.val(), received = view.inputReceived.val(), change = view.input.change.val();
				if (balanceDue > received) {
					view.dialog.mainMessageDialog.clear().error(["ระบบไม่อนุญาตให้จ่าย เนื่องจากจำนวนเงินไม่ถูกต้อง"]).show();
					return false;
				}
				var params = {
					//"amount": view.amount
					"payAmount": balanceDue//view.inputTotalCharge.val()
					//,"discount": view.inputAdditionalDiscount.val()
					//,"vatAmount": view.inputVat.val()
					, "totalPaid": balanceDue
					//,"wtAmount": deduction
					, "receiveAmount": balanceDue
					, "remainAmount": change
					, "paymentCase": $.map(view.table.payTypeList.data(), function (a, i) { return a[1] }).join(" + ")
					, "remark": ""
				};
				console.log("step2 initial params set.");
				console.log(params);
				//step2 deduction skip

				var paymentCase = "";
				// <!-- Pay Method. -->
				var methodIndex = 0, cashRow = view.table.payTypeList.find("method", 0);
				$.map((window["payChqListData"] || []), function (o, i) { // For: Cheque
					if (chequeRow.length > 0) {	// Fix by EPIS8 23/12/2016 refer issue no.57
						console.log(o);
						params["methods[" + methodIndex + "].type"] = "CHEQUE";
						params["methods[" + methodIndex + "].code"] = "CH";
						params["methods[" + methodIndex + "].name"] = "เช็ค";
						params["methods[" + methodIndex + "].chequeNo"] = $.trim(o[4]);
						params["methods[" + methodIndex + "].amount"] = o[6].replace(/[,]/g, "");
						params["methods[" + methodIndex + "].payChqBankCode"] = $.trim(o[1]);
						params["methods[" + methodIndex + "].payChqBankName"] = $.trim(o[2]);
						params["methods[" + methodIndex + "].payChqBranch"] = $.trim(o[3]);
						params["methods[" + methodIndex++ + "].payChqDate"] = $.trim(o[5]);

						paymentCase = paymentCase.concat("เช็ค " + $.trim(o[2]) + " เลขที่: " + $.trim(o[4]) + " + ");
					}
				});
				$.map((window["payCCListData"] || []), function (o, i) { // For: Credit Card
					params["methods[" + methodIndex + "].type"] = "CREDITCARD";
					params["methods[" + methodIndex + "].code"] = "CR";
					params["methods[" + methodIndex + "].name"] = "บัตรเครดิต";
					params["methods[" + methodIndex + "].cardType"] = $.trim(o[1]);
					params["methods[" + methodIndex + "].cardNo"] = $.trim(o[2]);
					params["methods[" + methodIndex + "].bankName"] = $.trim(o[3]);
					params["methods[" + methodIndex++ + "].amount"] = o[4].replace(/[,]/g, "");
					paymentCase = paymentCase.concat("บัตรเครดิต " + $.trim(o[1]) + " เลขที่: " + $.trim(o[2]) + " + ");//by NSD 26-02-2017
				});
				$.map((window["payMoneyOrderTableListData"] || []), function (o, i) { // For: Money Order
					params["methods[" + methodIndex + "].type"] = "MONEYORDER";
					params["methods[" + methodIndex + "].code"] = "MO";
					params["methods[" + methodIndex + "].name"] = "ธนาณัติ";
					params["methods[" + methodIndex + "].mnyOrderNo"] = $.trim(o[1]);
					params["methods[" + methodIndex + "].mnyOrderDt"] = o[2] + " 00:00:00";
					params["methods[" + methodIndex++ + "].amount"] = o[4].replace(/[,]/g, "");
					paymentCase = paymentCase.concat("ธนาณัติ เลขที่: " + $.trim(o[1]) + " + ");
				});
				$.map((window["payBillExchgTableListData"] || []), function (o, i) { // For: Bill of Exchange
					params["methods[" + methodIndex + "].type"] = "BILLEXCHANGE";
					params["methods[" + methodIndex + "].code"] = "BX";
					params["methods[" + methodIndex + "].name"] = "ตั๋วแลกเงิน";
					params["methods[" + methodIndex + "].trnfNo"] = $.trim(o[1]);
					params["methods[" + methodIndex + "].transferDt"] = o[2] + " 00:00:00";
					params["methods[" + methodIndex++ + "].amount"] = o[4].replace(/[,]/g, "");
					paymentCase = paymentCase.concat("ตั๋วแลกเงิน เลขที่: " + $.trim(o[1]) + " + ");
				});
				$.map((window["payCouponTableListData"] || []), function (o, i) { // For: Coupon
					params["methods[" + methodIndex + "].type"] = "COUPON";
					params["methods[" + methodIndex + "].code"] = "CP";
					params["methods[" + methodIndex + "].name"] = "คูปอง";
					params["methods[" + methodIndex + "].couponNo"] = $.trim(o[1]);
					params["methods[" + methodIndex++ + "].amount"] = o[4].replace(/[,]/g, "");
					paymentCase = paymentCase.concat("คูปอง เลขที่: " + $.trim(o[1]) + " + ");
				});
				$.map((window["payBankTxnfTableListData"] || []), function (o, i) { // For: Money Transfer
					params["methods[" + methodIndex + "].type"] = "BANKTRANSFER";
					params["methods[" + methodIndex + "].code"] = "TR";
					params["methods[" + methodIndex + "].name"] = "เงินโอนในประเทศ";
					params["methods[" + methodIndex + "].bankAcct"] = o[4];
					params["methods[" + methodIndex + "].transferDt"] = o[5] + " 00:00:00";
					params["methods[" + methodIndex + "].isBackDt"] = "${epContext.roleName}" == "GFMISAGENT";
					params["methods[" + methodIndex++ + "].amount"] = o[6].replace(/[,]/g, "");
					paymentCase = paymentCase.concat("เงินโอนในประเทศ + ");
				});
				$.map((window["payBankTxnfTableListGfData"] || []), function (o, i) { // For: Money Transfer
					params["methods[" + methodIndex + "].type"] = "BANKTRANSFER";
					params["methods[" + methodIndex + "].code"] = "GF";
					params["methods[" + methodIndex + "].name"] = "เงินโอน (GFMIS)";
					params["methods[" + methodIndex + "].bankAcct"] = o[4];
					params["methods[" + methodIndex + "].transferDt"] = o[5] + " 00:00:00";
					params["methods[" + methodIndex + "].isBackDt"] = "${epContext.roleName}" == "GFMISAGENT";
					params["methods[" + methodIndex++ + "].amount"] = o[6].replace(/[,]/g, "");
					paymentCase = paymentCase.concat("เงินโอน (GFMIS) + ");
				});
				$.map((window["payOffsetTableListData"] || []), function (o, i) { // For: Offset
					params["methods[" + methodIndex + "].type"] = "OFFSET";
					params["methods[" + methodIndex + "].code"] = "OF";
					params["methods[" + methodIndex + "].name"] = "offset";
					params["methods[" + methodIndex++ + "].amount"] = o[4].replace(/[,]/g, "");
					paymentCase = paymentCase.concat("offset " + o[1] + " + ");
				});
				$.map((window["payOtherTableListData"] || []), function (o, i) { // For: Other Type
					params["methods[" + methodIndex + "].type"] = "OTHER";
					params["methods[" + methodIndex + "].code"] = "OT";
					params["methods[" + methodIndex + "].name"] = "อื่น ๆ";
					params["methods[" + methodIndex++ + "].amount"] = o[4].replace(/[,]/g, "");
					paymentCase = paymentCase.concat("อื่น ๆ + ");
				});
				if (cashRow.length == 1) { // For: Cash
					params["methods[" + methodIndex + "].type"] = "CASH";
					params["methods[" + methodIndex + "].code"] = "CC";
					params["methods[" + methodIndex + "].name"] = "เงินสด";
					params["methods[" + methodIndex++ + "].amount"] = cashRow.find("td:eq(2) div").text().replace(/[,]/g, "");
					paymentCase = paymentCase.concat("เงินสด + ");
				}
				var watPaymentCaseConclude = "";
				var paymentCaseArr = paymentCase.substring(0, paymentCase.length - 3).split(" + ");
				var paymentCaseConclude = $.each(paymentCaseArr, function (i, o) { return o }).join(" + ");
				params["paymentCase"] = paymentCaseConclude;
				//console.log("step 3 finished"); console.log(params["paymentCase"]);
				console.log("step4 Payment set.");
				// <!-- Invoice List. -->
				var custIndex = 0, billingList = view.session("billingList"), receiptList = [];
				console.log('55551'); console.log(billingList); console.log('55551');
				while (customer = billingList[custIndex]) {
					params["customers[" + custIndex + "].custNo"] = customer.custNo;
					params["customers[" + custIndex + "].custName"] = customer.custName;
					params["customers[" + custIndex + "].custType"] = customer.custType;
					params["customers[" + custIndex + "].address1"] = customer.address1;
					params["customers[" + custIndex + "].address2"] = customer.address2;
					params["customers[" + custIndex + "].remark"] = customer.remark;
					params["customers[" + custIndex + "].taxNo"] = customer.custTaxId;
					params["customers[" + custIndex + "].custBranch"] = customer.custBranch;
					params["customers[" + custIndex + "].split"] = customer.split;
					params["customers[" + custIndex + "].custCategoryDesc"] = customer.custCategoryDesc;
					for (var serviceIndex = 0, n = customer.serviceList.length; serviceIndex < n; serviceIndex++) {
						var service = customer.serviceList[serviceIndex];
						params["customers[" + custIndex + "].services[" + serviceIndex + "].code"] = service.serviceName;
						params["customers[" + custIndex + "].services[" + serviceIndex + "].no"] = service.serviceName;
						params["customers[" + custIndex + "].services[" + serviceIndex + "].name"] = service.paymentDesc;
						//params["customers["+ custIndex +"].services["+ serviceIndex +"].subscriber"] = service.customerName;
						params["customers[" + custIndex + "].services[" + serviceIndex + "].amount"] = service.amount;//by NSD 06-02-2017//(service.amount*100/(100 + parseFloat(view.session("vatRate0")))).toFixed(2);//service.amount*(1.00 - view.currentVatRate);
						//params["customers["+ custIndex +"].services["+ serviceIndex +"].discount"] = service.discount;
						//params["customers["+ custIndex +"].services["+ serviceIndex +"].charge"] = service.excAmountIncDisc;//by NSD 06-02-2017//(service.amount*100/(100 + parseFloat(view.session("vatRate0")))).toFixed(2);//service.amount*(1.00 - view.currentVatRate);
						//params["customers["+ custIndex +"].services["+ serviceIndex +"].vat"] = (service.amount * parseFloat(view.session("vatRate0"))/(100+parseFloat(view.session("vatRate0")))).toFixed(2);//service.amount * view.currentVatRate;
						params["customers[" + custIndex + "].services[" + serviceIndex + "].totalCharge"] = service.amount;
						params["customers[" + custIndex + "].services[" + serviceIndex + "].serviceName"] = service.servicename;
						//params["customers["+ custIndex +"].services["+ serviceIndex +"].deduction"] = 0.00;
						//params["customers["+ custIndex +"].services["+ serviceIndex +"].vatRate"] = parseFloat(view.session("vatRate0"));

						//params["customers["+ custIndex +"].services["+ serviceIndex +"].serviceTypeName"] = service.serviceTypeName;//by NSD 16-02-2017
						//params["customers["+ custIndex +"].services["+ serviceIndex +"].promotion"] = service.promotion;//by NSD 22-02-2016
					}
					custIndex++;
				}
				console.log("step5 Service inv List set.");
				console.log("-- Ready to post! --");
				console.log(params);
				/*$.ajax({
					url: "../createPaymentPenaltyExtend.json",
					type: "POST",
					data: params,
					success: function(res){
						alert('success111');
						console.log('5555555555555');
						console.log(res);
						console.log('555555555555');
			
						$(document.body).append('<form id="printRCPT" action="../printPaymentPenaltyExtendReceipt.pdf" method="post" target="_printForm"></form>');
						var form = document.getElementById("printRCPT"); function input(n,v) { var input = document.createElement("input"); input.type = "hidden"; input.name = n; input.value = v; return input }
						$.each(res.data, function(i,o){
							form.appendChild(input("receipts["+ i +"].id", o.id));
						});
						form.submit();
						$("#penaltyExtendForm").hide();
						view.panelSummaryPaymentList.show(400);
						var data = [];
						$.each(res.data, function(i,r){
							custIndex = 0;
							//while (customer = billingList[custIndex]) {
								//if(customer.custNo == r.accountNo) {
									data.push({ "custNo": r.accountNo
										,"custName": r.accountName
										,"receiptNo": r.no
										,"receiptAmount": view.utils.numberFormat(r.totalCharge)
										,"status": '<span class="glyphicon glyphicon-ok-circle"></span> บันทึกลงระบบสำเร็จ'
										,"services": customer.serviceList})
								//}
								//custIndex++;
							//}
						});
						view.tableSummaryReceipts.data(data);
			
				}
			});*/
				post("../createPaymentPenaltyExtend.json", params, function (res) {

					$(document.body).append('<form id="printRCPT" action="../printPaymentPenaltyExtendReceipt.pdf" method="post" target="_printForm"></form>');
					var form = document.getElementById("printRCPT"); function input(n, v) { var input = document.createElement("input"); input.type = "hidden"; input.name = n; input.value = v; return input }
					$.each(res.data, function (i, o) {
						form.appendChild(input("receipts[" + i + "].id", o.id));
					});
					form.submit();
					$("#penaltyExtendForm").hide();
					view.panelSummaryPaymentList.show(400);
					var data = [];
					$.each(res.data, function (i, r) {
						custIndex = 0;
						//while (customer = billingList[custIndex]) {
						//if(customer.custNo == r.accountNo) {
						data.push({
							"custNo": r.accountNo
							, "custName": r.accountName
							, "receiptNo": r.no
							, "receiptAmount": view.utils.numberFormat(r.totalCharge)
							, "status": '<span class="glyphicon glyphicon-ok-circle"></span> บันทึกลงระบบสำเร็จ'
							, "services": r.services						
});
						//}
						//custIndex++;
						//}
					});
					console.log('xxxxx'); console.log(data); console.log('xxxxx');
					view.tableSummaryReceipts.data(data);
					$("#tableSummaryReceipts").bootstrapTable("load", data);
				}, view.message);
			}

			function detailFormatter(val, row, ind) {
				var details = '<table class="table table-striped table-bordered">'
					+ '<thead>'
					+ '<tr>'
					+ '<th class="text-center"> รายการ </th>'
					+ '<th class="text-center"> หมายเลขบริการ</th>'
					+ '<th class="text-right"> ยอดเงิน</th>'
					+ '<th class="text-right"> ยอดชำระ </th>'
					+ '</tr>'
					+ '</thead>' + '<tbody>';
				for (var i = 0, m = row.services.length; i < m; i++) {
					details += '<tr>'
						+ '<td class="text-center">' + $.trim(row.services[i].productName) + '</td>'
						+ '<td class="text-center">' + $.trim(row.services[i].productCode) + '</td>'
						+ '<td class="text-right">' + view.utils.numberFormat(row.services[i].totalCharge) + '</td>'
						+ '<td class="text-right">' + view.utils.numberFormat(row.services[i].totalCharge) + '</td>'
						+ '</tr>';
				}
				return details + '</tbody></table>';
			}

			function isParamEmptyRequireField() {
				var paramRequireField = paramSelectorArray.slice();
				var index = paramRequireField.indexOf('#id');
				if (index > -1) {
					paramRequireField.splice(index, 1);
				}
				return isEmptyRequireField(paramRequireField);
			}
			function isRadioExtPnt() {
				var radioExtPnt = $("#radioExtPnt");
				var pntextradioPnt = $("input[name='pntextradio']");
				if (!$("input[name='pntextradio']:checked").val()) {
					$(radioExtPnt).attr("style", "color:#a94442");
					$(radioExtPnt).attr("style", "background-color:#ffffaa;border:solid 1px #a94442;padding: 3px 15px;;border-radius: 4px;");
				} else {
					$(radioExtPnt).removeAttr("style");
					return true;
				}
			}

			function isEmptyRequireField(selectorArray) {
				var emptyList = []; var emptRm = [];
				$.each(selectorArray, function (index, selector) {
					if ($.trim($(selector).val()) == '') {
						emptyList.push(selector);
					} else {
						emptRm.push(selector)
					}
				});
				if (emptyList.length > 0) {
					setWarningStyle(emptyList);
					return true;
				} else {
					removeWarningStyle(emptRm);
					return false;
				}
			}

			function setWarningStyle(selectorArray) {
				$.each(selectorArray, function (index, selector) {
					$(selector).attr("style", "color:#a94442");
					$(selector).attr("style", "background-color:#ffffaa;border:solid 1px #a94442;padding:0 10px");
				});
			}
			function removeWarningStyle(selectorArray) {
				$.each(selectorArray, function (index, selector) {
					$(selector).removeAttr("style");
				});
			}

			function payChqSubmitClickEvent() {
				if (validationChqSubmit()) view.table.payChqList.insert(["-", view.input.payChqBankCode.val(), view.input.payChqBankName.val(), view.input.payChqBranch.val(), view.input.payChqNo.val(), view.input.payChqDate.val(), view.input.payChqAmount.val()], true)
			}

			function payBankTxnfButtonSubmitClickEvent() {
				if (validatePayBankTxnfButtonSubmit()) view.payBankTxnfTableList.insert(["-", view.input.payBankTxnfDropDownBankCode.val(), view.input.payBankTxnfDropDownBankName.val(), view.payBankTxnfDropDownBankBrnh.val(), view.payBankTxnfInputNo.val(), view.payBankTxnfInputDate.val(), view.payBankTxnfInputAmt.val()], true);
			}
			function validatePayBankTxnfButtonSubmit() {
				var isValid = true;
				if (view.input.payBankTxnfDropDownBankCode.val() == "") { view.input.payBankTxnfDropDownBankCode.error(true); isValid = false; } else { view.input.payBankTxnfDropDownBankCode.error(false); }
				if (view.input.payBankTxnfDropDownBankName.index() === 0) { view.input.payBankTxnfDropDownBankName.error(true); isValid = false; } else { view.input.payBankTxnfDropDownBankName.error(false); }
				if (view.payBankTxnfDropDownBankBrnh.index() === 0) { view.payBankTxnfDropDownBankBrnh.error(true); isValid = false; } else { view.payBankTxnfDropDownBankBrnh.error(false); }
				//if(view.payBankTxnfInputNo.val() == "") { view.payBankTxnfInputNo.error(true); isValid = false; } else {view.payBankTxnfInputNo.error(false); } //by NSD 17-02-2017
				if (view.payBankTxnfDropDownBankAcCd.index() === 0) { view.payBankTxnfDropDownBankAcCd.error(true); isValid = false; } else { view.payBankTxnfDropDownBankAcCd.error(false); }
				if (view.payBankTxnfInputDate.val() == "") { view.payBankTxnfInputDate.error(true); isValid = false; } else { view.payBankTxnfInputDate.error(false); }
				if (view.payBankTxnfDropDownBankAcct.index() === 0) { view.payBankTxnfDropDownBankAcct.error(true); isValid = false; } else { view.payBankTxnfDropDownBankAcct.error(false); }
				return isValid;
			}

		</script>
	</body>

	</html>