<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
                    <li><i>รับชำระค่าเติมเงินออนไลน์ (Top up)</i></li>
                    <li>ตรวจสอบข้อมูลการเติมเงิน</li>
                    <li class="active">เลือกวิธีการชำระ</li>
                    <li>ผลการชำระ</li>
                </ol>
            </div>
        </div>
        <div id="message"></div>
		<div id="panelNavigation" class="row">
		<ul class="list-inline pull-right list-menu-set">
			<li><a id="buttonCreatePaymentAndPrint" class="btn btn-link"><span class="glyphicon glyphicon-print"></span> บันทึกและพิมพ์</a></li>
			<li><a id="buttonCancelPayment" class="btn btn-link"><span class="glyphicon glyphicon-remove-circle"></span> ยกเลิกรายการ</a></li>
		</ul>
		</div>
		<div id="panelPaymentDetails" class="row">
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
<!--                 <a id="addTaxType" class="btn btn-link"><span class="glyphicon glyphicon-plus-sign"></span> เพิ่มรายการหัก</a> -->
                <div class="panel panel-default panal-radius">
                    <div class="panel-body" style="padding-right:0;padding-left:0">
                        <div class="col-md-12">
                            <div role="tabpanel" class="tab-pane hide" id="withholdingTaxPanel">
                                <div class="form-horizontal">
                                    <div class="form-group">
                                        <label class="control-label col-sm-6" >ประเภทภาษีหัก ณ ที่จ่าย :</label>
                                        <div class="col-sm-6">
                                            <label class="radio-inline"><input type="radio" name="withholdingTaxType" data-label="69 ทวิ" data-type="69BIS" id="69tvi" ><b> 69 ทวิ</b></label>&nbsp;&nbsp;
                                            <label class="radio-inline"><input type="radio" name="withholdingTaxType" data-label="3 เตรส" data-type="3TREDECIM" id="3trs"><b> 3 เตรส</b></label>&nbsp;&nbsp;
                                            <label class="radio-inline"><input type="radio" name="withholdingTaxType" data-label="69 ตรี" data-type="69TRE" id="69tri"><b> 69 ตรี</b></label>
                                        </div>
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
                                            <label class="radio-inline"><input type="radio" name="feeTaxType" data-label="ขาเข้า"><b> ขาเข้า</b></label>&nbsp;&nbsp;&nbsp;
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
                                            <label class="radio-inline"><input type="radio" name="penaltyTaxType" data-label="ขาเข้า"><b> ขาเข้า</b></label>&nbsp;&nbsp;&nbsp;
                                            <label class="radio-inline"><input type="radio" name="penaltyTaxType" data-label="ขาออก" checked><b> ขาออก</b></label>
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
                                            <th style="display:none;">ประเภทค่าปรับ</th>
                                            <th data-align="right" data-number-format="true">จำนวนเงิน</th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                </table>
                            </div>
                            <div role="tabpanel" class="tab-pane hide" id="retentionTaxPanel">
                                <div class="form-horizontal">
                                	<div class="form-group">
                                        <label class="control-label col-sm-6" >ประเภทเงินประกันผลงาน :</label>
                                        <div class="col-sm-5">
                                            <label class="radio-inline"><input type="radio" name="retentionTaxType" data-label="ขาเข้า" checked><b> ขาเข้า</b></label>&nbsp;&nbsp;&nbsp;
                                            <label class="radio-inline"><input type="radio" name="retentionTaxType" data-label="ขาออก"><b> ขาออก</b></label>
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
                                            <th style="display:none;">ประเภทเงินประกันผลงาน</th>
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
                            <!-- <li><a href="#">คูปอง</a></li> -->
                            <li><a href="#">เงินโอนในประเทศ</a></li>
                            <li><a href="#">offset</a></li>
                            <li><a href="#">เงินโอนต่างประเทศ</a></li>
                            <li><a href="#">อื่นๆ</a></li>
                            <li><a href="#">GFMIS</a></li>
                    </ul>
                </div>
