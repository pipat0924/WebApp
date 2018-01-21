<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
		<div class="row">
			<div class="col-md-12 tab-modefile">
				<ol id="breadcrumb" class="breadcrumb">
					<li><i>รับชำระเงินโอนในประเทศ</i></li>
					<li class="active">ค้นหาข้อมูลลูกค้า</li>
					<li>สรุปรายการเงินโอน</li>
					<li>ผลการรับชำระ</li>
				</ol>
				<div id="dialogMainMessage"></div>
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
												<label class="control-label col-sm-2">Billing Account :</label>
												<div class="col-sm-2"><input id="inputBillNo" class="form-control" disable></div>
												<label class="control-label col-sm-2" >Barcode / QRcode :</label>
												<div class="col-sm-2"><input id="inputBarcodeQRcode" class="form-control"></div>
												<div class="col-sm-4">
													<a id="buttonSearchBillNo" class="btn btn-primary"><span class="glyphicon glyphicon-search"></span> ค้นหา</a>&nbsp;&nbsp;
													<a id="buttonAdvanceSearch" class="btn btn-success"  data-toggle="modal" data-target="#CustomerSearch"><span class="glyphicon glyphicon-zoom-in"></span> ค้นหาเพิ่มเติม</a>
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
		<div id="panelNavigation" class="row form-inline text-right hide">
			<a id="buttonAddBillPayment" class="btn btn-link"><span class="glyphicon glyphicon-plus-sign"></span> เพิ่มรายการรับชำระ</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<a id="buttonSummaryPayment" class="btn btn-link" href="pay-4-stap_2.jsp"><span class="glyphicon glyphicon-th-list"></span> สรุปรายการเงินโอน</a><span class="badge badge_modefil">0</span>
			<a id="buttonProcessPayment" class="btn btn-link"><span class="glyphicon glyphicon-edit"></span> ดำเนินการรับชำระ</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		</div>
		<div id="panelCustomerInformation" class="row hide">
			<div class="col-md-12 tab-modefile">
				<div class="tab-content">
					<ul class="nav nav-tabs" role="tablist">
						<li role="presentation" class="active"><a href="#tab_cus" aria-controls="tab_cus" role="tab" data-toggle="tab"><span class="glyphicon glyphicon-user"></span> ข้อมูลลูกค้า</a></li>
					</ul>
					<div role="tabpanel" class="tab-pane active" id="tab_cus">
						<div class="panel panel-default panal-radius">
							<div class="panel-body">
								<div class="row">
									<div class="col-md-12">
										<div class="form-horizontal">
											<div class="form-group">
												<label class="control-label col-sm-2">เลขที่ลูกค้า :</label>
												<div class="col-sm-2"><input id="inputCustomerBillNo" class="form-control" disabled="disabled"></div>
												<label class="control-label col-sm-2">ชื่อลูกค้า :</label>
												<div class="col-sm-6"><input id="inputCustomerName" class="form-control"></div>
											</div>
											<div class="form-group">
												<label class="control-label col-sm-2">Tax ID :</label>
												<div class="col-sm-2"><input id="inputCustomerTaxNo" class="form-control" disable></div>
												<label class="control-label col-sm-2">สาขา :</label>
												<div class="col-sm-2"><input id="inputCustomerBranch" class="form-control"></div>
												<label class="control-label col-sm-2">หน่วยงานติดตามหนี้ :</label>
												<div class="col-sm-2"><input id="inputCustomerCollectionUnit" class="form-control" disabled></div>
											</div>
											<div class="form-group">
												<label class="control-label col-sm-2">กลุ่มผู้ใช้บริการ :</label>
												<div class="col-sm-2"><select class="form-control" id="inputCustomerGroup"></select></div>
												<label class="control-label col-sm-2" ></label>
												<div class="col-sm-2"></div>
												<label class="control-label col-sm-2">ยอดค้างชำระ (รวม VAT) :</label>
												<div class="col-sm-2"><input class="form-control" id="inputCustomerBalanceDue" disabled></div>
											</div>
											<div class="form-group">
												<label class="control-label col-sm-2">Billing Group :</label>
												<div class="col-sm-2"><input class="form-control" id="inputCustomerBillGroup" disabled></div>
												<label class="control-label col-sm-2"></label>
												<div class="col-sm-2"></div>
												<label class="control-label col-sm-2">ยอดชำระล่วงหน้า :</label>
												<div class="col-sm-2"><input class="form-control" id="inputCustomerTotalPaid" disabled></div>
											</div>
											<div class="form-group">
												<label class="control-label col-sm-2">สถานะลูกค้า :</label>
												<div class="col-sm-2"><input class="form-control" id="inputCustomerStatus" value="Active" disabled></div>
												<label class="control-label col-sm-2"></label>
												<div class="col-sm-2"></div>
												<label class="control-label col-sm-2">สกุลเงิน :</label>
												<div class="col-sm-2"><input class="form-control text-right" id="inputCustomerCurrencyCode" value="THB" disabled></div>
											</div>
											<div class="form-group">
												<label class="control-label col-sm-2">หมายเหตุ :</label>
												<div class="col-sm-6"><input class="form-control" id="inputCustomerRemark"></div>
												<label class="control-label col-sm-2">VAT Rate :</label>
												<div class="col-sm-2"><input class="form-control text-right" id="inputCustomerVatRate" value="7" disabled></div>
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-8 tab-modefile">
										<ul class="nav nav-tabs" role="tablist">
											<li role="presentation" class="active"><a href="#tab1" aria-controls="tab1" role="tab" data-toggle="tab"><span class="glyphicon glyphicon-home"></span> ที่อยู่สำหรับใบเสร็จรับเงิน</a></li>
											<li role="presentation"><a href="#tab2" aria-controls="tab2" role="tab" data-toggle="tab"><span class="glyphicon glyphicon-home"></span> ที่อยู่สำหรับใบแจ้งค่าใช้บริการ</a></li>
											<li role="presentation"><div class="checkbox"><label>&nbsp;&nbsp;&nbsp;<input type="checkbox">&nbsp;เปลี่ยนแปลงที่อยู่ในใบเสร็จรับเงิน/ใบกำกับภาษี</label></div></li>
										</ul>
										<div class="tab-content">
											<div role="tabpanel" class="tab-pane active" id="tab1">
												<div class="panel panel-default panal-radius">
													<div class="col-md-12">
														<div class="form-horizontal">
															<div class="form-group"><input class="form-control" id="inputCustomerReceiptAddress" disabled></div>
														</div>
													</div>
												</div>
											</div>
											<div role="tabpanel" class="tab-pane" id="tab2">
												<div class="panel panel-default panal-radius">
													<div class="col-md-12">
														<div class="form-horizontal">
															<div class="form-group"><input class="form-control" id="inputCustomerInvoiceAddress" disabled></div>
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
		</div>
		<div id="panelInvoiceDetails" class="row hide" style="margin-bottom: 20px">
			<div class="col-md-12 tab-modefile">
				<ul class="nav nav-tabs" role="tablist">
					<li role="presentation" class="active"><a href="#tab-2-1" aria-controls="tab-2-1" role="tab" data-toggle="tab"><span class="glyphicon glyphicon-briefcase"></span> รายการใบแจ้งค่าใช้บริการ</a></li>
					<li role="presentation" class=""><a href="#tab-2-2" aria-controls="tab-2-2" role="tab" data-toggle="tab"><span class="glyphicon glyphicon-th-list"></span> ประวัติการรับชำระ</a></li>
				</ul>
				<div class="tab-content">
					<div role="tabpanel" class="tab-pane active" id="tab-2-1">
						<table id="tableInvoiceList" data-row-style="rowStyle" data-toggle="table" data-detail-view="true" data-detail-formatter="detailFormatter" data-classes="table table-hover table-striped">
							<thead>
								<tr>
									<th data-field="checked" data-formatter="invoiceCheckboxFormatter">&nbsp;</th>
									<th data-align="center" data-formatter="runningFormatter"></th>
									<th data-field="invoiceNo" data-sortable="true">เลขที่ใบแจ้ง<br>ค่าใช้บริการ</th>
									<th data-field="issueDt" data-sortable="true">วันจัดทำใบแจ้ง<br>ค่าใช้บริการ</th>
									<th data-field="dueDt" data-sortable="true" data-align="right">วันครบ<br>กำหนด</th>
									<th data-field="charge" data-sortable="true" data-align="right" data-formatter="numberFormatter">ยอดก่อน<br>ภาษี</th>
									<th data-field="discount" data-sortable="true" data-align="right" data-formatter="numberInputFormatter">เงิน<br>ส่วนลด</th>
									<th data-field="vat" data-sortable="true" data-align="right" data-formatter="numberFormatter">ภาษีมูลค่า<br>เพิ่ม</th>
									<th data-field="totalCharge" data-sortable="true" data-align="right" data-formatter="numberFormatter">ยอดเงิน<br>รวมภาษี</th>
									<th data-field="balanceDue" data-sortable="true" data-align="right" data-formatter="numberInputFormatter">จำนวนเงิน<br>คงค้าง</th>
									<th data-field="totalPaid" data-sortable="true" data-align="right" data-formatter="numberFormatter">ยอดเงิน<br>ที่ต้องชำระ</th>
									<th data-field="deduct" data-sortable="true" data-align="right" data-formatter="numberInputFormatter">ภาษีหัก<br>ณ ที่จ่าย</th>
									<th data-field="billCycle">รอบการ<br>ใช้งาน</th>
									<th data-field="status" data-sortable="true">สถานะ</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td><input type="checkbox" class="checkbox" id="checkbox_37" onclick="show_price('37')"></td>
									<td>1</td>
									<td>255900001</td>
	                                    <td>02/02/2016</td>
	                                    <td>22/02/2016</td>
	                                    <td>10.00</td>
	                                    <td>1,000.00</td>
	                                    <td>70.00</td>
	                                    <td>1,070.00</td>
	                                    <td>1,070.00</td>
	                                    <td><input class="text-right" value="1,070.00" size="12px"></td>
	                                    <td>30.00</td>
	                                    <td>01/01/2016-30/01/2016</td>
	                                    <td>Normal</td>
		                            </tr>
							</tbody>
						</table>
					</div>
					<div role="tabpanel" class="tab-pane" id="tab-2-2">
						<table id="table" data-row-style="rowStyle" data-toggle="table" data-classes="table table-hover table-striped">
							<thead>
								<tr>
									<th data-field="a" class="">#</th>
									<th data-field="b" data-halign="center" data-align="center" class="">เลขที่ใบแจ้ง<br>ค่าใช้บริการ</th>
									<th data-field="c" data-halign="center" data-align="center" class="">ยอดเงินตามใบแจ้ง<br>ค่าใช้บริการ</th>
									<th data-field="e" data-halign="center" data-align="center" class="">รอบการใช้งาน</th>
									<th data-field="f" data-halign="center" data-align="left" class="">สถานที่<br>รับชำระเงิน</th>
									<th data-field="g" data-halign="center" data-align="center" class="" class="">เลขที่ใบ<br>กำกับภาษี</th>
									<th data-field="h" data-halign="center" data-align="center" class="" class="">วันที่ออกใบ<br>กำกับภาษี</th>
									<th data-field="i" data-halign="center" data-align="left" class="">ชื่อลูกค้า</th>
									<th data-field="k" data-halign="center" data-align="left" class="">วิธีการ<br>ชำระเงิน</th>
									<th data-field="j" data-halign="center" data-align="left" class="">ประเภทการ<br>ชำระเงิน</th>
									<th data-field="q" data-halign="center" data-align="right" class="">ยอดชำระ</th>
									<th data-field="m" data-halign="center" data-align="center" class="">สถานะ</th>
								</tr>
							</thead>
	                            <tbody>
	                                <tr>
	                                    <td>1</td>
	                                    <td>255900001</td>
	                                    <td>1,170.00</td>
	                                    <td>01/01/2016-30/01/2016</td>
	                                    <td>ศบล.นนทบุรี</td>
	                                    <td>EP0171701F150714000001</td>
	                                    <td>22/02/2016</td>
	                                    <td>บริษัท AEC จำกัด</td>
	                                    <td>เงินโอน</td>
	                                    <td>ชำระเต็มจำนวน</td>
	                                    <td>1,030.00</td>
	                                    <td>Normal</td>
	                                </tr>
	                            </tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
		<div id="panelPaymentDetails" class="row hide">
			<div class="col-md-7 tab-modefile">
				<ul class="nav nav-tabs" role="tablist">
					<li role="presentation" class="active"><a href="#tab-2-1" aria-controls="tab-2-1" role="tab" data-toggle="tab"><span class="glyphicon glyphicon-inbox"></span> ข้อมูลการโอนเงินในประเทศ</a></li>
				</ul>
				<div class="tab-content">
					<div role="tabpanel" class="tab-pane active" id="tab_x">
						<div class="panel panel-default panal-radius">
							<div class="panel-body">
								<div class="row">
									<div class="col-md-12">
										<div class="form-horizontal">
											<div class="form-group">
												<label class="control-label col-sm-4" >รหัสธนาคาร :</label>
												<div class="col-sm-3"><input id="inputBankCode" class="form-control" value="006"></div>
												<label class="control-label col-sm-2" >ชื่อธนาคาร :</label>
												<div class="col-sm-3"><select id="inputBankName" class="form-control"></select></div>
											</div>
											<div class="form-group">
												<label class="control-label col-sm-4" >สาขา :</label>
												<div class="col-sm-3"><select id="inputBankBrnh" class="form-control"></select></div>
												<label class="control-label col-sm-2" >เลขที่อ้างอิง :</label>
												<div class="col-sm-3"><input id="inputBankRefNo" class="form-control" value="123455"></div>
											</div>
											<div class="form-group">
												<label class="control-label col-sm-4" >รหัสบัญชีเงินฝากธนาคาร :</label>
												<div class="col-sm-3"><select id="inputBankAcCd" class="form-control"></select></div>
												<label class="control-label col-sm-2" >วันที่โอน :</label>
												<div class="col-sm-3">
													<div class="input-group input-append date" data-provide="datepicker" data-date-format="dd/mm/yyyy">
														<input class="form-control" id="inputBankDate" placeholder="dd/MM/yyyy" maxlength="10">
														<span class="input-group-addon add-on"><span class="glyphicon glyphicon-calendar"></span></span>
													</div>
												</div>
											</div>
											<div class="form-group">
												<label class="control-label col-sm-4" >เลขที่บัญชีเงินฝากธนาคาร :</label>
												<div class="col-sm-3"><select id="inputBankAcNo" class="form-control"></select></div>
	                                                <label class="control-label col-sm-2" >จำนวนเงิน :</label>
	                                                <div class="col-sm-3">
	                                                    <input class="form-control text-right" id="inputBankAmnt">
	                                                </div>
	                                            </div>
	                                            <div class="form-group">
	                                           		<label class="control-label col-sm-4" >หมายเหตุ :</label>
	                                                <div class="col-sm-8">
	                                                    <input class="form-control" id="inputBankRemark">
	                                                </div>
	                                            </div> 
												<div class="form-group" style="height:33px;">
	                                           		<label class="control-label col-sm-4" ></label>
	                                                <div class="col-sm-8"></div>
	                                            </div>

		                                    </div>
		                                </div>
		                            </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>   
                <div class="col-md-5 tab-modefile">
                    <ul class="nav nav-tabs" role="tablist">
                        <li role="presentation" class="active"><a href="#tab-2-1" aria-controls="tab-2-1" role="tab" data-toggle="tab"><span class="glyphicon glyphicon-film"></span> รายการหักอื่นๆ</a></li>
                        <li role="presentation" class="pull-right">
                        <div id="buttonSetDeductType" class="btn-group"></div>
						 </li>
                    </ul>
                    <div class="tab-content">
                        <div role="tabpanel" class="tab-pane active" id="tab_b">
                            <div class="panel panel-default panal-radius">
                                <div class="panel-body">
                                    <div class="row">
		                                <div class="col-md-12">
                                            <div role="tabpanel" class="tab-pane hide" id="deductType1">
												<div class="form-horizontal">
												    <div class="form-group">
													<label class="control-label col-sm-2" style="padding-right: 0; padding-left: 0">ศูนย์ต้นทุน :</label>
													<div class="col-sm-4">
													    <select class="form-control">
														<option>0000 จต.</option>
													    </select>
													</div>
													<label class="control-label col-sm-3" style="padding-right: 0; padding-left: 0">จำนวนเงิน :</label>
													<div class="col-sm-3">
													    <input type="text" class="form-control text-right" value="100.00">
													</div>
												    </div>
												    <div class="form-group">
													<label class="control-label col-sm-2" style="padding-right: 0; padding-left: 0">ภาษีซื้อ :</label>
													<div class="col-sm-4">
													    <input type="text" class="form-control text-right" value="0.00">
													</div>
													<label class="control-label col-sm-3" style="padding-right: 0; padding-left: 0">ภาษีหัก ณ ที่จ่าย :</label>
													<div class="col-sm-3">
													    <input type="text" class="form-control text-right" value="0.00">
													</div>
												    </div>
												</div>
												<table class="table table-striped">
												    <thead>
													<tr>
													    <th>#</th>
													    <th>ศูนย์ต้นทุน</th>
													    <th>ภาษีหัก ณ ที่จ่าย</th>
													    <th>ภาษีซื้อ</th>
													    <th>จำนวนเงิน</th>
													    <th></th>
													</tr>
												    </thead>
												    <tbody>
													<tr>
													    <td>1</td>
													    <td>0000 จต.</td>
													    <td>0.00</td>
													    <td>0.00</td>
													    <td>100.00</td>
													    <td><a href="#"><span class="glyphicon glyphicon-trash"></span></a></td>
													</tr>
												    </tbody>
												</table>
												<table id="table"
					                               data-row-style="rowStyle"
					                               data-toggle="table"
					                               data-classes="table table-hover table-striped">
												    <thead>
													<tr>
													    <th>#</th>
													    <th data-halign="center" data-align="left" class="">สรุปรายการหัก</th>
													    <th data-halign="center" data-align="right" class="">จำนวนเงิน</th>
													</tr>
												    </thead>
												    <tbody>
													<tr>
													    <td>1</td>
													    <td>ค่าธรรมเนียม</td>
													    <td>30.00</td>
													</tr>
												    </tbody>
												</table>
											</div>
                                            <div role="tabpanel" class="tab-pane hide" id="deductType2">2
		                                    </div>
                                            <div role="tabpanel" class="tab-pane hide" id="deductType3">3
		                                    </div>
                                            <div role="tabpanel" class="tab-pane hide" id="deductType4">4
		                                    </div>
		                                </div>
		                            </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>   
			</div>
		<div id="panelSummaryDetails" class="row hide">
                <div class="col-md-12">
                    <div class="panel panel-default">
                        <div class="panel-heading"><span class="glyphicon glyphicon-shopping-cart"></span> สรุปยอดเงินที่ต้องชำระ</div>
                        <div class="panel-body">
                            <div class="form-horizontal">
                                <div class="form-group">
		                            <label class="control-label col-sm-2" ><input type="checkbox" name="inputPrepaidCheckBox">&nbsp;&nbsp;&nbsp;รับชำระเงินล่วงหน้า</label>
		                            <div class="col-sm-2"><input class="form-control" id="inputPrepaidAmount"></div>
		                                <label class="control-label col-sm-6">จำนวนเงินก่อนหักส่วนลด :</label>
                                <div class="col-sm-2">
                                    <input class="form-control text-right" id="inputAmount" disabled="disabled">
                                </div>
		                        </div>
		                         <div class="form-group">
                                    <label class="control-label col-sm-10">  
                                        <div class="checkbox">
                                            <label>
                                                <input type="checkbox" id="check_dis"> <b>ลูกค้ารับภาษี&nbsp;&nbsp;&nbsp;<span class="glyphicon glyphicon-lock"></span> ส่วนลด (Discount) :</b>
                                            </label>
                                        </div>
                                    </label>
                                    <div class="col-sm-2">
                                        <input class="form-control text-right" id="inputDiscount" disabled="disabled">
                                    </div>
                                </div>
		                        <div class="form-group">
		                         <label class="control-label col-sm-10" >จำนวนเงินก่อนภาษีมูลค่าเพิ่ม (Charge) :</label>
		                            <div class="col-sm-2">
		                                <input class="form-control text-right" id="inputCharge" disabled="disabled">
		                            </div>
                               
                             </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-10" >ภาษีมูลค่าเพิ่ม (VAT) :</label>
                                    <div class="col-sm-2">
                                        <input class="form-control text-right" id="inputVat" disabled="disabled">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-10" >จำนวนเงินที่ต้องชำระรวมภาษีมูลค่าเพิ่ม :</label>
                                    <div class="col-sm-2">
                                        <input class="form-control text-right" id="inputTotalCharge" disabled="disabled">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-10" >ภาษีหัก ณ ที่จ่าย (WT) :</label>
                                    <div class="col-sm-2">
                                        <input class="form-control text-right" id="inputDeduct">
                                    </div>
                                </div>
                               
                                <div class="form-group">
                                    <label class="control-label col-sm-10" >ยอดเงินที่ต้องชำระ :</label>
                                    <div class="col-sm-2">
                                        <input class="form-control text-right" id="inputTotalCharge2">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-10" >ยอดเงินโอน :</label>
                                    <div class="col-sm-2">
                                        <input class="form-control text-right" id="inputTxnfAmount" disabled="disabled">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-10" >
                                        <label class="radio-inline">
                                            <input type="radio" name="Radio" id="Radio1" value="" checked> <b>รายได้อื่นที่ไม่มีภาษี</b>
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio" name="Radio" id="Radio2" value=""> <b>รายได้อื่นมีภาษี</b>
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio" name="Radio" id="Radio3" onchange="show_modal_advance()" value=""> <b>รับชำระล่วงหน้า :</b>
                                        </label>
                                    </label>
                                    <div class="col-sm-2">
                                        <input class="form-control text-right" id="inputAdvanced">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
    </section>
    <div class="modal fade" role="dialog" id="CustomerSearch">
        <div class="modal-dialog modal-lg">
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
                                                <label class="control-label col-sm-3" >เลขที่ลูกค้า :</label>
                                                <div class="col-sm-3">
                                                    <input class="form-control" value="70000657?">
                                                </div>
                                                <label class="control-label col-sm-3" >เลขประจำตัวผู้เสียภาษี :</label>
                                                <div class="col-sm-3">
                                                    <input class="form-control">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-sm-3" >ชื่อลูกค้า :</label>
                                                <div class="col-sm-3">
                                                    <input class="form-control">
                                                </div>
                                                <label class="control-label col-sm-3" >นามสกุล :</label>
                                                <div class="col-sm-3">
                                                    <input class="form-control">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label col-sm-3" >ชื่อนิติบุคคล/ราชการ :</label>
                                                <div class="col-sm-6">
                                                    <input class="form-control">
                                                </div>
                                                <div class="col-sm-3">
                                                    <button type="button" class="btn btn-info pull-right"><span class="glyphicon glyphicon-search"></span> ค้นหา</button>
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

				<i class="pull-right"><span class="glyphicon glyphicon-filter"></span> สามารถใช้เครื่องหมาย ? แทนหนึ่งตัวอักษร หรือ * แทนหลายตัวอักษรในการค้นหา</i><br>
                </div>
				
            </div>
        </div>
    </div>
   
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
		obj.elem.append('<a class="btn btn-default dropdown-toggle" data-toggle="dropdown"><span class="selection">'+ data[0] +'</span> <span class="caret"></span></a>').append('<ul class="dropdown-menu">'+ $.map(data, function(v,i){ return '<li><a href="javascript:void(0)">'+ v +'</a></li>' }).join("") +'</ul>');
		obj.selected = obj.elem.find(".selection");
		obj.list = obj.elem.find(".dropdown-menu a").each(function(i){ var a = this; $(a).click(function(){ index = i; obj.selected.text(a.innerHTML) }) });
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
	function CheckBox(el) { var obj = this; obj.el = el; obj.elem = $(el); obj.elem.click(window[obj.elem.attr("name") +"ClickEvent"])
		obj.contains = function(val) { return $.inArray(val, obj.val()) > -1 };
		obj.val = function() { return obj.elem.filter(":checked").map(function(i,o){ return o.value }) };
		obj.unchecked = function(){ obj.elem.each(function(i,o){ o.checked = false }); return this }
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
	function FadePanel() { var obj = this, index = 0, dura = 200;
		obj.els = $.map(arguments, function(o,i){ return $.type(o) !== "string" ? null : o });
		obj.elems = $(obj.els.join(","));
		obj.hide = function() { obj.elems.addClass("hide"); return this }; obj.show = function() { obj.elems.removeClass("hide"); return this }
		obj.prev = function() { obj.index(index - 1); return this }; obj.next = function() { obj.index(index + 1); return this }
		obj.index = function(ind) { if (ind === index) return this; var target = obj.elems.length > ind && ind > -1 ? ind : (ind >= obj.elems.length ? 0 : obj.elems.length - 1); obj.elems.eq(index).fadeOut(dura, function(){ obj.elems.eq(index = target).removeClass("hide").css("display", "") }); return this }
		obj.elems.eq(index).hide().removeClass("hide").show(dura);
		arguments[0].list.each(function(i,o){ $(o).click(function(){ obj.index(i) }) })
	}
	function Panel(el) { var obj = this, dura = 500; obj.el = el; obj.elem = $(el);
		obj.hide = function(ms) { obj.elem.hide(ms || dura); return this }; obj.show = function(ms) { if (!ms || !$.isNumeric(ms)) ms = dura; if (ms >= 0) obj.elem.css("display", "none").removeClass("hide").removeClass("hidden").show(ms); else obj.hide(Math.abs(ms)); return this }
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
		obj.elem.on("check.bs.table uncheck.bs.table check-all.bs.table uncheck-all.bs.table", window[el.replace("#", "") +"CheckBoxEvent"]);
	}
	(function(){ $(window["setup"]) })();
	// TO SUPPORT ECLIPSE OUTLINE.
	self.breadcrumb = new Breadcrumb("#breadcrumb");
	self.dialogMainMessage = new Message("#dialogMainMessage");
	self.buttonSearchBillNo = new Button("#buttonSearchBillNo");
	self.buttonAddBillPayment = new Button("#buttonAddBillPayment");
	self.buttonSummaryPayment = new Button("#buttonSummaryPayment");
	self.buttonProcessPayment = new Button("#buttonProcessPayment");
	self.buttonSetDeductType = new ButtonGroup("#buttonSetDeductType", ["ค่าธรรมเนียม", "ค่าปรับ", "เงินประกันผลงาน", "ค่าตอบแทนการรับชาระเงิน"]);
	self.inputBillNo = new Input("#inputBillNo");
	self.inputCustomerBillNo = new Input("#inputCustomerBillNo");
	self.inputCustomerName = new Input("#inputCustomerName");
	self.inputCustomerTaxNo = new Input("#inputCustomerTaxNo");
	self.inputCustomerBranch = new Input("#inputCustomerBranch");
	self.inputCustomerCollectionUnit = new Input("#inputCustomerCollectionUnit");
	self.inputCustomerGroup = new DropDown("#inputCustomerGroup").data([{ key: "", value: "ธุรกิจทั่วไป" },{ key: "", value: "บุคคลธรรมดา" }]);
	self.inputCustomerBalanceDue = new NumberInput("#inputCustomerBalanceDue");
	self.inputCustomerBillGroup = new Input("#inputCustomerBillGroup");
	self.inputCustomerTotalPaid = new NumberInput("#inputCustomerTotalPaid");
	self.inputCustomerStatus = new Input("#inputCustomerStatus");
	self.inputCustomerCurrencyCode = new Input("#inputCustomerCurrencyCode");
	self.inputCustomerVatRate = new NumberInput("#inputCustomerVatRate", 0);
	self.inputCustomerRemark = new Input("#inputCustomerRemark");
	self.inputCustomerReceiptAddress = new Input("#inputCustomerReceiptAddress");
	self.inputCustomerInvoiceAddress = new Input("#inputCustomerInvoiceAddress");
	self.inputBankCode = new Input("#inputBankCode");
	self.inputBankName = new DropDown("#inputBankName").data([{ key: "", value: "กรุงเทพ" },{ key: "", value: "กสิกรไทย" },{ key: "", value: "ไทยพาณิชย์" }]);
	self.inputBankBrnh = new Input("#inputBankBrnh");
	self.inputBankDate = new Input("#inputBankDate");
	self.inputBankRefNo = new Input("#inputBankRefNo");
	self.inputBankAcCd = new DropDown("#inputBankAcCd").data([{ key: "", value: "" }]);
	self.inputBankAcNo = new DropDown("#inputBankAcNo").data([{ key: "", value: "404-0-55236-9" }]);
	self.inputBankAmnt = new NumberInput("#inputBankAmnt");
	self.inputBankRemark = new Input("#inputBankRemark");
	self.inputPrepaidCheckBox = new CheckBox("[name=inputPrepaidCheckBox]");
	self.inputPrepaidAmount = new NumberInput("#inputPrepaidAmount");
	self.inputAmount = new NumberInput("#inputAmount");
	self.inputDiscount = new NumberInput("#inputDiscount");
	self.inputCharge = new NumberInput("#inputCharge");
	self.inputVat = new NumberInput("#inputVat");
	self.inputTotalCharge = new NumberInput("#inputTotalCharge");
	self.inputTotalCharge2 = new NumberInput("#inputTotalCharge2");
	self.inputDeduct = new NumberInput("#inputDeduct");
	self.inputTxnfAmount = new NumberInput("#inputTxnfAmount");
	self.inputAdvanced = new NumberInput("#inputAdvanced");
	self.panelNavigation = new Panel("#panelNavigation");
	self.panelCustomerInformation = new Panel("#panelCustomerInformation");
	self.panelInvoiceDetails = new Panel("#panelInvoiceDetails");
	self.panelPaymentDetails = new Panel("#panelPaymentDetails");
	self.panelSummaryDetails = new Panel("#panelSummaryDetails");
	self.panelAddDeductType = new FadePanel(self.buttonSetDeductType, "#deductType1", "#deductType2", "#deductType3", "#deductType4");
	self.tableInvoiceList = new BootstrapTable("#tableInvoiceList");
	return this;
})(jQuery);

