<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="resources/bootstrap-table/src/bootstrap-table.css" rel="stylesheet" type="text/css"/>
        <link href="resources/custom.css" rel="stylesheet" type="text/css"/>
        <script src="resources/jquery.min.js" type="text/javascript"></script>
        <script src="resources/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="resources/bootstrap-table/src/bootstrap-table.js"></script>
        <script src="resources/custom.js" type="text/javascript"></script>
    </head>
    <body>
        <header class="header_page"></header>
        <section class="container-fluid menu">
            <!-- <button class="menu-toggle btn btn-sm btn-default" style="margin-bottom: 5px"><span class="glyphicon glyphicon-step-forward"></span></button> -->
            <%-- <%@include  file="menu.jsp" %> --%>
            <div class="row">
                <div class="col-md-12 tab-modefile">
                    <ol class="breadcrumb"><li><i>ประวัติการรับชำระค่าใช้บริการ</i></li></ol>
					<div id="mainMessageDialog"></div>
                    <ul class="nav nav-tabs" role="tablist">
					<li role="presentation" class="active"><a aria-controls="tab_cus" role="tab" data-toggle="tab"><span class="glyphicon glyphicon-filter"></span> ค้นหาข้อมูล</a></li>
					</ul>
					<div class="panel panel-default panal-radius">
						<div class="panel-body">
							<div class="row">
								<div class="col-md-12">
									<div class="form-horizontal">
										<div class="form-group">
											<label class="control-label col-sm-2">เลขที่ลูกค้า(Billing Account):</label>
											<div class="col-sm-2"><input class="form-control" id="billNo"></div>
											<label class="control-label col-sm-2">Barcode / QRcode :</label>
											<div class="col-sm-2"><input class="form-control" id="barcode"></div>
											<div class="col-sm-4">
												<a id="search" class="btn btn-primary"><span class="glyphicon glyphicon-search"></span> ค้นหา</a> &nbsp;&nbsp;
												<a id="advanceSearch" class="btn btn-success" data-toggle="modal" href="#advanceSearchDialog"><span class="glyphicon glyphicon-zoom-in"></span> ค้นหาเพิ่มเติม</a>
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
                <div class="col-md-12 tab-modefile">
                    <ul id="customerInfoTab" class="nav nav-tabs" role="tablist">
                        <li role="presentation" class="active"><a href="#tab_cus" aria-controls="tab_cus" role="tab" data-toggle="tab"><span class="glyphicon glyphicon-user"></span> ข้อมูลลูกค้า</a></li>
                        <li role="presentation"><a href="#sub_script" aria-controls="sub_script" role="tab" data-toggle="tab"><span class="glyphicon glyphicon-list"></span> Subscription</a></li>
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
                                                    <div class="col-sm-2"><input id="custNo" class="form-control" disabled="disabled"></div>
                                                    <label class="control-label col-sm-1">ชื่อลูกค้า :</label>
                                                    <div class="col-sm-7"><input id="custName" class="form-control" disabled></div>
                                                </div>

                                                <div class="form-group">
                                                    <label class="control-label col-sm-2" >Tax ID :</label>
                                                    <div class="col-sm-2"><input id="taxNo" class="form-control" disabled></div>
                                                    <label class="control-label col-sm-1" >สาขา :</label>
                                                    <div class="col-sm-2"><input id="branch" class="form-control" disabled></div>
                                                    <label class="control-label col-sm-3" >หน่วยงานติดตามหนี้ :</label>
                                                    <div class="col-sm-2"><input id="collectUnit" class="form-control text-right" disabled></div>
                                                </div>

                                                <div class="form-group">
                                                    <label class="control-label col-sm-2">กลุ่มผู้ใช้บริการ :</label>
                                                    <div class="col-sm-2">
                                                        <select class="form-control" disabled>
                                                            <option>ธุรกิจทั่วไป</option>
                                                            <option>บุคคลธรรมดา</option>
                                                        </select>
                                                    </div>
                                                    <label class="control-label col-sm-2"></label>
                                                    <div class="col-sm-2"></div>
                                                    <label class="control-label col-sm-2">ยอดค้างชำระ (รวม VAT) :</label>
                                                    <div class="col-sm-2"><input id="custInfoInputAccruedAmount" class="form-control text-right" disabled></div>
                                                </div>

                                                <div class="form-group">
                                                    <label class="control-label col-sm-2" >Billing Group :</label>
                                                    <div class="col-sm-2"><input id="billGroup" class="form-control" disabled></div>
                                                    <label class="control-label col-sm-2" ></label>
                                                    <div class="col-sm-2"> </div>
                                                    <label class="control-label col-sm-2" >ยอดชำระล่วงหน้า :</label>
                                                    <div class="col-sm-2"><input id="custInfoInputAdvancedAmount" class="form-control" disabled></div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label col-sm-2" >สถานะลูกค้า :</label>
                                                    <div class="col-sm-2"><input id="custInfoInputStatus" class="form-control" value="Active" disabled></div>
                                                    <label class="control-label col-sm-2" ></label>
                                                    <div class="col-sm-2"> </div>
                                                    <label class="control-label col-sm-2" >สกุลเงิน :</label>
                                                    <div class="col-sm-2"><input id="custInfoInputCurrencyCode" class="form-control" disabled></div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label col-sm-2" >หมายเหตุ :</label>
                                                    <div class="col-sm-6"><input class="form-control" disabled></div>
                                                    <label class="control-label col-sm-2" >VAT Rate :</label>
                                                    <div class="col-sm-2"><input id="custInfoInputVatRate" class="form-control text-right" disabled></div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12 tab-modefile">
                                            <ul class="nav nav-tabs" role="tablist">
                                                <li role="presentation" class="active"><a href="#tab1" aria-controls="tab1" role="tab" data-toggle="tab"><span class="glyphicon glyphicon-home"></span> ที่อยู่สำหรับใบเสร็จรับเงิน</a></li>
                                                <li role="presentation"><a href="#tab2" aria-controls="tab2" role="tab" data-toggle="tab"><span class="glyphicon glyphicon-home"></span> ที่อยู่สำหรับใบแจ้งค่าใช้บริการ</a></li>
                                                <li role="presentation" class="">
													<div class="checkbox"><label> &nbsp;&nbsp;<input id="changeReceiptAddress" type="checkbox" disabled>เปลี่ยนแปลงที่อยู่ในใบเสร็จรับเงิน/ใบกำกับภาษี</label></div>
												</li>
												<li role="presentation" class="">
													<div class="checkbox" style="margin-left: 20px"><label> &nbsp;<input id="splitReceiptDocument" type="checkbox" disabled>แยกใบเสร็จรับเงิน/ใบกำกับภาษีตามใบแจ้งค่าใช้บริการ</label></div>
												</li>
                                            </ul>
                                            <div class="tab-content">
                                                <div role="tabpanel" class="tab-pane active" id="tab1"><textarea id="receiptAddress" class="form-control textarea-tab" readonly></textarea> </div>
                                                <div role="tabpanel" class="tab-pane" id="tab2"><textarea id="invoiceAddress" class="form-control textarea-tab" readonly></textarea></div>
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
            <div id="invoicePanel" class="hide row">
                <div class="col-md-12 tab-modefile">
                    <ul id="invoiceDetailsTab" class="nav nav-tabs" role="tablist">
                        <li role="presentation"><a href="#tab-2-1" aria-controls="tab-2-1" role="tab" data-toggle="tab"><span class="glyphicon glyphicon-briefcase"></span> รายการใบแจ้งค่าใช้บริการ</a></li>
                        <li role="presentation" class="active"><a href="#tab-2-2" aria-controls="tab-2-2" role="tab" data-toggle="tab"><span class="glyphicon glyphicon-th-list"></span> ประวัติการรับชำระ</a></li>
                        <li role="presentation"><a href="#tab-2-3" aria-controls="tab-2-3" role="tab" data-toggle="tab"><span class="glyphicon glyphicon-tasks"></span> รายการเปลี่ยนแปลง</a></li>
                    </ul>
                    <div class="tab-content">
                        <div role="tabpanel" class="tab-pane" id="tab-2-1">
                            <table id="invoiceList" data-toggle="table" data-detail-view="true" data-detail-formatter="detailFormatter">
                                <thead>
                                    <tr>
                                        <th data-formatter="runningFormatter">#</th>
										<th data-field="invoiceNo" data-sortable="true">เลขที่ใบแจ้ง<br>ค่าใช้บริการ</th>
										<th data-field="issueDt" data-sortable="true">วันจัดทำใบแจ้ง<br>ค่าใช้บริการ</th>
										<th data-field="dueDt" data-sortable="true" data-align="right">วันครบ<br>กำหนด</th>
										<th data-field="charge" data-sortable="true" data-align="right" data-formatter="numberFormatter">ยอดก่อน<br>ภาษี</th>
										<th data-field="discount" data-sortable="true" data-align="right">เงิน<br>ส่วนลด</th>
										<th data-field="vat" data-sortable="true" data-align="right" data-formatter="numberFormatter">ภาษีมูลค่า<br>เพิ่ม</th>
										<th data-field="totalCharge" data-sortable="true" data-align="right" data-formatter="numberFormatter">ยอดเงิน<br>รวมภาษี</th>
										<th data-field="balanceDue" data-sortable="true" data-align="right" data-formatter="numberFormatter">จำนวนเงิน<br>คงค้าง</th>
										<th data-field="totalPaid" data-sortable="true" data-align="right" data-formatter="numberFormatter">ยอดเงิน<br>ที่ต้องชำระ</th>
										<th data-field="withholdingTax" data-sortable="true">ภาษีหัก<br>ณ ที่จ่าย</th>
										<th data-field="billCycle">รอบการ<br>ใช้งาน</th>
										<th data-field="status" data-sortable="true">สถานะ</th>
                                    </tr>
                                </thead>
                            </table>
                        </div>
                        <div role="tabpanel" class="tab-pane active" id="tab-2-2">
                            <table id="historyList" data-row-style="rowStyle" data-toggle="table">
                              <thead>
									<tr>
										<th data-formatter="runningFormatter">#</th>
										<th data-field="receiptDt" data-align="center">วันที่ออกใบเสร็จรับเงิน</th>
										<th data-field="receiptNo" data-align="center">เลขที่ใบเสร็จรับเงิน</th>
										<th data-field="branch">สถานที่รับชำระ</th>
										<th data-field="user">ผู้รับชำระ</th>
										<th data-field="invoiceNo">เลขที่ใบแจ้ง<br>ค่าใช้บริการ</th>
										<th data-field="billCycle" data-align="center">รอบการใช้งาน</th>
										<th data-field="payMethod">วิธีการรับชำระ</th>
										<th data-field="totalCharge" data-align="right" data-formatter="numberFormatter">ยอดเงินตาม<br>ใบแจ้งค่าบริการ</th>
										<th data-field="totalPaid" data-align="right" data-formatter="numberFormatter">ยอดชำระ</th>
										<th data-field="status">สถานะ</th>
									</tr>
								</thead>
                            </table>
                        </div>
                        <div role="tabpanel" class="tab-pane" id="tab-2-3">
                            <table id="changeList" data-toggle="table">
                                <thead>
                                    <tr>
										<th data-field="paidDt">วันที่รับชำระเงิน</th>
										<th data-field="invoiceNo">เลขที่ใบแจ้ง<br>ค่าใช้บริการ</th>
										<th data-field="receiptNo">เลขที่ใบเสร็จรับเงิน</th>
										<th data-field="vat">ภาษีมูลค่าเพิ่ม</th>
										<th data-field="totalCharge">รวมเงิน</th>
										<th data-field="processedDt">วันที่ประมวลผล</th>
										<th data-field="branch">สถานที่รับชำระเงิน</th>
										<th data-field="user">ผู้รับชำระ</th>
										<th data-field="billCycle">รอบการใช้งาน</th>
										<th data-field="status">สถานะ</th>
										<th data-field="trackingId">Tracking ID</th>
                                    </tr>
                                </thead>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </section>

       <div id="advanceSearchDialog" class="modal fade" role="dialog">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">ค้นหาลูกค้า</h4>
                </div>
                <div class="modal-body">
					<div id="advanceSearchMessageDialog"></div>
					<div class="tab-modefile">
						<ul class="nav nav-tabs" role="tablist">
							<li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">เลขที่ใบแจ้งค่าบริการ</a></li>
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
													<span class="input-group-btn"><a id="advSrcBillNoBtn" class="btn btn-info"><span class="glyphicon glyphicon-search"></span> ค้นหา</a></span>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-md-12">
												<table id="advSrcBillNoResultList" data-toggle="table" data-pagination="true" data-page-size="5" data-page-list="[5, 10, 20, 50, 100, 200]" data-cache="false">
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
														<span class="input-group-btn"><a id="advSrcSvcNoBtn" class="btn btn-info"><span class="glyphicon glyphicon-search"></span> ค้นหา</a></span>
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
												<table id="advSrcSvcNoResultList" name="tableproperty" class="table table-hover" data-toggle="table" data-pagination="true" data-page-size="5" data-page-list="[5, 10, 20, 50, 100, 200]" data-cache="false">
													<thead>
														<tr>
															<th data-formatter="runningFormatter">#</th>
															<th data-field="propOne">หมายเลขบริการ</th>
															<th data-field="acctNo">เลขที่ลูกค้า</th>
															<th data-field="acctName">ชื่อลูกค้า</th>
															<th data-field="propLabel">ประเภทบริการ</th>
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
												<table id="advSrcCusNoResultList" class="table table-hover" data-toggle="table" data-pagination="true" data-page-size="5" data-page-list="[5, 10, 20, 50, 100, 200]" data-cache="false">
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
					<i class="pull-right"><span class="glyphicon glyphicon-filter"></span>
						สามารถใช้เครื่องหมาย ? แทนหนึ่งตัวอักษร หรือ *
						แทนหลายตัวอักษรในการค้นหา (กรุณาป้อนข้อมูลที่ต้องการค้นหาอย่างน้อย 4 ตัวอักษร)
						</i><br>
				</div>
            </div>
        </div>
    </div>

        <div id="status_print" class="hidden">
            <div style="float: none; margin: 0 auto;">
                <div class="panel panel-default vertical-center panel_status_print ">
                    <div class="panel-heading">พิมพ์</div>
                    <div class="panel-body">
                        <h4> กำลังพิมพ์ใบเสร็จรับเงิน...</h4>
                    </div>
                </div>
            </div>
        </div>


