<%--
  Created by Moew.
  User: Administrator
  Date: 19/5/2560
  Time: 16:13
  To change this template use File | Settings | File Templates.
--%>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
        <html>

        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>EPIS Back Office : Receipt No Management.</title>
            <link href="../pages/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
            <link href="../pages/resources/bootstrap-table/src/bootstrap-table.css" rel="stylesheet" type="text/css" />
            <link href="../pages/resources/custom.css" rel="stylesheet" type="text/css" />
            <link href="../pages/resources/select2.min.css" rel="stylesheet" type="text/css" />
            <script src="../pages/resources/jquery.min.js" type="text/javascript"></script>
            <script src="../pages/resources/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
            <script src="../pages/resources/bootstrap-table/src/bootstrap-table.js"></script>
            <script src="../pages/resources/custom.js" type="text/javascript"></script>
            <script src="../pages/resources/select2.min.js" type="text/javascript"></script>

        </head>

        <body>
            <header class="header_page"></header>
            <section class="container-fluid menu">
                <div class="row">
                    <div class="col-md-12 tab-modefile">
                        <ol class="breadcrumb">
                            <li><i>การกำหนดค่าอื่นๆ</i></li>
                            <li class="active"><i>กำหนดรูปแบบเลขที่เอกสาร</i></li>
                        </ol>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12 tab-modefile">
                        <div id="alertSuccessAreaMsg" class="hide alert alert-success"><span class="glyphicon glyphicon-ok-sign"></span> บันทึกเรียบร้อย</div>
                        <div id="alertDangerAreaMsg" class="hide alert alert-danger"><span class="glyphicon glyphicon-remove-sign"></span> เกิดข้อผิดพลาด</div>
                        <!-- Search -->
                        <div id="searchUnitPanel">
                            <ul class="nav nav-tabs" role="tablist">
                                <li role="presentation" class="active"><a href="#"><span class="glyphicon glyphicon-filter"></span> ประเภทบริการ</a></li>
                            </ul>
                            <div class="panel panel-default panal-radius">
                                <div class="panel-body">
                                    <div class="tab-content">
                                        <div role="tabpanel" class="tab-pane active" id="tab_1">
                                            <div class="form-horizontal">
                                                <div class="form-group">
                                                    <label class="control-label col-sm-1">ระบบงาน :</label>
                                                    <div class="col-sm-2 select2Div">
														<select class="form-control" id="searchSystemType" name="searchSystemType">
			                                            </select></div> 
                                                    <label class="control-label col-sm-1">รหัสระบบงาน :</label>
                                                    <div class="col-sm-2 select2Div"><select id="searchSystemCode" class="form-control">
                                                    <option value="All">--กรุณาเลือก--</option>
                                                    </select></div>
                                                    <label class="control-label col-sm-1">ชื่อเอกสาร :</label>
                                                    <div class="col-sm-2 select2Div"><select id="searchDocName" class="form-control">
                                                    <option value="All">--กรุณาเลือก--</option>
                                                    </select></div>
                                                    <div class="col-sm-3">
                                                     <a id="searchUnit" class="btn btn-primary"><span class="glyphicon glyphicon-search"></span> ค้นหาข้อมูล</a>&nbsp;
      <!--                                                      <a id="resetSearch" class="btn btn-danger"><span class="glyphicon glyphicon-minus-sign"></span> ยกเลิก</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                            <a id="createUnit" class="btn btn-success"><span class="glyphicon glyphicon-file"></span> เพิ่มรายการ</a> --> 
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- Display -->
                        <div id="displayUnitPanel" class="hide tab-content">
                            <div role="tabpanel" class="tab-pane active" id="tab-2-1">
                                <table id="unitTable" data-toggle="table" data-toolbar="#UnitToolbar" data-search="true" data-show-pagination-switch="true"
                                    data-show-refresh="true" data-show-toggle="true" data-show-columns="true" data-show-export="true"
                                    data-pagination="true" data-height="320" data-cache="false">
                                    <thead>
                                        <tr>
                                          		<!-- <th data-field="id" data-align="left" >id</th> -->
                                                <th data-field="docNo" data-align="left">รหัสเอกสาร</th>
                                                <th data-field="docName" data-align="left">ชื่อเอกสาร</th>
                                                <th data-field="branchCode" data-align="left">รหัสสาขา</th>
                                                <th data-field="posNo" data-align="left">หมายเลขเครื่อง</th>
                                                <th data-field="docType" data-align="left">ประเภทเอกสาร</th>
                                                <th data-field="dateFormat" data-align="left">รูปแบบวันที่ </th>
                                                <th data-field="bdEraFlag" data-align="left">ปีพุทธศักราช</th>
                                                <th data-field="runningNo" data-align="left">runningNo</th>
                                                <th data-field="systemCode" data-align="left">รหัสระบบงาน</th>
                                                <th data-field="systemType" data-align="left">ระบบงาน</th>
                                                <th data-field="format" data-align="left">รูปแบบเลขที่เอกสาร</th>
                                                <th data-field="status"  data-align="left">สถานะ</th>
                                                <th data-field="id" data-formatter="rowsFormatter" data-align="center"></th>
                                        </tr>
                                    </thead>
                                </table>
                            </div>
                        </div>
                        <!-- Create -->
                        <div id="createUnitPanel" class="hide">
                            <ul class="nav nav-tabs" role="tablist">
                                <li role="presentation" class="active"><a href="#"><span class="glyphicon glyphicon-list"></span> เพิ่มข้อมูลบริการ</a></li>
                            </ul>
                           <div id="alertRepeatInfo" class="hide alert alert-danger"><span class="glyphicon glyphicon-remove-sign"></span> ข้อมูซ้ำโปรดตรวจสอบข้อมูล</div>
                            
                            <div class="panel panel-default panal-radius">
                                <form id="createUnitFm" method="post" class="form-horizontal" role="form">
                                <br>
                                 <label id="caution">      *หมายเหตุ - xxx แทนจำนวนความยาวของตัวอักษร</label>
                                 <br>
                                
                                    <div class="panel-body">
                                        <div class="tab-content">
                                            <div role="tabpanel" class="tab-pane active" id="tab_1">
                                                <div class="form-horizontal">
                                                    <div class="form-group">
                                                        <label class="control-label col-sm-2">รหัสเอกสาร :</label>
                                                        <div class="col-sm-1"><input class="form-control" id="inputDocNo" name="inputDocNo" disabled></div>
                                                    	<input class="form-control" id="inputId" name="inputId" type="hidden">
                                                    	<label class="control-label col-sm-1">ชื่อเอกสาร :</label>
                                                         <div class="col-sm-3 "><input class="form-control" id="inputDocName" name="inputDocName" ></div>
                                                    </div>
                                                    <div class="form-group">
                                                    	<label class="control-label col-sm-2">ระบบงาน :</label>
                                                    	<div class="col-sm-3 select2Div">
														<select class="form-control" id="inputSystemType" name="inputSystemType">
			                                            </select></div> 
			                                        </div>
                                                     <!-- <div class="form-group">
                                                         <label class="control-label col-sm-2">ชื่อเอกสาร :</label>
                                                         <div class="col-sm-3 "><input class="form-control" id="inputDocName" name="inputDocName" ></div>
													 </div> -->
													 <div class="form-group">
                                                          <label class="control-label col-sm-2">รหัสระบบงาน:</label>
                                                          <div class="col-sm-3 select2Div">
                                                          <select class="form-control" id="inputSystemCode" name="inputSystemCode">
                                                          </select></div><label class="control-label">(SYSTEMCODE)</label>
													</div>
													<div class="form-group">
                                                          <label class="control-label col-sm-2">รหัสสาขา:</label>
                                                          <div class="col-sm-3"><input class="form-control" id="inputBranchCode" name="inputBranchCode" >
                                                         </div> <label class="control-label">(BRANCH)</label>
													</div>
                                                    <div class="form-group">
                                                     	  <label class="control-label col-sm-2">หมายเลขเครื่อง:</label>
                                                          <div class="col-sm-3"><input class="form-control" id="inputPosNo" name="inputPosNo" ></div>
														  <label class="control-label">(POS)</label>
                                                     </div>
                                                     <div class="form-group">
                                                          <label class="control-label col-sm-2">ประเภทเอกสาร:</label>
                                                          <div class="col-sm-3"><input class="form-control" id="inputDocType" name="inputDocType" ></div>
                                                          <label class="control-label">(DT)</label>
													</div>
													<div class="form-group">
                                                           <label class="control-label col-sm-2">รูปแบบวันที่ :</label>
                                                            <div class="col-sm-3 select2Div">
                                                            	<select class="form-control" id="inputDateFormat" name="inputDateFormat">
                                                                 <option value="">--กรุณาเลือก--</option>
                                                                 <option value="DDMMYY">DDMMYY</option>
                                                                 <option value="DDMMYYYY">DDMMYYYY</option>
                                                                 <option value="YYMMDD">YYMMDD</option>
                                                                 <option value="YYYYMMDD">YYYYMMDD</option>
                                                                 </select>
                                                             </div>
                                                            <div class="col-sm-1 "><label class="control-label">(DATE)</label></div>
                                                            <div class="col-sm-5 "><input type="checkbox" id="inputBdEraFlag" >
                                                           <label class="control-label">ปีพุทธศักราช</label></div>
                                                     </div>
                                                     <div class="form-group" >
                                                            <label class="control-label col-sm-2">เลขที่เอกสาร:</label>
                                                             <div class="col-sm-3"><input  class="form-control" id="inputRunningNo" name="inputRunningNo"></div>
                                                             <label class="control-label">(RUNNINGNO)</label>
                                                     </div>
                                                     <div class="form-group">
                                                             <label class="control-label col-sm-2">รูปแบบเลขที่เอกสาร</label>
                                                             <div class="col-sm-4" ><input id="inputFormat" name="inputFormat" class="form-control"></div>
                                                     </div>
												     <div class="form-group">
                                                              <label class="control-label col-sm-2">สถานะ :</label>
                                                              <div class="col-sm-2 select2Div"><select class="form-control" id="inputCheckStatus"
                                                                                name="inputCheckStatus">
			                                                <option value="ACTIVE">เปิดใช้งาน</option>
			                                                <option value="INACTIVE">ระงับใช้งาน</option>
			                                            </select></div>
			                                          </div>
			                                          <input  class="form-control" type="hidden" id="checkDocType" name="checkDocType">
													  <div class="col-sm-5">
                                                           <a id="save" class="btn btn-primary"><span class="glyphicon glyphicon-plus-sign"></span> บันทึกข้อมูล</a>&nbsp;
                                                           <a id="edit" class="btn btn-primary"><span class="glyphicon glyphicon-edit"></span> บันทึกข้อมูล</a>&nbsp;
                                                          <!--  <a id="resetCreate" class="btn btn-danger"><span class="glyphicon glyphicon-minus-sign"></span> ยกเลิก</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; -->
                                                           <!-- <a id="backToSearch" class="btn btn-success"><span class="glyphicon glyphicon-search"></span> ค้นหาข้อมูล</a> -->
                                                      </div>
                                                   </div>
                                                </div>
                                            </div>
                                        </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    </div>
            </section>
        </body>
        <script type="text/javascript">
        $(document).ready(function () {
        	document.getElementById("caution").style.color = "red";
        	document.getElementById('searchSystemCode').disabled = true;        
        	document.getElementById('searchDocName').disabled = true;
        	document.getElementById("inputBdEraFlag").checked = false;
        });
        var view = (function ($) {
            function DropDown(el, url) {
                var obj = this, data = [{ key: "", value: "", text: "Please Select" }]; obj.el = el; obj.elem = $(el);
                obj.data = function (array) { if ($.type(array) == "array") { setup(data = array); return this } return data; };
                obj.init = function (url, param) { if (url) $.get(url, { "groupKey": param }, function (res) { setup(data = res, param) }); else setup(data, param); return this };
                obj.disable = function (flag) { obj.elem.attr("disabled", (flag == null ? true : flag)); return this }
                obj.enable = function () { obj.disable(false); return this }
                obj.index = function () { if (arguments.length == 1) { var maxLen = data.length, index = arguments[0]; obj.elem.find("option").each(function (i, opt) { opt.removeAttribute("selected") }).eq(index < 0 ? maxLen + index : index).attr("selected", true); return this } return $.inArray("selected", obj.elem.find("option").map(function (i, opt) { return opt.selected ? "selected" : "no-selected" })) }
                obj.selected = function () { return data[obj.index()]; };
                obj.val = function () { return obj.elem.val(); };
                obj.key = function () { if (arguments.length == 0) { var key = obj.elem.find("option:selected").data("key"); if (typeof key === "string" && key === "undefined") { var item = data[obj.index()]; key = item && item.key ? item.key : item.id } return key } var key = arguments[0]; for (var i = 0, m = data.length; i < m; i++) { var item = data[i]; if (item.key && item.key == key) { obj.index(i); break; } if (item.id && item.id == key) { obj.index(i); break; } } return this }
                
                obj.initV2 = function(url)  { if (url)
                	$.get(url, function(res) {
                		setupV1(data = res.data)
                		}
                	); else setupV1(data); return this };
                    obj.initV3 = function(url,param) { if (url)
                    	$.ajax({
                    	    url : url,
                    	    type : "get",
                    	    data: { "groupKey": param },
                    	    async: false,
                    	    success : function(res) {
                    	    	setup(data = res, param)
                    	    },
                    	    error: function() {
                    	       connectionError();
                    	    }
                    	 });
                    else setupV1(data); return this };
                function setupV1(array) 
                { obj.elem.find("*").remove();
                obj.elem.append('<option data-index="-1" data-key="All" value="All">--กรุณาเลือก--</option>');
        		$.each(array,function(i,o){ 
        			obj.elem.append('<option data-index="'+ i +'" data-key="'+ o.key +'" value="'+ o.value +'">'+ (o.text || o.value) +'</option>') 
        			}); 
        		}
                function setup(array, param) {
                    obj.elem.find("*").remove();
                    $.each(array, function (i, o) {
                        if (o.category == 'payothers.service.unit') {
                            obj.elem.append('<option data-index="-1" data-key="All" value="All">ไม่เลือก</option>');
                            return false;
                        }
                        else {
                            obj.elem.append('<option data-index="-1" data-key="All" value="All">--กรุณาเลือก--</option>');
                            return false;
                        }
                    });
                    $.each(array, function (i, o) {

                        if (o.category == 'payothers.service.category') {
                            obj.elem.append('<option data-index="' + i + '" data-key="' + o.code + '" value="' + o.descFullTh + '">' + o.descFullTh + '</option>')
                        }
                        else {
                            obj.elem.append('<option data-index="' + i + '" data-key="' + o.key + '" value="' + o.value + '">'  + o.value + '</option>')
                        }
                    });
                }
                data = obj.elem.change(window[el.substring(1) + "ChangeEvent"]).find("option").map(function (i, opt) { return { key: opt.getAttribute("data-key"), value: opt.value, text: opt.textContent } });
            }
            function Input(el, maxLen, propPos) {
                var obj = this; obj.el = el; obj.elem = $(el);
                obj.error = function (flag) { if (arguments.length == 0 || flag) obj.elem.parent().addClass("has-error"); else obj.elem.parent().removeClass("has-error"); return this }
                obj.clear = function () { obj.val(""); return this }; obj.isEmpty = function () { return $.trim(obj.val()) === "" }; obj.isNumeric = function () { return $.isNumeric(obj.val()) }
                obj.nextFocus = function (nextElem) { if (nextElem && "Input|Button".indexOf(nextElem.constructor.name) > -1) { obj.elem.keyup(function (e) { var key = (e.which || e.keyCode || e.charCode || 0); if (key == 13) nextElem.elem.focus(); return true }) } return this };
                obj.click = function (func) { obj.elem.click(func); return this }
                obj.readOnly = function (flag) { obj.elem.attr("readonly", (flag == null ? true : flag)); return this }
                obj.disable = function (flag) { obj.elem.attr("disabled", (flag == null ? true : flag)); return this }
                obj.enable = function () { obj.disable(false); return this }
                obj.val = function () { if (arguments.length == 1) { this.elem.val(arguments[0]) } return $.trim(this.elem.val()) }
                obj.get = function (propName) { if ($.type(propPos[propName]) !== "array" || propPos[propName].length !== 2) return ""; return obj.val().substring(propPos[propName][0], propPos[propName][1]) }
                obj.elem.blur(window[el.substring(1) + "BlurEvent"]); obj.elem.keyup(function (e) { var func = (window[el.substring(1) + "KeyUpEvent"] || function () { }); func((e.which || e.keyCode || e.charCode || 0), obj.elem) }); obj.elem.focus(function () { this.select() }); if ($.isNumeric(maxLen)) { obj.elem.attr("maxLength", maxLen) }
            }
            
                (function () { $(window["setup"]) })();
                self.searchSystemCode = new DropDown("#searchSystemCode");
                self.searchDocName = new DropDown("#searchDocName"); 
                self.searchSystemType = new DropDown("#searchSystemType").initV2("../DocNoConf/findSystemType.json");
                self.inputSystemType = new DropDown("#inputSystemType").initV2("../DocNoConf/findSystemType.json");
                self.inputId = new Input("#inputId");
                self.inputDocNo = new Input("#inputDocNo");
                self.inputDocName = new Input("#inputDocName");
                self.inputBranchCode = new Input("#inputBranchCode");
                self.inputPosNo = new Input("#inputPosNo");
                self.inputDocType = new Input("#inputDocType");
                self.inputRunningNo = new Input("#inputRunningNo");
                self.inputFormat = new Input("#inputFormat");
                self.inputDateFormat = new DropDown("#inputDateFormat");
                self.inputCheckStatus = new DropDown("#inputCheckStatus");
                self.inputBdEraFlag = new Input("#inputBdEraFlag");
                self.checkDocType = new Input("#checkDocType");
                self.inputSystemCode = new DropDown("#inputSystemCode");
                return this;
            
        
        })(jQuery);
        
            $("#searchSystemType").select2();
			$("#searchSystemCode").select2();
            $("#searchDocName").select2();
            $("#inputDateFormat").select2();
			$("#inputCheckStatus").select2();
            $("#inputSystemType").select2();
         
            $('.select2Div span.select2-container').removeAttr('style');
			
            $(document).on("click", "a#searchUnit", function () {
                runningMsgHide();
                
                   // $('#displayUnitPanel').removeClass('hide');
                    //$('#createUnitPanel').addClass('hide');
                    
                    console.log("systemtype : "+view.searchSystemType.key() + " systemCode : "+view.searchSystemCode.val() + " docName : "+ view.searchDocName.val());
                    $.ajax({
                        type: "GET",
                        url: "../DocNoConf/searchDocNoCof.json?systemType="+view.searchSystemType.key()+"&systemCode="+ view.searchSystemCode.val()+"&docName="+view.searchDocName.val(),
                        dataType: "json",
                        contentType: "application/json; charset=utf-8",
                        success: function (data) {
                            console.log(data);
                            $('#displayUnitPanel').removeClass('hide');
                            $("#unitTable").bootstrapTable("load", data);
                        }
                    }); 
                
            }).on("click", "a#backToSearch", function () {
                runningMsgHide();
                location.reload();
                $('#searchUnitPanel').removeClass('hide'); $('#displayUnitPanel').addClass('hide'); $('#createUnitPanel').addClass('hide');
                $('#unitCode').removeAttr("style"); $('#unitName').removeAttr("style");
            }).on("click", "a#resetSearch", function () {
                runningMsgHide();
                $('#inputRevenueType1').val('').trigger('change.select2');
                $('#inputProduct1').val('').trigger('change.select2');
            }).on("click", "a#resetCreate", function () {
                runningMsgHide();
                $('#inputGL').removeAttr("style");
                $('#inputSubProduct').removeAttr("style");
                $('#inputRevenueType').removeAttr("style");
                $('#createUnitFm')[0].reset();
            }).on("click", "a#edit", function () {
                runningMsgHide();
                if (view.inputSystemType.key() == "All" || view.inputSystemCode.key() == "All" || view.inputDocName.val() == "" || view.checkDocType.val()== "1") {
                	if (view.inputSystemType.key() == "All") { $('#inputSystemType').attr("style", "color:#a94442"); $("#inputSystemType").attr("style", "background-color:#ffffaa;border:solid 1px #a94442;padding:0 10px"); }
                    if (view.inputSystemCode.val() == "All") { $('#inputSystemCode').attr("style", "color:#a94442"); $("#inputSystemCode").attr("style", "background-color:#ffffaa;border:solid 1px #a94442;padding:0 10px"); }
                    if (view.inputDocName.val() == "") { $('#inputDocName').attr("style", "color:#a94442"); $("#inputDocName").attr("style", "background-color:#ffffaa;border:solid 1px #a94442;padding:0 10px"); }
                    if (view.checkDocType.val() == "1") { $('#alertRepeatInfo').removeClass('hide');  $('#inputDocType').attr("style", "color:#a94442"); $("#inputDocType").attr("style", "background-color:#ffffaa;border:solid 1px #a94442;padding:0 10px"); }
                    return false;
                } else {
                    $('#inputSystemType').removeAttr("style"); $('#inputSystemCode').removeAttr("style"); $('#inputDocName').removeAttr("style");$('#inputDocType').removeAttr("style");
                                  
                    if ($('#inputBdEraFlag').is(":checked"))
                    {  var flag = "Y"  }else{  var flag = "N" }
                    
                    var dataSend = {
                        id: view.inputId.val(),
                        docNo: view.inputDocNo.val(),
                        docName: view.inputDocName.val(),
                        branchCode: view.inputBranchCode.val(),
                        posNo: view.inputPosNo.val(),
                        docType: view.inputDocType.val(),
                        dateFormat: view.inputDateFormat.val(),
                        bdEraFlag: flag,
                        runningNo: view.inputRunningNo.val(),
                        systemType: view.inputSystemType.key(),
                        systemCode: view.inputSystemCode.val(),
                        format: view.inputFormat.val(),
                        status: view.inputCheckStatus.val()
                    };
                    $.ajax({
                        type: "POST",
                        url: "../DocNoConf/saveDocNoConf.json",
                        data: JSON.stringify(dataSend),
                        dataType: "json",
                        contentType: "application/json; charset=utf-8",
                        success: function (data) {
                        	 $('#alertSuccessAreaMsg').removeClass('hide');
                            $('#alertDangerAreaMsg').addClass('hide');

                            console.log("hey");
                        },
                        error: function (dd) {
                        	 console.log("dd"+dd);
                            $('#alertDangerAreaMsg').removeClass('hide');
                            $('#alertSuccessAreaMsg').addClass('hide');
                            
                        }
                    });
                }
            }).on("change", "#searchSystemType", function () {
            	console.log($('#searchSystemType option:selected').val())
                console.log($('#searchSystemCode option:selected').val())
                console.log($('#searchDocName option:selected').val())
                var systemType = view.searchSystemType.key();
                var systemCode = view.searchSystemCode.val();
                var docName = view.searchDocName.val();
				var systemDropdown;                
                $.get( "../DocNoConf/findBySystemType.json?systemType="+systemType, 
                		function(res){
                			if (res.data.length>0) {
                				$("#searchSystemCode").find('option').remove();
                				$("#searchDocName").find('option').remove();
                               $('#searchDocName').append('<option data-index="-1" data-key="All" value="All">--กรุณาเลือก--</option>');
                                $('#searchSystemCode').append('<option data-index="-1" data-key="All" value="All">--กรุณาเลือก--</option>');
                                $.each(res.data, function (i, o) { 
									$('#searchSystemCode').append('<option data-index="'+ i +'" data-key="'+ o.systemCode +'" value="'+ o.systemCode +'">'+ o.systemCode +'</option>')
									//$('#searchSystemCode option[data-key=' + o.key + ']').prop('selected', true).trigger('change.select2');
									$('#searchDocName').append('<option data-index="'+ i +'" data-key="'+ o.docName +'" value="'+ o.docName +'">'+ o.docName +'</option>')

                                });
                            }
                			document.getElementById("searchSystemCode").disabled = false;
                            }); 

            }).on("change", "#searchSystemCode", function () {
                console.log($('#searchSystemType option:selected').val())
                console.log($('#searchSystemCode option:selected').val())
                console.log($('#searchDocName option:selected').val())
                var systemType = view.searchSystemType.key();
                var systemCode = view.searchSystemCode.val();
                var docName = view.searchDocName.val();
				var systemDropdown;                
                $.get( "../DocNoConf/findBySystemCode.json?systemCode="+systemCode, 
                		function(res){
                			if (res.data.length>0) {
                				$("#searchDocName").find('option').remove();
                               $('#searchDocName').append('<option data-index="-1" data-key="All" value="All">--กรุณาเลือก--</option>');
                                console.log("co : "+res.data );
                                $.each(res.data, function (i, o) { 
									$('#searchDocName').append('<option data-index="'+ i +'" data-key="'+ o.docName +'" value="'+ o.docName +'">'+ o.docName +'</option>')
                                });
                            }
                			document.getElementById("searchDocName").disabled = false;
                            }); 

            }).on("change", "#inputSystemType", function () {
                console.log($('#inputSystemType option:selected').val())
                 var systemType = view.inputSystemType.key();
 				var systemDropdown;
                 console.log(" sa systemType : " +systemType);
                 if(systemType == "AGENT"){
                 	systemDropdown = "../findAllAgentType.json"
                 }else{
                 	systemDropdown = "../MasterData/findByGroupKey.json?groupKey=SYSTEM_PAY"
                 }
                 console.log(systemDropdown); 
                 $.get( systemDropdown, 
                 		function(res){
                 			if (res.data.length>0) {
                 				$("#inputSystemCode").find('option').remove();
                                 $('#inputSystemCode').append('<option data-index="-1" data-key="All" value="All">--กรุณาเลือก--</option>');
                                 $.each(res.data, function (i, o) {
 									$('#inputSystemCode').append('<option data-index="'+ i +'" data-key="'+ (o.key||o.code) +'" value="'+ (o.value || o.code) +'">'+ (o.value || o.code) +'</option>')
 									//$('#inputSystemCode option[data-key=' + o.key + ']').prop('selected', true).trigger('change.select2');
                                 });
                             }
                             }); 
             }).on("change", "#inputDocType", function () {
                 
                 var docType = view.inputDocType.val();
                 $.get( "../DocNoConf/findByDocType.json?docType="+docType, 
                 		function(res){
                 			if (res.data.length>0) {
                 				$.each(res.data, function (i, o) {
                 					console.log("view.inputId.val() : "+view.inputId.val() +" res.data.id : " +o.id );
                 					if(o.id == view.inputId.val()){
                 					view.checkDocType.val("0");
                 					}else{
                 					view.checkDocType.val("1");                            
                 					}
                 				});
                 			}else{
                 					view.checkDocType.val("0"); 
                 				}
                 			});                          
             });

            function runningFormatter(val, row, ind) { return ind + 1; }
            function runningMsgHide() { $('#alertSuccessAreaMsg').addClass('hide'); $('#alertDangerAreaMsg').addClass('hide'); }
            function isPositiveFormatter(val) { var target; if (0 == val) { target = "ระงับใช้งาน"; } else if (1 == val) { target = "เปิดใช้งาน"; } return target; }
            function rowsFormatter(id) { return '<a id="unitEdit" style="cursor:pointer" onClick="findById(\'' + id + '\')" class="btn-sm btn-info"><i class="glyphicon glyphicon-edit"></i> แก้ไข</a>'; }
            function findById(id) {
                $.get("../DocNoConf/findById.json?id="+id, function (response) {
                    $('#searchUnitPanel').removeClass('hide');
                    $('#displayUnitPanel').removeClass('hide');
                    $('#createUnitPanel').removeClass('hide');
                    $('#resetCreate').addClass('disabled');
                    $('#save').addClass('hide');
                    res = response[0];
                    var systemCode= res.systemCode;

                    view.inputId.val(res.id)
                    view.inputDocNo.val(res.docNo);
                    view.inputDocName.val(res.docName);
                    view.inputBranchCode.val(res.branchCode)
                    view.inputPosNo.val(res.posNo);
                    view.inputDocType.val(res.docType);
                    view.inputRunningNo.val(res.runningNo);
                    view.inputFormat.val(res.format);
                    
                    $('#inputDateFormat option[value=' + res.dateFormat + ']').prop('selected', true).trigger('change.select2');
                    $('#inputCheckStatus option[value=' + res.status + ']').prop('selected', true).trigger('change.select2');
                    $('#inputSystemType option[data-key=' + res.systemType + ']').prop('selected', true).trigger('change.select2');
                    //$('#inputSystemCode option[value=' + res.docType + ']').prop('selected', true).trigger('change.select2');

                  
                    if(res.bdEraFlag == "Y"){
                        document.getElementById("inputBdEraFlag").checked = true;
                    }
                   
                    if(res.systemType == "AGENT"){
                     	var systemDropdown = "../findAllAgentType.json"
                     }else{
                     	var systemDropdown = "../MasterData/findByGroupKey.json?groupKey=SYSTEM_PAY"
                     }
                 
                     console.log(systemDropdown); 
                     $.get( systemDropdown, 
                     		function(res){
                     			if (res.data.length>0) {
                     				$("#inputSystemCode").find('option').remove();
                                     $('#inputSystemCode').append('<option data-index="-1" data-key="All" value="All">--กรุณาเลือก--</option>');
                                     $.each(res.data, function (i, o) {
                                    	 $('#inputSystemCode').append('<option data-index="'+ i +'" data-key="'+ (o.value||o.code) +'" value="'+ (o.value || o.code) +'">'+ (o.value || o.code) +'</option>')
     									//$('#inputSystemCode').append('<option data-index="'+ i +'" data-key="'+ value +'" value="'+ value +'">'+ value +'</option>')
										//$('#inputSystemCode option[value=' + value+ ']').prop('selected', true).trigger('change.select2');
     									$('#inputSystemCode option[value=' + systemCode + ']').prop('selected', true).trigger('change.select2');
                                     });
                                 }
                   }); 
                });
            }

        </script>

        </html>