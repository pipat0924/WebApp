<%-- 
    Document   : pay-service-charge
    Created on : Dec 15, 2015, 3:27:39 PM
    Author     : AmMie
--%>
<%@page import="java.util.ArrayList"%>
<%-- <%@page import="th.co.softpos.cat.epis.control.customer_epis_crm_control"%>
<%@page import="th.co.softpos.cat.epis.bean.customer_epis_crm_bean"%> --%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="bootstrap-table-1.8.1/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="bootstrap-table-1.8.1/bootstrap-table/src/bootstrap-table.css" rel="stylesheet" type="text/css"/>
        <link href="bootstrap-datepicker/jquery.datetimepicker.css" rel="stylesheet" type="text/css"/>
        <link href="option/custom.css" rel="stylesheet" type="text/css"/>
        <script src="bootstrap-table-1.8.1/jquery.min.js" type="text/javascript"></script>
        <script src="bootstrap-table-1.8.1/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="bootstrap-table-1.8.1/bootstrap-table/src/bootstrap-table.js"></script>
        <script src="bootstrap-datepicker/jquery.datetimepicker.js"></script>
        <script src="option/custom.js" type="text/javascript"></script>
    </head>
    <body>
        <header class="header_page"></header>
        <section class="container-fluid menu">
            <!-- <button class="menu-toggle btn btn-sm btn-default" style="margin-bottom: 5px"><span class="glyphicon glyphicon-step-forward"></span></button> -->
                <%-- <%@include  file="menu.jsp" %> --%>
            <div class="row">
                <div class="col-md-12">
                    <ol class="breadcrumb">
                        <li><i>รายการเปลี่ยนแปลง</i></li>
                    </ol>
                    <button type="button" class="btn btn-primary btn-advan"  data-toggle="modal" data-target="#CustomerSearch"><span class="glyphicon glyphicon-search"></span>  ค้นหา</button>
                    <div class="panel panel-default panal-radius">
                        <div class="panel-body">
                            <div class="form-inline">
                                <div class="form-group" style="margin-right: 50px;">
                                    <label>เลขที่ลูกค้า (Billing Account) : </label>
                                    <input type="text"  id="txt_ba_code" name="txt_ba_code" class="form-control">
                                    <!--<input type="text" id='billing_account' class="form-control" value="700006573">-->
                                </div>
                                <div class="form-group">
                                    <label>Barcode / QRcode : </label>
                                    <input type="text" class="form-control" id="">
                                    <button type="button" class="btn btn-primary" onclick="link()"><span class="glyphicon glyphicon-search"></span>ค้นหา</button>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

