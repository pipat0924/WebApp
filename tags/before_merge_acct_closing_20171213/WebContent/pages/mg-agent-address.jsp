<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>EPIS Back Office : Unit Management.</title>
    <link href="../pages/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <link href="../pages/resources/bootstrap-table/src/bootstrap-table.css" rel="stylesheet" type="text/css" />
    <link href="../pages/resources/custom.css" rel="stylesheet" type="text/css" />
    <script src="../pages/resources/jquery.min.js" type="text/javascript"></script>
    <script src="../pages/resources/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="../pages/resources/bootstrap-table/src/bootstrap-table.js"></script>
    <script src="../pages/resources/custom.js" type="text/javascript"></script>
</head>
<body>
<header class="header_page"></header>
<section class="container-fluid menu">
    <div class="row">
        <div class="col-md-12 tab-modefile">
            <ol class="breadcrumb">
                <li><i>การจัดการทั่วไป</i></li>
                <li class="active"><i>ข้อมูลตัวแทนรับชำระ</i></li>
            </ol>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12 tab-modefile">
            <div id="alertSuccessAreaMsg" class="hide alert alert-success"><span class="glyphicon glyphicon-ok-sign"></span> บันทึกเรียบร้อย</div>
            <div id="alertDangerAreaMsg" class="hide alert alert-danger"><span class="glyphicon glyphicon-remove-sign"></span> เกิดข้อผิดพลาด รหัสตัวแทนรับชำระซ้ำกัน</div>
            <!-- Search -->
            <div id="searchAgentPanel">
                <ul class="nav nav-tabs" role="tablist">
                    <li role="presentation" class="active"><a href="#"><span class="glyphicon glyphicon-filter"></span> ค้นหาหน่วยนับ</a></li>
                </ul>
                <div class="panel panel-default panal-radius">
                    <div class="panel-body">
                        <div class="tab-content">
                            <div role="tabpanel" class="tab-pane active" id="tab_1">
                                <div class="form-horizontal">
                                    <div class="form-group">
                                        <label class="control-label col-sm-2">รหัสตัวแทนรับชำระ :</label>
                                        <div class="col-sm-2"><input class="form-control" id="agentCodeForSh"></div>
                                        <label class="control-label col-sm-2">ชื่อตัวแทนรับชำระ :</label>
                                        <div class="col-sm-2"><input class="form-control" id="agentNameForSh"></div>
                                        <div class="col-sm-4">
                                            <a id="searchAgent" class="btn btn-primary"><span class="glyphicon glyphicon-search"></span> ค้นหาข้อมูล</a>&nbsp;
                                            <a id="resetSearch" class="btn btn-danger"><span class="glyphicon glyphicon-minus-sign"></span> ยกเลิก</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                            <a id="createAgent" class="btn btn-success"><span class="glyphicon glyphicon-file"></span> เพิ่มรายการ</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Display -->
            <div id="displayAgentPanel" class="hide tab-content">
                <div role="tabpanel" class="tab-pane active" id="tab-2-1">
                    <table id="agentTable" data-toggle="table" data-toolbar="#AgentToolbar" data-search="true" data-show-pagination-switch="true" data-show-refresh="true" data-show-toggle="true" data-show-columns="true" data-show-export="true" data-pagination="true" data-height="400" data-cache="false">
                        <thead>
                        <tr>
                            <th data-field="code"  data-align="left">รหัสตัวแทนรับชำระ</th>
                            <th data-field="taxId" data-align="center">รหัสผู้เสียภาษี (Tax ID)</th>
                            <th data-field="name" data-align="center">ชื่อตัวแทนรับชำระ</th>
                            <th data-field="companyName" data-align="center">ชื่อบริษัทตัวแทนรับชำระ</th>
                            <th data-field="taxIdSt" data-align="center">ตำแห่นง Tax ID เริ่มต้น</th>
                            <th data-field="taxIdEnd" data-align="center">ตำแห่นง Tax ID สิ้นสุด</th>
                            <th data-field="ref1St" data-align="center">ตำแหน่ง Ref No.1 เริ่มต้น</th>
                            <th data-field="ref1End" data-align="center">ตำแหน่ง Ref No.1 สิ้นสุด</th>
                            <th data-field="ref2St" data-align="center">ตำแหน่ง Ref No.2 เริ่มต้น</th>
                            <th data-field="ref2End" data-align="center">ตำแหน่ง Ref No.2 สิ้นสุด</th>
                            <th data-field="invoiceNoSt" data-align="center">ตำแหน่งเลขที่ใบแจ้งฯ เริ่มต้น</th>
                            <th data-field="invoiceNoEnd" data-align="center">ตำแหน่งเลขที่ใบแจ้งฯ สิ้นสุด</th>
                            <th data-field="dueDateSt" data-align="center">ตำแหน่ง Due Date เริ่มต้น</th>
                            <th data-field="dueDateEnd" data-align="center">ตำแหน่ง Due Date สิ้นสุด</th>
                            <th data-field="amountPos" data-align="center">ตำแหน่งยอดเงิน</th>
                            <th data-field="address" data-align="center">ที่อยู่ตัวแทนรับชำระ</th>
                            <th data-field="isPositive" data-formatter="isPositiveFormatter" data-align="left">สถานะ</th>
                            <th data-field="id" data-formatter="rowsFormatter" data-align="center"></th>
                        </tr>
                        </thead>
                    </table>
                </div>
            </div>
            <!-- Create -->
            <div id="createAgentPanel" class="hide">
                <ul class="nav nav-tabs" role="tablist">
                    <li role="presentation" class="active"><a href="#"><span class="glyphicon glyphicon-list"></span> เพิ่มตัวแทน</a></li>
                </ul>
                <div class="panel panel-default panal-radius">
                    <form id="createAgentFm" method="post" class="form-horizontal" role="form">
                        <div class="panel-body">
                            <div class="tab-content">
                                <div role="tabpanel" class="tab-pane active" id="tab_1">
                                    <div class="form-horizontal">
                                        <div class="form-group">
                                            <label class="control-label col-sm-2">รหัสตัวแทนรับชำระ :</label>
                                            <div class="col-sm-3"><input class="form-control" id="agentCode" name="agentCode"></div>
                                            <label class="control-label col-sm-2">ชื่อตัวแทนรับชำระ (เมนู):</label>
                                            <div class="col-sm-3"><input class="form-control" id="agentName" name="agentName"></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-2">รหัสตัวผู้เสียภาษี (Tax ID) :</label>
                                            <div class="col-sm-3"><input class="form-control" id="agentTaxId" name="agentTaxId"></div>
                                            <label class="control-label col-sm-2">ชื่อบริษัทตัวแทนรับชำระ :</label>
                                            <div class="col-sm-3"><input class="form-control" id="agentCompanyName" name="agentCompanyName"></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-2">ตำแห่นง Tax ID :</label>
                                            <div class="col-sm-2"><input class="form-control" id="taxIdSt" placeholder="ตำแหน่งเริ่มต้น"></div>
                                            <div class="col-sm-2"><input class="form-control" id="taxIdEnd" placeholder="ตำแหน่งสิ้นสุด"></div>
                                            <label class="control-label col-sm-2">ตำแหน่ง Ref No.1 :</label>
                                            <div class="col-sm-2"><input class="form-control" id="ref1St" placeholder="ตำแหน่งเริ่มต้น"></div>
                                            <div class="col-sm-2"><input class="form-control" id="ref1End" placeholder="ตำแหน่งสิ้นสุด"></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-2">ตำแหน่งเลขที่ใบแจ้งค่าใช้บริการ :</label>
                                            <div class="col-sm-2"><input class="form-control" id="invoiceNoSt" placeholder="ตำแหน่งเริ่มต้น"></div>
                                            <div class="col-sm-2"><input class="form-control" id="invoiceNoEnd" placeholder="ตำแหน่งสิ้นสุด"></div>
                                            <label class="control-label col-sm-2">ตำแหน่ง Ref No.2 :</label>
                                            <div class="col-sm-2"><input class="form-control" id="ref2St" placeholder="ตำแหน่งเริ่มต้น"></div>
                                            <div class="col-sm-2"><input class="form-control" id="ref2End" placeholder="ตำแหน่งเริ่มต้น"></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-2">ตำแหน่ง Due Date :</label>
                                            <div class="col-sm-2"><input class="form-control" id="dueDateSt" placeholder="ตำแหน่งเริ่มต้น"></div>
                                            <div class="col-sm-2"><input class="form-control" id="dueDateEnd" placeholder="ตำแหน่งสิ้นสุด "></div>
                                            <label class="control-label col-sm-2">ตำแหน่งยอดเงิน :</label>
                                            <div class="col-sm-2"><input class="form-control" id="amountSt" placeholder="ตำแหน่งเริ่มต้น"></div>
                                            <div class="col-sm-2"><input class="form-control" id="amountEnd" placeholder="ตำแหน่งสิ้นสุด "></div>
                                            
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-2">ที่อยู่ :</label>
                                            <div class="col-sm-8"><textarea class="form-control" id="address" name="address"></textarea></div>
                                        </div>
                                        <div class="form-group">
                                            <label class="control-label col-sm-2">สถานะการใช้งาน :</label>
                                            <div class="col-sm-2">
                                                <select class="form-control" id="isPositive" name="isPositive">
                                                    <option value="">- กรุณาเลือก -</option>
                                                    <option value="1">เปิดใช้งาน</option>
                                                    <option value="0">ระงับใช้งาน</option>
                                                </select>
                                            </div>
                                            <div class="col-sm-4 col-sm-offset-2">
                                                <a id="saveAgent" class="btn btn-primary"><span class="glyphicon glyphicon-plus-sign"></span> บันทึกข้อมูล</a>&nbsp;
                                                <a id="resetCreate" class="btn btn-danger"><span class="glyphicon glyphicon-minus-sign"></span> ยกเลิก</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                <a id="backToSearch" class="btn btn-success"><span class="glyphicon glyphicon-search"></span> ค้นหาข้อมูล</a>
                                            </div>
                                            <input type="hidden" id="id" name="id" value="0"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
