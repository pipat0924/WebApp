<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP Page</title>
<link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="resources/bootstrap-table/src/bootstrap-table.css" rel="stylesheet" type="text/css" />
<link href="resources/custom.css" rel="stylesheet" type="text/css" />
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
			<div class="col-md-12">
				<ol class="breadcrumb">
					<li><i>ยกเลิกรับชำระค่าบริการหนี้ นอก TBoss</i></li>
					<li>ค้นหาข้อมูลลูกค้า</li>
					<li class="active">สรุปการรับชำระเงิน</li>
					<li>เลือกวิธีการรับชำระ</li>
					<li>ผลการรับชำระ</li>
				</ol>
			</div>
		</div>
		<ul class="list-inline pull-right list-menu-set">
			<li><a href="pay-service-charge.jsp" class="btn btn-link"><span class="glyphicon glyphicon-user"></span> เพิ่มข้อมูลลูกค้า</a></li>
			<li><a id="submitPayment" class="btn btn-link"><span class="glyphicon glyphicon-edit"></span> ดำเนินการรับชำระ</a></li>
		</ul>
		<div class="row">
			<div class="col-md-12">
				<div class="panel panel-success">
					<div class="panel-heading">สรุปการรับชำระเงิน</div>
					<div class="panel-body">
						<table id="summaryList" data-row-style="rowStyle" data-toggle="table"
							data-detail-view="true" data-detail-formatter="detailFormatter"
							data-classes="table table-hover table-striped">
							<thead>
								<tr>
									<th data-align="center" data-formatter="runningFormatter">#</th>
									<th data-field="custNo">เลขที่ลูกค้า</th>
									<th data-field="custName">ชื่อลูกค้า</th>
									<th data-field="billGroup">Billing Group</th>
									<th data-formatter="billCountFormatter" data-align="right">จำนวนใบแจ้งค่าใช้บริการ</th>
									<th data-formatter="totalChargeFormatter" data-align="right">ยอดที่ต้องชำระ</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
		</div>
		<div class="well">
			<div class="row">
				<div class=" col-md-12">
					<div class="form-horizontal">
						<div class="form-group">
							<label class="control-label col-sm-10">ยอดเงินก่อนหักส่วนลด  :</label>
							<div class="col-sm-2"><input id="preItemsDiscount" class="form-control text-right" disabled></div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-10">ส่วนลด :</label>
							<div class="col-sm-2"><input id="itemsDiscount" class="form-control text-right" disabled></div>
						</div>

						<div class="form-group">
							<label class="control-label col-sm-10">ยอดเงินที่ต้องชำระก่อนภาษีมูลค่าเพิ่ม  :</label>
							<div class="col-sm-2"><input id="preDiscount" class="form-control text-right" disabled></div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-10">ภาษีมูลค่าเพิ่ม  :</label>
							<div class="col-sm-2"><input id="vat" class="form-control text-right" disabled></div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-10">ยอดเงินที่ต้องชำระรวมภาษีมูลค่าเพิ่ม :</label>
							<div class="col-sm-2"><input id="totalCharge" class="form-control text-right" disabled></div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-10">ภาษีหัก ณ ที่จ่าย :</label>
							<div class="col-sm-2"><input id="deduct" class="form-control text-right" disabled></div>
						</div>
						<div class="form-group">
							<%--<label class="control-label col-sm-10"><input type="checkbox" name="discountCheckbox" disabled>&nbsp;&nbsp;ลูกค้ารับภาระภาษี &nbsp;&nbsp;<span class="glyphicon glyphicon-lock"></span>&nbsp;ส่วนลด :</label>--%>
								<div class="control-label col-sm-10">
									<label class="strong"><input type="radio" name="discountCheckbox" disabled="disabled" value="1"> รับภาระภาษีเต็มจำนวน</label>&nbsp;&nbsp;&nbsp;&nbsp;
									<label class="strong"><input type="radio" name="discountCheckbox" disabled="disabled" value="2"> รับภาระภาษีบางส่วน :</label>
								</div>
								<div class="col-sm-2"><input id="discount" class="form-control text-right" disabled></div>
						</div>
						
					</div>
				</div>

			</div>
		</div>

	</section>