<!--            <ul class="list-inline pull-right list-menu-set">
                <li><a href="#"><span class="glyphicon glyphicon-plus-sign"></span> เพิ่มรายการรับชำระ</a></li>
                <li><a href="pay-service-charge_2.jsp"><span class="glyphicon glyphicon-th-list"></span> สรุปรายการรับชำระ</a> <span class="badge badge_modefil">1</span></li>
                <li><a href="#"><span class="glyphicon glyphicon-edit"></span> ดำเนินการรับชำระ</a></li>
            </ul>-->


            <div class="row">
                <div class="col-md-12 tab-modefile">
                    <!-- Nav tabs -->
                    <ul class="nav nav-tabs" role="tablist">
                        <li role="presentation" class="active"><a href="#tab_cus" aria-controls="tab_cus" role="tab" data-toggle="tab">ข้อมูลลูกค้า</a></li>
                        <li role="presentation"><a href="#sub_script" aria-controls="sub_script" role="tab" data-toggle="tab">Subscription</a></li>
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
                                                        <input class="form-control" value="700006573" disabled="disabled">
                                                    </div>
                                                    <label class="control-label col-sm-1">ชื่อลูกค้า :</label>
                                                    <div class="col-sm-7">
                                                        <input class="form-control" value="บริษัท AEC จำกัด">
                                                    </div>
                                                </div>

                                                <div class="form-group">
                                                    <label class="control-label col-sm-2" >Tax ID :</label>
                                                    <div class="col-sm-2">
                                                        <input class="form-control" value="2147560B45156" disable>
                                                    </div>
                                                    <label class="control-label col-sm-1" >สาขา :</label>
                                                    <div class="col-sm-2">
                                                        <input class="form-control" value="0000">
                                                    </div>
                                                    <label class="control-label col-sm-3" >หน่วยงานติดตามหนี้ :</label>
                                                    <div class="col-sm-2">
                                                        <input class="form-control text-right" value="00000จต." disabled>
                                                    </div>
                                                </div>

                                                <div class="form-group">
                                                    <label class="control-label col-sm-2" >กลุ่มผู้ใช้บริการ :</label>
                                                    <div class="col-sm-2">
                                                        <select class="form-control">
                                                            <option>ธุรกิจทั่วไป</option>
                                                            <option>บุคคลธรรมดา</option>
                                                        </select>
                                                    </div>
                                                    <label class="control-label col-sm-2" ></label>
                                                    <div class="col-sm-2">

                                                    </div>
                                                    <label class="control-label col-sm-2" >ยอดค้างชำระ (รวม VAT) :</label>
                                                    <div class="col-sm-2">
                                                        <input class="form-control text-right" value="1,070.00" disabled>
                                                    </div>
                                                </div>

                                                <div class="form-group">
                                                    <label class="control-label col-sm-2" >Billing Group :</label>
                                                    <div class="col-sm-2">
                                                        <input class="form-control" value="LN-Inmarsat-C" disabled>
                                                    </div>

                                                    <label class="control-label col-sm-2" ></label>
                                                    <div class="col-sm-2"> </div>

                                                    <label class="control-label col-sm-2" >ยอดชำระล่วงหน้า :</label>
                                                    <div class="col-sm-2">
                                                        <input class="form-control text-right" value="0.00" disabled>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label col-sm-2" >สถานะลูกค้า :</label>
                                                    <div class="col-sm-2">
                                                        <input class="form-control" value="Active" disabled>
                                                    </div>

                                                    <label class="control-label col-sm-2" ></label>
                                                    <div class="col-sm-2"> </div>

                                                    <label class="control-label col-sm-2" >สกุลเงิน :</label>
                                                    <div class="col-sm-2">
                                                        <input class="form-control text-right" value="THB" disabled>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label col-sm-2" >หมายเหตุ :</label>
                                                    <div class="col-sm-6">
                                                        <input class="form-control">
                                                    </div>
                                                    <label class="control-label col-sm-2" >VAT Rate :</label>
                                                    <div class="col-sm-2">
                                                        <input class="form-control text-right" value="70.00" disabled>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <!--------------------------------------->
                                    <br/>
                                    <!--------------------------------------->
                                    <div class="row">
                                        <div class="col-md-12 tab-modefile">
                                            <!-- Nav tabs -->
                                            <ul class="nav nav-tabs" role="tablist">
                                                <li role="presentation" class="active"><a href="#tab1" aria-controls="tab1" role="tab" data-toggle="tab">ที่อยู่สำหรับใบเสร็จรับเงิน</a></li>
                                                <li role="presentation"><a href="#tab2" aria-controls="tab2" role="tab" data-toggle="tab">ที่อยู่สำหรับใบแจ้งค่าใช้บริการ</a></li>
                                                <li role="presentation">
                                                    <div class="checkbox">
                                                        <label>
                                                            &nbsp;<input type="checkbox"> เปลี่ยนแปลงที่อยู่ในใบเสร็จรับเงิน/ใบกำกับภาษี
                                                        </label>
                                                    </div>
                                                </li>
                                                <li role="presentation">
                                                    <div class="checkbox" style="margin-left: 20px">
                                                        <label>
                                                            &nbsp;<input type="checkbox"> แยกใบเสร็จรับเงิน/ใบกำกับภาษีตามใบแจ้งค่าใช้บริการ
                                                        </label>
                                                    </div>
                                                </li>
                                            </ul>

                                            <!-- Tab panes -->
                                            <div class="tab-content">
                                                <div role="tabpanel" class="tab-pane active" id="tab1">
                                                    <textarea class="form-control textarea-tab">319 อาคารจัตุรัสจามจุรี ชั่น 22-41 ถนนพญาไท แขวงปทุมวัน เขตปทุมวัน กรุงเทพมหานคร 10330</textarea>
                                                </div>
                                                <div role="tabpanel" class="tab-pane" id="tab2">
                                                    <textarea class="form-control textarea-tab">ที่อยู่สำหรับใบแจ้งค่า</textarea>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!--------------------------------------->
                        <!--------------------------------------->
                        <div role="tabpanel" class="tab-pane" id="sub_script">
                            <div class="panel panel-default panal-radius">
                                <div class="panel-body">
                                    <br/>
                                    <table class="table">
                                        <thead>
                                            <tr>
                                                <th>#</th>
                                                <th>ประเภทบริการ</th>
                                                <th>หมายเลขบริการ</th>
                                                <th>สาถนะ</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td>1</td>
                                                <td>Inmasat-C</td>
                                                <td>456700100</td>
                                                <td>Disconnected By Customer</td>
                                            </tr>
                                            <tr>
                                                <td>2</td>
                                                <td>Inmasat-C</td>
                                                <td>456700100</td>
                                                <td>Disconnected By Customer</td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
            <!--------------------------------------->
            <!--------------------------------------->


            <div class="row">
                <div class="col-md-12 tab-modefile">
                    <!-- Nav tabs -->
                    <ul class="nav nav-tabs" role="tablist">
                        <li role="presentation" class=""><a href="#tab-2-1" aria-controls="tab-2-1" role="tab" data-toggle="tab">รายการใบแจ้งค่าใช้บริการ</a></li>
                        <li role="presentation" class=""><a href="#tab-2-2" aria-controls="tab-2-2" role="tab" data-toggle="tab">ประวัติการรับชำระ</a></li>
                        <li role="presentation" class="active"><a href="#tab-2-3" aria-controls="tab-2-3" role="tab" data-toggle="tab">รายการเปลี่ยนแปลง</a></li>
                    </ul>
                    <!-- Tab panes -->
                    <div class="tab-content">
                        <div role="tabpanel" class="tab-pane " id="tab-2-1">
                            <table id="table777"
                                   data-row-style="rowStyle"
                                   data-toggle="table"
                                   data-detail-view="true"
                                   data-detail-formatter="detailFormatter"
                                   data-classes="table table-hover table-striped"
                                   data-url="Convert_json.jsp?master=esb_RetrieveInvoiceHeader_Service&get="
                                   >
                                <thead>
                                    <tr>
                                        <th data-formatter="runningFormatter" >#</th>
                                        <th data-field="BillRefNo" data-sortable="true" class="">เลขที่ใบแจ้งค่าใช้บริการ</th>
                                        <!--<th data-field="c" data-sortable="true" class="">Period</th>-->
                                        <th data-field="IssueDate" data-sortable="true" class="">วันจัดทำใบแจ้งใช้ค่าบริการ</th>
                                        <th data-field="DueDate" data-sortable="true" data-halign="center" data-align="right" class="">วันครบกำหนด</th>
                                        <th data-field="Amount" data-sortable="true" data-halign="center" data-align="right" class=""  data-formatter="numberFormatter">ยอดก่อนภาษี</th>
                                        <th data-field="g" data-sortable="true" data-halign="center" data-align="right" class="">ส่วนลด</th>
                                        <th data-field="VAT" data-sortable="true" data-halign="center" data-align="right" class=""  data-formatter="numberFormatter">ภาษีมูลค่าเพิ่ม</th>
                                        <th data-field="Total" data-sortable="true" data-halign="center" data-align="right" class=""  data-formatter="numberFormatter">ยอดเงินรวมภาษี</th>
                                        <th data-field="BalanceDue" data-sortable="true" data-halign="center" data-align="right" class="col-xs-1"  data-formatter="numberFormatter">จำนวนเงินคงค้าง</th>
                                        <th data-field="TotalPaid" data-sortable="true" data-halign="center" data-align="right"  class="col-xs-1"  data-formatter="numberFormatter">ยอดเงินที่ต้องชำระ</th>
                                        <th data-field="j" data-sortable="true" class="col-xs-6">ภาษีหัก ณ ที่จ่าย</th>
                                        <th data-field="q" class="col-xs-6">รอบการใช้งาน</th>
                                        <th data-field="m" data-sortable="true" class="col-xs-1">สถานะ</th>

                                        <!--                                        <th data-field="a" class="">#</th>
                                                                                <th data-field="b" class="">เลขที่ใบแจ้งค่าใช้บริการ</th>
                                                                                <th data-field="c"  class="">วันที่จัดทำใบแจ้งค่าใช้บริการ</th>
                                                                                <th data-field="e" data-halign="center" data-align="right" class="">วันครบกำหนด</th>
                                                                                <th data-field="f" data-halign="center" data-align="right" class="">ยอดก่อนภาษี</th>
                                                                                <th data-field="g" data-halign="center" data-align="right" class="">ส่วนลด</th>
                                                                                <th data-field="h" data-halign="center" data-align="right" class="">ภาษีมูลค่าเพิ่ม</th>
                                                                                <th data-field="i" data-halign="center" data-align="right" class="">ยอดเงินรวมภาษี</th>
                                                                                <th data-field="k" class="col-xs-1">จำนวนเงินคงค้าง</th>
                                                                                <th data-field="l" class="col-xs-1">ยอดเงินที่ต้องชำระ</th>
                                                                                <th data-field="j" class="col-xs-6">ภาษีหัก ณ ที่จ่าย</th>
                                                                                <th data-field="q" class="col-xs-6">รอบการใช้งาน</th>
                                                                                <th data-field="m" class="col-xs-1">สถานะ</th>-->
                                    </tr>
                                </thead>
                                <!--                                <tbody>
                                                                    <tr>
                                                                        <td><input type="checkbox" class="checkbox" id="checkbox_37" onclick="show_price('37')"></td>
                                                                        <td>255900001</td>
                                                                        <td>02/02/2016</td>
                                                                        <td>22/02/2016</td>
                                                                        <td>1,000.00</td>
                                                                        <td>10.00</td>
                                                                        <td>70.00</td>
                                                                        <td>1,070.00</td>
                                                                        <td>1,070.00</td>
                                                                        <td>1,070.00</td>
                                                                        <td>30.00</td>
                                                                        <td>01/01/2016-30/01/2016</td>
                                                                        <td>Active</td>
                                                                <span style="display: none;" id="desc0">
                                                                    <pre><strong class="bold">Description</strong></pre>
                                                                </span>
                                                                </tr>
                                
                                                                </tbody>-->
                            </table>
                        </div>

                        <div role="tabpanel" class="tab-pane " id="tab-2-2">
                            <table id="table"
                                   data-row-style="rowStyle"
                                   data-toggle="table"
                                   data-classes="table table-hover table-striped"
                                   >
                                <thead>
                                    <tr>
                                        <th data-field="a" class="">#</th>
                                        <th data-field="b" class="">เลขที่ใบแจ้งค่าใช้บริการ</th>
                                        <th data-field="c" class="">ยอดเงินตามใบแจ้งค่าใช้บริการ</th>
                                        <th data-field="e" class="">รอบการใช้งาน</th>
                                        <th data-field="f" class="">สถานที่รับชำระเงิน</th>
                                        <th data-field="g" class="">เลขที่ใบกำกับภาษี</th>
                                        <th data-field="h" class="">วันที่ออกใบกำกับภาษี</th>
                                        <th data-field="i" class="">ชื่อลูกค้า</th>
                                        <th data-field="k" class="">วิธีการชำระเงิน</th>
                                        <th data-field="l" class="">จุดชำระเงิน</th>
                                        <th data-field="j" class="">ประเภทการชำระเงิน</th>
                                        <th data-field="q" class="">ยอดชำระ</th>
                                        <th data-field="m" class="">สถานะ</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>1</td>
                                        <td>255900001</td>
                                        <td>1,170.00</td>
                                        <td>01/01/2016-30/01/2016</td>
                                        <td>01717</td>
                                        <td>EP0171701F150714000001</td>
                                        <td>22/02/2016</td>
                                        <td>บริษัท AEC จำกัด</td>
                                        <td>เงินโอน</td>
                                        <td>01</td>
                                        <td>ชำระเต็มจำนวน</td>
                                        <td>1,030.00</td>
                                        <td>Active</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>

                        <div role="tabpanel" class="tab-pane active" id="tab-2-3">
                            <table id="table"
                                   data-row-style="rowStyle"
                                   data-toggle="table"
                                   data-classes="table table-hover table-striped"
                                   >
                                <thead>
									<tr>
										<th>วันที่รับชำระเงิน</th>
										<th>เลขที่ใบแจ้งค่าใช้บริการ</th>
										<th>เลขที่ใบเสร็จรับเงิน</th>
										<th>ภาษีมูลค่าเพิ่ม</th>
										<th>รวมเงิน</th>
										<th>วันที่ประมวลผล</th>
										<th>สถานที่รับชำระเงิน</th>
										<th>ผู้รับชำระ</th>
										<th>ประเภทการชำระ</th>
										<th>รอบการใช้งาน</th>
										<th>สถานะ</th>
										<th>Tracking ID</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>17/01/2014 11:00:00</td>
										<td><a href="">0</a></td>
										<td></td>
										<td></td>
										<td>500.00</td>
										<td>25/01/2015</td>
										<td>00000 สำนักงานใหญ่</td>
										<td>51314422</td>
										<td>ชำละล่วงหน้า</td>
										<td></td>
										<td>ยกเลิก</td>
										<td><a href="">14267770</a></td>
									</tr>
									<tr>
										<td>28/02/2015 12:00:00</td>
										<td><a href="">0</a></td>
										<td></td>
										<td></td>
										<td>306.63</td>
										<td>13/10/2015</td>
										<td>00000 สำนักงานใหญ่</td>
										<td>51314422</td>
										<td>ชำละล่วงหน้า</td>
										<td></td>
										<td>คืนเงินชำระล่วงหน้า</td>
										<td><a href="">16374175</a></td>
									</tr>
									<tr>
										<td>22/02/2016 12:00:00</td>
										<td><a href="">255900001</a></td>
										<td>EP0171701F150714000001</td>
										<td>70.00</td>
										<td>1,070.00</td>
										<td>22/02/2016</td>
										<td>00000 สำนักงานใหญ่</td>
										<td>513182772</td>
										<td>ชำระเต็มจำนวน</td>
										<td>01/02/2016-28/02/2016</td>
										<td>ยกเลิก</td>
										<td><a href="">12345678</a></td>
									</tr>
								</tbody>
                            </table>
                        </div>
                    </div>


                </div>
            </div>
            <br/>

            <!------------------------->
            <!----------ยอดที่ต้องชำระ--------------->