function setup() {
	var isNew = view.utils.queryString()["new"];
	if (isNew) {
		view.session("billingList", []);
	}
	var billingList = view.session("billingList");
	if (billingList.length > 0) {
		view.buttonAddBillPayment.disable();
		view.buttonSummaryPayment.badge(billingList.length);
		view.panelNavigation.show();
	}
}
function buttonSearchBillNoClickEvent() {
	view.dialogMainMessage.clear().showLoad("System is wait for response CRM data in {timeCnt} seconds.")
	get("../service/bill-profiles/search/no", { no: view.inputBillNo.val() }, function(res){
		view.panelNavigation.show(200);
		view.panelCustomerInformation.show(800);
		view.panelInvoiceDetails.show(1400);
		var summaryList = view.session("billingList").length;
		view.buttonAddBillPayment.disable();
		view.buttonSummaryPayment.disable(summaryList == 0).badge(summaryList);
		view.buttonProcessPayment.disable(summaryList == 0);
		var profile = res._embedded.billProfiles[0];
		view.inputCustomerBillNo.val(profile.no);
		view.inputCustomerName.val(profile.customerAccountName);
		view.inputCustomerTaxNo.val(profile.taxRegisterNo);
		view.inputCustomerBranch.val(profile.branchId);
		view.inputCustomerBillGroup.val(profile.billGroup);
		get("../service/bill-addrs/search/no", { "no": inputCustomerBillNo.val() }, function(res){
			var addr = res._embedded.billAddresses[0];
			view.inputCustomerReceiptAddress.val($.trim(addr.vatAddrLine1) +" "+ $.trim(addr.vatAddrLine2) +" "+ $.trim(addr.vatAddrLine3) +" "+ $.trim(addr.vatAddrLine4) +" "+ $.trim(addr.vatKhetAmphur) +" "+ $.trim(addr.vatProvince) +" "+ $.trim(addr.vatPostCode));
			view.inputCustomerInvoiceAddress.val($.trim(addr.billAddrLine1) +" "+ $.trim(addr.billAddrLine2) +" "+ $.trim(addr.billAddrLine3) +" "+ $.trim(addr.billAddrLine4) +" "+ $.trim(addr.billKhetAmphur) +" "+ $.trim(addr.billProvince) +" "+ $.trim(addr.billPostCode));
		}, view.dialogMainMessage);
		get("../findInvoiceList.json", { "no": view.inputCustomerBillNo.val() }, function(res){
			view.tableInvoiceList.data($.map(res.data, invoiceListMapper))
			var accruedAmount = 0, advancedAmount = 0; $.each(res.data, function(i,o){ accruedAmount += parseFloat(o.balanceDue, 10); if (o.billNo == "0") advancedAmount += Math.abs(parseFloat(o.totalPaid, 10)); });
			view.inputCustomerBalanceDue.val(accruedAmount);
			view.inputCustomerTotalPaid.val(advancedAmount);
			view.inputCustomerCurrencyCode.val(res.data[0].currencyCode);
			view.inputCustomerStatus.val("Active");
		}, view.dialogMainMessage, function(){ view.tableInvoiceList.hideLoad(); });
	}, view.dialogMainMessage, function(){ view.dialogMainMessage.stopLoad() })
}
function invoiceListMapper(o) {
	return {
		 "invoiceNo": o.billNo
		,"accountNo": o.accountNo
		,"issueDt": view.utils.dateFormat(o.issueDate)
		,"dueDt": view.utils.dateFormat(o.dueDate)
		,"amount": parseFloat(o.amountBeforeTax, 10)
		,"charge": parseFloat(o.amountBeforeTax, 10) - parseFloat((o.discount || 0), 10)
		,"discount": (o.discount || 0)
		,"vat": parseFloat(o.taxAmount, 10)
		,"totalCharge": parseFloat(o.amountAfterTax, 10)
		,"balanceDue": parseFloat(o.balanceDue, 10)
		,"totalAdj": parseFloat(o.totalAdj, 10)
		,"totalPaid": parseFloat(o.totalPaid, 10)
		,"deduct": parseFloat((o.withholdingTax || 0), 10)
		,"billCycle": view.utils.dateFormat(o.chargeCycleFromDate) +" - "+ view.utils.dateFormat(o.chargeCycleToDate)
		,"billRefNo": o.billNo
		,"currencyCode": o.currencycode
		,"status": o.status
	};
}
function invoiceCheckboxFormatter(val, row, ind) {
	return row.balanceDue <= 0 ? "" : '<input type="checkbox" name="invoiceSelected" onclick=\'invoiceCheckboxEvent(this, '+ JSON.stringify(row) +')\'>'
}
function invoiceCheckboxEvent(inp, row) {
	var checked = inp.checked;
	var amount = view.inputAmount.val();
	var discount = view.inputDiscount.val();
	var vat = view.inputVat.val();
	var totalCharge = view.inputTotalCharge.val();
	var deduct = view.inputDeduct.val();
	var balanceDue = (window["balanceDueAmt"] || 0);
	view.inputAmount.val(checked ? amount += row.amount : amount -= row.amount);
	view.inputDiscount.val(checked ? discount += row.discount : discount -= row.discount);
	view.inputCharge.val(amount - discount);
	view.inputVat.val(checked ? vat += row.vat : vat -= row.vat);
	view.inputTotalCharge.val(checked ? totalCharge += row.totalCharge : totalCharge -= row.totalCharge);
	view.inputDeduct.val(checked ? deduct += row.deduct : deduct -= row.deduct);
	window["balanceDueAmt"] = (checked ? balanceDue += row.balanceDue : balanceDue -= row.balanceDue);
	var invoiceSelectedList = window["invoiceSelectedList"] = (window["invoiceSelectedList"] || {});
	invoiceSelectedList[row.invoiceNo] = checked ? row : null;
	var invoiceSelectedListSize = $.map(invoiceSelectedList, function(o,i){ return o }).length;
	view.buttonAddBillPayment.disable(balanceDue <= 0);
	view.inputPrepaidCheckBox.unchecked();
	view.inputPrepaidAmount.disable().val(0);
	if (invoiceSelectedListSize == 0 && !checked) {
		view.panelPaymentDetails.hide(400);
		view.panelSummaryDetails.hide(1000);
		return;
	}
	if (invoiceSelectedListSize == 1 && checked ) {
		view.panelPaymentDetails.show(200);
		view.panelSummaryDetails.show(800);
		return;
	}
}
function inputPrepaidCheckBoxClickEvent() {
	view.inputPrepaidAmount.disable(!this.checked);
	if (!this.checked) view.inputPrepaidAmount.val(0);
}
function buttonAddBillPaymentClickEvent() {
	view.session("billingList", buildBillingList());
	location.href = 'pay-4-stap_2.jsp';
}
function buildBillingList() {
	var billingList = view.session("billingList");
	var invoiceList = [];
	var billingSel = $.map(billingList, function(o,i){ return o.custNo == view.inputCustomerBillNo.val() ? o : null });
	if (billingSel.length > 0) {
		billingSel[0].billGroup = view.inputCustomerBillGroup.val();
		billingSel[0].remark = view.inputCustomerRemark.val();
		billingSel[0].address1 = view.inputCustomerReceiptAddress.val();
		billingSel[0].address2 = view.inputCustomerInvoiceAddress.val();
		billingSel[0].prepaid = view.inputPrepaidAmount.val();
		invoiceList = billingSel[0].invoiceList;
	} else {
		billingList.push({
			"custNo": view.inputCustomerBillNo.val(),
			"custName": view.inputCustomerName.val(),
			"billGroup": view.inputCustomerBillGroup.val(),
			"remark": view.inputCustomerRemark.val(),
			"address1": view.inputCustomerReceiptAddress.val(),
			"address2": view.inputCustomerInvoiceAddress.val(),
			"prepaid": view.inputPrepaidAmount.val(),
			"invoiceList": invoiceList
		});
	}
	var invoiceMap = {}; $.each(invoiceList, function(i,o){ invoiceMap[o.invoiceNo] = true });
	$.each((window["invoiceSelectedList"] || {}), function(i,v) {
		if (invoiceMap[v.invoiceNo]) return;
		invoiceList.push($.extend(v, { "bankCode": view.inputBankCode.val(), "bankName": view.inputBankName.val(), "bankDate": view.inputBankDate.val(), "bankBrnh": view.inputBankBrnh.val(), "bankRefNo": view.inputBankRefNo.val(), "bankAcCd": view.inputBankAcCd.val(), "bankAcNo": view.inputBankAcNo.val(), "bankAmnt": view.inputBankAmnt.val(), "bankDeduct": 0, "bankRemark": view.inputBankRemark.val() }));
	});
	return billingList;
}
    var $table = $('#myTable');

    $table.on('expand-row.bs.table', function (e, index, row, $detail) {
        var res = $("#desc" + index).html();
        $detail.html(res);
    });
    $(document).ready(function () {
        $("#status_print").hide();
    });
    function submit_payment() {
        $("#status_print").show();
        setTimeout(' $("#status_print").hide()', 3000);
    }
    
    function detailFormatter(val, row, ind) {
    	return    	'<table class="table table-striped table-bordered">'+
				    	'<thead>'+
				    		'<tr>'+
				    		'<th class="text-center">รหัสผลิตภัณฑ์</th>'+
				    		'<th class="text-center">ชื่อผลิตภัณฑ์</th>'+
				    		'<th class="text-center">ผลิตภัณฑ์ย่อย</th>'+
				    		'<th class="text-center">ชื่อผลิตภัณฑ์ย่อย</th>'+
				    		'<th class="text-center">ประเภทรายได้</th>'+
				    		'<th class="text-center">จำนวนเงิน</th>'+
				    		'</tr>'+
				    	'</thead>'+
				    	'<tbody>'+
				    		'<tr><td class="text-center">102010403</td><td>บ.INMARSAT</td><td class="text-center">3</td><td>บ.INMARSAT-C</td><td>01</td><td class="text-right">1,000.00</td></tr>'+
				    	'</tbody>'+
			    	'</table>';	
    }
</script>