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
        <script src="resources/bootstrap-table/src/bootstrap-table.js" type="text/javascript"></script>
        <script src="resources/js/bootstrap-datepicker.min.js" type="text/javascript"></script>
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
                        <li><i>รับชำระค่าบริการ</i></li>
                        <li>ค้นหาข้อมูลลูกค้า</li>
                        <li>สรุปรายการรับชำระเงิน</li>
                        <li class="active">เลือกวิธีการรับชำระ</li>
                        <li>ผลการรับชำระ</li>
                    </ol>
                </div>
            </div>
            <div id="message"></div>
            <div id="mainMessageDialog"></div>
			<div id="summaryPanel" style="display: none">
			<ul class="list-inline pull-right list-menu-set">
				<li><a href="pay-service-charge.jsp?new"><span class="glyphicon glyphicon glyphicon-arrow-left"></span> กลับไปหน้าการรับชำระ</a></li>
			</ul>
			<div class="row">
				<div class="col-md-12">
					<div class="panel panel-success">
						<div class="panel-heading">ผลการรับชำระเงิน</div>
						<div class="panel-body">
							<table id="receiptList" data-row-style="rowStyle" data-toggle="table"
								data-detail-view="true" data-detail-formatter="detailFormatter"
								data-classes="table table-hover table-striped">
								<thead>
									<tr>
										<th data-align="center" data-formatter="runningFormatter">#</th>
										<th data-field="customerNo">เลขที่ลูกค้า</th>
										<th data-field="customerName">ชื่อลูกค้า</th>
										<th data-field="invoiceCount">จำนวนใบแจ้งค่าใช้บริการ</th>
										<th data-field="receiptNo">เลขที่ใบเสร็จรับเงิน/ใบกำกับภาษี</th>
										<th data-field="receiptAmount" data-align="right">จำนวนเงินที่รับชำระ</th>
										<th data-field="receiptStatus">สถานะการรับชำระ</th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
			</div>
			</div>
            <ul id="navigationPanel" class="list-inline pull-right list-menu-set">
                <li><a href="pay-service-charge_2.jsp" class="btn btn-link"><span class="glyphicon glyphicon-th-list"></span> สรุปรายการรับชำระ</a></li>
                <li><a id="submitPayment" class="btn btn-link"><span class="glyphicon glyphicon-print"></span> บันทึกและพิมพ์</a></li>
                <li><a id="submitPaymentEng" class="btn btn-link"><span class="glyphicon glyphicon-print"></span> บันทึกและพิมพ์(อังกฤษ)</a></li>
                <li><a id="cancelPaymentBtn" class="btn btn-link"><span class="glyphicon glyphicon-remove-circle"></span> ยกเลิกรายการ</a></li>
                <li><a id="creditLimit" class="btn btn-link"><span class="glyphicon glyphicon-remove-circle"></span> Credit Limit</a></li>
            </ul>
            <div id="fillDataInputPanel" class="row">
                <div class="col-md-5">
                    <label class="label-panal label-warning">รายการหัก</label>
                    <div id="setTaxType" class="btn-group">
                        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                            <span class="selection">ภาษีหัก ณ ที่จ่าย</span> <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu" id='testmenu'>
                            <li><a href="#">ภาษีหัก ณ ที่จ่าย</a></li>
                            <!-- GFMIS -->
                            <li><a href="#">ค่าธรรมเนียม</a></li>
                            <li><a href="#">ค่าปรับ</a></li>
                            <li><a href="#">เงินประกันผลงาน</a></li>
                            <li><a href="#">ค่าตอบแทนการรับชำระเงิน</a></li>
                        </ul>
                    </div>
<!--                     <a id="addTaxType" class="btn btn-link"><span class="glyphicon glyphicon-plus-sign"></span> เพิ่มรายการหัก</a> -->
                    <div class="panel panel-default panal-radius">
                        <div class="panel-body" style="padding-right:0;padding-left:0">
                            <div class="col-md-12">
                                <div role="tabpanel" class="tab-pane hide" id="withholdingTaxPanel">
                                    <div class="form-horizontal">
                                        <div class="form-group">
                                            <label class="control-label col-sm-6" >ประเภทภาษีหัก ณ ที่จ่าย :</label>
                                            <div class="col-sm-6">
                                                <label class="radio-inline"><input type="radio" name="withholdingTaxType" data-label="69 ทวิ" data-type="69BIS" id="69tvi"><b> 69 ทวิ</b></label>&nbsp;&nbsp;
                                                <label class="radio-inline"><input type="radio" name="withholdingTaxType" data-label="3 เตรส" data-type="3TREDECIM" id="3trs" ><b> 3 เตรส</b></label>&nbsp;&nbsp;
                                                <label class="radio-inline"><input type="radio" name="withholdingTaxType" data-label="69 ตรี" data-type="69TRE" id="69tri"><b> 69 ตรี</b></label>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                         <!-- ice fixed code no.109 'ทุกระบบต้องเลขที่ใบแจ้งค่าใช้บริการใบแจ้งค่าใช้บริการ" -->
                                            <label class="control-label col-sm-6" >เลขที่ใบแจ้งค่าใช้บริการ :</label>
                                            <!-- end ice fixed code no.109 'ทุกระบบต้องเลขที่ใบแจ้งค่าใช้บริการใบแจ้งค่าใช้บริการ" -->
                                            <div class="col-sm-5"><select id="withholdingInvoiceNo" class="form-control"></select></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-6" >เลขที่เอกสาร :</label>
                                            <div class="col-sm-5"><input id="withholdingTaxNo" type="text" class="form-control"></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-6" >จำนวนเงิน :</label>
                                            <div class="col-sm-5"><input id="withholdingTaxAmount" class="form-control text-right"></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-6" ></label>
                                            <div class="col-sm-5"><a id="addWithholdingTaxNo" class="btn btn-link"><span class="glyphicon glyphicon-th-list"></span>  เพิ่มรายการภาษีหัก ณ ที่จ่าย</a></div>
                                        </div>
                                    </div>
                                    <table id="withholdingTaxList" class="table table-hover">
                                        <thead>
                                            <tr>
                                                <th data-running-no="true">#</th>
                                                 <!-- ice fixed code no.109 'ทุกระบบต้องเลขที่ใบแจ้งค่าใช้บริการใบแจ้งค่าใช้บริการ" -->
                                                <th>เลขที่ใบแจ้งค่าใช้บริการ</th>
                                                 <!-- end ice fixed code no.109 'ทุกระบบต้องเลขที่ใบแจ้งค่าใช้บริการใบแจ้งค่าใช้บริการ" -->
                                                <th>เลขที่เอกสาร</th>
                                                <th>ประเภทหัก ณ ที่จ่าย</th>
                                                <th data-align="right" data-number-format="true">จำนวนเงิน</th>
                                                <th></th>
                                            </tr>
                                        </thead>
                                    </table>
                                </div>
                                <!-- GFMIS -->
                                <div role="tabpanel" class="tab-pane hide" id="feeTaxPanel">
                                    <div class="form-horizontal">
                                    	<div class="form-group hide">
                                            <label class="control-label col-sm-6" >ประเภทค่าธรรมเนียม :</label>
                                            <div class="col-sm-5">
                                                <label class="radio-inline"><input type="radio" name="feeTaxType" data-label="ขาออก" checked><b> ขาออก</b></label>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-6" >ศูนย์ต้นทุน :</label>
                                            <div class="col-sm-5"><select id="feeDepartmentCost" class="form-control"></select></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-6" >จำนวนเงิน :</label>
                                            <div class="col-sm-5"><input id="feeTaxAmount" class="form-control text-right"></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-6" >ภาษีซื้อ :</label>
                                            <div class="col-sm-5"><input id="feeBuyTaxAmount" class="form-control text-right"></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-6" >ภาษีหัก ณ ที่จ่าย :</label>
                                            <div class="col-sm-5"><input id="feeWTAmount" class="form-control text-right"></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-6" ></label>
                                            <div class="col-sm-5"><a id="addFeeTaxNo" class="btn btn-link"><span class="glyphicon glyphicon-th-list"></span> เพิ่มรายการค่าธรรมเนียม</a></div>
                                        </div>
                                    </div>
                                    <table id="feeTaxList" class="table table-hover">
                                        <thead>
                                            <tr>
                                                <th data-running-no="true">#</th>
                                                <th>ศูนย์ต้นทุน</th>
                                                <th style="display:none;">ประเภทค่าธรรมเนียม</th>
											    <th>ภาษีหัก ณ ที่จ่าย</th>
											    <th>ภาษีซื้อ</th>
                                                <th data-align="right" data-number-format="true">จำนวนเงิน</th>
                                                <th></th>
                                            </tr>
                                        </thead>
                                    </table>
                                </div>
                                <div role="tabpanel" class="tab-pane hide" id="penaltyTaxPanel">
                                    <div class="form-horizontal">
                                    	<div class="form-group hide">
                                            <label class="control-label col-sm-6" >ประเภทค่าปรับ :</label>
                                            <div class="col-sm-5">
<!--                                                 <label class="radio-inline"><input type="radio" name="penaltyTaxType" data-label="ขาเข้า" checked><b> ขาเข้า</b></label>&nbsp;&nbsp;&nbsp; -->
                                                <label class="radio-inline"><input type="radio" name="penaltyTaxType" data-label="ขาออก" checked><b> ขาออก</b></label>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                         <!-- ice fixed code no.109 'ทุกระบบต้องเลขที่ใบแจ้งค่าใช้บริการใบแจ้งค่าใช้บริการ" -->
                                            <label class="control-label col-sm-6" >เลขที่ใบแจ้งค่าใช้บริการ :</label>
                                            <!-- end ice fixed code no.109 'ทุกระบบต้องเลขที่ใบแจ้งค่าใช้บริการใบแจ้งค่าใช้บริการ" -->
                                            <div class="col-sm-5">
                                                <select id="penaltyInvoiceNo" class="form-control"></select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-6" >จำนวนเงิน :</label>
                                            <div class="col-sm-5">
                                                <input id="penaltyTaxAmount" class="form-control text-right">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-6" ></label>
                                            <div class="col-sm-5"><a id="addPenaltyTaxNo" class="btn btn-link"><span class="glyphicon glyphicon-th-list"></span>  เพิ่มรายการค่าปรับ</a></div>
                                        </div>
                                    </div>
                                    <table id="penaltyTaxList" class="table table-hover">
                                        <thead>
                                            <tr>
                                                <th data-running-no="true">#</th>
                                                 <!-- ice fixed code no.109 'ทุกระบบต้องเลขที่ใบแจ้งค่าใช้บริการใบแจ้งค่าใช้บริการ" -->
                                                <th>เลขที่ใบแจ้งค่าใช้บริการ</th>
                                                 <!-- end ice fixed code no.109 'ทุกระบบต้องเลขที่ใบแจ้งค่าใช้บริการใบแจ้งค่าใช้บริการ" -->
                                                <th style="display:none;">ประเภทค่าปรับ</th>
                                                <th data-align="right" data-number-format="true">จำนวนเงิน</th>
                                                <th></th>
                                            </tr>
                                        </thead>
                                    </table>
                                </div>
                                <div role="tabpanel" class="tab-pane hide" id="retentionTaxPanel">
                                    <div class="form-horizontal">
                                    	<div class="form-group hide">
                                            <label class="control-label col-sm-6" >ประเภทเงินประกันผลงาน :</label>
                                            <div class="col-sm-5">
<!--                                                 <label class="radio-inline"><input type="radio" name="retentionTaxType" data-label="ขาเข้า" checked><b> ขาเข้า</b></label>&nbsp;&nbsp;&nbsp; -->
                                                <label class="radio-inline"><input type="radio" name="retentionTaxType" data-label="ขาออก" checked><b> ขาออก</b></label>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                         <!-- ice fixed code no.109 'ทุกระบบต้องเลขที่ใบแจ้งค่าใช้บริการใบแจ้งค่าใช้บริการ" -->
                                            <label class="control-label col-sm-6" >เลขที่ใบแจ้งค่าใช้บริการ :</label>
                                             <!-- end ice fixed code no.109 'ทุกระบบต้องเลขที่ใบแจ้งค่าใช้บริการใบแจ้งค่าใช้บริการ" -->
                                            <div class="col-sm-5">
                                                <select id="retentionInvoiceNo" class="form-control"></select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-6" >จำนวนเงิน :</label>
                                            <div class="col-sm-5">
                                                <input id="retentionTaxAmount" class="form-control text-right">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-6" ></label>
                                            <div class="col-sm-5"><a id="addRetentionTaxNo" class="btn btn-link"><span class="glyphicon glyphicon-th-list"></span>  เพิ่มรายการเงินประกันผลงาน</a></div>
                                        </div>
                                    </div>
                                    <table id="retentionTaxList" class="table table-hover">
                                        <thead>
                                            <tr>
                                                <th data-running-no="true">#</th>
                                                 <!-- ice fixed code no.109 'ทุกระบบต้องเลขที่ใบแจ้งค่าใช้บริการใบแจ้งค่าใช้บริการ" -->
                                                <th>เลขที่ใบแจ้งค่าใช้บริการ</th>
                                                 <!-- end ice fixed code no.109 'ทุกระบบต้องเลขที่ใบแจ้งค่าใช้บริการใบแจ้งค่าใช้บริการ" -->
                                                <th style="display: none;">ประเภทเงินประกันผลงาน</th>
                                                <th data-align="right" data-number-format="true">จำนวนเงิน</th>
                                                <th></th>
                                            </tr>
                                        </thead>
                                    </table>
                                </div>
                                <div role="tabpanel" class="tab-pane hide" id="compensationTaxPanel">
                                    <div class="form-horizontal">
                                        <div class="form-group">
                                            <label class="control-label col-sm-6" >ศูนย์ต้นทุน :</label>
                                            <div class="col-sm-5">
                                            	<select id="compensationDepartmentCost" class="form-control"></select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-6" >จำนวนเงิน :</label>
                                            <div class="col-sm-5">
                                            	<input id="compensationTaxAmount" class="form-control text-right">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-6" ></label>
                                            <div class="col-sm-5"><a id="addCompensationTaxNo" class="btn btn-link"><span class="glyphicon glyphicon-th-list"></span>  เพิ่มรายการค่าตอบแทน</a></div>
                                        </div>
                                    </div>
                                    <table id="compensationTaxList" class="table table-hover">
                                        <thead>
                                            <tr>
                                                <th data-running-no="true">#</th>
                                                <th>ศูนย์ต้นทุน</th>
                                                <th data-align="right" data-number-format="true">จำนวนเงิน</th>
                                                <th></th>
                                            </tr>
                                        </thead>
                                    </table>
                                </div>
                                <a class="btn btn-primary pull-right" id="addTaxType" style="margin-top:1em"><span class="glyphicon glyphicon-plus-sign"></span> เพิ่มรายการหัก</a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-7">
                    <label class="label-panal label-warning">วิธีการรับชำระ</label>
                    <div id="setPayType" class="btn-group">
                        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                            <span class="selection">เงินสด</span> <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu" id='testmenu2'>
                            <li><a href="#">เงินสด</a></li>
                            <li><a href="#">เช็ค</a></li>
                            <li><a href="#">บัตรเครดิต</a></li>
                            <li><a href="#">ธนาณัติ</a></li>
                            <li><a href="#">ตั๋วแลกเงิน</a></li>
                            <li><a href="#">คูปอง</a></li>
                            <li><a href="#">เงินโอนในประเทศ</a></li>
                            <li><a href="#">offset</a></li>
                            <li><a href="#">เงินโอนต่างประเทศ</a></li>
                            <li><a href="#">อื่นๆ</a></li>
                            <li><a href="#">GFMIS</a></li>
                        </ul>
                    </div>
