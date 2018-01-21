<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
	<link href="resources/bootstrap-table/src/bootstrap-table.css" rel="stylesheet" type="text/css" />
    <link href="resources/css/datepicker.min.css" rel="stylesheet" type="text/css"/>
	<link href="resources/custom.css" rel="stylesheet" type="text/css" />
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
		<div class="row">
			<div class="col-md-12">
				<ol id="breadcrumb" class="breadcrumb">
					<li><i>รับชำระอื่นๆ</i></li>
					<li>ค้นหาข้อมูลลูกค้า</li>
					<li>สรุปรายการรับชำระเงิน</li>
					<li class="active">เลือกวิธีการรับชำระ</li>
					<li>ผลการรับชำระ</li>
				</ol>
			</div>
		</div>
		<div id="message"></div>
		<div id="panelNavigation" class="row">
		<ul class="list-inline pull-right list-menu-set">
			<li><a href="pay-other_2.jsp"><span class="glyphicon glyphicon-th-list"></span> สรุปรายการรับชำระ</a></li>
			<li><a id="buttonCreatePaymentAndPrint" class="btn btn-link"><span class="glyphicon glyphicon-print"></span> บันทึกและพิมพ์</a></li>
			<li><a id="buttonCancelPayment" class="btn btn-link"><span class="glyphicon glyphicon-remove-circle"></span> ยกเลิกรายการ</a></li>
		</ul>
		</div>
		<div id="panelPaymentDetails" class="row">
			<div class="col-md-5">
				<label class="label-panal label-warning"><span class="glyphicon glyphicon-inbox"></span> รายการหัก</label>
				<div id="buttonSetTaxType" class="btn-group"></div>
				<span class="glyphicon glyphicon-plus-sign"></span> เพิ่มวิธีการรับชำระ
				<div class="panel panel-default panal-radius">
					<div class="panel-body" style="padding-right: 0; padding-left: 0;">
						<div class="col-md-12">
							<div role="tabpanel" class="tab-pane active" id="taxType1">
								<div class="form-horizontal">
									<div class="form-group">
										<label class="control-label col-sm-6">ประเภทภาษีหัก ณ ที่จ่าย :</label>
										<div class="col-sm-6">
											<label class="radio-inline"> <input type="radio" name="RadioOptions" id="Radio1" value="option1" checked><b> 69 ทวิ</b></label>
											<label class="radio-inline"> <input type="radio" name="RadioOptions" id="Radio2" value="option2"><b> 3 เตรส</b></label>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-sm-6">เลขที่เอกสาร :</label>
										<div class="col-sm-5">
											<input type="text" class="form-control" value="" disabled>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-sm-6">จำนวนเงิน :</label>
										<div class="col-sm-5">
											<input type="text" class="form-control text-right" value="" disabled>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-sm-6"></label>
										<div class="col-sm-5"><span class="glyphicon glyphicon-th-list"></span> เพิ่มรายการภาษีหัก ณ ที่จ่าย</div>
									</div>
								</div>
								<table id="tableWithholdingTaxList" class="table">
									<thead>
										<tr>
											<th>#</th>
											<th>เลขที่เอกสาร</th>
											<th>ประเภทหักภาษี ณ ที่จ่าย</th>
											<th>จำนวนเงิน</th>
											<th></th>
										</tr>
									</thead>
								</table>
							</div>
							<div role="tabpanel" class="tab-pane hide" id="taxType2">
								<div class="form-horizontal">
									<div class="form-group">
										<label class="control-label col-sm-2" style="padding: 0">รายการหัก :</label>
										<div class="col-sm-4">
											<select class="form-control" disabled>
												<option> กรุณาเลือก </option>
												<option>ค่าธรรมเนียม</option>
											</select>
										</div>
										<label class="control-label col-sm-3"
											style="padding-right: 0; padding-left: 0">จำนวนเงิน :</label>
										<div class="col-sm-3">
											<input type="text" class="form-control text-right" value="" disabled>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-sm-2"
											style="padding-right: 0; padding-left: 0">ศูนย์ต้นทุน :</label>
										<div class="col-sm-4">
											<select class="form-control" disabled>
												<option> กรุณาเลือก </option>
												<option>0000 จต.</option>
											</select>
										</div>
										<label class="control-label col-sm-3"
											style="padding-right: 0; padding-left: 0">ภาษีหัก ณ ที่จ่าย :</label>
										<div class="col-sm-3">
											<input type="text" class="form-control text-right" value="" disabled>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-sm-9"
											style="padding-right: 0; padding-left: 0">ภาษีซื้อ :</label>
										<div class="col-sm-3">
											<input type="text" class="form-control text-right" value="" disabled>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-sm-8"></label>
										<div class="col-sm-4"><span class="glyphicon glyphicon-th-list"></span> เพิ่มรายการหักอื่น ๆ</div>
									</div>
								</div>
								<table id="tableOtherTaxList" class="table">
									<thead>
										<tr>
											<th>#</th>
											<th>รายการ</th>
											<th>รหัสศูนย์ต้นทุน</th>
											<th>ภาษีหัก ณ ที่จ่าย</th>
											<th>ภาษีซื้อ</th>
											<th>จำนวนเงิน</th>
											<th></th>
										</tr>
									</thead>
								</table>
							</div>
							<div role="tabpanel" class="tab-pane hide" id="taxType3">
								<div class="form-horizontal">
									<div class="form-group">
										<label class="control-label col-sm-6">เลขที่เอกสาร :</label>
										<div class="col-sm-5">
											<input type="text" class="form-control" value="" disabled>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-sm-6">จำนวนเงิน :</label>
										<div class="col-sm-5">
											<input type="text" class="form-control text-right" value="" disabled>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-sm-6"></label>
										<div class="col-sm-5"><a id="buttonAddOffsetTaxList" class="btn btn-link"><span class="glyphicon glyphicon-th-list"></span> เพิ่มรายการภาษีหัก ณ ที่จ่าย</a></div>
									</div>
								</div>
								<table id="tableOffsetTaxList" class="table">
									<thead>
										<tr>
											<th>#</th>
											<th>เลขที่เอกสาร</th>
											<th>ประเภทหักภาษี ณ ที่จ่าย</th>
											<th>จำนวนเงิน</th>
											<th></th>
										</tr>
									</thead>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-7">
				<label class="label-panal label-warning"><span class="glyphicon glyphicon-th-list"></span> วิธีการรับชำระ</label>
				<div id="buttonSetPayType" class="btn-group"></div>
				<a id="buttonAddPayType" class="btn btn-link"><span class="glyphicon glyphicon-plus-sign"></span> เพิ่มวิธีการรับชำระ</a>
				<div class="panel panel-default panal-radius">
					<div class="panel-body" style="padding-right: 0; padding-left: 0;">
						<div class="col-md-12">
							<div role="tabpanel" class="tab-pane hide" id="payType1">
								<div class="form-horizontal">
									<div class="form-group">
										<label class="control-label col-sm-8">จำนวนเงิน :</label>
										<div class="col-sm-4"><input id="inputPayCashAmount" class="form-control text-right"></div>
										<div class="form-group" style="height:195px;"><label class="control-label col-sm-4"></label><div class="col-sm-8"></div></div>
									</div>
								</div>
							</div>
							<div role="tabpanel" class="tab-pane hide" id="payType2">
								<div class="form-horizontal">
									<div class="form-group">
										<label class="control-label col-sm-2">รหัสธนาคาร :</label>
										<div class="col-sm-4">
											<select class="form-control">
												<option> กรุณาเลือก </option>
												<option>001</option>
											</select>
										</div>
										<label class="control-label col-sm-2">เลขที่เช็ค :</label>
										<div class="col-sm-4">
											<input class="form-control" id="" value="">
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-sm-2">ชื่อธนาคาร :</label>
										<div class="col-sm-4">
											<select class="form-control">
												<option> กรุณาเลือก </option>
												<option>กสิกรไทย</option>
											</select>
										</div>
										<label class="control-label col-sm-2">วันที่หน้าเช็ค :</label>
										<div class="col-sm-4">
											<div class="input-group input-append date" data-provide="datepicker" data-date-format="dd/mm/yyyy">
												<input class="form-control" id="inputPayChequeDate" placeholder="dd/MM/yyyy" maxlength="10">
												<span class="input-group-addon add-on"><span class="glyphicon glyphicon-calendar"></span></span>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-sm-2">สาขา :</label>
										<div class="col-sm-4">
											<input class="form-control" id="" value="">
										</div>
										<label class="control-label col-sm-2">จำนวนเงิน :</label>
										<div class="col-sm-4">
											<input class="form-control text-right" id="" value="">
										</div>
									</div>
								</div>
								<a class="btn btn-warning pull-right" id="buttonAddPayChequeList"><span class="glyphicon glyphicon-plus-sign"></span> เพิ่มรายการอื่น ๆ</a>
								<table id="tablePayChequeList" class="table">
									<thead>
										<tr>
											<th>#</th>
											<th>รหัสธนาคาร</th>
											<th>ชื่อธนาคาร</th>
											<th>สาขา</th>
											<th>เลขที่เช็ค</th>
											<th>วันที่หน้าเช็ค</th>
											<th>จำนวนเงิน</th>
											<th></th>
										</tr>
									</thead>
								</table>
							</div>
							<div role="tabpanel" class="tab-pane hide" id="payType3">
								<div class="form-horizontal">
									<div class="form-group">
										<label class="control-label col-sm-3">ประเภทบัตรเครดิต :</label>
										<div class="col-sm-3">
											<select class="form-control">
												<option> กรุณาเลือก </option>
												<option>VISA</option>
											</select>
										</div>
										<label class="control-label col-sm-3">เลขที่บัตร :</label>
										<div class="col-sm-3">
											<input class="form-control" id="" value="">
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-sm-3">ธนาคารเจ้าของเครื่อง (EDC) :</label>
										<div class="col-sm-3">
											<select class="form-control">
												<option> กรุณาเลือก </option>
												<option>ธนาคารกรุงเทพ</option>
											</select>
										</div>
										<label class="control-label col-sm-3">จำนวนเงิน :</label>
										<div class="col-sm-3">
											<input class="form-control text-right" id="" value="">
										</div>
									</div>
								</div>
								<a class="btn btn-warning pull-right" id="buttonAddPayCreditCardList"><span class="glyphicon glyphicon-plus-sign"></span> เพิ่มรายการบัตรเครดิต</a>
								<table id="tablePayCreditCardList" class="table">
									<thead>
										<tr>
											<th>#</th>
											<th>ประเภทบัตรเครดิต</th>
											<th>เลขที่บัตร</th>
											<th>EDC</th>
											<th>จำนวนเงิน</th>
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
												<input class="form-control" id="inputPayMoneyOrderDate" placeholder="dd/MM/yyyy" maxlength="10">
												<span class="input-group-addon add-on"><span class="glyphicon glyphicon-calendar"></span></span>
											</div>
										</div>
										<label class="control-label col-sm-3">เลขที่ธนาณัติ :</label>
										<div class="col-sm-3">
											<input class="form-control" id="" value="">
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-sm-3">รหัสไปรษณีย์ต้นทาง :</label>
										<div class="col-sm-3">
											<input class="form-control" id="" value="">
										</div>
										<label class="control-label col-sm-3">จำนวนเงิน :</label>
										<div class="col-sm-3">
											<input class="form-control text-right" id="" value=" ">
										</div>
									</div>
								</div>
								<br />
								<a class="btn btn-warning pull-right" id="buttonAddPayMoneyOrderList"><span class="glyphicon glyphicon-plus-sign"></span> เพิ่มรายการธนาณัติ</a>
								<table id="tablePayMoneyOrderList" class="table">
									<thead>
										<tr>
											<th>#</th>
											<th>เลขที่ธนาณัติ</th>
											<th>วันที่ธนาณัติ</th>
											<th>รหัสไปรษณีต้นทาง</th>
											<th>จำนวนเงิน</th>
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
												<input class="form-control" id="inputPayBillExchangeDate" placeholder="dd/MM/yyyy" maxlength="10">
												<span class="input-group-addon add-on"><span class="glyphicon glyphicon-calendar"></span></span>
											</div>
										</div>
										<label class="control-label col-sm-3">เลขที่ตั๋วแลกเงิน :</label>
										<div class="col-sm-3">
											<input class="form-control" id="" value="">
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-sm-3">รหัสไปรษณีย์ต้นทาง :</label>
										<div class="col-sm-3">
											<input class="form-control" id="" value="">
										</div>
										<label class="control-label col-sm-3">จำนวนเงิน :</label>
										<div class="col-sm-3">
											<input class="form-control text-right" id="" value="">
										</div>
									</div>
								</div>
								<br />
								<a class="btn btn-warning pull-right" id="buttonAddPayBillExchangeList"><span class="glyphicon glyphicon-plus-sign"></span> เพิ่มรายการตั๋วแลกเงิน</a>
								<table id="tablePayBillExchangeList" class="table">
									<thead>
										<tr>
											<th>#</th>
											<th>เลขที่ตั๋วแลกเงิน</th>
											<th>วันที่ตั๋วแลกเงิน</th>
											<th>รหัสไปรษณีต้นทาง</th>
											<th>จำนวนเงิน</th>
											<th></th>
										</tr>
									</thead>
								</table>
							</div>
							<div role="tabpanel" class="tab-pane hide" id="payType6">
								<div class="form-horizontal">
									<div class="form-group">
										<label class="control-label col-sm-9">เลขที่คูปอง :</label>
										<div class="col-sm-3">
											<input class="form-control" value="">
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-sm-9">จำนวนเงิน :</label>
										<div class="col-sm-3">
											<input class="form-control text-right" value="">
										</div>
									</div>
								</div>
								<a class="btn btn-warning pull-right" id="buttonAddPayCouponList"><span class="glyphicon glyphicon-plus-sign"></span> เพิ่มรายการคูปอง</a>
								<table id="tablePayCouponList" class="table">
									<thead>
										<tr>
											<th>#</th>
											<th>เลขที่คูปอง</th>
											<th>รายละเอียด</th>
											<th>วันที่หมดอายุ</th>
											<th>จำนวนเงิน</th>
											<th></th>
										</tr>
									</thead>
								</table>
							</div>
							<div role="tabpanel" class="tab-pane hide" id="payType7">
								<div class="form-horizontal">
									<div class="form-group">
										<label class="control-label col-sm-4">รหัสธนาคาร :</label>
										<div class="col-sm-3">
											<input class="form-control" value="">
										</div>
										<label class="control-label col-sm-2">ชื่อธนาคาร :</label>
										<div class="col-sm-3">
											<select class="form-control">
												<option> กรุณาเลือก </option>
												<option>กรุงเทพ</option>
											</select>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-sm-4">สาขา :</label>
										<div class="col-sm-3">
											<select class="form-control">
												<option> กรุณาเลือก </option>
												<option>รัชโยธิน</option>
											</select>
										</div>
										<label class="control-label col-sm-2">เลขที่อ้างอิง :</label>
										<div class="col-sm-3">
											<input class="form-control" value="">
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-sm-4">รหัสบัญชีเงินฝากธนาคาร :</label>
										<div class="col-sm-3">
											<select class="form-control">
												<option> กรุณาเลือก </option>
												<option>11056589</option>
											</select>
										</div>
										<label class="control-label col-sm-2">วันที่โอน :</label>
										<div class="col-sm-3">
											<div class="input-group input-append date" data-provide="datepicker" data-date-format="dd/mm/yyyy">
												<input class="form-control" id="inputPayBankTransferDate" placeholder="dd/MM/yyyy" maxlength="10">
												<span class="input-group-addon add-on"><span class="glyphicon glyphicon-calendar"></span></span>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-sm-4">เลขที่บัญชีเงินฝากธนาคาร :</label>
										<div class="col-sm-3">
											<select class="form-control">
												<option> กรุณาเลือก </option>
												<option>404-0-55236-9</option>
											</select>
										</div>
										<label class="control-label col-sm-2">จำนวนเงิน :</label>
										<div class="col-sm-3">
											<input class="form-control text-right" value="">
										</div>
									</div>
								</div>
								<a class="btn btn-warning pull-right" id="buttonAddPayBankTransferList"><span class="glyphicon glyphicon-plus-sign"></span> เพิ่มรายการอื่น ๆ</a>
								<table id="tablePayBankTransferList" class="table">
									<thead>
										<tr>
											<th>#</th>
											<th>รหัสธนาคาร</th>
											<th>ชื่อธนาคาร</th>
											<th>สาขา</th>
											<th>เลขที่อ้างอิง</th>
											<th>วันที่โอน</th>
											<th>จำนวนเงิน</th>
											<th></th>
										</tr>
									</thead>
								</table>
							</div>
							<div role="tabpanel" class="tab-pane hide" id="payType8">
								<div class="form-horizontal">
									<div class="form-group">
										<label class="control-label col-sm-3">ช่องทางการชำระ :</label>
										<div class="col-sm-3">
											<select class="form-control">
												<option> กรุณาเลือก </option>
												<option>2C2P</option>
											</select>
										</div>
										<label class="control-label col-sm-3">เลขที่อ้างอิง :</label>
										<div class="col-sm-3">
											<input class="form-control" value="">
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-sm-3">วันที่อ้างอิง :</label>
										<div class="col-sm-3">
											<div class="input-group input-append date" data-provide="datepicker" data-date-format="dd/mm/yyyy">
												<input class="form-control" id="inputPayOtherDate" placeholder="dd/MM/yyyy" maxlength="10">
												<span class="input-group-addon add-on"><span class="glyphicon glyphicon-calendar"></span></span>
											</div>
										</div>
										<label class="control-label col-sm-3">จำนวนเงิน :</label>
										<div class="col-sm-3">
											<input class="form-control text-right" value="">
										</div>
									</div>
								</div>
								<a class="btn btn-warning pull-right" id="buttonAddPayOtherList"><span class="glyphicon glyphicon-plus-sign"></span> เพิ่มรายการอื่น ๆ</a>
								<table id="tablePayOtherList" class="table">
									<thead>
										<tr>
											<th>#</th>
											<th>เลขที่อ้างอิง</th>
											<th>ช่องทางการรับชำระ</th>
											<th>วันที่รับชำระ</th>
											<th>จำนวนเงิน</th>
											<th></th>
										</tr>
									</thead>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div id="panelMethodSummary" class="row">
                <div class="col-md-5">
                    <div class="panel panel-default">
                        <div class="panel-heading">สรุปรายการหัก</div>
                        <div class="panel-body">
                            <table id="deductionList" data-row-style="rowStyle" data-toggle="table" data-classes="table table-hover table-striped">
                                <thead>
                                    <tr>
                                        <th data-running-no="true">#</th>
                                        <th>รายการหัก</th>
                                        <th data-align="right" data-number-format="true">จำนวนเงิน</th>
                                        <th></th>
                                    </tr>
                                </thead>
                            </table>
                        </div>
                    </div>
                </div>
                <div class='col-md-7'>
                    <div class="panel panel-default">
                        <div class="panel-heading">สรุปวิธีการรับชำระ</div>
                        <div class="panel-body">
                            <table id="tableSummaryPayType" data-row-style="rowStyle" data-toggle="table" data-classes="table table-hover table-striped">
								<thead>
									<tr>
										<th data-align="center" data-valign="middle" data-formatter="runningFormatter">#</th>
										<th data-field="payType" data-valign="middle">สรุปวิธีการรับชำระ</th>
										<th data-field="payAmount" data-valign="middle" data-align="right" data-formatter="numberFormatter">จำนวนเงิน</th>
										<th data-align="center" data-width="40px" data-formatter="removeButtonFormatter"></th>
									</tr>
								</thead>
							</table>
                        </div>
                    </div>
                </div>
            </div>
		<div id="panelPaymentSummary" class="row">
			<div class="col-md-12">
				<div class="panel panel-default">
					<div class="panel-heading"><span class="glyphicon glyphicon-shopping-cart"></span> สรุปยอดเงินที่ต้องชำระ</div>
					<div class="panel-body">
						<div class="form-horizontal">
							<!-- <input id="inputAmount" class="form-control text-right" disabled> -->
							
							<div class="form-group">
								<label class="control-label col-sm-10">ยอดเงินที่ต้องชำระก่อนภาษีมูลค่าเพิ่ม :</label>
								<div class="col-sm-2"><input class="form-control text-right" id="inputCharge" disabled></div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-10">ภาษีมูลค่าเพิ่ม :</label>
								<div class="col-sm-2"><input class="form-control text-right" id="inputVat" disabled></div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-10">ยอดเงินที่ต้องชำระรวมภาษีมูลค่าเพิ่ม :</label>
								<div class="col-sm-2"><input class="form-control text-right" id="inputTotalCharge" disabled></div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-10">ภาษีหัก ณ ที่จ่าย :</label>
								<div class="col-sm-2"><input class="form-control text-right" id="inputDeduction" disabled></div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-10">
									<div class="checkbox">
										<label><input type="checkbox" id="check_dis_not" disabled> <b>ลูกค้ารับภาระภาษี&nbsp;&nbsp;&nbsp;<span class="glyphicon glyphicon-lock"></span> ส่วนลด :</b></label>
									</div>
								</label>
								<div class="col-sm-2"><input id="inputAdditionalDiscount" class="form-control text-right" disabled></div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-10">ยอดเงินที่ต้องชำระ :</label>
								<div class="col-sm-2"><input class="form-control text-right" id="inputBalanceDue" disabled></div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-10">ยอดเงินรับมา :</label>
								<div class="col-sm-2"><input class="form-control text-right" id="inputReceived" disabled></div>
							</div>
							<div class="form-group">
								<label class="control-label col-sm-10">เงินทอน :</label>
								<div class="col-sm-2"><input class="form-control text-right" id="inputChange" disabled></div>
							</div>
							<div class="form-group">
                                 <div class="control-label col-sm-10" >
                                     <label class="strong"><input type="radio" name="specialOptions" value="1"> รายได้อื่นที่ไม่มีภาษี</label>&nbsp;&nbsp;&nbsp;&nbsp;
                                     <label class="strong"><input type="radio" name="specialOptions" value="2"> รายได้อื่นมีภาษี</label>
                                 </div>
                                 <div class="col-sm-2">
                                     <input id="inputAdvanced" class="form-control text-right" disabled>
                                 </div>
                             </div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="modal fade" role="dialog" id="modal_authen">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">Authentication</h4>
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="col-md-12">
								<div class="col-md-2"></div>
								<div class="col-md-10">
									<div class="form-horizontal">
										<div class="form-group">
											<label class="col-sm-4 control-label">ผู้อนุมัติ :</label>
											<div class="col-sm-8">
												<input type="text" class="form-control">
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-4 control-label">รหัสผ่าน :</label>
											<div class="col-sm-8">
												<input type="password" class="form-control">
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default">
							<span class="glyphicon glyphicon-ok-circle"></span> ตกลง
						</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">
							<span class="glyphicon glyphicon-remove-circle"></span> ยกเลิก
						</button>
					</div>
				</div>
			</div>
		</div>
		<div class="modal fade" role="dialog" id="modal_advance">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title">เลือกรายการลูกค้าที่จะรับชำระล่วงหน้า</h4>
					</div>
					<div class="modal-body">
						<div class="row">
							<div class="col-md-12">
								<label>รายการลูกค้า</label>
								<table id="table" data-row-style="rowStyle" data-toggle="table"
									data-classes="table table-hover table-striped">
									<thead>
										<tr>
											<th>#</th>
											<th>เลขที่ลูกค้า</th>
											<th>ชื่อลูกค้า</th>
											<th>ประเภทบริการ</th>
											<th>Billing Group</th>
											<th>ยอดชำระ</th>
										</tr>
									</thead>
								</table>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default">
							<span class="glyphicon glyphicon-ok-circle"></span> เลือกรายการ
						</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">
							<span class="glyphicon glyphicon-remove-circle"></span> ยกเลิก
						</button>
					</div>
				</div>
			</div>
		</div>
	<div id="panelSummaryPaymentList" style="display: none">
        <ul class="list-inline pull-right list-menu-set">
            <li><a href="pay-other_1.jsp?new"><span class="glyphicon glyphicon glyphicon-arrow-left"></span> กลับไปหน้าการรับชำระ</a></li>
        </ul>
        <div class="row">
            <div class="col-md-12">
                <div class="panel panel-success">
                    <div class="panel-heading">รายการรับชำระเงิน</div>
                    <div class="panel-body">
                        <table id="tableSummaryReceipts" data-row-style="rowStyle" data-toggle="table" data-classes="table table-hover table-striped">
                            <thead>
                                <tr>
                                    <th data-align="center" data-formatter="runningFormatter">#</th>
                                    <th data-field="custNo">เลขที่ลูกค้า</th>
                                    <th data-field="custName">ชื่อลูกค้า</th>
                                    <th data-field="serviceType">ประเภทบริการ</th>
                                    <th data-field="serviceName">ชื่อบริการ</th>
                                    <th data-field="serviceMoreData" data-align="right">จำนวนรายการ</th>
									<th data-field="serviceAmount" data-align="right" data-formatter="numberFormatter">จำนวนเงินต่อหน่วย</th>
                                    <th data-field="serviceVat" data-align="right" data-formatter="numberFormatter">ภาษีมูลค่าเพิ่ม</th>
                                    <th data-field="serviceDiscount" data-align="right" data-formatter="numberFormatter">ส่วนลด</th>
                                    <th data-field="serviceDeduction" data-align="right" data-formatter="numberFormatter">ภาษีหัก ณ ที่จ่าย</th>
                                    <th data-field="serviceTotalCharge" data-align="right" data-formatter="numberFormatter">ยอดเงินรวม</th>
                                    <th data-field="status">สถานะ</th>
                                </tr>
                            </thead>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
	</section>