</body>
<script type="text/javascript">
var view = (function($){
	var self = this, errorMsgEl = "#errorMsg", defaultErrorMessage = "An error occurred but there is no message response.";
	self.session = function(key, val) { if (!val) { var val = window.sessionStorage.getItem(key); return val && (val.indexOf("{") > -1 || val.indexOf("[") > -1) ? JSON.parse(val) : val } window.sessionStorage.setItem(key, ($.type(val) != "object" && $.type(val) != "array" ? val : JSON.stringify(val)))	};
	self.input = new(function(){
		var that = this;
		that.preDiscount = new NumberInput("#preDiscount");
		that.preItemsDiscount = new NumberInput("#preItemsDiscount");
		that.itemsDiscount = new NumberInput("#itemsDiscount");
		that.discount = new NumberInput("#discount");
		that.vat = new NumberInput("#vat");
		that.totalCharge = new NumberInput("#totalCharge");
		that.deduct = new NumberInput("#deduct");
		that.val = function() { if (arguments.length == 1) { $.each(arguments[0],function(k,v){ $("#"+ k).val(v) }) } };
		function Input(el) {
			this.el = el;
			this.elem = $(el);
			this.val = function() { if (arguments.length == 1) { this.elem.val(arguments[0]) } return this.elem.val() };
		}
		function NumberInput(el, dec) {
			var decimal = (dec || 2), obj;
			this.el = el;
			this.elem = obj = $(el);
			this.val = function() { if (arguments.length == 0) return parseFloat(strip(this.elem.val() || "0"), 10); this.elem.val(format(arguments[0])); }
			this.decimal = function(dec) { decimal = dec };
			this.format = format;
			this.elem.keypress(function(e) { var key = (e.which || e.keyCode || e.charCode || 0); var ch = String.fromCharCode(key); return "0123456789.".indexOf(ch) > -1 });
			this.elem.focus(function(){ this.select() });
			this.elem.blur(function(){ this.value = format(this.value) });
			function format(val) { return parseFloat(val || "0", 10).toFixed(decimal).replace(/(\d)(?=(\d{3})+\.)/g, '$1,'); };
			function strip(str) { return (str || "").replace(/[,]/g, "") }
			this.elem.val("0.00");
		}
	});
	self.table = new(function(){
		var that = this;
		that.summaryList = new BootstrapTable("#summaryList");
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
			obj.removeRow = function(index) { obj.elem.find("tbody").each(function(i,o){ o.children[index].remove(); }); return this; };
		}
		//// AUTOMATIC REGISTER FORMATTER FUNCTION ////
		window.runningFormatter = function(val, row, ind) { return ind + 1 }
		window.numberFormatter = function(val, row, ind) { return !$.isNumeric(val) ? "0.00" : parseFloat(val, 10).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,') }
		window.stringInputFormatter = function(val, row, ind) { return '<input value="'+ $.trim(val) +'" maxLength="40" class="form-control">' }
		window.numberInputFormatter = function(val, row, ind) { return '<input value="'+ (val || "0.00") +'" maxLength="10" class="form-control text-right" onkeypress="return $.isNumeric(String.fromCharCode(event.which)) || (event.which == 46 && this.value.indexOf(\'.\') == -1)" onfocus="(function(o){ o.value = o.value.replace(/[,]/g, \'\') })(this)" onblur="(function(o){ o.value = parseFloat(o.value, 10).toFixed(2).replace(/(\\d)(?=(\\d{3})+\\.)/g, \'$1,\'); })(this)">' }
	});
	self.utils = {
		numberFormat: function(num, dec) { return $.type(num) !== "number" ? "0.00" : num.toFixed(dec || 2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,'); }
	};
	/*self.session = function(key, val) {
		if (!val) {
			var val = window.sessionStorage.getItem(key);
			return val.indexOf("{") > -1 || val.indexOf("[") > -1 ? JSON.parse(val) : val;
		}
		window.sessionStorage.setItem(key, 
			($.type(val) != "object" && $.type(val) != "array" ? val : JSON.stringify(val)))
	};*/
	
	self.button = new(function(){
		var that = this;
		that.submitPayment = new Button("#submitPayment");
		function Button(el) { var obj = this, badge; obj.el = el; obj.elem = $(el);
			obj.hide = function() { this.elem.addClass("hide"); return this }; obj.show = function() { this.elem.removeClass("hide"); return this };
			obj.hideLoad = function(){ obj.elem.button("reset"); return this }; obj.showLoad = function(){ obj.elem.button("loading"); return this };
			obj.disable = function(flag) { if (flag == null || flag) this.elem.addClass("disabled").attr("disabled", true); else this.elem.removeClass("disabled").attr("disabled", false); return this }; obj.enable = function() { obj.disable(false); return this };
			obj.badge = function(val) { if (badge) badge.text(val) }; if ((badge = this.elem.next()).length == 0) badge = null;
			obj.elem.click(window[el.substring(1) +"ClickEvent"]);
		}
	});
	return this;
})(jQuery);
$(function(){
	var billingList = view.session("billingList");
	//console.log('xxxxx2');console.log(billingList);console.log('xxxxx2');
	var amount = 0, discount = 0, vat = 0, balanceDue = 0, totalCharge = 0, deduct = 0, preItemsDiscount = 0, itemsDiscount = 0;
	for (var i = 0, m = billingList.length; i < m; i++) {
		for (var j = 0, n = billingList[i].invoiceList.length; j < n; j++) {
			var invoice = billingList[i].invoiceList[j], isPartial = invoice.balanceDue - invoice.received != 0; if (!invoice.checked) continue;
			vat += invoice.calculatedVat;
			balanceDue += invoice.balanceDue;
			totalCharge += invoice.received;
			var isStateAgency = invoice.isStateAgency;
			deduct += calculateVatWT(invoice.balanceDue, invoice.received, invoice.vatRate, invoice.deduction, invoice.totalCharge, invoice.isStateAgency);
			/* if(isPartial && invoice.deduction > 0 ){
				
				deduct += (invoice.received - vat)  * ((isStateAgency) ? 0.01 : 0.03);
				console.log('test1 deduct : '+deduct+" invoice.received ")
			}else{
				deduct += invoice.deduction;
				console.log('test2 deduct : '+deduct)
			} */
			discount += invoice.afterSaleDiscount;
			itemsDiscount +=invoice.discount;
			console.log('### invoice.deduction :'+invoice.deduction+" isPartial:"+isPartial+" invoice.balanceDue:"+invoice.balanceDue+" invoice.received:"+invoice.received+" vatRate:"+invoice.vatRate+" invoice.isStateAgency"+invoice.isStateAgency)
		}
	}
	
	view.input.preDiscount.val(totalCharge - vat);
	view.input.vat.val(vat);
	view.input.totalCharge.val(totalCharge);
	view.input.preItemsDiscount.val(totalCharge-vat+itemsDiscount);
	view.input.itemsDiscount.val(itemsDiscount);
	// Fix By EPIS8 issue no 4, 6, 9
	//console.log('1#boat isStateAgency : '+view.inputCustomerSegment.key())
	
	/* if(deduct > 0){
		console.log('1#boat invoice.totalCharge :'+invoice.totalCharge + " invoice.received:"+invoice.received+ " vat:"+vat);
		console.log('1#boat totalCharge :'+totalCharge + " vat:"+vat);
		
		if(!isPartial) 
			view.input.deduct.val((totalCharge - vat) * ((isStateAgency) ? 0.01 : 0.03));
		else
			view.input.deduct.val(deduct);
	} */
	view.input.deduct.val(deduct);
	// End By EPIS8 issue no 4, 6, 9
	view.input.discount.val(discount);
	view.table.summaryList.data(billingList);
	//alert(billingList[0].invoiceList[0])
	//console.log(billingList[0].invoiceList[0])
	view.table.summaryList.expand();
});
function totalChargeFormatter(val, row, ind) {
	var totalCharge = 0;
	$.each(row.invoiceList, function(i,o){
		if (!o.checked) return;
		totalCharge += o.received
	})
	return view.utils.numberFormat(totalCharge, 0)
}
function billCountFormatter(val, row, ind) {
	return !row.invoiceList || row.invoiceList.length == 0 ? 0 : $.map(row.invoiceList, function(inv, i){ return inv.checked ? inv : null }).length
}
function calculateVatFromIncluded(received, vatRate) { 
	if(vatRate == null) 
		vatRate = 7;
	return (received / (100 + vatRate)) * vatRate 
}
function calculateVatWT(balanceDue, received, vatRate, deduction, totalCharge, isStateAgency) { 
	console.log("calculateVatWT#: balanceDue>"+balanceDue+" received>"+received+" deduction>"+deduction+" totalCharge>"+totalCharge)
	if(deduction == 0){ console.log("step0"); return deduction;}
	if(balanceDue == received && totalCharge != balanceDue){ // Partial + Full Payment
		console.log("step1");
		var vat = calculateVatFromIncluded(received, vatRate);
		return (received - vat)  * ((isStateAgency) ? 0.01 : 0.03);
	} else if (balanceDue == received && totalCharge == balanceDue){ // Fully Payment
		console.log("step2");
		return deduction;
	} else if (balanceDue != received && totalCharge == balanceDue){ // Partial Payment
		console.log("step3");
		var vat = calculateVatFromIncluded(received, vatRate);
		return (received - vat)  * ((isStateAgency) ? 0.01 : 0.03);
	} else if (balanceDue != received && totalCharge != balanceDue){ // Partial + Partial Payment
		console.log("step4");
		var vat = calculateVatFromIncluded(received, vatRate);
		return (received - vat)  * ((isStateAgency) ? 0.01 : 0.03);
	}
}
function removeFromInvoiceList(index,invoiceNo) {
	view.table.summaryList.collapse();
	var billingList = view.session("billingList");
	for (var i = 0, m = billingList.length; i < m; i++) {
		for (var j = 0, n = billingList[i].invoiceList.length; j < n; j++) {
			var invoice = billingList[i].invoiceList[j]; 
			if (invoiceNo == invoice.no) invoice.checked=false;
		}
	}
	var invoiceLockList=[];
	var invoiceLock={
			invNo:invoice.no,
			mode:"REMOVE"
	}
	invoiceLockList.push(invoiceLock)
	$.ajax({
	  type: "POST",
	  url: "../manageDuplicatePayQueue.json",
	  data: JSON.stringify(invoiceLockList),
	  dataType: "json",
	  contentType: "application/json; charset=utf-8",
	  success:function(data){
		    view.session("billingList", billingList);
			recalculateBillingList(index);
	  }
	});
}
function recalculateBillingList(index) {
	var billingList = view.session("billingList");
	var newBillingList = [];
	var amount = 0, discount = 0, vat = 0, balanceDue = 0, totalCharge = 0, deduct = 0, perCustomerTotal = 0, itemsDiscount = 0, preItemsDiscount = 0;
	console.log("length:" + billingList.length);
	for (var i = 0, m = billingList.length; i < m; i++) {
		perCustomerTotal = 0;
		for (var j = 0, n = billingList[i].invoiceList.length; j < n; j++) {
			var invoice = billingList[i].invoiceList[j], isPartial = invoice.balanceDue - invoice.received != 0; if (!invoice.checked) continue;
			vat += invoice.calculatedVat;
			balanceDue += invoice.balanceDue;
			totalCharge += invoice.received;
			//deduct += invoice.deduction; // backup
			var isStateAgency = invoice.isStateAgency;
			deduct += calculateVatWT(invoice.balanceDue, invoice.received, invoice.vatRate, invoice.deduction, invoice.totalCharge, invoice.isStateAgency);
			discount += invoice.afterSaleDiscount;
			perCustomerTotal += invoice.received;
			itemsDiscount += invoice.discount;
			
		}
		if(perCustomerTotal == 0) {
			view.table.summaryList.removeRow(index);
		} else {
			newBillingList.push(billingList[i]);
		}
	}
	
	view.input.preDiscount.val(totalCharge - vat);
	view.input.vat.val(vat);
	view.input.totalCharge.val(totalCharge);
	view.input.preItemsDiscount.val(totalCharge - vat + itemsDiscount);
	view.input.itemsDiscount.val(itemsDiscount);
	/*if(deduct > 0){
		// Fix By EPIS8 issue no 4, 6, 9
		if(invoice.totalCharge != invoice.received) 
			view.input.deduct.val((totalCharge - vat) * ((isStateAgency) ? 0.01 : 0.03));
		else
			view.input.deduct.val(deduct);
	}*/
	// End Fix By EPIS8 issue no 4, 6, 9
	view.input.discount.val(discount);
	view.table.summaryList.data(newBillingList);
	view.session("billingList",newBillingList);
	view.button.submitPayment.disable(totalCharge == 0);
	view.table.summaryList.expand();
}
function detailFormatter(ind, row) {
	return '<table class="table table-striped table-bordered"><thead>'
		+ '<tr>'
		+ '<th class="text-center">เลขที่ใบแจ้งค่าใช้บริการ</th>'
		+ '<th class="text-center">วันที่จัดทำใบแจ้งค่าใช้บริการ</th>'
		+ '<th class="text-center">วันที่ครบกำหนด</th>'
		+ '<th class="text-right">ส่วนลด</th>' //เพิ่ม ส่วนลด
		+ '<th class="text-right">ยอดก่อนภาษี</th>'
		+ '<th class="text-right">ภาษีมูลค่าเพิ่ม</th>'
		+ '<th class="text-right">ยอดเงินรวมภาษี</th>'
		+ '<th class="text-right">จำนวนเงินคงค้าง</th>'
		+ '<th class="text-right">ยอดชำระ</th>'
		+ '<th class="text-right">ภาษีหัก ณ ที่จ่าย</th>'
		+ '<th class="text-center">รอบการใช้งาน</th>'
		+ '<th class="text-center"></th>'
		+ '</tr></thead>'
		+ '<tbody>' + $.map((row.invoiceList || []), function(v,i) {
			return !v.checked ? null : '<tr><td class="text-center">'+ v.no +'</td>'
				+ '<td class="text-center">'+ v.issueDate +'</td>'
				+ '<td class="text-center">'+ v.dueDate +'</td>'
// 				+ '<td class="text-right">'+ view.utils.numberFormat(v.afterSaleDiscount) +'</td>'
				+ '<td class="text-right">'+ view.utils.numberFormat(v.discount) +'</td>'
				+ '<td class="text-right">'+ view.utils.numberFormat(v.amount) +'</td>'
				+ '<td class="text-right">'+ view.utils.numberFormat(v.vat) +'</td>'
				+ '<td class="text-right">'+ view.utils.numberFormat(v.totalCharge) +'</td>'
				+ '<td class="text-right">'+ view.utils.numberFormat(v.balanceDue) +'</td>'
				+ '<td class="text-right">'+ view.utils.numberFormat(v.received) +'</td>'
				+ '<td class="text-right">'+ view.utils.numberFormat(calculateVatWT(v.balanceDue, v.received, v.vatRate, v.deduction, v.totalCharge, v.isStateAgency)) +'</td>'
				+ '<td class="text-center">'+ v.billCycle +'</td>'
				+ '<td class="text-center"><a href="#" onclick="removeFromInvoiceList(\''+ind+'\',\''+v.no+'\')"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span></a></td></tr>'
		}).join("") + '</tbody>'
		+ '</table>';
	}
function submitPaymentClickEvent() { location.href = 'pay-service-charge_3.jsp'; }
</script>
</html>