<!--                     <a id="addPayType" class="btn btn-link"><span class="glyphicon glyphicon-plus-sign"></span> เพิ่มวิธีการรับชำระ</a> -->
                    <div class="panel panel-default panal-radius">
                        <div class="panel-body" style="padding-right: 0; padding-left: 0;">
                            <div class="col-md-12">
                                <div role="tabpanel" class="tab-pane hide" id="payType1">
                                    <div class="form-horizontal">
                                        <div class="form-group">
                                            <label class="control-label col-sm-8" >จำนวนเงิน :</label>
                                            <div class="col-sm-4">
                                                <input id="payCashAmount" class="form-control text-right">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div role="tabpanel" class="tab-pane hide" id="payType2">
                                    <div class="form-horizontal">
                                        <div class="form-group">
                                            <label class="control-label col-sm-2" >รหัสธนาคาร :</label>
                                            <div class="col-sm-4"><select id="payChqBankCode" class="form-control"></select></div>
                                            <label class="control-label col-sm-2" >เลขที่เช็ค :</label>
                                            <!-- ICE FIXED CODE ข้อ 23 เช็ค length = 7 -->
                                            <div class="col-sm-4"><input class="form-control" id="payChqNo" maxlength="7"></div>
                                             <!-- End ICE FIXED CODE ข้อ 23 -->
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-2" >ชื่อธนาคาร :</label>
                                            <div class="col-sm-4"><select id="payChqBankName" class="form-control"></select></div>
                                            <label class="control-label col-sm-2" >วันที่หน้าเช็ค :</label>
                                            <div class="col-sm-4">
                                                <div class="input-group input-append date" data-provide="datepicker" data-date-format="dd/mm/yyyy">
                                                    <input class="form-control" id="payChqDate" placeholder="dd/MM/yyyy" maxlength="10">
													<span class="input-group-addon add-on"><span class="glyphicon glyphicon-calendar"></span></span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-2" >สาขา :</label>
                                            <div class="col-sm-4"><input class="form-control" id="payChqBranch"></div>
                                            <label class="control-label col-sm-2" >จำนวนเงิน :</label>
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
                                            <label class="control-label col-sm-3" >ประเภทบัตรเครดิต :</label>
                                            <div class="col-sm-3"><select class="form-control" id="payCCType"></select></div>
                                            <label class="control-label col-sm-3" >เลขที่บัตร :</label>
                                            <div class="col-sm-3"><input class="form-control" id="payCCNo" maxlength="16"></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-3" >ธนาคารเจ้าของเครื่อง (EDC) :</label>
                                            <div class="col-sm-3"><select class="form-control" id="payCCBankName"></select></div>
                                            <label class="control-label col-sm-3" >จำนวนเงิน :</label>
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
                                            <label class="control-label col-sm-3" >วันที่ธนาณัติ :</label>
                                            <div class="col-sm-3">
                                                <div class="input-group input-append date" data-provide="datepicker" data-date-format="dd/mm/yyyy">
                                                    <input class="form-control" id="payMoneyOrderInputDate" placeholder="dd/MM/yyyy" maxlength="10">
                                                    <span class="input-group-addon add-on"><span class="glyphicon glyphicon-calendar"></span></span>
                                                </div>
                                            </div>                                  <label class="control-label col-sm-3" >เลขที่ธนาณัติ :</label>
                                            <!-- ICE FIXED CODE ข้อ 24 length เลขธนาณัติ = 9 -->
                                            <div class="col-sm-3"><input class="form-control" id="payMoneyOrderInputNo" maxlength="9"></div>
                                         <!-- End ICE FIXED CODE ข้อ 24 -->
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-3" >รหัสไปรษณีย์ต้นทาง :</label>
                                            <!-- ICE FIXED CODE ข้อ 24 length postcode = 5 -->
                                            <div class="col-sm-3"><input class="form-control" id="payMoneyOrderInputPostCode" maxlength="5"></div>
                                           <!-- end ICE FIXED CODE ข้อ 24  -->
                                            <label class="control-label col-sm-3" >จำนวนเงิน :</label>
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
                                            <label class="control-label col-sm-3" >วันที่ตั๋วแลกเงิน :</label>
                                            <div class="col-sm-3">
                                                <div class="input-group input-append date" data-provide="datepicker" data-date-format="dd/mm/yyyy">
                                                    <input class="form-control" id="payBillExchgInputDate" placeholder="dd/MM/yyyy" maxlength="10">
                                                    <span class="input-group-addon add-on"><span class="glyphicon glyphicon-calendar"></span></span>
                                                </div>
                                            </div>
                                            <label class="control-label col-sm-3" >เลขที่ตั๋วแลกเงิน :</label>
                                            <!-- ICE FIXED CODE ข้อ 22 length เลขที่ตั๋วแลกเงิน = 9 -->
                                            <div class="col-sm-3"><input class="form-control" id="payBillExchgInputNo" maxlength="9"></div>
                                      <!--  end ICE FIXED CODE ข้อ 22 -->
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-3" >รหัสไปรษณีย์ต้นทาง :</label>
                                            <!-- ICE FIXED CODE ข้อ 22 length postcode = 5 -->
                                            <div class="col-sm-3"><input class="form-control" id="payBillExchgInputPostCode" maxlength="5"></div>
                                          <!--  end ICE FIXED CODE ข้อ 22 -->
                                            <label class="control-label col-sm-3" >จำนวนเงิน :</label>
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
                                            <label class="control-label col-sm-9" >เลขที่คูปอง :</label>
                                            <div class="col-sm-3"><input class="form-control" id="payCouponInputNo"></div>
                                        </div>
                                        <!-- --> 
                                        <div class="form-group">
                                            <label class="control-label col-sm-9" >จำนวนเงิน :</label>
                                            <div class="col-sm-3"><input class="form-control text-right" id="payCouponInputAmt"></div>
                                        </div>
                                         <!-- 
                                         <input type="hidden" class="form-control text-right" id="payCouponInputAmt">
                                         -->
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
                                            <label class="control-label col-sm-4" >รหัสธนาคาร :</label>
                                            <div class="col-sm-3"><select class="form-control" id="payBankTxnfInputBankCode"></select></div>
                                            <label class="control-label col-sm-2" >ชื่อธนาคาร :</label>
                                            <div class="col-sm-3"><select class="form-control" id="payBankTxnfInputBankName"></select></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-4" >สาขา :</label>
                                            <div class="col-sm-3"><select class="form-control" id="payBankTxnfDropDownBankBrnh"></select></div>
                                            <label class="control-label col-sm-2" >เลขที่อ้างอิง :</label>
                                            <div class="col-sm-3"><input class="form-control" id="payBankTxnfInputNo" maxlength="10"></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-4" >รหัสบัญชีเงินฝากธนาคาร :</label>
                                            <div class="col-sm-3"><select class="form-control" id="payBankTxnfDropDownBankAcCd"></select></div>
                                            <label class="control-label col-sm-2" >วันที่โอน :</label>
                                            <div class="col-sm-3">
                                                <div class="input-group input-append date" data-provide="datepicker" data-date-format="dd/mm/yyyy">
                                                    <input class="form-control" id="payBankTxnfInputDate" placeholder="dd/MM/yyyy" maxlength="10">
                                                    <span class="input-group-addon add-on"><span class="glyphicon glyphicon-calendar"></span></span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-4" >เลขที่บัญชีเงินฝากธนาคาร :</label>
                                            <div class="col-sm-3"><select class="form-control" id="payBankTxnfDropDownBankAcct"></select></div>
                                            <label class="control-label col-sm-2" >จำนวนเงิน :</label>
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
                                                <th style="display: none;"></th>
                                            	<th style="display: none;"></th>
                                                <th></th>
                                            </tr>
                                        </thead>
                                    </table>
                                </div>
                                <div role="tabpanel" class="tab-pane hide" id="payType8">
                                    <div class="form-horizontal">
                                        <div class="form-group">
                                            <label class="control-label col-sm-3" >เลขที่เอกสาร/ปี :</label>
                                            <div class="col-sm-3"><input id="payOffsetDocumentNo" class="form-control"></div>
                                            <label class="control-label col-sm-3" >รหัสบัญชี :</label>
                                            <div class="col-sm-3"><input id="payOffsetAccountCode" class="form-control"></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-3" >ชื่อบัญชี :</label>
                                            <div class="col-sm-3"><input id="payOffsetAccountName" class="form-control text-right"></div>
                                            <label class="control-label col-sm-3" >จำนวนเงิน :</label>
                                            <div class="col-sm-3"><input id="payOffsetAmount" class="form-control text-right"></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-10" ></label>
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
                                <div role="tabpanel" class="tab-pane hide" id="payType11">
									<div class="form-horizontal">
										<div class="form-group">
											<label class="control-label col-sm-3">สกุลเงินต่างประเทศ :</label>
											<div class="col-sm-3"><select class="form-control" id="currencyTypeSelect" onchange="addExchangRate()"></select></div>
											<label class="control-label col-sm-3">อัตราแลกเปลี่ยน :</label>
											<div class="col-sm-3"><input class="form-control text-right" id="exchangeRateInput" value="0.00" onblur="convertAmount()" readonly></div>
										</div>
										<div class="form-group">
											<label class="control-label col-sm-3">วันที่โอน :</label>
											<div class="col-sm-3">
												<div class="input-group input-append date" data-provide="datepicker" data-date-format="dd/mm/yyyy">
													<input class="form-control" id="payDateInputDate" placeholder="dd/MM/yyyy" maxlength="10">
													<span class="input-group-addon add-on"><span class="glyphicon glyphicon-calendar"></span></span>
												</div>
											</div>
											<label class="control-label col-sm-3">จำนวนเงินต่างประเทศ :</label>
											<div class="col-sm-3"><input class="form-control text-right" id="foreignAmountInput" onblur="convertAmount()"></div>
										</div>
										<div class="form-group">
											<label class="control-label col-sm-3">เลขที่บัญชีที่โอนเข้า :</label>
											<div class="col-sm-3"><select class="form-control" id="payBankTxnfDropDownBank"></select></div>
											<label class="control-label col-sm-3">จำนวนเงิน(บาท) :</label>
											<div class="col-sm-3"><input class="form-control text-right" id="thAmountInput" readonly></div>
										</div>
									</div>
									<a class="btn btn-warning pull-right" id="payForeignButtonSubmit"><span class="glyphicon glyphicon-plus-sign"></span> เพิ่มรายการอื่น ๆ</a>
									<table id="payForeignTableList" class="table">
										<thead>
											<tr>
												<th data-align="center" data-running-no="true">#</th>
												<th>สกุลเงินต่างประเทศ</th>
												<th>อัตราแลกเปลี่ยน</th>
												<th>วันที่โอน</th>
												<th data-align="center" data-number-format="true">จำนวนเงินต่างประเทศ</th>
												<th data-align="right" data-number-format="true">จำนวนเงิน(บาท)</th>
												<th style="display: none"></th>
												<th></th>
											</tr>
										</thead>
									</table>
								</div>
                                <div role="tabpanel" class="tab-pane hide" id="payType9">
                                    <div class="form-horizontal">
                                        <div class="form-group">
                                            <label class="control-label col-sm-3" >ช่องทางการชำระ :</label>
                                            <div class="col-sm-3"><select class="form-control" id="payOtherDropDownChannel"></select></div>
                                            <label class="control-label col-sm-3" >เลขที่อ้างอิง :</label>
                                            <div class="col-sm-3"><input class="form-control" id="payOtherInputNo"></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-3" >วันที่อ้างอิง :</label>
                                            <div class="col-sm-3">
                                                <div class="input-group input-append date" data-provide="datepicker" data-date-format="dd/mm/yyyy">
                                                    <input class="form-control" id="payOtherInputDate" placeholder="dd/MM/yyyy" maxlength="10">
                                                    <span class="input-group-addon add-on"><span class="glyphicon glyphicon-calendar"></span></span>
                                                </div>
                                            </div>
                                            <label class="control-label col-sm-3" >จำนวนเงิน :</label>
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
                                            <label class="control-label col-sm-4" >รหัสธนาคาร :</label>
                                            <div class="col-sm-3"><input class="form-control" id="payBankTxnfInputBankCodeGf" maxlength="5" ></div>
                                            <label class="control-label col-sm-2" >ชื่อธนาคาร :</label>
                                            <div class="col-sm-3"><select class="form-control" id="payBankTxnfInputBankNameGf"></select></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-4" >สาขา :</label>
                                            <div class="col-sm-3"><select class="form-control" id="payBankTxnfDropDownBankBrnhGf"></select></div>
                                            <label class="control-label col-sm-2" >เลขที่อ้างอิง :</label>
                                            <div class="col-sm-3"><input class="form-control" id="payBankTxnfInputNoGf" maxlength="10"></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-4" >รหัสบัญชีเงินฝากธนาคาร :</label>
                                            <div class="col-sm-3"><select class="form-control" id="payBankTxnfDropDownBankAcCdGf"></select></div>
                                            <label class="control-label col-sm-2" >วันที่โอน :</label>
                                            <div class="col-sm-3">
                                                <div class="input-group input-append date" data-provide="datepicker" data-date-format="dd/mm/yyyy">
                                                    <input class="form-control" id="payBankTxnfInputDateGf" placeholder="dd/MM/yyyy" maxlength="10">
                                                    <span class="input-group-addon add-on"><span class="glyphicon glyphicon-calendar"></span></span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-4" >เลขที่บัญชีเงินฝากธนาคาร :</label>
                                            <div class="col-sm-3"><select class="form-control" id="payBankTxnfDropDownBankAcctGf"></select></div>
                                            <label class="control-label col-sm-2" >จำนวนเงิน :</label>
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
                                            <th style="display: none;"></th>
                                            <th style="display: none;"></th>
                                            <th></th>
                                        </tr>
                                        </thead>
                                    </table>
                                </div>
                                <a class="btn btn-primary pull-right" id="addPayType" style="margin-top:1em"><span class="glyphicon glyphicon-plus-sign"></span> เพิ่มวิธีการรับชำระ</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
           <!--  <div id="linkPanel" class="row">
            <div class="col-md-5">
            <div class="col-md-13" align="right">
				<a id="addTaxType" class="btn btn-link"><span class="glyphicon glyphicon-plus-sign"></span> เพิ่มรายการหัก</a>
            </div>
            </div>
            <div class="col-md-7">
            <div class="col-md-13" align="right">
				<a id="addPayType" class="btn btn-link"><span class="glyphicon glyphicon-plus-sign"></span> เพิ่มวิธีการรับชำระ</a>
            </div>
            </div>
            </div> -->
            <div id="informationPanel" class="row">
                <div class="col-md-5">
                    <div class="panel panel-default">
                        <div class="panel-heading">สรุปรายการหัก</div>
                        <div class="panel-body">
                            <table id="deductionList" class="table table-hover">
                                <thead>
                                    <tr>
                                        <th data-running-no="true">#</th>
                                        <th>รายการหัก</th>
                                        <th data-align="right" data-number-format="true" class="text-right">จำนวนเงิน</th>
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
            <div id="receiptInfoPanel" class="row">
                <div class="col-md-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">สรุปยอดเงินที่ต้องชำระ</div>
                        <div class="panel-body">
                            <div class="form-horizontal">
                            	<div class="form-group" id="foreignTotalChargeDiv">
                               		<label class="control-label col-sm-10" id="foreignTotalChargeLabel">ยอดเงินที่ต้องชำระรวมภาษีมูลค่าเพิ่ม (Foreign)  :</label>
                                    <div class="col-sm-2">
                                        <input id="foreignTotalCharge" class="form-control text-right" disabled>
                                    </div>
                                </div>
                                <div class="form-group" id="foreignExchangeDiv">
                                	<label class="control-label col-sm-10" >อัตราแลกเปลี่ยน  :</label>
                                    <div class="col-sm-2">
                                        <input id="foreignExchangeRate" class="form-control text-right" disabled>
                                    </div>
                               	</div>
                                <input id="moneyToPay" hidden="hidden" />
                                <div class="form-group">
                                    <label class="control-label col-sm-10" >ยอดเงินก่อนหักส่วนลด :</label>
                                    <div class="col-sm-2">
                                        <input id="preItemsDiscount" class="form-control text-right" disabled="disabled">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-10" >ส่วนลด :</label>
                                    <div class="col-sm-2">
                                        <input id="itemsDiscount" class="form-control text-right" disabled="disabled">
                                    </div>
                                </div>

								<div class="form-group">
                                    <label class="control-label col-sm-10" >ยอดเงินที่ต้องชำระก่อนภาษีมูลค่าเพิ่ม :</label>
                                    <div class="col-sm-2">
                                        <input id="charge" class="form-control text-right" disabled="disabled">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-10" >ภาษีมูลค่าเพิ่ม :</label>
                                    <div class="col-sm-2">
                                        <input id="vat" class="form-control text-right" disabled="disabled">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-10" >ยอดเงินที่ต้องชำระรวมภาษีมูลค่าเพิ่ม :</label>
                                    <div class="col-sm-2">
                                        <input id="totalCharge" class="form-control text-right" disabled="disabled">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-10" >ภาษีหัก ณ ที่จ่าย  :</label>
                                    <div class="col-sm-2">
                                        <input id="deduct" class="form-control text-right" disabled>
                                    </div>
                                </div>
                                <div class="form-group" id="feeSummaryDiv">
                               		<label class="control-label col-sm-10" >ค่าธรรมเนียม  :</label>
                                    <div class="col-sm-2">
                                        <input id="fee" class="form-control text-right" disabled>
                                    </div>
                                </div>
                                <div class="form-group" id="penaltySummaryDiv">
                                	<label class="control-label col-sm-10" >ค่าปรับ  :</label>
                                    <div class="col-sm-2">
                                        <input id="penalty" class="form-control text-right" disabled>
                                    </div>
                               	</div>
                                <div class="form-group" id="retentionSummaryDiv">
                                	<label class="control-label col-sm-10" >เงินประกันผลงาน  :</label>
                                    <div class="col-sm-2">
                                        <input id="retention" class="form-control text-right" disabled>
                                    </div>
                                </div>
                                <div class="form-group" id="compensationSummaryDiv">
                                    <label class="control-label col-sm-10" >ค่าตอบแทนการรับชำระเงิน  :</label>
                                    <div class="col-sm-2">
                                        <input id="compensation" class="form-control text-right" disabled>
                                    </div>
                                </div>
								<div class="form-group">
                                    <%--<label class="control-label col-sm-10">
                                        <div class="checkbox">
                                            <label>
                                                <input type="checkbox" id="check_dis_notused" disabled> <b>ลูกค้ารับภาระภาษี&nbsp;&nbsp;&nbsp;<span class="glyphicon glyphicon-lock"></span> อื่นๆ :</b>
                                            </label>
                                        </div>
                                    </label>--%>
                                        <div class="control-label col-sm-10">
                                            <label class="strong"><input type="radio" name="discountCheckbox" disabled="disabled" value="1"> รับภาระภาษีเต็มจำนวน</label>&nbsp;&nbsp;&nbsp;&nbsp;
                                            <label class="strong"><input type="radio" name="discountCheckbox" disabled="disabled" value="2"> รับภาระภาษีบางส่วน :</label>
                                        </div>
                                    <div class="col-sm-2">
                                        <input id="discount" class="form-control text-right" disabled="disabled">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-10" >ยอดเงินที่ต้องชำระ :</label>
                                    <div class="col-sm-2">
                                        <input id="balanceDue" class="form-control text-right" disabled>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-10" >ยอดเงินรับมา :</label>
                                    <div class="col-sm-2">
                                        <input id="inputReceived" class="form-control text-right" disabled="disabled">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-10" >เงินทอน :</label>
                                    <div class="col-sm-2">
                                        <input id="change" class="form-control text-right" disabled="disabled">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="control-label col-sm-10" >
                                        <label class="strong"><input type="radio" name="specialOptions" value="1"> รายได้อื่นที่ไม่มีภาษี</label>&nbsp;&nbsp;&nbsp;&nbsp;
                                        <label class="strong"><input type="radio" name="specialOptions" value="2"> รายได้อื่นมีภาษี</label>&nbsp;&nbsp;&nbsp;&nbsp;
                                        <label class="strong"><input type="radio" name="specialOptions" value="3"> รับชำระล่วงหน้า :</label>
                                    </div>
                                    <div class="col-sm-2">
                                        <input id="inputAdvanced" class="form-control text-right">
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
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title"><span class="glyphicon glyphicon-user"></span> Authentication</h4>
                        </div>
                        <div class="modal-body">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="col-md-2">

                                    </div>
                                    <div class="col-md-10">
                                        <div class="form-horizontal">
                                            <div class="form-group">
                                                <label class="col-sm-3 control-label">ผู้อนุมัติ :</label>
                                                <div class="col-sm-6">
                                                    <input type="text" class="form-control">
                                                </div>
                                                <label class="col-sm-3 control-label"></label>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-sm-3 control-label">รหัสผ่าน :</label>
                                                <div class="col-sm-6">
                                                    <input type="password" class="form-control">
                                                </div>
                                                <label class="col-sm-3 control-label"></label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-success" ><span class="glyphicon glyphicon-ok-circle"></span> ตกลง</button>
                            <button type="button" class="btn btn-danger" data-dismiss="modal"><span class="glyphicon glyphicon-remove-circle"></span> ยกเลิก</button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal fade" role="dialog" id="advancePayment">
                <div class="modal-dialog modal-lg">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title">เลือกรายการลูกค้าที่จะรับชำระล่วงหน้า</h4>
                        </div>
                        <div class="modal-body">
                        	<div id="advancePaymentMessage"></div>
                            <div class="row">
                                <div class="col-md-12">
                                    <label>รายการลูกค้า</label>

                                    <table id="customerList" data-toggle="table">
                                        <thead>
                                            <tr>
                                                <th data-align="center" data-formatter="runningFormatter">#</th>
                                                <th data-field="custNo">เลขที่ลูกค้า</th>
                                                <th data-field="custName">ชื่อลูกค้า</th>
                                            <!--<th data-field="svcType">ประเภทบริการ</th>-->
                                                <th data-field="billGroup">Billing Group</th>
                                                <th data-field="advanceAmount" data-formatter="numberInputFormatter">ยอดชำระ</th>
                                                <th data-field="billCycle" data-formatter="billCycleInputFormatter">รอบการใช้งาน</th>
                                            </tr>
                                        </thead>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <a id="advancePaymentSubmit" class="btn btn-default" ><span class="glyphicon glyphicon-ok-circle"></span> เลือกรายการ</a>
                            <a id="advancePaymentCancel" class="btn btn-default" data-dismiss="modal"><span class="glyphicon glyphicon-remove-circle"></span> ยกเลิก</a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal fade" role="dialog" id="otherProfitVat">
                <div class="modal-dialog modal-lg">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title">เลือกรายการลูกค้า</h4>
                        </div>
                        <div class="modal-body">
                        	<div id="advancePaymentMessage2"></div>
                            <div class="row">
                                <div class="col-md-12">
                                    <label>รายการลูกค้า</label>

                                    <table id="customerList2" data-toggle="table">
                                        <thead>
                                            <tr>
                                                <th data-align="center" data-formatter="runningFormatter">#</th>
                                                <th data-field="custNo">เลขที่ลูกค้า</th>
                                                <th data-field="custName">ชื่อลูกค้า</th>
                                                <th data-field="billGroup">Billing Group</th>
                                                <th data-field="otherAmount" data-formatter="numberInputFormatter2">ยอดชำระ</th>
                                                <th data-formatter="stringInputFormatter" data-field="otherNote">เพิ่มเติม</th>
                                            </tr>
                                        </thead>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <a id="otherPaymentSubmit" class="btn btn-default" ><span class="glyphicon glyphicon-ok-circle"></span> เลือกรายการ</a>
                            <a id="otherPaymentCancel" class="btn btn-default" data-dismiss="modal"><span class="glyphicon glyphicon-remove-circle"></span> ยกเลิก</a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal fade" role="dialog" id="creditLimitDialog">
                <div class="modal-dialog modal-lg">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title">เลือกรายการ Subscription</h4>
                        </div>
                        <div class="modal-body">
                        	<div id="creditLimitMessage"></div>
                            <div class="row">
                                <div class="col-md-12">
                                    <label>รายการใบแจ้งหนี้ / Subscription</label>
                                    <table id="subScriptionListTb" data-maintain-selected="true" class="table table-hover">
										<thead>
											<tr>
                                                <!--
												<th  data-field="checked" data-checkbox="true"><input name="btSelectAll" type="checkbox"></th>
												-->
                                                <th data-formatter="checkboxFormatter"><input id="btSelectAll" type="checkbox"></th>

                                                <th data-field="ba">BA</th>
												 <!-- ice fixed code no.109 'ทุกระบบต้องเลขที่ใบแจ้งค่าใช้บริการใบแจ้งค่าใช้บริการ" -->
												<th data-field="invoiceNo">เลขที่ใบแจ้งค่าใช้บริการ</th>
												 <!-- end ice fixed code no.109 'ทุกระบบต้องเลขที่ใบแจ้งค่าใช้บริการใบแจ้งค่าใช้บริการ" -->
                                                <th data-field="subscriptionNo">Subscription</th>
											</tr>
										</thead>
									</table>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <a id="creditLimitSubmit" class="btn btn-default" ><span class="glyphicon glyphicon-ok-circle"></span> เลือกรายการ</a>
                            <a id="creditLimitCancel" class="btn btn-default" data-dismiss="modal"><span class="glyphicon glyphicon-remove-circle"></span> ยกเลิก</a>
                        </div>
                    </div>
                </div>
            </div>
        </section>

    </body>
