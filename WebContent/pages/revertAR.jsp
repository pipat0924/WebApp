<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>โอนหนี้กลับ</title>
    <link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <link href="resources/bootstrap-table/src/bootstrap-table.css" rel="stylesheet" type="text/css" />
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
    <form id="bounceChequeForm" method="post" class="form-horizontal" role="form">
        <div class="row">
            <div class="col-md-12 tab-modefile">
                <ol class="breadcrumb">
                    <li><i>เช็คขัดข้อง</i></li>
                    <li class="active">ค้นหาข้อมูลลูกค้า</li>
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
                                    	<div class="col-sm-4">
	                                        <label class="control-label col-sm-5" for="invoiceNo">เลขที่ใบแจ้งค่าใช้บริการ:</label>
	                                        <div class="col-sm-7"><input class="form-control" id="invoiceNo"></div>
                                       </div> 
                                       <div class="col-sm-4">
	                                        <label class="control-label col-sm-5">เลขที่ใบเสร็จรับเงิน : </label>
	                                        <div class="col-sm-6"><input class="form-control" id="billNo"></div>
                                        </div> 
                                         <div class="col-sm-3">
                                        	<label class="control-label col-sm-4">เลขที่เช็ค :</label>	
                                        	<div class="col-sm-8"><input class="form-control" id="chequeNo"></div>
	                                        
                                        </div> 
                                          <div class="col-sm-1">
	                                            <a id="search" class="btn btn-primary" onclick="xxx()"><span class="glyphicon glyphicon-search"></span> ค้นหา</a> &nbsp;&nbsp;
	                                       </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
        <div class="row">
        	<div class="x">
	        	<div class="col-md-12">
	        	<div class="control-label col-sm-12">
	        	<a id="save" class="btn btn-primary" data-toggle="modal" data-target="#myModal"><span class="glyphicon glyphicon-download-alt"></span> โอนหนี้กลับ</a></div></div>
        	</div>
        </div>
        
        <div class="row">
        <div id="bouncePanel" class="x">
            <div class="col-md-12 tab-modefile">
            <ul id="customerInfoTab" class="nav nav-tabs" role="tablist">
                    <li role="presentation" class="active"><a href="#tab_bounce" aria-controls="tab_bounce" role="tab" data-toggle="tab"><span class="glyphicon glyphicon-briefcase"></span> ประวัติการรับชำระเงิน</a></li>
                </ul>
                    
                    <div class="tab-content">
                    <div role="tabpanel" class="tab-pane active" id="tab1">
                        <table id="invoiceList" data-maintain-selected="true" class="table table-hover" data-toggle="table" data-detail-view="true" data-detail-formatter="invoiceServiceFormatter" data-pagination="true" data-page-size="5" data-page-list="[5, 10, 20, 50, 100, 200]" data-cache="false">
                            <thead>
                            <tr>
                                <th data-field="checked" data-checkbox="true">&nbsp;</th>
                                <th data-field="invoiceNo" data-align="center" data-sortable="true">วันที่ทำรายการ</th>
                                <th data-field="receiptNo" data-sortable="true" data-align="center" >วันที่ออกใบเสร็จรับเงิน</th>
                                <th data-field="chequeNo" data-sortable="true" data-align="center" >เลขที่ใบเสร็จรับเงิน</th>
                                <th data-field="charge" data-sortable="true" data-align="right" >เลขที่เช็ค</th>
                                <th data-field="discount" data-sortable="true" data-align="right" >สถานที่รับชำระ</th>
                                <th data-field="vat" data-sortable="true" data-align="right" >ผู้รับชำระ</th>
                                <th data-field="totalCharge" data-sortable="true" data-align="right" >เลขที่ใบแจ้งค่าใช้บริการ</th>
                                <th data-field="totalAdj" data-sortable="true" data-align="right" >รอบการใช้งาน</th>
                                <th data-field="balanceDuex" data-sortable="true" data-align="right" >ยอดเงินตามใบแจ้งค่าบริการ</th>
                                <th data-field="balanceDue" data-sortable="true" data-align="right" >วิธีการรับชำระ</th>
                                <th data-field="deduct" data-sortable="true" data-align="right" >ประเภทการรับชำระ</th>
                                <th data-field="billCycle" data-sortable="true" data-align="center">ยอดชำระ</th>
                                <th data-field="billCycles" data-sortable="true" data-align="center">ภาษีมูลค่าเพิ่มจากการรับชำระ</th>
                                <th data-field="status" data-align="center" data-sortable="true">สถานะ</th>
                                <th data-field="issueDt" data-align="center" data-sortable="true">หมายเหตุ</th>
                                <th data-field="bounceCheque" data-sortable="true" data-align="center" data-checkbox="true">เช็คขัดข้อง </th>
                            </tr>
                            </thead>
                            <tbody>
                            	<tr>
                            		<td>xxx</td>
                            		<td>18/08/2560 13:55:38</td>
                            		<td>18/08/2560 13:55:38</td>
                            		<td>EPO170401F1708180008</td>
                            		<td>1111111</td>
                            		<td>ศบล. นนทบุรี</td>
                            		<td>EPIS3</td>
                            		<td>220547342</td>
                            		<td>16/03/2016 - 15/04/2016</td>
                            		<td>8,877.54</td>
                            		<td>เช็ค กรุงเทพ เลขที่ : 1111111 + เงินสด</td>
                            		<td>ชำระบางส่วน</td>
                            		<td>8,877.54</td>
                            		<td>580.77</td>
                            		<td>ปกติ</td>
                            		<td></td>
                            		<td><input type="checkbox" id="myCheck" checked disabled></td>
                            	</tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        </div>
        
        <div class="col-md-12">
        <div id="myModal" class="modal fade" role="dialog">
				<div class="modal-dialog">

					<!-- Modal content-->
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
						</div>
						<div class="modal-body">
						<div class="form-group">
                            <label class="control-label col-sm-9">โอนหนี้กลับ</label>
                            </div>
							<div class="form-group">
                            <label class="control-label col-sm-4">ยอดก่อนภาษีมูลค่าเพิ่ม :</label>
                            <div class="col-sm-5"><input type="text" id="prix" class="form-control text-right" disabled /></div>
							<div class="col-sm-2"><input type="checkbox" id="x1" /></div>
                            </div>
                            <div class="form-group">
                            <label class="control-label col-sm-4">ภาษีมูลค่าเพิ่ม :</label>
                                <div class="col-sm-5"><input type="text" id="vatx" class="form-control text-right" disabled></div>
                                <div class="col-sm-2"><input type="checkbox" id="x2"></div>
                            </div>
                            <div class="form-group">
                            <label class="control-label col-sm-4">ภาษีหัก ณ ที่จ่าย :</label>
                                <div class="col-sm-5"><input type="text" id="wtx" class="form-control text-right" disabled></div>
                                <div class="col-sm-2"><input type="checkbox" id="x3"></div>
                            </div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">บันทึก</button>
						</div>
					</div>
				</div>
			</div>
			</div>
			
    </form>
</section>
</body>
<script type="text/javascript">
$('.x').hide();
$('.xx').hide();
	function xxx(){
		$('.x').show();
		$('.xx').show();
		
		$('#prix').val("8,296.77");
		$('#vatx').val("580.77");
		$('#wtx').val("0.00");
	}
	
	document.getElementById('x1').onchange = function() {
	    document.getElementById('prix').disabled = !this.checked;
	};
	document.getElementById('x2').onchange = function() {
	    document.getElementById('vatx').disabled = !this.checked;
	};
	document.getElementById('x3').onchange = function() {
	    document.getElementById('wtx').disabled = !this.checked;
	};
	
</script>
</html>