<!--                 <a id="addPayType" class="btn btn-link"><span class="glyphicon glyphicon-plus-sign"></span> เพิ่มวิธีการรับชำระ</a> -->
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
                                        <div class="col-sm-4"><input class="form-control" id="payChqNo" maxlength="7"></div>
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
                                        </div>
                                        <label class="control-label col-sm-3" >เลขที่ธนาณัติ :</label>
                                        <div class="col-sm-3"><input class="form-control" id="payMoneyOrderInputNo"></div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-sm-3" >รหัสไปรษณีย์ต้นทาง :</label>
                                        <div class="col-sm-3"><input class="form-control" id="payMoneyOrderInputPostCode"></div>
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
                                        <div class="col-sm-3"><input class="form-control" id="payBillExchgInputNo"></div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-sm-3" >รหัสไปรษณีย์ต้นทาง :</label>
                                        <div class="col-sm-3"><input class="form-control" id="payBillExchgInputPostCode"></div>
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
                            <!-- <div role="tabpanel" class="tab-pane hide" id="payType6">
                                <div class="form-horizontal">
                                    <div class="form-group">
                                        <label class="control-label col-sm-9" >เลขที่คูปอง :</label>
                                        <div class="col-sm-3"><input class="form-control" id="payCouponInputNo"></div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-sm-9" >จำนวนเงิน :</label>
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
                            </div> -->
                            <div role="tabpanel" class="tab-pane hide" id="payType7">
                                <div class="form-horizontal">
                                    <div class="form-group">
                                        <label class="control-label col-sm-4" >รหัสธนาคาร :</label>
                                        <div class="col-sm-3"><select class="form-control" id="payBankTxnfInputBankCode" ></select></div>
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
                                            <th style="display:none;"></th>
                                            <th style="display:none;"></th>
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
                                            <th>เลขที่เอกสาร</th>
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
										<label class="control-label col-sm-4">รหัสธนาคาร :</label>
										<div class="col-sm-3">
											<input class="form-control" id="payBankTxnfInputBankCodeGf"
												maxlength="5">
										</div>
										<label class="control-label col-sm-2">ชื่อธนาคาร :</label>
										<div class="col-sm-3">
											<select class="form-control" id="payBankTxnfInputBankNameGf"></select>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-sm-4">สาขา :</label>
										<div class="col-sm-3">
											<select class="form-control"
												id="payBankTxnfDropDownBankBrnhGf"></select>
										</div>
										<label class="control-label col-sm-2">เลขที่อ้างอิง :</label>
										<div class="col-sm-3">
											<input class="form-control" id="payBankTxnfInputNoGf"
												maxlength="10">
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-sm-4">รหัสบัญชีเงินฝากธนาคาร
											:</label>
										<div class="col-sm-3">
											<select class="form-control"
												id="payBankTxnfDropDownBankAcCdGf"></select>
										</div>
										<label class="control-label col-sm-2">วันที่โอน :</label>
										<div class="col-sm-3">
											<div class="input-group input-append date"
												data-provide="datepicker" data-date-format="dd/mm/yyyy">
												<input class="form-control" id="payBankTxnfInputDateGf"
													placeholder="dd/MM/yyyy" maxlength="10"> <span
													class="input-group-addon add-on"><span
													class="glyphicon glyphicon-calendar"></span></span>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-sm-4">เลขที่บัญชีเงินฝากธนาคาร
											:</label>
										<div class="col-sm-3">
											<select class="form-control"
												id="payBankTxnfDropDownBankAcctGf"></select>
										</div>
										<label class="control-label col-sm-2">จำนวนเงิน :</label>
										<div class="col-sm-3">
											<input class="form-control text-right"
												id="payBankTxnfInputAmtGf">
										</div>
									</div>
								</div>
								<a class="btn btn-warning pull-right"
									id="payBankTxnfButtonSubmitGf"><span
									class="glyphicon glyphicon-plus-sign"></span> เพิ่มรายการอื่น ๆ</a>
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
                        </div>
                    </div>
                </div>
            </div>
		</div>
		<!-- <div id="linkPanel" class="row">
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
		<div id="panelMethodSummary" class="row">
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
		<div id="panelPaymentSummary" class="row">
			<div class="col-md-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">สรุปยอดเงินที่ต้องชำระ</div>
                        <div class="panel-body">
                            <div class="form-horizontal">
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
                                    <label class="control-label col-sm-10">  
                                        <div class="checkbox">
                                            <label>
                                                <input type="checkbox" id="check_dis_notused" disabled> <b>ลูกค้ารับภาระภาษี&nbsp;&nbsp;&nbsp;<span class="glyphicon glyphicon-lock"></span> ส่วนลด :</b>
                                            </label>
                                        </div>
                                    </label>
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
	<div id="panelSummaryPaymentList" style="display: none">
        <ul class="list-inline pull-right list-menu-set">
            <li><a href="pay-7-stap_1.jsp?new"><span class="glyphicon glyphicon glyphicon-arrow-left"></span> กลับไปหน้าการรับชำระ</a></li>
        </ul>
        <div class="row">
            <div class="col-md-12">
                <div class="panel panel-success">
                    <div class="panel-heading">ผลการรับชำระเงิน</div>
                    <div class="panel-body">
                        <table id="tableSummaryReceipts" data-row-style="rowStyle" data-toggle="table" 
                        	data-detail-view="true" data-detail-formatter="detailFormatter"
                        	data-classes="table table-hover table-striped">
                            <thead>
                                <tr>
                                    <th data-align="center" data-formatter="runningFormatter">#</th>
                                    <th data-field="custNo">เลขที่ลูกค้า</th>
                                    <th data-field="custName">ชื่อลูกค้า</th>
                                    <th data-field="receiptNo">เลขที่ใบเสร็จรับเงิน/ใบกำกับภาษี</th>
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
		obj.next = function(){ obj.index(++index); return this }; obj.prev = function(){ obj.index(--index); return this }
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
	function Radio(el) { var obj = this; obj.el = el; obj.elem = $(el);
		obj.disable = function(){ if (arguments.length == 2 && $.isNumeric(arguments[0])) obj.elem.eq(arguments[0]).attr("disabled", (arguments[1] != null && $.type(arguments[1]) == 'boolean' ? arguments[1] : false)); return this }; obj.enable = function(){ obj.disable(false); return this }
		obj.label = function() { return this.elem.filter(":checked").data("label") };
		obj.type = function() { return this.elem.filter(":checked").data("type") };
		obj.val = function() { var s = this.elem.filter(":checked"),val = s.val(); return !val ? s.data("label") : val };
		obj.checked = function(index) { obj.elem.eq(index).attr("checked", true); return this }
		obj.unchecked = function() { obj.elem.each(function(i,o){ o.checked = false }); return this }
		obj.click = function(evt) { obj.elem.each(function(i,o){ $(o).click(function(e){ if (o.checked) evt(o.value) }) }); return this }
	}
	function ButtonGroup(el) {
		var obj = this, index = 0;
		obj.el = el;
		obj.elem = $(el);
		obj.val = function() { return obj.elem.find(".selection").text() }
		obj.index = function() { if (arguments.length == 1) { obj.list[arguments[0]].click() } return index }
		obj.list = obj.elem.find(".dropdown-menu a").each(function(i){ $(this).click(function(){ index = i }) });
		obj.hideIndex = function() { if (arguments.length == 1) { var selected = obj.elem.find(".dropdown-menu a")[arguments[0]]; $(selected).addClass("hide");} return index }
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
            //    obj.elem.append('<option data-index="'+ i +'" data-key="'+ o.key +'" value="'+ o.value +'"e>'+ o.value +'</option>')
            }
        });
    }
    this.init(url);
    //data = obj.elem.change(window[el.substring(1) +"ChangeEvent"]).find("option").map(function(i, opt){ return { key: opt.getAttribute("data-key"), value: opt.value, text: opt.textContent } });
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
		/*obj.insert = function(array, showRemove, attrs) {
			if (arguments.length < 1) array = $.map(headers,function(o){ return " " });
			obj.body.append("<tr "+ $.map($.extend(attrs, {}), function(v, k){ return k +"='"+ v +"'" }).join(" ") +">"
				+ $.map(array, function(v,i){ var field = headers[i].field, value = v;
					if (headers[i].runningNo) value = (obj.size() + 1);
					else if (headers[i].numberFormat) { value = !$.isNumeric(v) ? "0.00" : parseFloat(v, 10).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,'); }
					else if (headers[i].checkbox) value = replace(checkboxHtml, null, field, i, v);
					else if (headers[i].radio) value = replace(radioHtml, null, field, i, v);
					else if (headers[i].input) value = replace(inputHtml, 'style="width:100%;margin:-4px 0;text-align:'+ (headers[i].align || "left") +'"', field, i, v);
					return '<td><div style="'+ (headers[i].align ? "text-align:"+ headers[i].align : "") +'">'+ value +'</div></td>' }).join("")
                        /!*if (headers[i].modifyButton) { colWidth += 45; value += '<a class="btn btn-link" onclick=\'(function(tableId, row, col){ window[tableId +"ModifyEvent"]($("#"+ tableId), row, col); })(this.parentNode.parentNode.parentNode.parentNode.parentNode.id, '+ JSON.stringify(array) +', this)\'><span class="glyphicon glyphicon-pencil"></span></a>'; } if (headers[i].removeButton) { colWidth += 45; value += '<a class="btn btn-link" onclick=\'(function(tableId, row, col){ window[tableId +"RemoveEvent"]($("#"+ tableId), row, col); col.parentNode.parentNode.parentNode.remove() })(this.parentNode.parentNode.parentNode.parentNode.parentNode.id, '+ JSON.stringify(array) +', this)\'><span class="glyphicon glyphicon-trash"></span></a>'; }
                        return '<td style="'+ (!headers[i].valign ? "" : "vertical-align:"+ headers[i].valign +";") + (!colStyle ? "" : colStyle +";") + (colWidth < 1 ? "" : "width:"+ colWidth +"px;") +'"><div style="'+ (headers[i].align ? "text-align:"+ headers[i].align : "") +'">'+ value +'</div></td>' }).join("")*!/
                        + (!showRemove ? "" : '<td style="width:40px;text-align:center"><a href="#" class="delList"><span class="glyphicon glyphicon-trash"></span></a></td>')
			+"</tr>"); return obj }*/
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
		obj.elem.find("thead th").each(function(i,o){ var elem = $(o); headers.push({ "field": elem.data("field"), "align": $.trim(elem.data("align")), "runningNo": elem.data("runningNo") === true, "numberFormat": elem.data("numberFormat") === true, "checkbox": elem.data("checkbox") === true, "radio": elem.data("radio") === true, "input": elem.data("input") === true }) });
		function replace(str, style, field, index, value){ var s = str; if (style) s = s.replace("\{style\}", style); return s.replace("\{field\}", $.trim(field)).replace("\{index\}", index).replace("\{value\}", value) }
		/*function extract(prop, col) { if (prop.checkbox) { var elem = col.children[0].children[0]; return !elem.checked ? "" : elem.value } else if (prop.radio) { var elem = col.children[0].children[0]; return !elem.checked ? "" : elem.value } else if (prop.input) { var elem = col.children[0].children[0]; return elem.value } return col.children[0].innerText }*/
        function extract(prop, col) { if (prop.checkbox) { var elem = col.children[0].children[0]; return !elem.checked ? "" : elem.value } else if (prop.radio) { var elem = col.children[0].children[0]; return !elem.checked ? "" : elem.value } else if (prop.input) { var elem = col.children[0].children[0]; return elem.value } return col.childNodes[0].nodeType === 3 ? col.textContent : col.children[0].innerHTML }//by NSD 10-03-2017
		if(obj.body.length < 1) { obj.elem.append("<tbody></tbody>"); obj.body = obj.elem.find("tbody") }
	}
	function BootstrapTable(el) { var obj = this, checkEvt = function(e){ console.log(e) }, uncheckEvt = checkEvt; obj.el = el; obj.elem = $(el);
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
	function Div(el) {
		var obj = this; obj.el = el; obj.elem = $(el);
		obj.hide = function() { obj.elem.addClass("hide"); return obj };
		obj.show = function(flag) { if (flag == null || flag) obj.elem.removeClass("hide"); else obj.hide(); return obj };
	}
	// TO SUPPORT ECLIPSE OUTLINE.
	self.breadcrumb = new Breadcrumb("#breadcrumb");
	self.message = new Message("#message");
	self.buttonAddTaxType = new Button("#addTaxType");
	self.buttonSetTaxType = new ButtonGroup("#setTaxType");
	self.buttonAddWithholdingTaxNo = new Button("#addWithholdingTaxNo");
	self.buttonAddFeeTaxNo = new Button("#addFeeTaxNo");
	self.buttonAddPenaltyTaxNo = new Button("#addPenaltyTaxNo");
	self.buttonAddRetentionTaxNo = new Button("#addRetentionTaxNo");
	self.buttonAddCompensationTaxNo = new Button("#addCompensationTaxNo");
	self.buttonAddPayType = new Button("#addPayType");
	self.buttonSetPayType = new ButtonGroup("#setPayType");
	self.buttonPayChqSubmit = new Button("#payChqSubmit");
	self.buttonPayCCSubmit = new Button("#payCCSubmit");
	self.buttonSubmitPayment = new Button("#submitPayment");
	
	self.panelNavigation = new Panel("#panelNavigation");
	self.panelPaymentDetails = new Panel("#panelPaymentDetails");
	self.panelPaymentSummary = new Panel("#panelPaymentSummary");
	self.panelMethodSummary = new Panel("#panelMethodSummary");
	self.linkPanel = new Panel("#linkPanel");
	self.panelSummaryPaymentList = new Panel("#panelSummaryPaymentList");
	self.panelTaxType = new FadePanel(self.buttonSetTaxType, "#withholdingTaxPanel","#feeTaxPanel", "#penaltyTaxPanel", "#retentionTaxPanel", "#compensationTaxPanel");
	self.panelPayType = new FadePanel(self.buttonSetPayType, "#payType1", "#payType2", "#payType3", "#payType4", "#payType5", "#payType7", "#payType8", "#payType11", "#payType9","#payType10");

	self.radioWithholdingTaxType = new Radio("[name=withholdingTaxType]");
	self.radioFeeTaxType = new Radio("[name=feeTaxType]");
	self.radioPenaltyTaxType = new Radio("[name=penaltyTaxType]");
	self.radioRetentionTaxType = new Radio("[name=retentionTaxType]");
	self.radioSpecialOptions = new Radio("[name=specialOptions]");
	
	self.inputPayCashAmount = new NumberInput("#payCashAmount");
	self.inputPayChqBankCode = new DropDown("#payChqBankCode").init("../findBankNameList.json", "code");
	self.inputPayChqBankName = new DropDown("#payChqBankName").init("../findBankNameList.json", "name");
	self.inputPayChqNo = new Input("#payChqNo");
	self.inputPayChqDate = new Input("#payChqDate");
	self.inputPayChqBranch = new Input("#payChqBranch");
	self.inputPayChqAmount = new NumberInput("#payChqAmount");
	self.inputPayCCType = new DropDown("#payCCType").data([{ key: "1", value: "VISA" },{ key: "2", value: "MASTER" }]);
	self.inputPayCCBankName = new DropDown("#payCCBankName").data([{ key: "1", value: "กรุงเทพ" },{ key: "2", value: "กสิกรไทย" }]);
	self.inputPayCCNo = new Input("#payCCNo");
	self.inputPayCCAmount = new NumberInput("#payCCAmount");
	self.inputWithholdingTaxNo = new Input("#withholdingTaxNo");
	self.inputWithholdingTaxAmount = new NumberInput("#withholdingTaxAmount");
	self.inputFeeDepartmentCost = new DropDown("#feeDepartmentCost").data([{ key: "1", value: "0000 จต." }]);
	self.inputFeeTaxAmount = new NumberInput("#feeTaxAmount");
	self.inputFeeWTAmount = new NumberInput("#feeWTAmount");
	self.inputFeeBuyTaxAmount = new NumberInput("#feeBuyTaxAmount");
	self.inputPenaltyTaxAmount = new NumberInput("#penaltyTaxAmount");
	self.inputRetentionTaxAmount = new NumberInput("#retentionTaxAmount");
	self.inputCompensationDepartmentCost = new DropDown("#compensationDepartmentCost").data([{ key: "1", value: "0000 จต." }]);
	self.inputCompensationTaxAmount = new NumberInput("#compensationTaxAmount");
	
	self.inputAdditionalDiscount = new NumberInput("#discount");
	self.inputCharge = new NumberInput("#charge");
	self.inputVat = new NumberInput("#vat");
	self.inputTotalCharge = new NumberInput("#totalCharge");
	self.inputDeduction = new NumberInput("#deduct");
	self.inputFee = new NumberInput("#fee");
	self.inputPenalty = new NumberInput("#penalty");
	self.inputRetention = new NumberInput("#retention");
	self.inputCompensation = new NumberInput("#compensation");
	self.inputBalanceDue = new NumberInput("#balanceDue");
	self.inputChange = new NumberInput("#change");
    self.inputReceived = new NumberInput("#inputReceived");
	self.inputAdvanced = new NumberInput("#inputAdvanced");
	
	self.tableDeductionList = new Table("#deductionList");
	self.tablePayTypeList = new Table("#payTypeList");
	self.tableWithholdingTaxList = new Table("#withholdingTaxList");
	self.tableFeeTaxList = new Table("#feeTaxList");
	self.tablePenaltyTaxList = new Table("#penaltyTaxList");
	self.tableRetentionTaxList = new Table("#retentionTaxList");
	self.tableCompensationTaxList = new Table("#compensationTaxList");
	self.tablePayChqList = new Table("#payChqList");
	self.tablePayCCList = new Table("#payCCList");
// 	self.tableReceiptList = new BootstrapTable("#receiptList");
	self.tableSummaryReceipts = new BootstrapTable("#tableSummaryReceipts");
	
	self.feeSummaryDiv = new Div("#feeSummaryDiv");
	self.penaltySummaryDiv = new Div("#penaltySummaryDiv");
	self.retentionSummaryDiv = new Div("#retentionSummaryDiv");
	self.compensationSummaryDiv = new Div("#compensationSummaryDiv");
	
	self.buttonPayMoneyOrderButtonSubmit = new Button("#payMoneyOrderButtonSubmit");
	self.inputPayMoneyOrderInputDate = new Input("#payMoneyOrderInputDate");
	self.inputPayMoneyOrderInputNo = new Input("#payMoneyOrderInputNo");
	self.inputPayMoneyOrderInputPostCode = new Input("#payMoneyOrderInputPostCode");
	self.inputPayMoneyOrderInputAmount = new NumberInput("#payMoneyOrderInputAmount");
	self.tablePayMoneyOrderTableList = new Table("#payMoneyOrderTableList");
	
	self.buttonPayBillExchgButtonSubmit = new Button("#payBillExchgButtonSubmit");
	self.inputPayBillExchgInputDate = new Input("#payBillExchgInputDate");
	self.inputPayBillExchgInputNo = new Input("#payBillExchgInputNo");
	self.inputPayBillExchgInputPostCode = new Input("#payBillExchgInputPostCode");
	self.inputPayBillExchgInputAmount = new NumberInput("#payBillExchgInputAmount");
	self.tablePayBillExchgTableList = new Table("#payBillExchgTableList");
	
	self.buttonPayCouponButtonSubmit = new Button("#payCouponButtonSubmit");
	self.inputPayCouponInputNo = new Input("#payCouponInputNo");
	//self.inputPayCouponInputAmt = new Input("#payCouponInputAmt");
    self.inputPayCouponInputAmt = new NumberInput("#payCouponInputAmt");
	self.tablePayCouponTableList = new Table("#payCouponTableList");
	
	self.buttonPayBankTxnfButtonSubmit = new Button("#payBankTxnfButtonSubmit");
	self.payBankTxnfDropDownBankCode = new DropDown("#payBankTxnfInputBankCode").init("../findBankNameList.json", "code");
    self.inputPayBankTxnfDropDownBankName = new DropDown("#payBankTxnfInputBankName").init("../findBankNameList.json", "name");//.data([{ key: "0", value: "กรุณาเลือกธนาคาร" },{ key: "006", value: "กรุงไทย" }]);
	
	self.inputPayBankTxnfInputNo = new Input("#payBankTxnfInputNo");
	self.inputPayBankTxnfInputAmt = new NumberInput ("#payBankTxnfInputAmt");
	self.inputPayBankTxnfInputDate = new Input("#payBankTxnfInputDate");
	self.inputPayBankTxnfDropDownBankBrnh = new DropDown("#payBankTxnfDropDownBankBrnh").data([{ key: "0123", value: "ปากเกร็ด" }]);
	self.inputPayBankTxnfDropDownBankAcct = new DropDown("#payBankTxnfDropDownBankAcct").data([{ key: "1", value: "123-6-02069-3" }]);
	self.inputPayBankTxnfDropDownBankAcCd = new DropDown("#payBankTxnfDropDownBankAcCd").data([{ key: "1", value: "11021044" }]); 
	self.tablePayBankTxnfTableList = new Table("#payBankTxnfTableList");
	
	self.buttonPayOtherButtonSubmit = new Button("#payOtherButtonSubmit");
	self.inputPayOffsetDocumentNo = new Input("#payOffsetDocumentNo");
	self.inputPayOffsetAccountCode = new Input("#payOffsetAccountCode");
	self.inputPayOffsetAmount = new NumberInput("#payOffsetAmount");
	self.inputPayOffsetAccountName = new Input("#payOffsetAccountName");
	self.inputPayOffsetButtonSubmit = new Button("#payOffsetButtonSubmit");
	self.tablePayOffsetTableList = new Table("#payOffsetTableList");
	self.inputPayOtherDropDownChannel = new DropDown("#payOtherDropDownChannel");
	self.inputPayOtherInputNo = new Input("#payOtherInputNo");
	self.inputPayOtherInputDate = new Input("#payOtherInputDate");
	self.inputPayOtherInputAmt = new NumberInput("#payOtherInputAmt");
	self.tablePayOtherTableList = new Table("#payOtherTableList");
	
	self.buttonCreatePaymentAndPrint = new Button("#buttonCreatePaymentAndPrint");
	self.buttonCancelPayment = new Button("#buttonCancelPayment");
	
	//GFMIS --BankTranfer
    self.payBankTxnfInputBankCodeGf = new Input("#payBankTxnfInputBankCodeGf");
    self.payBankTxnfInputNoGf = new Input("#payBankTxnfInputNoGf");
    self.payBankTxnfInputAmtGf = new NumberInput("#payBankTxnfInputAmtGf");
    self.payBankTxnfInputDateGf = new Input("#payBankTxnfInputDateGf");
    self.payBankTxnfDropDownBankNameGf = new DropDown("#payBankTxnfInputBankNameGf").data([{ key: "006", value: "กรุงไทย" }]);
    self.payBankTxnfDropDownBankBrnhGf = new DropDown("#payBankTxnfDropDownBankBrnhGf").data([{ key: "0123", value: "ปากเกร็ด" }]);
    self.payBankTxnfDropDownBankAcctGf = new DropDown("#payBankTxnfDropDownBankAcctGf").data([{ key: "0", value: "123-6-02069-3" }]);
    self.payBankTxnfDropDownBankAcCdGf = new DropDown("#payBankTxnfDropDownBankAcCdGf").data([{ key: "0", value: "11021044" }]);
    self.payBankTxnfButtonSubmitGf = new Button("#payBankTxnfButtonSubmitGf");
    self.payBankTxnfTableListGf = new Table("#payBankTxnfTableListGf");
    self.payForeignTableList = new Table("#payForeignTableList");
    
    self.exchangeRateInput = new Input("#exchangeRateInput");
    self.foreignAmountInput = new NumberInput("#foreignAmountInput");
    self.thAmountInput = new NumberInput("#thAmountInput");
    self.currencyTypeSelect = new DropDown("#currencyTypeSelect").init("../exchangeRateList.json");
    self.payForeignButtonSubmit = new Button("#payForeignButtonSubmit");
    self.payDateInputDate = new Input("#payDateInputDate");
    self.payBankTxnfDropDownBank = new DropDown("#payBankTxnfDropDownBank").data([{ key: "01", value: "121-2-11111-2" }]);
	
	self.amount = 0;
	self.currentVatRate = 0.00; 
	(function(){ $(window["setup"]) })();
	$('#payBankTxnfInputBankCode').click(function () {
        var key = view.payBankTxnfDropDownBankCode.key();
        $('#payBankTxnfInputBankName option:selected').prop('selected', false);
        $('#payBankTxnfInputBankName option[data-key='+key+']').prop('selected', true);
    });
    $('#payBankTxnfInputBankName').click(function () {
        var key = view.inputPayBankTxnfDropDownBankName.key();
        $('#payBankTxnfInputBankCode option:selected').prop('selected', false);
        $('#payBankTxnfInputBankCode option[data-key='+key+']').prop('selected', true);
    });
    $('#payChqBankCode').click(function () {
        var key = view.inputPayChqBankCode.key();
        $('#payChqBankName option:selected').prop('selected', false);
        $('#payChqBankName option[data-key='+key+']').prop('selected', true);
    });

    $('#payChqBankName').click(function () {
        var key = view.inputPayChqBankName.key();
        $('#payChqBankCode option:selected').prop('selected', false);
        $('#payChqBankCode option[data-key='+key+']').prop('selected', true);
    });
	
	return this;
})(jQuery);