</html>
<script type="text/javascript">
var cancelList=[];
var view = (function($){
	var self = this;
	self.session = function(key, val) { if (!val) { var val = window.sessionStorage.getItem(key); return val && (val.indexOf("{") > -1 || val.indexOf("[") > -1) ? JSON.parse(val) : val } window.sessionStorage.setItem(key, ($.type(val) != "object" && $.type(val) != "array" ? val : JSON.stringify(val)))	};
	self.utils = {
		guid: function(){ function r() { return Math.floor((Math.random() + 1) * 0x10000).toString(16).substring(1) } return r() + r() +"-"+ r() +"-"+ r() +"-"+ r() +"-"+ r() + r() + r() },
		numberFormat: function(num, dec) { return $.type(num) !== "number" ? "0.00" : num.toFixed(dec || 2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,'); },
		dateFormat: function() {var obj = arguments[0];if ($.type(obj) == "string") { var regGroupYYYYMMDD = /([0-9]{4}).([0-9]{2}).([0-9]{2})/g;var regGroupDDMMYYYY = /([0-9]{2}).([0-9]{2}).([0-9]{4})/g;var match = regGroupYYYYMMDD.exec(obj);if( match == null) {match = regGroupDDMMYYYY.exec(obj);dd = match[1];mm = match[2];yyyy = match[3];} else { dd = match[3];mm = match[2];yyyy = match[1]; } return (dd[1]?"":"0") + dd +"/"+ (mm[1]?"":"0") + mm +"/"+ yyyy } else if ($.isNumeric(obj)) { var dt = new Date(obj), dd = dt.getDate().toString(), mm = (dt.getMonth() + 1).toString(), yyyy = dt.getFullYear().toString(); return (dd[1]?"":"0") + dd +"/"+ (mm[1]?"":"0") + mm +"/"+ yyyy } else if ($.type == "date") { return "" } return "" },
		dateTimeFormat: function() { var obj = arguments[0], dt = $.type(obj) != "date" ? ($.isNumeric(obj) ? new Date(obj) : new Date()) : new Date(), dd = dt.getDate().toString(), MM = (dt.getMonth() + 1).toString(), yyyy = dt.getFullYear().toString(), hh = dt.getHours().toString(), mm = dt.getMinutes().toString(), ss = dt.getSeconds().toString(); return (dd[1]?"":"0") + dd +"/"+ (MM[1]?"":"0") + MM +"/"+ yyyy +" "+ (hh[1]?"":"0") + hh +":"+ (mm[1]?"":"0") + mm +":"+ (ss[1]?"":"0") + ss },
		queryString: function() { var pos = location.href.indexOf("?"), obj = {}; if (pos < 0) return obj; var params = location.href.slice(pos + 1).split("&"); for (var i = 0, m = params.length; i < m; i++) { pos = params[i].indexOf("="); if (pos < 0) { obj[params[i]] = true; continue; } obj[params[i].substring(0, pos)] = params[i].substring(pos + 1) } return obj },
		window: function(windowName, url, side) { window.windowOpened = (window.windowOpened || {}); var screenProp = ""; if (side == "left") { screenProp = "left=0,top=0,width="+ (screen.width/2) +",height="+ (screen.height-100) } else if (side == "right") { screenProp = "left="+ (screen.width/2-40) +",top=0,width="+ (screen.width/2+40) +",height="+ (screen.height-100) } window.windowOpened[windowName] = window.open(url, windowName, "menubar=no,scrollbars=no,resizable=0,"+ screenProp, false); return window.windowOpened }
	};
	//// AUTOMATIC REGISTER FORMATTER FUNCTION ////
	window.get = function(url, params, success, msgDialog, preCheck){ $.ajax({ "url": url, "type": "GET", "data": params, "error": msgDialog.valid, "success": function(res){ (preCheck || function(o){ console.log(o) })(res); if (msgDialog && !msgDialog.valid(res)) { return; } success(res) } }) }
	window.getSync = function(url, params, success, msgDialog, preCheck){ $.ajax({ "url": url, "type": "GET", "data": params, "async": false, "error": (msgDialog || { "valid": function(jqXHR, textStatus, errorThrow){ console.log(jqXHR); console.log("textStatus: "+ textStatus); console.log("errorThrow:"); console.log(errorThrow) } }).valid, "success": function(res){ var isNotJson = res.constructor == String; console.log(res); (preCheck || function(o){})(res); if (isNotJson) res = { "statusCode": "99", "errorList": ["No matching data found."] }; if (msgDialog && !msgDialog.valid(res)) { return; } success(res) } }) }
	window.post = function(url, params, success, msgDialog, preCheck){ $.ajax({ "url": url, "type": "POST", "data": params, "error": msgDialog.valid, "success": function(res){ (preCheck || function(o){ console.log(o) })(res); if (msgDialog && !msgDialog.valid(res)) { return; } success(res) } }) }
	window.add = function(num1, num2, dec) { if (!dec) dec = 2; return parseFloat((num1 + num2).toFixed(dec), 10); }; window.subtract = function(num1, num2, dec){ if (!dec) dec = 2; return parseFloat((num1 - num2).toFixed(dec), 10); }; window.multiply = function(num1, num2, dec){ if (!dec) dec = 2; return parseFloat((num1 * num2).toFixed(dec), 10); }; window.divide = function(num1, num2, dec){ if (!dec) dec = 2; return parseFloat((num1 / num2).toFixed(dec), 10); }
	window.stripToNumber = function(str) { return $.type(str) === "string" && $.isNumeric(str = str.replace(/[,]/g, "")) ? parseFloat(str, 10) : 0 }
	window.runningFormatter = function(val, row, ind) { return ind + 1 }; window.nullToDashedFormatter = function(val){ return val != null && (val == $.trim(val)).length > 0 ? val : "-" }
	window.numberFormatter = function(val) { return !$.isNumeric(stripToNumber(val)) ? "0.00" : parseFloat(val, 10).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,') }
	/*window.stringInputFormatter = function(val, row, ind) { return '<input id="custNote" value="'+ $.trim(val) +'" maxLength="100" class="form-control">' }*/
    window.stringInputFormatter = function(val, row, ind) {
        var index = "otherNote"+ind;
        return '<input id="'+index+'" value="เงินยกให้กสท" maxLength="100" class="form-control">' }
    window.billCycleInputFormatter = function(val, row, ind) {
        var index = "billCycle"+ind;
        return '<input id="'+index+'" value="" maxLength="140" class="form-control">'
    }
	window.numberInputFormatter = function(val, row, ind) { return '<input value="'+ self.utils.numberFormat(parseFloat(val || "0.00", 10)) +'" maxLength="10" style="width:100px" class="form-control text-right" onkeypress="return $.isNumeric(String.fromCharCode(event.which)) || (event.which == 46 && this.value.indexOf(\'.\') == -1)" onfocus="(function(o){ o.value = o.value.replace(/[,]/g, \'\') })(this)" onblur="(function(o){ o.value = parseFloat(o.value, 10).toFixed(2).replace(/(\\d)(?=(\\d{3})+\\.)/g, \'$1,\'); })(this)">' }
    window.numberInputFormatter2 = function(val, row, ind) {
        var index = "otherAmt"+ind;
        return '<input id="advanceAmount'+index+'" value="'+ self.utils.numberFormat(parseFloat(val || "0.00", 10)) +'" maxLength="10" style="width:100px" class="form-control text-right" onkeypress="return $.isNumeric(String.fromCharCode(event.which)) || (event.which == 46 && this.value.indexOf(\'.\') == -1)" onfocus="(function(o){ o.value = o.value.replace(/[,]/g, \'\') })(this)" onblur="(function(o){ o.value = parseFloat(o.value, 10).toFixed(2).replace(/(\\d)(?=(\\d{3})+\\.)/g, \'$1,\'); })(this)">' }
    window.modifyButtonFormatter = function(val, row, ind) { return '<a class="btn btn-link modList" onclick=\'(function(elem, row, ind){ var table = $("#"+ $(elem).closest("table")[0].id); window[table[0].id +"ModifyEvent"](table, row, ind) })(this, '+ JSON.stringify(row) +', '+ ind +')\'><span class="glyphicon glyphicon-pencil"></span></a>' }
	window.removeButtonFormatter = function(val, row, ind) { return '<a class="btn btn-link delList" onclick=\'(function(elem, row, ind){ $("#"+ $(elem).closest("table")[0].id).bootstrapTable("remove", { field: "index", values: [ind] }) })(this, '+ JSON.stringify(row) +', '+ ind +')\'><span class="glyphicon glyphicon-trash"></span></a>' }
	function Button(el) { var obj = this, badge; obj.el = el; obj.elem = $(el);
		obj.hide = function() { this.elem.addClass("hide"); return this }; obj.show = function() { this.elem.removeClass("hide"); return this };
		obj.hideLoad = function(){ obj.elem.button("reset"); return this }; obj.showLoad = function(){ obj.elem.button("loading"); return this };
		obj.disable = function(flag) { if (flag == null || flag) this.elem.addClass("disabled").attr("disabled", true); else this.elem.removeClass("disabled").attr("disabled", false); return this }; obj.enable = function() { obj.disable(false); return this };
		obj.badge = function(val) { if (badge) badge.text(val) }; if ((badge = this.elem.next()).length == 0) badge = null;
		obj.elem.click(window[el.substring(1) +"ClickEvent"]);
	}
	function Radio(el) { var obj = this; obj.el = el; obj.elem = $(el);
		obj.disable = function(){ if (arguments.length == 2 && $.isNumeric(arguments[0])) obj.elem.eq(arguments[0]).attr("disabled", (arguments[1] != null && $.type(arguments[1]) == 'boolean' ? arguments[1] : false)); return this }; obj.enable = function(){ obj.disable(false); return this }
		obj.label = function() { return this.elem.filter(":checked").data("label") };
		obj.type = function() { return this.elem.filter(":checked").data("type") };
		obj.val = function() { var s = this.elem.filter(":checked"),val = s.val(); return !val ? s.data("label") : val };
		obj.checked = function(index) { obj.elem.eq(index).attr("checked", true); return this }
		obj.unchecked = function() { obj.elem.each(function(i,o){ o.checked = false }); return this }
		obj.click = function(evt) { obj.elem.each(function(i,o){ $(o).click(function(e){ if (o.checked) evt(o.value) }) }); return this }
	}
	function Input(el, maxLen, propPos) {
		var obj = this;
		obj.el = el;
		obj.elem = $(el);
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
	function NumberInput(el, dec) { var obj = this, decimal = (dec || 2); this.el = el; this.elem = $(el);
		obj.val = function() { if (arguments.length == 0) return parseFloat(strip(this.elem.val() || "0"), 10); this.elem.val(format(arguments[0])); }
		obj.decimal = function(dec) { decimal = dec }; obj.format = format;
		obj.disable = function(){ obj.elem.attr("disabled", (arguments.length < 1 ? true : arguments[0])); return obj }; obj.enable = function(){ obj.disable(false); return obj }
		obj.hide = function() { this.elem.addClass("hide"); return this };obj.show = function() { this.elem.removeClass("hide"); return this };
		obj.elem.keypress(function(e) { var key = (e.which || e.keyCode || e.charCode || 0); var ch = String.fromCharCode(key); return "0123456789.".indexOf(ch) > -1 });
		obj.elem.focus(function(){ this.value = strip(this.value); this.select() });
		obj.elem.blur(function(){ this.value = format(this.value) });
		function format(val) { return ($.isNumeric(val) ? parseFloat(val, 10) : obj.val()).toFixed(decimal).replace(/(\d)(?=(\d{3})+\.)/g, '$1,'); };
		function strip(str) { return (str || "").replace(/[,]/g, "") }
		if (obj.elem.val() == "") obj.elem.val("0.00");
	}

    function DropDown(el, url) { var obj = this, data = [{ key: "", value: "", text: "Please Select" }]; obj.el = el; obj.elem = $(el);
        obj.data = function(array) { if ($.type(array) == "array") { setup(data = array); return this } return data; };
        obj.init = function(url, v) {if (url) $.get(url, function(res){ if(res!=null){if(res.data[0].rateCode!=null){ exchange = res.data; }} setup(data = res.data, v);}); else setup(data, v); return this };
        obj.error = function(flag) { if (arguments.length == 0 || flag) obj.elem.parent().addClass("has-error"); else obj.elem.parent().removeClass("has-error"); return this }
        obj.disable = function(flag) { obj.elem.attr("disabled", (flag == null ? true : flag)); return this }
        obj.enable = function() { obj.disable(false); return this }
        obj.index = function() { if (arguments.length == 1) { var maxLen = data.length, index = arguments[0]; obj.elem.find("option").each(function(i, opt){ opt.removeAttribute("selected") }).eq(index < 0 ? maxLen + index : index).attr("selected", true); return this } return $.inArray("selected", obj.elem.find("option").map(function(i, opt){ return opt.selected ? "selected" : "no-selected" })) }
        obj.selected = function(){ return data[obj.index()]; };
        obj.val = function() { return obj.elem.val(); };
        obj.key = function(){ if (arguments.length == 0) { var key = obj.elem.find("option:selected").data("key"); if (typeof key === "string" && key === "undefined") { var item = data[obj.index()]; key = item && item.key ? item.key : item.id } return key } var key = arguments[0]; for (var i = 0, m = data.length; i < m; i++) { var item = data[i]; if (item.key && item.key == key) { obj.index(i); break; } if (item.id && item.id == key) { obj.index(i); break; } } return this }
		function setup(array, v) {
			obj.elem.find("*").remove();
            //$("#currencyTypeSelect").append('<option data-index="0" data-key="0" value="0">กรุณาเลือก</option>');
            obj.elem.append('<option data-index="-1" data-key="-1" value="">กรุณาเลือก</option>');
            $.each(array,function(i,o){
                if(o.category == 'bank.name') {
                    if(v == 'name'){
                        obj.elem.append('<option data-index="'+ i +'" data-key="'+ o.code +'" value="'+ o.descFullTh +'"e>'+ o.descFullTh +'</option>')
                    }
                    else{
                        obj.elem.append('<option data-index="'+ i +'" data-key="'+ o.code +'" value="'+ o.code +'">'+ o.code +'</option>')
                    }
                }
                else{
                    if(o.rateCode!=null){
                        x = i;
                        x++;
                        obj.elem.append('<option data-index="' + x + '" data-key="' + x + '" value="' + o.code + '" data-message ="' + o.message + '" data-rate="'+ o.rateUnit +'" data-date="'+ o.dateUsed +' ">' + o.message + '</option>')
                    } else {
                        obj.elem.append('<option data-index="' + i + '" data-key="' + o.key + '" value="' + o.value + '">' + o.value + '</option>')
                    }
                }
            });
		}
		this.init(url);
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
		obj.typeSum =  function(typeIndex) { return stripToNumber(obj.find("type", typeIndex).find("td:eq(2) div").text());}
		obj.deduct = function(index) { var sum = 0; this.elem.find("tbody tr").each(function(i, row){ var val = row.children[index].innerText.replace(/[,]/g, ""); sum += (isNaN(val) ? 0 : parseFloat((val * -1), 10)) }); return sum };
		obj.calcBalance = function(typeindex,index) { var sum = 0; var gettingIncome = 'ขาเข้า'; this.elem.find("tbody tr").each(function(i, row){ var type = row.children[typeindex].innerText.replace(/[]/g, ""); var multiplier = (gettingIncome == type.trim()) ? 1 : -1; var val = row.children[index].innerText.replace(/[,]/g, ""); sum += (isNaN(val) ? 0 : parseFloat((val * multiplier), 10)) }); return sum };
		obj.sum = function(index) { var sum = 0; this.elem.find("tbody tr").each(function(i, row){ var val = row.children[index].innerText.replace(/[,]/g, ""); sum += (isNaN(val) ? 0 : parseFloat(val, 10)) }); return sum }
		$(obj.el).on("click", ".delList", function(){ $(this).parent().parent().remove(); $.each(headers, function(i,p){ if (p.runningNo) reorder(i) }); onDel(obj.data()) });
		obj.elem.removeClass("table").removeClass("table-hover").addClass("table").addClass("table-hover");
		obj.elem.find("thead th").each(function(i,o){ var elem = $(o); headers.push({ "field": elem.data("field"), "valign": $.trim(elem.data("valign")), "align": $.trim(elem.data("align")), "modifyButton": elem.data("modifyButton") === true, "removeButton": elem.data("removeButton") === true, "runningNo": elem.data("runningNo") === true, "numberFormat": elem.data("numberFormat") === true, "checkbox": elem.data("checkbox") === true, "radio": elem.data("radio") === true, "input": elem.data("input") === true }) });
		function replace(str, style, field, index, value){ var s = str; if (style) s = s.replace("\{style\}", style); return s.replace("\{field\}", $.trim(field)).replace("\{index\}", index).replace("\{value\}", value) }
		function extract(prop, col) { if (prop.checkbox) { var elem = col.children[0].children[0]; return !elem.checked ? "" : elem.value } else if (prop.radio) { var elem = col.children[0].children[0]; return !elem.checked ? "" : elem.value } else if (prop.input) { var elem = col.children[0].children[0]; return elem.value } return col.childNodes[0].nodeType === 3 ? col.textContent : col.children[0].innerHTML }
		if(obj.body.length < 1) { obj.elem.append("<tbody></tbody>"); obj.body = obj.elem.find("tbody") }
	}
	function Div(el) {
		var obj = this; obj.el = el; obj.elem = $(el);
		obj.hide = function() { obj.elem.addClass("hide"); return obj };
		obj.show = function(flag) { if (flag == null || flag) obj.elem.removeClass("hide"); else obj.hide(); return obj };
	}
	self.breadcrumb = new(function(){
		var that = this;
		that.breadcrumb = new Breadcrumb("#breadcrumb");
		function Breadcrumb(el) {
			var obj = this, list, index = 0;
			obj.el = el;
			obj.elem = $(el);
			obj.index = function(){ if(arguments.length == 1) { list.removeClass("active").eq(index = arguments[0]).addClass("active"); return obj } return index }
			list = obj.elem.find("li").each(function(i,o){ $(o).data("index", i) });
			index = list.filter(".active").data("index");
		}
	});
	self.button = new(function(){
		var that = this;
		that.addTaxType = new Button("#addTaxType");
		that.setTaxType = new ButtonGroup("#setTaxType");
		that.addWithholdingTaxNo = new Button("#addWithholdingTaxNo");
		that.addFeeTaxNo = new Button("#addFeeTaxNo");
		that.addPenaltyTaxNo = new Button("#addPenaltyTaxNo");
		that.addRetentionTaxNo = new Button("#addRetentionTaxNo");
		that.addCompensationTaxNo = new Button("#addCompensationTaxNo");
		that.addPayType = new Button("#addPayType");
		that.setPayType = new ButtonGroup("#setPayType");
		that.payChqSubmit = new Button("#payChqSubmit");
		that.payCCSubmit = new Button("#payCCSubmit");
		that.submitPayment = new Button("#submitPayment");
		that.creditLimit = new Button("#creditLimit");
		that.submitPaymentEng = new Button("#submitPaymentEng");
		that.advancePaymentSubmit = new Button("#advancePaymentSubmit");
		that.otherPaymentSubmit = new Button("#otherPaymentSubmit");
		that.advancePaymentCancel = new Button("#advancePaymentCancel");
		that.creditLimitSubmit = new Button("#creditLimitSubmit");
		that.creditLimitCancel = new Button("#creditLimitCancel");
		function ButtonGroup(el) {
			var obj = this, index = 0;
			obj.el = el;
			obj.elem = $(el);
			obj.val = function() { return obj.elem.find(".selection").text() }
			obj.index = function() { if (arguments.length == 1) { obj.list[arguments[0]].click() } return index }
			obj.list = obj.elem.find(".dropdown-menu a").each(function(i){ $(this).click(function(){ index = i }) });
			obj.hideIndex = function() { if (arguments.length == 1) { var selected = obj.elem.find(".dropdown-menu a")[arguments[0]]; $(selected).addClass("hide");} return index }
		}
	});
	self.dialog = new(function(){
		var that = this;
		that.mainMessageDialog = new Message("#mainMessageDialog");
		that.message = new Message("#message");
		that.advancePaymentMessage = new Message("#advancePaymentMessage");
		that.advancePaymentMessage2 = new Message("#advancePaymentMessage2");
		that.crditLimitMessage = new Message("#crditLimitMessage");
		that.advancePayment = new Modal("#advancePayment", true);
		that.otherProfitVat = new Modal("#otherProfitVat", true);
		that.creditLimitDialog = new Modal("#creditLimitDialog", true);
		function Modal(el, static) { var obj = this; obj.el = el; obj.elem = $(el);
			obj.hide = function() { this.elem.modal("hide") };
			obj.show = function() { this.elem.modal("show") };
			if (static) obj.elem.data("backdrop", "static")
		}
	});
	self.panel = new(function(){
		var that = this;
		that.summaryPanel = new Panel("#summaryPanel");
		that.navigationPanel = new Panel("#navigationPanel");
		that.fillDataInputPanel = new Panel("#fillDataInputPanel");
		that.informationPanel = new Panel("#informationPanel");
		that.linkPanel = new Panel("#linkPanel");
		that.receiptInfoPanel = new Panel("#receiptInfoPanel");
		that.tax = new FadePanel(self.button.setTaxType, "#withholdingTaxPanel","#feeTaxPanel", "#penaltyTaxPanel", "#retentionTaxPanel", "#compensationTaxPanel");
		that.pay = new FadePanel(self.button.setPayType, "#payType1", "#payType2", "#payType3", "#payType4", "#payType5", "#payType6", "#payType7", "#payType9", "#payType8", "#payType10", "#payType11");
		function Panel(el) { var obj = this, dura = 500; obj.el = el; obj.elem = $(el);
			obj.hide = function(ms) { obj.elem.hide(ms || dura); return this }; obj.show = function(ms) { obj.elem.show(ms || dura); return this }
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
	});
	self.radio = new(function(){
		var that = this;
		that.withholdingTaxType = new Radio("[name=withholdingTaxType]");
		that.feeTaxType = new Radio("[name=feeTaxType]");
		that.penaltyTaxType = new Radio("[name=penaltyTaxType]");
		that.retentionTaxType = new Radio("[name=retentionTaxType]");
		that.specialOptions = new Radio("[name=specialOptions]");
	});
	self.input = new(function(){
		var that = this;
		that.payCashAmount = new NumberInput("#payCashAmount");
		that.payChqBankCode = new DropDown("#payChqBankCode").init("../findBankNameList.json", "code");//.data([{ key: "0", value: "001" },{ key: "1", value: "002" },{ key: "2", value: "003" }]);
		that.payChqBankName = new DropDown("#payChqBankName").init("../findBankNameList.json", "name");//.data([{ key: "0", value: "กรุณาเลือกธนาคาร" },{ key: "1", value: "กรุงเทพ" },{ key: "2", value: "กสิกรไทย" }]);
		that.payChqNo = new Input("#payChqNo");
		that.payChqDate = new Input("#payChqDate");
		that.payChqBranch = new Input("#payChqBranch");
		that.payChqAmount = new NumberInput("#payChqAmount");
		that.payCCType = new DropDown("#payCCType").data([{ key: "1", value: "VISA" },{ key: "2", value: "MASTER" }]);
		that.payCCBankName = new DropDown("#payCCBankName").data([{ key: "1", value: "กรุงเทพ" },{ key: "2", value: "กสิกรไทย" }]);
		that.payCCNo = new Input("#payCCNo");
		that.payCCAmount = new NumberInput("#payCCAmount");
		that.withholdingTaxNo = new Input("#withholdingTaxNo");
		that.withholdingTaxAmount = new NumberInput("#withholdingTaxAmount");
		that.withholdingInvoiceNo = new DropDown("#withholdingInvoiceNo");
		that.feeDepartmentCost = new DropDown("#feeDepartmentCost").data([{ key: "1", value: "0000 จต." }]);
		that.feeTaxAmount = new NumberInput("#feeTaxAmount");
		that.feeWTAmount = new NumberInput("#feeWTAmount");
		that.feeBuyTaxAmount = new NumberInput("#feeBuyTaxAmount");
		that.penaltyInvoiceNo = new DropDown("#penaltyInvoiceNo");
		that.penaltyTaxAmount = new NumberInput("#penaltyTaxAmount");
		that.retentionInvoiceNo = new DropDown("#retentionInvoiceNo");
		that.retentionTaxAmount = new NumberInput("#retentionTaxAmount");
		that.compensationDepartmentCost = new DropDown("#compensationDepartmentCost").data([{ key: "1", value: "0000 จต." }]);
		that.compensationTaxAmount = new NumberInput("#compensationTaxAmount");
		that.discount = new NumberInput("#discount");
		that.charge = new NumberInput("#charge");

        that.preItemsDiscount = new NumberInput("#preItemsDiscount");
        that.moneyToPay = new NumberInput("#moneyToPay");
        that.itemsDiscount = new NumberInput("#itemsDiscount");

		that.vat = new NumberInput("#vat");
		that.totalCharge = new NumberInput("#totalCharge");
		that.deduct = new NumberInput("#deduct");
		that.fee = new NumberInput("#fee");
		that.penalty = new NumberInput("#penalty");
		that.retention = new NumberInput("#retention");
		that.compensation = new NumberInput("#compensation");
		that.balanceDue = new NumberInput("#balanceDue");
		that.change = new NumberInput("#change");
		that.foreignTotalCharge = new NumberInput("#foreignTotalCharge");
		that.foreignExchangeRate = new Input("#foreignExchangeRate");
        that.payBankTxnfDropDownBankCode = new DropDown("#payBankTxnfInputBankCode").init("../findBankNameList.json", "code");
        that.payBankTxnfDropDownBankName = new DropDown("#payBankTxnfInputBankName").init("../findBankNameList.json", "name");//.data([{ key: "0", value: "กรุณาเลือกธนาคาร" },{ key: "006", value: "กรุงไทย" }]);
		that.val = function() { if (arguments.length == 1) { $.each(arguments[0],function(k,v){ $("#"+ k).val(v) }) } };
	});
	self.table = new(function(){
		var that = this;
		that.deductionList = new Table("#deductionList");
		that.payTypeList = new Table("#payTypeList");
		that.withholdingTaxList = new Table("#withholdingTaxList");
		that.feeTaxList = new Table("#feeTaxList");
		that.penaltyTaxList = new Table("#penaltyTaxList");
		that.retentionTaxList = new Table("#retentionTaxList");
		that.compensationTaxList = new Table("#compensationTaxList");
		that.payChqList = new Table("#payChqList");
		that.payCCList = new Table("#payCCList");
		that.customerList = new BootstrapTable("#customerList");
		that.customerList2 = new BootstrapTable("#customerList2");
		that.subScriptionListTb = new Table("#subScriptionListTb");
        //that.subScriptionListTb = new BootstrapTable("#subScriptionListTb");
		that.receiptList = new BootstrapTable("#receiptList");
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
			obj.sumInput = function(index) { var sum = 0; obj.elem.find("tbody tr").each(function(i,o){ var val = o.children[index].children[0].value.replace(/[,]/g, ""); sum += (!$.isNumeric(val) ? 0 : parseFloat(val, 10)) }); return sum }
			obj.expand = function() { obj.elem.find(".detail-icon i.icon-plus").click(); return this };
			obj.collapse = function() { obj.elem.find(".detail-icon i.icon-minus").click(); return this };
			obj.checkboxEvent = function(func) { this.elem.on("check.bs.table uncheck.bs.table check-all.bs.table uncheck-all.bs.table", func); return this };
			obj.checkEvent = function(func) { checkEvt = func; return this };
			obj.uncheckEvent = function(func) { uncheckEvt = func; return this };
			obj.checkboxEvent(function(e) { if (e.type === "check") checkEvt(e); if (e.type === "uncheck") uncheckEvt(e) });
		}
	});
	self.div = new(function() {
		var that = this;
		that.feeSummaryDiv = new Div("#feeSummaryDiv");
		that.penaltySummaryDiv = new Div("#penaltySummaryDiv");
		that.retentionSummaryDiv = new Div("#retentionSummaryDiv");
		that.compensationSummaryDiv = new Div("#compensationSummaryDiv");
		that.foreignTotalChargeDiv = new Div("#foreignTotalChargeDiv");
		that.foreignExchangeDiv = new Div("#foreignExchangeDiv");
	});

	(function(){ $(window["setup"]) })();
	self.payMoneyOrderButtonSubmit = new Button("#payMoneyOrderButtonSubmit");
	self.payMoneyOrderInputDate = new Input("#payMoneyOrderInputDate");
	self.payMoneyOrderInputNo = new Input("#payMoneyOrderInputNo");
	self.payMoneyOrderInputPostCode = new Input("#payMoneyOrderInputPostCode");
	self.payMoneyOrderInputAmount = new NumberInput("#payMoneyOrderInputAmount");
	self.payMoneyOrderTableList = new Table("#payMoneyOrderTableList");
	self.payBillExchgButtonSubmit = new Button("#payBillExchgButtonSubmit");
	self.payBillExchgInputDate = new Input("#payBillExchgInputDate");
	self.payBillExchgInputNo = new Input("#payBillExchgInputNo");
	self.payBillExchgInputPostCode = new Input("#payBillExchgInputPostCode");
	self.payBillExchgInputAmount = new NumberInput("#payBillExchgInputAmount");
	self.payBillExchgTableList = new Table("#payBillExchgTableList");
	self.payCouponButtonSubmit = new Button("#payCouponButtonSubmit");
	self.payCouponInputNo = new Input("#payCouponInputNo");
	self.payCouponInputAmt = new NumberInput("#payCouponInputAmt");
	self.payCouponTableList = new Table("#payCouponTableList");
	self.payBankTxnfInputNo = new Input("#payBankTxnfInputNo");
    self.payBankTxnfInputAmt = new NumberInput("#payBankTxnfInputAmt");
    self.payBankTxnfInputDate = new Input("#payBankTxnfInputDate");
    self.payBankTxnfDropDownBankBrnh = new DropDown("#payBankTxnfDropDownBankBrnh").data([{ key: "0123", value: "ปากเกร็ด" }]);
    self.payBankTxnfDropDownBankAcct = new DropDown("#payBankTxnfDropDownBankAcct").data([{ key: "1", value: "123-6-02069-3" }]);
    self.payBankTxnfDropDownBankAcCd = new DropDown("#payBankTxnfDropDownBankAcCd").data([{ key: "1", value: "11021044" }]);
	self.payBankTxnfButtonSubmit = new Button("#payBankTxnfButtonSubmit");
	self.payBankTxnfTableList = new Table("#payBankTxnfTableList");
	self.payOffsetDocumentNo = new Input("#payOffsetDocumentNo");
	self.payOffsetAccountCode = new Input("#payOffsetAccountCode");
	self.payOffsetAmount = new NumberInput("#payOffsetAmount");
	self.payOffsetAccountName = new Input("#payOffsetAccountName");
	self.payOffsetButtonSubmit = new Button("#payOffsetButtonSubmit");
	self.payOffsetTableList = new Table("#payOffsetTableList");
	self.payOtherDropDownChannel = new DropDown("#payOtherDropDownChannel");
	self.payOtherInputNo = new Input("#payOtherInputNo");
	self.payOtherInputDate = new Input("#payOtherInputDate");
	self.payOtherInputAmt = new NumberInput("#payOtherInputAmt");
	self.payOtherButtonSubmit = new Button("#payOtherButtonSubmit");
	self.payOtherTableList = new Table("#payOtherTableList");
	self.inputReceived = new NumberInput("#inputReceived");
	self.inputAdvanced = new NumberInput("#inputAdvanced");

    self.payBankTxnfInputBankCodeGf = new Input("#payBankTxnfInputBankCodeGf");
    self.payBankTxnfInputNoGf = new Input("#payBankTxnfInputNoGf");
    self.payBankTxnfInputAmtGf = new NumberInput("#payBankTxnfInputAmtGf");
    self.payBankTxnfInputDateGf = new Input("#payBankTxnfInputDateGf");
    self.payBankTxnfDropDownBankNameGf = new DropDown("#payBankTxnfInputBankNameGf").data([{ key: "006", value: "กรุงไทย" }]);
    self.payBankTxnfDropDownBankBrnhGf = new DropDown("#payBankTxnfDropDownBankBrnhGf").data([{ key: "0123", value: "ปากเกร็ด" }]);
    self.payBankTxnfDropDownBankAcctGf = new DropDown("#payBankTxnfDropDownBankAcctGf").data([{ key: "1", value: "123-6-02069-3" }]);
    self.payBankTxnfDropDownBankAcCdGf = new DropDown("#payBankTxnfDropDownBankAcCdGf").data([{ key: "1", value: "11021044" }]);
    self.payBankTxnfButtonSubmitGf = new Button("#payBankTxnfButtonSubmitGf");
    self.payBankTxnfTableListGf = new Table("#payBankTxnfTableListGf");

    self.currencyTypeSelect = new DropDown("#currencyTypeSelect").init("../exchangeRateList.json");//.data([{ key: "0", value: "กรุณาเลือกสกุลเงิน" },{ key: "001", value: "USD" }]);
    self.exchangeRateInput = new Input("#exchangeRateInput");
    self.payDateInputDate = new Input("#payDateInputDate");
    self.foreignAmountInput = new NumberInput("#foreignAmountInput");
    self.thAmountInput = new NumberInput("#thAmountInput");
    self.payForeignButtonSubmit = new Button("#payForeignButtonSubmit");
    self.payForeignTableList = new Table("#payForeignTableList");
    self.payBankTxnfDropDownBank = new DropDown("#payBankTxnfDropDownBank").data([{ key: "01", value: "121-2-11111-2" }]);

	self.allowedCurrenyCode = true;
	self.containsForeignCurrencyCode = false;

    $('#payChqBankCode').click(function () {
        var key = view.input.payChqBankCode.key();
        $('#payChqBankName option:selected').prop('selected', false);
        $('#payChqBankName option[data-key='+key+']').prop('selected', true);
    });
    $('#payChqBankName').click(function () {
        var key = view.input.payChqBankName.key();
        $('#payChqBankCode option:selected').prop('selected', false);
        $('#payChqBankCode option[data-key='+key+']').prop('selected', true);
    });
	$('#payBankTxnfInputBankCode').click(function () {
        var key = view.input.payBankTxnfDropDownBankCode.key();
        $('#payBankTxnfInputBankName option:selected').prop('selected', false);
        $('#payBankTxnfInputBankName option[data-key='+key+']').prop('selected', true);
    });
    $('#payBankTxnfInputBankName').click(function () {
        var key = view.input.payBankTxnfDropDownBankName.key();
        $('#payBankTxnfInputBankCode option:selected').prop('selected', false);
        $('#payBankTxnfInputBankCode option[data-key='+key+']').prop('selected', true);
    });

	return this;
})(jQuery);

var exchange, currencyCode, currencyRate, currencyType;
$(document).ready(function () {
	$(document).on("click", "#cancelPaymentBtn", function() {
		unLockInvoice();
	});
    var isStateAgency = view.session("isStateAgency");
    //alert('333 '+isStateAgency);
    if(isStateAgency=='2'){
        $("#69tvi").prop('checked', 'checked');
    }else{
        $("#3trs").prop('checked', 'checked');
    }

        $("#btSelectAll").on( "click", function() {
            var btSelectChecked =  $( this ).prop('checked');
            $( "input[name^='subnoChecked_']" ).each(function(i,e) {
                $(this).prop('checked',btSelectChecked);
            });
            console.log( btSelectChecked );

        });
    $('#creditLimitDialog').on('hidden.bs.modal', function () {
       console.log(" creditLimitDialog close")
    })
    view.button.creditLimit.disable();
// 	initialPage();
    // Credit Limit.
    view.session("genCreditLimit","N");
    $.get("../checkBillingGroupCreditLimit.json", { "billingGroupCode":view.session("billingGroupCode") }, function(res) {
        console.log("checkBillingGroupCreditLimit["+res+"]");
        if(res>0) {
            view.button.creditLimit.enable();
            view.session("genCreditLimit","Y");
        }
    })
});

// function checkboxFormatter(val, row, ind){
function checkboxFormatter(ind){
    return "<input type=\"checkbox\" name=\"subnoChecked_\""+ind+" value=\""+val+"\" data-index=\""+ind+"\">";
}
function initialPage(){
	view.input.deduct.val(0);
	calculate();
}
var exchangeRate = null;
var checkSpecial = "";
var sumAfterDiscountVat = 0;
function setup(){
	var billingList = view.session("billingList");
    console.log('xxxx5555');console.log(billingList);console.log(billingList[0].invoiceList[0].currencyCode);console.log('xxxx555');
	view.input.deduct.val(0);
	var sumCurrentDeduct = view.table.deductionList.sum(2);
	view.table.deductionList.onDelete(function(data) {
		updateDeduction();
		calculate();
	});
	view.table.payTypeList.onDelete(function(data) {
		if (data.length < 1) {
			view.button.submitPayment.disable();
			view.button.submitPayment.disable();
		}
		calculate();
	});
	view.radio.specialOptions.click(function(val) {
		checkSpecial = val;
		console.log("CheckSpecial :::: "+checkSpecial)
		var note = "";
		if (val == "3") {
// 		params["checkSpecial"] = checkSpecial;
		
		if(view.table.customerList.data().length == 1){
			var advanceAmount =  view.input.change.val() + view.inputAdvanced.val();
			view.table.customerList.data($.map(billingList, function(o) { return $.extend(o, { "advanceAmount": advanceAmount }) }));
		}
		view.inputAdvanced.disable();
		view.dialog.advancePayment.show();
		view.dialog.advancePaymentMessage.hide(); return;
        }else if(val == "2") {
// 			params["checkSpecial"] = checkSpecial;
            view.inputAdvanced.disable();
            view.dialog.otherProfitVat.show();

            var otherAmount =  view.input.change.val() + view.inputAdvanced.val();
            view.table.customerList2.data($.map(billingList, function(o) { return $.extend(o, { "otherAmount": otherAmount }) }));

            view.dialog.advancePaymentMessage2.hide(); return;
        }else if(val == "1") {
// 			params["checkSpecial"] = checkSpecial;
            view.inputAdvanced.disable();
            view.dialog.otherProfitVat.show();

            var otherAmount =  view.input.change.val() + view.inputAdvanced.val();
            view.table.customerList2.data($.map(billingList, function(o) { return $.extend(o, { "otherAmount": otherAmount }) }));

            view.dialog.advancePaymentMessage2.hide(); return;
        }else{
            view.inputAdvanced.enable();
        }
	});
	view.button.submitPayment.disable();
	view.button.submitPaymentEng.disable();
	//
	///////////////////////////////////////////////
	var amount = 0, discount = 0, vat = 0, balanceDue = 0, totalCharge = 0, deduct = 0, itemsDiscount = 0, preItemsDiscount = 0, vat2 = 0;
	var invoiceNoList = [], currencyCodeList = [];//{ key: "0", value: "กรุณาเลือก" } 03-05-2017
	view.table.customerList.data($.map(billingList, function(o) { return $.extend(o, { "svcType": "", "advanceAmount": 0 }) }));
	view.table.customerList2.data($.map(billingList, function(o) { return $.extend(o, { "svcType": "", "otherAmount": 0 }) }));
	$.each(billingList, function(i,o){
		$.each(o.invoiceList, function(j,p){
			var isPartial = p.balanceDue - p.received != 0; if (!p.checked) return;
			vat += p.calculatedVat;
			balanceDue += p.balanceDue;
			totalCharge += p.received;
			discount += p.afterSaleDiscount;
			//deduct += p.deduction;
            itemsDiscount += p.discount; //by NSD 29-03-2017
			var isStateAgency = p.isStateAgency;
			deduct += calculateVatWT(p.balanceDue, p.received, p.vatRate, p.deduction, p.totalCharge, p.wtAvg);
//			p.deduction = 0;
			if(p.currencyCode != null && p.currencyCode.length > 0){
				currencyCodeList.push(p.currencyCode);
			}
            //by NSD 03-04-2017
            if(p.discountType == "2"){
                vat2+= (p.calculatedVat - p.afterSaleDiscVat);//(p.received - p.calculatedVat - p.afterSaleDiscount)*p.vatRate/100;
            }else{
                vat2+= p.calculatedVat;
            }
			invoiceNoList.push({ key: p.no, value: p.no });
		})
	});
	console.log(currencyCodeList);

	if(currencyCodeList.length > 1) {
		for(var i=0; i<currencyCodeList.length - 1 ; i++) {
			if(currencyCodeList[i] !== currencyCodeList[i+1]) {
				view.dialog.mainMessageDialog.clear().error(["ระบบไม่อนุญาตให้จ่าย เนื่องจากมี Invoice สกุลเงินต่างกัน โปรดกลับไปเลือกรายการใหม่ หรือ นำรายการ Invoice สกุลเงินต่างประเทศออก"]).show();
				view.allowedCurrenyCode = false;
				break;
			}
		}
	}

	for(var i=0; i<currencyCodeList.length; ++i) {
		view.containsForeignCurrencyCode = currencyCodeList[i] !== "12"; // 12 = TH Currency Code
		break;
	}
	var billingCurrency = view.session("billingCurrency");

	if(view.containsForeignCurrencyCode && view.allowedCurrenyCode && billingCurrency != null && billingCurrency != "" &&  billingCurrency!="th_TH" ) {
		//var url = "../service/xchng/search/dt-code"; params = { "dts": parent.view.utils.todayDate_yyyymmdd(), "dte": parent.view.utils.todayDate_yyyymmdd(), "code": "US" };
		var url = "../findCurrentExchangeRate.json"; params = { "dts": parent.view.utils.todayDate_yyyymmdd(), "dte": parent.view.utils.tmrDate_yyyymmdd(), "code": view.session("billingCurrency") };
		console.log(url);
		console.log(params);
		// var exchangeRate = null;
		function ExchangeRateHandler(res) {
			if(res && res.data) {
				window.exchangeRateObj = res.data[0];
				if(window.exchangeRateObj) {
					exchangeRate = window.exchangeRateObj.rateUnit;
					exchangeDate = window.exchangeRateObj.dateUsedShow;
					exchangeCurrency = window.exchangeRateObj.message;
                    $("#foreignTotalChargeLabel").html("ยอดเงินที่ต้องชำระรวมภาษีมูลค่าเพิ่ม (<span style='color:#000000;'>"+exchangeCurrency+"</span>)  :");
				}
			}
		}
		getSync(url, params, ExchangeRateHandler, view.dialog.mainMessageDialog);
		if(view.session("billingCurrency") != null && (view.session("billingCurrency").indexOf("TH") == -1)){
			view.dialog.message.warn(["กำลังทำรายการ Invoice สกุลเงินต่างประเทศด้วยอัตราแลกเปลี่ยน "+exchangeRate+" ("+$.trim(window.exchangeRateObj.message)+") ตามอัตราแลกเปลี่ยนวันที่ "+exchangeDate]).show();
		}

		if(exchangeRate != null ) {
// 			view.input.foreignTotalCharge.val(totalCharge - deduct - discount); // use minus deduct because haven't multiply by -1
			view.input.foreignTotalCharge.val(totalCharge - sumCurrentDeduct - discount); // use minus deduct because haven't multiply by -1
			view.input.foreignExchangeRate.val(exchangeRate);
			deduct *= exchangeRate; vat *= exchangeRate; totalCharge *= exchangeRate; discount *= exchangeRate;
            vat2 *= exchangeRate;//by NSD 20-04-2017
			sumCurrentDeduct *= exchangeRate;
		} else {
			view.dialog.mainMessageDialog.clear().error(["ระบบไม่อนุญาตให้จ่าย เนื่องจากสกุลเงินต่างประเทศไม่มีอัตราแลกเปลี่ยนระบุในฐานข้อมูล โปรดแจ้ง admin"]).show();
			view.allowedCurrenyCode = false;
			view.div.foreignTotalChargeDiv.hide();
			view.div.foreignExchangeDiv.hide();
		}
	} else {
		view.div.foreignTotalChargeDiv.hide();
		view.div.foreignExchangeDiv.hide();
	}
	// Fix By EPIS8 issue no 4, 6, 9
//	alert("p.totalCharge :"+invoice.totalCharge+" p.received :"+invoice.received)
//	if(invoice.totalCharge != invoice.received)
//		view.input.withholdingTaxAmount.val((totalCharge - vat) * 0.03);
//	else
//		view.input.withholdingTaxAmount.val(deduct);
	// End Fix By EPIS8 issue no 4, 6, 9


	//deduct *= -1;
	sumCurrentDeduct *= -1;
	// Fix By EPIS8 issue no 4, 6, 9
	//view.input.withholdingTaxAmount.val(deduct);
	/*if(deduct == 0)
		view.input.withholdingTaxAmount.val(deduct);
	else
		view.input.withholdingTaxAmount.val((totalCharge - vat) * 0.03);
	*/
	view.input.withholdingTaxAmount.val(deduct);
	// End Fix By EPIS8 issue no 4, 6, 9
	//view.input.charge.val(totalCharge - vat);
    view.input.charge.val(totalCharge.toFixed(2) - vat.toFixed(2));//by NSD 27-04-2017
	view.input.vat.val(vat);
	view.input.totalCharge.val(totalCharge);

    view.input.itemsDiscount.val(itemsDiscount);
    //view.input.preItemsDiscount.val(totalCharge - vat + itemsDiscount);
    view.input.preItemsDiscount.val(view.input.charge.val() + itemsDiscount);//by NSD 27-04-2017

// 	view.input.deduct.val(deduct);
	view.input.deduct.val(sumCurrentDeduct);
	view.input.discount.val(discount);
// 	view.input.balanceDue.val(totalCharge + deduct - discount);
	//view.input.balanceDue.val(totalCharge + sumCurrentDeduct - discount);
    view.input.balanceDue.val(((totalCharge-vat)-discount)+vat2 + sumCurrentDeduct);//by NSD 03-04-2017
    view.input.moneyToPay.val(((totalCharge-vat)-discount)+vat2 + sumCurrentDeduct);//by NSD 11-04-2017
	view.radio.specialOptions.disable(2, true);
    view.radio.specialOptions.disable(1, true);
    view.radio.specialOptions.disable(0, true);
	view.input.withholdingInvoiceNo.data(invoiceNoList);
	view.input.penaltyInvoiceNo.data(invoiceNoList);
	view.input.retentionInvoiceNo.data(invoiceNoList);

	// Add to support GFMIS
	if("${epContext.roleName}" == "GFMISAGENT") {
		view.button.setPayType.index(10); // BANKTRANSFER
		view.panel.pay.index(10);
		view.payBankTxnfInputBankCodeGf.val("006");
		view.payBankTxnfDropDownBankNameGf.index(1); // can use .key("006")
		view.payBankTxnfDropDownBankBrnhGf.index(1); // .key("0123")
		view.payBankTxnfDropDownBankAcctGf.index(1); // .key("1")
		view.payBankTxnfDropDownBankAcCdGf.index(1); // .key("1")
		view.payBankTxnfInputAmtGf.val(view.input.balanceDue.val());
		for(var hideInd = 0, end = 9; hideInd <= end; ++hideInd) view.button.setPayType.hideIndex(hideInd);
//        view.button.setPayType.hideIndex(10);
    }else if("${epContext.roleName}" == "TRANSFER"){
    	view.button.setPayType.index(6); // BANKTRANSFER
		//view.panel.pay.index(6);
    	for(var hideInd = 0, end = 5; hideInd <= end; ++hideInd) view.button.setPayType.hideIndex(hideInd);
		for(var hideInd = 7, end = 7; hideInd <= end; ++hideInd) view.button.setPayType.hideIndex(hideInd);
		for(var hideInd = 9, end = 10; hideInd <= end; ++hideInd) view.button.setPayType.hideIndex(hideInd);
    }else if("${epContext.roleName}" == "TRANSIN"){
    	view.button.setPayType.index(6); // BANKTRANSFER
    	for(var hideInd = 0, end = 5; hideInd <= end; ++hideInd) view.button.setPayType.hideIndex(hideInd);
		for(var hideInd = 7, end = 10; hideInd <= end; ++hideInd) view.button.setPayType.hideIndex(hideInd);
   
    	
    }else {
        view.button.setTaxType.hideIndex(3); // retention
        view.button.setTaxType.hideIndex(4); // compensation
		view.button.setPayType.hideIndex(10);
		view.div.retentionSummaryDiv.hide();
		view.div.compensationSummaryDiv.hide();
	}

	var debtAmt = Math.max(0, view.input.balanceDue.val() - view.inputReceived.val());
	view.input.payCashAmount.val(debtAmt);
	view.input.payChqAmount.val(debtAmt);
	view.input.payCCAmount.val(debtAmt);
	view.payMoneyOrderInputAmount.val(debtAmt);
	view.payBillExchgInputAmount.val(debtAmt);
	view.payCouponInputAmt.val(debtAmt);
	view.payBankTxnfInputAmt.val(debtAmt);
	view.payBankTxnfInputAmtGf.val(debtAmt);
	view.payOffsetAmount.val(debtAmt);
	view.payOtherInputAmt.val(debtAmt);
	view.thAmountInput.val(debtAmt);
	
	if(null == view.session("creditLimitData")) {
		var subScriptionList = view.session("subScriptionList");
		if(subScriptionList.length == 1) {
			var creditLimitData_S = "S|";
			for(var i = 0, m = billingList.length; i < m; i++) {
				for(var j = 0, n = billingList[i].invoiceList.length; j < n; j++) {
					var invoice = billingList[i].invoiceList[j];
					if(true == invoice.checked) {
						creditLimitData_S += invoice.no +'-'+ $.trim(subScriptionList[0]) +'|';
					}
				}
			}
			view.session("creditLimitData", creditLimitData_S);
			view.session("subScriptionData", subScriptionList[0]);
		} else {
			var creditLimitData_M = "M|";
			for(var i = 0, m = billingList.length; i < m; i++) {
				for(var j = 0, n = billingList[i].invoiceList.length; j < n; j++) {
					var invoice = billingList[i].invoiceList[j];
					if(true == invoice.checked) {
						creditLimitData_M += invoice.no +'-|';
					}
				}
			}
			view.session("creditLimitData", creditLimitData_M);
			view.session("subScriptionData", subScriptionList.length);
		}
	}

};
function calculateVatFromIncluded(received, vatRate) { return (received / (100 + vatRate)) * vatRate }
function updateDeduction() {
	view.input.deduct.val(view.table.deductionList.typeSum(0));
	view.input.fee.val(view.table.deductionList.typeSum(1));
	view.input.penalty.val(view.table.deductionList.typeSum(2));
	view.input.retention.val(view.table.deductionList.typeSum(3));
	view.input.compensation.val(view.table.deductionList.typeSum(4));
}
function calculate() {
    var balanceDue = 0;
    if(view.input.totalCharge.val()>0){
        balanceDue = view.input.moneyToPay.val();
    }else{
        balanceDue = (view.input.totalCharge.val() * 100) / 100;
    }
	var received = view.table.payTypeList.sum(2);
	var cash = stripToNumber(view.table.payTypeList.find("method", 0).find("td:eq(2) div").text());
	var nonCash = ((received * 100) - (cash * 100))/100;
	var advanced = view.inputAdvanced.val();
	var change = 0;
	var sumCurrentDeduct = view.table.deductionList.sum(2);
	var afterSaleDiscount = view.input.discount.val();
	balanceDue += sumCurrentDeduct;
	//balanceDue -= afterSaleDiscount;//comment by NSD 04-04-2017
	console.log("Initial: change-> "+change+", cash-> "+cash+", nonCash->"+nonCash+ ", adv->"+advanced);
	if (advanced > 0) {
		change = (((received * 100) - (balanceDue * 100)) - (advanced * 100))/100;
		if (change > cash) change = cash;
		if (change < 0) {
			advanced = ((advanced * 100) + (change * 100))/100; // this will decrease advanced value
			change = 0;
		}
		if (received <= 0) {
			advanced = 0;
			change = 0;
		} else if(nonCash > 0) {
			var diff = ((nonCash * 100) - (balanceDue * 100))/100;
			if( diff > 0) {
				var newadvanced = diff;
				if(newadvanced > advanced) advanced = newadvanced;
				console.log("step A1: change-> "+change+", cash-> "+cash+ ", adv->"+advanced);
			} else {
				advanced = 0;
				change = cash > 0 ? ((cash * 100) + (diff * 100))/100 : 0;
				if(change < 0) change = 0;
				console.log("step A2: change-> "+change+", cash-> "+cash+ ", adv->"+advanced);
			}
		}
		console.log("step A: change-> "+change+", cash-> "+cash+", adv->"+advanced);
	} else if (nonCash > 0) {
		var diff = ((nonCash * 100) - (balanceDue * 100))/100;
		if( diff > 0) {
			advanced = diff;
			change = cash;
			console.log("step B: change-> "+change+", cash-> "+cash+ ", adv->"+advanced);
		} else {
			advanced = 0;
			change = cash > 0 ? ((cash * 100) + (diff * 100))/100 : 0;
			if(change < 0) change = 0;
			console.log("step C: change-> "+change+", cash-> "+cash+ ", adv->"+advanced);
		}
	} else {
		var diff = ((cash * 100) - (balanceDue * 100))/100;
		advanced = 0;
		change = (diff < 0) ? 0 : diff;
		console.log("step D: change-> "+change+", cash-> "+cash+ ", adv->"+advanced);
	}
	view.input.balanceDue.val(balanceDue);
	view.inputReceived.val(received);
	view.input.change.val(change);
	view.inputAdvanced.disable(advanced > 0).val(advanced);
	view.radio.specialOptions.disable(2, change == 0 && advanced == 0);
    view.radio.specialOptions.disable(1, change == 0 && advanced == 0);//by NSD 02-04-2017
    view.radio.specialOptions.disable(0, change == 0 && advanced == 0);

	var debtAmt = Math.max(0, balanceDue - view.inputReceived.val());
	view.input.payCashAmount.val(debtAmt);
	view.input.payChqAmount.val(debtAmt);
	view.input.payCCAmount.val(debtAmt);
	view.payMoneyOrderInputAmount.val(debtAmt);
	view.payBillExchgInputAmount.val(debtAmt);
	view.payCouponInputAmt.val(debtAmt);
	view.payBankTxnfInputAmt.val(debtAmt);
	view.payBankTxnfInputAmtGf.val(debtAmt);
	view.payOffsetAmount.val(debtAmt);
	view.payOtherInputAmt.val(debtAmt);
	view.thAmountInput.val(debtAmt);
    if(change == 0 && advanced == 0){
        view.radio.specialOptions.unchecked();
    }
}
function calculate2() {
	var balanceDue = (view.input.totalCharge.val() * 100) / 100;
	var received = view.table.payTypeList.sum(2);
	var cash = stripToNumber(view.table.payTypeList.find("method", 0).find("td:eq(2) div").text());
	var nonCash = ((received * 100) - (cash * 100))/100;
	var advanced = view.inputAdvanced.val();
	var change = 0;
	var sumCurrentDeduct = view.table.deductionList.sum(2);
	var afterSaleDiscount = view.input.discount.val();
	balanceDue += sumCurrentDeduct;
	balanceDue -= afterSaleDiscount;
	console.log("Initial: change-> "+change+", cash-> "+cash+", nonCash->"+nonCash+ ", adv->"+advanced);
	if(received!=0) {
		if (advanced > 0) {
			change = (((received * 100) - (balanceDue * 100)) - (advanced * 100))/100;
			if (change > cash) change = cash;
			if (change < 0) {
				advanced = ((advanced * 100) + (change * 100))/100; // this will decrease advanced value
				change = 0;
			}
			if (received <= 0) {
				advanced = 0;
				change = 0;
			} else if(nonCash > 0) {
				var diff = ((nonCash * 100) - (balanceDue * 100))/100;
				if( diff > 0) {
					var newadvanced = diff;
					if(newadvanced > advanced) advanced = newadvanced;
					console.log("step A1: change-> "+change+", cash-> "+cash+ ", adv->"+advanced);
				} else {
					advanced = 0;
					change = cash > 0 ? ((cash * 100) + (diff * 100))/100 : 0;
					if(change < 0) change = 0;
					console.log("step A2: change-> "+change+", cash-> "+cash+ ", adv->"+advanced);
				}
			}
			console.log("step A: change-> "+change+", cash-> "+cash+", adv->"+advanced);
		} else if (nonCash > 0) {
			var diff = ((nonCash * 100) - (balanceDue * 100))/100;
			if( diff > 0) {
				advanced = diff;
				change = cash;
				console.log("step B: change-> "+change+", cash-> "+cash+ ", adv->"+advanced);
			} else {
				advanced = 0;
				change = cash > 0 ? ((cash * 100) + (diff * 100))/100 : 0;
				if(change < 0) change = 0;
				console.log("step C: change-> "+change+", cash-> "+cash+ ", adv->"+advanced);
			}
		} else {
			var diff = ((cash * 100) - (balanceDue * 100))/100;
			advanced = 0;
			change = (diff < 0) ? 0 : diff;
			console.log("step D: change-> "+change+", cash-> "+cash+ ", adv->"+advanced);
		}
	}
	view.input.balanceDue.val(balanceDue);
	view.inputReceived.val(received);
	view.input.change.val(change);
	view.inputAdvanced.disable(advanced >= 0).val(advanced);
	view.radio.specialOptions.disable(2, change == 0 && advanced == 0);
    view.radio.specialOptions.disable(1, change == 0 && advanced == 0);
    view.radio.specialOptions.disable(0, change == 0 && advanced == 0);


	var debtAmt = Math.max(0, balanceDue - view.inputReceived.val());
	view.input.payCashAmount.val(debtAmt);
	view.input.payChqAmount.val(debtAmt);
	view.input.payCCAmount.val(debtAmt);
	view.payMoneyOrderInputAmount.val(debtAmt);
	view.payBillExchgInputAmount.val(debtAmt);
	view.payCouponInputAmt.val(debtAmt);
	view.payBankTxnfInputAmt.val(debtAmt);
	view.payBankTxnfInputAmtGf.val(debtAmt);
	view.payOffsetAmount.val(debtAmt);
	view.payOtherInputAmt.val(debtAmt);
	view.thAmountInput.val(debtAmt);
    if(change == 0 && advanced == 0){
        view.radio.specialOptions.unchecked();
    }
}
$("#check_dis").change(function () { if ($(this).prop("checked")) {$("#modal_authen").modal('show');} });
$("#testmenu li a").click(function () { $(this).parents(".btn-group").find('.selection').text($(this).text()); $(this).parents(".btn-group").find('.selection').val($(this).text()); });
$("#testmenu2 li a").click(function () { $(this).parents(".btn-group").find('.selection').text($(this).text()); $(this).parents(".btn-group").find('.selection').val($(this).text()); });

function addTaxTypeClickEvent() {
	var index = view.button.setTaxType.index(), value = 0;
	if (index === 0)        { value = view.table.withholdingTaxList.deduct(4); 		window.wthList = view.table.withholdingTaxList.data() }
	else if (index === 1)   { value = view.table.feeTaxList.calcBalance(2,5); 		window.feeList = view.table.feeTaxList.data() }
	else if (index === 2)   { value = view.table.penaltyTaxList.calcBalance(2,3); 	window.pntyList = view.table.penaltyTaxList.data() }
	else if (index === 3)   { value = view.table.retentionTaxList.calcBalance(2,3); window.rttList = view.table.retentionTaxList.data() }
	else if (index === 4)   { value = view.table.compensationTaxList.deduct(2);		window.cmpstList = view.table.compensationTaxList.data() }
	view.table.deductionList.find("type", index).remove();
	view.table.deductionList.insert(["-", view.button.setTaxType.val(), value], true, { "type": index });
	updateDeduction();
	calculate();
}
function addPayTypeClickEvent() {
	var index = view.button.setPayType.index(), payAmt = view.input.payCashAmount.val();
	if (index == 1)      { window["payChqListData"] = view.table.payChqList.data();                         payAmt = view.table.payChqList.sum(6)        }
	else if (index == 2) { window["payCCListData"] = view.table.payCCList.data();                           payAmt = view.table.payCCList.sum(4)         }
	else if (index == 3) { window["payMoneyOrderTableListData"] = view.payMoneyOrderTableList.data();       payAmt = view.payMoneyOrderTableList.sum(4)  }
	else if (index == 4) { window["payBillExchgTableListData"] = view.payBillExchgTableList.data();         payAmt = view.payBillExchgTableList.sum(4)   }
	else if (index == 5) { window["payCouponTableListData"] = view.payCouponTableList.data();               payAmt = view.payCouponTableList.sum(4)      }
	else if (index == 6) { window["payBankTxnfTableListData"] = view.payBankTxnfTableList.data();           payAmt = view.payBankTxnfTableList.sum(6)    }
	else if (index == 7) { window["payOffsetTableListData"] = view.payOffsetTableList.data();               payAmt = view.payOffsetTableList.sum(4)      }
	else if (index == 9) { window["payOtherTableListData"] = view.payOtherTableList.data();                 payAmt = view.payOtherTableList.sum(4)       }
    else if (index == 10) { window["payBankTxnfTableListGfData"] = view.payBankTxnfTableListGf.data();      payAmt = view.payBankTxnfTableListGf.sum(6)  }
    else if (index == 8){ window["payForeignTableListData"] = view.payForeignTableList.data();              payAmt = view.payForeignTableList.sum(5)     }
	view.table.payTypeList.find("method", index).remove();
	view.table.payTypeList.insert(["-", view.button.setPayType.val(), payAmt], true, { method: index });
	view.inputReceived.val(view.table.payTypeList.sum(2));
	if(view.allowedCurrenyCode) {
		view.button.submitPayment.enable();
		view.button.submitPaymentEng.enable();
	}
	updateDeduction();
	calculate();
	addExchangRate();
}
function submitPayment(receiptLang) {
    var radioChkVal = $("input[name='specialOptions']:checked").val();
	var balanceDue = view.input.balanceDue.val(), received = view.inputReceived.val(), change = view.input.change.val(), advance = view.inputAdvanced.val(), deduction = Math.abs(view.input.deduct.val());
	if (balanceDue > received) {
		view.dialog.mainMessageDialog.clear().error(["ระบบไม่อนุญาตให้จ่าย เนื่องจากจำนวนเงินไม่ถูกต้อง"]).show();
		return false;
	}
	if (subtract(received, balanceDue) != add(advance, change)) {
		view.dialog.mainMessageDialog.clear().error(["ระบบไม่อนุญาตให้จ่าย เนื่องจากจำนวนเงินที่ทำรายการชำระล่วงหน้าไม่ถูกต้อง"]).show();
		return false;
	}
	var billingList = view.session("billingList"), sumBalanceDue = 0, sumReceived = 0, difBalanceDue = 0, difReceived = 0;
	for (var i = 0, m = billingList.length; i < m; i++) {
		for (var j = 0, n = billingList[i].invoiceList.length; j < n; j++) {
			var invoice = billingList[i].invoiceList[j]; if (invoice.no == "-" || invoice.no == "Advance Payment") continue;
			if (invoice.checked) { sumBalanceDue += invoice.balanceDue; sumReceived += invoice.received; }
			else                 { difBalanceDue += invoice.balanceDue; difReceived += invoice.received; }
		}
	}
	console.log("++++++++++++++++++++++++++++++++++++++++++");
	console.log(billingList);
	console.log("sumBalanceDue : "+sumBalanceDue +" , sumReceived : "+sumReceived+" , advance : "+advance+" , difBalanceDue : "+difBalanceDue);
	var hasDueAmount = false;
	for(var i = 0 ; i < billingList.length ; i++){
		for (var j = 0; j < billingList[i].invoiceList.length; j++) {
			var invoice = billingList[i].invoiceList[j]; if (invoice.no == "-" || invoice.no == "Advance Payment") continue;//by NSD 24-03-2017
			console.log("invoice.balanceDue : "+invoice.balanceDue +" , invoice.received : "+invoice.received);
			if((invoice.balanceDue - invoice.received) > 0){
				//Check advance payment that have or not
				if (view.inputAdvanced.val() > 0) {
					var inputs = view.table.customerList.elem.find("input");
					var inputs2 = view.table.customerList2.elem.find("input");
					$.each(view.table.customerList.data(), function(k,o) {
						var totalCharge = stripToNumber(inputs[k].value);
						console.log("billingList["+i+"].custNo : "+billingList[i].custNo+" , o.custNo : "+o.custNo +" , Advance Total Charge : "+totalCharge);
						if(billingList[i].custNo == o.custNo && totalCharge > 0){
							hasDueAmount = true;
						}
					});
					$.each(view.table.customerList2.data(), function(k,o) {
						var totalCharge = stripToNumber(inputs2[k].value);
						console.log("billingList["+i+"].custNo : "+billingList[i].custNo+" , o.custNo : "+o.custNo +" , Advance Total Charge : "+totalCharge);
						if(billingList[i].custNo == o.custNo && totalCharge > 0){
							hasDueAmount = true;
						}
					});
				}
			}
		}
	}

	console.log("hasDueAmount : "+hasDueAmount);
	if (advance > 0) {
        var inputs = view.table.customerList.elem.find("input"), totalAdvance = 0, totalAdvance1 = 0, total = 0;
        var inputs2 = view.table.customerList2.elem.find("input"), totalAdvance2 = 0;
        var customerList = view.table.customerList.data().length * 2, customerList2 = view.table.customerList2.data().length * 2;
       /*  $.each(view.table.customerList.data(), function(i,o) {
        	console.log(i);
        	console.log($('#advanceAmount'[i]).value);
            totalAdvance1 += stripToNumber(inputs[i].value);
            console.log("totalAdvance >>>> "+totalAdvance1);
        }); 
       		$.each(view.table.customerList2.data(), function(i,o) {
            totalAdvance2 += stripToNumber(inputs2[i].value);
        });*/
        console.log(customerList);
        for (i=0;i<customerList;i+=2) {
            totalAdvance1 += stripToNumber(inputs[i].value);
        }
        for (i=0;i<customerList2;i+=2) {
        	totalAdvance2 += stripToNumber(inputs2[i].value);
        }
        totalAdvance1 = (totalAdvance1*100)/100;
        totalAdvance2 = (totalAdvance2*100)/100;
        totalAdvance = totalAdvance1>0?totalAdvance1:totalAdvance2;
        if(totalAdvance != advance) {
            view.dialog.mainMessageDialog.clear().error(["ระบบไม่อนุญาตให้ดำเนินการ เนื่องจากเงินคงค้างอยู่ที่ช่องชำระเงินล่วงหน้า"]).show();
            return false;
        } else {
        	view.dialog.mainMessageDialog.clear();
        }
    } else {
    	view.dialog.mainMessageDialog.clear();
    }
	console.log("step1 validation passed.");
	var params = {
		 "amount": view.input.charge.val()
		,"payAmount": view.input.charge.val()
		,"discount": view.input.discount.val()
		,"vatAmount": view.input.vat.val()
		,"totalPaid": view.input.totalCharge.val()
		,"wtAmount": view.input.deduct.val()
		,"receiveAmount": view.inputReceived.val()
		,"remainAmount": view.input.change.val()
		,"paymentCase": $.map(view.table.payTypeList.data(), function(a,i){ return a[1] }).join(" + ") + (deduction > 0 ? " + WT" : "")
		,"remark": ""
		,"language":receiptLang
		,"creditLimitData":view.session("creditLimitData")
		,"note":$('#custNote').val()
		,"checkSpecial":checkSpecial
        ,"genCreditLimit":view.session("genCreditLimit")
	};
    var creditLimitTransList = view.session("creditLimitTransList");
    console.log("creditLimitTransList")
    console.log(creditLimitTransList)
    if (null!=creditLimitTransList) {
    	//console.log(creditLimitTransList[0]["contract"]);
		for(var i=0;i<creditLimitTransList.length;i++){
	        params["creditLimitTransList["+ i +"].contract"] = creditLimitTransList[i]["contract"];
	        params["creditLimitTransList["+ i +"].arRef"] = creditLimitTransList[i]["arRef"];
	        params["creditLimitTransList["+ i +"].msisdn"] = creditLimitTransList[i]["msisdn"];

            params["creditLimitTransList["+ i +"].amountIncVat"] = creditLimitTransList[i]["amountIncVat"];
            params["creditLimitTransList["+ i +"].vatAmount"] = creditLimitTransList[i]["vatAmount"];
            params["creditLimitTransList["+ i +"].amountExVat"] = creditLimitTransList[i]["amountExVat"];
            params["creditLimitTransList["+ i +"].accountNo"] = creditLimitTransList[i]["accountNo"];
            params["creditLimitTransList["+ i +"].arInvdate"] = creditLimitTransList[i]["arInvdate"];
            params["creditLimitTransList["+ i +"].arDuedate"] = creditLimitTransList[i]["arDuedate"];
            params["creditLimitTransList["+ i +"].received"] = creditLimitTransList[i]["received"];
            params["creditLimitTransList["+ i +"].msisdnSize"] = creditLimitTransList[i]["msisdnSize"];
            params["creditLimitTransList["+ i +"].status"] = creditLimitTransList[i]["status"];
            var msisdnList = creditLimitTransList[i]["msisdnList"];
            for(var k=0;k<msisdnList.length;k++){
                params["creditLimitTransList["+ i +"].msisdnList["+ k +"]"] = msisdnList[k];
            }
         }
    }


	console.log("step2 initial params set.");
	var deductIndex = 0;
	var gettingIncome = 'ขาเข้า', inType = '-IN', outType = '-OUT';
	var watPaymentCase = "WT ";
	// <!-- Deduction. -->
    //by NSD 17-03-2017
    var wtRow = view.table.deductionList.find("type", 0);
    var feeRow = view.table.deductionList.find("type", 1);
    var pntRow = view.table.deductionList.find("type", 2);
    var rttRow = view.table.deductionList.find("type", 3);
    var cmpRow = view.table.deductionList.find("type", 4);

	$.map((window["wthList"] || []), function(o, i) {
        if(wtRow.length>0) {
            params["deducts[" + deductIndex + "].type"] = o[3] == "69 ทวิ" ? "69BIS" : o[3] == "69 ตรี" ? "69TRE" : "3TREDECIM"; // 3TREDECIM, 69BIS, 69TRE
            params["deducts[" + deductIndex + "].withholdingDocNo"] = $.trim(o[2]);
            params["deducts[" + deductIndex + "].amount"] = o[4].replace(/[,]/g, "");
            params["deducts[" + deductIndex + "].invoiceNo"] = $.trim(o[1]);
            //console.log('SET WITHHOLDINGTAX '+o[0]+' : '+o[1]+' : '+o[2]+' : '+o[3]+' : '+o[4])
            deductIndex++;
            //watPaymentCase = watPaymentCase.concat("เลขที่: "+$.trim(o[2]+" + "));
            //if($.trim(o[2]).length>0)
            watPaymentCase = watPaymentCase.concat("" + $.trim(o[2] + " + "));
            //else
            //  watPaymentCase = (""+$.trim(o[2]+" + "));
        }
	});
	$.map((window["feeList"] || []), function(o, i) {
        if(feeRow.length > 0) {
            params["deducts[" + deductIndex + "].type"] = "FEE".concat((gettingIncome == o[2]) ? inType : outType); // FEE  ค่าธรรมเนียม
            params["deducts[" + deductIndex + "].amount"] = o[5].replace(/[,]/g, "");
            params["deducts[" + deductIndex + "].costCenter"] = $.trim(o[1]);
            params["deducts[" + deductIndex + "].withHoldingTax"] = o[3].replace(/[,]/g, ""); 
            params["deducts[" + deductIndex + "].taxAmt"] = o[4].replace(/[,]/g, "");
            deductIndex++;
        }
	});
	$.map((window["pntyList"] || []), function(o, i) {
        if(pntRow.length > 0) {
            params["deducts[" + deductIndex + "].type"] = "PTY".concat((gettingIncome == o[2]) ? inType : outType); // PENALTY ค่าปรับ
            params["deducts[" + deductIndex + "].amount"] = o[3].replace(/[,]/g, "");
            params["deducts[" + deductIndex + "].invoiceNo"] = $.trim(o[1]);
            deductIndex++;
        }
	});
	$.map((window["rttList"] || []), function(o, i) {
        if(rttRow.length > 0){
		var type =
		params["deducts["+ deductIndex +"].type"] = "RTTN".concat((gettingIncome == o[2])? inType : outType); // RETENTION ค่าประกันผลงาน
		params["deducts["+ deductIndex +"].amount"] = o[3].replace(/[,]/g, "");
		params["deducts[" + deductIndex + "].invoiceNo"] = $.trim(o[1]);
		deductIndex++;
        }
	});
	$.map((window["cmpstList"] || []), function(o, i) {
        if(cmpRow.length > 0) {
            params["deducts[" + deductIndex + "].type"] = "CMPST"; // COMPENSATE ค่าตอบแทนการรับชำระ
            params["deducts[" + deductIndex + "].amount"] = o[2].replace(/[,]/g, "");
            params["deducts[" + deductIndex + "].costCenter"] = $.trim(o[1]);
            deductIndex++;
        }
	});
	console.log("step3 Deduction set.");
	var paymentCase = "";
	// <!-- Pay Method. -->
	var methodIndex = 0, cashRow = view.table.payTypeList.find("method", 0);
	// Fix by EPIS8 23/12/2016 refer issue no.57
	var chequeRow = view.table.payTypeList.find("method", 1);
	var creditRow = view.table.payTypeList.find("method", 2);
	var moneyOrderRow = view.table.payTypeList.find("method", 3);
	var billExchangeRow = view.table.payTypeList.find("method", 4);
	var couponRow = view.table.payTypeList.find("method", 5);
	var moneyTransferRow = view.table.payTypeList.find("method", 6);
	var offsetRow = view.table.payTypeList.find("method", 7);
    var otherRow = view.table.payTypeList.find("method", 9);
    var moneyTransferGFRow = view.table.payTypeList.find("method", 10);
    var moneyForeignRow = view.table.payTypeList.find("method", 8);
    // End Fix by EPIS8 23/12/2016 refer issue no.57
    //var allPaycase = [ ["CASH", "CC", "เงินสด"], ["CHEQUE", "CH", "เช็ค"], ["CREDITCARD", "CR", "บัตรเครดิต"], ["MONEYORDER", "MO", "ธนาณัติ"], ["BILLEXCHANGE", "BX", "ตั๋วแลกเงิน"], ["COUPON", "CP", "คูปอง"], ["BANKTRANSFER", "TR", "เงินโอนในประเทศ"], ["OFFSET", "OF", "ofset"], ["FOREIGNTRANSFER", "TF", "เงินโอนต่างประเทศ"], ["OTHER", "OT", "อื่นๆ"], ["BANKTRANSFER", "GF", "เงินโอน (GFMIS)"]], payAdvance = {};
	var allPaycase = [ ["CASH", "CC", "เงินสด"], ["CHEQUE", "CH", "เช็ค"], ["CREDITCARD", "CR", "บัตรเครดิต"], ["MONEYORDER", "MO", "ธนาณัติ"], ["BILLEXCHANGE", "BX", "ตั๋วแลกเงิน"], ["COUPON", "CP", "คูปอง"], ["BANKTRANSFER", "TR", "เงินโอนในประเทศ"], ["OFFSET", "OF", "ofset"], ["FOREIGNTRANSFER", "TF", "เงินโอนต่างประเทศ"], ["OTHER", "OT", "อื่นๆ"], ["BANKTRANSFER", "GF", "เงินโอน (GFMIS)"] ], payAdvance = [];

    var change = 0 , amount = 0, countPay = 0, payIndex = [], amounts = [], receivedMoney = view.inputReceived.val(), balanceDue = view.input.balanceDue.val(), advance = view.inputAdvanced.val(), advanceTotal = 0, invTotal = 0, accountNo = [], countNormal=0, tmpChange = view.input.change.val();
    for (var i = 0, m = billingList.length; i < m; i++) { customer = billingList[i];  accountNo = accountNo.concat(customer.custNo); for (var j = 0, n = customer.invoiceList.length; j < n; j++) { var invoice = customer.invoiceList[j]; if (invoice.no == "Advance Payment") { advanceTotal += Number(invoice.totalCharge); } else { invTotal += Number(invoice.received); countNormal++; } } }
    console.log("advanceTotal is : "+advanceTotal);
    console.log("invTotal is : "+invTotal);
    
 	change = Number(receivedMoney) - Number(balanceDue);
 	//change > advanceTotal? change = change - advanceTotal : change = advanceTotal - change;
 	console.log("change is : "+change)
    for(var i=0;i<11;i++) { var payCashFlg = false, paycase = []; paycase[i] = view.table.payTypeList.find("method", i); amounts[i] = paycase[i].find("td:eq(2) div").text().replace(/[,]/g, ""); if (paycase[i].length > 0) { payIndex[countPay] = i; countPay++; } }
    //if (countPay > 0) { if (countPay > 1) { if(payIndex.indexOf(0)!='-1') {  amounts[payIndex.indexOf(0)] = Number(amounts[payIndex.indexOf(0)]) - change; amounts[payIndex.indexOf(0)] > advanceTotal?amounts[payIndex.indexOf(0)]=amounts[payIndex.indexOf(0)]-advanceTotal:amounts[payIndex.indexOf(0)]=advanceTotal-amounts[payIndex.indexOf(0)]; payAdvance = allPaycase[payIndex.indexOf(0)]; /*if (amounts[payIndex.indexOf(0)]<0) { Math.max(amounts); }*/ } else { amounts[payIndex[0]] = Number(amounts[payIndex[0]])- change; amounts[payIndex[0]] > advanceTotal?amounts[payIndex[0]]=amounts[payIndex[0]]-advanceTotal:amounts[payIndex[0]]=advanceTotal-amounts[payIndex[0]]; payAdvance = allPaycase[payIndex[0]];} } else { if(payIndex.indexOf(0)!='-1') { amounts[payIndex.indexOf(0)] = Number(amounts[payIndex.indexOf(0)]) - change; amounts[payIndex.indexOf(0)] > advanceTotal?amounts[payIndex.indexOf(0)]=amounts[payIndex.indexOf(0)]-advanceTotal:amounts[payIndex.indexOf(0)]=advanceTotal-amounts[payIndex[0]]; payAdvance = allPaycase[payIndex.indexOf(0)]; } else { amounts[payIndex[0]] = Number(amounts[payIndex[0]])- change; amounts[payIndex[0]] > advanceTotal?amounts[payIndex[0]]=amounts[payIndex[0]]-advanceTotal:amounts[payIndex[0]]=advanceTotal-amounts[payIndex[0]]; payAdvance = allPaycase[payIndex[0]]; } } }
   var tmpCash = [], payTotalAmount = 0, advAmount = [],flgAdv = false, tmpAmount = [], tmpTotal = 0, tmpIndex = [], tmpCcIndex, maxAmount = 0, tmp, advIntAmount = [], payAdvanceInt = [], tmpIdx = 0, isBackDate = false, transferDt;
   if (countPay > 0) { payTotalAmount = invTotal;
	   if(payIndex.indexOf(0)!='-1') {
	   		tmpCash = amounts[payIndex.indexOf(0)]; tmpCcIndex = payIndex.indexOf(0); tmpAmount = amounts.slice(); tmpAmount.splice(payIndex.indexOf(0), 1); tmpIndex = payIndex.slice(); tmpIndex.splice(payIndex.indexOf(0), 1); tmpAmount.push(tmpCash); tmpIndex.push(tmpCcIndex); tmpIdx = 1;
	   } else {
	   		tmpAmount = amounts; tmpIndex = payIndex;
	   }
   		inttTotal = change; var intTotal = 0, tmpInttTotal = 0;
	   if (change > 0) {
		   for (i=0;i<tmpIndex.length;i++) {
			   var changeIdx = 0;
			   if (tmpIndex[i]!=0) { changeIdx = tmpIndex[i] - tmpIdx; } else { changeIdx = 10; }
			   if (inttTotal>0) { tmpInttTotal = inttTotal; }
			   		inttTotal = inttTotal - tmpAmount[changeIdx];
			   if (inttTotal>=0) {
			   		advIntAmount[advIntAmount.length] = tmpAmount[changeIdx]; payAdvanceInt[payAdvanceInt.length] = allPaycase[tmpIndex[i]]; tmpAmount[changeIdx] = "";
			   } else {
			   if (inttTotal!=0) {
				   if (Number(advance)!=0) {
				   		advIntAmount[advIntAmount.length] = advance;/* tmpInttTotal!=0?tmpInttTotal:change; */ payAdvanceInt[payAdvanceInt.length] = allPaycase[tmpIndex[i]];
				   }
			   }
			   tmpAmount[changeIdx] = inttTotal*(-1);
			   break;
			   }
		   }
	   }
	   var advDocNo = [];
	   for (i=0;i<tmpIndex.length;i++) { var idx = 0;
	   		if (tmpIndex[i]!=0) { idx = tmpIndex[i] - tmpIdx; } else { idx = 10; }
	   		if (!flgAdv) {
	   			if (payTotalAmount>0) { tmpTotal = payTotalAmount; }
// 				payTotalAmount = payTotalAmount - tmpAmount[idx];
	   			if (payTotalAmount>=0) {
	   				payTotalAmount = payTotalAmount - tmpAmount[idx];
	   				if(payTotalAmount>=0){
	   					amounts[tmpIndex[i]] = tmpAmount[idx];
	   				}else{
	   					if (payTotalAmount!=0) {
			   				amounts[tmpIndex[i]] = tmpTotal!=0?tmpTotal:invTotal;
			   			}
			   			advAmount[advAmount.length] = payTotalAmount*(-1);
			   			payAdvance[payAdvance.length] = allPaycase[tmpIndex[i]];
			   			flgAdv = true;
	   				}
		   		} else {
		   			if (payTotalAmount!=0) {
		   				amounts[tmpIndex[i]] = tmpTotal!=0?tmpTotal:invTotal;
		   			}
		   			advAmount[advAmount.length] = payTotalAmount*(-1);
		   			payAdvance[payAdvance.length] = allPaycase[tmpIndex[i]];
		   			flgAdv = true;
		   		}
	   		} else {
			   advAmount[advAmount.length] = tmpAmount[idx];
			   payAdvance[payAdvance.length] = allPaycase[tmpIndex[i]];
			   amounts[tmpIndex[i]] = "";
	   		}
	   		switch (tmpIndex[i]) {
		      case 1   : $.map((window["payChqListData"] || []), function(o, i) {  if(chequeRow.length > 0 ){ advDocNo.push(o[4]); } });
		      case 2   : $.map((window["payCCListData"] || []), function(o, i) {  if(creditRow.length > 0 ){ advDocNo.push(o[2]); } });
		      case 3   : $.map((window["payMoneyOrderTableListData"] || []), function(o, i) {  if(moneyOrderRow.length > 0 ){ advDocNo.push(o[1]); } });
		      case 4   : $.map((window["payBillExchgTableListData"] || []), function(o, i) {  if(billExchangeRow.length > 0 ){ advDocNo.push(o[1]); } });
		      case 5   : $.map((window["payCouponTableListData"] || []), function(o, i) {  if(couponRow.length > 0 ){ advDocNo.push(o[1]); } });
		      case 6   : $.map((window["payBankTxnfTableListData"] || []), function(o, i) {  if(moneyTransferRow.length > 0 ){ advDocNo.push(o[7]); } });
		      case 7   : $.map((window["payOffsetTableListData"] || []), function(o, i) {  if(offsetRow.length > 0 ){ advDocNo.push(o[1]); } });
		      case 8   : $.map((window["payForeignTableListData"] || []), function(o, i) {  if(moneyForeignRow.length > 0 ){ advDocNo.push(o[6]); } });
		      case 9   : $.map((window["payOtherTableListData"] || []), function(o, i) {  if(otherRow.length > 0 ){ advDocNo.push(o[1]); } });
		      case 10  : $.map((window["payBankTxnfTableListGfData"] || []), function(o, i) {  if(moneyTransferGFRow.length > 0 ){ advDocNo.push(o[7]); } });
		      default : advDocNo.push(null);;
		   }  
	   	}       

   }
    
    
    console.log("change : "+change);console.log("receivedMoney : "+receivedMoney);console.log("balanceDue : "+balanceDue);console.log("advanceTotal : "+advanceTotal)
    console.log(accountNo.join(","));
    console.log(countNormal);
    if (countNormal>0) {
	    $.map((window["payChqListData"] || []), function(o, i) { // For: Cheque
			if(chequeRow.length > 0 && amounts[1] != "" ){	// Fix by EPIS8 23/12/2016 refer issue no.57
	            console.log(o);
				params["methods["+ methodIndex +"].type"] = "CHEQUE";
				params["methods["+ methodIndex +"].code"] = "CH";
				params["methods["+ methodIndex +"].name"] = "เช็ค";
				params["methods["+ methodIndex +"].chequeNo"] = $.trim(o[4]);
				params["methods["+ methodIndex +"].amount"] = amounts[1];
				params["methods["+ methodIndex +"].payChqBankCode"] = $.trim(o[1]);
				params["methods["+ methodIndex +"].payChqBankName"] = $.trim(o[2]);
				params["methods["+ methodIndex +"].payChqBranch"] = $.trim(o[3]);
				params["methods["+ methodIndex++ +"].payChqDate"] = $.trim(o[5]);
				paymentCase = paymentCase.concat("เช็ค "+$.trim(o[2])+" เลขที่: "+$.trim(o[4])+" + ");
			}
		});
		$.map((window["payCCListData"] || []), function(o, i) { // For: Credit Card
			if(creditRow.length > 0 && amounts[2] != "" ){	// Fix by EPIS8 23/12/2016 refer issue no.57
				params["methods["+ methodIndex +"].type"] = "CREDITCARD";
				params["methods["+ methodIndex +"].code"] = "CR";
				params["methods["+ methodIndex +"].name"] = "บัตรเครดิต";
				params["methods["+ methodIndex +"].cardType"] = $.trim(o[1]);
				params["methods["+ methodIndex +"].cardNo"] = $.trim(o[2]);
				params["methods["+ methodIndex +"].bankName"] = $.trim(o[3]);
				params["methods["+ methodIndex +"].docNo"] = $.trim(o[2]);
				params["methods["+ methodIndex++ +"].amount"] = amounts[2];
				paymentCase = paymentCase.concat("บัตรเครดิต "+$.trim(o[1])+" เลขที่: "+$.trim(o[2])+" + ");
			}
		});
		$.map((window["payMoneyOrderTableListData"] || []), function(o, i) { // For: Money Order
			if(moneyOrderRow.length > 0 && amounts[3] != "" ){	// Fix by EPIS8 23/12/2016 refer issue no.57
				params["methods["+ methodIndex +"].type"] = "MONEYORDER";
				params["methods["+ methodIndex +"].code"] = "MO";
				params["methods["+ methodIndex +"].name"] = "ธนาณัติ";
				params["methods["+ methodIndex +"].mnyOrderNo"] = $.trim(o[1]);
				params["methods["+ methodIndex +"].mnyOrderDt"] = o[2] +" 00:00:00";
				params["methods["+ methodIndex +"].postCode"] = $.trim(o[3]);
				params["methods["+ methodIndex +"].docNo"] = $.trim(o[1]);
				params["methods["+ methodIndex++ +"].amount"] = amounts[3];
				paymentCase = paymentCase.concat("ธนาณัติ เลขที่: "+$.trim(o[1])+" + ");
			}
		});
		$.map((window["payBillExchgTableListData"] || []), function(o, i) { // For: Bill of Exchange
			if(billExchangeRow.length > 0 && amounts[4] != "" ){	// Fix by EPIS8 23/12/2016 refer issue no.57
				params["methods["+ methodIndex +"].type"] = "BILLEXCHANGE";
				params["methods["+ methodIndex +"].code"] = "BX";
				params["methods["+ methodIndex +"].name"] = "ตั๋วแลกเงิน";
				params["methods["+ methodIndex +"].trnfNo"] = $.trim(o[1]);
				params["methods["+ methodIndex +"].transferDt"] = o[2] +" 00:00:00";
				params["methods["+ methodIndex +"].postCode"] = $.trim(o[3]);
				params["methods["+ methodIndex +"].docNo"] = $.trim(o[1]);
				params["methods["+ methodIndex++ +"].amount"] = amounts[4];
				paymentCase = paymentCase.concat("ตั๋วแลกเงิน เลขที่: "+$.trim(o[1])+" + ");
			}
		});
		$.map((window["payCouponTableListData"] || []), function(o, i) { // For: Coupon
			if(couponRow.length > 0 && amounts[5] != "" ){	// Fix by EPIS8 23/12/2016 refer issue no.57
				params["methods["+ methodIndex +"].type"] = "COUPON";
				params["methods["+ methodIndex +"].code"] = "CP";
				params["methods["+ methodIndex +"].name"] = "คูปอง";
				params["methods["+ methodIndex +"].couponNo"] = $.trim(o[1]);
				params["methods["+ methodIndex +"].docNo"] = $.trim(o[1]);
				params["methods["+ methodIndex++ +"].amount"] = amounts[5];
				paymentCase = paymentCase.concat("คูปอง เลขที่: "+$.trim(o[1])+" + ");
			}
		});
		$.map((window["payBankTxnfTableListData"] || []), function(o, i) { // For: Money Transfer
			if(moneyTransferRow.length > 0 && amounts[6] != "" ){	// Fix by EPIS8 23/12/2016 refer issue no.57
				params["methods["+ methodIndex +"].type"] = "BANKTRANSFER";
				params["methods["+ methodIndex +"].code"] = "TR";
				params["methods["+ methodIndex +"].name"] = "เงินโอนในประเทศ";
				params["methods["+ methodIndex +"].transferDt"] = o[5] +" 00:00:00";
				params["methods["+ methodIndex +"].isBackDt"] = "${epContext.roleName}" == "TRANSFER"||"${epContext.roleName}" == "TRANSIN";
				params["methods["+ methodIndex +"].bankCode"] = o[1];
		        params["methods["+ methodIndex +"].bankName"] = o[2];
		        params["methods["+ methodIndex +"].bankBrnh"] = o[3];
		        params["methods["+ methodIndex +"].refNo"] = o[4];
		        params["methods["+ methodIndex +"].bankAcctNo"] = o[7];
		        params["methods["+ methodIndex +"].bankAcCd"] = o[8];
		        params["methods["+ methodIndex +"].docNo"] = o[7];
				params["methods["+ methodIndex++ +"].amount"] = amounts[6];
				paymentCase = paymentCase.concat("เงินโอนในประเทศ + ");
			}
		}); 
	    $.map((window["payBankTxnfTableListGfData"] || []), function(o, i) {
			if(moneyTransferGFRow.length > 0 && amounts[10] != "" ){
				params["methods["+ methodIndex +"].type"] = "BANKTRANSFER";
				params["methods["+ methodIndex +"].code"] = "GF";
				params["methods["+ methodIndex +"].name"] = "เงินโอน (GFMIS)";
				params["methods["+ methodIndex +"].transferDt"] = o[5] +" 00:00:00";
				params["methods["+ methodIndex +"].bankCode"] = o[1];
		        params["methods["+ methodIndex +"].bankName"] = o[2];
		        params["methods["+ methodIndex +"].bankBrnh"] = o[3];
		        params["methods["+ methodIndex +"].refNo"] = o[4];
		        params["methods["+ methodIndex +"].bankAcctNo"] = o[7];
		        params["methods["+ methodIndex +"].bankAcCd"] = o[8];
				params["methods["+ methodIndex +"].isBackDt"] = "${epContext.roleName}" == "GFMISAGENT";
				params["methods["+ methodIndex +"].docNo"] = o[7];
				params["methods["+ methodIndex++ +"].amount"] = amounts[10];
				paymentCase = paymentCase.concat("เงินโอน (GFMIS) + ");
			}
		}); 
		$.map((window["payOffsetTableListData"] || []), function(o, i) { // For: Offset
			if(offsetRow.length > 0 && amounts[7] != "" ){	// Fix by EPIS8 23/12/2016 refer issue no.57
				params["methods["+ methodIndex +"].type"] = "OFFSET";
				params["methods["+ methodIndex +"].code"] = "OF";
				params["methods["+ methodIndex +"].name"] = "offset";
				params["methods["+ methodIndex +"].offsetDocumentNo"] = o[1];
				params["methods["+ methodIndex +"].offsetAccountCode"] = o[2];
				params["methods["+ methodIndex +"].offsetAccountName"] = o[3];
				params["methods["+ methodIndex +"].docNo"] = o[1];
				params["methods["+ methodIndex++ +"].amount"] = amounts[7];
				paymentCase = paymentCase.concat("offset "+o[1]+" + "); // add Document No
			}
		}); 
		$.map((window["payOtherTableListData"] || []), function(o, i) { // For: Other Type
			if(otherRow.length > 0 && amounts[9] != "" ){	// Fix by EPIS8 23/12/2016 refer issue no.57
				params["methods["+ methodIndex +"].type"] = "OTHER";
				params["methods["+ methodIndex +"].code"] = "OT"; 
				params["methods["+ methodIndex +"].name"] = "อื่นๆ";
				params["methods["+ methodIndex +"].otherNo"] = $.trim(o[1]);
				params["methods["+ methodIndex +"].otherType"] = $.trim(o[2]);
				params["methods["+ methodIndex +"].otherDt"] = $.trim(o[3]);
				params["methods["+ methodIndex +"].docNo"] = $.trim(o[1]);
				params["methods["+ methodIndex++ +"].amount"] = amounts[9];
				paymentCase = paymentCase.concat("อื่นๆ + ");
			}
		}); 
		$.map((window["payForeignTableListData"] || []), function(o, i) {
		    console.log("moneyForeignRow >>> ");
		    console.log(moneyForeignRow.length);
			if(moneyForeignRow.length > 0 && amounts[8] != "" ){
	            console.log("moneyForeignRow +++ true");
				params["methods["+ methodIndex +"].type"] = "FOREIGNTRANSFER";
				params["methods["+ methodIndex +"].code"] = "TF";
				params["methods["+ methodIndex +"].name"] = "เงินโอนต่างประเทศ";
	            params["methods["+ methodIndex +"].transferDt"] = o[3] +" 00:00:00";
	            params["methods["+ methodIndex +"].isBackDt"] = "${epContext.roleName}" == "TRANSFER";
	            params["methods["+ methodIndex +"].currencyCode"] = o[1];
	            params["methods["+ methodIndex +"].currencyRate"] = o[2];
	            params["methods["+ methodIndex +"].foreignAmount"] = o[4];
	            params["methods["+ methodIndex +"].bankAcctNo"] = o[6];
	            params["methods["+ methodIndex +"].docNo"] = o[6];
				params["methods["+ methodIndex++ +"].amount"] = amounts[8];
				paymentCase = paymentCase.concat("เงินโอนต่างประเทศ + ");
			}
		});
	
		if (cashRow.length == 1 && amounts[0] != "" ) { // For: Cash
			params["methods["+ methodIndex +"].type"] = "CASH";
			params["methods["+ methodIndex +"].code"] = "CC";
			params["methods["+ methodIndex +"].name"] = "เงินสด";
			params["methods["+ methodIndex++ +"].amount"] = amounts[0];//cashRow.find("td:eq(2) div").text().replace(/[,]/g, "");
			paymentCase = paymentCase.concat("เงินสด + ");
		}
    } else {
    	$.map((window["payChqListData"] || []), function(o, i) { 
			if(chequeRow.length > 0){
	            console.log(o);
				paymentCase = paymentCase.concat("เช็ค "+$.trim(o[2])+" เลขที่: "+$.trim(o[4])+" + ");
			}
		});
		$.map((window["payCCListData"] || []), function(o, i) { 
			if(creditRow.length > 0){	
				paymentCase = paymentCase.concat("บัตรเครดิต "+$.trim(o[1])+" เลขที่: "+$.trim(o[2])+" + ");
			}
		});
		$.map((window["payMoneyOrderTableListData"] || []), function(o, i) {
			if(moneyOrderRow.length > 0){
				paymentCase = paymentCase.concat("ธนาณัติ เลขที่: "+$.trim(o[1])+" + ");
			}
		});
		$.map((window["payBillExchgTableListData"] || []), function(o, i) {
			if(billExchangeRow.length > 0){
				paymentCase = paymentCase.concat("ตั๋วแลกเงิน เลขที่: "+$.trim(o[1])+" + ");
			}
		});
		$.map((window["payCouponTableListData"] || []), function(o, i) { 
			if(couponRow.length > 0){
				paymentCase = paymentCase.concat("คูปอง เลขที่: "+$.trim(o[1])+" + ");
			}
		});
		$.map((window["payBankTxnfTableListData"] || []), function(o, i) {
			if(moneyTransferRow.length > 0){
				isBackDate ="${epContext.roleName}" == "TRANSFER"||"${epContext.roleName}" == "TRANSIN";		
				transferDt = o[5] +" 00:00:00";				
				paymentCase = paymentCase.concat("เงินโอนในประเทศ + ");
			}
		});
	    $.map((window["payBankTxnfTableListGfData"] || []), function(o, i) {
			if(moneyTransferGFRow.length > 0){
				isBackDate = "${epContext.roleName}" == "GFMISAGENT";
				transferDt = o[5] +" 00:00:00";
				paymentCase = paymentCase.concat("เงินโอน (GFMIS) + ");
			}
		});
		$.map((window["payOffsetTableListData"] || []), function(o, i) { 
			if(offsetRow.length > 0){
				paymentCase = paymentCase.concat("offset "+o[1]+" + ");
			}
		});
		$.map((window["payOtherTableListData"] || []), function(o, i) {
			if(otherRow.length > 0){
				paymentCase = paymentCase.concat("อื่นๆ + ");
			}
		});
		$.map((window["payForeignTableListData"] || []), function(o, i) {
			if(moneyForeignRow.length > 0){
				isBackDate = "${epContext.roleName}" == "TRANSFER";		
				transferDt = o[3] +" 00:00:00";
				paymentCase = paymentCase.concat("เงินโอนต่างประเทศ + ");
			}
		});
	
		if (cashRow.length == 1) {
			paymentCase = paymentCase.concat("เงินสด + ");
		}
    }
	var watPaymentCaseConclude = "";
	if(deduction > 0) {
		var watPaymentCaseArr = watPaymentCase.substring(0, watPaymentCase.length-2).split(" + ");
		if(watPaymentCaseArr.length > 0) {
			watPaymentCaseConclude = " + ";
			watPaymentCaseConclude = watPaymentCaseConclude.concat($.each(watPaymentCaseArr, function(i,o){ return o }).join(" + "));
		}
	}
	var paymentCaseArr = paymentCase.substring(0, paymentCase.length-3).split(" + ");
	var paymentCaseConclude = $.each(paymentCaseArr, function(i,o){ return o }).join(" + ") + (deduction > 0? watPaymentCaseConclude: "");
	params["paymentCase"] = paymentCaseConclude;
	params["backDateFlag"] = isBackDate;
	params["transferDt"] = transferDt;

	console.log("step4 Payment set.");
	var billGroup=view.session("billingGroup");
	var subScriptionData = view.session("subScriptionData");
	// <!-- Invoice List. -->
	var custIndex = 0, invoiceIndex = 0, customer, receiptList = [];
    var otherFlg = false;
    if(radioChkVal == "1" || radioChkVal == "2"){
        //console.log('xxxx555');console.log(radioChkVal);console.log(view.table.customerList2.data()); console.log('xxxx555');
        otherFlg = true;
    }
    var billingListSize = billingList.length;
    var otherIndex = billingListSize;
	while (customer = billingList[custIndex]) {
        //var otherAmtFlg = false;
		params["customers["+ custIndex +"].custNo"] = customer.custNo;
		//params["customers["+ custIndex +"].custSubNo"] = subScriptionData[0];
		params["customers["+ custIndex +"].custName"] = customer.custName;
		params["customers["+ custIndex +"].custType"] = customer.custType;
		params["customers["+ custIndex +"].address1"] = customer.address1;
		params["customers["+ custIndex +"].address2"] = customer.address2;
		params["customers["+ custIndex +"].remark"] = (customer.remark ? customer.remark +" + " : "") + customer.additionalRemark;
		params["customers["+ custIndex +"].taxNo"] = customer.taxId;
		params["customers["+ custIndex +"].custBranch"] = customer.branch;
		params["customers["+ custIndex +"].split"] = customer.split;
		params["customers["+ custIndex +"].billGroup"] = billGroup;
		params["customers["+ custIndex +"].billingGroup"] = billGroup;
		params["customers["+ custIndex +"].collectionUnit"] = customer.collectionUnit;
		params["customers["+ custIndex +"].egpNo"] = customer.egpNo;
		params["customers["+ custIndex +"].egpContract"] = customer.egpContract;
		if (moneyForeignRow.length > 0 && currencyType !== "THB") {
	        params["customers["+ custIndex +"].currencyCode"] = currencyCode;
	        params["customers["+ custIndex +"].currencyRate"] = currencyRate;
		} else {
			params["customers["+ custIndex +"].currencyCode"] = view.session("billingCurrency");
	        params["customers["+ custIndex +"].currencyRate"] = exchangeRate;
		}
        //params["customers["+ custIndex +"].catCustomerSegment"] = customer.catCustomerSegment;
        params["customers["+ custIndex +"].acctCatLkp"] = customer.acctCatLkp;
        params["customers["+ custIndex +"].invoiceDisplay"] = customer.invoiceDisplay;
    	cancelList = view.session("cancelList");
        if(cancelList && cancelList.length> 0){
        	 params["customers["+ custIndex +"].receiptFormat"] = "SUBSTITUTE";
        }else{
	        params["customers["+ custIndex +"].receiptFormat"] = customer.receiptFormat;
        	
        }
		for (var j = 0, n = customer.invoiceList.length; j < n; j++) {
			var invoice = customer.invoiceList[j]; if (!invoice.checked) continue; if (invoice.no == "-" || invoice.no == "Advance Payment") continue;
			var isPartial = invoice.balanceDue - invoice.received != 0;
			var calculatedVat = invoice.calculatedVat,
			    calculatedCharge = /*!isPartial ? 0 :*/ invoice.received - calculatedVat,
			    calculatedAmount = /*!isPartial ? 0 :*/ calculatedCharge - invoice.discount;
			params["customers["+ custIndex +"].invoices["+ invoiceIndex +"].no"] = invoice.no;
			params["customers["+ custIndex +"].invoices["+ invoiceIndex +"].amount"] = calculatedAmount;
			params["customers["+ custIndex +"].invoices["+ invoiceIndex +"].discount"] = invoice.discount;
			params["customers["+ custIndex +"].invoices["+ invoiceIndex +"].charge"] = calculatedCharge;
			params["customers["+ custIndex +"].invoices["+ invoiceIndex +"].vat"] = calculatedVat;
			params["customers["+ custIndex +"].invoices["+ invoiceIndex +"].vatRate"] = invoice.vatRate;
			params["customers["+ custIndex +"].invoices["+ invoiceIndex +"].totalCharge"] = invoice.totalCharge;
// 			params["customers["+ custIndex +"].invoices["+ invoiceIndex +"].deduction"] = invoice.deduction;
// 			params["customers["+ custIndex +"].invoices["+ invoiceIndex +"].deduction"] = invoice.deduction + deduction;
			params["customers["+ custIndex +"].invoices["+ invoiceIndex +"].deduction"] = deduction;
			params["customers["+ custIndex +"].invoices["+ invoiceIndex +"].afterSaleDiscount"] = (invoice.afterSaleDiscount || 0);
			params["customers["+ custIndex +"].invoices["+ invoiceIndex +"].balanceDue"] = invoice.balanceDue - invoice.received;
			params["customers["+ custIndex +"].invoices["+ invoiceIndex +"].received"] = invoice.received;
			params["customers["+ custIndex +"].invoices["+ invoiceIndex +"].change"] = 0;
			params["customers["+ custIndex +"].invoices["+ invoiceIndex +"].billCycle"] = invoice.billCycle;
			params["customers["+ custIndex +"].invoices["+ invoiceIndex +"].issueDt"] = invoice.issueDate +" 00:00:00";
			params["customers["+ custIndex +"].invoices["+ invoiceIndex +"].dueDt"] = invoice.dueDate +" 00:00:00";
			params["customers["+ custIndex +"].invoices["+ invoiceIndex +"].currencyCode"] = invoice.currencyCode;
			params["customers["+ custIndex +"].invoices["+ invoiceIndex +"].kenan"] = invoice.kenan;
			params["customers["+ custIndex +"].invoices["+ invoiceIndex +"].status"] = invoice.status == "หนี้สูญ"? "W" : "N";
            params["customers["+ custIndex +"].invoices["+ invoiceIndex +"].discountType"] = invoice.discountType;
            params["customers["+ custIndex +"].invoices["+ invoiceIndex +"].afterSaleDiscVat"] = invoice.afterSaleDiscVat;
            params["customers["+ custIndex +"].invoices["+ invoiceIndex +"].discApprUser"] = invoice.discApprUser;
            params["customers["+ custIndex +"].invoices["+ invoiceIndex +"].taxTypeCode"] = invoice.taxTypeCode;
                /* */
                if(invoice.subNoList != null) {
                	for (var k = 0 ; k < invoice.subNoList.length ;  k++) {
                        params["customers["+ custIndex +"].invoices["+ invoiceIndex +"].subNoList["+k+"]"] =  invoice.subNoList[k];
                    }
                }
            /* */
            if(invoice.invoiceVatDetails != null) {
                for (var k = 0 ; k < invoice.invoiceVatDetails.length ;  k++) {
                    params["customers["+ custIndex +"].invoices["+ invoiceIndex +"].invoiceVatDetails["+k+"].accountNo"] =  invoice.invoiceVatDetails[k].accountNo;
                    params["customers["+ custIndex +"].invoices["+ invoiceIndex +"].invoiceVatDetails["+k+"].invoiceNo"] =  invoice.invoiceVatDetails[k].invoiceNo;
                    params["customers["+ custIndex +"].invoices["+ invoiceIndex +"].invoiceVatDetails["+k+"].amount"] =  invoice.invoiceVatDetails[k].amount;
                    params["customers["+ custIndex +"].invoices["+ invoiceIndex +"].invoiceVatDetails["+k+"].vat"] =  invoice.invoiceVatDetails[k].vat;
                    params["customers["+ custIndex +"].invoices["+ invoiceIndex +"].invoiceVatDetails["+k+"].taxTypeCode"] =  invoice.invoiceVatDetails[k].taxTypeCode;
                }
                //params["customers["+ custIndex +"].invoices["+ invoiceIndex +"].invoiceVatDetails"] =  invoice.invoiceVatDetails;
            }


           // params["customers["+ custIndex +"].invoices["+ invoiceIndex +"].subNoList"] = ['xyz'];
            //console.log(invoice.subNoList[0])
			//alert(invoice.subNoList[0])
            //alert(params["customers["+ custIndex +"].invoices["+ invoiceIndex +"].subNoList[0]"])
			console.log('TEST SET MULTI TAX : '+deduction);
			if(deduction > 0) {
				var watPaymentCase = paymentCase.concat("WT ");
				$.map((window["wthList"] || []), function(o, i) {
					console.log('START watPaymentCase : '+watPaymentCase);
					console.log('Invoice from tax = '+$.trim(o[1]));
					console.log('Invoice from original = '+invoice.no);
			        if($.trim(o[1]) == invoice.no){
			            if($.trim(o[2]).length>0)
			        	    watPaymentCase = watPaymentCase.concat("เลขที่: "+$.trim(o[2])+" + ");
			            else
                            watPaymentCase = watPaymentCase.concat(""+$.trim(o[2])+" + ");
			        	console.log('con watPaymentCase : '+watPaymentCase);
			        } else if($.trim(o[1]) == ''){//กรุณาเลือก
                        if($.trim(o[2]).length>0)
			        	    watPaymentCase = watPaymentCase.concat("เลขที่: "+$.trim(o[2])+" + ");
                        else
                            watPaymentCase = watPaymentCase.concat(""+$.trim(o[2])+" + ");
			        }
			        params["customers["+ custIndex +"].invoices["+ invoiceIndex +"].paymentCase"] = watPaymentCase.substring(0, watPaymentCase.length-3);
				});
			} else {
				params["customers["+ custIndex +"].invoices["+ invoiceIndex +"].paymentCase"] = paymentCase.substring(0, paymentCase.length-3);
			}

			invoiceIndex++;
		}
		if(otherFlg){
            if(customer.invoiceList.length > 0){
                var vatR = customer.invoiceList[0].vatRate;
                var otherReceiveFormat =  view.session("receiptFormat");
                if(radioChkVal == "1"){
                    vatR = 0;
                    //custIndex = custIndex+1;
                    invoiceIndex = 0;
                    otherReceiveFormat = "receive_only";//"R"
                    indexForOther = otherIndex;
                }else{
                	indexForOther  = custIndex;
                }
                $.each(view.table.customerList2.data(), function(i, o){
                    if(customer.custNo == o.custNo){
                        var invoiceNo = $("#otherNote"+i).val();
                        //var amt = stripToNumber($("#otherAmt"+i).val());
                        var amt = stripToNumber($("#advanceAmountotherAmt"+i).val());
                        var vat = (amt*vatR/(100+vatR)).toFixed(2);
                        var amtExc = amt - vat;
                        params["customers["+ otherIndex +"].invoices["+ invoiceIndex +"].no"] = invoiceNo;
                        params["customers["+ otherIndex +"].invoices["+ invoiceIndex +"].amount"] = amtExc;
                        params["customers["+ otherIndex +"].invoices["+ invoiceIndex +"].discount"] = 0;
                        params["customers["+ otherIndex +"].invoices["+ invoiceIndex +"].charge"] = amtExc;
                        params["customers["+ otherIndex +"].invoices["+ invoiceIndex +"].vat"] = ( (vatR==0)?null:vat );
                        params["customers["+ otherIndex +"].invoices["+ invoiceIndex +"].vatRate"] = vatR;
                        params["customers["+ otherIndex +"].invoices["+ invoiceIndex +"].totalCharge"] = amt;
// 			params["customers["+ custIndex +"].invoices["+ invoiceIndex +"].deduction"] = invoice.deduction;
// 			params["customers["+ custIndex +"].invoices["+ invoiceIndex +"].deduction"] = invoice.deduction + deduction;
                        params["customers["+ otherIndex +"].invoices["+ invoiceIndex +"].deduction"] = 0;
                        params["customers["+ otherIndex +"].invoices["+ invoiceIndex +"].afterSaleDiscount"] = 0;
                        params["customers["+ otherIndex +"].invoices["+ invoiceIndex +"].balanceDue"] = 0;
                        params["customers["+ otherIndex +"].invoices["+ invoiceIndex +"].received"] = amt;
                        params["customers["+ otherIndex +"].invoices["+ invoiceIndex +"].change"] = 0;
                        /*params["customers["+ custIndex +"].invoices["+ invoiceIndex +"].billCycle"] = invoice.billCycle;
                         params["customers["+ custIndex +"].invoices["+ invoiceIndex +"].issueDt"] = invoice.issueDate +" 00:00:00";
                         params["customers["+ custIndex +"].invoices["+ invoiceIndex +"].dueDt"] = invoice.dueDate +" 00:00:00";
                         params["customers["+ custIndex +"].invoices["+ invoiceIndex +"].currencyCode"] = invoice.currencyCode;
                         params["customers["+ custIndex +"].invoices["+ invoiceIndex +"].kenan"] = invoice.kenan;*/
                        params["customers["+ otherIndex +"].invoices["+ invoiceIndex +"].status"] = "OTH";
                        params["customers["+ otherIndex +"].receiptFormat"] = otherReceiveFormat
                        
                         
                        if(deduction > 0) {
                            var watPaymentCase = paymentCase.concat("WT ");
                            $.map((window["wthList"] || []), function(o, i) {
                                console.log('START watPaymentCase : '+watPaymentCase);
                                console.log('Invoice from tax = '+$.trim(o[1]));
                                console.log('Invoice from original = '+invoice.no);
                                if($.trim(o[1]) == invoice.no){
                                    if($.trim(o[2]).length>0)
                                        watPaymentCase = watPaymentCase.concat("เลขที่: "+$.trim(o[2])+" + ");
                                    else
                                        watPaymentCase = watPaymentCase.concat(""+$.trim(o[2])+" + ");
                                    console.log('con watPaymentCase : '+watPaymentCase);
                                } else if($.trim(o[1]) == ''){
                                    if($.trim(o[2]).length>0)
                                        watPaymentCase = watPaymentCase.concat("เลขที่: "+$.trim(o[2])+" + ");
                                    else
                                        watPaymentCase = watPaymentCase.concat(""+$.trim(o[2])+" + ");
                                }
                                params["customers["+ otherIndex +"].invoices["+ invoiceIndex +"].paymentCase"] = watPaymentCase.substring(0, watPaymentCase.length-3);
                            });
                        } else {
                            params["customers["+ otherIndex +"].invoices["+ invoiceIndex +"].paymentCase"] = paymentCase.substring(0, paymentCase.length-3);
                        }
                        if(radioChkVal == "1"){
                        	otherIndex ++;
                        }
                    }
                });
            }
        }

		custIndex++;
		invoiceIndex = 0;
	}
	console.log("step5 Invoice List set.");
	// <!-- Advance Invoice. -->
	var advIndex = 0, advCaseIndex=0;
	for (var i = 0, m = billingList.length; i < m; i++) {
		customer = billingList[i];
		for (var j = 0, n = customer.invoiceList.length; j < n; j++) {
			var invoice = customer.invoiceList[j];
            if (invoice.no != "-" && invoice.no != "Advance Payment") continue;//by NSD 24-03-2017
			if(invoice.totalCharge > 0){
				params["advances["+ advIndex +"].custNo"] = customer.custNo;
				//params["advances["+ advIndex +"].custSubNo"] = subScriptionData[0];
				params["advances["+ advIndex +"].custType"] = customer.custType;
                params["advances["+ advIndex +"].invoiceDisplay"] = customer.invoiceDisplay;
				params["advances["+ advIndex +"].invoiceNo"] = invoice.no//"0";
				params["advances["+ advIndex +"].kenan"] = invoice.kenan;
				params["advances["+ advIndex +"].currencyCode"] = invoice.currencyCode;
				params["advances["+ advIndex +"].amount"] = invoice.charge;
				params["advances["+ advIndex +"].discount"] = 0;
				params["advances["+ advIndex +"].charge"] = invoice.charge;
				params["advances["+ advIndex +"].vat"] = invoice.calculatedVat;
				params["advances["+ advIndex +"].totalCharge"] = invoice.totalCharge;
				params["advances["+ advIndex +"].deduction"] = 0;
				params["advances["+ advIndex +"].balanceDue"] = 0;
				params["advances["+ advIndex +"].totalPaid"] = 0;
				params["advances["+ advIndex +"].received"] = invoice.totalCharge;
				params["advances["+ advIndex +"].change"] = 0;
                params["advances["+ advIndex +"].billCycle"] = invoice.billCycle;
/*                 params["advances["+ advIndex +"].payType"] = payAdvance[0];
                params["advances["+ advIndex +"].payCode"] = payAdvance[1];
                params["advances["+ advIndex +"].payName"] = payAdvance[2]; */
				advIndex++;
			}
		}
	}
	for (x=0;x<advAmount.length;x++) {
        console.log("============= LOOP ("+ x +")");
        params["advancesCase["+ advCaseIndex +"].payType"] = payAdvance[x][0];
        params["advancesCase["+ advCaseIndex +"].payCode"] = payAdvance[x][1];
        params["advancesCase["+ advCaseIndex +"].payName"] = payAdvance[x][2];
        params["advancesCase["+ advCaseIndex +"].payAmount"] = advAmount[x];
        advCaseIndex++;
   	}
	if (view.inputAdvanced.val() > 0 && radioChkVal == "3") {
		var inputs = view.table.customerList.elem.find("input");
		var inputs2 = view.table.customerList2.elem.find("input");
        //console.log('55555 last step');console.log(view.table.customerList.data());console.log('55555 last step');
		$.each(view.table.customerList.data(), function(i,o) {
			var totalCharge = stripToNumber(inputs[i].value), vatRate = o.invoiceList[0].vatRate, calculatedVat = calculateVatFromIncluded(totalCharge, vatRate), charge = totalCharge - calculatedVat;
            var billCycle = $("#billCycle"+i).val();
			if(totalCharge > 0){
				params["advances["+ advIndex +"].custNo"] = o.custNo;
				//params["advances["+ advIndex +"].custSubNo"] = subScriptionData[0];
				params["advances["+ advIndex +"].custType"] = o.custType;
                params["advances["+ advIndex +"].invoiceDisplay"] = o.invoiceDisplay;
				params["advances["+ advIndex +"].invoiceNo"] = "0";
				params["advances["+ advIndex +"].kenan"] = o.invoiceList[0].kenan;
				params["advances["+ advIndex +"].currencyCode"] = o.invoiceList[0].currencyCode;
				params["advances["+ advIndex +"].amount"] = charge;
				params["advances["+ advIndex +"].discount"] = 0;
				params["advances["+ advIndex +"].charge"] = charge;
				params["advances["+ advIndex +"].vat"] = calculatedVat;
				params["advances["+ advIndex +"].totalCharge"] = view.inputAdvanced.val();//totalCharge;
				params["advances["+ advIndex +"].deduction"] = 0;
				params["advances["+ advIndex +"].balanceDue"] = 0;
				params["advances["+ advIndex +"].totalPaid"] = 0;
				params["advances["+ advIndex +"].received"] = totalCharge;
				params["advances["+ advIndex +"].change"] = 0;
                params["advances["+ advIndex +"].billCycle"] = billCycle;
                /* params["advances["+ advIndex +"].payType"] = payAdvance[0];
                params["advances["+ advIndex +"].payCode"] = payAdvance[1];
                params["advances["+ advIndex +"].payName"] = payAdvance[2]; */
                params["advances["+ advIndex +"].type"] = "Inattentive";
				advIndex++;
			}
		});
		/*$.each(view.table.customerList2.data(), function(i,o) {
			var totalCharge = stripToNumber(inputs2[i].value), vatRate = o.invoiceList[0].vatRate, calculatedVat = calculateVatFromIncluded(totalCharge, vatRate), charge = totalCharge - calculatedVat;
			if(totalCharge > 0){
				params["advances["+ advIndex +"].custNo"] = o.custNo;
				params["advances["+ advIndex +"].custSubNo"] = subScriptionData[0];
				params["advances["+ advIndex +"].custType"] = o.custType;
				params["advances["+ advIndex +"].invoiceNo"] = "0";
				params["advances["+ advIndex +"].kenan"] = o.invoiceList[0].kenan;
				params["advances["+ advIndex +"].currencyCode"] = o.invoiceList[0].currencyCode;
				params["advances["+ advIndex +"].amount"] = charge;
				params["advances["+ advIndex +"].discount"] = 0;
				params["advances["+ advIndex +"].charge"] = charge;
				params["advances["+ advIndex +"].vat"] = calculatedVat;
				params["advances["+ advIndex +"].totalCharge"] = totalCharge;
				params["advances["+ advIndex +"].deduction"] = 0;
				params["advances["+ advIndex +"].balanceDue"] = 0;
				params["advances["+ advIndex +"].totalPaid"] = 0;
				params["advances["+ advIndex +"].received"] = totalCharge;
				params["advances["+ advIndex +"].change"] = 0;
				advIndex++;
			}
		});*/
	}
	
	for (x=0;x<advIntAmount.length;x++) {
        console.log("============= LOOP ("+ x +")");
        params["advancesCase["+ advCaseIndex +"].payType"] = payAdvanceInt[x][0];
        params["advancesCase["+ advCaseIndex +"].payCode"] = payAdvanceInt[x][1];
        params["advancesCase["+ advCaseIndex +"].payName"] = payAdvanceInt[x][2];
        params["advancesCase["+ advCaseIndex +"].payAmount"] = advIntAmount[x];
        params["advancesCase["+ advCaseIndex +"].docNo"] = advDocNo[x];
        advCaseIndex++;
    }
	
	console.log("step6 Advance Invoice set.");
	console.log("-- Ready to post! --");
    console.log(params);
    //console.log("flag Header***"+view.session("receiptFormat"));
    //alert(params)
    // check coupon
    var isCall = true;
    //=================
    if (couponRow.length > 0){
		$.ajax({ 
			type: 'GET', 
			url: '../checkStatusServer.json?ip='+"10.44.1.4",//view.inputBillNo.val(), 
			//url: '../checkPromotionServerStatus.json?ip='+accountList[0]+"&accounts="+accountList[1], 
			async: false,
			success: function (data) { 
				console.log(' serverStatus ');console.log(data); 
				if (data == "ERROR") {
					view.dialog.mainMessageDialog.clear().error(["ไม่สามารถเชื่อมต่อกับระบบ INSALE ได้"]).show();
					isCall = false;
				}
			} 
		});
    }
    
    $.ajax({ 
		type: 'GET', 
		url: '../checkPromotionServerStatus.json?ip=10.44.1.4&accounts='+accountNo.join(","), 
		async: false,
		success: function (data) { 
			console.log(' serverStatus ');console.log(data); 
			if (data == "ERROR") {
				view.dialog.mainMessageDialog.clear().error(["ไม่สามารถเชื่อมต่อกับระบบ INSALE ได้"]).show();
				isCall = false;
			}
		} 
	});
    //return false;
    if (isCall) {
	    $.ajax({
	        url: "../createPaymentProduct.json",
	        type: "POST",
	        data: params,
	        error: view.dialog.mainMessageDialog.valid,
	        success: function(res) {
	        	console.log(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>")
	        	console.log(res)
	        
	            if (!view.dialog.mainMessageDialog.valid(res)) {
	            console.log("+++++++++++++++++++++++++++++++")	
	            console.log()
	            
	                return;
	            }
	            view.dialog.mainMessageDialog.clear();
	            $(document.body).append('<form action="../printPaymentReceipt.pdf" method="post" target="_printForm"></form>');
	            var form = document.forms[0];
	
	            function input(n, v) {
	                var input = document.createElement("input");
	                input.type = "hidden";
	                input.name = n;
	                input.value = v;
	                return input
	            }
	            $.each(res.data, function(i, o) {
	                var rcptFormat = "";
	                //by NSD 25-04-2017
	                if (o.flgHeader == '1') {
	                    rcptFormat = "receive_wh";
	                } else if (o.flgHeader == '2') {
	                    rcptFormat = "receive_only";
	                } else {
	                    rcptFormat = "wh_only";
	                }
	                form.appendChild(input("receipts[" + i + "].id", o.id));
	                form.appendChild(input("receipts[" + i + "].billingServiceName", o.billingServiceName)); //by NSD 24-03-20
	                form.appendChild(input("receipts[" + i + "].flgHeader", o.flgHeader));
	                if(cancelList && cancelList.length> 0){
	                    form.appendChild(input("cancelDate", cancelList[0].docDttm + " 00:00"));
// 	                    form.appendChild(input("checkSpecial", 1));
	               }
	
	            });
	            var custIndex = 0,
	                invoiceIndex = 0,
	                customer, receiptList = [];
	            var billingList = view.session("billingList");
	            while (customer = billingList[custIndex]) {
	                form.appendChild(input("customers[" + custIndex + "].custNo", customer.custNo));
	                form.appendChild(input("customers[" + custIndex + "].egpNo", customer.egpNo));
	                form.appendChild(input("customers[" + custIndex + "].egpContract", customer.egpContract));
	                for (var j = 0, n = customer.invoiceList.length; j < n; j++) {
	                    var invoice = customer.invoiceList[j];
	                    form.appendChild(input("customers[" + custIndex + "].invoices[" + invoiceIndex + "].currencyCode", invoice.currencyCode));
	                    invoiceIndex++;
	                }
	                custIndex++;
	                invoiceIndex = 0;
	            }
	
	            // add Code Receive Only
	            var receiptFormat = view.session("receiptFormat");
	            //var billGroup=view.session("billingGroup");
	            //alert(billGroup);
	            form.appendChild(input("", receiptFormat)); // receive + wh [receive_wh] , receive only [receive_only] ,  wh only [wh_only]
	            form.appendChild(input("billingGroup", billGroup));
	            form.appendChild(input("receiptLang", receiptLang));
	            form.appendChild(input("note", $('#custNote').val()));
	            form.appendChild(input("checkSpecial", checkSpecial));
	            //
	            view.session("cancelList", []);
	            form.submit();
	            view.breadcrumb.breadcrumb.index(4);
	            view.panel.navigationPanel.hide(200);
	            view.panel.fillDataInputPanel.hide(400);
	            view.panel.linkPanel.hide(200);
	            view.panel.informationPanel.hide(600);
	            view.panel.receiptInfoPanel.hide(800);
	            view.panel.summaryPanel.show(1000);
	            var receiptList = [],
	                invoice;
	            $.each(res.data, function(i, r) {
	                invoice = r.invoices[0];
	                var invCount = 0;
	                custIndex = 0;
	                for (i = 0; i < r.invoices.length; i++) {
	                    if (r.invoices[i].billCycle != null) {
	                        invCount++;
	                    }
	                }
	                while (customer = billingList[custIndex]) {
	                    if (customer.custNo == r.accountNo) {
	                    	var multipy = 1 ;
	                    
	                        //console.log('xxxx555Invoice');console.log(r.invoices);console.log('xxxx555Invoice');
	                        /*var exchangeRate
	                        if(r.payment!=null && r.payment.currencyRate!=null && r.payment.currencyRate>0){
	
	                        }*/
	                       // alert("xxxx555attributes "+r.attributes+" xxxx555attributes")
	                        console.log('xxxx555attributes');console.log(r.attributes);console.log('xxxx555attributes');
	                        if(exchangeRate==null || exchangeRate == 0.00){
	                            exchangeRate = 1.0;
	                        }
	                        multipy = exchangeRate;
	                        if(r.attributes=='A')
	                        	multipy = 1.0;
	                        //alert(multipy)
	                        console.log('xxxx555Invoice');console.log(r);console.log('xxxx555Invoice');
	                        receiptList.push({
	                            "customerNo": r.accountNo,
	                            "customerName": r.accountName,
	                            "invoiceCount": invCount //r.invoices.length
	                                ,
	                            "receiptNo": r.no,
	                           // "receiptAmount": view.utils.numberFormat(r.received),
	                            "receiptAmount": view.utils.numberFormat(r.received*multipy),
	                            "receiptStatus": '<span class="glyphicon glyphicon-ok-circle"></span> บันทึกลงระบบสำเร็จ',
	                            "invoices": r.invoices.sort(function(a, b) {
	                                return a.id - b.id
	                            })
	                        });
	                        //console.log('xxxx555Invoice2');console.log(r.invoices);console.log('xxxx555Invoice2');
	                    }
	                    custIndex++;
	                }
	            });
	            view.table.receiptList.data(receiptList);
	            // cleart inv lock
	            var invoiceLockList = [];
	            for (var i = 0, m = billingList.length; i < m; i++) {
	                for (var j = 0, n = billingList[i].invoiceList.length; j < n; j++) {
	                    var invoice = billingList[i].invoiceList[j];
	                    var invoiceLock = {
	                        invNo: invoice.no,
	                        mode: "REMOVE"
	                    }
	                    invoiceLockList.push(invoiceLock)
	                }
	            }
	            console.log(invoiceLockList);
	            console.log(JSON.stringify(invoiceLockList));
	            $.ajax({
	                type: "POST",
	                url: "../manageDuplicatePayQueue.json",
	                data: JSON.stringify(invoiceLockList),
	                dataType: "json",
	                contentType: "application/json; charset=utf-8",
	                success: function(data) {
	
	                }
	            });
	        }
	    }); 
    }
   	//=================
}

function submitPaymentClickEvent() {
	submitPayment("TH");
}
function submitPaymentEngClickEvent() {
    submitPayment("ENG");
}
//return false;
// Credit Limit.
function creditLimitClickEvent() {
	// Credit Limit.
    //alert("creditLimitClick")
	var subScriptionList = view.session("subScriptionList");
	var billingList = view.session("billingList");
    console.log("subScriptionList show");
    console.log(subScriptionList);
    console.log(billingList)
    view.table.subScriptionListTb.clear();
    var indexSubnoChecked = 0;
	for(var i = 0, m = billingList.length; i < m; i++) {
        var ba=billingList[i].custNo;
		for(var j = 0, n = billingList[i].invoiceList.length; j < n; j++) {
			var invoice = billingList[i].invoiceList[j];
			//alert(invoice.checked=="on")
			//if(true == invoice.checked) {
                var baHidden = "<input type=\"hidden\" id=\"subnoBa_"+indexSubnoChecked+"\" value=\""+ba+"\"/>";
                var invoiceHidden = "<input type=\"hidden\" id=\"subnoInvoice_"+indexSubnoChecked+"\" value=\""+invoice.no+"\"/>";

                var totalChargeHidden = "<input type=\"hidden\" id=\"subnoTotalCharge_"+indexSubnoChecked+"\" value=\""+invoice.balanceDue+"\"/>"; // totalCharge
                var vatHidden = "<input type=\"hidden\" id=\"subnoVat_"+indexSubnoChecked+"\" value=\""+invoice.vat+"\"/>";
                var amountHidden = "<input type=\"hidden\" id=\"subnoAmount_"+indexSubnoChecked+"\" value=\""+invoice.amount+"\"/>";
                var kenanHidden = "<input type=\"hidden\" id=\"subnoKenan_"+indexSubnoChecked+"\" value=\""+invoice.kenan+"\"/>";
                var issueDateHidden = "<input type=\"hidden\" id=\"subnoIssueDate_"+indexSubnoChecked+"\" value=\""+invoice.issueDate+"\"/>";
                var dueDateHidden = "<input type=\"hidden\" id=\"subnoDueDate_"+indexSubnoChecked+"\" value=\""+invoice.dueDate+"\"/>";
                var subnoReceivedHidden = "<input type=\"hidden\" id=\"subnoReceived_"+indexSubnoChecked+"\" value=\""+invoice.received+"\"/>";

            var valueHidden = baHidden+invoiceHidden+totalChargeHidden+vatHidden+amountHidden+kenanHidden+issueDateHidden+dueDateHidden+subnoReceivedHidden;
				var checkBoxList = "<input type=\"checkbox\" name=\"subnoChecked_"+(indexSubnoChecked)+"\" value=\""+invoice.no+"\" data-index=\""+indexSubnoChecked+"\">";
			<%--
				for(var k=0; k<subScriptionList.length; k++) {
					subScriptionNoList += '<option data-index="'+ k +'" data-key="'+ $.trim(subScriptionList[k]) +'" value="'+ invoice.no +'-'+ $.trim(subScriptionList[k]) +'">'+ $.trim(subScriptionList[k]) +'</option>';
				}
			--%>
                var subScriptionNoList = ""//;"<input type=\"hidden\" id=\"creditLimitDd"+indexSubnoChecked+"\" value=\""+invoice.kenan+"\"/>"+invoice.kenan;

            var msisdnSize = "<input type=\"hidden\" id=\"subnoMsisdnSize_"+indexSubnoChecked+"\" value=\"0\"/>";

            if(invoice.subNoList!=null) {
                 subScriptionNoList = "<select id='creditLimitDd"+ indexSubnoChecked +"' style='width:160px;height:28px'>";
                var subNoListSize = invoice.subNoList.length;
                msisdnSize = "<input type=\"hidden\" id=\"subnoMsisdnSize_"+indexSubnoChecked+"\" value=\""+subNoListSize+"\"/>";
                if (subNoListSize > 0) {
                    if(subNoListSize>1)
                        subScriptionNoList += '<option data-index="-1" data-key="0" value="0">All Subscription</option>';
                    for (var k = 0; k < subNoListSize; k++) {
                        subScriptionNoList += '<option data-index="' + k + '" data-key="' + $.trim(invoice.subNoList[k]) + '" value="' + $.trim(invoice.subNoList[k]) + '">' + $.trim(invoice.subNoList[k]) + '</option>';
                    }
                }
                subScriptionNoList += '</select>';
            }else{ // advanced
                var subScriptionList = view.session("subScriptionList");
                subScriptionNoList = "<select id='creditLimitDd"+ indexSubnoChecked +"' style='width:160px;height:28px'>";
                var subNoListSize = subScriptionList.length;
                msisdnSize = "<input type=\"hidden\" id=\"subnoMsisdnSize_"+indexSubnoChecked+"\" value=\""+subNoListSize+"\"/>";
                if (subNoListSize > 0) {
                    if(subNoListSize>1)
                        subScriptionNoList += '<option data-index="-1" data-key="0" value="0">All Subscription</option>';
                    for (var k = 0; k < subNoListSize; k++) {
                        subScriptionNoList += '<option data-index="' + k + '" data-key="' + subScriptionList[k] + '" value="' + subScriptionList[k] + '">' + subScriptionList[k] + '</option>';
                    }
                }
                subScriptionNoList += '</select>';
            }


				//view.table.subScriptionListTb.insert(["-", invoice.no, subScriptionNoList]);
            console.log(ba)
            view.table.subScriptionListTb.insert([checkBoxList,ba+valueHidden+msisdnSize, invoice.no, subScriptionNoList]);
            indexSubnoChecked++;
			//}
		}
	}

	if(subScriptionList.length == 1) {
		var creditLimitData_S = "S|";
		for(var i = 0, m = billingList.length; i < m; i++) {
			for(var j = 0, n = billingList[i].invoiceList.length; j < n; j++) {
				var invoice = billingList[i].invoiceList[j];
				if(true == invoice.checked) {
					creditLimitData_S += invoice.no +'-'+ $.trim(subScriptionList[0]) +'|';
				}
			}
		}
		view.session("creditLimitData", creditLimitData_S);
		view.session("subScriptionData", subScriptionList[0]);
	} else {
		var creditLimitData_M = "M|";
		for(var i = 0, m = billingList.length; i < m; i++) {
			for(var j = 0, n = billingList[i].invoiceList.length; j < n; j++) {
				var invoice = billingList[i].invoiceList[j];
				if(true == invoice.checked) {
					creditLimitData_M += invoice.no +'-|';
				}
			}
		}
		view.session("creditLimitData", creditLimitData_M);
		view.session("subScriptionData", subScriptionList.length);
	}
	view.dialog.creditLimitDialog.show();
}
function creditLimitSubmitClickEvent() {
    var haveCheck = false;
    var creditLimitTransList = [];
    var table=$("#subScriptionListTb")
    var data=table.bootstrapTable("getData")
    $( "input[name^='subnoChecked_']" ).each(function(i,e) {
        if($(this).prop('checked')){
            var rowid= $(this).attr("data-index");
            console.log(rowid)
            var baHidden = $("#subnoBa_"+rowid).val();
            var invoiceHidden = $("#subnoInvoice_"+rowid).val();
            var totalChargeHidden = $("#subnoTotalCharge_"+rowid).val();
            var vatHidden = $("#subnoVat_"+rowid).val();
            var amountHidden = $("#subnoAmount_"+rowid).val();
            var kenanHidden = $("#subnoKenan_"+rowid).val();
            var issueDateHidden = $("#subnoIssueDate_"+rowid).val();
            var dueDateHidden = $("#subnoDueDate_"+rowid).val();
            var receivedHidden = $("#subnoReceived_"+rowid).val();
            var creditLimitDd = $("#creditLimitDd"+rowid).val();
            var subnoMsisdnSize =  $("#subnoMsisdnSize_"+rowid).val();
            haveCheck = true;
            var tagName = $("#creditLimitDd"+rowid).prop("tagName");
           // alert(tagName)
            var msisdnList = [];
            if(tagName=='INPUT'){
                msisdnList = view.session("subScriptionList");
            }else{
                $("#creditLimitDd"+rowid+" > option").each(function() {
                    if(this.value!='0')
                        msisdnList.push(this.text)
                    // alert(this.text + ' ' + this.value);
                });
            }
           // alert(msisdnList)
            console.log(msisdnList);
            var creditLimitTrans = {
                "contract":baHidden,
                "arRef":invoiceHidden,
                "msisdn":creditLimitDd,
                "amountIncVat":totalChargeHidden,
                "vatAmount":vatHidden,
                "amountExVat":amountHidden,
                "accountNo":kenanHidden,
                "arInvdate":issueDateHidden,
                "arDuedate":dueDateHidden,
                "received":receivedHidden,
                "msisdnSize":subnoMsisdnSize,
                "msisdnList":msisdnList,
                "status":"N"
            };
            creditLimitTransList.push(creditLimitTrans);
        }
    });
    console.log(creditLimitTransList)
    view.session("creditLimitTransList", creditLimitTransList);

    var subScriptionList = view.session("subScriptionList");
	var creditLimitData = (subScriptionList.length == 1 ? "S|": "M|");
	var billingList = view.session("billingList");
	for(var i = 0, m = billingList.length; i < m; i++) {
		for(var j = 0, n = billingList[i].invoiceList.length; j < n; j++) {
			var invoice = billingList[i].invoiceList[j];
			if(true == invoice.checked) {
				creditLimitData += $("#creditLimitDd"+j).val() +"|";
			}
		}
	}
	view.session("creditLimitData", creditLimitData);
	view.session("subScriptionData", (subScriptionList.length == 1 ? subScriptionList[0]: subScriptionList.length));
	view.dialog.creditLimitDialog.hide();
}
function addWithholdingTaxNoClickEvent() {
	if(validateAddWithholdingTaxNo()) {
		var taxType = view.radio.withholdingTaxType.label();
		view.table.withholdingTaxList.insert(["-", view.input.withholdingInvoiceNo.val(), view.input.withholdingTaxNo.val(), taxType, view.input.withholdingTaxAmount.val()], true, {"type": view.radio.withholdingTaxType.type()});
	}
}
function validateAddWithholdingTaxNo() {
	var isValid = true;
// 	if(view.input.withholdingTaxNo.val() == "") {view.input.withholdingTaxNo.error(true); isValid = false;} else {view.input.withholdingTaxNo.error(false);}
	return isValid;
}
function addFeeTaxNoClickEvent() {
	view.table.feeTaxList.insert(["-", view.input.feeDepartmentCost.val(), view.radio.feeTaxType.label(), view.input.feeWTAmount.val(), view.input.feeBuyTaxAmount.val(), view.input.feeTaxAmount.val()], true);
    $('#feeTaxList td:nth-child(3)').hide();
}
function addPenaltyTaxNoClickEvent() {
    view.table.penaltyTaxList.insert(["-", view.input.penaltyInvoiceNo.val(), view.radio.penaltyTaxType.label(), view.input.penaltyTaxAmount.val()], true);
    $('#penaltyTaxList td:nth-child(3)').hide();
}
function addRetentionTaxNoClickEvent() {
    view.table.retentionTaxList.insert(["-", view.input.retentionInvoiceNo.val(), view.radio.retentionTaxType.label(), view.input.retentionTaxAmount.val()], true);
    $('#retentionTaxList td:nth-child(3)').hide();
}
function addCompensationTaxNoClickEvent() {
	view.table.compensationTaxList.insert(["-", view.input.compensationDepartmentCost.val(), view.input.compensationTaxAmount.val()], true);
}
function payChqSubmitClickEvent() {
	if(validationChqSubmit()) view.table.payChqList.insert(["-", view.input.payChqBankCode.val(), view.input.payChqBankName.val(), view.input.payChqBranch.val(), view.input.payChqNo.val(), view.input.payChqDate.val(), view.input.payChqAmount.val()], true)
}
function validationChqSubmit() {
	var isValid = true;
	if(view.input.payChqNo.val() == "") {view.input.payChqNo.error(true); isValid = false;} else {view.input.payChqNo.error(false);}
	if(view.input.payChqDate.val() == "") {view.input.payChqDate.error(true); isValid = false;} else {view.input.payChqDate.error(false);}
	return isValid;
}
function payCCSubmitClickEvent() {
	if(validatePayCCSubmit()) view.table.payCCList.insert(["-", view.input.payCCType.val(), view.input.payCCNo.val(), view.input.payCCBankName.val(), view.input.payCCAmount.val()], true)
}
function validatePayCCSubmit() {
	var isValid = true;
	if(view.input.payCCType.index() === 0) {view.input.payCCType.error(true); isValid = false;} else {view.input.payCCType.error(false);}
	if(view.input.payCCNo.val() == "" || view.input.payCCNo.val() < 16) {view.input.payCCNo.error(true); isValid = false;} else {view.input.payCCNo.error(false);}
	if(view.input.payCCBankName.index() === 0) {view.input.payCCBankName.error(true); isValid = false; } else {view.input.payCCBankName.error(false);}
	return isValid;
}
function payMoneyOrderButtonSubmitClickEvent() {
	if(validatePayMoneyOrderButtonSubmit()) view.payMoneyOrderTableList.insert(["-", view.payMoneyOrderInputNo.val(), view.payMoneyOrderInputDate.val(), view.payMoneyOrderInputPostCode.val(), view.payMoneyOrderInputAmount.val()], true);
}
function validatePayMoneyOrderButtonSubmit() {
	var isValid = true;
	if(view.payMoneyOrderInputDate.val() == "") { view.payMoneyOrderInputDate.error(true); isValid = false; } else {view.payMoneyOrderInputDate.error(false);}
	if(view.payMoneyOrderInputNo.val() == "") { view.payMoneyOrderInputNo.error(true); isValid = false; } else {view.payMoneyOrderInputNo.error(false);}
	return isValid;
}
function payBillExchgButtonSubmitClickEvent() {
	if(validatePayBillExchgButtonSubmit()) view.payBillExchgTableList.insert(["-", view.payBillExchgInputNo.val(), view.payBillExchgInputDate.val(), view.payBillExchgInputPostCode.val(), view.payBillExchgInputAmount.val()], true);
}
function validatePayBillExchgButtonSubmit() {
	var isValid = true;
	if(view.payBillExchgInputDate.val() == "") { view.payBillExchgInputDate.error(true); isValid = false; } else {view.payBillExchgInputDate.error(false); }
	if(view.payBillExchgInputNo.val() == "") { view.payBillExchgInputNo.error(true); isValid = false; } else {view.payBillExchgInputNo.error(false); }
	return isValid;
}
function payCouponButtonSubmitClickEvent() {
// 	if(validatePayCouponButtonSubmit()) view.payCouponTableList.insert(["-", view.payCouponInputNo.val(), "", "", view.payCouponInputAmt.val()], true);
	view.dialog.mainMessageDialog.clear().hide();
	if(validatePayCouponButtonSubmit()){
		var isCouponNoDup = false;
		payCouponTableListData = view.payCouponTableList.data();
		$.map((window["payCouponTableListData"] || []), function(o, i) {
			if(view.payCouponInputNo.val() == o[1]){
				isCouponNoDup = true;
			}
		});

		if(isCouponNoDup){
			view.dialog.mainMessageDialog.clear().error(["เลขที่คูปองซ้ำ"]).show();
			return false;
		}else{
			$.get("../getCouponDetail.json", { "couponId": view.payCouponInputNo.val() }, function(data) {
				console.log("getCouponDetail.")
				console.log(data)
				if(data!=null && data.rsHeader!=null && data.rsHeader.statusCode!=null){
					if(data.rsHeader.statusCode == '30' ){
						view.dialog.mainMessageDialog.clear().error(["คูปองนี้ถูกใช้แล้ว"]).show();
						return false;
					}
					if(data.rsHeader.statusCode == '20' ){
						view.dialog.mainMessageDialog.clear().error(["คูปองหมดอายุ"]).show();
						return false;
					}
					if(data.rsHeader.statusCode == '10' ){
						view.dialog.mainMessageDialog.clear().error(["เลขที่คูปองไม่ถูกต้อง"]).show();
						return false;
					}
					if(data.rsHeader.statusCode == '00' ){
						view.payCouponTableList.insert(["-", data.rsBody.rsData[0].code, data.rsBody.rsData[0].name,  view.utils.dateFormat(data.rsBody.rsData[0].lastDate), data.rsBody.rsData[0].amount], true);
					} else {
						view.dialog.mainMessageDialog.clear().error(["ไม่สามารถติดต่อระบบ INSALE ได้"]).show();
						return false;						
					}
				}else{
					view.dialog.mainMessageDialog.clear().error(["ไม่สามารถติดต่อระบบ INSALE ได้"]).show();
					return false;
				}
				/*
				if(data != null && data.length > 0){
					if(data[0].Error == 'N'){
						view.dialog.mainMessageDialog.clear().error(["เลขที่คูปองไม่ถูกต้อง"]).show();
						return false;
					}else if(data[0].status == 'W'){
						view.payCouponTableList.insert(["-", data[0].code, data[0].name, data[0].lastDttm, data[0].amount], true);
					}else if(data[0].Error == 'U' || data[0].status == 'S'){
						view.dialog.mainMessageDialog.clear().error(["คูปองนี้ถูกใช้แล้ว"]).show();
						return false;
					}else if(data[0].Error == 'E' || data[0].status == 'E'){
						view.dialog.mainMessageDialog.clear().error(["คูปองหมดอายุ"]).show();
						return false;
					}else if(data[0].Error == 'F'){
						view.dialog.mainMessageDialog.clear().error(["Fail/Error Response"]).show();
						return false;
					}
				}
				*/
			});
		}
	}
}
function validatePayCouponButtonSubmit() {
	var isValid = true;
	if(view.payCouponInputNo.val() == "") { view.payCouponInputNo.error(true); isValid = false; } else {view.payCouponInputNo.error(false); }
	return isValid;
}
function payBankTxnfButtonSubmitClickEvent() {
	if(validatePayBankTxnfButtonSubmit()) view.payBankTxnfTableList.insert(["-", view.input.payBankTxnfDropDownBankCode.val(), view.input.payBankTxnfDropDownBankName.val(), view.payBankTxnfDropDownBankBrnh.val(), view.payBankTxnfInputNo.val(), view.payBankTxnfInputDate.val(), view.payBankTxnfInputAmt.val(), view.payBankTxnfDropDownBankAcct.val(), view.payBankTxnfDropDownBankAcCd.val() ], true);
    $('#payBankTxnfTableList td:nth-child(8), #payBankTxnfTableList td:nth-child(9)').hide();
}
function validatePayBankTxnfButtonSubmit() {
	var isValid = true;
	if(view.input.payBankTxnfDropDownBankCode.val() == "") { view.input.payBankTxnfDropDownBankCode.error(true); isValid = false; } else {view.input.payBankTxnfDropDownBankCode.error(false); }
	if(view.input.payBankTxnfDropDownBankName.index() === 0) { view.input.payBankTxnfDropDownBankName.error(true); isValid = false; } else {view.input.payBankTxnfDropDownBankName.error(false); }
	if(view.payBankTxnfDropDownBankBrnh.index() === 0) { view.payBankTxnfDropDownBankBrnh.error(true); isValid = false; } else {view.payBankTxnfDropDownBankBrnh.error(false); }
	//if(view.payBankTxnfInputNo.val() == "") { view.payBankTxnfInputNo.error(true); isValid = false; } else {view.payBankTxnfInputNo.error(false); } //by NSD 17-02-2017
	if(view.payBankTxnfDropDownBankAcCd.index() === 0) { view.payBankTxnfDropDownBankAcCd.error(true); isValid = false; } else {view.payBankTxnfDropDownBankAcCd.error(false); }
	if(view.payBankTxnfInputDate.val() == "") { view.payBankTxnfInputDate.error(true); isValid = false; } else {view.payBankTxnfInputDate.error(false); }
	if(view.payBankTxnfDropDownBankAcct.index() === 0) { view.payBankTxnfDropDownBankAcct.error(true); isValid = false; } else {view.payBankTxnfDropDownBankAcct.error(false); }
	return isValid;
}
function payBankTxnfButtonSubmitGfClickEvent() {
	if(validatePayBankTxnfButtonSubmitGf()) view.payBankTxnfTableListGf.insert(["-", view.payBankTxnfInputBankCodeGf.val(), view.payBankTxnfDropDownBankNameGf.val(), view.payBankTxnfDropDownBankBrnhGf.val(), view.payBankTxnfInputNoGf.val(), view.payBankTxnfInputDateGf.val(), view.payBankTxnfInputAmtGf.val(), view.payBankTxnfDropDownBankAcctGf.val(), view.payBankTxnfDropDownBankAcCdGf.val()], true);
    $('#payBankTxnfTableListGf td:nth-child(8), #payBankTxnfTableListGf td:nth-child(9)').hide();
}
function validatePayBankTxnfButtonSubmitGf() {
	var isValid = true;
	if(view.payBankTxnfInputBankCodeGf.val() == "") { view.payBankTxnfInputBankCodeGf.error(true); isValid = false; } else {view.payBankTxnfInputBankCodeGf.error(false); }
	if(view.payBankTxnfDropDownBankNameGf.index() === 0) { view.payBankTxnfDropDownBankNameGf.error(true); isValid = false; } else {view.payBankTxnfDropDownBankNameGf.error(false); }
	if(view.payBankTxnfDropDownBankBrnhGf.index() === 0) { view.payBankTxnfDropDownBankBrnhGf.error(true); isValid = false; } else {view.payBankTxnfDropDownBankBrnhGf.error(false); }
	//if(view.payBankTxnfInputNo.val() == "") { view.payBankTxnfInputNo.error(true); isValid = false; } else {view.payBankTxnfInputNo.error(false); } //by NSD 17-02-2017
	if(view.payBankTxnfDropDownBankAcCdGf.index() === 0) { view.payBankTxnfDropDownBankAcCdGf.error(true); isValid = false; } else {view.payBankTxnfDropDownBankAcCdGf.error(false); }
	if(view.payBankTxnfInputDateGf.val() == "") { view.payBankTxnfInputDateGf.error(true); isValid = false; } else {view.payBankTxnfInputDateGf.error(false); }
	if(view.payBankTxnfDropDownBankAcctGf.index() === 0) { view.payBankTxnfDropDownBankAcctGf.error(true); isValid = false; } else {view.payBankTxnfDropDownBankAcctGf.error(false); }
	return isValid;
}
function payOffsetButtonSubmitClickEvent() {
	if(validatePayOffsetButtonSubmit()) view.payOffsetTableList.insert(["-", view.payOffsetDocumentNo.val(), view.payOffsetAccountCode.val(), view.payOffsetAccountName.val(), view.payOffsetAmount.val()], true);
}
function validatePayOffsetButtonSubmit() {
	var isValid = true;
	if(view.payOffsetDocumentNo.val() == "") { view.payOffsetDocumentNo.error(true); isValid = false; } else {view.payOffsetDocumentNo.error(false); }
	if(view.payOffsetAccountCode.val() == "") { view.payOffsetAccountCode.error(true); isValid = false; } else {view.payOffsetAccountCode.error(false); }
	if(view.payOffsetAccountName.val() == "") { view.payOffsetAccountName.error(true); isValid = false; } else {view.payOffsetAccountName.error(false); }
	return isValid;
}
function payOtherButtonSubmitClickEvent() {
	if(validatePayOtherButtonSubmit()) view.payOtherTableList.insert(["-", view.payOtherInputNo.val(), view.payOtherDropDownChannel.val(), view.payOtherInputDate.val(), view.payOtherInputAmt.val()], true);
}
function validatePayOtherButtonSubmit() {
	var isValid = true;
	if(view.payOtherDropDownChannel.index() === 0) { view.payOtherDropDownChannel.error(true); isValid = false; } else {view.payOtherDropDownChannel.error(false); }
	//if(view.payOtherInputNo.val() == "") { view.payOtherInputNo.error(true); isValid = false; } else {view.payOtherInputNo.error(false); } //by NSD 17-02-2017
	if(view.payOtherInputDate.val() == "") { view.payOtherInputDate.error(true); isValid = false; } else {view.payOtherInputDate.error(false); }
	return isValid;
}

function payForeignButtonSubmitClickEvent() {
	if(validatePayForeignButtonSubmit()) view.payForeignTableList.insert(["-", $('#currencyTypeSelect option:selected').attr('data-message')/* $("#currencyTypeSelect").val() */, view.exchangeRateInput.val(), view.payDateInputDate.val(), view.foreignAmountInput.val(), view.thAmountInput.val(), view.payBankTxnfDropDownBank.val()], true);
	$('#payForeignTableList td:nth-child(7)').hide();
	addExchangRate();
}
function validatePayForeignButtonSubmit() {
	var isValid = true;
	if($("#currencyTypeSelect").val() == 0) { $("#currencyTypeSelect").css("border-color","#a94442");/*view.currencyTypeSelect.error(true);*/ isValid = false; } else { $("#currencyTypeSelect").css("border-color","#ccc");/*view.currencyTypeSelect.error(false);*/ }
	if(view.payDateInputDate.val() == "") { view.payDateInputDate.error(true); isValid = false; } else {view.payDateInputDate.error(false); }
	return isValid;
}

function advancePaymentSubmitClickEvent() {
	var balanceDue = view.input.balanceDue.val(), received = view.inputReceived.val(), advancedLimit = numberFormatter(((received * 100) - (balanceDue * 100)) / 100);
	console.log("received : "+received +" , balanceDue : "+balanceDue+", advancedLimit : "+advancedLimit);
	var sum = (view.table.customerList.sumInput(4) * 100) / 100;
	var change = (numberFormatter(received - balanceDue - sum)*100)/100;
	var cash = stripToNumber(view.table.payTypeList.find("method", 0).find("td:eq(2) div").text());//numberFormatter(view.table.payTypeList.find("method", 0).find("td:eq(2) div").text());
	console.log("view.table.customerList.sumInput(4) : "+view.table.customerList.sumInput(4)+", cash = "+cash+", change = "+change+", sum = "+sum);
	view.dialog.advancePaymentMessage.clear();
	if (sum > advancedLimit && sum != 0) {
		view.dialog.advancePaymentMessage.error(["ระบบอนุญาตให้ทำการกำหนด จำนวนเงินการจ่ายล่วงหน้ารวมกันแล้วเท่ากับ "+ numberFormatter(advancedLimit) +" บาท"]).show()
		return;
	}else if(change > cash){
		view.dialog.advancePaymentMessage.error(["จำนวนเงินที่ชำระล่วงหน้านี้ทำให้จำนวนเงินทอนมากกว่าเงินสดที่รับชำระมา จึงไม่สามารถทำการทอนเงินได้"]).show()
		return;
	}
	view.input.change.val(change);
	view.inputAdvanced.val(sum);
	view.dialog.advancePayment.hide();
// 	calculate();
}
function otherPaymentSubmitClickEvent() {
	var balanceDue = view.input.balanceDue.val(), received = view.inputReceived.val(), advancedLimit = numberFormatter(((received * 100) - (balanceDue * 100)) / 100);
	console.log("received : "+received +" , balanceDue : "+balanceDue+", advancedLimit : "+advancedLimit);
	var sum = (view.table.customerList2.sumInput(4) * 100) / 100; //why?
    var change = (numberFormatter(received - balanceDue - sum)*100)/100;
    console.log("CustNote :::: "+$('#custNote').val());
	view.dialog.advancePaymentMessage2.clear();
	if (sum > advancedLimit && sum != 0) {
		view.dialog.advancePaymentMessage2.error(["ระบบอนุญาตให้ทำการกำหนด จำนวนเงินการจ่ายล่วงหน้ารวมกันแล้วเท่ากับ "+ numberFormatter(advancedLimit) +" บาท"]).show()
		return;
	}
    view.input.change.val(change);
	view.inputAdvanced.val(sum);
	view.dialog.otherProfitVat.hide();
	//calculate2();
}
function detailFormatter(val, row, ind) {
	var details = '<table class="table table-striped table-bordered">'
		+ '<thead>'
		+ '<tr>'
		+ '<th class="text-center">เลขที่ใบแจ้งค่าใช้บริการ</th>'
		+ '<th class="text-center">วันที่จัดทำใบแจ้งค่าใช้บริการ</th>'
		+ '<th class="text-center">วันที่ครบกำหนด</th>'
		+ '<th class="text-right">ยอดก่อนภาษี</th>'
		+ '<th class="text-right">ภาษีมูลค่าเพิ่ม</th>'
		+ '<th class="text-right">ยอดเงินรวมภาษี</th>'
        + '<th class="text-right">ส่วนลดหลังการขาย</th>'

		+ '<th class="text-right">ยอดชำระ</th>'
		+ '<th class="text-right">ภาษีหัก ณ ที่จ่าย</th>'
        + '<th class="text-right">จำนวนเงินคงค้าง</th>'
		+ '<th class="text-center">รอบการใช้งาน</th>' + '</tr>'
		+ '</thead>' + '<tbody>';
		console.log("XXXUUU")
		console.log(row)
	for (var i = 0, m = row.invoices.length; i < m; i++) {
		var multipy = 1 ;
	
        var totalCharge = 0, amtBeforeVat =0, amtVat =0, flgExchange = false;
        if(exchangeRate==null || exchangeRate == 0.00){
            exchangeRate = 1.0;
        }else{
            totalCharge = row.invoices[i].totalCharge*exchangeRate;
            if(row.invoices[i].vatRate!=null)
                amtVat = totalCharge*row.invoices[i].vatRate/(100+row.invoices[i].vatRate);
            amtBeforeVat = totalCharge - amtVat;
            flgExchange = true;
        }
        multipy = exchangeRate;
        if(row.invoices[i].attributes=='A')
        	multipy = 1.0;
        //alert(multipy)
		var issueDt = row.invoices[i].issueDt, dueDt = row.invoices[i].dueDt;
		details += '<tr>'
		+ '<td class="text-center">'+ $.trim(row.invoices[i].no) +'</td>'
		+ '<td class="text-center">'+ (issueDt == null ? "-" : view.utils.dateFormat(issueDt)) +'</td>'
		+ '<td class="text-center">'+ (dueDt == null ? "-" : view.utils.dateFormat(dueDt)) +'</td>'
		//+ '<td class="text-right">'+ view.utils.numberFormat(flgExchange? amtBeforeVat:row.invoices[i].charge) +'</td>'
		+ '<td class="text-right">'+ view.utils.numberFormat( row.invoices[i].charge*multipy) +'</td>'
		//+ '<td class="text-right">'+ (row.invoices[i].vat == null ? "-" : view.utils.numberFormat(flgExchange? amtVat: row.invoices[i].vat*multipy)) +'</td>'
		+ '<td class="text-right">'+ (row.invoices[i].vat == null ? "-" : view.utils.numberFormat(row.invoices[i].vat*multipy)) +'</td>'
        	+ '<td class="text-right">'+ view.utils.numberFormat(row.invoices[i].totalCharge*multipy) +'</td>'
            + '<td class="text-right">'+ view.utils.numberFormat(row.invoices[i].afterSaleDiscount) +'</td>'

		+ '<td class="text-right">'+ view.utils.numberFormat(row.invoices[i].totalCharge*multipy-row.invoices[i].afterSaleDiscount-(row.invoices[i].afterSaleDiscVat == null? 0 : row.invoices[i].afterSaleDiscVat)) +'</td>'
		+ '<td class="text-right">'+ view.utils.numberFormat(row.invoices[i].deduction) +'</td>'
            + '<td class="text-right">'+ view.utils.numberFormat(row.invoices[i].balanceDue*multipy) +'</td>'
		+ '<td class="text-center">'+ (row.invoices[i].billCycle == null? "-" : $.trim(row.invoices[i].billCycle))+'</td>'
		+ '</tr>';
	}
	return details + '</tbody>'
		+ '</table>';
}
function  unLockInvoice(){
	var invoiceLockList=[];
	var billingList = view.session("billingList");
	for (var i = 0, m = billingList.length; i < m; i++) {
		for (var j = 0, n = billingList[i].invoiceList.length; j < n; j++) {
			var invoice = billingList[i].invoiceList[j];
			var invoiceLock={
					invNo:invoice.no,
					mode:"REMOVE"
			}
			invoiceLockList.push(invoiceLock)
		}
	}
	console.log(invoiceLockList)
	$.ajax({
		  type: "POST",
		  url: "../manageDuplicatePayQueue.json",
		  data: JSON.stringify(invoiceLockList),
		  dataType: "json",
		  contentType: "application/json; charset=utf-8",
		  success:function(data){
			    view.session("billingList", []);
			    window.location.href="pay-service-charge.jsp?new"
		  }
		});
}

function calculateVatFromIncludedWT(received, vatRate) {
	if(vatRate == null)
		vatRate = 7;
	return (received / (100 + vatRate)) * vatRate
}
//function calculateVatWT(balanceDue, received, vatRate, deduction, totalCharge, isStateAgency) {
function calculateVatWT(balanceDue, received, vatRate, deduction, totalCharge, wtAvg) {
	console.log("calculateVatWT: balanceDue>"+balanceDue+" received>"+received+" deduction>"+deduction+" totalCharge>"+totalCharge)
	if(deduction == 0){return deduction;}
	if(balanceDue == received && totalCharge != balanceDue){ // Partial + Full Payment
		var vat = calculateVatFromIncludedWT(received, vatRate);
		//return (received - vat)  * ((isStateAgency) ? 0.01 : 0.03);
		return ( (received - vat)  * wtAvg ).toFixed(2);
	} else if (balanceDue == received && totalCharge == balanceDue){ // Fully Payment
		return deduction;
	} else if (balanceDue != received && totalCharge == balanceDue){ // Partial Payment
		var vat = calculateVatFromIncludedWT(received, vatRate);
		//return (received - vat)  * ((isStateAgency) ? 0.01 : 0.03);
		return ( (received - vat)  * wtAvg).toFixed(2);
	}else if (balanceDue != received && totalCharge != balanceDue){ // Partial + Partial Payment
		var vat = calculateVatFromIncluded(received, vatRate);
		//return (received - vat)  * ((isStateAgency) ? 0.01 : 0.03);
		return ( (received - vat)  * wtAvg).toFixed(2);;
	}
}
view.button.setPayType.elem.click(function(){
	var billingCurrency = view.session("billingCurrency"), currencyCode = $('#currencyTypeSelect option:selected').attr('data-code');
	$('#currencyTypeSelect').val(billingCurrency);
	addExchangRate();
});
function addExchangRate(){
    var Type = $('#currencyTypeSelect').val(), billingCurrency = view.session("billingCurrency");
	var balanceDue = ((view.input.totalCharge.val() * 100) + (view.table.deductionList.sum(2) * 100)) / 100;
    var received = view.table.payTypeList.sum(2);
    currencyType = Type;
    currencyRate = $('#currencyTypeSelect option:selected').attr('data-rate');
  	currencyName = $('#currencyTypeSelect option:selected').attr('data-message');
  	currencyCode = view.currencyTypeSelect.val();
    
    view.exchangeRateInput.val(currencyRate);
    
    tmpAmount = balanceDue - received;
    if (tmpAmount>0) {
        view.thAmountInput.val(tmpAmount);
        view.foreignAmountInput.val(tmpAmount/currencyRate);//billingList[0].currencyRate);
    } else {
    	view.thAmountInput.val(0.00);
        view.foreignAmountInput.val(0.00);
    }
    //convertAmount();
}
function convertAmount() {
    var rate = view.exchangeRateInput.val();
    var amount = view.foreignAmountInput.val();
    var sum = 0;
    if (amount != 0) {
        sum = rate*amount;
    } else {
        sum = 0*amount;
    }
    view.thAmountInput.val(sum); 
}
$('#currencyTypeSelect').on('change', function(){
	var Type = $('#currencyTypeSelect').val(), billingCurrency = view.session("billingCurrency"), 
	currencyRate = $('#currencyTypeSelect option:selected').attr('data-rate'), currencyName = $('#currencyTypeSelect option:selected').attr('data-message'), currencyDate = $('#currencyTypeSelect option:selected').attr('data-date');
	if (billingCurrency == "th_TH" || Type == billingCurrency) {
    	view.dialog.mainMessageDialog.clear();
    } else { 
    	view.dialog.mainMessageDialog.clear().error(["ไม่สามารถดำเนินการได้ เนื่องจากระบุประเภทสกุลเงินต่างกัน"]).show();
    	return false;
   	}
});
</script>