</body>
</html>
<script>
var view = (function($){
	var self = this, defaultErrorMessage = "An error occurred but there is no message response.";
	self.session = function(key, val) { if (!val) { var val = window.sessionStorage.getItem(key); return val && (val.indexOf("{") > -1 || val.indexOf("[") > -1) ? JSON.parse(val) : val } window.sessionStorage.setItem(key, ($.type(val) != "object" && $.type(val) != "array" ? val : JSON.stringify(val)))	};
	self.utils = {
		guid: function(){ function r() { return Math.floor((Math.random() + 1) * 0x10000).toString(16).substring(1) } return r() + r() +"-"+ r() +"-"+ r() +"-"+ r() +"-"+ r() + r() + r() },
		numberFormat: function(num, dec) { return $.type(num) !== "number" ? "0.00" : num.toFixed(dec || 2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,'); },
		dateFormat: function() { var obj = arguments[0]; if ($.type(obj) == "string" && /[0-9]{2}.[0-9]{2}.[0-9]{4}/g) { return obj } else if ($.isNumeric(obj)) { var dt = new Date(obj), dd = dt.getDate().toString(), mm = (dt.getMonth() + 1).toString(), yyyy = dt.getFullYear().toString(); return (dd[1]?"":"0") + dd +"/"+ (mm[1]?"":"0") + mm +"/"+ yyyy } else if ($.type == "date") { return "" } return "" },
		dateTimeFormat: function() { var obj = arguments[0], dt = $.type(obj) != "date" ? ($.isNumeric(obj) ? new Date(obj) : new Date()) : new Date(), dd = dt.getDate().toString(), MM = (dt.getMonth() + 1).toString(), yyyy = dt.getFullYear().toString(), hh = dt.getHours().toString(), mm = dt.getMinutes().toString(), ss = dt.getSeconds().toString(); return (dd[1]?"":"0") + dd +"/"+ (MM[1]?"":"0") + MM +"/"+ yyyy +" "+ (hh[1]?"":"0") + hh +":"+ (mm[1]?"":"0") + mm +":"+ (ss[1]?"":"0") + ss },
		queryString: function() { var pos = location.href.indexOf("?"), obj = {}; if (pos < 0) return obj; var params = location.href.slice(pos + 1).split("&"); for (var i = 0, m = params.length; i < m; i++) { pos = params[i].indexOf("="); if (pos < 0) { obj[params[i]] = true; continue; } obj[params[i].substring(0, pos)] = params[i].substring(pos + 1) } return obj },
		window: function(windowName, url, side) { window.windowOpened = (window.windowOpened || {}); var screenProp = ""; if (side == "left") { screenProp = "left=0,top=0,width="+ (screen.width/2) +",height="+ (screen.height-100) } else if (side == "right") { screenProp = "left="+ (screen.width/2-40) +",top=0,width="+ (screen.width/2+40) +",height="+ (screen.height-100) } window.windowOpened[windowName] = window.open(url, windowName, "menubar=no,scrollbars=no,resizable=0,"+ screenProp, false); return window.windowOpened }
	};
	//// AUTOMATIC REGISTER FORMATTER FUNCTION ////
	window.get = function(url, params, success, msgDialog, preCheck){ $.ajax({ "url": url, "type": "GET", "data": params, "error": msgDialog.valid, "success": function(res){ (preCheck || function(o){ console.log(o) })(res); if (msgDialog && !msgDialog.valid(res)) { return; } success(res) } }) }
	window.post = function(url, params, success, msgDialog, preCheck){ $.ajax({ "url": url, "type": "POST", "data": params, "error": msgDialog.valid, "success": function(res){ (preCheck || function(o){ console.log(o) })(res); if (msgDialog && !msgDialog.valid(res)) { return; } success(res) } }) }
	window.stripToNumber = function(str) { return $.type(str) === "string" && $.isNumeric(str = str.replace(/[,]/g, "")) ? parseFloat(str, 10) : 0 }
	window.runningFormatter = function(val, row, ind) { return ind + 1 }
	window.numberFormatter = function(val, row, ind) { return !$.isNumeric(stripToNumber(val)) ? "0.00" : parseFloat(val, 10).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,') }
	window.stringInputFormatter = function(val, row, ind) { return '<input value="'+ $.trim(val) +'" maxLength="40" class="form-control">' }
	window.numberInputFormatter = function(val, row, ind) { return '<input value="'+ self.utils.numberFormat(parseFloat(val || "0.00", 10)) +'" maxLength="10" style="width:100px" class="form-control text-right" onkeypress="return $.isNumeric(String.fromCharCode(event.which)) || (event.which == 46 && this.value.indexOf(\'.\') == -1)" onfocus="(function(o){ o.value = o.value.replace(/[,]/g, \'\') })(this)" onblur="(function(o){ o.value = parseFloat(o.value, 10).toFixed(2).replace(/(\\d)(?=(\\d{3})+\\.)/g, \'$1,\'); })(this)">' }
	window.modifyButtonFormatter = function(val, row, ind) { return '<a class="btn btn-link modList" onclick=\'(function(elem, row, ind){ var table = $("#"+ $(elem).closest("table")[0].id); window[table[0].id +"ModifyEvent"](table, row, ind) })(this, '+ JSON.stringify(row) +', '+ ind +')\'><span class="glyphicon glyphicon-pencil"></span></a>' }
	window.removeButtonFormatter = function(val, row, ind) { return '<a class="btn btn-link delList" onclick=\'(function(elem, row, ind){ $("#"+ $(elem).closest("table")[0].id).bootstrapTable("remove", { field: "index", values: [ind] }) })(this, '+ JSON.stringify(row) +', '+ ind +')\'><span class="glyphicon glyphicon-trash"></span></a>' }
	function Breadcrumb(el) {
		var obj = this, list, index = 0;
		obj.el = el;
		obj.elem = $(el);
		obj.index = function(){ if(arguments.length == 1) { list.removeClass("active").eq(index = arguments[0]).addClass("active"); return obj } return index }
		list = obj.elem.find("li").each(function(i,o){ $(o).data("index", i) });
		index = list.filter(".active").data("index");
	}
	function Button(el, url, inp, validate) { var obj = this, inputs = inp, val = (validate || function(){ return true }), badge, done = function(res) { console.log(res) }; obj.el = el; obj.elem = $(el);
		obj.inputs = function(object) { inputs = object; return this };
		obj.click = function(func) { this.elem.click(func) };
		obj.hide = function() { this.elem.addClass("hide"); return this }; obj.show = function() { this.elem.removeClass("hide"); return this };
		obj.hideLoad = function(){ obj.elem.button("reset"); return this }; obj.showLoad = function(){ obj.elem.button("loading"); return this };
		obj.disable = function(flag) { if (flag == null || flag) this.elem.addClass("disabled"); else this.elem.removeClass("disabled"); return this };
		obj.enable = function() { this.elem.removeClass("disabled"); return this };
		obj.validate = function(func) { val = func; return obj }
		obj.done = function(func) { done = func };
		obj.badge = function(val) { if (badge) badge.text(val) };
		function eventHandler (e) {
			if (!val(inputs)) return;
			if (!url) return; obj.elem.button("loading");
			var params = {}; $.each(inputs, function(k,v){
				if ($.type(v) === "object" && v.val) params[k] = v.val();
				else if ($.type(v) === "array")      params[k] = v.join("|");
				else if ($.type(v) === "function")   params[k] = v();
				else                                 params[k] = v;
			}); $.get(url, params, function(res){ obj.elem.button("reset"); done(res) });
		}
		if ((badge = this.elem.next()).length == 0) badge = null;
		obj.elem.click(eventHandler).click(window[el.substring(1) +"ClickEvent"]);
	}
	function ButtonGroup(el, array) { var obj = this, data = (array || ["Please Select"]), index = 0; obj.el = el; obj.elem = $(el);
		obj.val = function() { return obj.selected.text() }
		obj.index = function() { if (arguments.length == 1) { obj.list[arguments[0]].click() } return index }
		obj.elem.append('<a class="btn btn-default dropdown-toggle" data-toggle="dropdown"><span class="selection">'+ data[0] +'</span> <span class="caret"></span></a>').append('<ul class="dropdown-menu">'+ $.map(data, function(v,i){ return '<li><a href="#">'+ v +'</a></li>' }).join("") +'</ul>');
		obj.selected = obj.elem.find(".selection");
		obj.list = obj.elem.find(".dropdown-menu a").each(function(i){ var a = this; $(a).click(function(){ index = i; obj.selected.text(a.innerHTML) }) });
	}
	function DropDown(el, url) {
		var obj, data = [{ key: "", value: "Please Select" }];
		this.el = el;
		this.elem = obj = $(el);
		this.data = function(array) { if ($.type(array) == "array") { setup(data = array); return this } return data; }
		this.init = function(url) { if (url) $.get(url, function(res) { setup(res.data) }); else setup(data); };
		this.index = function() { return obj.find("option:selected").data("index") }
		this.key = function() { return obj.find("option:selected").data("key") }
		this.val = function() { return obj.val(); }
		function setup(array) {
			obj.find("*").remove();
			$.each(array,function(i,o){
				obj.append('<option data-index="'+ i +'" data-key="'+ o.key +'" value="'+ o.value +'">'+ o.value +'</option>')
			});
		}
		this.init(url);
	}
	function Input(el, maxLen, propPos) { var obj = this; obj.el = el; obj.elem = $(el);
		obj.error = function(flag) { if (arguments.length == 0 || flag) obj.elem.parent().addClass("has-error"); else obj.elem.parent().removeClass("has-error"); return this }
		obj.clear = function() { obj.val(""); return this }
		obj.empty = function() { return $.trim(obj.val()) === "" }
		obj.click = function(func) { obj.elem.click(func); return this }
		obj.readOnly = function(flag) { obj.elem.attr("readonly", (flag == null ? true : flag)); return this }
		obj.disable = function(flag) { obj.elem.attr("disabled", (flag == null ? true : flag)); return this }
		obj.enable = function() { obj.disable(false); return this }
		obj.val = function() { if (arguments.length == 1) { this.elem.val(arguments[0]) } return this.elem.val() }
		obj.get = function(propName) { if ($.type(propPos[propName]) !== "array" || propPos[propName].length !== 2) return ""; return obj.val().substring(propPos[propName][0], propPos[propName][1]) }
		if ($.isNumeric(maxLen)) { obj.elem.attr("maxLength", maxLen) }
	}
	function NumberInput(el, dec) { var obj = this, decimal = (dec || 2); obj.el = el; obj.elem = $(el).addClass("text-right");
		obj.val = function() { if (arguments.length == 0) return parseFloat(strip(this.elem.val() || "0"), 10); this.elem.val(format(arguments[0])); }
		obj.decimal = function(dec) { decimal = dec }; obj.format = format;
		obj.disable = function(){ obj.elem.attr("disabled", (arguments.length != 1 ? true : arguments[0])); return obj }; obj.enable = function(){ obj.disable(false); return obj }
		obj.elem.keypress(function(e) { var key = (e.which || e.keyCode || e.charCode || 0); var ch = String.fromCharCode(key); return "0123456789.".indexOf(ch) > -1 });
		obj.elem.focus(function(){ this.value = strip(this.value); this.select() });
		obj.elem.blur(function(){ this.value = format(this.value) });
		function format(val) { return ($.isNumeric(val) ? parseFloat(val, 10) : obj.val()).toFixed(decimal).replace(/(\d)(?=(\d{3})+\.)/g, '$1,'); };
		function strip(str) { return (str || "").replace(/[,]/g, "") }
		if (obj.elem.val() == "") obj.elem.val("0.00");
	}
	function Message(el) { var obj = this, timeCnt = 0, loadFunc; obj.el = el; obj.elem = $(el);
		obj.hide = function() { obj.elem.addClass("hide"); return obj };
		obj.show = function(flag) { if (flag == null || flag) obj.elem.removeClass("hide"); else obj.hide(); return obj };
		obj.clear = function() { obj.elem.find("*").remove(); obj.hide(); return obj };
		obj.message = function(arr, cls) { $.each(arr, function(i,o) { var m = o; if ($.type(o) === "object") { m = (o.desc || o.messageDesc) +" [code="+ (o.code || o.messageCode) +"]" }; obj.elem.append('<div class="'+ cls +'">'+ m +'<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button></div>') }); return obj };
		obj.error = function(arr) { return obj.message(arr, "alert alert-danger") };
		obj.warn = function(arr) { return obj.message(arr, "alert alert-warning") };
		obj.success = function(arr) { return obj.message(arr, "alert alert-success") };
		obj.valid = function(jqXHR, textStatus, errorThrow){ var res = jqXHR; if (jqXHR && jqXHR.responseJSON) res = jqXHR.responseJSON; if (res && (res.statusCode == '0' || $.type(res._embedded) === 'object')) return true; obj.warn(res && $.type(res.warningList) === 'array' ? res.warningList : []).error(res && $.type(res.errorList) === 'array' ? res.errorList : ["An error occurred on the server side but there is no error message returned."]).show(); return false }
		obj.hideLoad = function(){ obj.stopLoad().clear(); return this }; obj.stopLoad = function(){ clearInterval(loadFunc); return this }
		obj.showLoad = function(msg){ obj.elem.append('<div id="'+ obj.el +'-loading" class="alert alert-warning">'+ bind(msg, 0) +'</div>'); timeCnt = 0; var elem = document.getElementById(obj.el +"-loading"); loadFunc = setInterval(function(){ elem.innerHTML = bind(msg, ++timeCnt) }, 1000); obj.show(); return this }
		function bind(msg, timeCnt) { return msg.replace(/\{timeCnt\}/g, timeCnt) }
	}
	function FadePanel() { var obj = this, index = 0, dura = 200;
		obj.els = $.map(arguments, function(o,i){ return $.type(o) !== "string" ? null : o });
		obj.elems = $(obj.els.join(","));
		obj.hide = function() { obj.elems.addClass("hide"); return this }; obj.show = function() { obj.elems.removeClass("hide"); return this }
		obj.prev = function() { obj.index(index - 1); return this }; obj.next = function() { obj.index(index + 1); return this }
		obj.index = function(ind) { if (ind === index) return this;console.log(obj.elems); var target = obj.elems.length > ind && ind > -1 ? ind : (ind >= obj.elems.length ? 0 : obj.elems.length - 1); obj.elems.eq(index).fadeOut(dura, function(){ obj.elems.eq(index = target).removeClass("hide").css("display", "") }); return this }
		obj.elems.eq(index).hide().removeClass("hide").show(dura);
		arguments[0].list.each(function(i,o){ $(o).click(function(){ obj.index(i) }) })
	}
	function Panel(el) { var obj = this, dura = 500; obj.el = el; obj.elem = $(el);
		obj.hide = function(ms) { obj.elem.hide(ms || dura); return this }; obj.show = function(ms) { obj.elem.show(ms || dura); return this }
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
				+ $.map(array, function(v,i){ var field = headers[i].field, value = v;
					if (headers[i].runningNo) value = (obj.size() + 1);
					else if (headers[i].numberFormat) { value = !$.isNumeric(v) ? "0.00" : parseFloat(v, 10).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,'); }
					else if (headers[i].checkbox) value = replace(checkboxHtml, null, field, i, v);
					else if (headers[i].radio) value = replace(radioHtml, null, field, i, v);
					else if (headers[i].input) value = replace(inputHtml, 'style="width:100%;margin:-4px 0;text-align:'+ (headers[i].align || "left") +'"', field, i, v);
					return '<td><div style="'+ (headers[i].align ? "text-align:"+ headers[i].align : "") +'">'+ value +'</div></td>' }).join("")
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
		obj.elem.find("thead th").each(function(i,o){ var elem = $(o); headers.push({ "field": elem.data("field"), "align": $.trim(elem.data("align")), "runningNo": elem.data("runningNo") === true, "numberFormat": elem.data("numberFormat") === true, "checkbox": elem.data("checkbox") === true, "radio": elem.data("radio") === true, "input": elem.data("input") === true }) });
		function replace(str, style, field, index, value){ var s = str; if (style) s = s.replace("\{style\}", style); return s.replace("\{field\}", $.trim(field)).replace("\{index\}", index).replace("\{value\}", value) }
		function extract(prop, col) { if (prop.checkbox) { var elem = col.children[0].children[0]; return !elem.checked ? "" : elem.value } else if (prop.radio) { var elem = col.children[0].children[0]; return !elem.checked ? "" : elem.value } else if (prop.input) { var elem = col.children[0].children[0]; return elem.value } return col.children[0].innerText }
		if(obj.body.length < 1) { obj.elem.append("<tbody></tbody>"); obj.body = obj.elem.find("tbody") }
	}
	function BootstrapTable(el) { var obj = this, checkEvt = function(e){ console.log(e) }, uncheckEvt = checkEvt; obj.el = el; obj.elem = $(el);
		obj.clear = function() { obj.elem.bootstrapTable("removeAll"); return obj }; obj.remove = function(index){ obj.elem.bootstrapTable("remove", { field: "index", values: [index] }); return this }
		obj.showLoad = function() { this.elem.bootstrapTable("showLoading"); return this }; obj.hideLoad = function() { this.elem.bootstrapTable("hideLoading"); return this };
		obj.find = function(field, value) { var data = obj.elem.bootstrapTable("getData"); return $.map(data, function(o,i){ return o[field] === value ? o : null }) }
		obj.insert = function(row) { var data = obj.elem.bootstrapTable("getData"); obj.elem.bootstrapTable("insertRow", { "index": data.length, "row": row }); return this }
		obj.data = function() { if (arguments.length == 1) { this.elem.bootstrapTable("load", arguments[0]); } return this.elem.bootstrapTable("getData") };
		obj.selected = function() { return this.elem.bootstrapTable("getSelections") };
		obj.sum = function(isAll, colName) { var sum = 0; $.each(this.elem.bootstrapTable(isAll ? "getData" : "getSelections"), function(i,o){ sum += (o[colName] || 0) }); return sum };
		obj.sumInput = function(index) { var sum = 0; obj.elem.find("tbody tr").each(function(i,o){ var val = o.children[index].children[0].value.replace(/[,]/g, ""); sum += (!$.isNumeric(val) ? 0 : parseFloat(val, 10)) }); return sum }
		obj.expand = function() { obj.elem.find(".detail-icon i.icon-plus").click(); return this };
		obj.collapse = function() { obj.elem.find(".detail-icon i.icon-minus").click(); return this };
		obj.checkboxEvent = function(func) { this.elem.on("check.bs.table uncheck.bs.table check-all.bs.table uncheck-all.bs.table", func); return this };
		obj.checkEvent = function(func) { checkEvt = func; return this };
		obj.uncheckEvent = function(func) { uncheckEvt = func; return this };
		obj.checkboxEvent(function(e) { if (e.type === "check") checkEvt(e); if (e.type === "uncheck") uncheckEvt(e) });
	}
	(function(){ $(window["setup"]) })();
	// TO SUPPORT ECLIPSE OUTLINE.
	self.breadcrumb = new Breadcrumb("#breadcrumb");
	self.message = new Message("#message");
	self.buttonCreatePaymentAndPrint = new Button("#buttonCreatePaymentAndPrint");
	self.buttonCancelPayment = new Button("#buttonCancelPayment");
	self.buttonAddTaxType = new Button("#buttonAddTaxType");
	self.buttonAddPayType = new Button("#buttonAddPayType");
	self.buttonAddWithholdingTaxList = new Button("#buttonAddWithholdingTaxList");
	self.buttonAddOtherTaxList = new Button("#buttonAddOtherTaxList");
	self.buttonAddOffsetTaxList = new Button("#buttonAddOffsetTaxList");
	self.buttonAddPayChequeList = new Button("#buttonAddPayChequeList");
	self.buttonAddPayCreditCardList = new Button("#buttonAddPayCreditCardList");
	self.buttonAddPayMoneyOrderList = new Button("#buttonAddPayMoneyOrderList");
	self.buttonAddPayBillExchangeList = new Button("#buttonAddPayBillExchangeList");
	self.buttonAddPayCouponList = new Button("#buttonAddPayCouponList");
	self.buttonAddPayBankTransferList = new Button("#buttonAddPayBankTransferList");
	self.buttonAddPayOtherList = new Button("#buttonAddPayOtherList");
	self.buttonSetTaxType = new ButtonGroup("#buttonSetTaxType", ["ภาษีหัก ณ ที่จ่าย", "อื่น ๆ"]);
	self.buttonSetPayType = new ButtonGroup("#buttonSetPayType", ["เงินสด", "เช็ค", "บัตรเครดิต", "ธนาณัติ", "ตั๋วแลกเงิน", "คูปอง", "เงินโอนในประเทศ", "offset", "อื่น ๆ"]);
	self.inputPayCashAmount = new NumberInput("#inputPayCashAmount");
	self.inputAmount = new NumberInput("#inputAmount");
	self.inputAdditionalDiscount = new NumberInput("#inputAdditionalDiscount");
	self.inputCharge = new NumberInput("#inputCharge");
	self.inputVat = new NumberInput("#inputVat");
	self.inputTotalCharge = new NumberInput("#inputTotalCharge");
	self.inputBalanceDue = new NumberInput("#inputBalanceDue");
	self.inputDeduction = new NumberInput("#inputDeduction");
	self.inputReceived = new NumberInput("#inputReceived");
	self.inputChange = new NumberInput("#inputChange");
	self.panelNavigation = new Panel("#panelNavigation");
	self.panelPaymentDetails = new Panel("#panelPaymentDetails");
	self.panelPaymentSummary = new Panel("#panelPaymentSummary");
	self.panelMethodSummary = new Panel("#panelMethodSummary");
	self.panelSummaryPaymentList = new Panel("#panelSummaryPaymentList");
	self.panelTaxType = new FadePanel(self.buttonSetTaxType, "#taxType1", "#taxType2", "#taxType3");
	self.panelPayType = new FadePanel(self.buttonSetPayType, "#payType1", "#payType2", "#payType3", "#payType4", "#payType5", "#payType6", "#payType7", "#payType8");
	self.tableSummaryTaxType = new BootstrapTable("#tableSummaryTaxType");
	self.tableSummaryPayType = new BootstrapTable("#tableSummaryPayType");
	self.tableSummaryReceipts = new BootstrapTable("#tableSummaryReceipts");
	self.tableWithholdingTaxList = new Table("#tableWithholdingTaxList");
	self.tableOtherTaxList = new Table("#tableOtherTaxList");
	self.tableOffsetTaxList = new Table("#tableOffsetTaxList");
	self.tablePayChequeList = new Table("#tablePayChequeList");
	self.tablePayCreditCardList = new Table("#tablePayCreditCardList");
	self.tablePayMoneyOrderList = new Table("#tablePayMoneyOrderList");
	self.tablePayBillExchangeList = new Table("#tablePayBillExchangeList");
	self.tablePayCouponList = new Table("#tablePayCouponList");
	self.tablePayBankTransferList = new Table("#tablePayBankTransferList");
	self.tablePayOtherList = new Table("#tablePayOtherList");
	return this;
})(jQuery);

function setup() {
	var billingList = view.session("billingList"), afterSaleDiscount = 0, amount = 0, discount = 0, charge = 0, vat = 0, totalCharge = 0, deduction = 0;
	$.each(billingList, function(i,o){
		afterSaleDiscount += o.afterSaleDiscount
		$.each(o.serviceList, function(j,p){
			amount += (p.serviceAmount * p.serviceMoreData);
			discount += p.serviceDiscount;
			vat += p.serviceVat;
			deduction += p.serviceDeduction;
		});
	});
	view.inputAmount.val(amount);
	view.inputCharge.val(charge = amount - discount);
	view.inputVat.val(vat);
	view.inputTotalCharge.val(totalCharge = charge + vat);
	view.inputDeduction.val(deduction);
	view.inputAdditionalDiscount.val(afterSaleDiscount);
	view.inputBalanceDue.val(totalCharge - deduction - afterSaleDiscount);
}
function buttonCreatePaymentAndPrintClickEvent() {
	if (view.inputBalanceDue.val() > view.inputReceived.val()) {
		view.message.clear().error(["ระบบไม่อนุญาตให้จ่าย เนื่องจากจำนวนเงินไม่ถูกต้อง"]).show();
		return false;
	}
	var params = {
		 "amount": view.inputAmount.val()
		,"payAmount": view.inputTotalCharge.val()
		,"discount": view.inputAdditionalDiscount.val()
		,"vatAmount": view.inputVat.val()
		,"totalPaid": view.inputBalanceDue.val()
		,"wtAmount": view.inputDeduction.val()
		,"receiveAmount": view.inputBalanceDue.val()
		,"remainAmount": view.inputChange.val()
		,"paymentCase": $.map(view.tableSummaryPayType.data(), function(o,i){ return o.payType }).join(" + ")
		,"remark": ""
	};
	var deductIndex = 0;
	// <!-- Deduction. -->
	$.map((window["wthList"] || []), function(o, i) {
		params["deducts["+ deductIndex +"].type"] = "69BIS"; // 3TREDECIM, 69BIS
		params["deducts["+ deductIndex +"].amount"] = o[4].replace(/[,]/g, "");
		deductIndex++;
	});
	$.map((window["othList"] || []), function(o, i) {
		params["deducts["+ deductIndex +"].type"] = "PENALTY"; // PENALTY, FEE, RETENTION, //ค่าปรับ ค่าธรรมเนียม ค่าประกันผลงาน ค่าตอบแทนการรับชำระ
		params["deducts["+ deductIndex +"].amount"] = o[5].replace(/[,]/g, "");
		deductIndex++;
	});
	// <!-- Pay Method. -->
	var methodIndex = 0, cashRow = view.tableSummaryPayType.find("method", 0);
	$.map((window["payChqListData"] || []), function(o, i) { // For: Cheque
		params["methods["+ methodIndex +"].type"] = "CHEQUE";
		params["methods["+ methodIndex +"].code"] = "2";
		params["methods["+ methodIndex +"].name"] = "เช็ค";
		params["methods["+ methodIndex +"].chequeNo"] = o[4];
		params["methods["+ methodIndex++ +"].amount"] = o[6].replace(/[,]/g, "");
	});
	$.map((window["payCCListData"] || []), function(o, i) { // For: Credit Card
		params["methods["+ methodIndex +"].type"] = "CREDITCARD";
		params["methods["+ methodIndex +"].code"] = "3";
		params["methods["+ methodIndex +"].name"] = "บัตรเครดิต";
		params["methods["+ methodIndex++ +"].amount"] = o[4].replace(/[,]/g, "");
	});
	$.map((window["payMoneyOrderTableListData"] || []), function(o, i) { // For: Money Order
		params["methods["+ methodIndex +"].type"] = "MONEYORDER";
		params["methods["+ methodIndex +"].code"] = "4";
		params["methods["+ methodIndex +"].name"] = "ธนาณัติ";
		params["methods["+ methodIndex++ +"].amount"] = o[4].replace(/[,]/g, "");
	});
	$.map((window["payBillExchgTableListData"] || []), function(o, i) { // For: Bill of Exchange
		params["methods["+ methodIndex +"].type"] = "BILLEXCHANGE";
		params["methods["+ methodIndex +"].code"] = "5";
		params["methods["+ methodIndex +"].name"] = "ตั๋วแลกเงิน";
		params["methods["+ methodIndex++ +"].amount"] = o[4].replace(/[,]/g, "");
	});
	$.map((window["payCouponTableListData"] || []), function(o, i) { // For: Coupon
		params["methods["+ methodIndex +"].type"] = "COUPON";
		params["methods["+ methodIndex +"].code"] = "6";
		params["methods["+ methodIndex +"].name"] = "คูปอง";
		params["methods["+ methodIndex++ +"].amount"] = o[4].replace(/[,]/g, "");
	});
	$.map((window["payBankTxnfTableListData"] || []), function(o, i) { // For: Money Transfer
		params["methods["+ methodIndex +"].type"] = "BANKTRANSFER";
		params["methods["+ methodIndex +"].code"] = "7";
		params["methods["+ methodIndex +"].name"] = "เงินโอนในประเทศ";
		params["methods["+ methodIndex +"].bankAcct"] = o[4];
		params["methods["+ methodIndex++ +"].amount"] = o[6].replace(/[,]/g, "");
	});
	$.map((window["payOffsetTableListData"] || []), function(o, i) { // For: Other Type
		params["methods["+ methodIndex +"].type"] = "OFFSET";
		params["methods["+ methodIndex +"].code"] = "8";
		params["methods["+ methodIndex +"].name"] = "offset";
		params["methods["+ methodIndex++ +"].amount"] = o[4].replace(/[,]/g, "");
	});
	$.map((window["payOtherTableListData"] || []), function(o, i) { // For: Other Type
		params["methods["+ methodIndex +"].type"] = "OTHER";
		params["methods["+ methodIndex +"].code"] = "9";
		params["methods["+ methodIndex +"].name"] = "อื่น ๆ";
		params["methods["+ methodIndex++ +"].amount"] = o[4].replace(/[,]/g, "");
	});
	if (cashRow.length == 1) { // For: Cash
		params["methods["+ methodIndex +"].type"] = "CASH";
		params["methods["+ methodIndex +"].code"] = "1";
		params["methods["+ methodIndex +"].name"] = "เงินสด";
		params["methods["+ methodIndex++ +"].amount"] = cashRow[0].payAmount;
	}
	// <!-- Invoice List. -->
	var custIndex = 0, billingList = view.session("billingList"), receiptList = [];
	while (customer = billingList[custIndex]) {
		params["customers["+ custIndex +"].custNo"] = customer.custNo;
		params["customers["+ custIndex +"].custName"] = customer.custName;
		params["customers["+ custIndex +"].custType"] = customer.custType;
		params["customers["+ custIndex +"].address1"] = customer.address1;
		params["customers["+ custIndex +"].address2"] = customer.address2;
		params["customers["+ custIndex +"].remark"] = customer.remark;
		params["customers["+ custIndex +"].split"] = customer.split;
		for (var serviceIndex = 0, n = customer.serviceList.length; serviceIndex < n; serviceIndex++) {
			var service = customer.serviceList[serviceIndex];
			params["customers["+ custIndex +"].services["+ serviceIndex +"].type"] = service.serviceType;
			params["customers["+ custIndex +"].services["+ serviceIndex +"].name"] = service.serviceName;
			params["customers["+ custIndex +"].services["+ serviceIndex +"].moreData"] = service.serviceMoreData;
			params["customers["+ custIndex +"].services["+ serviceIndex +"].unit"] = service.serviceUnit;
			params["customers["+ custIndex +"].services["+ serviceIndex +"].amount"] = (service.serviceAmount*service.serviceMoreData);
			params["customers["+ custIndex +"].services["+ serviceIndex +"].discount"] = service.serviceDiscount;
			params["customers["+ custIndex +"].services["+ serviceIndex +"].charge"] = ((service.serviceAmount *service.serviceMoreData)- service.serviceDiscount);
			params["customers["+ custIndex +"].services["+ serviceIndex +"].vat"] = service.serviceVat;
			params["customers["+ custIndex +"].services["+ serviceIndex +"].totalCharge"] = service.serviceTotalCharge;
			params["customers["+ custIndex +"].services["+ serviceIndex +"].deduction"] = service.serviceDeduction;
		}
		custIndex++;
	}
	post("../createPaymentService.json", params, function(res){
		view.message.clear();
		$(document.body).append('<form action="../printPaymentOthersReceipt.pdf" method="post" target="_printForm"></form>');
		var form = document.forms[0]; function input(n,v) { var input = document.createElement("input"); input.type = "hidden"; input.name = n; input.value = v; return input }
		$.each(res.data, function(i,o){
			form.appendChild(input("receipts["+ i +"].id", o.id));
		});
		form.submit();
		view.breadcrumb.index(4);
		view.panelNavigation.hide(0);
		view.panelPaymentDetails.hide(0);
		view.panelPaymentSummary.hide(0);
		view.panelMethodSummary.hide(0);
		view.panelSummaryPaymentList.show(400);
		var data = []; $.map(billingList, function(o,i){
			var custNo = o.custNo, custName = o.custName;
			$.map(o.serviceList, function(p,j){
				data.push({ "custNo": custNo, "custName": custName, "serviceType": p.serviceType, "serviceName": p.serviceName, "serviceMoreData": p.serviceMoreData, "serviceAmount": p.serviceAmount, "serviceVat": p.serviceVat, "serviceDiscount": p.serviceDiscount, "serviceDeduction": p.serviceDeduction, "serviceTotalCharge": p.serviceTotalCharge, "status": '<span class="glyphicon glyphicon-ok-circle"></span> บันทึกลงระบบสำเร็จ' })
			});
		});
		view.tableSummaryReceipts.data(data);
	}, view.message);
}
function buttonCancelPaymentClickEvent() {
	location.href = "pay-other_1.jsp?new"
}
function buttonAddTaxTypeClickEvent() {
	var index = view.buttonSetTaxType.index();
	var amount = 0;
	if (index == 0) { amount = view.tableWithholdingTaxList.sum(3) }
	else if (index == 1) { amount = view.tableOtherTaxList.sum(5) }
	else if (index == 2) { amount = view.tableOffsetTaxList.sum(3) }
	view.tableSummaryTaxType.remove(index).insert({ "index": index, "method": index, "taxType": view.buttonSetTaxType.val(), "taxAmount": amount });
	calculate();
}
function buttonAddPayTypeClickEvent() {
	var index = view.buttonSetPayType.index();
	var amount = 0;
	if (index == 0) { amount = view.inputPayCashAmount.val() }
	else if (index == 1) { window["payChqListData"] = view.tablePayChequeList.data();                  amount = view.tablePayChequeList.sum(6) }
	else if (index == 2) { window["payCCListData"] = view.tablePayCreditCardList.data();               amount = view.tablePayCreditCardList.sum(4) }
	else if (index == 3) { window["payMoneyOrderTableListData"] = view.tablePayMoneyOrderList.data();  amount = view.tablePayMoneyOrderList.sum(4) }
	else if (index == 4) { window["payBillExchgTableListData"] = view.tablePayBillExchangeList.data(); amount = view.tablePayBillExchangeList.sum(4) }
	else if (index == 5) { window["payCouponTableListData"] = view.tablePayCouponList.data();          amount = view.tablePayCouponList.sum(4) }
	else if (index == 6) { window["payBankTxnfTableListData"] = view.tablePayBankTransferList.data();  amount = view.tablePayBankTransferList.sum(6) }
	else if (index == 7) { window["payOtherTableListData"] = view.tablePayOtherList.data();            amount = view.tablePayOtherList.sum(4) }
	view.tableSummaryPayType.remove(index).insert({ "index": index, "method": index, "payType": view.buttonSetPayType.val(), "payAmount": amount });
	calculate();
}
function tableSummaryTaxTypeModifyEvent(table, row, ind) { console.log(row) }
function tableSummaryPayTypeModifyEvent(table, row, ind) { console.log(row) }
function calculate() {
	var taxAmount = view.tableSummaryTaxType.sum(true, "taxAmount"), balanceDue = view.inputBalanceDue.val(), payAmount = view.tableSummaryPayType.sum(true, "payAmount");
	var change = payAmount - balanceDue;
	view.inputDeduction.val(taxAmount);
	view.inputReceived.val(payAmount);
	view.inputChange.val(change > 0 ? change : 0);
}


$("#check_dis").change(function() {
		if ($(this).prop("checked")) {
			// alert('ok');
			$("#modal_authen").modal('show');
		}
	});
	$("#testmenu li a").click(function() {

		$(this).parents(".btn-group").find('.selection').text($(this).text());
		$(this).parents(".btn-group").find('.selection').val($(this).text());

	});
	$("#testmenu2 li a").click(function() {

		$(this).parents(".btn-group").find('.selection').text($(this).text());
		$(this).parents(".btn-group").find('.selection').val($(this).text());

	});
	function showTab1(tab) {
		// $('#myTab1 a[href="#tab-1-3"]').tab('show');
		$('#myTab1 a[href="#tab-1-' + tab + '"]').tab('show');
	}
	function showTab2(tab) {
		$('#myTab2 a[href="#tab' + tab + '"]').tab('show');
		// $('#myTab1 a[href="#tab-1-3"]').tab('show');

	}
	function show_modal_advance() {
		$("#modal_advance").modal("show");
	}
</script>