</body>
<script type="text/javascript">
var view = (function($){
	var self = this, defaultErrorMessage = "An error occurred but there is no message response.";
	self.button = new(function(){
		var that = this;
		that.advanceSearch = new Button("#advanceSearch");
		that.advSearchSelectedBillNo = new Button("#advSearchSelectedBillNo");
		that.search = new Button("#search", "../service/bill-profiles/search/no");
		that.advSearchInvoiceSubmit = new Button("#advSearchInvoiceSubmit");
		that.advSearchServiceSubmit = new Button("#advSearchServiceSubmit");
		that.advSearchCustomerSubmit = new Button("#advSearchCustomerSubmit");
		function Button(el, url, inp, validate) {
			var obj = this, inputs = inp, val = (validate || function(){ return true }), badge, done = function(res) { console.log(res) };
			obj.el = el;
			obj.elem = $(el);
			obj.inputs = function(object) { inputs = object; return this };
			obj.click = function(func) { this.elem.click(func) };
			obj.hide = function() { this.elem.addClass("hide"); return this };
			obj.show = function() { this.elem.removeClass("hide"); return this };
			obj.disable = function(flag) { if (flag == null || flag) this.elem.addClass("disabled"); else this.elem.removeClass("disabled"); return this };
			obj.enable = function() { this.elem.removeClass("disabled"); return this };
			obj.validate = function(func) { val = func; return obj }
			obj.done = function(func) { done = func };
			obj.badge = function(val) { if (badge) badge.text(val) };
			function eventHandler (e) {
				if (!val(inputs)) return;
				if (!url) return;
				obj.elem.button("loading");
				var params = {}; $.each(inputs, function(k,v){
					if ($.type(v) === "object" && v.val) params[k] = v.val();
					else if ($.type(v) === "array")      params[k] = v.join("|");
					else if ($.type(v) === "function")   params[k] = v();
					else                                 params[k] = v;
				});
				$.get(url, params, function(res){ obj.elem.button("reset"); done(res) });
			}
			if ((badge = this.elem.next()).length == 0) badge = null;
			obj.elem.click(eventHandler);
		}
	});
	self.dialog = new(function(){
		var that = this;
		that.mainMessageDialog = new Message("#mainMessageDialog");
		that.advSearchMsgDialog = new Message("#advSearchMsgDialog");
		that.advanceSearchDialog = new Modal("#advanceSearchDialog");
		that.customerPanel = new Message("#customerPanel");
		that.invoicePanel = new Message("#invoicePanel");
		function Message(el) {
			var obj = this;
			obj.el = el;
			obj.elem = $(el);
			obj.hide = function() { obj.elem.addClass("hide"); return obj };
			obj.show = function(flag) { if (flag == null || flag) obj.elem.removeClass("hide"); else obj.hide(); return obj };
			obj.clear = function() { obj.elem.find("*").remove(); obj.hide(); return obj };
			obj.message = function(arr, cls) { $.each(arr, function(i,m) { obj.elem.append('<div class="'+ cls +'">'+ m +'<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button></div>') }); return obj };
			obj.error = function(arr) { return obj.message(arr, "alert alert-danger") };
			obj.warn = function(arr) { return obj.message(arr, "alert alert-warning") };
			obj.success = function(arr) { return obj.message(arr, "alert alert-success") };
		}
		function Modal(el) {
			this.el = el;
			this.elem = $(el);
			this.hide = function() { this.elem.modal("hide") };
			this.show = function() { this.elem.modal("show") };
		}
		function Panel(el) {
			this.el = el;
			this.elem = $(el);
			this.hide = function() { this.elem.addClass("hide") };
			this.show = function() { this.elem.removeClass("hide") };
		}
	});
	self.checkbox = new(function(){
		var that = this;
		that.advSearchServiceProp = new CheckBox("[name=advSearchServiceProp]")
		function CheckBox(el) {
			var obj = this;
			obj.el = el;
			obj.elem = $(el);
			obj.contains = function(val) { return $.inArray(val, obj.val()) > -1 };
			obj.val = function() { return obj.elem.filter(":checked").map(function(i,o){ return o.value }) };
		}
	});
	self.input = new(function(){
		var that = this;
		that.advSearchInvoiceNo = new Input("#advSearchInvoiceNo");
		that.advSearchServiceNo = new Input("#advSearchServiceNo");
		that.advSearchServiceLabel = new DropDown("#advSearchServiceLabel").data([{ key: "Inmarsat Name", value: "Inmarsat Name"},{ key: "SLA", value: "SLA"},{ key: "Bundle Service Description", value: "Bundle Service Description"},{ key: "Bundle Service Flag", value: "Bundle Service Flag"},{ key: "Owner", value: "Owner"},{ key: "Brand", value: "Brand"},{ key: "Market Name", value: "Market Name"}]);
		that.advSearchCustomerNo = new Input("#advSearchCustomerNo");
		that.advSearchTaxNo = new Input("#advSearchTaxNo");
		that.advSearchCustFirstName = new Input("#advSearchCustFirstName");
		that.advSearchCustLastName = new Input("#advSearchCustLastName");
		that.advSearchOrgName = new Input("#advSearchOrgName");
		that.billNo = new Input("#billNo", 18);
		that.barcode = new Input("#barcode", 62, { "billNo": [16, 34], "invoiceNo": [34, 52] });
		that.custNo = new Input("#custNo");
		that.custName = new Input("#custName");
		that.taxNo = new Input("#taxNo");
		that.branch = new Input("#branch");
		that.collectUnit = new Input("#collectUnit");
		that.accruedAmount = new NumberInput("#accruedAmount");
		that.billGroup = new Input("#billGroup");
		that.advancedAmount = new NumberInput("#advancedAmount");
		that.receiptAddress = new Input("#receiptAddress");
		that.invoiceAddress = new Input("#invoiceAddress");
		that.val = function() { if (arguments.length == 1) { $.each(arguments[0],function(k,v){ $("#"+ k).val(v) }) } };
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
	});
	self.tab = new(function(){
		var that = this;
		that.advanceSearchTab = new Tab("#advanceSearchTab");
		that.customerInfoTab = new Tab("#customerInfoTab");
		that.invoiceDetailsTab = new Tab("#invoiceDetailsTab");
		function Tab(el) {
			var obj = this, index = 0, change = function(e){ console.log(e) }, initEvts = [];
			obj.el = el;
			obj.elem = $(el);
			obj.index = function() { if (arguments.length == 1) { index = arguments[0]; obj.elem.find("li").removeClass("active").find("a").eq(index).click(); return this } return index }
			obj.show = function(ind) { obj.elem.find("a").eq(ind).tab("show"); return this; }
			obj.change = function(func) { change = func; return this }
			obj.init = function(ind, evt) { initEvts[ind] = evt; return obj }
			obj.elem.find("a").each(function(i,o){ $(o).data("index", i) });
			obj.elem.find("li").each(function(i,o){ if ($(o).hasClass("active")) index = i; initEvts.push(function(){ console.log("index: "+ i) }) });
			obj.elem.on("show.bs.tab", function(e){ index = $(e.target).data("index"); change(e); initEvts[index]() });
		}
	});
	self.table = new(function(){
		var that = this;
		that.subscriptionList = new Table("#subscriptionList")
		that.invoiceList = new BootstrapTable("#invoiceList");
		that.historyList = new BootstrapTable("#historyList");
		that.changeList = new BootstrapTable("#changeList");
		that.advSearchInvoiceList = new Table("#advSearchInvoiceList");
		that.advSearchServiceList = new Table("#advSearchServiceList");
		that.advSearchCustomerList = new Table("#advSearchCustomerList");
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
					+ $.map(array, function(v,i){
						var field = headers[i].field, value = v;
						if (headers[i].runningNo) value = (obj.size() + 1);
						else if (headers[i].numberFormat) { value = !$.isNumeric(v) ? "0.00" : parseFloat(v, 10).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,'); }
						else if (headers[i].checkbox) value = replace(checkboxHtml, null, field, i, v);
						else if (headers[i].radio) value = replace(radioHtml, null, field, i, v);
						else if (headers[i].input) value = replace(inputHtml, 'style="width:100%;margin:-4px 0;text-align:'+ (headers[i].align || "left") +'"', field, i, v);
						return '<td><div style="'+ (headers[i].align ? "text-align:"+ headers[i].align : "") +'">'+ value +'</div></td>' }).join("")
					+ (!showRemove ? "" : '<td style="width:40px;text-align:center"><a href="#" class="delList"><span class="glyphicon glyphicon-trash"></span></a></td>') 
				+"</tr>"); return obj
			};
			obj.find = function(key, cri) { return obj.elem.find("tbody tr").filter("["+ key +"="+ cri +"]") }
			obj.clear = function() { obj.elem.find("tbody tr").remove(); return obj }
			obj.remove = function(index) { this.elem.find("tbody tr").eq(index).remove(); $.each(headers,function(i,o){ if (o.runningNo) reorder(i) }); }
			obj.data = function() { var data = [];
				if (arguments.length != 1) { var rows = obj.elem.find("tbody tr");
					for (var i = 0, m = rows.length; i < m; i++) { var row = []; for (var j = 0, n = rows[i].children.length; j < n; j++) { row.push(extract(headers[j], rows[i].children[j])) } data.push(row) }
					return data;
				} for (var i = 0, m = (data = arguments[0]).length; i < m; i++) { obj.insert(data[i]); } return obj
			};
			obj.size = function() { return obj.elem.find("tbody tr").length };
			obj.sum = function(index) { var sum = 0; this.elem.find("tbody tr").each(function(i, row){ var val = $(row).find("td").eq(index).text(); sum += (isNaN(val) ? 0 : parseFloat(val, 10)) }); return sum }
			$(obj.el).on("click", ".delList", function(){ $(this).parent().parent().remove(); reorder(); onDel(obj.data()) });
			obj.elem.find("thead th").each(function(i,o){ var elem = $(o);
				headers.push({ "field": elem.data("field"), "align": $.trim(elem.data("align")), "runningNo": elem.data("runningNo") === true, "numberFormat": elem.data("numberFormat") === true, "checkbox": elem.data("checkbox") === true, "radio": elem.data("radio") === true, "input": elem.data("input") === true })
			}); function replace(str, style, field, index, value){ var s = str; if (style) s = s.replace("\{style\}", style); return s.replace("\{field\}", $.trim(field)).replace("\{index\}", index).replace("\{value\}", value) }
			function extract(prop, col) { if (prop.checkbox) { var elem = col.children[0].children[0]; return !elem.checked ? "" : elem.value } else if (prop.radio) { var elem = col.children[0].children[0]; return !elem.checked ? "" : elem.value } else if (prop.input) { var elem = col.children[0].children[0]; return elem.value } return col.children[0].innerText }
			if(obj.body.length < 1) { obj.elem.append("<tbody></tbody>"); obj.body = obj.elem.find("tbody") }
		}
		function BootstrapTable(el) {
			var obj = this, checkEvt = function(e){ console.log(e) }, uncheckEvt = checkEvt;
			obj.el = el;
			obj.elem = $(el);
			obj.clear = function() { obj.elem.bootstrapTable("removeAll"); return obj }
			obj.showLoad = function() { this.elem.bootstrapTable("showLoading"); return this };
			obj.hideLoad = function() { this.elem.bootstrapTable("hideLoading"); return this };
			obj.data = function() { if (arguments.length == 1) { this.elem.bootstrapTable("load", arguments[0]); } return this.elem.bootstrapTable("getData") };
			obj.selected = function() { return this.elem.bootstrapTable("getSelections") };
			obj.sum = function(isAll, colName) { var sum = 0; $.each(this.elem.bootstrapTable(isAll ? "getData" : "getSelections"), function(i,o){ sum += (o[colName] || 0) }); return sum };
			obj.expand = function() { obj.elem.find(".detail-icon i.icon-plus").click(); return this };
			obj.collapse = function() { obj.elem.find(".detail-icon i.icon-minus").click(); return this };
			obj.checkboxEvent = function(func) { this.elem.on("check.bs.table uncheck.bs.table check-all.bs.table uncheck-all.bs.table", func); return this };
			obj.checkEvent = function(func) { checkEvt = func; return this };
			obj.uncheckEvent = function(func) { uncheckEvt = func; return this };
			obj.checkboxEvent(function(e) { if (e.type === "check") checkEvt(e); if (e.type === "uncheck") uncheckEvt(e) });
		}
		//// AUTOMATIC REGISTER FORMATTER FUNCTION ////
		window.runningFormatter = function(val, row, ind) { return ind + 1 }
		window.numberFormatter = function(val, row, ind) { return !$.isNumeric(val) ? "0.00" : parseFloat(val, 10).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,').replace(/\.00$/g, "") }
	});
	function Input(el, maxLen, propPos) {
		var obj = this;
		obj.el = el;
		obj.elem = $(el);
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
	self.utils = {
		guid: function(){ function r() { return Math.floor((Math.random() + 1) * 0x10000).toString(16).substring(1) } return r() + r() +"-"+ r() +"-"+ r() +"-"+ r() +"-"+ r() + r() + r() },
		numberFormat: function(num, dec) { return $.type(num) !== "number" ? "0.00" : num.toFixed(dec || 2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,'); },
		dateFormat: function() { var obj = arguments[0]; if ($.type(obj) == "string" && /[0-9]{2}.[0-9]{2}.[0-9]{4}/g) { return obj } else if ($.isNumeric(obj)) { var dt = new Date(obj), dd = dt.getDate().toString(), mm = (dt.getMonth() + 1).toString(), yyyy = dt.getFullYear().toString(); return (dd[1]?"":"0") + dd +"/"+ (mm[1]?"":"0") + mm +"/"+ yyyy } else if ($.type == "date") { return "" } return "" },
		dateTimeFormat: function() { var obj = arguments[0], dt = $.type(obj) != "date" ? ($.isNumeric(obj) ? new Date(obj) : new Date()) : new Date(), dd = dt.getDate().toString(), MM = (dt.getMonth() + 1).toString(), yyyy = dt.getFullYear().toString(), hh = dt.getHours().toString(), mm = dt.getMinutes().toString(), ss = dt.getSeconds().toString(); return (dd[1]?"":"0") + dd +"/"+ (MM[1]?"":"0") + MM +"/"+ yyyy +" "+ (hh[1]?"":"0") + hh +":"+ (mm[1]?"":"0") + mm +":"+ (ss[1]?"":"0") + ss },
		queryString: function() { var pos = location.href.indexOf("?"), obj = {}; if (pos < 0) return obj; var params = location.href.slice(pos + 1).split("&"); for (var i = 0, m = params.length; i < m; i++) { pos = params[i].indexOf("="); if (pos < 0) { obj[params[i]] = true; continue; } obj[params[i].substring(0, pos)] = params[i].substring(pos + 1) } return obj },
		window: function(windowName, url, side) { window.windowOpened = (window.windowOpened || {}); var screenProp = ""; if (side == "left") { screenProp = "left=0,top=0,width="+ (screen.width/2) +",height="+ (screen.height-100) } else if (side == "right") { screenProp = "left="+ (screen.width/2-40) +",top=0,width="+ (screen.width/2+40) +",height="+ (screen.height-100) } window.windowOpened[windowName] = window.open(url, windowName, "menubar=no,scrollbars=no,resizable=0,"+ screenProp, false); return window.windowOpened }
	};
	self.contextPath = '${pageContext.request.contextPath}/';
	self.custInfoInputAccruedAmount = new NumberInput("#custInfoInputAccruedAmount");
	self.custInfoInputAdvancedAmount = new NumberInput("#custInfoInputAdvancedAmount");
	self.custInfoInputStatus = new Input("#custInfoInputStatus");
	self.custInfoInputCurrencyCode = new Input("#custInfoInputCurrencyCode");
	self.custInfoInputVatRate = new Input("#custInfoInputVatRate");
	return this;
})(jQuery);

view.button.search.validate(function() {
	var isBarcode = !view.input.barcode.empty()
	var billNo = isBarcode ? view.input.barcode.get("billNo") : view.input.billNo.val();
	view.dialog.mainMessageDialog.clear();
	if (!isBarcode && billNo.length < 4) {
		view.dialog.mainMessageDialog.error(["Please fill in 'Billing Account' at least 4 characters."]).show();
		return false;
	} else if (isBarcode && billNo.length < 4) {
		view.dialog.mainMessageDialog.error(["Please fill in valid 'Barcode / QRCode' to input."]).show();
		return false;
	}
	view.button.search.inputs({ "no": (window.billNoSelected = billNo) });
	return true;
}).done(function(res) {
	var profile = res;
	view.dialog.mainMessageDialog.clear();
	view.dialog.customerPanel.show();
	view.dialog.invoicePanel.show();
	view.input.custNo.val(profile.no);
	view.input.custName.val(profile.customerAccountName);
	view.input.taxNo.val(profile.taxRegisterNo);
	view.input.branch.val(profile.branchId);
	view.input.collectUnit.val(profile.taxRegisterNo);
	view.input.accruedAmount.val(0);
	view.input.billGroup.val(profile.billGroup);
	view.input.advancedAmount.val(0);
	view.table.historyList.showLoad();
	$.get("../service/bill-addrs/search/no", { "no": billNoSelected }, function(res){
		if (!res._embedded.billAddresses || res._embedded.billAddresses.length < 1) { return; }
		var addr = res._embedded.billAddresses[0];
		view.input.receiptAddress.val($.trim(addr.vatAddrLine1) +" "+ $.trim(addr.vatAddrLine2) +" "+ $.trim(addr.vatAddrLine3) +" "+ $.trim(addr.vatAddrLine4) +" "+ $.trim(addr.vatKhetAmphur) +" "+ $.trim(addr.vatProvince) +" "+ $.trim(addr.vatPostCode));
		view.input.invoiceAddress.val($.trim(addr.billAddrLine1) +" "+ $.trim(addr.billAddrLine2) +" "+ $.trim(addr.billAddrLine3) +" "+ $.trim(addr.billAddrLine4) +" "+ $.trim(addr.billKhetAmphur) +" "+ $.trim(addr.billProvince) +" "+ $.trim(addr.billPostCode));
	});
	$.get("../findPaymentHistory.json", { "billingNo": billNoSelected }, function(res){
		view.table.historyList.hideLoad().data($.map((res && res.data ? res.data : []), HistoryListRowMapper));
	});
	view.table.invoiceList.showLoad();
	$.get("../findInvoiceList.json", { "no": billNoSelected }, function(res) {
		view.table.invoiceList.hideLoad();
		if (!res || !res.data || res.data.length < 1) { return; }
		view.table.invoiceList.data($.map(res.data, invoiceListMapper))
		var accruedAmount = 0, advancedAmount = 0; $.each(res.data, function(i,o){ accruedAmount += parseFloat(o.balanceDue, 10); advancedAmount += parseFloat(o.totalPaid, 10); });
		view.custInfoInputAccruedAmount.val(accruedAmount);
		view.custInfoInputAdvancedAmount.val(advancedAmount);
		view.custInfoInputCurrencyCode.val(res.data[0].currencyCode);
		view.custInfoInputVatRate.val(res.data[0].taxRate);
		view.custInfoInputStatus.val("ปกติ");
	});
});
view.button.advanceSearch.click(function() {
	view.dialog.advSearchMsgDialog.clear();
	view.table.advSearchInvoiceList.clear();
	view.table.advSearchServiceList.clear();
	view.table.advSearchCustomerList.clear();
});
view.button.advSearchSelectedBillNo.click(function() {
	var tabInd = view.tab.advanceSearchTab.index(), bills;
	if (tabInd === 0) {
		bills = $.map(view.table.advSearchInvoiceList.data(), function(o,i){ return !o[0] ? null : o[0] });
	} else if (tabInd === 1) {
		bills = $.map(view.table.advSearchServiceList.data(), function(o,i){ return !o[0] ? null : o[0] });
	} else if (tabInd === 2) {
		bills = $.map(view.table.advSearchCustomerList.data(), function(o,i){ return !o[0] ? null : o[0] });
	}
	view.dialog.advSearchMsgDialog.clear();
	if (bills.length != 1) { view.dialog.advSearchMsgDialog.error(["Please select at least 1 record of the result list."]).show(); return }
	view.input.billNo.val(bills[0]);
	view.dialog.advanceSearchDialog.hide();
	view.button.search.elem.click();
});
view.button.advSearchInvoiceSubmit.click(function(res) {
	view.dialog.advSearchMsgDialog.clear();
	view.table.advSearchInvoiceList.showLoad();
	$.get("../service/bill-svcs/search/no", { "no": view.input.advSearchInvoiceNo.val() }, function(res){
		var data = []; $.map(res._embedded.billServices, function(o,i){
			data.push([ o.profile.no, o.profile.customer.no, o.profile.customer.fullName, o.propLabel, o.profile.billGroup,o.profile.no ])
		});
		view.table.advSearchInvoiceList.hideLoad().data(data);
	});
});
view.button.advSearchServiceSubmit.click(function() {
	var isProp1 = view.checkbox.advSearchServiceProp.contains("1");
	var isProp2 = view.checkbox.advSearchServiceProp.contains("2");
	var svcNo = view.input.advSearchServiceNo.val().replace("[\?\*]", "");
	view.dialog.advSearchMsgDialog.clear();
	if (svcNo.length < 4) {
		view.dialog.advSearchMsgDialog.error(["Please fill 'Service No' at least 4 characters."]).show();
		return;
	}
	view.table.advSearchServiceList.showLoad();
	function ResponseHandler(res) {
		var data = []; $.map(res._embedded.billServices, function(o,i){
			data.push([ o.propOne, o.profile.customer.no, o.profile.customer.fullName, o.propLabel, o.profile.billGroup, o.profile.no ])
		});
		view.table.advSearchServiceList.hideLoad().data(data);
	}
	if (isProp1 && isProp2) {
		$.get("../service/bill-svcs/search/both", { "label": view.input.advSearchServiceLabel.val(), "one": svcNo, "two": svcNo }, ResponseHandler);
	} else if (isProp1) {
		$.get("../service/bill-svcs/search/one", { "label": view.input.advSearchServiceLabel.val(), "one": svcNo }, ResponseHandler);
	} else if (isProp2) {
		$.get("../service/bill-svcs/search/two", { "label": view.input.advSearchServiceLabel.val(), "two": svcNo }, ResponseHandler);
	} else {
		view.table.advSearchServiceList.hideLoad();
		view.dialog.advSearchMsgDialog.error(["Please select 'Property1' or 'Property2' before proceed to search data."]).show();
	}
});
view.button.advSearchCustomerSubmit.click(function() {
	view.dialog.advSearchMsgDialog.clear();
	var url = "", params;
	if (!view.input.advSearchCustomerNo.empty())         { url = "../service/bill-profiles/search/custNo"; params = { "custNo": view.input.advSearchCustomerNo.val() } }
	else if (!view.input.advSearchTaxNo.empty()) { url = "../service/bill-profiles/search/tax"; params = { "tax": view.input.advSearchTaxNo.val() } }
	else if (!view.input.advSearchCustFirstName.empty() || !view.input.advSearchCustLastName.empty()) {
		url = "../service/bill-profiles/search/name"; params = { "firstName": view.input.advSearchCustFirstName.val(), "lastName": view.input.advSearchCustLastName.val() }
	}
	else if (!view.input.advSearchOrgName.empty())  { url = "../service/bill-profiles/search/org"; params = { "org": view.input.advSearchOrgName.val() } }
	else { view.dialog.advSearchMsgDialog.error(["Please fill in customer details at least 1 input."]).show(); return; }
	view.table.advSearchCustomerList.showLoad();
	$.get(url, params, function(res){
		var data = []; $.map(res._embedded.billProfiles, function(o,i){
			data.push([ o.no, o.customer.no, o.customer.fullName, o.customer.typeDesc, o.billGroup, o.no ])
		});
		view.table.advSearchCustomerList.hideLoad().data(data);
	});
});
view.tab.customerInfoTab.init(1, function(e) {
	view.table.subscriptionList.showLoad();
	$.get("../service/bill-svcs/search/no", { "no": billNoSelected }, function(res) {
		view.table.subscriptionList.hideLoad();
		if (!res || !res._embedded || !res._embedded.billServices || res._embedded.billServices.length < 1) { view.dialog.mainMessageDialog.error(["There is no result data."]).show(); return; }
		var data = []; $.each(res._embedded.billServices, function(i,o){ data.push(["-", o.propLabel, o.propOne, "Active"], false) });
		view.table.subscriptionList.data(data)
	});
});
view.tab.invoiceDetailsTab.init(0, function() {
});
view.tab.invoiceDetailsTab.init(1, function() {
	view.table.historyList.showLoad();
	$.get("../findPaymentHistory.json", { "billingNo": billNoSelected }, function(res) {
		view.table.historyList.hideLoad().data($.map((res && res.data ? res.data : []), HistoryListRowMapper));
	});
});
view.tab.invoiceDetailsTab.init(2, function() {
	view.table.changeList.showLoad();
	$.get("../findPaymentDetails.json", { "no": billNoSelected }, function(res) {
		view.table.changeList.hideLoad().data($.map((res && res.data ? res.data : []), function(o,i){
			return {
				 "paidDt": o
				,"invoiceNo": o
				,"receiptNo": o
				,"vat": o
				,"totalCharge": o
				,"processedDt": o
				,"branch": o
				,"user": o
				,"billCycle": o
				,"status": o
				,"trackingId": o
			};
		}));
	});
});

function invoiceListMapper(o) {
	return {
		 "invoiceNo": o.billNo
		,"accountNo": o.accountNo
		,"issueDt": view.utils.dateFormat(o.issueDate)
		,"dueDt": view.utils.dateFormat(o.dueDate)
		,"amount": parseFloat(o.amountBeforeTax, 10)
		,"charge": parseFloat(o.amountAfterTax, 10)
		,"discount": 0
		,"vat": parseFloat(o.taxRate, 10)
		,"totalCharge": parseFloat(o.amountAfterTax, 10)
		,"balanceDue": parseFloat(o.balanceDue, 10)
		,"totalPaid": parseFloat(o.totalDue, 10)
		,"withholdingTax": parseFloat(o.taxAmount, 10)
		,"billCycle": view.utils.dateFormat(o.chargeCycleFromDate) +" - "+ view.utils.dateFormat(o.chargeCycleToDate)
		,"billRefNo": o.billNo
		,"currencyCode": o.currencyCode
		,"status": "ปกติ"
	};
}
function HistoryListRowMapper(o,i) {
	return {
		 "receiptDt": view.utils.dateTimeFormat(o.receiptPrintDate)
		,"receiptNo": o.receiptNo
		,"branch": o.shopPaymentName.substring(0, o.shopPaymentName.lastIndexOf(" "))
		,"user": o.paymentReceiver
		,"invoiceNo": o.billRefNo
		,"billCycle": o.billCycle
		,"totalCharge": (o.billAmount || 0)
		,"payMethod": o.paymentMethod
		,"totalPaid": (o.transAmount || 0)
		,"status": o.status
	};
}
        var $table = $('#table_1');
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

        function detail_invoice(refno) {
            $.ajax({
                type: 'POST',
                url: 'detail_invoice.jsp',
                data: {refno: refno},
                success: function (data) {
                    //alert(data);
                    $("#show_detail_invoice_" + refno).prepend(data);
                    $("#show_detail_invoice_" + refno).removeClass("hidden");
                }
            });
        }
        function show_price(refno) {
            // alert(refno);
            if ($("#checkbox_" + refno).prop("checked")) {
                $("#text_price_" + refno).addClass("hidden");
                $("#input_price_" + refno).removeClass("hidden");
                $("#input_price_" + refno).focus();
            } else {
                $("#text_price_" + refno).removeClass("hidden");
                $("#input_price_" + refno).addClass("hidden");
            }
        }
        $("#btn_save_c").click(function () {
            //alert("ok");
            var dataSet = {
                c_Num: $("#c_Num").val(),
                c_IdBank: $("#c_IdBank").val(),
                c_Bank: $("#c_Bank").val(),
                c_Bank_branch: $("#c_Bank_branch").val(),
                c_Date: $("#c_Date").val(),
                c_money: $("#c_money").val()
            };
            $.ajax({
                type: 'POST',
                url: 'data_chack.jsp',
                data: dataSet,
                success: function (data) {
                    //alert(data);
                    $("#data_chack_show").prepend(data);
                    $("#c_Num").val("");
                    $("#c_IdBank").val("");
                    $("#c_Bank").val("");
                    $("#c_Bank_branch").val("");
                    $("#c_Date").val("");
                    $("#c_money").val("");
                }
            });
        });

        $("#TABTABLE1").on('shown.bs.tab', 'a[data-toggle="tab"]', function (e) {
//            alert("เย้ๆ");'
            var $tablemessages = $('#tablemessages');
//            $table1234.bootstrapTable("refresh", {url: "", cache: false});
            $tablemessages.bootstrapTable("resetView");
//            $table1234.bootstrapTable("refresh");
        });

        function CLICKON() {
            var $tablemessages = $('#tablemessages');
            var a = $('#txt_ba_code2').val();
            var b = $('#txt_tax_id2').val();
            var c = $('#txt_name2').val();
            var d = $('#txt_surname2').val();
            var e = $('#txt_cus_type2').val();
//            $table1234.bootstrapTable('load', 'Convert_json.jsp?master=pop_crm&get=' + a + '&get2=' + b + '&get3=' + c + '&get4=' + d + '&get5=' + e + '');
            $tablemessages.bootstrapTable("refresh", {url: "Convert_json.jsp?master=CRM_CALL_DATA&get=" + a + "&get2=" + b + "&get3=" + c + "&get4=" + d + "&get5=" + e + ""})
//        $table1234.bootstrapTable('load', 'Convert_json.jsp?master=pop_crm2&get=336421');
        }
        
        function CLICKON1() {
            var $tablehome = $('#tablehome');
            var a = $('#txt_billno').val();
            $tablehome.bootstrapTable("refresh", {url: "Convert_json.jsp?master=esb_RetrieveInvoiceAccountCodeInfo_Service&get=" + a + ""})
        }


        function link() {
            var hh = document.getElementById('txt_ba_code').value;
            location.href = './pay-service-charge.jsp?id=' + hh + '';
        }
        function GETLVALUE() {
            var $tablemessages = $('#tablemessages');
            var $table777 = $('#table777');
            var aaa = JSON.stringify($tablemessages.bootstrapTable('getSelections'));
            var CCC = aaa.replace("[", "").replace("]", "");
            var trans = JSON.parse(CCC);

            $("#txt_ba_code1").val(trans['cat_bill_acct_id']);
            $("#txt_ba_name1").val(trans['customer_account_name']);
            $("#txt_tax_id1").val(trans['tax_register_number']);
            $("#txt_cus_group1").val(trans['property_label']);
            $("#txt_billing_group1").val(trans['billing_group']);
//            $("#txt_vat1").val(trans['Tax']);
//            $("#address").val(trans['Address']);

//            trans['BA_Code'] = "800050144";//FIX ไว้เพราะ ลูกค้า Billing Provide ให้แค่ตัวนี้
            $table777.bootstrapTable("refresh", {url: "Convert_json.jsp?master=esb_RetrieveInvoiceHeader_Service&get=" + trans['cat_bill_acct_id'] + ""});

            $('#TABTABLE1').on('hidden.bs.modal', function () {
                $(this).find("input,textarea,select").val('').end();
                var $tablemessages = $('#tablemessages');
                $tablemessages.bootstrapTable("refresh", {url: "", cache: false});
                $tablemessages.bootstrapTable("resetView");

            }
            );
        }
</script>
</html>
