<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
                <li><i>รับชำระอื่นๆ</i></li>
                <li>ค้นหาข้อมูลลูกค้า</li>
                <li class="active">สรุปการรับชำระเงิน</li>
                <li>เลือกวิธีการรับชำระ</li>
                <li>ผลการรับชำระ</li>
            </ol>
        </div>
    </div>
    <ul class="list-inline pull-right">
        <li><a href="pay-other_1.jsp"><span class="glyphicon glyphicon-user"></span> เพิ่มข้อมูลลูกค้า</a></li>
        <li><a href="pay-other_3.jsp"><span class="glyphicon glyphicon-edit"></span> ดำเนินการรับชำระ</a></li>
    </ul>
    <div class="row">
        <div class="col-md-12">
            <div class="panel panel-success">
                <div class="panel-heading"><span class="glyphicon glyphicon-shopping-cart"></span> สรุปการรับชำระเงิน</div>
                <div class="panel-body">
                    <table id="tableBillingList" data-row-style="rowStyle" data-toggle="table"
                           data-classes="table table-hover table-striped">
                        <thead>
                        <tr>
                            <th data-align="center" data-field="id" data-formatter="runningFormatter">#</th>
                            <th data-field="custNo">เลขที่ลูกค้า</th>
                            <th data-field="custName">ชื่อลูกค้า</th>
                            <th data-field="serviceType">ประเภทบริการ</th>
                            <th data-field="serviceName">ชื่อบริการ</th>
                            <th data-field="serviceMoreData" data-align="right">จำนวนรายการ</th>
                            <th data-field="serviceAmount" data-align="right" data-formatter="numberFormatter">จำนวนเงินต่อหน่วย</th>
                            <th data-field="serviceDiscount" data-align="right" data-formatter="numberFormatter">เงินส่วนลด</th>
                            <th data-field="serviceVat" data-align="right" data-formatter="numberFormatterForVat">ภาษีมูลค่าเพิ่ม</th>
                            <th data-field="serviceDeduction" data-align="right" data-formatter="numberFormatter">ภาษีหัก ณ ที่จ่าย</th>
                            <th data-field="serviceTotalCharge" data-align="right" data-formatter="numberFormatter">ยอดเงินรวม</th>
                            <th  data-align="center" data-formatter="removeFromInvoiceListFormatter"></th>
                        </tr>
                        </thead>
                    </table><br/>
                    <div class="form-horizontal">
                        <!-- <input id="inputAmount" class="form-control text-right" disabled> -->
                        <!-- <input class="form-control text-right" id="inputTotalCharge2" disabled> -->
                        <div class="form-group">
                            <label class="control-label col-sm-10" >ยอดเงินก่อนหักส่วนลด :</label>
                            <div class="col-sm-2">
                                <input id="inputPreDiscount" class="form-control text-right" disabled="disabled">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-10" >ส่วนลด :</label>
                            <div class="col-sm-2">
                                <input id="inputDiscount" class="form-control text-right" disabled="disabled">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-10">ส่วนลดพิเศษ  :</label>
                            <div class="col-sm-2">
                                <input id="discount" class="form-control text-right" disabled="disabled">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-10">ยอดเงินที่ต้องชำระก่อนภาษีมูลค่าเพิ่ม  :</label>
                            <div class="col-sm-2"><input id="inputCharge" class="form-control text-right" disabled></div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-10">ภาษีมูลค่าเพิ่ม  :</label>
                            <div class="col-sm-2"><input id="inputVat" class="form-control text-right" disabled></div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-10">ยอดเงินที่ต้องชำระรวมภาษีมูลค่าเพิ่ม :</label>
                            <div class="col-sm-2"><input id="inputTotalCharge" class="form-control text-right" disabled></div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-10">ภาษีหัก ณ ที่จ่าย :</label>
                            <div class="col-sm-2"><input id="inputDeduction" class="form-control text-right" disabled></div>
                        </div>
                        <!--
                        <div class="form-group">
                            <label class="control-label col-sm-10"><input type="checkbox" name="checkboxAdditionalDiscount" disabled>&nbsp;&nbsp;ลูกค้ารับภาระภาษี &nbsp;&nbsp;<span class="glyphicon glyphicon-lock"></span>&nbsp;ส่วนลด :</label>
                            <div class="col-sm-2"><input id="inputAdditionalDiscount" class="form-control text-right" disabled></div>
                        </div>
                        -->
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
        window.runningFormatter = function(val, row, ind) { return ind + 1 };
        window.stripToNumber = function(str) { return $.type(str) === "string" && $.isNumeric(str = str.replace(/[,]/g, "")) ? parseFloat(str, 10) : 0 }
        window.numberFormatter = function(val, row, ind) { return !$.isNumeric(stripToNumber(val)) ? "0.00" : parseFloat(val, 10).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,') };
        window.numberFormatterForVat = function(val, row, ind) {
            if(val == '0'){
                return "0.00";
            }else if(val == null){
                return "-";
            }
            else{
                return !$.isNumeric(stripToNumber(val)) ? "0.00" : parseFloat(val, 10).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,')
            }
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
        function BootstrapTable(el) { var obj = this, checkEvt = function(e){ console.log(e) }, uncheckEvt = checkEvt; obj.el = el; obj.elem = $(el);
            obj.clear = function() { obj.elem.bootstrapTable("removeAll"); return obj }
            obj.showLoad = function() { this.elem.bootstrapTable("showLoading"); return this }; obj.hideLoad = function() { this.elem.bootstrapTable("hideLoading"); return this };
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
            obj.remove = function(ind){ obj.elem.bootstrapTable('remove', { field  : 'id', values : [ind] }); return this; }
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
            obj.sum = function(index) { var sum = 0; this.elem.find("tbody tr").each(function(i, row){ var val = row.children[index].innerText.replace(/[,]/g, ""); sum += (isNaN(val) ? 0 : parseFloat(val, 10)) }); return sum }
            $(obj.el).on("click", ".delList", function(){ $(this).parent().parent().remove(); $.each(headers, function(i,p){ if (p.runningNo) reorder(i) }); onDel(obj.data()) });
            obj.elem.removeClass("table").removeClass("table-hover").addClass("table").addClass("table-hover");
            obj.elem.find("thead th").each(function(i,o){ var elem = $(o); headers.push({ "field": elem.data("field"), "valign": $.trim(elem.data("valign")), "align": $.trim(elem.data("align")), "modifyButton": elem.data("modifyButton") === true, "removeButton": elem.data("removeButton") === true, "runningNo": elem.data("runningNo") === true, "numberFormat": elem.data("numberFormat") === true, "checkbox": elem.data("checkbox") === true, "radio": elem.data("radio") === true, "input": elem.data("input") === true }) });
            function replace(str, style, field, index, value){ var s = str; if (style) s = s.replace("\{style\}", style); return s.replace("\{field\}", $.trim(field)).replace("\{index\}", index).replace("\{value\}", value) }
            function extract(prop, col) { if (prop.checkbox) { var elem = col.children[0].children[0]; return !elem.checked ? "" : elem.value } else if (prop.radio) { var elem = col.children[0].children[0]; return !elem.checked ? "" : elem.value } else if (prop.input) { var elem = col.children[0].children[0]; return elem.value } return col.children[0].innerText }
            if(obj.body.length < 1) { obj.elem.append("<tbody></tbody>"); obj.body = obj.elem.find("tbody") }
        }
        (function(){ $(window["setup"]) })();
        self.inputPreDiscount = new NumberInput("#inputPreDiscount");
        self.inputDiscount = new NumberInput("#inputDiscount");
        self.discount = new NumberInput("#discount");

        self.inputAmount = new NumberInput("#inputAmount");
        self.inputAdditionalDiscount = new NumberInput("#inputAdditionalDiscount");
        self.inputCharge = new NumberInput("#inputCharge");
        self.inputVat = new NumberInput("#inputVat");
        self.inputTotalCharge = new NumberInput("#inputTotalCharge");
        self.inputTotalCharge2 = new NumberInput("#inputTotalCharge2");
        self.inputDeduction = new NumberInput("#inputDeduction");
        self.tableBillingList = new BootstrapTable("#tableBillingList");
        return this;
    })(jQuery);

    function setup() {
        var billingList = view.session("billingList"), specialDiscount = 0, amount = 0, discount = 0, charge = 0, vat = 0, totalCharge = 0, deduction = 0;
        var vatRate = parseFloat(view.session("vatRate"));
        view.tableBillingList.clear();
        $.each(billingList, function(i,o){
            var custNo = o.custNo;
            var custName = o.custName;
            specialDiscount += o.specialDiscount;
            $.each(o.serviceList, function(j,p){
                amount += (p.serviceAmount * p.serviceMoreData);
                discount += p.serviceDiscount;
                if(p.serviceVat != null){
                    vat += p.serviceVat;
                }
                deduction += p.serviceDeduction;
                view.tableBillingList.insert({"id": view.tableBillingList.data().length,"custNo": custNo, "custName": custName, "serviceType": p.serviceType, "serviceName": p.serviceName, "serviceMoreData": p.serviceMoreData, "serviceAmount": p.serviceAmount, "serviceDiscount": p.serviceDiscount, "serviceVat": p.serviceVat, "serviceDeduction": p.serviceDeduction, "serviceTotalCharge": p.serviceTotalCharge});
            });
        });
        view.inputAmount.val(amount);
        view.inputPreDiscount.val(amount);
        view.inputDiscount.val(discount);
        view.discount.val(specialDiscount);
        view.inputAdditionalDiscount.val(discount);
        view.inputCharge.val(charge = amount - discount - specialDiscount);
        vat = (charge*vatRate)/100;
        view.inputVat.val(vat);
        view.inputTotalCharge.val(totalCharge = charge + vat);
        view.inputDeduction.val(deduction);
        //  view.inputDeduction.val(0.00);
        view.inputTotalCharge2.val(totalCharge);
        view.inputAdditionalDiscount.val(specialDiscount);
    }
    function tableBillingListModifyEvent() { console.log(arguments); }
    function tableBillingListRemoveEvent() { console.log(arguments); }
    function  removeFromInvoiceList(ind) {
        /*console.log(" removeFromInvoiceList ["+ind+"]")
         var table=$("#tableBillingList");
         table.bootstrapTable('remove', {
         field: 'id',
         values: [ind]
         });*/
        //setup();
        var billingList = view.session("billingList")
        console.log(billingList);
        var serviceIndex=0;
        for (var i = 0; i <  billingList.length; i++) {
            for (var j = 0; j  < billingList[i].serviceList.length; j++) {
                //if(billingList[i].serviceList[j].id==ind)
                if(serviceIndex==ind)
                    billingList[i].serviceList.splice(j,1);
                serviceIndex++;
            }
            var service_size = billingList[i].serviceList.length;
            if(service_size==0)
                billingList.splice(i,1);
        }
        view.session("billingList",billingList);
        console.log(billingList);
        // view.tableBillingList.remove(ind);
        //view.buttonSummaryPayment.disable(view.tableBillingList.data().length <= 0);
        //view.buttonProcessPayment.disable(view.tableBillingList.data().length <= 0);
        //calculate();
        setup();
    }
    function removeFromInvoiceListFormatter(val, row, ind) {
        console.log("row --> ");
        console.log(row);
        return '<a  style="cursor: pointer" onclick="removeFromInvoiceList('+row.id+')"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span></a>';
        //return '<a href="#"  onclick="removeFromInvoiceList("+ row.billNo +","+row.acctNo+")'>เลือก</a>";
    }
</script>