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
                            <li><i>เช็คขัดข้อง</i></li>
                            <li>รับชำระลูกหนี้ SAP รายตัว</li>
                            <li class="active">สรุปการรับชำระเงิน</li>
                            <li>สรุปการรับชำระเงิน</li>
                            <li>เลือกวิธีการรับชำระ</li>
                            <li>ผลการรับชำระ</li>
                        </ol>
                    </div>
                </div>
                <ul class="list-inline pull-right list-menu-set">
                    <li><a href="pay-bounceCheque.jsp" class="btn btn-link"><span class="glyphicon glyphicon-user"></span> เพิ่มข้อมูลลูกค้า</a></li>
                    <li><a href="pay-bounceCheque3.jsp" id="submitPayment" class="btn btn-link"><span class="glyphicon glyphicon-edit"></span> ดำเนินการรับชำระ</a></li>
                </ul>
                <div class="row">
                    <div class="col-md-12">
                        <div class="panel panel-success">
                            <div class="panel-heading">สรุปการรับชำระเงิน</div>
                            <div class="panel-body">
                                <table id="summaryList" data-row-style="rowStyle" data-toggle="table" data-detail-view="true" data-detail-formatter="detailFormatter"
                                    data-classes="table table-hover table-striped">
                                    <thead>
                                        <tr>
                                            <th data-align="center" data-formatter="runningFormatter">#</th>
                                            <th data-field="arAccountCode">รหัสลูกหนี้</th>
                                            <th data-field="arName">ชื่อลูกหนี้</th>
                                            <th data-field="glAccount">บัญชีกระทบยอด</th>
                                            <th data-formatter="docHead">จำนวนใบแจ้งหนี้<br>(Doc.Header Text)</th>
                                            <th data-formatter="amountAROut">จำนวนเงินตามสกุลที่บันทึก<br>(ตปท.)ที่ต้องชำระ</th>
                                            <th data-formatter="amountARIn">จำนวนเงินสกุล<br>ในประเทศที่ต้องชำระ</th>
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
                                <div class="col-sm-9">
                                    <div class="form-group">
                                        <label class="control-label col-sm-12">จำนวนเงินตามสกุลที่บันทึก</label>

                                        <div class="form-group">
                                            <label class="control-label col-sm-1"></label>
                                            <label class="control-label col-sm-8">ยอดเงินที่ต้องชำระก่อนภาษีมูลค่าเพิ่ม  :</label>
                                            <div class="col-sm-3"><input id="sumBalance2" class="form-control text-right" disabled></div>
                                        </div>

                                        <div class="form-group">
                                            <label class="control-label col-sm-1"></label>
                                            <label class="control-label col-sm-8">ภาษีมูลค่าเพิ่ม  :</label>
                                            <div class="col-sm-3"><input id="sumVat2" class="form-control text-right" disabled></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-1"></label>
                                            <label class="control-label col-sm-8">ยอดเงินที่ต้องชำระรวมภาษีมูลค่าเพิ่ม :</label>
                                            <div class="col-sm-3"><input id="sumTotalCharge2" class="form-control text-right" disabled></div>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-sm-3">
                                    <div class="form-group">
                                        <label class="control-label col-sm-12">จำนวนเงินสกุลในประเทศ</label>

                                        <div class="form-group">
                                            <label class="control-label col-sm-3"></label>
                                            <div class="col-sm-9"><input id="sumBalance" class="form-control text-right" disabled></div>
                                        </div>

                                        <div class="form-group">
                                            <label class="control-label col-sm-3"></label>
                                            <div class="col-sm-9"><input id="sumVat" class="form-control text-right" disabled></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-3"></label>
                                            <div class="col-sm-9"><input id="sumTotalCharge" class="form-control text-right" disabled></div>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>

                    </div>
                </div>

        </section>

    </body>

    </html>
    <script type="text/javascript">
        var view = (function ($) {
            var self = this, errorMsgEl = "#errorMsg", defaultErrorMessage = "An error occurred but there is no message response.";
            self.session = function (key, val) { if (!val) { var val = window.sessionStorage.getItem(key); return val && (val.indexOf("{") > -1 || val.indexOf("[") > -1) ? JSON.parse(val) : val } window.sessionStorage.setItem(key, ($.type(val) != "object" && $.type(val) != "array" ? val : JSON.stringify(val))) };
            self.input = new (function () {
                var that = this;
                that.preDiscount = new NumberInput("#preDiscount");
                that.preItemsDiscount = new NumberInput("#preItemsDiscount");
                that.itemsDiscount = new NumberInput("#itemsDiscount");
                that.discount = new NumberInput("#discount");
                that.vat = new NumberInput("#vat");
                that.totalCharge = new NumberInput("#totalCharge");
                that.deduct = new NumberInput("#deduct");
                that.sumBalance = new NumberInput("#sumBalance");
                that.sumBalance2 = new NumberInput("#sumBalance2");
                that.sumTotalCharge = new NumberInput("#sumTotalCharge");
                that.sumTotalCharge2 = new NumberInput("#sumTotalCharge2");
                that.sumVat = new NumberInput("#sumVat");
                that.sumVat2 = new NumberInput("#sumVat2");
                that.val = function () { if (arguments.length == 1) { $.each(arguments[0], function (k, v) { $("#" + k).val(v) }) } };
                function Input(el) {
                    this.el = el;
                    this.elem = $(el);
                    this.val = function () { if (arguments.length == 1) { this.elem.val(arguments[0]) } return this.elem.val() };
                }
                function NumberInput(el, dec) {
                    var decimal = (dec || 2), obj;
                    this.el = el;
                    this.elem = obj = $(el);
                    this.val = function () { if (arguments.length == 0) return parseFloat(strip(this.elem.val() || "0"), 10); this.elem.val(format(arguments[0])); }
                    this.decimal = function (dec) { decimal = dec };
                    this.format = format;
                    this.elem.keypress(function (e) { var key = (e.which || e.keyCode || e.charCode || 0); var ch = String.fromCharCode(key); return "0123456789.".indexOf(ch) > -1 });
                    this.elem.focus(function () { this.select() });
                    this.elem.blur(function () { this.value = format(this.value) });
                    function format(val) { return parseFloat(val || "0", 10).toFixed(decimal).replace(/(\d)(?=(\d{3})+\.)/g, '$1,'); };
                    function strip(str) { return (str || "").replace(/[,]/g, "") }
                    this.elem.val("0.00");
                }
            });
            self.table = new (function () {
                var that = this;
                that.summaryList = new BootstrapTable("#summaryList");
                function BootstrapTable(el) {
                    var obj = this, checkEvt = function (e) { console.log(e) }, uncheckEvt = checkEvt;
                    obj.el = el;
                    obj.elem = $(el);
                    obj.clear = function () { obj.elem.bootstrapTable("removeAll"); return obj }
                    obj.showLoad = function () { this.elem.bootstrapTable("showLoading"); return this };
                    obj.hideLoad = function () { this.elem.bootstrapTable("hideLoading"); return this };
                    obj.data = function () { if (arguments.length == 1) { this.elem.bootstrapTable("load", arguments[0]); } return this.elem.bootstrapTable("getData") };
                    obj.selected = function () { return this.elem.bootstrapTable("getSelections") };
                    obj.sum = function (isAll, colName) { var sum = 0; $.each(this.elem.bootstrapTable(isAll ? "getData" : "getSelections"), function (i, o) { sum += (o[colName] || 0) }); return sum };
                    obj.sumInput = function (index) { var sum = 0; obj.elem.find("tbody tr").each(function (i, o) { var val = o.children[index].children[0].value.replace(/[,]/g, ""); sum += (!$.isNumeric(val) ? 0 : parseFloat(val, 10)) }); return sum }
                    obj.expand = function () { obj.elem.find(".detail-icon i.icon-plus").click(); return this };
                    obj.collapse = function () { obj.elem.find(".detail-icon i.icon-minus").click(); return this };
                    obj.checkboxEvent = function (func) { this.elem.on("check.bs.table uncheck.bs.table check-all.bs.table uncheck-all.bs.table", func); return this };
                    obj.checkEvent = function (func) { checkEvt = func; return this };
                    obj.uncheckEvent = function (func) { uncheckEvt = func; return this };
                    obj.checkboxEvent(function (e) { if (e.type === "check") checkEvt(e); if (e.type === "uncheck") uncheckEvt(e) });
                    obj.removeRow = function (index) { obj.elem.find("tbody").each(function (i, o) { o.children[index].remove(); }); return this; };
                }
                //// AUTOMATIC REGISTER FORMATTER FUNCTION ////
                window.runningFormatter = function (val, row, ind) { return ind + 1 }
                window.numberFormatter = function (val, row, ind) { return !$.isNumeric(val) ? "0.00" : parseFloat(val, 10).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,') }
                window.stripToNumber = function (str) { return $.type(str) === "string" && $.isNumeric(str = str.replace(/[^0-9\.]/g, "")) ? parseFloat(str, 10) : 0 }
                window.stringInputFormatter = function (val, row, ind) { return '<input value="' + $.trim(val) + '" maxLength="40" class="form-control">' }
                window.numberInputFormatter = function (val, row, ind) { return '<input value="' + (val || "0.00") + '" maxLength="10" class="form-control text-right" onkeypress="return $.isNumeric(String.fromCharCode(event.which)) || (event.which == 46 && this.value.indexOf(\'.\') == -1)" onfocus="(function(o){ o.value = o.value.replace(/[,]/g, \'\') })(this)" onblur="(function(o){ o.value = parseFloat(o.value, 10).toFixed(2).replace(/(\\d)(?=(\\d{3})+\\.)/g, \'$1,\'); })(this)">' }
                window.numberFormatterWithCurrency = function (val, row, ind) {
                    var currencyCode = "฿";
                    if (row.currencyCode == "1") {
                        currencyCode = "$";
                    }
                    return !$.isNumeric(stripToNumber(val)) ? "0.00" + currencyCode : parseFloat(val, 10).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,') + currencyCode
                }
                window.numberFormatter2 = function (val, row, ind) {
                    return !$.isNumeric(stripToNumber(val)) || val == 0 ? "-" : parseFloat(val, 10).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,')
                }
            });
            self.utils = {
                numberFormat: function (num, dec) { return $.type(num) !== "number" ? "0.00" : num.toFixed(dec || 2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,'); }
            };

            self.button = new (function () {
                var that = this;
                that.submitPayment = new Button("#submitPayment");
                function Button(el) {
                    var obj = this, badge; obj.el = el; obj.elem = $(el);
                    obj.hide = function () { this.elem.addClass("hide"); return this }; obj.show = function () { this.elem.removeClass("hide"); return this };
                    obj.hideLoad = function () { obj.elem.button("reset"); return this }; obj.showLoad = function () { obj.elem.button("loading"); return this };
                    obj.disable = function (flag) { if (flag == null || flag) this.elem.addClass("disabled").attr("disabled", true); else this.elem.removeClass("disabled").attr("disabled", false); return this }; obj.enable = function () { obj.disable(false); return this };
                    obj.badge = function (val) { if (badge) badge.text(val) }; if ((badge = this.elem.next()).length == 0) badge = null;
                    obj.elem.click(window[el.substring(1) + "ClickEvent"]);
                }
            });
            return this;
        })(jQuery);

        var vatRDGET;
        var rateChangeRDGet;
        $(document).ready(function () {
            var bounceCheque = view.session("bounceCheque");

            vatRDGET = bounceCheque[0].vatRD;
            rateChangeRDGet = bounceCheque[0].rDGet;
            var sumVat = 0, sumVat2 = 0, sumBalance = 0, sumBalance2 = 0, sumTotalCharge = 0, sumTotalCharge2 = 0;
            for (var rowid = 0; rowid < bounceCheque.length; rowid++) {
                for (var i = 0; i < bounceCheque[rowid].invoiceList.length; i++) {
                    // var amountChange = bounceCheque[rowid].invoiceList[i]["amountARin"];
                    // var amountChange2 = bounceCheque[rowid].invoiceList[i]["amountARout"];

                    // sumTotalCharge += amountChange;
                    // sumTotalCharge2 += amountChange2;

                    // var amountARout = calculateVatFromIncluded(bounceCheque[rowid].invoiceList[i]["amountARin"]);
                    // var amountARin = calculateVatFromIncluded(bounceCheque[rowid].invoiceList[i]["amountARout"]);

                    // sumBalance += amountARout;
                    // sumBalance2 += amountARin;

                    // sumVat += stripToNumberWithFormat(amountChange - amountARout);
                    // sumVat2 += stripToNumberWithFormat(amountChange2 - amountARin);
                    //
                    var amountChange = bounceCheque[rowid].invoiceList[i]["amountARin"];
                    var amountChange2 = bounceCheque[rowid].invoiceList[i]["amountARout"];

                    var amountCaloutS = stripToNumberWithFormat(bounceCheque[rowid].invoiceList[i]["amountPay"]);
                    var rateChange = rateChangeRDGet
                    
                    // var receivedValue = stripToNumberWithFormat($("#received_" + rowid).val());
                    var amountBath = (rateChange*amountCaloutS);

                    sumTotalCharge += (amountBath);
                    sumTotalCharge2 += (amountCaloutS);

                    var amountARin = calculateVatFromIncluded((amountBath));
                    var amountARout = calculateVatFromIncluded(amountChange2);

                    var amountARoutCal = calculateVatFromIncluded((amountCaloutS));

                    sumBalance += amountARin;
                    sumBalance2 += amountARoutCal;

                    sumVat += stripToNumberWithFormat((amountBath) - amountARin);
                    sumVat2 += stripToNumberWithFormat((amountCaloutS) - amountARoutCal);
                }
            }
            view.table.summaryList.data(bounceCheque);
            view.table.summaryList.expand();

            // view.input.sumBalance.val(sumBalance);
            // view.input.sumBalance2.val(sumBalance2);
            $('#sumBalance').val(view.utils.numberFormat(sumBalance));
            $('#sumBalance2').val(view.utils.numberFormat(sumBalance2));
            $('#sumVat').val(view.utils.numberFormat(sumVat));
            $('#sumVat2').val(view.utils.numberFormat(sumVat2));
            $('#sumTotalCharge').val(view.utils.numberFormat(sumTotalCharge));
            $('#sumTotalCharge2').val(view.utils.numberFormat(sumTotalCharge2));
        });

        function docHead(val, row, ind) {
            return !row.invoiceList || row.invoiceList.length == 0 ? 0 : $.map(row.invoiceList, function (inv, i) { return inv.docHead ? inv : null }).length
        }

        function amountARIn(val, row, ind) {
            var amountARPay = 0;
            var changeRate = 0;
            var amountPayGetRate = 0;
            for(var i=0; i<row.invoiceList.length; i++) {
                changeRate = rateChangeRDGet;
                amountARPay = stripToNumberWithFormat(row.invoiceList[i].amountPay);
                amountPayGetRate += (amountARPay*changeRate);
            }
            return view.utils.numberFormat(amountPayGetRate);
        }

        function amountAROut(val, row, ind) {
            var amountARPay = 0;
            for(var i=0; i<row.invoiceList.length; i++) {
                amountARPay += stripToNumberWithFormat(row.invoiceList[i].amountPay);
            }
            return view.utils.numberFormat(amountARPay);
        }

        function subAmountBra(number, digits) {
            var multiplier = Math.pow(10, digits),
                adjustedNum = number * multiplier,
                truncatedNum = Math[adjustedNum < 0 ? 'ceil' : 'floor'](adjustedNum);

            return truncatedNum / multiplier;
        };

        function stripToNumberWithFormat(str) {
            var strFormat = str + "";
            strFormat = strFormat.replace(/[^0-9\.]/g, "");
            return parseFloat(strFormat, 10);
        }

        function calculateVatFromIncluded(ammount) {
            var rtVatBalance = 0;
            var sumVat = (100+vatRDGET);
            rtVatBalance = (ammount * 100) / sumVat;
            return rtVatBalance;
        }

        function detailFormatter(ind, row) {
            return '<table class="table table-striped table-bordered"><thead>'
                + '<tr>'
                + '<th class="text-center">เลขที่ใบแจ้งหนี้<br>(Doc. Header Text)</th>'
                + '<th class="text-center">รอบการใช้งาน<br>(การอ้างอิง)</th>'
                + '<th class="text-center">บริการ<br>(คีย์อ้างอิง3)</th>'
                + '<th class="text-center">สกุลเงิน</th>'
                + '<th class="text-center">จำนวนเงินตามสกุลที่บันทึก<br>(ตปท.)</th>'
                + '<th class="text-center">อัตราแลกเปลี่ยน</th>'
                + '<th class="text-center">จำนวนเงินสกุลในประเทศ</th>'
                + '<th class="text-center">ยอดเงินคงเหลือตามสกุลที่บันทึก</th>'
                + '<th class="text-center">ยอดชำระตามสกุลที่บันทึก</th>'
                + '<th class="text-center">วันที่แปลงค่า</th>'
                + '<th class="text-center">เลขที่เอกสาร</th>'
                + '<th class="text-center">ปีบัญชี</th>'
                + '<th class="text-center">วันที่เอกสาร</th>'
                + '<th class="text-center">วันที่ผ่านรายการ</th>'
                + '<th class="text-center">รหัสภาษี</th>'
                + '<th class="text-center">ข้อความ (AW)</th>'
                + '</tr></thead>'
                + '<tbody>' + $.map((row.invoiceList || []), function (v, i) {
                    return '<tr><td class="text-center">' + v.docHead + '</td>'
                        + '<td class="text-center">' + v.refDate + '</td>'
                        + '<td class="text-center">' + v.serviceKey3 + '</td>'
                        + '<td class="text-center">' + v.currency + '</td>'
                        + '<td class="text-center">' + view.utils.numberFormat(v.amountARout) + '</td>'
                        + '<td class="text-center">' + view.utils.numberFormat(v.rateChange) + '</td>'
                        + '<td class="text-center">' + view.utils.numberFormat(v.amountARin) + '</td>'
                        + '<td class="text-center">' + view.utils.numberFormat(v.amountTotalPay) + '</td>'
                        + '<td class="text-center">' + view.utils.numberFormat(stripToNumberWithFormat(v.amountPay)) + '</td>'
                        + '<td class="text-center">' + v.currencyChangeDate + '</td>'
                        + '<td class="text-center">' + v.docNo + '</td>'
                        + '<td class="text-center">' + v.accountYear + '</td>'
                        + '<td class="text-center">' + v.docDate + '</td>'
                        + '<td class="text-center">' + v.passDate + '</td>'
                        + '<td class="text-center">' + v.vatCode + '</td>'
                        + '<td class="text-center">' + v.message + '</td>'
                        // + '<td class="text-center"><a href="#" onclick="removeFromInvoiceList(\'' + ind + '\',\'' + v.no + '\')"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span></a></td></tr>'
                }).join("") + '</tbody>'
                + '</table>';
        }
    </script>