</section>
</body>
<script>
    var view = (function($){
        function DropDown(el, url) { var obj = this, data = [{ key: "", value: "", text: "Please Select" }]; obj.el = el; obj.elem = $(el);
            obj.data = function(array) { if ($.type(array) == "array") { setup(data = array); return this } return data; };
            obj.init = function(url) { if (url) $.get(url, function(res) { setup(data = res.data) }); else setup(data); return this };
            obj.disable = function(flag) { obj.elem.attr("disabled", (flag == null ? true : flag)); return this }
            obj.enable = function() { obj.disable(false); return this }
            obj.index = function() { if (arguments.length == 1) { var maxLen = data.length, index = arguments[0]; obj.elem.find("option").each(function(i, opt){ opt.removeAttribute("selected") }).eq(index < 0 ? maxLen + index : index).attr("selected", true); return this } return $.inArray("selected", obj.elem.find("option").map(function(i, opt){ return opt.selected ? "selected" : "no-selected" })) }
            obj.selected = function(){ return data[obj.index()]; };
            obj.val = function() { return obj.elem.val(); };
            obj.key = function(){ if (arguments.length == 0) { var key = obj.elem.find("option:selected").data("key"); if (typeof key === "string" && key === "undefined") { var item = data[obj.index()]; key = item && item.key ? item.key : item.id } return key } var key = arguments[0]; for (var i = 0, m = data.length; i < m; i++) { var item = data[i]; if (item.key && item.key == key) { obj.index(i); break; } if (item.id && item.id == key) { obj.index(i); break; } } return this }
            data = obj.elem.change(window[el.substring(1) +"ChangeEvent"]).find("option").map(function(i, opt){ return { key: opt.getAttribute("data-key"), value: opt.value, text: opt.textContent } });
        }
        (function(){ $(window["setup"]) })();
        return this;
    })(jQuery);

    function runningFormatter(val, row, ind) { return ind + 1; }
    function runningMsgHide() { $('#alertSuccessAreaMsg').addClass('hide'); $('#alertDangerAreaMsg').addClass('hide'); }
    function isPositiveFormatter(val) {var target; if("0" == val) {target = "ระงับใช้งาน";} else if("1" == val) { target = "เปิดใช้งาน";} return target; }
    function rowsFormatter(id) {return '<a id="agentEdit" style="cursor:pointer" onClick="findAgentDetailById(\''+ id +'\')" class="btn-sm btn-info"><i class="glyphicon glyphicon-edit"></i> แก้ไข</a>'; }

    $(document).on("click", "a#searchAgent", function() {
        runningMsgHide();
        if($('#agentCodeForSh').val() == '' && $('#agentNameForSh').val() == '') {
            if($('#agentCodeForSh').val() == ''){ $('#agentCodeForSh').attr("style", "color:#a94442"); $("#agentCodeForSh").attr("style", "background-color:#ffffaa;border:solid 1px #a94442;padding:0 10px");}
            if($('#agentNameForSh').val() == ''){ $('#agentNameForSh').attr("style", "color:#a94442"); $("#agentNameForSh").attr("style", "background-color:#ffffaa;border:solid 1px #a94442;padding:0 10px");}
            return false;
        } else {
            $('#displayAgentPanel').removeClass('hide');
            $('#createAgentPanel').addClass('hide');
            $('#agentCodeForSh').removeAttr("style");
            $('#agentNameForSh').removeAttr("style");
            var dataSend = {
                code: $("#agentCodeForSh").val(),
                name: $("#agentNameForSh").val()
            };
            $.ajax({
                type: "POST",
                url: "../findAgentByCodeOrName.json",
                data: JSON.stringify(dataSend),
                dataType: "json",
                contentType: "application/json; charset=utf-8",
                success:function(data){
                    console.log(data);
                    $("#agentTable").bootstrapTable("load", data);
                }
            });
        }
    }).on("click", "a#createAgent", function() {
        runningMsgHide();
        $('#searchAgentPanel').addClass('hide');
        $('#displayAgentPanel').addClass('hide');
        $('#createAgentPanel').removeClass('hide');
        $('#agentCode').removeAttr("style");
        $('#agentName').removeAttr("style");
        $('#agentTaxId').removeAttr("style");
        $('#agentCompanyName').removeAttr("style");
        $('#taxIdPosos').removeAttr("style");
        $('#invoiceNoSt').removeAttr("style");
        $('#invoiceNoEnd').removeAttr("style");
        $('#ref1St').removeAttr("style");
        $('#ref1End').removeAttr("style");
        $('#ref2St').removeAttr("style");
        $('#ref2End').removeAttr("style");
        $('#dueDateSt').removeAttr("style");
        $('#dueDateEnd').removeAttr("style");
        //$('#amountPos').removeAttr("style");
        $('#amountSt').removeAttr("style");
        $('#amountEnd').removeAttr("style");
        $('#isPositive').removeAttr("style");
        $("#id").val('');
        $("#agentCode").val('');
        $("#agentName").val('');
        $("#agentTaxId").val('');
        $("#agentCompanyName").val('');
        $("#taxIdSt").val('');
        $("#taxIdEnd").val('');
        $("#invoiceNoSt").val('');
        $("#invoiceNoEnd").val('');
        $("#ref1St").val('');
        $("#ref1End").val('');
        $("#ref2St").val('');
        $("#ref2End").val('');
        $("#dueDateSt").val('');
        $("#dueDateEnd").val('');
        //$("#amountPos").val('');
        $("#amountSt").val('');
        $("#amountEnd").val('');
        
        $("#address").val('');
        $("#isPositive").val('');
    }).on("click", "a#backToSearch", function() {
        runningMsgHide();
        $('#searchAgentPanel').removeClass('hide');
        $('#displayAgentPanel').addClass('hide');
        $('#createAgentPanel').addClass('hide');
        $('#agentCode').removeAttr("style");
        $('#agentName').removeAttr("style");
        $('#agentTaxId').removeAttr("style");
        $('#agentCompanyName').removeAttr("style");
        $('#taxIdPosos').removeAttr("style");
        /* $('#invoiceNoPos').removeAttr("style");
        $('#ref1Pos').removeAttr("style");
        $('#ref2Pos').removeAttr("style");
        $('#dueDatePos').removeAttr("style"); */
        $('#invoiceNoSt').removeAttr("style")
        $('#invoiceNoEnd').removeAttr("style")
        $('#invoiceNoSt').removeAttr("style")
        $('#invoiceNoEnd').removeAttr("style")
        $('#ref1St').removeAttr("style");
        $('#ref1End').removeAttr("style");
        $('#ref2St').removeAttr("style");
        $('#ref2End').removeAttr("style");
        $('#dueDateSt').removeAttr("style");
        $('#dueDateEnd').removeAttr("style");
        //$('#amountPos').removeAttr("style");
        $('#amountSt').removeAttr("style");
        $('#amountEnd').removeAttr("style");
        $('#isPositive').removeAttr("style");
    }).on("click", "a#resetSearch", function() {
        runningMsgHide();
        $('#agentCodeForSh').removeAttr("style");
        $('#agentCodeForSh').val("");
        $('#agentNameForSh').removeAttr("style");
        $('#agentNameForSh').val("");
    }).on("click", "a#resetCreate", function() {
        runningMsgHide();
        $('#agentCode').removeAttr("style");
        $('#agentName').removeAttr("style");
        $('#agentTaxId').removeAttr("style");
        $('#agentCompanyName').removeAttr("style");
        $('#taxIdPosos').removeAttr("style");
        /* $('#invoiceNoPos').removeAttr("style");
        $('#ref1Pos').removeAttr("style");
        $('#ref2Pos').removeAttr("style");
        $('#dueDatePos').removeAttr("style"); */
        $('#invoiceNoSt').removeAttr("style");
        $('#invoiceNoEnd').removeAttr("style");
        $('#ref1St').removeAttr("style");
        $('#ref1End').removeAttr("style");
        $('#ref2St').removeAttr("style");
        $('#ref2End').removeAttr("style");
        $('#dueDateSt').removeAttr("style");
        $('#dueDateEnd').removeAttr("style");
        //$('#amountPos').removeAttr("style");
        $('#amountSt').removeAttr("style");
        $('#amountEnd').removeAttr("style");
        $('#isPositive').removeAttr("style");
        $('#createAgentFm')[0].reset();
    }).on("click", "a#saveAgent", function() {
        runningMsgHide();
        if($('#agentCode').val() == '' || $('#agentName').val() == '' || $('#isPositive').val() == '' || $('#agentTaxId').val() == '' || $('#agentCompanyName').val() == '' || $('#taxIdPos').val() == '' /*|| $('#invoiceNoPos').val() == '' || $('#ref1Pos').val() == '' || $('#ref2Pos').val() == '' || $('#dueDatePos').val() == '' || $('#amountPos').val() == ''*/) {
            if($('#agentCode').val() == '') { $('#agentCode').attr("style", "color:#a94442"); $("#agentCode").attr("style", "background-color:#ffffaa;border:solid 1px #a94442;padding:0 10px"); }
            if($('#agentName').val() == '') { $('#agentName').attr("style", "color:#a94442"); $("#agentName").attr("style", "background-color:#ffffaa;border:solid 1px #a94442;padding:0 10px"); }
            if($('#agentTaxId').val() == '') { $('#agentTaxId').attr("style", "color:#a94442"); $("#agentTaxId").attr("style", "background-color:#ffffaa;border:solid 1px #a94442;padding:0 10px"); }
            if($('#agentCompanyName').val() == '') { $('#agentCompanyName').attr("style", "color:#a94442"); $("#agentCompanyName").attr("style", "background-color:#ffffaa;border:solid 1px #a94442;padding:0 10px"); }
            if($('#taxIdPos').val() == '') { $('#taxIdPos').attr("style", "color:#a94442"); $("#taxIdPos").attr("style", "background-color:#ffffaa;border:solid 1px #a94442;padding:0 10px"); }
            /* if($('#invoiceNoPos').val() == '') { $('#invoiceNoPos').attr("style", "color:#a94442"); $("#invoiceNoPos").attr("style", "background-color:#ffffaa;border:solid 1px #a94442;padding:0 10px"); }
            if($('#ref1Pos').val() == '') { $('#ref1Pos').attr("style", "color:#a94442"); $("#ref1Pos").attr("style", "background-color:#ffffaa;border:solid 1px #a94442;padding:0 10px"); }
            if($('#ref2Pos').val() == '') { $('#ref2Pos').attr("style", "color:#a94442"); $("#ref2Pos").attr("style", "background-color:#ffffaa;border:solid 1px #a94442;padding:0 10px"); }
            if($('#dueDatePos').val() == '') { $('#dueDatePos').attr("style", "color:#a94442"); $("#dueDatePos").attr("style", "background-color:#ffffaa;border:solid 1px #a94442;padding:0 10px"); }
            if($('#amountPos').val() == '') { $('#amountPos').attr("style", "color:#a94442"); $("#amountPos").attr("style", "background-color:#ffffaa;border:solid 1px #a94442;padding:0 10px"); } */
            if($('#isPositive').val() == '') { $('#isPositive').attr("style", "color:#a94442"); $("#isPositive").attr("style", "background-color:#ffffaa;border:solid 1px #a94442;padding:0 10px"); }
            return false;
        } else {
            $('#agentCode').removeAttr("style");
            $('#agentName').removeAttr("style");
            $('#agentTaxId').removeAttr("style");
            $('#agentCompanyName').removeAttr("style");
            $('#taxIdPosos').removeAttr("style");
            /* $('#invoiceNoPos').removeAttr("style");
            $('#ref1Pos').removeAttr("style");
            $('#ref2Pos').removeAttr("style");
            $('#dueDatePos').removeAttr("style"); */
            $('#invoiceNoSt').removeAttr("style")
            $('#invoiceNoEnd').removeAttr("style")
            $('#ref1St').removeAttr("style");
            $('#ref1End').removeAttr("style");
            $('#ref2St').removeAttr("style");
            $('#ref2End').removeAttr("style");
            $('#dueDateSt').removeAttr("style");
            $('#dueDateEnd').removeAttr("style");
            //$('#amountPos').removeAttr("style");
            $('#amountSt').removeAttr("style");
        	$('#amountEnd').removeAttr("style");
            $('#isPositive').removeAttr("style");
            var dataSend={
                id:$("#id").val(),
                code:$("#agentCode").val(),
                name:$("#agentName").val(),
                taxId:$("#agentTaxId").val(),
                companyName:$("#agentCompanyName").val(),
                taxIdSt:$("#taxIdSt").val(),
                taxIdEnd:$("#taxIdEnd").val(),
                invoiceNoSt:$("#invoiceNoSt").val(),
                invoiceNoEnd:$("#invoiceNoEnd").val(),
                ref1St:$("#ref1St").val(),
                ref1End:$("#ref1End").val(),
                ref2St:$("#ref2St").val(),
                ref2End:$("#ref2End").val(),
                dueDateSt:$("#dueDateSt").val(),
                dueDateEnd:$("#dueDateEnd").val(),
                //amountPos:$("#amountPos").val(),
                amountSt:$("#amountSt").val(),
                amountEnd:$("#amountEnd").val(),
                
                address:$("#address").val(),
                isPositive:$("#isPositive").val(),
            };
            $.ajax({
                type: "POST",
                url: "../saveAgent.json",
                data: JSON.stringify(dataSend),
                dataType: "json",
                contentType: "application/json; charset=utf-8",
                success:function(data){
                    $('#createAgentFm')[0].reset();
                    $('#alertSuccessAreaMsg').removeClass('hide');
                    $('#alertDangerAreaMsg').addClass('hide');
                },
                error: function(data) {
                    $('#alertDangerAreaMsg').removeClass('hide');
                    $('#alertSuccessAreaMsg').addClass('hide');
                }
            });
        }
    });

    function findAgentDetailById(id) {
        $.get("../findAgentDetailById.json", { "id": id }, function(res) {
            $('#searchAgentPanel').addClass('hide'); $('#displayAgentPanel').addClass('hide');  $('#createAgentPanel').removeClass('hide');  $('#resetCreate').addClass('disabled');
            $("#id").val(res.id);
            $("#agentCode").val(res.code);
            $("#agentName").val(res.name);
            $("#agentTaxId").val(res.taxId);
            $("#agentCompanyName").val(res.companyName);
            $("#taxIdSt").val(res.taxIdSt);
            $("#taxIdEnd").val(res.taxIdEnd);
            $("#invoiceNoSt").val(res.invoiceNoSt);
            $("#invoiceNoEnd").val(res.invoiceNoEnd);
            $("#ref1St").val(res.ref1St);
            $("#ref1End").val(res.ref1End);
            $("#ref2St").val(res.ref2St);
            $("#ref2End").val(res.ref2End);
            $("#dueDateSt").val(res.dueDateEnd);
            $("#dueDateEnd").val(res.dueDateEnd);
            //$("#amountPos").val(res.amountPos);
            $("#amountSt").val(res.amountSt);
            $("#amountEnd").val(res.amountEnd);
            $("#address").val(res.address);
            $('#isPositive').val(res.isPositive)
        });
    }
</script>
</html>