var exchange;
function setup() {
	view.tableDeductionList.onDelete(function(data) {
		updateDeduction();
		calculate();
	});
	view.tablePayTypeList.onDelete(function(data) {
		if (data.length < 1)
		    view.buttonSubmitPayment.disable();
		calculate();
	});
	view.radioSpecialOptions.click(function(val) {
		if (val !== "3") { view.inputAdvanced.enable(); return; }
		view.inputAdvanced.disable();
	});
	view.buttonSubmitPayment.disable();
	///////////////////////////////////////////////
	var billingList = view.session("billingList"), afterSaleDiscount = 0, amount = 0, discount = 0, charge = 0, vat = 0, totalCharge = 0, deduction = 0;
    console.log('6666');console.log(billingList);console.log('6666');
	$.each(billingList, function(i,o){
		view.currentVatRate = o.vatRate;
		$.each(o.serviceList, function(j,p){
			totalCharge += p.amount;
		});
		//totalCharge += o.serviceList.amount;//by NSD 01-02-2017
	});
	vat = totalCharge * view.currentVatRate;
	amount = totalCharge * (1.00 - view.currentVatRate)
	view.inputWithholdingTaxAmount.val(deduction);
	deduction *= -1;
	view.amount = amount;
	view.inputCharge.val(charge = amount - discount);
	view.inputVat.val(vat);
    //by NSD 23-01-2017
    view.inputCharge.val(view.session("inputAmount"));
    view.inputVat.val(view.session("inputVat"));
    //view.currentVatRate.val(view.session("vatRate0"));
    //END NSD
	view.inputTotalCharge.val(totalCharge);
	view.inputDeduction.val(deduction);
	view.inputAdditionalDiscount.val(afterSaleDiscount);
	view.inputBalanceDue.val(totalCharge + deduction - afterSaleDiscount);
	
	// Add to support GFMIS
	if("${epContext.roleName}" == "TRANSFER") {
		view.buttonSetPayType.index(5); // BANKTRANSFER
		view.panelPayType.index(5);
		for(var hideInd = 0, end = 4; hideInd <= end; ++hideInd) view.buttonSetPayType.hideIndex(hideInd);
		view.buttonSetPayType.hideIndex(6);
		for(var hideInd = 8, end = 9; hideInd <= end; ++hideInd) view.buttonSetPayType.hideIndex(hideInd);
	}else if("${epContext.roleName}" == "GFMISAGENT"){
		view.buttonSetPayType.index(9); // BANKTRANSFER
		view.panelPayType.index(9);
        view.payBankTxnfInputBankCodeGf.val("006");
        view.payBankTxnfDropDownBankNameGf.index(1); // can use .key("006")
        view.payBankTxnfDropDownBankBrnhGf.index(1); // .key("0123")
        view.payBankTxnfDropDownBankAcctGf.index(1); // .key("1")
        view.payBankTxnfDropDownBankAcCdGf.index(1); // .key("1")
        view.payBankTxnfInputAmtGf.val(view.inputBalanceDue.val());
        for(var hideInd = 0, end = 8; hideInd <= end; ++hideInd) view.buttonSetPayType.hideIndex(hideInd); 
    }else if("${epContext.roleName}" == "TRANSIN"){
    	view.buttonSetPayType.index(5); // BANKTRANSFER
		//view.panelPayType.index(6);
		for(var hideInd = 0, end = 4; hideInd <= end; ++hideInd) view.buttonSetPayType.hideIndex(hideInd);
		for(var hideInd = 6, end = 9; hideInd <= end; ++hideInd) view.buttonSetPayType.hideIndex(hideInd);
    
	}else {
		view.buttonSetTaxType.hideIndex(3); // retention
        view.buttonSetTaxType.hideIndex(4); // compensation
        view.buttonSetPayType.hideIndex(9);
        view.retentionSummaryDiv.hide();
        view.compensationSummaryDiv.hide();
	}
	
	view.inputPayCashAmount.val(view.inputBalanceDue.val());
    view.inputPayChqAmount.val(view.inputBalanceDue.val());//by NSD 26-04-2017
    view.inputPayCCAmount.val(view.inputBalanceDue.val());
    view.inputPayMoneyOrderInputAmount.val(view.inputBalanceDue.val());
    view.inputPayBillExchgInputAmount.val(view.inputBalanceDue.val());
    view.inputPayCouponInputAmt.val(view.inputBalanceDue.val());
    view.inputPayBankTxnfInputAmt.val(view.inputBalanceDue.val());
    view.inputPayOffsetAmount.val(view.inputBalanceDue.val());
    view.inputPayOtherInputAmt.val(view.inputBalanceDue.val());
    view.thAmountInput.val(view.inputBalanceDue.val());
}
$("#testmenu li a").click(function () { $(this).parents(".btn-group").find('.selection').text($(this).text()); $(this).parents(".btn-group").find('.selection').val($(this).text()); });
$("#testmenu2 li a").click(function () { $(this).parents(".btn-group").find('.selection').text($(this).text()); $(this).parents(".btn-group").find('.selection').val($(this).text()); });
function buttonCreatePaymentAndPrintClickEvent() {
	if (view.inputBalanceDue.val() > view.inputReceived.val()) {
		view.message.clear().error(["ระบบไม่อนุญาตให้จ่าย เนื่องจากจำนวนเงินไม่ถูกต้อง"]).show();
		return false;
	}
	if (view.inputAdvanced.val() > 0 && view.radioSpecialOptions.val() == "") {
		view.message.clear().error(["ระบบไม่อนุญาตให้จ่าย เนื่องจากจำนวนเงินไม่ถูกต้อง"]).show();
		return false;
	}
	var balanceDue = view.inputBalanceDue.val(), received = view.inputReceived.val(), change = view.inputChange.val(), advance = view.inputAdvanced.val(), deduction = Math.abs(view.inputDeduction.val());
	var params = {
		 "amount": view.amount
		,"payAmount": view.inputTotalCharge.val()
		,"discount": view.inputAdditionalDiscount.val()
		,"vatAmount": view.inputVat.val()
		,"totalPaid": balanceDue
		,"wtAmount": deduction
		,"receiveAmount": balanceDue
		,"remainAmount": change
		,"paymentCase": $.map(view.tablePayTypeList.data(), function(a,i){ return a[1] }).join(" + ") + (deduction > 0 ? " + WT" : "")
		,"remark": ""
	};
	console.log("step2 initial params set.");
	var deductIndex = 0; 
	var gettingIncome = 'ขาเข้า', inType = '-IN', outType = '-OUT';
	var watPaymentCase = "WT ";
	// <!-- Deduction. -->
    var wtRow = view.tableDeductionList.find("type", 0);
    var feeRow = view.tableDeductionList.find("type", 1);
    var pntRow = view.tableDeductionList.find("type", 2);
    var rttRow = view.tableDeductionList.find("type", 3);
    var cmpRow = view.tableDeductionList.find("type", 4);

	$.map((window["wthList"] || []), function(o, i) {
        if(wtRow.length>0){
            params["deducts["+ deductIndex +"].type"] = o[2] == "69 ทวิ"? "69BIS" : o[2] == "69 ตรี" ? "69TRE" : "3TREDECIM"; // 3TREDECIM, 69BIS, 69TRE
            params["deducts["+ deductIndex +"].withholdingDocNo"] = $.trim(o[1]);
            params["deducts["+ deductIndex +"].amount"] = o[3].replace(/[,]/g, "");
            deductIndex++;
            if($.trim(o[1])!=""){
                watPaymentCase = watPaymentCase.concat("เลขที่: "+$.trim(o[1]+" + "));//by NSD 02-03-2017
            }else{
                watPaymentCase = watPaymentCase.concat(" + ");
            }
        }
	});
	$.map((window["feeList"] || []), function(o, i) {
        if(feeRow.length > 0){
            params["deducts["+ deductIndex +"].type"] = "FEE".concat((gettingIncome == o[2])? inType : outType); // FEE  ค่าธรรมเนียม
            params["deducts["+ deductIndex +"].amount"] = o[5].replace(/[,]/g, "");
            deductIndex++;
        }
	});
	$.map((window["pntyList"] || []), function(o, i) {
        if(pntRow.length > 0){
            params["deducts["+ deductIndex +"].type"] = "PTY".concat((gettingIncome == o[2])? inType : outType); // PENALTY ค่าปรับ
            params["deducts["+ deductIndex +"].amount"] = o[2].replace(/[,]/g, "");
            deductIndex++;
        }
	});
	$.map((window["rttList"] || []), function(o, i) {
        if(rttRow.length > 0){
            var type =
            params["deducts["+ deductIndex +"].type"] = "RTTN".concat((gettingIncome == o[2])? inType : outType); // RETENTION ค่าประกันผลงาน
            params["deducts["+ deductIndex +"].amount"] = o[2].replace(/[,]/g, "");
            deductIndex++;
        }
	});
	$.map((window["cmpstList"] || []), function(o, i) {
        if(cmpRow.length > 0){
            params["deducts["+ deductIndex +"].type"] = "CMPST"; // COMPENSATE ค่าตอบแทนการรับชำระ
            params["deducts["+ deductIndex +"].amount"] = o[2].replace(/[,]/g, "");
            deductIndex++;
        }
	});
	console.log("step3 Deduction set.");
	 var paymentCase = "";
	//<!-- Pay Method. -->
	var methodIndex = 0, cashRow = view.tablePayTypeList.find("method", 0);
	// Fix by EPIS8 23/12/2016 refer issue no.57
	var chequeRow = view.tablePayTypeList.find("method", 1);
	var creditRow = view.tablePayTypeList.find("method", 2);
	var moneyOrderRow = view.tablePayTypeList.find("method", 3);
	var billExchangeRow = view.tablePayTypeList.find("method", 4);
	//var couponRow = view.tablePayTypeList.find("method", 5);
	var moneyTransferRow = view.tablePayTypeList.find("method", 5);
	var offsetRow = view.tablePayTypeList.find("method", 6);
	 var otherRow = view.tablePayTypeList.find("method", 8);
 	var moneyTransferGFRow = view.tablePayTypeList.find("method", 9);
 	var moneyForeignRow = view.tablePayTypeList.find("method", 7);
 	// End Fix by EPIS8 23/12/2016 refer issue no.57
 	

 	var change = 0 , amount = 0, countPay = 0, payIndex = [], amounts = [], receivedMoney = view.inputReceived.val(), balanceDue = view.inputBalanceDue.val(), advance = view.inputAdvanced.val();
    change = Number(receivedMoney) - Number(balanceDue);
    for(var i=0;i<11;i++) { var payCashFlg = false, paycase = []; paycase[i] = view.tablePayTypeList.find("method", i); amounts[i] = paycase[i].find("td:eq(2) div").text().replace(/[,]/g, ""); if (paycase[i].length > 0) { payIndex[countPay] = i; countPay++; } }
    if (countPay > 0) { if (countPay > 1) { if(payIndex.indexOf(0)!='-1') { change = Number(receivedMoney) - Number(balanceDue); amounts[payIndex.indexOf(0)] = Number(amounts[payIndex.indexOf(0)]) - change; /*if (amounts[payIndex.indexOf(0)]<0) { Math.max(amounts); }*/ } else { amounts[payIndex[0]] = Number(amounts[payIndex[0]])- change; } } else { if(payIndex.indexOf(0)!='-1') { change = Number(receivedMoney) - Number(balanceDue); amounts[payIndex.indexOf(0)] = Number(amounts[payIndex.indexOf(0)]) - change; } else { amounts[payIndex[0]] = Number(amounts[payIndex[0]])- change; } } }
    
    $.map((window["payChqListData"] || []), function(o, i) { // For: Cheque
		if(chequeRow.length > 0){	// Fix by EPIS8 23/12/2016 refer issue no.57
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
		if(creditRow.length > 0){	// Fix by EPIS8 23/12/2016 refer issue no.57
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
		if(moneyOrderRow.length > 0){	// Fix by EPIS8 23/12/2016 refer issue no.57
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
		if(billExchangeRow.length > 0){	// Fix by EPIS8 23/12/2016 refer issue no.57
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
	/* $.map((window["payCouponTableListData"] || []), function(o, i) { // For: Coupon
		if(couponRow.length > 0){	// Fix by EPIS8 23/12/2016 refer issue no.57
			params["methods["+ methodIndex +"].type"] = "COUPON";
			params["methods["+ methodIndex +"].code"] = "CP";
			params["methods["+ methodIndex +"].name"] = "คูปอง";
			params["methods["+ methodIndex +"].couponNo"] = $.trim(o[1]);
			params["methods["+ methodIndex++ +"].amount"] = amounts[5];
			paymentCase = paymentCase.concat("คูปอง เลขที่: "+$.trim(o[1])+" + ");
		}
	}); */
	$.map((window["payBankTxnfTableListData"] || []), function(o, i) { // For: Money Transfer
		if(moneyTransferRow.length > 0){	// Fix by EPIS8 23/12/2016 refer issue no.57
			params["methods["+ methodIndex +"].type"] = "BANKTRANSFER";
			params["methods["+ methodIndex +"].code"] = "TR";
			params["methods["+ methodIndex +"].name"] = "เงินโอนในประเทศ";
			params["methods["+ methodIndex +"].transferDt"] = o[5] +" 00:00:00";
			params["methods["+ methodIndex +"].isBackDt"] = "${epContext.roleName}" == "TRANSFER" || "${epContext.roleName}" == "TRANSIN";
			params["methods["+ methodIndex +"].bankCode"] = o[1];
	        params["methods["+ methodIndex +"].bankName"] = o[2];
	        params["methods["+ methodIndex +"].bankBrnh"] = o[3];
	        params["methods["+ methodIndex +"].refNo"] = o[4];
	        params["methods["+ methodIndex +"].bankAcctNo"] = o[8];
	        params["methods["+ methodIndex +"].bankAcCd"] = o[7];
	        params["methods["+ methodIndex +"].docNo"] = o[8];
			params["methods["+ methodIndex++ +"].amount"] = amounts[5];
			paymentCase = paymentCase.concat("เงินโอนในประเทศ + ");
		}
	});
    $.map((window["payBankTxnfTableListGfData"] || []), function(o, i) {
		if(moneyTransferGFRow.length > 0){
			params["methods["+ methodIndex +"].type"] = "BANKTRANSFER";
			params["methods["+ methodIndex +"].code"] = "GF";
			params["methods["+ methodIndex +"].name"] = "เงินโอน (GFMIS)";
			params["methods["+ methodIndex +"].transferDt"] = o[5] +" 00:00:00";
			params["methods["+ methodIndex +"].isBackDt"] = "${epContext.roleName}" == "GFMISAGENT";
			params["methods["+ methodIndex +"].bankCode"] = o[1];
	        params["methods["+ methodIndex +"].bankName"] = o[2];
	        params["methods["+ methodIndex +"].bankBrnh"] = o[3];
	        params["methods["+ methodIndex +"].refNo"] = o[4];
	        params["methods["+ methodIndex +"].bankAcctNo"] = o[7];
	        params["methods["+ methodIndex +"].bankAcCd"] = o[8];
	        params["methods["+ methodIndex +"].docNo"] = o[7];
			params["methods["+ methodIndex++ +"].amount"] = amounts[9];
			paymentCase = paymentCase.concat("เงินโอน (GFMIS) + ");
		}
	});
	$.map((window["payOffsetTableListData"] || []), function(o, i) { // For: Offset
		if(offsetRow.length > 0){	// Fix by EPIS8 23/12/2016 refer issue no.57
			params["methods["+ methodIndex +"].type"] = "OFFSET";
			params["methods["+ methodIndex +"].code"] = "OF";
			params["methods["+ methodIndex +"].name"] = "offset";
			params["methods["+ methodIndex +"].offsetDocumentNo"] = o[1];
			params["methods["+ methodIndex +"].offsetAccountCode"] = o[2];
			params["methods["+ methodIndex +"].offsetAccountName"] = o[3];
			params["methods["+ methodIndex +"].docNo"] = o[1];
			params["methods["+ methodIndex++ +"].amount"] = amounts[6];
			paymentCase = paymentCase.concat("offset "+o[1]+" + "); // add Document No
		}
	});
	$.map((window["payOtherTableListData"] || []), function(o, i) { // For: Other Type
		if(otherRow.length > 0){	// Fix by EPIS8 23/12/2016 refer issue no.57
			params["methods["+ methodIndex +"].type"] = "OTHER";
			params["methods["+ methodIndex +"].code"] = "OT"; 
			params["methods["+ methodIndex +"].name"] = "อื่นๆ";
			params["methods["+ methodIndex +"].otherNo"] = $.trim(o[1]);
			params["methods["+ methodIndex +"].otherType"] = $.trim(o[2]);
			params["methods["+ methodIndex +"].otherDt"] = $.trim(o[3]);
			params["methods["+ methodIndex +"].docNo"] = $.trim(o[1]);
			params["methods["+ methodIndex++ +"].amount"] = amounts[8];
			paymentCase = paymentCase.concat("อื่นๆ + ");
		}
	});
	$.map((window["payForeignTableListData"] || []), function(o, i) {
	    console.log("moneyForeignRow >>> ");
	    console.log(moneyForeignRow.length);
		if(moneyForeignRow.length > 0){
            console.log("moneyForeignRow +++ true");
			params["methods["+ methodIndex +"].type"] = "FOREIGNTRANSFER";
			params["methods["+ methodIndex +"].code"] = "TF";
			params["methods["+ methodIndex +"].name"] = "เงินโอนต่างประเทศ";
            params["methods["+ methodIndex +"].transferDt"] = o[3] +" 00:00:00";
            params["methods["+ methodIndex +"].isBackDt"] = "${epContext.roleName}" == "TRANSIN";
            params["methods["+ methodIndex +"].currencyCode"] = o[1];
            params["methods["+ methodIndex +"].currencyRate"] = o[2];
            params["methods["+ methodIndex +"].foreignAmount"] = o[4];
            params["methods["+ methodIndex +"].docNo"] = o[6];
			params["methods["+ methodIndex++ +"].amount"] = amounts[7];
			paymentCase = paymentCase.concat("เงินโอนต่างประเทศ + ");
		}
	});
	if (cashRow.length == 1) { // For: Cash
		params["methods["+ methodIndex +"].type"] = "CASH";
		params["methods["+ methodIndex +"].code"] = "CC";
		params["methods["+ methodIndex +"].name"] = "เงินสด";
		params["methods["+ methodIndex++ +"].amount"] = amounts[0];
		paymentCase = paymentCase.concat("เงินสด + ");
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
	console.log("step4 Payment set.");
	// <!-- Invoice List. -->
	var custIndex = 0, billingList = view.session("billingList"), receiptList = [];
	while (customer = billingList[custIndex]) {
		params["customers["+ custIndex +"].custNo"] = customer.custNo;
		params["customers["+ custIndex +"].custName"] = customer.custName;
		params["customers["+ custIndex +"].custType"] = customer.custType;
		params["customers["+ custIndex +"].address1"] = customer.address1;
		params["customers["+ custIndex +"].address2"] = customer.address2;
		params["customers["+ custIndex +"].remark"] = customer.remark;
		params["customers["+ custIndex +"].taxNo"] = customer.custTaxId;
		params["customers["+ custIndex +"].custBranch"] = customer.custBranch;
		params["customers["+ custIndex +"].split"] = customer.split;
        params["customers["+ custIndex +"].catCustomerSegment"] = customer.catCustomerSegment;
        params["customers["+ custIndex +"].acctCatLkp"] = customer.acctCatLkp;
        params["customers["+ custIndex +"].custCategoryDesc"] = customer.custCategoryDesc;
		for (var serviceIndex = 0, n = customer.serviceList.length; serviceIndex < n; serviceIndex++) {
			var service = customer.serviceList[serviceIndex];
			params["customers["+ custIndex +"].services["+ serviceIndex +"].code"] = service.serviceType;
			params["customers["+ custIndex +"].services["+ serviceIndex +"].no"] = service.serviceNo;
			params["customers["+ custIndex +"].services["+ serviceIndex +"].name"] = service.promotion;
			params["customers["+ custIndex +"].services["+ serviceIndex +"].subscriber"] = service.customerName;
			params["customers["+ custIndex +"].services["+ serviceIndex +"].amount"] = service.excAmount;//by NSD 06-02-2017//(service.amount*100/(100 + parseFloat(view.session("vatRate0")))).toFixed(2);//service.amount*(1.00 - view.currentVatRate);
			params["customers["+ custIndex +"].services["+ serviceIndex +"].discount"] = service.discount;
			params["customers["+ custIndex +"].services["+ serviceIndex +"].charge"] = service.excAmountIncDisc;//by NSD 06-02-2017//(service.amount*100/(100 + parseFloat(view.session("vatRate0")))).toFixed(2);//service.amount*(1.00 - view.currentVatRate);
			params["customers["+ custIndex +"].services["+ serviceIndex +"].vat"] = (service.amount * parseFloat(view.session("vatRate0"))/(100+parseFloat(view.session("vatRate0")))).toFixed(2);//service.amount * view.currentVatRate;
			params["customers["+ custIndex +"].services["+ serviceIndex +"].totalCharge"] = service.amount;
            params["customers["+ custIndex +"].services["+ serviceIndex +"].amtIncVat"] = service.amtIncVat;
            params["customers["+ custIndex +"].services["+ serviceIndex +"].glAccount"] = service.glAccount;
			params["customers["+ custIndex +"].services["+ serviceIndex +"].deduction"] = deduction;
			params["customers["+ custIndex +"].services["+ serviceIndex +"].vatRate"] = parseFloat(view.session("vatRate0"));
            params["customers["+ custIndex +"].services["+ serviceIndex +"].profitCode"] = service.profitCode;//by NSD 16-02-2017
            params["customers["+ custIndex +"].services["+ serviceIndex +"].profitName"] = service.profitName;//by NSD 16-02-2017
            params["customers["+ custIndex +"].services["+ serviceIndex +"].serviceTypeName"] = service.serviceTypeName;//by NSD 16-02-2017
            params["customers["+ custIndex +"].services["+ serviceIndex +"].promotion"] = service.promotion;//by NSD 22-02-2016
			params["customers["+ custIndex +"].services["+ serviceIndex +"].groupCode"] = customer.groupCode;
			params["customers["+ custIndex +"].services["+ serviceIndex +"].groupName"] = customer.groupName;

		}
		custIndex++;
	}
	console.log("step5 Service inv List set.");
	console.log("-- Ready to post! --");
    console.log(params);
    //------
	post("../createPaymentTopup.json", params, function(res){
		view.message.clear();
		$(document.body).append('<form action="../printPaymentTopUpReceipt.pdf" method="post" target="_printForm"></form>');
		var form = document.forms[0]; function input(n,v) { var input = document.createElement("input"); input.type = "hidden"; input.name = n; input.value = v; return input }
		$.each(res.data, function(i,o){
			form.appendChild(input("receipts["+ i +"].id", o.id));
            form.appendChild(input("receipts["+ i +"].refNo", o.ref1));
            form.appendChild(input("receipts["+ i +"].serviceType", o.services[0].serviceName));
            form.appendChild(input("receipts["+ i +"].promotion", o.promotion));
		});
		form.submit();
		view.breadcrumb.index(4);
		view.panelNavigation.hide(0);
		view.panelPaymentDetails.hide(0);
		view.panelPaymentSummary.hide(0);
		view.panelMethodSummary.hide(0);
		view.linkPanel.hide(0);
		view.panelSummaryPaymentList.show(400);
		var data = [];
		$.each(res.data, function(i,r){
			//custIndex = 0;
			//while (customer = billingList[custIndex]) {
				//if(customer.custNo == r.accountNo) {
					data.push({ "custNo": r.accountNo 
						,"custName": r.accountName
						,"receiptNo": r.no
						,"receiptAmount": view.utils.numberFormat(r.totalCharge)
						,"status": '<span class="glyphicon glyphicon-ok-circle"></span> บันทึกลงระบบสำเร็จ'
			
						//,"services": customer.serviceList})
                        ,"services": r.services})
				//}
				//custIndex++;
			//}
		});
        /* console.log('555555555');
        console.log(res); console.log(customer); console.log(data);
        console.log('555555555'); */
		view.tableSummaryReceipts.data(data);
	}, view.message); 
}
function buttonCancelPaymentClickEvent() {
	location.href = "pay-7-stap_1.jsp?new"
}
function addTaxTypeClickEvent() {
	var index = view.buttonSetTaxType.index(), value = 0;
	if (index === 0)        { value = view.tableWithholdingTaxList.deduct(3); 		window.wthList = view.tableWithholdingTaxList.data() }
	else if (index === 1)   { value = view.tableFeeTaxList.calcBalance(2,5); 		window.feeList = view.tableFeeTaxList.data() }
	else if (index === 2)   { value = view.tablePenaltyTaxList.calcBalance(2,2); 	window.pntyList = view.tablePenaltyTaxList.data() }
	else if (index === 3)   { value = view.tableRetentionTaxList.calcBalance(2,2);  window.rttList = view.tableRetentionTaxList.data() }
	else if (index === 4)   { value = view.tableCompensationTaxList.deduct(2);		window.cmpstList = view.tableCompensationTaxList.data() }
	view.tableDeductionList.find("type", index).remove();
	view.tableDeductionList.insert(["-", view.buttonSetTaxType.val(), value], true, { "type": index });
	updateDeduction();
	calculate();
}
function addPayTypeClickEvent() {
	var index = view.buttonSetPayType.index(), payAmt = view.inputPayCashAmount.val();
	if (index == 1)      { window["payChqListData"] = view.tablePayChqList.data();                         payAmt = view.tablePayChqList.sum(6)        }
	else if (index == 2) { window["payCCListData"] = view.tablePayCCList.data();                           payAmt = view.tablePayCCList.sum(4)         }
	else if (index == 3) { window["payMoneyOrderTableListData"] = view.tablePayMoneyOrderTableList.data();       payAmt = view.tablePayMoneyOrderTableList.sum(4)  }
	else if (index == 4) { window["payBillExchgTableListData"] = view.tablePayBillExchgTableList.data();         payAmt = view.tablePayBillExchgTableList.sum(4)   }
	//else if (index == 5) { window["payCouponTableListData"] = view.tablePayCouponTableList.data();               payAmt = view.tablePayCouponTableList.sum(4)      }
	else if (index == 5) { window["payBankTxnfTableListData"] = view.tablePayBankTxnfTableList.data();           payAmt = view.tablePayBankTxnfTableList.sum(6)    }
	else if (index == 6) { window["payOffsetTableListData"] = view.tablePayOffsetTableList.data();               payAmt = view.tablePayOffsetTableList.sum(4)      }
	else if (index == 8) { window["payOtherTableListData"] = view.tablePayOtherTableList.data();                 payAmt = view.tablePayOtherTableList.sum(4)       }
    else if (index == 9) { window["payBankTxnfTableListGfData"] = view.payBankTxnfTableListGf.data();      payAmt = view.payBankTxnfTableListGf.sum(6)  }
    else if (index == 7){ window["payForeignTableListData"] = view.payForeignTableList.data();              payAmt = view.payForeignTableList.sum(5)     }
	view.tablePayTypeList.find("method", index).remove();
	view.tablePayTypeList.insert(["-", view.buttonSetPayType.val(), payAmt], true, { method: index });
	view.inputReceived.val(view.tablePayTypeList.sum(2));
	if(view.allowedCurrenyCode) {
		view.buttonSubmitPayment.enable();
		view.buttonSubmitPaymentEng.enable();
	}
	updateDeduction();
	calculate();
}
function addWithholdingTaxNoClickEvent() {
	if (validateAddWithholdingTaxNo()) {
		var taxType = view.radioWithholdingTaxType.label();
		view.tableWithholdingTaxList.insert(["-", view.inputWithholdingTaxNo.val(), taxType, view.inputWithholdingTaxAmount.val()], true, {"type": view.radioWithholdingTaxType.type()});
	}
}
function validateAddWithholdingTaxNo() {
	var isValid = true;
// 	if(view.inputWithholdingTaxNo.val() == "") {view.inputWithholdingTaxNo.error(true); isValid = false;} else {view.inputWithholdingTaxNo.error(false);}
	return isValid;
}
function addFeeTaxNoClickEvent() {
	view.tableFeeTaxList.insert(["-", view.inputFeeDepartmentCost.val(), view.radioFeeTaxType.label(), view.inputFeeWTAmount.val(), view.inputFeeBuyTaxAmount.val(), view.inputFeeTaxAmount.val()], true);
    $('#feeTaxList td:nth-child(3)').hide();
}
function addPenaltyTaxNoClickEvent() {
	view.tablePenaltyTaxList.insert(["-", view.radioPenaltyTaxType.label(), view.inputPenaltyTaxAmount.val()], true);
    $('#penaltyTaxList td:nth-child(2)').hide();
}
function addRetentionTaxNoClickEvent() {
	view.tableRetentionTaxList.insert(["-", view.radioRetentionTaxType.label(), view.inputRetentionTaxAmount.val()], true);
    $('#retentionTaxList td:nth-child(2)').hide();
}
function addCompensationTaxNoClickEvent() {
	view.tableCompensationTaxList.insert(["-", view.inputCompensationDepartmentCost.val(), view.inputCompensationTaxAmount.val()], true);
}
function payChqSubmitClickEvent() {
	if(validationChqSubmit()) view.tablePayChqList.insert(["-", view.inputPayChqBankCode.val(), view.inputPayChqBankName.val(), view.inputPayChqBranch.val(), view.inputPayChqNo.val(), view.inputPayChqDate.val(), view.inputPayChqAmount.val()], true)
}
function validationChqSubmit() {
	var isValid = true;
	if(view.inputPayChqNo.val() == "") {view.inputPayChqNo.error(true); isValid = false;} else {view.inputPayChqNo.error(false);}
	if(view.inputPayChqDate.val() == "") {view.inputPayChqDate.error(true); isValid = false;} else {view.inputPayChqDate.error(false);}
	return isValid;
}
function payCCSubmitClickEvent() {
	if(validatePayCCSubmit()) view.tablePayCCList.insert(["-", view.inputPayCCType.val(), view.inputPayCCNo.val(), view.inputPayCCBankName.val(), view.inputPayCCAmount.val()], true)
}
function validatePayCCSubmit() {
	var isValid = true;
	if(view.inputPayCCType.index() === 0) {view.inputPayCCType.error(true); isValid = false;} else {view.inputPayCCType.error(false);}
	if(view.inputPayCCNo.val() == "" || view.inputPayCCNo.val() < 16) {view.inputPayCCNo.error(true); isValid = false;} else {view.inputPayCCNo .error(false);}
	if(view.inputPayCCBankName.index() === 0) {view.inputPayCCBankName.error(true); isValid = false; } else {view.inputPayCCBankName.error(false);}
	return isValid;
}
function payMoneyOrderButtonSubmitClickEvent() {
	if(validatePayMoneyOrderButtonSubmit()) view.tablePayMoneyOrderTableList.insert(["-", view.inputPayMoneyOrderInputNo.val(), view.inputPayMoneyOrderInputDate.val(), view.inputPayMoneyOrderInputPostCode.val(), view.inputPayMoneyOrderInputAmount.val()], true);
}
function validatePayMoneyOrderButtonSubmit() {
	var isValid = true;
	if(view.inputPayMoneyOrderInputDate.val() == "") { view.inputPayMoneyOrderInputDate.error(true); isValid = false; } else {view.inputPayMoneyOrderInputDate.error(false);}
	if(view.inputPayMoneyOrderInputNo.val() == "") { view.inputPayMoneyOrderInputNo.error(true); isValid = false; } else {view.inputPayMoneyOrderInputNo.error(false);}
	return isValid;
}
function payBillExchgButtonSubmitClickEvent() {
	if(validatePayBillExchgButtonSubmit()) view.tablePayBillExchgTableList.insert(["-", view.inputPayBillExchgInputNo.val(), view.inputPayBillExchgInputDate.val(), view.inputPayBillExchgInputPostCode.val(), view.inputPayBillExchgInputAmount.val()], true);
}
function validatePayBillExchgButtonSubmit() {
	var isValid = true;
	if(view.inputPayBillExchgInputDate.val() == "") { view.inputPayBillExchgInputDate.error(true); isValid = false; } else {view.inputPayBillExchgInputDate.error(false); }
	if(view.inputPayBillExchgInputNo.val() == "") { view.inputPayBillExchgInputNo.error(true); isValid = false; } else {view.inputPayBillExchgInputNo.error(false); }
	return isValid;
}
function payCouponButtonSubmitClickEvent() {
	if(validatePayCouponButtonSubmit()) view.tablePayCouponTableList.insert(["-", view.inputPayCouponInputNo.val(), "", "", view.inputPayCouponInputAmt.val()], true);
}
function validatePayCouponButtonSubmit() {
	var isValid = true;
	if(view.inputPayCouponInputNo.val() == "") { view.inputPayCouponInputNo.error(true); isValid = false; } else {view.inputPayCouponInputNo.error(false); }
	return isValid;
}
function payBankTxnfButtonSubmitClickEvent() {
	if(validatePayBankTxnfButtonSubmit()) view.tablePayBankTxnfTableList.insert(["-", view.payBankTxnfDropDownBankCode.val(), view.inputPayBankTxnfDropDownBankName.val(), view.inputPayBankTxnfDropDownBankBrnh.val(), view.inputPayBankTxnfInputNo.val(), view.inputPayBankTxnfInputDate.val(), view.inputPayBankTxnfInputAmt.val(), view.inputPayBankTxnfDropDownBankAcCd.val(), view.inputPayBankTxnfDropDownBankAcct.val()], true);
    $('#payBankTxnfTableList td:nth-child(8), #payBankTxnfTableList td:nth-child(9)').hide();
}
function validatePayBankTxnfButtonSubmit() {
	var isValid = true;	
	if(view.payBankTxnfDropDownBankCode.val() == "") { view.payBankTxnfDropDownBankCode.error(true); isValid = false; } else {view.payBankTxnfDropDownBankCode.error(false); }
	if(view.inputPayBankTxnfDropDownBankName.index() === 0) { view.inputPayBankTxnfDropDownBankName.error(true); isValid = false; } else {view.inputPayBankTxnfDropDownBankName.error(false); }
	if(view.inputPayBankTxnfDropDownBankBrnh.index() === 0) { view.inputPayBankTxnfDropDownBankBrnh.error(true); isValid = false; } else {view.inputPayBankTxnfDropDownBankBrnh.error(false); }
	if(view.inputPayBankTxnfInputNo.val() == "") { view.inputPayBankTxnfInputNo.error(true); isValid = false; } else {view.inputPayBankTxnfInputNo.error(false); }
	if(view.inputPayBankTxnfDropDownBankAcCd.index() === 0) { view.inputPayBankTxnfDropDownBankAcCd.error(true); isValid = false; } else {view.inputPayBankTxnfDropDownBankAcCd.error(false); }
	if(view.inputPayBankTxnfInputDate.val() == "") { view.inputPayBankTxnfInputDate.error(true); isValid = false; } else {view.inputPayBankTxnfInputDate.error(false); }
	if(view.inputPayBankTxnfDropDownBankAcct.index() === 0) { view.inputPayBankTxnfDropDownBankAcct.error(true); isValid = false; } else {view.inputPayBankTxnfDropDownBankAcct.error(false); }
	return isValid;
}
function payOffsetButtonSubmitClickEvent() {
	if(validatePayOffsetButtonSubmit()) view.tablePayOffsetTableList.insert(["-", view.inputPayOffsetDocumentNo.val(), view.inputPayOffsetAccountCode.val(), view.inputPayOffsetAccountName.val(), view.inputPayOffsetAmount.val()], true);
}
function validatePayOffsetButtonSubmit() {
	var isValid = true;
	if(view.inputPayOffsetDocumentNo.val() == "") { view.inputPayOffsetDocumentNo.error(true); isValid = false; } else {view.inputPayOffsetDocumentNo.error(false); }
	if(view.inputPayOffsetAccountCode.val() == "") { view.inputPayOffsetAccountCode.error(true); isValid = false; } else {view.inputPayOffsetAccountCode.error(false); }
	if(view.inputPayOffsetAccountName.val() == "") { view.inputPayOffsetAccountName.error(true); isValid = false; } else {view.inputPayOffsetAccountName.error(false); }
	return isValid;
}
function payOtherButtonSubmitClickEvent() {
	if(validatePayOtherButtonSubmit()) view.tablePayOtherTableList.insert(["-", view.inputPayOtherInputNo.val(), view.inputPayOtherDropDownChannel.val(), view.inputPayOtherInputDate.val(), view.inputPayOtherInputAmt.val()], true);
}
function validatePayOtherButtonSubmit() {
	var isValid = true;
	if(view.inputPayOtherDropDownChannel.index() === 0) { view.inputPayOtherDropDownChannel.error(true); isValid = false; } else {view.inputPayOtherDropDownChannel.error(false); }
	if(view.inputPayOtherInputNo.val() == "") { view.inputPayOtherInputNo.error(true); isValid = false; } else {view.inputPayOtherInputNo.error(false); }
	if(view.inputPayOtherInputDate.val() == "") { view.inputPayOtherInputDate.error(true); isValid = false; } else {view.inputPayOtherInputDate.error(false); }
	return isValid;
}
function payForeignButtonSubmitClickEvent() {
	if(validatePayForeignButtonSubmit()) view.payForeignTableList.insert(["-", $("#currencyTypeSelect").val()/*view.currencyTypeSelect.val()*/, view.exchangeRateInput.val(), view.payDateInputDate.val(), view.foreignAmountInput.val(), view.thAmountInput.val(), view.payBankTxnfDropDownBank.val()], true);
}
function payBankTxnfButtonSubmitGfClickEvent() {
    if(view.payBankTxnfInputDateGf.val() == "") { view.payBankTxnfInputDateGf.error(true); return; }
    view.payBankTxnfInputDateGf.error(false);
    view.payBankTxnfTableListGf.insert(["-", view.payBankTxnfInputBankCodeGf.val(), view.payBankTxnfDropDownBankNameGf.val(), view.payBankTxnfDropDownBankBrnhGf.val(), view.payBankTxnfInputNoGf.val(), view.payBankTxnfInputDateGf.val(), view.payBankTxnfInputAmtGf.val(), view.payBankTxnfDropDownBankAcCdGf.val(), view.payBankTxnfDropDownBankAcctGf.val()], true);
    $('#payBankTxnfTableListGf td:nth-child(8), #payBankTxnfTableListGf td:nth-child(9)').hide();
}
function validatePayForeignButtonSubmit() {
	var isValid = true;
	if($("#currencyTypeSelect").val() == 0) { $("#currencyTypeSelect").css("border-color","#a94442");/*view.currencyTypeSelect.error(true);*/ isValid = false; } else { $("#currencyTypeSelect").css("border-color","#ccc");/*view.currencyTypeSelect.error(false);*/ }
	if(view.payDateInputDate.val() == "") { view.payDateInputDate.error(true); isValid = false; } else {view.payDateInputDate.error(false); }
	return isValid;
}
function updateDeduction() {
	view.inputDeduction.val(view.tableDeductionList.typeSum(0));
	view.inputFee.val(view.tableDeductionList.typeSum(1));
	view.inputPenalty.val(view.tableDeductionList.typeSum(2));
	view.inputRetention.val(view.tableDeductionList.typeSum(3));
	view.inputCompensation.val(view.tableDeductionList.typeSum(4));
}
function calculate() {
	var balanceDue = ((view.inputTotalCharge.val() * 100) + (view.tableDeductionList.sum(2) * 100)) / 100;
	var received = view.tablePayTypeList.sum(2);
	var cash = stripToNumber(view.tablePayTypeList.find("method", 0).find("td:eq(2) div").text());
	var nonCash = ((received * 100) - (cash * 100))/100;
	var advanced = view.inputAdvanced.val();
	var change = 0;
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
	view.inputBalanceDue.val(balanceDue);
	view.inputReceived.val(received);
	view.inputChange.val(change);
	view.inputAdvanced.val(advanced);
	//view.radioSpecialOptions.disable(2, change == 0 && advanced == 0);
    //by NSD 10-03-2017
    var leftAmt = balanceDue-received;
    if(leftAmt>0){
        view.inputPayCashAmount.val(leftAmt);
        view.inputPayChqAmount.val(leftAmt);
        view.inputPayCCAmount.val(leftAmt);
        view.inputPayMoneyOrderInputAmount.val(leftAmt);
        view.inputPayBillExchgInputAmount.val(leftAmt);
        view.inputPayCouponInputAmt.val(leftAmt);
        view.inputPayBankTxnfInputAmt.val(leftAmt);
        view.inputPayOffsetAmount.val(leftAmt);
        view.inputPayOtherInputAmt.val(leftAmt);
        view.thAmountInput.val(leftAmt);
    }else{
        view.inputPayCashAmount.val(0.00);
        view.inputPayChqAmount.val(0.00);
        view.inputPayCCAmount.val(0.00);
        view.inputPayMoneyOrderInputAmount.val(0.00);
        view.inputPayBillExchgInputAmount.val(0.00);
        view.inputPayCouponInputAmt.val(0.00);
        view.inputPayBankTxnfInputAmt.val(0.00);
        view.inputPayOffsetAmount.val(0.00);
        view.inputPayOtherInputAmt.val(0.00);
        view.thAmountInput.val(0.00);
    }
}
function detailFormatter(val, row, ind) {
	
	var details = '<table class="table table-striped table-bordered">'
		+ '<thead>'
		+ '<tr>'
		+ '<th class="text-center"> ประเภทบริการ </th>'
        /*+ '<th class="text-center"> ชื่อบริการ </th>'*/
        + '<th class="text-center"> หมายเลขบริการ</th>'
        + '<th class="text-center">	เงินส่วนลด</th>'
        + '<th class="text-right"> ยอดก่อนภาษี </th>'
        + '<th class="text-right"> ภาษีหัก ณ ที่จ่าย</th>'
		+ '<th class="text-right"> ภาษีมูลค่าเพิ่ม </th>'
		+ '<th class="text-right"> ยอดเงินรวมภาษี </th>'
		+ '<th class="text-right"> ยอดชำระ </th>'                                 
        + '</tr>'
		+ '</thead>' + '<tbody>';
	for (var i = 0, m = row.services.length; i < m; i++) {
		details += '<tr>'
		//+ '<td class="text-center">'+$.trim(row.services[i].serviceType)+'</td>'
                + '<td class="text-center">'+$.trim(row.services[i].serviceTypeName)+'</td>'
		//+ '<td class="text-center">'+$.trim(row.services[i].promotion)+'</td>'
        /*+ '<td class="text-center">'+$.trim(row.services[i].serviceName)+' '+$.trim(row.services[i].serviceNo)+'</td>'*/
		+ '<td class="text-center">'+$.trim(row.services[i].serviceNo)+'</td>'
		//+ '<td class="text-right">'+view.utils.numberFormat(row.services[i].amount *(1.00 - view.currentVatRate))+'</td>'
        + '<td class="text-right">'+view.utils.numberFormat(row.services[i].discount)+'</td>'
        + '<td class="text-right">'+view.utils.numberFormat(row.services[i].charge)+'</td>'//by NSD 24-01-2017, 06-02-2016
        + '<td class="text-right">'+view.utils.numberFormat(row.services[i].deduction)+'</td>'
        //+ '<td class="text-right">'+view.utils.numberFormat(row.services[i].amount * view.currentVatRate)+'</td>'
        + '<td class="text-right">'+view.utils.numberFormat(row.services[i].vat)+'</td>'
		+ '<td class="text-right">'+view.utils.numberFormat(row.services[i].totalCharge)+'</td>'
		+ '<td class="text-right">'+view.utils.numberFormat(row.services[i].totalCharge)+'</td>'
		+ '</tr>';
	}
	return details + '</tbody></table>';
}

$(document).ready(function () {

    var isStateAgency = view.session("isStateAgency"), custNoList = [];
    if(isStateAgency=='2'){
        $("#69tvi").prop('checked', 'checked');
    }else{
        $("#3trs").prop('checked', 'checked');
    }
} );
view.buttonSetPayType.elem.click(function(){
	var currencyCode = $('#currencyTypeSelect option:selected').attr('data-code');
	$('#currencyTypeSelect').val('th_TH');
	addExchangRate();
});
function addExchangRate(){
    var Type = $('#currencyTypeSelect').val();
	var balanceDue = ((view.inputTotalCharge.val() * 100) + (view.tableDeductionList.sum(2) * 100)) / 100;
    var received = view.tablePayTypeList.sum(2);
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
</script>