<!--            <div class="well">
                <div class="row">
                    <div class=" col-md-12">
                        <div class="form-horizontal">
                            <div class="form-group">
                                <label class="control-label col-sm-2" >
                                    <div class="checkbox">
                                        <label>
                                            <input type="checkbox"><b> รับชำระล่วงหน้า :</b>
                                        </label>
                                    </div>
                                </label>
                                <div class="col-sm-2">
                                    <input class="form-control text-right" value="0.00">
                                </div>
                                <label class="control-label col-sm-6" >ยอดเงินที่ต้องชำระก่อนภาษีมูลค่าเพิ่ม :</label>
                                <div class="col-sm-2">
                                    <input class="form-control text-right" value="0.00" disabled>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-10" >ภาษีมูลค่าเพิ่ม :</label>
                                <div class="col-sm-2">
                                    <input class="form-control text-right" value="0.00"  disabled>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-10" >ยอดเงินรวมภาษีมูลค่าเพิ่ม :</label>
                                <div class="col-sm-2">
                                    <input class="form-control text-right" value="0.00"  disabled>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-10" >ภาษีหัก ณ ที่จ่าย (WT):</label>
                                <div class="col-sm-2">
                                    <input class="form-control text-right" value="0.00"  disabled>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-sm-3" >รายละเอียดเพิ่มเติมในใบเสร็จรับเงิน :</label>
                                <div class="col-sm-4">
                                    <input class="form-control" value="">
                                </div>
                                <label class="control-label col-sm-3" >ส่วนลด :</label>
                                <div class="col-sm-2">
                                    <input class="form-control text-right" value="0.00"  disabled>
                                </div>
                            </div>
                        </div>
                    </div>-->

                </div>
            </div>
        </section>

         <div class="modal fade" role="dialog" id="CustomerSearch">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">ค้นหาลูกค้า</h4>
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
                                        </div>
                                        <i><span class="glyphicon glyphicon-eye-open"></span> สามารถใช้เครื่องหมาย ? แทนหนึ่งตัวอักษร หรือ * แทนหลายตัวอักษรในการค้นหา</i>
                                        <br><br>
                                        <label class="label-panal label-success">ผลการค้นหา</label>
                                        <div class="panel panel-default panal-radius">
                                            <div class="panel-body">
                                                <div class="row">
                                                    <div class="col-md-12">
                                                        <table class="table table-hover">
                                                            <thead>
                                                                <tr>
                                                                    <th>#</th>
                                                                    <th>เลขที่ใบแจ้งค่าบริการ</th>
                                                                    <th>เลขที่ลูกค้า</th>
                                                                    <th>ชื่อลูกค้า</th>
                                                                    <th>ประเภทบริการ</th>
                                                                    <th>Billing Group</th> 
                                                                </tr>
                                                            </thead>
                                                            <tbody>
                                                                <tr>
                                                                    <td><input type="radio" name="optionsRadios1" id="optionsRadios1" value="option1" checked> </td>
                                                                    <td>255900001</td>
                                                                    <td>700005673</td>
                                                                    <td>บริษัท AEC จำกัด</td>
                                                                    <td>Inmarsat-C</td>
                                                                    <td>LN</td>
                                                                </tr>
                                                                <tr>
                                                                    <td><input type="radio" name="optionsRadios1" id="optionsRadios2" value="option2">  </td>
                                                                    <td>255900002</td>
                                                                    <td>700005675</td>
                                                                    <td>บริษัท พี เอ็น แท้งก์เกอร์จำกัด</td>
                                                                    <td>Inmarsat-C</td>
                                                                    <td>LN</td>
                                                                </tr>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
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
                                        </div>
                                        <i><span class="glyphicon glyphicon-eye-open"></span> สามารถใช้เครื่องหมาย ? แทนหนึ่งตัวอักษร หรือ * แทนหลายตัวอักษรในการค้นหา</i>
                                        <br><br>
                                        <label class="label-panal label-success">ผลการค้นหา</label>
                                        <div class="panel panel-default panal-radius">
                                            <div class="panel-body">
                                                <div class="row">
                                                    <div class="col-md-12">
                                                        <table class="table table-hover">
                                                            <thead>
                                                                <tr>
                                                                    <th>#</th>
                                                                    <th>หมายเลขบริการ</th>
                                                                    <th>เลขที่ลูกค้า</th>
                                                                    <th>ชื่อลูกค้า</th>
                                                                    <th>ประเภทบริการ</th>
                                                                    <th>Billing Group</th> 
                                                                </tr>
                                                            </thead>
                                                            <tbody>
                                                                <tr>
                                                                    <td><input type="radio" name="optionsRadios2" id="optionsRadios1" value="option1" checked> </td>
                                                                    <td>456700100</td>
                                                                    <td>700005673</td>
                                                                    <td>บริษัท AEC จำกัด</td>
                                                                    <td>Inmarsat-C</td>
                                                                    <td>LN</td>
                                                                </tr>
                                                                <tr>
                                                                    <td><input type="radio" name="optionsRadios2" id="optionsRadios2" value="option2">  </td>
                                                                    <td>456700200</td>
                                                                    <td>700005675</td>
                                                                    <td>บริษัท พี เอ็น แท้งก์เกอร์จำกัด</td>
                                                                    <td>Inmarsat-C</td>
                                                                    <td>LN</td>
                                                                </tr>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
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
                                                    <button type="button" class="btn btn-primary pull-right"><span class="glyphicon glyphicon-search"></span> ค้นหา</button>
                                                </div>
                                            </div>
                                        </div>

                                        <i><span class="glyphicon glyphicon-eye-open"></span> สามารถใช้เครื่องหมาย ? แทนหนึ่งตัวอักษร หรือ * แทนหลายตัวอักษรในการค้นหา</i>
                                        <br><br>
                                        <label class="label-panal label-success">ผลการค้นหา</label>
                                        <div class="panel panel-default panal-radius">
                                            <div class="panel-body">
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
                                                                </tr>
                                                            </thead>
                                                            <tbody>
                                                                <tr>
                                                                    <td><input type="radio" name="optionsRadios" id="optionsRadios1" value="option1" checked> </td>
                                                                    <td>700005673</td>
                                                                    <td>บริษัท AEC จำกัด</td>
                                                                    <td>Inmarsat-C</td>
                                                                    <td>LN</td>
                                                                </tr>
                                                                <tr>
                                                                    <td><input type="radio" name="optionsRadios" id="optionsRadios2" value="option2">  </td>
                                                                    <td>700005675</td>
                                                                    <td>บริษัท พี เอ็น แท้งก์เกอร์จำกัด</td>
                                                                    <td>Inmarsat-C</td>
                                                                    <td>LN</td>
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
                        </div>
                    </div>


                </div>

                <div class="modal-footer" style="border: none">
                    <button type="button" class="btn btn-default" data-dismiss="modal"><span class="glyphicon glyphicon-ok-circle"></span> เลือกรายการลูกค้า</button>
                    <button type="button" class="btn btn-danger" data-dismiss="modal"><span class="glyphicon glyphicon-remove-circle"></span> ปิด</button>
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
        $("#c_Date").datetimepicker({
            timepicker: false,
            format: 'd/m/Y', // กำหนดรูปแบบวันที่ ที่ใช้ เป็น 00-00-0000			
            lang: 'th', // แสดงภาษาไทย
            scrollMonth: true,
            // minDate: '0', //เลือกวันย้อนหลัง
            // maxDate: '0'
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
        function runningFormatter(value, row, index) {
            return index + 1;
        }
        function numberFormatter(val, row, index) {
            return val.toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,');
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
